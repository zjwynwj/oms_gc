/**
 * addPurReceipt.ftl 页面引用JS
 */
var curRow = null; // 选择的全局变量,标记当前选择的是哪一行
var curCol = null; // 选择的全局变量,标记当前选择的是哪一列
var lasterSel; // 保存最后选择的一行的行id
$(function() {
	//业务日期初始化
	$("#datepicker").val(oms.formatDate(new Date()));
    // 单据设置列表表格
    jQuery("#blackId").jqGrid({
        datatype: "json",
        url: base + '/template/purOrder/queryPurOrderDetailList.jhtml',
        postData: {"id":$("#purOrderId").val()},
        mtype: 'POST',
        colNames: ['序号', 'gdsId', '商品编号', '商品名称','规格代码', '规格', '属性', '单位', '订单数量','已入库数量', '出库数量','库位编码', '库位编码id', '备注'],
        colModel: [
            {name: 'id', index: 'id', hidden: true},
            {name: 'gdsId', index: 'gdsId', hidden: true  },
            {name: 'gdsShowInfo', index: 'gdsShowInfo', width: 200,
	            formatter: function(cellvalue, options, rowObject) {
	                return "<input value='" + cellvalue + "' width='200px' class='bindSelect' name='gdsInfoDetail' id=gdsShowInfo_" + options.rowId + ">";
	            }
	        },
	        {name: 'gdsName', index: 'gdsName', width:100},
	        {name: 'skuOuterId', index: 'skuOuterId', width:100},
	        {name: 'gdsFormat', index: 'gdsFormat', width:100},
	        {name: 'attbs', index: 'attbs', width:100},
	        {name: 'cal',index: 'cal'},
	        {name: 'purAmount',index: 'purAmount'},
	        {name: 'purRealAmount',index: 'purRealAmount'},
	        {name: 'amount',index: 'amount',editable: true},
	        {name: 'warehousePositionsName',index: 'warehousePositionsName',width: 300,
	            formatter: function(cellvalue, options, rowObject) {
	            	if(typeof(cellvalue) == "undefined"){
	            		cellvalue = "";
	            	}
	                return "<input value='" + cellvalue + "' width='300px' class='bindSelect' name='warehousePositionsName' id=warehousePositionsName_" + options.rowId + ">";
	            }
	        },
	        {name: 'warehousePositionsId',index: 'warehousePositionsId',hidden: true},
	        {name: 'remarks',index: 'remarks',editable: true}
	    ],
        rowNum: 50,
        pager: '#pager',
        toolbarfilter: true,
        height: '100%',
        viewrecords: true,
        autowidth: true,
        rownumbers: true,
        // cellurl:base+"/template/receipt/save_receipt_details.jhtml",
        prmNames: { // 默认发送参数格式设置
            page: "pageNum",
            rows: "rows"
        },
        prmNames: {
            page: "pageNum",
            // 表示请求页码的参数名称
            rows: "rows",
            // 表示请求行数的参数名称
            sort: "page.oid",
            // 表示用于排序的列名的参数名称
            order: "page.oor",
            // 表示采用的排序方式的参数名称
            search: "issearch",
            // 表示是否是搜索请求的参数名称
            nd: "nd",
            // 表示已经发送请求的次数的参数名称
            state: "isstate"
        },
        jsonReader: { // 返回数据格式设置
            root: "data",
            repeatitems: false
        },
        loadComplete: function(a) {
            var ids = jQuery("#blackId").jqGrid('getDataIDs');
            for (var i = 0; i < ids.length; i++) {
                var gdsShowInfoObj = $("#" + $($("#blackId").getCell(ids[i], "gdsShowInfo")).attr("id"));
                var warehousePositionsName = $("#" + $($("#blackId").getCell(ids[i], "warehousePositionsName")).attr("id"));
                bindSelect2(gdsShowInfoObj);
                bindWarehousePositionsName(warehousePositionsName);
                $(gdsShowInfoObj).prev("div").find(".select2-chosen").text($(gdsShowInfoObj).val());
                //$(warehousePositionsName).prev("div").find(".select2-chosen").text($(warehousePositionsName).val());
                jQuery("#blackId").setCell(ids[i],"amount",0);
            }
            if (a == null || a.total == 0) {
                addCol('1');
            }
        },
        ajaxGridOptions: {
            timeout: oms.ajaxTimeout
        },
        // 统一超时时间
        successTip: false,
        beforeProcessing: oms.grid.ajaxSuccessFn,
        loadError: oms.grid.ajaxErrorFn,
        multiselect: false,
        'cellsubmit': 'clientArray',
        // 不进行ajax提交
        'afterEditCell': afterEditCellFn,
        'cellEdit': true
        // 设置可编辑单元格
    });

    // 业务时间
    $("#datepicker").datepicker({
        showOn: "button",
        buttonImage: base + "/dinghao/template/front/js/elem/jqueryui/css/images/calendar.gif",
        buttonImageOnly: true
    });
  

    // 自动生成编号
    // 单据初始化
    var options = {};
    options.params = {"nuType": "05"};
    // 没有订单编号 自动生成
    options.url = base + "/template/baseNumber/findBaseNumberNext.jhtml";
    options.successCallback = successBackFn;
    oms.ajaxCall(options);
    function successBackFn(data) {
        $("#receiptCodeShow").html(data.data);
        $("#receiptCode").val(data.data);
    }
    /**
     * 弹出供应商
     */
    $("#remarks").on("click",
	    function() {
	        oms.s_addPop("供应商选择", base + "/template/custInfo/get_custinfomgr.jhtml", "", 600, 400);
	    });
	    // 浏览器大小发生变化的时候，重新计算jqgrid的宽度
	    $(window).resize(function() {
	        oms.grid.mdetailconWidth("purOrderGridId");
	    });
	    $("#cancelButton").click(function() {
	        history.go( - 1);
    });
	    
	    
	    
}); // jquery 结束
function gridDataisPerfect() {
    var warehouseName = $("#warehouseName").val();
    if (warehouseName == '') {
        $.artDialog({
            title: '消息',
            content: '请先选择入库仓库'
        });
        $("#warehouseName").focus();
        return;
    }
    var purData = $("#blackId").getRowData();
    var totalAmout = 0;
    for (var i in purData) {
    	var row = purData[i];
        if ($.trim(row.warehousePositionsId) == "" || $.trim(row.warehousePositionsId) == undefined) {
            oms.tooltip("请选择库位信息!", "error");
            return false;
        }
    	if(parseFloat(oms.numSub(row.amount , row.purRealAmount)) > 0){
    		oms.tooltip("出库数量不能超过当前的已入库数量!", "error");
            return false;
    	}
    	totalAmout += row.amount;
    }
    if(parseFloat(totalAmout) == 0){
		oms.tooltip("出库数量不能为0","error" , 5000);
		return false;
	}
    return true;
}

