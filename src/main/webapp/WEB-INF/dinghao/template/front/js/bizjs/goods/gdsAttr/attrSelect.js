/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面初始化  查询商品属性数据</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
$(function(){
	//初始化商品属性
	var options = {};
    options.params = {"clsId":$("#clsId").val()};
	options.url = base+"/template/gdsAttb/queryAttbTable.jhtml";
	options.successCallback = queryAttbBackFn;
	oms.ajaxCall(options);
});

/******************************************************************************
函数名称:   <ATFunc>queryAttbBackFn</ATFunc>
函数功能:   <ATFuncDesc>页面初始化商品属性</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function queryAttbBackFn(data){
	var gdsAttr = data.data;
	var str = '';
	for(var i in gdsAttr){
		var k = 1+parseInt(i) ;
		if(gdsAttr[i].gdsAtvList == ""){
			continue;
		}			
		str+='<dl><dt  title='+gdsAttr[i].attbName+'>'+gdsAttr[i].attbName+'</dt>';
		str+='<dd ><select id="attb'+gdsAttr[i].attbId+'" name='+gdsAttr[i].attbName+'>';
		str+="<option value='0'>请选择</option>";
		var gdsAttrInfo = gdsAttr[i].gdsAtvList;
		for(var j in gdsAttrInfo){
			str += "<option value="+gdsAttrInfo[j].id+">"+gdsAttrInfo[j].attbValue+"</option>";
		}
		str+='</select></dd></dl>'
	}
	
	if($.trim(str) == ""){
		oms.tooltip("此分类下还未设置属性 ，请先到商品属性页面添加商品属性!" , "error");
		return;
	}
	$("#gdsAttr").html(str);
	
	var attbs = $("#attbs").val();
	if($.trim(attbs) != ""){
		var attbList = attbs.split(";");
		for(var i in attbList){
			var attbId = "attb"+attbList[i].split(":")[0];
			$("#"+attbId).val(attbList[i].split(":")[1]);
		}
	}
}

/******************************************************************************
函数名称:   <ATFunc>saveGdsAttbFn</ATFunc>
函数功能:   <ATFuncDesc>保存选择的 商品属性</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveGdsAttbFn(){
	var attbs = "";
	var attbsName ="";
	$("#gdsAttr").find("select").each(function(){
		var attbId = $(this).attr("id").replace("attb","");
		var attbTitle = $(this).attr("name");
		var atvId = $(this).val();
		var atvName = $(this).find("option:selected").text();
		if(atvId != $.trim("0")){
			attbs += ";" + attbId + ":" + atvId+ ":" + attbTitle+ ":" + atvName;
			attbsName += ";" + attbTitle + ":" + atvName;
		}
	});
	if($.trim(attbs)  != ""){
		attbs = attbs.substring(1 , attbs.length);
		attbsName = attbsName.substring(1 , attbsName.length);
	}
	parent.saveGdsAttr(attbs,attbsName);
	closeWinFn();
}

/******************************************************************************
函数名称:   <ATFunc>closeWinFn</ATFunc>
函数功能:   <ATFuncDesc>关闭弹出</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function closeWinFn() {
	oms.s_Pop_closedChild();
}