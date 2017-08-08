//店铺选择下拉
$('#shopName').select2({
	placeholder : "请输入店铺",
	minimumInputLength : 1,
	multiple : false,
	width : '200',
	formatResult : function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
		return result.name ;
	},
	formatSelection : function(item) {// 选中后显示在文本框中的值
		$('#shopId').val(item.id);
		return item.name;
	},
	dropdownCssClass : "bigdrop", // apply css that makes the dropdown
	// taller
	escapeMarkup : function(m) {
		return m;
	},
	ajax : {
		url : base+"/template/shop/findForGrid.jhtml",
		delay : 250,
		dataType : 'json',
		data : function(term, page) {
			return {
				name : term,// 汉字处理方式
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

$("#shopName").unbind("change");
$("#shopName").bind("change" , function(){
	$("#shopId").val($(this).val());
});

$(function(){
	$("#startTime").datepicker({
		showOn: "button", 
		buttonImage: template_base +"/js/elem/jqueryui/css/images/calendar.gif",
		buttonImageOnly: true 
	});
	
	$("#endTime").datepicker({
		showOn: "button", 
		buttonImage: template_base +"/js/elem/jqueryui/css/images/calendar.gif",
		buttonImageOnly: true 
	});
	
	$("#endTime").val(oms.formatDate(new Date()));
	//应付日期初始化
	var t=new Date();
	var newDay = new Date(t.getFullYear(),t.getMonth(),(t.getDate()-1));
	var dueDate = oms.formatDate(newDay);
	$("#startTime").val(dueDate);
	
});

function startDown(){
	if($.trim($("#shopId").val()) == ""){
		oms.tooltip("请选择店铺" , "error");
		return;
	}
	var options = {};
    options.params = {"downStartTime":$("#startTime").val() , "downEndTime":$("#endTime").val() , "shopId":$("#shopId").val()};
    options.isMask = true;
	options.url = base+"/template/orderMgr/downLoadSalesOrder.jhtml";
	options.successCallback = function(data){
		oms.tooltip(data.errMsg , "succeed");
		parent.refreshGrid();
		closeWinFn();
	};
	oms.ajaxCall(options);
}

function closeWinFn() {
	oms.s_Pop_closedChild();
}