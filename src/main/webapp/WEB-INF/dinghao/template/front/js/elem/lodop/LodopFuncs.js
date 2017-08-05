var CreatedOKLodop7766=null;

function getLodop(oOBJECT,oEMBED){
/**************************
  本函数根据浏览器类型决定采用哪个页面元素作为Lodop对象：
  IE系列、IE内核系列的浏览器采用oOBJECT，
  其它浏览器(Firefox系列、Chrome系列、Opera系列、Safari系列等)采用oEMBED,
  如果页面没有相关对象元素，则新建一个或使用上次那个,避免重复生成。
  64位浏览器指向64位的安装程序install_lodop64.exe。
**************************/
        var strHtmInstall="<p class='c-orange f-mt5'>打印控件未安装!点击这里<a href='"+base+"/dinghao/install_lodop32.exe' target='_self'>执行安装</a>,安装后请重启浏览器或重新进入。</p>";
        var strHtmUpdate="<p class='c-orange f-mt5'>打印控件需要升级!点击这里<a href='"+base+"/dinghao/install_lodop32.exe' target='_self'>执行升级</a>,升级后请重新进入。</p>";
        var strHtm64_Install="<p class='c-orange f-mt5'>打印控件未安装!点击这里<a href='"+base+"/dinghao/install_lodop64.exe' target='_self'>执行安装</a>,安装后请重启浏览器或重新进入。</p>";
        var strHtm64_Update="<p class='c-orange f-mt5'>打印控件需要升级!点击这里<a href='"+base+"/dinghao/install_lodop64.exe' target='_self'>执行升级</a>,升级后请重新进入。</p>";
        var strHtmFireFox="<span class='c-orange'>（注意：如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】->【扩展】中先卸它）</span>";
        var strHtmChrome="<span class='c-orange'>(如果此前正常，仅因浏览器升级或重安装而出问题，需重新执行以上安装）</span>";
        var LODOP;		
	try{	
	     //=====判断浏览器类型:===============
	     var isIE	 = (navigator.userAgent.indexOf('MSIE')>=0) || (navigator.userAgent.indexOf('Trident')>=0);
	     var is64IE  = isIE && (navigator.userAgent.indexOf('x64')>=0);
	     //=====如果页面有Lodop就直接使用，没有则新建:==========
	     if (oOBJECT!=undefined || oEMBED!=undefined) { 
               	 if (isIE) 
	             LODOP=oOBJECT; 
	         else 
	             LODOP=oEMBED;
	     } else { 
		 if (CreatedOKLodop7766==null){
          	     LODOP=document.createElement("object"); 
		     LODOP.setAttribute("width",0); 
                     LODOP.setAttribute("height",0); 
		     LODOP.setAttribute("style","position:absolute;left:0px;top:-100px;width:0px;height:0px;");  		     
                     if (isIE) LODOP.setAttribute("classid","clsid:2105C259-1E0C-4534-8141-A753534CB4CA"); 
		     else LODOP.setAttribute("type","application/x-print-lodop");
		     document.documentElement.appendChild(LODOP); 
	             CreatedOKLodop7766=LODOP;		     
 	         } else 
                     LODOP=CreatedOKLodop7766;
	     };
	     //=====判断Lodop插件是否安装过，没有安装或版本过低就提示下载安装:==========
	     if ((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")) {
	             if (navigator.userAgent.indexOf('Chrome')>=0)
	                 document.documentElement.innerHTML=strHtmChrome+document.documentElement.innerHTML;
	             if (navigator.userAgent.indexOf('Firefox')>=0)
	                 document.documentElement.innerHTML=strHtmFireFox+document.documentElement.innerHTML;
	             if (is64IE) document.write(strHtm64_Install); else
	             if (isIE)   document.write(strHtmInstall);    else
	                 document.documentElement.innerHTML=strHtmInstall+document.documentElement.innerHTML;
	             return LODOP;
	     } else 
	     if (LODOP.VERSION<"6.1.9.5") {
	             if (is64IE) document.write(strHtm64_Update); else
	             if (isIE) document.write(strHtmUpdate); else
	             document.documentElement.innerHTML=strHtmUpdate+document.documentElement.innerHTML;
	    	     return LODOP;
	     };
	     //=====如下空白位置适合调用统一功能(如注册码、语言选择等):====	     
	    // LODOP.SET_LICENSES("恒生电子股份有限公司","83420CB7AB0D661978D8D05CE036EE47","恒生電子股份有限公司","874320063B3CDFDA3958BAEBFBDD1F01");
	    // LODOP.SET_LICENSES("THIRD LICENSE","","The Hang Seng electronic Limited by Share Ltd","DE03BB4DFEDCD52C434BC881DEAFBC73");

	     //============================================================	     
	     return LODOP; 
	} catch(err) {
	     if (is64IE)	
            document.documentElement.innerHTML="Error:"+strHtm64_Install+document.documentElement.innerHTML;else
            document.documentElement.innerHTML="Error:"+strHtmInstall+document.documentElement.innerHTML;
	     return LODOP; 
	};
}