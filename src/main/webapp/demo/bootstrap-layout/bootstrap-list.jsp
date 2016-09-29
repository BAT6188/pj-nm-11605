<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>Bootstrap 列表组</title>

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
<div class="alert alert-info">Bootstrap 列表组</div>

<p></p>
<ul class="list-group">
    <li class="list-group-item">免费域名注册</li>
    <li class="list-group-item">免费 Window 空间托管</li>
    <li class="list-group-item">图像的数量</li>
    <li class="list-group-item">24*7 支持</li>
    <li class="list-group-item">每年更新成本</li>
</ul>

<br>
<br>
<div class="alert alert-info">向列表组添加徽章</div>

<p></p>
<ul class="list-group">
    <li class="list-group-item">免费域名注册</li>
    <li class="list-group-item">免费 Window 空间托管</li>
    <li class="list-group-item">图像的数量</li>
    <li class="list-group-item">
        <span class="badge">新</span>
        24*7 支持
    </li>
    <li class="list-group-item">每年更新成本</li>
    <li class="list-group-item">
        <span class="badge">新</span>
        折扣优惠
    </li>
</ul>

<br>
<br>
<div class="alert alert-info">向列表组添加链接</div>

<p></p>
<a href="#" class="list-group-item active">
    免费域名注册
</a>
<a href="#" class="list-group-item">24*7 支持</a>
<a href="#" class="list-group-item">免费 Window 空间托管</a>
<a href="#" class="list-group-item">图像的数量</a>
<a href="#" class="list-group-item">每年更新成本</a>
<br>
<br>
<div class="alert alert-info">向列表组添加自定义内容</div>

<p></p>
<div class="list-group">
    <a href="#" class="list-group-item active">
        <h4 class="list-group-item-heading">
            入门网站包
        </h4>
    </a>
    <a href="#" class="list-group-item">
        <h4 class="list-group-item-heading">
            免费域名注册
        </h4>
        <p class="list-group-item-text">
            您将通过网页进行免费域名注册。
        </p>
    </a>
    <a href="#" class="list-group-item">
        <h4 class="list-group-item-heading">
            24*7 支持
        </h4>
        <p class="list-group-item-text">
            我们提供 24*7 支持。
        </p>
    </a>
</div>
<div class="list-group">
    <a href="#" class="list-group-item active">
        <h4 class="list-group-item-heading">
            商务网站包
        </h4>
    </a>
    <a href="#" class="list-group-item">
        <h4 class="list-group-item-heading">
            免费域名注册
        </h4>
        <p class="list-group-item-text">
            您将通过网页进行免费域名注册。
        </p>
    </a>
    <a href="#" class="list-group-item">
        <h4 class="list-group-item-heading">24*7 支持</h4>
        <p class="list-group-item-text">我们提供 24*7 支持。</p>
    </a>
</div>


</body>
</html>
