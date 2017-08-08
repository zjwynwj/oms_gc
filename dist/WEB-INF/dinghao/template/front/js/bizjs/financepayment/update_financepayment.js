/**
 * add_financereceipt.ftl 页面JS
 */
$(function() {
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
		oms.s_addPop("供应商选择", base_template + "/custInfo/get_custinfomgr.jhtml",
			"", 1000, 500);
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
           $("#id").val(data.t); 
        } else {
            $.artDialog({
                title: '消息',
                content: data.msg
            });
        }
    }
});
