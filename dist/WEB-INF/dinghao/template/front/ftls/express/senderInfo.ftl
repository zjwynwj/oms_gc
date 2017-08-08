	<#include "/template/front/header.ftl">
	<link rel="stylesheet" type="text/css" href="${BASE_PATH}/dinghao/template/front/js/elem/select2-master/dist/css/select2.min.css">
	<body class="bgf">
		<form id="iform" action="${BASE_PATH}/template/senderInfoMgr/saveSenderInfo.jhtml">
			 <div class="m-content">
				<ul class="m-message-list f-mb10 f-clearfix">
		            <li>
		            	<label><i style="color:red;margin: 0px 5px;">*</i>发货人姓名:</label>
		            	<input type="text" class="u-ipt u-ipt-nm verify" verifyData="{type:'allcheck',length:'1,10' ,required:'yes'}" id="senderName" name="senderName"/>
	            	</li>
	            	<li>
		            	<label><i style="color:red;margin: 0px 5px;">*</i>手机:</label>
		            	<input type="text" class="u-ipt u-ipt-nm verify" verifyData="{type:'mobile'}" id="mobile" name="mobile"/>
	            	</li>
	            	<li>
		            	<label>固定电话:</label>
		            	<input type="text" class="u-ipt u-ipt-nm " id="telephone" name="telephone"/>
	            	</li>
	            	<li>
		            	<label><i style="color:red;margin: 0px 5px;">*</i>省市区:</label>
		            	<input type="text"   id="area" name="area" autocomplete="off" readonly class="u-ipt u-ipt-nm" />
	            	</li>
	            	<li>
		            	<label><i style="color:red;margin: 0px 5px;">*</i>详细地址:</label>
		            	<input type="text" class="u-ipt u-ipt-nm verify" verifyData="{type:'allcheck',length:'1,32' ,required:'yes'}" id="sendAddress" name="sendAddress"/>
	            	</li>
	            	<li>
		            	<label>邮编:</label>
		            	<input type="text" class="u-ipt u-ipt-nm " id="postcode" name="postcode"/>
	            	</li>
	            	<li>
		            	<label>店铺名称:</label>
		            	<input type="text" class="u-ipt u-ipt-nm " id="shopTitle" name="shopTitle"/>
	            	</li>
	            	<li>
		            	<label>备注1:</label>
		            	<input type="text" class="u-ipt u-ipt-nm " id="memo1" name="memo1"/>
	            	</li>
	            	<li>
		            	<label>备注2:</label>
		            	<input type="text" class="u-ipt u-ipt-nm " id="memo2" name="memo2"/>
	            	</li>
	            	<li>
		            	<label>备注3:</label>
		            	<input type="text" class="u-ipt u-ipt-nm " id="memo3" name="memo3"/>
	            	</li>
	             </ul>
	             <ul class="search-con f-fl">
	             	<li>
		        		<input type="button" onclick="saveSenderInfo()" class="u-btn u-btn-nm u-btn-bg-blue" value="保存">
	        		</li>
		        </ul>
		        <input type='hidden' id="id" name="id">
		        <input type='hidden' id="state" name="state">
		        <input type='hidden' id="city" name="city">
		        <input type='hidden' id="district" name="district">
			</div>
		</form>
	</body>
	<script src="${BASE_PATH}/dinghao/template/front/js/newsArea.js?v=${config_front}"></script>
	<script src="${BASE_PATH}/dinghao/template/front/js/areaSec.js?v=${config_front}"></script>	
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/express/senderInfo.js?v=${config_front}"></script>
</html>