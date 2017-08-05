/**
 * add_financereceipt.ftl 页面JS
 */
$(function() {
    // 自动生成编号
    // 单据初始化
    var options = {};
    options.params = {
        "nuType": "09"
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
        showOn: "button",
        buttonImage: base + "/dinghao/template/front/js/elem/jqueryui/css/images/calendar.gif",
        buttonImageOnly: true
    });
    
    /**
     * 弹出供应商
     */
    $("#remarks").on(
	    "click",
	    function() {
		oms.s_addPop("供应商选择", base + "/template/custInfo/get_custinfomgr.jhtml",
			"", 1000, 490);
	});
    
    
    $("a.savePurOrder").click(function(){
	//保证数据的一致性
	var recNo=$.trim($("#recNo").val())==''?'':$.trim($("#recNo").val());
	if(recNo==''){
	    $("#providerId").val('');
	}
	$("#myForm").submit();
    });
    
}); // jquery end

$('#myForm').ajaxForm({
    dataType: 'json',
    success: function(data) {
        if (data.success) {
            $.artDialog({
                title: '消息',
                content: data.msg
            });
          // $("#id").val(data.t); 
        } else {
            $.artDialog({
                title: '消息',
                content: data.msg
            });
        }
    }
});


/******************************************************************************
函数名称:   <ATFunc>addFinanceReceipt</ATFunc>
函数功能:   <ATFuncDesc>添加客户信息</ATFuncDesc>       
开发人员:   helong
修改记录:   gucong
******************************************************************************/
function addFinanceReceipt(){
	
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
	
	var financeReceipt = {};
	financeReceipt.recNo= $.trim($("#recNo").val());
	financeReceipt.custId=$.trim($("#providerId").val());
	
	financeReceipt.busiDate=$.trim($("#datepicker").val());
	
	financeReceipt.busiPerson=$.trim($("#busiPerson").val());
	financeReceipt.recAccount=$.trim($("#recAccount").val());
	financeReceipt.amount=$.trim($("#amount").val());
	financeReceipt.finalType=$.trim($("#finalType").val());
	financeReceipt.purpose=$.trim($("#purpose").val());
	financeReceipt.memo=$.trim($("#memo").val());
	
	//financeReceipt.isdefault=$.trim($("input[name=isdefault]").filter(':checked').val());
	//financeReceipt.status=$("input[name=status]").filter(':checked').val();
	 
	var options = {};
    options.params = financeReceipt;
	options.url = base_template+"/finance_receipt/save_financereceipt.jhtml";
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
