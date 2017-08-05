var curRow = null;  //选择的全局变量,标记当前选择的是哪一行
var curCol = null;  //选择的全局变量,标记当前选择的是哪一列
var lasterSel ;     //保存最后选择的一行的行id

var maingridid = "#blankId";

$(function(){
		
   // 获取初始采购员列表
	$.ajax({
		url: base+"/template/custInfo/findUserList.jhtml",
		type: 'POST',
		data: {"offset":"0","rows":"20"},
		dataType: 'json',
	
		timeout: "300000",
		error: function(data){
			alert("查询数据失败");
		},
		success: function(data){
			var data_list = data.data.list	
			data_list.forEach(function(e){  
				e.id = e.id;
				e.text = e.username;
			})  
			$('#busiPerson').select2({
				width : '200',
				data:data_list
			});
			
			//pageInit(data.data);
		},
		async:true
	});
	

	
	//点击空白处，保存正在编辑的数据

	$('html').bind('click', function(e) { //用于点击其他地方保存正在编辑状态下的行
	    if ( lasterSel != "" ) {
	        if($(e.target).closest(maingridid).length == 0) {
	            $(maingridid).jqGrid("saveCell", curRow, curCol);
	            lasterSel = ""; 
	        } 
	    } 
	  
	    var purData = $(maingridid).getRowData();
		for(var i in purData){
			var row = purData[i];
			var rowid = parseFloat(row.id);
			var num = parseFloat(row.num);
			var price = parseFloat(row.price);
			jQuery(maingridid).setCell(rowid,"amount",num*price);
		}
		
		var target = e.target;
		if($(target).attr("id")=="save")
		{
			save();
			
		}
		if($(target).attr("id")=="addPurOrder")
		{
			addPurOrder();
		}
	    
		/*
		if($(target).attr("type")=="checkbox")
		{
			 
			if($(target).is(':checked')== true){
			   $(target).prop("checked",false);
			}
			else
			{
				$(target).prop("checked",true);
			}
				
		}
		*/
		
	});
	
	//////////////
	$("#busiDate").datepicker({
		showOn: "button", 
		buttonImage: template_base+"/js/elem/jqueryui/css/images/calendar.gif",
		buttonImageOnly: true  
	});
	
	$("#planDate").datepicker({
		showOn: "button", 
		buttonImage: template_base+"/js/elem/jqueryui/css/images/calendar.gif",
		buttonImageOnly: true  
	});
	
		
	pageInit();
	//addLine(0);
	
	//浏览器大小发生变化的时候，重新计算jqgrid的宽度
	$(window).resize(function(){
		oms.grid.mdetailconWidth("blankId");
	});
	
	
})


function addLine(){
	
	//var count = jQuery(maingridid).jqGrid('getGridParam','records');
	var defaultData = [{id:0,spinfo:"请输入商品编码",attbs:"",supplierId:"",supplierName:"",gdsShowInfo:""}];
	//新增一行
	jQuery(maingridid).jqGrid('addRowData','',defaultData);
	
	var e = $(maingridid).find("tr").last().find(".bindSelect");
	bindSelect2(e);
	
	var s = $(maingridid).find("tr").last().find(".bindSupplierSelect");
	bindSupplierSelect2(s)
}



