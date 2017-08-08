/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面初始化jqgrid数据</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
$(function(){
	//单据设置列表表格
	jQuery("#mainGridId").jqGrid({
		datatype: "json",
		url: base_template + '/shop/findForGrid.jhtml',
		mtype: 'POST', 
		postData : {},
		height : 'auto',
		colNames: ['ID','操作','平台', '店铺编号', '店铺名称','卖家昵称' ,'手机','所在地区','发货地址','邮政编码','默认物流','状态'], 
		colModel: [
           {name:'id',index:'id',hidden:true, align:'center'},
           {name:'act',index:'act', align:'left'},
           {name:'planType',index:'planType', align:'center'},
           {name:'code',index:'code', align:'left'},
           {name:'name',index:'name', align:'left'},
           {name:'nickname',index:'nickname', align:'center'},
           {name:'phone',index:'phone', align:'center'} , 
          
           {name:'areaName',index:'areaName',hidden:false, align:'left',width:'250px' },
           {name:'address',index:'address', align:'center' },
           {name:'zipcode',index:'zipcode', align:'center'},
           {name:'expName',index:'expName', align:'center'},
           {name:'beactive',index:'beactive',hidden:false, align:'center',
				formatter : function(cellvalue, options, rowObject) {
					if (cellvalue == 0) {
						return "停用";
					} else {
						return "启用";
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
		autowidth : false,
		shrinkToFit:true,
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
		successTip:false,   //加载成功后是否出现提示信息
		beforeProcessing:oms.grid.ajaxSuccessFn,
		loadError:oms.grid.ajaxErrorFn,
		gridComplete : function() {
			var ids = jQuery("#mainGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var rowId = ids[i];
				//设置可以编辑
				jQuery('#mainGridId').editRow(rowId);
				var addBtn = "<div class='operating'><a href='javascript:' class='operating-pencil' title='修改'  onclick=\"modShop("+rowId+")\"></a>";
				jQuery("#mainGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
			//初始化计算jqgrid宽度
			$("#mainGridId").setGridWidth($('.m-detail-con').width()-20);
		}
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("mainGridId");	
	})
	
	
	
	$('#searchBtn').click(function(){
		
		//获取单据号
		var name = $("#name").val();
		var postData = new Array();
		var queryData = {};
		queryData.name = $("#name").val();
		postData.push(queryData);
	    $("#mainGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);

	})
	
});

/******************************************************************************
函数名称:   <ATFunc>currencyFmatter</ATFunc>
函数功能:   <ATFuncDesc>转化显示方法1</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function currencyFmatter (cellvalue, options, rowObject) {      
	var new_format_value="×";
	if(cellvalue){
		new_format_value="√";
	}
   return new_format_value      
}    

/******************************************************************************
函数名称:   <ATFunc>currencyFmatter2</ATFunc>
函数功能:   <ATFuncDesc>转化显示方法2</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function currencyFmatter2 (cellvalue, options, rowObject) {      
	var new_format_value="否";
	if(cellvalue){
		new_format_value="是";
	}
   return new_format_value      
} 

/******************************************************************************
函数名称:   <ATFunc>modShop</ATFunc>
函数功能:   <ATFuncDesc>跳转到修改店铺页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function modShop(rowId){
	var row = $("#mainGridId").getRowData(rowId);
	artTabs({
        addTab: {
            items: {
                id: 'modShop',
                title: '修改店铺信息',
                url: base_template + '/shop/turnModShop.jhtml?id='+row.id+''
            }
        },
        isRefresh : true
    })
}
