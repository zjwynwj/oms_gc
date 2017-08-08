/******************************************************************************
函数名称:   <ATFunc>turnAddPurOrder</ATFunc>
函数功能:   <ATFuncDesc>跳转到添加采购订单页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function turnAddPurOrder(){
	artTabs({
        addTab: {
            items: {
                id: 'addPurOrder',
                title: '添加采购订单',
                url: base + '/template/purOrder/turnAddPurOrder.jhtml'
            }
        }
    })
}



function search(){
	var postData = new Array();
	var queryData = {};
	queryData.keyWord = $("#keyWord").val();
	
	postData.push(queryData);
    $("#purOrderGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}


$(function() {
	//采购表格
	jQuery("#purOrderGridId").jqGrid({
		datatype: "json",
		url: base + '/template/purOrder/findPurOrderForGrid.jhtml',
		mtype: 'POST', 
		postData : {},
		altRows:true,
		height : 'auto',
		colNames: ['序号','','操作', '单据编号', '客户名称','custId', '采购日期', '应付日期','业务员','订单金额','实付金额','入库状态','付款状态','订单状态','备注'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name : 'radio',index : 'radio',sortable:false,align:"center",width:'30',
				formatter:function(cellvalue, options, rowObject){
					return '<input type="radio" name="order" data-id="'+options.rowId+'" />';
				}				
			},
			{name:'act',index:'act' ,width:'40px'},
			{name:'purNo',index:'purNo'},
			{name:'custName',index:'custName'},
			{name:'custId',index:'custId',hidden:true},
			{name:'busiDate',index:'busiDate',formatter:'date',	formatoptions:{srcformat: 'U' , newformat: 'Y/m/d'}},
			{name:'dueDate',index:'dueDate',formatter:'date',formatoptions:{srcformat: 'U' , newformat: 'Y/m/d'}},
			{name:'createBy',index:'createBy'},
			{name:'payMoney',index:'payMoney'},
			{name:'totalPayAmt',index:'totalPayAmt'},
			{name:'purImStatus',index:'purImStatus',formatter:function(cellvalue, options, rowObject){
				var new_format_value=" ";
				if(cellvalue == '1'){
					new_format_value="已入库";
				}
				 return new_format_value  
			 }},
			{name:'purPayStatus',index:'purPayStatus',formatter:function(cellvalue, options, rowObject){
				var new_format_value=" ";
				if(cellvalue == '1'){
					new_format_value="已付款";
				}
				 return new_format_value  
			 }},
			{name:'purOrderStatus',index:'purOrderStatus'},
			{name:'remark',index:'remark'}
        ], 
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : 'purOrderGridPanelId',
		toolbarfilter : true,
		viewrecords : true,
		sortname : 'no',
		sortorder : 'act',
		autowidth : true,
		rownumbers: true,
		prmNames: {   //默认发送参数格式设置
			page:"pageNum",
			rows:"rows"
		},
		jsonReader:{  //返回数据格式设置
			root: "data.list",  
			total: "data.pageCount",  
			records: "data.count",
			repeatitems : false
		},
		ajaxGridOptions:{
			timeout:oms.ajaxTimeout
		},//统一超时时间
		successTip:false,
		beforeProcessing:oms.grid.ajaxSuccessFn,
		loadError:oms.grid.ajaxErrorFn,
		gridComplete : function() {
			var ids = jQuery("#purOrderGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var cl = ids[i];
				var addBtn = "<div class='operating'>";
				addBtn += "<a href='javascript:' class='operating-pencil' title='修改' onclick=\"modPurOrder("+cl+")\"></a></div>";
				jQuery("#purOrderGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		}
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("purOrderGridId");	
	})
});

function modPurOrder(rowId){
	var id = jQuery("#purOrderGridId").getCell(rowId,"id");
	var rowData = $("#purOrderGridId").getRowData(id);
	
	var purNo = rowData.purNo;
    var mtitle  = "修改采购订单";
	if(rowData.purOrderStatus != "1"){
		//oms.tooltip("订单"+purNo+"不为新增状态,不支持修改操作!","error");
		mtitle  = "采购订单详情";
	}

	artTabs({
        addTab: {
            items: {
                id: 'modPurOrder',
                title: mtitle,
                url: base + '/template/purOrder/turnModPurOrder.jhtml?id='+id+''
            }
        },
		isRefresh: true
    })
}

function stockOperate(type){
    var arry=[];
	$.each($("input[name='order']"),function(i,v){
		if($(this).is(":checked")) {			
			arry.push($(this).attr("data-id"));   
		} 
	}) 
	if(arry.length == 0){
		oms.tooltip("请选择操作的记录!","error");
		return;
	}
	
	var rowData = $("#purOrderGridId").jqGrid("getRowData", arry);
	var purNo = rowData.purNo;
	var purOrderId = rowData.id;
	var custName = rowData.custName;
	var custId = rowData.custId;
	if(rowData.purOrderStatus == 0){
		oms.tooltip("订单"+purNo+"已删除,不支持此操作!","error");
		return;
	}
	if(rowData.purOrderStatus == "3"){
		oms.tooltip("订单"+purNo+"已完成,不支持此操作!","error");
		return;
	}
	if(rowData.purImStatus == "3"){
		oms.tooltip("订单"+purNo+"已经入库完毕,不支持此操作!","error");
		return;
	}
	
    if(type == 'ingoods'){
 	    artTabs({
	 		addTab : {
	 		    items : {
	 			id : 'addPurReceipt',
	 			title : '新增采购入库单',
	 			url : base + "/template/receipt/add_pur_receipt.jhtml?purOrderId="+purOrderId+"&custName="+custName+"&custId="+custId+"&purNo="+purNo+""
	 		    }
	 		}
 	    });
    		
    }else if(type == 'outgoods'){
    	 artTabs({
 	 		addTab : {
 	 		    items : {
 	 			id : 'addPurReceipt',
 	 			title : '新增采购出库单',
 	 			url : base + "/template/receipt/add_pur_outbound.jhtml?purOrderId="+purOrderId+"&custName="+custName+"&custId="+custId+"&purNo="+purNo+""
 	 		    }
 	 		}
  	    });
	}
}

