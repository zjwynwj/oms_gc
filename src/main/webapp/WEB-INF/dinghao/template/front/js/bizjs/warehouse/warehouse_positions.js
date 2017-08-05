/**
 * 页面 warehouse_positions.ftl 引入js
 */
$(function() {
    // 客户表格
    jQuery("#warehouseIndexId").jqGrid(
	    {
		datatype : "json",
		url : base_template + '/warehouse/get_warehouse_positions.jhtml',
		mtype : 'POST',
		postData : {
		    'dhWarehousePositions' : $(
			    "input[name='dhWarehousePositions']").val()
			    
		},
		colNames : [ '序号', '操作', '库位编号', '备注', '状态' ,'默认库位'],
		colModel : [ 
		   {name : 'id', index : 'id', hidden : true},
		   { name : 'act',index : 'act', formatter : act},
		   { name : 'warehousePositionsCode',index : 'warehousePositionsCode'},
		   {name : 'remarks', index : 'remarks'}, 
		   {  name : 'isDelete', index : 'isDelete',
			   formatter : function(cellvalue, options, rowObject) {
				if (cellvalue == "1") {
				    return "停用";
				} else {
				    return "启用";
				}
		    }
		   },
		   {  name : 'remarks1', index : 'remarks1',
			   formatter : function(cellvalue, options, rowObject) {
				if (cellvalue == "0") {
				    return "";
				} else {
				    return "默认";
				}
		    }
		   }
		   
		  
		  ],
		rowNum : 15,
		rowList : [ 15, 30, 50 ],
		pager : '#pager',
		toolbarfilter : true,
		height : '100%',
		viewrecords : true,
		autowidth : true,
		rownumbers : true,
		prmNames : { // 默认发送参数格式设置
		    page : "pageNum",
		    rows : "rows"
		},
		prmNames : {
		    page : "pageNum", // 表示请求页码的参数名称
		    rows : "rows", // 表示请求行数的参数名称
		    sort : "page.oid", // 表示用于排序的列名的参数名称
		    order : "page.oor", // 表示采用的排序方式的参数名称
		    search : "issearch", // 表示是否是搜索请求的参数名称
		    nd : "nd", // 表示已经发送请求的次数的参数名称
		    state : "isstate"
		},
		jsonReader : { // 返回数据格式设置
		    root : "rows",
		    records : "records",
		    total : "total",
		    repeatitems : false
		},
		ajaxGridOptions : {
		    timeout : oms.ajaxTimeout
		},// 统一超时时间
		successTip : false,
		beforeProcessing : oms.grid.ajaxSuccessFn,
		loadError : oms.grid.ajaxErrorFn,
		multiselect : false
	    });
});
// --------------------------生成操作列内容脚本--------------------------------

// 库位编辑
function act(cellvalue, options, rowObject) {
    var arr_html = [];
    arr_html
	    .push("<div class='operating'> <a title='编辑' class='operating-pencil' style='cursor:pointer' onclick=\"EditButton('"
		    + rowObject.id + "')\"></a>");
    arr_html
	    .push("<a title='删除' style='cursor:pointer'class='operating-trash' onclick=\"DeleteButton('"
		    + rowObject.id + "','2')\"></a></div>");
    return arr_html.join("");
}
// 删除仓位信息
function DeleteButton(gId) {
    // 询问框
    $.artDialog({
	title : '消息',
	content : '你确认要删除吗?',
	cancel : function() {
	    return false;
	},
	ok : function() {
	    $.post(base_template + "/warehouse/delete_warehouse_positions.jhtml", {
		    "id" : gId
		}, function(data) {
		    if(data.result){
			jQuery("#warehouseIndexId").jqGrid().trigger("reloadGrid");
		    }
		    $.artDialog({
			title : '消息',
			content : data.msg
		    });
		}, "json");
	}
    });
}
// 编辑仓位信息
function EditButton(gId) {
    var dhWarehousePositions = $("input[name='dhWarehousePositions']").val();
    saveOrUpdate(gId, dhWarehousePositions);
}
/**
 * 编辑或删除仓位信息
 * 
 * @param id
 * @param dhWarehousePositions
 */
function saveOrUpdate(id, dhWarehousePositions) {
    var url = base_template + '/warehouse/save_update_warehouse_positions.jhtml?1=1';
    if (id != "") {
    	url += "&id=" + id;
    }
    if (dhWarehousePositions != "") {
    	url += "&dhWarehousePositions=" + dhWarehousePositions;
    }
    oms.s_addPop("库位编辑", url, "", 550, 400,{isRefresh:true,isChild:true,isChangeClose:true});
}

function search(){
	var queryData = {};
	queryData.dhWarehousePositions = $("input[name='dhWarehousePositions']").val();
	if($("#isShow").attr("checked") == 'checked'){
		queryData.isDelete = "";
	}
	else
	{
		queryData.isDelete = "0";
	}
	 $("#warehouseIndexId").jqGrid('setGridParam', {
		    postData : queryData
		});
	 $("#warehouseIndexId").jqGrid().trigger("reloadGrid");
	
}
