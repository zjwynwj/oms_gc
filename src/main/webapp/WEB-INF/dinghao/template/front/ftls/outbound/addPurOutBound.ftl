<#include "/template/front/header.ftl">
<body>
<div class="m-detail-con f-clearfix">
		<div class="m-content">
			<div class="m-form-tit f-mb20 f-clearfix">
				<ul class="m-form-grd-lt">
					<li><label>单据编号：</label><span id="receiptCodeShow"></span> </li>
				</ul>
				<ul class="m-form-grd-rt">
					<li><a href="javascript:void(0);" myLable="savePurOrder"  class="u-btn save u-btn-auto u-btn-bg-blue">确认</a></li>
				</ul>
				<h4>采购出库单</h4>
			</div>
			<div class="m-toolbar f-mb15 f-clearfix">
				<input type="hidden" id="receiptCode" name='receiptCode'>
				<input type="hidden" id="purOrderId" name='purOrderIds' value="${purOrderId}">
				<input type="hidden" id="receiptType" value="2">
				<input type="hidden" id="serviceType" value="1">
				<ul class="search-con">
					<li>
					<label>出库仓库：</label> 
						  <select class="u-select u-select-nm" name="warehouseId" id="warehouseId">
							<option value="">请选择</option> 
							<#list warehouses as warehouse>
							     <#if receipt.warehouseId==warehouse.id>
							       <option value="${warehouse.id}"  selected="selected"  >${warehouse.warehouseName}</option>
							         <#else>
							       <option value="${warehouse.id}">${warehouse.warehouseName}</option>
							    </#if>
							</#list>
					   	  </select>
						
					</li>
					<li>
						<label>供应商：</label> 
						<input id="providerId" type="hidden" autocomplete="off" name="providerId" class="u-ipt u-ipt-nm" value="${custId}" /> 
						<input id="remarks" name="remarks" type="text" class="u-ipt u-ipt-nm u-ipt-disabled" value="${custName}" disabled/>
					</li>
					<li><label>业务类型：</label><span id="receiptCodeShow">采购进货</span></li>
					<li><label>业务单号：</label><input type="text" id="serviceNum" value="${purNo}" class="u-ipt u-ipt-nm u-ipt-disabled" disabled/></li>
					<li><label>经手人：</label><input type="text" id="handledPerson"	class="u-ipt u-ipt-nm" placeholder="输入内容" /></li>
					<li><label>业务日期：</label>
						<div class="date">
							<input type="text" verifyData_top="{type:'empty'}"
								value="<#if receipt.createDate?exists>${receipt.createDate?string("yyyy-MM-dd")}</#if>" class="u-ipt u-ipt-sm verify"
								id="datepicker" name="createDate" disabled/>
						</div>
					</li>
				</ul>
			</div>
			
		</div>
			<div class="m-grid-wrap f-mb20 f-clearfix">
				<table id="blackId"></table>
			</div>
			

</div>
	<script type="text/javascript"
		src="${BASE_PATH}/dinghao/template/front/js/bizjs/outbound/addPurOutBound.js?v=${config_front}">
	</script>
</body>
</html>

