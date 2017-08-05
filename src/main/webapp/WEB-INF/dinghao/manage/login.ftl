<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Mosaddek">
<meta name="keyword"
	content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

<title>oms 后台</title>

<!-- Bootstrap core CSS -->
<link href="${BASE_PATH}/dinghao/manage/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${BASE_PATH}/dinghao/manage/css/bootstrap-reset.css"
	rel="stylesheet">

<!--external css-->
<link
	href="${BASE_PATH}/dinghao/manage/assets/font-awesome/css/font-awesome.css"
	rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="${BASE_PATH}/dinghao/manage/css/style.css" rel="stylesheet">
<link href="${BASE_PATH}/dinghao/manage/css/style-responsive.css"
	rel="stylesheet" />

<!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
<!--[if lt IE 9]>
    <script src="${BASE_PATH}/dinghao/manage/js/html5shiv.js"></script>
    <script src="${BASE_PATH}/dinghao/manage/js/respond.min.js"></script>
    <![endif]-->
<script src="${BASE_PATH}/dinghao/manage/js/jquery.js"></script>
<script src="${BASE_PATH}/dinghao/manage/js/jquery.form.min.js"></script>
<script src="${BASE_PATH}/dinghao/manage/assets/layer/layer.js"></script>
<script
	src="${BASE_PATH}/dinghao/manage/assets/layer//extend/layer.ext.js"></script>
<style type="text/css">
p.error {
	color: #DE5959;
}

.form-signin input[type="text"].error, .form-signin input[type="password"].error
	{
	border-color: #b94a48;
	color: #b94a48;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
}

input.error:focus {
	border-color: #953b39;
	color: #b94a48;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px
		#d59392;
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 6px #d59392;
}
</style>
</head>

<body class="login-body">

	<div class="container">

		<form class="form-signin" id="adminForm"
			action="${BASE_PATH}/admin/loginJson.jhtml" autocomplete="off"
			method="post">
			<h2 class="form-signin-heading">
				<img src="${TEMPLATE_BASE_PATH}/images/logo.png"
					style="height: 38px;" />
			</h2>
			<div class="login-wrap">
				<div class="form-group">
					<label for="exampleInputEmail1">用户名</label> <input type="text"
						name="name" class="form-control validate[required]" placeholder="用户名" value=""
						style="*width: 250px;" autofocus>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">密码</label> <input type="password"
						name="password" class="form-control validate[required]" placeholder="密码" value=""
						style="*width: 250px;">
				</div>
				<div class="form-group">
					<input type="text" name="captcha" class="form-control validate[required]"
						placeholder="验证码" style="width: 100px; float: left;"> <img
						id="captcha"
						style="cursor: pointer; cursor: hand; margin-top: -13px;"
						onclick="this.src='${BASE_PATH}/admin/captcha.jhtml?'+Math.random();"
						src="${BASE_PATH}/admin/captcha.jhtml">
				</div>
				<div class="clearfix"></div>
				<div>
					<p class="error" for="captcha" style="display: none;"></p>
				</div>
				<button class="btn btn-lg btn-login btn-block" type="submit">登录</button>
			</div>
		</form>

	</div>
	<!--添加校验-->
	<script
		src="${BASE_PATH}/dinghao/manage/assets/jquery.validationEngin/jquery.validationEngine.js"></script>
	<script
		src="${BASE_PATH}/dinghao/manage/assets/jquery.validationEngin/jquery.validationEngine-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css"
		href="${BASE_PATH}/dinghao/manage/assets/jquery.validationEngin/validationEngine.jquery.css" />
	<script type="text/javascript">
	$(function(){
		$("#adminForm").validationEngine();
	});
		/**
		 * 显示表单的错误提示
		 * @param id 表单ID
		 * @param errors 错误列表
		 */
		function showErrors(id, errors) {
			id.find('p[class=error]').hide();
			id.find('input,select').removeClass("error");
			for ( var name in errors) {
				var e = id.find('p[for=' + name + ']');
				id.find('input[name=' + name + '],select[name=' + name + ']')
						.addClass("error");
				if (e.length == 0) {
					id.find(
							'input[name=' + name + '],select[name=' + name
									+ ']').after(
							'<p for="'+name+'" class="error"></p>');
					e = id.find('p[for=' + name + ']');
				}
				if (errors[name] != "") {
					e.html(errors[name]);
					e.show();
				}
			}
		}
		$(function() {
			$('#adminForm')
					.ajaxForm(
							{
								dataType : 'json',
								success : function(data) {
									if (data.result) {
									<#if requestUrlString??>
										location.href = "${requestUrlString}";
									</#if>
									} else {
										//showErrors($('#adminForm'), data.errors);
										var textResult="";
										for ( var name in data.errors) {
											textResult+=(data.errors)[name]+"</br>";
										}
										layer.msg(textResult, {icon: 2});
										if (data.msg == "change_captcha") {
											$('#captcha').attr(
													"src",
													"${BASE_PATH}/admin/captcha.jhtml?"
															+ Math.random());
											$(
													'#adminForm input[name="captcha"]')
													.val('');
										}
									}
								}
							});
		});
	</script>
</body>
</html>