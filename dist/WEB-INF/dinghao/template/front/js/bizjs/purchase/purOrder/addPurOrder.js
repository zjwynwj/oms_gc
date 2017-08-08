var curRow = null;  //选择的全局变量,标记当前选择的是哪一行
var curCol = null;  //选择的全局变量,标记当前选择的是哪一列
var lasterSel ;     //保存最后选择的一行的行id

$("#remarks").dblclick(function(){

		 oms.s_addPop("供应商选择", base + "/template/custInfo/get_custinfomgr.jhtml", "", 1000, 490);

});

$(function(){
	
	//单据初始化	
	var options = {};
    options.params = {"nuType":"03"};
	options.url = base+"/template/baseNumber/findBaseNumberNext.jhtml";
	options.successCallback = numberBackFn;
	oms.ajaxCall(options);
	
	//业务日期初始化
	$("#busiDate").val(oms.formatDate(new Date()));
	//应付日期初始化
	var t=new Date();
	var newDay = new Date(t.getFullYear(),t.getMonth(),(t.getDate()+30));
	var dueDate = oms.formatDate(newDay);
	$("#dueDate").val(dueDate);
	
	//点击空白处，保存正在编辑的数据
	$('html').bind('click', function(e) { //用于点击其他地方保存正在编辑状态下的行
	    if ( lasterSel != "" ) {
	        if($(e.target).closest('#purOrderGridId').length == 0) {
	            $("#purOrderGridId").jqGrid("saveCell", curRow, curCol);
	            lasterSel = ""; 
	        } 
	    } 
	    var purData = $("#purOrderGridId").getRowData();
	    var money = 0;
		for(var i in purData){
			var row = purData[i];
			money += parseFloat(row.purMoney);
		}
		$("#payMoney").val(money);
		formatMoney($("#payMoney"),2);
		
		var target = e.target;
		if($(target).attr("id")=="save")
		{
			savePurOrder();
		}

	});
	
	// 时间js相关调用
	$("#busiDate").datepicker({
		showOn: "button", 
		buttonImage: template_base +"/js/elem/jqueryui/css/images/calendar.gif",
		buttonImageOnly: true  
	}); 
	$("#dueDate").datepicker({
		showOn: "button", 
		buttonImage: template_base +"/js/elem/jqueryui/css/images/calendar.gif",
		buttonImageOnly: true  
	});
	//判断是否通过采购建议单生成
	var suggestGoodsids = $("#suggestGoodsids").val();
	
	if (suggestGoodsids == "" ){
		pageInit();
		addBtnFn();	
	}
	else{
		
		pageInitByIds();
	}
	
	//浏览器大小发生变化的时候，重新计算jqgrid的宽度
	$(window).resize(function(){
		oms.grid.mdetailconWidth("purOrderGridId");
	});
	
});

