com.hw.map.GeometryUtil = {
    pointsString2Points: function(a) {
        var d, e, b = [],
            c = a.split(";");
        for (d = 0; d < c.length; d++) e = c[d].split(","),
            b.push(new com.hw.map.HWPoint(parseFloat(e[0]), parseFloat(e[1])));
        return b
    }
},
    com.hw.map.HWPoint = function(a, b) {
        this.x = a,
            this.y = b,
            this.type = "com.hw.map.HWPoint"
    },
    com.hw.map.HWExtent = function(a, b, c, d) {
        this.xMin = a,
            this.yMin = b,
            this.xMax = c,
            this.yMax = d,
            this.type = "com.hw.map.HWExtent"
    },
    com.hw.map.HWExtent.prototype.getCenter = function() {
        return new com.hw.map.HWPoint((this.xMin + this.xMax) / 2, (this.yMin + this.yMax) / 2)
    },
    com.hw.map.HWExtent.prototype.getWidth = function() {
        return this.xMax - this.xMin
    },
    com.hw.map.HWExtent.prototype.getHeight = function() {
        return this.yMax - this.yMin
    },
    com.hw.map.HWExtent.prototype.expand = function(a) {
        var b = a * this.getWidth() / 2,
            c = a * this.getHeight() / 2,
            d = this.getCenter();
        this.xMin = d.x - b,
            this.xMax = d.x + b,
            this.yMin = d.y - c,
            this.yMax = d.y + c
    },
    com.hw.map.HWExtent.prototype.toPolygon = function() {
        var a = [];
        return a.push(new com.hw.map.HWPoint(this.xMin, this.yMin)),
            a.push(new com.hw.map.HWPoint(this.xMin, this.yMax)),
            a.push(new com.hw.map.HWPoint(this.xMax, this.yMax)),
            a.push(new com.hw.map.HWPoint(this.xMax, this.yMin)),
            a.push(new com.hw.map.HWPoint(this.xMin, this.yMin)),
            a
    },
    com.hw.map.HWMarker = function(a, b, c, d, e, f, g) {
        this.id = a,
            this.point = b,
            this.imgSrc = c,
            this.width = d,
            this.height = e,
            this.xOffset = f,
            this.yOffset = g,
            this.type = "com.hw.map.HWMarker"
    },
    com.hw.map.HWMarker.prototype.addEventListener = function() {
        alert(" no defined ! ")
    },
    com.hw.map.HWText = function(a, b, c, d, e, f, g, h, i) {
        this.id = a,
            this.point = b,
            this.text = c,
            this.fontSize = d,
            this.fontName = e,
            this.color = f,
            this.opacity = g,
            this.xOffset = h,
            this.yOffset = i,
            this.type = "com.hw.map.HWText"
    },
    com.hw.map.HWText.prototype.addEventListener = function() {
        alert(" no defined ! ")
    },
    com.hw.map.HWPolyline = function(a, b, c, d, e, f) {
        this.id = a,
            this.points = "string" == typeof b ? com.hw.map.GeometryUtil.pointsString2Points(b) : b,
            this.weight = c,
            this.lineType = d,
            this.color = e,
            this.opacity = f,
            this.type = "com.hw.map.HWPolyline"
    },
    com.hw.map.HWPolyline.LINE_TYPE_DASH = "dash",
    com.hw.map.HWPolyline.LINE_TYPE_SOLID = "solid",
    com.hw.map.HWPolyline.prototype.addEventListener = function() {
        alert(" no defined ! ")
    },
    com.hw.map.HWPolygon = function(a, b, c, d, e, f, g, h) {
        this.id = a,
            this.points = "string" == typeof b ? com.hw.map.GeometryUtil.pointsString2Points(b) : b,
            this.fillColor = c,
            this.lineColor = d,
            this.lineWeight = e,
            this.lineType = f,
            this.lineOpacity = g,
            this.opacity = h,
            this.type = "com.hw.map.HWPolygon"
    },
    com.hw.map.HWPolygon.prototype.addEventListener = function() {
        alert(" no defined ! ")
    };