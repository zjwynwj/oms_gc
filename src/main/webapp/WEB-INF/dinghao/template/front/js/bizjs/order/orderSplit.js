var curRow = null;  //选择的全局变量,标记当前选择的是哪一行
var curCol = null;  //选择的全局变量,标记当前选择的是哪一列
var lasterSel ;     //保存最后选择的一行的行id
var numArray = {}

$(function() {
	
	bindMoneyAndAmountEvent();
	//点击空白处，保存正在编辑的数据
	$('html').bind('click', function(e) { //用于点击其他地方保存正在编辑状态下的行
	    if ( lasterSel != "" ) {
	        if($(e.target).closest('#orderItemGridId').length == 0) {
	            $("#orderItemGridId").jqGrid("saveCell", curRow, curCol);
	            lasterSel = ""; 
	        } 
	    } 
	    var target = e.target;
		if($(target).attr("id")=="save")
		{
			saveOrder();
		}

	});
	
	//订单表格
	jQuery("#orderItemGridId").jqGrid({
		datatype: "json",
		url: base + '/template/orderMgr/querySalesOrderItemList.jhtml',
		mtype: 'POST', 
		postData : {"id":$("#id").val()},
		height : 'auto',
		colNames: ['id','操作','gdsId', '商品名称','gdsName', '商品编码', '规格', '商品数量'], 
		colModel: [
		    {name:'id',index:'id',editable:true},
			{name:'act',index:'act',sortable:true ,width:40,hidden:true},
			{name:'gdsId',index:'gdsId', hidden:true},
			{name:'gdsInfo',index:'gdsInfo',sortable:false ,width:300,
				formatter:function(cellvalue, options, rowObject){
                	return "<input value='"+rowObject.gdsName+"' width='400px' class='bindSelect' name='gdsInfoSelect2' id=gdsShowInfo_"+options.rowId+">";
                }
			},
			{name:'gdsName',index:'gdsName', hidden:true},
			{name:'gdsNo',index:'gdsNo'},
			{name:'gdsFormat',index:'gdsFormat'},
			{name:'qty',index:'qty',editable:true}
        ], 
		toolbarfilter : true,
		viewrecords : true,
		sortname : 'no',
		sortorder : 'act',
		autowidth : true,
		rownumbers: true,
		prmNames: {   //默认发送参数格式设置
			page:"pageNum",
			rows:"rows"
		},
		'cellEdit' : true,  //设置可编辑单元格
	    'cellsubmit' : 'clientArray',  //不进行ajax提交
	    'afterEditCell' : afterEditCellFn,
		jsonReader:{  //返回数据格式设置
			root: "data",  
			repeatitems : false
		},
		ajaxGridOptions:{
			timeout:oms.ajaxTimeout
		},//统一超时时间
		successTip:false,
		beforeProcessing:oms.grid.ajaxSuccessFn,
		loadError:oms.grid.ajaxErrorFn,
		loadComplete :function(a) {
			var ids = jQuery("#orderItemGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var gdsShowInfoObj =$("#"+$($("#orderItemGridId").getCell(ids[i] , "gdsInfo")).attr("id"));
				bindSelect2(gdsShowInfoObj);
				var id = $("#orderItemGridId").getRowData(ids[i]).id;
				var qty = $("#orderItemGridId").getRowData(ids[i]).qty;
				numArray[id] = qty;
			}
		},
		multiselect : true
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("orderItemGridId");	
	})
	
	jQuery("#newOrderItemGridId").jqGrid({
		data : [],
		datatype : "local",
		height : 'auto',
		colNames: ['商品名称', '商品编码', '规格', '商品数量','id'], 
		colModel: [
			{name:'gdsName',index:'gdsName'},
			{name:'gdsNo',index:'gdsNo'},
			{name:'gdsFormat',index:'gdsFormat'},
			{name:'qty',index:'qty'},
			{name:'id',index:'id'}
        ], 
		toolbarfilter : true,
		viewrecords : true,
		sortname : 'no',
		sortorder : 'act',
		autowidth : true,
		rownumbers: true,
		prmNames: {   //默认发送参数格式设置
			page:"pageNum",
			rows:"rows"
		},
		'cellEdit' : true,  //设置可编辑单元格
	    'cellsubmit' : 'clientArray',  //不进行ajax提交
		multiselect : true
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("newOrderItemGridId");	
	})
});

