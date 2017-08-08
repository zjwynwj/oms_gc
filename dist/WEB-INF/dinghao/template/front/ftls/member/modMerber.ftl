<#include "/template/front/header.ftl">
	<body>
		<div class="m-detail-con f-clearfix">
			<div class="m-toolbar f-mb15 f-clearfix">
				<input type="hidden" id="id" value="${member.id}">
			    <input type="hidden" id="userid" value="${member.userid}">
				<ul class="search-con f-fr">
		        	<input type="button" onclick="saveCustInfo()" class="u-btn u-btn-lg u-btn-bg-blue" value="保存">
		        </ul>
		    </div>
		    <div class="m-content">
		     <h4 class="m-title">基本信息</h4>
				<ul class="m-message-list f-mb10 f-clearfix">
		             <li>
		               <label>店铺名称:</label>
		               <input type="hidden" id="shopId" value="${member.shopId}">
		               <input type="text" class="u-ipt u-ipt-nm u-ipt-disabled"  id="shopName" value="${member.shopName}" />
		            </li>
		             <li>
		               <label>会员昵称:</label>
		               <input type="text" class="u-ipt u-ipt-nm u-ipt-disabled"  id="nick" value="${member.nick}" />
		            </li>
		             <li>
		               <label>会员姓名:</label>
		               <input type="text" class="u-ipt u-ipt-nm "  id="code" value="${member.username}" />
		            </li>
		            <li>
		               <label>性别:</label>
		               <input type="text" class="u-ipt u-ipt-nm "  id="sex" value="${member.sex}" />
		             </li>
		             <li>
		               <label>生日:</label>
		               <div class="date">
		                 <input type="text" class="u-ipt u-ipt-nm "  id="birthday" value="${member.birthday}"        />
		                 </div>
		             </li>
		             <li>
		               <label>购买次数:</label>
		               <input type="text" class="u-ipt u-ipt-nm "  id="buytimes" value="${member.buytimes}" />
		             </li>
	            </ul>
			</div>
			 <div class="m-content">
		     <h4 class="m-title">联系信息</h4>
				<ul class="m-message-list f-mb10 f-clearfix">
				   <li>
		               <label>手机号码:</label>
		               <input type="text" class="u-ipt u-ipt-nm "  id="code" value="${member.recvmobile}" />
		            </li>
		             <li>
		               <label>固定号码:</label>
		               <input type="text" class="u-ipt u-ipt-nm "  id="code" value="${member.recvphone}" />
		            </li>
		            
		              <li>
		               <label>所在地区:</label>
		               <input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" value="${member.provname}-${member.cityname}-${member.countyname}"  id="areaName" />
		            </li>
		            
				 </ul>
			</div>
		</div>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/base/modMember.js?v=${config_front}"></script>	
</html>