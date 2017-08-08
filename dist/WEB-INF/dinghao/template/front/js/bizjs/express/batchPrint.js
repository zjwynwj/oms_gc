var itemVals,propVals,templatePic;
var orderDataArray,senderData;
var LODOP = null;
var printerName = '';
var printTemplate;//打印模板
$(function(){
	 initTradeInfo($("#orderNums").val());//初始化  起始打印订单交易id 与 结束订单交易id
     var templateId=$("#printId").val()//获取模板id
   //根据物流公司id获取物流打印模板数据
 	 oms.ajaxCall({
 		 url :base_template+"/printMgr/queryPrintTemplate.jhtml",
 		 params: {
 			 "id":templateId,
 		 },
 		 successCallback:function(result){
 			 var  data=result.data;
 			 $("#templateName").html(data.name);
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

//直接打印按钮
function printBtnClick(){
	$.artDialog({
		title:'提示',
		content:'是否直接打印!',
		ok:function(){
			 //根据选中的订单id获取订单数据
			 oms.LayerShow("正在读取数据中...");
			 oms.ajaxCall({
				 url :base_template+'/orderMgr/queryOrderBatchPrintData.jhtml',
				 successTip:false,
				 isMask:false,
				 async:false, 
				 params: {
					 "ids":$("#orderIds").val()
				 },
				 successCallback:function(result){
					 var options = {};
					 options.chainPar = result.data
					 options.url = base+"/template/senderInfoMgr/querySenderInfo.jhtml";
					 options.successCallback = function(result , status, xhr, orderData){
						 orderDataArray=orderData;
						 senderData=result.data;
						 batchPrint();//开始打印
					 };
					 oms.ajaxCall(options);
				 },
				 bizErrCallback:function(){
						oms.LayerHide();  //回调函数去掉遮罩层
				 }
			 });
		}
	});
};

//批量打印功能
function batchPrint(){
	oms.LayerShow("正在打印中...");
	if(!orderDataArray){
		oms.LayerHide();  //回调函数去掉遮罩层
        return;
	};
	var count=0;
	
	for(var index in orderDataArray){
		var orderData=orderDataArray[index];
		if(!orderData){
			oms.LayerHide();  //回调函数去掉遮罩层
	        return;
		}
		toPrint(orderData,senderData);//
		count++;
	};
	returnPrintStatus();
};

//初始化  起始打印订单交易id 与 结束订单交易id
function initTradeInfo(orderNums){
	var orderNumArray = orderNums.split(",");
	var startOrder=orderNumArray[0];
	var endOrder=orderNumArray[orderNumArray.length-1];
	//起始订单交易单号
	$("#start_no").html(startOrder);
	$("#end_no").html(endOrder);
	$("#order_num").html(orderNumArray.length);
}

 //回填打印状态
function returnPrintStatus(){
	var ids=$("#orderIds").val();
	var options = {};
    options.params = {"ids":ids};
	options.url = base+"/template/orderMgr/savePrintStatus.jhtml";
	options.successCallback = function(data){
		oms.LayerHide();  //回调函数去掉遮罩层
		oms.tooltip("打印结束","succeed");
	    setTimeout('oms.s_Pop_closedChild()',500);//防止在页面关闭的时候   回填打印状态的请求仍然没有结束,延迟0.5秒执行
	    parent.refreshGrid();
	};
	oms.ajaxCall(options);
};

 