	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
		     <div class="m-form-tit f-mb20 f-clearfix">
		        	<ul class="m-form-grd-lt">
		                <li>
		                 <label>单据编号：</label>
		                  <input type="text" id="suggestNo" disabled="" class="u-ipt u-ipt-nm u-ipt-disabled" placeholder="自动生成">
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
		                   <input type="button" id= "save" class="u-btn u-btn-auto u-btn-bg-blue" value="保存">
		                 </li>
		            </ul>
		            
		            <h4>采购建议单</h4>
		        </div>
		        
		        <div class="m-toolbar f-mb15 f-clearfix">
		         <form id="Form1">
		         
		             <input id="stockId" value="${stockId}" type="hidden" >
		             
		             <ul class="search-con f-fl">
		            	<li>
		            	  <label>采购员:</label>
		        	  	  <input id="busiPerson" name = "busiPerson" width="200px"  >
		        	  	 </li>
		        	  	 <li>
		       	           <label>业务日期:</label>
		        	  	    <div class="date"><input type="text" id="busiDate"  name = "busiDate" value="${busi_date}" c class="u-ipt u-ipt-sm"  value="" /></div>
		                  </li>
		                  <li>
		                   <label>预计到货:</label>
		        	  	   <div class="date"><input type="text" id="planDate"  name = "planDate" class="u-ipt u-ipt-sm"  value="${plan_date}" /></div>
		            
		        	       </li>
		              </ul>
		          </form>
		    	  </div>
		        
		
			 <div class="m-detail-wrap">
			       <div class="m-grid-wrap f-mb20 f-clearfix">
		    	
		            <table id="blackId"></table>
		           </div>
		      </div>
		     
		  
		</div>
	</body>
	<script src="${TEMPLATE_BASE_PATH}/js/elem/select2-master/dist/js/select2.full.js?v=${config_front}"></script>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/purchase/suggest/add.js?v=${config_front}"></script>
</html>

