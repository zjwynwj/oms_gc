/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面 初始化  请求客户信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
$(function() {
	//客户表格
	jQuery("#custInfoGridId").jqGrid({
		datatype: "json",
		url: base_template + '/custInfo/findCustInfoForGrid.jhtml',
		mtype: 'POST', 
		postData : {},
		height : 'auto',
		colNames: ['序号','操作', '客户编号', '客户名称', '联系人', '联系号码','联系地址','传真','邮编','单位号码','状态','备注'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'act',index:'act' ,width:'40px'},
			{name:'custNo',index:'custNo'},
			{name:'custName',index:'custName'},
			{name:'linkMan',index:'linkMan'},
			{name:'linkPhone',index:'linkPhone'},
			{name:'linkAddr',index:'linkAddr'},
			{name:'fax',index:'fax',align:'right'},
			{name:'postCode',index:'postCode'},
			{name:'compPhone',index:'compPhone'},
			{name:'status',index:'status' , formatter:function(cellvalue, options, rowObject){
				if(cellvalue == "0"){
					return "停用";
				}else{
					return "启用";
				}
			}},
			{name:'remark1',index:'remark1'}
        ], 
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : 'custInfoGridPanelId',
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
			var ids = jQuery("#custInfoGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var cl = ids[i];
				var addBtn = "<div class='operating'>";
				addBtn += "<a href='javascript:' class='operating-pencil' title='修改' onclick=\"modCust("+cl+")\"></a></div>";
				jQuery("#custInfoGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		},
		multiselect : true
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("custInfoGridId");	
	})
});

/******************************************************************************
函数名称:   <ATFunc>turnAddCustInfo</ATFunc>
函数功能:   <ATFuncDesc>跳转到添加客户页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function turnAddCustInfo(){
	artTabs({
        addTab: {
            items: {
                id: 'addCustInfo',
                title: '添加客户信息',
                url: base_template + '/custInfo/turnAddCustInfo.jhtml'
            }
        }
    })
}

/******************************************************************************
函数名称:   <ATFunc>modCust</ATFunc>
函数功能:   <ATFuncDesc>跳转到修改客户页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function modCust(rowId){
	var row = $("#custInfoGridId").getRowData(rowId);
	
	artTabs({
        addTab: {
            items: {
                id: 'modCustInfo',
                title: '修改客户信息',
                url: base_template + '/custInfo/turnModCustInfo.jhtml?id='+row.id+''
            }
        },
        isRefresh : true
    })
}

/******************************************************************************
函数名称:   <ATFunc>search</ATFunc>
函数功能:   <ATFuncDesc>点击搜索 查询相关客户信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function search(){
	var postData = new Array();
	var queryData = {};
	queryData.keyWord = $("#keyWord").val();
	postData.push(queryData);
    $("#custInfoGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}