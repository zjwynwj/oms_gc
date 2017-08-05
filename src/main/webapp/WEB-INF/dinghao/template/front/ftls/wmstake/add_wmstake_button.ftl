 <#escape x as x!""> <#include "/template/front/header.ftl">
 <script type="text/javascript" src="${TEMPLATE_BASE_PATH}/js/ajaxfileupload.js"></script>
<body>
	<div class="m-detail-con f-clearfix">
		<input type="hidden" name="id"
			id="takeId" value="${wmsTake.id}">
		<div class="m-toolbar f-mb15 f-clearfix">
			<ul class="search-con f-fl">
				<li>
				<img src="loading.gif" id="loading" style="display: none;">
				<input type="file" id="file" name="file" />
				<a onClick="importExcel('${wmsTake.id}');" href="javascript:"
					class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">导入明细</a></li>
				<li><a onclick="exportExcel('${wmsTake.id}')" href="javascript:void(0);"
					class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">导出明细</a></li>
			</ul>
			<ul class="search-con f-fr">
				<#if !wmsTake.status>
				 <li><a onClick="startWms(this);" href="javascript:"
					class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">开始盘点</a></li>
				</#if>
				<li><a onClick="saveButton(this);" href="javascript:"
					class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">保存盘点</a></li>
				<li><a onClick="endWms(this);" href="javascript:"
					class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue">结束盘点</a></li>
			</ul>
		</div>
		<div class="m-content">
			<div class="m-pandian">
				<h4 class="m-title u-bd-none">扫描区</h4>
				<ul class="m-message-list f-mb10 f-clearfix">
					<li><label>商品条码:</label><input type="text"
						class="u-ipt u-ipt-lg"  id = "scan" placeholder="输入内容" /></li>
					<li><label>商品名称:</label><input type="text"
						class="u-ipt u-ipt-lg" placeholder="输入内容" /></li>
					
					<li><label>扫描提示:</label> <textarea
						id="sptips"	class="u-textarea u-textarea-nm" rows="3"></textarea></li>
				</ul>
			</div>
			<div class="m-pandian u-bd-none f-pl20">
				<h4 class="m-title u-bd-none">盘点单信息</h4>
				<ul class="m-message-list f-mb10 f-clearfix">
					<li><label>盘点单号:</label><input type="text"
						class="u-ipt u-ipt-lg" value="${wmsTake.takeNo}"
						readonly="readonly" placeholder="输入内容" /></li>
					<li><label>仓库名称:</label><input type="text"
						class="u-ipt u-ipt-lg" value="${wmsTake.warehouseName}"
						readonly="readonly" placeholder="输入内容" /></li>
					<li><label>开始时间:</label>
					<div class="date">
							<input id="datepicker" value="${(wmsTake.startTime?string('yyyy-MM-dd'))!''}" type="text" class="u-ipt u-ipt-lg"
								placeholder="输入内容" />
						</div></li>
					<li><label>结束时间:</label>
					<div class="date">
							<input id="datepicker2" type="text" value="${(wmsTake.endTime?string('yyyy-MM-dd'))!''}" class="u-ipt u-ipt-lg"
								placeholder="输入内容" />
						</div></li>
				</ul>
			</div>
			<div class="f-clearfix"></div>
			<div class="m-grid-wrap f-pt20 f-clearfix">
				<table id="blackId"></table>
				<!-- jqGrid 分页 div gridPager -->
				<div id="pager"></div>
			</div>
		</div>
	</div>
</body>
</html>

<script type="text/javascript"
	src="${BASE_PATH}/dinghao/template/front/js/bizjs/wmstake/detail_wmstake_button.js?v=${config_front}">
    
</script>
</#escape>
