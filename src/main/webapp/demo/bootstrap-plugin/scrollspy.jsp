<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>滚动监听</title>

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
<div class="alert alert-info">滚动监听</div>

<p></p>
<nav id="navbar-example" class="navbar navbar-default navbar-static" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse"
                    data-target=".bs-js-navbar-scrollspy">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">教程名称</a>
        </div>
        <div class="collapse navbar-collapse bs-js-navbar-scrollspy">
            <ul class="nav navbar-nav">
                <li><a href="#ios">iOS</a></li>
                <li><a href="#svn">SVN</a></li>
                <li class="dropdown">
                    <a href="#" id="navbarDrop1" class="dropdown-toggle"
                       data-toggle="dropdown">Java
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" role="menu"
                        aria-labelledby="navbarDrop1">
                        <li><a href="#jmeter" tabindex="-1">jmeter</a></li>
                        <li><a href="#ejb" tabindex="-1">ejb</a></li>
                        <li class="divider"></li>
                        <li><a href="#spring" tabindex="-1">spring</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div data-spy="scroll" data-target="#navbar-example" data-offset="0"
     style="height:200px;overflow:auto; position: relative;">
    <h4 id="ios">iOS</h4>
    <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple
        TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。
    </p>
    <h4 id="svn">SVN</h4>
    <p>Apache Subversion，通常缩写为 SVN，是一款开源的版本控制系统软件。Subversion 由 CollabNet 公司在 2000 年创建。但是现在它已经发展为 Apache Software Foundation 的一个项目，因此拥有丰富的开发人员和用户社区。
    </p>
    <h4 id="jmeter">jMeter</h4>
    <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。
    </p>
    <h4 id="ejb">EJB</h4>
    <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
    </p>
    <h4 id="spring">Spring</h4>
    <p>Spring 框架是一个开源的 Java 平台，为快速开发功能强大的 Java 应用程序提供了完备的基础设施支持。
    </p>
    <p>Spring 框架最初是由 Rod Johnson 编写的，在 2003 年 6 月首次发布于 Apache 2.0 许可证下。
    </p>
</div>



<br>
<br>
<div class="alert alert-info">滚动监听-refresh</div>

<p></p>

<nav id="myScrollspy2" class="navbar navbar-default navbar-static" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse"
                    data-target=".bs-js-navbar-scrollspy">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">教程名称</a>
        </div>
        <div class="collapse navbar-collapse bs-js-navbar-scrollspy">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#ios2">iOS</a></li>
                <li><a href="#svn2">SVN</a></li>
                <li class="dropdown">
                    <a href="#" id="navbarDrop2" class="dropdown-toggle"
                       data-toggle="dropdown">Java
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" role="menu"
                        aria-labelledby="navbarDrop2">
                        <li><a href="#jmeter2" tabindex="-1">jmeter</a></li>
                        <li><a href="#ejb2" tabindex="-1">ejb</a></li>
                        <li class="divider"></li>
                        <li><a href="#spring2" tabindex="-1">spring</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div data-spy="scroll" data-target="#myScrollspy2" data-offset="0"
     style="height:200px;overflow:auto; position: relative;">
    <div class="section">
        <h4 id="ios2">iOS<small><a href="#" onclick="removeSection(this);">
            &times; 删除该部分</a></small>
        </h4>
        <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple
            TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
    </div>
    <div class="section">
        <h4 id="svn2">SVN<small></small></h4>
        <p>Apache Subversion，通常缩写为 SVN，是一款开源的版本控制系统软件。Subversion 由 CollabNet 公司在 2000 年创建。但是现在它已经发展为 Apache Software Foundation 的一个项目，因此拥有丰富的开发人员和用户社区。</p>
    </div>
    <div class="section">
        <h4 id="jmeter2">jMeter<small><a href="#" onclick="removeSection(this);">
            &times; 删除该部分</a></small>
        </h4>
        <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
    </div>
    <div class="section">
        <h4 id="ejb2">EJB</h4>
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。</p>
    </div>
    <div class="section">
        <h4 id="spring2">Spring</h4>
        <p>Spring 框架是一个开源的 Java 平台，为快速开发功能强大的 Java 应用程序提供了完备的基础设施支持。</p>
        <p>Spring 框架最初是由 Rod Johnson 编写的，在 2003 年 6 月首次发布于 Apache 2.0 许可证下。</p>
    </div>
