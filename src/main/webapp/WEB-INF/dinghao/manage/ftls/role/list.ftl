<#assign menu="role"> <#assign submenu="role_list"> <#include
"/manage/head.ftl">
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading">
						<i class="icon-home"></i> 角色信息列表
						<div style="float: right;">
							<a class="btn btn-primary"
								href="${BASE_PATH}/manage/role/add.jhtml"><i
								class="icon-plus"></i>&nbsp;新增角色信息</a>
						</div>
					</header>
					<div class="panel-body">
						<div class="adv-table">
							<div role="grid" class="dataTables_wrapper"
								id="hidden-table-info_wrapper">
								<table class="table table-striped table-advance table-hover">
									<thead>
										<tr>
											<th width="20%">名称</th>
											<th width="10%">是否内置</th>
											<th width="60%">描述</th>
											<th width="10%">操作</th>
										</tr>
									</thead>
									<tbody role="alert" aria-live="polite" aria-relevant="all">
									<#list rolesList.list as e>
										<tr>
											<td>${e.name}</td>
											<td><#if e.isSystem??&&e.isSystem>是<#else>否</#if> </td>
											<td>${e.description}</td>
											<td>
												
												<#if !(e.isSystem??&&e.isSystem)>
												<a
												href="${BASE_PATH}/manage/role/update.jhtml?id=${e.id}"
												title="修改"><i class=" icon-edit"></i>&nbsp;&nbsp; </a>
												<a
												href="javascript:void(0);" data-value="${e.id}" title="删除"
												class="js_delete_role"><i class="icon-remove-circle"></i>&nbsp;&nbsp;</a></#if>
												
											</td>
										</tr>
									</#list>
									</tbody>
								</table>

								<div style="height: 30px;">
									<div class="pagination">${rolesList.pageNumHtml}</div>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
			<!-- page end-->
	</section>
</section>
</script>
<script
	src="${BASE_PATH}/dinghao/manage/js/htmljs/role/list.js?v=${config_v}"></script>
<#include "/manage/foot.ftl">
