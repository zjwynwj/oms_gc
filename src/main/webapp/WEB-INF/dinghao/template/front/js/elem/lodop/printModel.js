var itemVals,propVals,templatePic;
var LODOP; //声明为全局变量 
var orderData,senderData;
var printTemplate;//打印模板
function prn1_preview(srcOrderData,srcSenderData) {
	orderData=srcOrderData;
	senderData=srcSenderData;
	printModel();	
	LODOP.PRINTA();//打印
};
function toDesign(srcOrderData,srcSenderData){
	orderData=srcOrderData;
	senderData=srcSenderData;
	printModel();	
	LODOP.PRINT_DESIGN();		
	
}
//预览
function toPreview(srcOrderData,srcSenderData) {
	orderData=srcOrderData;
	senderData=srcSenderData;
	printModel();	
    LODOP.PREVIEW();//预览	
};

//电子面单
function toPreviewElc(srcOrderData,srcSenderData,srcPrintTemplate) {
	orderData=srcOrderData;
	senderData=srcSenderData;
	printTemplate=srcPrintTemplate;
	printModel(true);	
    LODOP.PREVIEW();//预览	
};


//直接打印物流单模板	
function toPrint(srcOrderData,srcSenderData) {
	orderData=srcOrderData;
	senderData=srcSenderData;
	printModel();	
//	LODOP.PRINTA();//选择打印机
  LODOP.PREVIEW();//预览
//    LODOP.PRINT();//直接打印
};

//直接打印电子面单	
function toPrintElc(srcOrderData,srcSenderData,srcPrintTemplate) {
	orderData=srcOrderData;
	senderData=srcSenderData;
	printTemplate=srcPrintTemplate;
	printModel(true);	
//	LODOP.PRINTA();//选择打印机
    LODOP.PREVIEW();//预览
   // LODOP.PRINT();//直接打印
};

/**
 * @param isElc  是否电子面单  true表示需要进行背景图片的打印
 */
