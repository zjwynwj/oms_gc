	<#include "/template/front/header.ftl">
	<body class="m-pop-body">
		<div class="m-pop-detail f-clearfix">
			<input type="hidden" id="clsId" value="${gdsAttbVo.clsId}">
			<input type="hidden" id="attbs" value="${gdsAttbVo.attbs}">
			<div id='gdsAttr'></div>
		</div>
		<div class="m-pop-bot">
			<a href="javascript:" onclick="saveGdsAttbFn()" id="save" class="u-btn u-btn-nm u-btn-bg-blue f-mr15">确认</a>
			<a href="javascript:" onclick="closeWinFn()" id="close" class="u-btn u-btn-nm u-btn-bg-blue">关闭</a>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/goods/gdsAttr/attrSelect.js?v=${config_front}"></script>
</html>