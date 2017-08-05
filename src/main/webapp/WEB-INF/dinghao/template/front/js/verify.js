var v_Regular = {
	tel: /^((0[1-9]\d{1,2})|852|853|886|00852|00853|00886)-\d{7,8}(-\d{1,4})?$/,  //固话
	mobile: /^1(3\d|5[0-35-9]|8[025-9]|47)\d{8}$/,  //手机
	money: /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/,
	email: /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/,  
	url: /(http|https):\/\/[0-9a-zA-Z\-]+(\.[0-9a-zA-Z\-]+)+(:\d+)?(\/[^\/]+)*[\/]?/i,  
	myCard: /^((1[1-5])|(2[1-3])|(3[1-7])|(4[1-6])|(5[0-4])|(6[1-5])|71|(8[12])|91)\d{4}(((((19|20)((\d{2}(0[13-9]|1[012])(0[1-9]|[12]\d|30))|(\d{2}(0[13578]|1[02])31)|(\d{2}02(0[1-9]|1\d|2[0-8]))|(([13579][26]|[2468][048]|0[48])0229)))|20000229)\d{3}(\d|X|x))|(((\d{2}(0[13-9]|1[012])(0[1-9]|[12]\d|30))|(\d{2}(0[13578]|1[02])31)|(\d{2}02(0[1-9]|1\d|2[0-8]))|(([13579][26]|[2468][048]|0[48])0229))\d{3}))$/,
	postcode: /^[0-8]\d{5}$/,
	userName: /^[\u4E00-\u9FA5a-zA-Z][\u4E00-\u9FA5a-zA-Z0-9_]*$/
}

function getReadyverify(getThis){
	var obj = getThis.attr('verifyData');
	var getVal = getThis.val();
	getThis.siblings('div').remove();  //清除提示
	if(obj){
		obj = eval('(' + obj + ')');
		formValidator(getThis,obj,getVal);
	}
}

function formValidator($this,obj,getVal){
	switch(obj.type)
	{
	case "money":
		if(obj.required == "no" && getVal == ""){
			$this.removeClass('v_Error');
		}else{
			verifyMoney($this,getVal);
		}
		break;
	case "email":
		if(obj.required == "no" && getVal == ""){
			$this.removeClass('v_Error');
		}else{
			verifyEmail($this,getVal);
		}
		break;
	case "url":
		if(obj.required == "no" && getVal == ""){
			$this.removeClass('v_Error');
		}else{
			verifyUrl($this,getVal);
		}
		break;
	case "mobile":
		if(obj.required == "no" && getVal == ""){
			$this.removeClass('v_Error');
			$this.removeClass('v_Error');
		}else{
			verifyMobile($this,getVal);
		}
		break;
	case "tel":
		if(obj.required == "no" && getVal == ""){
			$this.removeClass('v_Error');
		}else{
			verifyTel($this,getVal);
		}
		break;
	case "myCard":
		if(obj.required == "no" && getVal == ""){
			$this.removeClass('v_Error');
		}else{
			verifymyCard($this,getVal);
		}	
		break;
	case "postcode":
		if(obj.required == "no" && getVal == ""){
			$this.removeClass('v_Error');
		}else{
			verifyPostcode($this,getVal);
		}		
		break;
	case "userName":
		var lenNum = getLen(obj.length);
		if(obj.required == "no" && getVal == ""){
			$this.removeClass('v_Error');
		}else if(getVal.length < lenNum.minLen || getVal.length > lenNum.maxLen){
			var left= $this.width();
			var Tip = '字符长度不正确';
			var showTip = creatText(Tip,left);
			$this.wrap('<div class="m-verify"></div>').after(showTip);
			$this.removeClass("v_Correct").addClass("v_Error");		
			return false;
		}else{
			verifyuserName($this,getVal);
		}		
		break;
	case "allcheck":
		var left = $this.width();
		if(obj.length){
			var lenNum = getLen(obj.length);
		}	
		if(obj.required == "no" && getVal == ""){
			$this.removeClass('v_Error');
		}else if(obj.length != null){
			if(getVal.length < lenNum.minLen || getVal.length > lenNum.maxLen){
				var Tip = '字符长度不正确';
				var showTip = creatText(Tip,left);
				$this.wrap('<div class="m-verify"></div>').after(showTip);
				$this.removeClass("v_Correct").addClass("v_Error");		
				return false;
			}else{
				$this.wrap('<div class="m-verify"></div>').after('<div style="left:'+ (left+8) +'px" class="m-tipShow-correct"></div>');
				$this.removeClass("v_Error").addClass("v_Correct");
			}				
		}else{
			verifyallcheck($this,getVal);
		}		
		break;
	}
}

