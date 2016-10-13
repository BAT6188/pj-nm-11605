com.hw.map.HWMap = function(a, b) {
    this.mapContainerId = a,
        this.mapOptions = b,
        this.__initMap(a, b)
},
    com.hw.map.HWLog = {
        log: function(a) {
            console && console.log && console.log(a)
        }
    };
var mapPrototype = com.hw.map.HWMap.prototype;
com.hw.map.HWMapEvents = {
    GRAPHIC_MOUSE_CLICK: "graphicMouseClick",
    GRAPHIC_MOUSE_OVER: "graphicMouseOver",
    GRAPHIC_MOUSE_OUT: "graphicMouseOut"
},
    com.hw.map.HWMapOperateModel = {
        MAP_OPERATION_PAN: "pan",
        MAP_OPERATION_MEASURELENGTH: "measureLength",
        MAP_OPERATION_MEASUREAREA: "measureArea",
        MAP_OPERATION_DRAWPOINT: "drawPoint",
        MAP_OPERATION_DRAWPOLYGON: "drawPolygon",
        MAP_OPERATION_DRAWPOLYLINE: "drawPolyline",
        MAP_OPERATION_DRAWCIRCLE: "drawCircle",
        MAP_OPERATION_DRAWEXTENT: "drawExtent",
        MAP_OPERATION_DRAWARROW: "drawArrow"
    },
    com.hw.map.HWMapDefaultLayerIds = {
        DEFAULT_TILED_VECTOR_LAYER_ID: "defaultTiledVectorLayerId",
        DEFAULT_TILED_IMAGE_LAYER_ID: "defaultTiledImageLayerId",
        DEFAULT_POLYGON_LAYER_ID: "defaultPolygonLayerId",
        DEFAULT_POLYLINE_LAYER_ID: "defaultPolylineLayerId",
        DEFAULT_MARKER_LAYER_ID: "defaultMarkerLayerId"
    },
    com.hw.map.HWMapDefaultBaseLayerTypes = {
        ARCGIS_SERVER_TILED_LAYER: "arcgis_server_tiled",
        CUSTOM_TILED_MAP_LAYER: "custom_tiled_map_layer",
        BAIDU_VECTOR_LAYER: "baidu_vector",
        BAIDU_IMAGE_LAYER: "baidu_image",
        BAIDU_ANNO_LAYER: "baidu_anno",
        GOOGLE_VECTOR_LAYER: "google_vector",
        GOOGLE_IMAGE_LAYER: "google_image",
        GOOGLE_ANNO_LAYER: "google_anno"
    },
    com.hw.map.HWMapDefaultLayerIds.DEFAULTIDS = [com.hw.map.HWMapDefaultLayerIds.DEFAULT_TILED_VECTOR_LAYER_ID, com.hw.map.HWMapDefaultLayerIds.DEFAULT_POLYGON_LAYER_ID, com.hw.map.HWMapDefaultLayerIds.DEFAULT_POLYLINE_LAYER_ID, com.hw.map.HWMapDefaultLayerIds.DEFAULT_MARKER_LAYER_ID],
    mapPrototype.__initMap = function() {
        alert("执行类init方法")
    },
    mapPrototype.measureTextConfig = {
        graphic: void 0,
        id: "measureTextGraphicId",
        font: "宋体",
        fontSize: 20,
        color: "#FF0000",
        opacity: .8
    },
    mapPrototype.measureGeometryConfig = {
        graphic: void 0,
        id: "measureGeometryId",
        lineWidth: 5,
        lineType: com.hw.map.HWPolyline.LINE_TYPE_DASH,
        lineColor: "#FF0000",
        lineOpacity: .5,
        fillOpacity: .1,
        fillColor: "#00FF00"
    },
    mapPrototype.DrawGeometryConfig = {
        lineWidth: 5,
        lineType: com.hw.map.HWPolyline.LINE_TYPE_DASH,
        lineColor: "#FF0000",
        lineOpacity: .5,
        fillOpacity: .1,
        fillColor: "#00FF00"
    },
    mapPrototype.overviewExtentGeometryConfig = {
        graphic: void 0,
        id: "overviewExtentGeometryId",
        lineWidth: 5,
        lineType: com.hw.map.HWPolyline.LINE_TYPE_DASH,
        lineColor: "#FF0000",
        lineOpacity: .5,
        fillOpacity: .1,
        fillColor: "#00FF00",
        expandRadio: 3
    },
    mapPrototype.addBaseLayer = function() {},
    mapPrototype.addMarker = function() {},
    mapPrototype.addPolygon = function() {},
    mapPrototype.addPolyline = function() {},
    mapPrototype.addText = function() {},
    mapPrototype.updateMarker = function() {},
    mapPrototype.updatePolyline = function() {},
    mapPrototype.updatePolygon = function() {},
    mapPrototype.updateText = function() {},
    mapPrototype.clearOverlays = function() {},
    mapPrototype.removeOverlay = function() {},
    mapPrototype.getOverlayById = function() {},
    mapPrototype.removeOverlayById = function() {},
    mapPrototype.showInfoWindow = function() {},
    mapPrototype.hideInfoWindow = function() {},
    mapPrototype.changeDragMode = function() {},
    mapPrototype.panByPointAndLevel = function() {},
    mapPrototype.panByExtent = function() {},
    mapPrototype.setExtent = function(a) {
        this.panByExtent(a.xMin, a.yMin, a.xMax, a.yMax)
    },
    mapPrototype.zoomToLevel = function() {},
    mapPrototype.centerAt = function() {},
    mapPrototype.panUp = function() {},
    mapPrototype.panDown = function() {},
    mapPrototype.panLeft = function() {},
    mapPrototype.panRight = function() {},
    mapPrototype.zoomIn = function() {},
    mapPrototype.zoomOut = function() {},
    mapPrototype.setPanZoomEnable = function() {},
    mapPrototype.getCenter = function() {},
    mapPrototype.toMap = function() {},
    mapPrototype.toScreen = function() {},
    mapPrototype.getExtent = function() {},
    mapPrototype.getLevel = function() {},
    mapPrototype.getScale = function() {},
    mapPrototype.getHeight = function() {},
    mapPrototype.getWidth = function() {},
    mapPrototype.switchOverview = function() {},
    mapPrototype.addDynamicLayer = function() {},
    mapPrototype.removeDynamicLayer = function() {},
    mapPrototype.clearMeasureMessage = function() {};