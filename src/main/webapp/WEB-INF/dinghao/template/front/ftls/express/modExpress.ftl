	<#include "/template/front/header.ftl">
	<body class="m-pop-body">
		<div class="m-pop-detail f-clearfix">
			<ul class="m-message-list single-row f-mb10 f-clearfix">
				<li>
					<label><i class="c-red"> *&nbsp; </i>物流公司名称：</label>
					<input type="text" maxlength="32" class="u-ipt u-ipt-nm u-ipt-disabled" id="name" placeholder="" disabled/>
				</li>
				<li>
					<label>联系人：</label>
					<input type="text" class="u-ipt u-ipt-nm " id="linkMan" />
				</li>
				<li>
					<label>联系号码：</label>
					<input type="text" class="u-ipt u-ipt-nm" id="linkPhone"/>
				</li>
				<li>
					<label>启用状态:</label>
	            	<div class="input-con">
	           			<input type="radio" name="actived" value="1">启用</input>
	           		</div>
	           		<div class="input-con">
	           			<input type="radio" name="actived" value="0">停用</input>
	       			</div>
       			</li>
			</ul>
		</div>
		<div class="m-pop-bot">
			<a href="javascript:" onclick="saveClsFn()" id="save" class="u-btn u-btn-nm u-btn-bg-blue f-mr15">确认</a>
			<a href="javascript:" onclick="closeWinFn()" id="close" class="u-btn u-btn-nm u-btn-bg-blue">关闭</a>
		</div>
		
		<input type="hidden" id="id" value="${id}">
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/express/modExpress.js?v=${config_front}"></script>
</html>