//得到属性length的值
function getLen(Len){
	var cutlen = Len.indexOf(',');
	var minLen = Len.substring(0,cutlen);
	var maxLen = Len.substring(cutlen+1,Len.length);
	return {minLen:minLen,maxLen:maxLen};
}

//验证均通过(不验证格式)
function verifyallcheck($this,value){
	var left = $this.width();
	if(value == ""){
		var Tip = '不能为空';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else{
		$this.wrap('<div class="m-verify"></div>').after('<div style="left:'+ (left+8) +'px" class="m-tipShow-correct"></div>');
		$this.removeClass("v_Error").addClass("v_Correct");
	}	
}

//手机验证
function verifyMobile($this,value){
	var left = $this.width();
	if(value == ""){
		var Tip = '不能为空';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else if(!v_Regular.mobile.test(value)){
		var Tip = '手机号码输入有误,请重新输入';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else{
		$this.wrap('<div class="m-verify"></div>').after('<div style="left:'+ (left+8) +'px" class="m-tipShow-correct"></div>');
		$this.removeClass("v_Error").addClass("v_Correct");
	}
}
//金额验证
function verifyMoney($this,value){
	var left = $this.width();
	if(value == ""){
		var Tip = '不能为空';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else if(!v_Regular.money.test(value)){
	
		var Tip = '金额格式输入有误,请重新输入';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else{
		$this.wrap('<div class="m-verify"></div>').after('<div style="left:'+ (left+8) +'px" class="m-tipShow-correct"></div>');
		$this.removeClass("v_Error").addClass("v_Correct");
	}
}
//邮箱验证
function verifyEmail($this,value){
	var left = $this.width();
	if(value == ""){
		var Tip = '不能为空';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else if(!v_Regular.email.test(value)){
		var Tip = '邮箱格式输入有误,请重新输入';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else{
		$this.wrap('<div class="m-verify"></div>').after('<div style="left:'+ (left+8) +'px" class="m-tipShow-correct"></div>');
		$this.removeClass("v_Error").addClass("v_Correct");
	}
}
//URL验证
function verifyUrl($this,value){
	var left = $this.width();
	if(value == ""){
		var Tip = '不能为空';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else if(!v_Regular.url.test(value)){
		var Tip = 'URL格式输入有误,请重新输入';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else{
		$this.wrap('<div class="m-verify"></div>').after('<div style="left:'+ (left+8) +'px" class="m-tipShow-correct"></div>');
		$this.removeClass("v_Error").addClass("v_Correct");
	}
}
//固话验证
function verifyTel($this,value){
	var left = $this.width();
	if(value == ""){
		var Tip = '不能为空';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else if(!v_Regular.tel.test(value)){
		var Tip = '固话格式输入有误,请重新输入';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else{
		$this.wrap('<div class="m-verify"></div>').after('<div style="left:'+ (left+8) +'px" class="m-tipShow-correct"></div>');
		$this.removeClass("v_Error").addClass("v_Correct");
	}
}
//身份证验证
function verifymyCard($this,value){
	var left = $this.width();
	if(value == ""){
		var Tip = '不能为空';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else if(!v_Regular.myCard.test(value)){
		var Tip = '身份证格式输入有误,请重新输入';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else{
		$this.wrap('<div class="m-verify"></div>').after('<div style="left:'+ (left+8) +'px" class="m-tipShow-correct"></div>');
		$this.removeClass("v_Error").addClass("v_Correct");
	}
}
//邮编验证
function verifyPostcode($this,value){
	var left = $this.width();
	if(value == ""){
		var Tip = '不能为空';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else if(!v_Regular.postcode.test(value)){
		var Tip = '邮编格式输入有误,请重新输入';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else{
		$this.wrap('<div class="m-verify"></div>').after('<div style="left:'+ (left+8) +'px" class="m-tipShow-correct"></div>');
		$this.removeClass("v_Error").addClass("v_Correct");
	}
}

//用户名验证
function verifyuserName($this,value){
	var left = $this.width();
	if(value == ""){
		var Tip = '不能为空';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else if(!v_Regular.userName.test(value)){
		var Tip = '格式输入有误,请重新输入';
		var showTip = creatText(Tip,left);
		$this.wrap('<div class="m-verify"></div>').after(showTip);
		$this.removeClass("v_Correct").addClass("v_Error");
		return false;
	}else{
		$this.wrap('<div class="m-verify"></div>').after('<div style="left:'+ (left+8) +'px" class="m-tipShow-correct"></div>');
		$this.removeClass("v_Error").addClass("v_Correct");
	}	
}



//构造提示信息标签
function creatText(Tip,left){
	var left = left || 0;
	var strHtml = '';
		strHtml += '<div class="m-tipShow-error" style="left:'+ (left+8) +'px"><span></span><em>'+ Tip +'</em></div>';
	return strHtml;	
}

