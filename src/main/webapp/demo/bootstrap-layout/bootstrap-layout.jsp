<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>网格系统</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link href="<%=request.getContextPath()%>/common/scripts/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/common/scripts/font-awesome-4.6.3/css/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/common/scripts/bootstrap-datetimepicker2.3.11/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/pageStyle.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/common/scripts/jquery1.12.4/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/bootstrap-3.3.7/js/bootstrap.js"></script>
    
    <style>
        .back-color{
            background-color: #00bcd4;
            border:5px solid red;
        }
        
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="back-color col-md-1">col-md-1</div>
        <div class="back-color col-md-1">col-md-1</div>
        <div class="back-color col-md-1">col-md-1</div>
        <div class="back-color col-md-1">col-md-1</div>
        <div class="back-color col-md-1">col-md-1</div>
        <div class="back-color col-md-1">col-md-1</div>
        <div class="back-color col-md-1">col-md-1</div>
        <div class="back-color col-md-1">col-md-1</div>
        <div class="back-color col-md-1">col-md-1</div>
        <div class="back-color col-md-1">col-md-1</div>
        <div class="back-color col-md-1">col-md-1</div>
        <div class="back-color col-md-1">col-md-1</div>
    </div>
    <div class="row">
        <div class="back-color  col-md-8">.col-md-8</div>
        <div class="back-color  col-md-4">.col-md-4</div>
    </div>
    <div class="row">
        <div class="back-color col-md-4">.col-md-4</div>
        <div class="back-color col-md-4">.col-md-4</div>
        <div class="back-color col-md-4">.col-md-4</div>
    </div>
    <div class="row">
        <div class="back-color col-md-6">.col-md-6</div>
        <div class="back-color col-md-6">.col-md-6</div>
    </div>
    <div class="row">
        <div class="back-color col-md-2">.col-md-2</div>
        <div class="back-color col-md-4">.col-md-4</div>
        <div class="back-color col-md-2">.col-md-2</div>
        <div class="back-color col-md-4">.col-md-4</div>
    </div>
</div>

</body>
</html>
