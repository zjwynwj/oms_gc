 <#escape x as x!""> <#include "/template/front/header.ftl">
<input type="hidden" name="" id="stock_Id" />
<input type="hidden" name="${locStockVo.gdsId}" id="gds_Id" />
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-content">
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
<script type="text/javascript">
var gdsId = "${locStockVo.gdsId}";
var stockId = "${locStockVo.stockId}";
</script>
<script type="text/javascript"
	src="${BASE_PATH}/dinghao/template/front/js/bizjs/locstock/detail_locstock_items.js?v=${config_front}"></script>
</html>
</#escape>
