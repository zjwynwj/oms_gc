/**
 * 页面 warehouse.ftl引入js
 */
$(function() {
	// 客户表格
	jQuery("#warehouseIndexId").jqGrid({
		datatype : "json",
		url : base_template + '/warehouse/getWarehouses.jhtml',
		mtype : 'POST',
		postData : {isDelete:"0"},
		colNames : [ '序号', '操作', '编号', '名称', '地址', '管理员', '状态','默认仓库', '库位' ],
		colModel : [
		            {name : 'id',index : 'id',hidden : true	}, 
		            {name : 'act',index : 'act',width:40	},
		            {name : 'warehouseCode',index : 'warehouseCode'}, 
		            {name : 'warehouseName',index : 'warehouseName'},
		            {name : 'address',index : 'address'},
		            {name : 'managerName',index : 'managerName'},
		            {name : 'isDelete',index : 'isDelete',
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
			 				}
			 				else {
			 				    return "默认";
			 				}
		 			   }
		            },
		            {name : 'operation',index : 'operation'} ],
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
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
		multiselect : false,
		gridComplete : function() {
			var ids = jQuery("#warehouseIndexId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var rowId = ids[i];
				//设置可以编辑
				var addBtn2 = "<div class='operating'><a href='javascript:' onclick=\"EditButton('" + rowId + "');\" class='operating-pencil' title='修改仓库'></a></div>";
				var addBtn1 = "<div class='operating'><a href='javascript:' onclick=\"EditPos('" + rowId + "');\" class='operating-pencil' title='修改库位'></a></div>";
				jQuery("#warehouseIndexId").jqGrid('setRowData', ids[i], {
					act : addBtn2,
					operation:addBtn1
				});
			}
			//初始化计算jqgrid宽度
			$("#warehouseIndexId").setGridWidth($('.wrap-table').width());
		}
	});
});
// --------------------------生成操作列内容脚本--------------------------------
function EditButton(gId) {
	// 编辑库位
	addWarehouse(gId);
	
}
function EditPos(id){
	artTabs({
		addTab: {
				items: {
						id: 'modWarePos',
						title: '库位编辑',
						url:  base_template+"/warehouse/warehouse_positions_index.jhtml?id=" + id
				}
		}
	})
}
function addWarehouse(id){
	var url = base_template+'/warehouse/addOrUpdateWarehouse.jhtml';
	if (id != ""&&typeof(id)!='undefined') {
		url += "?id=" + id;
	}
	oms.s_addPop("仓库",url,"",550,400);
}


function search(){
	var postData = new Array();
	var queryData = {};
	/*
	queryData.keyWord = $("#keyWord").val();
		*/
	if($("#isShow").attr("checked") == 'checked'){
		queryData.isDelete = "";
	}
	else
	{
		queryData.isDelete = "0";
	}

	postData.push(queryData);
    $("#warehouseIndexId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}

// 新增仓库
/*function addWarehouse(id) {
	var url = '/template/warehouse/addOrUpdateWarehouse.jhtml';
	if (id != "") {
		url += "?id=" + id;
	}
	layer.open({
		type : 2,
		title : "仓库",
		area : [ '550px', '400px' ], 
		shadeClose : true,
		shade : 0.5,
		skin : 'layui-layer-rim', // 加上边框
		content : [ url, 'no' ],
	});
}*/