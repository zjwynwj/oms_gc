	<#include "/template/front/header.ftl">
	<script src="${BASE_PATH}/dinghao/template/front/js/elem/lodop/LodopFuncs.js?v=${config_front}"></script>
	<script src="${BASE_PATH}/dinghao/template/front/js/elem/lodop/printUtil.js?v=${config_front}"></script>
	<script src="${BASE_PATH}/dinghao/template/front/js/elem/lodop/printModel.js?v=${config_front}"></script>
	<style>
		dl.normal{height:35px;}
		dt#printTip b{ font-weight:normal;font-size:12px;}
		dl.normal .txt01{height:25px; padding:2px 0px; text-align:center; line-height:18px;text-indent: 0px;}
		dl.normal dd p{height:25px;}
	</style>
		
	</head>
	<body onload="">
		
			<input type="hidden" name="orderIds" id="orderIds" value="${orderIds}" />
			<input type="hidden" name="printId" id="printId" value="${printId}" />
			<input type="hidden" name="orderNums" id="orderNums" value="${orderNums}" />
			<input type="hidden" name="itemVals" id="itemVals" value="" />
			<input type="hidden" name="propVals" id="propVals" value="" />
			<input type="hidden" name="templatePic" id="templatePic" value="" />
			<input type="hidden" name="template_width" id="template_width" />
			<input type="hidden" name="template_height" id="template_height"/>
		  <div class="m-toolbar f-mb15 f-clearfix">
			<ul class="search-con f-clearfix">
			<li>
		        <label>共选择：</label>
		        <label><strong id="order_num">0</strong>个订单</label>
		    </li>
		   	<li>
		        <label>起始单号：</label>
		        <label id="start_no"></label>
		    </li>
		  	<li>
		        <label>结束单号：</label>
		        <label  id="end_no"></label>
		    </li>
		   	<li>
		        <label id="printTip">打印机：</label>
		        <span id="chooseMachine"></span>
		    </li>
		    	<li>
		        <label>当前打单模板：</label>
		        <label  id="templateName"></label>
		    </li>
		   </ul>
		     
		     </div>
		     <div class="m-toolbar f-mb15 f-clearfix">
		     
		   	<ul class="search-con f-clearfix">
		    	<li>
		          <label>偏移量：</label>
		       
		        
						<input  type="radio" name="p_x" value="-" /> 左
						<input  type="radio" name="p_x" value="+" checked="checked " style="margin-left:15px;" /> 右
						<input style="float:none" type="text" id="x_mm"  size="3" value="0" onkeyup="this.value = this.value.replace(/[^0-9]/g, '');" style="width:40px;" /> (毫米)
				        
						<input  type="radio" name="p_y"  value="-" checked="checked "/> 上
						<input  type="radio" name="p_y" value="+" style="margin-left:15px;"/> 下
						<input  style="float:none" type="text"  id="y_mm"  size="3" value="0" onkeyup="this.value = this.value.replace(/[^0-9]/g, '');" style="width:40px;"/> (毫米)
				
		    
		    </li>
		       </ul>
		       </div>
		   <div class="m-toolbar f-mb15 f-clearfix">
		    
		         <center>
		         <a href="javascript:printBtnClick();" id="save" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue" >直接打印</a>
	            </center>
		   		 
		   </div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/express/batchPrint.js?v=${config_front}"></script>
</html>