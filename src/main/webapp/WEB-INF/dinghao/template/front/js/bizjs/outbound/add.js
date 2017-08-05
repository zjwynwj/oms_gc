/**
 * add_update.ftl 页面引用JS
 */
var curRow = null; // 选择的全局变量,标记当前选择的是哪一行
var curCol = null; // 选择的全局变量,标记当前选择的是哪一列
var lasterSel; // 保存最后选择的一行的行id
$(function() {
    // 单据设置列表表格
    jQuery("#blackId").jqGrid({
    	datatype: "local",
		height: 340,
		autowidth: true,
		sortorder : "no",
		rownumbers: true,
		cmTemplate: {sortable: false},
		toolbarfilter : true,
		viewrecords : true,
		'cellEdit' : true,  //设置可编辑单元格
	    'cellsubmit' : 'clientArray',  //不进行ajax提交
	    rowNum:20,
		rowList:[20,50,100],
		loadonce : false,
		'afterEditCell' : afterEditCellFn,
		pager: '#gridPager',
		pagerpos:'center',	//指定分页栏的位置
		pgbuttons:true, //是否显示翻页按钮
		pginput: true,	//是否显示跳转页面的输入框
			
        colNames: ['序号', 'gdsInfoId', 'receiptId', '操作', '商品编号 ','商品名称','规格代码','规格名称' ,'属性', '可出库数量', '数量', '库位编码', '库位编码id', '备注'],
        colModel: [{ name: 'id', index: 'id',  hidden: true },
        { name: 'gdsInfoId', index: 'gdsInfoId', hidden: true     },
        { name: 'receiptId',  index: 'receiptId',    hidden: true     },
        { name: 'act',          index: 'act',  hidden:  $("input[name='id']").val()>0?true:false,   width:60 },
        { name: 'gdsInfoDetail',            index: 'gdsInfoDetail',            width: 250,
            formatter: function(cellvalue, options, rowObject) {
                return "<input value='" + cellvalue + "' width='300px' class='bindSelect' name='gdsInfoDetail' id=gdsShowInfo_" + options.rowId + ">";
            }
        },
        { name: 'gdsName',index: 'gdsName'},
        { name: 'skuOuterId',index: 'skuOuterId',width:80},
        { name: 'gdsFormat',         index: 'gdsFormat'  ,width:100   },
        { name: 'gdsInfoFormat',         index: 'gdsInfoFormat',width:80    },
        { name: 'curQty',    index: 'curQty'  ,width:80    },
        { name: 'amount',   index: 'amount',  editable: true,width:60   },
        {name: 'warehousePositionsName', index: 'warehousePositionsName', width: 200,
            formatter: function(cellvalue, options, rowObject) {
                return "<input value='" + cellvalue + "' width='160px' class='bindSelect' name='warehousePositionsName' id=warehousePositionsName_" + options.rowId + ">";
            }
        },
        { name: 'warehousePositionsId',   index: 'warehousePositionsId', hidden: true      },
        { name: 'remarks',index: 'remarks', editable: true        }
      
        ],
       
        gridComplete: function() {
            var ids = jQuery("#blackId").jqGrid('getDataIDs');
            for (var i = 0; i < ids.length; i++) {
                var rowId = ids[i];
                // 设置可以编辑
                var addBtn = "<div class='operating'><a href='javascript:void(0);' onclick='addCol();' class='operating-plus' title='新增行'></a>";
                addBtn += "<a href='javascript:' class='operating-trash' onclick=\"delBtnFn('" + rowId + "');\" title='删除'></a></div>";
                
                var curQty = "<a href='javascript:'  onclick=\"getQyt('" + rowId + "');\" >查看</a>"
                jQuery("#blackId").jqGrid('setRowData', ids[i], {
                    act: addBtn,curQty:curQty
                });
            }
            // 初始化计算jqgrid宽度
           // $("#blackId").setGridWidth($('.wrap-table').width());
           
        },
        loadComplete: function(a) {
           
        }
		    });

    // 业务时间
    $("#datepicker")
	    .datepicker(
		    {
			showOn : "button",

			buttonImage : base
				+ "/dinghao/template/front/js/elem/jqueryui/css/images/calendar.gif",
			buttonImageOnly : true
		    });
    
    
    // 自动生成编号
    // 单据初始化
    var options = {};
    options.params = {
	"nuType" : "06"
    };
    // 没有订单编号 自动生成
  	options.url = base + "/template/baseNumber/findBaseNumberNext.jhtml";
	options.successCallback = successBackFn;
	oms.ajaxCall(options);
	function successBackFn(data) {
	    $("#receiptCodeShow").html(data.data)
	    $("#receiptCode").val(data.data)
	}

    /**
     * 弹出供应商
     */
    $("#remarks").on(
	    "click",
	    function() {
		oms.s_addPop("供应商选择", base + "/template/custInfo/get_custinfomgr.jhtml",
			"", 1000, 480);
	    });
    addCol('1');
    // 浏览器大小发生变化的时候，重新计算jqgrid的宽度
    $(window).resize(function() {
    	oms.grid.mdetailconWidth("purOrderGridId");
    });

});// jquery 结束

