/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面初始化  请求单据号</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
$(function(){
	/*
	var options = {};
    options.params = {"nuType":"02"};
	options.url = base+"/template/baseNumber/findBaseNumberNext.jhtml";
	options.successCallback = numberBackFn;
	oms.ajaxCall(options);
	//默认为启用
	$("input:radio[name=status][value=1]").attr("checked", "true");
	*/
	
	var getInputs = $('#verifyCon').find('input[type=text]').filter('.verify');	 //得到表单中所有的input
	getInputs.blur(function(){
		var $this = $(this);
		getReadyverify($this);	
	})
	
	
}); 

/******************************************************************************
函数名称:   <ATFunc>numberBackFn</ATFunc>
函数功能:   <ATFuncDesc>填充单据号</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function numberBackFn(data){
	$("#custNo").val(data.data);
}

/******************************************************************************
函数名称:   <ATFunc>saveCustInfo</ATFunc>
函数功能:   <ATFuncDesc>添加客户信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveAccount(){
	
	var getInputs = $('#verifyCon').find('input[type=text]').filter('.verify');	 
	for(var i = 0; i < getInputs.length; i++){
		var $this = getInputs.eq(i);
		getReadyverify($this);
		if($this.hasClass("v_Error")){
			alert("验证不成功，请返回！");
			$this.focus();
			return false;
		}
	}
	
	var financeAccount = {};
	financeAccount.type= $.trim($("#type").val());
	financeAccount.bankNo=$.trim($("#bankNo").val());
	financeAccount.bankName=$.trim($("#bankName").val());
	financeAccount.bankAccount=$.trim($("#bankAccount").val());

	financeAccount.isdefault=$.trim($("input[name=isdefault]").filter(':checked').val());

	financeAccount.amount=$.trim($("#amount").val());
	financeAccount.status=$("input[name=status]").filter(':checked').val();
	
	
	 
	var options = {};
    options.params = financeAccount;
	options.url = base_template+"/finance/addAccount.jhtml";
	options.successCallback = addAccountBackFn;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>addCustBackFn</ATFunc>
函数功能:   <ATFuncDesc>添加客户信息回调函数</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function addAccountBackFn(data){
	oms.tooltip(data.errMsg , "succeed");
	/*
	artTabs({
	    toTab: {id : "financeAccountMgr"},
	    closeTab: true,
	    isRefresh: true
	});
	*/
	$.artTabs({
	    closeTab : true
	})
}