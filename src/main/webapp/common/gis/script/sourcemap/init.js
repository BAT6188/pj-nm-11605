function init(a, b, c) {
    return com.hw.map.utils.MapConstUtil.MapDirLocation = a,
        com.hw.map.utils.MapConstUtil.MapDirLocationGIS = b,
    c || (c = "arcgis3.11"),
        -1 == com.hw.map.utils.MapConstUtil.MapTypes.indexOf(c) ? (alert("请传入合法的type类型:" + com.hw.map.utils.MapConstUtil.MapTypes), void 0) : (com.hw.map.utils.DomTools.addJavascriptUrl(a + "/" + "HWGeometry.js"), com.hw.map.utils.DomTools.addJavascriptUrl(a + "/" + "HWMap.js"), com.hw.map.utils.DomTools.addJavascriptUrl(a + "/" + c + ".js"), void 0)
}
var com = {};
com.hw = {},
    com.hw.map = {},
    com.hw.map.utils = {},
    com.hw.map.utils.MapConstUtil = {
        MapDirLocation: "/hm_gis/script/map",
        MapDirLocationGIS: "http://localhost:8080/3.16/3.16",
        MapTypes: "arcgis3.11,baidu",
        BaseLayers: [{
            id: "vectorTiledLayer",
            visible: !0,
            mapUrl: "http://localhost:8090/iserver/services/map-PICC_test/rest/maps/PICC_Test1",
            layerType: "tiled",
            layerName: "test1"
        }]
    },
    com.hw.map.utils.DomTools = {
        addJavascriptUrl: function(a) {
            document.writeln('<script type="text/javascript" src="' + a + '" > </script>')
        },
        addCssUrl: function(a) {
            document.writeln('<link type="text/css" rel="stylesheet" href="' + a + '" >')
        }
    },
    com.hw.JSTools = {
        cloneObject: function(a) {
            var c, b = a.constructor === Array ? [] : {};
            for (c in a) a.hasOwnProperty(c) && (b[c] = "object" == typeof a[c] ? cloneObject(a[c]) : a[c]);
            return b
        }
    },
    com.hw.map.utils.MapTools = {
        getCircleLinePoint: function(a, b) {
            var d, c = [];
            for (d = 0; 361 > d; d += 1) c.push(com.hw.map.utils.MapTools.getCirclePoint(d / 180 * Math.PI, b, a.x, a.y)),
                c.push(com.hw.map.utils.MapTools.getCirclePoint((d + .5) / 180 * Math.PI, b, a.x, a.y));
            return c
        },
        lonLatToMeters: function(a, b) {
            var c = 6378137 * 2 * Math.PI / 2,
                d = a * c / 180,
                e = Math.log(Math.tan((90 + b) * Math.PI / 360)) / (Math.PI / 180);
            return e = e * c / 180,
            {
                x: d,
                y: e
            }
        },
        metersToLonLat: function(a, b) {
            var c = 6378137 * 2 * Math.PI / 2,
                d = 180 * (a / c),
                e = 180 * (b / c);
            return e = 180 / Math.PI * (2 * Math.atan(Math.exp(e * Math.PI / 180)) - Math.PI / 2),
            {
                x: d,
                y: e
            }
        },
        getCirclePoint: function(a, b, c, d) {
            var e = c + Math.cos(a) * b,
                f = d + Math.sin(a) * b,
                g = {
                    x: e,
                    y: f
                };
            return g
        },
        getCircles: function(a, b, c) {
            var e, f, d = [];
            for (e = 0; 90 > e; e++) f = 4 * e / 180 * Math.PI,
                d.push(com.hw.map.utils.MapTools.getCirclePoint(f, a, b, c));
            return d
        },
        getEllipsePoint: function(angle, a, b, x, y) {
            var x1 = a * Math.sin(angle) + x,
                y1 = b * Math.cos(angle) + y,
                h = {
                    x: x1,
                    y: y1
                };
            return h
        },
        getElipses: function(a, b, x, y) {
            var angle, pointAngle, points = [];
            for (angle = 0; 90 > angle; angle++) pointAngle = 4 * angle / 180 * Math.PI,
                points.push(com.hw.map.utils.MapTools.getEllipsePoint(pointAngle, a, b, x, y));
            return points
        },
        rotate: function(angle, points, x, y) {
            var f, g, h, i, j, e = [];
            for (f = 0; f < points.length; f++) g = com.hw.map.utils.MapTools.calDistanceByTowPoint({
                    x: x,
                    y: y
                },
                points[f]),
                h = com.hw.map.utils.MapTools.calAngle({
                        x: x,
                        y: y
                    },
                    points[f]),
                i = (h + angle) * Math.PI / 180,
                j = com.hw.map.utils.MapTools.getCirclePoint(i, g, x, y),
                e.push(j);
            return e
        },
        calPointByLineAndDistance: function(a, b, c) {
            var d = {
                x: 0,
                y: 0
            };
            return d.x = a.x + c * (b.x - a.x),
                d.y = a.y + c * (b.y - a.y),
                d
        },
        calDistanceByTowPoint: function(a, b) {
            return Math.pow(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2), .5)
        },
        calLineLength: function(a) {
            var c, b = 0;
            for (c = 0; c < a.length - 1; c++) b += com.hw.map.utils.MapTools.calDistanceByTowPoint(a[c], a[c + 1]);
            return b
        },
        calAngle: function(a, b) {
            var c = b.x - a.x,
                d = b.y - a.y,
                e = 180 * Math.atan(d / c) / Math.PI;
            return b.x < a.x ? e + 180 : e
        },
        createArrowByTowPoint: function(a, b, c, d) {
            var h, i, j, k, l, m, n, o, e = com.hw.map.utils.MapTools.calDistanceByTowPoint(a, b),
                f = com.hw.map.utils.MapTools.calAngle(a, b) * Math.PI / 180;
            return com.hw.map.utils.MapTools.getCirclePoint(f, c / 2, a.x, a.y),
                h = com.hw.map.utils.MapTools.getCirclePoint(f + Math.PI / 2, c / 2, a.x, a.y),
                i = com.hw.map.utils.MapTools.getCirclePoint(f - Math.PI / 2, c / 2, a.x, a.y),
                j = com.hw.map.utils.MapTools.getCirclePoint(f, e - d, h.x, h.y),
                k = com.hw.map.utils.MapTools.getCirclePoint(f + Math.PI / 2, d / 2 - c / 2, j.x, j.y),
                l = b,
                m = com.hw.map.utils.MapTools.getCirclePoint(f, e - d, i.x, i.y),
                n = com.hw.map.utils.MapTools.getCirclePoint(f - Math.PI / 2, d / 2 - c / 2, m.x, m.y),
                o = h,
                [h, j, k, l, n, m, i, o]
        }
    };