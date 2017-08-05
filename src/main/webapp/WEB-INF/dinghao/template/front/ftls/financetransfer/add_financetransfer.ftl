<#escape x as x!""> <#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-content">
			<div class="m-form-tit f-mb20 f-clearfix">
				<ul class="m-form-grd-lt">
					<li><label>单据编号：</label><span id="receiptCodeShow"></span></li>
				</ul>
				<ul class="m-form-grd-rt">
					<li><a href="javascript:void(0);"  onclick="addFinanceTrans()"
						class="u-btn save u-btn-auto u-btn-bg-blue savePurOrder">保存</a></li>
				</ul>
				<h4>划拨单</h4>
			</div>
			<div class="m-toolbar f-mb15 f-clearfix">
				<form id="verifyCon">
					<input
						type="hidden" id='recNo' value="" name="recNo">
					<ul class="search-con">
						<li><label>业务日期:</label>
							<div class="date">
								<input id="datepicker" verifyData_top="{type:'empty'}"
									name="busiDate" type="text" class="u-ipt u-ipt-nm verify"
									value="${busiDate}" />
							</div></li>
						<li><label>转出账户：</label><select name="payAccount"
							id="payAccount" class="u-select u-select-nm"> <#list
								financeAccounts as financeAccount>
								<option value="${financeAccount.id}">${financeAccount.bankNo}</option>
								</#list>
						</select></li>
						<li><label>转入账户：</label><select name="recAccount"
							id="recAccount" class="u-select u-select-nm"> <#list
								financeAccounts as financeAccount>
								<option value="${financeAccount.id}">${financeAccount.bankNo}</option>
								</#list>
						</select></li>
						<li><label>经办人：</label> <input type="text" value=""
							name="busiPerson" class="u-ipt u-ipt-nm" /></li>
						<li><label>金额：</label> <input type="text" value="1"
							name="amount" id="amount" class="u-ipt u-ipt-nm verify" verifyData="{type:'money',required:'yes'}" /></li>
						<li><label>手续费：</label> <input type="text" value="0"
							name="poundage" id="poundage" class="u-ipt u-ipt-nm verify"   verifyData="{type:'money',required:'no'}"/></li>
						<li><label>结款方式：</label> 
						  <select name="finalType" id="finalType"
							class="u-select u-select-nm">
								<option value="1">银行转账</option>
								<option value="2">现金</option>
						</select></li>
						<li>
						    <input type="radio" checked="checked"
								name="payType" id = "payType" value="1"><span style="line-height:30px;height:30px;">转出账户支付</span> </input>
						  <input
								type="radio" name="payType" value="2"> <span  style="line-height:30px;height:30px;">转出账户支付</span> </input>
						</li>
						<li><label>备注：</label> <input type="text" value=""
							name="memo" id="memo" class="u-ipt u-ipt-nm" /></li>
					</ul>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src=${BASE_PATH}/dinghao/template/front/js/bizjs/financetransfer/add_financetransfer.js?v=${config_front}>
	
    </script>
</body>
</html>
</#escape>
