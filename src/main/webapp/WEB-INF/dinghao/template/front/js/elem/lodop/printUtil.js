
//检查是否已经按安装
function CheckIsInstall() {	 
	try{ 
	    var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM')); 
		//if ((LODOP!=null)&&(typeof(LODOP.VERSION)!="undefined")) alert("本机已成功安装过Lodop控件!\n  版本号:"+LODOP.VERSION); 
	 }catch(err){ 
		//alert("Error:本机未安装或需要升级!"); 
	 } 
}; 


//替换特殊标签
function replaceTag(src) {
    if (!src) {
        return src;
    }
    if (src == null || src == "") {
        return src;
    }
    src = "" + src;
    if (src.indexOf("<BR>") != -1) {
        var reg = new RegExp("<BR>", "g");
        var stt = src.replace(reg, "\n");
        return stt;
    }

    if (src.indexOf("<br>") != -1) {
        var reg = new RegExp("<br>", "g");
        var stt = src.replace(reg, "\n");
        return stt;
    } else {
        return src;
    }
}