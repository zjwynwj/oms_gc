var curRow = null;  //选择的全局变量,标记当前选择的是哪一行
var curCol = null;  //选择的全局变量,标记当前选择的是哪一列
var lasterSel ;     //保存最后选择的一行的行id


$(function() {
    $("#datepicker")
	    .datepicker(
		    {
			showOn : "button",
			buttonImage : base
				+ "/dinghao/template/front/js/elem/jqueryui/css/images/calendar.gif",
			buttonImageOnly : true
		    });
    $("#datepicker2")
	    .datepicker(
		    {
			showOn : "button",
			buttonImage : base
				+ "/dinghao/template/front/js/elem/jqueryui/css/images/calendar.gif",
			buttonImageOnly : true
		    });
    
    
    
    $('html').bind('click', function(e) { //用于点击其他地方保存正在编辑状态下的行
	    if ( lasterSel != "" ) {
	        if($(e.target).closest("#blackId").length == 0) {
	            $("#blackId").jqGrid("saveCell", curRow, curCol);
	            lasterSel = ""; 
	        } 
	    } 
	  
	    var itemData = $("#blackId").getRowData();
	
		for(var i in itemData){
		
			var row = itemData[i];
			var rowid = parseFloat(row.id);
			var sysQty = parseFloat(row.sysQty);
			var countQty = parseFloat(row.countQty);
				if (isNaN(countQty)){
					countQty = 0;
				}
			jQuery("#blackId").setCell(rowid,"diffQty", sysQty - countQty);
		}
		
		var target = e.target;
		if($(target).attr("id")=="save")
		{
			//save();
		}
		

	});
    
    // 单据设置列表表格
    jQuery("#blackId").jqGrid(
	    {
		datatype : "json",
		url : base_template + '/wmstake/detail_wmstake_item.jhtml?takeId='
			+ $("#takeId").val(),
		mtype : 'POST',
		forceFit : true,
		colNames : [ '序号', '商品编号', '规格属性', '盘点前数量', '盘点数量', '差异', '库位id','库位',
			'时间' ],
		colModel : [ 
		             {name : 'id', index : 'id', hidden : true}, 
		             {name : 'gdsNo',index : 'gdsNo',},
		             {name : 'gdsFormat', index : 'gdsFormat', formatter : gdsFormat},
		             {name : 'sysQty',index : 'sysQty'}, 
		             {name : 'countQty',index : 'countQty' ,editable:true},
		             {name : 'diffQty', index : 'diffQty'},
		             {name : 'locId', index : 'locId',hidden : true},
			         {name : 'warehousePositionsName', index : 'warehousePositionsName'},
		             {name : 'createDate',index : 'createDate', formatter : 'date',
						    formatoptions : {
							srcformat : 'u',
							newformat : 'Y-m-d'
						    }
		           }
		           ],
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#pager',
		toolbarfilter : true,
		height : '100%',
		viewrecords : true,
		autowidth : true,
		rownumbers : true,
		prmNames : { // 默认发送参数格式设置
		    page : "pageNum",
		    rows : "rows"
		},
		prmNames : {
		    page : "pageNum",
		    // 表示请求页码的参数名称
		    rows : "rows",
		    // 表示请求行数的参数名称
		    sort : "page.oid",
		    // 表示用于排序的列名的参数名称
		    order : "page.oor",
		    // 表示采用的排序方式的参数名称
		    search : "issearch",
		    // 表示是否是搜索请求的参数名称
		    nd : "nd",
		    // 表示已经发送请求的次数的参数名称
		    state : "isstate"
		},
		jsonReader : { // 返回数据格式设置
		    root : "rows",
		    records : "records",
		    total : "total",
		    repeatitems : false
		},
		ajaxGridOptions : {
		    timeout : oms.ajaxTimeout
		},
		// 统一超时时间
		successTip : false,
		beforeProcessing : oms.grid.ajaxSuccessFn,
		loadError : oms.grid.ajaxErrorFn,
		multiselect : false,
		'cellEdit' : true,  //设置可编辑单元格
	    'cellsubmit' : 'clientArray',  //不进行ajax提交
	    'afterEditCell' : afterEditCellFn,
	    });
    
    
    
     $("#scan").on("change",function(){
        scansp($(this).val());
     });

});