function printModel(isElc){
	
	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM')); 
	
	if(orderData){
		LODOP.PRINT_INIT("打单任务"+orderData["tbId"]);
	}else{
		LODOP.PRINT_INIT("打印任务"+new Date().getTime());
	}
	
	var printName=$("#printerName").val();
	if (printName!=null&&printName != '') {
	        LODOP.SET_PRINTER_INDEX(printName); //设置选择后的打印机     
    }
	
//    var seqid = 1;//打印元素的序号
	if(isElc){
		//添加背景图片
		if(printTemplate){
			var templatePicUrl= printTemplate.templatePicUrl;
			var divW=printTemplate.divW;
			var divH =printTemplate.divH;
//			LODOP.ADD_PRINT_IMAGE(0,0,divW,divH,"<img border='0' src='"+templatePicUrl+"' width='"+divW+"' height='"+divH+"'   />");
			LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='"+templatePicUrl+"'    />");
			LODOP.SET_SHOW_MODE("BKIMG_WIDTH",divW);
			LODOP.SET_SHOW_MODE("BKIMG_HEIGHT",divH);
			LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW",true);
			LODOP.SET_SHOW_MODE("BKIMG_PRINT",true);
		}else{
			var img=$("#printPic");
			var templatePicUrl=img.attr("src") ;
			var divW=img.width();
			var divH =img.height();
//			LODOP.ADD_PRINT_HTM(0,0,"100mm","180mm","<img border='0' src='"+templatePicUrl+"' width='"+divW+"' height='"+divH+"'   />");	
//			LODOP.SET_PRINT_STYLEA(1,"Stretch",1);
			LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='"+templatePicUrl+"'    />");
			LODOP.SET_SHOW_MODE("BKIMG_WIDTH",divW);
			LODOP.SET_SHOW_MODE("BKIMG_HEIGHT",divH);
			LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW",true);
			LODOP.SET_SHOW_MODE("BKIMG_PRINT",true);
		}
//		seqid=2;

	}
	
	/*LODOP.ADD_PRINT_RECT(70,27,634,242,0,0);
	LODOP.SET_PRINT_STYLE("FontSize",18);
	LODOP.SET_PRINT_STYLE("Bold",1);
	LODOP.SET_PRINT_PAGESIZE(2);
	//LODOP.ADD_PRINT_TEXT(50,231,260,39,"打印页面部分内容");
	LODOP.ADD_PRINT_HTM(88,10,550,600,document.getElementById("all_width").innerHTML);*/
	
	/*
	var x_px =100;//x轴偏移量
	var y_px = 18;//y轴偏移量
	var x_tag = "-";//- 向左 ; + 向右
	var y_tag = "-";//- 向上 ; + 向下
	 */	
	//初始化偏移量
	if ($('#x_mm').val() == "") {
        $('#x_mm').val(0);
    }
    if ($('#y_mm').val() == "") {
        $('#y_mm').val(0);
    }
    //获取偏移量
	var x_px=getPxNum($('#x_mm').val());
	var y_px=getPxNum($('#y_mm').val());
	var x_tag= $('input:radio[name=p_x]:checked').val();
    var y_tag= $('input:radio[name=p_y]:checked').val();
   //偏移量不存在时  设置默认值
    x_px=x_px?x_px:0;
    y_px=y_px?y_px:0;
    x_tag=x_tag?x_tag:"+";
    y_tag=y_tag?y_tag:"+";
    
	var fontname = 'simsun';
    var fontsize = 12;
	var fontbold=0;	//加粗显示 1加粗,0正常显示
    LODOP.SET_PRINT_STYLE("FontSize", fontsize);
    LODOP.SET_PRINT_STYLE("Bold", fontbold);	//加粗显示 1加粗,0正常显示
	
    itemVals= $("#itemVals").val();
    propVals= $("#propVals").val();
    if (itemVals&&itemVals != '') {
    	  var arrVal = itemVals.split(";");//值
          var arrVal1 = propVals.split(";");//字体属性
    	
          var fontName1 = fontname;
          var fontSize1 = fontsize;
          var fontBold1 = fontbold;
          var strFontName = "宋体";
          //组装打印数据
          for (var i = 0; i < arrVal.length; i++) {
              var temp = arrVal[i].split(",");
              var id = temp[0];//显示值
              if (id==null||id == "") {
                  continue;
              }
              var isBarCode=false;//是否条形码打印
              if(id.indexOf("bar_")>=0){
            	  //表示需要生成二维码
            	  isBarCode=true;
                  id=id.replace("bar_","")
              }
              
              id=id.replace("_copy","")
              
              var show = temp[1];//是否显示
              var left = temp[2];//左边距离
              var top = temp[3];//上距离
              var width = temp[4];//宽
              var height = temp[5];//高
              var val = "";//显示值
              if(orderData==null){
            	  val=id;
              }else{
            	  val=orderData[id];
            	  if(oms.isEmpty(val)){
            		  val=senderData[id];
            	  }
              }
              val=replaceTag(val);//替换换行
              if(oms.isEmpty(val)){val=""};
              if (show == 1) {
                  if (propVals != '') {
                      var temp1 = arrVal1[i].split(",");
                      fontName1 = temp1[1];
                      fontSize1 = temp1[2];
                      fontBold1 = temp1[3];
                  }

                  left = countNum(parseInt(left), parseInt(x_px), x_tag);
                  top = countNum(parseInt(top), parseInt(y_px), y_tag);

                  if (fontName1 == "simsun") {
                      strFontName = "宋体";
                  } else {
                      strFontName = "黑体";
                  }
//                   console.log(top, left, width, height, val);
                  if(isBarCode){
              		LODOP.ADD_PRINT_BARCODE(top, left, width, height,"128A",val);
                    LODOP.SET_PRINT_STYLEA(0, "FontSize", 8);
                  }else{
                	LODOP.ADD_PRINT_TEXT(top, left, width, height, val);
            	    LODOP.SET_PRINT_STYLEA(0, "FontName", strFontName);
                    LODOP.SET_PRINT_STYLEA(0, "FontSize", fontSize1);
                    LODOP.SET_PRINT_STYLEA(0, "Bold", fontBold1);
                  }
               
//                  seqid++;
              }
          }
    	
    	
    }
    
    var divW=getPxNum($('#template_width').val());
    var divH=getPxNum($('#template_height').val());
    LODOP.SET_PRINT_PAGESIZE(1, divW+ "px", divH + "px", '');
    //LODOP.PREVIEW();//预览	
    
};	

