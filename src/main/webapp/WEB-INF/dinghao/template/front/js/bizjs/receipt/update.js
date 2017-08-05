/**
 * add_update.ftl 页面引用JS
 */
var curRow = null; // 选择的全局变量,标记当前选择的是哪一行
var curCol = null; // 选择的全局变量,标记当前选择的是哪一列
var lasterSel; // 保存最后选择的一行的行id
$(function() {
    // 单据设置列表表格
    jQuery("#blackId").jqGrid({
        datatype: "json",
       url: base_template + '/receipt/get_receipt_details.jhtml?receiptType=1&receiptId=' + $("input[name='id']").val(),
        mtype: 'POST',
        colNames: ['序号', 'gdsInfoId', 'receiptId', '操作', '商品编号 ', '商品名称','规格代码','规格名称','属性', '单位', '数量','单价','含税单价', '库位编码', '库位编码id', '备注'],
        colModel: [{ name: 'id', index: 'id',  hidden: true },
                    {name: 'gdsInfoId', index: 'gdsInfoId', hidden: true     },
                    {name: 'receiptId', index: 'receiptId',    hidden: true     },
                    {name: 'act', index: 'act', hidden:  $("input[name='id']").val()>0?true:false },
                 {name: 'gdsInfoDetail',index: 'gdsInfoDetail', width: 200,
                    	formatter: function(cellvalue, options, rowObject) {
                    			var dis =  $("input[name='id']").val()>0?"disable":"" ;
            
                    		return "<input "+dis+" value='" + cellvalue + "' width='200px' class='bindSelect' name='gdsInfoDetail' id=gdsShowInfo_" + options.rowId + ">";
            }
        },
        { name: 'gdsName', index: 'gdsName',width:100},
        { name: 'skuOuterId',index: 'skuOuterId',width:100},
        { name: 'gdsFormat',index: 'gdsFormat',width:100},
        { name: 'gdsInfoFormat', index: 'gdsInfoFormat',width:100    },
        { name: 'gdsInfoCal',index: 'gdsInfoCal',width:30,hidden: true },
        { name: 'amount', index: 'amount', editable: $("input[name='id']").val()>0?false:true},
        { name: 'price', index: 'price', editable: $("input[name='id']").val()>0?false:true},
        { name: 'taxPrice', index: 'taxPrice', editable: $("input[name='id']").val()>0?false:true},
           {
            name: 'warehousePositionsName',
            index: 'warehousePositionsName',
            width: 150,
            formatter: function(cellvalue, options, rowObject) {
                return "<input value='" + cellvalue + "' width='150px' class='bindSelect' name='warehousePositionsName' id=warehousePositionsName_" + options.rowId + ">";
            }
        },
        { name: 'warehousePositionsId', index: 'warehousePositionsId',hidden: true },
        { name: 'remarks',index: 'remarks',editable: $("input[name='id']").val()>0?false:true}
        ],
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: '#pager',
        toolbarfilter: true,
        height: '100%',
        viewrecords: true,
        autowidth: true,
        rownumbers: true,
        // cellurl:base+"/template/receipt/save_receipt_details.jhtml",

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
			repeatitems : false
        },
        ajaxGridOptions: {
            timeout: oms.ajaxTimeout
        },
        // 统一超时时间
        successTip: false,
        beforeProcessing: oms.grid.ajaxSuccessFn,
        loadError: oms.grid.ajaxErrorFn,
        multiselect: false,
        gridComplete: function() {
            var ids = jQuery("#blackId").jqGrid('getDataIDs');
            for (var i = 0; i < ids.length; i++) {
                var rowId = ids[i];
                // 设置可以编辑
                var addBtn = "<div class='operating'><a href='javascript:void(0);' onclick='addCol();' class='operating-plus' title='新增行'></a>";
                addBtn += "<a href='javascript:' class='operating-trash' onclick=\"delBtnFn('" + rowId + "');\" title='删除'></a></div>";
                jQuery("#blackId").jqGrid('setRowData', ids[i], {
                    act: addBtn
                });
            }
            // 初始化计算jqgrid宽度
            $("#blackId").setGridWidth($('.wrap-table').width());
           
        },
        loadComplete: function(a) {
            var ids = jQuery("#blackId").jqGrid('getDataIDs');
            for (var i = 0; i < ids.length; i++) {
                
            	var gdsShowInfoObj = $("#" + $($("#blackId").getCell(ids[i], "gdsInfoDetail")).attr("id"));
                var warehousePositionsName = $("#" + $($("#blackId").getCell(ids[i], "warehousePositionsName")).attr("id"));
                
                bindSelect2(gdsShowInfoObj);
                bindWarehousePositionsName(warehousePositionsName);
                
                $(gdsShowInfoObj).prev("div").find(".select2-chosen").text($(gdsShowInfoObj).val());
                $(warehousePositionsName).prev("div").find(".select2-chosen").text($(warehousePositionsName).val());
            }
            if (a == null || a.total == 0) {
                addCol('1');
            }
        },
        'cellsubmit': 'clientArray',
        // 不进行ajax提交
        'afterEditCell': afterEditCellFn,
        'cellEdit': true
        // 设置可编辑单元格
    });

    // 业务时间
    $("#datepicker").datepicker({
        showOn: "both",
        buttonImage: base + "/dinghao/template/front/js/elem/jqueryui/css/images/calendar.gif",
        buttonImageOnly: true
    });
    
    // 自动生成编号
    // 单据初始化
    /*
    var options = {};
    options.params = {
        "nuType": "05"
    };

    options.url = base + "/template/baseNumber/findBaseNumberNext.jhtml";
    options.successCallback = function(data) {
        $("#receiptCodeShow").html(data.data);
        $("#receiptCode").val(data.data);
    };
    oms.ajaxCall(options);
       */ 
   
    /**
     * 弹出供应商
     */
    $("#remarks").on("click",
    function() {
        oms.s_addPop("供应商选择", base + "/template/custInfo/get_custinfomgr.jhtml", "", 1000, 490);
    });


    // 浏览器大小发生变化的时候，重新计算jqgrid的宽度
    $(window).resize(function() {
        oms.grid.mdetailconWidth("purOrderGridId");
    });
    
    //设置只读
    $("#warehouseId").attr("disabled","disabled");
    $("#remarks").attr("disabled","disabled");
    $("#serviceType").attr("disabled","disabled");
    $("#serviceNum").attr("disabled","disabled");
    $("#handledPerson").attr("disabled","disabled");
    $("#datepicker").attr("disabled","disabled");
       
   
}); // jquery 结束

