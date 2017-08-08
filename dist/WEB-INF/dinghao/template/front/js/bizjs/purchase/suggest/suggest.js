var maingridid = "blackId";
$(function(){

	$('#addSuggestB').artTabs({
		addTab: {
				items: {
						id: 'addSuggest',
						title: '新增采购建议单',
						url: base+'/template/suggest/purSuggestAdd.jhtml'
				}
		}
	})
	$('#searchBtn').click(function(){
		
	  
		    var postData = new Array();
			var queryData = {};
			queryData.suggestNo =  $("#suggestNo").val();
			queryData.busiDate1 =  $("#busiDate1").val();
			queryData.busiDate2 =  $("#busiDate2").val() + " 23:59:59";
				
			postData.push(queryData);
		    $("#"+maingridid).jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);

	})
	


	$("#busiDate1").datepicker({
		showOn: "both", 
		buttonImage: template_base+"/js/elem/jqueryui/css/images/calendar.gif",
		buttonImageOnly: true  
	});
	$("#busiDate2").datepicker({
		showOn: "both", 
		buttonImage: template_base+"/js/elem/jqueryui/css/images/calendar.gif",
		buttonImageOnly: true  
	});
	

	
	
	pageInit();

	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth(maingridid);	
	})
	
});
	



function addLine(){
	var defaultData = [{id:"1",suggestNo:"",status:"",busiDate:"",planDate:"",busiPerson:"",remark:""}];
	//新增一行
	jQuery("#blackId").jqGrid('addRowData','',defaultData);
}




function pageInit(){

  jQuery("#"+maingridid).jqGrid({
	    datatype: "json",
		url: base+"/template/suggest/queryList.jhtml",
		mtype: 'POST', 
		postData : {"offset":"0","rows":"20"},
		height: 380,
		autowidth: true,
		ajaxGridOptions:{
			timeout:oms.ajaxTimeout
		},//统一超时时间
		forceFit:true,
		rownumbers: true,
		colNames:['序号','操作','单据号', '状态', '业务日期','预计到货','采购员','备注'],
		colModel:[
			{name:'id',index:'id', sorttype:"int",sortable:false,width:"40px",hidden:true},
			{name:'act',index:'act',sortable:false,width:"100px"},
			{name:'suggestNo',index:'suggestNo', sorttype:"date",width:"150px"},
			{name:'status',index:'status',editable:true,
				formatter:function(cellvalue, options, rowObject){
					var new_format_value=" ";
					if(cellvalue == 'new'){
						new_format_value="新增";
					}
					 return new_format_value  }
			},
			{name:'busiDate',index:'busiDate', align:"right",sorttype:"date",formatter:'date',	formatoptions:{srcformat: 'U' , newformat: 'Y/m/d'}},
			{name:'planDate',index:'planDate', align:"right",sorttype:"date",editable:false,formatter:'date',	formatoptions:{srcformat: 'U' , newformat: 'Y/m/d'}},		
			{name:'userName',index:'userName',align:"left"},		
			{name:'remark',index:'remark', sortable:false}		
		],
		rowNum:20,
		rowList:[20,50,100],
		viewrecords: true,
		multiselect: true,
		toolbarfilter : true,
		pager: 'gridPager',
		pagerpos:'center',	//指定分页栏的位置
		pgbuttons:true, //是否显示翻页按钮
		pginput: true,	//是否显示跳转页面的输入框
		//pgtext: "sd",	//当前页信息
		//caption: "Manipulating Array Data",
		//scrollOffset: 30, //设置垂直滚动条宽度
		loadComplete:function(){
			
		},
		jsonReader:{  //返回数据格式设置
			root: "data.list",  
			total: "data.pageCount",  
			records: "data.count",
			repeatitems : false
		},
		gridComplete : function() {
			var ids = jQuery("#"+maingridid).jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				
				var rowId = ids[i];
				//获取当前 数据库id
				var dataId = jQuery("#blackId").getRowData(rowId).id;
				
				
				//设置可以编辑
				//jQuery('#blackId').editRow(rowId);
				addBtn = "<div class='operating'><a href='javascript:' onclick=\"f_edit('" + dataId + "');\" class='operating-pencil' title='修改'></a>";
				addBtn += "<a href='javascript:' class='operating-trash' title='删除'></a>" +
						"<a href='javascript:' class='operating-comment' title='详细'></a></div>";
				jQuery("#blackId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		
		
			    
		}
	})
	/*
	jQuery("#"+maingridid).jqGrid("clearGridData");
	for(var i=0;i<=mydata.length;i++){
	
			jQuery("#"+maingridid).jqGrid('addRowData',i+1,mydata[i]);
	}
	*/
 
}


function f_edit(dataId)
{
	artTabs({
        addTab: {
            items: {
                id: 'editSuggest',
                title: '修改采购建议',
                url: base+'/template/suggest/purSuggestEdit.jhtml?id='+dataId
            }
        }
    })
}
