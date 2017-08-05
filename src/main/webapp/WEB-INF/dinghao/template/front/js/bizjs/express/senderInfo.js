$(function(){
	$.areaSelect({
		id:'area'
	});
	
	var getInputs = $('#iform').find('input[type=text]').filter('.verify'); //得到表单中所有的input
	getInputs.blur(function(){
		var $this = $(this);
		getReadyverify($this);	
	})
	
	var options = {};
	options.url = base+"/template/senderInfoMgr/querySenderInfo.jhtml";
	options.successCallback = function(result){
		var data  = result.data;
		$("#id").val(data.id);
		$("#senderName").val(data.senderName);
		$("#mobile").val(data.mobile);
		$("#telephone").val(data.telephone);
		var area = data.state;
		if(data.city != ""){
			area += "-"+data.city;
		}
		if(data.district != ""){
			area += "-"+data.district;
		}
		$("#area").val(area);
		$("#sendAddress").val(data.sendAddress);
		$("#postcode").val(data.postcode);
		$("#shopTitle").val(data.shopTitle);
		$("#memo1").val(data.memo1);
		$("#memo2").val(data.memo2);
		$("#memo3").val(data.memo3);
		
	};
	oms.ajaxCall(options);
	
});

function saveSenderInfo(){
	var getInputs = $('#iform').find('input[type=text]').filter('.verify');	 
	for(var i = 0; i < getInputs.length; i++){
		var $this = getInputs.eq(i);
		getReadyverify($this);
		if($this.hasClass("v_Error")){
			$this.focus();
			return;
		}
	}
	var area = $("#area").val();
	if(area == "" || area=="请选择城市"){
		oms.tooltip("请选择省市区!","error");
		return;
	}
	$("#state").val(area.split("-")[0]);
	$("#city").val(area.split("-")[1]);
	$("#district").val(area.split("-")[2]);
	
	oms.ajaxFormCall({
		formId:"iform",
		successTip:true,
		successCallback:function(data){
			oms.tooltip("保存发货人信息成功!","succeed");
		}
	})
}