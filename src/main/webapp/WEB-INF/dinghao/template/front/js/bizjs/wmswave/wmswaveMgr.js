$(function(){
	$("#startTime").datepicker({
		showOn: "both", 
		buttonImage: template_base +"/js/elem/jqueryui/css/images/calendar.gif",
		buttonImageOnly: true 
	});
	
	$("#endTime").datepicker({
		showOn: "both", 
		buttonImage: template_base +"/js/elem/jqueryui/css/images/calendar.gif",
		buttonImageOnly: true 
	});

	$("#endTime").val(oms.formatDate(new Date()));
	//应付日期初始化
	var t=new Date();
	var newDay = new Date(t.getFullYear(),t.getMonth(),(t.getDate()-1));
	var dueDate = oms.formatDate(newDay);
	$("#startTime").val(dueDate);

	//订单表格
	jQuery("#wmsWaveGridId").jqGrid({
		datatype: "json",
		url: base + '/template/wmswave/queryWmsWaveGridList.jhtml',
		mtype: 'POST', 
		postData : {},
		height : 'auto',
		colNames: ['序号','操作', '拣货单号', '打印状态', '分拣状态', '创建时间','备注'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'act',index:'act' ,width:'70px'},
			{name:'waveNo',index:'waveNo'},
			{name:'ispickprint',index:'ispickprint'},
			{name:'isscaned',index:'isscaned'},
			{name:'createDate',index:'createDate', formatter : 'date',formatoptions : {srcformat : 'u',	newformat : 'Y-m-d H:i:s'}},
			{name:'memo',index:'memo'}
        ], 
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : 'wmsWaveGridPanelId',
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
			var ids = jQuery("#wmsWaveGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var cl = ids[i];
				var addBtn = "<div class='operating'>";
				addBtn += "<a href='javascript:' class='operating-comment' title='详情' onclick=\"detail("+cl+")\"></a>" ;
				addBtn += "<a href='javascript:' class='operating-pand' title='打印' onclick=\"print("+cl+")\"></a>";
				addBtn += "<a href='javascript:' class='operating-pencil' title='分拣' onclick=\"sorting("+cl+")\"></a></div>";
				jQuery("#wmsWaveGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		},
		multiselect : true
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("wmsWaveGridId");	
	})
});

function search(){
	var postData = new Array();
	var queryData = {};
	queryData.startTime = "";
	queryData.endTime = "";
	queryData.ispickprint = "";
	queryData.isscaned = "";
	queryData.waveNo = "";
	queryData.startTime = $("#startTime").val(); 
	queryData.endTime = $("#endTime").val(); 
	queryData.ispickprint = $("#ispickprint").val();
	queryData.isscaned = $("#isscaned").val(); 
	queryData.waveNo = $.trim($("#waveNo").val()); 
	
	postData.push(queryData);
    $("#wmsWaveGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}

function detail(rowId){
	var rowData = $("#wmsWaveGridId").jqGrid('getRowData',rowId);
	artTabs({
        addTab: {
            items: {
                id: 'waveDetail',
                title: '拣货单明细 ',
                url: base + '/template/wmswave/turnWmsWaveDetail.jhtml?id='+rowData.id+''
            }
        },
        isRefresh: true
    });    

}

function print(rowId){
	var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM')); 
    if((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")){
        return;
    }
    LODOP.PRINT_INIT("初始化拣货单打印");
    
    var rowData = $("#wmsWaveGridId").jqGrid('getRowData',rowId);
    
    //设置拣货单头部
    $("#waveNo1").html(rowData.waveNo);
    LODOP.ADD_PRINT_HTM(10,"5%","100%","100%",$("#iformhead").html());	     
    LODOP.SET_PRINT_STYLEA(0,"LinkedItem",1);
    
    oms.ajaxCall({
		 url :base_template+'/wmswave/queryWavePrintData.jhtml',
		 successTip:false,
		 isMask:false,
		 async:false, 
		 params: {
			 "waveNo":rowData.waveNo
		 },
		 successCallback:function(result){
			 var firstPrintFlag = true;
			 var data = result.data;
			 for(var i in data){
				 firstPrintFlag = preData(data[i] , LODOP,firstPrintFlag);
			 }
		 }
	});
   
    LODOP.PREVIEW();	
	LODOP.PRINT();//打印项加载完毕，打印
    //修改打印状态
    //var jsonData = ebusiOrder_getdata1();
    //editPrintStatus(jsonData);
}

function preData(data , LODOP ,firstPrintFlag){
	
	 //准备打印项
	 var orderHead=data;
	 //先清空
	 $("#orderNo1").html("");
	 $("#tbId1").html("");
	 $("#buyerNick1").html("");
	 $("#shopName").html("");
	 $("#warehouseName").html("");
	 $("#num").html("");
	 $("#mark1").html("");
	 
	 $("#orderNo1").html(orderHead.orderNum);
	 $("#tbId1").html(orderHead.topTids);
	 $("#buyerNick1").html(orderHead.recvname);
	 $("#shopName").html(orderHead.shopName);
	 $("#warehouseName").html(orderHead.warehouseName);
	 $("#mark1").html(orderHead.mark);
	 var orderDetail = orderHead.itemList;
	 var totalNum=0;
	 //清空table
	 $('.table tr:not(:first)').empty();
	 //$('#iform_t tr:not(:first)').empty();

	 for(i=0;i<orderDetail.length;i++){
		 var v=orderDetail[i];
		 var  outerId = v.gdsNo==undefined?'':v.gdsNo;
		 var outerSkuId =  v.skuOuterId==undefined?'':v.skuOuterId;
		 var gdsName = v.gdsName==undefined?'':v.gdsName;
		 var  gdsFormat = v.gdsFormat==undefined?'':v.gdsFormat;
		 var  locNo = v.locNo==undefined?'':v.locNo;
		 
		 var  num = v.qty==undefined?'':v.qty;
		 var s='<tr><td>'+outerId+'</td><td>'+outerSkuId+'</td><td>'+gdsFormat+'</td><td>'+locNo+'</td><td>'+
		num+'</td></tr>';
		 $(".table").append(s);
		 totalNum+=parseInt(num);
	 } 
	 $("#num").html(totalNum);
	 
	 var left;
	 if(firstPrintFlag == true){
		  left="0%";
	 }else{
		 left=0;
	 }
	 LODOP.ADD_PRINT_HTM(10,left,"100%","100%",$("#iform").html());	     
	 LODOP.SET_PRINT_STYLEA(0,"LinkedItem",-1);
	 return firstPrintFlag = false;
		
}

function sorting(rowId){
	var rowData = $("#wmsWaveGridId").jqGrid('getRowData',rowId);
	if(rowData.isscaned == "1"){
		oms.tooltip("拣货单:"+rowData.waveNo+"已扫描，请勿重复操作!" ,"error");
		return;
	}
	artTabs({
        addTab: {
            items: {
                id: 'wavePick',
                title: '分拣扫描',
                url: base + '/template/wmswave/turnWavePick.jhtml?id='+rowData.id+''
            }
        },
        isRefresh: true
    });  
}