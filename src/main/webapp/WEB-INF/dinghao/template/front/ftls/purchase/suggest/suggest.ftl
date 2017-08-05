<#include "/template/front/header.ftl">
<body>
		<div class="m-detail-con">
			<div class="m-toolbar f-mb15 f-clearfix">
		    	<ul class="search-con f-fl">
		        	<li><a id = "addSuggestB" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">新增建议<i class="ico-add"></i></a></li>
		        	<li>
		            	<div class="u-btn-group"><a href="javascript:" id="selAll" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray f-pr30">批量操作<i class="ico-sel"></i></a>
		                    <ul class="u-btn-sel-con">
		                        <li><a href="#">删除</a></li>
		                        <li><a href="#">完成</a></li>
		                        
		                    </ul>
		                </div>
		            </li>
		        </ul>
		    	<ul class="search-con f-fr">
		            
		        	<li>
		            	<label>业务日期:</label>
		            	<div class="date"><input type="text" id="busiDate1" class="u-ipt u-ipt-sm"  value="${start_date}" /></div>
		            	<span class="to">至</span>
		            	<div class="date"><input type="text" id="busiDate2" class="u-ipt u-ipt-sm"  value="${end_date}"/></div>
		            </li>
		            <li><label>单据编号:</label><input id="suggestNo" type="text" class="u-ipt u-ipt-nm" placeholder="请输入单据编号"/></li>
		            
		            <li><a href="javascript:" id="searchBtn" class="u-btn u-btn-auto u-btn-bg-gray u-bd-color-gray">搜 索</a></li>
		         </ul>
		        
		    </div>
				<div class="m-detail-wrap">
			    		<div class="m-grid-wrap f-mb20 f-clearfix">
		                  <table id="blackId"></table>
		                  
		                <div id="gridPager"></div>
		              </div>
		       </div>
		</div>
</body>
<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/purchase/suggest/suggest.js?v=${config_front}"></script>

</html>

