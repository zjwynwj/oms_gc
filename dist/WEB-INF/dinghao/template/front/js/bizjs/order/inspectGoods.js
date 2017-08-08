var haveData = false;

function forcedBy(){
	var msg = "";
	if(checkIsEnd()){
		msg = "订单扫描出库完成,是否结束扫描?";
	}else{
		msg = "订单扫描出库未完成,是否结束扫描?";
	}
	$("#gdsPact").blur();
	$.artDialog({
		title:'提示',
		content:msg,
		ok:function(){
			saveScan();
		},
		cancel:function(){
			$("#gdsPact").focus();
		}
	});
}

$(function(){
	$("#gdsPact").unbind("keydown").bind("keydown",function(e){
		scanGoods($(this),e);
	});
	$("#expcode").unbind("keydown").bind("keydown",function(e){
		queryFitOrder($(this),e);
	});
	$("#expcode").focus();
	
	jQuery("#inspectGoodsGridId").jqGrid({
		data : [],
		datatype : "local",
		height : 'auto',
		colNames: ['id','gdsId','商品编号', '商品名称/规格/属性', '商品数量' ,'扫描数量'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'gdsId',index:'gdsId' , hidden:true },
			{name:'gdsNo',index:'gdsNo'},
			{name:'gdsInfo',index:'gdsInfo'},
			{name:'qty',index:'qty'},
			{name:'scanNum',index:'scanNum'},
        ], 
		rowNum : 50,
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
			var ids = jQuery("#inspectGoodsGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				jQuery("#inspectGoodsGridId").setCell(ids[i],"scanNum",0);
			}
		},
		beforeProcessing:oms.grid.ajaxSuccessFn,
		loadError:oms.grid.ajaxErrorFn
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("inspectGoodsGridId");	
	});
});

function queryFitOrder(option, event){
	haveData = false;
	 if(event.keyCode==13) {
	    var expcode = $("#expcode").val();
	    if($.trim(expcode) == ""){	    	
			oms.tooltip("还没有扫描物流单号,请扫描！","error");
			return; 
	    }
	    
	    var options = {};
	    options.params = {"expcode":expcode};
		options.url = base+"/template/orderMgr/findOrderDataByParam.jhtml";
		options.successCallback = function(data){
			var orderData = data.data;
			if(orderData.length == 0){
				oms.tooltip("该物流单号对应的订单不存在，请确认!","error");
				$("#expcode").val("");
				$("#expcode").focus();
				return; 
			}else if(orderData.length >1){
				oms.tooltip("该物流单号对应的多个订单,数据有误,请联系管理员","error");
				$("#expcode").val("");
				$("#expcode").focus();
				return; 
			}else{
				var order = orderData[0];
				if(order.disabled == 1){//如果选择了审单模块，就要判断异常订单
					oms.tooltip("物流单号:"+ $("#expcode").val() +"对应订单:"+order.orderNum+"已作废，请确认!","error");
					$("#mark").val("物流单号:"+ $("#expcode").val() +"对应订单:"+order.orderNum+"已作废，请确认!");
					$("#expcode").val("");
					$("#expcode").focus();
					return; 
				}
				if(order.sendStatus == 1){
					oms.tooltip("物流单号:"+ $("#expcode").val() +"对应订单:"+order.orderNum+"已发货，请确认!","error");
					$("#mark").val("物流单号:"+ $("#expcode").val() +"对应订单:"+order.orderNum+"已发货，请确认!");
					$("#expcode").val("");
					$("#expcode").focus();
					return; 	
				}
				if(order.hasrefund == 1){
					oms.tooltip("物流单号:"+ $("#expcode").val() +"对应订单:"+order.orderNum+"已退款，请确认!","error");
					$("#mark").val("物流单号:"+ $("#expcode").val() +"对应订单:"+order.orderNum+"已退款，请确认!");
					$("#expcode").val("");
					$("#expcode").focus();
					return; 	
				}
				if(order.hasscaned == 1){
					oms.tooltip("物流单号:"+ $("#expcode").val() +"对应订单:"+order.orderNum+"已扫描出库，请确认!","error");
					$("#mark").val("物流单号:"+ $("#expcode").val() +"对应订单:"+order.orderNum+"已扫描出库，请确认!");
					$("#expcode").val("");
					$("#expcode").focus();
					return; 	
				}
				$("#orderId").val(order.id);
				$("#orderNum").val(order.orderNum);
				loadGridData(order);
			}
		};
		oms.ajaxCall(options);
	 }
}

