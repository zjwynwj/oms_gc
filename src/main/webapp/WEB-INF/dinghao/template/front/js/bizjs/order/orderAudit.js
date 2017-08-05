function downOrder(){
	var str = encodeURI(base+"/template/orderMgr/turnOrderDown.jhtml");
	oms.s_addPop("下载订单",str,"",600,350);
}
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
*/
function refreshGrid(){
	searchSalesrOrder();
	searchauditOrder();
}

function search(){
    var conId = $('#tabConId').children('div:visible').attr('id');
    if(conId == "cutTabCon0"){
    	searchSalesrOrder();
    }else if(conId == "cutTabCon1"){
    	searchauditOrder();
    }
}

function searchSalesrOrder(){
	var postData = new Array();
	var queryData = {};
	queryData.hasfaudit = "0";
	queryData.disabled = "0";
	
	queryData.orderNum = $("#orderNum").val();
	queryData.topTids = $("#topTids").val();

	var areaname =  $("#areaname").val();
	var area = areaname.split("-");
	queryData.prov = area[0]==undefined?"":area[0];
	queryData.city = area[1]==undefined?"":area[1];
	queryData.county = area[2]==undefined?"":area[2];
	
	if($("#isCombine").attr("checked")){
		queryData.hasfaudit = "0";
		queryData.disabled = "0";
	}

	if($("#isSellerMemo").is(":checked")){
	
		queryData.sellerMemo = "isSellerMemo";//特殊处理了下
	}
	else
		{
		queryData.sellerMemo = "";
		}

	queryData.platType = $("#platType").val();
	queryData.shopId = $("#shopId").val();
	postData.push(queryData);
    $("#salesrOrderGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}

function searchauditOrder(){
	var postData = new Array();
	var queryData = {};
	queryData.hasfaudit = "1";
	queryData.disabled = "0";
	
	var areaname =  $("#areaname").val();
	var area = areaname.split("-");
	queryData.prov = area[0]==undefined?"":area[0];
	queryData.city = area[1]==undefined?"":area[1];
	queryData.county = area[2]==undefined?"":area[2];
	
	if($("#isSellerMemo").is(":checked")){
		
		queryData.sellerMemo = "isSellerMemo";//特殊处理了下
	}
	else
		{
		queryData.sellerMemo = "";
		}

	queryData.platType = $("#platType").val();
	queryData.shopId = $("#shopId").val();
	queryData.topTids = $("#topTids").val();
	queryData.orderNum = $("#orderNum").val();
	postData.push(queryData);
    $("#auditOrderGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}

$(function() {
	oms.selFun('operateOrder');
	oms.selFun('auditOrCancel');
	oms.cutCon("cutTab","click","readOn");  //标签切换调用
	
	$.areaSelect({
		id:'areaname'
	});
	
	//订单表格
	jQuery("#salesrOrderGridId").jqGrid({
		datatype: "json",
		url: base + '/template/orderMgr/querySalesOrderGrid.jhtml',
		mtype: 'POST', 
		postData : {"exceptionStatus" : "0", "hasfaudit":"0"},
		height : 'auto',
		colNames: ['序号','操作', '平台', '店铺', '订单编号', '交易单号','标注','卖家备注','买家备注','买家昵称','收货地址','收货信息','审核状态','作废状态','退款状态','发货状态','来源编号'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'act',index:'act' ,width:'70px'},
			{name:'platType',index:'platType',sortable:false},
			{name:'shopName',index:'shopName',sortable:false},
			{name:'orderNum',index:'orderNum'},
			{name:'topTids',index:'topTids',sortable:true },
			{name:'mark',index:'mark'},
			{name:'sellerMemo',index:'sellerMemo'},
			{name:'buyerMemo',index:'buyerMemo'},
			{name:'custNick',index:'custNick'},
			{name:'address',index:'address'},
			{name:'recvInfo',index:'recvInfo'},
			{name:'hasfaudit',index:'hasfaudit',
				formatter:function(cellvalue, options, rowObject){
				var new_format_value=" ";
				if(cellvalue == '1'){
					new_format_value="已审核";
				}
				 return new_format_value  
			 }},
			{name:'disabled',index:'disabled'},
			{name:'hasrefund',index:'hasrefund'},
			{name:'hassend',index:'hassend', formatter:function(cellvalue, options, rowObject){
				var new_format_value=" ";
				if(cellvalue == '1'){
					new_format_value="已发货";
				}
				else{
					new_format_value="未发货";
				}
			   return new_format_value  
			}},
			{name:'fromcodes',index:'fromcodes'}
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
		gridComplete : function() {
			var ids = jQuery("#salesrOrderGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var cl = ids[i];
				var addBtn = "<div class='operating'>";
				addBtn += "<a href='javascript:' class='operating-pencil' title='修改' onclick=\"modSalesOrder("+cl+")\"></a></div>";
				jQuery("#salesrOrderGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		},
		multiselect : true
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("salesrOrderGridId");	
	})
	
	//订单表格
	jQuery("#auditOrderGridId").jqGrid({
		datatype: "json",
		url: base + '/template/orderMgr/querySalesOrderGrid.jhtml',
		mtype: 'POST', 
		postData : {"exceptionStatus" : "0" , "hasfaudit":"1"},
		height : 'auto',
		colNames: ['序号', '平台', '店铺', '订单编号', '交易单号','标注','卖家备注','买家备注','买家昵称','收货地址','收货信息','审核状态','作废状态','退款状态','发货状态','来源编号'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'platType',index:'platType'},
			{name:'shopName',index:'shopName'},
			{name:'orderNum',index:'orderNum'},
			{name:'topTids',index:'topTids',sortable:true},
			{name:'mark',index:'mark'},
			{name:'sellerMemo',index:'sellerMemo'},
			{name:'buyerMemo',index:'buyerMemo'},
			{name:'custNick',index:'custNick'},
			{name:'address',index:'address'},
			{name:'recvInfo',index:'recvInfo'},
			{name:'hasfaudit',index:'hasfaudit', formatter:function(cellvalue, options, rowObject){
				var new_format_value=" ";
				if(cellvalue == '1'){
					new_format_value="已审核";
				}
				 return new_format_value  
			 }},
			{name:'disabled',index:'disabled', formatter:function(cellvalue, options, rowObject){
				var new_format_value=" ";
				if(cellvalue == '1'){
					new_format_value="已作废";
				}
				 return new_format_value  
			 }},
			{name:'hasrefund',index:'hasrefund', formatter:function(cellvalue, options, rowObject){
				var new_format_value=" ";
				if(cellvalue == '1'){
					new_format_value="已退货";
				}
				
			   return new_format_value  
			}},
			{name:'hassend',index:'hassend', formatter:function(cellvalue, options, rowObject){
				var new_format_value=" ";
				if(cellvalue == '1'){
					new_format_value="已发货";
				}
				else{
					new_format_value="未发货";
				}
			   return new_format_value  
			}},
			{name:'fromcodes',index:'fromcodes'}
        ], 
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : 'auditOrderGridPanelId',
		toolbarfilter : true,
		viewrecords : true,
		sortname : 'topTids',
		sortorder : 'act',
		sortable: true,
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
		oms.grid.mdetailconWidth("auditOrderGridId");	
	})
	oms.grid.mdetailconWidth("salesrOrderGridId");
	oms.grid.mdetailconWidth("auditOrderGridId");	
});

function comOrSplit(operType){
	var gridId = getGridId();
	var selectRowId = $('#'+gridId).jqGrid('getGridParam','selarrrow');
    if(selectRowId == ""){
		oms.tooltip("请选择操作的订单!","error");
		return;
	}
	    
	if(operType == "combine"){
		var topTids = checkSales(selectRowId);
		if(!topTids){
			return;
		}else{
			var options = {};
		    options.params = {"topTids" : topTids.join(",")};
		    options.isMask = true;
		    options.url = base+"/template/orderMgr/combineSalesOrder.jhtml";
		    options.successCallback = function(data){
				oms.tooltip(data.errMsg , "succeed");
				refreshGrid();
		    };
		    oms.ajaxCall(options);
		}
	}else if(operType == "cancelCom"){
		if(selectRowId.length>1){
			oms.tooltip("取消合并操作只能选择一条记录!","error");
			return;
		}
		var rowData = $("#"+gridId).jqGrid('getRowData',selectRowId[0]);
		if($.trim(rowData.fromcodes) == ""){
			oms.tooltip("选择订单非合并订单,不符合取消合并订单操作!","error");
			return;
		}
		if($.trim(rowData.hasfaudit) == "1"){
			oms.tooltip("订单已审核不能取消合并!","error");
			return;
		}
		if($.trim(rowData.disabled) == "1"){
			oms.tooltip("订单已作废不能取消合并!","error");
			return;
		}
		var options = {};
		options.params = {"id" : rowData.id};
	    options.isMask = true;
	    options.url = base+"/template/orderMgr/cancelComSalesOrder.jhtml";
	    options.successCallback = function(data){
			oms.tooltip(data.errMsg , "succeed");
			refreshGrid();
	    };
	    oms.ajaxCall(options);
	}else if(operType == "split"){
		if(selectRowId.length > 1){
			oms.tooltip("只能选择一条订单拆分!","error");
		}
		var rowData = $("#"+gridId).jqGrid('getRowData',selectRowId[0]);
		if($.trim(rowData.hasfaudit) == "1"){
			oms.tooltip("订单已审核不能拆分!","error");
			return;
		}
		if($.trim(rowData.disabled) == "1"){
			oms.tooltip("订单已作废不能拆分!","error");
			return;
		}
		if($.trim(rowData.sendStatus) == "1"){
			oms.tooltip("订单已发货不能拆分!","error");
			return;
		}
		if($.trim(rowData.hasrefund) == "1"){
			oms.tooltip("订单已退款不能拆分!","error");
			return;
		}
		if(rowData.fromcodes != ""){
			oms.tooltip("订单:"+rowData.orderNum+"是合并订单，不能拆分!","error");
			return;
		}
		
		artTabs({
	        addTab: {
	            items: {
	                id: 'orderSplit',
	                title: '订单拆分',
	                url: base + '/template/orderMgr/turnOrderSplit.jhtml?id='+rowData.id+''
	            }
	        },
	        isRefresh : true
	    });
	}
}

function checkSales(selectRowId){
	 var topTids = [];
     var shopId = "";
     var custNick = "";
     var address = "";
     var recvInfo = "";
     var gridId = getGridId();
     for(var i in selectRowId){
    	 var rowData = $("#"+gridId).jqGrid('getRowData',selectRowId[i]);
    	 if(i==0){
    		 shopId = rowData.shopId;
    		 custNick = rowData.custNick;
    		 address = rowData.address;
    		 recvInfo = rowData.recvInfo;
    	 }else{
    		 if(shopId != rowData.shopId){
    			 oms.tooltip("只能合并相同的店铺!","error");
    			 return false;
    		 }
    		 if(custNick != rowData.custNick){
    			 oms.tooltip("只能合并相同的买家昵称!","error");
    			 return false;
    		 }
    		 if(address != rowData.address){
    			 oms.tooltip("只能合并相同的收货 地址!","error");
    			 return false;
    		 }
    		 if(recvInfo != rowData.recvInfo){
    			 oms.tooltip("只能合并相同的收货信息!","error");
    			 return false;
    		 }
    	 }
    	 if(rowData.hasfaudit == 1){
    		 oms.tooltip("订单:"+rowData.orderNum+"已审核,不能合并!","error");
    		 return false;
    	 }
    	 if(rowData.disabled == 1){
    		 oms.tooltip("订单:"+rowData.orderNum+"已作废,不能合并!","error");
    		 return false;
    	 }
    	 if(rowData.fromcodes != ""){
    		 oms.tooltip("订单:"+rowData.orderNum+"是合并订单，不能再次合并!","error");
    		 return false;
    	 }
    	 topTids.push(rowData.topTids);
     }
     return topTids;
}

function auditOrCancel(operType){
	var gridId = getGridId();
	var selectRowId = $('#'+gridId).jqGrid('getGridParam','selarrrow');
    if(selectRowId == ""){
		oms.tooltip("请选择操作的订单!","error");
		return;
	}
    
    var ids = [];
	if(operType == "audit"){
		for(var i in selectRowId){
			var rowData = $("#"+gridId).jqGrid('getRowData',selectRowId[i]);  
			 if(rowData.disabled == '1'){
	    		 oms.tooltip("订单:"+rowData.orderNum+"已作废,不能进行审核操作 !","error");
	    		 return;
	    	 }
			 if(rowData.hasfaudit == '1'){
	    		 oms.tooltip("订单:"+rowData.orderNum+"已审核,不能重复审核!","error");
	    		 return;
	    	 }
			 if(rowData.hasrefund == '1'){
	    		 oms.tooltip("订单:"+rowData.orderNum+"已退款,不能进行审核操作 !","error");
	    		 return;
	    	 }
			 ids.push(rowData.id);
	    }
		var options = {};
		options.params = {"ids" : ids.join(",")};
	    options.isMask = true;
	    options.url = base+"/template/orderMgr/auditSalesOrder.jhtml";
	    options.successCallback = function(data){
			oms.tooltip(data.errMsg , "succeed");
			refreshGrid();
	    };
	    oms.ajaxCall(options);
	}else if(operType == "cancelAudit"){
		for(var i in selectRowId){
			 var rowData = $("#"+gridId).jqGrid('getRowData',selectRowId[i]);  
			 if(rowData.disabled == '1'){
	    		 oms.tooltip("订单:"+rowData.orderNum+"已作废,不能进行取消审核操作 !","error");
	    		 return;
	    	 }
			 if(rowData.hasfaudit == '0'){
	    		 oms.tooltip("订单:"+rowData.orderNum+"未审核,不能进行取消审核操作 !","error");
	    		 return;
	    	 }
			 if(rowData.sendStatus == '1'){
	    		 oms.tooltip("订单:"+rowData.orderNum+"已发货,不能进行取消审核操作 !","error");
	    		 return;
	    	 }
			 ids.push(rowData.id);
	    }
		var options = {};
		options.params = {"ids" : ids.join(",")};
	    options.isMask = true;
	    options.url = base+"/template/orderMgr/cancelAuditSalesOrder.jhtml";
	    options.successCallback = function(data){
			oms.tooltip(data.errMsg , "succeed");
			refreshGrid();
	    };
	    oms.ajaxCall(options);
	}else{
		oms.tooltip("无该选项" , "error");
	}
}

function addSalesOrder(){
	artTabs({
        addTab: {
            items: {
                id: 'addSalesOrder',
                title: '添加订单信息',
                url: base + '/template/orderMgr/turnAddSalesOrder.jhtml'
            }
        }
    })
}

function modSalesOrder(rowId){
	var row = $("#salesrOrderGridId").getRowData(rowId);
	artTabs({
        addTab: {
            items: {
                id: 'modSalesOrder',
                title: '修改订单信息',
                url: base + '/template/orderMgr/turnModSalesOrder.jhtml?id='+row.id+''
            }
        },
        isRefresh : true
    })
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
    		oms.tooltip("订单"+nextRow.orderNum+"已发货,不能修改物流公司","error");
    		return;
    	}
    }
    
    var str = encodeURI(base+"/template/orderMgr/turnModPrintTemplate.jhtml");
	oms.s_addPop("修改打印模板",str,"",600,350);
}

function getGridId(){
	var gridId ="";
	var conId = $('#tabConId').children('div:visible').attr('id');
    if(conId == "cutTabCon0"){
    	gridId = "salesrOrderGridId";
    }else if(conId == "cutTabCon1"){
    	gridId = "auditOrderGridId";
    }
    return gridId;
}