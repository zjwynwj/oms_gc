/**
 * 页面 menu/add.ftl中引入的Js
 */
$(function() {
    $("#add_form").validationEngine();
    // 表单提交后获取
    $('#add_form').ajaxForm({
	dataType : 'json',
	success : function(data) {
	    if (data.result) {
		bootbox.alert("保存成功，将刷新页面", function() {
		    window.location.reload();
		});
	    } else {
		layer.alert(data.errors, {
		    icon : 2
		});
	    }
	}
    });

    $("#parentId").change(function() {
	var value = $(this).val();
	if (value == "") {
	    $("#href").attr("readonly", "readonly");
	    $("input[name='menuType']").val('1');
	    $("#href").val("");
	    $(".grandparents").show();
	} else {
	    $("#href").removeAttr("readonly");
	    $("input[name='menuType']").val('2');
	    $(".grandparents").hide();
	}
    });
    $("#parentId").change();
});