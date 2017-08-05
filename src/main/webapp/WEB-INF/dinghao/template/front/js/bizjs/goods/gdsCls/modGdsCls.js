var editid=[];
$(function() {
	//初始化商品分类
	initClsTree();
	//商品表格
	jQuery("#gdsClsGridId").jqGrid({
		datatype: "json",
		url: base + '/template/gdscls/findGdsClsListPage.jhtml',
		mtype: 'POST', 
		postData : {clsPno:0,clsName:''},
		height : 'auto',
		colNames: ['序号','操作', '编号', '分类名称', '税率', '状态'], 
		colModel: [
			{name:'id',index:'id',sortable:true , hidden:true},
			{name:'act',index:'act' ,width:'80px'},
			{name:'clsNo',index:'clsNo'},
			{name:'clsName',index:'clsName',editable:true},
			{name:'taxRate',index:'taxRate',editable:true},
			{name:'clsStatus',index:'clsStatus' , formatter:function(cellvalue, options, rowObject){
				if(cellvalue == "0"){
					return "停用";
				}else{
					return "启用";
				}
			}},
        ], 
		rowNum : 10,
		rowList : [10, 20, 30],
		pager : 'gdsClsGridPanelId',
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
		beforeEditCell:function(rowid,cellname,v,iRow,iCol)
		 { 
			lastrow = iRow; lastcell = iCol; 
		   $("#gdsClsGridId").jqGrid("saveCell",lastrow,lastcell);
		 } ,
		gridComplete : function() {
			var ids = jQuery("#gdsClsGridId").jqGrid('getDataIDs');
		
			for (var i = 0; i < ids.length; i++) {
				var cl = ids[i];
				var addBtn = "<div class='operating'>";
				
				editid[ "editsave"+cl]= false; //初始化每行数据编辑状态
				
				addBtn += "<a id='editsave"+cl+"' href='javascript:' class='operating-pencil' title='修改' onclick=\"editrow('editsave"+cl+"','" + cl + "');\"></a>";
				if( $("#gdsClsGridId").getRowData(cl).clsStatus != "停用"){
					addBtn += "<a href='javascript:' class='operating-trash' title='停用' onclick=\"block("+cl+")\"></a>";
				}else{
					addBtn += "<a href='javascript:' class='operating-check' title='启用' onclick=\"start("+cl+")\"></a>";
				}
				addBtn += "</div>"
				/*
				if( $("#gdsClsGridId").getRowData(cl).clsStatus != "停用"){
					addBtn += "<a href='javascript:' class='operating-trash' title='停用' onclick=\"block("+cl+")\"></a>";
				}else{
					addBtn += "<a href='javascript:' class='operating-check' title='启用' onclick=\"start("+cl+")\"></a>";
				}
				*/
				jQuery("#gdsClsGridId").jqGrid('setRowData', ids[i], {
					act : addBtn
				});
			}
		},
		multiselect : false
	});
	
	
	
})

