<#include "/template/front/header.ftl">
<body>
<div class="m-detail-con f-clearfix">
		<div class="m-content">
			<div class="m-toolbar f-mb15 f-clearfix">
				<ul class="search-con f-fl">
					<li><a href="javascript:void(0);" id="save"
						class="u-btn save u-btn-auto u-btn-bg-blue savePurOrder">保存</a></li>
				</ul>
			</div>
			<div class="m-toolbar f-mb15 f-clearfix">
				
					<div class="m-toolbar f-mb15 f-clearfix">
						
						<ul class="search-con f-fl">
							<li><label>退货单号：</label> <input id="rtnNo" name="rtnNo"
								type="text" class="u-ipt u-ipt-nm" readonly="readonly" value="" /></li>
							<li><label>平台:</label> 
								<select name="platType" id="platType" class="u-select u-select-nm" <#if	salesOrderVo.id??> disabled </#if> >
								 <#list plattypes?keys as key>
									<option value="${key}" <#if	salesOrderVo.platType == key > selected </#if>  >${plattypes[key]}</option>
                                 </#list>
								</select>
							</li>
							<li><label>店铺名称：</label><select name="shopId" id="shopId"
								class="u-select u-select-nm"> <#list shops as shop>
									<option value="${shop.id}" <#if	salesOrderVo.shopId == shop.id > selected </#if> >${shop.name}</option> </#list>
							</select></li>
							<li><label>来源订单：</label>
							     <input type="hidden" id="orderId" value="${salesOrderVo.id}" name="orderId" class="u-ipt u-ipt-nm" />
							     <input type="text" id="orderNum" value="${salesOrderVo.orderNum}" name="orderNum" class="u-ipt u-ipt-nm" />
							</li>
							<li><label>退货金额：</label> <input type="text" value=""
								name="goodsMoney" id="goodsMoney" readonly="readonly"
								class="u-ipt u-ipt-nm" /></li>
							<li><label>退货运费：</label> <input type="number" value=""
								name="carriage" id="carriage" class="u-ipt u-ipt-nm" /></li>
							<li><label>合计金额：</label> <input type="text" value=""
								name="rtncash" id="rtncash" readonly="readonly"
								class="u-ipt u-ipt-nm" /></li>
							<li><label>退货仓库：</label> <select name="stockId" id="stockId"
								class="u-select u-select-nm"> <#list warehouses as
									warehouse>
									<option value="${warehouse.id}">${warehouse.warehouseName}</option>
									</#list>
							</select></li>
							<li><label>退货原因：</label> <input type="text" value=""
								name="rtnreason" id="rtnreason" class="u-ipt u-ipt-nm" /></li>
							<li><label>是否入库：</label> <input type="checkbox"
								name="received"></li>
						</ul>
					</div>
					<div class="m-toolbar f-mb15 f-clearfix">
					
						<ul class="search-con f-fl">
							<li><label>物流名称：</label> 
							 <select class="u-select u-select-nm" id="expid">
									<option value="">请选择</option> 
									<#list expresslist as express>
									     <option value="${express.id}">${express.name}</option>
									</#list>
							   </select>
							<li><label>物流单号:</label> <input type="text" name="expcode"
								id="expcode" class="u-ipt u-ipt-nm"></li>
							<li><label>物流费用：</label><input type="text"
								name="deliveryfeeee" id="deliveryfeeee" class="u-ipt u-ipt-nm"></li>
						</ul>
					</div>
					<div class="f-mb15 f-clearfix">
					
						<ul class="search-con f-fl">
							<li>
							   <label>买家昵称：</label> 
							   <input id="custNick" type="text" class="u-ipt u-ipt-nm" name="custNick" value = "${salesOrderVo.custNick}"  >
							</li>
							<li><label>买家姓名:</label> <input type="text" name="recvName" value = "${salesOrderVo.recvname}"
								id="recvName" class="u-ipt u-ipt-nm"></li>
							<li><label>买家手机：</label><input type="text" name="recvMobile"
								id="recvMobile" class="u-ipt u-ipt-nm" value = "${salesOrderVo.recvmobile}"></li>
							<li><label>买家电话：</label><input type="text" name="recvPhone"
								id="recvPhone" class="u-ipt u-ipt-nm" value = "${salesOrderVo.recvphone}"></li>
							<li><label>地区：</label><input type="text" name="area" readonly
								id="area" autocomplete="off" class="u-ipt u-ipt-nm"  value = "${salesOrderVo.recvArea}"></li>
							<li><label>收货地址：</label><input type="text" name="area" 
								id="address"   class="u-ipt u-ipt-lg" style="width:400px" value = "${salesOrderVo.address}"></li>
						</ul>
					</div>
			
			</div>
		</div>
		
			<div class="m-grid-wrap f-mb20 f-clearfix">
				<table id="blackId"></table>
				<div id="pager"></div>
				
			</div>

</div>
	<script src="${BASE_PATH}/dinghao/template/front/js/newsArea.js"></script>
	<script src="${BASE_PATH}/dinghao/template/front/js/areaSec.js"></script>
	<script type="text/javascript"
		src=${BASE_PATH}/dinghao/template/front/js/bizjs/salesrtnorder/add_sales_rtn_order_item.js?v=${config_front}>
	
    </script>
</body>
</html>

