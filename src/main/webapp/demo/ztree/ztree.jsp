<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>日期组件</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link href="<%=request.getContextPath()%>/scripts/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/scripts/font-awesome-4.6.3/css/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/scripts/ztree-3.5.24/metrStyle-cd/metroStyle.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/scripts/jquery1.12.4/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/bootstrap-3.3.7/js/bootstrap.js"></script>

    <script src="<%=request.getContextPath()%>/scripts/ztree-3.5.24/jquery.ztree.all.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/slimScroll/jquery.slimscroll.js"></script>
<style>

    .Node-frame-menubar {
        width: 300px;
        height: 500px;
        position: relative;
        left: 0px;
        border-right: 1px solid #e5e5e5;
        padding: 10px;
    }
</style>
</head>
<body>
<div class="container">
    <div class="alert alert-info"> 树组件</div>

    <button  type="button" class="btn btn-default" data-id="show" >显示</button>
    <button  type="button" class="btn btn-default" data-id="hide"  >隐藏</button>
    <button  type="button" class="btn btn-default" data-id="update"  >以输入值为当前值</button>
    <button  type="button" class="btn btn-default" data-id="setStart"  >限制开始</button>
    <button  type="button" class="btn btn-default" data-id="setEnd"  >限制结束</button>
    <button  type="button" class="btn btn-default" data-id="setStartNull"  >解除限制开始</button>
    <button  type="button" class="btn btn-default"  data-id="setEndNull" >解除限制结束</button>
    <button  type="button" class="btn btn-default"  data-id="remove" >删除</button>

    <div class="alert alert-info"> 树组件</div>
    <p></p>
    <div class="Node-frame-menubar">
        <div class="scrollContent" >
            <ul id="treeDemo1" class="ztree"></ul>
        </div>
    </div>
    <p></p>
</div>


</body>
</html>

<script>

    $(".scrollContent").slimScroll({
        height:"100%",
        railOpacity:.9,
        alwaysVisible:!1
    });

    var setting = {
        height:500,
        width:200,
        view: {
            showLine: false
        },
        async: {
            enable: true,
            url:"ztreeData.json",
            autoParam:["id", "name=n", "level=lv"],
            otherParam:{"otherParam":"zTreeAsyncTest"},
            dataFilter: filter
        }
    };
    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        for (var i=0, l=childNodes.length; i<l; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
        }
        return childNodes;
    }
    $.fn.zTree.init($("#treeDemo1"), setting);

    $("button").on("click",function(e){
        switch ($(this).attr("data-id")){
            case "show":
                // 显示
                $('#datetimepicker').datetimepicker('show');
                break;
            case "hide":
                // 隐藏
                $('#datetimepicker').datetimepicker('hide');
                break;
            case "update":
                // 使用当前输入框中的值更新日期时间选择器。
                $('#datetimepicker').datetimepicker('update');
                break;
            case "setStart":
                // 设置限制开始时间
                $('#datetimepicker').datetimepicker('setStartDate', '2012-01-01');
                break;
            case "setEnd":
                //给日期时间选择器设置结束日期。
                $('#datetimepicker').datetimepicker('setEndDate', '2012-01-01');
                break;
            case "setStartNull":
                // 清除开始时间
                $('#datetimepicker').datetimepicker('setStartDate');
                break;
            case "setEndNull":
                // 清除结束时间
                $('#datetimepicker').datetimepicker('setEndDate');
                break;
            case "remove":
                // 删除组件
                $('#datetimepicker').datetimepicker('remove');
                break;
        }
    });

</script>
