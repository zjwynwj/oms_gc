<#escape x as x!""> <#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-toolbar f-mb15 f-clearfix">
			<ul class="search-con f-fr">
				<li>
					<div class="checkbox">
						<input type="checkbox" onclick="search()" id="isShowBlockGds">不显示停用商品</input>
					</div>
				</li>
				<li><input type="text" class="u-ipt u-ipt-nm"
					placeholder="编码/规格/名称" id="keyWord" /></li>
				<li>
				<li><a href="javascript:search()"
					class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>
				</li>
			</ul>
		</div>
		<div class="m-detail-wrap">
			<div class="m-detail-lt">
				<div id="gdsClsTree" class="ztree f-pl20 f-pt10"></div>
			</div>
			<div class="m-detail-rt">
				<div class="m-grid-wrap f-mb20 f-clearfix">
					<table id="gdsInfoGridId"></table>
					<div id="gdsInfoGridPanelId"></div>
				</div>
			</div>
		</div>
		<div class="m-form-btn">
			<input type="button" id="closeGetCustInfoMgr"
				class="u-btn u-btn-lg u-btn-bg-blue" value="取消"> <input
				type="button" id="returnGetCustInfoMgr"
				class="u-btn u-btn-lg u-btn-bg-blue" value="确认">
		</div>
	</div>
</body>
<!-- zTree -->
<link rel="stylesheet"
	href="${BASE_PATH}/dinghao/template/front/js/elem/zTree/css/zTreeStyle/zTreeStyle.css?v=${config_front}"
	type="text/css">
<script type="text/javascript"
	src="${BASE_PATH}/dinghao/template/front/js/elem/zTree/js/jquery.ztree.core-3.5.js?v=${config_front}"></script>
<script type="text/javascript"
	src="${BASE_PATH}/dinghao/template/front/js/elem/zTree/js/jquery.ztree.exedit-3.5.js?v=${config_front}"></script>
<script
	src="${BASE_PATH}/dinghao/template/front/js/bizjs/goods/gdsMgr/getGdsMgr.js?v=${config_front}"></script>
</html>
</#escape>
