<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<#include "../template/front/header.ftl">

<html>
<head>
    <title>任务列表</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        table {
            width: 90%;
            margin: 0 auto;
            text-align: center;
            border-right: 1px solid #999;
            border-bottom: 1px solid #999;
        }

        table tr {
        }

        table td, table th {
            border-left: 1px solid #999;
            border-top: 1px solid #999;
        }

        .btn {
            border: 0;
            border-radius: 4px;
            background-color: #428bca;
            color: #fff;
            padding: 3px 5px;
            cursor: pointer;
        }
    </style>
</head>

<body style="background: beige;">
<div style="text-align: center;">
    <span><a href="/user/logout">退出</a></span>
</div>
<h2 style="text-align: center;">任务列表</h2>
<table id="table_report" class="table table-striped table-bordered table-hover" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <!-- th class="center">序号</th-->
        <th class="center">任务名</th>
        <th class="center">时间表达式</th>
         <th class="center">下次运行时间</th>
        <th class="center">任务状态</th>
        <th class="center">开始时间</th>
        <th class="center" width="15%">操作</th>
    </tr>
    </thead>
    <tbody>
    <#if (jobInfos?size>0)> <#list jobInfos as job>

    <tr>
        <td class='center' style="width: auto;">${job.jobText}</td>
        <td class='center' style="width: auto;">${job.cronExpr}</td>
         <td class='center' style="width: auto;"> ${job.nextFireTime?string("yyyy-MM-dd HH:mm:ss zzzz")}</td>
        <td class='center' style="width: auto;">${job.jobStatus}</td>
        <td class='center' style="width: auto;">${job.startTime?string("yyyy-MM-dd HH:mm:ss zzzz")}</td>
        <td class='center' style="width: auto;">
            <a class="btn btn-minier btn-success" onclick="edit('${job.jobName}','${job.jobGroup}');"><i
                    class="icon-edit"></i>编辑</a><br>
            <a class="btn btn-minier btn-warning" onclick="pauseJob('${job.jobName}','${job.jobGroup}');"><i
                    class="icon-edit"></i>暂停</a>
            <a class="btn btn-minier btn-purple" onclick="resumeJob('${job.jobName}','${job.jobGroup}');"><i
                    class="icon-edit"></i>恢复</a>
            <a class="btn btn-minier btn-danger"
               onclick="deleteJob('${job.jobName}','${job.jobGroup}','${job.triggerName}','${job.triggerGroupName}');"><i
                    class="icon-edit"></i>删除</a>
        </td>
    </tr>

    </#list> </#if>

    </tbody>
</table>

<div style="width: 90%;margin: 0 auto;text-align: center;margin-top: 25px;">
    <button type="button" onclick="add();" class="btn">新增任务</button>
</div>
<script type="text/javascript">
    var url = "${BASE_PATH}";

    function add() {
        window.location.href = url + "/quartz/toAdd";
    }

    function edit(jobName, jobGroup) {
        window.location.href = url + "/quartz/toEdit?jobName=" + jobName + "&jobGroup=" + jobGroup;
    }

    //暂停任务
    function pauseJob(jobName, jobGroupName) {
        $.post(url + "/quartz/pauseJob", {"jobName": jobName, "jobGroupName": jobGroupName}, function (data) {
            if (data.status = 'success') {
                window.location.href = window.location.href;
            } else {
                alert("操作失败，请刷新重新！");
            }
        });
    }

    //恢复任务
    function resumeJob(jobName, jobGroupName) {
        $.post(url + "/quartz/resumeJob", {"jobName": jobName, "jobGroupName": jobGroupName}, function (data) {
            if (data.status = 'success') {
                window.location.href = window.location.href;
            } else {
                alert("操作失败，请刷新重新！");
            }
        });
    }

    //删除
    function deleteJob(jobName, jobGroupName, triggerName, triggerGroupName) {
        $.post(url + "/quartz/deleteJob", {
                    "jobName": jobName,
                    "jobGroupName": jobGroupName,
                    "triggerName": triggerName,
                    "triggerGroupName": triggerGroupName
                },
                function (data) {
                    if (data.status = 'success') {
                        window.location.href = window.location.href;
                    } else {
                        alert("操作失败，请刷新重新！");
                    }
                });
    }
</script>
</body>
</html>
