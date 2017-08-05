$(function() {
	 $("#remarks").on("click",
			    function() {
			        oms.s_addPop("供应商选择", base + "/template/custInfo/get_custinfomgr.jhtml", "", 1000, 490);
			    });
	 
    $("#datepicker")
	    .datepicker(
		    {
			showOn : "both",
			buttonImage : base
				+ "/dinghao/template/front/js/elem/jqueryui/css/images/calendar.gif",
			buttonImageOnly : true
		    });
    $("#datepicker2")
	    .datepicker(
		    {
			showOn : "both",
			buttonImage : base
				+ "/dinghao/template/front/js/elem/jqueryui/css/images/calendar.gif",
			buttonImageOnly : true
		    });

    // 单据设置列表表格
    jQuery("#receiptIndexId").jqGrid({
	datatype : "json",
	url : base_template + '/receipt/get_receipts.jhtml?receiptType=1',
	mtype : 'POST',
	colNames : [ '序号', '操作', '单据号', '业务类型', '业务日期', '业务单号', '供应商', '仓库' ],
	colModel : [ 
	             {name : 'id',index : 'id',hidden : true	}, 
	             {name : 'act',index : 'act', width : '80px',formatter : act,}, 
	             {name : 'receiptCode',index : 'receiptCode'	},
	             {name : 'serviceType',index : 'serviceType',
	            	 	formatter : function(cellvalue, options, rowObject) {
	            	 			if (cellvalue == '1') {
	            	 					return "采购进货";
								} else if (cellvalue == '2') {
								    return "直接入库";
								} else {
								    return "";
								}
						}
	             	}, 
	             {name : 'createDate', index : 'createDate',
				    formatter : 'date',
				    formatoptions : {
					srcformat : 'u',
					newformat : 'Y-m-d'
				    }
	             },
	             {name : 'serviceNum',index : 'serviceNum'}, 
	             {name : 'company',index : 'company'	},
	             {name : 'warehouseName',index : 'warehouseName'} 
	            ],
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
    // 初始化计算jqgrid宽度
    $("#receiptIndexId").setGridWidth($('.m-detail-con').width() - 20);

    // 搜索
    $('#search').click(function() {
    	
	   $("#receiptIndexId").jqGrid('setGridParam', {
	    postData : {
		warehouseId : $("#warehouseId").val(),
		beginDate : $("input[name='beginDate']").val(),
		endDate : $("input[name='endDate']").val(),
		providerId : $("input[name='providerId']").val(),
		receiptCode : $("input[name='receiptCode']").val(),
		receiptType : 1
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
    arr_html
	    .push("<div class='operating'>" +
	    		"<a href='javascript:' onclick=\"EditButton('" + rowObject.id+ "','" + rowObject.warehouseId
		    + "');\" class='operating-comment' title='详情'></a>" 
			+"<a href='javascript:' class='operating-pand' title='打印' onclick=\"print("+rowObject.id+")\"></a>" 
		    +  "</div>");

    return arr_html.join("");
}

function AddReceipt() {
    // 监测是否询盘中
 
	    var url = base_template + "/receipt/add_update.jhtml?receiptType=1";

	    artTabs({
			addTab : {
			    items : {
				id : 'addReceiptreceiptType1',
				title : '新增入库单',
				url : url
			    }
			}
		});
}

function childAdd(id, warehouseId) {
    // 监测是否询盘中
    $.get(base_template+"/wmstake/check_wmstake.jhtml", {
	stockId : warehouseId
    }, function(data) {
	if(data.success){
	    var url = base_template + "/receipt/add_update.jhtml?receiptType=1";
	    if (id != "") {
		url += "&id=" + id;
	    }
		artTabs({
				addTab : {
				    items : {
					id : 'addReceiptreceiptType1',
					title : '新增入库单',
					url : url
				    }
			}}
		);
	}else{
		
		oms.tooltip(data.msg , "failed");
	 
	}
    });
    
}
function EditButton(id, warehouseId) {
	var url = base_template + "/receipt/add_update.jhtml?receiptType=1";
    if (id != "") {
	url += "&id=" + id;
    }
	artTabs({
			addTab : {
			    items : {
				id : 'getReceiptreceiptType1',
				title : '入库单详情',
				url : url
			    }
		}}
	);
}

function print(rowId){
	var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM')); 
    if((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")){
        return;
    }
    LODOP.PRINT_INIT("初始化入库单打印");
    
    var rowData = $("#receiptIndexId").jqGrid('getRowData',rowId);
    
    //设置拣货单头部
    $("#receiptCode1").html(rowData.receiptCode);
    $("#serviceType").html(rowData.serviceType);
    $("#createDate").html(rowData.createDate);
    $("#warehouseName").html(rowData.warehouseName);
    $("#custName").html(rowData.company);
    $("#serviceNum").html(rowData.serviceNum);
          
    oms.ajaxCall({
		 url :base_template+'/receipt/get_receipt_details.jhtml',
		 successTip:false,
		 isMask:false,
		 async:false, 
		 params: {
			 "receiptType":1,
			 "receiptId":rowData.id
		 },
		 successCallback:function(result){
			 var firstPrintFlag = true;
			 var data = result.data;
			 firstPrintFlag = preData(data , LODOP,firstPrintFlag);
		 }
	});
   
    LODOP.PREVIEW();	
	LODOP.PRINT();//打印项加载完毕，打印
  
}

function preData(data , LODOP ,firstPrintFlag){
	
	 //准备打印项
	 var orderHead=data;
	 var orderDetail = data;
	 var totalNum=0;
	 //清空table
	 $('.table tr:not(:first)').empty();
	
	 for(i=0;i<orderDetail.length;i++){
		 var v=orderDetail[i];
		 var outerId = v.gdsInfoDetail==undefined?'':v.gdsInfoDetail;
		 var outerSkuId =  v.skuOuterId==undefined?'':v.skuOuterId;
		 var gdsName = v.gdsName==undefined?'':v.gdsName;
		 var gdsFormat = v.gdsFormat==undefined?'':v.gdsFormat;
		 var locNo = v.warehousePositionsName==undefined?'':v.warehousePositionsName;
		 
		 var  num = v.amount==undefined?'':v.amount;
		 var s='<tr><td>'+outerId+'</td><td>'+outerSkuId+'</td><td>'+gdsFormat+'</td><td>'+locNo+'</td><td>'+
		num+'</td></tr>';
		 $(".table").append(s);
		
	 } 
	 
	 
	 var left;
	 if(firstPrintFlag == true){
		  left="5%";
	 }else{
		 left=0;
	 }
	 LODOP.ADD_PRINT_HTM(10,left,"100%","100%",$("#iform").html());	     
	 LODOP.SET_PRINT_STYLEA(0,"LinkedItem",-1);
	 return firstPrintFlag = false;
		
}


