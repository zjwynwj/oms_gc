<!doctype html>
<!-- 统一取消变量控制判断 -->
<#escape x as x!""></#escape> 
<#setting classic_compatible=true>

<html>
	<head>
		<meta  http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>OMS平台</title>
		<link rel="stylesheet" type="text/css" href="${TEMPLATE_BASE_PATH}/css/common.css?v=${config_front}">
		<link rel="stylesheet" type="text/css" href="${TEMPLATE_BASE_PATH}/css/global.css?v=${config_front}">
		<link rel="stylesheet" type="text/css" href="${TEMPLATE_BASE_PATH}/css/tabcut.css?v=${config_front}">
		<script src="${TEMPLATE_BASE_PATH}/js/jquery-1.8.3.min.js?v=${config_front}"></script>
		<!-- 智能提示 -->
		<link rel="stylesheet" type="text/css"	href="${TEMPLATE_BASE_PATH}/js/elem/select2/select2.css?v=${config_front}">
		<script type="text/javascript"	src="${TEMPLATE_BASE_PATH}/js/elem/select2/select2.js?v=${config_front}"></script>
		<script type="text/javascript"	src="${TEMPLATE_BASE_PATH}/js/elem/select2/select2_locale_zh-CN.js?v=${config_front}"></script>
		<!--jqUi-->
		<link rel="stylesheet" type="text/css" href="${TEMPLATE_BASE_PATH}/js/elem/jqueryui/css/jquery-ui-1.10.4.custom.css?v=${config_front}">
		<link rel="stylesheet" type="text/css" href="${TEMPLATE_BASE_PATH}/js/elem/jqGrid/css/ui.jqgrid.css?v=${config_front}">
		
		<script src="${TEMPLATE_BASE_PATH}/js/jquery-form.min.js?v=${config_front}"></script>
		<script src="${TEMPLATE_BASE_PATH}/js/artTabs.js?v=${config_front}"></script>
		<script src="${TEMPLATE_BASE_PATH}/js/dialog.js?v=${config_front}"></script>
		<script	src="${TEMPLATE_BASE_PATH}/js/oms_common.js?v=${config_front}"></script>
		<script	src="${TEMPLATE_BASE_PATH}/js/oms_ajax.js?v=${config_front}"></script>
		<script src="${TEMPLATE_BASE_PATH}/js/elem/jqueryui/js/jquery-ui-1.10.3.custom.js?v=${config_front}"></script>
		<script src="${TEMPLATE_BASE_PATH}/js/elem/jqueryui/js/jquery.ui.datepicker-zh-CN.min.js?v=${config_front}"></script>
		<script src="${TEMPLATE_BASE_PATH}/js/elem/jqGrid/src/i18n/grid.locale-cn.js?v=${config_front}"></script>
		<script src="${TEMPLATE_BASE_PATH}/js/elem/jqGrid/js/jquery.jqGrid.src.js?v=${config_front}"></script>
		<script src="${TEMPLATE_BASE_PATH}/js/elem/select2-master/dist/js/select2.min.js?v=${config_front}"></script>
		<script src="${TEMPLATE_BASE_PATH}/js/tabcut.js?v=${config_front}"></script>
		<script src="${TEMPLATE_BASE_PATH}/js/verify.js?v=${config_front}"></script>
		
		<script type="text/javascript">
			//全局变量 JS引入
			var base="${BASE_PATH}";
			var base_template="${BASE_PATH_TEMPLATE}";
			var template_base="${TEMPLATE_BASE_PATH}";
		</script>
	</head>