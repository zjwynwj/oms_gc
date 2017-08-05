var orderDataEnd = false;
var detailDataEnd = false;
var	waveIdJson = {}; 

function saveWmsWave(){
    var options = {};
    options.params = {"orderIds":$("#orderIds").val() , "waveNo":$("#waveNo").val(),"createDate":$("#createDate").val()};
	options.url = base+"/template/wmswave/generateWaveNo.jhtml";
	options.successCallback = function(data){
		oms.tooltip(data.errMsg,"succeed");
		
		artTabs({
		  //  toTab: {id : "orderPrint"},
		    closeTab: true
		    //isRefresh: true
		});
	};
	oms.ajaxCall(options);
}

$(function(){
	//单据初始化	
	var options = {};
    options.params = {"nuType":"09"};
	options.url = base+"/template/baseNumber/findBaseNumberNext.jhtml";
	options.successCallback = function(data){
		$("#waveNo").val(data.data);
	};
	oms.ajaxCall(options);
	
	$("#createDate").val(oms.formatDate(new Date()));
	
	jQuery("#wmsWaveOrderGridId").jqGrid({
		datatype: "json",
		url: base + '/template/orderMgr/findOrderDataByParam.jhtml',
		mtype: 'POST', 
		postData : {"ids" : $("#orderIds").val()},
		height : 'auto',
		colNames: ['id','订单编号', '波次号', '交易单号', '店铺名称', '仓库名称','物流公司','物流单号','退货','卖家备注','买家备注'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'orderNum',index:'orderNum'},
			{name:'waveId',index:'waveId',width:'60px'},
			{name:'topTids',index:'topTids'},
			{name:'shopName',index:'shopName'},
			{name:'warehouseName',index:'warehouseName'},
			{name:'expressName',index:'expressName'},
			{name:'expcode',index:'exprexpcodeessName'},
			{name:'hasrefund',index:'hasrefund',width:'60px'},
			{name:'sellerMemo',index:'sellerMemo'},
			{name:'buyerMemo',index:'buyerMemo'},
        ], 
		rowNum : 30,
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
			root: "data",  
			repeatitems : false
		},
		ajaxGridOptions:{
			timeout:oms.ajaxTimeout
		},//统一超时时间
		successTip:false,
		gridComplete : function() {
			var ids = jQuery("#wmsWaveOrderGridId").jqGrid('getDataIDs');
			var j = 1;
			for (var i = 0; i < ids.length; i++) {
				var rowData = $("#wmsWaveOrderGridId").jqGrid('getRowData',ids[i]);
				waveIdJson[rowData.orderNum] = j;
				jQuery("#wmsWaveOrderGridId").setCell(ids[i],"waveId",j++);
			}
			orderDataEnd = true;
			bindWaveId();
		},
		beforeProcessing:oms.grid.ajaxSuccessFn,
		loadError:oms.grid.ajaxErrorFn
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("wmsWaveOrderGridId");	
	});
	
	jQuery("#wmsWaveDetailGridId").jqGrid({
		datatype: "json",
		url: base + '/template/orderMgr/findOrderItemListByParam.jhtml',
		mtype: 'POST', 
		postData : {"orderIds" : $("#orderIds").val()},
		height : 'auto',
		colNames: ['id','商品编号', '商品名称/规格/属性', '波次', '数量', '仓库名称','库位编号','物流公司','物流单号','订单编号','分拣数量'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'gdsNo',index:'gdsNo'},
			{name:'gdsInfo',index:'gdsInfo'},
			{name:'waveId',index:'waveId'},
			{name:'qty',index:'qty'},
			{name:'stockName',index:'stockName'},
			{name:'locNo',index:'locNo'},
			{name:'expressName',index:'expressName'},
			{name:'expcode',index:'expcode'},
			{name:'orderNum',index:'orderNum'},
			{name:'waveNum',index:'waveNum'},
        ], 
		rowNum : 50,
		pager : 'wmsWaveDetailGridPanelId',
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
			root: "data",  
			repeatitems : false
		},
		ajaxGridOptions:{
			timeout:oms.ajaxTimeout
		},//统一超时时间
		successTip:false,
		gridComplete : function() {
			detailDataEnd = true;
			bindWaveId();
		},
		beforeProcessing:oms.grid.ajaxSuccessFn,
		loadError:oms.grid.ajaxErrorFn
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("wmsWaveDetailGridId");	
	});
});

function bindWaveId(){
	if(orderDataEnd == true && detailDataEnd==true){
		var ids = jQuery("#wmsWaveDetailGridId").jqGrid('getDataIDs');
		for (var i = 0; i < ids.length; i++) {
			var rowData = $("#wmsWaveDetailGridId").jqGrid('getRowData',ids[i]);
			var waveNum = waveIdJson[rowData.orderNum] +"-"+rowData.orderNum+"("+rowData.qty+")";
			jQuery("#wmsWaveDetailGridId").setCell(ids[i],"waveId",waveIdJson[rowData.orderNum]);
			jQuery("#wmsWaveDetailGridId").setCell(ids[i],"waveNum",waveNum);
		}
	}
}