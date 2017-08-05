<#assign menu="admin_list"> <#assign submenu="add_admin"> <#include
"/manage/head.ftl">
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading"> 添加会员 </header>
					<div class="panel-body">
						<form id="add_admin_form" method="post" class="form-horizontal"
							autocomplete="off" action="${BASE_PATH}/manage/templateadmin/addNew.jhtml">
							<fieldset>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label"> <span
										class="requiredField">*</span>用户名
									</label>
									<div class="col-sm-10">
										<input type="text"
											class="form-control validate[required,custom[onlyLetterNumber]]"
											name="username" placeholder="用户名(登陆名)" id="username" vaule="">
									</div>
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
											name="email" placeholder="管理员邮箱" id="email" vaule="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label"> <span
										class="requiredField">*</span>角色
									</label>
									<div class="col-sm-10">
										<#list roles as e><#if e.id gt 1> <label style="margin-right: 30px;">
											<input type="checkbox" name="roleIds"
											class="validate[required]" value="${e.id}">${e.name}
										</label></#if> </#list>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">设置 </label>
									<div class="col-sm-10">
										<label> <input type="checkbox" name="isEnabled"
											class="" value="true">是否启用
										</label>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">姓名 </label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="name"
											placeholder="姓名" id="name" vaule="">
									</div>
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
	src="${BASE_PATH}/dinghao/manage/js/htmljs/templateadmin/add.js?v=${config_v}"></script>
<script type="text/javascript">
	<#if error??>
		layer.alert("${error}", {icon: 2});
	</#if>
	
	<#if success??>
		layer.alert("${success}", {icon: 1});
	</#if>
</script>
<#include "/manage/foot.ftl">
