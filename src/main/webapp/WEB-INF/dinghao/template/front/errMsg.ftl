	<#include "/template/front/header.ftl">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>交易错误</title>
	<script>
		$(function(){
			var errMsg = "${commResponse.errMsg}";
			if(errMsg != null && errMsg != "") {
				errMsg = decodeURI(errMsg); 
			}else{
				errMsg = "系统出错了!";
			}	
			$("#errMsgId").html(errMsg);
		});
		
		function prevPage() {
			history.back(-1); // 跳转
			return false;
		}
	
	</script> 
	</head>
	<body>
	<div class="error_tips text-center">
	    <div class="c-red f-size24 f-mt40">
	        <span class="f-size36 f-mt8 f-mr5 icon-radius icon-success error"></span>
	        <span>交易错误</span>
	    </div>
	    <p class="f-mt20 f-size16" class="text">错误信息：<span id="errMsgId"></span></p>
	    <!-- <a href="javascript:prevPage()" class="u-btn u-btn-lg u-btn-bg-blue f-mt20">返回</a>-->
	</div>
	</body>
</html>