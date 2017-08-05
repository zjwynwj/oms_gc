<#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-content">
			<div class="m-form-tit f-mb20 f-clearfix">
				<ul class="m-form-grd-lt">
					<li><label>单据编号：</label><span id="receiptCodeShow">${receipt.receiptCode}</span> </li>
				</ul>
				<ul class="m-form-grd-rt">
				     <#if receipt.receiptCode==null  >
					   <li><a href="javascript:void(0);" id="save" onclick="savePurOrder()" class="u-btn save u-btn-auto u-btn-bg-blue">确认</a></li>
				     </#if>
				</ul>
				<h4>入库单</h4>
			</div>
			<div class="m-toolbar f-mb15 f-clearfix">
					<input type="hidden" id='receiptId' value="${receipt.id}" name="id">
					<input type="hidden" id="receiptCode" name='receiptCode'value="${receipt.receiptCode}">
					<input type="hidden" id="purOrderIds" name='purOrderIds' value="${purOrderIds}">
					<input type="hidden" name="receiptType" value="1" id= "receiptType">
					<ul class="search-con">
						<li>
						 <label>入库仓库：</label> 
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
						<li><label>供应商：</label> 
						<input id="providerId" type="hidden" autocomplete="off" name="providerId" class="u-ipt u-ipt-nm" value="${receipt.providerId}" /> 
							<input id="remarks" name="remarks" type="text" class="u-ipt u-ipt-nm" value="${custInfo.custName}" /></li>
						<li><label>业务类型：</label> <select class="u-select u-select-nm" name="serviceType" id="serviceType">
								<option value="">请选择</option>
								<option<#if	receipt.serviceType??&&receipt.serviceType==1>
									selected="selected"</#if> value="1">采购进货</option>
								<option<#if receipt.serviceType??&&receipt.serviceType==2>
									selected="selected"</#if> value="2">直接入库</option>
						</select></li>
						<li><label>业务单号：</label><input type="text" name="serviceNum" id="serviceNum"
							value="${receipt.serviceNum}" class="u-ipt u-ipt-nm"
							placeholder="" /></li>
						<li><label>经手人：</label>
						<input type="text" value="${receipt.handledPerson}" name="handledPerson" id= "handledPerson"
							class="u-ipt u-ipt-nm" placeholder="输入内容" /></li>
						<li><label>业务日期：</label>
							<div class="date">
								<input type="text" verifyData_top="{type:'empty'}"
									value="<#if receipt.createDate?exists>${receipt.createDate?string("yyyy-MM-dd")}</#if>" class="u-ipt u-ipt-sm verify"
									id="datepicker" name="createDate" />
							</div></li>
					</ul>
			
			</div>
			<div class="m-grid-wrap f-mb20 f-clearfix">
				<table id="blackId"></table>
				<!-- jqGrid 分页 div gridPager -->
				<div id="pager"></div>
			</div>
			
		</div>
	</div>

</body>

<script src="${TEMPLATE_BASE_PATH}/js/elem/select2-master/dist/js/select2.full.js?v=${config_front}"></script>
<script src="${BASE_PATH}/dinghao/template/front/js/bizjs/receipt/update.js?v=${config_front}"></script>
</html>

