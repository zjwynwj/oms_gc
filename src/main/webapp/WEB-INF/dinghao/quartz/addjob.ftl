<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<#include "../template/front/header.ftl">
<html>
<head>

    <title>新增任务</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body style="background: beige; margin: 300px 600px 200px;">
<form action="${BASE_PATH}/quartz/add" method="post">
    <span>新增Trigger</span>
    <hr/>
    <table id="table_report" class="table table-striped table-bordered table-hover">


        <tr>
            <td>cron</td>
            <td><input type="text" name="cron" value=""/></td>
        </tr>
        <tr>
            <td>jobName</td>
            <td>
                <select type="text" name="jobName" value="">
                <#if (jobs?size>0)>
                    <#list jobs as job>
                        <option value="${job.jobName}">${job.text}</option>
                    </#list>
                </#if>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button type="submit" style="border: 0;background-color: #428bca;">提交</button>
                <button class="cancel" style="border: 0;background-color: #fff;">返回</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script>
    $(document).ready(function () {
        $(".cancel").click(function () {
            history.go(-1);
        });
    });
</script>

