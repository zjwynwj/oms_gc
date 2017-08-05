<#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-toolbar f-mb15 f-clearfix">
			<ul class="search-con f-fl">
				<li>
					<label>绑定商品分类：</label><input type="text" class="u-ipt u-ipt-nm" placeholder="商品分类" id="clsName" onclick="selectGdsCls()"><input type="hidden" id="clsId">
				</li>
				<li><a href="javascript:addAttbForTable()" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">添加属性名<i class="ico-add"></i></a></li>
			</ul>
		</div>
		<div class="m-content">
			<h4 class="m-title">属性值设置</h4>
			<table class="m-attr-table">
		    	<thead>
		        	<tr>
		            	<th width="180">属性名</th>
		                <th>属性值</th>
		                <th width="80">操作</th>
		            </tr>
		        </thead>
	   			<tbody id='gdsAttrInfo'></tbody>
			</table>
		</div>
	</div>
</body>
<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/goods/gdsAttr/gdsAttr.js?v=${config_front}"></script>
</html>
