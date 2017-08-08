	<#include "/template/front/header.ftl">
	<link rel="stylesheet" type="text/css" href="${BASE_PATH}/dinghao/template/front/js/elem/select2-master/dist/css/select2.min.css?v=${config_front}">
	<script src="${BASE_PATH}/dinghao/template/front/js/elem/select2-master/dist/js/select2.min.js?v=${config_front}"></script>
	<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-form-tit f-mb20 f-clearfix">
			<input type="hidden" value="${salesOrder.id}" id="id">
			<ul class="search-con f-fr">
				<input type="button"  id="save" class="u-btn u-btn-auto u-btn-bg-blue" value="保存">
		    </ul>
	    </div>
      	<div class="m-content">
	    	<h4 class="m-title">订单信息</h4>
            	<ul class="m-message-list f-mb10 f-clearfix">
		        	<li>
		        		<label>平台:</label>
		                <input  readonly="readonly" id="platType" width="200px"  class="u-ipt u-ipt-nm u-ipt-disabled" name="platType" value="${salesOrder.platType}">
		            </li>
		        	<li><label>订单编号:</label><span id="orderNum">${salesOrder.orderNum}</span></li>
		            <li>
		            	<label>店铺:</label>
		            	<span id="shopName">${salesOrder.shopName}</span>
		            	<input id="shopId" type="hidden" name="shopId" value="${salesOrder.shopId}">
	            	</li>
		            <li>
		            	<label>订单金额:</label>
		                <input type="text" class="u-ipt u-ipt-nm"   id="totalFee" value="${salesOrder.totalFee}" readonly style="TEXT-ALIGN:right"/>
		            </li>
					<li>
						<label>付款金额:</label>
						<input type="text" class="u-ipt u-ipt-nm"  placeholder="付款金额" id="payedMoney" value="${salesOrder.payedMoney}" readonly style="TEXT-ALIGN:right"/>
					</li>
					<li>
		            	<label>优惠金额:</label>
		            	<input id="discountfee" width="200px"  class="u-ipt u-ipt-nm" name="discountfee" value="${salesOrder.discountfee}" readonly style="TEXT-ALIGN:right">
	            	</li>
					<li>
		            	<label>仓库名称:</label>
		            	<input id="warehouseName" width="200px"  class="u-ipt u-ipt-nm" name="warehouseName" value="${salesOrder.warehouseName}" readonly>
	            	</li>
		            <li>
		            	<label>卖家备注:</label>
		            	<input id="sellerMemo" width="200px"  class="u-ipt u-ipt-nm" name="sellerMemo" value="${salesOrder.sellerMemo}" readonly>
	            	</li>
	            	<li>
	            		<label>买家留言:</label>
						<input id="buyerMemo" width="200px"  class="u-ipt u-ipt-nm" name="buyerMemo" value="${salesOrder.buyerMemo}" readonly>
					</li>
				</ul>
			</div>
			<h4 class="m-title">原订单明细</h4>
			<div class="m-detail-wrap">
	           <div class="m-grid-wrap f-mb20 f-clearfix">
					<table id="orderItemGridId"></table>
				</div>
			</div>
			<h4 class="m-title">订单明细</h4>
			<div class="m-detail-wrap f-clearfix">
	           <div class="m-grid-wrap f-mb20 f-clearfix">
					<table id="newOrderItemGridId"></table>
				</div>
			</div>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/order/orderSplit.js?v=${config_front}"></script>
</html>