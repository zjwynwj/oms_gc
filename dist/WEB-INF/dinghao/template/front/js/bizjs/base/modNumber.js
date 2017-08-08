//单据号id
var id;

/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面初始化执行 查询单据信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
$(function(){
	//初始化主表信息
	var baseNumber = {};
	baseNumber.id = $("#BUSInumberOfId").val();
	var options = {};
	options.params = baseNumber;
	options.url = base_template+"/baseNumber/queryBaseNumber.jhtml";
	options.successCallback = initInstoreInfoFn;
	oms.ajaxCall(options);
});


/******************************************************************************
函数名称:   <ATFunc>searchFn</ATFunc>
函数功能:   <ATFuncDesc>初始化单据设置信息回调函数</ATFuncDesc>       
输入参数:   data
开发人员:   helong
修改记录:
******************************************************************************/
function initInstoreInfoFn(data) {
	var number = data.data;
	$("#BUSInumberOfNuName").val(number.nuName);
	$("#BUSInumberOfNuPrefix").val(number.nuPrefix);
    $("#BUSInumberOfIsCheck").find("option[value='"+number.isCheck+"']").attr("selected",true);
	
	
	$("#BUSInumberOfNuDigit").val(number.nuDigit);
	$("#BUSInumberOfNuCurrent").val(number.nuCurrent);
	$("#BUSInumberOfNuStep").val(number.nuStep);
	$("#BUSInumberOfNuDemo").val(number.nuDemo);
	
	
	$("#BUSInumberOfYearRule").attr("checked", number.yearRule);
	$("#BUSInumberOfMontRule").attr("checked", number.montRule);
	$("#BUSInumberOfDayRule").attr("checked", number.dayRule);
}

/******************************************************************************
函数名称:   <ATFunc>doChange</ATFunc>
函数功能:   <ATFuncDesc>勾选checkbox 触发方法</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function doChange() {
	var nuPrefix =	$("#BUSInumberOfNuPrefix").val();
	var isYear = $("#BUSInumberOfYearRule").attr("checked");
	var isMonth = $("#BUSInumberOfMontRule").attr("checked");
	var isDay = $("#BUSInumberOfDayRule").attr("checked");
	var nuDigit=$("#BUSInumberOfNuDigit").val();
	var nuDigitStr= new Array(parseInt(nuDigit)+1).join("X");
	var setNO = "{"+nuPrefix+"}" + (isYear ? "{YYYY}" : "")
			+ (isMonth ? "{MM}" : "") + (isDay ? "{DD}" : "")+"{"+nuDigitStr+"}";
	$("#BUSInumberOfNuDemo").val(setNO);
}

/******************************************************************************
函数名称:   <ATFunc>numberInfo_save</ATFunc>
函数功能:   <ATFuncDesc>保存单据号信息</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function numberInfo_save(){
	
	var jsonData = getGdsdata();//获取参数
	var options = {};
	options.params = jsonData;
	options.url = base_template+"/baseNumber/modNumberMgr.jhtml";
	options.successCallback = saveBaseNumberBackFn;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>saveBaseNumberBackFn</ATFunc>
函数功能:   <ATFuncDesc>保存单据号信息回调方法</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveBaseNumberBackFn(data){
	oms.tooltip(data.errMsg , "succeed");
	/*
	artTabs({
	    toTab: {id : "numberMgr"},
	    isRefresh: true
	});
	window.location.reload();
	*/
}

/******************************************************************************
函数名称:   <ATFunc>getGdsdata</ATFunc>
函数功能:   <ATFuncDesc>获取单据号信息</ATFuncDesc>   
返回参数:   numberDto   
开发人员:   helong
修改记录:
******************************************************************************/
function getGdsdata(){
	var number={};
	number["id"]=$("#BUSInumberOfId").val();
	number["nuPrefix"]=$("#BUSInumberOfNuPrefix").val();
	number["isCheck"]=$("#BUSInumberOfIsCheck").val();
	number["nuDigit"]=$("#BUSInumberOfNuDigit").val();
	number["nuCurrent"]=$("#BUSInumberOfNuCurrent").val();
	number["nuStep"]=$("#BUSInumberOfNuStep").val();
	number["nuDemo"]=$("#BUSInumberOfNuDemo").val();
	number["yearRule"]=$("#BUSInumberOfYearRule").attr("checked")?true:false;
	number["montRule"]=$("#BUSInumberOfMontRule").attr("checked")?true:false;
	number["dayRule"]=$("#BUSInumberOfDayRule").attr("checked")?true:false;
	return number;
}



