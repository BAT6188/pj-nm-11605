/**
 * 增加一个图标
 */
function addMarker(){
	var point = new com.hw.map.HWPoint(-10,-10);
	var marker = new com.hw.map.HWMarker("1",point,"images/markers/park2.png",30,30,0,0);
	testmap.addMarker(marker);
	marker.addEventListener(com.hw.map.HWMapEvents.GRAPHIC_MOUSE_CLICK,function(gra){
		alert(gra.type+" "+gra.id+" CLICK了 ！");
	});
	//marker.addEventListener(com.hw.map.HWMapEvents.GRAPHIC_MOUSE_OVER,function(gra){
	//	alert(gra.type+" "+gra.id+" OVER了 ！");
	//});
	//marker.addEventListener(com.hw.map.HWMapEvents.GRAPHIC_MOUSE_OUT,function(gra){
	//	alert(gra.type+" "+gra.id+" OUT了 ！");
	//});
	window.testMarker = marker;
};

function updateMarker(){
	if(!window.testMarker){
		alert("请先添加一个marker");
		return;
	}
	var marker = window.testMarker;
	marker.imgSrc = "images/markers/bike1.png";
	marker.point = new com.hw.map.HWPoint(-1111140,-1111140);
	testmap.updateMarker(marker);
};
function removeMarker(){
	if(!window.testMarker){
		alert("请先添加一个marker");
		return;
	}
	testmap.removeOverlay(window.testMarker);
	window.testMarker = null;
};
/**
 * 增加多边形
 */
function addPolygon(){
	var polygon = new com.hw.map.HWPolygon("2","-10,-10;-1111140,-1111140;-10,-1111140;-10,-10","#00FF00","#FF0000",2,com.hw.map.HWPolyline.LINE_TYPE_DASH,0.1,0.4);
	//var polygon = new com.hw.map.HWPolygon("-10,-10;-50,-50;-10,-50;-10,-10","#00FF00","#FF0000",2,com.hw.map.HWPolyline.LINE_TYPE_SOLID,0.5,0.4);
	testmap.addPolygon(polygon);
	polygon.addEventListener(com.hw.map.HWMapEvents.GRAPHIC_MOUSE_CLICK,function(gra){
		alert(gra.type+" "+gra.id+" 被点击了 ！");
	});
	window.testPolygon = polygon;
};
/**
 * 更新多边形
 */
function updatePolygon(){
	var polygon = window.testPolygon;
	if(!polygon){
		alert("请先添加一个多边形");
	}
	polygon.points[1].x= 1111140;
	polygon.points[1].y= 1111140;
	polygon.lineType= com.hw.map.HWPolyline.LINE_TYPE_SOLID;
	polygon.lineWeight = 20;
	polygon.fillColor = "#0000FF";
	testmap.updatePolygon(polygon);
};
function removePolygon(){
	if(!window.testPolygon){
		alert("请先添加一个Polygon");
		return;
	}
	testmap.removeOverlay(window.testPolygon);
	window.testPolygon = null;
};
/**
 * 增加折线
 */
function addPolyline(){
	var polyline = new com.hw.map.HWPolyline("3","-10,0;-1111140,-1111140;0,-1111140",20,com.hw.map.HWPolyline.LINE_TYPE_DASH,"#FF0000",0.5);
	testmap.addPolyline(polyline);
	polyline.addEventListener(com.hw.map.HWMapEvents.GRAPHIC_MOUSE_CLICK,function(gra){
		alert(gra.type+" "+gra.id+" 被点击了 ！");
	});
	window.testPolyline = polyline;
};
/**
 * 增加折线
 */
function updatePolyline(){
	var polyline = window.testPolyline;
	if(!polyline){
		alert("请先添加一条线");
	}
	polyline.points[1].x= 1111140;
	polyline.points[1].y= 1111140;
	polyline.lineType= com.hw.map.HWPolyline.LINE_TYPE_SOLID;
	polyline.weight = 10;
	polyline.color = "#0000FF";
	testmap.updatePolyline(polyline);
};
function removePolyline(){
	if(!window.testPolyline){
		alert("请先添加一个Polyline");
		return;
	}
	testmap.removeOverlay(window.testPolyline);
	window.testPolylinen = null;
};
/**
 * 增加一个图标
 */
function addText(){
	var point = new com.hw.map.HWPoint(-10,-10);
	var text = new com.hw.map.HWText("4",point,"测试文字，看看是否正常",20,"宋体","#FF0000",0.5,0,-35);
	testmap.addText(text);
	text.addEventListener(com.hw.map.HWMapEvents.GRAPHIC_MOUSE_CLICK,function(gra){
		alert(gra.type+" "+gra.id+" 被点击了 ！");
	});
	window.testText = text;
}
/**
 * 增加一个图标
 */
function updateText(){
	var text = window.testText;
	if(!text){
		alert("请先添加文字");
	}
	text.point.x = 1111140;
	text.point.y = 1111140;
	text.font = "黑体";
	text.fontSize = 40;
	text.text = "修改后的文本";
	text.opercity = 1;
	text.yOffset = -70;
	testmap.updateText(text);
}
function removeText(){
	if(!window.testText){
		alert("请先添加一个Text");
		return;
	}
	testmap.removeOverlay(window.testText);
	window.testText = null;
};

/**
 * 清除所有标记
 * @returns
 */
function clearOverlays(){
	testmap.clearOverlays();
};

/**
 * 删除一个标记
 * @returns
 */
