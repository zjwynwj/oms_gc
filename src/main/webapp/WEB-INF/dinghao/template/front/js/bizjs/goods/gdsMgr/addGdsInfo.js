/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面初始化  请求单据号</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
$(function(){
	var options = {};
    options.params = {"nuType":"01"};
	options.url = base+"/template/baseNumber/findBaseNumberNext.jhtml";
	options.successCallback = numberBackFn;
	oms.ajaxCall(options);
	//默认为启用
	$("input:radio[name=gdsStatus][value=1]").attr("checked", "true");
	bindMoneyAndAmountEvent();
}); 

/******************************************************************************
函数名称:   <ATFunc>numberBackFn</ATFunc>
函数功能:   <ATFuncDesc>填充单据号</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function numberBackFn(data){
	$("#gdsNo").val(data.data);
}

/******************************************************************************
函数名称:   <ATFunc>setGdsCls</ATFunc>
函数功能:   <ATFuncDesc>打开新增商品分类选择页面</ATFuncDesc>       
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
函数功能:   <ATFuncDesc>打开 商品属性选择页面</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function setGdsAttb(){
	var clsId = $("#clsId").val();
	if(clsId ==""){
		oms.tooltip("请先选择商品分类!" , "error");
		return;
	}
	oms.s_addPop("商品属性选择",base+"/template/gdsAttb/attrSelect.jhtml?attbs=&clsId="+clsId+"","",600,450);
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
			formId:"addGds",
			successCallback:function(result){
				saveGdsInfo(base + "/" +result.data);
			}
		});
	}
}

/******************************************************************************
函数名称:   <ATFunc>saveGdsInfo</ATFunc>
函数功能:   <ATFuncDesc>请求保存商品</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveGdsInfo(imgPath){
	var gdsInfoVo = {};
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
	//alert(JSON.stringify(gdsInfoVo));
	var options = {};
    options.params = gdsInfoVo;
	options.url = base+"/template/gdsMgr/addGdsInfo.jhtml";
	options.successCallback = addGdsBackFn;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>saveGdsInfo</ATFunc>
函数功能:   <ATFuncDesc>保存商品  回调函数</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function addGdsBackFn(data){
	oms.tooltip(data.errMsg , "succeed");
	/*
	artTabs({
	    toTab: {id : "gdsMgr"},
	    closeTab: true,
	    isRefresh: true
	});
	*/
}
