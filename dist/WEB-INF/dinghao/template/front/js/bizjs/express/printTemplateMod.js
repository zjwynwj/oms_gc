var templateId;//模板id
$(function(){
	 CheckIsInstall();
	 templateId=$("#id").val();//获取模板id
	 var logisId=$("#logisIdVal").val();//获取物流公司id
	 var logisName=$("#logisNameVal").val();
	 
	 if(!oms.isEmpty(logisId)){
		 $("#logisId").empty();
	     $("#logisId").append("<option value='"+logisId+"' >"+logisName+"</option>");
	 }
	
     addPbText();//动态加载页面
     $("#template_width,#template_height").blur(function(){
			mmToPx();
	 });
     
	//根据物流公司id获取物流打印模板数据
	oms.ajaxCall({
		 url :base_template+"/printMgr/queryPrintTemplate.jhtml",
		 params: {
			 "id":templateId,
		 },
		 successCallback:function(result){
			 var  data=result.data;
			 //初始化数据
			 templateId=data.id;
			 var type=data.type;//3,4 电子模板
			 if(type=="3"||type=="4"){
				 $("#preBtn").attr("href","javascript:toPreviewElc()");
			 }
			 $("#templateName").val(data.name);
			 $("#itemVals").val(data.itemVals);
			 $("#propVals").val(data.propVals);
			 $("#templatePic").val(data.templatePic);
			 $("#template_width").val(data.templateDivW);//模板宽度
			 $("#template_height").val(data.templateDivH);//模板高度
			 
			 if(!oms.isEmpty(data.logisId)){
				 $("#logisId").empty();
			     $("#logisId").append("<option value='"+data.logisId+"' >"+data.logisName+"</option>");
			 }
			 
			 setTemplateSize(data.templateDivW+":"+data.templateDivH);//重新设置大小
	    	 showPanel($("#itemVals").val(),$("#propVals").val(),data.templatePic);
		 }
	 });
	
}); 

//修改页面的提交保存
function doModSubmit(){
	if($.trim($("#templateName").val()) ==""){
		oms.tooltip("请填写模板名称!","error");
		return;
	}
	doSubmit(templateId)
};

//上传图片
function uploadImg(){
	if($("input[type=file]").val()==null||$("input[type=file]").val()==''){
		return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
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

//预览状态下   保存按钮无效
function destroyButton(){
	$("#btnSubmit").remove();
	$("input[type=file]").parent().remove();
}
