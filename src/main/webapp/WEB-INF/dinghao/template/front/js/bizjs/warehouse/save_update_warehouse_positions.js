/**
 * 页面 warehouse/addWarehouse.ftl引入js
 */
$(function() {
    $('#myWarehouse').ajaxForm({
	dataType : 'json',
	success : function(data) {
	    oms.tooltip(data.msg , "succeed");
	    oms.s_Pop_closedChild(true,false); // 执行关闭
	}
    });
    $("#cancelForm").on("click", function() {
	   oms.s_Pop_closedChild(); // 执行关闭
    });
});

/*******************************************************************************
 * 函数名称: <ATFunc>saveWarePosFn</ATFunc>
 * <p>
 * 函数功能: <ATFuncDesc>保存仓库库位</ATFuncDesc>
 * <p>
 * 开发人员: gucong 修改记录:
 ******************************************************************************/
function saveWarePosFn() {
    if ($.trim($("#dhWarehousePositions").val()) == "") {
	oms.tooltip("仓库编码不能为空!", "error");
	return;
    }
    var ware = {};
    ware.id = $("#id").val();
    ware.dhWarehousePositions = $("#dhWarehousePositions").val();
    ware.warehousePositionsCode = $("#warehousePositionsCode").val();
    ware.remarks = $("#remarks").val();
    ware.isDelete = $("input:radio[name='isDelete']:checked").val();
    var remarks1 = $("input[name='remarks1']:checked").val();
    if (typeof (remarks1) == 'undefined') {
	remarks1 = 0;
    }
    ware.remarks1 = remarks1;
    var options = {};
    options.params = ware;
    options.url = base_template
	    + "/warehouse/add_update_warehouse_positions.jhtml";
    options.successCallback = function(data) {
    	oms.tooltip(data.errMsg, "succeed");
    	 oms.s_Pop_closedChild(true,false); // 执行关闭
    }
    oms.ajaxCall(options);
}
