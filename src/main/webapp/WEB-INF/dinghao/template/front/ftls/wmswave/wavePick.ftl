	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
			<div class="m-toolbar f-mb15 f-clearfix">
				<input type="hidden" value="${wave.ispickprint}" id="ispickprint">
				<input type="hidden" value="${wave.isscaned}" id="isscaned">
				<input type="hidden" value="${wave.id}" id="id">
		        <ul class="search-con f-fl">
		            <li>
		            	<label>拣货单号:</label>
		            	<input type="text" class="u-ipt u-ipt-nm u-ipt-disabled" id="waveNo" disabled=""  value="${wave.waveNo}"/>
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
	            		<label>波次号:</label>
		            	<span id="waveId" style="font-size:28px; color:red;width:100px;"></span>
	            	</li>
	            	<li style="float:right;">
		        		<a onclick="endPick()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">强制结束</a>
	        		</li>
	            	
	            </ul>
	            
			</div>
			
			<div class="m-toolbar f-mb15 f-clearfix" style="height:70px;">
			    <ul class="search-con f-fl">
	            	<li>
		            	<label>提示:</label>
		            	<textarea class="u-textarea u-textarea-nm" style="width:400px;" rows="3" id="mark"></textarea>
	            	</li>
	            </ul>
			</div>
			<div class="m-grid-wrap f-mb20 f-clearfix">
				<table id="wmsWaveDetailGridId"></table>
			</div>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/wmswave/wavePick.js?v=${config_front}"></script>	
</html>