<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>Bootstrap 徽章（Badges）</title>

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
<div class="alert alert-info">Bootstrap 徽章（Badges）</div>
<p></p>

<a href="#">Mailbox <span class="badge">50</span></a>

<br>
<br>
<div class="alert alert-info">徽章</div>
<p></p>
<div class="container">
    <h2>徽章</h2>
    <p>.badge 类指定未读消息的数量:</p>
    <p><a href="#">收件箱 <span class="badge">21</span></a></p>
</div>


<br>
<br>
<div class="alert alert-info">激活导航状态</div>
<p></p>
<h4>胶囊式导航中的激活状态</h4>
<ul class="nav nav-pills">
    <li class="active">
        <a href="#">首页
            <span class="badge">42</span>
        </a>
    </li>
    <li>
        <a href="#">简介</a>
    </li>
    <li>
        <a href="#">消息
            <span class="badge">3</span>
        </a>
    </li>
</ul>
<br>
<h4>列表导航中的激活状态</h4>
<ul class="nav nav-pills nav-stacked" style="max-width: 260px;">
    <li class="active">
        <a href="#">
            <span class="badge pull-right">42</span>首页</a>
    </li>
    <li>
        <a href="#">简介</a>
    </li>
    <li>
        <a href="#">
            <span class="badge pull-right">3</span>消息
        </a>
    </li>
</ul>



</body>
</html>
