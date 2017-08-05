<#escape x as x!""> <#include "/template/front/header.ftl">
<body>
<div class="m-detail-con f-clearfix">
	<div class="m-content">
			<div class="m-form-tit f-mb20 f-clearfix">
				<ul class="m-form-grd-lt">
					<li><label>单据编号：</label><span id="receiptCodeShow"></span></li>
				</ul>
				<ul class="m-form-grd-rt">
					<li><a href="javascript:void(0);"  onclick="addFinancePay()"class="u-btn save u-btn-auto u-btn-bg-blue">保存</a></li>
				</ul>
				<h4>付款单</h4>
			</div>
			<div class="m-toolbar f-mb15 f-clearfix">
			<form id="verifyCon">
					<input type="hidden" id='id' value="" name="id">
					<input	type="hidden" id='payNo' value="" name="payNo">
					<ul class="search-con">
						<li><label>收款款方：</label>
					 <input id="providerId" type="hidden" autocomplete="off" name="custId" class="u-ipt u-ipt-nm" value="" />
					 <input id="remarks" readonly="readonly" name="custName" type="text" class="u-ipt u-ipt-nm verify" value="" verifyData="{type:'allcheck',required:'yes'}" />
					 </li>
						<li><label>业务日期:</label>
							<div class="date">
						<input id="datepicker" verifyData_top="{type:'empty'}"	name="busiDate" type="text" class="u-ipt u-ipt-nm verify"
									value="${busiDate}" verifyData="{required:'yes'}"/>
							</div>
						</li>
						<li><label>经办人：</label><input type="text" name="busiPerson" id="busiPerson"
							value="" class="u-ipt u-ipt-nm" /></li>
						<li><label>我方账号：</label>
						<select name="recAccount" id="recAccount" class="u-select u-select-nm">
							<#list financeAccounts as financeAccount>
								 <option value="${financeAccount.id}">${financeAccount.bankNo}</option>
							</#list>
						</select>
						</li>
						<li><label>金额：</label> 
						   <input type="text" value="" id="amount" name="amount" class="u-ipt u-ipt-nm verify" verifyData="{type:'money',required:'yes'}" /></li>
						<li><label>结款方式：</label> 
						  <select name="finalType" id="finalType"	class="u-select u-select-nm">
								<option value="1">银行转账</option>
								<option value="2">现金</option>
						</select></li>
						<li><label>用途：</label> 
						   <input type="text" value=""	id="purpose" name="purpose" class="u-ipt u-ipt-nm" />
						  </li>
						<li><label>备注：</label> <input type="text" value=""
							name="memo" class="u-ipt u-ipt-nm" /></li>
					</ul>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src=${BASE_PATH}/dinghao/template/front/js/bizjs/financepayment/add_financepayment.js?v=${config_front}>
	
    </script>
</body>
</html>
</#escape>
