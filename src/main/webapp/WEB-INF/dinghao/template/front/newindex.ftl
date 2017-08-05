<#include "header.ftl">
<body>
	<div class="m-header f-clearfix">
		<h4 class="logo">
			<img src="${TEMPLATE_BASE_PATH}/images/common/logo.png">
		</h4>
		<ul class="link">
			<li class="user"><a href="${BASE_PATH}"><img
					src="${TEMPLATE_BASE_PATH}/images/common/pic.png"></a>
				<p>	${TEMPLATE_ADMIN.name}</p>
				<a href="${BASE_PATH}/logout.jhtml"><p style=" float:right;margin-left:20px;">退出</p></a>
			 </li>
		</ul>
		<ul class="nav" id="navId">
		<#list templateAdminMenus as templateAdminMenu>
			<li><a href="javascript:">${templateAdminMenu.grandparents}</a></li>
		</#list>
		</ul>
	</div>
	<div class="m-container f-clearfix">
		<div class="m-main">
			<div class="m-tabList">
				<div id="tabLeft" class="m-tab-left"></div>
				<div id="tabRight" class="m-tab-right"></div>
				<div class="m-tab-list">
					<ul id="tabList">
						<li id="0list" class="on"><a style="display: block;"
							href="javascript:">首页</a></li>
					</ul>
				</div>
			</div>
			<div class="m-tabCon" id="tabCon">
				<div id="0Con" class="m-tab-content">
					<div class="m-detail-con f-clearfix">
						<div class="m-index-lt f-fl">
							<div class="m-lt-box f-mb15">
								<div class="tit">
									<h4>我的任务</h4>
								</div>
								<ul class="m-fun">
									<li><img
										src="${TEMPLATE_BASE_PATH}/images/common/fly_index_ico03.png" />
										<p>
											<a href="javascript:AddSkd()">马上收款</a>
										</p>
										<p>快速发起一笔收款</p></li>
									<li><img
										src="${TEMPLATE_BASE_PATH}/images/common/fly_index_ico04.png" />
										<p>
											<a href="#">我要付款</a>
										</p>
										<p>快速发起一笔付款</p></li>
									<li><img
										src="${TEMPLATE_BASE_PATH}/images/common/fly_index_ico05.png" />
										<p>
											<a href="#">我要发货</a>
										</p>
										<p>快速发货</p></li>
								</ul>
							</div>
							<div class="m-lt-box">
								<div class="tit">
									<h4>操作引导</h4>
								</div>
								<div>操作引导内容</div>
							</div>
						</div>
						<div class="m-index-rt f-fr">
							<div class="m-index-box">
								<div class="tit">
									<a href="#">更多</a>
									<h4>未完成订单情况</h4>
								</div>
								<ul>
									<li><a href="#"><span>2014-11-15</span>YM20140328520</a></li>
									<li><a href="#"><span>2014-11-15</span>YM20140328520</a></li>
									<li><a href="#"><span>2014-11-15</span>YM20140328520</a></li>
								</ul>
							</div>
							<div class="m-index-box">
								<div class="tit">
									<a href="#">更多</a>
									<h4>系统公告</h4>
								</div>
								<ul>
									<li><a href="#">进销存电脑版V1.1.0升级</a></li>
									<li><a href="#">12月狂欢送大礼 快来占便宜啦！</a></li>
									<li><a href="#">12月狂欢送大礼 快来占便宜啦！</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
$(function(){
	//下面这块的数据，以后肯定是需要ajax过来的，现在暂时模拟	
	<#list templateAdminMenus as templateAdminMenu>
	var menuData_${templateAdminMenu_index} = 
	{
		title:'${templateAdminMenu.grandparents}',
		menuList:[
			<#list templateAdminMenu.parents as parents>
			{
			<#if (templateAdminMenu.parents?size==(parents_index+1))>
				text : '${parents.parents}',
				items : [				         
					<#list parents.children as menu>
						<#if (parents.children?size==(menu_index+1))>
							{id:'${menu.id}',text:'${menu.name}',href:'${BASE_PATH_TEMPLATE}/${menu.href}'}
						<#else>
							{id:'${menu.id}',text:'${menu.name}',href:'${BASE_PATH_TEMPLATE}/${menu.href}'},
						</#if>
					</#list>
				]}
			<#else>
				text : '${parents.parents}',
				items : [				         
					<#list parents.children as menu>
						<#if (parents.children?size==(menu_index+1))>
							{id:'${menu.id}',text:'${menu.name}',href:'${BASE_PATH_TEMPLATE}/${menu.href}'}
						<#else>
							{id:'${menu.id}',text:'${menu.name}',href:'${BASE_PATH_TEMPLATE}/${menu.href}'},
						</#if>
					</#list>
				]},
			</#if>
			</#list>
		]
	}
	</#list>
	
	
	//给一级菜单添加绑定事件，而不是在每个a上添加一个onClick事件
	$('#navId li').click(function(){
		//初始化
		$('.m-container .m-nav').remove();
		$('#mm').remove();
		var index = $(this).index();
		//index值为一个对应的菜单，0为第一个菜单，1为第二个菜单，以此类推	
		switch (index)
		{
		<#list templateAdminMenus as templateAdminMenu>
		case ${templateAdminMenu_index}:
			$.artTabs({
				menu : menuData_${templateAdminMenu_index}
			});
		  break;
		</#list>
		}
		$(this).addClass('on').siblings('li').removeClass('on');
	})
	
	$('#navId li').first().trigger('click');
	
})

function AddSkd() {
    var url = base_template + "/finance_receipt/add_financereceipt.jhtml";
    artTabs({
	addTab : {
	    items : {
		id : 'add_financereceipt',
		title : '新增收款单',
		url : url
	    }
	}
    });
}
</script>
</html>
