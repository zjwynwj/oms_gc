var curRow = "";
var curCol = "";
var lasterSel = "";
	
$(function() {
	var isIE=!!window.ActiveXObject;
    var isIE6=isIE&&!window.XMLHttpRequest;
    var isIE8=isIE&&!!document.documentMode;
    var isIE7=isIE&&!isIE6&&!isIE8;     
    if(isIE8 || isIE7){       
        document.attachEvent('click',function(e){
	   		 if (lasterSel != "" ) {
	   	    	var a=$(e.target).closest('#expressareaGridId').length==0?true:false,
	   	    	    b=$(e.target).parent().hasClass("jqgroup") || 
	   	    			$(e.target).parent().parent().hasClass("jqgroup"), //group td
	   	    	    c=$(e.target).hasClass("ui-jqgrid-sortable"),
	   	    	    d= a || b || c;    	
	   	        if(d) {        	
	   	            $("#expressareaGridId").jqGrid("saveCell", curRow, curCol);
	   	            lasterSel = ""; 
	   	        } 
	   	    } 
	   	},true);
    }else{
    	document.addEventListener('click',function(e){
	   		if (lasterSel != "" ) {
	   	    	var a=$(e.target).closest('#expressareaGridId').length==0?true:false,
	   	    	    b=$(e.target).parent().hasClass("jqgroup") || 
	   	    			$(e.target).parent().parent().hasClass("jqgroup"), //group td
	   	    	    c=$(e.target).hasClass("ui-jqgrid-sortable"),
	   	    	    d= a || b || c;    	
	   	        if(d) {        	
	   	            $("#expressareaGridId").jqGrid("saveCell", curRow, curCol);
	   	            lasterSel = ""; 
	   	        } 
	   	    } 
	   		if($(e.target).attr("name")=="save"){
	   			$("#expressareaGridId").jqGrid("saveCell", curRow, curCol);
   	            lasterSel = ""; 
	   		}
	   	},true);
    }
	//订单表格
	jQuery("#expressareaGridId").jqGrid({
		datatype: "json",
		url: base_template + '/expressareaMgr/findDmsExpressareaInfoList.jhtml',
		mtype: 'POST', 
		postData : {"expressId" : $("#expressId").val()},
		height : 'auto',
		colNames: ['序号','操作','支持配送', '地区', '起步价(元)', '起步重量(g)', '续价(元)','续重(g)'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'act',index:'act' ,width:'80px'},
			{name:'isdelivery',index:'isdelivery', align:'center',formatter:function(cellvalue, options, rowObject){
					if(cellvalue == "0"){
						return "<input type='checkbox' onclick='supportDelivery("+options.rowId+" , this)'/>";
					}else{
						return "<input type='checkbox' onclick='supportDelivery("+options.rowId+" , this)' checked/>";
					}
				}
			},
			{name:'provName',index:'provName'},
			{name:'startPrice',index:'startPrice',editable:true,formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaulValue: 0}},
			{name:'startWeight',index:'startWeight',editable:true},
			{name:'addPrice',index:'addPrice',editable:true,formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaulValue: 0}},
			{name:'addWeight',index:'addWight',editable:true}
        ], 
		rowNum : 40,
//		rowList : [10, 20, 30],
//		pager : 'expressareaGridPanelId',
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
			root: "data",
			repeatitems : false
		},
		ajaxGridOptions:{
			timeout:oms.ajaxTimeout
		},//统一超时时间
		successTip:false,
		beforeProcessing:oms.grid.ajaxSuccessFn,
		loadError:oms.grid.ajaxErrorFn,
		'cellEdit' : true,  //设置可编辑单元格
	    'cellsubmit' : 'clientArray',  //不进行ajax提交
		'afterEditCell' : afterEditCellFn,
		gridComplete : function() {
			var ids = jQuery("#expressareaGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var cl = ids[i];
				var addBtn = "<div class='operating'>";
				addBtn += "<input type='button' onclick=\"saveExpressarea("+cl+")\" value='保存'  name='save'></input></div>";
				jQuery("#expressareaGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		}
//		multiselect : true
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("expressareaGridId");	
	})
});

function search(){
	var postData = new Array();
	var queryData = {};
	queryData.keyWord = $.trim($("#keyWord").val());
	postData.push(queryData);
    $("#expressareaGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}

function supportDelivery(rowId , e){
	var row = $("#expressareaGridId").getRowData(rowId);
	var options = {};
	if($(e).attr("checked") == "checked" ){
		options.params = {"id" : row.id , "isdelivery" : "1"};
	}else{
		options.params = {"id" : row.id , "isdelivery" : "0"};
	}
    options.isMask = true;
	options.url = base_template+"/expressareaMgr/modDmsExpressareaDelivery.jhtml";
	options.successCallback = function(data){
		oms.tooltip(data.errMsg , "succeed");
		search();
	};
	oms.ajaxCall(options);
}

function afterEditCellFn(rowid, cellname, value, iRow, iCol) {
	curRow = iRow;
	curCol = iCol;
	lasterSel = rowid;
	if(curCol ==6 || curCol ==8){
		var item = $('#expressareaGridId').find('tr').eq(curRow).find('td').eq(curCol).find('input');
		item.attr("maxlength" , 8);
		bindJqgridJustNumberEvent(item);
	}
	
	if(curCol==5 || curCol ==7){
		var item = $('#expressareaGridId').find('tr').eq(curRow).find('td').eq(curCol).find('input');
		bindJqgridMoneyAndAmountEvent(item);
	}
}

function saveExpressarea(rowId){
	var row = $("#expressareaGridId").getRowData(rowId);
	if($(row.isdelivery).attr("checked") == "checked"){
		if(row.startPrice == 0 || row.startWeight == 0 || row.addPrice == 0 || row.addWeight == 0){
			oms.tooltip("开启配送,价格和重量信息不能为空!" , "error");
			return;
		}
	}
	
	var expressareaVoList = {};
	expressareaVoList["list[0].id"] = row.id;
	expressareaVoList["list[0].startPrice"] = row.startPrice;
	expressareaVoList["list[0].startWeight"] = row.startWeight;
	expressareaVoList["list[0].addPrice"] = row.addPrice;
	expressareaVoList["list[0].addWeight"] = row.addWeight;
	var options = {};
    options.params = expressareaVoList;
	options.url = base_template+"/expressareaMgr/modExpressareaInfo.jhtml";
	options.successCallback = function(data){
		oms.tooltip(data.errMsg , "succeed");
		search();
	};
	oms.ajaxCall(options);
}
