/*
//店铺选择下拉
$('#shopName').select2({
	placeholder : "请输入店铺",
	minimumInputLength : 1,
	multiple : false,
	width : '200',
	formatResult : function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
		return result.name ;
	},
	formatSelection : function(itgenerateExpcode){//选中后显示在文本框中的值
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
*/
/*
$('#expressName').select2({
	placeholder : "请输入物流公司名称",
	minimumInputLength : 1,
	multiple : false,
	width : '200',
	formatResult : function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
		return result.name ;
	},
	formatSelection : function(item) {// 选中后显示在文本框中的值
		$('#expid').val(item.id);
		return item.name;
	},
	dropdownCssClass : "bigdrop", // apply css that makes the dropdown
	// taller
	escapeMarkup : function(m) {
		return m;
	},
	ajax : {
		url : base+"/template/expressMgr/findExpressInfoGrid.jhtml",
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

$("#expressName").unbind("change");
$("#expressName").bind("change" , function(){
	$("#expid").val($(this).val());
});
*/
function refreshGrid(){
	searchSalesrOrder();
	searchNoPrintOrder();
	searchHasPrintOrder();
}

function search(){
	var conId = $('#tabConId').children('div:visible').attr('id');
	if(conId == "cutTabCon0"){
    	searchSalesrOrder();
    }else if(conId == "cutTabCon1"){
    	searchNoPrintOrder();
    }else if(conId == "cutTabCon2"){
    	searchHasPrintOrder();
    }
}

