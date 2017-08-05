<#escape x as x!""> <#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-content">
			<div class="m-form-tit f-mb20 f-clearfix">
				<ul class="m-form-grd-lt">
					<li><label>单据编号：</label><span>${financeReceipt.recNo}</span></li>
				</ul>
				<ul class="m-form-grd-rt">
					<li><a href="javascript:void(0);" class="u-btn save u-btn-auto u-btn-bg-blue savePurOrder">保存</a></li>
				</ul>
				<h4>收款单</h4>
			</div>
			<div class="m-toolbar f-mb15 f-clearfix">
				<form id="myForm"
					action="${BASE_PATH}/finance_receipt/save_financereceipt.jhtml"
					method="post">
					<input type="hidden" id='id' value="${financeReceipt.id}" name="id">
					<ul class="search-con">
						<li><label>付款方：</label> ${financeReceipt.custName}</li>
						<li><label>业务日期:</label>
							<div class="date">
								<#if financeReceipt.busiDate?exists>${financeReceipt.busiDate?string("yyyy-MM-dd")}</#if>
							</div></li>
						<li><label>经办人：</label>${financeReceipt.busiPerson}</li>
						<li><label>我方账号：</label>
							
								<#list financeAccounts as financeAccount>
									 <#if financeReceipt.recAccount==financeAccount.id>${financeAccount.bankNo} </#if> 
								</#list>
							
						</li>
						<li><label>金额：</label> ${financeReceipt.amount}
							</li>
						<li><label>结款方式：</label> 
								 <#if financeReceipt.finalType==1> 银行转账</#if>
								 <#if financeReceipt.finalType==2> 现金</#if> 
						</select></li>
						<li><label>用途：</label> ${financeReceipt.purpose}</li>
						<li><label>备注：</label> ${financeReceipt.memo}</li>
					</ul>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src=${BASE_PATH}/dinghao/template/front/js/bizjs/financereceipt/update_financereceipt.js?v=${config_front}>
	
    </script>
</body>
</html>
</#escape>
