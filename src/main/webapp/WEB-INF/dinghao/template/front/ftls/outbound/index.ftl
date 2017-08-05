 <#escape x as x!""> <#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-content">
			<div class="m-toolbar f-mb15 f-clearfix">
				<ul class="search-con f-fl">
					
					  <li style="float:left;">
					      <label style="float:left;width:auto;"> 仓库</label> 
					      <select class="u-select u-select-nm"
						name="warehouseId">
							<option value="">请选择</option> <#list warehouses as warehouse>
							<option value="${warehouse.id}">${warehouse.warehouseName}</option>
							</#list>
					</select></li>
					<li><label>销售日期:</label>
						<div class="date">
							<input type="text" name="beginDate" class="u-ipt u-ipt-sm"
								id="datepicker" />
						</div> <span class="to">至</span>
						<div class="date">
							<input type="text" name="endDate" class="u-ipt u-ipt-sm"
								id="datepicker2" />
						</div></li>
					<li>
					   <label>客户:</label>
					   <input id="providerId" type="hidden" autocomplete="off" name="providerId" class="u-ipt u-ipt-nm" value="" />
					   <input type="text" name="remarks" id= "remarks"
						class="u-ipt u-ipt-nm" placeholder="输入内容" />
						</li>
					<li><label>出库单号:</label><input type="text" name="receiptCode"
						class="u-ipt u-ipt-nm" placeholder="输入内容" /></li>
					<li><a href="javascript:void(0);" id="search"
						class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>
				</ul>
			</div>
			
			<div class="m-toolbar f-mb0 f-clearfix">
			   <ul class="search-con f-fl">
			
			    	<li><a onClick="childAdd('');" href="javascript:"
						class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">新增出库<i
							class="ico-add"></i></a>
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
</body>
<script type="text/javascript"
	src="${BASE_PATH}/dinghao/template/front/js/bizjs/outbound/index.js?v=${config_front}"></script>
</html>
</#escape>