function pageInit(){
   
  jQuery(maingridid).jqGrid({
	    data : [],
		url: base + '/template/suggest/querySuggestDetailList.jhtml',
		datatype : "json",
		postData: {"id":$("#suggestId").val()},
		mtype: 'POST', 
		height : 'auto',
		colNames:['序号','商品id','操作','商品编号','商品名称',  '规格代码','规格','属性','供应商id', '供应商','数量','采购单价','采购金额'],
		colModel:[
		     {name:'id',index:'id',sortable:false ,width:25,hidden:true},
		    {name:'gdsId',index:'gdsId',sortable:false ,hidden:true,},
		    
			{name:'act',index:'act',sortable:false,width:80},
			{name:'gdsShowInfo',index:'gdsShowInfo',sortable:false ,width:160,
				formatter:function(cellvalue, options, rowObject){
                	return "<input  value='"+cellvalue+"' width='150px' class='bindSelect' name='gdsInfoSelect2' id=gdsShowInfo_"+options.rowId+">";
                }
		    },
		    {name:'gdsName',index:'gdsName',width:150},
		    {name:'skuOuterId',index:'skuOuterId',width:80},
		    {name:'gdsFormat',index:'gdsFormat',width:100},
		    {name:'attbs',index:'attbs',width:100},
			{name:'supplierId',index:'supplierId', align:"right",hidden:true},
			{name:'supplierName',index:'supplierName', align:"right" ,width:200,
				formatter:function(cellvalue, options, rowObject){
                	return "<input  value='"+cellvalue+"' width='300px' class='bindSupplierSelect' name='supplierSelect2' id=Supplier_"+options.rowId+">";
                }
			},
			{name:'num',index:'num', align:"right",sorttype:"float",editable:true},		
			{name:'price',hidden:true,index:'price',align:"right",sorttype:"float",editable:true,formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaulValue: 0}},		
			{name:'amount',hidden:true,index:'amount', align:"right",editable:false,formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaulValue: 0}}		
		],
		
		autowidth: true,
		sortorder : "no",
		rownumbers: true,
		cmTemplate: {sortable: false},
		toolbarfilter : true,
		viewrecords : true,
		'cellEdit' : false,  //设置可编辑单元格
	    'cellsubmit' : 'clientArray',  //不进行ajax提交
	    'afterEditCell' : afterEditCellFn,
	    'ondblClickRow' : function(rowid,iRow,iCol,e){
	    	if ($(maingridid).jqGrid('getGridParam','cellEdit')==false)
	    	    $(maingridid).setGridParam({cellEdit:true});
  			
  				
	    },
		 rowNum:1000,
		rowList:[1000],
		pager : 'pageId',
		pagerpos:'center',	//指定分页栏的位置
		pgbuttons:true, //是否显示翻页按钮
		pginput: true,	//是否显示跳转页面的输入框
		jsonReader:{  //返回数据格式设置
			root: "data",  
			repeatitems : false
		},
		
		loadComplete:function(){
			var ids = jQuery(maingridid).jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var gdsShowInfoObj =$("#"+$($(maingridid).getCell(ids[i] , "gdsShowInfo")).attr("id"));
				bindSelect2(gdsShowInfoObj);
				 $(gdsShowInfoObj).prev("div").find(".select2-chosen").text($(gdsShowInfoObj).val());
				var supperObj =$("#"+$($(maingridid).getCell(ids[i] , "supplierName")).attr("id"));
				
				bindSupplierSelect2(supperObj);
			}
		},
		gridComplete : function() {
			var ids = jQuery(maingridid).jqGrid('getDataIDs');
			for (var i = 0; i < ids.length; i++) {
				var rowId = ids[i];
				addBtn = "<div class='operating'><a href='javascript:addLine()' class='operating-plus' title='新增行'></a>";
				addBtn += "<a href='javascript:' class='operating-trash' title='删除'onclick=\"delBtnFn('" + rowId + "');\"></a>" +
				     			"</div>";
				jQuery(maingridid).jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
			    
		},
		multiselect: true		
	})
}

