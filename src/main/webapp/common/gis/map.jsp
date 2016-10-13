<%@ page language="java" pageEncoding="utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>HM_GIS</title>
    <script type="text/javascript" src="script/minmap/init.js" ></script>
	<script type="text/javascript">
		init('<%=request.getContextPath()%>/common/gis/script/minmap','<%=basePath%>/common/gis/3.11');
	</script>
    <script type="text/javascript" src="script/hwmapCommon.js" ></script>
</head>
<body class="tundra" onload="HwmapCommon.initmap()" style="margin: 0;">
	<div id="mapContainer"></div>
</body>
</html>