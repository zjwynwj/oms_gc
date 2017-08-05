	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
			<div class="m-toolbar f-mb15 f-clearfix">
				<input type="hidden" id="orderIds" value="${orderIds}">
				<ul class="search-con f-fr">
		        	<input type="button"  id="saveWmsWave" class="u-btn u-btn-auto u-btn-bg-blue"  onclick="saveWmsWave()" value="保存">
		        </ul>
		        <ul class="search-con">
		            <li>
		            	<label>拣货单号:</label>
		            	<input type="text" class="u-ipt u-ipt-nm u-ipt-disabled" placeholder="输入内容" id="waveNo" disabled=""/>
	            	</li>
		            <li>
		            	<label>创建时间：</label> 
		            	<div class="date"><input type="text" class="u-ipt u-ipt-sm" id="createDate"/></div>
	            	</li>
		            <li><label>业务员:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="createBy"/></li>
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
	<script>
		$(function(){
			$("#createDate").datepicker({
				showOn: "button", 
				buttonImage: template_base +"/js/elem/jqueryui/css/images/calendar.gif",
				buttonImageOnly: true  
			});
		});
	</script>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/wmswave/addWmsWave.js?v=${config_front}"></script>	
</html>