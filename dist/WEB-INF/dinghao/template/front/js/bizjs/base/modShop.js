/******************************************************************************
函数名称:   <ATFunc></ATFunc>
函数功能:   <ATFuncDesc>页面初始化 请求客户信息</ATFuncDesc>       
开发人员:   gucong
修改记录:
******************************************************************************/
$(function(){
	/*
	var options = {};
    options.params = {"id":$("#id").val()};
	options.url = base+"/template/custInfo/queryCustInfoById.jhtml";
	options.successCallback = queryCustBackFn;
	oms.ajaxCall(options);
	*/
}); 


/******************************************************************************
函数名称:   <ATFunc>saveShop</ATFunc>
函数功能:   <ATFuncDesc>修改店铺信息</ATFuncDesc>       
开发人员:   gucong
修改记录:
******************************************************************************/
function saveShop(){
	var shop = {};
	shop.id= $.trim($("#id").val());
	shop.name= $.trim($("#name").val());
	shop.nickname=$.trim($("#nickname").val());
	shop.planType=$.trim($("#planType").val());
	shop.phone=$.trim($("#phone").val());
	shop.mobile=$.trim($("#mobile").val());
	shop.zipcode=$.trim($("#zipcode").val());
	shop.beactive=$("input[name=beactive]").filter(':checked').val(); 

	shop.expid=$.trim($("#expid").val());
	shop.printId=$.trim($("#printId").val());
	shop.warehouseId=$.trim($("#warehouseId").val());
	shop.appkey=$("#appkey").val();
	shop.appsecret=$.trim($("#appsecret").val());
	shop.sessionkey=$.trim($("#sessionkey").val());
	shop.address=$.trim($("#address").val());//$("input[name=status]").filter(':checked').val();
	
	var options = {};
    options.params = shop;
	options.url = base_template+"/shop/modShopMgr.jhtml";
	options.successCallback = modShopBackFn;
	oms.ajaxCall(options);
}

/******************************************************************************
函数名称:   <ATFunc>modShopBackFn</ATFunc>
函数功能:   <ATFuncDesc>修改回调函数</ATFuncDesc>       
开发人员:   gucong
修改记录:
******************************************************************************/
function modShopBackFn(data){
	oms.tooltip(data.errMsg , "succeed");
	/*
	artTabs({
	    toTab: {id : "shopMgr"},
	    isRefresh: true
	});
	
	artTabs({
	    toTab: {id : "modShop"},
	    isRefresh: true
	});
	*/
}