/******************************************************************************
函数名称:   <ATFunc>block</ATFunc>
函数功能:   <ATFuncDesc>请求停用商品</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function block(rowId){
	var row = $("#gdsClsGridId").getRowData(rowId);
	var params = {"id":row.id ,"clsStatus":"0"};
	var options = {};
	options.params = params;
	options.url = base+"/template/gdscls/modGdsCls.jhtml";
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
	var row = $("#gdsClsGridId").getRowData(rowId);
	var params = {"id":row.id ,"clsStatus":"1"};
	var options = {};
	options.params = params;
	options.url = base+"/template/gdscls/modGdsCls.jhtml";
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
var lastrow
var lastcell

/******************************************************************************
函数名称:   <ATFunc>selectGdsCls</ATFunc>
函数功能:   <ATFuncDesc>点击树节点 查询相关商品分类 </ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function selectGdsCls(event, treeId, treeNode){
	 selectClsName = treeNode.clsName;
	 selectClsId = treeNode.id;
	 selectLevel = treeNode.level;
	 selectClsNo = treeNode.clsNo;
	 childNode = treeNode.children;
	 
	 if(selectLevel==3)
		 return;
	 
	 var postData = new Array();
	 var queryData = {};
	 queryData.gdsStatus = "";
	 //queryData.clsName = $("#keyWord").val();
	// alert(queryData.keyWord);
	 //queryData.clsId = treeNode.id;
	 queryData.clsPno = treeNode.clsNo;
	 if($("#isShowBlockGds").attr("checked") == 'checked'){
		 queryData.gdsStatus = "1";
	 }
	 postData.push(queryData);
     $("#gdsClsGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
}



/******************************************************************************
函数名称:   <ATFunc>saveClsFn</ATFunc>
函数功能:   <ATFuncDesc>保存修改商品分类</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveClsFn(){
	if($.trim($("#clsName").val()) == ""){
		alert("分类名不能为空!");
		return;
	}
	var gdsCls = {}
	gdsCls.id = $("#id").val();
	gdsCls.clsName = $("#clsName").val();
	gdsCls.taxRate = $("#taxRate").val();
	
	var options = {};
    options.params = gdsCls;
	options.url = base+"/template/gdscls/modGdsCls.jhtml";
	options.successCallback = modClsBackFn;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>modClsBackFn</ATFunc>
函数功能:   <ATFuncDesc>保存修改商品分类回调函数</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function modClsBackFn(data){
	oms.tooltip(data.errMsg , "succeed");
	parent.initClsTree();
	closeWinFn();
}

/******************************************************************************
函数名称:   <ATFunc>closeWinFn</ATFunc>
函数功能:   <ATFuncDesc>关闭窗口</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function closeWinFn() {
	oms.s_Pop_closedChild();
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
	queryData.clsName = $("#keyWord").val();
	//queryData.clsPno = "-1";
	queryData.ClsId = selectClsId;
//	queryData.clsPno = selectClsNo;
	/*
	if($("#isShowBlockGds").attr("checked") == 'checked'){
		queryData.gdsStatus = "1";
	}
	*/
	postData.push(queryData);
    $("#gdsClsGridId").jqGrid("setGridParam", {"postData":postData[0]}).trigger("reloadGrid",[{page:1}]);
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
	var str = encodeURI(base+"/template/gdscls/turnAddcls.jhtml?id="+selectClsId);
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
	var str = (base+"/template/gdscls/editModcls.jhtml?id="+selectClsId+"&clsName="+selectClsName);
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


function editrow(objid,rowid){

	if(editid[objid] == undefined || editid[objid] ==false)
		{
		editid[objid] = true;
		  $("#"+objid).addClass('operating-save');
		  $("#"+objid).removeClass('operating-pencil');
		}		
    else
    	{
    	editid[objid] = false;
    	   $("#"+objid).addClass('operating-pencil');
  	      $("#"+objid).removeClass('operating-save');
    	}
    	
	
	if(editid[objid] ==true){
	
	   $('#gdsClsGridId').jqGrid('editRow',rowid,{
			keys : true,		//这里按[enter]保存
			url: base+"/template/gdscls/modGdsCls.jhtml",
			mtype : "POST",
			restoreAfterError: true,
			extraparam: {
				"id": rowid,//rowData.id,
				"clsName": $("#"+rowid+"_clsName").val(),
				"taxRate": $("#"+rowid+"_taxRate").val()
				
			},
			oneditfunc: function(rowid){
				
			},
			aftersavefunc:function(rowid){
				 $("#"+objid).addClass('operating-pencil');
			  	   $("#"+objid).removeClass('operating-check');
				console.log(rowid);
			},
			succesfunc: function(response){
				
				return true;
			},
			errorfunc: function(rowid, res){
			
				console.log(rowid);
				console.log(res);
			}
		});
	   
	}
	
	if(editid[objid] ==false){
		
		$('#gdsClsGridId').jqGrid('saveRow',rowid,{
			url: base+"/template/gdscls/modGdsCls.jhtml",
			mtype : "POST",
			restoreAfterError: true,
			extraparam: {
				"id": rowid,//rowData.id,
				"clsName": $("#"+rowid+"_clsName").val(),
				"taxRate": $("#"+rowid+"_taxRate").val()
				
			},
			oneditfunc: function(rowid){
				//alert("save oneditfunc");
				console.log(rowid);
			},
			succesfunc: function(response){
				//alert("save success2");
				return true;
			},
			errorfunc: function(rowid, res){
				//alert("save errorfunc");
				console.log(rowid);
				console.log(res);
			}
		});
	   
	}

	
}

