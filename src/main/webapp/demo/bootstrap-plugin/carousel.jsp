<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>Carousel</title>

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
<div class="alert alert-info">Carousel</div>
<p></p>

<div id="myCarousel" class="carousel slide">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="images/slide1.png" alt="First slide">
        </div>
        <div class="item">
            <img src="images/slide2.png" alt="Second slide">
        </div>
        <div class="item">
            <img src="images/slide3.png" alt="Third slide">
        </div>
    </div>
    <!-- 轮播（Carousel）导航 -->
    <a class="carousel-control left" href="#myCarousel"
       data-slide="prev">&lsaquo;</a>
    <a class="carousel-control right" href="#myCarousel"
       data-slide="next">&rsaquo;</a>
</div>


<br>
<br>
<div class="alert alert-info">可选的标题</div>

<p></p>

<div id="myCarouse2" class="carousel slide">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
        <li data-target="#myCarouse2" data-slide-to="0" class="active"></li>
        <li data-target="#myCarouse2" data-slide-to="1"></li>
        <li data-target="#myCarouse2" data-slide-to="2"></li>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="images/slide1.png" alt="First slide">
            <div class="carousel-caption">标题 1</div>
        </div>
        <div class="item">
            <img src="images/slide2.png" alt="Second slide">
            <div class="carousel-caption">标题 2</div>
        </div>
        <div class="item">
            <img src="images/slide3.png" alt="Third slide">
            <div class="carousel-caption">标题 3</div>
        </div>
    </div>
    <!-- 轮播（Carousel）导航 -->
    <a class="carousel-control left" href="#myCarouse2"
       data-slide="prev">&lsaquo;
    </a>
    <a class="carousel-control right" href="#myCarouse2"
       data-slide="next">&rsaquo;
    </a>
</div>



<br>
<br>
<div class="alert alert-info">实例</div>

<p></p>
<div id="myCarouse3" class="carousel slide">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
        <li data-target="#myCarouse3" data-slide-to="0"
            class="active"></li>
        <li data-target="#myCarouse3" data-slide-to="1"></li>
        <li data-target="#myCarouse3" data-slide-to="2"></li>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="images/slide1.png" alt="First slide">
        </div>
        <div class="item">
            <img src="images/slide2.png" alt="Second slide">
        </div>
        <div class="item">
            <img src="images/slide3.png" alt="Third slide">
        </div>
    </div>
    <!-- 轮播（Carousel）导航 -->
    <a class="carousel-control left" href="#myCarouse3"
       data-slide="prev">&lsaquo;</a>
    <a class="carousel-control right" href="#myCarouse3"
       data-slide="next">&rsaquo;</a>
    <!-- 控制按钮 -->
    <div style="text-align:center;">
        <input type="button" class="btn start-slide" value="Start">
        <input type="button" class="btn pause-slide" value="Pause">
        <input type="button" class="btn prev-slide" value="Previous Slide">
        <input type="button" class="btn next-slide" value="Next Slide">
        <input type="button" class="btn slide-one" value="Slide 1">
        <input type="button" class="btn slide-two" value="Slide 2">
        <input type="button" class="btn slide-three" value="Slide 3">
    </div>
</div>
<script>
    $(function(){
        // 初始化轮播
        $(".start-slide").click(function(){
            $("#myCarouse3").carousel('cycle');
        });
        // 停止轮播
        $(".pause-slide").click(function(){
            $("#myCarouse3").carousel('pause');
        });
        // 循环轮播到上一个项目
        $(".prev-slide").click(function(){
            $("#myCarousel").carousel('prev');
        });
        // 循环轮播到下一个项目
        $(".next-slide").click(function(){
            $("#myCarouse3").carousel('next');
        });
        // 循环轮播到某个特定的帧
        $(".slide-one").click(function(){
            $("#myCarouse3").carousel(0);
        });
        $(".slide-two").click(function(){
            $("#myCarouse3").carousel(1);
        });
        $(".slide-three").click(function(){
            $("#myCarouse3").carousel(2);
        });
    });
</script>


<br>
<br>
<div class="alert alert-info">事件</div>

<p></p>
<div id="myCarouse4" class="carousel slide">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
        <li data-target="#myCarouse4" data-slide-to="0"
            class="active"></li>
        <li data-target="#myCarouse4" data-slide-to="1"></li>
        <li data-target="#myCarouse4" data-slide-to="2"></li>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="images/slide1.png" alt="First slide">
        </div>
        <div class="item">
            <img src="images/slide2.png" alt="Second slide">
        </div>
        <div class="item">
            <img src="images/slide3.png" alt="Third slide">
        </div>
    </div>
    <!-- 轮播（Carousel）导航 -->
    <a class="carousel-control left" href="#myCarouse4"
       data-slide="prev">&lsaquo;</a>
    <a class="carousel-control right" href="#myCarouse4"
       data-slide="next">&rsaquo;</a>
</div>
<script>
    $(function(){
        $('#myCarouse4').on('slide.bs.carousel', function () {
            alert("当调用 slide 实例方法时立即触发该事件。");
        });
    });
</script>

</body>
</html>
