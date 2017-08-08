	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
			<div class="m-toolbar f-mb15 f-clearfix">
				<input type="hidden" id="id" value="${custInfoVo.id}">
				<ul class="search-con f-fr">
		        	<input type="button" onclick="saveCustInfo()" class="u-btn u-btn-lg u-btn-bg-blue" value="保存">
		        </ul>
		    </div>
		    <div class="m-content">
				<ul class="m-message-list f-mb10 f-clearfix">
		            <li><label>客户编号:</label>
		                 <input type="text" class="u-ipt u-ipt-nm u-ipt-disabled" placeholder="输入内容" id="custNo"  value="${custInfo.custNo}" disabled=""/>
		             </li>
		            <li><label>客户名称:</label>
		                <input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="custName" value="${custInfo.custName}"/>
		             </li>
		            <li><label>联系人:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="linkMan" value="${custInfo.linkMan}"/></li>
		            <li><label>联系号码:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="linkPhone" value="${custInfo.linkPhone}" /></li>
		            <li><label>联系地址:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="linkAddr"  value="${custInfo.linkAddr}" /></li>
		            <li><label>传真:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="fax" value="${custInfo.fax}"  /></li>
		            <li><label>邮编 :</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="postCode"  value="${custInfo.postCode}" /></li>
		            <li><label>单位电话:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="compPhone"  value="${custInfo.compPhone}"/></li>
		            <li>
		            	<label>备注:</label>
		            	<textarea class="u-textarea u-textarea-nm" rows="3" id="remark1" value="${custInfo.remark1}" ></textarea>
	            	</li>
		            <li>
		            	<label>启用状态:</label>
		            	<div class="position-relative display-block">
		           			<input type="radio"  name="status" <#if custInfo.status == '1' >checked ="checked"  </#if>  value="1">启用</input>
		           			<input type="radio"  name="status" <#if custInfo.status == '0' >checked ="checked"  </#if>  value="0">停用</input>
		       			</div>
	            	</li>
	            </ul>
			</div>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/base/modCustInfo.js?v=${config_front}"></script>	
</html>