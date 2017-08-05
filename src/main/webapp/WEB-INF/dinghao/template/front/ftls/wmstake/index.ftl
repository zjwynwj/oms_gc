 <#escape x as x!""> <#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
	
	 <div class="m-content">
	
		<div class="m-toolbar f-mb15 f-clearfix">
			<ul class="search-con f-fl">
				<li><label> 盘点单号</label><input name="takeNo" id="takeNo"
					class="u-ipt u-ipt-nm" type="text"></li>
				<li><label>盘点仓库</label>
				 <select class="u-select u-select-nm" name="stockId" id="stockId">
						<option value="">请选择</option> <#list warehouses as warehouse>
						<option value="${warehouse.id}">${warehouse.warehouseName}</option>
						</#list>
				</select>
				</li>
				<li><label>创建日期:</label>
					<div class="date">
						<input type="text" name="beginDate" class="u-ipt u-ipt-sm"
							id="datepicker" />
					</div> <span class="to">至</span>
					<div class="date">
						<input type="text" name="endDate" class="u-ipt u-ipt-sm"
							id="datepicker2" />
					</div></li>
				<li><a href="javascript:void(0);" id="search"
					class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>
			</ul>
		</div>
		<div class="m-toolbar f-mb0 f-clearfix">
			<ul class="search-con f-fl">
			
				<li>
				  <a href="javascript:void(0);" onclick="addByTab()"
					class="u-btn save u-btn-auto u-btn-bg-blue f-pl40">新增盘点<i class="ico-add"></i></a>
				</li>
			</ul>
		</div>
		<div class="m-toolbar f-mb20 f-clearfix">
			<div class="m-grid-wrap">
				<table id="blackId"></table>
				<div id="pager"></div>
				<div></div>
			</div>
		</div>
		
	   </div>
	</div>
</body>
<script type="text/javascript"
	src="${BASE_PATH}/dinghao/template/front/js/bizjs/wmstake/index.js?v=${config_front}"></script>
</html>
</#escape>
