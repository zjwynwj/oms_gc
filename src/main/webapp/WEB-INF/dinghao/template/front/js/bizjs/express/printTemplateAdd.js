$(function(){
	//加载系统物流公司信息
	 addLogisInfo();//
	 CheckIsInstall();
     addPbText();//动态加载页面
     $("#template_width,#template_height").blur(function(){
			mmToPx();
	 });
});

//上传图片
function uploadImg(){
	if($("input[type=file]").val()==null||$("input[type=file]").val()==''){
		return false; 
	}
	oms.ajaxFormCall({
		formId:"addPic",
		successTip:true,
		successCallback:function(result){
			 var imgPath = base +"/"+result.data;
			 $("#templatePic").val(imgPath);
			 changeTemplatePic(imgPath);
		}
	})
	return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
};

//加载物流公司信息
function addLogisInfo(){
	oms.ajaxCall({
		url : base_template+'/expressMgr/getAllDmsExpress.jhtml',
		successCallback : function (result){
	        var resArray = result.data;
	        for(var i=0;i<resArray.length;i++){
	        	var v = resArray[i].id;
	        	var name = resArray[i].name;
	        	$("#logisId").append("<option value='"+v+"' >"+name+"</option>");
	        	
	        }
		}
	});
}

function doAddSubmit(){
	if($.trim($("#templateName").val()) ==""){
		oms.tooltip("请填写模板名称!","error");
		return;
	}
	doSubmit(null);
}