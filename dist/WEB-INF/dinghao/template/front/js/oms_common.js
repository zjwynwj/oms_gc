var oms = oms || {};
oms.grid = oms.grid || {};
oms.ajaxTimeout = 300000;

$(function(){
	oms.selFun('selAll');
})

/**
 * 功能说明: grid请求成功统一处理函数
 * @author helong
 * @date 2015-12-26 下午4:14:49
 * @version V1.0
 * @param data 数据
 * @param status 状态
 * @param xhr xhr信息
 */
oms.grid.ajaxSuccessFn = function(data, status, xhr) {
	var ts = this;
	if(!data) {
		/*var msg = "未知错误";
		alert(msg);*/
		return;
	}else if(data.success) {
		if(ts.p.successTip) {
			var msg = "交易成功";
			if(data.errMsg!="") {
				msg = data.errMsg;
			}
			alert(msg);
		}
		if(ts.p.successCallback && $.isFunction(ts.p.successCallback)) {
			ts.p.successCallback.call(ts, data, status, xhr);
		}
	}else {
		//alert(data.errMsg);
	}
}

/**
 * 功能说明: grid请求失败统一处理函数
 * @author helong
 * @date 2015-12-26 下午4:14:49
 * @version V1.0
 * @param xhr xhr信息
 * @param status 状态
 * @param error 错误信息
 */
oms.grid.ajaxErrorFn = function(xhr, status, error) {
	var ts = this;
	var msg = "请求失败";
	alert(msg);
	if(ts.p.errorCallback && $.isFunction(ts.p.errorCallback)) {
		ts.p.errorCallback.call(ts, xhr, status, error);
	}
}

/**
 * jqgrid在窗口改变时更改宽度
 * 
 * mdetailconWidth,根据.m-detail-con来计算宽度
 * windowWidth，根据window来计算宽度
*/
oms.grid.mdetailconWidth=oms.grid.windowWidth=function(id,mh){
	mh=mh||25;
	$("#"+id).setGridWidth($(window).width()-mh);
}

/**
 * jqgrid在窗口改变时更改宽度
 * 
 * mdetailconWidth,根据.m-detail-con来计算宽度
 * windowWidth，根据window来计算宽度
*/
oms.grid.mdetailconWidth=function(id){
	$("#"+id).setGridWidth($('.m-detail-con').width());	
}
oms.grid.windowWidth=function(id){
	$("#"+id).setGridWidth($(window).width());	
}
/**
 * 弹出层
 * auth:tonyt
 * time:2015-2-15
 * 
 * 参数:1.vTitle：弹出层命名
 * 		2.vURL: url
 * 		3.triggerId：ID
 *		4.vWidth:宽度	  
 *		5.vHeight:高度
 * 		
*/
/* ---------------  弹出层 begin -------------------------*/
//参数说明----vTitle:标题，vURL：指向的url，triggerId：id,vWidth:宽度，vHeight:高度，vp:其他参数
//{isRefresh:true,isChild:true,isChangeClose:true,position:relative}
//isRefresh----是否刷新父页面
//isChild----是否是主页面
//isChangeClose---是否关闭按钮重写方法
//position--弹出层定位方式：relative/fixed/static/inherit
oms.s_addPop = function(vTitle,vURL,triggerId,vWidth,vHeight,vp){	
	var isRefresh=( vp ? vp.isRefresh : false )|| false;
	var isChild=( vp ? vp.isChild : false )|| false;	
	var p = vp ? vp.position : "absolute";
	var isChangeClose=( vp ? vp.isChangeClose : false )|| false;	
	var creatPop = function(vWidth,vHeight){
		var width = vWidth || 820;
		var height = vHeight || 460;
		Top = Topsize(height,p);		
		var conHeight = height - 36;
		var windowWidth = $(window).width();
		var s_popHtml = ''; //弹出框内容开始创建
		s_popHtml += '<div class="m-sPopBg" id="popBg"></div>' //创建遮罩层
		s_popHtml += '<div class="m-sPopCon" id="popCon" style="width:' + width + 'px;position:'+p+'; height:'+ height +'px; left:'+ (windowWidth-width) / 2 +'px; top:'+ Top +'px;"><div class="m-sPopTitle"><strong>';
		s_popHtml += vTitle;//标题
		s_popHtml += '</strong>';
		s_popHtml += '<b id="sPopClose" class="m-sPopClose">' + '×' + '</b>'
		s_popHtml += '</div><div id="iframeDiv" class="m-sPopContent" style="height:' + conHeight + 'px;">';
		s_popHtml += ''//导入内容
		s_popHtml += '</div></div>';
		if (!triggerId || triggerId == '') {
			$('body').append(s_popHtml); //父级显示弹出窗口
			$("#popBg,#popCon").show();
			//alert(vURL);
			$('#iframeDiv').append('<iframe scrolling="auto" frameborder="0" class="m-frameCon"></iframe>');
			$('.m-frameCon').attr('src', vURL); //兼容IE6下iframe调不进去问题
			Resize(); //上下高度自适应
			if(!isChangeClose)
			{				
				$('#sPopClose').unbind("click").bind("click",function () {
					oms.s_Pop_closedParent(isRefresh,isChild);
				});
			}
			
		}
		return s_popHtml;
	}
	function Topsize(height,p){	
		var windowHeight = $(window).height();//当前窗口高度
		var scrollTop = $(document).scrollTop();/*垂直方向滚动的值*/
		var offsetHeight  = (windowHeight - height) / 2;
		return p=="fixed" ? offsetHeight : scrollTop + offsetHeight;
	}
	function Resize(){
		$(window).resize(function(){
			var height = $("#popCon").height();
			Top = Topsize(height);
			$('#popCon').css({"top":Top+"px"});
		});
	}
	if(triggerId) {
		$('#' + triggerId).click(function(){
			$("#popCon,#popBg").remove();
			//$('html').addClass("scroll");
			var popWin = creatPop(vWidth,vHeight);
			$('body').append(popWin);//父级显示弹出窗口
			$("#popBg,#popCon").show();
			$('#iframeDiv').append('<iframe scrolling="auto" frameborder="0" class="m-frameCon"></iframe>');	
			$('.m-frameCon').attr('src',vURL);  //兼容IE6下iframe调不进去问题
			Resize(); //上下高度自适应
			if(!isChangeClose)
			{
				$('#sPopClose').unbind("click").bind("click",function () {
					oms.s_Pop_closedParent(isRefresh,isChild);
				});
			}
		})
	} else {
		creatPop(vWidth, vHeight);
	};
}
//参数说明----isRefresh：是否刷新页面。isChild：要刷新的页面是否是子页面（可参考ebusi的订单发货，弹出层在在父页面上）
oms.s_Pop_closedChild = function (isRefresh,isChild){
	if(isRefresh)
	{		
		if(isChild) {
			parent.window.frames[0].location.reload();
		}
		else parent.window.location.reload();
	}
	$("#popCon,#popBg",parent.document).remove();	
}
oms.s_Pop_closedParent = function (isRefresh,isChild){
	if(isRefresh)
	{		
		if(isChild) {
			window.frames[0].history.go(0); 
		}
		else window.location.reload();
	}				
	$("#popCon,#popBg").remove();
}

