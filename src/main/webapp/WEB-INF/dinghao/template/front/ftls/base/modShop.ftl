	<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
			<div class="m-toolbar f-mb15 f-clearfix">
				<input type="hidden" id="id" value="${shop.id}">
				<ul class="search-con f-fr">
		        	<input type="button" onclick="saveShop()" class="u-btn u-btn-lg u-btn-bg-blue" value="保存">
		        </ul>
		    </div>
		    <div class="m-content">
		    	<h4 class="m-title">基本信息</h4>
					<ul class="m-message-list f-mb10 f-clearfix">
			        	<li>
			            	<label>店铺编号:</label>
			                <input type="text" class="u-ipt u-ipt-nm u-ipt-disabled" placeholder="输入内容" id="code" value="${shop.code}" disabled=""/>
			            </li>
			            <li>
			                <label>平台:</label>
			                <input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" value="${shop.planType}"  id="planType" />
			            </li>
			            <li>
			                <label>店铺名称:</label>
			                <input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" value="${shop.name}"  id="name" />
			            </li>
			            <li>
			                <label>卖家昵称:</label>
			                <input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" value="${shop.nickname}"  id="nickname" />
			            </li>
			            <li>
			                <label>联系电话:</label>
			                <input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" value="${shop.phone}"  id="phone" />
			            </li>
			            <li>
			                <label>手机:</label>
			                <input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" value="${shop.mobile}"  id="mobile" />
			            </li>
		            </ul>
				</div>
				<div class="m-content">
			    	<h4 class="m-title">物流信息</h4>
			        <ul class="m-message-list f-mb10 f-clearfix">
			        	<li>
			            	<label>默认仓库:</label>
			                <select class="u-select u-select-nm" id="warehouseId">
								<option value="">请选择</option> 
								<#list warehouses as warehouse>
								   <#if shop.warehouseId==warehouse.id>
								        <option value="${warehouse.id}"   selected="selected" >${warehouse.warehouseName}</option>
								   <#else>
								     <option value="${warehouse.id}">${warehouse.warehouseName}</option>
								    </#if>
								 </#list>
						    </select>
			            </li>
			            <li style = "display:none">
			            	<label>默认物流:</label>
			               	<select class="u-select u-select-nm" id="expid">
						    	<option value="">请选择</option> 
								<#list expresslist as express>
								   <#if shop.expid==express.id>
								       <option value="${express.id}"   selected="selected" >${express.name}</option>
								   <#else>
								       <option value="${express.id}">${express.name}</option>
								    </#if>
								</#list>
						    </select>
			            </li>
			            <li>
			               <label>所在地区:</label>
			               <input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" value="${shop.provName}-${shop.cityName}-${shop.countyName}"  id="areaName" />
			            </li>
			            <li>
			               <label>发货地址:</label>
			               <input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容"  value="${shop.address}"  id="address" />
			            </li>
			            <li>
			               <label>邮政编码:</label>
			               <input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容"  value="${shop.zipcode}"  id="zipcode" />
			            </li>
			            <li>
			                <label>默认打印格式:</label>
			                <select class="u-select u-select-nm" id="printId">
								<option value="">请选择</option> 
								<#list printTemplatelist as printTemplate>
								    <#if shop.printId==printTemplate.id>
								        <option value="${printTemplate.id}"   selected="selected" >${printTemplate.name}</option>
								    <#else>
								        <option value="${printTemplate.id}">${printTemplate.name}</option>
								    </#if>
								</#list>
						    </select>
			            </li>
			        </ul>
			    </div>
			     
			    <div class="m-content">
			    	<h4 class="m-title">平台接口</h4>
		            <ul class="m-message-list f-mb10 f-clearfix">
		            	<li>
		               		<label>appkey:</label>
		               		<input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容"  value="${shop.appkey}"  id="appkey" />
			            </li>
			            <li>
			               <label>appsecret:</label>
			               <input  type="password" class="u-ipt u-ipt-nm" placeholder="输入内容"  value="${shop.appsecret}"  id="appsecret" />
			            </li>
			            <li>
			               <label>sessionkey:</label>
			               <input type="password" class="u-ipt u-ipt-nm" placeholder="输入内容"  value="${shop.sessionkey}"  id="sessionkey" />
			            </li>
			        </ul>
			    </div>
		        <div class="m-content">
			    	<h4 class="m-title">其他</h4>
		            <ul class="m-message-list f-mb10 f-clearfix">
		            	<li>
			            	<label>状态:${shop.beactive} </label>
			            	<div class="position-relative display-block">
			           			<input type="radio" name="beactive"  id="beactive" value="1"  <#if shop.beactive == 'true' >checked ="checked"  </#if>  >启用</input>
			           			<input type="radio" name="beactive"  id="beactive" value="0"  <#if shop.beactive != 'true' >checked ="checked" </#if>  >停用</input>
			       			</div>
	            	    </li>
	            	</ul>
		        </div>
	        </div>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/base/modShop.js?v=${config_front}"></script>	
</html>