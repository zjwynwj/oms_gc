	<#include "/template/front/header.ftl">
	<link rel="stylesheet" type="text/css" href="${BASE_PATH}/dinghao/template/front/js/elem/select2-master/dist/css/select2.min.css?v=${config_front}">
	<script src="${BASE_PATH}/dinghao/template/front/js/elem/select2-master/dist/js/select2.min.js?v=${config_front}"></script>
	<body>
		<div class="m-detail-con f-clearfix">
		  <div class="m-content">
		    <div class="m-form-tit f-mb20 f-clearfix">
		     <ul class="m-form-grd-lt">
		        <input type="hidden" value="${salesOrder.id}" id="id">
			   <li><label>订单编号:</label><span id="orderNum">${salesOrder.orderNum}</span></li>
			 </ul>
			 <ul class="search-con f-fr">
				   <input type="button"  id="add" onclick="addGdsRow()" class="u-btn u-btn-auto u-btn-bg-blue" value="添加">
		        	<input type="button"  id="save" class="u-btn u-btn-auto u-btn-bg-blue" value="保存">
		     </ul>
		     <h4>销售订单</h4>
		      </div>
		      <div class="m-toolbar f-mb15 f-clearfix">
		       <h4 class="m-title">订单信息</h4>
		       <ul class="search-con f-fl">
		        	   <li><label>交易单号：</label><span id="topTids">${salesOrder.topTids}</span></li>
		            <li>
		            	<label>物流公司：</label>
		            	<select class="u-select u-select-nm" id="expid">
							<option value="">请选择</option> 
							<#list expresslist as express>
							   <#if salesOrder.expid==express.id>
							        <option value="${express.id}"   selected="selected" >${express.name}</option>
							   <#else>
							     <option value="${express.id}">${express.name}</option>
							    </#if>
							</#list>
					   </select>
	            	</li>
		            <li><label>平台:</label><span id="platType">${salesOrder.platType}</span></li>
		            <li>
		            	<label>店铺:</label>
		            	<span id="shopName">${salesOrder.shopName}</span>
		            	<input id="shopId" type="hidden" name="shopId" value="${salesOrder.shopId}">
	            	</li>
		            <li><label>订单金额:</label><span id="totalFee">${salesOrder.totalFee}</span></li>
					<li>
						<label>付款金额:</label>
						<input type="text" class="u-ipt u-ipt-nm"  placeholder="付款金额" id="payedMoney" value="${salesOrder.payedMoney}" readonly style="TEXT-ALIGN:right"/>
					</li>
					<li>
						<label>标注:</label>
						<select class="u-select u-select-nm" id="mark">
							<option value="">-</option>
						    <option value="暂不发货"  <#if salesOrder.mark=="暂不发货" >  selected="selected"</#if> >暂不发货 </option>
						    <option value="要求退货" <#if salesOrder.mark=="要求退货" >  selected="selected"</#if> >要求退货</option>
						    <option value="地址异常" <#if salesOrder.mark=="地址异常" >  selected="selected"</#if>>地址异常</option>
						    <option value="团购活动" <#if salesOrder.mark=="团购活动" >  selected="selected"</#if>>团购活动</option>
						</select>
					</li>
					
		            <li><label>发货仓库:</label>
		             <select class="u-select u-select-nm" name="warehouseId" id="warehouseId">
							<option value="">请选择</option> 
							<#list warehouses as warehouse>
							     <#if salesOrder.stockId==warehouse.id>
							       <option value="${warehouse.id}"  selected="selected"  >${warehouse.warehouseName}</option>
							         <#else>
							       <option value="${warehouse.id}">${warehouse.warehouseName}</option>
							    </#if>
							</#list>
					   </select>
					 </li>
		            <li>
		            	<label>卖家备注:</label>
		            	<input id="sellerMemo" width="200px"  class="u-ipt u-ipt-nm" name="sellerMemo" value="${salesOrder.sellerMemo}">
	            	</li>
				</ul>
			</div>
			<div class="m-toolbar f-mb15 f-clearfix">
		      <h4 class="m-title">买家信息</h4>
		           <ul class="search-con f-fl">
		            <li>
		            	<label>买家昵称:</label>
		            	<input type="text" class="u-ipt u-ipt-nm"   id="custNick" value="${salesOrder.custNick}" readonly/>
	            	</li>
		            <li>
		            	<label>收货人姓名：</label>
		            	<input type="text" class="u-ipt u-ipt-nm"   id="recvname" value="${salesOrder.recvname}" readonly/>
	            	</li>
		            <li>
		            	<label>收货地址:</label>
		            	<input type="text"   id="district" autocomplete="off" readonly class="u-ipt u-ipt-lg" value="${salesOrder.prov}-${salesOrder.city}-${salesOrder.county}"/>
					</li>
		            <li>
		            	<label>详细地址:</label>
		            	<input type="text" class="u-ipt u-ipt-nm"   id="address" value="${salesOrder.address}"/>
		            </li>
		            <li>
		            	<label>手机号码:</label>
		            	<input type="text" class="u-ipt u-ipt-nm"  id="recvphone" value="${salesOrder.recvphone}" readonly/>
	            	</li>
					<li>
						<label>电话号码:</label>
						<input type="text" class="u-ipt u-ipt-nm"  id="recvmobile" value="${salesOrder.recvmobile}" readonly/>					
					</li>
					<li>
						<label>邮编:</label>
						<input type="text" class="u-ipt u-ipt-nm"  id="zipcode" value="${salesOrder.zipcode}" readonly/>
					</li>
					<li><label>买家留言:</label>
					   <input type="text" class="u-ipt u-ipt-nm"  id="buyerMemo" value="${salesOrder.buyerMemo}" readonly/>
					  
					</li>
				</ul>
			</div>
		  </div>
			<div class="m-detail-wrap">
	           <div class="m-grid-wrap f-mb20 f-clearfix">
					
					<table id="orderItemGridId"></table>
				</div>
			</div>
	</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/order/modExceptionOrder.js?v=${config_front}"></script>
	<script src="${BASE_PATH}/dinghao/template/front/js/newsArea.js?v=${config_front}"></script>
	<script src="${BASE_PATH}/dinghao/template/front/js/areaSec.js?v=${config_front}"></script>	
</html>