</div>
<script>
    $(function(){
        removeSection = function(e) {
            $(e).parents(".section").remove();
            $('[data-spy="scroll"]').each(function () {
                var $spy = $(this).scrollspy('refresh')
            });
        }
        $("#myScrollspy2").scrollspy();
    });
</script>




<br>
<br>
<div class="alert alert-info">事件</div>

<p></p>
<nav id="myScrollspy3" class="navbar navbar-default navbar-static" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse"
                    data-target=".bs-js-navbar-scrollspy">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">教程名称</a>
        </div>
        <div class="collapse navbar-collapse bs-js-navbar-scrollspy">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#ios3">iOS</a></li>
                <li><a href="#svn3">SVN</a></li>
                <li class="dropdown">
                    <a href="#" id="navbarDrop3" class="dropdown-toggle"
                       data-toggle="dropdown">
                        Java <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" role="menu"
                        aria-labelledby="navbarDrop3">
                        <li><a href="#jmeter3" tabindex="-1">jmeter</a></li>
                        <li><a href="#ejb3" tabindex="-1">ejb</a></li>
                        <li class="divider"></li>
                        <li><a href="#spring3" tabindex="-1">spring</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div data-spy="scroll" data-target="#myScrollspy" data-offset="0"
     style="height:200px;overflow:auto; position: relative;">
    <div class="section">
        <h4 id="ios3">iOS<small><a href="#" onclick="removeSection(this);">
            &times; 删除该部分</a></small>
        </h4>
        <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple
            TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
    </div>
    <div class="section">
        <h4 id="svn3">SVN<small></small></h4>
        <p>Apache Subversion，通常缩写为 SVN，是一款开源的版本控制系统软件。Subversion 由 CollabNet 公司在 2000 年创建。但是现在它已经发展为 Apache Software Foundation 的一个项目，因此拥有丰富的开发人员和用户社区。</p>
    </div>
    <div class="section">
        <h4 id="jmeter3">jMeter<small><a href="#" onclick="removeSection(this);">
            &times; 删除该部分</a></small>
        </h4>
        <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
    </div>
    <div class="section">
        <h4 id="ejb3">EJB</h4>
        <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。</p>
    </div>
    <div class="section">
        <h4 id="spring3">Spring</h4>
        <p>Spring 框架是一个开源的 Java 平台，为快速开发功能强大的 Java 应用程序提供了完备的基础设施支持。</p>
        <p>Spring 框架最初是由 Rod Johnson 编写的，在 2003 年 6 月首次发布于 Apache 2.0 许可证下。</p>
    </div>
</div>
<span id="activeitem" style="color:red;"></span>
<script>
    $(function(){
        removeSection = function(e) {
            $(e).parents(".section").remove();
            $('[data-spy="scroll"]').each(function () {
                var $spy = $(this).scrollspy('refresh')
            });
        }
        $("#myScrollspy3").scrollspy();
        $('#myScrollspy3').on('activate.bs.scrollspy', function () {
            var currentItem = $(".nav li.active > a").text();
            $("#activeitem").html("目前您正在查看 - " + currentItem);
        })
    });
</script>



<%--<br>--%>
<%--<br>--%>
<%--<div class="alert alert-info">创建水平滚动监听</div>--%>

<%--<p></p>--%>
<%--<style>--%>
    <%--body {--%>
        <%--position: relative;--%>
    <%--}--%>
    <%--#section1 {padding-top:50px;height:500px;color: #fff; background-color: #1E88E5;}--%>
    <%--#section2 {padding-top:50px;height:500px;color: #fff; background-color: #673ab7;}--%>
    <%--#section3 {padding-top:50px;height:500px;color: #fff; background-color: #ff9800;}--%>
    <%--#section41 {padding-top:50px;height:500px;color: #fff; background-color: #00bcd4;}--%>
    <%--#section42 {padding-top:50px;height:500px;color: #fff; background-color: #009688;}--%>
<%--</style>--%>

