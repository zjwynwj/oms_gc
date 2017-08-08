var curRow = null; // 选择的全局变量,标记当前选择的是哪一行
var curCol = null; // 选择的全局变量,标记当前选择的是哪一列
var lasterSel; // 保存最后选择的一行的行id
var maingridid = "#blackId";
$(function() {
    /**
     * 绑定地区插件
     */
    $.areaSelect({
	id : 'area'
    });
 
    
    /*
    $('#expressName').select2({
	placeholder : "请输入物流公司名称",
	minimumInputLength : 1,
	multiple : false,
	width : '200',
	formatResult : function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
	    return result.name;
	},
	initSelection: function(element, callback) {
            callback({
                id: $('#expid').val(),
                name:  $("#expressName").val()
            });
        },
	formatSelection : function(item) {// 选中后显示在文本框中的值
	    $('#expid').val(item.id);
	    $("#expressName").val(item.name);
	    return item.name;
	},
	dropdownCssClass : "bigdrop", // apply css that makes the dropdown
	// taller
	escapeMarkup : function(m) {
	    return m;
	},
	ajax : {
	    url : base + "/template/expressMgr/findExpressInfoGrid.jhtml",
	    delay : 250,
	    dataType : 'json',
	    data : function(term, page) {
		return {
		    keyWord : term,// 汉字处理方式
		    actived : "1",
		    pageNum : 1,// 页码
		    rows : 10,// 数量
		};
	    },
	    results : function(data, page) {
		return {
		    results : data.data.list
		};
	    }
	},
	allowClear : true
    // 单选
    });
    */
    // 点击空白处，保存正在编辑的数据
    $('html').bind('click', function(e) { // 用于点击其他地方保存正在编辑状态下的行
	if (lasterSel != "") {
	    var purData = $("#blackId").getRowData();
	    var money = 0;
	    for ( var i in purData) {
		var row = purData[i];
		if (!$("#isgift_" + lasterSel).prop("checked")) {
		    money += parseFloat(row.price);
		}
	    }
	    $("#goodsMoney").val(money);
	    formatMoney($("#goodsMoney"), 2);
	    $("#goodsMoney").change();

	    if ($(e.target).closest('#blackId').length == 0) {
		$("#blackId").jqGrid("saveCell", curRow, curCol);
		lasterSel = "";
	    }
	}
	var target = e.target;
	if ($(target).attr("id") == "save") {

	    savePurOrder();
	}

    });
    
  
    // 单据设置列表表格
    jQuery("#blackId")
	    .jqGrid(
		    {
			datatype : "json",
			url : base
				+ '/template/salesrtnorder/getSalesRtnOrderItems.jhtml',
			postData : {"rtnId":$("#rtnId").val()},
			mtype : 'POST',
			colNames : [ '序号', '操作', 'gdsId', '商品编号 ','商品名称','规格代码' ,'规格', '属性',
				'退货数量', '赠品','换货', '库位编码', '库位编码id', '单价', '金额(元)' ],
			colModel : [
				{ name : 'id',index : 'id', hidden : true				},
				{ name : 'act',index : 'act',width:'80'	},
				{name : 'gdsId',index : 'gdsId',hidden : true},
				{ name : 'gdsShowInfo',  index : 'gdsShowInfo', width: 200,
				    formatter : function(cellvalue, options,rowObject) {
					if (typeof (cellvalue) == "undefined") {
					    cellvalue = "";
					}
					return "<input value='"	+ cellvalue + "' class='bindSelect' name='gdsShowInfo' id=gdsShowInfo_"
						+ options.rowId + ">";
				    }
				},
				{name : 'gdsName', index : 'gdsName',width:100},
				{name : 'skuOuterId', index : 'skuOuterId',width:60},
				{name : 'gdsFormat', index : 'gdsFormat',width:80},
				{ name :'attbs',  index : 'attbs',width:100},
				{ name :'rtnQty',index:'rtnQty',editable:true,width:80,
				    formatter : 'currency',
				    formatoptions : {
					decimalSeparator : ".",
					thousandsSeparator : ",",
					decimalPlaces : 0,
					prefix : "",
					suffix : "",
					defaulValue : 1,
					again:'right'
				    }
				},
				{
				    name : 'isgift',index : 'isgift',width:60,
				    formatter : function(cellvalue, options,
					    rowObject) {
					var text = "<input type='checkbox'";
					if (rowObject.isgift) {
					    text += " checked = 'checked' ";
					}
					return text += " name='isgift' id=isgift_"
						+ options.rowId + ">";
				    }
				},
				{
				    name : 'rtnType', index : 'rtnType',width:60,
				    formatter : function(cellvalue, options,
					    rowObject) {
						var text = "<input type='checkbox'";
						if (rowObject.rtnType =='2') {
						    text += " checked = 'checked' ";
						}
						return text += " name='rtnType' id=rtnType_"
						+ options.rowId + ">";
				    }
				},
				{
				    name : 'warehousePositionsName',
				    index : 'warehousePositionsName',
				    width : 200,
				    formatter : function(cellvalue, options,
					    rowObject) {
					if (typeof (cellvalue) == "undefined") {
					    cellvalue = "";
					}
					return "<input value='"
						+ cellvalue
						+ "' class='bindSelect' name='warehousePositionsName' id=warehousePositionsName_"
						+ options.rowId + ">";
				    }
				}, {
				    name : 'locid',
				    index : 'locid',
				    hidden : true
				}, {
				    name : 'originalPrice',
				    index : 'originalPrice',
				    editable : true,
				    formatter : 'currency',
				    formatoptions : {
					decimalSeparator : ".",
					thousandsSeparator : ",",
					decimalPlaces : 2,
					prefix : "",
					suffix : "",
					defaulValue : 0
				    }
				}, {
				    name : 'price',
				    index : 'price',
				    formatter : 'currency',
				    formatoptions : {
					decimalSeparator : ".",
					thousandsSeparator : ",",
					decimalPlaces : 2,
					prefix : "",
					suffix : "",
					defaulValue : 0
				    }
				} ],
			rowNum : 50,
			pager : '#pager',
			toolbarfilter : true,
			height : '100%',
			viewrecords : true,
			autowidth : true,
			rownumbers : true,
			// cellurl:base+"/template/receipt/save_receipt_details.jhtml",
			prmNames : { // 默认发送参数格式设置
			    page : "pageNum",
			    rows : "rows"
			},
			prmNames : {
			    page : "pageNum",
			    // 表示请求页码的参数名称
			    rows : "rows",
			    // 表示请求行数的参数名称
			    sort : "page.oid",
			    // 表示用于排序的列名的参数名称
			    order : "page.oor",
			    // 表示采用的排序方式的参数名称
			    search : "issearch",
			    // 表示是否是搜索请求的参数名称
			    nd : "nd",
			    // 表示已经发送请求的次数的参数名称
			    state : "isstate"
			},
			jsonReader : { // 返回数据格式设置
			    root : "data",
			    repeatitems : false
			},
			gridComplete : function() {
			    var ids = jQuery("#blackId").jqGrid('getDataIDs');
			    for (var i = 0; i < ids.length; i++) {
				var rowId = ids[i];
				// 设置可以编辑
				var addBtn = "<div class='operating'><a href='javascript:void(0);' onclick='addCol();' class='operating-plus' title='新增行'></a>";
				addBtn += "<a href='javascript:' class='operating-trash' onclick=\"delBtnFn('"
					+ rowId + "');\" title='删除'></a></div>";
				jQuery("#blackId").jqGrid('setRowData', ids[i],
					{
					    act : addBtn
					});
			    }
			
			},
			loadComplete : function(a) {
			    var ids = jQuery("#blackId").jqGrid('getDataIDs');
			    for (var i = 0; i < ids.length; i++) {
				var gdsShowInfoObj = $("#"+$($("#blackId").getCell(ids[i],"gdsShowInfo")).attr("id"));
				bindSelect2(gdsShowInfoObj);
				$(gdsShowInfoObj).prev("div").find(".select2-chosen").text($(gdsShowInfoObj).val());
				
				var warehousePositionsName = $("#"+ $($("#blackId").getCell(ids[i],"warehousePositionsName")).attr("id"));
			
				bindWarehousePositionsName(warehousePositionsName);
		        $(warehousePositionsName).prev("div").find(".select2-chosen").text($(warehousePositionsName).val());
		
			    }
			    /*
			    if ( a == null || a.total == 0) {
				   addCol('1');
			    }
			    */
			},
			ajaxGridOptions : {
			    timeout : oms.ajaxTimeout
			},
			// 统一超时时间
			successTip : false,
			beforeProcessing : oms.grid.ajaxSuccessFn,
			loadError : oms.grid.ajaxErrorFn,
			multiselect : false,
			'cellsubmit' : 'clientArray',
			// 不进行ajax提交
			'afterEditCell' : afterEditCellFn,
			'cellEdit' : true
		    // 设置可编辑单元格
		    });

    function afterEditCellFn(rowid, cellname, value, iRow, iCol) {
	curRow = iRow;
	curCol = iCol;
	lasterSel = rowid;
	$('#blackId')
		.find('tr')
		.eq(curRow)
		.find('td')
		.eq(curCol)
		.find('input')
		.blur(
			function() {
			    var amount = (typeof ($('#blackId').find('tr').eq(
				    curRow).find('td').eq(9).find('input')
				    .val()) == 'undefined') ? $('#blackId')
				    .find('tr').eq(curRow).find('td').eq(9)
				    .text() : $('#blackId').find('tr').eq(
				    curRow).find('td').eq(9).find('input')
				    .val();
			    var price = (typeof ($('#blackId').find('tr').eq(
				    curRow).find('td').eq(14).find('input')
				    .val()) == 'undefined') ? $('#blackId')
				    .find('tr').eq(curRow).find('td').eq(14)
				    .text() : $('#blackId').find('tr').eq(
				    curRow).find('td').eq(14).find('input')
				    .val();
			    amount = parseFloat(amount.replace(/[^\d\.-]/g, ""));
			    price = parseFloat(price.replace(/[^\d\.-]/g, ""));
			    jQuery("#blackId").setCell(rowid, "price",
				    amount * price);
			});

	if (curCol == 9) {
	    var item = $('#blackId').find('tr').eq(curRow).find('td')
		    .eq(curCol).find('input');
	    item.attr("maxlength", 8);
	    bindJqgridJustNumberEvent(item);
	}

	if (curCol == 14) {
	    var item = $('#blackId').find('tr').eq(curRow).find('td')
		    .eq(curCol).find('input');
	    bindJqgridMoneyAndAmountEvent(item);
	}
    }

    function gridDataisPerfect() {
	var warehouseName = $("#warehouseName").val();
	if (warehouseName == '') {
	    $.artDialog({
		title : '消息',
		content : '请先选择入库仓库'
	    });
	    $("#warehouseName").focus();
	    return;
	}

	var purData = $("#blackId").getRowData();
	for ( var i in purData) {
	    var row = purData[i];
	    if ($.trim(row.gdsId) == "" || $.trim(row.gdsId) == undefined) {
		oms.tooltip("请选择商品信息!", "error");
		return false;
	    }
	    if ($.trim(row.locId) == "" || $.trim(row.locId) == undefined) {
		oms.tooltip("请选择库位信息!", "error");
		return false;
	    }
	}
	return true;
    }

    $("#goodsMoney,#carriage").change(
	    function() {
		var carriage = parseFloat($("#carriage").val() == '' ? 0 : $(
			"#carriage").val().replace(/[^\d\.-]/g, ""));
		var goodsMoney = parseFloat($("#goodsMoney").val().replace(
			/[^\d\.-]/g, ""));
		$("#rtncash").val(carriage * goodsMoney);
	    });
});// --jQuery 结束

