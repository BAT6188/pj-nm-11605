<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>面板布局</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link href="<%=request.getContextPath()%>/common/scripts/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/common/scripts/font-awesome-4.6.3/css/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/common/scripts/bootstrap-table-1.11.0/bootstrap-table.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/pageStyle.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/common/scripts/jquery1.12.4/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/bootstrap-3.3.7/js/bootstrap.js"></script>

    <script src="<%=request.getContextPath()%>/common/scripts/bootstrap-table-1.11.0/bootstrap-table.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.js"></script>

</head>
<body>
<div class="container">
    <div class="alert alert-info"> table中一定要设置 data-toggle="table"</div>

    <table data-toggle="table">
    <thead>
    <tr>
        <th>Name</th>
        <th>Stars</th>
        <th>Forks</th>
        <th>Description</th>
    </tr>
    </thead>
    <tbody>
    <tr id="tr-id-1" class="tr-class-1">
        <td id="td-id-1" class="td-class-1">
            <a href="https://github.com/wenzhixin/bootstrap-table">bootstrap-table</a>
        </td>
        <td>526</td>
        <td>122</td>
        <td>An extended Bootstrap table with radio, checkbox, sort, pagination, and other added features. (supports twitter bootstrap v2 and v3)
        </td>
    </tr>
    <tr id="tr-id-2" class="tr-class-2">
        <td id="td-id-2" class="td-class-2">
            <a href="https://github.com/wenzhixin/multiple-select">multiple-select</a>
        </td>
        <td>288</td>
        <td>150</td>
        <td>A jQuery plugin to select multiple elements with checkboxes :)
        </td>
    </tr>
    <tr id="tr-id-3" class="tr-class-3">
        <td id="td-id-3" class="td-class-3">
            <a href="https://github.com/wenzhixin/bootstrap-show-password">bootstrap-show-password</a>
        </td>
        <td>32</td>
        <td>11</td>
        <td>Show/hide password plugin for twitter bootstrap.
        </td>
    </tr>
    <tr id="tr-id-4" class="tr-class-4">
        <td id="td-id-4" class="td-class-4">
            <a href="https://github.com/wenzhixin/blog">blog</a>
        </td>
        <td>13</td>
        <td>4</td>
        <td>my blog</td>
    </tr>
    <tr id="tr-id-5" class="tr-class-5">
        <td id="td-id-5" class="td-class-5">
            <a href="https://github.com/wenzhixin/scutech-redmine">scutech-redmine</a>
        <td>6</td>
        <td>3</td>
        <td>Redmine notification tools for chrome extension.</td>
    </tr>
    </tbody>
</table>

</div>

</body>
</html>
