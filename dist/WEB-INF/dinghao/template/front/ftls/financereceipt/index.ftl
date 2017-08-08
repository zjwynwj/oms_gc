 <#escape x as x!""> <#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-toolbar f-mb15 f-clearfix">
			<ul class="search-con f-fl">
				<li><a onClick="childAdd();" href="javascript:"
					id="addReceipt"
					class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">新增收款单</i></a></li>
				<li><label>业务日期:</label>
					<div class="date">
						<input type="text" name="beginDate" class="u-ipt u-ipt-sm"
							id="datepicker" />
					</div> <span class="to">至</span>
					<div class="date">
						<input type="text" name="endDate" class="u-ipt u-ipt-sm"
							id="datepicker2" />
					</div></li>
				<li><label>往来单位</label><input name="remarks" id="custName"
					class="u-ipt u-ipt-nm" type="text"> <input name="providerId"
					id="custId" class="u-ipt u-ipt-nm" type="hidden"></li>
				<li><label>单据号</label><input name="recNo" id="recNo"
					class="u-ipt u-ipt-nm" type="text"></li>
				<li><a href="javascript:void(0);" id="search"
					class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>
			</ul>
		</div>
		<div class="m-toolbar f-mb20 f-clearfix">
			<div class="m-grid-wrap">
				<table id="blackId"></table>
				<div id="pager"></div>
				<div></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="${BASE_PATH}/dinghao/template/front/js/bizjs/financereceipt/index.js?v=${config_front}"></script>
</html>
</#escape>
