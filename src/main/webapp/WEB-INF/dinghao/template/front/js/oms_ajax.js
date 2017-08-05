$(function(){
	/**
	 * ajax请求session过期全局处理函数
	 */
	$.ajaxSetup({     
	    contentType:"application/x-www-form-urlencoded;charset=utf-8",     
	    complete:function(XMLHttpRequest,textStatus){  
	        // 通过XMLHttpRequest取得响应头，sessionstatus，  
	        var sessionStatus = XMLHttpRequest.getResponseHeader("sessionStatus");  
	        if(sessionStatus == "timeout"){//session过期标志 
		        //跳转到登录页面  
        		top.location.href = base + "/login.jhtml";
	        }  
	    }  
	}); 
});

var oms = oms || {};
oms.grid = oms.grid || {};

//加载遮罩层（外部可调用）
oms.LayerShow = function(text) {
    var addDiv= oms.loadDiv(text); 
    oms.LayerHide();
    $('body',top.document).append($(addDiv));
    $(window).resize(oms.Position);  
    oms.Position(); 
}

//隐藏遮罩层(外部可调用)
oms.LayerHide = function() {
    oms.del(); 
}
//加载遮罩层
oms.loadDiv = function(text) {	
	var div='<div class="m-sPopBg" id="_layer_Main" style="background:#fff;display:inline-block"></div><div id="_layer_" class="loaddings f-r5"><p class="loadingpng"></p><p>'+text+'</p></div>';
    return div; 
}

//获取相对位置
oms.Position = function() {
	var obj=$("#_layer_").length===1?$("#_layer_"):parent.$("#_layer_");	
	var _w=obj.outerWidth(),_h=obj.outerHeight();
	obj.css({"margin-left":-_w/2+"px","margin-top":-_h/2+"px"});
}

//删除
oms.del = function() {
	$("#_layer_",top.document).remove();
	$("#_layer_Main",top.document).remove();	
}

/**
 * 功能说明: 交易提示信息
 * @author ljf <liangjf@hundsun.com>
 * @date 2015-2-26 下午4:14:49
 * @version V1.0
 * @param msg 信息文本
 */
oms.tooltip = function(msg,variety,time) {
	$("#m_tip_con",top.document).remove();
	var time = time || 5000;
	var kind = null;
	if(variety == "succeed"){
		kind = 'm-tip-succeed';	
	}else if(variety == "error"){
		kind = 'm-tip-error';	
	}
	var str = ''
		str += '<div id="m_tip_con" class="m-tip-con '+ kind +'">'+ msg +'</div>';
	$('body',top.document).append(str);
	window.top.setTimeout('$("#m_tip_con",top.document).remove()',time);
}

/**
 * ajax通用调用方法（改版后，方便参数拓展和调用）
 * auth:chenliang
 * time:2015-10-15
 * @param option.url 请求地址
 * @param option.params 请求参数
 * @param option.successCallback ajax成功回调函数
 * @param option.errorCallback ajax失败回调函数
 * @param option.successTip 是否提示成功信息 true-提示 false-不提示
 * @param option.chainPar 链参数 会传递到回调方法里
 * @param option.async 是否异步
 * @param option.bizErrTip 是否错误提示(默认或者false的时候提示，true的时候不提示)
 * @param option.bizErrCallback 错误回调函数
 * @param option.isMask 是否需要遮罩层,默认不需要
 */
oms.ajaxCall = function(option) {
	if(option.isMask) {
		oms.LayerShow("正在加载中...");
	}
	var ts = this;
	//成功的回调
	var _succ = function(data, status, xhr){
		if(option.isMask) {
			oms.LayerHide();  //回调函数去掉遮罩层
		}
		if(!data) {
			var msg = "未知错误";
			oms.tooltip(msg,"error");
			return;
		}else if(data.overtime) {//超时
			top.location.href = oms.bathPath + "/login.jthml";
			return;
		}else if(data.success) { //交易成功
			if(option.successTip) {
				var msg = "交易成功";
			
				if(data.errMsg!="") {
				
					msg = data.errMsg;
				}
				oms.tooltip(msg,"succeed");
			}
			if(option.successCallback && $.isFunction(option.successCallback)) {//是否存在回调函数
				return option.successCallback.call(ts, data, status, xhr, option.chainPar);
			}
		}else if(!data.success) { //交易失败
			if(!option.bizErrTip || typeof(option.bizErrTip)=="undified" || option.bizErrTip=="") {
				var msg = "交易失败";
				if(data.errMsg!="") {
					msg = data.errMsg;
				}
				oms.tooltip(msg,"error");
			}
			if(option.bizErrCallback && $.isFunction(option.bizErrCallback)) {//是否存在回调函数
				return option.bizErrCallback.call(ts, data, status, xhr, option.chainPar);
			}
		}else { //交易失败
			oms.tooltip(data.errMsg,"error");
		}
	};

	//失败的回调
	var _err = function(xhr, status, error){
		if(option.isMask) {
			oms.LayerHide();  //回调函数去掉遮罩层
		}
		var msg = "请求失败";
		oms.tooltip(msg,"error");
		if(option.errorCallback && $.isFunction(option.errorCallback)) {
			option.errorCallback.call(ts, xhr, status, error, option.chainPar)
		}
	};
	if(option.async == null){
		option.async = true;
	}
	//ajax请求
	$.ajax({
		url: option.url,
		type: 'POST',
		data: option.params,
		dataType: 'json',
		timeout: oms.ajaxTimeout,
		error: _err,
		success: _succ,
		async:option.async
	});
}