/* ---------------  弹出层 end -------------------------*/


/*----------- 展开内容  begin -----------*/
oms.showAdv = function (advId,isCon){
	var $this = $('#'+advId);
	//解决不用文字替换的展开
	if(isCon){
		if($this.hasClass('open')){
			$('#'+ advId + 'Con').hide();
			$this.removeClass('open');
		}else{
			$('#'+ advId + 'Con').show();
			$this.addClass('open');
		}
	}else{
		if($this.hasClass('open')){
			$('#'+ advId + 'Con').hide();
			$this.text("展开高级搜索");
			$this.removeClass('open');
		}else{
			$('#'+ advId + 'Con').show();
			$this.text("收起高级搜索");
			$this.addClass('open');
		}
	}
}
oms.showAdvClose = function (getThis){	
	var getId = getThis.closest('div.m-toolbar').find('div.m-toolbar-adv').attr('id');
	var i = getId.indexOf('Con');
	var aClickId = getId.substring(0,i);	
	$('#'+ aClickId + 'Con').hide();
	$('#'+ aClickId).text("展开高级搜索");
	$('#'+ aClickId).removeClass('open');
}

/*----------- 展开内容 end -----------*/

/*----------- 内容切换 begin -----------*/
oms.cutCon = function(myId,way,className){ //传入需要切换的父级ID、切换方式（onmouseover/onclick）
	var $navSub = $('#' + myId).children('li');
	if (way == 'click') {
		$navSub.click(function(){
			value = $navSub.index(this);
			ReadSub(myId,className,$navSub,value);	
		})
	}
	if (way == 'mouseover') {	
		$navSub.mouseover(function(){
			value = $navSub.index(this);
			ReadSub(myId,className,$navSub,value);
		})
	}
}
function ReadSub(myId,className,$navSub,value){
	$('#' + myId + 'Con' + value).show().siblings('div').hide();
	$navSub.eq(value).addClass(className).siblings().removeClass(className);
}
/*----------- 内容切换 end -----------*/

/*----------- 下拉选择操作 begin -----------*/
oms.selFun = function(id,time){
	var time = time || 200;
	var $this = $('#'+id);
	$this.click(function(){
		if($this.parent().hasClass('open')){
			$this.next('ul').slideUp(time,function(){
				$this.parent().removeClass('open');	
			});
		}else{
			$this.next('ul').slideDown(time,function(){
				$this.parent().addClass('open');	
			});
		}		
	})	
	$(document).click(function(event){
		var evtEle = $(event.target).closest("a");
		if(evtEle.attr("id") == id){
			return;
		}else{
			if($this.parent().hasClass('open')){
				$this.next('ul').slideUp(time,function(){
					$this.parent().removeClass('open');	
				});
			}
		}
	});
}
/*----------- 下拉选择操作 end -----------*/

/*----------- select动态导入 begin -----------*/
$(function(){
	var getSelArr = $('input[name="selectData"]');
	for(var i=0; i<getSelArr.length; i++){
		var selId = getSelArr.eq(i).attr('selId');
		var selUrl = getSelArr.eq(i).attr('selUrl');
		var selClassName = getSelArr.eq(i).attr('className') || 'u-select-nm';
		oms.strSelect(getSelArr.eq(i),selId,selUrl,selClassName);
	}
})

