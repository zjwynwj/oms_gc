	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
			<div class="m-toolbar f-mb15 f-clearfix">
				<ul class="search-con f-fl">
				
					<li><a href="javascript:" class="u-btn u-btn-bg-blue u-btn-auto  u-bd-color-blue f-pl40" onclick="addGdsCls()">添加分类<i class="ico-add"></i></a></li>
					<li><a href="javascript:" class="u-btn u-btn-bg-blue u-btn-auto  u-bd-color-blue"  onclick="initClsTree()">刷新</a></li>
				    <li><a href="javascript:" class="u-btn u-btn-bg-blue u-btn-auto  u-bd-color-blue"  onclick="delGdsCls()" >删除</a></li>
				
				 
		        </ul>
		        
		        <ul class="search-con f-fr">
		       	
		        	 <li>
		        	 	<input type="text" class="u-ipt u-ipt-nm" placeholder="分类名称" id="keyWord"/>
	        	 	</li>
	        	 	<li>
	        	 		<li><a href="javascript:search()" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>
	        	 	</li>
		        </ul>
			</div>
			<div class="m-detail-wrap">
				<div class="m-detail-lt">
					<div id="gdsClsTree" class="ztree f-pl20 f-pt10"></div>
				</div>
				<div class="m-detail-rt">
		    		<div class="m-grid-wrap f-mb20 f-clearfix">
						<table id="gdsClsGridId"></table>
						<div id="gdsClsGridPanelId"></div>
			        </div>
		    	</div>
			</div>
		</div>
	</body>
	<!-- zTree -->
	<link rel="stylesheet" href="${BASE_PATH}/dinghao/template/front/js/elem/zTree/css/zTreeStyle/zTreeStyle.css?v=${config_front}" type="text/css">
	<script type="text/javascript" src="${BASE_PATH}/dinghao/template/front/js/elem/zTree/js/jquery.ztree.core-3.5.js?v=${config_front}"></script>
	<script type="text/javascript" src="${BASE_PATH}/dinghao/template/front/js/elem/zTree/js/jquery.ztree.exedit-3.5.js?v=${config_front}"></script>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/goods/gdsCls/modGdsCls.js?v=${config_front}"></script>
</html>

