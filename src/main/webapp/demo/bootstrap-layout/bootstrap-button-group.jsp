<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>字体图标</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link href="<%=request.getContextPath()%>/common/scripts/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/common/scripts/font-awesome-4.6.3/css/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/common/scripts/bootstrap-datetimepicker2.3.11/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/pageStyle.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/common/scripts/jquery1.12.4/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/bootstrap-3.3.7/js/bootstrap.js"></script>
</head>
<body>

<br>
<br>
<div class="alert alert-info">该 class 用于形成基本的按钮组。在 .btn-group 中放置一系列带有 class .btn 的按钮。</div>

<p></p>
<div class="btn-group">
    <button type="button" class="btn btn-default">Button1</button>
    <button type="button" class="btn btn-default">Button2</button>
</div>
<p></p>
<div class="alert alert-info"> .btn-toolbar 将btn-group组合到一起</div>
<p></p>
<div class="btn-toolbar" role="toolbar">
    <div class="btn-group">
        <button type="button" class="btn btn-default">Button1</button>
        <button type="button" class="btn btn-default">Button2</button>
    </div>
    <div class="btn-group">
        <button type="button" class="btn btn-default">Button1</button>
        <button type="button" class="btn btn-default">Button2</button>
    </div>
</div>

<p></p>
<div class="alert alert-info"> .btn-group-lg, .btn-group-sm, .btn-group-xs 这些 class 可应用到整个按钮组的大小调整，而不需要对每个按钮进行大小调整。</div>
<p></p>
<div class="btn-group btn-group-lg">
    <button type="button" class="btn btn-default">Button1</button>
    <button type="button" class="btn btn-default">Button2</button>
</div>
<div class="btn-group btn-group-sm">
    <button type="button" class="btn btn-default">Button1</button>
    <button type="button" class="btn btn-default">Button2</button>
</div>
<div class="btn-group btn-group-xs">
    <button type="button" class="btn btn-default">Button1</button>
    <button type="button" class="btn btn-default">Button2</button>
</div>

<p></p>
<div class="alert alert-info"> .btn-group-vertical该 class 让一组按钮垂直堆叠显示，而不是水平堆叠显示。</div>
<p></p>
<div class="btn-group-vertical">
    <button type="button" class="btn btn-default">Button1</button>
    <button type="button" class="btn btn-default">Button2</button>
    <button type="button" class="btn btn-default">Button1</button>
    <button type="button" class="btn btn-default">Button2</button>
</div>



<p></p>
<div class="alert alert-info">垂直的按钮组</div>
<p></p>
<div class="btn-group-vertical">
    <button type="button" class="btn btn-default">按钮 1</button>
    <button type="button" class="btn btn-default">按钮 2</button>
    <div class="btn-group-vertical">
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
            下拉
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <li><a href="#">下拉链接 1</a></li>
            <li><a href="#">下拉链接 2</a></li>
        </ul>
    </div>
</div>



</body>
</html>
