$(function(){
	$("#expcode").unbind("keydown").bind("keydown",function(e){
		queryFitOrder($(this),e);
	});
	$("#weight").unbind("keydown").bind("keydown",function(e){
		weighting($(this),e);
	});
	$("#expcode").focus();
});

function queryFitOrder(option, event){
	
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
				$("#mark").val("该物流单号对应的订单不存在，请确认!");
				$("#expcode").val("");
				$("#expcode").focus();
				return; 
			}else if(orderData.length >1){
				oms.tooltip("该物流单号对应的多个订单,数据有误,请联系管理员!","error");
				$("#mark").val("该物流单号对应的多个订单,数据有误,请联系管理员!");
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
				$("#mark").val("");
			}
		}
		oms.ajaxCall(options);
	 }
}


function weighting(option, event){
	if(event.keyCode==13){
		var str = $("#weight").val();
		if(str == ""){
			oms.tooltip("还没有称重,请称重！","error");
			return; 			
		}
		var r=/^(([1-9]\d{0,9})|0)(\.\d{1,9})?$/;
		if(r.test(str) == false){
			oms.tooltip("重量值只能输入整数和小数！","error");
			return; 
		}
		var expcode = $("#expcode").val();
		if(expcode == ""){
			oms.tooltip("请先扫描快递单号！","error");
			return; 
		}
		var options = {};
	    options.params = {"id":$("#orderId").val()};
		options.url = base+"/template/orderMgr/queryOrderStandWeight.jhtml";
		options.successCallback = function(data){
			var standWeight = data.data;
			var weight = $("#weight").val();
			if(1.5*parseFloat(standWeight) < weight){
				oms.tooltip("订单重量超标50%以上!","error");
				$("#mark").val("订单重量超标50%以上!");
				return;
			}else{
				var options = {};
			    options.params = {"id":$("#orderId").val() ,'standardWeight':standWeight , 'realWeight':weight};
				options.url = base+"/template/orderMgr/saveOrderWeight.jhtml";
				options.successCallback = function(data){
					oms.tooltip("订单称重成功!","error");
					$("#mark").val("订单称重成功!");
				};
				oms.ajaxCall(options);
			}
			
		}
		oms.ajaxCall(options);
	}	
}
