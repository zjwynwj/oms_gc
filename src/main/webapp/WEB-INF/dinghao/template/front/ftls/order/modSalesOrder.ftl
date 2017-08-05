<#include "/template/front/header.ftl">
	<link rel="stylesheet" type="text/css" href="${BASE_PATH}/dinghao/template/front/js/elem/select2-master/dist/css/select2.min.css?v=${config_front}">
	<script src="${BASE_PATH}/dinghao/template/front/js/elem/select2-master/dist/js/select2.min.js?v=${config_front}"></script>
<body>
<div class="m-detail-con f-clearfix">

		 <div class="m-form-tit f-mb20 f-clearfix">
		     <ul class="m-form-grd-lt">
		     <input type="hidden" value="${salesOrder.id}" id="id">
			   <li><label>订单编号:</label><span id="orderNum">${salesOrder.orderNum}</span></li>
			</ul>
			
			<ul class="search-con f-fr">
					<input type="button"  id="save" class="u-btn u-btn-auto u-btn-bg-blue" value="保存">
		    </ul>
		    <h4>销售订单</h4>
		 </div>
		    <div class="m-content">
		      <h4 class="m-title">订单信息</h4>
		        <div class="m-toolbar f-mb15 f-clearfix">
		          <ul class="search-con f-fl">
		             <li><label>交易单号：</label>
		              <input id="topTids" width="200px"  class="u-ipt u-ipt-nm u-ipt-disabled" name="topTids" value="${salesOrder.topTids}">
		            </li>
		            <li>
		            	<label>物流公司：</label>
		            	 <select class="u-select u-select-nm" id="expid">
									<option value="">请选择</option> 
									<#list expresslist as express>
									     <option value="${express.id}"  <#if
										salesOrder.expid==express.id > selected="selected"
										</#if>  >${express.name}</option>
									</#list>
							   </select>
	            	</li>
		            <li><label>平台:</label>
		                 <input  readonly="readonly" id="platType" width="200px"  class="u-ipt u-ipt-nm u-ipt-disabled" name="platType" value="${salesOrder.platType}">
		       
		             </li>
		            <li>
		            	<label>店铺:</label>
		            	<span id="shopName">${salesOrder.shopName}</span>
		            	<input id="shopId" type="hidden" name="shopId" value="${salesOrder.shopId}">
	            	</li>
		            <li><label>订单金额:</label>
		                		<input type="text" class="u-ipt u-ipt-nm"   id="totalFee" value="${salesOrder.totalFee}" readonly style="TEXT-ALIGN:right"/>
				  
		              </li>
					<li>
						<label>付款金额:</label>
						<input type="text" class="u-ipt u-ipt-nm"  placeholder="付款金额" id="payedMoney" value="${salesOrder.payedMoney}" readonly style="TEXT-ALIGN:right"/>
					</li>
					<li>
						<label>标注:</label>
						<input type="hidden" class="u-ipt u-ipt-nm"  placeholder="标注" id="markVal" value="${salesOrder.mark}"/>
						<select class="u-select u-select-nm" id="mark">
							<option value="">-</option>
						    <option value="暂不发货">暂不发货 </option>
						    <option value="要求退货">要求退货</option>
						     <option value="换货订单">换货订单</option>
						    <option value="地址异常">地址异常</option>
						    <option value="团购活动">团购活动</option>
						</select>
					</li>
					<li><label>发货仓库：</label> <select name="stockId" id="stockId"
								class="u-select u-select-nm"> <#list warehouses as
									warehouse>
									<option value="${warehouse.id}" 
									 <#if
										salesOrder.stockId==warehouse.id > selected="selected"
										</#if> 
										>${warehouse.warehouseName}</option>
									</#list>
					</select></li>
					<li><label>买家留言:</label>
					 
					 <input id="buyerMemo" width="200px"  class="u-ipt u-ipt-nm" name="buyerMemo" value="${salesOrder.buyerMemo}" disabled>
					 </li>
		            <li>
		            	<label>卖家备注:</label>
		            	<input id="sellerMemo" width="200px"  class="u-ipt u-ipt-nm" name="sellerMemo" value="${salesOrder.sellerMemo}">
	            	</li>
				</ul>
				</div>
			</div>
				<div class="m-content">
		      <h4 class="m-title">买家信息</h4>
		          <div class="m-toolbar f-mb15 f-clearfix">
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
		            	<label>固定号码:</label>
		            	<input type="text" class="u-ipt u-ipt-nm"  id="recvphone" value="${salesOrder.recvphone}" />
	            	</li>
	            	 <li>
		            	<label>身份证:</label>
		            	<input type="text" class="u-ipt u-ipt-nm"  id="identityCard" value="${salesOrder.identityCard}"/>
	            	</li>
					<li>
						<label>手机号码:</label>
						<input type="text" class="u-ipt u-ipt-nm"  id="recvmobile" value="${salesOrder.recvmobile}" />					
					</li>
					<li>
						<label>邮编:</label>
						<input type="text" class="u-ipt u-ipt-nm"  id="zipcode" value="${salesOrder.zipcode}" />
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
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/order/modSalesOrder.js?v=${config_front}"></script>
	<script src="${BASE_PATH}/dinghao/template/front/js/newsArea.js?v=${config_front}"></script>
	<script src="${BASE_PATH}/dinghao/template/front/js/areaSec.js?v=${config_front}"></script>	
</html>