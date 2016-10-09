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
<br>
<br>
<br>
<div class="alert alert-info"> 字体图标http://www.runoob.com/bootstrap/bootstrap-glyphicons.html</div>
<p>
    <button type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-sort-by-attributes"></span>
    </button>
    <button type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-sort-by-attributes-alt"></span>
    </button>
    <button type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-sort-by-order"></span>
    </button>
    <button type="button" class="btn btn-default">
        <span class="glyphicon glyphicon-sort-by-order-alt"></span>
    </button>
</p>
<button type="button" class="btn btn-default btn-lg">
    <span class="glyphicon glyphicon-user"></span>
    User
</button>
<button type="button" class="btn btn-default btn-sm">
    <span class="glyphicon glyphicon-user"></span>
    User
</button>
<button type="button" class="btn btn-default btn-xs">
    <span class="glyphicon glyphicon-user"></span>
    User
</button>

<p></p>
<div class="alert alert-info"> 带有导航栏的字体图标</div>
<p></p>
<div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="#">
                        <span class="glyphicon glyphicon-home">Home</span></a>
                </li>
                <li>
                    <a href="#shop">
                        <span class="glyphicon glyphicon-shopping-cart">Shop</span></a>
                </li>
                <li>
                    <a href="#support">
                        <span class="glyphicon glyphicon-headphones">Support</span></a>
                </li>
            </ul>
        </div>
        <!-- /.nav-collapse -->
    </div>
    <!-- /.container -->
</div>

<p></p>
<div class="alert alert-info"> 定制字体图标</div>
<p></p>
<button type="button" class="btn btn-primary btn-lg">
    <span class="glyphicon glyphicon-user"></span> User
</button>

<p></p>
<div class="alert alert-info"> 定制字体尺寸</div>
<p></p>
<button type="button" class="btn btn-primary btn-lg" style="font-size: 60px">
    <span class="glyphicon glyphicon-user"></span> User
</button>

<p></p>
<div class="alert alert-info">定制字体颜色</div>
<p></p>
<button type="button" class="btn btn-primary btn-lg" style="color: rgb(212, 106, 64);">
    <span class="glyphicon glyphicon-user"></span> User
</button>

<p></p>
<div class="alert alert-info">应用文本阴影</div>
<p></p>
<button type="button" class="btn btn-primary btn-lg" style="text-shadow: black 5px 3px 3px;">
    <span class="glyphicon glyphicon-user"></span> User
</button>


</body>
</html>