function pageInit(){
	jQuery("#purOrderGridId").jqGrid({
		data : [],
		datatype : "local",
		height : 'auto',
		colNames: ['gdsId','操作', '商品编号','商品名称','规格代码','规格','属性','数量', '采购单价(元)', '金额(元)'], 
		colModel: [
			{name:'gdsId',index:'gdsId',sortable:false ,hidden:true},
			{name:'act',index:'act',sortable:false ,width:60},
			{name:'gdsShowInfo',index:'gdsShowInfo',sortable:false ,width:150,
				formatter:function(cellvalue, options, rowObject){
                	return "<input  width='150px' class='bindSelect' name='gdsInfoSelect2' id=gdsShowInfo_"+options.rowId+">";
                }
		    },
			{name:'gdsName',index:'gdsName',sortable:false,width:150},
			{name:'skuOuterId',index:'skuOuterId',sortable:false,width:80},
			{name:'gdsFormat',index:'gdsFormat',sortable:false,width:100},
			{name:'attbs',index:'attbs',sortable:false},
			{name:'purAmount',index:'purAmount',sortable:false ,editable:true,align:'right',formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 0, prefix: "", suffix:"", defaulValue: 0}},
			{name:'purPrice',index:'purPrice',sortable:false ,editable:true,align:'right',formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaulValue: 0}},
			{name:'purMoney',index:'purMoney',sortable:false,align:'right',formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaulValue: 0}},
		], 
		rowNum : 10,
		rowList : [10, 20, 30],
		sortname : 'no',
		toolbarfilter : true,
		viewrecords : true,
		sortorder : "no",
		rownumbers: true,
		cmTemplate: {sortable: false},
		'cellEdit' : true,  //设置可编辑单元格
	    'cellsubmit' : 'clientArray',  //不进行ajax提交
	    'afterEditCell' : afterEditCellFn,
		gridComplete : function() {
			var ids = jQuery("#purOrderGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				
				var rowId = ids[i];
				var addBtn = "<div class='operating'><a href='javascript:' class='operating-plus' title='增加' onclick=\"addBtnFn();\"></a>";
				addBtn += "<a href='javascript:' class='operating-trash' title='删除' onclick=\"delBtnFn('" + rowId + "');\"></a></div>";
				jQuery("#purOrderGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		},
		autowidth : true
	});
}
function pageInitByIds(){
	
	jQuery("#purOrderGridId").jqGrid({
		data : [],
		url: base + '/template/purOrder/queryDetailListBySugIds.jhtml',
		datatype : "json",
		postData: {"suggestGoodsids": $("#suggestGoodsids").val()},
		mtype: 'POST', 
		height : 'auto',
		colNames: ['gdsId','操作', '商品编号','商品名称','规格代码','规格','属性','数量', '采购单价(元)', '金额(元)'], 
		colModel: [
			{name:'gdsId',index:'gdsId',sortable:false ,hidden:true},
			{name:'act',index:'act',sortable:false ,width:60},
			{name:'gdsShowInfo',index:'gdsShowInfo',sortable:false ,width:150,
				formatter:function(cellvalue, options, rowObject){
                	return "<input value='"+cellvalue+"' width='150px' class='bindSelect' name='gdsInfoSelect2' id=gdsShowInfo_"+options.rowId+">";
                }
		    },
			{name:'gdsName',index:'gdsName',sortable:false,width:150},
			{name:'skuOuterId',index:'skuOuterId',sortable:false,width:80},
			{name:'gdsFormat',index:'gdsFormat',sortable:false,width:100},
			{name:'attbs',index:'attbs',sortable:false,width:100},
			{name:'purAmount',index:'purAmount',sortable:false ,editable:true,align:'right'},
			{name:'purPrice',index:'purPrice',sortable:false ,editable:true,align:'right',formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaulValue: 0}},
			{name:'purMoney',index:'purMoney',sortable:false,align:'right',formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaulValue: 0}},
		], 
		rowNum : 10,
		rowList : [10, 20, 30],
		sortname : 'no',
		toolbarfilter : true,
		viewrecords : true,
		sortorder : "no",
		rownumbers: true,
		cmTemplate: {sortable: false},
		'cellEdit' : true,  //设置可编辑单元格
	    'cellsubmit' : 'clientArray',  //不进行ajax提交
	    'afterEditCell' : afterEditCellFn,
	    jsonReader:{  //返回数据格式设置
	    	root: "data",  
			repeatitems : false
		},
		gridComplete : function(a) {
			var ids = jQuery("#purOrderGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var rowId = ids[i];
				var addBtn = "<div class='operating'><a href='javascript:' class='operating-plus' title='增加' onclick=\"addBtnFn();\"></a>";
				addBtn += "<a href='javascript:' class='operating-trash' title='删除' onclick=\"delBtnFn('" + rowId + "');\"></a></div>";
				jQuery("#purOrderGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		},
		loadComplete :function(a) {
			var ids = jQuery("#purOrderGridId").jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var gdsShowInfoObj =$("#"+$($("#purOrderGridId").getCell(ids[i] , "gdsShowInfo")).attr("id"));
				bindSelect2(gdsShowInfoObj);
			}
		},
		autowidth : true
	});
}
function afterEditCellFn(rowid, cellname, value, iRow, iCol) {
	curRow = iRow;
	curCol = iCol;
	lasterSel = rowid;
	$('#purOrderGridId').find('tr').eq(curRow).find('td').eq(curCol).find('input').blur(function(){
		var amount = (typeof($('#purOrderGridId').find('tr').eq(curRow).find('td').eq(8).find('input').val()) == 'undefined') ? $('#purOrderGridId').find('tr').eq(curRow).find('td').eq(8).text() : $('#purOrderGridId').find('tr').eq(curRow).find('td').eq(8).find('input').val();
		var price = (typeof($('#purOrderGridId').find('tr').eq(curRow).find('td').eq(9).find('input').val()) == 'undefined') ? $('#purOrderGridId').find('tr').eq(curRow).find('td').eq(9).text() : $('#purOrderGridId').find('tr').eq(curRow).find('td').eq(9).find('input').val();
		amount = parseFloat(amount.replace(/[^\d\.-]/g, ""));
		price = parseFloat(price.replace(/[^\d\.-]/g, ""));
		jQuery("#purOrderGridId").setCell(rowid,"purMoney",amount*price);
	});
	
	if(curCol ==8){
		var item = $('#purOrderGridId').find('tr').eq(curRow).find('td').eq(curCol).find('input');
		item.attr("maxlength" , 8);
		bindJqgridJustNumberEvent(item);
	}
	
	if(curCol==9){
		var item = $('#purOrderGridId').find('tr').eq(curRow).find('td').eq(curCol).find('input');
		bindJqgridMoneyAndAmountEvent(item);
	}
}

function numberBackFn(data){
	$("#purNo").val(data.data);
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
	var purData = $("#purOrderGridId").getRowData();
	for(var i in purData){
		var row = purData[i];
		if($.trim(row.gdsId) == "" || $.trim(row.gdsId) == undefined){
			oms.tooltip("请选择商品信息!","error");
			return false;
		}
		if($.trim(row.purMoney) == 0){
			oms.tooltip("请完善采购记录的采购数量或采购单价录入!","error");
			return false;
		}
	}
	return true;
}

