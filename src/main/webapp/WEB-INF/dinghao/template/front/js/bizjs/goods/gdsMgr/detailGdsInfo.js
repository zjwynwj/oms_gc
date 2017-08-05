/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面初始化 请求商品信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
$(function(){
	var options = {};
    options.params = {"id":$("#id").val()};
	options.url = base+"/template/gdsMgr/queryGdsInfoById.jhtml";
	options.successCallback = queryGdsBackFn;
	oms.ajaxCall(options);
}); 

/******************************************************************************
函数名称:   <ATFunc>queryGdsBackFn</ATFunc>
函数功能:   <ATFuncDesc>商品信息填充到页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function queryGdsBackFn(data){
	var gdsInfo = data.data;
	$("#gdsNo").val(gdsInfo.gdsNo);
	$("#skuOuterId").val(gdsInfo.skuOuterId);
	$("#gdsName").val(gdsInfo.gdsName);
	$("#cal").val(gdsInfo.cal);
	$("#gdsFormat").val(gdsInfo.gdsFormat);
	$("#standWeight").val(gdsInfo.standWeight);
	$("#clsName").val(gdsInfo.clsName);
	$("#clsId").val(gdsInfo.clsId);
	$("#attbs").val(gdsInfo.attbs);
	if(typeof(gdsInfo.imgPath) != "undefined" && $.trim(gdsInfo.imgPath) != ""){
		$("#imgShow").attr("src" , gdsInfo.imgPath);
	}
	
	if(gdsInfo.attbs != "" && typeof(gdsInfo.attbs) != "undefined"){
		var attbs = gdsInfo.attbs.split(";");
		var attbsName = "";
		for(var i in attbs){
			attbsName += ";" + attbs[i].split(":")[2] + ":" +attbs[i].split(":")[3];
		}
		$("#attbsName").val(attbsName.substring(1 , attbsName.length));
	}
	$("#gdsSelPrice").val(gdsInfo.gdsSelPrice);
	$("#gdsPact").val(gdsInfo.gdsPact);
	$("#gdsLowAmount").val(gdsInfo.gdsLowAmount);
	$("#gdsHighAmount").val(gdsInfo.gdsHighAmount);
	$("input:radio[name=gdsStatus][value="+gdsInfo.gdsStatus+"]").attr("checked", "true");
	$("#remark").val(gdsInfo.remark);
}

/******************************************************************************
函数名称:   <ATFunc>setGdsCls</ATFunc>
函数功能:   <ATFuncDesc>跳转到商品分类选择 页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function setGdsCls(){
	oms.s_addPop("商品分类选择",base+"/template/gdscls/turnGdsclsSelect.jhtml","",600,450);
}

/******************************************************************************
函数名称:   <ATFunc>saveGdsCls</ATFunc>
函数功能:   <ATFuncDesc>商品分类选择 回调函数</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveGdsCls(selectClsId,selectClsName){
	if(selectClsId != $("#clsId").attr("oldValue")){
		$("#clsId").val(selectClsId);
		$("#clsName").val(selectClsName);
		$("#clsId").attr("oldValue",selectClsId);
		$("#attbs").val("");
		$("#attbsName").val("");
	}
}

/******************************************************************************
函数名称:   <ATFunc>setGdsAttb</ATFunc>
函数功能:   <ATFuncDesc>跳转到商品属性选择 页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function setGdsAttb(){
	var clsId = $("#clsId").val();
	var attbs = $("#attbs").val();
	if(clsId ==""){
		oms.tooltip("请先选择商品分类!" , "error");
		return;
	}
	oms.s_addPop("商品属性选择",base+"/template/gdsAttb/attrSelect.jhtml?clsId="+clsId+"&attbs="+attbs+"","",600,450);
}

/******************************************************************************
函数名称:   <ATFunc>saveGdsAttr</ATFunc>
函数功能:   <ATFuncDesc>商品属性选择 回调函数</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveGdsAttr(attbs,attbsName){
	$("#attbs").val(attbs);
	$("#attbsName").val(attbsName);
}

/******************************************************************************
函数名称:   <ATFunc>verifyGdsInfo</ATFunc>
函数功能:   <ATFuncDesc>验证商品信息商品</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function verifyGdsInfo(){
	if($("#clsId").val() == ""){
		oms.tooltip("请选择商品分类" , "error");
		return false;
	}
	if($("#gdsName").val() == ""){
		oms.tooltip("请输入商品名称" , "error");
		return false;
	}
	return  true;
}
