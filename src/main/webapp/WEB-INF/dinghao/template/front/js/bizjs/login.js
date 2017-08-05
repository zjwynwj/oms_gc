/*******************************************************************************
 * 函数名称: <ATFunc>login.js</ATFunc> 函数功能: <ATFuncDesc></ATFuncDesc> 开发人员: Zihan
 * 2016年3月7日 修改记录:
 ******************************************************************************/

$(function() {
    $("#adminForm").validationEngine();
});
/**
 * 显示表单的错误提示
 * 
 * @param id
 *                表单ID
 * @param errors
 *                错误列表
 */
function showErrors(errors) {
    var MSG = "";
    for ( var name in errors) {
	MSG += errors[name]+"<br/>";
    }
    $.artDialog({
	title : '消息',
	content : MSG
    });
}
$(function() {
    $('#adminForm').ajaxForm(
	    {
		dataType : 'json',
		success : function(data) {
		    if (data.result) {
			parent.location.href = base_template+"/my_page.jhtml";
		    } else {
			showErrors(data.errors);
			var textResult = "";
			for ( var name in data.errors) {
			    textResult += (data.errors)[name] + "</br>";
			}
			if (data.msg == "change_captcha") {
			    $('#validatecode').attr(
				    "src",
				    base+"/admin/captcha.jhtml?"
					    + Math.random());
			    $('#adminForm input[name="captcha"]').val('');
			}
		    }
		}
	    });
});