function gridDataisPerfect() {
	
    var warehouseId = $("#warehouseId").val();
    if (warehouseId == '') {
        $.artDialog({
            title: '消息',
            content: '请先选择入库仓库'
        });
        $("#warehouseId").focus();
        return;
    }
    var serviceType = $("#serviceType").val();
    if (serviceType == '') {
        $.artDialog({
            title: '消息',
            content: '请先选择业务类型'
        });
        $("#serviceType").focus();
        return;
    }
    var purData = $("#blackId").getRowData();
    for (var i in purData) {
        var row = purData[i];
        if ($.trim(row.gdsInfoId) == "" || $.trim(row.gdsInfoId) == undefined) {
            oms.tooltip("请选择商品信息!", "error");
            return false;
        }
        if ($.trim(row.warehousePositionsId) == "" || $.trim(row.warehousePositionsId) == undefined) {
            oms.tooltip("请选择库位信息!", "error");
            return false;
        }
        if ($.trim(row.receiptId) == 0) {
            oms.tooltip("请完善采入库单相关信息的录入!", "error");
            return false;
        }
    }
    return true;
}
/**
 * ****************************************************************************
 * 函数名称: <ATFunc>addCol</ATFunc> 函数功能: <ATFuncDesc>新增行</ATFuncDesc> 开发人员:
 * Zihan 2016年1月18日 修改记录:
 * ****************************************************************************
 */
function addCol(times) {
    // 初始化
    if (typeof(times) == 'undefined' || times != '1') {
        var warehouseName = $("#warehouseName").val();
        if (warehouseName == '') {
            $.artDialog({
                title: '消息',
                content: '请先选择入库仓库'
            });
            $("#warehouseName").focus();
            return;
        }
    }
   
    var defaultData = [{
        id: "",
        gdsInfoId: "",
        receiptId: "-1",
        gdsInfoDetail: "",
        gdsInfoFormat: "",
        gdsInfoCal: "",
        amount: "",
        warehousePositionsName: "",
        warehousePositionsId: "",
        remarks: ""
    }];
    // 新增一行
    jQuery("#blackId").jqGrid('addRowData', '', defaultData, "last");
    // 商品添加智能提示
    var gdsInfoDetail = $("#blackId").find("tr").last().find("input[name='gdsInfoDetail']");
    bindSelect2(gdsInfoDetail);
    var warehousePositionsName = $("#blackId").find("tr").last().find("input[name='warehousePositionsName']");
    bindWarehousePositionsName(warehousePositionsName);
}

