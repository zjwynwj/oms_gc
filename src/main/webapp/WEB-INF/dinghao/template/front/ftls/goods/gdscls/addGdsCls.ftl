	<#include "/template/front/header.ftl">
	<body class="m-pop-body">
		<form id="verifyCon">
			<div class="m-pop-detail f-clearfix">
				<ul class="m-message-list single-row f-mb10 f-clearfix">
					<li>
						<label><i style="color:red; margin: 0px 5px;">*</i>商品类别名称：</label>
						<input type="text" maxlength="32" class="u-ipt u-ipt-nm verify" id="clsName" verifyData="{type:'allcheck',length:'1,32' ,required:'yes'}"/>
					</li>
					<li>
						<label>父类别：</label>
						<input type="text" disabled="" class="u-ipt u-ipt-nm u-ipt-disabled" id="clsPnoName" value="${gdsClsVo.clsName}"/>
					</li>
					<li>
						<label><i style="color:red; margin: 0px 5px;">*</i>税率：</label>
						<input type="text" class="u-ipt u-ipt-nm" id="taxRate" value=0.00 amountFlag/>%
					</li>
				</ul>
			</div>
			<div class="m-pop-bot">
				<a href="javascript:" onclick="saveClsFn()" id="save" class="u-btn u-btn-nm u-btn-bg-blue f-mr15">确认</a>
				<a href="javascript:" onclick="closeWinFn()" id="close" class="u-btn u-btn-nm u-btn-bg-blue">关闭</a>
			</div>
		</form>
			
		<input type="hidden" id="clsPno" value="${gdsClsVo.clsNo}">
		<input type="hidden" id="clsPLevel" value="${gdsClsVo.level}">
	</body>
	<!-- zTree -->
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/goods/gdsCls/addGdsCls.js?v=${config_front}"></script>
</html>

