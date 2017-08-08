	<#include "/template/front/header.ftl">
	<body class="m-pop-body">
		<div class="m-pop-detail f-clearfix">
			<div class="m-prd-kind" style="border-right:none;">
		    	<ul id='gdsCls1'></ul>
		    </div>
		    <div class="m-prd-kind" style="border-right:none;">
		    	<ul id='gdsCls2'></ul>
		    </div>
		    <div class="m-prd-kind">
		    	<ul id='gdsCls3'></ul>
		    </div>
		    <div class="f-clearfix"></div>
		    <div id='gdsClsDetailInfo' class='f-pt10'></div>
		</div>
		<div class="m-pop-bot">
			<a href="javascript:" onclick="saveGdsClsFn()" id="save" class="u-btn u-btn-nm u-btn-bg-blue f-mr15">确认</a>
			<a href="javascript:" onclick="closeWinFn()" id="close" class="u-btn u-btn-nm u-btn-bg-blue">关闭</a>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/goods/gdsCls/gdsClsSelect.js?v=${config_front}"></script>
</html>