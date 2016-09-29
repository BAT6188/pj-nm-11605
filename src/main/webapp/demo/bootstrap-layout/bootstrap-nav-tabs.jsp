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
<div class="alert alert-info">表格导航或标签</div>

<p></p>
<p>标签式的导航菜单</p>
<ul class="nav nav-tabs">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="#">SVN</a></li>
    <li><a href="#">iOS</a></li>
    <li><a href="#">VB.Net</a></li>
    <li><a href="#">Java</a></li>
    <li><a href="#">PHP</a></li>
</ul>


<p></p>
<div class="alert alert-info"> 胶囊式的导航菜单</div>
<p></p>

<ul class="nav nav-pills">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="#">SVN</a></li>
    <li><a href="#">iOS</a></li>
    <li><a href="#">VB.Net</a></li>
    <li><a href="#">Java</a></li>
    <li><a href="#">PHP</a></li>
</ul>

<p></p>
<div class="alert alert-info">垂直的胶囊式导航菜单</div>
<p></p>
<ul class="nav nav-pills nav-stacked">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="#">SVN</a></li>
    <li><a href="#">iOS</a></li>
    <li><a href="#">VB.Net</a></li>
    <li><a href="#">Java</a></li>
    <li><a href="#">PHP</a></li>
</ul>

<p></p>
<div class="alert alert-info"> 两端对齐的导航</div>
<p></p>
<ul class="nav nav-pills nav-justified">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="#">SVN</a></li>
    <li><a href="#">iOS</a></li>
    <li><a href="#">VB.Net</a></li>
    <li><a href="#">Java</a></li>
    <li><a href="#">PHP</a></li>
</ul><br><br><br>
<ul class="nav nav-tabs nav-justified">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="#">SVN</a></li>
    <li><a href="#">iOS</a></li>
    <li><a href="#">VB.Net</a></li>
    <li><a href="#">Java</a></li>
    <li><a href="#">PHP</a></li>
</ul>

<p></p>
<div class="alert alert-info">禁用链接</div>
<p></p>
<ul class="nav nav-pills">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="#">SVN</a></li>
    <li class="disabled"><a href="#">iOS（禁用链接）</a></li>
    <li><a href="#">VB.Net</a></li>
    <li><a href="#">Java</a></li>
    <li><a href="#">PHP</a></li>
</ul><br><br>
<ul class="nav nav-tabs">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="#">SVN</a></li>
    <li><a href="#">iOS</a></li>
    <li  class="disabled"><a href="#">VB.Net（禁用链接）</a></li>
    <li><a href="#">Java</a></li>
    <li><a href="#">PHP</a></li>
</ul>

<p></p>
<div class="alert alert-info">下拉菜单</div>
<p></p>
<ul class="nav nav-tabs">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="#">SVN</a></li>
    <li><a href="#">iOS</a></li>
    <li><a href="#">VB.Net</a></li>
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            Java <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
            <li><a href="#">Swing</a></li>
            <li><a href="#">jMeter</a></li>
            <li><a href="#">EJB</a></li>
            <li class="divider"></li>
            <li><a href="#">分离的链接</a></li>
        </ul>
    </li>
    <li><a href="#">PHP</a></li>
</ul>
<p></p>
<div class="alert alert-info">带有下拉菜单的胶囊</div>
<p></p>
<ul class="nav nav-pills">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="#">SVN</a></li>
    <li><a href="#">iOS</a></li>
    <li><a href="#">VB.Net</a></li>
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            Java <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
            <li><a href="#">Swing</a></li>
            <li><a href="#">jMeter</a></li>
            <li><a href="#">EJB</a></li>
            <li class="divider"></li>
            <li><a href="#">分离的链接</a></li>
        </ul>
    </li>
    <li><a href="#">PHP</a></li>
</ul>

<p></p>
<div class="alert alert-info">
    <h2>动态标签</h2>
    <p><strong>提示:</strong> 修改 data-toggle="tab" 为 data-toggle="pill" 来设置胶囊式标签页。</p>
</div>
<p></p>

<div class="container">


    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#home">首页</a></li>
        <li><a data-toggle="tab" href="#menu1">菜单 1</a></li>
        <li><a data-toggle="tab" href="#menu2">菜单 2</a></li>
        <li><a data-toggle="tab" href="#menu3">菜单 3</a></li>
    </ul>

    <div class="tab-content">
        <div id="home" class="tab-pane fade in active">
            <h3>首页</h3>
            <p>菜鸟教程 —— 学的不仅是技术，更是梦想！！！</p>
        </div>
        <div id="menu1" class="tab-pane fade">
            <h3>菜单 1</h3>
            <p>这是菜单 1 显示的内容。这是菜单 1 显示的内容。这是菜单 1 显示的内容。</p>
        </div>
        <div id="menu2" class="tab-pane fade">
            <h3>菜单 2</h3>
            <p>这是菜单 2 显示的内容。这是菜单 2 显示的内容。这是菜单 2 显示的内容。</p>
        </div>
        <div id="menu3" class="tab-pane fade">
            <h3>菜单 3</h3>
            <p>这是菜单 3 显示的内容。这是菜单 3 显示的内容。这是菜单 3 显示的内容。</p>
        </div>
    </div>
</div>
</body>
</html>
