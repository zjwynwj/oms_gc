<#include "/template/front/header.ftl"> <#escape x as x!"">
<body class="m-pop-body">
	<div class="m-pop-detail f-clearfix">
		<div class="m-toolbar f-mb15 f-clearfix">
			<input type="hidden" value="${warehouse.id}" name="id" id="id">
			<ul class="m-message-list single-row f-mb10 f-clearfix">
				<li>
					<label>编号： </label>
					<input type="text" class="u-ipt u-ipt-nm"
						value="${warehouse.warehouseCode}" name="warehouseCode"
						id="warehouseCode">
				</li>
				<li>
					<label>名称：</label>
					<input type="text" class="u-ipt u-ipt-nm"
						value="${warehouse.warehouseName}" name="warehouseName"
						id="warehouseName">
				</li>
				<li>
					<label>管理员： </label>
					<input type="text" class="u-ipt u-ipt-nm"
						value="${warehouse.managerName}" name="managerName"
						id="managerName">
				</li>
				<li>
					<label>状态：</label>
					<div class="input-con">
						<input type="radio"<#if warehouse.isDelete??&&
						warehouse.isDelete >checked="checked"</#if> name="isDelete"
						value="1">停用
					</div>
					<div class="input-con">
						<input type="radio" name="isDelete"<#if
						warehouse.isDelete??&&!warehouse.isDelete >checked="checked"</#if>
						value="0">启用
					</div>
				</li>
				<li>
					<label>地址： </label>
					<input type="text" class="u-ipt u-ipt-nm"
						value="${warehouse.address}" name="address" id="address">
				</li>
				<li>
					<label>默认仓库： </label>
					<div class="input-con">
						<input type="checkbox" value="1"<#if warehouse.remarks1??&&
						warehouse.remarks1=='1' >checked="checked"</#if> name="remarks1"
						id="remarks1" > 是
					</div>
				</li>
			</ul>
		</div>
	</div>
	<div class="m-pop-bot">
		<input class="u-btn u-btn-nm u-btn-bg-blue f-mr15" type="button"
			onclick="saveWareFn()" value="保存"> <input type="button"
			class="u-btn u-btn-nm u-btn-bg-blue" id="cancelForm" value="取消">
	</div>

</body>
<script type="text/javascript"
	src="${BASE_PATH}/dinghao/template/front/js/bizjs/warehouse/saveOrUpdateWarehouse.js?v=${config_front}">
    
</script>
</html>
</#escape>
