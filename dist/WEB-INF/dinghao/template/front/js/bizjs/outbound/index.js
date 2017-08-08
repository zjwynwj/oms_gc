$(function() {
	
	 $("#remarks").on("click",
			    function() {
			        oms.s_addPop("客户选择", base + "/template/custInfo/get_custinfomgr.jhtml", "", 1000, 490);
			    });
	 
    $("#datepicker")
	    .datepicker(
		    {
			showOn : "button",
			buttonImage : base
				+ "/dinghao/template/front/js/elem/jqueryui/css/images/calendar.gif",
			buttonImageOnly : true
		    });
    $("#datepicker2")
	    .datepicker(
		    {
			showOn : "button",
			buttonImage : base
				+ "/dinghao/template/front/js/elem/jqueryui/css/images/calendar.gif",
			buttonImageOnly : true
		    });

    // 单据设置列表表格
    jQuery("#receiptIndexId").jqGrid({
	datatype : "json",
	url : base_template + '/receipt/get_receipts.jhtml?receiptType=2',
	mtype : 'POST',
	colNames : [ '序号', '操作', '单据号', '业务类型', '业务日期', '业务单号', '客户', '仓库' ],
	colModel : [ {
	    name : 'id',
	    index : 'id',
	    hidden : true
	}, {
	    name : 'act',
	    index : 'act',
	    formatter : act
	}, {
	    name : 'receiptCode',
	    index : 'receiptCode'
	}, {
	    name : 'serviceType',
	    index : 'serviceType',
	    formatter : function(cellvalue, options, rowObject) {
		if (cellvalue == '1') {
		    return "采购退货";
		} else if (cellvalue == '2') {
		    return "直接出库";
		} else {
		    return "";
		}

	    }
	}, {
	    name : 'createDate',
	    index : 'createDate',
	    formatter : 'date',
	    formatoptions : {
		srcformat : 'u',
		newformat : 'Y-m-d'
	    }
	}, {
	    name : 'serviceNum',
	    index : 'serviceNum'
	}, {
	    name : 'company',
	    index : 'company'
	}, {
	    name : 'warehouseName',
	    index : 'warehouseName'
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

    // 搜索
    $('#search').click(function() {
	$("#receiptIndexId").jqGrid('setGridParam', {
	    postData : {
		warehouseId : $("input[name='warehouseId']").val(),
		beginDate : $("input[name='beginDate']").val(),
		endDate : $("input[name='endDate']").val(),
		providerId : $("input[name='providerId']").val(),
		receiptCode : $("input[name='receiptCode']").val(),
		receiptType : 2
	    }
	});
	$("#receiptIndexId").jqGrid().trigger("reloadGrid");
    });
  //grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("receiptIndexId");	
	})
});
// --------------------------生成操作列内容脚本--------------------------------
// 入库单编辑
function act(cellvalue, options, rowObject) {
    var arr_html = [];

    arr_html.push("<div class='operating'><a href='javascript:' onclick=\"EditButton('"
	    + rowObject.id + "');\" class='operating-comment' title='详情'></a></div>");
    return arr_html.join("");
}
function childAdd(id, warehouseId) {
    $.get(base_template + "/wmstake/check_wmstake.jhtml", {
    	stockId : warehouseId
    }, function(data) {
	if (data.success) {
	    var url = base_template + "/receipt/add_update.jhtml?receiptType=2";
	    if (id != "") {
	    	url += "&id=" + id;
	    }
	    artTabs({
		addTab : {
		    items : {
			id : 'addReceiptreceiptType2',
			title : '新增出库单',
			url : url
		    }
		}
	    });
	} else {
		oms.tooltip(data.msg , "failed");
	}
    });
}
function EditButton(id, warehouseId) {
	var url = base_template + "/receipt/add_update.jhtml?receiptType=2";
    if (id != "") {
    	url += "&id=" + id;
    }
	artTabs({
			addTab : {
			    items : {
				id : 'getReceiptreceiptType2',
				title : '出库单详情',
				url : url
			    }
		}}
	);
}