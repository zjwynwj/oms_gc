	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
			<div class="m-toolbar f-mb15 f-clearfix">
				<input type="hidden" value="${wave.ispickprint}" id="ispickprint">
				<input type="hidden" value="${wave.isscaned}" id="isscaned">
		    	<input type="hidden" value="${wave.id}" id="id">
		        <ul class="search-con">
		            <li>
		            	<label>拣货单号:</label>
		            	<input type="text" class="u-ipt u-ipt-nm u-ipt-disabled" placeholder="输入内容" id="waveNo" disabled=""  value="${wave.waveNo}"/>
	            	</li>
	            	<li><div class="checkbox"><input type="checkbox" id="ispickprintCheckBox" disabled>是否打印</input></div>
	            	<li><div class="checkbox">
	            	   <input type="checkbox" id="isscanedCheckBox" disabled    <#if wave.ISSCANED==1> checked = "checked" </#if> >是否分拣</input>
	            	 </div>
	           
	        		<li>
		        		<a onclick="sorting()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">分拣</a>
	        		</li>
    			</li>
	            </ul>
			</div>
			订单列表
			<div class="m-grid-wrap f-mb20 f-clearfix">
				<table id="wmsWaveOrderGridId"></table>
			</div>
			商品明细
			<div class="m-grid-wrap f-mb20 f-clearfix">
				<table id="wmsWaveDetailGridId"></table>
			</div>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/wmswave/waveDetail.js?v=${config_front}"></script>	
</html>