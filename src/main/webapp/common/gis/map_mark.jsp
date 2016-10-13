<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8">
    <title>通用标绘页面</title>
    <jsp:include page="/common/common_include.jsp" flush="true"/>
    <%
        String width = request.getParameter("width");
        String height = request.getParameter("height");
    %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/contents/sandboxie/styles/sandboxie.css" type="text/css">
    <script type="text/javascript" src="script/map_mark.js"></script>
</head>
<body style="overflow: hidden;">
    <iframe id="mapFrame" name="mapFrame" src="${pageContext.request.contextPath}/contents/gis/map.jsp" style="overflow: hidden;width: <%=width%>px;height: <%=height%>px;" frameborder="0"></iframe>
    <div class="navbar" style="display:block;">
        <ul>
            <li><span class="point"></span></li>
            <li><span class="polyline"></span></li>
            <li><span class="polygon"></span></li>
        </ul>
    </div>
</body>
</html>