function loadGridData(data){
	jQuery("#inspectGoodsGridId").clearGridData();
	var options = {};
    options.params = {"orderIds":data.id};
	options.url = base+"/template/orderMgr/findOrderItemListByParam.jhtml";
	options.successCallback = function(data){
		var detailData = data.data;
		if(detailData.length == 0){
			oms.tooltip("订单数据有误，订单联系管理员!","error");
			return;
		}else{
			haveData = true;
		    jQuery("#inspectGoodsGridId").jqGrid('addRowData',1,detailData,"last");
		}
		
	};
	oms.ajaxCall(options);
}

function scanGoods(option, event){
	if(event.keyCode==13) {
		if(!haveData){
			oms.tooltip("还没有扫描商品条码,请扫描！","error");
			return; 
		}
		if(checkIsEnd()){
			oms.tooltip("此订单已扫描出货完成,请不要继续扫描!" ,"succeed");
			$("#mark").val("此订单已扫描出货完成,请不要继续扫描!");
			$("#gdsPact").val("");
			endScan();
			return;
		}
		
		var gdsPact = $("#gdsPact").val();
		if(gdsPact == ""){	    	
			oms.tooltip("还没有扫描商品条码,请扫描！","error");
			return; 
	    }
		 
	    var expcode = $("#expcode").val();
	    if($.trim(expcode) == ""){	 
	    	$("#gdsPact").val("");
			$("#expcode").focus();
			oms.tooltip("还没有扫描物流单号,请扫描！","error");
			return; 
	    }
	    
	    var options = {};
	    options.params = {"gdsPact" : gdsPact};
		options.url = base+"/template/gdsMgr/selectGdsInfoGridListNoPage.jhtml";
		options.successCallback = function(data){
			if(data.data.length == 0){
				oms.tooltip("没查询到此商品!" ,"error");
			}else if(data.data.length > 1){
				oms.tooltip("商品数据有误，请联系管理员!" ,"error");
			}else{
				var gdsId= data.data[0].id;
			    var options = {};
			    options.params = {"orderIds": $("#orderId").val() , "gdsId":gdsId};
				options.url = base+"/template/orderMgr/findOrderItemListByParam.jhtml";
				options.chainPar = data.data[0];
				options.successCallback = function(data,status, xhr, goods){
					var itemList = data.data;
					var ids = jQuery("#inspectGoodsGridId").jqGrid('getDataIDs');
					var haveOrder = false;
					var stop = false;
					for(var i in itemList){
						for(var j in ids){
							var rowData = $("#inspectGoodsGridId").jqGrid('getRowData',ids[j]);
							if(rowData.id == itemList[i].id && rowData.qty>rowData.scanNum){
								jQuery("#inspectGoodsGridId").setCell(ids[j],"scanNum",parseInt(rowData.scanNum) + 1);
								haveOrder = true;
								stop = true;
								$("#mark").val("商品编码:"+goods.gdsNo+",商品名称:"+goods.gdsName+",扫描数量:1,订单号:"+$("#orderNum").val()+"扫描成功");
								if(checkIsEnd()){
									oms.tooltip("扫描完成!" ,"succeed");
									$("#mark").val("物流单号:"+ $("#expcode").val() +"对应订单:"+$("#orderNum").val()+"已扫描出库完成!");
									endScan();
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
						$("#gdsPact").focus();
						oms.tooltip("此商品已扫描完成,或则此订单暂无此商品,请扫描其他商品!" ,"error");
					}
				};
				oms.ajaxCall(options);
			}
		};
		oms.ajaxCall(options);
    }
}

function checkIsEnd(){
	var ids = jQuery("#inspectGoodsGridId").jqGrid('getDataIDs');
	var isEnd = true;
	for(var j in ids){
		var rowData = $("#inspectGoodsGridId").jqGrid('getRowData',ids[j]);
		if(rowData.qty > rowData.scanNum){
			isEnd = false;
		}
	}
	return isEnd;
}

function endScan(){
	$("#gdsPact").blur();
	$.artDialog({
		title:'提示',
		content:'订单扫描扫描完成,是否结束？',
		ok:function(){
			saveScan();
		},
		cancel:function(){
			$("#gdsPact").focus();
		}
		
	});
}

function saveScan(){
	var options = {};
    options.params = {"id":$("#orderId").val()};
	options.url = base+"/template/orderMgr/saveInspectGoods.jhtml";
	options.successCallback = function(data){
		$.artDialog({
			title:'提示',
			content:"订单:"+$("#orderNum").val()+"扫描出库成功,"+"是否继续操作？",
			ok:function(){
				artTabs({
					isRefresh: true
				});
			},
			cancel:function(){
				artTabs({
				    closeTab: true
				});
			}
			
		});
	};
	oms.ajaxCall(options);
}