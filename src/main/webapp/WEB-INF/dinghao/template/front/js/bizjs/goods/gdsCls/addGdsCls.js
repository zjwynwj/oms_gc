$(function(){
	var getInputs = $('#verifyCon').find('input[type=text]').filter('.verify'); //得到表单中所有的input
	getInputs.blur(function(){
		var $this = $(this);
		getReadyverify($this);	
	})
	bindMoneyAndAmountEvent();
})

/******************************************************************************
函数名称:   <ATFunc>saveClsFn</ATFunc>
函数功能:   <ATFuncDesc>保存分类</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function saveClsFn(){
	var getInputs = $('#verifyCon').find('input[type=text]').filter('.verify');	 
	for(var i = 0; i < getInputs.length; i++){
		var $this = getInputs.eq(i);
		getReadyverify($this);
		if($this.hasClass("v_Error")){
			$this.focus();
			return;
		}
	}
	//alert($("#taxRate").val());
	//return;
	
//	if(){
//		
//	}
	
	var gdsCls = {}
	gdsCls.clsPno = $("#clsPno").val();
	gdsCls.level = parseInt($("#clsPLevel").val())+1;
	gdsCls.clsName = $("#clsName").val();
	gdsCls.taxRate = $("#taxRate").val();
	
	var options = {};
    options.params = gdsCls;
	options.url = base+"/template/gdscls/addGdsCls.jhtml";
	options.successCallback = addClsBackFn;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>addClsBackFn</ATFunc>
函数功能:   <ATFuncDesc>保存分类回调方法</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function addClsBackFn(data){
	oms.tooltip(data.errMsg , "succeed");
	parent.initClsTree();
	closeWinFn();
}

/******************************************************************************
函数名称:   <ATFunc>closeWinFn</ATFunc>
函数功能:   <ATFuncDesc>关闭弹出层</ATFuncDesc>       
开发人员:   helong
修改记录:
******************************************************************************/
function closeWinFn() {
	oms.s_Pop_closedChild();
}