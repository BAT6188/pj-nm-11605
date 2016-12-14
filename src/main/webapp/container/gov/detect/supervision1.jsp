<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>三级网格化信息维护</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script src="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/jquery.ztree.all.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/slimScroll/jquery.slimscroll.js"></script>
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
            showLine: true
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

</script>
