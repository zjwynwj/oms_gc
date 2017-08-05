	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
			<input type="hidden" value="${wave.ispickprint}" id="ispickprint">
			<input type="hidden" value="${wave.isscaned}" id="isscaned">
			<input type="hidden" value="${wave.id}" id="id">
			<div class="m-content">
	        <ul class="m-message-list f-mb10 f-clearfix">
	            <li>
	            	<label>快递单号:</label>
	            	<input type="text" class="u-ipt u-ipt-nm " id="expcode" />
            	</li>
            	<li>
	            	<label>订单号:</label>
	            	<input type="hidden" class="u-ipt u-ipt-nm u-ipt-disabled" id="orderId" disabled=""  />
	            	<input type="text" class="u-ipt u-ipt-nm u-ipt-disabled" id="orderNum" disabled=""  />
            	</li>
            	<li>
	            	<label>重量:</label>
	            	<input type="text" class="u-ipt u-ipt-nm " id="weight"></input>kg
            	</li>
            	<li>
	            	<label>信息:</label>
	            	<textarea class="u-textarea u-textarea-nm" rows="5" id="mark"></textarea>
            	</li>
            </ul>
            </div>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/order/batchWeight.js?v=${config_front}"></script>	
</html>