function searchSalesrOrder(){
//	var page = $('#salesrOrderGridId').getGridParam('page'); // current page
	var postData = new Array();
	var queryData = {};
	queryData.hasfaudit = "1";
	queryData.platType = "";
	queryData.shopId = "";
	queryData.expid = $("#expid").val();
	queryData.orderNum = $("#orderNum").val();
	queryData.topTids = $("#topTids").val();
	queryData.platType = $("#platType").val();
	queryData.shopId = $("#shopId").val();
	postData.push(queryData);
    $("#salesrOrderGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}

function searchNoPrintOrder(){
	var postData = new Array();
	var queryData = {};
	queryData.hasfaudit = "1";
	queryData.ecprinted = "0";
	queryData.platType = "";
	queryData.shopId = "";
	queryData.orderNum = $("#orderNum").val();
	queryData.topTids = $("#topTids").val();
	queryData.expid = $("#expid").val();
	queryData.platType = $("#platType").val();
	queryData.shopId = $("#shopId").val();
	postData.push(queryData);
    $("#noPrintOrderGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}

function searchHasPrintOrder(){
	var postData = new Array();
	var queryData = {};
	queryData.hasfaudit = "1";
	queryData.ecprinted = "1";
	queryData.platType = "";
	queryData.shopId = "";
	queryData.orderNum = $("#orderNum").val();
	queryData.topTids = $("#topTids").val();
	queryData.expid = $("#expid").val();
	queryData.platType = $("#platType").val();
	queryData.shopId = $("#shopId").val();
	postData.push(queryData);
    $("#hasPrintOrderGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}
function  rtnGoods()
{

	var gridId = getGridId();
    var selectRowId = $('#'+gridId).jqGrid('getGridParam','selarrrow');
    if(selectRowId == ""){
		oms.tooltip("单选一个订单进行退货单据生成!","error");
		return;
	}
    if(selectRowId.length >1)
    {
    	oms.tooltip("单选一个订单进行退货单据生成!","error");
		return;
    }
    var orderId = 0;
    for(var i in selectRowId){
    	var nextRow = $("#"+gridId).jqGrid('getRowData',selectRowId[i]);
    	if($.trim(nextRow.hassend) != "1"){
    		oms.tooltip("订单"+nextRow.orderNum+"未发货,不能生成退货单","error");
    		return;
    	}
    	else
    		{
    		orderId = nextRow.id;
    		}
    	
    }
	
	var url = base_template + "/salesrtnorder/add_sales_rtn_order_item.jhtml?"+"orderId="+orderId;
    artTabs({
		addTab : {
		    items : {
			id : 'add_sales_rtn_order_item',
			title : '新增退货单',
			url : url
		    }
		}
    });
}
$(function() {
	oms.cutCon("cutTab","click","readOn");  //标签切换调用
	
	//订单表格
	jQuery("#salesrOrderGridId").jqGrid({
		datatype: "json",
		url: base + '/template/orderMgr/querySalesOrderGrid.jhtml',
		mtype: 'POST', 
		postData : {"exceptionStatus" : "0", "hasfaudit":"1"},
		height : 'auto',
		colNames: ['id','打印类型', '拣货单号', '店铺', '订单编号', '交易单号','expid','remark1','打印模板','物流单号','是否发货','是否打印快递单','卖家备注','买家备注','买家昵称'], 
		colModel: [
		    {name:'id',index:'id',sortable:true , hidden:true},
		    {name:'act',index:'act' ,width:'70px'},
			{name:'waveNo',index:'waveNo'},
			{name:'shopName',index:'shopName'},
			{name:'orderNum',index:'orderNum'},
			{name:'topTids',index:'topTids'},
			{name:'expid',index:'expid', hidden:true},
			{name:'remark1',index:'remark1', hidden:true},
			{name:'printTemplateName',index:'printTemplateName'},
			{name:'expcode',index:'expcode', width:320,sortable:false,
	        	 formatter:function(cellvalue, options, rowObject){
	 	     	   var s=cellvalue===undefined?"":cellvalue;
	 	     	   return '<input type="text" class="editInput" maxlength="20" value='+s+'>';
	 	      	}
			},
			{name:'hassend',index:'hassend'},
			{name:'ecprinted',index:'ecprinted'},
			{name:'sellerMemo',index:'sellerMemo'},
			{name:'buyerMemo',index:'buyerMemo'},
			{name:'custNick',index:'custNick'}
        ], 
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : 'salesOrderGridPanelId',
		toolbarfilter : true,
		viewrecords : true,
		sortname : 'id',
		sortorder : 'desc',
		sortable : true,
		autowidth : true,
		rownumbers: true,
		prmNames: {   //默认发送参数格式设置
			page:"pageNum",
			rows:"rows"
		},
		jsonReader:{  //返回数据格式设置
			root: "data.list",  
			total: "data.pageCount",  
			records: "data.count",
			repeatitems : false
		},
		ajaxGridOptions:{
			timeout:oms.ajaxTimeout
		},//统一超时时间
		successTip:false,
		beforeProcessing:oms.grid.ajaxSuccessFn,
		loadError:oms.grid.ajaxErrorFn,
		multiselect : true
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("salesrOrderGridId");	
	})
	
	//订单表格
	jQuery("#noPrintOrderGridId").jqGrid({
		datatype: "json",
		url: base + '/template/orderMgr/querySalesOrderGrid.jhtml',
		mtype: 'POST', 
		postData : {"exceptionStatus" : "0" , "hasfaudit":"1" ,"ecprinted":"0"},
		height : 'auto',
		colNames: ['id','打印类型', '拣货单号', '店铺', '订单编号', '交易单号','expid','remark1','打印模板','物流单号','是否发货','是否打印快递单','卖家备注','买家备注','买家昵称'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'act',index:'act' ,width:'70px'},
			{name:'waveNo',index:'waveNo'},
			{name:'shopName',index:'shopName'},
			{name:'orderNum',index:'orderNum'},
			{name:'topTids',index:'topTids'},
			{name:'expid',index:'expid', hidden:true},
			{name:'remark1',index:'remark1', hidden:true},
			{name:'printTemplateName',index:'printTemplateName'},
			{name:'expcode',index:'expcode', width:320,sortable:false,
	        	 formatter:function(cellvalue, options, rowObject){
	 	     	   var s=cellvalue===undefined?"":cellvalue;
	 	     	   return '<input type="text" class="editInput" maxlength="20" value='+s+'>';
	 	      	}
			},
			{name:'hassend',index:'hassend'},
			{name:'ecprinted',index:'ecprinted'},
			{name:'sellerMemo',index:'sellerMemo'},
			{name:'buyerMemo',index:'buyerMemo'},
			{name:'custNick',index:'custNick'}
        ], 
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : 'noPrintOrderGridPanelId',
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
		jsonReader:{  //返回数据格式设置
			root: "data.list",  
			total: "data.pageCount",  
			records: "data.count",
			repeatitems : false
		},
		ajaxGridOptions:{
			timeout:oms.ajaxTimeout
		},//统一超时时间
		successTip:false,
		beforeProcessing:oms.grid.ajaxSuccessFn,
		loadError:oms.grid.ajaxErrorFn,
		multiselect : true
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("noPrintOrderGridId");	
	})
	
	//订单表格
	jQuery("#hasPrintOrderGridId").jqGrid({
		datatype: "json",
		url: base + '/template/orderMgr/querySalesOrderGrid.jhtml',
		mtype: 'POST', 
		postData : {"exceptionStatus" : "0", "hasfaudit":"1" ,"ecprinted":"1"},
		height : 'auto',
		colNames: ['id','打印类型', '拣货单号', '店铺', '订单编号', '交易单号','expid','remark1','打印模板','物流单号','是否发货','是否打印快递单','卖家备注','买家备注','买家昵称'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'act',index:'act' ,width:'70px'},
			{name:'waveNo',index:'waveNo'},
			{name:'shopName',index:'shopName'},
			{name:'orderNum',index:'orderNum'},
			{name:'topTids',index:'topTids'},
			{name:'expid',index:'expid', hidden:true},
			{name:'remark1',index:'remark1', hidden:true},
			{name:'printTemplateName',index:'printTemplateName'},
			{name:'expcode',index:'expcode', width:320,sortable:false,
	        	 formatter:function(cellvalue, options, rowObject){
	 	     	   var s=cellvalue===undefined?"":cellvalue;
	 	     	   return '<input type="text" class="editInput" maxlength="20" value='+s+'>';
	 	      	}
			},
			{name:'hassend',index:'hassend'},
			{name:'ecprinted',index:'ecprinted'},
			{name:'sellerMemo',index:'sellerMemo'},
			{name:'buyerMemo',index:'buyerMemo'},
			{name:'custNick',index:'custNick'}
        ], 
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : 'hasPrintOrderGridPanelId',
		toolbarfilter : true,
		viewrecords : true,
		sortname : 'id',
		sortorder : 'desc',
		autowidth : true,
		rownumbers: true,
		prmNames: {   //默认发送参数格式设置
			page:"pageNum",
			rows:"rows"
		},
		jsonReader:{  //返回数据格式设置
			root: "data.list",  
			total: "data.pageCount",  
			records: "data.count",
			repeatitems : false
		},
		ajaxGridOptions:{
			timeout:oms.ajaxTimeout
		},//统一超时时间
		successTip:false,
		beforeProcessing:oms.grid.ajaxSuccessFn,
		loadError:oms.grid.ajaxErrorFn,
		multiselect : true
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("hasPrintOrderGridId");	
	})
	
	oms.grid.mdetailconWidth("salesrOrderGridId");
	oms.grid.mdetailconWidth("noPrintOrderGridId");	
	oms.grid.mdetailconWidth("hasPrintOrderGridId");	
});

function generateExpcode(){
	var gridId = getGridId();
    var selectRowId = $('#'+gridId).jqGrid('getGridParam','selarrrow');
    if(selectRowId == ""){
		oms.tooltip("请选择操作的记录!","error");
		return;
	}
    
    var firstRow = $("#"+gridId).jqGrid('getRowData',selectRowId[0]);
    var expressName = firstRow.expressName;
    var expcode =   $("#expCode").val(); // $("#" +selectRowId[0]+ " .editInput").val();
    var expid = firstRow.expid;

    for(var i in selectRowId){
    	var nextRow = $("#"+gridId).jqGrid('getRowData',selectRowId[i]);
    	if($.trim(nextRow.hassend) == "1"){
    		oms.tooltip("订单"+nextRow.orderNum+"已发货,不能生成物流单号","error");
    		return;
    	}
		if(firstRow.expid != nextRow.expid){
			oms.tooltip("必须是相同的物流公司才能批量生成物流单号!","error");
			return;
		}
    }
    
   // var expcode = $("#" +selectRowId[0]+ " .editInput").val();
	if(expcode == ""){
 		oms.tooltip("请先输入起始单号!","error");
 		return;
 	} 
	
	var options = {};
	options.params = {"id" : expid};
    options.isMask = true;
    options.url = base+"/template/expressMgr/queryExpressInfo.jhtml";
    options.successCallback = function(data){
		var re = new RegExp("/"+data.data.expressReg+"/");
		if(re.test(expcode) || typeof(data.data.expressReg) == "undefined"){
			var selectNum = selectRowId.length;
		    var array = [];
		  	if(expressName == "顺丰速运"){
		 		array = SFlogis(expcode,selectNum);
		  	}else if(expressName == "EMS"){
		  		array = emsLogis(expcode,selectNum);
		  	}else{
		  		array = logis(expcode,selectNum);
		  	}
		  	var options = {};
			options.params = {"expcodeArr" : array.join(",")};
		    options.isMask = true;
		    options.url = base+"/template/orderMgr/findExistExpcode.jhtml";
		    options.chainPar = array;
		    options.successCallback = function(result,status, xhr, array){
		    	var data = result.data;
		    	if(data.length >0){
					oms.tooltip("有重复单号，请重新生成！","error");
					return; 
				}
		    	var gridId = getGridId();
				var selectRowId =  $('#'+gridId).jqGrid('getGridParam','selarrrow'); 
				var rowData = $("#"+gridId).jqGrid("getRowData", selectRowId[0]);
				var logisNum = $( "#" + selectRowId[0]+ " .editInput").val();
				for(var i in selectRowId){
					jQuery("#"+gridId).setCell(selectRowId[i],"expcode",array[i]);
				}
				updateExpcode();
		    };
		    oms.ajaxCall(options);
		}else{
			oms.tooltip("物流单号"+expcode+"不属于"+data.data.name+"物流公司的正确物流编号","error");
	 		return;
		}
    };
    oms.ajaxCall(options);
	return;
}

function getGridId(){
	var conId = $('#tabConId').children('div:visible').attr('id');
	var gridId = "";
	if(conId == "cutTabCon0"){
		gridId = "salesrOrderGridId";
    }else if(conId == "cutTabCon1"){
    	gridId = "noPrintOrderGridId";
    }else if(conId == "cutTabCon2"){
    	gridId = "hasPrintOrderGridId";
    }
    return gridId;
}

function updateExpcode(){
	var gridId = getGridId();
	var salesOrderData = {};
    var selectRowId = $('#'+gridId).jqGrid('getGridParam','selarrrow');
    for(var i in selectRowId){
    	var rowData = $("#"+gridId).jqGrid('getRowData',selectRowId[i]);
	    salesOrderData["salesOrderList["+i+"].id"] = rowData.id;
	    salesOrderData["salesOrderList["+i+"].orderNum"] = rowData.orderNum;
	    salesOrderData["salesOrderList["+i+"].expcode"] = $("#" +selectRowId[i]+ " .editInput").val();
    }
    
    var options = {};
    options.params = salesOrderData;
	options.url = base+"/template/orderMgr/modSalesOrderExpcode.jhtml";
	options.successCallback = function(data){
		oms.tooltip(data.errMsg,"succeed");
 		return;
	};
	oms.ajaxCall(options);
}

function generateWaveNo(){
	var gridId = getGridId();
    var selectRowId = $('#'+gridId).jqGrid('getGridParam','selarrrow');
    if(selectRowId == ""){
		oms.tooltip("请选择操作的记录!","error");
		return;
	}
    
    for(var i in selectRowId){
    	var nextRow = $("#"+gridId).jqGrid('getRowData',selectRowId[i]);
    	if($.trim(nextRow.hassend) == "1"){
    		oms.tooltip("订单"+nextRow.orderNum+"已发货,不能生成拣货单","error");
    		return;
    	}
    	if($.trim(nextRow.waveNo) != ""){
    		oms.tooltip("订单"+nextRow.orderNum+"已生成拣货单,不能重复生成","error");
    		return;
    	}
    }
	artTabs({
        addTab: {
            items: {
                id: 'addWmsWave',
                title: '生成拣货单',
                url: base + '/template/wmswave/turnAddWmsWave.jhtml?orderIds='+selectRowId.join(",")+''
            }
        }
    });    
}

function modPrintTemplate(){
	var gridId = getGridId();
    var selectRowId = $('#'+gridId).jqGrid('getGridParam','selarrrow');
    if(selectRowId == ""){
		oms.tooltip("请选择操作的记录!","error");
		return;
	}
    
    for(var i in selectRowId){
    	var nextRow = $("#"+gridId).jqGrid('getRowData',selectRowId[i]);
    	if($.trim(nextRow.hassend) == "1"){
    		oms.tooltip("订单"+nextRow.orderNum+"已发货,不能修改打印模板","error");
    		return;
    	}
    }
    
    var str = encodeURI(base+"/template/orderMgr/turnModPrintTemplate.jhtml");
	oms.s_addPop("修改打印模板",str,"",600,350);
}

function deliverGoods(){
	var gridId = getGridId();
    var selectRowId = $('#'+gridId).jqGrid('getGridParam','selarrrow');
    if(selectRowId == ""){
		oms.tooltip("请选择操作的记录!","error");
		return;
	}
    
    for(var i in selectRowId){
    	var nextRow = $("#"+gridId).jqGrid('getRowData',selectRowId[i]);
    	if($.trim(nextRow.hassend) == "1"){
    		oms.tooltip("订单"+nextRow.orderNum+"已发货,不能再次发货","error");
    		return;
    	}
    	if($(nextRow.expcode).val() == ""){
    		oms.tooltip("订单"+nextRow.orderNum+"没有设置物流单号,不能进行发货操作","error");
    		return;
    	}
    }
    
    var salesOrderData = {};
    var selectRowId = $('#'+gridId).jqGrid('getGridParam','selarrrow');
    for(var i in selectRowId){
    	var rowData = $("#"+gridId).jqGrid('getRowData',selectRowId[i]);
	    salesOrderData["salesOrderList["+i+"].id"] = rowData.id;
	    salesOrderData["salesOrderList["+i+"].orderNum"] = rowData.orderNum;
	    salesOrderData["salesOrderList["+i+"].expcode"] = $("#" +selectRowId[i]+ " .editInput").val();
    }
    
    var options = {};
    options.params = salesOrderData;
	options.url = base+"/template/orderMgr/deliverGoods.jhtml";
	options.successCallback = function(data){
		refreshGrid();
		if(data.success){
			oms.tooltip(data.errMsg,"succeed");
		}else{
			oms.tooltip(data.errMsg,"error");
		}	
 		return;
	};
	oms.ajaxCall(options);
}

function printTemplate(){
	var gridId = getGridId();
    var selectRowId = $('#'+gridId).jqGrid('getGridParam','selarrrow');
    if(selectRowId == ""){
		oms.tooltip("请选择操作的记录!","error");
		return;
	}
    var printId = "";
    var isPrinted = false;
    var orderIds = new Array();
    var orderNums = new Array();
    for(var i in selectRowId){
    	var nextRow = $("#"+gridId).jqGrid('getRowData',selectRowId[i]);
    	if(i==0){
    		var printId = nextRow.remark1;
    	}
    	if(printId != nextRow.remark1){
    		oms.tooltip("所选模板不是同一个模板,请重新选择!","error");
    	}
    	if($.trim(nextRow.hassend) == "1"){
    		oms.tooltip("订单"+nextRow.orderNum+"已发货,不能打印物流单!","error");
    		return;
    	}
    	if($(nextRow.expcode).val() == ""){
    		oms.tooltip("订单"+nextRow.orderNum+"没有设置物流单号,不能打印物流单!","error");
    		return;
    	}
    	if(nextRow.ecprinted == "1"){
    		isPrinted = true;
    	}
    	orderIds.push(nextRow.id);
    	orderNums.push(nextRow.orderNum);
     }
    
    
    if(isPrinted){
    	$.artDialog({
    		title:'提示',
    		content:'所选记录存在已打印订单,是否继续打印!',
    		ok:function(){
    			turnBatchPrint(orderIds , printId ,orderNums);
    		}
    	});
    }else{
    	turnBatchPrint(orderIds , printId , orderNums);
    }
}

function turnBatchPrint(orderIds , printId , orderNums){
	var str = encodeURI(base+"/template/printMgr/turnBatchPrint.jhtml?orderIds="+orderIds+"&printId="+printId+"&orderNums="+orderNums+"");
	oms.s_addPop("批量打单",str,"",720,490);
}