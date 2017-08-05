var itemVals,propVals,templatePic;
var orderData,senderData;
var printTemplate;
var LODOP = null;
var printerName = '';
var pintType=null;//打印类型   2为普通订单 4为电子面单
 
$(function(){
     var templateId=$("#id").val();//获取模板id
 	 //根据物流公司id获取物流打印模板数据
 	 oms.ajaxCall({
 		 url :base_template+"/printMgr/queryPrintTemplate.jhtml",
 		 params: {
 			 "id":templateId,
 		 },
 		 successCallback:function(result){
 			 var  data=result.data;
 			 printTemplate=data;//打印模板数据
 			 pintType=data.type;
 			 //初始化数据
 			 $("#itemVals").val(data.itemVals);
 			 $("#propVals").val(data.propVals);
 			 $("#templatePic").val(data.templatePic);
 			 $("#template_width").val(data.templateDivW);//模板宽度
 			 $("#template_height").val(data.templateDivH);//模板高度
 			 var divH=parseInt(data.divH)*4/5;
 			 var divW=parseInt(data.divW)*4/5;
 			 $("#pb,#printPic").css({'width':divW+"px","height":divH+"px"});
 			 $("#printPic").attr("src",data.templatePicUrl);//模板高度
 			 addPbText();//动态加载页面
 	    	 showPanel($("#itemVals").val(),$("#propVals").val(),data.templatePic);
 	    	 
 	    	 if (LODOP == null) {
 	            LODOP = getLodop(document.getElementById('LODOP'), document.getElementById('LODOP_EM'));
 	            if ((LODOP == null) || (typeof (LODOP.VERSION) == "undefined")) {

 	            } else {
 	                LODOP.PRINT_INIT("打印");
 	                initPrintMachine();
 	                $('#printTip').html('<b>打印机</b>');
 	                if (printerName) {
 	                    $('#printerName option[value=' + printerName + ']').attr('selected', 'selected');
 	                }
 	            }
 	        }
 		 }
 	 });
 	
});

//直接打印按钮
function prntBtnClick(){
	 if(pintType&&pintType!=null){
		 
		 	 getExampleModel();
			 if(pintType=="2"){
				toPreviewElc(orderData,senderData,printTemplate);//电子模板
		     }else{
				toPreview(orderData,senderData);//电子模板
		     }
	 }else{
		 alert("打印模板未取得");
		 return;
	 }
	
}

//初始化打印机设备
function initPrintMachine() {
    var printerCount = LODOP.GET_PRINTER_COUNT();
    var machines = "<select name=\"printerName\" id=\"printerName\" style=\"width:200px;\"><option value=\"\">请选择打印机</option>";
    var printerM = '';
    for (var i = 0; i < printerCount; i++) {
        printerM = LODOP.GET_PRINTER_NAME(i);
        machines += "<option value='" + printerM + "'>" + printerM + "</option>";
    }
    machines += "</select>";
    document.getElementById("chooseMachine").innerHTML = machines;
}
 
function getExampleModel(){
	 orderData={
			topTids: "订单id",//	订单id  	 --隐藏数据
			/**1.卖家信息*/
			sellerMemo: "卖家备注",//	卖家备注  --来自订单
			/**2.买家信息*/
			recvname: "收件人姓名",//	收件人姓名 --来自订单
			custNick: "收件人昵称",//	收件人昵称 --来自订单
			recvmobile: "收件人手机",//	收件人手机 --来自订单
			recvphone: "收件人固话",//	收件人固话 --来自订单
			address: "收件人详细地址",//	收件人详细地址 --来自订单
			recvArea: "收件人省市区",//	收件人省市区
			city: "收件人市",//	收件人市 --来自订单
			zipcode: "收件人邮编",//	收件人邮编 --来自订单
			buyerMemo: "买家留言",
			
			/**3.订单信息*/
			orderNum: "订单编号",//	订单编号  	 --来自明细
			printDate: "打印日期",//打印日期
	};
	
	 senderData={
				senderName: "发件人姓名",	//发件人姓名
				mobile: "发件人手机",	//发件人手机
				telephone:"发件人固话",
				state: "发件人所在省",	
				city: "发件人城市",	//发件人城市
				district: "发件人所在区",	
				sendAddress: "发件人地址",	//发件人地址
				postcode: "发件人邮编",	//发件人邮编
				shopTitle: "发件人店铺名称",	//发件人店铺名称
				memo1: "备注1(发货人信息中设置)",	//备注1(发货人信息中设置)
				memo2: "备注2(发货人信息中设置)",	//备注2(发货人信息中设置)
				memo3: "备注3(发货人信息中设置)",	//备注3(发货人信息中设置)
				area  : "发件人省市区"//省-市-区
	 };
	
	
};
 
function removeChart(){
    try{
       $("body").empty();
       //解决SCRIPT5009: “__flash__removeCallback”未定义 
    }catch(e){
    }
  }
 