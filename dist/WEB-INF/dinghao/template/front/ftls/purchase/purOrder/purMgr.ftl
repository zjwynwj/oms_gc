	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
			<div class="m-toolbar f-mb15 f-clearfix">
				<ul class="search-con f-fl">
	        		<li><a onclick="turnAddPurOrder()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">新增采购订单<i class="ico-add"></i></a></li>
	        		<li>
		        		<a onclick="stockOperate('ingoods')" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">生成入库单</a>
	        		</li>
	        		<li>
		        		<a onclick="stockOperate('outgoods')" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">生成出库单</a>
	        		</li>
	        	</ul>
		        <ul class="search-con f-fr">
		        	<li>
		                <label style = "float:left;width:auto;" >采购订单：</label> 
		        	 	<input type="text" class="u-ipt u-ipt-nm" placeholder="采购订单/供应商" id="keyWord"/>
	        	 	</li>
	        	 	
	        	 	<li>
	        	 		<li><a href="javascript:search()" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>
	        	 	</li>
		        </ul>
			</div>
			<div class="m-detail-wrap">
	    		<div class="m-grid-wrap f-mb20 f-clearfix">
					<table id="purOrderGridId"></table>
					<div id="purOrderGridPanelId"></div>
		        </div>
			</div>
			
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/purchase/purOrder/purMgr.js?v=${config_front}"></script>
</html>

