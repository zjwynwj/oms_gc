<#assign menu="menu"> <#assign submenu="menu_add"> 
<#include "/manage/head.ftl">
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
							<input type="hidden" name="menuType" value="0">
							<fieldset>
								<div class="form-group">
									<label class="col-sm-1 control-label"> <span
										class="requiredField">*</span>名称
									</label>
									<div class="col-sm-5">
										<input type="text" class="form-control validate[required]"
											name="name" placeholder="名称" id="name" value="">
									</div>
									<label class="col-sm-1 control-label"><span
										class="requiredField">*</span>排序</label>
									<div class="col-sm-5">
										<input type="number" id="sort"
											class="form-control validate[required,custom[integer]] "
											name="sort" placeholder="排序">
									</div>
								</div>
								
								<div class="form-group">
									
									<label class="col-sm-1 control-label">备注</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" name="remarks"
											placeholder="备注" id="remarks" vaule="">
									</div>
									<label class="col-sm-3 control-label">设置 <input
										type="checkbox" name="delFlag" style="margin-left: 10px;"
										value="1">启用
									</label>
									<div class="col-sm-3">
										<label> </label>
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
	src="${BASE_PATH}/dinghao/manage/js/htmljs/menu/add.js?v=${config_v}"></script>
<#include "/manage/foot.ftl">
