<%@ page language="java" import="java.util.*" pageEncoding="utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Simple Map</title>
    <script type="text/javascript" src="script/minmap/init.js" ></script>
    <script type="text/javascript" src="script/index.js" ></script>
    <script type="text/javascript">
		init('<%=request.getContextPath()%>/common/gis/script/minmap','<%=basePath%>/common/gis/3.11');
        var testmap = null;
		function initmap()
		{
			var options = {
				showNavigateBar: true,
				showOverview : true,
				showScale : true,
				center: {x: 12241037.503714196, y: 4840194.519315728},
				zoomLevel:1,
				showScale : true,
				mapLoad : function(map){
					//alert(map);
				},
				mapExtentChanged: function(extent){
					//alert(extent.xMin+","+extent.yMin+","+extent.xMax+","+extent.yMax);
				},
				baseLayers:[
					{
						layerId:"layerId1",
						//url:"http://cache1.arcgisonline.cn/arcgis/rest/services/ChinaOnlineCommunity/MapServer",
						url:"http://125.70.9.194:6080/common/rest/services/MAP1230/MapServer",
						visible:true,
						type:com.hw.map.HWMapDefaultBaseLayerTypes.CUSTOM_TILED_MAP_LAYER,
						attributes: {
							fullExtent: {
								xmin: -20037508.342787,
								ymin: -20037508.342787,
								xmax: 20037508.342787,
								ymax: 20037508.342787
							},
							initExtent: {
								xmin: 11501488.165446503,
								ymin: 3695866.152885527,
								xmax: 11678516.32295504,
								ymax: 3728734.075048165
							},
							tiledInfo: {
								"rows": 256,
								"cols": 256,
								"compressionQuality": 0,
								"origin": {"x": -20037508.342787, "y": 20037508.342787},
								"spatialReference": {"wkid": 4326},
								"lods": [
									{"level": 13, "scale": 72223.819286, "resolution": 19.1092570712683},
									{"level": 14, "scale": 36111.909643, "resolution": 9.55462853563415},
									{"level": 15, "scale": 18055.954822, "resolution": 4.77731426794937},
									{"level": 16, "scale": 9027.977411, "resolution": 2.38865713397468},
									{"level": 17, "scale": 4513.988705, "resolution": 1.19432856685505}
								]
							},
							getImageFunc: function (level, row, col) {
								//return "http://mt" + (col % 4) + ".google.cn/vt/lyrs=m@226000000&hl=zh-CN&gl=cn&x=" + col + "&y=" + row + "&z=" + level + "&s=Gali";
								return "http://dev1.zthz.com:9090/eerduosi_map_data/vector/" + level + "/" +col + "/"+row+".png";
							}
						}
					}
				],
				overviewOptions:{
					layer:{
						layerId:"layerId1",
						//url:"http://cache1.arcgisonline.cn/arcgis/rest/services/ChinaOnlineCommunity/MapServer",
						//url:"http://125.70.9.194:6080/common/rest/services/MAP1230/MapServer",
						url:"http://125.70.9.194:6080/common/rest/services/MAP1230/MapServer",
						visible:true,
						type:com.hw.map.HWMapDefaultBaseLayerTypes.CUSTOM_TILED_MAP_LAYER,
						attributes:{
							fullExtent:{xmin:-20037508.342787,ymin: -20037508.342787, xmax:20037508.342787,ymax: 20037508.342787},
							initExtent:{xmin:11501488.165446503,ymin: 3695866.152885527,xmax: 11678516.32295504,ymax: 3728734.075048165},
							tiledInfo:{
								"rows": 256,
								"cols": 256,
								"compressionQuality": 0,
								"origin": { "x": -20037508.342787, "y": 20037508.342787 },
								"spatialReference": { "wkid": 4326 },
								"lods": [
									{"level": 13, "scale": 72223.819286, "resolution": 19.1092570712683},
									{"level": 14, "scale": 36111.909643, "resolution": 9.55462853563415},
									{"level": 15, "scale": 18055.954822, "resolution": 4.77731426794937},
									{"level": 16, "scale": 9027.977411, "resolution": 2.38865713397468},
									{"level": 17, "scale": 4513.988705, "resolution": 1.19432856685505}
								]
							},
							getImageFunc: function (level, row, col) {
								return "http://dev1.zthz.com:9090/eerduosi_map_data/vector/" + level + "/" +col + "/"+row+".png";
								//return "http://mt" + (col % 4) + ".google.cn/vt/lyrs=m@226000000&hl=zh-CN&gl=cn&x=" + col + "&y=" + row + "&z=" + level + "&s=Gali";
							}
						}
					}
				}
			};
			testmap = new com.hw.map.HWMap("mapContainer",options);
		}
    </script>