function afterEditCellFn(rowid, cellname, value, iRow, iCol) {
	curRow = iRow;
	curCol = iCol;
	lasterSel = rowid;
	if(curCol ==9){
		var item = $('#orderItemGridId').find('tr').eq(curRow).find('td').eq(curCol).find('input');
		var oldValue = $(item).val();
		item.attr("maxlength" , 8);
		bindJqgridJustNumberEvent(item);
		$(item).bind("blur",function(){
			var rowData = $("#orderItemGridId").getRowData(rowid);
			var oldValue = numArray[rowData.id];
			var newValue = $(this).val();
			
			var ids = jQuery("#newOrderItemGridId").jqGrid('getDataIDs');
			var isExist = false;
			var newRowId = "";
			for(var i in ids){
				if($("#newOrderItemGridId").getRowData(ids[i]).id == rowData.id){
					isExist = true;
					newRowId = ids[i];
				}
			}
			if(parseInt(newValue) > parseInt(oldValue)){
				oms.tooltip("不能超过原商品数量!","error");
				$(this).val(oldValue);
			}else if(parseInt(newValue) == parseInt(oldValue)){
				if(isExist){
					jQuery("#newOrderItemGridId").delRowData(newRowId);
				}
			}else{
				if(!isExist){
					var nextNum = oldValue - newValue;
					var defaultData = [{gdsName:rowData.gdsName,gdsNo:rowData.gdsNo,gdsFormat:rowData.gdsFormat,qty:nextNum,id:rowData.id}];
					jQuery("#newOrderItemGridId").jqGrid('addRowData',1,defaultData,"last");
				}else{
					var nextNum = oldValue - newValue;
					jQuery("#newOrderItemGridId").setCell(newRowId,"qty",nextNum);
				}
			}
		});
	}
}

function bindSelect2(e){
	e.select2({
		placeholder : "请输入商品编号\名称",
		minimumInputLength : 1,
		multiple : false,
		width : '200',
		formatResult : function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
			return result.gdsName ;
		},
		formatSelection : function(item) {// 选中后显示在文本框中的值
			return item.gdsName+"-"+item.gdsNo+"-"+item.gdsFormat;
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
	e.prev("div").find(".select2-chosen").text(e.val());
	
	$("input[name=gdsInfoSelect2]").unbind("change");
	$("input[name=gdsInfoSelect2]").bind("change",function(){
		var rowId = $(this).attr("id").split("_")[1];
		var gdsId = $(this).val() || " ";
		var gdsShowInfo = $(this).prev("div").find(".select2-chosen").text().split("-")[0];
		var gdsName = gdsShowInfo;
		var gdsNo = $(this).prev("div").find(".select2-chosen").text().split("-")[1];
		var gdsFormat = $(this).prev("div").find(".select2-chosen").text().split("-")[2];
		$(this).prev("div").find(".select2-chosen").text(gdsShowInfo);
		
		jQuery("#orderItemGridId").setCell(rowId,"gdsId",gdsId);
		if($.trim(gdsId) == ""){
			jQuery("#orderItemGridId").setCell(rowId,"gdsName"," ");
		}else{
			jQuery("#orderItemGridId").setCell(rowId,"gdsName",gdsName);
		}
		jQuery("#orderItemGridId").setCell(rowId,"gdsNo",gdsNo);
		if(gdsFormat != "undefined"){
			jQuery("#orderItemGridId").setCell(rowId,"gdsFormat",gdsFormat);
		}
	});
}

function saveOrder(){
	
	var orderDate = $("#orderItemGridId").getRowData();
	var isExistOrder = false;
	for(var i in orderDate){
		if(orderDate[i].qty != 0){
			isExistOrder = true;
		}
	}
	if(!isExistOrder){
		oms.tooltip("原始订单至少要有1条子订单!","error");
	}
	
	var newOrderData = $("#newOrderItemGridId").getRowData();
	if(newOrderData.length == 0){
		oms.tooltip("没有要拆分的订单!","error");
		return false;
	}
	
	var salesOrderDataVo = {};
	salesOrderDataVo["id"] = $("#id").val();
	
	var griddata = $("#newOrderItemGridId").getRowData();
	for(var i in griddata){
		salesOrderDataVo["itemList["+i+"].id"] = griddata[i].id;
		salesOrderDataVo["itemList["+i+"].qty"] = griddata[i].qty;
	}
	var options = {};
    options.params = salesOrderDataVo;
	options.url = base+"/template/orderMgr/saveOrderSplit.jhtml";
	options.successCallback = function(data){
		oms.tooltip(data.errMsg,"succeed");
	}
	oms.ajaxCall(options);
}