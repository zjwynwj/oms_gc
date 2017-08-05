	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
			<div class="m-toolbar f-mb15 f-clearfix">
			   
				<ul class="search-con f-fr">
		        	<input type="button" onclick="saveAccount()" class="u-btn u-btn-lg u-btn-bg-blue" value="保存">
		        </ul>
		    </div>
		    <form id="verifyCon">
		   <div class="m-content">
		       <h4 class="m-title">基本信息</h4>
		          <input type="hidden" id="id" value="${financeAccount.id}" />
				<ul class="m-message-list f-mb10 f-clearfix">
		            <li><label>账户类型:</label>
		               
		               <input type="type" id="type"  class="u-ipt u-ipt-nm" value="${financeAccount.type}" readonly="true" />
		            </li>
		            <li><label>银行名称:</label><input type="text" class="u-ipt u-ipt-nm verify" placeholder="输入内容" id="bankName"  value="${financeAccount.bankName}" verifyData="{type:'allcheck',required:'yes'}"/></li>
		            <li><label>账户:</label><input type="text" class="u-ipt u-ipt-nm verify" placeholder="输入内容" id="bankNo" value="${financeAccount.bankNo}" verifyData="{type:'allcheck',required:'yes'}" /></li>
		            <li><label>户名:</label><input type="text" class="u-ipt u-ipt-nm verify" placeholder="输入内容" id="bankAccount" value="${financeAccount.bankAccount}" verifyData="{type:'allcheck',required:'yes'}" /></li>
		            <li>
		               <label>期初金额:</label>
		               <input type="text" class="u-ipt u-ipt-nm verify"  id="amount" value="${financeAccount.amount}"  verifyData="{type:'money',required:'yes'}"/>
		            </li>
		            
		            <li>
		            	<label>启用状态:</label>
		            	<div class="position-relative display-block">
		           			<input type="radio"  name="status"  <#if financeAccount.status == '1' >checked ="checked"  </#if>  value="1">启用</input>
		           			<input type="radio"  name="status" <#if financeAccount.status == '0' >checked ="checked"  </#if>  value="0">停用</input>
		       			</div>
	            	</li>
	            	
	            	 <li>
		            	<label>默认账户:</label>
		            	<div class="position-relative display-block">
		           			<input type="checkbox"  id ="isdefault" name="isdefault" <#if financeAccount.isdefault == '1' >checked ="checked"  </#if> value="1" />
		           		
		       			</div>
	            	</li>
	            </ul>
			</div>
			</form>
		</div>
	</body>
	<script src="${BASE_PATH}/dinghao/template/front/js/verify.js?v=${config_front}"></script>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/base/modAccount.js?v=${config_front}"></script>	
</html>