</head>
<body class="tundra" onload="initmap()" >
<div id="mapContainer" style="width:910px; height:610px;" ></div>

<!-- <div>
  <div data-dojo-type="dijit/layout/BorderContainer"
         data-dojo-props="design:'headline', gutters:false"
         style="width:910px; height:610px; margin:0;padding:0;">

    <div id="mapDiv"
           data-dojo-type="dijit/layout/ContentPane"
           data-dojo-props="region:'center'"
           style="padding:5;margin:5;width:900px; height:600px;">
    </div>
  </div>
</div>-->
<div>
	<input type="button" value="增加marker" onclick="addMarker();"/>
	<input type="button" value="修改marker" onclick="updateMarker();"/>
	<input type="button" value="删除marker" onclick="removeMarker();"/>
	<input type="button" value="增加polygon" onclick="addPolygon();"/>
	<input type="button" value="修改polygon" onclick="updatePolygon();"/>
	<input type="button" value="删除polygon" onclick="removePolygon();"/>
	<input type="button" value="增加polyline" onclick="addPolyline();"/>
	<input type="button" value="修改polyline" onclick="updatePolyline();"/>
	<input type="button" value="删除polyline" onclick="removePolyline();"/>
	<input type="button" value="增加text" onclick="addText();"/>
	<input type="button" value="修改text" onclick="updateText();"/>
	<input type="button" value="删除text" onclick="removeText();"/>
</div>
<div>
	<input type="button" value="清除所有标记" onclick="clearOverlays();"/>
	<input type="button" value="删除一个标记" onclick="removeOverlay();"/>
	<input type="button" value="显示信息窗口" onclick="showInfoWindow();"/>
	<input type="button" value="隐藏信息窗口" onclick="hideInfoWindow();"/>
	<input type="button" value="点和级别定位" onclick="panByPointAndLevel();"/>
	<input type="button" value="地图范围定位" onclick="panByExtent();"/>
	<input type="button" value="指定地图级别" onclick="zoomToLevel();"/>
	<input type="button" value="定位地图中心" onclick="centerAt();"/>
	<input type="button" value="切换缩略图" onclick="switchOverview();"/>
</div>
<div>
	<input type="button" value="左移" onclick="panLeft();" />
	<input type="button" value="右移" onclick="panRight();"/>
	<input type="button" value="上移" onclick="panUp();"/>
	<input type="button" value="下移" onclick="panDown();"/>
	<input type="button" value="放大" onclick="zoomIn();"/>
	<input type="button" value="缩小" onclick="zoomOut();"/>
	<input type="button" value="获取宽高" onclick="getWidthAndHeight();"/>
	<input type="button" value="获取范围" onclick="getExtent();"/>
	<input type="button" value="获取中心点" onclick="getCenter();"/>
	<input type="button" value="地图坐标转屏幕坐标" onclick="toMap();"/>
	<input type="button" value="地图坐标转屏幕坐标" onclick="toScreen();"/>
</div>
<div>
	<input type="button" value="平移" onclick="dragPan();"/>
	<input type="button" value="测距" onclick="dragMeasureLength();"/>
	<input type="button" value="侧面" onclick="dragMeasureArea();"/>
	<input type="button" value="画点" onclick="dragPoint();"/>
	<input type="button" value="画线" onclick="dragLine();"/>
	<input type="button" value="画面" onclick="dragPolygon();"/>
	<input type="button" value="画圆" onclick="dragCircle();"/>
	<input type="button" value="画长方形" onclick="dragExtent();"/>
	<input type="button" value="画箭头" onclick="dragArrow();"/>
	<input type="button" value="增加动态图层" onclick="addDynamicLayer();"/>
	<input type="button" value="删除动态图层" onclick="removeDynamicLayer();"/>
	<input type="button" value="清除测量信息" onclick="clearMeasureMessage();"/>
</div>
</body>
</html>