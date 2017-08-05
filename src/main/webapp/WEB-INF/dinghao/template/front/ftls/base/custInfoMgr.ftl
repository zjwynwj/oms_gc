	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
			<div class="m-toolbar f-mb15 f-clearfix">
				<ul class="search-con f-fl">
	        		<li><a onclick="turnAddCustInfo()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">新增客户<i class="ico-add"></i></a></li>
	        	</ul>
		        <ul class="search-con f-fr">
		        	<li>
		        	 	<input type="text" class="u-ipt u-ipt-nm" placeholder="客户编码/客户名称" id="keyWord"/>
	        	 	</li>
	        	 	<li>
	        	 		<li><a href="javascript:search()" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>
	        	 	</li>
		        </ul>
			</div>
			<div class="m-detail-wrap">
	    		<div class="m-grid-wrap f-mb20 f-clearfix">
					<table id="custInfoGridId"></table>
					<div id="custInfoGridPanelId"></div>
		        </div>
			</div>
			
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/base/custInfoMgr.js?v=${config_front}"></script>
</html>

