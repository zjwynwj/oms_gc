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
					action="${BASE_PATH_TEMPLATE}/finance_receipt/save_financereceipt.jhtml"
					method="post">
					<input type="hidden" id='id' value="${financeReceipt.id}" name="id">
					<ul class="search-con">
						<li><label>付款方：</label> <input id="providerId" type="hidden"
							autocomplete="off" name="custId" class="u-ipt u-ipt-nm" value="${financeReceipt.custId}" />
							<input id="remarks" name="custName" type="text"
							class="u-ipt u-ipt-nm" value="${financeReceipt.custName}" /></li>
						<li><label>业务日期:</label>
							<div class="date">
								<input id="datepicker" verifyData_top="{type:'empty'}"
									name="busiDate" type="text" class="u-ipt u-ipt-nm verify"
									value="<#if financeReceipt.busiDate?exists>${financeReceipt.busiDate?string("yyyy-MM-dd")}</#if>" />
							</div></li>
						<li><label>经办人：</label><input type="text" name="busiPerson"
							value="${financeReceipt.busiPerson}" class="u-ipt u-ipt-nm" /></li>
						<li><label>我方账号：</label>
							<select name="recAccount" id="recAccount" class="u-select u-select-nm">
								<#list financeAccounts as financeAccount>
									 <option <#if financeReceipt.recAccount==financeAccount.id> selected="selected"</#if> value="${financeAccount.id}">${financeAccount.bankNo}</option>
								</#list>
							</select>
						</li>
						<li><label>金额：</label> <input type="number" value="${financeReceipt.amount}"
							name="amount" class="u-ipt u-ipt-nm" /></li>
						<li><label>结款方式：</label> <select name="finalType"
							class="u-select u-select-nm">
								<option <#if financeReceipt.finalType==1> selected="selected"</#if> value="1">银行转账</option>
								<option <#if financeReceipt.finalType==2> selected="selected"</#if> value="2">现金</option>
						</select></li>
						<li><label>用途：</label> <input type="text" value="${financeReceipt.purpose}"
							name="purpose" class="u-ipt u-ipt-nm" /></li>
						<li><label>备注：</label> <input type="text" value="${financeReceipt.memo}"
							name="memo" class="u-ipt u-ipt-nm" /></li>
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