//切换模板
function chooseTemplate(obj){
	//三个参数：itemVals/propVals,templatePic 从数据库读取	
	var n=$("#itemVals").val(),m=$("#propVals").val(),s=$("#templatePic").val();
	showPanel(n,m,s);
};
//更改背景图片
function changeTemplatePic(src){
	$("#printPic").attr("src",src);
};
//保存到数据库
function doSubmit(templateId){
	var arrChk = document.getElementsByName("chk");
    var items = "";
    var props = "";
    for (var i = 0; i < arrChk.length; i++) {
        var chk = arrChk[i];
        var b = 0;
        if (chk.checked == true)
            b = 1;
        else
            b = 0;
        items += chk.id + "," + b + "," + chk.value + ";";
        props += document.getElementById("input_" + chk.id).value + ";";
    }
	$("#itemVals").val(items);
	$("#propVals").val(props);
	
	var params={
			'id': templateId,
            'tval': $('#template').val(),
            'templatePic': $('#templatePic').val(),
            'name': $('#templateName').val(),
            'logisId': $('#logisId').val(),
            'logisName': $("#logisId option:selected").text(),
            'texpCom': $('#expressCompany').val(),
            'templateDivW': $('#template_width').val(),
            'templateDivH': $('#template_height').val(),
            'divW': getPxNum($('#template_width').val()),
            'divH': getPxNum($('#template_height').val()),
            'itemVals': $('#itemVals').val(),
            'propVals': $('#propVals').val()	
	};
	
	var url="";
	if(oms.isEmpty(templateId)){
		params["type"]="1";
		url=base_template+"/printMgr/addPrintTemplate.jhtml";//新增
	}else{
		url=base_template+"/printMgr/modPrintTemplate.jhtml";//修改
	}
	oms.ajaxCall({
		 url :url,
		 params: params,
		 successTip:true,
		 successCallback:function(){
			 /*
			artTabs({
			    toTab: {id : "printTemplateMgr"},
			    closeTab: true,
			    isRefresh: true
			});
			*/
		 }
	 });
	
};
//根据总体条件显示相应的选项
function showPanel(n,m,s){
	if (n != '') {
		var arrVal = n.split(";");
		var arrVal1 = m.split(";");
		for (var i = 0; i < arrVal.length; i++) {
			var temp = arrVal[i].split(",");
			var id = temp[0];
			if (document.getElementById(id) == null) {
				continue;
			}
			var show = temp[1];
			var left = temp[2];
			var top = temp[3];
			var width = temp[4];
			var height = temp[5];
			var fontName = 'simsun';
			var fontSize = 10;
			var fontBold = 0;
			if (m != '') {
				var temp1 = arrVal1[i].split(",");
				fontName = temp1[1];
				fontSize = temp1[2];
				fontBold = temp1[3];
			}
			setItem(id, show, width, height, left, top, fontName, fontSize, fontBold); //设置选择框
		}
	}
	$("#printPic").attr("src",s)
};
function setItem(id, show, w, h, l, t, name, size, bold)
{
    var obj = document.getElementById(id);
    var objDiv = document.getElementById("div_" + id);
    var o = document.getElementById("font_" + id);
    var o1 = document.getElementById("input_" + id);
    if (w == "")
    {w = 120;
    } else {
        w = parseInt(w);
    }
    if (h == "")
    {h = 25;
    } else {
        h = parseInt(h);
    }
    l = parseInt(l);
    t = parseInt(t);
    if (show == 1) {
        o1.value = id + "," + name + "," + size + "," + bold;
        o.style.fontFamily = name;
        o.style.fontSize = size + "pt";
        var sb = "normal";
        if (bold == 1) {
            sb = "bold";
        }
        o.style.fontWeight = sb;

        obj.checked = true;
        obj.value = l + "," + t + "," + w + "," + h;
        objDiv.style.display = "";
        objDiv.style.left = l + "px";
        objDiv.style.top = t + "px";
        objDiv.style.width = w + "px";
        objDiv.style.height = h + "px";
    } else {
        obj.checked = false;
        objDiv.style.display = "none";
    }	
};
//点击check 显示/隐藏div
function showDiv(obj) {
    var id = obj.id;
    var flag = obj.checked;
    var chkVal = obj.value;
    var arrVal = chkVal.split(",");
    x = parseInt(arrVal[0]);
    y = parseInt(arrVal[1]);
    var objDiv = document.getElementById("div_" + id);
    if (flag) {
        objDiv.style.display = "";
        objDiv.style.left = x + "px";
        objDiv.style.top = y + "px";
    } else {
        objDiv.style.display = "none";
    }
};
//将点击后的字体设置传回
function setId(id)
{
    var o1 = document.getElementById("input_" + id).value;

    var o = document.getElementById("font_" + id);
    document.getElementById("propName").innerHTML = "<b>" + o.innerHTML + "</b>";
    document.getElementById("propId").value = id;

    var sss = o1.split(",");
    document.getElementById("fontName").value = sss[1];
    document.getElementById("fontsize").value = sss[2];
    document.getElementById("fontbold").value = sss[3];
};
//动态加载页面
function addPbText(){
	
	if($("#div_senderName").length===1){
		var s='font-size: 12pt; font-family: 宋体; font-weight: normal; left: 278px; top: 83px; width: 120px; height: 25px;';
		var chks=$("#itemVals").val().split(";"),
			font=$("#propVals").val().split(";");
		$.each(chks,function(i,v){
			var id=v.split(",")[0],
				isShow=v.split(",")[1]==="0" ?  "none" : "block",
				_l=v.split(",")[2] * 4 / 5,
				_t=v.split(",")[3] * 4 / 5,
				_w=v.split(",")[4] * 4 / 5,
				_h=v.split(",")[5] * 4 / 5,
				ff=font[i][1],
				fs=font[i][2],
				fw=font[i][3]==="1" ? "bold" : "normal";	
			$("#div_"+id).css({"font-size":fs+"pt","font-family":ff,"font-weight":fw,"left":_l+"px","top":_t+"px","width":_w+"px","height":_h+"px","display":isShow});
		});
		return;	
	}		
	var chks =$("[name=chk]");
	//var resultDivs="<img id=\"printPic\" src=\"http://www.ecbao.cn/dsb/uploads/models/eeb12175-d286-41af-96dd-37bffde4f90e.jpg\"/>";
	var resultDivs="<img id=\"printPic\" src=\"\"/>";
	var str="";	
	$.each(chks,function(i,v){
		var id=$(this).attr("id"),
			text =$.trim($(this).parent("li").text());			
		resultDivs += "<div id=\"div_" + id + "\" style=\"width:120px; height:25px; top:0px; left:880px;display:none;cursor: move;position: absolute;\" class=\"divset\" onClick=\"setId('" + id + "')\">";
		resultDivs += "<input type=\"hidden\" name=\"input_" + id + "\" id=\"input_" + id + "\" value=\"" + id + ",simsun,12,0\">";
		resultDivs += "<div id=\"title" + i + "\" onMouseDown=\"move(this,event)\" class=\"title\"><font id=\"font_" + id + "\">" + text + "</font></div>";
		resultDivs += "<div id=\"border" + i + "\" onMouseDown=\"startResize(this,event)\" class=\"resize\"></div>";		
		resultDivs += "</div>";		
	});
	$('#pb').html(resultDivs);
	mmToPx();
};
//设置选中div的字体名
function setFontName(name)
{
    var propId = document.getElementById("propId").value;
    var o = document.getElementById("font_" + propId);
    var inputO = document.getElementById("input_" + propId).value;
    o.style.fontFamily = name;
    var ss = inputO.split(",");
    var tmpVal = ss[0] + "," + name + "," + ss[2] + "," + ss[3];
    document.getElementById("input_" + propId).value = tmpVal;
};
//设置选中div的字体大小
function setFontSize(size)
{
    var propId = document.getElementById("propId").value;
    var o = document.getElementById("font_" + propId);
    var inputO = document.getElementById("input_" + propId).value;
    if (size != -1) {
        o.style.fontSize = size + "pt";
    } else {
        o.style.fontSize = "10pt";
    }
    var ss = inputO.split(",");
    var tmpVal = ss[0] + "," + ss[1] + "," + size + "," + ss[3];
    document.getElementById("input_" + propId).value = tmpVal;
};
//设置选中div的字体粗细
function setFontBold(flag)
{
    var propId = document.getElementById("propId").value;
    var o = document.getElementById("font_" + propId);
    var inputO = document.getElementById("input_" + propId).value;
    var sb = "normal";
    if (flag == 1) {
        sb = "bold";
    }
    o.style.fontWeight = sb;
    var ss = inputO.split(",");
    var tmpVal = ss[0] + "," + ss[1] + "," + ss[2] + "," + flag;
    document.getElementById("input_" + propId).value = tmpVal;
};
//自定义尺寸时改变宽高的数值
function setTemplateSize(val) {
    if (val != "custom") {
        var arrVal = val.split(":");

        document.getElementById("template_width").value = arrVal[0];
        document.getElementById("template_height").value = arrVal[1];

    }  
	mmToPx();
};
//右侧菜单显示隐藏控制
function togglemenu(obj) {
	obj=$(obj);
	if(obj.next("ul").is(":visible")) return;
	obj.parent().find("ul").slideUp();
	obj.next("ul").slideToggle();	
	if(obj.attr("id")==="tempset1" || obj.attr("id")==="tempset2" || obj.attr("id")==="tempset3")
		obj.next("ul").css({"overflow-y":"scroll"});
}
function js_getDPI() {return 96;}
//设置模板区域的大小
function mmToPx(){
	var obj = document.getElementById("template_width");
    var obj1 = document.getElementById("template_height");
	var mmnum = obj.value;		
	var hhnum = parseInt(obj1.value);
	var num = 25.4;
	var dpi = js_getDPI();
	var w = Math.floor((mmnum / 25.4) * dpi);
	var incun1 = Math.floor(hhnum / 25.4);
	var h = Math.floor((hhnum / 25.4) * dpi);
	var pb=$("#pb"),ppic=$("#printPic"),all_width=$("#all_width");	
	if(mmnum===""){
		$("#pb,#printPic,#all_width").css({"width":"auto","height":"auto"});
	}else{		
		$("#pb,#printPic,#all_width").css({"width":w+"px","height":h+"px"});
	}
	$("#masterdiv").css({"height":$("#pb").outerHeight()+47+"px"});
};
var tuodong_a,resizeable;
//开始拖动
function move(o, e) {
	tuodong_a = o;
	document.all ? tuodong_a.setCapture() : window.captureEvents(Event.MOUSEMOVE);
	b = e.clientX - parseInt(tuodong_a.parentNode.style.left);
	c = e.clientY - parseInt(tuodong_a.parentNode.style.top);
}
$(document).mousemove(function (d) {
	if (!tuodong_a) {
		return;
	}
	if (!d) {
		d = event;
	}
	tuodong_a.parentNode.style.left = (d.clientX - b) + "px";
	tuodong_a.parentNode.style.top = (d.clientY - c) + "px";
});
//停止拖动 
$(document).mouseup(function (d) {
	if (!tuodong_a)
		return;
	if (!d)
		d = event;
	var offs = $("#pb").offset();
	var offs1 = $("#" + tuodong_a.id).offset();
	var mapX = d.clientX - offs.left;
	var mapY = d.clientY - offs.top;
	var ddd = document.getElementById(tuodong_a.id);
	var p = tuodong_a.offsetParent;
	posLeft = p.offsetLeft;
	posTop = p.offsetTop;
	var divId = tuodong_a.parentNode.id;
	var chkid = divId.substr(divId.indexOf("div_") + 4, divId.length);
	var chk = document.getElementById(chkid);
	var vals = chk.value.split(",");
	chk.value = posLeft + "," + posTop + "," + vals[2] + "," + vals[3];
	document.all ? tuodong_a.releaseCapture() : window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP);
	tuodong_a = "";
});
//开始缩放
function startResize(obj, e) {
	resizeable = obj;
	document.all ? resizeable.setCapture() : window.captureEvents(Event.MOUSEMOVE);
	b = e.screenX - parseInt(resizeable.parentNode.style.width);
	c = e.screenY - parseInt(resizeable.parentNode.style.height);
}

