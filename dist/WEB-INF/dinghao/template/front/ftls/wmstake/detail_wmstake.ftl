<#escape x as x!""> <#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-content">
			<div class="m-form-tit f-mb15 f-clearfix">
				<ul class="m-form-grd-rt">
					<li><a href="javascript:void(0);" 
						class="u-btn delete u-btn-auto u-btn-bg-blue" onclick="deleteByTab();">删除</a></li>
					<li><a href="javascript:void(0);"
						class="u-btn  u-btn-auto u-btn-bg-blue"
						myLable="saveAndAddPurOrder">确认</a></li>
				</ul>
			</div>
			<input type="hidden" value="${takeId}" name="takeId" id="takeId">
			<div class="m-grid-wrap f-mb20 f-clearfix">
				<table id="blackId"></table>
				<!-- jqGrid 分页 div gridPager -->
				<div id="pager"></div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="${BASE_PATH}/dinghao/template/front/js/bizjs/wmstake/detail_wmstake.js?v=${config_front}">
	</script>
</body>
</html>
</#escape>
