$(function(){
	// 单据设置列表表格
	jQuery("#blackId").jqGrid({
		datatype : "json",
		url : base_template + '/wmstake/detail_wmstake_item.jhtml?takeId='+$("#takeId").val(),
		mtype : 'POST',
		forceFit:true,
		colNames : [ '序号', '操作', '商品编号', '商品名称', '规格', '属性', '盘点前数量', '盘点后数量',
		             '差异','库位','是否锁定','锁定人','锁定时间' ],
		colModel : [ {name : 'id',index : 'id',hidden : true},
		             {name : 'act',index : 'act'},
		             {name : 'gdsNo',index : 'gdsNo'},
		             {name : 'gdsName',index : 'gdsName'},
		             {name : 'gdsFormat',index : 'gdsFormat'},
		             {name : 'attbs',index : 'attbs'},
		             {name : 'sysQty',index : 'sysQty'},
		             {name : 'countQty',index : 'countQty'},
		             {name : 'diffQty',index : 'diffQty'}, 
		             {name : 'warehousePositionsName',index : 'warehousePositionsName'}, 
		             
		             {name : 'isLocked',index : 'isLocked',formatter:function(ellvalue, options,
					rowObject){
		        	 if(rowObject.isLocked==0){
		        	     return '未锁定';
		        	 }else{
		        	     return '已锁定';
		        	 }}},
		        	 {name : 'name',index : 'name'},
		             {name : 'lockedDate',index : 'lockedDate', formatter : 'date',
		            	 formatoptions : {srcformat : 'u',newformat : 'Y-m-d'}
		             }],
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
		gridComplete : function() {
			var ids = jQuery("#blackId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var rowId = ids[i];
				// 设置可以编辑
				var addBtn = "<div class='operating'><a href='javascript:void(0);' onclick='deleteButtom(\"id="+rowId+"\")' class='operating-trash' onclick=\"delBtnFn('" + rowId + "');\" title='删除'></a></div>";
				jQuery("#blackId").jqGrid('setRowData', ids[i],{act : addBtn});
				}
		},
		multiselect : true
	});
});
function deleteButtom(id){	
	$.artDialog({
	    title:'消息',
	    content:'你确认要删除吗?',
	    cancel:function(){
	        return false;
	    },
	    ok:function(){
	    	$.post(base_template+"/wmstake/delete_wmstake_item.jhtml?"+id,
			   function(data){
			    if(data.success){
			    	$.artDialog({
			    	    title:'消息',
			    	    content:'删除成功!'
			    	});
			    	$("#blackId").jqGrid().trigger("reloadGrid");
			    }else{
			    	$.artDialog({
			    	    title:'消息',
			    	    content:data.msg
			    	});
			    }
			   }, "json");
	    }
	});
}
function deleteByTab(){
	var deleteIds=[];
	var rowData = jQuery('#blackId').jqGrid('getGridParam','selarrrow');
if(rowData.length){
    for(var i=0;i<rowData.length;i++){
       deleteIds.push("id="+rowData[i]);
    }
}
if(deleteIds.length==0){
	$.artDialog({
	    title:'消息',
	    content:'请选择要删除的数据!'
	});
}else{
	 deleteButtom(deleteIds.join("&"));
}   
}