$(document).mousemove(function (d) {
	if (!resizeable)
		return;
	if (!d)
		d = event;
	resizeable.parentNode.style.width = (d.screenX - b) + "px";
	resizeable.parentNode.style.height = (d.screenY - c) + "px";
});
//停止缩放 
$(document).mouseup(function (d) {
	if (!resizeable)
		return;
	if (!d)
		d = event;
	var win = resizeable.parentNode; //取得父窗体 			
	x1 = parseInt(win.style.width);
	y1 = parseInt(win.style.height);
	var xx = x1 + "," + y1;
	var divId = win.id;
	var chkid = divId.substr(divId.indexOf("div_") + 4, divId.length);
	var chk = document.getElementById(chkid);
	var vals = chk.value.split(",");
	chk.value = vals[0] + "," + vals[1] + "," + xx;
	document.all ? resizeable.releaseCapture() : window.captureEvents(Event.MOUSEMOVE | Event.MOUSEUP);
	resizeable = "";
});

function getPxNum(val)
{
    var mmnum = val;
    var num = 25.4;
    var dpi = js_getDPI();//window.screen.deviceXDPI;
    var px = Math.floor((mmnum / num) * dpi);
    return px;
}


//计算偏移量
function countNum(num1, num2, tag)
{
    if (tag == "-") {
        return num1 - num2;
    } else {
        return num1 + num2;
    }
}
var _edit ={
		IN: function (obj) {
			if (obj.innerHTML == "") {
				var tag = "undefined";
			} else {
				var tag = obj.firstChild.tagName;
			}
			if (typeof (tag) != "undefined" && (tag == "INPUT" || tag == "TEXTAREA"))
				return;
			var org = obj.innerHTML;
			var orglen = org.replace(/[^\x00-\xff]/g, '**').length;
			if (obj.offsetHeight <= 22) {
				var val = window.ActiveXObject ? obj.innerText : obj.textContent;
				var txt = document.createElement("INPUT");
				txt.value = val;
				txt.style.background = "#FFC";
				txt.style.width = obj.offsetWidth + "px";
				obj.innerHTML = "";
				obj.appendChild(txt);
				txt.focus();
				txt.onblur = function (e) {
					obj.innerHTML = txt.value;
					return false;
				};
				return false;
			} else {
				var content = obj.innerHTML;
				var html = document.createElement('TEXTAREA');
				html.style.width = obj.offsetWidth + "px";
				html.style.height = obj.offsetHeight + "px";
				obj.innerHTML = "";
				html.value = content;
				obj.appendChild(html);
				html.focus();
				html.onblur = function (e) {
					obj.innerHTML = html.value;
				};
				return false;
			}
		}
	};

//批量替换br标签
function replaceTag(src) {
    if (!src) {
        return src;
    }
    if (src == null || src == "") {
        return src;
    }
    src = "" + src;
    if (src.indexOf("<BR>") != -1) {
        var reg = new RegExp("<BR>", "gm");
        var stt = src.replace(reg, "\n");
        return stt;
    }
    if (src.indexOf("<br>") != -1) {
        var reg = new RegExp("<br>", "gm");
        var stt = src.replace(reg, "\n");
        return stt;
    }
    if (src.indexOf("<BR/>") != -1) {
        var reg = new RegExp("<BR/>", "gm");
        var stt = src.replace(reg, "\n");
        return stt;
    }
    if (src.indexOf("<br/>") != -1) {
        var reg = new RegExp("<br/>", "gm");
        var stt = src.replace(reg, "\n");
        return stt;
    }
    return src;
}