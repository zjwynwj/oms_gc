<#assign menu="menu"> <#assign submenu="menu_list"> <#include
"/manage/head.ftl">
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<div class="row">
			<div class="col-lg-12">
				<section class="panel">
					<header class="panel-heading">
						<i class="icon-home"></i> 导航栏目列表
						<div style="float: right;">
							<a class="btn btn-primary"
								href="${BASE_PATH}/manage/menu/add_menu.jhtml"><i
								class="icon-plus"></i>&nbsp;新增导航</a>
							<a class="btn btn-primary"
								href="${BASE_PATH}/manage/menu/add.jhtml"><i
								class="icon-plus"></i>&nbsp;新增父节点</a>
							
						</div>
					</header>
					<div class="panel-body">
						<div class="adv-table">
							<div role="grid" class="dataTables_wrapper"
								id="hidden-table-info_wrapper">
								<#list parentList.list as e>
								<table class="table table-striped table-advance table-hover">
									<thead>
										<tr>
											<th width="20%">父节点：${e.name}</th>
											<th><a
												href="${BASE_PATH}/manage/menu/add.jhtml?parentId=${e.id}"
												adminid="${e.id}" title="新增子节点" class="js_add_menu"><i
													class="icon-plus">&nbsp;&nbsp;</i></a> <a
												href="${BASE_PATH}/manage/menu/update.jhtml?id=${e.id}"
												title="修改"><i class=" icon-edit"></i>&nbsp;&nbsp; </a> <a
												href="javascript:void(0);" adminid="${e.id}" title="删除"
												class="js_delete_menu"><i class="icon-remove-circle"></i>&nbsp;&nbsp;</a></th>
										</tr>
									</thead>
									<tbody role="alert" aria-live="polite" aria-relevant="all">
										<tr>
											<td></td>
											<td><@dinghao_menu_children_list_tag id=e.id> <#list
												tag_menu_children_list as tag_menu><span
												style="margin: 3px 10px; display: inline-block;">${tag_menu_index+1}.<a
													href="${BASE_PATH}/manage/menu/update.jhtml?id=${tag_menu.id}"
													adminid="${e.id}" title="修改节点" class="js_add_admin">
														${tag_menu.name}</a>[<#if tag_menu.delFlag?? &&
													tag_menu.delFlag=='0'><span class="red">未启用</span><#else><span
													class="green">正常</span></#if>]
											</span></#list> </@dinghao_menu_children_list_tag>
											</td>
										</tr>
									</tbody>
								</table>
								</#list>

								<div style="height: 30px;">
									<div class="pagination">${parentList.pageNumHtml}</div>
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
	src="${BASE_PATH}/dinghao/manage/js/htmljs/menu/list.js?v=${config_v}"></script>
<#include "/manage/foot.ftl">
