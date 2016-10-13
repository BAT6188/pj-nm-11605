com.hw.map.utils.DomTools.addCssUrl(com.hw.map.utils.MapConstUtil.MapDirLocation + "/css/style.css"),
    com.hw.map.utils.DomTools.addJavascriptUrl(com.hw.map.utils.MapConstUtil.MapDirLocationGIS);
var mapPrototype = com.hw.map.HWMap.prototype;
com.hw.map.HWMap.currentIdIndex = 0,
    com.hw.map.HWMap.getCurrentIdIndex = function() {
        return com.hw.map.HWMap.currentIdIndex++,
            com.hw.map.HWMap.currentIdIndex
    },
    mapPrototype.__initMap = function(a, b) {
        var d, e, f, g, h, c = this;
        c.dpi = 96,
            c.meterPerInch = .0254,
            this.mapContainer = document.getElementById(a),
            this.mapDiv = document.createElement("div"),
            this.mapDiv.id = "com_hw_map_HWMap_" + com.hw.map.HWMap.getCurrentIdIndex(),
            this.mapDiv.style = "width:100%;height:100%;margin:0px;padding:0px;border:0px;position:relative;",
            this.mapContainer.insertBefore(this.mapDiv, null),
            d = 0,
            e = 0,
        b.center && (d = b.center.x, e = b.center.y),
            f = 1,
        b.zoomLevel && (f = b.zoomLevel),
            c.mMap = new BMap.Map(this.mapDiv.id),
            c.mMap.hwMap = c,
            g = new BMap.Point(d, e),
            c.mMap.centerAndZoom(g, f),
            h = c.mMap,
        b && b.showNavigateBar === !0 && h.addControl(new BMap.NavigationControl({
            type: BMAP_NAVIGATION_CONTROL_LARGE
        })),
        b && b.showScale === !0 && h.addControl(new BMap.ScaleControl),
        b && b.showOverview === !0 && h.addControl(new BMap.OverviewMapControl),
        b && b.showMapType && 1 != b.showMapType || h.addControl(new BMap.MapTypeControl),
            c.setPanZoomEnable(!0),
        b && b.mapLoad && b.mapLoad(c),
            h.addEventListener("dragend",
                function() {
                    b && b.mapExtentChanged && b.mapExtentChanged(c._getExtent())
                }),
            h.addEventListener("zoomend",
                function() {
                    b && b.mapExtentChanged && b.mapExtentChanged(c._getExtent())
                }),
            c._drawTool = new com.hw.map.BaiduDrawTool(c),
            c._currentDragMode = "pan",
            c._currentDragCallback = null,
            c._drawTool.on("draw-end",
                function(a) {
                    var b = c._currentDragMode;
                    return com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASURELENGTH === b,
                    com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASUREAREA === b,
                        c._currentDragCallback ? com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASURELENGTH === b ? (c._currentDragCallback(c._GeometryToHWPoints(a)), void 0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASUREAREA === b ? (c._currentDragCallback(c._GeometryToHWPoints(a)), void 0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOINT === b ? (c._currentDragCallback(new com.hw.map.HWPoint(a.lng, a.lat)), void 0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOLYGON === b ? (c._currentDragCallback(c._GeometryToHWPoints(a)), void 0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOLYLINE === b ? (c._currentDragCallback(c._GeometryToHWPoints(a)), void 0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWCIRCLE === b ? (c._currentDragCallback(c._GeometryToHWPoints(a)), void 0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWEXTENT === b ? (c._currentDragCallback(c._getExtent(a)), void 0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWARROW === b ? (c._currentDragCallback(c._GeometryToHWPoints(a)), void 0) : void 0 : void 0
                }),
            this.scales = [591657527.591555, 295828763.795777, 147914381.897889, 73957190.948944, 36978595.474472, 18489297.737236, 9244648.868618, 4622324.434309, 2311162.217155, 1155581.108577, 577790.554289, 288895.277144, 144447.638572, 72223.819286, 36111.9096437, 18055.9548224, 9027.977411, 4513.988705, 2256.994353, 1128.497176],
            this.resolutions = [156543.033928, 78271.5169639999, 39135.7584820001, 19567.8792409999, 9783.93962049996, 4891.96981024998, 2445.98490512499, 1222.99245256249, 611.49622628138, 305.748113140558, 152.874056570411, 76.4370282850732, 38.2185141425366, 19.1092570712683, 9.55462853563415, 4.77731426794937, 2.38865713397468, 1.19432856685505, .597164283559817, .298582141647617]
    },
    mapPrototype._getExtent = function() {
        var a = this.mMap.getBounds();
        return a ? new com.hw.map.HWExtent(a.getSouthWest().lng, a.getSouthWest().lat, a.getNorthEast().lng, a.getNorthEast().lat) : {}
    },
    mapPrototype._GeometryToHWPoints = function(a) {
        var c, b = [];
        for (c = 0; c < a.length; c++) b.push(new com.hw.map.HWPoint(a[c].lng, a[c].lat));
        return b
    },
    mapPrototype._toAGSPoint = function(a) {
        return new BMap.Point(a.x, a.y)
    },
    mapPrototype._toAGSPoints = function(a) {
        var c, d, b = [];
        for (c = 0; c < a.length; c++) d = this._toAGSPoint(a[c]),
            b.push(d);
        return b
    },
    mapPrototype.addMarker = function(a) {
        var b = new BMap.Icon(a.imgSrc, new BMap.Size(a.width, a.height), {
                offset: new BMap.Size(a.xOffset, a.yOffset)
            }),
            c = this._toAGSPoint(a.point),
            d = new BMap.Marker(c, {
                icon: b
            });
        d._graphic = a,
            a._graphic = d,
            a._map = this,
            this.mMap.addOverlay(d)
    },
    mapPrototype.addText = function(a) {
        var c = new BMap.Label(a.text, {
            position: this._toAGSPoint(a.point),
            offset: new BMap.Size(a.xOffset, a.yOffset)
        });
        c.setStyle({
            color: a.color,
            fontFamily: a.fontName,
            border: "0px",
            background: "rgba(0,0,0,0)",
            fontSize: a.fontSize + "px"
        }),
            c._graphic = a,
            a._graphic = c,
            a._map = this,
            this.mMap.addOverlay(c)
    },
    mapPrototype.addPolygon = function(a) {
        var c = new BMap.Polygon(this._toAGSPoints(a.points), {
            strokeStyle: a.lineType,
            strokeWeight: a.lineWeight,
            strokeColor: a.lineColor,
            strokeOpacity: a.lineOpacity,
            fillColor: a.fillColor,
            fillOpacity: a.opacity
        });
        c._graphic = a,
            a._graphic = c,
            a._map = this,
            this.mMap.addOverlay(c)
    },
    mapPrototype.addPolyline = function(a) {
        var c = new BMap.Polyline(this._toAGSPoints(a.points), {
            strokeStyle: a.lineType,
            strokeColor: a.color,
            strokeWeight: a.weight,
            strokeOpacity: a.opacity
        });
        c._graphic = a,
            a._graphic = c,
            a._map = this,
            this.mMap.addOverlay(c)
    },
    mapPrototype.updateMarker = function(a) {
        var b = a._graphic;
        return b ? (this.removeOverlay(a), this.addMarker(a), void 0) : (alert("marker not related !"), void 0)
    },
    mapPrototype.updatePolyline = function(a) {
        var b = a._graphic;
        return b ? (this.removeOverlay(a), this.addPolyline(a), void 0) : (alert("polygon not related !"), void 0)
    },
    mapPrototype.updatePolygon = function(a) {
        var b = a._graphic;
        return b ? (this.removeOverlay(a), this.addPolygon(a), void 0) : (alert("polygon not related !"), void 0)
    },
    mapPrototype.updateText = function(a) {
        var b = a._graphic;
        return b ? (this.removeOverlay(a), this.addText(a), void 0) : (alert("text not related !"), void 0)
    },
    mapPrototype.clearOverlays = function() {
        var b, a = this.mMap.getOverlays();
        for (b = 0; b < a.length; b++) this._disposeOverlay(a[b]._graphic);
        this.mMap.clearOverlays()
    },
    mapPrototype._disposeOverlay = function(a) {
        a._graphic && (a._graphic._graphic = void 0, a._graphic = void 0, a._map = void 0)
    },
    mapPrototype.removeOverlay = function(a) {
        var b = a._graphic;
        return b ? (this._disposeOverlay(a), this.mMap.removeOverlay(b), void 0) : (alert("overlay not related !"), void 0)
    },
    mapPrototype.getOverlayById = function(a) {
        var c, b = this.mMap.getOverlays();
        for (c = 0; c < b.length; c++) if (b[c]._graphic.id == a) return b[c];
        return null
    },
    mapPrototype.removeOverlayById = function(a) {
        var b = this.getOverlayById(a);
        return b ? (this.removeOverlay(b), !0) : !1
    },
    mapPrototype.showInfoWindow = function(a, b, c, d, e, f) {
        this.info ? (this.info.setWidth(0), this.info.setHeight(0), this.info.setTitle(f), this.info.setContent(e), this.info.redraw()) : this.info = new BMap.InfoWindow(e, {
            width: 0,
            height: 0,
            title: f
        }),
            this.mMap.openInfoWindow(this.info, this._toAGSPoint({
                x: a,
                y: b
            }))
    },
    mapPrototype.hideInfoWindow = function() {
        this.mMap.closeInfoWindow()
    },
    mapPrototype.panByPointAndLevel = function(a, b, c) {
        this.mMap.centerAndZoom(this._toAGSPoint(a, b), c)
    },
    mapPrototype.panByExtent = function(a, b, c, d) {
        this.mMap.setExtent(new esri.geometry.Extent(a, b, c, d), !0),
            this.mMap.setViewport([this._toAGSPoint(a, b), this._toAGSPoint(c, d)])
    },
    mapPrototype.zoomToLevel = function(a) {
        this.mMap.setZoom(a)
    },
    mapPrototype.centerAt = function(a, b) {
        this.mMap.panTo(this._toAGSPoint(a, b))
    },
    mapPrototype.panUp = function() {
        var a = this.mMap.getBounds();
        this.mMap.panTo(this._toAGSPoint((a.getSouthWest().lng + a.getNorthEast().lng) / 2, a.getNorthEast().lat))
    },
    mapPrototype.panDown = function() {
        var a = this.mMap.getBounds();
        this.mMap.panTo(this._toAGSPoint((a.getSouthWest().lng + a.getNorthEast().lng) / 2, a.getSouthWest().lat))
    },
    mapPrototype.panLeft = function() {
        var a = this.mMap.getBounds();
        this.mMap.panTo(this._toAGSPoint(a.getSouthWest().lng, (a.getSouthWest().lat + a.getNorthEast().lat) / 2))
    },
    mapPrototype.panRight = function() {
        var a = this.mMap.getBounds();
        this.mMap.panTo(this._toAGSPoint(a.getNorthEast().lng, (a.getSouthWest().lat + a.getNorthEast().lat) / 2))
    },
    mapPrototype.zoomIn = function() {
        this.mMap.zoomIn()
    },
    mapPrototype.zoomOut = function() {
        this.mMap.zoomOut()
    },
    mapPrototype.setPanZoomEnable = function(a) {
        this.mMap.disableDoubleClickZoom(),
            a === !0 ? (this.mMap.enableDragging(), this.mMap.enableScrollWheelZoom()) : (this.mMap.disableDragging(), this.mMap.disableScrollWheelZoom())
    },
    mapPrototype.getCenter = function() {
        var a = this.mMap.getCenter();
        return new com.hw.map.HWPoint(a.lng, a.lat)
    },
    mapPrototype.getExtent = function() {
        return this._getExtent()
    },
    mapPrototype.toMap = function(a, b) {
        var c = this.mMap.pixelToPoint(new BMap.Pixel(a, b));
        return new com.hw.map.HWPoint(c.lng, c.lat)
    },
    mapPrototype.toScreen = function(a, b) {
        var c = this.mMap.pointToPixel(this._toAGSPoint({
            x: a,
            y: b
        }));
        return new com.hw.map.HWPoint(c.x, c.y)
    },
    mapPrototype.getLevel = function() {
        return this.mMap.getZoom()
    },
    mapPrototype.getScale = function() {
        return this.scales[this.mMap.getZoom()]
    },
    mapPrototype.getHeight = function() {
        var a = this.mMap.getSize();
        return a.height
    },
    mapPrototype.getWidth = function() {
        var a = this.mMap.getSize();
        return a.width
    },
    mapPrototype.changeDragMode = function(a, b) {
        return this._currentDragMode = a,
            this._currentDragCallback = null,
            this._drawTool.deactivate(),
            com.hw.map.HWMapOperateModel.MAP_OPERATION_PAN === a ? (this._drawTool.activate(a), !0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASURELENGTH === a ? (this._currentDragCallback = b, this._drawTool.setLineSymbol(this._getLineStyle(this.DrawGeometryConfig)), this._drawTool.setTextSymbol(this._getTextStyle(this.measureTextConfig)), this._drawTool.activate(a), !0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASUREAREA === a ? (this._currentDragCallback = b, this._drawTool.setFillSymbol(this._getPolygonStyle(this.DrawGeometryConfig)), this._drawTool.setTextSymbol(this._getTextStyle(this.measureTextConfig)), this._drawTool.activate(a), !0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOINT === a ? (this._currentDragCallback = b, this._drawTool.activate(a), !0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOLYGON === a ? (this._currentDragCallback = b, this._drawTool.setFillSymbol(this._getPolygonStyle(this.DrawGeometryConfig)), this._drawTool.activate(a), !0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOLYLINE === a ? (this._currentDragCallback = b, this._drawTool.setLineSymbol(this._getLineStyle(this.DrawGeometryConfig)), this._drawTool.activate(a), !0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWCIRCLE === a ? (this._currentDragCallback = b, this._drawTool.setFillSymbol(this._getPolygonStyle(this.DrawGeometryConfig)), this._drawTool.activate(a), !0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWEXTENT === a ? (this._currentDragCallback = b, this._drawTool.setFillSymbol(this._getPolygonStyle(this.DrawGeometryConfig)), this._drawTool.activate(a), !0) : com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWARROW === a ? (this._currentDragCallback = b, this._drawTool.setFillSymbol(this._getPolygonStyle(this.DrawGeometryConfig)), this._drawTool.activate(a), !0) : (alert(a + " is invalid!"), !1)
    },
    mapPrototype._getLineStyle = function(a) {
        return {
            strokeStyle: a.lineType,
            strokeColor: a.color,
            strokeWeight: a.weight,
            strokeOpacity: a.opacity
        }
    },
    mapPrototype._getPolygonStyle = function(a) {
        return {
            strokeStyle: a.lineType,
            strokeWeight: a.lineWeight,
            strokeColor: a.lineColor,
            strokeOpacity: a.lineOpacity,
            fillColor: a.fillColor,
            fillOpacity: a.opacity
        }
    },
    mapPrototype._getTextStyle = function(a) {
        return {
            color: a.color,
            fontFamily: a.font,
            border: "0px",
            background: "rgba(0,0,0,0)",
            fontSize: a.fontSize + "px"
        }
    },
    mapPrototype.addDynamicLayer = function() {},
    mapPrototype.removeDynamicLayer = function() {},
    mapPrototype.clearMeasureMessage = function() {
        this.mMap,
            this.mMap.graphics.clear()
    },
    com.hw.map.HWMarker.prototype.addEventListener = function(a, b) {
        return com.hw.map.HWMap._addGraphicEventHandler(this, a, b)
    },
    com.hw.map.HWText.prototype.addEventListener = function(a, b) {
        return com.hw.map.HWMap._addGraphicEventHandler(this, a, b)
    },
    com.hw.map.HWPolyline.prototype.addEventListener = function(a, b) {
        return com.hw.map.HWMap._addGraphicEventHandler(this, a, b)
    },
    com.hw.map.HWPolygon.prototype.addEventListener = function(a, b) {
        return com.hw.map.HWMap._addGraphicEventHandler(this, a, b)
    },
    com.hw.map.HWMap._clickHandler = function(a) {
        var b = a.target._graphic;
        b && b.onClick && b.onClick(b)
    },
    com.hw.map.HWMap._mouseOverHandler = function(a) {
        var b = a.target._graphic;
        b && b.onMouseOver && b.onMouseOver(b)
    },
    com.hw.map.HWMap._mouseOutHandler = function(a) {
        var b = a.target._graphic;
        b && b.onMouseOut && b.onMouseOut(b)
    },
    com.hw.map.HWMap._addGraphicEventHandler = function(a, b, c) {
        var e = a._graphic;
        return e && a._map ? a.id ? b === com.hw.map.HWMapEvents.GRAPHIC_MOUSE_CLICK ? (e.addEventListener("click", com.hw.map.HWMap._clickHandler), a.onClick = c, !0) : b === com.hw.map.HWMapEvents.GRAPHIC_MOUSE_OVER ? (e.addEventListener("mouseover", com.hw.map.HWMap._mouseOverHandler), a.onMouseOver = c, !0) : b === com.hw.map.HWMapEvents.GRAPHIC_MOUSE_OUT ? (e.addEventListener("mouseout", com.hw.map.HWMap._mouseOutHandler), a.onMouseOut = c, !0) : !1 : (alert(" no id !"), !1) : (alert(" overlay not related !"), !1)
    },
    com.hw.map.BaiduDrawTool = function(a) {
        var b = this;
        b.hwMap = a,
            b.events = {},
            b.overlay = null,
            b.text = null,
            b.graphics = [],
            b.points = [],
            b.lastPoint = null
    },
    com.hw.map.BaiduDrawTool.prototype.activate = function(a) {
        var b = this;
        b.deactivate(),
            b.mode = a,
        this.mode != com.hw.map.HWMapOperateModel.MAP_OPERATION_PAN && (b.hwMap.mMap.addEventListener("dblclick", b.dblClick), b.hwMap.mMap.addEventListener("mousemove", b.mouseMove), b.hwMap.mMap.addEventListener("click", b.click))
    },
    com.hw.map.BaiduDrawTool.prototype.deactivate = function(a) {
        var b = this;
        b.mode = com.hw.map.HWMapOperateModel.MAP_OPERATION_PAN,
            b.hwMap.mMap.removeEventListener("dblclick", b.dblClick),
            b.hwMap.mMap.removeEventListener("mousemove", b.mouseMove),
            b.hwMap.mMap.removeEventListener("click", b.click),
        b.overlay && !a && (b.hwMap.mMap.removeOverlay(this.overlay), b.overlay = null),
        b.text && !a && (b.hwMap.mMap.removeOverlay(this.text), b.text = null),
        b.graphics && b.graphics.length > 0 && !a && b.hwMap.mMap.removeOverlay(b.graphics[i]),
        a || (b.graphics = [], b.points = [])
    },
    com.hw.map.BaiduDrawTool.prototype.setLineSymbol = function(a) {
        this.lineSymbol = a
    },
    com.hw.map.BaiduDrawTool.prototype.setFillSymbol = function(a) {
        this.fillSymbol = a
    },
    com.hw.map.BaiduDrawTool.prototype.setTextSymbol = function(a) {
        this.textSymbol = a
    },
    com.hw.map.BaiduDrawTool.prototype.on = function(a, b) {
        this.events[a] = b
    },
    com.hw.map.BaiduDrawTool.prototype.drawEnd = function(a, b) {
        this.events["draw-end"] && this.events["draw-end"](a),
            this.deactivate(b)
    },
    com.hw.map.BaiduDrawTool.prototype.calPolylineDistanceForlnglat = function(a) {
        var c, b = 0;
        for (c = 1; c < a.length; c++) b += com.hw.map.utils.MapTools.calDistanceByTowPoint({
                x: a[c - 1].lng,
                y: a[c - 1].lat
            },
            {
                x: a[c].lng,
                y: a[c].lat
            });
        return b
    },
    com.hw.map.BaiduDrawTool.prototype.calPolylineDistance = function(a) {
        var c, b = 0;
        for (c = 1; c < a.length; c++) b += this.hwMap.mMap.getDistance(a[c - 1], a[c]);
        return b
    },
    com.hw.map.BaiduDrawTool.prototype.dblClick = function() {
        var c, d, e, f, g, h, i, j, b = this.hwMap._drawTool;
        if ((b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOLYLINE || b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOLYGON) && b.drawEnd(b.points), b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASURELENGTH && (b.drawEnd(b.points, !0), c = b.calPolylineDistance(b.points), d = c > 1e4 ? (c / 1e3 + "").split(".")[0] + " 公里": (c + "").split(".")[0] + " 米", b.drawText(d, b.points[b.points.length - 1])), b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASUREAREA) {
            for (b.drawEnd(b.points, !0), e = 0, b.points.push(b.points[0]), f = 0, g = 0, h = b.calPolylineDistanceForlnglat(b.points), c = b.calPolylineDistance(b.points), i = 0, 0 != h && (i = c / h), j = 0; j < b.points.length - 1; j++) e += b.points[j].lng * b.points[j + 1].lat - b.points[j + 1].lng * b.points[j].lat,
                f += b.points[j].lng,
                g += b.points[j].lat;
            f += b.points[b.points.length - 1].lng,
                g += b.points[b.points.length - 1].lat,
                e = Math.abs(e * i * i) / 2,
                d = e > 1e8 ? (e / 1e6 + "").split(".")[0] + " 平方公里": (e + "").split(".")[0] + " 平方米",
                b.drawText(d, new BMap.Point(f / b.points.length, g / b.points.length))
        }
    },
    com.hw.map.BaiduDrawTool.prototype.drawText = function(a, b) {
        var c = this;
        c.text && c.hwMap.mMap.removeOverlay(c.text),
            c.text = new BMap.Label(a, {
                position: b
            }),
            c.text.setStyle(c.textSymbol),
            c.hwMap.mMap.addOverlay(c.text)
    },
    com.hw.map.BaiduDrawTool.prototype.mouseMove = function(a) {
        var c, b = this.hwMap._drawTool;
        b.overlay && (c = a.point, (b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOLYLINE || b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOLYGON || b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASURELENGTH || b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASUREAREA) && b.overlay && (b.hwMap.mMap.removeOverlay(b.overlay), b.points[b.points.length - 1] = c, b.overlay.setPath(b.points), b.hwMap.mMap.addOverlay(b.overlay)), b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWEXTENT && b.overlay && (b.hwMap.mMap.removeOverlay(b.overlay), b.points[b.points.length - 1] = c, b.overlay.setPath([new BMap.Point(b.points[0].lng, b.points[0].lat), new BMap.Point(b.points[0].lng, b.points[1].lat), new BMap.Point(b.points[1].lng, b.points[1].lat), new BMap.Point(b.points[1].lng, b.points[0].lat)]), b.hwMap.mMap.addOverlay(b.overlay)), b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWCIRCLE && b.overlay && (b.hwMap.mMap.removeOverlay(b.overlay), b.points[b.points.length - 1] = c, b.overlay.setRadius(b.hwMap.mMap.getDistance(b.points[0], b.points[1])), b.hwMap.mMap.addOverlay(b.overlay)))
    },
    com.hw.map.BaiduDrawTool.prototype.click = function(a) {
        var b = this.hwMap._drawTool,
            c = a.point;
        return b.lastPoint && b.hwMap.mMap.getDistance(b.lastPoint, c) < .01 ? (b.dblClick(a), void 0) : (b.lastPoint = c, b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOINT && b.drawEnd(c), (b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOLYLINE || b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASURELENGTH) && (b.points.push(c), b.overlay ? (b.hwMap.mMap.removeOverlay(b.overlay), b.points[b.points.length - 2] = c, b.overlay.setPath(b.points)) : (b.points.push(c), b.overlay = new BMap.Polyline(b.points, b.lineSymbol)), b.hwMap.mMap.addOverlay(b.overlay)), (b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWPOLYGON || b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_MEASUREAREA) && (b.points.push(c), b.overlay ? (b.hwMap.mMap.removeOverlay(b.overlay), b.points[b.points.length - 2] = c, b.overlay.setPath(b.points)) : (b.points.push(c), b.overlay = new BMap.Polygon(b.points, b.fillSymbol)), b.hwMap.mMap.addOverlay(b.overlay)), b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWEXTENT && (b.points.push(c), b.overlay ? (b.hwMap.mMap.removeOverlay(b.overlay), b.drawEnd(b.overlay.getBounds())) : (b.points.push(c), b.overlay = new BMap.Polygon([new BMap.Point(b.points[0].lng, b.points[0].lat), new BMap.Point(b.points[0].lng, b.points[1].lat), new BMap.Point(b.points[1].lng, b.points[1].lat), new BMap.Point(b.points[1].lng, b.points[0].lat)], b.fillSymbol), b.hwMap.mMap.addOverlay(b.overlay))), b.mode == com.hw.map.HWMapOperateModel.MAP_OPERATION_DRAWCIRCLE && (b.points.push(c), b.overlay ? (b.hwMap.mMap.removeOverlay(b.overlay), b.drawEnd(com.hw.map.utils.MapTools.getCircles(b.calPolylineDistanceForlnglat(b.points), b.points[0].lng, b.points[0].lat))) : (b.points.push(c), b.overlay = new BMap.Circle(c, .01, b.fillSymbol), b.hwMap.mMap.addOverlay(b.overlay))), void 0)
    };