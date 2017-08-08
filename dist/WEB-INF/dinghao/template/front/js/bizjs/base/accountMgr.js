/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面 初始化  请求客户信息</ATFuncDesc>       
开发人员:   gucong
修改记录:
******************************************************************************/
$(function() {
	//客户表格
	jQuery("#mainGridId").jqGrid({
		datatype: "json",
		url: base_template + '/finance/findAccountForGrid.jhtml',
		mtype: 'POST', 
		postData : {},
		height : 'auto',
		colNames: ['序号','操作', '类型', '开户行', '账号', '户名','期初金额(元)','状态','默认账户'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'act',index:'act' ,width:'80px'},
			{name:'type',index:'type'},
			{name:'bankName',index:'bankName'},
			{name:'bankNo',index:'bankNo'},
			{name:'bankAccount',index:'bankAccount'},
			{name:'amount',index:'amount'},
			{name:'status',index:'status' , 
				formatter:function(cellvalue, options, rowObject){
					if(cellvalue == "0"){
						return "停用";
					}else{
						return "启用";
					}
				}
			},
			{name:'isdefault',index:'isdefault',
				formatter:function(cellvalue, options, rowObject){
					if(cellvalue == "0"){
						return "否";
					}else{
						return "是";
					}
				}
			
			
			}
        ], 
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : 'mainGridPanelId',
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
			var ids = jQuery("#mainGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var cl = ids[i];
				var addBtn = "<div class='operating'>";
				addBtn += "<a href='javascript:' class='operating-pencil' title='修改' onclick=\"modAccount("+cl+")\"></a></div>";
				jQuery("#mainGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		},
		multiselect : true
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("mainGridId");	
	})
});

/******************************************************************************
函数名称:   <ATFunc>turnAddCustInfo</ATFunc>
函数功能:   <ATFuncDesc>跳转到添加客户页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function turnAddAccount(){
	artTabs({
        addTab: {
            items: {
                id: 'addAccount',
                title: '添加账户信息',
                url: base_template + '/finance/turnAddAccount.jhtml'
            }
        }
    })
}

/******************************************************************************
函数名称:   <ATFunc>modAccount</ATFunc>
函数功能:   <ATFuncDesc>跳转到修改账户页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function modAccount(rowId){
	var row = $("#mainGridId").getRowData(rowId);
	artTabs({
        addTab: {
            items: {
                id: 'modAccount',
                title: '修改账户信息',
                url: base_template + '/finance/turnModAccount.jhtml?id='+row.id+''
            }
        },
        isRefresh : true
    })
}

/******************************************************************************
函数名称:   <ATFunc>search</ATFunc>
函数功能:   <ATFuncDesc>点击搜索 查询相关账户信息</ATFuncDesc>       
开发人员:   gucong
修改记录:
******************************************************************************/
function search(){
	var postData = new Array();
	var queryData = {};
	queryData.bankNo = $("#keyWord").val();
	postData.push(queryData);
    $("#mainGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}