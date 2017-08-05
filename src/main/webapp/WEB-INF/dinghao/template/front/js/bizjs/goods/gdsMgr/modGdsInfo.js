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
	$("#brand").val(gdsInfo.brand);
	$("#ingredient").val(gdsInfo.ingredient);
	$("#purpose").val(gdsInfo.purpose);
	$("#producer").val(gdsInfo.producer);
	$("#countrycode").val(gdsInfo.countrycode);
	$("#countryname").val(gdsInfo.countryname);
	$("#expiration").val(gdsInfo.expiration);
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
	$("#artNo").val(gdsInfo.artNo);
	$("#generatePact").val(gdsInfo.generatePact);
	bindMoneyAndAmountEvent();
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
	if($.trim($("#gdsName").val()) == ""){
		oms.tooltip("请输入商品名称" , "error");
		return false;
	}
	if($("#clsId").val() == ""){
		oms.tooltip("请选择商品分类" , "error");
		return false;
	}
	return  true;
}

function uploadsImg(){
	if(!verifyGdsInfo()){
		return;
	}
	if($.trim($("#photoUpload").val()) == ""){
		saveGdsInfo("");
	}else{
		oms.ajaxFormCall({
			formId:"addPic",
			successCallback:function(result){
				saveGdsInfo(base+"/"+result.data);
			}
		});
	}
}

/******************************************************************************
函数名称:   <ATFunc>saveGdsInfo</ATFunc>
函数功能:   <ATFuncDesc>保存修改的商品信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveGdsInfo(imgPath){
	var gdsInfoVo = {};
	gdsInfoVo.id= $.trim($("#id").val());
	gdsInfoVo.gdsNo= $.trim($("#gdsNo").val());
	gdsInfoVo.skuOuterId= $.trim($("#skuOuterId").val());
	gdsInfoVo.gdsName=$.trim($("#gdsName").val());
	gdsInfoVo.gdsFormat=$.trim($("#gdsFormat").val());
	gdsInfoVo.gdsPact=$.trim($("#gdsPact").val());
	gdsInfoVo.standWeight=$.trim($("#standWeight").val());
	gdsInfoVo.clsId=$.trim($("#clsId").val());
	gdsInfoVo.gdsSelPrice=$.trim($("#gdsSelPrice").attr("realMoney"));
	gdsInfoVo.cal=$("#cal").val();
	gdsInfoVo.brand=$("#brand").val();
	gdsInfoVo.ingredient=$("#ingredient").val();
	gdsInfoVo.purpose=$("#purpose").val();
	gdsInfoVo.producer=$("#producer").val();
	gdsInfoVo.countrycode=$("#countrycode").val();
	gdsInfoVo.countryname=$("#countryname").val();
	gdsInfoVo.expiration=$("#expiration").val();
	gdsInfoVo.attbs=$.trim($("#attbs").val());
	gdsInfoVo.gdsStatus=$("input[name=gdsStatus]").filter(':checked').val();
	gdsInfoVo.gdsLowAmount=$.trim($("#gdsLowAmount").val());
	gdsInfoVo.gdsHighAmount=$.trim($("#gdsHighAmount").val());
	gdsInfoVo.imgPath=imgPath;
	gdsInfoVo.remark=$.trim($("#remark").val());
	
	var options = {};
    options.params = gdsInfoVo;
	options.url = base+"/template/gdsMgr/modGdsInfo.jhtml";
	options.successCallback = modGdsBackFn;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>modGdsBackFn</ATFunc>
函数功能:   <ATFuncDesc>商品修改保存 回调函数</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function modGdsBackFn(data){
	oms.tooltip(data.errMsg , "succeed");
	/*
	artTabs({
	    toTab: {id : "gdsMgr"},
	    closeTab: true,
	    isRefresh: true
	});
	*/
}
