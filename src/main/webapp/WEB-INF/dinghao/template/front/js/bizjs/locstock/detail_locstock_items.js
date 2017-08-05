$(function() {
    // 单据设置列表表格
    jQuery("#blackId").jqGrid(
	    {
		datatype : "json",
		url : base_template + "/locstock/detail_locstock_items_js.jhtml?stockId="
			+ stockId + "&gdsId=" + gdsId,
		mtype : 'POST',
		forceFit : true,
		colNames : [ '序号', '商品编号', '商品名称', '规格代码','规格名称','属性', '库位编号', '数量' ],
		colModel : [ 
		     { name : 'id', index : 'id',  hidden : true		},
		     {   name : 'gdsNo',index : 'gdsNo',width:180,}, 
		     {name : 'gdsName', index : 'gdsName'	},
		     {name : 'skuOuterId', index : 'skuOuterId',width:100,	},
		     {name : 'gdsFormat', index : 'gdsFormat',width:100,	},
				    
		     {
				    name : 'attbs',
		    index : 'attbs',
		    formatter : attbs
		}, {
		    name : 'warehousePositionsName',
		    index : 'warehousePositionsName'
		},{
		    name : 'totalQty',
		    index : 'totalQty'
		} ],
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
			root: "data.list",  
			total: "data.pageCount",  
			records: "data.count",
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

});// jQuery 结束
function attbs(cellvalue, options, rowObject){
    if(rowObject.attbs != "" && rowObject.attbs  != "undefined" && typeof(rowObject.attbs) != "undefined"){
	attbs = rowObject.attbs.split(";");
	var gdsAttbs="";
	for(var i in attbs){
		gdsAttbs += ";"+attbs[i].split(":")[2]+":"+attbs[i].split(":")[3];
	}
	return gdsAttbs.substring(1, gdsAttbs.length);
    }else{
	return "";
    }
    
}