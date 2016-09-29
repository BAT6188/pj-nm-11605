<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>button</title>

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
<div class="alert alert-info">button</div>
<p></p>
<button id="fat-btn" class="btn btn-primary" data-loading-text="Loading..."
        type="button"> 加载状态
</button>
<script>
    $(function() {
        $("#fat-btn").click(function(){
            $(this).button('loading').delay(1000).queue(function() {
                // $(this).button('reset');
            });
        });
    });
</script>

<br>
<br>
<div class="alert alert-info">单个切换</div>

<p></p>

<button type="button" class="btn btn-primary"
        data-toggle="button"> 单个切换
</button>



<br>
<br>
<div class="alert alert-info">复选框（Checkbox）</div>

<p></p>
<div class="btn-group" data-toggle="buttons">
    <label class="btn btn-primary">
        <input type="checkbox"> 选项 1
    </label>
    <label class="btn btn-primary">
        <input type="checkbox"> 选项 2
    </label>
    <label class="btn btn-primary">
        <input type="checkbox"> 选项 3
    </label>
</div>


<br>
<br>
<div class="alert alert-info">单选按钮（Radio）</div>

<p></p>
<div class="btn-group" data-toggle="buttons">
    <label class="btn btn-primary">
        <input type="radio" name="options" id="option1"> 选项 1
    </label>
    <label class="btn btn-primary">
        <input type="radio" name="options" id="option2"> 选项 2
    </label>
    <label class="btn btn-primary">
        <input type="radio" name="options" id="option3"> 选项 3
    </label>
</div>

<br>
<br>
<div class="alert alert-info">button-js</div>

<p></p>
<h2>点击每个按钮查看方法效果</h2>
<h4>演示 .button('toggle') 方法</h4>
<div id="myButtons1" class="bs-example">
    <button type="button" class="btn btn-primary">原始</button>
</div>

<h4>演示 .button('loading') 方法</h4>
<div id="myButtons2" class="bs-example">
    <button type="button" class="btn btn-primary"
            data-loading-text="Loading...">原始
    </button>
</div>

<h4>演示 .button('reset') 方法</h4>
<div id="myButtons3" class="bs-example">
    <button type="button" class="btn btn-primary"
            data-loading-text="Loading...">原始
    </button>
</div>

<h4>演示 .button(string) 方法</h4>
<button type="button" class="btn btn-primary" id="myButton4"
        data-complete-text="Loading finished">请点击我
</button>
<script>
    $(function () {
        $("#myButtons1 .btn").click(function(){
            $(this).button('toggle');
        });
    });
    $(function() {
        $("#myButtons2 .btn").click(function(){
            $(this).button('loading').delay(1000).queue(function() {
            });
        });
    });
    $(function() {
        $("#myButtons3 .btn").click(function(){
            $(this).button('loading').delay(1000).queue(function() {
                $(this).button('reset');
            });
        });
    });
    $(function() {
        $("#myButton4").click(function(){
            $(this).button('loading').delay(1000).queue(function() {
                $(this).button('complete');
            });
        });
    });
</script>

</body>
</html>
