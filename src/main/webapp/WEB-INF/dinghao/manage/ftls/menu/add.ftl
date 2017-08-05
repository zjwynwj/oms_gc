<#assign menu="menu"> <#assign submenu="menu_add"> <#include
"/manage/head.ftl">
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading"> 添加导航 </header>
					<div class="panel-body">
						<form id="add_form" method="post" class="form-horizontal"
							autocomplete="off" action="${BASE_PATH}/manage/menu/addJson.jhtml">
							<input type="hidden" value="2" name="menuType">
							<fieldset>
								<div class="form-group">
									<label class="col-sm-1 control-label">父节点 </label>
									<div class="col-sm-5">
										<select name="parentId" id="parentId" class="form-control">
											<option value="">父节点</option> <#list parentList.list as e>
											<option value="${e.id}"<#if parentId?? && parentId
												== e.id> selected="selected"</#if> >${e.name}</option> </#list>
										</select>
									</div>
									<label class="col-sm-1 control-label"> <span
										class="requiredField">*</span>名称
									</label>
									<div class="col-sm-5">
										<input type="text" class="form-control validate[required]"
											name="name" placeholder="名称" id="name" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-1 control-label"><span
										class="requiredField">*</span>排序</label>
									<div class="col-sm-5">
										<input type="number" id="sort"
											class="form-control validate[required,custom[integer]] "
											name="sort" placeholder="排序">
									</div>
									<label class="col-sm-1 control-label">链接</label>
									<div class="col-sm-5">
										<input type="text" id="href" class="form-control" name="href"
											placeholder="链接(eg:/manage/role/list.jhtml)">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-1 control-label">图标</label>
									<div class="col-sm-5">
										<input type="text" id="icon" class="form-control" name="icon"
											placeholder="图标font-awesome (eg:icon-cogs)">
									</div>
									<label class="col-sm-1 control-label grandparents">所属导航</label>
									<div class="col-sm-5 menuType">
										<select name="grandparents" id="grandparents" class="form-control grandparents">
											<#list menuList.list as e>
											<option value="${e.id}">${e.name}</option> </#list>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-1 control-label">备注</label>
									<div class="col-sm-11">
										<input type="text" class="form-control" name="remarks"
											placeholder="备注" id="remarks" vaule="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">设置 <input
										type="checkbox" name="delFlag" style="margin-left: 10px;"
										value="1">启用
									</label>
									<div class="col-sm-3">
										<label> </label>
									</div>
									<label class="col-sm-3 control-label">新窗口打开 <input
										style="margin-left: 10px;" type="checkbox" name="target"
										class="" value="1">是
									</label>
									<div class="col-sm-3"></div>
								</div>


								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label"></label>
									<button class="btn btn-danger" id="submitForm" type="submit">增加</button>
								</div>
							</fieldset>
						</form>
					</div>
				</section>
			</div>
		</div>
		<!-- page end-->
	</section>
</section>
<!--main content end-->
<script type="text/javascript"
	src="${BASE_PATH}/dinghao/manage/js/htmljs/menu/add.js?v=${config_v}"></script>
<#include "/manage/foot.ftl">
