<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>Bootstrap 警告（Alerts）</title>

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
<div class="alert alert-info">Bootstrap 警告（Alerts）</div>
<p></p>

<div class="alert alert-success">成功！很好地完成了提交。</div>
<div class="alert alert-info">信息！请注意这个信息。</div>
<div class="alert alert-warning">警告！请不要提交。</div>
<div class="alert alert-danger">错误！请进行一些更改。</div>

<br>
<br>
<div class="alert alert-info">可取消的警告（Dismissal Alerts）</div>
<p></p>
<div class="alert alert-success alert-dismissable">
    <button type="button" class="close" data-dismiss="alert"
            aria-hidden="true">
        &times;
    </button>
    成功！很好地完成了提交。
</div>
<div class="alert alert-info alert-dismissable">
    <button type="button" class="close" data-dismiss="alert"
            aria-hidden="true">
        &times;
    </button>
    信息！请注意这个信息。
</div>
<div class="alert alert-warning alert-dismissable">
    <button type="button" class="close" data-dismiss="alert"
            aria-hidden="true">
        &times;
    </button>
    警告！请不要提交。
</div>
<div class="alert alert-danger alert-dismissable">
    <button type="button" class="close" data-dismiss="alert"
            aria-hidden="true">
        &times;
    </button>
    错误！请进行一些更改。
</div>


<br>
<br>
<div class="alert alert-info">警告（Alerts）中的链接</div>
<p></p>
<div class="alert alert-success">
    <a href="#" class="alert-link">成功！很好地完成了提交。</a>
</div>
<div class="alert alert-info">
    <a href="#" class="alert-link">信息！请注意这个信息。</a>
</div>
<div class="alert alert-warning">
    <a href="#" class="alert-link">警告！请不要提交。</a>
</div>
<div class="alert alert-danger">
    <a href="#" class="alert-link">错误！请进行一些更改。</a>
</div>



</body>
</html>
