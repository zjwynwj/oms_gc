	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
			<div class="m-toolbar f-mb15 f-clearfix">
				<input type="hidden" id="orderId">
		        <ul class="search-con f-fl">
		            <li>
		            	<label>快递单号:</label>
		            	<input type="text" class="u-ipt u-ipt-nm" id="expcode" />
	            	</li>
	            	<li>
		            	<label>商品条码:</label>
		            	<input type="text" class="u-ipt u-ipt-nm" id="gdsPact"  />
	            	</li>
	            	<li>
		            	<label>订单号:</label>
		            	<input type="text" class="u-ipt u-ipt-nm u-ipt-disabled" id="orderNum" disabled=""  />
	            	</li>
	            	<li>
		            	<label>扫描信息:</label>
		            	<textarea class="u-textarea u-textarea-nm" rows="3" id="mark"></textarea>
	            	</li>
	            </ul>
	            <ul class="search-con f-fr">
	            	<li>
		        		<a onclick="forcedBy()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">强制扫描通过</a>
	        		</li>
	            </ul>
			</div>
			<div class="m-grid-wrap f-mb20 f-clearfix">
				<table id="inspectGoodsGridId"></table>
			</div>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/order/inspectGoods.js?v=${config_front}"></script>	
</html>