/******************************************************************************
函数名称:   <ATFunc>addBtnFn</ATFunc>
函数功能:   <ATFuncDesc>表格新增事件</ATFuncDesc>       
输入参数:   rowId  选中的id 
返 回 值:   
开发人员:   helong
修改记录:
******************************************************************************/
function addBtnFn() {
	if(!gridDataisPerfect()){
		return;
	}
	var defaultData = [{gdsShowInfo:"",attbs:"",purAmount:"1.00",calName:"",purPrice:"",purMoney:"0"}];
	jQuery("#purOrderGridId").jqGrid('addRowData',1,defaultData,"last");
	
	
    var e = $("#purOrderGridId").find("tr").last().find(".bindSelect");
	
	bindSelect2(e);
	
}

/******************************************************************************
函数名称:   <ATFunc>delBtnFn</ATFunc>
函数功能:   <ATFuncDesc>删除事件</ATFuncDesc>       
输入参数:   
返 回 值:    
开发人员:   helong
修改记录:
******************************************************************************/
function delBtnFn(rowId) {
	//取得所有id
	var ids = jQuery("#purOrderGridId").jqGrid('getDataIDs');
	var size = ids.length;
	jQuery("#purOrderGridId").delRowData(rowId);
	if(size==1) {
		addBtnFn();
	}
}

function verifySubmitData(){
	if($.trim($("#providerId").val()) == ""){
		oms.tooltip("请选择供应商!","error");
		return false;
	}
	return gridDataisPerfect();
}

function savePurOrder(){
	if(!verifySubmitData()){
		return;
	}
	var purOrderDataVo = {};
	purOrderDataVo["purOrderVo.purNo"] = $("#purNo").val();
	purOrderDataVo["purOrderVo.custId"] = $("#providerId").val();
	purOrderDataVo["purOrderVo.purType"] = $("#purType").val();
	purOrderDataVo["purOrderVo.busiDate"] = $("#busiDate").val();
	purOrderDataVo["purOrderVo.payMoney"] = $("#payMoney").attr("realMoney");
	purOrderDataVo["purOrderVo.dueDate"] = $("#dueDate").val();
	
	var griddata = $("#purOrderGridId").getRowData();
	for(var i in griddata){
		purOrderDataVo["purOrderDetailVoList["+i+"].gdsId"] = griddata[i].gdsId;
		purOrderDataVo["purOrderDetailVoList["+i+"].purPrice"] = griddata[i].purPrice;
		purOrderDataVo["purOrderDetailVoList["+i+"].purAmount"] = griddata[i].purAmount;
		purOrderDataVo["purOrderDetailVoList["+i+"].purMoney"] = griddata[i].purMoney;
		purOrderDataVo["purOrderDetailVoList["+i+"].purInAmount"] = "0.00";
		purOrderDataVo["purOrderDetailVoList["+i+"].purInMoney"] = "0.00";
		purOrderDataVo["purOrderDetailVoList["+i+"].purOutAmount"] = "0.00";
		purOrderDataVo["purOrderDetailVoList["+i+"].purOutMoney"] = "0.00";
		purOrderDataVo["purOrderDetailVoList["+i+"].purRealAmount"] = "0.00";
		purOrderDataVo["purOrderDetailVoList["+i+"].purRealMoney"] = "0.00";
	}
	var options = {};
    options.params = purOrderDataVo;
	options.url = base+"/template/purOrder/addPurOrder.jhtml";
	options.successCallback = savePurOrderBackFn;
	oms.ajaxCall(options);
}

function savePurOrderBackFn(data){
	oms.tooltip(data.errMsg,"succeed");
	/*
	artTabs({
	    toTab: {id : "purMgr"},
	    isRefresh: true
	});
	*/
}


function bindSelect2(e){
	var eid = e.attr("id").split("_")[1];
	e.select2({
		placeholder : "请输入商品编号\名称",
		minimumInputLength : 1,
		multiple : false,
		width : '150',
		formatResult : function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
			return result.gdsShowInfo ;
		},
		formatSelection : function(item) {// 选中后显示在文本框中的值
			
			 var attbs = new Array();
	            var gdsAttbs = " ";
	            if (item.attbs != "" && item.attbs != "undefined" && typeof(item.attbs) != "undefined") {
	                attbs = item.attbs.split(";");
	                for (var i in attbs) {
	                    gdsAttbs += ";" + attbs[i].split(":")[2] + ":" + attbs[i].split(":")[3];
	                }
	                gdsAttbs = gdsAttbs.substring(2, gdsAttbs.length);
	            }
	            
	        	jQuery("#purOrderGridId").setCell(eid,"attbs",gdsAttbs);
	        	jQuery("#purOrderGridId").setCell(eid,"skuOuterId",item.skuOuterId);
	        	jQuery("#purOrderGridId").setCell(eid,"gdsName",item.gdsName);
	        	jQuery("#purOrderGridId").setCell(eid,"gdsId",item.id);
				jQuery("#purOrderGridId").setCell(eid,"gdsFormat",item.gdsFormat);
			return item.gdsNo;
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
		jQuery("#purOrderGridId").setCell(rowId,"gdsId",gdsId);
		jQuery("#purOrderGridId").setCell(rowId,"attbs",gdsAttbs);
	});
	*/
}