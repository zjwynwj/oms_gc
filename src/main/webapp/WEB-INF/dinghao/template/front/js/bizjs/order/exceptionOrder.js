/*
//店铺选择下拉
$('#shopName').select2({
	placeholder : "请输入店铺",
	minimumInputLength : 1,
	multiple : false,
	width : '200',
	formatResult : function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
		return result.name ;
	},
	formatSelection : function(item) {// 选中后显示在文本框中的值
		$('#shopId').val(item.id);
		return item.name;
	},
	dropdownCssClass : "bigdrop", // apply css that makes the dropdown
	// taller
	escapeMarkup : function(m) {
		return m;
	},
	ajax : {
		url : base+"/template/shop/findForGrid.jhtml",
		delay : 250,
		dataType : 'json',
		data : function(term, page) {
			return {
				name : term,// 汉字处理方式
				pageNum : 1,// 页码
				rows : 10,// 数量
			};
		},
		results : function(data, page) {
			return {
				results : data.data.list
			};
		}
	},
	allowClear : true// 单选
});

$("#shopName").unbind("change");
$("#shopName").bind("change" , function(){
	$("#shopId").val($(this).val());
});
*/
function search(){
	var postData = new Array();
	var queryData = {};
	queryData.shopId = "";
	queryData.platType = "";
	queryData.exceptionStatusArr = "";
	queryData.topTids = "";
	queryData.custNick = "";
	var exceptionStatus = "";
	if($("#noGoods").attr("checked") == "checked"){
		exceptionStatus += ",1";
	}
	if($("#outOfStock").attr("checked") == "checked"){
		exceptionStatus += ",2";
	}
	if($("#noGoods").attr("checked") == "checked" && $("#outOfStock").attr("checked") == "checked"){
		exceptionStatus = ",3";
	}
	if(typeof($("#noGoods").attr("checked")) == "undefined" && typeof($("#outOfStock").attr("checked")) == "undefined"){
		exceptionStatus = ",1,2,3";
	}
	queryData.exceptionStatusArr = exceptionStatus.substring(1,exceptionStatus.length);
	queryData.platType = $("#platType").val();
	queryData.shopId = $("#shopId").val();
	queryData.topTids = $("#topTids").val();
	queryData.custNick = $("#custNick").val();
	
	postData.push(queryData);
    $("#exceptionOrderGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}

$(function() {
	//订单表格
	jQuery("#exceptionOrderGridId").jqGrid({
		datatype: "json",
		url: base + '/template/orderMgr/findExceptionSalesOrderForGrid.jhtml',
		mtype: 'POST', 
		postData : {"exceptionStatusArr" : "1,2,3"},
		height : 'auto',
		colNames: ['序号','操作', '平台', '店铺', '订单编号', '交易单号','缺货','商品不匹配','卖家备注','买家备注','买家昵称','收货地址'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'act',index:'act' ,width:'70px'},
			{name:'platType',index:'platType',width:'70px'},
			{name:'shopName',index:'shopName',width:'200'},
			{name:'orderNum',index:'orderNum',width:'200'},
			{name:'topTids',index:'topTids',width:'200'},
			{name:'exceptionStatus',index:'exceptionStatus', align:'center',width:'50' , formatter:function(cellvalue, options, rowObject){
				var new_format_value=" ";
				if(cellvalue == '2' || cellvalue == '3'){
					new_format_value= "√";
				}
			   return new_format_value  
			}},
			{name:'exceptionStatus',index:'exceptionStatus', align:'center' , formatter:function(cellvalue, options, rowObject){
				var new_format_value=" ";
				if(cellvalue == '1' || cellvalue == '3'){
					new_format_value="√";
				}
			   return new_format_value  
			}},
			{name:'buyerMemo',index:'buyerMemo'},
			{name:'sellerMemo',index:'sellerMemo'},
			{name:'custNick',index:'custNick'},
			{name:'address',index:'address'}
        ], 
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : 'exceptionOrderGridPanelId',
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
			var ids = jQuery("#exceptionOrderGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var cl = ids[i];
				var addBtn = "<div class='operating'>";
				addBtn += "<a href='javascript:' class='operating-pencil' title='修改' onclick=\"modExceptionSalesOrder("+cl+")\"></a>" ;
				
				
				addBtn +="</div>";
				
				
				jQuery("#exceptionOrderGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		},
		multiselect : true
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("exceptionOrderGridId");	
	})
});

function modExceptionSalesOrder(rowId){
	var row = $("#exceptionOrderGridId").getRowData(rowId);
	artTabs({
        addTab: {
            items: {
                id: 'modExceptionOrder',
                title: '修改异常订单信息',
                url: base + '/template/orderMgr/turnModExceptionOrder.jhtml?id='+row.id+''
            }
        },
        isRefresh : true
    })
}

/******************************************************************************
函数名称:   <ATFunc>turnAddGdsInfo</ATFunc>
函数功能:   <ATFuncDesc>跳转到添加商品也买</ATFuncDesc>       
开发人员:   gucong
修改记录:
******************************************************************************/
function turnAddGdsInfo(){
	artTabs({
        addTab: {
            items: {
                id: 'addGdsInfo',
                title: '添加商品信息',
                url: base + '/template/gdsMgr/turnAddGdsInfo.jhtml'
            }
        }
    });
}



function downGoods(){
	 var options = {};
     options.param = {};
     options.isMask = true;
 	 options.url = base+"/template/gdsMgr/downLoadGoods.jhtml";
 	 options.successCallback = function(data){
 		oms.tooltip(data.errMsg , "succeed");
 		search();
 	 };
 	 oms.ajaxCall(options);
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