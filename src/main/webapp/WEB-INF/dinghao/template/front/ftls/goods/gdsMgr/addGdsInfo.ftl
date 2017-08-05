	<#include "/template/front/header.ftl">
	<style>
		/*======== 标签切换样式 ========*/
		ul.cutTab{ zoom:1; overflow:hidden;}
		ul.cutTab li{ float:left; width:200px; height:30px; line-height:30px; margin-right:20px; border:1px solid #999; text-align:center; background:#f1f1f1; cursor:pointer;}
		ul.cutTab li.readOn{ background:#FFC; color:#00C;}
		.cutTabCon{ display:none; border:1px solid #999; height:200px;}
		.upload-box{position:relative; width:200px;height:200px; text-align:center;}
		.photoUpload{ width:152px;}
		.transparent_class{filter: alpha(opacity=0);-moz-opacity: 0;-khtml-opacity: 0;opacity: 0;padding: 0px;top: 0px;right: 0px;font-family: Arial;font-size: 20px;cursor: pointer;position: absolute;margin: 0;}
		.selectPics{position: absolute;top:142px;left: 20px;width:160px;height: 30px;line-height: 30px;border-radius: 0;background: #000;opacity:.8;filter:alpha(opacity=80);color: #fff;text-align: center;font-size: 12px;cursor:pointer;}
		.selectPics:hover{opacity:1;filter:alpha(opacity=100);}
	</style>
	<script src="${BASE_PATH}/dinghao/template/front/js/picLoad.js?v=${config_front}"></script>
	<script>
		$(function(){
			oms.cutCon("cutTab","click","readOn");  //标签切换调用
			$("#photoUpload").uploadImgs();
		})
	</script>
	<body>
		<input type="hidden" id="clsId" oldValue="">
		<input type="hidden" id="attbs">
		<form id="addGds" action="${BASE_PATH_TEMPLATE}/gdsMgr/goodsImgUpLoadFile.jhtml" method="post" enctype="multipart/form-data">
			<div class="m-detail-con f-clearfix">
				<div class="m-toolbar f-mb15 f-clearfix">
					<ul class="search-con f-fl" >
			        	<input type="button" class="u-btn u-btn-nm u-btn-bg-blue" value="分享" style="display:none">
			        	<input type="button" onclick="uploadsImg()" class="u-btn u-btn-nm u-btn-bg-blue" value="保存">
			        </ul>
			        <ul class="search-con f-fr">
			        	
			        </ul>
			    </div>
			    <div class="m-content">
			    	<h4 class="m-title">基本信息</h4>
					<ul class="m-message-list f-mb10 f-clearfix">
			            <li><label>商品编号:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="gdsNo" /></li>
			            <li><label>成分composition:</label><input type="text" class="u-ipt u-ipt-nm"  id="ingredient"/></li>
			            <li>
			            	<label><i class="requeid">*</i>品名fabric name:</label>
			            	<input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="gdsName"/>
			            </li>
			              <li><label>规格construction:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="gdsFormat"/></li>
			          
			            <li>
			            	<label>单位:</label>
			            	<select class="u-select u-select-nm" id="cal">
								<option value="公斤">公斤</option>
								<option value="米">米</option>
								<option value="码">码</option>
								<option value="件">件</option>
							</select>
			            </li>
			            <li>
			            	<label><i class="requeid">*</i>分类:</label><input type="text" class="u-ipt u-ipt-nm u-ipt-disabled" placeholder="输入内容" id="clsName" disabled=""/>
			            	<input type="button" onclick="setGdsCls()" class="u-btn u-btn-auto u-btn-bg-blue" value="设置">
			            </li>
			            <li>
			            	<label>属性:</label><input type="text" class="u-ipt u-ipt-nm u-ipt-disabled" placeholder="输入内容" id="attbsName" disabled=""/>
			            	<input type="button" onclick="setGdsAttb()" class="u-btn u-btn-auto u-btn-bg-blue" value="设置">
		            	</li>
		            	<li>
		            		<label>销售价(元):</label>
		            		<input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="gdsSelPrice" realMoney="0.00"/>
	            		</li>
			            <li><label>条形码:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="gdsPact"/></li>
			            <li><label>克重gsm:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="standWeight" amountFlag/>克/平方米</li>
		                <li><label>纱支yarn count:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="artNo" /></li>
		                <li><label>门幅width:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="generatePact" amountFlag /></li>
		             </ul>
		             <h4 class="m-title">备案信息</h4>
					 <ul class="m-message-list f-mb10 f-clearfix">
					   <li><label>品牌:</label><input type="text" class="u-ipt u-ipt-nm"  id="brand"/></li>
			             <li><label>规格代码:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="skuOuterId"/></li>
			         
			           <li><label>用途:</label><input type="text" class="u-ipt u-ipt-nm"  id="purpose"/></li>
			           <li><label>生产企业:</label><input type="text" class="u-ipt u-ipt-nm"  id="producer"/></li>
			           <li><label>生产国代码:</label><input type="text" class="u-ipt u-ipt-nm"  id="countrycode"/></li>
			           <li><label>生产国名称:</label><input type="text" class="u-ipt u-ipt-nm"  id="countryname"/></li>
			           <li><label>有效期:</label><input type="text" class="u-ipt u-ipt-nm"  id="expiration"/></li>
			           
					 </ul>
		             <dl style="height:210px;">
				         <dt>图片上传：</dt>
				         <dd>
				         	<div class="upload-box">
				         		<div style="width:160px;height:160px;margin:10px auto 0;">
				         			<img src="http://imgcache.qq.com/club/item/wallpic/items/1/5411/1366_768_5411.jpg" width="160" height="160" class="Img"/>
				         		</div>
				         		<input type="file" name="file" accept="" id="photoUpload" class="transparent_class" />
				         		<span class="selectPics" onclick="$('#photoUpload').click()">点击上传图片</span>                  		
				         	</div>
				         </dd>
				     </dl>
				     
		             <h4 class="m-title">库存预警</h4>
						<ul class="m-message-list f-mb10 f-clearfix">
				            <li><label>最低库存:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="gdsLowAmount" justNumber/></li>
				            <li><label>最高库存:</label><input type="text" class="u-ipt u-ipt-nm" placeholder="输入内容" id="gdsHighAmount" justNumber/></li>
				        </ul>
				      <h4 class="m-title">其他</h4>
					<ul class="m-message-list f-mb10 f-clearfix">
			            <li>
			            	<label>商品状态:</label>
			            	<div class="input-con">
			           			<input type="radio" name="gdsStatus" value="1">启用</input>
			           		</div>
			           		<div class="input-con">
			           			<input type="radio" name="gdsStatus" value="0">停用</input>
			       			</div>
		            	</li>
			            <li>
			            	<label>备注:</label>
			            	<textarea class="u-textarea u-textarea-nm" rows="3" id="remark"></textarea>
		            	</li>
			        </ul>
				</div>
			</div>
		</form>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/goods/gdsMgr/addGdsInfo.js?v=${config_front}"></script>	
</html>