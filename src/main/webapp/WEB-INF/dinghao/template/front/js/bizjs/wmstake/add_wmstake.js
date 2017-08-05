/*******************************************************************************
 * 函数名称: <ATFunc>add_wmstake.js</ATFunc> 函数功能:
 * <ATFuncDesc>新增盘点单页面(add_wmstake.ftl)的JS</ATFuncDesc> 开发人员: Zihan 2016年1月22日
 * 修改记录:
 ******************************************************************************/
var curRow = null; // 选择的全局变量,标记当前选择的是哪一行
var curCol = null; // 选择的全局变量,标记当前选择的是哪一列
var lasterSel; // 保存最后选择的一行的行id

var maingridid = "#blackId";

$(function() {
    // ----------------------------------Star-----------------------------------
    // 自动生成编号 单据初始化
    var options = {};
    options.params = {
	"nuType" : "07"
    };
    // 没有订单编号 自动生成
    options.url = base + "/template/baseNumber/findBaseNumberNext.jhtml";
    options.successCallback = successBackFn;
    oms.ajaxCall(options);
    function successBackFn(data) {
    	$("#takeNo").val(data.data);
    }
    // -----------------------------------End------------------------------------

    // ----------------------------仓库智能提示------Star--------------------------
    /*
    $('#warehouseName').select2({
	placeholder : "请输入仓库名",
	minimumInputLength : 1,
	multiple : false,
	width : '200',
	formatResult : function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
	    return result.warehouseName + "/" + result.address;
	},
	initSelection : function(element, callback) {
	    callback({
		id : warehouseId,
		warehouseName : placeholderText
	    });
	},
	formatSelection : function(item) {// 选中后显示在文本框中的值
	    $('#stockId').val(item.id);
	    $('#warehouseName').val(item.warehouseName);
	    return item.warehouseName;
	},
	dropdownCssClass : "bigdrop", // apply css that makes the dropdown
	// taller
	escapeMarkup : function(m) {
	    return m;
	},
	ajax : {
	    url : base + "/template/warehouse/get_warehouses_select2.jhtml",
	    delay : 250,
	    dataType : 'json',
	    data : function(term, page) {
		return {
		    warehouseName : encodeURI(decodeURIComponent(term, true)),// 汉字处理方式
		    pageNum : 1,// 页码
		    rows : 15,// 数量
		    t : (new Date()).valueOf()
		};
	    },
	    results : function(data, page) {
		return {
		    results : data
		};
	    }
	},
	allowClear : true
    // 单选
    });
    */
    // ----------------------------仓库智能提示------End---------------------------

    // ----------------------------盘点日期------Start----------------------------
    $("#datepicker")
	    .datepicker(
		    {
			showOn : "button",
			buttonImage : base
				+ "/dinghao/template/front/js/elem/jqueryui/css/images/calendar.gif",
			buttonImageOnly : true
		    });
    // ----------------------------盘点日期------End------------------------------


    // 单据设置列表表格
    jQuery("#blackId")
	    .jqGrid(
		    {
			datatype : "json",
			url : base_template
				+ '/wmstake/get_wmstake_details.jhtml?takeId=-1',
			mtype : 'POST',
			colNames : [  'gdsId', 'takeId', '操作', '商品编号',
				 '属性', '单位', '库位编码id', '库位编码',
				'当前数量', '备注' ],
			colModel : [
				{name : 'gdsId',index : 'gdsId', hidden : true},
				{name : 'takeId',index : 'takeId',hidden : true},
				{name : 'act',index : 'act',width:'70px'},
				{ name : 'gdsNo', index : 'gdsNo',width:260,
				    formatter : function(cellvalue, options, rowObject) {
					
					return "<input value='"
					+ cellvalue
					+ "' width='300px' class='bindSelect' name='gdsInfoSelect2' id=gdsShowInfo_"
					+ options.rowId + ">";
				    }
				},
				
				{ name : 'attbs',index : 'attbs'	},
				{name : 'cal',    index : 'cal'	,width:40},
				{ name : 'locId',    index : 'locId',   hidden : true				},
				{
				    name : 'warehousePositionsName',
				    index : 'warehousePositionsName',width:240,
				    formatter : function(cellvalue, options,rowObject) {
					return "<input value='"
						+ cellvalue
						+ "' width='300px' class='bindSelectPos' name='warehousePositionsName' id=warehousePositionsName_"
						+ options.rowId + ">";
				    }
				}, {
				    name : 'sysQty',
				    index : 'sysQty'
				}, {
				    name : 'memo',
				    index : 'memo',
				    editable : true
				} ],
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			pager : '#pager',
			toolbarfilter : true,
			height : '100%',
			viewrecords : true,
			autowidth : true,
			rownumbers : true,
			prmNames : {
			    page : "pageNum",
			    rows : "rows"
			},
			prmNames : {
			    page : "pageNum", // 表示请求页码的参数名称
			    rows : "rows", // 表示请求行数的参数名称
			    sort : "page.oid", // 表示用于排序的列名的参数名称
			    order : "page.oor", // 表示采用的排序方式的参数名称
			    search : "issearch", // 表示是否是搜索请求的参数名称
			    nd : "nd", // 表示已经发送请求的次数的参数名称
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
			},// 统一超时时间
			successTip : false,
			beforeProcessing : oms.grid.ajaxSuccessFn,
			loadError : oms.grid.ajaxErrorFn,
			multiselect : false,
			gridComplete : function() {
			    var ids = jQuery("#blackId").jqGrid('getDataIDs');
			    for (var i = 0; i < ids.length; i++) {
				var rowId = ids[i];
				// 设置可以编辑
			
				var addBtn = "<div class='operating'><a href='javascript:void(0);' onclick='addCol();' class='operating-plus' title='新增行'></a>";
				addBtn += "<a href='javascript:' class='operating-trash' onclick=\"delBtnFn('"
					+ rowId + "');\" title='删除'></a></div>";
				jQuery("#blackId").jqGrid('setRowData', ids[i],
					{
					    act : addBtn
					});
			    }
			    // 初始化计算jqgrid宽度
			    $("#blackId")
				    .setGridWidth($('.wrap-table').width());
			},
			loadComplete : function(a) {
			    var ids = jQuery("#blackId").jqGrid('getDataIDs');
			    for (var i = 0; i < ids.length; i++) {
				
			    	var warehousePositionsName = $("#"+ $($("#blackId").getCell(ids[i],"warehousePositionsName")).attr("id"));
				    bindWarehousePositionsName(warehousePositionsName);
				    $(warehousePositionsName).prev("div").find(".select2-chosen").text(
							$(warehousePositionsName).val());
					    }
				
				    var gdsShowInfoObj = $("#"+ $($("#blackId")
					.getCell(ids[i],"gdsNo")).attr("id"));
				    bindSelect2(gdsShowInfoObj);
				
				    $(gdsShowInfoObj).prev("div").find(".select2-chosen").text($(gdsShowInfoObj).val());
				
			    if (a == null || a.total == 0) {
			    	addCol('1');
			    }
			},
			'cellsubmit' : 'clientArray', // 不进行ajax提交
			'afterEditCell' : afterEditCellFn,
			'cellEdit' : true
		    // 设置可编辑单元格
		    });

    // ----------------------------------Star-----------------------------------
    function afterEditCellFn(rowid, cellname, value, iRow, iCol) {
	curRow = iRow;
	curCol = iCol;
	lasterSel = rowid;
    }
    // ----------------------------------End-----------------------------------
$("#scan").on("change",function(){
    addcolByScan($(this).val());
});
});// jQuery END

