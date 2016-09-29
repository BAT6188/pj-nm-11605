<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>Bootstrap 面板（Panels）</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link href="<%=request.getContextPath()%>/scripts/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/scripts/font-awesome-4.6.3/css/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/scripts/bootstrap-datetimepicker2.3.11/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/pageStyle.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/scripts/jquery1.12.4/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/bootstrap-3.3.7/js/bootstrap.js"></script>
</head>
<body>

<br>
<br>
<div class="alert alert-info">Bootstrap 面板（Panels）</div>

<p></p>
<div class="panel panel-default">
    <div class="panel-body">
        这是一个基本的面板
    </div>
</div>


<br>
<br>
<div class="alert alert-info">面板标题</div>

<p></p>
<div class="panel panel-default">
    <div class="panel-heading">
        不带 title 的面板标题
    </div>
    <div class="panel-body">
        面板内容
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">
            带有 title 的面板标题
        </h3>
    </div>
    <div class="panel-body">
        面板内容
    </div>
</div>
<br>
<br>
<div class="alert alert-info">面板脚注</div>

<p></p>
<div class="panel panel-default">
    <div class="panel-body">
        这是一个基本的面板
    </div>
    <div class="panel-footer">面板脚注</div>
</div>
<br>
<br>
<div class="alert alert-info">带语境色彩的面板</div>

<p></p>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">面板标题</h3>
    </div>
    <div class="panel-body">
        这是一个基本的面板
    </div>
</div>
<div class="panel panel-success">
    <div class="panel-heading">
        <h3 class="panel-title">面板标题</h3>
    </div>
    <div class="panel-body">
        这是一个基本的面板
    </div>
</div>
<div class="panel panel-info">
    <div class="panel-heading">
        <h3 class="panel-title">面板标题</h3>
    </div>
    <div class="panel-body">
        这是一个基本的面板
    </div>
</div>
<div class="panel panel-warning">
    <div class="panel-heading">
        <h3 class="panel-title">面板标题</h3>
    </div>
    <div class="panel-body">
        这是一个基本的面板
    </div>
</div>
<div class="panel panel-danger">
    <div class="panel-heading">
        <h3 class="panel-title">面板标题</h3>
    </div>
    <div class="panel-body">
        这是一个基本的面板
    </div>
</div>


<br>
<br>
<div class="alert alert-info">带表格的面板</div>

<p></p>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">面板标题</h3>
    </div>
    <div class="panel-body">
        这是一个基本的面板
    </div>
    <table class="table">
        <th>产品</th><th>价格 </th>
        <tr><td>产品 A</td><td>200</td></tr>
        <tr><td>产品 B</td><td>400</td></tr>
    </table>
</div>
<div class="panel panel-default">
    <div class="panel-heading">面板标题</div>
    <table class="table">
        <th>产品</th><th>价格 </th>
        <tr><td>产品 A</td><td>200</td></tr>
        <tr><td>产品 B</td><td>400</td></tr>
    </table>
</div>

<br>
<br>
<div class="alert alert-info">带列表组的面板</div>

<p></p>
<div class="panel panel-default">
    <div class="panel-heading">面板标题</div>
    <div class="panel-body">
        <p>这是一个基本的面板内容。这是一个基本的面板内容。
            这是一个基本的面板内容。这是一个基本的面板内容。
            这是一个基本的面板内容。这是一个基本的面板内容。
            这是一个基本的面板内容。这是一个基本的面板内容。
        </p>
    </div>
    <ul class="list-group">
        <li class="list-group-item">免费域名注册</li>
        <li class="list-group-item">免费 Window 空间托管</li>
        <li class="list-group-item">图像的数量</li>
        <li class="list-group-item">24*7 支持</li>
        <li class="list-group-item">每年更新成本</li>
    </ul>
</div>
</body>
</html>
