	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con">
			<div class="m-toolbar f-mb15 f-clearfix">
				 <ul class="search-con">
					<li style="float:left;">
		            	<label style="float:left;width:auto;">平台:</label>
		            	<select class="u-select u-select-nm" id="purType">
						    <option value="TB">淘宝</option>
						    <option value="WSC">微商城</option>
						</select>
					</li>
					<li style="float:left;">
		            	<label style="float:left;width:auto;">店铺：</label> 
		            	<select class="u-select u-select-nm" id="shopId">
							<option value="">请选择</option> 
							<#list shoplist as shop>
							     <option value="${shop.id}">${shop.name}</option>
							</#list>
					     </select>
	            	</li>
					<li>
	        			<div class="checkbox"><input type="checkbox" onclick="search()" id="outOfStock">缺货</input></div>
	    			</li>
	    			<li>
	        			<div class="checkbox"><input type="checkbox" onclick="search()" id="noGoods">商品不匹配</input></div>
	    			</li>
	    			<li>
	    				<label>交易单号:</label>
	    				<input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="topTids"/>
    				</li>
    				<li>
	    				<label>买家昵称:</label>
	    				<input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="custNick"/>
    				</li>
	    			<li>
	        	 		<a href="javascript:search()" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a>
	        	 	</li>
    			</ul>
			</div>
		    <div class="m-toolbar f-mb15 f-clearfix">
				<ul class="search-con f-fl">
		        	<li><a onclick="AddReceipt()"  class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">补货<i class="ico-add"></i></a></li>
		        	<li><a onclick="turnAddGdsInfo()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">新增商品<i class="ico-add"></i></a></li>
	            	<li><a onclick="downGoods()"      href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">商品同步<i class="ico-add"></i></a></li>
		        </ul>
			</div>
			<div>
				<table id="exceptionOrderGridId"></table>
				<div id="exceptionOrderGridPanelId"></div>
			<div>
			
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/order/exceptionOrder.js?v=${config_front}"></script>
</html>
