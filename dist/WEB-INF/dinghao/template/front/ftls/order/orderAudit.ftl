	<#include "/template/front/header.ftl">
	<style>
		/*======== 标签切换样式 ========*/
		ul.cutTab{ zoom:1; overflow:hidden;}
		ul.cutTab li{ float:left; width:150px; height:30px; line-height:30px; margin-right:10px; border:1px solid #999; border-top:3px solid #999;border-bottom:0px; text-align:center; background:#f1f1f1; cursor:pointer;}
		ul.cutTab li.readOn{ background:#FFC; color:#00C;border-top:3px solid #66f;border-bottom:0px;}
		.cutTabCon{ display:none; border:1px solid #999; height:200px;top:-1px}
	</style>
	<body>
		<div class="m-detail-con">
			<div class="m-toolbar f-mb15 f-clearfix">
				 <ul class="search-con">
					<li  style = "float:left" >
		            	<label style = "width:auto; ">平台:</label>
		            	<select class="u-select u-select-nm" id="platType" style="width:60px">
						    <option value="">全部</option>
					      <option value="TB">淘宝</option>
					        <option value="WSC">微商城</option>
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
	            	
	            	<li style = "float:left;;width:200px">
		            	<label style = "width:auto; ">地区：</label> 
		            	<input id="areaname" 	class="u-ipt u-ipt-nm" name="areaname">
		            	
	            	</li>
	            	
	            	<li style = "float:left">
		            	<label style = "width:auto; ">订单号：</label> 
		            	<input id="orderNum" width="200px"	class="u-ipt u-ipt-nm" name="orderNum">
		         	</li>
		         	
					<li style = "float:left">
		            	<label style = "width:auto; ">交易单：</label> 
		            	<input id="topTids" width="200px"	class="u-ipt u-ipt-nm" name="topTids">
		         	</li>
					<li style="display:none">
	        			<div class="checkbox"><input type="checkbox"  id="isCombine">可合并订单</input></div>
	    			</li>
	    			<li>
	        			<div class="checkbox"><input type="checkbox" id="isSellerMemo">卖家备注</input></div>
	    			</li>
	    			<li>
	        	 		<a href="javascript:search()" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a>
	        	 	</li>
    			</ul>
			</div>
			<div class="m-toolbar f-mb15 f-clearfix">
				<ul class="search-con f-fl">
		        	<li><a onclick="downOrder()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">订单同步<i class="ico-add"></i></a></li>
		        	<li><a onclick="addSalesOrder()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">订单新增<i class="ico-add"></i></a></li>
		        	<li>
		            	<div class="u-btn-group"><a href="javascript:" id="operateOrder" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray f-pr30">合并订单<i class="ico-sel"></i></a>
		                    <ul class="u-btn-sel-con">
		                        <li><a href="javascript:comOrSplit('combine')">合并订单</a></li>
		                        <li><a href="javascript:comOrSplit('cancelCom')">取消合并</a></li>
		                        <li><a href="javascript:comOrSplit('split')">拆分订单</a></li>
		                    </ul>
		                </div>
		            </li>
		            
		            <li>
		            	<div class="u-btn-group"><a href="javascript:" id="auditOrCancel" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray f-pr30">审核订单<i class="ico-sel"></i></a>
		                    <ul class="u-btn-sel-con">
		                        <li><a href="javascript:auditOrCancel('audit')">审核订单</a></li>
		                        <li><a href="javascript:auditOrCancel('cancelAudit')">取消审核</a></li>
		                    </ul>
		                </div>
		            </li>
		            
		            <li>
		        		<a onclick="modPrintTemplate()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">修改打印模板</a>
	        		</li>
		        </ul>
			</div>
			<div id="tabConId">
				<ul id="cutTab" class="cutTab">
					<li class="readOn">未审订单</li>
					<li>已审订单 </li>
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
							<table id="auditOrderGridId"></table>
							<div id="auditOrderGridPanelId"></div>
				        </div>
					</div>
				</div>
			
			</div>
		</div>
	</body>
	<script src="${BASE_PATH}/dinghao/template/front/js/newsArea.js?v=${config_front}"></script>
	<script src="${BASE_PATH}/dinghao/template/front/js/areaSec.js?v=${config_front}"></script>	
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/order/orderAudit.js?v=${config_front}"></script>
</html>
