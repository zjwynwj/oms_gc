	<#include "/template/front/header.ftl">
	<body class="bgf7"> 
		<form id="iform">
			<div class="m-detail-con f-clearfix">
		        <div class="m-toolbar f-mb15 f-clearfix">
					<ul class="search-con f-lt">
						<a href="javascript:numberInfo_save()" class="u-btn u-btn-auto u-btn-bg-blue">保存</a>
			        </ul>
			    </div>
			
			    <div class="m-content">
				    <h4 class="m-title">基本信息</h4>
					<ul class="m-message-list f-mb10 f-clearfix">
						<input type="text" hidden="true" readOnly class="txt01 col-xs-7" placeholder="" id="BUSInumberOfId" value="${baseNumberVo.id}"/>
						<li>
							<label>单据类型:</label>
						    <input type="text" readOnly class="u-ipt u-ipt-nm u-ipt-disabled" placeholder="" id="BUSInumberOfNuName" disabled=""/>
						</li>
						<li>
						    <label>单据前缀:</label>
						    <input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="BUSInumberOfNuPrefix" verifyData="{type:'allcheck',length:'1,4' ,required:'yes'}"  onchange="doChange()"/>
						</li>
					    <li>
						    <label>是否需要审核:</label>
					    	<select id="BUSInumberOfIsCheck" class="u-select u-select-nm" >
				        		<option value="true">是</option>	    
				        		<option value="false">否</option>        
				        	</select>
						 </li>
						
						 <li>
						     <label>编码位数:</label>
						     <input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="BUSInumberOfNuDigit" verifyData="{type:'number',length:'1,1' ,required:'yes'}" onchange="doChange()" />
						 </li>
				         <li>
						     <label>当前编号:</label>
						      <input type="text" readOnly class="u-ipt u-ipt-nm" placeholder="输入内容" id="BUSInumberOfNuCurrent"/>	
						 </li>
						 <li>
						     <label>步频:</label>
						     <input type="text" class="u-ipt u-ipt-nm verify"  placeholder="输入内容" id="BUSInumberOfNuStep" verifyData="{type:'number',length:'1,1' ,required:'yes'}"  />
						 </li>
						 <li>
						 	<label>单据样例:</label>
						    <input type="text" style="width:220px;" readOnly class="u-ipt u-ipt-nm f_fl" placeholder="输入内容" id="BUSInumberOfNuDemo"/>
						    	<div class="input-con col-xs-3 f-pl5 f_fr">
					        		<input type="checkbox" id="BUSInumberOfYearRule" onclick="doChange()" >年		        	
					        		<input type="checkbox" id="BUSInumberOfMontRule" onclick="doChange()" >月		       
					        		<input type="checkbox" id="BUSInumberOfDayRule" onclick="doChange()" >日
					        	</div>
						 </li>
					</ul>
				</div>
			</div>
		</form>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/base/modNumber.js?v=${config_front}"></script>
</html>