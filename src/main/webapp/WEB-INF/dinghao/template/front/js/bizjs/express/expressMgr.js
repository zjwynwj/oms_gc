function downExpressInfo(){
	var options = {};
    options.params = {};
    options.isMask = true;
	options.url = base_template+"/expressMgr/downExpressInfo.jhtml";
	options.successCallback = function(data){
		oms.tooltip(data.errMsg , "succeed");
		search();
	};
	oms.ajaxCall(options);
}

$(function() {
	//订单表格
	jQuery("#expressGridId").jqGrid({
		datatype: "json",
		url: base_template + '/expressMgr/findExpressInfoGrid.jhtml',
		mtype: 'POST', 
		postData : {},
		height : 'auto',
		colNames: ['序号','操作', '编号', '物流公司', '联系人', '联系号码','启用'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'act',index:'act' ,width:'80px'},
			{name:'code',index:'code'},
			{name:'name',index:'name'},
			{name:'linkMan',index:'linkMan'},
			{name:'linkPhone',index:'linkPhone'},
			{name:'actived',index:'actived', align:'center',formatter:function(cellvalue, options, rowObject){
				if(cellvalue == "0"){
					return "<input type='checkbox' onclick='startOrCancel("+options.rowId+" , this)'/>";
				}else{
					return "<input type='checkbox' onclick='startOrCancel("+options.rowId+" , this)' checked/>";
				}
			}}			
        ], 
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : 'expressGridPanelId',
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
			var ids = jQuery("#expressGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var cl = ids[i];
				var addBtn = "<div class='operating'>";
				addBtn += "<a href='javascript:' class='operating-pencil' title='修改' onclick=\"modExpress("+cl+")\"/></a>";
				addBtn += "<a href='javascript:' class='operating-pand' title='配送区域' onclick=\"setExpressarea("+cl+")\"></a></div>";
				jQuery("#expressGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		},
		multiselect : false
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("expressGridId");	
	})
});

function search(){
	var postData = new Array();
	var queryData = {};
	queryData.keyWord = $.trim($("#keyWord").val());
	postData.push(queryData);
    $("#expressGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}

function startOrCancel(rowId ,e ){
	var row = $("#expressGridId").getRowData(rowId);
	if($(e).attr("checked") == "checked" ){
		var options = {};
	    options.params = {"id" : row.id , "actived" : "1"};
	    options.isMask = true;
		options.url = base_template+"/expressMgr/modExpressInfo.jhtml";
		options.successCallback = function(data){
			oms.tooltip(data.errMsg , "succeed");
			search();
		};
		oms.ajaxCall(options);
	}else{
		var options = {};
	    options.params = {"id" : row.id , "actived" : "0"};
	    options.isMask = true;
		options.url = base_template+"/expressMgr/modExpressInfo.jhtml";
		options.successCallback = function(data){
			oms.tooltip(data.errMsg , "succeed");
			search();
		};
		oms.ajaxCall(options);
	}
}

function modExpress(rowId){
	var row = $("#expressGridId").getRowData(rowId);
	var str = encodeURI(base_template+"/expressMgr/turnModExpress.jhtml?id="+row.id+"");
	oms.s_addPop("修改物流信息",str,"",600,350);
}

function setExpressarea(rowId){
	var row = $("#expressGridId").getRowData(rowId);
	artTabs({
        addTab: {
            items: {
                id: 'detailGdsInfo',
                title: '配送区域信息',
                url: base_template + '/expressareaMgr/turnExpressareaMgr.jhtml?id='+row.id+'&name='+row.name+''
            }
        },
        isRefresh : true
    })
}