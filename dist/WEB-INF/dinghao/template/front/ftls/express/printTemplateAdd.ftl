	<#include "/template/front/header.ftl">
		<style>
		.c-orange {color: #ff7800;}
			/*printModel*/
			.c-orange {color: #ff7800;}
			.printbox{float:left;width:100%;height:auto;position:relative}
			#pb{color:#333}
			#pb .divset{position:absolute;background:#fff;border:1px solid #5cc400;box-shadow:0 2px 5px #dee8f2}
			#pb .title{position:absolute;color:#333;line-height:2em;padding-left:5px;cursor:move;width:95%;height:95%;top:0;left:0;z-index:2}
			.sidecheck{ float:left; margin-left:20px; width:245px;height:500px;font-size:12px;}
			.sidecheck h2{background:#f1f8fc;font-size:14px;padding-left:10px;line-height:28px;border-bottom:1px solid #e3e3e3;}
			.sidecheck ul{padding:10px;max-height:300px;overflow-y:scroll;}
			#masterdiv ul {overflow: auto;width: 245px;}
			.sidecheck ul li{padding:3px 0 0 3px;}
			#singleRst .floattpset {background: #fff;filter: alpha(opacity = 90);opacity: 0.9;-moz-opacity: 0.9;z-index: 100;position: absolute;padding: 0;margin: 0;border: 1px #FF9900 solid;color: #333;display:none}
			.pbset {width: 100%;padding: 10px 0 10px 0;line-height: 3;	color: #333}
			.pbset_left {float: left;width: 100%;padding-left: 1%;}
			.pbset_left li {padding: 0 0 5px 10px;}
			.pbset_left li input {margin-right: 10px;}
			.pbset_right {float: left;width: 44%;padding-left: 1%;}
			#pb .resize {position: absolute;width: 7px;height: 7px;cursor: nw-resize;background: url(${BASE_PATH}/dinghao/template/front/images/common/btn_resize.gif) no-repeat;z-index: 5;right: 1px;bottom: 1px;}
		</style>
		
		<script src="${BASE_PATH}/dinghao/template/front/js/elem/lodop/LodopFuncs.js?v=${config_front}"></script>
		<script src="${BASE_PATH}/dinghao/template/front/js/elem/lodop/printUtil.js?v=${config_front}"></script>
		<script src="${BASE_PATH}/dinghao/template/front/js/elem/lodop/printModel.js?v=${config_front}"></script>
		
		<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
		    <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
		</object>

	<body>
		<div class="m-detail-con f-clearfix">
			<form id="addPic" action="${BASE_PATH_TEMPLATE}/printMgr/templateImgUpLoadFile.jhtml" enctype="multipart/form-data" method="post" >
				<input type="hidden" name="id" id="id" value="" />
				<input type="hidden" name="itemVals" id="itemVals" value="" />
				<input type="hidden" name="propVals" id="propVals" value="" />
				<input type="hidden" name="templatePic" id="templatePic" value="" />
				<div class="m-toolbar f-mb15 f-clearfix">
					<ul class="search-con f-fl">
		 			    <li>
							<label style="float:left;width:auto;">模板名称：</label>
							<input class="u-ipt u-ipt-nm"  type="text" value="" id='templateName'/>
						</li> 
						<li>
							<label style="float:left;width:auto;">快递公司：</label>
							<select name="logisId" id="logisId" class="u-select u-select-nm" >
	                  	  	</select>
						</li>
						<!--<li class="f-mr10">
							<label>类型：</label>
							<select name="type" id="type" value="1" class="u-select u-select-nm" >
		                    	<option value="1">普通物流模板</option>
		                        <option value="2">电子面单模板</option>
		                    </select>
						</li>-->
						<li>
							<label style="float:left;width:auto;">尺寸：</label>
							<select  class="u-select u-select-nm"  name="templateSize" id="templateSize" onchange="setTemplateSize(this.value);" value="230:137">
		                    	<option value="custom">自定义</option>
		                        <option value="300:200">300×200</option>
		                        <option value="200:150">200×150</option>
		                    </select>
						</li>
						
						
						<li>
							<label style="float:left;width:auto;">宽高：</label>
							<input style="float:left;width:60px;" class="u-ipt u-ipt-nm" name="template_width" id="template_width" type="text" value="230" />
							<input style="float:left;width:60px;" class="u-ipt u-ipt-nm" name="template_height" id="template_height" type="text" value="126">
							<label style="float:left;width:auto;">mm</label>
						</li>
						<li>
					      	<input value="保存模板" type="button" onclick="doAddSubmit()" class="u-btn u-btn-bg-blue u-btn-auto"  />
						</li>
						</ul>
						</div>
				<div class="m-toolbar f-mb15 f-clearfix">
					<ul class="search-con f-fl  f-clearfix">
						<li>
							<label style="float:left;width:auto;" >背景图片：</label>
							<input type="file" name="file" >
							<input type="button" value="上传" onclick="uploadImg()"  class="u-btn u-btn-bg-gray u-bd-color-gray u-btn-auto"/>
							
						</li>
					</ul>			
				</div>
			</form>
			
			<div class="m-toolbar f-mb15 f-clearfix">
				<input type="hidden" name="propId" id="propId" value="senderName">
				当前属性名:<span id="propName"><b>&nbsp;发件人姓名</b></span>
				字体:
				<select id="fontName" name="fontName" onchange="setFontName(this.value);">
			    	<option value="simsun">宋体</option>
			    	<option value="simHei">黑体</option>
			    </select>
			      字号：
			    <select id="fontsize" name="fontsize" onchange="setFontSize(this.value);">
			    	<option value="8">8pt</option>
			        <option value="10">10pt</option>
			        <option value="12">12pt</option>
			        <option value="14">14pt</option>
			        <option value="16">16pt</option>
			        <option value="18">18pt</option>
			        <option value="20">20pt</option>
			        <option value="22">22pt</option>
			        <option value="24">24pt</option>
			        <option value="26">26pt</option>
			        <option value="28">28pt</option>
			        <option value="30">30pt</option>
			        <option value="32">32pt</option>
			        <option value="34">34pt</option>
			        <option value="36">36pt</option>
			        <option value="38">38pt</option>
			        <option value="40">40pt</option>
			        <option value="42">42pt</option>
			        <option value="44">44pt</option>
			        <option value="46">46pt</option>
			    </select>
			    加粗:
			    <select id="fontbold" name="fontbold" onchange="setFontBold(this.value);">
			        <option value="0">不加粗</option>
			        <option value="1">加粗</option>
			    </select>
			</div>
			<div id="all_width" style="float:left;">
				<div class="printbox" id="pb"></div>
			</div>
			<div class="sidecheck" id="masterdiv"> 
				<h2 id="tempset1" onclick="togglemenu(this);">卖家信息</h2>
			    <ul id="tempsetlist1" name="tmp_use" class="clearfix" >                            
			        <li name="chkli"><input name="chk" id="senderName" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;发件人姓名</li>	
			        <li name="chkli"><input name="chk" id="mobile" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;发件人手机</li>
			        <li name="chkli"><input name="chk" id="telephone" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;发件人固话</li>
			        <li name="chkli"><input name="chk" id="city" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;发件人城市</li>
			        <li name="chkli"><input name="chk" id="area" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;发件人省市区</li>
			        <li name="chkli"><input name="chk" id="sendAddress" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;发件人地址</li>
			        <li name="chkli"><input name="chk" id="shopTitle" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;发件人店铺名称</li>
			        <li name="chkli"><input name="chk" id="postcode" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;发件人邮编</li>
			        <li name="chkli"><input name="chk" id="sellerMemo" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;卖家备注</li>
			    </ul>	    
			    <h2 id="tempset2" class="clearfix" onclick="togglemenu(this);">买家信息</h2>
			    <ul id="tempsetlist2" name="tmp_use" style="display: none">
			    	<li name="chkli"><input name="chk" id="recvname" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;收件人姓名</li>
			        <li name="chkli"><input name="chk" id="custNick" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;收件人昵称</li>
			        <li name="chkli"><input name="chk" id="recvmobile" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;收件人手机</li>
			        <li name="chkli"><input name="chk" id="recvphone" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;收件人固话</li>
			        <li name="chkli"><input name="chk" id="city" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;收件人城市</li>
			        <li name="chkli"><input name="chk" id="recvArea" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;收件人省市区</li>
			        <li name="chkli"><input name="chk" id="address" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;收件人地址</li>
			        <li name="chkli"><input name="chk" id="zipcode" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;收件人邮编</li>
			        <li name="chkli"><input name="chk" id="buyerMemo" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;买家留言</li>                    
			    </ul>
			    <h2 id="tempset3" class="clearfix" onclick="togglemenu(this);">订单信息</h2>
			    <ul id="tempsetlist3" name="tmp_use" style="display: none">
					<li name="chkli"><input name="chk" id="topTids" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;订单编号</li>
					<li name="chkli"><input name="chk" id="goodsName" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;宝贝名称</li>
					<li name="chkli"><input name="chk" id="titleSkuPropNum" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;宝贝名称+属性+数量</li>
		       </ul>
			   <h2 id="tempset4" class="clearfix" onclick="togglemenu(this);">其他信息</h2>
			   <ul id="tempsetlist4" name="tmp_use" style="display: none">
		           <li name="chkli"><input name="chk" id="memo1" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;备注1</li>
		            <li name="chkli"><input name="chk" id="memo2" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;备注2</li>
		            <li name="chkli"><input name="chk" id="memo3" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;备注3</li>
		            <li name="chkli"><input name="chk" id="printDate" type="checkbox" value="760,0,120,25" onClick="showDiv(this);" />&nbsp;打印日期</li>
				</ul>
			</div>	
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/express/printTemplateAdd.js?v=${config_front}"></script>
</html>