function bindSelect2(e) {
    e.select2({
        placeholder: "请输入商品编号/名称",
        minimumInputLength: 1,
        multiple: false,
        width: '200',
        formatResult: function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
            return result.gdsShowInfo;
        },
        formatSelection: function(item) { // 选中后显示在文本框中的值
            var rowId = $(e).attr("id").split("_")[1];
            var attbs = new Array();
            var gdsAttbs = " ";
            if (item.attbs != "" && item.attbs != "undefined" && typeof(item.attbs) != "undefined") {
                attbs = item.attbs.split(";");
                for (var i in attbs) {
                    gdsAttbs += ";" + attbs[i].split(":")[2] + ":" + attbs[i].split(":")[3];
                }
                gdsAttbs = gdsAttbs.substring(2, gdsAttbs.length);
            }
            jQuery("#blackId").setCell(rowId, "gdsInfoFormat", gdsAttbs);
            jQuery("#blackId").setCell(rowId, "gdsName", item.gdsName);
            jQuery("#blackId").setCell(rowId, "gdsFormat", item.gdsFormat);
            jQuery("#blackId").setCell(rowId, "skuOuterId", item.skuOuterId);
            jQuery("#blackId").setCell(rowId, "gdsInfoId", item.id);
            $("#gdsShowInfo_" + rowId).val(item.gdsShowInfo);
            jQuery("#blackId").setCell(rowId, "gdsShowInfo", item.gdsShowInfo);
            return item.gdsShowInfo;
        },
        dropdownCssClass: "bigdrop",
        // apply css that makes the dropdown
        escapeMarkup: function(m) {
            return m;
        },
        ajax: {
            url: base + "/template/gdsMgr/findGdsInfoForGrid.jhtml",
            delay: 250,
            dataType: 'json',
            data: function(term, page) {
                return {
                    keyWord: term,
                    // 汉字处理方式
                    pageNum: 1,
                    // 页码
                    rows: 10,
                    // 数量
                };
            },
            results: function(data, page) {
                return {
                    results: data.data.list
                };
            }
        },
        allowClear: true
        // 单选
    });
}

/**
 * 获取库位编码
 */
