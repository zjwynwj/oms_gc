<#escape x as x!""> <#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-content">
			<div class="m-toolbar f-mb15 f-clearfix">
				<ul class="search-con f-fl">
					<li><a href="javascript:void(0);" class="u-btn save u-btn-auto u-btn-bg-blue " id="save">保存</a></li>
				</ul>
			</div>
			<div class="m-toolbar f-mb15 f-clearfix">
				
				
					<div class="m-toolbar f-mb15 f-clearfix">

						<ul class="search-con f-fl">
						<input id="rtnId" name="rtnId" type="hidden" value="${salesRtnOrder.rtnId}" />
							<li><label>退货单号：</label> <input id="rtnNo" name="rtnNo"
								type="text" class="u-ipt u-ipt-nm u-ipt-disabled"
								value="${salesRtnOrder.rtnNo}" /></li>
							<li><label>平台:</label> <select name="platType" id="platType"
								class="u-select u-select-nm u-ipt-disabled"> <#list plattypes?keys as
									key>
									<option value="${key}"<#if salesRtnOrder.platType==key
										> selected="selected" </#if> >${plattypes[key]}</option> </#list>
							</select></li>
							<li><label>店铺名称：</label><select name="shopId" id="shopId"
								class="u-select u-select-nm"> <#list shops as shop>
									<option value="${shop.id}"<#if
										salesRtnOrder.shopId==shop.id > selected="selected"
										</#if>>${shop.name}</option> </#list>
							</select></li>
							<li><label>来源订单：</label><input type="hidden" id="orderId"
								value="${salesRtnOrder.orderId}" name="orderId"
								class="u-ipt u-ipt-nm" /> <input type="text" id="orderNum"
								value="${salesRtnOrder.orderNum}" name="orderNum"
								class="u-ipt u-ipt-nm" /></li>
							<li><label>退货金额：</label> <input type="number"
								value="${salesRtnOrder.goodsMoney}" name="goodsMoney"
								id="goodsMoney" readonly="readonly" class="u-ipt u-ipt-nm" /></li>
							<li><label>退货运费：</label> <input type="number"
								value="${salesRtnOrder.carriage}" name="carriage" id="carriage"
								class="u-ipt u-ipt-nm" /></li>
							<li><label>合计金额：</label> <input type="number"
								value="${salesRtnOrder.rtncash}" name="rtncash" id="rtncash"
								class="u-ipt u-ipt-nm" /></li>
							<li><label>退货仓库：</label> <select name="stockId" id="stockId"
								class="u-select u-select-nm"> <#list warehouses as
									warehouse>
									<option value="${warehouse.id}"<#if
										salesRtnOrder.stockId==warehouse.id >selected="selected"</#if>
										>${warehouse.warehouseName}</option> </#list>
							</select></li>
							<li><label>退货原因：</label> <input type="text"
								value="${salesRtnOrder.rtnreason}" name="rtnreason"
								id="rtnreason" class="u-ipt u-ipt-nm" /></li>
							<li><label>是否入库：</label> <input type="checkbox"  id="received"  <#if
								salesRtnOrder.received> checked="checked"</#if> name="received"></li>
						</ul>
					</div>
					<div class="m-toolbar f-mb15 f-clearfix">
				
						<ul class="search-con f-fl">
							<li><label>物流名称：</label>
							     <select class="u-select u-select-nm" id="expid">
									<option value="">请选择</option> 
									<#list expresslist as express>
									     <option <#if
										salesRtnOrder.expid==express.id >selected="selected"</#if>  value="${express.id}">${express.name}</option>
									</#list>
							   </select>
								
								</li>
							<li><label>物流单号:</label> <input type="text" name="expcode"
								id="expcode" class="u-ipt u-ipt-nm"
								value="${salesRtnOrder.expcode}"></li>
							<li><label>物流费用：</label>
							   <input type="text"
								name="deliveryfeeee" id="deliveryfeeee"
								value="${salesRtnOrder.deliveryfeeee}" class="u-ipt u-ipt-nm"></li>
						</ul>
					</div>
					<div class="f-mb15 f-clearfix">
				
						<ul class="search-con f-fl">
							<li><label>买家昵称：</label> <input id="custNick" type="text"
								class="u-ipt u-ipt-nm" name="custNick"
								value="${salesRtnOrder.custNick}"></li>
							<li><label>买家姓名:</label> <input type="text" name="recvName"
								id="recvName" class="u-ipt u-ipt-nm"
								value="${salesRtnOrder.recvName}"></li>
							<li><label>买家手机：</label><input type="text" name="recvMobile"
								id="recvMobile" class="u-ipt u-ipt-nm"
								value="${salesRtnOrder.recvMobile}"></li>
							<li><label>买家电话：</label><input type="text" name="recvPhone"
								id="recvPhone" class="u-ipt u-ipt-nm"
								value="${salesRtnOrder.recvPhone}"></li>
						   <li><label>地区：</label><input type="text" name="area"
								id="area" autocomplete="off" readonly
								value="${salesRtnOrder.provId}-${salesRtnOrder.cityId}-${salesRtnOrder.countyId}"
								class="u-ipt u-ipt-nm"></li>
							<li><label>收货地址：</label><input type="text" name="address"
								id="address" class="u-ipt u-ipt-nm"
								value="${salesRtnOrder.address}" style="width:400px"  ></li>
						

						</ul>
					</div>
	
			</div>
		</div>
		<div class="m-toolbar f-mb20 f-clearfix">
			退货商品
			<div class="m-grid-wrap">
				<table id="blackId"></table>
				<div id="pager"></div>
				<div></div>
			</div>
		</div>
	</div>
	<script src="${BASE_PATH}/dinghao/template/front/js/newsArea.js"></script>
	<script src="${BASE_PATH}/dinghao/template/front/js/areaSec.js"></script>
	<script type="text/javascript"
		src=${BASE_PATH}/dinghao/template/front/js/bizjs/salesrtnorder/update_sales_rtn_order_item.js?v=${config_front}>
	
    </script>
</body>
</html>
</#escape>