// -----------------------------库位智能提示-----Star-----------------------------
function bindWarehousePositionsName(e) {
    $(e).select2({
			placeholder : "请输库位名称",
			minimumInputLength : 1,
			multiple : false,
			width : '200',
			formatResult : function(result, container, query,
				escapeMarkup) { // 下拉框显示的值(待选区)
			    return result.warehousePositionsCode;
			},
			/*
			initSelection : function(element, callback) {
			    callback({
				id : element.locId,
				warehousePositionsCode : element.warehousePositionsCode
			    });
			},
			*/
			formatSelection : function(item) {// 选中后显示在文本框中的值
			    var rowId = $(e).attr("id").split("_")[1];
			    jQuery("#blackId").setCell(rowId, "locId", item.id);
			    $("#warehousePositionsName_" + rowId).val(
				    item.warehousePositionsCode);
			    jQuery("#blackId").setCell(rowId,
				    "warehousePositionsCode",
				    item.warehousePositionsCode);
			    return item.warehousePositionsCode;
			},
			dropdownCssClass : "bigdrop", // apply
			escapeMarkup : function(m) {
			    return m;
			},
			ajax : {
			    url : base_template
				    + "/warehouse/get_warehouses_detail_select2.jhtml",
			    delay : 250,

			    dataType : 'json',
			    data : function(term, page) {
				return {
				    warehousePositionsCode : encodeURI(decodeURIComponent(
					    term, true)),// 汉字处理方式
				    pageNum : 1,// 页码
				    rows : 15,// 数量
				    dhWarehousePositions : $("#stockId").val(),
				    t : (new Date()).valueOf()
				};
			    },
			    results : function(data, page) {
				return {
				    results : data
				};
			    }
			},
			allowClear : true
		    // 单选
		    });
    
    /*
    e.prev("div").find(".select2-chosen").text(e.val());
    $("input[name=warehousePositionsName]").unbind("change");
	$("input[name=warehousePositionsName]").bind("change",function(){
		var rowId = $(this).attr("id").split("_")[1];
		var locId = $(this).val() || " ";

		var locName = $(this).prev("div").find(".select2-chosen").text().split("-")[0];
	
		$(this).prev("div").find(".select2-chosen").text(locName);
		jQuery(maingridid).setCell(rowId,"locId",locId);
		
	});
     */
    
    
}
// ---------------------------库位智能提示-------End------------------------------