function gridDataisPerfect() {
    var purData = $("#blackId").getRowData();
    for ( var i in purData) {
	var row = purData[i];
	if ($.trim(row.gdsInfoId) == "" || $.trim(row.gdsInfoId) == undefined) {
	    oms.tooltip("请选择商品信息!", "error");
	    return false;
	}
	if ($.trim(row.warehousePositionsId) == ""
		|| $.trim(row.warehousePositionsId) == undefined) {
	    oms.tooltip("请选择库位信息!", "error");
	    return false;
	}
	if ($.trim(row.receiptId) == 0) {
	    oms.tooltip("请完善采入库单相关信息的录入!", "error");
	    return false;
	}
    }
    return true;
}
/**
 * ****************************************************************************
 * 函数名称: <ATFunc>addCol</ATFunc> 函数功能: <ATFuncDesc>新增行</ATFuncDesc> 开发人员:
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
	id : "",
	gdsInfoId : "",
	receiptId : "-1",
	gdsInfoDetail : "",
	gdsInfoFormat : "",
	curQty : "",
	amount : "",
	warehousePositionsName : "",
	warehousePositionsId : "",
	remarks : ""
    } ];
    // 新增一行
    jQuery("#blackId").jqGrid('addRowData', '', defaultData, "last");
    // 商品添加智能提示
    var gdsInfoDetail = $("#blackId").find("tr").last().find(
	    "input[name='gdsInfoDetail']");
    bindSelect2(gdsInfoDetail);
    var warehousePositionsName = $("#blackId").find("tr").last().find(
	    "input[name='warehousePositionsName']");
    bindWarehousePositionsName(warehousePositionsName);
}

function bindSelect2(e) {
    e.select2({
	placeholder : "请输入商品编号/名称",
	minimumInputLength : 1,
	multiple : false,
	width : '200',
	formatResult : function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
	    return result.gdsShowInfo;
	},
	formatSelection : function(item) {// 选中后显示在文本框中的值
	    var rowId = $(e).attr("id").split("_")[1];
	    var attbs = new Array();
	    var gdsAttbs = " ";
	    if (item.attbs != "" && item.attbs != "undefined"
		    && typeof (item.attbs) != "undefined") {
		attbs = item.attbs.split(";");
		for ( var i in attbs) {
		    gdsAttbs += ";" + attbs[i].split(":")[2] + ":"
			    + attbs[i].split(":")[3];
		}
		gdsAttbs = gdsAttbs.substring(2, gdsAttbs.length);
	    }
	    jQuery("#blackId").setCell(rowId, "gdsInfoFormat", gdsAttbs);
	    jQuery("#blackId").setCell(rowId, "gdsFormat", item.gdsFormat);
	    jQuery("#blackId").setCell(rowId, "gdsName", item.gdsName);
	    jQuery("#blackId").setCell(rowId, "skuOuterId", item.skuOuterId==null?"":item.skuOuterId);

		jQuery("#blackId").setCell(rowId, "gdsInfoId", item.id);
	    $("#gdsShowInfo_" + rowId).val(item.gdsShowInfo);
	    jQuery("#blackId").setCell(rowId, "gdsShowInfo", item.gdsShowInfo);
	    return item.gdsNo ;
	},
	dropdownCssClass : "bigdrop", // apply css that makes the dropdown
	escapeMarkup : function(m) {
	    return m;
	},
	ajax : {
	    url : base + "/template/gdsMgr/findGdsInfoForGrid.jhtml",
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
	allowClear : true
    // 单选
    });
}
/*******************************************************************************
 * 函数名称: <ATFunc>delBtnFn</ATFunc> 函数功能: <ATFuncDesc>删除事件</ATFuncDesc> 输入参数: 返
 * 回 值: 开发人员: helong 修改记录:
 ******************************************************************************/
function delBtnFn(rowId) {
    // 取得所有id
    var ids = jQuery("#blackId").jqGrid('getDataIDs');
    var size = ids.length;
    jQuery("#blackId").delRowData(rowId);
    if (size == 1) {
	addCol();
    }
}
/**
 * 获取库位编码
 */
