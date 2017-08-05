<#assign menu="update_password">
<#assign submenu="update_admin">
<#include "/manage/head.ftl">
<style type="text/css">
</style>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading"> 修改密码</header>
					<div class="panel-body">
						<form id="update_admin_form" method="post" class="form-horizontal"
							autocomplete="off" action="${BASE_PATH}/manage/admin/updatePasswordJson.jhtml">
							<fieldset>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label">用户名</label>
									<div class="col-sm-10">
										${admin.name}
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label"><span
										class="requiredField">*</span>原始密码</label>
									<div class="col-sm-10">
										<input type="password" id="OldPassword"
											class="form-control validate[required,minSize[6]] "
											name="OldPassword" placeholder="原始密码">
									</div>
								</div>							
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label"><span
										class="requiredField">*</span>新密码</label>
									<div class="col-sm-10">
										<input type="password" id="password"
											class="form-control validate[required,minSize[6]] "
											name="password" placeholder="新密码">
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
									<label class="col-sm-2 col-sm-2 control-label"></label>
									<div class="col-sm-10">
										<button class="btn btn-danger" type="submit">修改</button>
									</div>
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
	src="${BASE_PATH}/dinghao/manage/js/htmljs/admin/updatePassword.js?v=${config_v}"></script>
<#include "/manage/foot.ftl">
