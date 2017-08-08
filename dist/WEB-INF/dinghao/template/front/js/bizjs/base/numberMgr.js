/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面初始化jqgrid数据</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
$(function(){
	//单据设置列表表格
	jQuery("#numberSettingsGridId").jqGrid({
		datatype: "json",
		url: base_template + '/baseNumber/findBaseNumberForGrid.jhtml',
		mtype: 'POST', 
		height : 'auto',
		colNames: ['ID','操作', '单据类型', '单据名称','单据前缀', '年份规则' ,'月份规则','日期规则','是否需要审核','编码位数','当前编号','步频','单据样例'], 
		colModel: [
           {name:'id',index:'id',hidden:true, align:'center'},
           {name:'act',index:'act', align:'center',width:'40px'},
           {name:'nuType',index:'nuType', align:'center',width:'80px'},
           {name:'nuName',index:'nuName', align:'center',width:'80px'},
           {name:'nuPrefix',index:'nuPrefix', align:'center',width:'80px'},
           {name:'yearRule',index:'yearRule', hidden:false, align:'center',width:'80px' , formatter:currencyFmatter},
           {name:'montRule',index:'montRule', align:'center',width:'80px', formatter:currencyFmatter},
           {name:'dayRule',index:'dayRule',hidden:false, align:'center',width:'80px' , formatter:currencyFmatter},
           {name:'isCheck',index:'isCheck', align:'center',width:'100px' , formatter:currencyFmatter2},
           {name:'nuDigit',index:'nuDigit', align:'center',width:'80px'},
           {name:'nuCurrent',index:'nuCurrent', align:'center',width:'80px'},
           {name:'nuStep',index:'nuStep',hidden:false, align:'center'},
           {name:'nuDemo',index:'nuDemo',width:'250px',hidden:false, align:'center'}
        ], 
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : 'numberSettingsGridPanelId',
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
			var ids = jQuery("#numberSettingsGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var rowId = ids[i];
				//设置可以编辑
				jQuery('#numberSettingsGridId').editRow(rowId);
				var addBtn = "<div class='operating'><a href='javascript:' class='operating-pencil' title='修改'  onclick=\"modBaseNum("+rowId+")\"></a>";
				jQuery("#numberSettingsGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
			//初始化计算jqgrid宽度
			$("#numberSettingsGridId").setGridWidth($('.m-detail-con').width()-20);
		}
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("numberSettingsGridId");	
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
函数名称:   <ATFunc>modBaseNum</ATFunc>
函数功能:   <ATFuncDesc>跳转到修改单据号页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function modBaseNum(rowId){
	var row = $("#numberSettingsGridId").getRowData(rowId);
	artTabs({
        addTab: {
            items: {
                id: 'modBaseNum',
                title: '修改单据号信息',
                url: base_template + '/baseNumber/turnModNumber.jhtml?id='+row.id+''
            }
        },
        isRefresh : true
    })
}
