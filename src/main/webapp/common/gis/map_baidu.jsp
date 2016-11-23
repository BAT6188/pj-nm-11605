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
    <script src="<%=request.getContextPath()%>/demo/easyform/js/jquery-2.1.0.min.js"></script>
    <script type="text/javascript" src="script/minmap/init.js" ></script>
	<script type="text/javascript">
		init('<%=request.getContextPath()%>/common/gis/script/minmap','http://api.map.baidu.com/api?v=2.0&ak=k7slqaBCNNoUy32wK7AFmROuqm32L87t',"baidu");
	</script>
    <script type="text/javascript" src="script/hwmapCommon_baidu.js" ></script>
</head>
<body class="tundra" onload="HwmapCommon.initmap()" style="margin: 0;">
	<div id="mapContainer"></div>
</body>
</html>