oms.strSelect = function(myself,id,url,className){
	var str = '';
		str += '<select class="u-select '+className+'" id="'+ id +'"></select>';
		myself.after(str);
	$.ajax({
		type: "get",
  		url: url,
		dataType: "script",
		success: function(data){
			var jsonText = $.parseJSON(data);
			for(var i=0; i<jsonText.length; i++){
				var opt = '';
					opt += '<option value="'+ jsonText[i].key +'">'+ jsonText[i].value +'</option>';
					$('#'+id).append(opt);
					//console.log($('#'+id).find('option').length);
			}
		}
	})
}
/*----------- select动态导入 end -----------*/

//字符串格式的时间转换成时间戳
//dataStr 格式是yyyy-mm-dd
oms.strToDatetimeStamp = function(dataStr) {
	date = new Date(Date.parse(dataStr.replace(/-/g, "/")));
	return date.getTime();
}

//格式化时间格式
//date 时间
//formatStr 需要显示的格式，默认为yyyy-MM-dd
oms.formatDate = function(date, formatStr) {
	if('undefined'==date || ''==date || null==date) {
		return '';
	}
	if('undefined'==formatStr || ''==formatStr || null==formatStr) {
		return new Date(date).format("yyyy-MM-dd");
	}else {
		return new Date(date).format(formatStr);
	}
}

//格式化日期
Date.prototype.format = function(format) {
	var o = { 
		"M+" : this.getMonth()+1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
		"S" : this.getMilliseconds() //millisecond 
	} 

	if(/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 

	for(var k in o) { 
		if(new RegExp("("+ k +")").test(format)) { 
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
		} 
	} 
	return format; 
}


/**
* 	扩展String对象，实现得到DomStr中的属性值的方法
* （其中没有调用浏览器底层解析一个Dom元素的方法，不需要对浏览器做适配）
* @param attr:Dom元素中需要取得属性值的名字,默认取attr为'truevalue'
* @returns 返回一个字符串值,如果属性不存在返回 ""(空字符串而不是undefined)
*/
String.prototype.jqval =function(attr){
	var trueval="";
	if(this==""){
		return "";
	}else if(this.indexOf("<")==-1){//不是一个dom元素
		return this;
	}else {
		if(attr==undefined||attr==""){
			attr="truevalue";
		}
		var Str="";
		Str=this.replace(/"([^"]*)"/g, "'$1'");
		if($("#temInnerhtml").length<=0){
			$("body").append("<div id=\"temInnerhtml\" style=\"display:none;\"></div>");
		}		
		$("#temInnerhtml").append(Str);
		var obj=$("#temInnerhtml").children().eq(0);
		trueval=obj.attr(attr);
		$("#temInnerhtml").remove();
	}
	return trueval==undefined?"":trueval;		
}

/**
* 扩展String对象，去掉字符串中空格
*/
String.prototype.trim = function(){
  return this.replace(/(^\s*)|(\s*$)/g, "");
}

/**
* 扩展String对象，是否为空串
*/
String.prototype.isEmpty = function(){
  return this==undefined||this==null||this.trim()=="";
}

/**
* Jqgrid 输入框绑定事件，用于将jqgrid中input值保存到truevalue里，防止拿到jqgrid 拿到innerHtml时值丢失。
* 这里通常配合String.prototype.jqval一起使用
*/
oms.bindJqgridInputBlur = function(jqgrid){
	if(jqgrid.isEmpty()){
		return;
	}
	var inputs = $('#'+jqgrid).find('td').children("input[type=text]");
	//绑定获得焦点事件
	inputs.focus(function (){
		$(this).attr("style","width: 99%;");
	});
	//绑定失去焦点事件
	inputs.blur(function (){
		//保存值
		$(this).attr("truevalue",$(this).val());
		//改变样式
		$(this).attr("style","width: 99%;border: 0px;background: inherit;text-align: inherit;");
	});
	//触发失去焦点事件
	inputs.each(function(){
		$(this).blur();
	});		
}

/**
* 输入框绑定只限浮点数输入事件
*/
oms.bindAmtInputBlur=function(obj){
	//var obj=$("#"+inputId);
	obj.keyup(function (){
		if(event.keyCode>=35&&event.keyCode<=39){//排除一些位置控制按键
			;
		}else{
			var cursorPosition=$(this).cursorPosition();//拿到当前光标
			obj.val(obj.val().replace(/[^\d.]/g,""));  //清除“数字”和“.”以外的字符  
			obj.val(obj.val().replace(/^\./g,""));  //验证第一个字符是数字而不是. 
			obj.val(obj.val().replace(/\.{2,}/g,".")); //只保留第一个. 清除多余的. 
			var val=obj.val();
			val = val.replace(".","-").replace(/\./g,"").replace("-",".");
			obj.val(val);
			obj.cursorPosition(cursorPosition);//设置当前光标
		}					
	})
}

function clearNoNum(obj){   
	obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是. 
	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.   
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}

