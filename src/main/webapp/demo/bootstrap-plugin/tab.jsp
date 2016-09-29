<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>tab</title>

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
<div class="alert alert-info">tab1</div>

<p></p>

<ul id="myTab" class="nav nav-tabs">
    <li class="active">
        <a href="#home" data-toggle="tab">
            W3Cschool Home
        </a>
    </li>
    <li><a href="#ios" data-toggle="tab">iOS</a></li>
    <li class="dropdown">
        <a href="#" id="myTabDrop1" class="dropdown-toggle"
           data-toggle="dropdown">Java
            <b class="caret"></b>
        </a>
        <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
            <li><a href="#jmeter" tabindex="-1" data-toggle="tab">jmeter</a></li>
            <li><a href="#ejb" tabindex="-1" data-toggle="tab">ejb</a></li>
        </ul>
    </li>
</ul>
<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="home">
        <p>W3Cschoool菜鸟教程是一个提供最新的web技术站点，本站免费提供了建站相关的技术文档，帮助广大web技术爱好者快速入门并建立自己的网站。菜鸟先飞早入行——学的不仅是技术，更是梦想。</p>
    </div>
    <div class="tab-pane fade" id="ios">
        <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple
            TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
    </div>
    <div class="tab-pane fade" id="jmeter">
        <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
    </div>
    <div class="tab-pane fade" id="ejb">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>
</div>



<br>
<br>
<div class="alert alert-info">标签页（Tab）插件方法</div>

<p></p>

<ul id="myTab2" class="nav nav-tabs">
    <li class="active"><a href="#home2" data-toggle="tab">
        W3Cschool Home</a>
    </li>
    <li><a href="#ios" data-toggle="tab">iOS</a></li>
    <li class="dropdown">
        <a href="#" id="myTabDrop2" class="dropdown-toggle"
           data-toggle="dropdown">Java <b class="caret"></b>
        </a>
        <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop2">
            <li><a href="#jmeter" tabindex="-1" data-toggle="tab">
                jmeter</a>
            </li>
            <li><a href="#ejb" tabindex="-1" data-toggle="tab">
                ejb</a>
            </li>
        </ul>
    </li>
</ul>
<div id="myTabContent2" class="tab-content">
    <div class="tab-pane fade in active" id="home2">
        <p>W3Cschoool菜鸟教程是一个提供最新的web技术站点，本站免费提供了建站相关的技术文档，帮助广大web技术爱好者快速入门并建立自己的网站。菜鸟先飞早入行——学的不仅是技术，更是梦想。</p>
    </div>
    <div class="tab-pane fade" id="ios2">
        <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple
            TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
    </div>
    <div class="tab-pane fade" id="jmeter2">
        <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
    </div>
    <div class="tab-pane fade" id="ejb2">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>
</div>
<script>
    $(function () {
        $('#myTab2 li:eq(1) a').tab('show');
    });
</script>


<br>
<br>
<div class="alert alert-info">event</div>

<p></p>

<hr>
<p class="active-tab"><strong>激活的标签页</strong>：<span></span></p>
<p class="previous-tab"><strong>前一个激活的标签页</strong>：<span></span></p>
<hr>
<ul id="myTab3" class="nav nav-tabs">
    <li class="active"><a href="#home3" data-toggle="tab">
        W3Cschool Home</a></li>
    <li><a href="#ios3" data-toggle="tab">iOS</a></li>
    <li class="dropdown">
        <a href="#" id="myTabDrop3" class="dropdown-toggle"
           data-toggle="dropdown">
            Java <b class="caret"></b></a>
        <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop3">
            <li><a href="#jmeter3" tabindex="-1" data-toggle="tab">jmeter</a></li>
            <li><a href="#ejb3" tabindex="-1" data-toggle="tab">ejb</a></li>
        </ul>
    </li>
</ul>
<div id="myTabContent3" class="tab-content">
    <div class="tab-pane fade in active" id="home3">
        <p>W3Cschoool菜鸟教程是一个提供最新的web技术站点，本站免费提供了建站相关的技术文档，帮助广大web技术爱好者快速入门并建立自己的网站。菜鸟先飞早入行——学的不仅是技术，更是梦想。</p>
    </div>
    <div class="tab-pane fade" id="ios3">
        <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple
            TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
    </div>
    <div class="tab-pane fade" id="jmeter3">
        <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
    </div>
    <div class="tab-pane fade" id="ejb3">
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
        </p>
    </div>
</div>
<script>
    $(function(){
        $('#myTab3 > a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            // 获取已激活的标签页的名称
            var activeTab = $(e.target).text();
            // 获取前一个激活的标签页的名称
            var previousTab = $(e.relatedTarget).text();
            $(".active-tab span").html(activeTab);
            $(".previous-tab span").html(previousTab);
        });
    });
</script>
</body>
</html>
