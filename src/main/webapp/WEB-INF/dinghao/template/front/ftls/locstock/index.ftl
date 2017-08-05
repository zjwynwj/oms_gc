<#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">

			<div class="m-toolbar f-mb15 f-clearfix">
				<ul class="search-con f-fl">
					<li>
					<label  style="width:auto">仓库:</label> 
					<select class="u-select u-select-nm" name="stockId" id="stockId" >
							<option value="">请选择</option> 
							<#list warehouses as warehouse>
							   <option value="${warehouse.id}">${warehouse.warehouseName}</option>
							</#list>
					</select>
					</li>
					<li><label style="width:auto">商品分类:</label>
						<input type="hidden" id="clsId"name="clsId">
						<input type="text" class="u-ipt u-ipt-nm u-ipt-disabled" readonly="readonly" placeholder="输入内容" id="clsName">
						</li>
					<li><label style="width:auto">商品编码:</label><input type="text" name="gdsNo" id="gdsNo"
						class="u-ipt u-ipt-nm" placeholder="输入内容" /></li>
					<li><label  style="width:auto">规格名称:</label><input type="text" name="gdsFormat" id="gdsFormat"
						class="u-ipt u-ipt-nm" placeholder="输入内容" /></li>
					<li><a href="javascript:void(0);" id="search"
						class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>、
					
			     
				</ul>
				
				<ul class="search-con f-fr">
				  <li><a onclick="synGoodsQty()" alt="同步到微商城" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue ">库存同步</a></li>
		   
				  <li><a onclick="addsuggest()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">生成采购建议<i class="ico-add"></i></a></li>
		   
				</ul>
			</div>
		
			<div class="m-detail-wrap">
				<div class="m-grid-wrap">
					<table id="blackId"></table>
					<div id="pagerid"></div>
				
				</div>
			</div>

	</div>
</body>
<script type="text/javascript"
	src="${BASE_PATH}/dinghao/template/front/js/bizjs/locstock/index.js?v=${config_front}"></script>
</html>

