 <#escape x as x!""> <#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-toolbar f-mb15 f-clearfix">
			<ul class="search-con f-fl">
				<li>
				<select name="platType" id="platType"
					class="u-select u-select-nm">
						<option value="">全部平台</option> <#list plattypes?keys as key>
						<option value="${key}">${plattypes[key]}</option> </#list>
				</select>
				</li>

				<li> <select name="shopId" id="shopId"
					class="u-select u-select-nm">
						<option value="">全部店铺</option> <#list shops as shop>
						<option value="${shop.id}">${shop.name}</option> </#list>
				</select></li>
			
				<li><input name="rtnNo" id="rtnNo"
					class="u-ipt u-ipt-nm" type="text" placeholder="退货单号"></li>
				<li><input name="orderNum" id="orderNum"
					class="u-ipt u-ipt-nm" type="text" placeholder="来源订单"></li>
				<li><input name="custNick" id="custNick"
					class="u-ipt u-ipt-nm" type="text" placeholder="买家昵称"></li>
						<li><input type="checkbox" value="1" name="received"  >收货</li>
				<li><a href="javascript:void(0);" id="search"
					class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>
			</ul>
			<ul class="search-con f-fl">
				<li><a onClick="childAdd();" href="javascript:" id="addReceipt"
					class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">新增退货单</i></a></li>
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
	src="${BASE_PATH}/dinghao/template/front/js/bizjs/salesrtnorder/index.js?v=${config_front}"></script>
</html>
</#escape>
