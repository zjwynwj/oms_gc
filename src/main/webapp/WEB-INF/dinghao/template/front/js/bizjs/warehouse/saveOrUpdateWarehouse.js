/**
 * 页面 warehouse/addWarehouse.ftl引入js 
 */
$(function(){
	
	
	$("#cancelForm").on("click",function(){	
		oms.s_Pop_closedChild(); //执行关闭
	});
	/*
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	$('#myWarehouse').ajaxForm({
		dataType : 'json',
		success : function(data) {
			if (data.result) {
				layer.alert(data.msg, function() {
					parent.location.reload();//刷新父页面
					parent.layer.close(index); //执行关闭					
				});
			} else {
				layer.alert(data.errors, {icon: 2});
			}
		}
	});	
	*/
	
	
});




/******************************************************************************
函数名称:   <ATFunc>saveWareFn</ATFunc>
函数功能:   <ATFuncDesc>保存仓库</ATFuncDesc>       
开发人员:   gucong
修改记录:
******************************************************************************/
function saveWareFn(){
	if($.trim($("#warehouseCode").val()) == ""){
		oms.tooltip("仓库编码不能为空!" , "error");
		return;
	}
	var ware = {};
	ware.id = $("#id").val();
	ware.warehouseCode = $("#warehouseCode").val();
	ware.warehouseName = $("#warehouseName").val();
	ware.managerName = $("#managerName").val();
	ware.isDelete = $("input:radio[name='isDelete']:checked").val();
	var remarks1 = $("input[name='remarks1']:checked").val();
	if(typeof(remarks1)=='undefined'){
	    remarks1=0;
	}
	ware.remarks1 =remarks1;
	var options = {};
    options.params = ware;
	options.url = base_template+"/warehouse/saveAddOrUpdateWarehouse.jhtml";
	options.successCallback = function(data){
		oms.tooltip(data.errMsg,"succeed");
	}
	oms.ajaxCall(options);
}

	