function save(){
	  $(maingridid).jqGrid("saveCell", curRow, curCol);
	 $(maingridid).setGridParam({cellEdit:false});
	 
	var purSuggestDataVo = {};
	purSuggestDataVo["purSuggestVo.busiPerson"]= $.trim($("#busiPerson").val());
	purSuggestDataVo["purSuggestVo.busiDate"]= new Date($.trim($("#busiDate").val()) );
	purSuggestDataVo["purSuggestVo.planDate"]=new Date($.trim($("#planDate").val()));
	purSuggestDataVo["purSuggestVo.suggestNo"]=$("#suggestNo").val();
	purSuggestDataVo["purSuggestVo.id"]=$("#suggestId").val();
	purSuggestDataVo["purSuggestVo.status"]=$("#status").val();
	
	//alert($("#suggestNo").val());
	
	var rowIds = jQuery(maingridid).jqGrid('getDataIDs');
	var griddata = $(maingridid).getRowData();
	for(var i in griddata){
		
	     
          purSuggestDataVo["purSuggestGoodsVoList["+i+"].gdsId"] =  griddata[i].gdsId;
          purSuggestDataVo["purSuggestGoodsVoList["+i+"].supplierId"] = griddata[i].supplierId;
          purSuggestDataVo["purSuggestGoodsVoList["+i+"].num"] =  griddata[i].num;
          purSuggestDataVo["purSuggestGoodsVoList["+i+"].price"] =griddata[i].price;
          purSuggestDataVo["purSuggestGoodsVoList["+i+"].id"] = griddata[i].id;
          
          // purSuggestDataVo.purSuggestGoodsVoList.push(curdata);                  
       }
       

	 
  

	//objVo.status=$("input[name=status]").filter(':checked').val();

	var options = {};
    options.params = purSuggestDataVo;
	options.url = base+"/template/suggest/saveSuggest.jhtml";
	options.successCallback = saveBackFn;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>addBackFn</ATFunc>
函数功能:   <ATFuncDesc>添加采购建议回调函数</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveBackFn(data){
	oms.tooltip(data.errMsg , "succeed");
	/*
	artTabs({
	    toTab: {id : "suggestMgr"},
	    closeTab: true,
	    isRefresh: true
	});
	*/
}


function afterEditCellFn(rowid, cellname, value, iRow, iCol) {
	curRow = iRow;
	curCol = iCol;
	lasterSel = rowid;
  
   /*
	$(maingridid).find('tr').eq(curRow).find('td').eq(curCol).find('input').blur(function(){
		var num = (typeof($(maingridid).find('tr').eq(curRow).find('td').eq(8).find('input').val()) == 'undefined') 
		          ? $(maingridid).find('tr').eq(curRow).find('td').eq(8).text()
		          : $(maingridid).find('tr').eq(curRow).find('td').eq(8).find('input').val();
		var price = (typeof($(maingridid).find('tr').eq(curRow).find('td').eq(9).find('input').val()) == 'undefined')
		           ? $(maingridid).find('tr').eq(curRow).find('td').eq(9).text() 
		           : $(maingridid).find('tr').eq(curRow).find('td').eq(9).find('input').val();
		price = parseFloat(price.replace(/[^\d\.-]/g, ""));
		jQuery(maingridid).setCell(rowid,"amount",num*price);
	});
	
	if(curCol ==8){
		var item = $(maingridid).find('tr').eq(curRow).find('td').eq(curCol).find('input');
		item.attr("maxlength" , 8);
		bindJqgridJustNumberEvent(item);
	}
	
	if(curCol==9){
		var item = $(maingridid).find('tr').eq(curRow).find('td').eq(curCol).find('input');
		bindJqgridMoneyAndAmountEvent(item);
	}
    */
	//
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
	         
			jQuery(maingridid).setCell(eid,"skuOuterId",item.skuOuterId);
			jQuery(maingridid).setCell(eid,"gdsName",item.gdsName);
			jQuery(maingridid).setCell(eid,"gdsFormat",item.gdsFormat);
			jQuery(maingridid).setCell(eid,"attbs",gdsAttbs);
			jQuery(maingridid).setCell(eid,"gdsId",item.id);
			$("#gdsShowInfo_" + eid).val(item.gdsNo);
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
		var attbsInfo =   $(this).prev("div").find(".select2-chosen").text().split("-")[1];
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
		jQuery(maingridid).setCell(rowId,"attbs",gdsAttbs);
		jQuery(maingridid).setCell(rowId,"gdsId",gdsId);
		
	});
	*/
}

function bindSupplierSelect2(e){
	e.select2({
		placeholder : "请输入供应商名称",
		minimumInputLength : 1,
		multiple : false,
		width : '200',
		formatResult : function(result, container, query, escapeMarkup) { // 下拉框显示的值(待选区)
			return result.custName ;
		},
		formatSelection : function(item) {// 选中后显示在文本框中的值
			return item.custName;
		},
		dropdownCssClass : "bigdrop", // apply css that makes the dropdown
		escapeMarkup : function(m) {
			return m;
		},
		ajax : {
			url : base+"/template/custInfo/findCustInfoForGrid.jhtml",
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
	
	$("input[name=supplierSelect2]").unbind("change");
	$("input[name=supplierSelect2]").bind("change",function(){
		var rowId = $(this).attr("id").split("_")[1];
		var supplierId = $(this).val() || " ";
		var supplierName = $(this).prev("div").find(".select2-chosen").text().split("-")[0];
	
		if (supplierName!="undefined"){
		 $(this).prev("div").find(".select2-chosen").text(supplierName);
		}
	
		jQuery(maingridid).setCell(rowId,"supplierId",supplierId);
		
	});
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
	var ids = jQuery(maingridid).jqGrid('getDataIDs');
	var size = ids.length;
	jQuery(maingridid).delRowData(rowId);
	if(size==1) {
		addLine();
	}
}



function addPurOrder(){
	 var selectRowId = $(maingridid).jqGrid('getGridParam','selarrrow');
     if(selectRowId == ""){
    	 oms.tooltip("请选择操作的记录!","error");
    	 return;
	 }
    var  ids_1 = selectRowId.join(",");

     var  ids  = ids_1.split(",");

     //判断 供应商是否一致
     var fsupplierId = -1;
     for(var i=1;i<ids.length;i++) {
    	 rowId = ids[i];
    	var rowData = $(maingridid).jqGrid('getRowData',rowId);
    	var supplierId = rowData.supplierId;
	   
	    	if(supplierId!=fsupplierId && fsupplierId!= -1)
    		{
    		 oms.tooltip("供应商不一致!","error");
    		 return;
    		}
    	fsupplierId = supplierId;
     }
     

	 artTabs({
 		addTab : {
 		  items: {
                id: 'addPurOrder',
                title: '添加采购订单',
                url: base + '/template/purOrder/generatePurOrder.jhtml'+'?suggestGoodsids='+selectRowId
            } 	 	
 		}
      });

}
