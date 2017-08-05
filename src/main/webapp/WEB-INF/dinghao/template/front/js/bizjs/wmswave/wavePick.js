function endPick(){
	var msg = "";
	if(checkIsEnd()){
		msg = "拣货扫描结束,是否结束此拣货单";
	}else{
		msg = "拣货未扫描完成,是否结束此拣货单";
	}
	$.artDialog({
		title:'提示',
		content:msg,
		ok:function(){
			saveWavePick();
		}
	});
}

$(function(){
	$("#gdsPact").unbind("keydown").bind("keydown",function(e){
		queryFitOrder($(this),e);
	});
	
	jQuery("#wmsWaveDetailGridId").jqGrid({
		datatype: "json",
		url: base + '/template/orderMgr/findOrderItemListByParam.jhtml',
		mtype: 'POST', 
		postData : {"waveNo" : $("#waveNo").val()},
		height : 'auto',
		colNames: ['id','商品编号', '商品名称/规格/属性', '仓库名称','库位编号','订单编号','波次号','商品数量' ,'分拣数量'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'gdsNo',index:'gdsNo'},
			{name:'gdsInfo',index:'gdsInfo'},
			{name:'stockName',index:'stockName'},
			{name:'locNo',index:'locNo'},
			{name:'orderNum',index:'orderNum'},
			{name:'waveId',index:'waveId'},
			{name:'qty',index:'qty'},
			{name:'pickNum',index:'pickNum'},
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
				jQuery("#wmsWaveDetailGridId").setCell(ids[i],"pickNum",0);
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

function queryFitOrder(option, event){
	 if(event.keyCode==13) {
		if(checkIsEnd()){
			oms.tooltip("此拣货单已扫描完成,请不要继续扫描!" ,"succeed");
			$("#mark").val("此拣货单已扫描完成,请不要继续扫描!");
			$("#gdsPact").val("");
			endPickScan();
			$("#gdsPact").blur();
			return;
		}
	    var gdsPact = $("#gdsPact").val();
		if(gdsPact == ""){	    	
			oms.tooltip("还没有扫描商品条码,请扫描！","error");
			$("#gdsPact").focus();
			return; 
	    }
		
	    var options = {};
	    options.params = {"gdsPact" : gdsPact};
		options.url = base+"/template/gdsMgr/selectGdsInfoGridListNoPage.jhtml";
		options.successCallback = function(data){
			if(data.data.length == 0){
				oms.tooltip("没查询到此商品!" ,"error");
				$("#gdsPact").focus();
				return;
			}else if(data.data.length > 1){
				oms.tooltip("商品数据有误，请联系管理员!" ,"error");
				$("#gdsPact").focus();
				return;
			}else{
				var waveNo = $("#waveNo").val();
				var gdsId= data.data[0].id;
			    var options = {};
			    options.params = {"waveNo":waveNo , "gdsId":gdsId};
				options.url = base+"/template/orderMgr/findOrderItemListByParam.jhtml";
				options.chainPar = data.data[0];
				options.successCallback = function(data,status, xhr, goods){
					var itemList = data.data;
					var ids = jQuery("#wmsWaveDetailGridId").jqGrid('getDataIDs');
					var haveOrder = false;
					var stop = false;
					for(var i in itemList){
						for(var j in ids){
							var rowData = $("#wmsWaveDetailGridId").jqGrid('getRowData',ids[j]);
							if(rowData.id == itemList[i].id && rowData.qty>rowData.pickNum){
								jQuery("#wmsWaveDetailGridId").setCell(ids[j],"pickNum",parseInt(rowData.pickNum) + 1);
								$("#orderNum").val(rowData.orderNum);
								$("#waveId").text(rowData.waveId);
								haveOrder = true;
								stop = true;
								$("#mark").val("商品编码:"+goods.gdsNo+",商品名称:"+goods.gdsName+",扫描数量:1,订单号:"+rowData.orderNum+",波次号:"+rowData.waveId);
								if(checkIsEnd()){
									oms.tooltip("扫描完成!" ,"succeed");
									$("#mark").val("拣货单:"+$("#waveNo").val()+"已扫描分拣完成!");
									endPickScan();
									$("#gdsPact").blur();
									return;
								}
								break;
							}
						}
						if(stop){
							break;
						}
					}
					if(!haveOrder){
						$("#gdsPact").val("");
						$("#gdsPact").blur();
						oms.tooltip("此商品已扫描完成,或则此拣货单暂无此商品,请扫描其他商品!" ,"error");
					}
				};
				oms.ajaxCall(options);
			}
			$("#gdsPact").focus();
		};
		oms.ajaxCall(options);
	 }
}

function checkIsEnd(){
	var ids = jQuery("#wmsWaveDetailGridId").jqGrid('getDataIDs');
	var isEnd = true;
	for(var j in ids){
		var rowData = $("#wmsWaveDetailGridId").jqGrid('getRowData',ids[j]);
		if(rowData.qty > rowData.pickNum){
			isEnd = false;
		}
	}
	return isEnd;
}

function endPickScan(){
	$.artDialog({
		title:'提示',
		content:'拣货扫描完成,是否结束此拣货单？',
		ok:function(){
			saveWavePick();
		},
		cancel:function(){
			$("#gdsPact").focus();
		}
	});
}

function saveWavePick(){
	var options = {};
    options.params = {"id":$("#id").val()};
	options.url = base+"/template/wmswave/savePickScanWmsWave.jhtml";
	options.successCallback = function(data){
		oms.tooltip("扫描拣货单:"+$("#waveNo").val()+"分拣成功!" ,"succeed");
		
		artTabs({
		 
		    closeTab: true
		  
		});
	};
	oms.ajaxCall(options);
}
