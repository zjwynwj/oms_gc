var curRow = null;  //选择的全局变量,标记当前选择的是哪一行
var curCol = null;  //选择的全局变量,标记当前选择的是哪一列
var lasterSel ;     //保存最后选择的一行的行id

//物流选择下拉
/*
$('#expressName').select2({
	placeholder : "请输入物流公司名称",
	minimumInputLength : 1,
	multiple : false,
	width : '200',
	formatResult : function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
		return result.name ;
	},
	formatSelection : function(item) {// 选中后显示在文本框中的值
		$('#expid').val(item.id);
		return item.name;
	},
	dropdownCssClass : "bigdrop", // apply css that makes the dropdown
	// taller
	escapeMarkup : function(m) {
		return m;
	},
	ajax : {
		url : base+"/template/expressMgr/findExpressInfoGrid.jhtml",
		delay : 250,
		dataType : 'json',
		data : function(term, page) {
			return {
				keyWord : term,// 汉字处理方式
				actived : "1",
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

$("#expressName").unbind("change");
$("#expressName").bind("change" , function(){
	$("#expid").val($(this).val());
});
*/
$(function() {
	$.areaSelect({
		id:'district'
	});
	//$("#expressName").prev("div").find(".select2-chosen").text($("#expressName").val());
	$("#mark").val($("#markVal").val());
	
	bindMoneyAndAmountEvent();
	
	//点击空白处，保存正在编辑的数据
	$('html').bind('click', function(e) { //用于点击其他地方保存正在编辑状态下的行
	    if ( lasterSel != "" ) {
	        if($(e.target).closest('#orderItemGridId').length == 0) {
	            $("#orderItemGridId").jqGrid("saveCell", curRow, curCol);
	            lasterSel = ""; 
	        } 
	    } 
	    var target = e.target;
		if($(target).attr("id")=="save")
		{
			saveOrder();
		}

	});
	
	//订单表格
	jQuery("#orderItemGridId").jqGrid({
		datatype: "json",
		url: base + '/template/orderMgr/querySalesOrderItemList.jhtml',
		mtype: 'POST', 
		postData : {"id":$("#id").val()},
		height : 'auto',
		colNames: ['序号','操作','gdsId', '商品编码', '商品名称', '规格代码', '规格名称', '属性', '商品数量'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'act',index:'act',sortable:true ,width:40,hidden:true},
			{name:'gdsId',index:'gdsId', hidden:true},

			{name:'gdsNo',index:'gdsNo',sortable:false ,width:200,
				formatter:function(cellvalue, options, rowObject){
                	return "<input value='"+rowObject.gdsNo+"' width='200px' class='bindSelect' name='gdsInfoSelect2' id=gdsShowInfo_"+options.rowId+">";
                }
			},
			{name:'gdsName',index:'gdsName', width:200},
			{name:'skuOuterId',index:'skuOuterId', width:80},
		
		   {name:'gdsFormat',index:'gdsFormat',width:80},
			{name:'attbs',index:'attbs', width:80},
			{name:'qty',index:'qty',editable:true,width:60}
        ], 
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
		'cellEdit' : true,  //设置可编辑单元格
	    'cellsubmit' : 'clientArray',  //不进行ajax提交
	    'afterEditCell' : afterEditCellFn,
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
		gridComplete : function() {
			var ids = jQuery("#orderItemGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var cl = ids[i];
				var addBtn = "<div class='operating'><a href='javascript:' class='operating-plus' title='增加' onclick=\"addGdsRow();\"></a>";
				
				addBtn += "<a href='javascript:' class='operating-trash' title='删除' onclick=\"delItem('"+cl+"')\"></a></div>";
				jQuery("#orderItemGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		},
		loadComplete :function(a) {
			var ids = jQuery("#orderItemGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var gdsShowInfoObj =$("#"+$($("#orderItemGridId").getCell(ids[i] , "gdsNo")).attr("id"));
				bindSelect2(gdsShowInfoObj);
				$(gdsShowInfoObj).prev("div").find(".select2-chosen").text($(gdsShowInfoObj).val());
			}
		},
		multiselect : true
	});
	
	//grid根据页面缩放  改变大小
	$(window).resize(function(){
		oms.grid.mdetailconWidth("orderItemGridId");	
	})
});

function delItem(rowId){
	
	$.artDialog({
		title:'提示',
		content:'确定要删除吗?',
		ok:function(){
			//取得所有id
			var ids = jQuery("#orderItemGridId").jqGrid('getDataIDs');
			var size = ids.length;
			jQuery("#orderItemGridId").delRowData(rowId);
		}
	})
}

function afterEditCellFn(rowid, cellname, value, iRow, iCol) {
	curRow = iRow;
	curCol = iCol;
	lasterSel = rowid;
	if(curCol ==9){
		var item = $('#orderItemGridId').find('tr').eq(curRow).find('td').eq(curCol).find('input');
		item.attr("maxlength" , 8);
		bindJqgridJustNumberEvent(item);
	}
}

/******************************************************************************
函数名称:   <ATFunc>isPerfectData</ATFunc>
函数功能:   <ATFuncDesc>遍历整个表格，查看数据是否 录入完全</ATFuncDesc>       
输入参数:   
返 回 值:   boolean 是否完整数据 
开发人员:   helong
修改记录:
******************************************************************************/
function gridDataisPerfect(){
	var data = $("#orderItemGridId").getRowData();
	for(var i in data){
		var row = data[i];
		if($.trim(row.gdsId) == "" || $.trim(row.gdsId) == undefined){
			oms.tooltip("请选择商品信息!","error");
			return false;
		}
		if($.trim(row.qty) == 0){
			oms.tooltip("请输入商品数量!","error");
			return false;
		}
	}
	return true;
}

function addGdsRow(){
	if(!gridDataisPerfect()){
		return;
	}
	var defaultData = [{gdsId:"",gdsName:"",gdsNo:"",gdsFormat:"",qty:"0"}];
	jQuery("#orderItemGridId").jqGrid('addRowData',1,defaultData,"last");
	var e = $("#orderItemGridId").find("tr").last().find(".bindSelect");
	bindSelect2(e);
}

function bindSelect2(e){
	var eid = e.attr("id").split("_")[1];
	e.select2({
		placeholder : "请输入商品编号\名称",
		minimumInputLength : 1,
		multiple : false,
		width : '250',
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
			    jQuery("#orderItemGridId").setCell(rowId, "attbs", gdsAttbs);
			    jQuery("#orderItemGridId").setCell(rowId, "gdsFormat",item.gdsFormat==""?" ":item.gdsFormat);
			    jQuery("#orderItemGridId").setCell(rowId, "gdsName", item.gdsName);
			    jQuery("#orderItemGridId").setCell(rowId, "skuOuterId", item.skuOuterId==""?" ":item.skuOuterId);

				jQuery("#orderItemGridId").setCell(rowId, "gdsId", item.id);
			    $("#gdsShowInfo_" + rowId).val(item.gdsShowInfo);
			    jQuery("#orderItemGridId").setCell(rowId, "gdsShowInfo", item.gdsShowInfo);
			    return item.gdsNo ;
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
	/*
	e.prev("div").find(".select2-chosen").text(e.val());
	
	$("input[name=gdsInfoSelect2]").unbind("change");
	$("input[name=gdsInfoSelect2]").bind("change",function(){
		var rowId = $(this).attr("id").split("_")[1];
		var gdsId = $(this).val() || " ";
		var gdsShowInfo = $(this).prev("div").find(".select2-chosen").text().split("-")[0];
		var gdsName = gdsShowInfo;
		var gdsNo = $(this).prev("div").find(".select2-chosen").text().split("-")[1];
		var gdsFormat = $(this).prev("div").find(".select2-chosen").text().split("-")[2];
		var attbsInfo =   $(this).prev("div").find(".select2-chosen").text().split("-")[3];
		
		$(this).prev("div").find(".select2-chosen").text(gdsShowInfo);
		
		jQuery("#orderItemGridId").setCell(rowId,"gdsId",gdsId);
		if($.trim(gdsId) == ""){
			jQuery("#orderItemGridId").setCell(rowId,"gdsName"," ");
		}else{
			jQuery("#orderItemGridId").setCell(rowId,"gdsName",gdsName);
		}
		jQuery("#orderItemGridId").setCell(rowId,"gdsNo",gdsNo);
		if(gdsFormat != "undefined"){
			jQuery("#orderItemGridId").setCell(rowId,"gdsFormat",gdsFormat);
		}
		
		var attbs = new Array();
		var gdsAttbs =" ";
		if(attbsInfo != "" && attbsInfo  != "undefined" && typeof(attbsInfo) != "undefined"){
			attbs = attbsInfo.split(";");
			
			for(var i in attbs){
				gdsAttbs += ";"+attbs[i].split(":")[2]+":"+attbs[i].split(":")[3];
			}
			gdsAttbs = gdsAttbs.substring(2, gdsAttbs.length);
		}
		jQuery("#orderItemGridId").setCell(rowId,"attbs",gdsAttbs);
		
		
	});
	*/
}


function verifySubmitData(){
	if($.trim($("#expid").val()) == ""){
		oms.tooltip("请选择物流公司!","error");
		return false;
	}
	
	if($.trim($("#district").val()) == "请选择城市"){
		oms.tooltip("请选择收货地址!","error");
		return false;
	}
	
	if($.trim($("#address").val()) == ""){
		oms.tooltip("请填写详细的收货地址!","error");
		return false;
	}
	return gridDataisPerfect();
}

function saveOrder(){
	var data = $("#orderItemGridId").getRowData();
	if(data.length == 0){
		oms.tooltip("没有订单明细,请添加商品!","error");
		return false;
	}
	if(!verifySubmitData()){
		return;
	}
	var salesOrderDataVo = {};
	salesOrderDataVo["id"] = $("#id").val();
	salesOrderDataVo["expid"] = $("#expid").val();
	salesOrderDataVo["stockId"] = $("#stockId").val();
	salesOrderDataVo["mark"] = $("#mark").val();
	salesOrderDataVo["sellerMemo"] = $("#sellerMemo").val();
	var district = $("#district").val().split("-");
	
	salesOrderDataVo["prov"] = district[0];
	salesOrderDataVo["city"] = district[1];
	salesOrderDataVo["county"] = district[2];
	salesOrderDataVo["identityCard"] = $("#identityCard").val();
	salesOrderDataVo["recvmobile"] = $("#recvmobile").val();
	
	salesOrderDataVo["address"] = $("#address").val();
	
	var griddata = $("#orderItemGridId").getRowData();
	for(var i in griddata){
		salesOrderDataVo["itemList["+i+"].id"] = griddata[i].id;
		salesOrderDataVo["itemList["+i+"].gdsId"] = griddata[i].gdsId;
		salesOrderDataVo["itemList["+i+"].qty"] = griddata[i].qty;
		salesOrderDataVo["itemList["+i+"].isgift"] = "0";
		salesOrderDataVo["itemList["+i+"].gdsName"] = griddata[i].gdsName;
	}
	var options = {};
    options.params = salesOrderDataVo;
	options.url = base+"/template/orderMgr/saveModSalesOrder.jhtml";
	options.successCallback = function(data){
		oms.tooltip(data.errMsg,"succeed");
		/*
		artTabs({
		    toTab: {id : "orderAudit"},
		    isRefresh: true
		});
		*/
		artTabs({
		    toTab: {id : "modSalesOrder"},
		    isRefresh: true
		});
	}
	oms.ajaxCall(options);
}