	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
			<div class="m-toolbar f-mb15 f-clearfix">
			   
				<ul class="search-con f-fr">
		        	<input type="button" onclick="saveCustInfo()" class="u-btn u-btn-lg u-btn-bg-blue" value="保存">
		        </ul>
		    </div>
		   <div class="m-content">
		       <h4 class="m-title">基本信息</h4>
				<ul class="m-message-list f-mb10 f-clearfix">
		            <li><label>客户编号:</label><input type="text" class="u-ipt u-ipt-nm u-ipt-disabled" placeholder="输入内容" id="custNo" disabled=""/></li>
		            <li><label>客户名称:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="custName"/></li>
		            <li><label>联系人:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="linkMan"/></li>
		            <li><label>联系号码:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="linkPhone"/></li>
		            <li><label>联系地址:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="linkAddr"/></li>
		            <li><label>传真:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="fax"/></li>
		            <li><label>邮编 :</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="postCode"/></li>
		            <li><label>单位电话:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="compPhone"/></li>
		            <li>
		            	<label>备注:</label>
		            	<textarea class="u-textarea u-textarea-nm" rows="3" id="remark1"></textarea>
	            	</li>
		            <li>
		            	<label>启用状态:</label>
		            	<div class="position-relative display-block">
		           			<input type="radio"  name="status" value="1">启用</input>
		           			<input type="radio"  name="status" value="0">停用</input>
		       			</div>
	            	</li>
	            </ul>
			</div>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/base/addCustInfo.js?v=${config_front}"></script>	
</html>