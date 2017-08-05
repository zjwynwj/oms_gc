<#assign menu="role"> <#assign submenu="role_update"> <#include
"/manage/head.ftl">
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!-- page start-->
		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading">修改角色</header>
					<div class="panel-body">
						<form id="update_form" method="post" class="form-horizontal"
							autocomplete="off" action="${BASE_PATH}/manage/role/updateJson.jhtml">
							<input type="hidden" name="id"value="${roleOnly.id}">
							<fieldset>
								<div class="form-group">
									<label class="col-sm-1 control-label"> <span
										class="requiredField">*</span>名称
									</label>
									<div class="col-sm-5">
										<input type="text" class="form-control validate[required]"
											name="name" placeholder="名称" id="name" value="${roleOnly.name}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-1 control-label">描述</label>
									<div class="col-sm-5">
										<input type="text" id="description" class="form-control"
											name="description"value=${roleOnly.description} placeholder="描述">
									</div>
								</div>
								<div role="grid" class="dataTables_wrapper"
									id="hidden-table-info_wrapper">
									<#list parentList.list as e>
									<table class="table table-striped table-advance table-hover"
										style="margin-bottom: 0px;">
										<thead>
											<tr>
												<th width="20%">父节点：${e.name}</th>
												<th></th>
											</tr>
										</thead>
										<tbody role="alert" aria-live="polite" aria-relevant="all">
											<tr>
												<td></td>
												<td><@dinghao_menu_children_list_tag id=e.id> <#list
													tag_menu_children_list as tag_menu> 
													<#if tag_menu.delFlag??
													&& tag_menu.delFlag=='1'>
													<span
													style="margin: 3px 10px; display: inline-block;"> <input
														<#list menuList as menuId>
															<#if menuId== tag_menu.id>
																checked="checked"
															</#if>
														</#list>													
														type="checkbox"  name="menus" id="menu_${tag_menu.id}" class="validate[required]"  value="${tag_menu.id}">
														${tag_menu.name}[<span class="green">正常</span>]</#if>
												</span></#list> </@dinghao_menu_children_list_tag>
												</td>
											</tr>
										</tbody>
									</table>
									</#list>
								</div>
								<div class="form-group">
									<label class="col-sm-2 col-sm-2 control-label"></label>
									<button class="btn btn-danger" id="submitForm" type="submit">确认修改</button>
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
	src="${BASE_PATH}/dinghao/manage/js/htmljs/role/update.js?v=${config_v}"></script>
<#include "/manage/foot.ftl">