function afterEditCellFn(rowid, cellname, value, iRow, iCol) {
	curRow = iRow;
	curCol = iCol;
	lasterSel = rowid;
	
	$("#blackId").find('tr').eq(curRow).find('td').eq(curCol).find('input').blur(function(){
		
		/*
		var sysQty = (typeof($("#blackId").find('tr').eq(curRow).find('td').eq(4).find('input').val()) == 'undefined') 
	    ? $("#blackId").find('tr').eq(curRow).find('td').eq(4).text()
	    : $("#blackId").find('tr').eq(curRow).find('td').eq(4).find('input').val();
	    */
		var countQty = (typeof($("#blackId").find('tr').eq(curRow).find('td').eq(5).find('input').val()) == 'undefined') 
	    ? $("#blackId").find('tr').eq(curRow).find('td').eq(5).text()
	    : $("#blackId").find('tr').eq(curRow).find('td').eq(5).find('input').val();
	    jQuery("#blackId").setCell(rowid,"diffQty",sysQty - countQty );
	}); 
	if(curCol ==5  ){
		var item = $("#blackId").find('tr').eq(curRow).find('td').eq(curCol).find('input');
		item.attr("maxlength" , 10);
		bindJqgridJustNumberEvent(item);
	}
	
}
// 入库单编辑
function gdsFormat(cellvalue, options, rowObject) {
    addBtn = rowObject.gdsFormat + rowObject.attbs;
    return addBtn;
}
//条码扫描
function scansp(gdspact){
   //
	var param_scan = {};
	var takeId = $("#takeId").val();
	//当前扫描数据是否和商品匹配
	var param_scan = {'gdsPact':gdspact,'Id':takeId};
	$.ajax({
		url: base_template + "/wmstake/spscanWmsTake.jhtml",
		type: 'GET',
		data: param_scan,
		dataType: 'json',
		timeout: "300000",
		error: function(data){
			
			 $("#sptips").val("扫描失败："+param_scan.gdsPact);
		},
		success: function(data){
	
			var dataObj=eval("("+data.t+")");
			
			 if (dataObj.spexists == 'false') //商品编码不存在
		        {
				   $("#sptips").val( "商品编码不存在："+param_scan.gdsPact);
				   return;
		    	}
			   if (dataObj.itemexists =='false')//商品不在盘点单
			   {
				   $("#sptips").val("商品不在盘点单："+param_scan.gdsPact);
				   return;
			   }
			   $("#sptips").val("扫描成功,商品编码："+param_scan.gdsPact);
			   
			   var griddata = $("#blackId").getRowData();
			   	for (var i in griddata) {
			   		var row = griddata[i];
					var rowid = parseFloat(row.id);
				
			   		var gdsNo = griddata[i].gdsNo;
			   		var locId = griddata[i].locId;
			   		var countQty = parseFloat(griddata[i].countQty);
			   		var sysQty = parseFloat(griddata[i].sysQty);
					   		
			   		
		
			  	    
					
			   		if (gdsNo == dataObj.gdsNo && locId == dataObj.locId) 
		   			{
			   			jQuery("#blackId").setCell(rowid,"countQty", countQty +  1);
			   			jQuery("#blackId").setCell(rowid,"diffQty", sysQty - (countQty +  1));
		   			}
			   	}
			  
		},
		async:true
	});
	
	$("#scan").val("") ;
	$("#scan").focus();
	
}
/**
 * 开始盘点
 */
