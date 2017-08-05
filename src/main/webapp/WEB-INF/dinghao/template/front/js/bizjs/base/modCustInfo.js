/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面初始化 请求客户信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
$(function(){
	//var options = {};
    //options.params = {"id":$("#id").val()};
	//options.url = base_template+"/custInfo/queryCustInfoById.jhtml";
	//options.successCallback = queryCustBackFn;
	//oms.ajaxCall(options);
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
函数名称:   <ATFunc>saveCustInfo</ATFunc>
函数功能:   <ATFuncDesc>修改客户信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveCustInfo(){
	var custInfoVo = {};
	custInfoVo.id= $.trim($("#id").val());
	custInfoVo.custNo= $.trim($("#custNo").val());
	custInfoVo.custName=$.trim($("#custName").val());
	custInfoVo.linkPhone=$.trim($("#linkPhone").val());
	custInfoVo.linkMan=$.trim($("#linkMan").val());
	custInfoVo.linkAddr=$.trim($("#linkAddr").val());
	custInfoVo.fax=$.trim($("#fax").val());
	custInfoVo.postCode=$("#postCode").val();
	custInfoVo.compPhone=$.trim($("#compPhone").val());
	custInfoVo.remark1=$.trim($("#remark1").val());
	custInfoVo.status=$("input[name=status]").filter(':checked').val();
	
	var options = {};
    options.params = custInfoVo;
	options.url = base_template+"/custInfo/modCustInfo.jhtml";
	options.successCallback = modCustBackFn;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>addCustBackFn</ATFunc>
函数功能:   <ATFuncDesc>修改客户信息回调函数</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function modCustBackFn(data){
	oms.tooltip(data.errMsg , "succeed");
	/*
	artTabs({
	    toTab: {id : "shopMgr"},
	    isRefresh: true
	});
	*/
	
}