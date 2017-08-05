<#include "/template/front/header.ftl">
	<link rel="stylesheet" type="text/css" href="${BASE_PATH}/dinghao/template/front/js/elem/select2-master/dist/css/select2.min.css?v=${config_front}">
	<script src="${BASE_PATH}/dinghao/template/front/js/elem/select2-master/dist/js/select2.min.js?v=${config_front}"></script>
	
	<style>
	.area-box { position: relative; }
	.area-con { display:none; position: absolute; background:#fff; z-index:1000; left: 0px; width: 288px; border: 1px solid #83a0ff; min-height: 50px; }
	.area-con .operation{ background:#83a0ff; margin-bottom:4px;}
	.area-con .operation ul{ padding:0px; width:100%; zoom:1; overflow:hidden;}
	.area-con .operation li{ float:left; width:25%;}
	.area-con .operation li a{ display:block; height:22px; line-height:21px; text-align:center; color:#fff;}
	.area-con .operation li.on a{ background:#fff; color:#83a0ff;} 
	.province,.city,.area{ display:none;}
	.province .box{ zoom:1; overflow:hidden; border-bottom:1px solid #efefef; padding-bottom:4px; margin-bottom:5px;}
	.province .box.last{ border-bottom:none;}
	.province .box .sort{ float:left; width:38px; height:48px; line-height:48px; color:#83a0ff; text-align:center; cursor:default;}
	.province .box .list{ float:left; width:250px;}
	.province .box .list a,.city .box a,.area .box a{ display:block; float:left; width:40px; height:24px; line-height:24px; text-align:center; white-space:nowrap;}
	.province .box .list a:hover,.city .box a:hover,.area .box a:hover{ color: #83a0ff; background:#f1f1f1;}
	.city .box,.area .box{ zoom:1; overflow:hidden; padding:5px 12px 10px;}
	.city .box a,.area .box a{ width:auto; padding:0 4px; margin-right:8px;}
	</style>
<body>
  <div class="m-detail-con f-clearfix">
      
			 <div class="m-form-tit f-mb20 f-clearfix">
					<ul class="m-form-grd-lt">
					  <li>
					      <label>订单编号:</label>
				            <span id="orderNum"></span>
				       </li>
					</ul>
				    <ul class="m-form-grd-rt">
				      <li>
						<input type="button"  id="add" onclick="addGdsRow()" class="u-btn u-btn-auto u-btn-bg-blue" style = "margin-right:15px;" value="添加商品">
				          <input type="button" style="float:right;" id="save" class="u-btn u-btn-auto u-btn-bg-blue" value="保存">
				      </li>
				    </ul>
				      <h4>销售订单</h4>
		    </div>
				    
			<div class="m-content">
			 <div class="m-toolbar f-mb15 f-clearfix">
			   <h4 class="m-title">订单信息</h4>
				<ul class="search-con f-fl">
				         
				            <li>
								<label>平台:</label>
				            	<select class="u-select u-select-nm" id="platType">
								    <option value="TB">TB</option>
								     <option value="WSC">WSC</option>
								</select>
							</li>
				            <li>
				            	<label>店铺：</label> 
				                 <select class="u-select u-select-nm" id="shopId">
									<option value="">请选择</option> 
									<#list shoplist as shop>
									     <option value="${shop.id}">${shop.name}</option>
									</#list>
							     </select>
							   
				            	<input id="stockId" type="hidden" name="stockId">
			            	</li>
			            	<li>
				            	<label>物流公司：</label>
				            	 <select class="u-select u-select-nm" id="expid">
									<option value="">请选择</option> 
									<#list expresslist as express>
									     <option value="${express.id}">${express.name}</option>
									</#list>
							   </select>
			            	</li>
				            <li>
				              <label>订单金额：</label>
				            <input id="totalFee"  type="type"  disabled=""  class="u-ipt u-ipt-nm u-ipt-disabled"  value="0.00">
				            </li>
							<li>
								<label>付款金额：</label>
								<input type="text" class="u-ipt u-ipt-nm" id="payedMoney" style="TEXT-ALIGN:right"/>
							</li>
							<li>
								<label>标注：</label>
								<select class="u-select u-select-nm" id="mark">
									<option value="">-</option>
								    <option value="暂不发货">暂不发货 </option>
								    <option value="要求退货">要求退货</option>
								    <option value="地址异常">地址异常</option>
								    <option value="团购活动">团购活动</option>
								</select>
							</li>
							<li>
								<label>买家留言：</label>
								<input type="text" class="u-ipt u-ipt-nm" id="buyerMemo"/>
							</li>
				            <li>
				            	<label>卖家备注：</label>
				            	<input id="sellerMemo" width="200px"  class="u-ipt u-ipt-nm" name="sellerMemo">
			            	</li>
				
				            <li>
				            	<label><i class="requeid">*</i>买家昵称：</label>
				            	<input type="text" class="u-ipt u-ipt-nm"   id="custNick"/>
			            	</li>
				            <li>
				            	<label><i  class="requeid">*</i>收货人姓名：</label>
				            	<input type="text" class="u-ipt u-ipt-nm"   id="recvname"/>
			            	</li>
				            <li>
				            	<label><i  class="requeid">*</i>收货地址：</label>
				            	<input type="text"   id="district" autocomplete="off" readonly="true" class="u-ipt u-ipt-nm"/>
							</li>
				            <li>
				            	<label><i  class="requeid">*</i>详细地址:</label>
				            	<input type="text" class="u-ipt u-ipt-nm"   id="address"/>
				            </li>
				            <li>
				            	<label><i  class="requeid">*</i>手机号码：</label>
				            	<input type="text" class="u-ipt u-ipt-nm"  id="recvphone"/>
			            	</li>
							<li>
								<label>电话号码：</label>
								<input type="text" class="u-ipt u-ipt-nm"  id="recvmobile"/>					
							</li>
							<li>
								<label>邮编：</label>
								<input type="text" class="u-ipt u-ipt-nm"  id="zipcode"/>
							</li>
						</ul>
			   </div>
					
					<div class="m-detail-wrap">
			           <div class="m-grid-wrap f-mb20 f-clearfix">
							
							<table id="orderItemGridId"></table>
						</div>
					</div>
			   </div>
 </div>
</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/order/addSalesOrder.js?v=${config_front}"></script>
	<script src="${BASE_PATH}/dinghao/template/front/js/newsArea.js?v=${config_front}"></script>
	<script src="${BASE_PATH}/dinghao/template/front/js/areaSec.js?v=${config_front}"></script>	
</html>