var oldPrintId = ""
//物流公司选择下拉
$('#printTemplateName').select2({
	placeholder : "请输入物流公司名称",
	minimumInputLength : 1,
	multiple : false,
	width : '200',
	formatResult : function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
		return result.logisName +"-"+result.name;
	},
	formatSelection : function(item) {// 选中后显示在文本框中的值
		$('#printId').val(item.id);
		$('#expId').val(item.logisId);
		return item.name;
	},
	dropdownCssClass : "bigdrop", // apply css that makes the dropdown
	// taller
	escapeMarkup : function(m) {
		return m;
	},
	ajax : {
		url : base+"/template/printMgr/findPrintTemplate.jhtml",
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
	allowClear : true// 单选
});

$("#printTemplateName").unbind("change");
$("#printTemplateName").bind("change" , function(){
	$("#printId").val($(this).val());
	if($.trim($("#printId").val()) == ""){
		$("#expId").val("");
	}
});

$(function(){
	var gridId = parent.getGridId();
    var selectRowId = parent.$('#'+gridId).jqGrid('getGridParam','selarrrow');
    var firstRow = parent.$("#"+gridId).jqGrid('getRowData',selectRowId[0]);
    var printTemplateName = firstRow.printTemplateName;
    var printId = firstRow.remark1;
    oldPrintId = printId;
    var expid = firstRow.expid;
    $("#printTemplateName").prev("div").find(".select2-chosen").text(printTemplateName);
    $("#printId").val(printId);
    $("#expid").val(expid);
});

function saveExpressFn(){
	if($.trim($("#printId").val()) == ""){
		oms.tooltip("请选择打印模板" , "error");
		return;
	}
	if($.trim($("#printId").val()) == oldPrintId){
		oms.tooltip("打印模板未改变" , "error");
		return;
	}
	var gridId = parent.getGridId();
	var salesOrderData = {};
    var selectRowId = parent.$('#'+gridId).jqGrid('getGridParam','selarrrow');
    for(var i in selectRowId){
    	var rowData = parent.$("#"+gridId).jqGrid('getRowData',selectRowId[i]);
	    salesOrderData["salesOrderList["+i+"].id"] = rowData.id;
	    salesOrderData["salesOrderList["+i+"].orderNum"] = rowData.orderNum;
	    salesOrderData["salesOrderList["+i+"].remark1"] = $("#printId").val();
    }
    var options = {};
    options.params = salesOrderData;
	options.url = base+"/template/orderMgr/modSalesOrderPrintTemplate.jhtml";
	options.successCallback = function(data){
		oms.tooltip(data.errMsg,"succeed");
		parent.refreshGrid();
		closeWinFn();
 		return;
	};
	oms.ajaxCall(options);
}

function closeWinFn() {
	oms.s_Pop_closedChild();
}