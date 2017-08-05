//选择的分类  信息
var selectClsId = "";
var selectClsName = "";
/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面初始化，查询第一级商品分类信息   绑定每一级分类选择后出发的事件</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
$(function(){
	var gdsCls = {'level':1};
	$.ajax({
		url: base+"/template/gdscls/queryGdsClsList.jhtml",
		type: 'POST',
		data: gdsCls,
		dataType: 'json',
		timeout: "300000",
		error: function(data){
			alert("查询失败");
		},
		success: function(data){
			initGdsCls1(data);
		},
		async:true
	});
	
	$('#gdsCls1').delegate('li','click',function(){
		$(this).addClass('on').siblings().removeClass('on');
		selectClsId = $(this).find('a').attr('id');
		selectClsName = $(this).text();
		var str = "您当前选择的分类是："+$(this).text();
		$("#gdsClsDetailInfo").html(str);
	});
	$('#gdsCls2').delegate('li','click',function(){
		$(this).addClass('on').siblings().removeClass('on');
		selectClsId = $(this).find('a').attr('id');
		selectClsName = $(this).text();
		var cls1Name = $('#gdsCls1').find('li[class=on]').text();
		var str = "您当前选择的分类是："+cls1Name+">"+$(this).text();
		$("#gdsClsDetailInfo").html(str);
	});
	$('#gdsCls3').delegate('li','click',function(){
		$(this).addClass('on').siblings().removeClass('on');
		selectClsId = $(this).find('a').attr('id');
		selectClsName = $(this).text();
		var cls1Name = $('#gdsCls1').find('li[class=on]').text();
		var cls2Name = $('#gdsCls2').find('li[class=on]').text();
		var str = "您当前选择的分类是："+cls1Name+">"+cls2Name+">"+$(this).text();
		$("#gdsClsDetailInfo").html(str);
	});
});

/******************************************************************************
函数名称:   <ATFunc>initGdsCls1</ATFunc>
函数功能:   <ATFuncDesc>初始化第一级商品分类信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function initGdsCls1(result){
	var gdsCls1Info = result.data;
	var str = "";
	for(var i in gdsCls1Info){
		var clsId = gdsCls1Info[i].id + '';
		var clsNo = gdsCls1Info[i].clsNo + '';
		str += " <li><a id="+clsId+" onclick='ajaxGdsCls2(\""+clsNo+"\")'>"+gdsCls1Info[i].clsName+"</a></li>";
	} 
	$("#gdsCls1").html(str);
}

/******************************************************************************
函数名称:   <ATFunc>ajaxGdsCls2</ATFunc>
函数功能:   <ATFuncDesc>查询第二级商品分类信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function ajaxGdsCls2(clsNo){	
	var gdsCls = {'level':2 , 'clsPno':clsNo};
	$.ajax({
		url: base+"/template/gdscls/queryGdsClsList.jhtml",
		type: 'POST',
		data: gdsCls,
		dataType: 'json',
		timeout: "300000",
		error: function(data){
			alert("查询失败");
		},
		success: function(data){
			initGdsCls2(data);
		},
		async:true
	});
}

/******************************************************************************
函数名称:   <ATFunc>ajaxGdsCls2</ATFunc>
函数功能:   <ATFuncDesc>加载第二级商品分类信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function initGdsCls2(result){
	var gdsCls2Info = result.data;
	var str = "";
	for(var i in gdsCls2Info){
		var clsId = gdsCls2Info[i].id + '';
		var clsNo = gdsCls2Info[i].clsNo + '';
		str += " <li><a id="+clsId+" onclick='ajaxGdsCls3(\""+clsNo+"\")'>"+gdsCls2Info[i].clsName+"</a></li>";
	} 
	$("#gdsCls2").html(str);
	$("#gdsCls3").html("");
}

/******************************************************************************
函数名称:   <ATFunc>ajaxGdsCls3</ATFunc>
函数功能:   <ATFuncDesc>请求第二级商品分类信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function ajaxGdsCls3(clsNo){
	var gdsCls = {'level':3 , 'clsPno':clsNo};
	$.ajax({
		url: base+"/template/gdscls/queryGdsClsList.jhtml",
		type: 'POST',
		data: gdsCls,
		dataType: 'json',
		timeout: "300000",
		error: function(data){
			alert("查询失败");
		},
		success: function(data){
			initGdsCls3(data);
		},
		async:true
	});
}

/******************************************************************************
函数名称:   <ATFunc>initGdsCls3</ATFunc>
函数功能:   <ATFuncDesc>加载第二级商品分类信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function initGdsCls3(result){
	var gdsCls3Info = result.data;
	var str = "";
	for(var i in gdsCls3Info){
		var clsId = gdsCls3Info[i].id + '';
		str += " <li><a id="+clsId+">"+gdsCls3Info[i].clsName+"</a></li>";
	} 
	$("#gdsCls3").html(str);
}

/******************************************************************************
函数名称:   <ATFunc>saveGdsClsFn</ATFunc>
函数功能:   <ATFuncDesc>保存选择的商品分类</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveGdsClsFn() {
	parent.saveGdsCls(selectClsId,selectClsName);
	closeWinFn();
}

/******************************************************************************
函数名称:   <ATFunc>closeWinFn</ATFunc>
函数功能:   <ATFuncDesc>关闭窗口</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function closeWinFn() {
	parent.$("#gdsName").focus();
	oms.s_Pop_closedChild();
}
