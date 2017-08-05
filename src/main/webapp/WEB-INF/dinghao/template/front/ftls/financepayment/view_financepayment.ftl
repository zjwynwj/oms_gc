<#escape x as x!""> <#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-content">
			<div class="m-form-tit f-mb20 f-clearfix">
				<ul class="m-form-grd-lt">
					<li><label>单据编号：</label><span>${financePayment.payNo}</span></li>
				</ul>
				<ul class="m-form-grd-rt">
					<li><a href="javascript:void(0);"
						class="u-btn save u-btn-auto u-btn-bg-blue savePurOrder">保存</a></li>
				</ul>
				<h4>付款单</h4>
			</div>
			<div class="m-toolbar f-mb15 f-clearfix">

				<ul class="search-con">
					<li><label>付款方：</label> ${financePayment.custName}</li>
					<li><label>业务日期:</label>
						<div class="date"><#if
							financePayment.busiDate?exists>${financePayment.busiDate?string("yyyy-MM-dd")}</#if>
						</div></li>
					<li><label>经办人：</label>${financePayment.busiPerson}</li>
					<li><label>我方账号：</label> <#list financeAccounts as
						financeAccount> <#if financePayment.recAccount==financeAccount.id>
						${financeAccount.bankNo}</#if> </#list></li>
					<li><label>金额：</label> ${financePayment.amount}</li>
					<li><label>结款方式：</label> <#if financePayment.finalType==1>
						银行转账</#if> <#if financePayment.finalType==2> 现金"</#if> </select></li>
					<li><label>用途：</label> ${financePayment.purpose}</li>
					<li><label>备注：</label>${financePayment.memo}</li>
				</ul>

			</div>
		</div>
	</div>

</body>
</html>
</#escape>
