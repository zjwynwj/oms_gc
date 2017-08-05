function turnAddPrintTemplate(){
	artTabs({
        addTab: {
            items: {
                id: 'turnPrintTemplateAdd',
                title: '添加打印模板',
                url: base_template + '/printMgr/turnPrintTemplateAdd.jhtml'
            }
        }
    });
}

$(function() {
	//商品表格
	jQuery("#printTempalteGridId").jqGrid({
		datatype: "json",
		url: base + '/template/printMgr/findPrintTemplate.jhtml',
		mtype: 'POST', 
		postData : {},
		height : 'auto',
		colNames: ['id','logisId','模板名称','物流公司名称','模板类型','模板宽度(mm)','模板高度(mm)','修改时间','操作','默认状态'], 
		colModel: [
			{name : 'id', index : 'id' , hidden:true}, 
            {name : 'logisId', index : 'logisId' , hidden:true}, 
            {name : 'name', index : 'name'}, 
            {name : 'logisName', index : 'logisName'}, 
            {name : 'type', index : 'type', hidden:false}, 
            {name : 'templateDivW', index : 'templateDivW'}, 
            {name : 'templateDivH', index : 'templateDivH'}, 
            {name : 'updateTime', index : 'updateTime', formatter:'date',formatoptions:{srcformat : 'u',newformat : 'Y-m-d H:i:s'}},
            {name : 'status', index : 'status',align:'center', title:false,
				formatter:function(cellvalue, options, rowObject){
								
					var ae = 	"<a href='javascript:' class='operating-pencil' title='修改打印模板' onclick=\"modPrintReport('"+rowObject.id+"'  , '1')\"></a>";
					ae += "<a href='javascript:' class='operating-comment' title='预览打印模板' onclick=\"previewPrintReport('"+rowObject.id+"')\"></a>";
					if(cellvalue == "1" ){
						ae += "<a href='javascript:' class='operating-check' title='停用' onclick=\"modPrintReport('"+rowObject.id+"' , '2')\"></a>";
					}else{
						ae += "<a href='javascript:' class='operating-check' title='启用' onclick=\"modPrintReport('"+rowObject.id+"' , '3')\"></a>";
					}
					var act="<div class='operating'>"+ae+"</div>";
        			return act;
                }
            }, 
            {name : 'defaultFlag', index : 'defaultFlag' , hidden:true}
        ], 
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : 'printTempalteGridPanelId',
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
		multiselect : true
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("printTempalteGridId");	
	});
});

function modPrintReport(rowId , operType){
	var griddata = $("#printTempalteGridId").getRowData(rowId);
	if(operType == "1"){
		var id = griddata.id;
		artTabs({
	        addTab: {
	            items: {
	                id: 'turnPrintTemplateMod',
	                title: '打印模板修改 ',
	                url: base_template + '/printMgr/turnPrintTemplateMod.jhtml?id='+id+''
	            }
	        },
	        isRefresh: true
	    }); 
	}else{
		var params = {};
		if(operType == "2"){
			params={'id': griddata.id,status :"0"};
		}else{
			params={'id': griddata.id,status :"1"};
		}
		oms.ajaxCall({
			 url :base_template+"/printMgr/modPrintTemplate.jhtml",
			 params: params,
			 successTip:true,
			 successCallback:function(data){
					oms.tooltip(data.errMsg,"succeed");
					search();
			 }
		});

	}
}

function previewPrintReport(rowId){
	var griddata = $("#printTempalteGridId").getRowData(rowId);
	var str = encodeURI(base+"/template/printMgr/turnPrintPreView.jhtml?id="+griddata.id+"");
	oms.s_addPop("打印 模板预览",str,"",720,450);
}

function search(){
	var postData = new Array();
	var queryData = {};
	queryData.keyWord = $("#keyWord").val();
	postData.push(queryData);
    $("#printTempalteGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}