<%--<nav class="navbar navbar-inverse navbar-fixed-top">--%>
    <%--<div class="container-fluid">--%>
        <%--<div class="navbar-header">--%>
            <%--<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">--%>
                <%--<span class="icon-bar"></span>--%>
                <%--<span class="icon-bar"></span>--%>
                <%--<span class="icon-bar"></span>--%>
            <%--</button>--%>
            <%--<a class="navbar-brand" href="#">WebSiteName</a>--%>
        <%--</div>--%>
        <%--<div>--%>
            <%--<div class="collapse navbar-collapse" id="myNavbar">--%>
                <%--<ul class="nav navbar-nav">--%>
                    <%--<li><a href="#section1">Section 1</a></li>--%>
                    <%--<li><a href="#section2">Section 2</a></li>--%>
                    <%--<li><a href="#section3">Section 3</a></li>--%>
                    <%--<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Section 4 <span class="caret"></span></a>--%>
                        <%--<ul class="dropdown-menu">--%>
                            <%--<li><a href="#section41">Section 4-1</a></li>--%>
                            <%--<li><a href="#section42">Section 4-2</a></li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</nav>--%>

<%--<div id="section1" class="container-fluid">--%>
    <%--<h1>Section 1</h1>--%>
    <%--<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>--%>
    <%--<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>--%>
<%--</div>--%>
<%--<div id="section2" class="container-fluid">--%>
    <%--<h1>Section 2</h1>--%>
    <%--<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>--%>
    <%--<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>--%>
<%--</div>--%>
<%--<div id="section3" class="container-fluid">--%>
    <%--<h1>Section 3</h1>--%>
    <%--<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>--%>
    <%--<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>--%>
<%--</div>--%>
<%--<div id="section41" class="container-fluid">--%>
    <%--<h1>Section 4 Submenu 1</h1>--%>
    <%--<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>--%>
    <%--<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>--%>
<%--</div>--%>
<%--<div id="section42" class="container-fluid">--%>
    <%--<h1>Section 4 Submenu 2</h1>--%>
    <%--<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>--%>
    <%--<p>Try to scroll this section and look at the navigation bar while scrolling! Try to scroll this section and look at the navigation bar while scrolling!</p>--%>
<%--</div>--%>



<br>
<br>
<div class="alert alert-info">如何创建垂直滚动监听</div>

<p></p>
<style>
    body {
        position: relative;
    }
    ul.nav-pills {
        top: 20px;
        position: fixed;
    }
    div.col-sm-9 div {
        height: 250px;
        font-size: 28px;
    }
    #section1a {color: #fff; background-color: #1E88E5;}
    #section2a {color: #fff; background-color: #673ab7;}
    #section3a {color: #fff; background-color: #ff9800;}
    #section41a {color: #fff; background-color: #00bcd4;}
    #section42a {color: #fff; background-color: #009688;}

    @media screen and (max-width: 810px) {
        #section1, #section2, #section3, #section41, #section42  {
            margin-left: 150px;
        }
    }
</style>
<div class="container">
    <div class="row">
        <nav class="col-sm-3" id="myScrollspy">
            <div class="container-fluid">
                <div class="container-fluid">
                    <ul class="nav nav-pills nav-stacked">
                        <li class="active"><a href="#section1a">Section 1</a></li>
                        <li><a href="#section2a">Section 2</a></li>
                        <li><a href="#section3a">Section 3</a></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Section 4 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#section41a">Section 4-1</a></li>
                                <li><a href="#section42a">Section 4-2</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="col-sm-9">
            <div id="section1a">
                <h1>Section 1</h1>
                <p>Try to scroll this section and look at the navigation list while scrolling!</p>
            </div>
            <div id="section2a">
                <h1>Section 2</h1>
                <p>Try to scroll this section and look at the navigation list while scrolling!</p>
            </div>
            <div id="section3a">
                <h1>Section 3</h1>
                <p>Try to scroll this section and look at the navigation list while scrolling!</p>
            </div>
            <div id="section41a">
                <h1>Section 4-1</h1>
                <p>Try to scroll this section and look at the navigation list while scrolling!</p>
            </div>
            <div id="section42a">
                <h1>Section 4-2</h1>
                <p>Try to scroll this section and look at the navigation list while scrolling!</p>
            </div>
        </div>
    </div>
</div>


</body>
</html>