function bindSelect2(e) {
	e.select2({
		placeholder : "请输入商品编号\名称",
		minimumInputLength : 1,
		multiple : false,
		width : '150',
		formatResult : function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
			return result.gdsShowInfo ;
		},
		formatSelection : function(item) {// 选中后显示在文本框中的值
			 var rowId = $(e).attr("id").split("_")[1];
			    var attbs = new Array();
			    var gdsAttbs = " ";
			    if (item.attbs != "" && item.attbs != "undefined"
				    && typeof (item.attbs) != "undefined") {
				attbs = item.attbs.split(";");
				for ( var i in attbs) {
				    gdsAttbs += ";" + attbs[i].split(":")[2] + ":"
					    + attbs[i].split(":")[3];
				}
				gdsAttbs = gdsAttbs.substring(2, gdsAttbs.length);
			    }
			    jQuery("#blackId").setCell(rowId, "gdsId", item.id);
			    jQuery("#blackId").setCell(rowId, "gdsName", item.gdsName);
				 jQuery("#blackId").setCell(rowId, "skuOuterId", item.skuOuterId);
				 jQuery("#blackId").setCell(rowId, "gdsFormat", item.gdsFormat);
			     jQuery("#blackId").setCell(rowId, "attbs", gdsAttbs);
			    $("#gdsShowInfo_" + rowId).val(item.gdsShowInfo);
			    return item.gdsShowInfo;
		},
		dropdownCssClass : "bigdrop", // apply css that makes the dropdown
		escapeMarkup : function(m) {
			return m;
		},
		ajax : {
			url : base+"/template/gdsMgr/findGdsInfoForGrid.jhtml",
			delay : 250,
			dataType : 'json',
			data : function(term, page) {
				return {
					keyWord : term,// 汉字处理方式
					pageNum : 1,// 页码
					rows : 10,// 数量
				};
			},
			results : function(data, page) {
				return {
					results : data.data.list
				};
			}
		},
		allowClear : true// 单选
	});
	/*
	e.prev("div").find(".select2-chosen").text(e.val());
	
	$("input[name=gdsInfoSelect2]").unbind("change");
	$("input[name=gdsInfoSelect2]").bind("change",function(){
		var rowId = $(this).attr("id").split("_")[1];
		var gdsId = $(this).val() || " ";
		var gdsShowInfo = $(this).prev("div").find(".select2-chosen").text().split("-")[0];
		var attbsInfo = $(this).prev("div").find(".select2-chosen").text().split("-")[1];
		$(this).prev("div").find(".select2-chosen").text(gdsShowInfo);
		
		var attbs = new Array();
		var gdsAttbs =" ";
		if(attbsInfo != "" && attbsInfo  != "undefined" && typeof(attbsInfo) != "undefined"){
			attbs = attbsInfo.split(";");
			
			for(var i in attbs){
				gdsAttbs += ";"+attbs[i].split(":")[2]+":"+attbs[i].split(":")[3];
			}
			gdsAttbs = gdsAttbs.substring(2, gdsAttbs.length);
		}
		jQuery(maingridid).setCell(rowId,"gdsId",gdsId);
		jQuery(maingridid).setCell(rowId,"attbs",gdsAttbs);
	});
	*/
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
        width: '150',
        formatResult: function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
            return result.warehousePositionsCode;
        },
        formatSelection: function(item) { // 选中后显示在文本框中的值
            var rowId = $(e).attr("id").split("_")[1];
            jQuery("#blackId").setCell(rowId, "locId", item.id);
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
                    dhWarehousePositions: $("#stockId").val(),
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

function addCol(times) {
    // 初始化
    if (typeof (times) == 'undefined' || times != '1') {
	if (!gridDataisPerfect()) {
	    return;
	}
	var warehouseName = $("#stockId").val();
	if (warehouseName == '') {
	    $.artDialog({
		title : '消息',
		content : '请先选择入库仓库'
	    });
	    $("#stockId").focus();
	    return;
	}
    }
    var defaultData = [ {
	gdsId : "",
	gdsShowInfo : "",
	attbs : "",
	rtnQty : "1",
	isgift : "",
	warehousePositionsName : "",
	locid : "",
	originalPrice : "",
	price : ""
    } ];
    // 新增一行
    jQuery("#blackId").jqGrid('addRowData', '', defaultData, "last");
    // 商品添加智能提示
    var gdsInfoDetail = $("#blackId").find("tr").last().find("input[name='gdsShowInfo']");
    bindSelect2(gdsInfoDetail);
    var warehousePositionsName = $("#blackId").find("tr").last().find(
	    "input[name='warehousePositionsName']");
    bindWarehousePositionsName(warehousePositionsName);
}

function gridDataisPerfect() {
    var purData = $("#blackId").getRowData();
    for ( var i in purData) {
	var row = purData[i];
	if ($.trim(row.gdsId) == "" || $.trim(row.gdsId) == undefined) {
	    oms.tooltip("请选择商品信息!", "error");
	    return false;
	}
	if ($.trim(row.locid) == "" || $.trim(row.locid) == undefined) {
	    oms.tooltip("请完善库位编码!", "error");
	    return false;
	}
    }
    return true;
}

function savePurOrder() {

    if (!gridDataisPerfect()) {
    	return;
    }

    var salesRtnOrderVo = {};
    salesRtnOrderVo["rtnNo"] = $("#rtnNo").val();
    salesRtnOrderVo["platType"] = $("#platType").val();
    salesRtnOrderVo["shopId"] = $("#shopId").val();
    salesRtnOrderVo["orderNum"] = $("#orderNum").val();
    salesRtnOrderVo["goodsMoney"] = $("#goodsMoney").val();
    salesRtnOrderVo["carriage"] = $("#carriage").val();
    salesRtnOrderVo["rtncash"] = $("#rtncash").val();
    salesRtnOrderVo["stockId"] = $("#stockId").val();
    salesRtnOrderVo["rtnreason"] = $("#rtnreason").val();
    salesRtnOrderVo["received"] = $("#received").prop("checked");
    salesRtnOrderVo["expid"] = $("#expid").val();
    salesRtnOrderVo["expcode"] = $("#expcode").val();
    salesRtnOrderVo["deliveryfeeee"] = $("#deliveryfeeee").val();
    salesRtnOrderVo["custNick"] = $("#custNick").val();
    salesRtnOrderVo["recvName"] = $("#recvName").val();
    salesRtnOrderVo["recvMobile"] = $("#recvMobile").val();
    salesRtnOrderVo["address"] = $("#address").val();
    salesRtnOrderVo["area"] = $("#area").val();
    salesRtnOrderVo["rtnId"] = $("#rtnId").val();

    salesRtnOrderVo["orderId"] = $("#orderId").val();
    salesRtnOrderVo["orderNum"] = $("#orderNum").val();
       
    var griddata = $("#blackId").getRowData();
    for ( var i in griddata) {
    	salesRtnOrderVo["salesRtnOrderItemVos[" + i + "].id"] = griddata[i].id;
    	salesRtnOrderVo["salesRtnOrderItemVos[" + i + "].gdsId"] = griddata[i].gdsId;
        salesRtnOrderVo["salesRtnOrderItemVos[" + i + "].rtnQty"] = griddata[i].rtnQty;
     	salesRtnOrderVo["salesRtnOrderItemVos[" + i + "].isgift"] = $("#"+$(griddata[i].isgift).attr("id")).prop("checked");
     	salesRtnOrderVo["salesRtnOrderItemVos[" + i + "].rtnType"] = $("#"+$(griddata[i].rtnType).attr("id")).prop("checked")==true?2:1;
     	salesRtnOrderVo["salesRtnOrderItemVos[" + i + "].locid"] = griddata[i].locid;
    	salesRtnOrderVo["salesRtnOrderItemVos[" + i + "].originalPrice"] = griddata[i].originalPrice;
    	salesRtnOrderVo["salesRtnOrderItemVos[" + i + "].price"] = griddata[i].price;
    }
    var options = {};
    options.params = salesRtnOrderVo;
    options.url = base + "/template/salesrtnorder/saveSalesRtnOrderItem.jhtml";
    options.successCallback = savePurOrderBackFn;
    oms.ajaxCall(options);
}
function savePurOrderBackFn(data) {
	oms.tooltip(data.errMsg , "succeed");
  
}
