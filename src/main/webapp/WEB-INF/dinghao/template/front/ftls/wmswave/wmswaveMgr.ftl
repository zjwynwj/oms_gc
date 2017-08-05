<#include "/template/front/header.ftl">
	<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
	    <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
	</object>
	<body>
		<div class="m-detail-con">
			<div class="m-toolbar f-mb15 f-clearfix">
				 <ul class="search-con">
					<li  style = "float:left" >
		            	<label style = "width:auto; ">查询日期:</label>
		            	<div class="date"><input type="text" class="u-ipt u-ipt-sm" id="startTime"/></div>
		            	<span class="to">至</span>
		            	<div class="date"><input type="text" class="u-ipt u-ipt-sm" id="endTime"/></div>
					</li>
					<li style = "float:left">
		            	<label style = "width:auto; ">打印状态：</label> 
		            	<select class="u-select u-select-nm" id="ispickprint">
						    <option value="">全部</option>
						    <option value="0">未打印</option>
						    <option value="1">已打印</option>
						</select>
	            	</li>
	            	<li style = "float:left">
		            	<label style = "width:auto; ">分拣状态：</label> 
		            	<select class="u-select u-select-nm" id="isscaned">
						    <option value="">全部</option>
						    <option value="0">未拣货</option>
						    <option value="1">已拣货</option>
						</select>
	            	</li>
	            	<li style = "float:left">
		            	<label style = "width:auto; ">拣货单号：</label> 
		            	<input id="waveNo" width="200px"	class="u-ipt u-ipt-nm" name="waveNo">
		            
	            	</li>
	    			<li>
	        	 		<a href="javascript:search()" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a>
	        	 	</li>
    			</ul>
			</div>
				
			<div class="m-detail-wrap">
	    		<div class="m-grid-wrap f-mb20 f-clearfix">
					<table id="wmsWaveGridId"></table>
					<div id="wmsWaveGridPanelId"></div>
		        </div>
			</div>
		</div>
	    <form id="iformhead"  style="display:none">
		 <table  cellpadding="0" cellspacing="0" width="100%" style="font-size:16px;margin-bottom:10px;">
		    <tr>
					<td width="80" align="left" style="height:24px;">拣货单号：</td>
					<td id="waveNo1" width="150" align="left" style="height:24px;"></td>
					<td width="80" align="left" style="height:24px;"></td>
					<td  width="150" align="left" style="height:24px;"></td>
					<td width="80" align="left" style="height:24px;"></td>
					<td  width="150" align="left" style="height:24px;"></td>
				</tr>
		 </table>
	   </form>
		<form id="iform"  style="display:none">
		    
			<div class="m-detail-con clearfix f-p5" style="padding-bottom:20px;">	
			<table  id ="iform_t" cellpadding="0" cellspacing="0" width="100%" style="font-size:16px;margin-bottom:10px;">
				<tr>
					<td width="80" align="left" style="height:24px;">订单编号：</td>
					<td id="orderNo1" width="150" align="left" style="height:24px;"></td>
					<td width="80" align="left" style="height:24px;">交易单号：</td>
					<td id="tbId1" width="150" align="left" style="height:24px;"></td>
					<td width="80" align="left" style="height:24px;">系统备注：</td>
					<td id="mark1" width="150" align="left" style="height:24px;"></td>
				</tr>
				<tr>
					<td width="80" align="left" style="height:24px;">买家账号：</td>
					<td id="buyerNick1" width="150" align="left" style="height:24px;"></td>
					<td width="80" align="left" style="height:24px;">店铺名称：</td>
					<td id="shopName" width="150" align="left" style="height:24px;"></td>
				</tr>
				<tr>
					<td width="80" align="left" style="height:24px;">发货仓库：</td>
					<td id="warehouseName" width="150" align="left" style="height:24px;"></td>
					<td width="80" align="left" style="height:24px;">数量：</td>
					<td id="num" width="150" align="left" style="height:24px;"></td>
				</tr>
			</table>
			<div class="clearfix"></div>
			<div class="m-grid-wrap">
				<style>
				.table{width:720px;}
				.table th,.table td{height:24px;font-size:14px;width:120px;text-align:center;word-break: break-all}
				</style>
				<table cellspacing="0"  cellpadding="0" class="table">
					<tr>
						<th>商品编号</th>
						<th>规格代码</th>
						<th>规格</th>
						<th>库位编码</th>
						<th>数量</th>
					</tr>
				</table>
			</div>	
		</div>	
	</form>
	
	</body>
	<script src="${BASE_PATH}/dinghao/template/front/js/elem/lodop/LodopFuncs.js?v=${config_front}"></script>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/wmswave/wmswaveMgr.js?v=${config_front}"></script>
</html>
