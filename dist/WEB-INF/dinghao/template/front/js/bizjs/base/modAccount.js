/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面初始化 请求客户信息</ATFuncDesc>       
开发人员:   gucong
修改记录:
******************************************************************************/
$(function(){
	/*
	var options = {};
    options.params = {"id":$("#id").val()};
	options.url = base_template+"/custInfo/queryCustInfoById.jhtml";
	options.successCallback = queryCustBackFn;
	oms.ajaxCall(options);
	*/
}); 

/******************************************************************************
函数名称:   <ATFunc>queryCustBackFn</ATFunc>
函数功能:   <ATFuncDesc>客户信息填充到页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function queryCustBackFn(data){
	var custInfo = data.data;
	$("#custNo").val(custInfo.custNo);
	$("#custName").val(custInfo.custName);
	$("#linkPhone").val(custInfo.linkPhone);
	$("#linkMan").val(custInfo.linkMan);
	$("#linkAddr").val(custInfo.linkAddr);
	$("#fax").val(custInfo.fax);
	$("#postCode").val(custInfo.postCode);
	$("#compPhone").val(custInfo.compPhone);
	$("#remark1").val(custInfo.remark1);
	$("input:radio[name=status][value="+custInfo.status+"]").attr("checked", "true");
}

/******************************************************************************
函数名称:   <ATFunc>saveShop</ATFunc>
函数功能:   <ATFuncDesc>修改店铺信息</ATFuncDesc>       
开发人员:   gucong
修改记录:
******************************************************************************/
function saveAccount(){
	var financeAccount = {};
	financeAccount.id= $.trim($("#id").val());
	financeAccount.type= $.trim($("#type").val());
	financeAccount.bankNo=$.trim($("#bankNo").val());
	financeAccount.bankName=$.trim($("#bankName").val());
	financeAccount.bankAccount=$.trim($("#bankAccount").val());
	financeAccount.amount=$.trim($("#amount").val());
	financeAccount.status=$("input[name=status]").filter(':checked').val();
	if($("input[name=isdefault]").filter(':checked').val() ==1)
    {
		 financeAccount.isdefault=1; 
    }
	else
		{
		 financeAccount.isdefault=0;
		}
	
    

		
	var options = {};
    options.params = financeAccount;
	options.url = base_template+"/finance/modAccount.jhtml";
	options.successCallback = modAccountBackFn;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>modShopBackFn</ATFunc>
函数功能:   <ATFuncDesc>修改回调函数</ATFuncDesc>       
开发人员:   gucong
修改记录:
******************************************************************************/
function modAccountBackFn(data){
	oms.tooltip(data.errMsg , "succeed");
	/*
	artTabs({
	    toTab: {id : "shopMgr"},
	    isRefresh: true
	});
	
	artTabs({
	    toTab: {id : "modShop"},
	    isRefresh: true
	});
	*/
}