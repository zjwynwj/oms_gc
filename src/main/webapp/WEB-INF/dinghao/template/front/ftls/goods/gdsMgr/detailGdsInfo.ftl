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
		<input type="hidden" id="id" value="${gdsInfoVo.id}">
		<form id="addPic" action="${BASE_PATH_TEMPLATE}/gdsMgr/goodsImgUpLoadFile.jhtml" method="post" enctype="multipart/form-data">
			<div class="m-detail-con f-clearfix">
				<div class="m-toolbar f-mb15 f-clearfix">
					<ul class="search-con f-fl">
			        	<input type="button" class="u-btn u-btn-lg u-btn-bg-blue" value="分享">
			        </ul>
			      </div>
			    <div class="m-content">
					<h4 class="m-title">基本信息</h4>
					<ul class="m-message-list f-mb10 f-clearfix">
			            <li><label>商品编号:</label><input type="text" class="u-ipt u-ipt-nm u-ipt-disabled"  id="gdsNo" disabled=""/></li>
			            <li><label>规格代码:</label><input type="text" class="u-ipt u-ipt-nm"  id="skuOuterId" disabled=""/></li>
			            <li><label>商品名称:</label><input type="text" class="u-ipt u-ipt-nm"  id="gdsName" disabled=""/></li>
			            <li>
			            	<label>单位:</label>
			            	<select id="cal" disabled="">
								<option value="盒">盒</option>
								<option value="支">支</option>
								<option value="个">个</option>
							</select>
			            </li>
			            <li><label>规格:</label><input type="text" class="u-ipt u-ipt-nm" id="gdsFormat" disabled=""/></li>
			            <li>
			            	<label>分类:</label><input type="text" class="u-ipt u-ipt-nm u-ipt-disabled"  id="clsName" disabled=""/>
			            </li>
			            <li>
			            	<label>属性:</label><input type="text" class="u-ipt u-ipt-nm u-ipt-disabled"  id="attbsName" disabled=""/>
		            	</li>
				
			            <li><label>销售价(元):</label><input type="text" class="u-ipt u-ipt-nm" id="gdsSelPrice" disabled=""/></li>
			            <li><label>条形码:</label><input type="text" class="u-ipt u-ipt-nm" id="gdsPact" disabled=""/></li>
			        	<li><label>标准重量:</label><input type="text" class="u-ipt u-ipt-nm"  id="standWeight" disabled=""/>kg</li>
			         </ul>
			         
			        <dl style="height:210px;">
				         <dt>图片上传：</dt>
				         <dd>
				         	<div class="upload-box">
				         		<div style="width:160px;height:160px;margin:10px auto 0;">
				         			<img src="http://imgcache.qq.com/club/item/wallpic/items/1/5411/1366_768_5411.jpg" width="160" id="imgShow" height="160" class="Img"/>
				         		</div>
				         		<input type="file" name="file" accept="" id="photoUpload" class="transparent_class" />
				         	</div>
				         </dd>
				     </dl>
			         
			         <h4 class="m-title">库存预警</h4>
					<ul class="m-message-list f-mb10 f-clearfix">
			            <li><label>最低库存:</label><input type="text" class="u-ipt u-ipt-nm" id="gdsLowAmount" disabled=""/></li>
			            <li><label>最高库存:</label><input type="text" class="u-ipt u-ipt-nm" id="gdsHighAmount" disabled=""/></li>
			        </ul>
					 <h4 class="m-title">其他</h4>
					<ul class="m-message-list f-mb10 f-clearfix">
			            <li>
			            	<label>商品状态:</label>
			            	<div class="position-relative display-block">
			           			<input type="radio"  name="gdsStatus" value="1" disabled="">启用</input>
			           			<input type="radio"  name="gdsStatus" value="0" disabled="">停用</input>
			       			</div>
		            	</li>
			            <li>
			            	<label>备注:</label>
			            	<textarea class="u-textarea u-textarea-nm" rows="3" id="remark" disabled=""></textarea>
		            	</li>
			        </ul>
				</div>
			</div>
		</form>
	</body>
	<script	src="${BASE_PATH}/dinghao/template/front/js/bizjs/goods/gdsMgr/detailGdsInfo.js?v=${config_front}"></script>	
</html>