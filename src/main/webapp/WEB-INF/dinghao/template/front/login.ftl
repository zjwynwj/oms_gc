<#include "header.ftl">
<script>
   /*登陆页面跳出框架*/
   if (window != top)   
     top.location.href = location.href;   
</script>
<style>
body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, form,
	fieldset, input, p, blockquote, th, td {
	margin: 0;
	padding: 0;
}

body {
	font-size: 12px;
	color: #666;
	font-family: Tahoma, "宋体";
	background: #fff;
	line-height: 24px;
}

body.loginbg {
	background: #5da9e5 url(Img/loginbg.png) repeat-x;
}

fieldset, img {
	border: 0;
}

ol, ul {
	list-style: none;
}

h1, h2, h3, h4, h5, h6 {
	font-size: 100%;
}

em {
	font-style: normal;
}

input, button, select, textarea {
	outline: none;
}

body.loginbg {
	background: #5da9e5 url(${TEMPLATE_BASE_PATH}/images/common/loginbg.png)
		repeat-x 0px -50px;
}

.login {
	width: 960px;
	height: 520px;
	margin: 0 auto;
	background: url(${TEMPLATE_BASE_PATH}/images/common/login.png) no-repeat
		0px -50px;
}

.login ul {
	width: 512px;
	margin: 0 auto;
	padding: 245px 0 0 0;
	zoom: 1;
	overflow: hidden;
}

.login ul li {
	height: 28px;
	line-height: 28px;
	padding-bottom: 15px;
	zoom: 1;
	overflow: hidden;
}

.login ul li label {
	width: 140px;
	text-align: right;
	display: inline-block;
	color: #555;
	font-size: 14px;
	margin-right: 10px;
}

.login ul li input.text {
	width: 180px;
	border: none;
	padding: 0px 2px;
	font-family: verdana, "微软雅黑";
	background: none;
}

.login ul li input.verify {
	width: 86px;
	margin-right: 6px;
}

.login ul li span {
	display: inline-block;
	height: 24px;
	line-height: 24px;
}

.login ul li span a {
	color: #fff;
	text-decoration: none;
}

.login ul li span img {
	vertical-align: middle;
	margin-right: 5px;
}

.login ul li.btncheck {
	height: auto;
	padding: 12px 0 0 65px;
}

.login ul li.btncheck input.btn {
	width: 116px;
	height: 38px;
	display: block;
	float: left;
	cursor: pointer;
	background: none;
	border: none;
	overflow: hidden;
	text-indent: -1000px;
}

.login ul li.btncheck input.check {
	vertical-align: middle;
	margin: 0 4px 0 20px;
}

.login ul li.btncheck em {
	color: #fff;
}

.login .copyright {
	display: block;
	text-align: center;
	color: #fff;
	padding-top: 50px;
}

.login .copyright a {
	color: #fff;
}
</style>
<body class="loginbg">
	<div class="login">
		<form class="form-signin" id="adminForm"
			action="${BASE_PATH}/loginJson.jhtml" autocomplete="off"
			method="post">
			<ul>
				<li><label>登录账号:</label><input type="text" name="userName"
					id="username" class="text validate[required]" /></li>
				<li><label>登录密码:</label><input type="password" id="password"
					class="text validate[required]" name="password" /></li>
				<li><label style="position: relative; top: -27px;">验 证
						码:</label> <input type="text" name="captcha" class="text verify"
					placeholder="" style="position: relative; left: 00px; top: -28px;" />
					<img id="validatecode"
					style="cursor: pointer; cursor: hand; margin-top: -13px;"
					onclick="this.src='${BASE_PATH}/admin/captcha.jhtml?'+Math.random();"
					src="${BASE_PATH}/admin/captcha.jhtml"></li>
				<li class="btncheck"><input type="submit" value="登录"
					class="btn" /></li>
			</ul>
		</form>
		<div class="copyright">
			Copyright © 2017 All Rights Reserved
		</div>
	</div>
</body>
<!--添加校验-->
<script
	src="${BASE_PATH}/dinghao/manage/assets/jquery.validationEngin/jquery.validationEngine.js"></script>
<script
	src="${BASE_PATH}/dinghao/manage/assets/jquery.validationEngin/jquery.validationEngine-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="${BASE_PATH}/dinghao/manage/assets/jquery.validationEngin/validationEngine.jquery.css" />
<script type="text/javascript"
	src="${BASE_PATH}/dinghao/template/front/js/bizjs/login.js?v=${config_front}">
    
</script>
</html>
