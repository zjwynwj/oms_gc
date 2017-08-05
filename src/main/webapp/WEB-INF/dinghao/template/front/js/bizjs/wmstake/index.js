$(function() {
    $("#datepicker").datepicker({
        showOn: "button",
        buttonImage: base + "/dinghao/template/front/js/elem/jqueryui/css/images/calendar.gif",
        buttonImageOnly: true
    });
    $("#datepicker2").datepicker({
        showOn: "button",
        buttonImage: base + "/dinghao/template/front/js/elem/jqueryui/css/images/calendar.gif",
        buttonImageOnly: true
    });

    // 单据设置列表表格
    jQuery("#blackId").jqGrid({
        datatype: "json",
        url: base_template + '/wmstake/get_wmstake.jhtml',
        mtype: 'POST',
        forceFit: true,
        colNames: ['序号', '操作', '盘点单号', '盘点名称', '开始盘点', '结束盘点', '仓库', '盘点日期', '盘点人', '备注'],
        colModel: [{
            name: 'id',
            index: 'id',
            hidden: true
        },
        {
            name: 'act',
            index: 'act',
            formatter: act
        },
        {
            name: 'takeNo',
            index: 'takeNo'
        },
        {
            name: 'takeName',
            index: 'takeName'
        },
        {
            name: 'starttake',
            index: 'starttake'
        },
        {
            name: 'endtakeNo',
            index: 'endtakeNo'
        },
        {
            name: 'warehouseName',
            index: 'warehouseName'
        },
        {
            name: 'startTime',
            index: 'startTime',
            formatter: 'date',
            formatoptions: {
                srcformat: 'u',
                newformat: 'Y-m-d'
            }
        },
        {
            name: 'remarks',
            index: 'remarks'
        },
        {
            name: 'memo',
            index: 'memo'
        }],
        rowNum: 10,
        rowList: [10, 20, 30],
        pager: '#pager',
        toolbarfilter: true,
        height: '100%',
        viewrecords: true,
        autowidth: true,
        rownumbers: true,
        prmNames: { // 默认发送参数格式设置
            page: "pageNum",
            rows: "rows"
        },
        prmNames: {
            page: "pageNum",
            // 表示请求页码的参数名称
            rows: "rows",
            // 表示请求行数的参数名称
            sort: "page.oid",
            // 表示用于排序的列名的参数名称
            order: "page.oor",
            // 表示采用的排序方式的参数名称
            search: "issearch",
            // 表示是否是搜索请求的参数名称
            nd: "nd",
            // 表示已经发送请求的次数的参数名称
            state: "isstate"
        },
        jsonReader: { // 返回数据格式设置
            root: "rows",
            records: "records",
            total: "total",
            repeatitems: false
        },
        ajaxGridOptions: {
            timeout: oms.ajaxTimeout
        },
        // 统一超时时间
        successTip: false,
        beforeProcessing: oms.grid.ajaxSuccessFn,
        loadError: oms.grid.ajaxErrorFn,
        multiselect: true
    });

    // 搜索
    $('#search').click(function() {
        $("#blackId").jqGrid('setGridParam', {
            postData: {
                takeNo: $("input[name='takeNo']").val(),
                beginDate: $("input[name='beginDate']").val(),
                endDate: $("input[name='endDate']").val(),
                stockId: $("#stockId").val()
            }
        });
        $("#blackId").jqGrid().trigger("reloadGrid");
    });

}); // jQuery 结束
// --------------------------生成操作列内容脚本--------------------------------
// 入库单编辑
function act(cellvalue, options, rowObject) {
    addBtn = "<div class='operating'><a href='javascript:void(0);'onclick=\"detailButtom('" + rowObject.id + "')\" class='operating-comment' title='详细'></a>";
    if( rowObject.startTime == null )
    {
    	  addBtn += "<a href='javascript:void(0);' onclick='deleteButtom(\"id=" + rowObject.id + "\")' class='operating-trash' title='删除'></a>";
   	}
  

    if( rowObject.endTime == null )
    {
        addBtn += "<a href='javascript:void(0);' onclick='wmsTakeByTab(" + rowObject.id + ")' class='operating-pand' title='盘点'></a>";
    }
    addBtn += "&nbsp</div>"
    return addBtn;
}
/**
 * ****************************************************************************
 * 函数名称: <ATFunc>index.js</ATFunc> 函数功能: <ATFuncDesc>删除盘点单(表格中的删除)</ATFuncDesc>
 * 开发人员: Zihan 2016年1月21日 修改记录:
 * ****************************************************************************
 */
