	<#include "/template/front/header.ftl">
		<style>
			/*printModel*/
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
		
	</head>
	<body>
		<input type="hidden" name="id" id="id" value="${id}" />
		<input type="hidden" name="itemVals" id="itemVals" value="" />
		<input type="hidden" name="propVals" id="propVals" value="" />
		<input type="hidden" name="templatePic" id="templatePic" value="" />
		<input type="hidden" name="template_width" id="template_width" value="" >
		<input type="hidden" name="template_height" id="template_height" value="" >
			
		<div id="singleRst" style="padding-bottom:80px;">
			<div style="border-bottom-width: 1px; border-bottom-color: rgb(204, 204, 204); border-bottom-style: dashed; position: relative; width: 695.2px; height: 384px;" id="pb">
				<img id="printPic" src="" border="0">
				<div id="div_senderName" class="floattpset" onclick="_edit.IN(this);" title="发件人姓名">发件人姓名</div>
				<div id="div_mobile" class="floattpset" onclick="_edit.IN(this);" title="发件人手机">发件人手机</div>
				<div id="div_telephone" class="floattpset" onclick="_edit.IN(this);" title="发件人固话">发件人固话</div>
				<div id="div_city" class="floattpset" onclick="_edit.IN(this);" title="发件人城市">发件人城市</div>
				<div id="div_area" class="floattpset" onclick="_edit.IN(this);" title="发件人省市区">发件人省市区</div>
				<div id="div_sendAddress" class="floattpset" onclick="_edit.IN(this);" title="发件人地址">发件人地址</div>
				<div id="div_postcode" class="floattpset" onclick="_edit.IN(this);" title="发件人邮编">发件人邮编</div>
				<div id="div_shopTitle" class="floattpset" onclick="_edit.IN(this);" title="发件人店铺名称">发件人店铺名称</div>
				<div id="div_memo1" class="floattpset" onclick="_edit.IN(this);" title="备注1">备注1</div>
				<div id="div_memo2" class="floattpset" onclick="_edit.IN(this);" title="备注2">备注2</div>
				<div id="div_memo3" class="floattpset" onclick="_edit.IN(this);" title="备注3">备注3</div>
				<div id="div_sellerMemo" class="floattpset" onclick="_edit.IN(this);" title="卖家备注">卖家备注</div>
				
				<div id="div_recvname" class="floattpset" onclick="_edit.IN(this);" title="收件人姓名">收件人姓名</div>
				<div id="div_custNick" class="floattpset" onclick="_edit.IN(this);" title="收件人昵称">收件人昵称</div>
				<div id="div_recvmobile" class="floattpset" onclick="_edit.IN(this);" title="收件人手机">收件人手机</div>
				<div id="div_recvphone" class="floattpset" onclick="_edit.IN(this);" title="收件人固话">收件人固话</div>
				<div id="div_address" class="floattpset" onclick="_edit.IN(this);" title="收件人详细地址">收件人详细地址</div>
				<div id="div_recvArea" class="floattpset" onclick="_edit.IN(this);" title="收件人省市区 ">收件人省市区</div>
				<div id="div_city" class="floattpset" onclick="_edit.IN(this);" title="收件人城市 ">收件人城市</div>
				<div id="div_zipcode" class="floattpset" onclick="_edit.IN(this);" title="收件人邮编 ">收件人邮编</div>
				<div id="div_buyerMemo" class="floattpset" onclick="_edit.IN(this);" title="买家留言 ">买家留言</div>
				<div id="div_topTids" class="floattpset" onclick="_edit.IN(this);" title="订单编号">订单编号</div>
				<div id="div_printDate" class="floattpset" onclick="_edit.IN(this);" title="打印日期">打印日期</div>
			</div>
			<div class="pbset">
		        <div class="pbset_left">
		            <span id="printTip"><b>打印机</b></span>:
		            <span id="chooseMachine"></span><b>偏移:</b>
		            <input name="p_x"   value="-" type="radio">向左
		            <input checked="checked " name="p_x"   value="+" type="radio">向右
		            <input name="x_mm" id="x_mm" class="txt01" value="0" size="3" onkeyup="this.value = this.value.replace(/[^0-9]/g, '');" type="text">mm，
		            <input checked="checked " name="p_y"   value="-" type="radio">向上
		            <input name="p_y"   value="+" type="radio">向下
		            <input name="y_mm" id="y_mm" class="txt01" value="0" size="3" onkeyup="this.value = this.value.replace(/[^0-9]/g, '');" type="text">mm &nbsp;&nbsp;
		        </div>
		    </div>
		</div>
		<div class="m-pop-bot f-pt10">
			<a href="javascript:prntBtnClick();" id="save" class="btn btn-normal btn-bluePage f-r0">测试打印</a>
		</div>	
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/express/printPreview.js?v=${config_front}"></script>
</html>