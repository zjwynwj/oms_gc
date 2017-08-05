	<#include "/template/front/header.ftl">
	<link rel="stylesheet" type="text/css" href="${BASE_PATH}/dinghao/template/front/js/elem/select2-master/dist/css/select2.min.css?v=${config_front}">
	<script src="${BASE_PATH}/dinghao/template/front/js/elem/select2-master/dist/js/select2.min.js?v=${config_front}"></script>
	<body>
		<div class="m-detail-con f-clearfix">
			 <div class="m-form-tit f-mb20 f-clearfix">
				  <input type="hidden"  id="purId" value="${id}">
				  
				 <ul class="m-form-grd-lt"> 
					    <li><label>采购编号:</label><input type="text" class="u-ipt u-ipt-nm u-ipt-disabled" placeholder="输入内容" id="purNo" disabled=""/></li>
				    </ul>
					
					<ul class="m-form-grd-rt">
				        <li>
				        <input type="button"  id="save" class="u-btn u-btn-bg-blue  u-btn-auto" value="保存">
				        </li>
				         <li>
				            	<label>采购类型:</label>
				            	<select class="u-select u-select-nm" id="purType">
								    <option value="0">零售</option>
								    <option value="1">批发</option>
								</select>
							</li>
				    </ul>
				      <h4>采购订单</h4>
			  </div>
		        
		       <div class="m-toolbar f-mb15 f-clearfix">
				    <ul class="search-con">
				            <li>
				            	<label style = "float:left;width:auto;" >客户名称：</label> 
				            	
				            	<input id="remarks" 	name="remarks" class="u-ipt u-ipt-nm " >
				            	<input id="providerId" type="text" name="providerId" hidden="true">
			            	</li>
				           
				            <li><label style = "float:left;width:auto;" >业务员:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="createBy"/></li>
				            <li>
				            	<label style = "float:left;width:auto;" >业务日期:</label>
				            	<div class="date"><input type="text" class="u-ipt u-ipt-sm" id="busiDate"/></div>
			            	</li>
			            </ul>
			            
			            <ul class="search-con">
							<li>
								<label style = "float:left;width:auto;" >订单金额:</label>
								<input type="text" class="u-ipt u-ipt-nm u-ipt-disabled"  realMoney placeholder="订单金额" id="payMoney" readonly=true value="0.00" style="TEXT-ALIGN:right"/>
							</li>
							<li>
								<label style = "float:left;width:auto;" >应付日期:</label>
								<div class="date"><input type="text" class="u-ipt u-ipt-sm" id="dueDate" readonly=true/></div>
							</li>
						</ul>
					</div>
					<div class="m-content">
						<div class="m-detail-wrap">
							<table id="purOrderGridId"></table>
						</div>
					</div>
		</div>
	</body>
	<script>
		$(function(){
			$("#busiDate").datepicker({
				showOn: "button", 
				buttonImage: template_base +"/js/elem/jqueryui/css/images/calendar.gif",
				buttonImageOnly: true  
			});
		});
	</script>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/purchase/purOrder/modPurOrder.js?v=${config_front}"></script>	
</html>