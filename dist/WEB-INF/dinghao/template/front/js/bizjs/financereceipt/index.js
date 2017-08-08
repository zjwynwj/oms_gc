/*******************************************************************************
 * 函数名称: <ATFunc>index.js</ATFunc> 函数功能: <ATFuncDesc></ATFuncDesc> 开发人员: Zihan
 * 2016年2月23日 修改记录:
 ******************************************************************************/
$(function() {
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
    jQuery("#blackId").jqGrid({
	datatype : "json",
	url : base_template + '/finance_receipt/get_finance_receipts.jhtml',
	mtype : 'POST',
	forceFit : true,
	colNames : [ '序号', '操作', '单据号', '业务日期', '往来单位', '经办人', '金额' ],
	colModel : [ {
	    name : 'id',
	    index : 'id',
	    hidden : true
	}, {
	    name : 'act',
	    index : 'act',
	    formatter : act
	}, {
	    name : 'recNo',
	    index : 'recNo'
	}, {
	    name : 'busiDate',
	    index : 'busiDate',
	    formatter : 'date',
	    formatoptions : {
		srcformat : 'u',
		newformat : 'Y-m-d'
	    }
	}, {
	    name : 'custName',
	    index : 'custName'
	}, {
	    name : 'busiPerson',
	    index : 'busiPerson'
	}, {
	    name : 'amount',
	    index : 'amount'
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
		custId : $("#custName").val() == '' ? '' : $("#custId").val(),
		beginDate : $("input[name='beginDate']").val(),
		endDate : $("input[name='endDate']").val(),
		recNo : $("#recNo").val()
	    }
	});
	$("#blackId").jqGrid().trigger("reloadGrid");
    });

    function act(cellvalue, options, rowObject) {
	/*<a href='javascript:void(0);'onclick=\"updateButtom('"
	+ rowObject.id
	+ "')\" class='operating-pencil' title='修改'/>" */
	var addBtn = "<div class='operating'><a href='javascript:void(0);'onclick=\"viewButtom('"
		+ rowObject.id
		+ "')\" class='operating-comment' title='查看'/></div>";
	return addBtn;
    }

    /**
     * 弹出供应商
     */
    $("#custName").on(
	    "click",
	    function() {
		oms.s_addPop("供应商选择", base_template + "/custInfo/get_custinfomgr.jhtml",
			"", 1000, 500);
	    });
});// jQuery 结束

function childAdd() {
    var url = base_template + "/finance_receipt/add_financereceipt.jhtml";
    artTabs({
	addTab : {
	    items : {
		id : 'add_financereceipt',
		title : '新增收款单',
		url : url
	    }
	}
    });
}

function updateButtom(id) {
    var url = base_template + "/finance_receipt/update_financereceipt.jhtml?id=" + id;
    artTabs({
	addTab : {
	    items : {
		id : 'update_financereceipt',
		title : '修改收款单',
		url : url
	    }
	}
    });
}
function viewButtom(id) {
    var url = base_template + "/finance_receipt/view_financereceipt.jhtml?id=" + id;
    artTabs({
	addTab : {
	    items : {
		id : 'view_financereceipt',
		title : '查看收款单',
		url : url
	    }
	}
    });
}
