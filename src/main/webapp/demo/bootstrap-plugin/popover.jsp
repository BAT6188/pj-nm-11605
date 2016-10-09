<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>popover</title>

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
<div class="alert alert-info">popover</div>
<p></p>

<div class="container" style="padding: 100px 50px 10px;" >
    <button type="button" class="btn btn-default" title="Popover title"
            data-container="body" data-toggle="popover" data-placement="left"
            data-content="左侧的 Popover 中的一些内容">
        左侧的 Popover
    </button>
    <button type="button" class="btn btn-primary" title="Popover title"
            data-container="body" data-toggle="popover" data-placement="top"
            data-content="顶部的 Popover 中的一些内容">
        顶部的 Popover
    </button>
    <button type="button" class="btn btn-success" title="Popover title"
            data-container="body" data-toggle="popover" data-placement="bottom"
            data-content="底部的 Popover 中的一些内容">
        底部的 Popover
    </button>
    <button type="button" class="btn btn-warning" title="Popover title"
            data-container="body" data-toggle="popover" data-placement="right"
            data-content="右侧的 Popover 中的一些内容">
        右侧的 Popover
    </button>
</div>
<script>
    $(function () {
        $("[data-toggle='popover']").popover();
    });
</script>

<br>
<br>
<div class="alert alert-info">popover</div>

<p></p>

<div class="container" style="padding: 100px 50px 10px;" >
    <button type="button" class="btn btn-default popover-show"
            title="Popover title" data-container="body"
            data-toggle="popover" data-placement="left"
            data-content="左侧的 Popover 中的一些内容 —— show 方法">
        左侧的 Popover
    </button>
    <button type="button" class="btn btn-primary popover-hide"
            title="Popover title" data-container="body"
            data-toggle="popover" data-placement="top"
            data-content="顶部的 Popover 中的一些内容 —— hide 方法">
        顶部的 Popover
    </button>
    <button type="button" class="btn btn-success popover-destroy"
            title="Popover title" data-container="body"
            data-toggle="popover" data-placement="bottom"
            data-content="底部的 Popover 中的一些内容 —— destroy 方法">
        底部的 Popover
    </button>
    <button type="button" class="btn btn-warning popover-toggle"
            title="Popover title" data-container="body"
            data-toggle="popover" data-placement="right"
            data-content="右侧的 Popover 中的一些内容 —— toggle 方法">
        右侧的 Popover
    </button><br><br><br><br><br><br>
    <p class="popover-options">
        <a href="#" type="button" class="btn btn-warning" title="<h2>Title</h2>"
           data-container="body" data-toggle="popover" data-content="
																	 <h4>Popover 中的一些内容 —— options 方法</h4>">
            Popover
        </a>
    </p>
    <script>
        $(function () { $('.popover-show').popover('show');});
        $(function () { $('.popover-hide').popover('hide');});
        $(function () { $('.popover-destroy').popover('destroy');});
        $(function () { $('.popover-toggle').popover('toggle');});
        $(function () { $(".popover-options a").popover({html : true });});
    </script>
</div>



<br>
<br>
<div class="alert alert-info">popover</div>

<p></p>

<div clas="container" style="padding: 100px 50px 10px;" >
    <button type="button" class="btn btn-primary popover-show"
            title="Popover title" data-container="body"
            data-toggle="popover"
            data-content="左侧的 Popover 中的一些内容 —— show 方法">
        左侧的 Popover
    </button>
</div>
<script>
    $(function () { $('.popover-show').popover('show');});
    $(function () { $('.popover-show').on('shown.bs.popover', function () {
        alert("当显示时警告消息");
    })});
</script>

</body>
</html>