/**
* jquery 插件,获取输入框光标位置,设置光标位置
* @author oschina 博主（不好意思，忘了你的名字）
*/
$.fn.extend({
	cursorPosition:function( value ){
		var elem = this[0];
			if (elem&&(elem.tagName=="TEXTAREA"||elem.type.toLowerCase()=="text")) {
			   if($.browser.msie){
					   var rng;
					   if(elem.tagName == "TEXTAREA"){ 
						    rng = event.srcElement.createTextRange();
						    rng.moveToPoint(event.x,event.y);
					   }else{ 
					    	rng = document.selection.createRange();
					   }
					   if( value === undefined ){
					   	 rng.moveStart("character",-event.srcElement.value.length);
					     return  rng.text.length;
					   }else if(typeof value === "number" ){
					   	 var index=this.position();
						 index>value?( rng.moveEnd("character",value-index)):(rng.moveStart("character",value-index))
						 rng.select();
					   }
				}else{
					if( value === undefined ){
					   	 return elem.selectionStart;
					   }else if(typeof value === "number" ){
					   	 elem.selectionEnd = value;
     			         elem.selectionStart = value;
					   }
				}
			}else{
				if( value === undefined )
				   return undefined;
			}
	}
})

/*
 * 设置输入域(input/textarea)光标的位置
 * @param {HTMLInputElement/HTMLTextAreaElement} elem
 * @param {Number} index
 */
function setCursorPosition(elem, index) {
    var val = elem.value
    var len = val.length
 
    // 超过文本长度直接返回
    if (len < index) return
    setTimeout(function() {
        elem.focus()
        if (elem.setSelectionRange) { // 标准浏览器
            elem.setSelectionRange(index, index)   
        } else { // IE9-
            var range = elem.createTextRange()
            range.moveStart("character", -len)
            range.moveEnd("character", -len)
            range.moveStart("character", index)
            range.moveEnd("character", 0)
            range.select()
        }
    }, 10)
}

//金额格式化 补零  存入显示值 和  真实值
function formatMoney(t, perLength){
	$(t).val(parseFloat($(t).val().replace(/[^\d\.-]/g, ""))); 
	if($(t).hasClass("negative") && getIndexOfArray($(t).val(),"-")===0){
		$(t).attr('realMoney' , $(t).val());
		negativeFormatMoney($(t).val(),t);
		return false;
	}
	var perStr = '.';
	if(perLength != undefined && perLength != '' && perLength != null){
		for(i = 0; i < perLength; i++){
			perStr += '0';
		}
	}else{
		perStr += '00';
	}
	if($(t).val() == '' || $(t).val() == '0'){
		$(t).val('0' + perStr);
	}
	if($(t).val().indexOf('.') == -1){
		var s = $(t).val().replace(/\b(0+)/gi,"") + perStr;
		$(t).val(fmoney(s, perLength));
		$(t).attr('realMoney' , unFormatMoneyDfZero($(t).val()));
	}else{
		var c1 = $(t).val().split('.')[0];
		var c2 = $(t).val().split('.')[1];
		var c11 = c1.replace(/\b(0+)/gi,"") == ''?'0':c1.replace(/\b(0+)/gi,"");
		var c22 = c11 + '.'+c2;
		var rr;
		if(perLength != undefined && perLength != '' && perLength != null){
			rr = parseFloat(c22).toFixed(perLength);
		}else{
			rr = parseFloat(c22).toFixed(2);
		}
		$(t).val(fmoney(rr, perLength));
		$(t).attr('realMoney' , unFormatMoneyDfZero($(t).val()));
	}
}
//负数处理三位一逗
function negativeFormatMoney(v,t){
	var v1 = -v;
	var v2 = parseInt(v1);
	var v3 = String(v2);
	if((v3.length)%3 == 0){
	    $(t).val(fmoney(v, 2).replace(',',''));
	}else{
	    $(t).val(fmoney(v, 2));
	}
}

