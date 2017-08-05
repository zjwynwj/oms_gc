<#escape x as x!""> <#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-content">
			<div class="m-form-tit f-mb20 f-clearfix">
				<ul class="m-form-grd-lt">
					<li><label>单据编号：</label><span id="receiptCodeShow">${financeTransfer.recNo}</span></li>
				</ul>
				<ul class="m-form-grd-rt">
					<li></li>
				</ul>
				<h4>划拨单</h4>
			</div>
			<div class="m-toolbar f-mb15 f-clearfix">
					<ul class="search-con">
						<li><label>业务日期:</label>
							<div class="date">
								${financeTransfer.busiDate?string('yyyy-MM-dd')}
							</div></li>
						<li><label>转出账户：</label> 
							<#list financeAccounts as financeAccount>
								<#if financeAccount.id==financeTransfer.payAccount>${financeAccount.bankNo}</#if>
							</#list>
						</li>
						<li><label>转入账户：</label>
							<#list financeAccounts as financeAccount>
								<#if financeAccount.id==financeTransfer.recAccount>${financeAccount.bankNo}</#if>
							</#list>
						</li>
						<li><label>经办人：</label> ${financeTransfer.busiPerson}</li>
						<li><label>金额：</label> ${financeTransfer.amount}</li>
						<li><label>手续费：</label> ${financeTransfer.poundage}</li>
						<li><label>结款方式：</label> 
							<#if financeTransfer.finalType==1 >
								银行转账 
							<#else>
								现金
							</#if>
						</li>
						<li><label>
							<#if financeTransfer.payType==1 >
								转出账户支付
							<#else>
								转入账户支付
							</#if></label>
						</li>
						<li><label>备注：</label> ${financeTransfer.memo}</li>
					</ul>
			</div>
		</div>
	</div>
</body>
</html>
</#escape>