// 智能提示
function bindWarehousePositionsName(e) {
    $(e)
	    .select2(
		    {
			placeholder : "请输库位名称",
			minimumInputLength : 1,
			multiple : false,
			width : '160',
			formatResult : function(result, container, query,
				escapeMarkup) { // 下拉框显示的值(待选区)
			    return result.warehousePositionsCode;
			},
			formatSelection : function(item) {// 选中后显示在文本框中的值
			    var rowId = $(e).attr("id").split("_")[1];
			    jQuery("#blackId").setCell(rowId,
				    "warehousePositionsId", item.id);
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
				    dhWarehousePositions : $("#warehouseId").val(),
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
}
function savePurOrder() {
	 // 入库单ID (新增或修改)
    if (!gridDataisPerfect()) {
        return;
    }
    // 保存入库单信息
   // $("#myForm").submit();
    var receiptVo = {};
    receiptVo["id"] = $("#receiptId").val();
    
    receiptVo["receiptCode"] = $("#receiptCode").val();
    receiptVo["receiptType"] = $("#receiptType").val();
    receiptVo["warehouseId"] = $("#warehouseId").val();
    receiptVo["providerId"] = $("#providerId").val();
    receiptVo["serviceType"] = $("#serviceType").val();
    receiptVo["serviceNum"] = $("#serviceNum").val();
    receiptVo["handledPerson"] = $("#handledPerson").val();
    receiptVo["createDate"] = $("#datepicker").val();
    
    var griddata = $("#blackId").getRowData();
    
    for (var i in griddata) {
    
    	receiptVo["receiptDetailVos[" + i + "].id"] = griddata[i].id;
    	receiptVo["receiptDetailVos[" + i + "].gdsInfoId"] = griddata[i].gdsInfoId;
    	receiptVo["receiptDetailVos[" + i + "].receiptId"] = 0;
    	receiptVo["receiptDetailVos[" + i + "].gdsInfoFormat"] = griddata[i].gdsInfoFormat;
   
        receiptVo["receiptDetailVos[" + i + "].amount"] = griddata[i].amount;
        receiptVo["receiptDetailVos[" + i + "].warehousePositionsId"] = griddata[i].warehousePositionsId;
        receiptVo["receiptDetailVos[" + i + "].remarks"] = griddata[i].remarks;
    }
  

    var options = {};
    options.params = receiptVo;
    options.url = base+"/template/receipt/saveReceipt.jhtml";
    options.successCallback = savePurOrderBackFn;
    oms.ajaxCall(options);
}

// 点击空白处，保存正在编辑的数据
$('html').bind('click', function(e) { // 用于点击其他地方保存正在编辑状态下的行
    if (lasterSel != "") {
	if ($(e.target).closest('#blackId').length == 0) {
	    $("#blackId").jqGrid("saveCell", curRow, curCol);
	    lasterSel = "";
	}
    }
    if ($(e.target).attr("id") == "savePurOrder") {
    	savePurOrder();
    }
});
function afterEditCellFn(rowid, cellname, value, iRow, iCol) {
    curRow = iRow;
    curCol = iCol;
    lasterSel = rowid;
}

function savePurOrderBackFn(data) {
	oms.tooltip(data.errMsg , "succeed");
	artTabs({
		 closeTab : true
	});
}

function getQyt(rowid){
	
	var gid = $("#blackId").getCell(rowid , "gdsInfoId");
	var locId = $("#blackId").getCell(rowid , "warehousePositionsId");
	var stockId =  $("#warehouseId").val();
	
	if (stockId =="" || stockId == null  )
	{
		oms.tooltip("请选择仓库！", "error");
		return;
	}
	if (gid =="" || gid == null  )
	{
		oms.tooltip("请选择商品！", "error");
		return;
	}

	 var options = {};
	    options.params = {
	    		"stockId" : stockId,
	    		"gdsId" : gid,
	    		"locId" : locId	    		    		
	    };
	    // 没有订单编号 自动生成
	  	options.url = base_template + '/locstock/detail_locstock.jhtml';
		options.successCallback = function(data){
			var dataList = data.data.list;
			  var curQty = "<a href='javascript:'  onclick=\"getQyt('" + rowid + "');\" title=\"点击获取库存\" >"+dataList[0].totalQty+"</a>"
		    jQuery("#blackId").setCell(rowid, "curQty", curQty);
		 };
		oms.ajaxCall(options);
		
	
}