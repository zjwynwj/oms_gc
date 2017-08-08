$(function() {
    // 单据设置列表表格
    jQuery("#blackId").jqGrid(
	    {
		datatype : "json",
		url : base_template + '/locstock/detail_locstock.jhtml',
		mtype : 'POST',

		colNames : [ '序号', '操作','仓库id','仓库名称', '商品编号', '商品名称','规格代码' ,'规格名称','属性', '现货数量', '库存上线',
			'库存下限','仓库成本' ],
		colModel : [
		            { name : 'gdsId', index : 'gdsId', hidden : true,key:true	},
		             { name : 'act', index : 'act', formatter : act,width:80}, 
		              { name : 'stockId', index : 'stockId',hidden : true	},
		             { name : 'warehouseName', index : 'warehouseName',width:80},
		             { name : 'gdsNo', index : 'gdsNo',width:80},
		             { name : 'gdsName', index : 'gdsName'}, 
		             { name : 'skuOuterId', index : 'skuOuterId',width:80}, 
		             { name : 'gdsFormat', index : 'gdsFormat',width:80}, 
		             { name : 'attbs', index : 'attbs',   formatter : attbs},
		             {   name : 'totalQty', index : 'totalQty',width:60},
		             { name : 'gdsHighAmount', index : 'gdsHighAmount',width:60},
		             { name : 'gdsLowAmount',  index : 'gdsLowAmount',width:60},
		             { name : 'amount',  index : 'amount',width:60}
		             
		             ],
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : 'pagerid',
		toolbarfilter : true,
		height : 'auto',
		viewrecords : true,
		autowidth : true,
		rownumbers : true,
		prmNames : {
		    page : "pageNum", // 表示请求页码的参数名称
		    rows : "rows"
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
		multiselect : true
	    });

    // 浏览器大小发生变化的时候，重新计算jqgrid的宽度
    $(window).resize(function() {
        oms.grid.mdetailconWidth("blackId");
    });
    // 搜索
    $('#search').click(function() {
		$("#blackId").jqGrid('setGridParam', {
		    postData : {
			stockId : $("#stockId").val(),
			clsId : $("#clsId").val(),
			gdsNo : $("#gdsNo").val(),
			gdsFormat : $("#gdsFormat").val()
		    }
		});
		$("#blackId").jqGrid().trigger("reloadGrid");
    });
    /**
     * 商品分类操作
     */
    $("#clsName").click(function() {
		oms.s_addPop("商品分类选择", base + "/template/gdscls/turnGdsclsSelect.jhtml",
			"", 600, 450);
	    });
    
    
});// jQuery 结束
/**
 * ****************************************************************************
*函数名称:   <ATFunc>saveGdsCls</ATFunc>
*函数功能:   <ATFuncDesc>选择分类后回调函数</ATFuncDesc>       
*开发人员:   Zihan 2016年1月27日
*修改记录:
*****************************************************************************
 */
function saveGdsCls(selectClsId, selectClsName) {
    $("#clsId").val(selectClsId);
    $("#clsName").val(selectClsName);
}
// --------------------------生成操作列内容脚本--------------------------------
function act(cellvalue, options, rowObject) {
    addBtn = "<a href='javascript:void(0);'onclick=\"detailButtom('"
	    + rowObject.stockId + "','"+rowObject.gdsId+"')\"  title='详细'>查看分库</a>";
    return addBtn;
}

function attbs(cellvalue, options, rowObject){
    if(rowObject.attbs != null && rowObject.attbs != "" && rowObject.attbs  != "undefined" && typeof(rowObject.attbs) != "undefined"){
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
function detailButtom(stockId,gdsId) {
    var url = base_template + "/locstock/detail_locstock_items.jhtml?stockId=" + stockId+"&gdsId="+gdsId;
    artTabs({
        addTab: {
            items: {
                id: 'detailLocStock',
                title: '现货表详情',
                url: url
            }
        }
    });

}

function addsuggest(){
	if($("#stockId").val() == ""){
		oms.tooltip("请先选择仓库","error");
		return;
	}
	var stockId = $("#stockId").val();
	artTabs({
	 		addTab : {
	 		    items : {
	 			id : 'addSuggest',
	 			title : '生成采购建议单',
	 			url : base + "/template/suggest/purSuggestAdd.jhtml?stockId="+stockId
	
	 		    }
	 		}
	});
}


function synGoodsQty(){
	if($("#stockId").val() == ""){
		oms.tooltip("请先选择仓库","error");
		return;
	}
	 var selectRowId = $('#blackId').jqGrid('getGridParam','selarrrow');
     if(selectRowId == ""){
    	 oms.tooltip("请选择操作的记录!","error");
    	 return;
	 }
     
	var options = {};
	//获取选择的商品id
	
	var gdsIds = selectRowId.join(",");
	
	var param = {};
	param.stockId = $("#stockId").val();
	param.gdsIds = gdsIds;
	
    options.params = param;

    options.isMask = true;
	options.url = base+"/template/locstock/synGoodsQty.jhtml";
	options.successCallback = function(data){
		oms.tooltip(data.errMsg , "succeed");
		//search();
	 };
	 oms.ajaxCall(options);

}