function addcolByScan(gdsNo){
    var stockId = $("#stockId").val();
    if (stockId == '') {
	
		oms.tooltip("请先选择入库仓库!","error");
		$("#stockId").focus();
		return;
    }
    if(gdsNo==''||typeof(gdsNo)=='undefined'){
    	return;
    }else{
	$.post(base_template + "/locstock/detail_locstock_items_select2.jhtml", { gdsNo1 :gdsNo,pageNum : 1,rows : 10,stockId:$("#stockId").val() },
	function(data){
	    callbackt(data);
	}, "json");
    }
}

/**
 * ****************************************************************************
 * 函数名称: <ATFunc>addCol</ATFunc> 函数功能: <ATFuncDesc>新增行</ATFuncDesc> 开发人员: *
 * Zihan 2016年1月18日 修改记录:
 * ****************************************************************************
 */
function addCol(times) {
    // 初始化
    if (typeof (times) == 'undefined' || times != '1') {
		if (!gridDataisPerfect()) {
		    return;
		}
		var warehouseName = $("#warehouseName").val();
		if (warehouseName == '') {
		    $.artDialog({
			title : '消息',
			content : '请先选择入库仓库'
		    });
		    $("#warehouseName").focus();
		    return;
		}
    }
    var defaultData = [ {
	gdsId : "",
	takeId : "-1",
	gdsNo : "",
	gdsName : "",
	gdsFormat : "",
	attbs : "",
	cal : "",
	locId : "",
	warehousePositionsName : "",
	sysQty : "",
	memo : ""
    } ];
    // 新增一行
    jQuery("#blackId").jqGrid('addRowData', 1, defaultData, "last");
   
    

    var s = $(maingridid).find("tr").last().find(".bindSelectPos");
    bindWarehousePositionsName(s);
    
    var gdsNo = $("#blackId").find("tr").last().find("input[name='gdsNo']");
    var e = $(maingridid).find("tr").last().find(".bindSelect");
    
    bindSelect2(e);
    
}
// -------------------------商品弹出框---------Star-----------------------------------
function callbackt(obj) {
    if (obj.length > 0) {  
	for (var int = 0; int < obj.length; int++) {
		
    	var data = {
       	    gdsId : obj[int].id,
    	    takeId : "-1",
    	    gdsNo : obj[int].gdsNo,
    	    gdsName : obj[int].gdsName,
    	    gdsFormat : obj[int].gdsFormat,
    	    attbs : obj[int].attbs,
    	    cal : obj[int].cal,
    	    locId : obj[int].locId,
    	    warehousePositionsName : obj[int].warehousePositionsName,
    	    sysQty : obj[int].totalQty,
    	    memo : ""
    	}
    	// 新增行
    	jQuery("#blackId").jqGrid('addRowData', 1, data, "last");
    	   
    	
    	// 添加监听事件
    	    var warehousePositionsName = $(maingridid).find("tr").last().find(".bindSelectPos");
    	    bindWarehousePositionsName(warehousePositionsName);
    	    
    	    
    	    var gdsNo = $("#blackId").find("tr").last().find("input[name='gdsNo']");
    	    var e = $(maingridid).find("tr").last().find(".bindSelect");
    	    bindSelect2(e);
    	 }
    
    }
}
function bindGdsInfo(e) {
    lasterSel = e;
    oms.s_addPop("商品选择", base + "/template/gdsMgr/get_turn_gdsmgr.jhtml", "", 1000, 600);
}
// -------------------------商品弹出框---------End-----------------------------------

// -----------------------------点击新增-----Star-----------------------------------
function gridDataisPerfect() {
    var warehouseName = $("#warehouseName").val();
    if (warehouseName == '') {
	$.artDialog({
	    title : '消息',
	    content : '请先选择入库仓库'
	});
	$("#warehouseName").focus();
	return;
    }

    var purData = $("#blackId").getRowData();
    for ( var i in purData) {
	var row = purData[i];
	if ($.trim(row.gdsId) == "" || $.trim(row.gdsId) == undefined) {
	    oms.tooltip("请选择商品信息!", "error");
	    return false;
	}
	if ($.trim(row.locId) == "" || $.trim(row.locId) == undefined) {
	    oms.tooltip("请选择库位信息!", "error");
	    return false;
	}
    }
    return true;
}
// -----------------------------点击新增-----End----------------------------------