/**
 * ajax form通用提交(封装jquery from插件)（改版后，方便参数拓展和调用）
 * auth:helong
 * time:2015-1-5
 * 
 * 参数:1.formId:form的id
 * 		2.beforeSubmitFn(formData, jqForm, options):提交前用于验证数据和组装自定义数据的自定义回调函数
 * 				formData: 数组对象，提交表单时，Form插件会以Ajax方式自动提交这些数据，格式如：[{name:user,value:val },{name:pwd,value:pwd}]  
 *			    jqForm:   jQuery对象，封装了表单的元素     
 *			    options:  options对象  
 * 		3.successCallback(data,status,xhr)：ajax访问成功后回调函数
 *      4.errorCallback(xhr,status,error)：ajax访问失败后回调函数
 *      5.successTip：是否提示成功信息 true-提示 false-不提示
 *      6.@param async 是否异步
 * 		7.@param bizErrTip 是否错误提示(默认或者false的时候提示，true的时候不提示)
 *      8.@param bizErrCallback 错误回调函数
 *      9.@param isMask 是否加遮罩层
 *      10.@param timeout 如果不传 默认取 oms.ajaxTimeout
 * 
*/
oms.ajaxFormCall = function(option){
	if(option.isMask) {
		oms.LayerShow("正在加载中...");
	}
	var ts = this;
	var _succ = function(data, status, xhr) {
		if(option.isMask) {
			oms.LayerHide();  //回调函数去掉遮罩层
		}
		if(!data) {
			var msg = "未知错误";
			oms.tooltip(msg,"error");
			return;
		}else if(data.overtime) {//超时
			top.location.href = oms.bathPath + "/login.jhtml";
			return;
		}else if(data.success) {
			if(option.successTip) {
				var msg = "交易成功";
			
				if(data.errMsg!="") {
					msg = data.errMsg;
				}
				oms.tooltip(msg,"succeed");
			}
			if(option.successCallback && $.isFunction(option.successCallback)) {//是否存在回调函数
				return option.successCallback.call(ts, data, status, xhr, option.chainPar);
			}
		}else if(!data.success) { //交易失败
			if(!option.bizErrTip || typeof(option.bizErrTip)=="undified" || option.bizErrTip=="") {
				var msg = "交易失败";
				if(data.errMsg!="") {
					msg = data.errMsg;
				}
				oms.tooltip(msg,"error");
			}
			if(option.bizErrCallback && $.isFunction(option.bizErrCallback)) {//是否存在回调函数
				return option.bizErrCallback.call(ts, data, status, xhr);
			}
		}else {// 交易失败
			oms.tooltip(data.errMsg,"error");
		}
	};
	
	var _err = function(xhr, status, error){
		if(option.isMask) {
			oms.LayerHide();  //回调函数去掉遮罩层
		}
		var msg = "请求失败";
		oms.tooltip(msg,"error");
		if(option.errorCallback && $.isFunction(option.errorCallback)) {
			option.errorCallback.call(ts, xhr, status, error);
		}
	}
	
	var _timeout;
	if(option.timeout){
		_timeout=option.timeout;
	}else{
		_timeout=oms.ajaxTimeout;
	}
	
	
	$('#'+option.formId).ajaxSubmit({
	   beforeSubmit: option.beforeSubmitFn, //提交前的回调函数  
	   success: _succ,               //提交成功的回调函数  
	   error:_err,                   //提交失败的回调函数
	   type: "POST",                 //默认是form的method（get or post），如果申明，则会覆盖  
	   dataType: "json",             //html(默认), xml, script, json...接受服务端返回的类型  
	   timeout:_timeout   //限制请求的时间，当请求大于3秒后，跳出请求  
	});
	return false;  //阻止表单默认提交
}
