 <#escape x as x!""> 
<#assign menu="admin_list"> <#assign submenu="update_admin"> <#include
"/manage/head.ftl">
<style type="text/css">
</style>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading"> 修改管理员资料</header>
					<div class="panel-body">
						<form id="update_admin_form" method="post" class="form-horizontal"
							autocomplete="off" action="${BASE_PATH}/manage/templateadmin/updateJson.jhtml">
							<fieldset>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">用户名 </label> <input
										type="hidden" name="id" value="${admin.id}">
									<div class="col-sm-10">${admin.username}</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label"><span
										class="requiredField">*</span>密码</label>
									<div class="col-sm-10">
										<input type="password" id="password"
											class="form-control validate[required,minSize[6]] "
											name="password" placeholder="管理员密码">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label"><span
										class="requiredField">*</span>确认密码</label>
									<div class="col-sm-10">
										<input type="password"
											class="form-control validate[required,equals[password],minSize[6]]"
											name="rePassword" placeholder="确认密码">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label"><span
										class="requiredField">*</span>E-mail</label>
									<div class="col-sm-10">
										<input type="text"
											class="form-control validate[required,custom[email]]"
											name="email" placeholder="管理员邮箱" id="email"
											value="${admin.email}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label"> <span
										class="requiredField">*</span>角色
									</label>
									<div class="col-sm-10">
										<#list roles as e> <#if e.id gt 1> <label
											style="margin-right: 30px;"> <input type="checkbox"<#if
											admin.roles??> <#list admin.roles as role> <#if role==e.id>
											checked="checked" </#if> </#list> </#if> name="roleIds"
											class="validate[required]" value="${e.id}">${e.name}
										</label></#if> </#list>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">设置 </label>
									<div class="col-sm-10">
										<label> <input type="checkbox" name="isEnabled"<#if
											admin.isEnabled?? && admin.isEnabled> checked="checked"</#if>
											class="" value="true">是否启用
										</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">姓名 </label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="name"
											placeholder="姓名" id="name" value="${admin.name}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label"></label>
									<button class="btn btn-danger" id="submitForm" type="submit">修&nbsp;改</button>
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
<script type="text/javascript"
	src="${BASE_PATH}/dinghao/manage/js/htmljs/admin/update.js?v=${config_v}"></script>
<!--main content end-->
<#include "/manage/foot.ftl">
 </#escape>