function removeOverlay(){
	if(!window.testMarker){
		alert("请先添加一个marker");
		return;
	}
	var marker = window.testMarker;
	testmap.removeOverlay(marker);
};
/**
 * 显示信息窗口
 * @returns
 */
function showInfoWindow(){
	testmap.showInfoWindow(0,0,200,200,"<div style='margin:0px;padding: 0px;width:200px;height: 200px;background-color: #4f87bf;'>asdf<br>asdf<br>asdf<br>asdf<br>asdf<br><h1>adfa</h1>aaa<br>aaabb</div>","测试");
};
/**
 * 隐藏信息窗口
 * @returns
 */
function hideInfoWindow(){
	testmap.hideInfoWindow();
};
/**
 * 点和级别定位
 * @returns
 */
function panByPointAndLevel(){
	testmap.panByPointAndLevel(120,31,12);
};
/**
 * 地图范围定位
 * @returns
 */
function panByExtent(){
	testmap.panByExtent(-40,-20,40,20);
};
/**
 * 指定地图级别
 * @returns
 */
function zoomToLevel(){
	testmap.zoomToLevel(12);
};
/**
 * 定位地图中心
 * @returns
 */
function centerAt(){
	testmap.centerAt(0,0);
};

function switchOverview(){
	testmap.switchOverview();
}

/**
 * 左移
 * @returns
 */
function panLeft(){
	testmap.panLeft();
};
/**
 * 右移
 * @returns
 */
function panRight(){
	testmap.panRight();
};
/**
 * 上移
 * @returns
 */
function panUp(){
	testmap.panUp();
};
/**
 * 下移
 * @returns
 */
function panDown(){
	testmap.panDown();
};
/**
 * 放大
 * @returns
 */
function zoomIn(){
	testmap.zoomIn();
};
/**
 * 缩小
 * @returns
 */
function zoomOut(){
	testmap.zoomOut();
};
/**
 * 获取宽高
 * @returns
 */
function getWidthAndHeight(){
	alert("height:"+testmap.getHeight()+ "   width:"+testmap.getWidth());
};
/**
 * 获取范围
 * @returns
 */
function getExtent(){
	var extent = testmap.getExtent();
	alert("xmin:"+extent.xMin+" ymin:"+extent.yMin+" xmax:"+extent.xMax+" ymax:"+extent.yMax);
};
/**
 * 获取中心点
 * @returns
 */
function getCenter(){
	var center = testmap.getCenter();
	alert("x:"+center.x+"  y:"+center.y);
};
/**
 * 屏幕坐标转地图坐标
 * @returns
 */
function toMap(){
	var point = testmap.toMap(0,0);
	alert("x:"+point.x+"  y:"+point.y);
};
/**
 * 地图坐标转屏幕坐标
 * @returns
 */
function toScreen(){
	var mapPoint = testmap.toMap(0,0);
	var point = testmap.toScreen(mapPoint.x,mapPoint.y);
	alert("x:"+point.x+"  y:"+point.y);
};


/**
 * 平移
 * @returns
 */
function dragPan(){
	testmap.changeDragMode(com.hw.map.HWMapOperateModel.PAN);
};
/**
 * 测距
 * @returns
 */
function dragMeasureLength(){
	testmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASURELENGTH,function(geometry){
		alert(geometry.length);
	});
};
/**
 * 侧面
 * @returns
 */
function dragMeasureArea(){
	testmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASUREAREA,function(geometry){
		alert(geometry.length);
	});
};
/**
 * 画点
 * @returns
 */
function dragPoint(){
	testmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOINT,function(geometry){
		alert("x:"+geometry.x+"  y:"+geometry.y);
	});
};
/**
 * 画线
 * @returns
 */
function dragLine(){
	testmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOLYLINE,function(geometry){
		alert(geometry.length);
	});
};
/**
 * 画面
 * @returns
 */
function dragPolygon(){
	testmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOLYGON,function(geometry){
		alert(geometry.length);
	});
};
/**
 * 画圆
 * @returns
 */
function dragCircle(){
	testmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWCIRCLE,function(geometry){
		alert(geometry.length);
	});
};
/**
 * 画长方形
 * @returns
 */
function dragExtent(){
	testmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWEXTENT,function(geometry){
		alert("xmin:"+geometry.xMin+" ymin:"+geometry.yMin+" xmax:"+geometry.xMax+" ymax:"+geometry.yMax);
	});
};
/**
 * 画箭头
 * @returns
 */
function dragArrow(){
	testmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWARROW,function(geometry){
		alert(geometry.length);
	});
};
/**
 * 添加动态图层
 * @returns
 */
function addDynamicLayer(){
	testmap.addDynamicLayer("dynamicLayer1",function (xmin,ymin,xmax,ymax,width,height){
		var leftbottom = com.hw.map.utils.MapTools.metersToLonLat(xmin, ymin);
		var rightTop = com.hw.map.utils.MapTools.metersToLonLat(xmax, ymax);
		return "http://sampleserver1.arcgisonline.com/ArcGIS/rest/services/Demographics/ESRI_Population_World/MapServer/export?dpi=96&transparent=true&format=jpeg&bbox="+
		leftbottom.x+"%2C"+leftbottom.y+"%2C"+rightTop.x+"%2C"+rightTop.y+"&bboxSR=4326&imageSR=4326&size="+width+"%2C"+height+"&f=image";
	});
};
/**
 * 移除动态图层
 */
function removeDynamicLayer(){
	testmap.removeDynamicLayer("dynamicLayer1");
}

/**
 * 删除动态图层
 * @returns
 */
function clearMeasureMessage(){
	testmap.clearMeasureMessage();
};