$(function(){
	if($("#ispickprint").val() == "1"){
		$("#ispickprintCheckBox").attr("checked" , true);
	}
	
	if($("#isscaned").val() == "1"){
		$("#isscanedCheckBox").attr("checked" , true);
	}
	
	jQuery("#wmsWaveOrderGridId").jqGrid({
		datatype: "json",
		url: base + '/template/orderMgr/findOrderDataByParam.jhtml',
		mtype: 'POST', 
		postData : {"waveNo" : $("#waveNo").val()},
		height : 'auto',
		colNames: ['id','订单编号', '波次号', '交易单号', '店铺名称', '仓库名称','物流公司','物流单号','退货','卖家备注','买家备注'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'orderNum',index:'orderNum'},
			{name:'waveId',index:'waveId',width:'60px'},
			{name:'topTids',index:'topTids'},
			{name:'shopName',index:'shopName'},
			{name:'stockName',index:'stockName'},
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
		postData : {"waveNo" : $("#waveNo").val()},
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
			var ids = jQuery("#wmsWaveDetailGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var rowData = $("#wmsWaveDetailGridId").jqGrid('getRowData',ids[i]);
				var waveNum = rowData.waveId +"-"+rowData.orderNum+"("+rowData.qty+")";
				jQuery("#wmsWaveDetailGridId").setCell(ids[i],"waveNum",waveNum);
			}
		},
		beforeProcessing:oms.grid.ajaxSuccessFn,
		loadError:oms.grid.ajaxErrorFn
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("wmsWaveDetailGridId");	
	});
});




function sorting(){

	
	if($("#isscaned").val()=="1"){
		oms.tooltip("拣货单:"+$("#waveNo").val()+"已扫描，请勿重复操作!" ,"error");
		return;
	}
	artTabs({
        addTab: {
            items: {
                id: 'wavePick',
                title: '分拣扫描',
                url: base + '/template/wmswave/turnWavePick.jhtml?id='+$("#id").val()+''
            }
        },
        isRefresh: true
    });  
}

