	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con">
			<input type="hidden" id="expressId" value=${dmsExpressVo.id}>
			<div class="m-toolbar f-mb15 f-clearfix">
				<ul class="search-con">
					<li ><span style="font-size:22px;color:red">${dmsExpressVo.name}</span></li>
				</ul>
			</div>
		
			<div class="m-detail-wrap">
	    		<div class="m-grid-wrap f-mb20 f-clearfix">
					<table id="expressareaGridId"></table>
					<div id="expressareaGridPanelId"></div>
		        </div>
			</div>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/express/expressareaMgr.js?v=${config_front}"></script>
</html>
