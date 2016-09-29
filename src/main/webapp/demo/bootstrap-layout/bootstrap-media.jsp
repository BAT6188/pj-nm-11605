<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>Bootstrap 多媒体对象（Media Object）</title>

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
<div class="alert alert-info">Bootstrap 多媒体对象（Media Object）</div>

<p></p>
<div class="media">
    <a class="pull-left" href="#">
        <img class="media-object" src="images/timg2.jpg"
             alt="媒体对象">
    </a>
    <div class="media-body">
        <h4 class="media-heading">媒体标题</h4>
        这是一些示例文本。这是一些示例文本。
        这是一些示例文本。这是一些示例文本。
        这是一些示例文本。这是一些示例文本。
        这是一些示例文本。这是一些示例文本。
        这是一些示例文本。这是一些示例文本。
    </div>
</div>
<div class="media">
    <a class="pull-left" href="#">
        <img class="media-object" src="images/timg2.jpg"
             alt="媒体对象">
    </a>
    <div class="media-body">
        <h4 class="media-heading">媒体标题</h4>
        这是一些示例文本。这是一些示例文本。
        这是一些示例文本。这是一些示例文本。
        这是一些示例文本。这是一些示例文本。
        这是一些示例文本。这是一些示例文本。
        这是一些示例文本。这是一些示例文本。
        <div class="media">
            <a class="pull-left" href="#">
                <img class="media-object" src="images/timg2.jpg"
                     alt="媒体对象">
            </a>
            <div class="media-body">
                <h4 class="media-heading">媒体标题</h4>
                这是一些示例文本。这是一些示例文本。
                这是一些示例文本。这是一些示例文本。
                这是一些示例文本。这是一些示例文本。
                这是一些示例文本。这是一些示例文本。
                这是一些示例文本。这是一些示例文本。
            </div>
        </div>
    </div>
</div>

<br>
<br>
<div class="alert alert-info">Bootstrap 多媒体对象（Media Object）</div>

<p></p>
<ul class="media-list">
    <li class="media">
        <a class="pull-left" href="#">
            <img class="media-object" src="images/timg2.jpg"
                 alt="通用的占位符图像">
        </a>
        <div class="media-body">
            <h4 class="media-heading">媒体标题</h4>
            <p>这是一些示例文本。这是一些示例文本。
                这是一些示例文本。这是一些示例文本。
                这是一些示例文本。这是一些示例文本。
                这是一些示例文本。这是一些示例文本。
                这是一些示例文本。这是一些示例文本。</p>
            <!-- 嵌套的媒体对象 -->
            <div class="media">
                <a class="pull-left" href="#">
                    <img class="media-object" src="images/timg2.jpg"
                         alt="通用的占位符图像">
                </a>
                <div class="media-body">
                    <h4 class="media-heading">嵌套的媒体标题</h4>
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    <!-- 嵌套的媒体对象 -->
                    <div class="media">
                        <a class="pull-left" href="#">
                            <img class="media-object" src="images/timg2.jpg"
                                 alt="通用的占位符图像">
                        </a>
                        <div class="media-body">
                            <h4 class="media-heading">嵌套的媒体标题</h4>
                            这是一些示例文本。这是一些示例文本。
                            这是一些示例文本。这是一些示例文本。
                            这是一些示例文本。这是一些示例文本。
                            这是一些示例文本。这是一些示例文本。
                            这是一些示例文本。这是一些示例文本。
                        </div>
                    </div>
                </div>
            </div>
            <!-- 嵌套的媒体对象 -->
            <div class="media">
                <a class="pull-left" href="#">
                    <img class="media-object" src="images/timg2.jpg"
                         alt="通用的占位符图像">
                </a>
                <div class="media-body">
                    <h4 class="media-heading">嵌套的媒体标题</h4>
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                    这是一些示例文本。这是一些示例文本。
                </div>
            </div>
        </div>
    </li>
    <li class="media">
        <a class="pull-right" href="#">
            <img class="media-object" src="images/timg2.jpg"
                 alt="通用的占位符图像">
        </a>
        <div class="media-body">
            <h4 class="media-heading">媒体标题</h4>
            这是一些示例文本。这是一些示例文本。
            这是一些示例文本。这是一些示例文本。
            这是一些示例文本。这是一些示例文本。
            这是一些示例文本。这是一些示例文本。
            这是一些示例文本。这是一些示例文本。
        </div>
    </li>
</ul>
</body>
</html>
