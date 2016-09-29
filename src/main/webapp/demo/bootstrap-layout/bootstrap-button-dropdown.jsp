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
<div class="alert alert-info">Bootstrap 按钮下拉菜单</div>

<p></p>
<div class="btn-group">
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">默认
        <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
        <li>
            <a href="#">功能</a>
        </li>
        <li>
            <a href="#">另一个功能</a>
        </li>
        <li>
            <a href="#">其他</a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="#">分离的链接</a>
        </li>
    </ul>
</div>
<div class="btn-group">
    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">原始
        <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
        <li>
            <a href="#">功能</a>
        </li>
        <li>
            <a href="#">另一个功能</a>
        </li>
        <li>
            <a href="#">其他</a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="#">分离的链接</a>
        </li>
    </ul>
</div>
<p></p>


<div class="alert alert-info"> 分割的按钮下拉菜单</div>
<p></p>
<div class="btn-group">
    <button type="button" class="btn btn-default">默认</button>
    <button type="button" class="btn btn-default dropdown-toggle"
            data-toggle="dropdown">
        <span class="caret"></span>
        <span class="sr-only">切换下拉菜单</span>
    </button>
    <ul class="dropdown-menu" role="menu">
        <li><a href="#">功能</a></li>
        <li><a href="#">另一个功能</a></li>
        <li><a href="#">其他</a></li>
        <li class="divider"></li>
        <li><a href="#">分离的链接</a></li>
    </ul>
</div>
<div class="btn-group">
    <button type="button" class="btn btn-primary">原始</button>
    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
        <span class="caret"></span>
        <span class="sr-only">切换下拉菜单</span>
    </button>
    <ul class="dropdown-menu" role="menu">
        <li><a href="#">功能</a></li>
        <li><a href="#">另一个功能</a></li>
        <li><a href="#">其他</a></li>
        <li class="divider"></li>
        <li><a href="#">分离的链接</a></li>
    </ul>
</div>

<p></p>
<div class="alert alert-info"> 按钮下拉菜单的大小</div>
<p></p>
<div class="btn-group">
    <button type="button" class="btn btn-default dropdown-toggle btn-lg" data-toggle="dropdown">默认
        <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
        <li>
            <a href="#">功能</a>
        </li>
        <li>
            <a href="#">另一个功能</a>
        </li>
        <li>
            <a href="#">其他</a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="#">分离的链接</a>
        </li>
    </ul>
</div>
<div class="btn-group">
    <button type="button" class="btn btn-primary dropdown-toggle btn-sm" data-toggle="dropdown">原始
        <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
        <li>
            <a href="#">功能</a>
        </li>
        <li>
            <a href="#">另一个功能</a>
        </li>
        <li>
            <a href="#">其他</a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="#">分离的链接</a>
        </li>
    </ul>
</div>
<div class="btn-group">
    <button type="button" class="btn btn-success dropdown-toggle btn-xs" data-toggle="dropdown">成功
        <span class="caret"></span></button>
    <ul class="dropdown-menu" role="menu">
        <li>
            <a href="#">功能</a>
        </li>
        <li>
            <a href="#">另一个功能</a>
        </li>
        <li>
            <a href="#">其他</a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="#">分离的链接</a>
        </li>
    </ul>
</div>
<p></p>
<div class="alert alert-info"> 按钮上拉菜单</div>
<p></p>
<div class="row" style="margin-left:50px; margin-top:200px">
    <div class="btn-group dropup">
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">默认
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="#">功能</a>
            </li>
            <li>
                <a href="#">另一个功能</a>
            </li>
            <li>
                <a href="#">其他</a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#">分离的链接</a>
            </li>
        </ul>
    </div>
    <div class="btn-group dropup">
        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">原始
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="#">功能</a>
            </li>
            <li>
                <a href="#">另一个功能</a>
            </li>
            <li>
                <a href="#">其他</a>
            </li>
            <li class="divider"></li>
            <li>
                <a href="#">分离的链接</a>
            </li>
        </ul>
    </div>
</div>



</body>
</html>
