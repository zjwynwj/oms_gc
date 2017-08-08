<#include "/template/front/header.ftl">
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
	    <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-content">
			<div class="m-toolbar f-mb15 f-clearfix">
				<ul class="search-con f-fl">
				   <li style="float:left;">
					   <label style="float:left;width:auto;">仓库</label> 
					   <select class="u-select u-select-nm" name="warehouseId" id="warehouseId">
							<option value="">请选择</option> 
							<#list warehouses as warehouse>
							   <option value="${warehouse.id}">${warehouse.warehouseName}</option>
							</#list>
					   </select>
					</li>
					<li><label>业务日期:</label>
						<div class="date">
							<input type="text" name="beginDate" class="u-ipt u-ipt-sm"
								id="datepicker" />
						</div> <span class="to">至</span>
						<div class="date">
							<input type="text" name="endDate" class="u-ipt u-ipt-sm"
								id="datepicker2" />
						</div></li>
					<li><label>供应商:</label>
					  <input id="providerId" type="hidden" autocomplete="off" name="providerId" class="u-ipt u-ipt-nm" value="" />
					   <input type="text" name="remarks" id= "remarks"
						class="u-ipt u-ipt-nm" placeholder="输入内容" />
				   </li>
					<li><label>入库单号:</label><input type="text" name="receiptCode"
						class="u-ipt u-ipt-nm" placeholder="输入内容" /></li>
					<li><a href="javascript:void(0);" id="search"
						class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>
				</ul>
			</div>
			
			<div class="m-toolbar f-mb0 f-clearfix">
			  <ul class="search-con f-fl">
			     <li>
				  <a onClick="childAdd('');" href="javascript:" id="addReceipt"
						class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">新增入库</i></a>
				</li>
			  </ul>
		   </div>
			<div class="m-toolbar f-mb20 f-clearfix">
				<div class="m-grid-wrap">
					<table id="receiptIndexId"></table>
					<div id="pager"></div>
					<div></div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<form id="iform"  style="display:none">
		    
			<div class="m-detail-con clearfix f-p5" style="padding-bottom:20px;">	
			<table  id ="iform_t" cellpadding="0" cellspacing="0" width="100%" style="font-size:16px;margin-bottom:10px;">
				<tr>
					<td width="80" align="left" style="height:24px;">入库单号：</td>
					<td id="receiptCode1" width="150" align="left" style="height:24px;"></td>
					<td width="80" align="left" style="height:24px;">业务类型：</td>
					<td id="serviceType" width="150" align="left" style="height:24px;"></td>
					<td width="80" align="left" style="height:24px;">业务日期：</td>
					<td id="createDate" width="150" align="left" style="height:24px;"></td>
				</tr>
				<tr>
					<td width="80" align="left" style="height:24px;">仓库名称：</td>
					<td id="warehouseName" width="150" align="left" style="height:24px;"></td>
					<td width="80" align="left" style="height:24px;">供应商：</td>
					<td id="custName" width="150" align="left" style="height:24px;"></td>
					<td width="80" align="left" style="height:24px;">业务单号：</td>
					<td id="serviceNum" width="150" align="left" style="height:24px;"></td>
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
    <script type="text/javascript" src="${BASE_PATH}/dinghao/template/front/js/bizjs/receipt/index.js?v=${config_front}"></script>
</html>

