	<#include "/template/front/header.ftl">
	<body class="m-pop-body">
		<div class="m-pop-detail f-clearfix">
			<ul class="m-message-list single-row f-mb10 f-clearfix">
				<li>
	            	<label>打印模板：</label>
	            	<input id="printTemplateName" width="200px"	name="printTemplateName">
	            	<input id="printId" type="hidden" name="printId">
	            	<input id="expid" type="hidden" name="expid">
            	</li>
			</ul>
		</div>
		<div class="m-pop-bot">
			<a href="javascript:" onclick="saveExpressFn()" id="save" class="u-btn u-btn-nm u-btn-bg-blue f-mr15">确认</a>
			<a href="javascript:" onclick="closeWinFn()" id="close" class="u-btn u-btn-nm u-btn-bg-blue">关闭</a>
		</div>
		
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/order/modPrintTemplate.js?v=${config_front}"></script>
</html>

