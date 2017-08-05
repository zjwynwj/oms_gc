/**
 * add_financereceipt.ftl 页面JS
 */
$(function() {
    // 自动生成编号
    // 单据初始化
    var options = {};
    options.params = {
        "nuType": "12"
    };
    // 没有订单编号 自动生成
    options.url = base + "/template/baseNumber/findBaseNumberNext.jhtml";
    options.successCallback = successBackFn;
    oms.ajaxCall(options);
    function successBackFn(data) {
        $("#receiptCodeShow").html(data.data);
        $("#recNo").val(data.data);
    }
    // 业务时间
    $("#datepicker").datepicker({
        showOn: "both",
        buttonImage: base + "/dinghao/template/front/js/elem/jqueryui/css/images/calendar.gif",
        buttonImageOnly: true
    });
    /*
    $("a.savePurOrder").click(function(){
	//保证数据的一致性
	var recNo=$.trim($("#recNo").val())==''?'':$.trim($("#recNo").val());
	if(recNo==''){
	    $("#providerId").val('');
	}
	$("#myForm").submit();
    });
    */
}); // jquery end


/******************************************************************************
函数名称:   <ATFunc>addFinanceTrans</ATFunc>
函数功能:   <ATFuncDesc>添加信息</ATFuncDesc>       
开发人员:   helong
修改记录:   gucong
******************************************************************************/
function addFinanceTrans(){
	
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
	
	var financeTrans = {};
	financeTrans.recNo= $.trim($("#recNo").val());
	//financeTrans.custId=$.trim($("#providerId").val());
	
	financeTrans.busiDate=$.trim($("#datepicker").val());
	
	financeTrans.busiPerson=$.trim($("#busiPerson").val());
	financeTrans.payAccount=$.trim($("#payAccount").val());
	financeTrans.recAccount=$.trim($("#recAccount").val());
	financeTrans.amount=$.trim($("#amount").val());
	financeTrans.poundage=$.trim($("#poundage").val());
	financeTrans.finalType=$.trim($("#finalType").val());
	financeTrans.payType=$.trim($("#payType").val());
	financeTrans.memo=$.trim($("#memo").val());
	
	//financeReceipt.isdefault=$.trim($("input[name=isdefault]").filter(':checked').val());
	//financeReceipt.status=$("input[name=status]").filter(':checked').val();
	 
	var options = {};
    options.params = financeTrans;
	options.url = base_template+"/finance_transfer/save_financetransfer.jhtml";
	options.successCallback = addBackFn;
	oms.ajaxCall(options);
}

/*
******************************************************************************
函数名称:   <ATFunc>addBackFn</ATFunc>
函数功能:   <ATFuncDesc>添加收款单回调函数</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function addBackFn(data){
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
