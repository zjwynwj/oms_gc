	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
			<div class="m-toolbar f-mb15 f-clearfix">
				<ul class="search-con f-fl">
		        	<li><a onclick="turnAddPrintTemplate()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">新增模板<i class="ico-add"></i></a></li>
		        </ul>
		        <ul class="search-con f-fr">
		        	<li>
		        		<label>物流公司名称:</label>
		        	 	<input type="text" class="u-ipt u-ipt-nm" placeholder="" id="keyWord"/>
	        	 	</li>
	        	 	<li>
	        	 		<li><a href="javascript:search()" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>
	        	 	</li>
		        </ul>
			</div>
			<div class="m-detail-wrap">
	    		<div class="m-grid-wrap f-mb20 f-clearfix">
					<table id="printTempalteGridId"></table>
					<div id="printTempalteGridPanelId"></div>
		        </div>
			</div>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/express/printTemplateMgr.js?v=${config_front}"></script>
</html>

