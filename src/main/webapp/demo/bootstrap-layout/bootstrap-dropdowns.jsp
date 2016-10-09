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
<div class="alert alert-info"> Bootstrap 下拉菜单（Dropdowns）http://www.runoob.com/bootstrap/bootstrap-dropdowns.html</div>

<p></p>
<div class="dropdown">
    <button type="button" class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">主题
        <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">Java</a>
        </li>
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">数据挖掘</a>
        </li>
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">数据通信/网络</a>
        </li>
        <li role="presentation" class="divider"></li>
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">分离的链接</a>
        </li>
    </ul>
</div>

<p></p>
<div class="alert alert-info"> 对齐</div>
<p></p>
<div class="dropdown">
    <button type="button" class="btn dropdown-toggle" id="dropdownMenu2" data-toggle="dropdown">主题
        <span class="caret"></span>
    </button>
    <ul class="dropdown-menu pull-right" role="menu" aria-labelledby="dropdownMenu1">
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">Java</a>
        </li>
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">数据挖掘</a>
        </li>
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">数据通信/网络</a>
        </li>
        <li role="presentation" class="divider"></li>
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">分离的链接</a>
        </li>
    </ul>
</div>

<p></p>
<div class="alert alert-info"> 标题</div>
<p></p>
<div class="dropdown">
    <button type="button" class="btn dropdown-toggle" id="dropdownMenu3" data-toggle="dropdown">主题
        <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
        <li role="presentation" class="dropdown-header">下拉菜单标题</li>
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">Java</a>
        </li>
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">数据挖掘</a>
        </li>
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">数据通信/网络</a>
        </li>
        <li role="presentation" class="divider"></li>
        <li role="presentation" class="dropdown-header">下拉菜单标题</li>
        <li role="presentation">
            <a role="menuitem" tabindex="-1" href="#">分离的链接</a>
        </li>
    </ul>
</div>

<p></p>
<div class="alert alert-info">向上下拉菜单</div>
<p></p>

<div class="container">
    <div class="dropup">
        <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">教程
            <span class="caret"></span></button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">HTML</a></li>
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">CSS</a></li>
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">JavaScript</a></li>
            <li role="presentation" class="divider"></li>
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">关于我们</a></li>
        </ul>
    </div>
</div>

<p></p>
<div class="alert alert-info"><h2>下拉菜单</h2>
    <p>.disabled 类用于设置下拉菜单的禁用项：</p></div>
<p></p>
<div class="container">

    <div class="dropdown">
        <button class="btn btn-default dropdown-toggle" type="button" id="menuaa1" data-toggle="dropdown">教程
            <span class="caret"></span></button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="menuaa1">
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">HTML</a></li>
            <li role="presentation" class="disabled"><a role="menuitem" tabindex="-1" href="#">CSS</a></li>
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">JavaScript</a></li>
            <li role="presentation" class="divider"></li>
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">关于我们</a></li>
        </ul>
    </div>
</div>



<p></p>
<div class="alert alert-info">
    <h2>下拉菜单</h2>
    <p>.divider 类用于设置下拉菜单的分割线：</p>
</div>
<p></p>

<div class="container">
    <div class="dropdown">
        <button class="btn btn-default dropdown-toggle" type="button" id="menubb1" data-toggle="dropdown">教程
            <span class="caret"></span></button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="menubb1">
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">菜鸟教程</a></li>
            <li role="presentation" class="divider"></li>
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">HTML</a></li>
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">CSS</a></li>
            <li role="presentation" class="divider"></li>
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">关于我们</a></li>
        </ul>
    </div>
</div>

</body>
</html>