function deleteButtom(id) {
    $.artDialog({
        title: '消息',
        content: '你确认要删除吗?',
        cancel: function() {
            return false;
        },
        ok: function() {
            $.post(base_template + "/wmstake/delete_wmstakes.jhtml?" + id,
            function(data) {
                if (data.success) {
                    $.artDialog({
                        title: '消息',
                        content: '删除成功!'
                    });
                    $("#blackId").jqGrid().trigger("reloadGrid");
                } else {
                    $.artDialog({
                        title: '消息',
                        content: data.msg
                    });
                }
            },
            "json");
        }
    });
}
/**
 * ****************************************************************************
 * 函数名称: <ATFunc>index.js</ATFunc> 函数功能: <ATFuncDesc>删除按钮时间</ATFuncDesc> 开发人员:
 * Zihan 2016年1月21日 修改记录:
 * ****************************************************************************
 */
function deleteByTab() {
    var deleteIds = [];
    var rowData = jQuery('#blackId').jqGrid('getGridParam', 'selarrrow');
    if (rowData.length) {
        for (var i = 0; i < rowData.length; i++) {
            deleteIds.push("id=" + rowData[i]);
        }
    }
    if (deleteIds.length == 0) {
        $.artDialog({
            title: '消息',
            content: '请选择要删除的数据!'
        });
    } else {
        deleteButtom(deleteIds.join("&"));
    }
}
/**
 * ****************************************************************************
 * 函数名称: <ATFunc>index.js</ATFunc> 函数功能: <ATFuncDesc>盘点按钮事件</ATFuncDesc> 开发人员:
 * Zihan 2016年1月22日 修改记录:
 * ****************************************************************************
 */
function wmsTakeByTab(takeId) {
	
	 var url = base_template + "/wmstake/add_wmstake_button.jhtml?id=" + takeId;
     artTabs({
         addTab: {
             items: {
                 id: 'wmsTakeByTab',
                 title: '盘点',
                 url: url
             }
         }
     });
     return;
    var deleteIds = [];
    var rowData = jQuery('#blackId').jqGrid('getGridParam', 'selarrrow');
    if (rowData.length) {
        for (var i = 0; i < rowData.length; i++) {
            deleteIds.push("id=" + rowData[i]);
        }
    }
    if (deleteIds.length == 0) {
        $.artDialog({
            title: '消息',
            content: '请选择要盘点的数据!'
        });
    } else if (deleteIds.length > 1) {
        $.artDialog({
            title: '消息',
            content: '请选择一条数据!'
        });
    } else {
    	
        var url = base + "/template/wmstake/add_wmstake_button.jhtml?id=" + rowData[0];
        artTabs({
            addTab: {
                items: {
                    id: 'wmsTakeByTab',
                    title: '盘点',
                    url: url
                }
            }
        });
    }
}

function detailButtom(id) {
    var url = base_template + "/wmstake/detail_wmstake.jhtml?id=" + id;
    artTabs({
        addTab: {
            items: {
                id: 'detailwmstake',
                title: '盘点单明细',
                url: url
            }
        }
    });
}
function addByTab() {
    var url = base_template + "/wmstake/add_wmstake.jhtml";
    artTabs({
        addTab: {
            items: {
                id: 'addwmstake',
                title: '新增盘点单',
                url: url
            }
        }
    });
}