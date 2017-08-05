	<#include "/template/front/header.ftl">
	<link rel="stylesheet" type="text/css" href="${BASE_PATH}/dinghao/template/front/js/elem/select2-master/dist/css/select2.min.css?v=${config_front}">
	<script src="${BASE_PATH}/dinghao/template/front/js/elem/select2-master/dist/js/select2.min.js?v=${config_front}"></script>
	<body class="m-pop-body">
		<div class="m-pop-detail f-clearfix">
			<ul class="m-message-list single-row f-mb10 f-clearfix">
				<li>
	            	<label>下单时间:</label>
	            	<div class="date"><input type="text" class="u-ipt u-ipt-sm" id="startTime"/></div>
	            	  <span style="float:left;width:auto;line-height:30px;padding:0 5px;">至</span>
	            	<div class="date"><input type="text" class="u-ipt u-ipt-sm" id="endTime"/></div>
            	</li>
				<li>
	            	<label>店铺：</label> 
	            	 <select class="u-select u-select-nm" id="shopId">
							<option value="">请选择</option> 
							<#list shoplist as shop>
							     <option value="${shop.id}">${shop.name}</option>
							</#list>
					  </select>
            	</li>
			</ul>
		</div>
		<div class="m-pop-bot">
			<a href="javascript:" onclick="startDown()" id="save" class="u-btn u-btn-nm u-btn-bg-blue f-mr15">确认</a>
			<a href="javascript:" onclick="closeWinFn()" id="close" class="u-btn u-btn-nm u-btn-bg-blue">关闭</a>
		</div>
		
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/order/orderDown.js?v=${config_front}"></script>
</html>

