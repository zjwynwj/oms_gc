//配置ztree相关参数
var setting = {
	data: {
		key: {
			name:"clsName"
		},
		simpleData: {
			enable: true,
			idKey:"clsNo",
			pIdKey:"clsPno"
		}
	},
	callback: {
		onClick:selectGdsCls
	}
};

//用于保存点击树节点后  相关的参数
var selectClsName = "";
var selectClsId = "";
var selectLevel = "";
var selectClsNo = "";
var childNode = "";

/******************************************************************************
函数名称:   <ATFunc>selectGdsCls</ATFunc>
函数功能:   <ATFuncDesc>点击树节点 查询相关商品信息 </ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function selectGdsCls(event, treeId, treeNode){
	 selectClsName = treeNode.clsName;
	 selectClsId = treeNode.id;
	 selectLevel = treeNode.level;
	 selectClsNo = treeNode.clsNo;
	 childNode = treeNode.children;
	 
	 var postData = new Array();
	 var queryData = {};
	 queryData.gdsStatus = "";
	 queryData.keyWord = $("#keyWord").val();
	 queryData.clsId = treeNode.id;
	 queryData.clsNo = treeNode.clsNo;
	 if($("#isShowBlockGds").attr("checked") == 'checked'){
		 queryData.gdsStatus = "1";
	 }
	 postData.push(queryData);
     $("#gdsInfoGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}

/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面 初始化  请求商品分类和商品的信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
$(function() {
	//初始化商品分类
	initClsTree();
	
	//商品表格
	jQuery("#gdsInfoGridId").jqGrid({
		datatype: "json",
		url: base + '/template/gdsMgr/findGdsInfoForGrid.jhtml',
		mtype: 'POST', 
		postData : {},
		height : 'auto',
		colNames: ['序号','编号', '名称', '条形码', '规格','属性','基本单位','零售价(元)','状态','备注'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'gdsNo',index:'gdsNo'},
			{name:'gdsName',index:'gdsName'},
			{name:'gdsPact',index:'gdsPact'},
			{name:'gdsFormat',index:'gdsFormat'},
			{name:'attbs',index:'attbs',formatter:function(cellvalue, options, rowObject){
				if(typeof(cellvalue) !=  "undefined" && cellvalue!=""){
					var attbs1 = cellvalue.split(";");
					var attbs2 = "";
					for(var i in attbs1){
						attbs2 += ";"+attbs1[i].split(":")[2]+":"+attbs1[i].split(":")[3];
					}
					return attbs2.substring(1 , attbs2.length);
				}else{
					return "";
				}
			}},
			{name:'cal',index:'cal'},
			{name:'gdsSelPrice',index:'gdsSelPrice',align:'right',formatter:'currency',formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "", suffix:"", defaulValue: 0}},
			{name:'gdsStatus',index:'gdsStatus' , formatter:function(cellvalue, options, rowObject){
				if(cellvalue == "0"){
					return "停用";
				}else{
					return "启用";
				}
			}},
			{name:'remark',index:'remark'}
        ], 
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : 'gdsInfoGridPanelId',
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
		gridComplete : function() {
			$("#cb_gdsInfoGridId").hide();
		},
		multiselect : true
	});
	
	/**
	 * 关闭弹出框
	 */
	$("#closeGetCustInfoMgr").on("click", function() {
		oms.s_Pop_closedChild();
	});
	/**
	 * 点击确认
	 */
	$("#returnGetCustInfoMgr").on("click", function() {
		var values=[];
		var rowData = jQuery('#gdsInfoGridId').jqGrid('getGridParam','selarrrow');
		for (var int = 0; int < rowData.length; int++) {
			values.push($("#gdsInfoGridId").jqGrid('getRowData',rowData[int]));
			
		}
		parent.callbackt(values);
		oms.s_Pop_closedChild();
	});
});//jquery 结束

/******************************************************************************
函数名称:   <ATFunc>block</ATFunc>
函数功能:   <ATFuncDesc>请求停用商品</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function block(rowId){
	var row = $("#gdsInfoGridId").getRowData(rowId);
	var params = {"id":row.id ,"gdsStatus":"0"};
	var options = {};
	options.params = params;
	options.url = base+"/template/gdsMgr/modGdsInfo.jhtml";
	options.successCallback = successBackFn;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>start</ATFunc>
函数功能:   <ATFuncDesc>启用商品</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function start(rowId){
	var row = $("#gdsInfoGridId").getRowData(rowId);
	var params = {"id":row.id ,"gdsStatus":"1"};
	var options = {};
	options.params = params;
	options.url = base+"/template/gdsMgr/modGdsInfo.jhtml";
	options.successCallback = successBackFn;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>successBackFn</ATFunc>
函数功能:   <ATFuncDesc>基本请求的  回调函数</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function successBackFn(data){
	oms.tooltip(data.errMsg , "succeed");
	search();
}

/******************************************************************************
函数名称:   <ATFunc>detail</ATFunc>
函数功能:   <ATFuncDesc>跳转到商品详情页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function detail(rowId){
	var row = $("#gdsInfoGridId").getRowData(rowId);
	artTabs({
        addTab: {
            items: {
                id: 'detailGdsInfo',
                title: '商品信息详情',
                url: base + '/template/gdsMgr/turnDetailGdsInfo.jhtml?id='+row.id+''
            }
        },
        isRefresh : true
    })
}

/******************************************************************************
函数名称:   <ATFunc>initClsTree</ATFunc>
函数功能:   <ATFuncDesc>请求商品分类信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function initClsTree(){
	var options = {};
	options.url = base+"/template/gdscls/queryGdsClsList.jhtml";
	options.successCallback = loadGdsclsData;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>loadGdsclsData</ATFunc>
函数功能:   <ATFuncDesc>加载商品分类信息到 ztree中</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function loadGdsclsData(data){
	var data = data.data;
	ztree = $.fn.zTree.init($("#gdsClsTree"), setting, data);
	var treeObj = $.fn.zTree.getZTreeObj("gdsClsTree"); 
	treeObj.expandAll(true);
	var boxWidth=$(".ztree").width(),newWidth=20;
	$(".ztree li").each(function(){
		var obj=$(this).find("a span:eq(1)");
		var w=obj.offset().left - 30,
			_w=boxWidth - w - newWidth;
		if(obj.width()+w>boxWidth){
			obj.css({"width":_w+"px","overflow":"hidden","display":"inline-block"});
			obj.after($('<span>...</span>'));
		}		
	});
}

/******************************************************************************
函数名称:   <ATFunc>search</ATFunc>
函数功能:   <ATFuncDesc>点击搜索 查询相关商品信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function search(){
	var postData = new Array();
	var queryData = {};
	queryData.keyWord = $("#keyWord").val();
	queryData.clsId = "";
	queryData.clsNo = "";
	queryData.gdsStatus = "";
	if($("#isShowBlockGds").attr("checked") == 'checked'){
		queryData.gdsStatus = "1";
	}
	postData.push(queryData);
    $("#gdsInfoGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}


