<#include "/template/front/header.ftl">

<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-toolbar f-mb15 f-clearfix">
		<input type="hidden" name="dhWarehousePositions" value="${warehouse.id}"> 
			<ul class="search-con f-fl">
				<li>${warehouse.warehouseName} <a href="javascript:void(0);" onclick="saveOrUpdate('','${warehouse.id}')" class="u-btn u-btn-nm u-btn-bg-blue u-btn-auto  u-bd-color-blue">新增</a></li>				
			</ul>
			<ul class="search-con f-fr">
				<li>
        			<div class="checkbox"><input type="checkbox" id="isShow">显示停用库位</input></div>
    			</li>
				<li> <a href="javascript:search();"  class="u-btn u-btn-nm u-btn-bg-gray u-bd-color-gray">刷新</a></li>				
			</ul>
		</div>
		<div class="m-content">
			<div class="m-grid-wrap">
				<table id="warehouseIndexId"></table>
				<div id="pager"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${BASE_PATH}/dinghao/template/front/js/bizjs/warehouse/warehouse_positions.js?v=${config_front}"></script>
</body>
</html>