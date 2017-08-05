/*******************************************************************************
 * 函数名称: <ATFunc>index.js</ATFunc> 函数功能: <ATFuncDesc></ATFuncDesc> 开发人员: Zihan
 * 2016年2月23日 修改记录:
 ******************************************************************************/
$(function() {
    // 单据设置列表表格
    jQuery("#blackId")
	    .jqGrid(
		    {
			datatype : "json",
			url : base_template
				+ '/salesrtnorder/getSalesRtnOrders.jhtml',
			mtype : 'POST',
			forceFit : true,
			colNames : [ '序号', '操作', '平台', '店铺', '退货单号', '来源订单',
				'入库', '买家昵称', '买家地区', '买家地址', '实退总金额' ],
			colModel : [
				{ name : 'id', index : 'id', hidden : true},
				{ name : 'act',index : 'act', formatter : act, width:100},
				{ name : 'platType', index : 'platType',width:60,
					  formatter : function(cellvalue, options, rowObject) {
					    if (rowObject.platType == 'SG') {
					        return '手工';
					    } else if (rowObject.platType == 'TB') {
					       return '淘宝';
					    } else if (rowObject.platType == 'WSC') {
					        return '微商城';
					   }
				     	return '';
				    }
				},
				{name : 'shopName',  index : 'shopName'},
				{name : 'rtnNo',index : 'rtnNo'},
				{name : 'orderName',index : 'orderName'},
				{name : 'received',index : 'received',width:40,
					formatter : function(cellvalue, options,  rowObject) {
					if (rowObject.received) {
					    return '是';
					} else {
					    return '否';
					}
				    }
				},
				{ name : 'custNick', index : 'custNick'},
				{ name : 'area', index : 'area',
				    formatter : function(cellvalue, options,  rowObject) {
					return rowObject.provId
						+ rowObject.cityId
						+ rowObject.countyId;
				    }
				}, {
				    name : 'address',
				    index : 'address'
				}, {
				    name : 'rtncash',
				    index : 'rtncash'
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
			    page : "pageNum",
			    // 表示请求页码的参数名称
			    rows : "rows",
			    // 表示请求行数的参数名称
			    sort : "page.oid",
			    // 表示用于排序的列名的参数名称
			    order : "page.oor",
			    // 表示采用的排序方式的参数名称
			    search : "issearch",
			    // 表示是否是搜索请求的参数名称
			    nd : "nd",
			    // 表示已经发送请求的次数的参数名称
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
			},
			// 统一超时时间
			successTip : false,
			beforeProcessing : oms.grid.ajaxSuccessFn,
			loadError : oms.grid.ajaxErrorFn
		    });

    // 搜索
    $('#search').click(function() {
		$("#blackId").jqGrid('setGridParam', {
		    postData : {
			platType : $("#platType").val(),
			shopId : $("#shopId").val(),
			received : $("input[name='received']:checked").val(),
			rtnNo : $("#rtnNo").val(),
			orderNum : $("#orderNum").val(),
			custNick : $("#custNick").val()
		    }
		});
		$("#blackId").jqGrid().trigger("reloadGrid");
    });

    function act(cellvalue, options, rowObject) {
		var addBtn = "<div class='operating'><a href='javascript:void(0);'onclick=\"updateButtom('"
			+ rowObject.rtnId
			+ "')\" title='修改' class='operating-pencil'></a>  " +
					" <a href='javascript:void(0);'onclick=\"receivedButtom('"
			+ rowObject.rtnId + "')\" title='收货'  class='operating-plus'></a></div>";
		return addBtn;
    }

    /**
     * 弹出供应商
     */
    $("#custName").on(
	    "click",
	    function() {
		oms.s_addPop("供应商选择", base_template
			+ "/custInfo/get_custinfomgr.jhtml", "", 1000, 500);
	    });
});// jQuery 结束

function childAdd() {
    var url = base_template + "/salesrtnorder/add_sales_rtn_order_item.jhtml";
    artTabs({
	addTab : {
	    items : {
		id : 'add_sales_rtn_order_item',
		title : '新增退货单',
		url : url
	    }
	}
    });
}

function updateButtom(id) {
    var url = base_template
	    + "/salesrtnorder/update_sales_rtn_order_item.jhtml?id=" + id;
    artTabs({
	addTab : {
	    items : {
		id : 'update_sales_rtn_order_item',
		title : '修改退货单',
		url : url
	    }
	}
    });
}
function receivedButtom(id) {
	/*
    var url = base_template + "/salesrtnorder/save_received_sales_rtn_order.jhtml?id=" + id
	    + "&stockId=" + $("#stockId").val();
    artTabs({
	addTab : {
	    items : {
		id : 'save_received_sales_rtn_order',
		title : '退款收货',
		url : url
	    }
	}
    });
    */
    
    var options = {};
    options.params = {"rtnId": id };
    options.url =  base_template + "/salesrtnorder/save_received_sales_rtn_order.jhtml";
    options.successCallback = function(data) {
        	oms.tooltip(data.errMsg , "succeed");
    };
    oms.ajaxCall(options);
}
