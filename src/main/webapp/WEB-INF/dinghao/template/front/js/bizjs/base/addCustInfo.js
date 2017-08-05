/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面初始化  请求单据号</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
$(function(){
	var options = {};
    options.params = {"nuType":"02"};
	options.url = base_template+"/baseNumber/findBaseNumberNext.jhtml";
	options.successCallback = numberBackFn;
	oms.ajaxCall(options);
	//默认为启用
	$("input:radio[name=status][value=1]").attr("checked", "true");
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
function saveCustInfo(){
	var custInfoVo = {};
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
	options.url = base_template+"/custInfo/addCustInfo.jhtml";
	options.successCallback = addCustBackFn;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>addCustBackFn</ATFunc>
函数功能:   <ATFuncDesc>添加客户信息回调函数</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function addCustBackFn(data){
	oms.tooltip(data.errMsg , "succeed");
	/*
	artTabs({
	    toTab: {id : "custMgr"},
	    closeTab: true,
	    isRefresh: true
	});
	*/
}