function startWms() {
    $.artDialog({
	title : '消息',
	content : '你确认要开始盘点吗?',
	cancel : function() {
	    return false;
	},
	ok : function() {
	    $.post(base_template + "/wmstake/start_wmstakes.jhtml", {
			id : $("#takeId").val()
	    }, function(data) {
		if (data.success) {
		    $.artDialog({
			title : '消息',
			content : '库存已锁定，您可以开始盘点了!'
		    });
		    
		    $.artTabs({
		        isRefresh : true
		    })
		} else {
		    $.artDialog({
			title : '消息',
			content : data.msg
		    });
		}
	    }, "json");
	}
    });
}
/**
 * 结束盘点
 */
function endWms() {
    $.artDialog({
	title : '消息',
	content : '你确认要结束盘点吗?',
	cancel : function() {
	    return false;
	},
	ok : function() {
	    $.post(base_template + "/wmstake/end_wmstakes.jhtml", {
		id : $("#takeId").val()
	    }, function(data) {
		if (data.success) {
			oms.tooltip("盘点结束成功！" , "success");
		   
		} else {
		    $.artDialog({
			title : '消息',
			content : data.msg
		    });
		}
	    }, "json");
	}
    });
}

function exportExcel(takeId) {
    top.location = base_template + "/wmstake/export_excel.jhtml?takeId=" + takeId;
}
function importExcel(takeId) {
    /*
     * $("#loading").ajaxStart(function() { $(this).show(); })// 开始上传文件时显示一个图片
     * .ajaxComplete(function() { $(this).hide(); });// 文件上传完成将图片隐藏起来
     */
    var mine = $("#file").val();
    mine = mine.toLowerCase().substr(mine.lastIndexOf("."));
    if (!(mine == ".xls")) {
	$.artDialog({
	    title : '消息',
	    content : '请导入正确的EXCEL文件，仅支持xls格式!',
	    ok : function() {
		return false;
	    }
	});
	return false;
    }
    $.ajaxFileUpload({
	url : base_template + '/wmstake/import_excel.jhtml?takeId='+takeId,
	// 用于文件上传的服务器端请求地址
	secureuri : false,
	// 一般设置为false
	fileElementId : 'file',
	// 文件上传空间的id属性 <input type="file" id="file"
	// name="file" />
	dataType : 'json',
	// 返回值类型 一般设置为json
	success : function(data, status) // 服务器成功响应处理函数
	{
	    if (data.success) {
		$.artDialog({
		    title : '消息',
		    content : data.msg,
		    ok : function() {
			jQuery("#blackId").jqGrid().trigger("reloadGrid");
			return false;
		    }
		});
	    } else {
		$.artDialog({
		    title : '消息',
		    content : data.msg,
		    ok : function() {
			return false;
		    }
		});
	    }
	},
	error : function(data, status, e) // 服务器响应失败处理函数
	{
	    $.artDialog({
		title : '消息',
		content : e,
		ok : function() {
		    return false;
		}
	    });
	}
    })

    return false;
}
function saveButton(){
    var wmsTakeItem = {};
    var griddata = $("#blackId").getRowData();
   
	for (var i in griddata) {
            wmsTakeItem["wmsTakeItem[" + i + "].id"] = griddata[i].id;
            wmsTakeItem["wmsTakeItem[" + i + "].sysQty"] = griddata[i].sysQty;
            wmsTakeItem["wmsTakeItem[" + i + "].countQty"] = griddata[i].countQty;
            wmsTakeItem["wmsTakeItem[" + i + "].diffQty"] = griddata[i].diffQty;
            wmsTakeItem["wmsTakeItem[" + i + "].scanQty"] = 0;
    }
 
    var options = {};
    options.params = wmsTakeItem;
    options.url = base + "/template/wmstake/update_wmsTake_item.jhtml";
    options.successCallback = savePurOrderBackFn;
    oms.ajaxCall(options);
}
function savePurOrderBackFn(data) {
	oms.tooltip("盘点单保持成功！" , "success");
}