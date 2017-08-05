<#escape x as x!""> <#include "/template/front/header.ftl">
<body class="m-pop-body">
	<div class="m-pop-detail f-clearfix">
		<form
			action="${BASE_PATH}/warehouse/add_update_warehouse_positions.jhtml"
			method="post" id="myWarehouse">
			<input type="hidden" value="${dhWarehousePositions}"
				name="dhWarehousePositions" id="dhWarehousePositions"> <input
				type="hidden" value="${warehousePositions.id}" name="id" id="id">
			<ul class="m-message-list  single-row f-mb10 f-clearfix">
				<li>
					<label> 库位编号 </label>
					<input type="text" class="u-ipt u-ipt-nm"
						id="warehousePositionsCode"
						value="${warehousePositions.warehousePositionsCode}"
						name="warehousePositionsCode">
				</li>
				<li>
					<label>状态</label>
					<div class="input-con">
						<input type="radio"<#if warehousePositions.isDelete??&&
						warehousePositions.isDelete ==1 >checked="checked"</#if>
						name="isDelete" value="1">停用 <input type="radio" name="isDelete"<#if
						warehousePositions.isDelete??&&warehousePositions.isDelete ==0
						>checked="checked"</#if> value="0">启用
					</div>
				</li>
				<li>
					<label>备注 </label>
					<textarea class="u-textarea u-textarea-lg" id="remarks"
						name="remarks" rows="3">${warehousePositions.remarks}</textarea>
				</li>
				<li>
					<label>默认库位： </label>
					<div class="input-con">
						<input type="checkbox" value="1"<#if
						warehousePositions.remarks1??&& warehousePositions.remarks1=='1'
						>checked="checked"</#if> name="remarks1" id="remarks1" > 是
					</div>
				</li>
			</ul>
		</form>
	</div>

	<div class="m-pop-bot">

		<input type="button" onclick="saveWarePosFn()" value="保存"
			class="u-btn u-btn-nm u-btn-bg-blue f-mr15"> <input
			type="button" id="cancelForm"
			class="u-btn u-btn-nm u-btn-bg-blue f-mr15" value="取消">
	</div>

</body>
<script type="text/javascript"
	src="${BASE_PATH}/dinghao/template/front/js/bizjs/warehouse/save_update_warehouse_positions.js?v=${config_front}">
    
</script>
</#escape>
