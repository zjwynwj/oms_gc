<#assign menu="admin_list"> <#assign submenu="admin_list"> <#include
"/manage/head.ftl">
<style type="text/css">
.pagination {
	border-radius: 4px;
	display: inline-block;
	margin: 0;
	padding-left: 0;
}
</style>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading">
						<i class="icon-home"></i> 所有管理员列表
						<div style="float: right;">
							<a class="btn btn-primary"
								href="${BASE_PATH}//manage/admin/add.jhtml"><i
								class="icon-plus"></i>&nbsp;新增管理员</a>
						</div>
					</header>
					<div class="panel-body">
						<div class="adv-table">
							<div role="grid" class="dataTables_wrapper"
								id="hidden-table-info_wrapper">
								<table class="table table-striped table-advance table-hover">
									<thead>
										<tr>
											<th>用户名</th>
											<th>E-mail</th>
											<th>姓名</th>
											<th>状态</th>
											<th>创建日期</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody role="alert" aria-live="polite" aria-relevant="all">
										<#list pageVo.list as e>
										<tr class="gradeA odd">
											<td>${e.username}</td>
											<td>${e.email}</td>
											<td>${e.name}</td>
											<td><#if e.isEnabled?? && e.isEnabled> <span
												class="green">正常</span> <#else> <span class="red">未启用</span>
												</#if>
											</td>
											<td>${e.createDate?string("yyyy-MM-dd")}</td>
											<td><a
												href="${BASE_PATH}/manage/admin/update.jhtml?id=${e.id}"
												title="修改"><i class=" icon-edit"></i> </a> <#if e.id gt 1>| <a
												href="javascript:void(0);" adminid="${e.id}" title="删除"
												class="js_delete_admin"><i class="icon-remove-circle"></i>
											</a> </#if>

											</td>
										</tr>
										</#list>
									</tbody>
								</table>
								<div style="height: 30px;">
									<div class="pagination">${pageVo.pageNumHtml}</div>
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
	src="${BASE_PATH}/dinghao/manage/js/htmljs/admin/manage.js?v=${config_v}"></script>
<#include "/manage/foot.ftl">
