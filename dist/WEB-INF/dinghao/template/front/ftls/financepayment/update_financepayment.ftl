<#escape x as x!""> <#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-content">
			<div class="m-form-tit f-mb20 f-clearfix">
				<ul class="m-form-grd-lt">
					<li><label>单据编号：</label><span>${financePayment.payNo}</span></li>
				</ul>
				<ul class="m-form-grd-rt">
					<li><a href="javascript:void(0);" class="u-btn save u-btn-auto u-btn-bg-blue savePurOrder">保存</a></li>
				</ul>
				<h4>付款单</h4>
			</div>
			<div class="m-toolbar f-mb15 f-clearfix">
				<form id="myForm"
					action="${BASE_PATH_TEMPLATE}/finance_payment/save_financepayment.jhtml"
					method="post">
					<input type="hidden" id='id' value="${financePayment.id}" name="id">
					<ul class="search-con">
						<li><label>付款方：</label> <input id="providerId" type="hidden"
							autocomplete="off" name="custId" class="u-ipt u-ipt-nm" value="${financePayment.custId}" />
							<input id="remarks" name="custName" type="text"
							class="u-ipt u-ipt-nm" value="${financePayment.custName}" /></li>
						<li><label>业务日期:</label>
							<div class="date">
								<input id="datepicker" verifyData_top="{type:'empty'}"
									name="busiDate" type="text" class="u-ipt u-ipt-nm verify"
									value="<#if financePayment.busiDate?exists>${financePayment.busiDate?string("yyyy-MM-dd")}</#if>" />
							</div></li>
						<li><label>经办人：</label><input type="text" name="busiPerson"
							value="${financePayment.busiPerson}" class="u-ipt u-ipt-nm" /></li>
						<li><label>我方账号：</label>
							<select name="recAccount" id="recAccount" class="u-select u-select-nm">
								<#list financeAccounts as financeAccount>
									 <option <#if financePayment.recAccount==financeAccount.id> selected="selected"</#if> value="${financeAccount.id}">${financeAccount.bankNo}</option>
								</#list>
							</select>
						</li>
						<li><label>金额：</label> <input type="number" value="${financePayment.amount}"
							name="amount" class="u-ipt u-ipt-nm" /></li>
						<li><label>结款方式：</label> <select name="finalType"
							class="u-select u-select-nm">
								<option <#if financePayment.finalType==1> selected="selected"</#if> value="1">银行转账</option>
								<option <#if financePayment.finalType==2> selected="selected"</#if> value="2">现金</option>
						</select></li>
						<li><label>用途：</label> <input type="text" value="${financePayment.purpose}"
							name="purpose" class="u-ipt u-ipt-nm" /></li>
						<li><label>备注：</label> <input type="text" value="${financePayment.memo}"
							name="memo" class="u-ipt u-ipt-nm" /></li>
					</ul>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src=${BASE_PATH}/dinghao/template/front/js/bizjs/financepayment/update_financepayment.js?v=${config_front}>
	
    </script>
</body>
</html>
</#escape>
