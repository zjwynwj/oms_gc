<#include "header.ftl">
<body>
<div class="m-header f-clearfix">
    <h4 class="logo"><img src="${TEMPLATE_BASE_PATH}/images/common/logo.png"></h4>
    <ul class="link">
        <li class="user"> <a href="http://www.worldchine.com"><img src="${TEMPLATE_BASE_PATH}/images/common/pic.png"></a>
            <p><a href="http://www.worldchine.com">admin@helloHT.com</a><br/>管理员</p>
        </li>
    </ul>
    <ul class="nav" id="navId">
    	<li><a href="javascript:">仓库管理</a></li>
        <li><a href="javascript:">订单管理</a></li>
        <li><a href="javascript:">财务管理</a></li>
        <li><a href="javascript:">采购管理</a></li>
        <li><a href="javascript:">基础信息</a></li>
        <li><a href="javascript:">例子</a></li>
    </ul>
</div>
<div class="m-container f-clearfix">
    <div class="m-main">
        <div class="m-tabList">
            <div id="tabLeft" class="m-tab-left"></div>
            <div id="tabRight" class="m-tab-right"></div>
            <div class="m-tab-list">
                <ul id="tabList">
                    <li id="0list" class="on"><a style="display:block;" href="javascript:">首页</a></li>
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
                                <li><img src="${TEMPLATE_BASE_PATH}/images/common/fly_index_ico03.png"/>
                                    <p><a href="javascript:addskd()">马上收款</a></p>
                                    <p>快速发起一笔收款</p>
                                </li>
                                <li><img src="${TEMPLATE_BASE_PATH}/images/common/fly_index_ico04.png"/>
                                    <p><a href="#">我要付款</a></p>
                                    <p>快速发起一笔付款</p>
                                </li>
                                <li><img src="${TEMPLATE_BASE_PATH}/images/common/fly_index_ico05.png"/>
                                    <p><a href="#">我要发货</a></p>
                                    <p>快速发货</p>
                                </li>
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
                            <div class="tit"><a href="#">更多</a><h4>未完成订单情况</h4></div>
                            <ul>
                            	<li><a href="#"><span>2014-11-15</span>YM20140328520</a></li>
                            	<li><a href="#"><span>2014-11-15</span>YM20140328520</a></li>
                            	<li><a href="#"><span>2014-11-15</span>YM20140328520</a></li>
                            </ul>
                        </div>
                        <div class="m-index-box">
                            <div class="tit"><a href="#">更多</a><h4>系统公告</h4></div>
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
	var menuData_jc = 
	{
		title:'基础信息',
		menuList:[{
			text : '单据',
			items : [
				{id:'numberMgr',text:'单据号管理',href:'${BASE_PATH}/template/baseNumber/turnNumberMgr.jhtml'}
			]},
			{
			text : '物流管理',
			items : [
				{id:'expressMgr',text:'物流公司管理',href:'${BASE_PATH}/template/expressMgr/turnExpressMgr.jhtml'}
			]},
			{
			text : '店铺',
			items : [
				{id:'shopMgr',text:'店铺管理',href:'${BASE_PATH}/template/shop/turnShopMgr.jhtml'}
			]}
		]
	}
	var menuData_cg = 
	{
		title:'采购管理',
		menuList:[{
			text : '采购订单',
			items : [
				{id:'suggestMgr',text:'采购建议',href:'${BASE_PATH}/template/suggest/turnSuggest.jhtml'},
				{id:'purMgr',text:'采购订单',href:'${BASE_PATH}/template/purOrder/turnPurMgr.jhtml'}
			]},
			{
			text : '基础资料',
			items : [
				{id:'custMgr',text:'供应商管理',href:'${BASE_PATH}/template/custInfo/turnCustInfoMgr.jhtml'}
			]}
		]
	}
	var menuData_cw = 
	{
		title:'财务管理',
		menuList:[{
			text : '资金管理',
			items : [
				{id:'suggestMgr3',text:'收款单',href:'${BASE_PATH}/template/finance_receipt'},
				{id:'suggestMgr4',text:'付款单',href:'${BASE_PATH}/template/finance_payment'},
			    {id:'suggestMgr5',text:'资金划拨',href:'${BASE_PATH}/template/finance_transfer'},
		         {id:'suggestMgr6',text:'账户调整(待开发)',href:'${BASE_PATH}/template/suggest/turnSuggest.jhtml'}
		
			]},
			
			{
			text : '财务报表',
			items : [
				{id:'custMgr3',text:'应付余额表(待开发)',href:'${BASE_PATH}/template/custInfo/turnCutsInfoMgr.jhtml'},
				{id:'custMgr4',text:'利润表(待开发)',href:'${BASE_PATH}/template/custInfo/turnCutsInfoMgr.jhtml'},
				{id:'custMgr5',text:'账户收支明细表(待开发)',href:'${BASE_PATH}/template/custInfo/turnCutsInfoMgr.jhtml'}
			]},
			{
			text : '账户管理',
			items : [
				{id:'financeAccountMgr',text:'我的账户',href:'${BASE_PATH}/template/finance/turnAccountMgr.jhtml'}
			]}
		]
	}
	var menuData_dd = 
	{
		title:'订单管理',
		menuList:[{
			text : '订单审核',
			items : [
				{id:'orderAudit',text:'订单审核',href:'${BASE_PATH}/template/orderMgr/turnOrderAudit.jhtml'},
				{id:'exceptionOrder',text:'异常订单',href:'${BASE_PATH}/template/orderMgr/turnExceptionOrdert.jhtml'}
			]},
			{
			text : '配货管理',
			items : [
				{id:'orderPrint',text:'订单打印',href:'${BASE_PATH}/template/orderMgr/turnOrderPrint.jhtml'},
				{id:'wmswaveMgr',text:'订单拣货',href:'${BASE_PATH}/template/wmswave/turnWmsWaveMgr.jhtml'},
				{id:'inspecctGoodsMgr',text:'验货出库',href:'${BASE_PATH}/template/orderMgr/turnInspectGoods.jhtml'},
				{id:'custMgr10',text:'打包称重',href:'${BASE_PATH}/template/orderMgr/turnBatchWeight.jhtml'}
			]},
			
			{
			text : '物流管理',
			items : [
				{id:'expressMgr',text:'物流公司管理',href:'${BASE_PATH}/template/expressMgr/turnExpressMgr.jhtml'},
				{id:'printTemplateMgr',text:'打印模板管理',href:'${BASE_PATH}/template/printMgr/turnPrintTemplateMgr.jhtml'},
				{id:'senderInfoMgr',text:'发货人信息',href:'${BASE_PATH}/template/senderInfoMgr/turnSenderInfo.jhtml'}
			]},
			{
			text : '运营管理',
			items : [
				{id:'custMgr',text:'退货管理',href:'${BASE_PATH}/template/salesrtnorder/index.jhtml'},
				{id:'custMgr12',text:'促销规则(待开发)',href:'${BASE_PATH}/template/custInfo/turnCutsInfoMgr.jhtml'},
				{id:'custMgr13',text:'会员管理',href:'${BASE_PATH}/template/memberMgr/turnMemberMgr.jhtml'}
		    ]}
		]
	}
	var menuData_ck = 
	{
		title:'库存管理',
		menuList:[
		    {text : '商品',
			items : [
				{id:'gdsCLsMgr',text:'商品分类',href:'${BASE_PATH}/template/gdscls/turnModcls.jhtml'},
			    {id:'gdsMgr',text:'商品管理',href:'${BASE_PATH}/template/gdsMgr/turnGdsMgr.jhtml'},
		     	{id:'gdsAttr',text:'商品属性',href:'${BASE_PATH}/template/gdsAttb/gdsAttr.jhtml'}

			]},
			{text : '库存管理',
			items : [
				{id:'zTree',text:'入库单',href:'${BASE_PATH}/template/receipt?receiptType=1'},
				{id:'jqGrid',text:'出库单',href:'${BASE_PATH}/template/receipt?receiptType=2'},
				{id:'test3',text:'盘点单',href:'${BASE_PATH}/template/wmstake'},
				{id:'test4',text:'调整单',href:'${BASE_PATH}/template/demoPage/citySelect.html'},
				{id:'test5',text:'调拨单',href:'${BASE_PATH}/template/demoPage/citySelect.html'}
			]},
			{
			text : '仓库管理',
			items : [
				{id:'demo17',text:'仓库设置',href:'${BASE_PATH}/template/warehouse/index.jhtml'},
				{id:'demo20',text:'现货报表',href:'${BASE_PATH}/template/locstock'}			
			]}			
		]
	}
	 var menuData_demo = 
	{
		title:'例子',
		menuList:[
			{
			text : '样式',
			items : [
				{id:'lodop',text:'打印',href:'${TEMPLATE_BASE_PATH}/demoPage/lodop.html'},
				{id:'demo1',text:'基本结构页',href:'${TEMPLATE_BASE_PATH}/demoPage/demo_base.html'},
				{id:'demo2',text:'表单及验证',href:'${TEMPLATE_BASE_PATH}/demoPage/form_verify.html'},
				{id:'demo3',text:'盘点管理',href:'${TEMPLATE_BASE_PATH}/demoPage/pandian.html'},
				{id:'demo4',text:'新增入库单',href:'${TEMPLATE_BASE_PATH}/demoPage/rukuOrder.html'},
				{id:'tabsMethod',text:'artTabs框架',href:'${TEMPLATE_BASE_PATH}/demoPage/artTabs.html'},
				{id:'basic_styles',text:'基本样式调用',href:'${TEMPLATE_BASE_PATH}/demoPage/basic_styles.html'},
				{id:'dialog',text:'弹出提示信息',href:'${TEMPLATE_BASE_PATH}/demoPage/dialog.html'},
				{id:'tabpic',text:'tab切换及上传图片',href:'${TEMPLATE_BASE_PATH}/demoPage/tab_pic.html'},
				{id:'test233',text:'测试页面二',href:'${TEMPLATE_BASE_PATH}/demoPage/test2.html'}
			]},
			{
			text : '元素',
			items : [
				{id:'zTree24',text:'zTree树形结构',href:'${TEMPLATE_BASE_PATH}/demoPage/zTree.html'},
				{id:'jqGrid23',text:'jqGrid表格控件',href:'${TEMPLATE_BASE_PATH}/demoPage/jqGrid.html'},
				{id:'test323',text:'测试页面三',href:'${TEMPLATE_BASE_PATH}/demoPage/test3.html'},
				{id:'test4232',text:'省市区选择',href:'${TEMPLATE_BASE_PATH}/demoPage/citySelect.html'},
				{id:'selectLayer',text:'选择及弹出层控件',href:'${TEMPLATE_BASE_PATH}/demoPage/selectLayer.html'}
			]}			
		]
	}
	
	//给一级菜单添加绑定事件，而不是在每个a上添加一个onClick事件
	$('#navId li').click(function(){
		//初始化
		$('.m-container .m-nav').remove();
		$('#mm').remove();
		var index = $(this).index();
		//index值为一个对应的菜单，0为第一个菜单，1为第二个菜单，以此类推	
		switch (index)
		{
		case 0:
			$.artTabs({
				menu : menuData_ck
			})
		  break;
		case 1:
		  $.artTabs({
				menu : menuData_dd
			})
		  break;
		case 2:
		 $.artTabs({
				menu : menuData_cw
			})
		  break;
		case 3:
		  $.artTabs({
				menu : menuData_cg
		  })
		  break;
		case 4:
		  $.artTabs({
				menu : menuData_jc
			})
		  break;
		case 5:
			$.artTabs({
				menu : menuData_demo
			});
		  break;
		}
		$(this).addClass('on').siblings('li').removeClass('on');
	})
	
	$('#navId li').first().trigger('click');
	
})
</script>
</html>