//金额格式化 每千位加逗号
function fmoney(s, n) {
	n = n > 0 && n <= 20 ? n : 2;
	s=s==undefined?"0":s;//修正fmoney对于异常数据的处理异常问题
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	t = "";
	for (i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}

//格式化金额，输入非金额返回0.00
function formatMoneyDfZero(mVal) {
	var mv = parseFloat(mVal);
	if(isNaN(mv)) {  //如果不是数字格式
		mVal = 0;
	}
	var val = fmoney(mVal, 2);
	return val;
}

//反格式化金额
function unFormatMoneyDfZero(fVal) {
	return parseFloat(fVal.replace(/[^\d\.-]/g, ""));
}

//绑定form表单的金额input和数量input事件
function bindMoneyAndAmountEvent(){
	$("input[realMoney]").each(function(){
		var negative=$(this).hasClass("negative");		
		$(this).val((parseFloat($(this).val())).toFixed(2));
		formatMoney(this, 2);
		$(this).bind('focus',function(){
			turnRealMoney(this);
		});
		$(this).bind('blur',function(){
			formatMoney(this, 2);
		});
		$(this).bind('keyup',function(e){
			onFormatPrefPer(this, 8,2 ,e);
		});
		$(this).bind('keypress',function(e){
			forbitten(e,negative);
		});
	});
	$("input[amountFlag]").each(function(){
		$(this).val((parseFloat($(this).val())).toFixed(2));
		if(isNaN($(this).val())){
			$(this).val("0.00");
		}
		$(this).bind('focus',function(){
			checkZeroAndShow(this);
		});
		$(this).bind('blur',function(){
			formatAmount(this, 2);
		});
		$(this).bind('keyup',function(e){
			onFormatPrefPer(this, 8,2,e);
		});
		$(this).bind('keypress',function(e){
			forbitten(e);
		});
	});
	$("input[justNumber]").each(function(){
		$(this).focus(function(){
			//checkZeroAndShow(this);
		});
		$(this).bind('keypress',function(e){
			if (navigator.userAgent.indexOf('Firefox') >= 0){
				if((e.which < 48 || e.which > 57 ) && e.which != 46  && e.which != 0  && e.which != 8){
					e.preventDefault();
				}
				if($(this).val().length >= 20 && e.which != 0  && e.which != 8){
					e.preventDefault();
				}
			}else{
				if($(this).val().length >= 20){
					event.returnValue=false;
				}
				if(event.keyCode < 48 || event.keyCode > 57 ){
					event.returnValue=false;
				}
			}
		});
		$(this).bind('keyup',function(e){
			justAllowNum(this , e);
		});
		$(this).bind('blur',function(e){
			$(this).val($(this).val().substring(0,20));
			var regStrs = ['[^\\d]+$', ''];
		    var reg = new RegExp('[^\\d]+$');
		    $(this).val($(this).val().replace(reg , ''));
		});
	});
}
//绑定jqgrid中编辑框input事件
function bindJqgridMoneyAndAmountEvent(item){
	$(item).val((parseFloat($(item).val())).toFixed(2));
	if(isNaN($(item).val())){
		$(item).val("0.00");
	}
	item.keyup(function(e){
		onFormatPrefPer(this, 8,2 ,e);
	});	
	item.focus(function(){
		checkZeroAndShow(this);
	});
	item.blur(function(){
		formatAmount(this, 2);
	});
	item.bind('keypress',function(e){
		forbitten(e);
	});
}
//绑定jqgrid中编辑框input事件
function bindJqgridJustNumberEvent(item){
	item.focus(function(){
		checkZeroAndShow(this);
	});
	item.bind('keypress',function(e){
		if (navigator.userAgent.indexOf('Firefox') >= 0){
			if((e.which < 48 || e.which > 57 ) && e.which != 46  && e.which != 0  && e.which != 8){
				e.preventDefault();
			}
			if($(item).val().length >= 20 && e.which != 0  && e.which != 8){
				e.preventDefault();
			}
		}else{
			if($(item).val().length >= 20){
				event.returnValue=false;
			}
			if(event.keyCode < 48 || event.keyCode > 57 ){
				event.returnValue=false;
			}
		}
	});
	item.bind('keyup',function(e){
		justAllowNum(item , e);
	});
	item.bind('blur',function(e){
		if($(item).val()==""){
			$(item).val(0);
		}
		$(item).val(unFormatMoneyDfZero($(item).val()));
		$(item).val($(item).val().substring(0,20));
		var regStrs = ['[^\\d]+$', ''];
	    var reg = new RegExp('[^\\d]+$');
	    $(item).val($(item).val().replace(reg , ''));
	});
}

function justAllowNum(item , e){
	if (navigator.userAgent.indexOf('Firefox') >= 0){
		if(e.which>=35&&e.which<=40 || e.which==8 || e.which==46){//排除一些位置控制按键
			return ;
		}	
	}else{
		if(event.keyCode>=35&&event.keyCode<=40 || event.keyCode==8 || event.keyCode==46){//排除一些位置控制按键
			return ;
		}	
	}
	var regStrs = ['[^\\d]+$', ''];
    var reg = new RegExp('[^\\d]+$');
    $(item).val($(item).val().replace(reg , ''));
}
//只能输入数字和小数点  il整数位长度  dl小数位长度
function onFormatPrefPer(th , il , dl ,e){
	var negative=$(th).hasClass("negative");	
	if (navigator.userAgent.indexOf('Firefox') >= 0){
		if(e.which>=35&&e.which<=40 || e.which==8 || e.which==46){//排除一些位置控制按键
			return ;
		}	
	}else{
		if(event.keyCode>=35&&event.keyCode<=40 || event.keyCode==8 || event.keyCode==46){//排除一些位置控制按键
			return ;
		}	
	}
	var cursorPosition=$(th).cursorPosition();//拿到当前光标
	var s1 = th.value;
	if(negative) $(th).val($(th).val().replace(/[^\d.-]/g,""));
	else
	$(th).val($(th).val().replace(/[^\d.]/g,""));  //清除“数字”和“.”以外的字符 	
	var s2 = th.value;
	cursorPosition = cursorPosition-(s1.length-s2.length);
	cursorPosition = (cursorPosition==0)?1:cursorPosition;
	var regStrs = negative ? [
	                 		['^0(\\d+)$', '$1'],
	                		['[^\\d\\.-]+$', ''],
	                		['\\.(\\d*?)\\.+', '.$1'],
	                		['^(\\d+\\.\\d{'+dl+'}).+', '$1'], 
	                		['^(\\.\\d{'+dl+'}).+', '0$1']
	                    ] : [
		['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0
		['[^\\d\\.]+$', ''], //禁止录入任何非数字和点
		['\\.(\\d*?)\\.+', '.$1'], //禁止录入两个以上的点
		['^(\\d+\\.\\d{'+dl+'}).+', '$1'], //禁止录入小数点后两位以上
		['^(\\.\\d{'+dl+'}).+', '0$1']     //首位为小数点时前面加零
  ];

	if(th.value.indexOf('.')>il || th.value.indexOf('.')==-1){
	   var subStr = th.value.split(".");
	   var str1 = subStr[0].substring(0,il);
	   var  str2 = subStr[1];
	   if(th.value.indexOf('.')==-1){
		   th.value = str1;
	   }else{
		 th.value = str1+"."+str2;
	   }
	   if(cursorPosition>il){
			cursorPosition=il;
	   }
	}
	     
  for(i=0; i<regStrs.length; i++){
      var reg = new RegExp(regStrs[i][0]);
      th.value = th.value.replace(reg, regStrs[i][1]);
  }
	setCursorPosition(th,cursorPosition);
}
//数量格式化 补零
function formatAmount(t, perLength){
	if(t.value == ""){
		t.value=0.00;
	}
	var perStr = '.';
	if(perLength != undefined && perLength != '' && perLength != null){
		for(i = 0; i < perLength; i++){
			perStr += '0';
		}
	}else{
		perStr += '00';
	}
	if($(t).val() == '' || $(t).val() == '0'){
		$(t).val('0' + perStr);
	}
	
	if($(t).val().indexOf('.') == -1){
		var s = $(t).val().replace(/\b(0+)/gi,"") + perStr;
		$(t).val(s);
	}else{
		var c1 = $(t).val().split('.')[0];
		var c2 = $(t).val().split('.')[1];
		var c11 = c1.replace(/\b(0+)/gi,"") == ''?'0':c1.replace(/\b(0+)/gi,"");
		var c22 = c11 + '.'+c2;
		var rr;
		if(perLength != undefined && perLength != '' && perLength != null){
			rr = parseFloat(c22).toFixed(perLength);
		}else{
			rr = parseFloat(c22).toFixed(2);
		}
		$(t).val(rr);
	}
}
function turnRealMoney(th){
	th.value = unFormatMoneyDfZero(th.value);
	setCursorPosition(th);
	if(parseFloat(th.value) == 0){
		th.value = "";
	}
}

function checkZeroAndShow(th){
	if($.trim(th.value) == ""){
		return;
	}
	th.value = unFormatMoneyDfZero(th.value);
	setCursorPosition(th);
	if(parseFloat(th.value) == 0){
		th.value = "";
	}
}

function setCursorPosition(elem, index) {
  var val = elem.value;
  var len = val.length;
	var index = index || len;
  // 超过文本长度直接返回
  if (len < index) return
	
	elem.focus();
	if (elem.setSelectionRange){ // 标准浏览器
		elem.setSelectionRange(index, index);    
	} else { // IE9-
		var range = elem.createTextRange();
		range.moveStart("character", -len);
		range.moveEnd("character", -len);
		range.moveStart("character", index);
		range.moveEnd("character", 0);
		range.select();
	}
 
}
/*e----e,allowNeg--是否允许有负数，默认为false*/
function forbitten(e,allowNeg){
	allowNeg=allowNeg||false;
	var a=allowNeg?((e.which < 48 || e.which > 57 ) && e.which != 45 && e.which != 46  && e.which != 0  && e.which != 8):
		((e.which < 48 || e.which > 57 ) && e.which != 46  && e.which != 0  && e.which != 8),
		b=allowNeg?((event.keyCode < 48 || event.keyCode > 57 ) && event.keyCode != 46 && event.keyCode != 45):
			((event.keyCode < 48 || event.keyCode > 57 ) && event.keyCode != 46);
	if (navigator.userAgent.indexOf('Firefox') >= 0){
		if(a){
			e.preventDefault();
		}
	}else{
		if(b){
			event.returnValue=false;
		}
	}
}

/******判断当前浏览器*********/
/*
* 返回值和对应的浏览器
* firefox ---火狐浏览器
* Opera------Opera浏览器
* chrome-----谷歌浏览器
* safari-----苹果浏览器
* 6----------IE6
* 7----------IE7
* 8----------IE8
* 9----------IE9
* 10---------IE10
* 11---------IE11
*/
oms.browserVerisionJudge=function(){
	var check = function(r) {
  	return r.test(navigator.userAgent.toLowerCase());
	};
	var isIe=function(){
 		return ("ActiveXObject" in window);
	};
	var _IE = (function(){//ie789
		var v = 3, div = document.createElement('div'), all = div.getElementsByTagName('i');
		while (
			div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->',
			all[0]
		);		
		return v > 4 ? v : false ;
	}());
	var	isWebkit = check(/webkit/) ? "webkit" : false,//不做判断暂时
		isFirefox = check(/firefox/) ? "firefox" : false,
		isOpera= check(/opr/) ? "Opera" : false,
		isChrome=!isOpera && check(/chrome/) ? "chrome" : false,
		isSafari=!isChrome && !isOpera && check(/safari/) ? "safari" : false,
		isIE10=!_IE &&  navigator.userAgent.indexOf("MSIE 10.0")!=-1 ? "10" : false,
		isIE11=!_IE && !isIE10 && "ActiveXObject" in window ? "11" : false;	
	return isFirefox || isOpera || isChrome ||  isSafari || _IE || isIE10 || isIE11;	
}

/** 
* 加法运算，避免数据相加小数点后产生多位数和计算精度损失。 
* 
* @param num1加数1 | num2加数2 
*/ 
oms.numAdd=function(num1, num2) { 
	var baseNum, baseNum1, baseNum2; 
	try { 
		baseNum1 = num1.toString().split(".")[1].length; 
	} catch (e) { 
		baseNum1 = 0; 
	} 
	try { 
		baseNum2 = num2.toString().split(".")[1].length; 
	} catch (e) { 
		baseNum2 = 0; 
	} 
	baseNum = Math.pow(10, Math.max(baseNum1, baseNum2)); 
	return (num1 * baseNum + num2 * baseNum) / baseNum; 
}
/** 
* 减法运算，避免数据相减小数点后产生多位数和计算精度损失。 
* 
* @param num1被减数 | num2减数 
*/ 
oms.numSub = function(num1, num2) { 
	var baseNum, baseNum1, baseNum2; 
	var precision;// 精度 
	try { 
		baseNum1 = num1.toString().split(".")[1].length; 
	} catch (e) { 
		baseNum1 = 0; 
	} 
	try { 
		baseNum2 = num2.toString().split(".")[1].length; 
	} catch (e) { 
		baseNum2 = 0; 
	} 
	baseNum = Math.pow(10, Math.max(baseNum1, baseNum2)); 
	precision = (baseNum1 >= baseNum2) ? baseNum1 : baseNum2; 
	return ((num1 * baseNum - num2 * baseNum) / baseNum).toFixed(precision); 
} 
/** 
* 乘法运算，避免数据相乘小数点后产生多位数和计算精度损失。 
* 
* @param num1被乘数 | num2乘数 
*/ 
oms.numMulti = function(num1, num2) { 
	var baseNum = 0; 
	try { 
		baseNum += num1.toString().split(".")[1].length; 
	} catch (e) { 
	} 
	try { 
		baseNum += num2.toString().split(".")[1].length; 
	} catch (e) { 
	} 
	return Number(num1.toString().replace(".", "")) * Number(num2.toString().replace(".", "")) / Math.pow(10, baseNum); 
} 
/** 
* 除法运算，避免数据相除小数点后产生多位数和计算精度损失。 
* 
* @param num1被除数 | num2除数 
*/ 
oms.numDiv = function(num1, num2) { 
	var baseNum1 = 0, baseNum2 = 0; 
	var baseNum3, baseNum4; 
	try { 
		baseNum1 = num1.toString().split(".")[1].length; 
	} catch (e) { 
		baseNum1 = 0; 
	} 
	try { 
		baseNum2 = num2.toString().split(".")[1].length; 
	} catch (e) { 
		baseNum2 = 0; 
	} 
	with (Math) { 
		baseNum3 = Number(num1.toString().replace(".", "")); 
		baseNum4 = Number(num2.toString().replace(".", "")); 	
		
		return numMulti((baseNum3 / baseNum4) , pow(10, baseNum2 - baseNum1));
	}
} 

/******************************************************************************
函数名称:   <ATFunc>SFlogis</ATFunc>
函数功能:   <ATFuncDesc>顺丰快递的物流单号生成规则</ATFuncDesc>       
输入参数: 
		    logisNum, selectNum:请求参数
返 回 值:    
开发人员:  
修改记录:
******************************************************************************/
function SFlogis(logisNum, selectNum){
	var array = new Array();
	var fri,Nfri,Yuandanhao;
	var num1,num2,num3,num4,num5,num6,num7,num8,num9,num10,num11,num12,Nnum1,Nnum2,Nnum3,Nnum4,Nnum5,Nnum6,Nnum7,Nnum8,Nnum9,Nnum10,Nnum11,Nnum12,mid;
	fri=logisNum.substring(0,11); 
	array.push(logisNum);
	Yuandanhao = logisNum;
	//alert(Yuandanhao.substring(0,1));
	
	for(var i=0;i<selectNum-1;i++){
		Nfri= (parseFloat(fri)+1).toString();
        num1= parseInt(Yuandanhao.substring(0, 1));
        num2= parseInt(Yuandanhao.substring(1, 2));
        num3= parseInt(Yuandanhao.substring(2, 3));
        num4= parseInt(Yuandanhao.substring(3, 4));
        num5= parseInt(Yuandanhao.substring(4, 5));
        num6= parseInt(Yuandanhao.substring(5, 6));
        num7= parseInt(Yuandanhao.substring(6, 7));
        num8= parseInt(Yuandanhao.substring(7, 8));
        num9= parseInt(Yuandanhao.substring(8, 9));
        num10= parseInt(Yuandanhao.substring(9, 10));
        num11= parseInt(Yuandanhao.substring(10, 11));
        num12= parseInt(Yuandanhao.substring(11, 12));  //12位没有，就11位，添加两个变量存储原始直
		
        Nnum1= parseInt(Nfri.substring(0, 1));
        Nnum2= parseInt(Nfri.substring(1, 2));
        Nnum3= parseInt(Nfri.substring(2, 3));
        Nnum4= parseInt(Nfri.substring(3, 4));
        Nnum5= parseInt(Nfri.substring(4, 5));
        Nnum6= parseInt(Nfri.substring(5, 6));
        Nnum7= parseInt(Nfri.substring(6, 7));
        Nnum8= parseInt(Nfri.substring(7, 8));
        Nnum9= parseInt(Nfri.substring(8, 9));
        Nnum10= parseInt(Nfri.substring(9, 10));
        Nnum11= parseInt(Nfri.substring(10, 11));
		
        if ((Nnum9-num9==1) && ((num9)%(2)==1)) 
        {
          if   (num12-8 >=0) 
             Nnum12=num12-8;             // -8
          else
             Nnum12= num12-8 +10;
        }
        else if ((Nnum9-num9==1) && ((num9)%(2)==0))  
        {
          if   (num12-7 >=0) 
             Nnum12=num12-7;             // -7
          else
             Nnum12= num12-7 +10;
        }
        else
        {
          if (((num10==3)||(num10==6))&&(num11==9)) 
          {
            if   (num12-5 >=0) 
               Nnum12=num12-5;             // -5
            else
               Nnum12= num12-5 +10;
          }
          else if   (num11==9) 
          {
            if   (num12-4 >=0) 
               Nnum12=num12-4;             // -4
            else
               Nnum12= num12-4 +10;
          }
          else
          {
            if   (num12-1 >=0 )
               Nnum12=num12-1 ;           // -1
            else
               Nnum12= num12-1 +10;
          };
        };
        array.push(Nfri+(Nnum12.toString()));
        Yuandanhao=Nfri+(Nnum12).toString();
        fri = (parseFloat(fri)+1).toString();
	}
	return array;		
}

/******************************************************************************
函数名称:   <ATFunc>emsLogis</ATFunc>
函数功能:   <ATFuncDesc>ems快递的物流单号生成规则</ATFuncDesc>       
输入参数: 
		    logisNum, selectNum:请求参数
返 回 值:    
开发人员:  
修改记录:
******************************************************************************/
function emsLogis(logisNum, selectNum){
	
	var array = new Array();
    var fri;
    var res,num3,num4,num5,num6,num7,num8,num9,num0,mid;
    fri=logisNum.substring(2,10);
    for(var i=0;i<selectNum;i++){
        num3=parseInt(fri.substring(0, 1));
        num4=parseInt(fri.substring(1, 2));
        num5=parseInt(fri.substring(2, 3));
        num6=parseInt(fri.substring(3, 4));
        num7=parseInt(fri.substring(4, 5));
        num8=parseInt(fri.substring(5, 6));
        num9=parseInt(fri.substring(6, 7));
        num0=parseInt(fri.substring(7, 8));
        mid= 8*num3+6*num4+4*num5+2*num6+3*num7+5*num8+9*num9+7*num0 ;
        res=11-(mid)%(11);
   
        if (res==10)
        	res=0;
        if (res==11)
        	res=5;
         //1、此处长度不足时前面需要根据情况补0  2、起始的EC 和CS请自行截取后拼接
          array.push((logisNum.substring(0,2))+fri+res.toString()+(logisNum.substring(11,13)));
          fri=(parseFloat(fri)+1).toString();	
    }
   // alert(array);
    return array;		
}

/******************************************************************************
函数名称:   <ATFunc>logis</ATFunc>
函数功能:   <ATFuncDesc>普通快递的物流单号生成规则</ATFuncDesc>       
输入参数: 
		    logisNum, selectNum:请求参数
返 回 值:    
开发人员:  
修改记录:
******************************************************************************/
function logis(logisNum, selectNum){
	var array = new Array();
	var fri,nfri;
	fri = logisNum;
	array.push(logisNum);
	for(var i=1;i<selectNum;i++){
		fri = (parseFloat(fri)+1).toString();
		array.push(fri);	
	}
	return array;
}

oms.isEmpty=function(str){
	if(typeof(str) == "undefined"||str===null||$.trim(str)==''||str=='undefined'||str=='null'){
		return true;
	}else{
		return false;
	}
}

/**数据字典取数，应用于单独的下拉框数据加载
*dictName:数据字典名称
*selVal：默认选中的val,如果改值不存在，默认选中第一条记录
*/
function getDictforSelect(dictName, selVal){
	var str="";
	var options = {};
    options.params = {"dictValue":dictName};
	options.url = base+"/template/dict/queryDictItem.jhtml";
	options.async = false;
	options.successCallback = function(result){
		var data = result.data;
		if (data.length != 0) {
			var strTemp="";
	        var flag = false;  //是否匹配到selVal，默认否
	        for(var i in data){
	        	if(data[i].dictitemValue == selVal) { //匹配到选中的值
	        		flag = true;
	        		break;
	        	}
	        }
	        for(var i in data){
	        	if(flag) {  //有选中项参数
	        		if(data[i].dictitemValue == selVal){
	        			strTemp += "<option value="+data[i].dictitemValue+" selected='true'>"+data[i].dictitemName+"</option>";
		            }else{
		            	strTemp += "<option value="+data[i].dictitemValue+">"+data[i].dictitemName+"</option>";
		            }
	        	}else {
	            	strTemp += "<option value="+data[i].dictitemValue+">"+data[i].dictitemName+"</option>";
	        	}
	        } 
	        str=strTemp;
	 	}
	}
	oms.ajaxCall(options);
	
	return str;
};

//数据字典取数，应用于grid下拉数据加载
function getDictGrid(dictName){
	var str="";
	var options = {};
    options.params = {"dictValue":dictName};
	options.url = base+"/template/dict/queryDictItem.jhtml";
	options.async = false;
	options.successCallback = function(result){
		var data = result.data;
		if (data.length != 0) {
			var strTemp="";
	        if (data.length != 0) {
	        	var length=data.length;
		        for(var i=0;i<length;i++){
		            if(i!=length-1){
		            	strTemp+=data[i].dictitemValue+":"+data[i].dictitemName+";";
		            }else{
		            	strTemp+=data[i].dictitemValue+":"+data[i].dictitemName;
		            	str=strTemp;
		            }
		        }   
			}
	 	}
	}
	oms.ajaxCall(options);
	return str;
}