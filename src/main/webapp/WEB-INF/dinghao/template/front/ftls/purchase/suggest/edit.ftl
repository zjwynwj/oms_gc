<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
		
			    <div class="m-form-tit f-mb20 f-clearfix">
			       <input type="hidden" id="status" name = "status" value="${suggest.status}" />
			       <input type="hidden" id="suggestId" value="${suggest.id}">
		        	<ul class="m-form-grd-lt">
		                <li>
		                 <label>单据编号：</label>
		                  <input type="text" id="suggestNo" disabled="" class="u-ipt u-ipt-nm u-ipt-disabled" value="${suggest.suggestNo}" >
		                </li>
		            </ul>
		            <ul class="m-form-grd-rt">
		               <li style="display:none"> 
		                <div class="f-rt">
		            	  <input type="checkbox" id="needprint" checked="checked" >
		            	   <label>保存后打印</label>
		            	 </div>
		            	 </li>
		                 <li>
		                   <input type="button" id="save" class="u-btn u-btn-auto u-btn-bg-blue" value="保存">
		                   <li><a id = "addPurOrder" href="javascript:" class="u-btn u-btn-auto u-btn-bg-blue u-bd-color-blue f-pl40">生成采购订单<i class="ico-add"></i></a></li>
		                 </li>
		            </ul>
		            
		            <h4>采购建议单</h4>
		        </div>
		        
		        <div class="m-toolbar f-mb15 f-clearfix">
		          
		             <ul class="search-con f-fl">
		            	<li>
		            	  <label>采购员:</label>
		        	  	  <input id="busiPerson" id="busiPerson" name = "busiPerson" value="${suggest.busiPerson}"  width="200px"  >
		       	         </li>
		       	          <li>
		       	            <label>业务日期:</label>
		        	  	     <div class="date"><input type="text" id="busiDate"  name = "busiDate" value="${suggest.busiDate?string('yyyy-MM-dd')}"  class="u-ipt u-ipt-sm"  /></div>
		                  </li>
		                  <li>
		                     <label>预计到货:</label>
		        	  	     <div class="date"><input type="text" id="planDate"  name = "planDate" class="u-ipt u-ipt-sm"  value="${suggest.planDate?string('yyyy-MM-dd')}" /></div>
		                   </li>
		                   
		              </ul>
		       
		    	  </div>
		        
			      <div class="m-detail-wrap">
			    		<div class="m-grid-wrap f-mb20 f-clearfix">
							<table id="blankId"></table>
							<div style="display:none" id="pageId"></div>
						</div>
				</div>
		</div>
</body>
	<script src="${TEMPLATE_BASE_PATH}/js/elem/select2-master/dist/js/select2.full.js?v=${config_front}"></script>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/purchase/suggest/edit.js?v=${config_front}"></script>


</html>

