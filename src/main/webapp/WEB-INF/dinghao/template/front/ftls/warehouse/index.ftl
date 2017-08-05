<#include "/template/front/header.ftl">

<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-toolbar f-mb15 f-clearfix">
			<ul class="search-con f-fl">
				<li><a onClick="addWarehouse('');" href="javascript:"
					class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">新增仓库<i
						class="ico-add"></i></a></li>				
			</ul>
			<ul class="search-con f-fr">
				<li>
        			<div class="checkbox"><input type="checkbox" id="isShow">显示停用仓库</input></div>
    			</li>
			  <li><a href="javascript:search()" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>
			</ul>
		</div>
		<div class="m-content">
			<div class="m-grid-wrap">
				<table id="warehouseIndexId"></table>
				<div id="pager"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${BASE_PATH}/dinghao/template/front/js/bizjs/warehouse/index.js?v=${config_front}"></script>
</body>
</html>