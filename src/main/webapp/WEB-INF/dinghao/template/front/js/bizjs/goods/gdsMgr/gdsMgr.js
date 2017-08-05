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
		height : '400px',
		colNames: ['序号','操作', '商品编码', '规格代码', '名称', '条形码', '规格名称','属性','单位','零售价(元)','状态','备注'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'act',index:'act' ,width:'140px'},
			{name:'gdsNo',index:'gdsNo',width:'200px'},
			{name:'skuOuterId',index:'skuOuterId',width:'120px'},
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
			var ids = jQuery("#gdsInfoGridId").jqGrid('getDataIDs');
		
			for (var i = 0; i < ids.length; i++) {
				var cl = ids[i];
				var addBtn = "<div class='operating'>";
				addBtn += "<a href='javascript:' class='operating-pencil' title='修改' onclick=\"mod("+cl+")\"></a>";
				if( $("#gdsInfoGridId").getRowData(cl).gdsStatus != "停用"){
					addBtn += "<a href='javascript:' class='operating-trash' title='停用' onclick=\"block("+cl+")\"></a>";
				}else{
					addBtn += "<a href='javascript:' class='operating-check' title='启用' onclick=\"start("+cl+")\"></a>";
				}
				addBtn += "<a href='javascript:' class='operating-comment' title='详情' onclick=\"detail("+cl+")\"></a></div>";
				jQuery("#gdsInfoGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		},
		multiselect : true
	});
});

/******************************************************************************
函数名称:   <ATFunc>mod</ATFunc>
函数功能:   <ATFuncDesc>跳转商品修改页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function mod(rowId){
	var row = $("#gdsInfoGridId").getRowData(rowId);
	artTabs({
        addTab: {
            items: {
                id: 'modGdsInfo',
                title: '修改商品信息',
                url: base + '/template/gdsMgr/turnModGdsInfo.jhtml?id='+row.id+''
            }
        },
        isRefresh : true
    })
}

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
函数名称:   <ATFunc>addGdsCls</ATFunc>
函数功能:   <ATFuncDesc>跳转到商品分类添加页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function addGdsCls(){
	if(selectClsId == ""){
		oms.tooltip("请选择操作商品分类!" , "error");
		return;
	}
	if(parseInt(selectLevel)>= 3){
		oms.tooltip("最多只有三级分类，此分类不能继续增加子分类!" , "error");
		return;
	}
	var str = base+"/template/gdscls/turnAddcls.jhtml?id="+selectClsId;
	oms.s_addPop("添加商品分类",str,"",600,350);
}

/******************************************************************************
函数名称:   <ATFunc>modGdsCls</ATFunc>
函数功能:   <ATFuncDesc>跳转到商品分类修改页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function modGdsCls(){
	if(selectClsId == ""){
		oms.tooltip("请选择操作商品分类!" , "error");
		return;
	}
	
	if(selectClsNo == "0"){
		oms.tooltip("不能修改根节点!" , "error");
		return;
	}
	var str = (base+"/template/gdscls/turnModcls.jhtml?id="+selectClsId+"&clsName="+selectClsName);
	oms.s_addPop("修改商品分类",str,"",600,350);
}

/******************************************************************************
函数名称:   <ATFunc>delGdsCls</ATFunc>
函数功能:   <ATFuncDesc>删除商品</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function delGdsCls(){
	if(selectClsId == ""){
		oms.tooltip("请选择操作商品分类!" , "error");
		return;
	}
	if(selectClsNo == "0"){
		oms.tooltip("不能删除根节点!" , "error");
		return;
	}
	
	$.artDialog({
		title:'提示',
		content:'确定要删除此商品分类么吗?',
		ok:function(){
			//组装删除的 数据
			var options = {};
			options.params = {"id":selectClsId};
			options.url = base+'/template/gdscls/delGdsCls.jhtml',
			options.successCallback = delBackFn;
			oms.ajaxCall(options);
		}
	});
}

/******************************************************************************
函数名称:   <ATFunc>delBackFn</ATFunc>
函数功能:   <ATFuncDesc>删除商品回调函数</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function delBackFn(data){
	initClsTree();
	oms.tooltip(data.errMsg , "succeed");
}

/******************************************************************************
函数名称:   <ATFunc>turnAddGdsInfo</ATFunc>
函数功能:   <ATFuncDesc>跳转到添加商品也买</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function turnAddGdsInfo(){
	artTabs({
        addTab: {
            items: {
                id: 'addGdsInfo',
                title: '添加商品信息',
                url: base + '/template/gdsMgr/turnAddGdsInfo.jhtml'
            }
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
	queryData.clsId = selectClsId;
	queryData.clsNo = selectClsNo;
	queryData.gdsStatus = "";
	if($("#isShowBlockGds").attr("checked") == 'checked'){
		queryData.gdsStatus = "1";
	}
	postData.push(queryData);
    $("#gdsInfoGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}

/******************************************************************************
函数名称:   <ATFunc>stockOperate</ATFunc>
函数功能:   <ATFuncDesc>批量请求操作商品</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function stockOperate(type){
     var selectRowId = $('#gdsInfoGridId').jqGrid('getGridParam','selarrrow');
     if(selectRowId == ""){
    	 oms.tooltip("请选择操作的记录!","error");
    	 return;
	 }
     
     var  param = {};
     param.ids = selectRowId.join(",");
     if(type == 'block'){
    	 param.gdsStatus = "0";
	 }else if(type == 'start'){
		 param.gdsStatus = "1";
	 }
    
     var options = {};
     options.params = param;
 	 options.url = base+"/template/gdsMgr/stockOperateGds.jhtml";
 	 options.successCallback = successBackFn;
 	 oms.ajaxCall(options);
}

function downGoods(){
	 var options = {};
     options.param = {};
     options.isMask = true;
 	 options.url = base+"/template/gdsMgr/downLoadGoods.jhtml";
 	 options.successCallback = function(data){
 		oms.tooltip(data.errMsg , "succeed");
 		search();
 	 };
 	 oms.ajaxCall(options);
}
function beian(){
	 var options = {};
     options.params = {};
     options.isMask = false;
 	 options.url = base+"/template/gdsMgr/goodsBeian.jhtml";
 	 options.successCallback = function(data){
 		oms.tooltip(data.errMsg , "succeed");
 		search();
 	 };
 	 oms.ajaxCall(options);

}


