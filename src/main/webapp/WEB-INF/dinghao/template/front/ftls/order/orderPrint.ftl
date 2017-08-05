	<#include "/template/front/header.ftl">
	<style>
		/*======== 标签切换样式 ========*/
		ul.cutTab{ zoom:1; overflow:hidden;}
		ul.cutTab li{ float:left; width:200px; height:30px; line-height:30px; margin-right:20px; border:1px solid #999; text-align:center; background:#f1f1f1; cursor:pointer;}
		ul.cutTab li.readOn{ background:#FFC; color:#00C;}
		.cutTabCon{ display:none; border:1px solid #999; height:200px;}
	</style>
	<body>
		<div class="m-detail-con">
			<div class="m-toolbar f-mb15 f-clearfix">
				 <ul class="search-con">
					<li style="display:none;">
		            	<label style = "float:left;width:auto;">平台:</label>
		            	<select class="u-select u-select-nm" id="purType">
						    <option value="TB">TB</option>
						</select>
					</li>
					<li style = "float:left">
		            	<label style = "width:auto; ">店铺：</label> 
		            	<select class="u-select u-select-nm" id="shopId">
							<option value="">请选择</option> 
							<#list shoplist as shop>
							     <option value="${shop.id}">${shop.name}</option>
							</#list>
					     </select>
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
	            	
	            	<li style = "float:left">
		            	<label style = "width:auto; ">订单号：</label> 
		            	<input id="orderNum" width="200px"	class="u-ipt u-ipt-nm" name="orderNum">
		         	</li>
		         	<li style = "float:left">
		            	<label style = "width:auto; ">交易单：</label> 
		            	<input id="topTids" width="200px"	class="u-ipt u-ipt-nm" name="topTids">
		         	</li>
	    			<li style="float:right;">
	        	 		<a href="javascript:search()" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a>
	        	 	</li>
    			</ul>
			</div>
			<div class="m-toolbar f-mb15 f-clearfix">
				
				<ul class="search-con f-fl">
		        	<li>
		        	    <input type="text" class="u-ipt u-ipt-nm" placeholder="输入物流单号" id="expCode"/>
		        		<a onclick="generateExpcode()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">生成物流单号</a>
	        		</li>
		        	<li>
		        		<a onclick="printTemplate()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">打印物流面单</a>
	        		</li>
	        		<li>
		        		<a onclick="generateWaveNo()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">生成拣货单</a>
	        		</li>
	        		<li>
		        		<a onclick="modPrintTemplate()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">修改打印模板</a>
	        		</li>
	        		<li>
		        		<a onclick="deliverGoods()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">发货</a>
	        		</li>
	        		<li>
		        		<a onclick="rtnGoods()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">退货</a>
	        		</li>
		        </ul>
			</div>
			
			<div id="tabConId">
				<ul id="cutTab" class="cutTab">
					<li class="readOn">全部</li>
					<li>未打印</li>
					<li>已打印</li>
				</ul>
				<div id="cutTabCon0" class="cutTabCon" style="display:block;">
					<div class="m-detail-wrap">
			    		<div class="m-grid-wrap f-mb20 f-clearfix">
							<table id="salesrOrderGridId"></table>
							<div id="salesOrderGridPanelId"></div>
				        </div>
					</div>
				</div>
				<div id="cutTabCon1" class="cutTabCon">
					<div class="m-detail-wrap">
			    		<div class="m-grid-wrap f-mb20 f-clearfix">
							<table id="noPrintOrderGridId"></table>
							<div id="noPrintOrderGridPanelId"></div>
				        </div>
					</div>
				</div>
				<div id="cutTabCon2" class="cutTabCon">
					<div class="m-detail-wrap">
			    		<div class="m-grid-wrap f-mb20 f-clearfix">
							<table id="hasPrintOrderGridId"></table>
							<div id="hasPrintOrderGridPanelId"></div>
				        </div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/order/orderPrint.js?v=${config_front}"></script>
</html>