// 智能提示
function bindWarehousePositionsName(e) {
    $(e).select2({
        placeholder: "请输库位名称",
        minimumInputLength: 1,
        multiple: false,
        width: '200',
        formatResult: function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
            return result.warehousePositionsCode;
        },
        formatSelection: function(item) { // 选中后显示在文本框中的值
            var rowId = $(e).attr("id").split("_")[1];
            jQuery("#blackId").setCell(rowId, "warehousePositionsId", item.id);
            $("#warehousePositionsName_" + rowId).val(item.warehousePositionsCode);
            jQuery("#blackId").setCell(rowId, "warehousePositionsCode", item.warehousePositionsCode);
            return item.warehousePositionsCode;
        },
        dropdownCssClass: "bigdrop",
        // apply
        escapeMarkup: function(m) {
            return m;
        },
        ajax: {
            url: base + "/template/warehouse/get_warehouses_detail_select2.jhtml",
            delay: 250,
            dataType: 'json',
            data: function(term, page) {
                return {
                    warehousePositionsCode: encodeURI(decodeURIComponent(term, true)),
                    // 汉字处理方式
                    pageNum: 1,
                    // 页码
                    rows: 15,
                    // 数量
                    dhWarehousePositions: $("#warehouseId").val(),
                    t: (new Date()).valueOf()
                };
            },
            results: function(data, page) {
                return {
                    results: data
                };
            }
        },
        allowClear: true
        // 单选
    });
}

// 点击空白处，保存正在编辑的数据
$('html').bind('click',
function(e) { // 用于点击其他地方保存正在编辑状态下的行
    if (lasterSel != "") {
        if ($(e.target).closest('#blackId').length == 0) {
            $("#blackId").jqGrid("saveCell", curRow, curCol);
            lasterSel = "";
        }
    }
    if ($(e.target).attr("myLable") == "savePurOrder") {
        savePurOrder();
    }
});
function afterEditCellFn(rowid, cellname, value, iRow, iCol) {
    curRow = iRow;
    curCol = iCol;
    lasterSel = rowid;
    if(curCol == 8){
		var item = $('#blackId').find('tr').eq(curRow).find('td').eq(curCol).find('input');
		item.attr("maxlength" , 8);
		bindJqgridJustNumberEvent(item);
	}
}

function savePurOrder() {
    // 入库单ID (新增或修改)
    if (!gridDataisPerfect()) {
        return;
    }
    
    var receiptDetailVos = {};
    receiptDetailVos["warehouseId"] = $("#warehouseId").val();
   // receiptDetailVos["warehouseName"] = $("#warehouseName").val();
    receiptDetailVos["providerId"] =  $("#providerId").val();
    receiptDetailVos["purOrderId"] =  $("#purOrderId").val();
    receiptDetailVos["receiptCode"] =  $("#receiptCodeShow").text();
    receiptDetailVos["serviceType"] =  $("#serviceType").val();
    receiptDetailVos["serviceNum"] =  $("#serviceNum").val();
    receiptDetailVos["handledPerson"] =  $("#handledPerson").val();
    receiptDetailVos["receiptType"] =  $("#receiptType").val();
    receiptDetailVos["createDate"] =  $("#datepicker").val();
    
    
    var griddata = $("#blackId").getRowData();
    for (var i in griddata) {
    	receiptDetailVos["receiptDetailVos[" + i + "].putOrderDetailId"] = griddata[i].id;
        receiptDetailVos["receiptDetailVos[" + i + "].gdsInfoId"] = griddata[i].gdsId;
        receiptDetailVos["receiptDetailVos[" + i + "].gdsInfoDetail"] = $(griddata[i].gdsShowInfo).find(".select2-chosen").html();
        receiptDetailVos["receiptDetailVos[" + i + "].warehousePositionsName"] = $(griddata[i].warehousePositionsName).find(".select2-chosen").html();
        receiptDetailVos["receiptDetailVos[" + i + "].gdsInfoFormat"] = griddata[i].gdsFormat;
        receiptDetailVos["receiptDetailVos[" + i + "].gdsInfoCal"] = griddata[i].cal;
        receiptDetailVos["receiptDetailVos[" + i + "].amount"] = griddata[i].amount;
        receiptDetailVos["receiptDetailVos[" + i + "].warehousePositionsId"] = griddata[i].warehousePositionsId;
        receiptDetailVos["receiptDetailVos[" + i + "].remarks"] = griddata[i].remarks;
    }
    var options = {};
    options.params = receiptDetailVos;
    options.url = base + "/template/purOrder/savePurOrderReceiptOrOutBound.jhtml";
    options.successCallback = function(data){
    	oms.tooltip(data.errMsg,"succeed");
    	
    	artTabs({
		    closeTab: true,
		});
    };
    oms.ajaxCall(options);
}