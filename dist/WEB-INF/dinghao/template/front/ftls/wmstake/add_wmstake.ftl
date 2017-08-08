<#escape x as x!""> <#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-content">
			<div class="m-form-tit f-mb15 f-clearfix">
				<ul class="m-form-grd-lt">
					<li><a onClick="childAdd(this);" href="javascript:"
						class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">批量选择</a></li>
					<li><a onClick="childAdd(this);" href="javascript:"
						class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">扫描</a></li>
					<li><input type="text" class="u-ipt u-ipt-lg" id="scan"></li>
				</ul>
				<h4>盘点单</h4>
				<ul class="m-form-grd-rt">
					<li><a href="javascript:void(0);" myLable="savePurOrder"
						class="u-btn save u-btn-auto u-btn-bg-blue">保存</a></li>
					<li><a href="javascript:void(0);"
						class="u-btn save u-btn-auto u-btn-bg-blue"
						myLable="saveAndAddPurOrder">保存并新增</a></li>
				</ul>
			</div>
			<div class="m-toolbar f-mb15 f-clearfix">
				<form action="${BASE_PATH_TEMPLATE}/wmstake/save_wmstake.jhtml" method="post"
					id="myForm">
					<input name="id" value="" id="id" type="hidden">
					<ul class="search-con">
						<li><label>盘点单号：</label> <input id="takeNo"
							readonly="readonly" width="200px" class="u-ipt u-ipt-lg"
							name="takeNo"</li>
						<li><label>盘点单名称：</label> <input id="takeName" width="200px"
							class="u-ipt u-ipt-lg" name="takeName"></li>
						<li><label>仓库：</label> 
				
						    <select class="u-select u-select-nm" name="stockId" id="stockId">
								<option value="">请选择</option>
								 <#list warehouses as warehouse>
								<option value="${warehouse.id}">${warehouse.warehouseName}</option>
							</#list>
						</select>
				
					    </li>
						<li><label>创建日期：</label>
							<div class="date">
								<input type="text" verifyData_top="{type:'empty'}"
									class="u-ipt u-ipt-sm verify" id="datepicker" name="createDate" value="${date}" />
							</div></li>
						<li><label>备注：</label> <input id="memo" style="width: 360px;"
							class="u-ipt u-ipt-lg" name="memo"></li>

					</ul>
				</form>
			</div>
			<div class="m-grid-wrap f-mb20 f-clearfix">
				<table id="blackId"></table>
				<!-- jqGrid 分页 div gridPager -->
				<div id="pager"></div>
			</div>
			
		</div>
	</div>

	<script type="text/javascript"
		src="${BASE_PATH}/dinghao/template/front/js/bizjs/wmstake/add_wmstake.js?v=${config_front}">
	
    </script>
</body>
</html>
</#escape>
