var HwmapCommon;
HwmapCommon = {
    hwmap: undefined,

    options: {
        mapContainerId: "mapContainer",
        showNavigateBar: true,
        showOverview: true,
        showScale: true,
        center: {x:110,y:39.8},
        zoomLevel: 4,
        mapLoad: function (map) {
            //alert(map);
        },
        mapExtentChanged: function (extent) {
            //alert(extent.xMin+","+extent.yMin+","+extent.xMax+","+extent.yMax);
        },
        baseLayers: [
            {
                layerId: "layerId1",
                //url:"http://cache1.arcgisonline.cn/arcgis/rest/services/ChinaOnlineCommunity/MapServer",
                url: "http://125.70.9.194:6080/common/rest/services/MAP1230/MapServer",
                visible: true,
                type: com.hw.map.HWMapDefaultBaseLayerTypes.CUSTOM_TILED_MAP_LAYER,
                attributes: {
                    fullExtent: {
                        xmin: 109.13633772570552,
                        ymin: 39.51218267628951,
                        xmax: 110.38671501398679,
                        ymax: 39.97903171404245
                    },
                    initExtent: {
                        xmin: 109.15619793210595,
                        ymin: 39.82410961737357,
                        xmax: 110.22981073793659,
                        ymax: 40.279538453889494
                    },
                    tiledInfo: {
                        "rows": 256,
                        "cols": 256,
                        "compressionQuality": 0,
                        "origin": {"x": -400, "y": 400},
                        "spatialReference": {"wkid": 4490},
                        "lods": [
                            {"level": 0, "scale": 2308574, "resolution": 0.005493161812073633},
                            {"level": 1, "scale": 1154287, "resolution": 0.0027465809060368165},
                            {"level": 2, "scale": 577143, "resolution": 0.0013732892632879053},
                            {"level": 3, "scale": 288571, "resolution": 6.866434419134498E-4},
                            {"level": 4, "scale": 144285, "resolution": 3.43320531226222E-4},
                            {"level": 5, "scale": 72142, "resolution": 1.716590758826081E-4},
                            {"level": 6, "scale": 36071, "resolution": 8.582953794130404E-5},
                            {"level": 7, "scale": 18035, "resolution": 4.2913579240149105E-5},
                            {"level": 8, "scale": 9017, "resolution": 2.145559988957164E-5},
                            {"level": 9, "scale": 4508, "resolution": 1.0726610214282904E-5}
                        ]
                    },
                    getImageFunc: function (level, row, col) {
                        //token=" + token.getToken() + "&appCode=" + token.getAppCode() + + "&timestamp=" + token.getTimestamp();
                        //serviceCode=").append(this.BASEMAP).append("&mapName=").append(mapName).append("&level=").append(level).append("&col=").append(col).append("&row=")
                        return "http://172.17.29.42:8080/dsgissrv/rest?token="+token+"&appCode="+appCode+"&timestamp="+timestamp+"&serviceCode=basemap&mapName=dsmap&level="+level+"&row="+row+"&col="+col;
                    }
                }
            },
            {
                layerId: "layerId2",
                //url:"http://cache1.arcgisonline.cn/arcgis/rest/services/ChinaOnlineCommunity/MapServer",
                url: "http://125.70.9.194:6080/common/rest/services/MAP1230/MapServer",
                visible: false,
                type: com.hw.map.HWMapDefaultBaseLayerTypes.CUSTOM_TILED_MAP_LAYER,
                attributes: {
                    fullExtent: {
                        xmin: 109.13633772570552,
                        ymin: 39.51218267628951,
                        xmax: 110.38671501398679,
                        ymax: 39.97903171404245
                    },
                    initExtent: {
                        xmin: 109.15619793210595,
                        ymin: 39.82410961737357,
                        xmax: 110.22981073793659,
                        ymax: 40.279538453889494
                    },
                    tiledInfo: {
                        "rows": 256,
                        "cols": 256,
                        "compressionQuality": 0,
                        "origin": {"x": -400, "y": 400},
                        "spatialReference": {"wkid": 4490},
                        "lods": [
                            {"level": 0, "scale": 2308574, "resolution": 0.005493161812073633},
                            {"level": 1, "scale": 1154287, "resolution": 0.0027465809060368165},
                            {"level": 2, "scale": 577143, "resolution": 0.0013732892632879053},
                            {"level": 3, "scale": 288571, "resolution": 6.866434419134498E-4},
                            {"level": 4, "scale": 144285, "resolution": 3.43320531226222E-4},
                            {"level": 5, "scale": 72142, "resolution": 1.716590758826081E-4},
                            {"level": 6, "scale": 36071, "resolution": 8.582953794130404E-5},
                            {"level": 7, "scale": 18035, "resolution": 4.2913579240149105E-5},
                            {"level": 8, "scale": 9017, "resolution": 2.145559988957164E-5},
                            {"level": 9, "scale": 4508, "resolution": 1.0726610214282904E-5}
                        ]
                    },
                    getImageFunc: function (level, row, col) {
                        //token=" + token.getToken() + "&appCode=" + token.getAppCode() + + "&timestamp=" + token.getTimestamp();
                        //serviceCode=").append(this.BASEMAP).append("&mapName=").append(mapName).append("&level=").append(level).append("&col=").append(col).append("&row=")
                        return "http://172.17.29.42:8080/dsgissrv/rest?token="+token+"&appCode="+appCode+"&timestamp="+timestamp+"&serviceCode=basemap&mapName=dsimagemap&level="+level+"&row="+row+"&col="+col;
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
                attributes: {
                    fullExtent: {
                        xmin: 109.13633772570552,
                        ymin: 39.51218267628951,
                        xmax: 110.38671501398679,
                        ymax: 39.97903171404245
                    },
                    initExtent: {
                        xmin: 109.15619793210595,
                        ymin: 39.82410961737357,
                        xmax: 110.22981073793659,
                        ymax: 40.279538453889494
                    },
                    tiledInfo: {
                        "rows": 256,
                        "cols": 256,
                        "compressionQuality": 0,
                        "origin": {"x": -400, "y": 400},
                        "spatialReference": {"wkid": 4490},
                        "lods": [
                            {"level": 0, "scale": 2308574, "resolution": 0.005493161812073633},
                            {"level": 1, "scale": 1154287, "resolution": 0.0027465809060368165},
                            {"level": 2, "scale": 577143, "resolution": 0.0013732892632879053},
                            {"level": 3, "scale": 288571, "resolution": 6.866434419134498E-4},
                            {"level": 4, "scale": 144285, "resolution": 3.43320531226222E-4},
                            {"level": 5, "scale": 72142, "resolution": 1.716590758826081E-4},
                            {"level": 6, "scale": 36071, "resolution": 8.582953794130404E-5},
                            {"level": 7, "scale": 18035, "resolution": 4.2913579240149105E-5},
                            {"level": 8, "scale": 9017, "resolution": 2.145559988957164E-5},
                            {"level": 9, "scale": 4508, "resolution": 1.0726610214282904E-5}
                        ]
                    },
                    getImageFunc: function (level, row, col) {
                        return "http://172.17.29.42:8080/dsgissrv/rest?token="+token+"&appCode="+appCode+"&timestamp="+timestamp+"&serviceCode=basemap&mapName=dsmap&level="+level+"&row="+row+"&col="+col;
                    }
                }
            },
            layer:{
                layerId:"layerId2",
                //url:"http://cache1.arcgisonline.cn/arcgis/rest/services/ChinaOnlineCommunity/MapServer",
                //url:"http://125.70.9.194:6080/common/rest/services/MAP1230/MapServer",
                url:"http://125.70.9.194:6080/common/rest/services/MAP1230/MapServer",
                visible:false,
                type:com.hw.map.HWMapDefaultBaseLayerTypes.CUSTOM_TILED_MAP_LAYER,
                attributes: {
                    fullExtent: {
                        xmin: 109.13633772570552,
                        ymin: 39.51218267628951,
                        xmax: 110.38671501398679,
                        ymax: 39.97903171404245
                    },
                    initExtent: {
                        xmin: 109.15619793210595,
                        ymin: 39.82410961737357,
                        xmax: 110.22981073793659,
                        ymax: 40.279538453889494
                    },
                    tiledInfo: {
                        "rows": 256,
                        "cols": 256,
                        "compressionQuality": 0,
                        "origin": {"x": -400, "y": 400},
                        "spatialReference": {"wkid": 4490},
                        "lods": [
                            {"level": 0, "scale": 2308574, "resolution": 0.005493161812073633},
                            {"level": 1, "scale": 1154287, "resolution": 0.0027465809060368165},
                            {"level": 2, "scale": 577143, "resolution": 0.0013732892632879053},
                            {"level": 3, "scale": 288571, "resolution": 6.866434419134498E-4},
                            {"level": 4, "scale": 144285, "resolution": 3.43320531226222E-4},
                            {"level": 5, "scale": 72142, "resolution": 1.716590758826081E-4},
                            {"level": 6, "scale": 36071, "resolution": 8.582953794130404E-5},
                            {"level": 7, "scale": 18035, "resolution": 4.2913579240149105E-5},
                            {"level": 8, "scale": 9017, "resolution": 2.145559988957164E-5},
                            {"level": 9, "scale": 4508, "resolution": 1.0726610214282904E-5}
                        ]
                    },
                    getImageFunc: function (level, row, col) {
                        return "http://172.17.29.42:8080/dsgissrv/rest?token="+token+"&appCode="+appCode+"&timestamp="+timestamp+"&serviceCode=basemap&mapName=dsimagemap&level="+level+"&row="+row+"&col="+col;
                    }
                }
            }
        }
    },
    initmap: function () {
        //设置mapContainer 全屏
        var mapContainer = document.getElementById(this.options.mapContainerId);
        if (window.initMap) {
            window.initMap(this, mapContainer);
        }
        //如果没有设置container大小，设置为窗口大小
        if (!mapContainer.style.width) {
            mapContainer.style.width = (window.innerWidth) + "px";
            mapContainer.style.height = (window.innerHeight) + "px";
        }

        this.hwmap = new com.hw.map.HWMap(this.options.mapContainerId, this.options);
        this.MapTools = com.hw.map.utils.MapTools;
        //添加获取多边形中心点方法
        this.MapTools.getPolygonCenter = function (points) {
            var len = points.length;
            var xSum = 0;
            var ySum = 0;
            var count = 0;
            for (var i = 0; i < len; i++) {
                var point = points[i];
                if (point.x && point.y) {
                    count++;
                    xSum += point.x;
                    ySum += point.y;
                }
            }
            return {x: xSum / count, y: ySum / count};
        };
        /**
         * 字符转坐标对象
         * @param str 坐标之间用;分隔，经纬度之间用,分隔
         * @returns {Array}
         */
        this.MapTools.strToPoints = function (str) {

            if (!str || str.indexOf(",") <= 0 || str.indexOf(";") <= 0) {
                return;
            }
            var points = [];
            var pointStrArr = str.split(";");
            for(var i = 0; i < pointStrArr.length; i++) {
                var xyStr = pointStrArr[i];
                if (xyStr && xyStr.indexOf(",") > 0) {
                    var xy = xyStr.split(",");
                    points.push({x: parseFloat(xy[0]), y: parseFloat(xy[1])});
                }
            }
            return points;
        };
        //添加默认图层
        this.addLayer(this.DEFALUT_LAYER_ID);

        if (window.initMapFinish) {
            window.initMapFinish(this, this.hwmap);
        }
        if (window.initFinishEvents && window.initFinishEvents.length > 0) {
            for (var i = 0; i < window.initFinishEvents.length; i++) {
                var finish = window.initFinishEvents[i];
                finish(this, this.hwmap);
            }
        }
    },
    LINE_TYPE_DASH: "dash",
    LINE_TYPE_SOLID: "solid",
    OPTIONS_DEF_MARKER: {
        id: "",
        image: "images/markers/park2.png",
        width: 30,
        height: 30,
        xOffset: 0,
        yOffset: 0
    },
    OPTIONS_DEF_POLYGON: {
        id: "",
        fillColor: "#00FF00",
        lineColor: "#FF0000",
        lineWeight: 2,
        lineType: "solid",
        lineOpacity: 0.7,
        opacity: 0.4
    },
    OPTIONS_DEF_POLYLINE: {
        id: "",
        weight: 2,
        lineType: "solid",
        color: "#FF0000",
        opacity: 0.5
    },
    OVERLAY_TYPE_HWMARKER: "com.hw.map.HWMarker",
    OVERLAY_TYPE_HWPOLYLINE: "com.hw.map.HWPolyline",
    OVERLAY_TYPE_HWPOLYGON: "com.hw.map.HWPolygon",
    DEFALUT_LAYER_ID: "default_layer_id",
    //_idMapOverlay:{},
    _idMapLayer: {},
    /**
     * 添加图层
     * @param layerId
     */
    addLayer: function (layerId) {
        if (!this._idMapLayer[layerId]) {
            this._idMapLayer[layerId] = {id: layerId};
        }
    },
    /**
     * 获取图层
     * @param layerId
     * @returns {*}
     */
    getLayer: function (layerId) {
        return this._idMapLayer[layerId];
    },
    /**
     * 清除图层
     * @param layerId
     */
    clearLayer: function (layerId) {
        for (var id in this._idMapLayer[layerId]) {
            this.removeOverlay(id);
        }
    },
    /**
     * 删除图层
     * @param layerId
     */
    removeLayer: function (layerId) {
        this.clearLayer(layerId);
        delete this._idMapLayer[layerId];
    },
    /**
     * 添加标绘信息到缓存
     * @param overlay
     * @param layerId
     * @private
     */
    _addOverlay: function (overlay, layerId) {
        layerId = layerId ? layerId : this.DEFALUT_LAYER_ID;
        if (!this._idMapLayer[layerId]) this.addLayer(layerId);
        overlay.layerId = layerId;
        this._idMapLayer[layerId][overlay.id] = overlay;
    },
    /**
     * 添加标绘
     * @param overlay
     */
    addOverlay: function (overlay) {
        var type = overlay.type;
        if (type == this.OVERLAY_TYPE_HWMARKER) {
            this.addMarker(overlay);
        } else if (type == this.OVERLAY_TYPE_HWPOLYLINE) {
            this.addPolyline(overlay);
        } else if (type == this.OVERLAY_TYPE_HWPOLYGON) {
            this.addPolygon(overlay);
        } else {
            console.dir("添加未知类型：" + type);
        }
    },
    /**
     * 更新标绘
     * @param overlay
     */
    updateOverlay: function (overlay) {
        var type = overlay.type;
        if (type == this.OVERLAY_TYPE_HWMARKER) {
            this.updateMarker(overlay);
        } else if (type == this.OVERLAY_TYPE_HWPOLYLINE) {
            this.updatePolyline(overlay);
        } else if (type == this.OVERLAY_TYPE_HWPOLYGON) {
            this.updatePolygon(overlay);
        } else {
            console.dir("添加未知类型：" + type);
        }
    },
    /**
     * 删除标绘
     * @param overlayId
     * @returns {boolean}
     */
    removeOverlay: function (overlayId) {
        var overlay = this.getOverlay(overlayId);
        if (!overlayId || !overlay) {
            return false;
        }
        this.hwmap.removeOverlay(overlay);
        delete this._idMapLayer[overlay.layerId][overlayId];

    },
    /**
     * 获取标绘
     * @param overlayId
     * @param layerId
     * @returns {*}
     * @private
     */
    _getOverlay: function (overlayId, layerId) {
        if (layerId) {
            return this._idMapLayer[layerId][overlayId];
        }
        for (var layerId in this._idMapLayer) {
            var layer = this.getLayer(layerId);
            for (var key in layer) {
                if (key == overlayId) {
                    return layer[key];
                }
            }
        }
    },
    /**
     *获取标绘
     * @param overlayId
     * @param flat 是否只返回属性
     * @returns {*}
     */
    getOverlay: function (overlayId, flat, layerId) {
        var overlay = this._getOverlay(overlayId, layerId);
        if (!overlay || typeof(overlay) != "object") return;
        if (!flat)  return overlay;
        var flatOverlay = {};
        for (var key in overlay) {
            var type = typeof(overlay[key]);
            if ((key == "point" || key == "points") || (key == 'data') || (type != "object" && type != "function")) {
                flatOverlay[key] = overlay[key];
            }
        }
        return flatOverlay;

    },
    getOverlays: function (layerId,flat) {
        var overlys = [];
        if (layerId) {
            var layer = this.getLayer(layerId);
            for (var key in layer) {
                var o = this.getOverlay(key, flat);
                if (o) {
                    overlys.push(this.getOverlay(key, flat));
                }
            }
        }else{
            for (var layerId in this._idMapLayer) {
                var o = this.getOverlays(layerId, flat);
                if (o) {
                    overlys.push(o);
                }
            }
        }
        return overlys;
    },
    clear:function () {
        this.clearOverlays();
        this.hideInfoWindow();
    },
    /**
     * 清除所有标记
     * @returns
     */
    clearOverlays: function () {
        this._idMapLayer = {};
        this.hwmap.clearOverlays();
    },
    _createPoint: function (x, y) {
        return new com.hw.map.HWPoint(x, y);
    },
    /**
     * 合并对象
     * @param des
     * @param src
     * @param override
     * @returns {*}
     */
    extend: function () {
        var target = arguments[0] || {};
        for (var i = 1; i < arguments.length; i++) {
            if (typeof arguments[i] == "object") {
                for (var name in arguments[i]) {
                    target[name] = arguments[i][name];
                }
            }
        }
        return target;
    },
    /**
     * 验证坐标
     * @param x
     * @param y
     * @returns {boolean}
     */
    verifyCoor: function (x, y) {
        return !(!x || !y);
    },
    /**
     * 添加图标
     * @param options
     * id,
     * x,
     * y,
     * image,
     * width,
     * height,
     * xOffset,
     * yOffset,
     * callback,
     * click,
     * over
     * @returns {com.hw.map.HWMarker}
     */
    addMarker: function (options, layerId) {
        if (!options.point && !this.verifyCoor(options.x, options.y)) {
            return;
        }
        var opt = this.extend({}, this.OPTIONS_DEF_MARKER, options);
        if (!opt.id) {
            opt.id = "marker_" + (new Date()).getTime();
        }
        if (!opt.point) {
            opt.point = this._createPoint(opt.x, opt.y);
        }
        var marker = new com.hw.map.HWMarker(opt.id, opt.point, opt.image, opt.width, opt.height, opt.xOffset, opt.yOffset);
        this.hwmap.addMarker(marker);
        if ("function" == typeof(options.click)) {
            marker.addEventListener(com.hw.map.HWMapEvents.GRAPHIC_MOUSE_CLICK, opt.click);
            /*marker.addEventListener(com.hw.map.HWMapEvents.GRAPHIC_MOUSE_OVER,function(gra){
             alert(gra.type+" "+gra.id+" OVER了 ！");
             });*/
            //marker.addEventListener(com.hw.map.HWMapEvents.GRAPHIC_MOUSE_OUT,function(gra){
            //	alert(gra.type+" "+gra.id+" OUT了 ！");
            //});
        }

        //复制自定义配置属性
        for(var field in opt){
            if (marker[field]) {
                continue;
            }
            marker[field] = opt[field];
        }
        this._addOverlay(marker, layerId);
        if (options.callback) options.callback(marker);
        return marker;
    },
    /**
     * 更新图标
     * @param options
     * id,
     * image,
     * width,
     * height,
     * x,
     * y,
     * @returns {com.hw.map.HWMarker}
     */
    updateMarker: function (options, layerId) {
        var marker = this.getOverlay(options.id);
        if (!options.id || !marker) {
            return null;
        }
        this.extend(marker, options);
        if (this.verifyCoor(options.x, options.y)) {
            marker.point.x = options.x;
            marker.point.y = options.y;
        }
        this.hwmap.updateMarker(marker);
        this._addOverlay(marker, layerId);
        marker.addEventListener(com.hw.map.HWMapEvents.GRAPHIC_MOUSE_CLICK, options.click);
        return marker;
    },
    /**
     * 增加多边形
     * @param opt
     * id,
     * points,
     * data,
     * fillColor,
     * lineColor,
     * lineWeight,
     * lineType:dash,solid,
     * lineOpacity,
     * opacity,
     * callback,
     * click,
     * return HWPolygon
     */
    addPolygon: function (options, layerId) {
        var opt = this.extend({}, this.OPTIONS_DEF_POLYGON, options);
        var polygon = new com.hw.map.HWPolygon(opt.id, opt.points, opt.fillColor, opt.lineColor,
            opt.lineWeight, opt.lineType, opt.lineOpacity, opt.opacity);
        this.hwmap.addPolygon(polygon);
        polygon.addEventListener(com.hw.map.HWMapEvents.GRAPHIC_MOUSE_CLICK, opt.click);
        polygon.data = opt.data;
        this._addOverlay(polygon, layerId);
        if (options.callback) options.callback(polygon);
        return polygon;
    },
    /**
     * 更新多边形
     * @param options
     * id,
     * points,
     * lineType:dash,solid,
     * lineWeight,
     * fillColor,
     */
    updatePolygon: function (options, layerId) {
        var polygon = this.getOverlay(options.id);
        if (!options.id || !polygon) {
            return false;
        }
        this.extend(polygon, options);
        if (options.points) {
            polygon.points = "string" == typeof polygon.points ? com.hw.map.GeometryUtil.pointsString2Points(polygon.points) : polygon.points;
        }
        this.hwmap.updatePolygon(polygon);
        this._addOverlay(polygon, layerId);
        polygon.addEventListener(com.hw.map.HWMapEvents.GRAPHIC_MOUSE_CLICK, options.click);
        return polygon;
    },
    /**
     * 增加线
     * @param opt
     * id,
     * points,
     * lineWeight,
     * lineType:dash,solid,
     * lineColor,
     * lineOpacity,
     * callback,
     * click
     */
    addPolyline: function (options, layerId) {
        var opt = this.extend({}, this.OPTIONS_DEF_POLYGON, options);
        var polyline = new com.hw.map.HWPolyline(opt.id, opt.points, opt.lineWeight, opt.lineType, opt.lineColor, opt.lineOpacity);
        this.hwmap.addPolyline(polyline);
        polyline.addEventListener(com.hw.map.HWMapEvents.GRAPHIC_MOUSE_CLICK, opt.click);
        polyline.data = opt.data;
        this._addOverlay(polyline, layerId);
        if (options.callback) options.callback(polyline);
        return polyline;
    },
    /**
     * 增加折线
     * @param options
     * id
     * points
     * lineType
     * weight
     * color,
     * callback
     */
    updatePolyline: function (options, layerId) {
        var polyline = this.getOverlay(options.id);
        if (!options.id || !polyline) {
            return false;
        }
        this.extend(polyline, options);
        if (polyline.points) {
            polyline.points = "string" == typeof polyline.points ? com.hw.map.GeometryUtil.pointsString2Points(polyline.points) : polyline.points;
        }
        this.hwmap.updatePolyline(polyline);
        this._addOverlay(polyline, layerId);
        if (options.callback) options.callback(polyline);
    },
    /**
     * 增加一个文字
     * @param options
     * id
     * x,
     * y,
     * text,
     * fontSize,
     * fontName,
     * color,
     * opacity,
     * xOffset
     * yOffset
     * click
     */
    addText: function (options, layerId) {
        var point = this._createPoint(options.x, options.y);
        var text = new com.hw.map.HWText(options.id, point, options.text, options.fontSize, options.fontName, options.color, options.opacity, options.xOffset, options.yOffset);
        this.hwmap.addText(text);
        text.addEventListener(com.hw.map.HWMapEvents.GRAPHIC_MOUSE_CLICK, options.click);
        this._addOverlay(text, layerId);

    },
    /**
     * 增加一个图标
     * @param options
     * id,
     * x,
     * y,
     * font,
     * fontSize
     * text,
     * opercity,
     * xOffset
     * yOffset
     */
    updateText: function (options, layerId) {
        var text = this.getOverlay(options.id);
        if (!options.id || !text) {
            return false;
        }
        this.extend(text, options);
        if (this.verifyCoor(options.x, options.y)) {
            text.point.x = options.x;
            text.point.y = options.y;
        }
        this.hwmap.updateText(text);
        this._addOverlay(text, layerId);
    },

    /**
     * 显示信息窗口
     * @options
     * x
     * y
     * width,
     * height,
     * html
     * title
     * @returns infoWindowDOM
     */
    showInfoWindow: function (options) {
        this.hwmap.showInfoWindow(options.x, options.y, options.width, options.height, options.html, options.title);
        return this._getInfoWindowDOM();
    },
    /**
     * 获取信息显示窗口dom
     * @returns {*}
     */
    _getInfoWindowDOM: function () {
        return this.hwmap.infoWindow.domNode;
    },
    /**
     * 隐藏信息窗口
     * @returns
     */
    hideInfoWindow: function () {
        this.hwmap.hideInfoWindow();
    },
    /**
     * 点和级别定位
     * @returns
     */
    panByPointAndLevel: function (x, y, level) {
        this.hwmap.panByPointAndLevel(x, y, level);
    },
    /**
     * 地图范围定位
     * @returns
     */
    panByExtent: function (ax, ay, bx, by) {
        this.hwmap.panByExtent(ax, ay, bx, by);
    },
    /**
     * 指定地图级别
     * @returns
     */
    zoomToLevel: function (level) {
        this.hwmap.zoomToLevel(level);
    },
    /**
     * 定位地图中心
     * @returns
     */
    centerAt: function (x, y) {
        this.hwmap.centerAt(x, y);
    },

    /**
     * 切换缩略图
     */
    switchOverview: function () {
        this.hwmap.switchOverview();
    },

    /**
     * 左移
     * @returns
     */
    panLeft: function () {
        this.hwmap.panLeft();
    },
    /**
     * 右移
     * @returns
     */
    panRight: function () {
        this.hwmap.panRight();
    },
    /**
     * 上移
     * @returns
     */
    panUp: function () {
        this.hwmap.panUp();
    },
    /**
     * 下移
     * @returns
     */
    panDown: function () {
        this.hwmap.panDown();
    },
    /**
     * 放大
     * @returns
     */
    zoomIn: function () {
        this.hwmap.zoomIn();
    },
    /**
     * 缩小
     * @returns
     */
    zoomOut: function () {
        this.hwmap.zoomOut();
    },
    /**
     * 宽度
     */
    getWidth: function () {
        return this.hwmap.getWidth();
    },
    /**
     * 高度
     */
    getHeight: function () {
        return this.hwmap.getHeight();
    },
    /**
     * 获取范围
     * @returns {xMin,yMin,xMax,yMax}
     */
    getExtent: function () {
        return this.hwmap.getExtent();
    },
    /**
     * 获取中心点
     * @returns {x,y}
     */
    getCenter: function () {
        return this.hwmap.getCenter();
    },
    /**
     * 屏幕坐标转地图坐标
     * @param pixelX
     * @param pixelY
     * @returns {x,y}
     */
    toMap: function (pixelX, pixelY) {
        return this.hwmap.toMap(pixelX, pixelY);
    },
    /**
     * 地图坐标转屏幕坐标
     * @param x
     * @param y
     * @returns screen:{x,y}
     */
    toScreen: function (x, y) {
        return this.hwmap.toScreen(x, y);
    },


    /**
     * 平移
     * @returns
     */
    pan: function () {
        this.hwmap.changeDragMode(com.hw.map.HWMapOperateModel.PAN);
    },
    /**
     * 测距
     * @returns
     */
    measureLength: function (callback) {
        this.hwmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASURELENGTH, function (geometry) {
            if (callback) callback(geometry);
        });
    },
    /**
     * 测面
     * @returns
     */
    measureArea: function (callback) {
        this.hwmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASUREAREA, function (geometry) {
            callback(geometry);
        });
    },
    /**
     * 画点
     * @returns
     */
    preDragPoint: function (callback) {
        this.hwmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOINT, function (geometry) {
            if ("function" == typeof(callback)) {
                callback(geometry);
            }
        });
    },
    /**
     * 添加图标
     * @param options
     * id,
     * imgSrc,
     * width,
     * height,
     * xOffset,
     * yOffset,
     * autoExit,
     * before,
     * after,
     * callback,
     * click,
     * over
     */
    dragPoint: function (options, layerId) {
        var that = this;
        that.preDragPoint(function (geometry) {
            if (options.before) options.before();
            options.x = geometry.x;
            options.y = geometry.y;
            var marker = that.addMarker(options, layerId);
            if (!(options.autoExit === false)) {
                that.pan();
            }
            if (options.after) options.after(marker);
        })
    },
    /**
     * 画线
     * @returns
     */
    preDragLine: function (callback) {
        this.hwmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOLYLINE, function (geometry) {
            callback(geometry);
        });
    },
    /**
     * 画线
     * @param options
     * id,
     * points,
     * weight,
     * lineType:dash,solid,
     * color,
     * opacity,
     * autoExit,
     * before,
     * after,
     * callback,
     * click
     */
    dragLine: function (options, layerId) {
        var that = this;
        this.preDragLine(function (geometry) {
            if (options.before) options.before();
            options.points = geometry;
            var polyline = that.addPolyline(options, layerId);
            if (!(options.autoExit === false)) {
                that.pan();
            }
            if (options.after) options.after(polyline);
        })

    },

    /**
     * 画图形
     * @param options
     * type:图形类型
     * id,
     * points,
     * fillColor,
     * lineColor,
     * lineWeight,
     * lineType:dash,solid,
     * lineOpacity,
     * opacity,
     * click,
     */
    drwg: function (options, layerId) {
        this.hwmap.changeDragMode(type, function (geometry) {
            options.points = geometry;
            this.addPolygon(options, layerId);
        })
    },
    /**
     * 画多边形
     * @returns
     */
    preDragPolygon: function (callback) {
        this.hwmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOLYGON, function (geometry) {
            callback(geometry);
        });
    },
    /**
     * 画多边形
     * @param options
     * id,
     * fillColor,
     * lineColor,
     * lineWeight,
     * lineType:dash,solid,
     * lineOpacity,
     * opacity,
     * autoExit,
     * before,
     * after,
     * callback,
     * click,
     * @returns
     */
    dragPolygon: function (options, layerId) {
        var that = this;
        this.preDragPolygon(function (geometry) {
            if (options.before) options.before();
            options.points = geometry;
            var polygon = that.addPolygon(options, layerId);
            if (!(options.autoExit === false)) {
                that.pan();
            }
            if (options.after) options.after(polygon);

        })
    },
    /**
     * 画圆
     * @returns
     */
    preDragCircle: function (callback) {
        this.hwmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWCIRCLE, function (geometry) {
            callback(geometry);
        });
    },
    /**
     * 画圆
     * @param options
     */
    dragCircle: function (options, layerId) {
        var that = this;
        this.preDragCircle(function (geometry) {
            options.points = geometry;
            that.addPolygon(options, layerId);
            that.pan();
        });
    },
    /**
     * 画长方形
     * @param callback(geometry); geometry:{xMin,yMin,xMax,yMax}
     * @returns
     */
    preDragExtent: function (callback) {
        this.hwmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWEXTENT, function (geometry) {
            callback(geometry);
        });
    },
    /**
     * 画长方形
     * @param options
     * id,
     * fillColor,
     * lineColor,
     * lineWeight,
     * lineType:dash,solid,
     * lineOpacity,
     * opacity,
     * click,
     */
    dragExtent: function (options, layerId) {
        var that = this;
        this.preDragExtent(function (geometry) {
            options.points = geometry.xMin + "," + geometry.yMin + ";" + geometry.xMin + "," + geometry.yMax + ";" + geometry.xMax + "," + geometry.yMax + ";" + geometry.xMax + "," + geometry.yMin + ";" + geometry.xMin + "," + geometry.yMin;
            that.addPolygon(options, layerId);
            that.pan();
        })
    },
    /**
     * 画箭头
     * @returns
     */
    preDragArrow: function (callback) {
        this.hwmap.changeDragMode(com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWARROW, function (geometry) {
            callback(geometry);
        });
    },
    /**
     * 画箭头
     * @param options
     * id,
     * fillColor,
     * lineColor,
     * lineWeight,
     * lineType:dash,solid,
     * lineOpacity,
     * opacity,
     * click,
     */
    dragArrow: function (options, layerId) {
        var that = this;
        this.preDragArrow(function (geometry) {
            options.points = geometry;
            that.addPolygon(options, layerId);
            that.pan();
        })
    },
    /**
     * 添加动态图层
     * @returns
     */
    addDynamicLayer: function () {
        this.hwmap.addDynamicLayer("dynamicLayer1", function (xmin, ymin, xmax, ymax, width, height) {
            var leftbottom = com.hw.map.utils.MapTools.metersToLonLat(xmin, ymin);
            var rightTop = com.hw.map.utils.MapTools.metersToLonLat(xmax, ymax);
            return "http://sampleserver1.arcgisonline.com/ArcGIS/rest/services/Demographics/ESRI_Population_World/MapServer/export?dpi=96&transparent=true&format=jpeg&bbox=" +
                leftbottom.x + "%2C" + leftbottom.y + "%2C" + rightTop.x + "%2C" + rightTop.y + "&bboxSR=4326&imageSR=4326&size=" + width + "%2C" + height + "&f=image";
        });
    },
    /**
     * 移除动态图层
     */
    removeDynamicLayer: function () {
        this.hwmap.removeDynamicLayer("dynamicLayer1");
    },

    /**
     * 清除测量信息
     * @returns
     */
    clearMeasureMessage: function () {
        this.hwmap.clearMeasureMessage();
    },

    /*展示矢量图*/
    showVectorGIS:function(){
        this.hwmap.hideBaseLayer(1);
        this.hwmap.showBaseLayer(0);
    },

    /*展示影像图*/
    showImageGIS:function(){
        this.hwmap.hideBaseLayer(0);
        this.hwmap.showBaseLayer(1);
    }
};
$(function () {
    var LAYER_TYPE_VECTOR = "vector";
    var LAYER_TYPE_IMAGE = "iamge";
    $("#switchLayerBtn").bind("click",function () {
        var layerType = $(this).attr("data-layer-type");
        if (layerType == LAYER_TYPE_VECTOR) {
            HwmapCommon.showImageGIS();
            $(this).css("background", "url(" + rootPath + "/common/gis/images/vector.png)");
            $(this).attr("data-layer-type",LAYER_TYPE_IMAGE);
        }else{
            HwmapCommon.showVectorGIS();
            $(this).css("background", "url(" + rootPath + "/common/gis/images/image.png)");
            $(this).attr("data-layer-type",LAYER_TYPE_VECTOR);
        }

    });
});