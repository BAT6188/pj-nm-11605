<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link href="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/metrStyle-cd/metroStyle.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/jquery.ztree.all.js"></script>
    <title>一张图综合检测预警</title>
</head>
<body>
<div class="content clearfix">
    <div class="wrap">
        <div class="tree-left left">
            <div class="ztree oneImageTree">
            </div>
        </div>
        <div class="main-right right">
            <iframe id="mapFrame" name="mapFrame" src="${pageContext.request.contextPath}/common/gis/map.jsp" style="overflow: hidden;" frameborder="0"></iframe>
        </div>

    </div>
</div>
<%@include file="/container/gov/composite/enterprise_plotting.jsp" %>
<script src="<%=request.getContextPath()%>/container/gov/composite/scripts/one_image.js"></script>
</body>
</html>
