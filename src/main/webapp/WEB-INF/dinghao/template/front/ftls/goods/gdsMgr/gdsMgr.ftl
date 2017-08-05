<#include "/template/front/header.ftl">
<body>
	<div class="m-detail-con f-clearfix">
		<div class="m-toolbar f-mb15 f-clearfix">
			<ul class="search-con f-fl">
	        	<li><a onclick="turnAddGdsInfo()" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">新增商品<i class="ico-add"></i></a></li>
	        	<li><a onclick="downGoods()"      href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">商品同步<i class="ico-add"></i></a></li>
	        	<li>
	            	<div class="u-btn-group"><a href="javascript:" id="selAll" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray f-pr30">批量操作<i class="ico-sel"></i></a>
	                    <ul class="u-btn-sel-con">
	                        <li><a href="javascript:stockOperate('block')">停用</a></li>
	                        <li><a href="javascript:stockOperate('start')">启用</a></li>
	                    </ul>
	                </div>
	            </li>
	        </ul>
	        
	        <ul class="search-con f-fr">
	       	 	<li>
        			<div class="checkbox"><input type="checkbox" onclick="search()" id="isShowBlockGds">不显示停用商品</input></div>
    			</li>
	        	 <li>
	        	 	<input type="text" class="u-ipt u-ipt-nm" placeholder="编码/规格/名称" id="keyWord"/>
        	 	</li>
        	 
        	    <li><a href="javascript:search()" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>
        	    <li>
        	    <a href="javascript:beian()" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">备案</a></li>
        	 	</li>
	        </ul>
		</div>
		<div class="m-detail-wrap">
			<div class="m-detail-lt">
				<p class="m-oper">
					<input class="u-btn u-btn-sm u-btn-bg-gray u-bd-color-gray" type="button" onclick="addGdsCls()" value='添加分类'></input>
	
					<input class="u-btn u-btn-sm u-btn-bg-gray u-bd-color-gray" type="button" onclick="delGdsCls()" value='删除分类'></input>
				</p>
				<div id="gdsClsTree" class="ztree f-pl20 f-pt10"></div>
			</div>
			<div class="m-detail-rt">
	    		<div class="m-grid-wrap f-mb20 f-clearfix">
					<table id="gdsInfoGridId"></table>
					<div id="gdsInfoGridPanelId"></div>
		        </div>
	    	</div>
		</div>
	</div>
</body>
<!-- zTree -->
<link rel="stylesheet" href="${BASE_PATH}/dinghao/template/front/js/elem/zTree/css/zTreeStyle/zTreeStyle.css?v=${config_front}" type="text/css">
<script type="text/javascript" src="${BASE_PATH}/dinghao/template/front/js/elem/zTree/js/jquery.ztree.core-3.5.js?v=${config_front}"></script>
<script type="text/javascript" src="${BASE_PATH}/dinghao/template/front/js/elem/zTree/js/jquery.ztree.exedit-3.5.js?v=${config_front}"></script>
<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/goods/gdsMgr/gdsMgr.js?v=${config_front}"></script>
</html>