function bindSelect2(e) {
    e.select2({
        placeholder: "请输入商品编号/名称",
        minimumInputLength: 1,
        multiple: false,
        width: '160',
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
            jQuery("#blackId").setCell(rowId, "gdsInfoCal", item.cal);
            jQuery("#blackId").setCell(rowId, "gdsInfoId", item.id);
            jQuery("#blackId").setCell(rowId, "gdsName", item.gdsName);
            jQuery("#blackId").setCell(rowId, "gdsFormat", "");
            jQuery("#blackId").setCell(rowId, "gdsFormat", item.gdsFormat);
            jQuery("#blackId").setCell(rowId, "skuOuterId", item.skuOuterId);
            
            $("#gdsShowInfo_" + rowId).val(item.gdsShowInfo);
            jQuery("#blackId").setCell(rowId, "gdsShowInfo", item.gdsShowInfo);
            return item.gdsNo;
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
/*******************************************************************************
 * 函数名称: <ATFunc>delBtnFn</ATFunc> 函数功能: <ATFuncDesc>删除事件</ATFuncDesc> 输入参数: 返
 * 回 值: 开发人员: helong 修改记录:
 ******************************************************************************/
function delBtnFn(rowId) {
    // 取得所有id
    var ids = jQuery("#blackId").jqGrid('getDataIDs');
    var size = ids.length;
    jQuery("#blackId").delRowData(rowId);
    if (size == 1) {
        addCol();
    }
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
        width: '120',
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
            url: base_template + "/warehouse/get_warehouses_detail_select2.jhtml",
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
function savePurOrder() {
    // 入库单ID (新增或修改)
    if (!gridDataisPerfect()) {
        return;
    }
    // 保存入库单信息
   // $("#myForm").submit();
    
 
    var receiptVo = {};
    receiptVo["id"] = $("#receiptId").val();
    receiptVo["receiptCode"] = $("#receiptCode").val();
    receiptVo["receiptType"] = $("#receiptType").val();
    receiptVo["warehouseId"] = $("#warehouseId").val();
    receiptVo["providerId"] = $("#providerId").val();
    receiptVo["serviceType"] = $("#serviceType").val();
    receiptVo["serviceNum"] = $("#serviceNum").val();
    receiptVo["handledPerson"] = $("#handledPerson").val();
    receiptVo["createDate"] = $("#datepicker").val();
             
    var griddata = $("#blackId").getRowData();
    
    for (var i in griddata) {
    	receiptVo["receiptDetailVos[" + i + "].id"] = griddata[i].id;
    	receiptVo["receiptDetailVos[" + i + "].gdsInfoId"] = griddata[i].gdsInfoId;
    	receiptVo["receiptDetailVos[" + i + "].receiptId"] = $("#receiptId").val();
        if (griddata[i].id == '') {
        	receiptVo["receiptDetailVos[" + i + "].gdsInfoDetail"] = $(griddata[i].gdsInfoDetail).find(".select2-chosen").html();
        	receiptVo["receiptDetailVos[" + i + "].warehousePositionsName"] = $(griddata[i].warehousePositionsName).find(".select2-chosen").html();
        } else {
            var gdsInfoDetail = $("#gdsShowInfo_" + (Number(i) + Number(1))).val();
            // 为修改后
            if (typeof(gdsInfoDetail) == 'undefined') {
                gdsInfoDetail = $(griddata[i].gdsInfoDetail).find(".select2-chosen").html();
            }
            receiptVo["receiptDetailVos[" + i + "].gdsInfoDetail"] = gdsInfoDetail;
            // ------------------------------------------------------------------------
            var warehousePositionsName = $("#warehousePositionsName_" + (Number(i) + Number(1))).val();
            // 为修改后
            if (typeof(warehousePositionsName) == 'undefined') {
                warehousePositionsName = $(griddata[i].warehousePositionsName).find(".select2-chosen").html();
            }
            receiptVo["receiptDetailVos[" + i + "].warehousePositionsName"] = warehousePositionsName;
        }
        receiptVo["receiptDetailVos[" + i + "].gdsInfoFormat"] = griddata[i].gdsInfoFormat;
        receiptVo["receiptDetailVos[" + i + "].gdsInfoCal"] = griddata[i].gdsInfoCal;
        receiptVo["receiptDetailVos[" + i + "].amount"] = griddata[i].amount;
        receiptVo["receiptDetailVos[" + i + "].warehousePositionsId"] = griddata[i].warehousePositionsId;
        receiptVo["receiptDetailVos[" + i + "].remarks"] = griddata[i].remarks;
    }
 
    var options = {};
    options.params = receiptVo;
    options.url = base+"/template/receipt/saveReceipt.jhtml";
    options.successCallback = savePurOrderBackFn;
    oms.ajaxCall(options);
    
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
    
   
 
});
function afterEditCellFn(rowid, cellname, value, iRow, iCol) {
    curRow = iRow;
    curCol = iCol;
    lasterSel = rowid;
}

function savePurOrderBackFn(data) {

	oms.tooltip(data.msg , "succeed");
}