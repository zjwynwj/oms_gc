	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
		    <div class="m-toolbar f-mb15 f-clearfix">
		        <ul class="search-con f-fl">
        	        <li><a id = "addShop" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">新增店铺<i class="ico-add"></i></a></li>
       		    </ul>
		        <ul class="search-con f-fr">
        	        <li><label>店铺名称:</label><input id="name" type="text" class="u-ipt u-ipt-nm" placeholder="请输入店铺名称"/></li>
                    <li><a href="javascript:" id="searchBtn" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>
         	    </ul>
		    </div>
		    <div>
		        <table id="mainGridId"></table>
			    <div id="mainGridPanelId"></div>
		    <div>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/base/shopMgr.js?v=${config_v}"></script>
</html>