// -----------------------------删除行-----Star-----------------------------------
function delBtnFn(rowId) {
    // 取得所有id
    var ids = jQuery("#blackId").jqGrid('getDataIDs');
    var size = ids.length;
    jQuery("#blackId").delRowData(rowId);
    if (size == 1) {
	   addCol('1');
    }
}
// ------------------------------删除行----End------------------------------------

// 点击空白处，保存正在编辑的数据
$('html').bind('click', function(e) { // 用于点击其他地方保存正在编辑状态下的行
    if (lasterSel != "") {
	if ($(e.target).closest('#blackId').length == 0) {
	    $("#blackId").jqGrid("saveCell", curRow, curCol);
	    lasterSel = "";
	}
    }
    if ($(e.target).attr("myLable") == "savePurOrder") {
    	saveWmsTake();
    } else if ($(e.target).attr("myLable") == "saveAndAddPurOrder") {
    	saveWmsTake();
    }
});
function saveWmsTake() {
    // 监测数据是否正确
    if (!gridDataisPerfect()) {
	return;
    }
    // 保存入库单信息
   // $("#myForm").submit();
    var wmsTakeVo = {};
    wmsTakeVo["takeNo"] = $("#takeNo").val();
    wmsTakeVo["takeName"] = $("#takeName").val();
    wmsTakeVo["stockId"] = $("#stockId").val();
    wmsTakeVo["createDate"] = $("#datepicker").val();
    wmsTakeVo["memo"] = $("#memo").val();

	
	var griddata = $("#blackId").getRowData();
	for(var i in griddata){
		wmsTakeVo["wmsTakeItem["+i+"].gdsId"] = griddata[i].gdsId;
		wmsTakeVo["wmsTakeItem["+i+"].locId"] = griddata[i].locId;
		wmsTakeVo["wmsTakeItem["+i+"].memo"] = griddata[i].memo;
			
	}
	var options = {};
    options.params = wmsTakeVo;
	options.url = base+"/template/wmstake/addWmstake.jhtml";
	options.successCallback = saveBackFn;
	oms.ajaxCall(options);
    
}

function saveBackFn(data){
	oms.tooltip(data.errMsg,"succeed");
	
}



function bindSelect2(e){
	e.select2({
		placeholder : "请输入商品编号\名称",
		minimumInputLength : 1,
		multiple : false,
		width : '230',
		formatResult : function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
			return result.gdsShowInfo ;
		},
		formatSelection : function(item) {// 选中后显示在文本框中的值
			return item.gdsShowInfo+"-"+item.attbs;
		},
		dropdownCssClass : "bigdrop", // apply css that makes the dropdown
		escapeMarkup : function(m) {
			return m;
		},
		ajax : {
			url : base+"/template/gdsMgr/findGdsInfoForGrid.jhtml",
			delay : 250,
			dataType : 'json',
			data : function(term, page) {
				return {
					keyWord : term,// 汉字处理方式
					pageNum : 1,// 页码
					rows : 10,// 数量
				};
			},
			results : function(data, page) {
				return {
					results : data.data.list
				};
			}
		},
		allowClear : true// 单选
	});
	e.prev("div").find(".select2-chosen").text(e.val());
	
	$("input[name=gdsInfoSelect2]").unbind("change");
	$("input[name=gdsInfoSelect2]").bind("change",function(){
		var rowId = $(this).attr("id").split("_")[1];
		
		var gdsId = $(this).val() || " ";
		var gdsShowInfo = $(this).prev("div").find(".select2-chosen").text().split("-")[0];
		var attbsInfo = $(this).prev("div").find(".select2-chosen").text().split("-")[1];
		$(this).prev("div").find(".select2-chosen").text(gdsShowInfo);
		
		var attbs = new Array();
		var gdsAttbs =" ";
		if(attbsInfo != "" && attbsInfo  != "undefined" && typeof(attbsInfo) != "undefined"){
			attbs = attbsInfo.split(";");
			
			for(var i in attbs){
				gdsAttbs += ";"+attbs[i].split(":")[2]+":"+attbs[i].split(":")[3];
			}
			gdsAttbs = gdsAttbs.substring(2, gdsAttbs.length);
		}
		jQuery(maingridid).setCell(rowId,"gdsId",gdsId);
		jQuery(maingridid).setCell(rowId,"attbs",gdsAttbs);
	});
}
