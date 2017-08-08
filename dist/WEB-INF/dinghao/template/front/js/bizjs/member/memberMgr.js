
$(function() {
	//订单表格
	jQuery("#blackId").jqGrid({
		datatype: "json",
		url: base + '/template/memberMgr/findMemberGrid.jhtml',
		mtype: 'POST', 
		postData : {},
		height : 'auto',
		colNames: ['序号','操作', '店铺名称', '会员名称', '手机', '省份','城市','地址','购买次数'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'act',index:'act' ,width:'40px'},
			{name:'shopName',index:'shopName'},
			{name:'username',index:'username'},
			{name:'recvmobile',index:'recvmobile'},
			{name:'provname',index:'provname'},
			{name:'cityname',index:'cityname'},
			{name:'address',index:'address'},
			{name:'buytimes',index:'buytimes',width:'60px',align:'center'}
					
        ], 
		rowNum : 15,
		rowList : [15, 30, 50],
		pager : 'gridPager',
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
			var ids = jQuery("#blackId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var cl = ids[i];
				var addBtn = "<div class='operating'>";
				addBtn += "<a href='javascript:' class='operating-pencil' title='修改' onclick=\"modMerber("+cl+")\"></a>";
				jQuery("#blackId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		},
		multiselect : true
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("blackId");	
	})
});

function search(){
	var postData = new Array();
	var queryData = {};
	queryData.keyWord = $.trim($("#keyWord").val());
	postData.push(queryData);
    $("#blackId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}

function startOrCancel(rowId ,e ){
	var row = $("#expressGridId").getRowData(rowId);
	if($(e).attr("checked") == "checked" ){
		var options = {};
	    options.params = {"id" : row.id , "actived" : "1"};
	    options.isMask = true;
		options.url = base+"/template/expressMgr/modExpressInfo.jhtml";
		options.successCallback = function(data){
			oms.tooltip(data.errMsg , "succeed");
			search();
		};
		oms.ajaxCall(options);
	}else{
		var options = {};
	    options.params = {"id" : row.id , "actived" : "0"};
	    options.isMask = true;
		options.url = base+"/template/expressMgr/modExpressInfo.jhtml";
		options.successCallback = function(data){
			oms.tooltip(data.errMsg , "succeed");
			search();
		};
		oms.ajaxCall(options);
	}
}

function modMerber(rowId){

	var row = $("#blackId").getRowData(rowId);

	artTabs({
        addTab: {
            items: {
                id: 'modMember',
                title: '修改会员信息',
                url: base_template + '/memberMgr/turnModMember.jhtml?id='+row.id+''
            }
        },
        isRefresh : true
    })
}

function setExpressarea(rowId){
	var row = $("#blackId").getRowData(rowId);
	artTabs({
        addTab: {
            items: {
                id: 'detailGdsInfo',
                title: '配送区域信息',
                url: base + '/template/expressareaMgr/turnExpressareaMgr.jhtml?id='+row.id+'&name='+row.name+''
            }
        },
        isRefresh : true
    })
}