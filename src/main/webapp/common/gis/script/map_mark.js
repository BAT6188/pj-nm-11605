var MapMarkDialog = {
    _points:undefined,
    init:function () {
        var that = this;
        //初始化dialog大小
        var markDialogModal = $("#markDialog").find(".modal-dialog")
            .width($(window).width()-100);
        var mapFrame = markDialogModal.find("iframe").css({
            width:markDialogModal.width()-3,
            height:$(window).height()-200,
            marginBottom:'-10px'
        });
        //获取map对象
        var mapWindow = mapFrame[0].contentWindow;
        var hwmap;
        mapWindow.initMap = function (hwmapCommon,mapContainer) {
            $(mapContainer).width(mapFrame.width());
            $(mapContainer).height(mapFrame.height());
        };
        mapWindow.initMapFinish = function (hwmapCommon) {
            hwmap = hwmapCommon;
        };
        //选择坐标按钮开启
        that.setMode("point");
        $("#markDialogOK").bind('click', function (e) {
            var result = that._closed(that._points);
            if (!(result === false)) {
                $('#markDialog').modal('hide');
            }
        });
        $(".point").bind('click',function () {
            hwmap.clearOverlays();
            hwmap.dragPoint({
                id:"mark_"+(new Date()).getTime(),
                imgSrc:"images/markers/mark.png",
                width:30,
                height:30,
                callback:function (mark) {
                    that._points = mark.point;
                }
            });
        });

        $(".polyline").bind('click',function () {
            hwmap.clearOverlays();
            hwmap.dragLine({
                id:"polyline_"+(new Date()).getTime(),
                callback:function (polyline) {
                    var pointsStr = "";
                    for (var i = 0; i < polyline.points.length; i++){
                        var point = polyline.points[i];
                        pointsStr += point.x + "," + point.y + ";";
                    }
                    that._points = pointsStr;
                }
            });
        });

        $(".polygon").bind('click',function () {
            hwmap.clearOverlays();
            hwmap.dragPolygon({
                id:"polygon_"+(new Date()).getTime(),
                callback: function (polygon) {
                    var pointsStr = "";
                    for (var i = 0; i < polygon.points.length; i++){
                        var point = polygon.points[i];
                        pointsStr += point.x + "," + point.y + ";";
                    }
                    that._points = pointsStr;
                }
            });
        });
    },
    /**
     * 设置标绘模式
     * @param mode point polyline polygon
     */
    setMode:function (mode) {
        $("." + mode).css('display','block').siblings().hide();
    },
    open:function () {
        $("#markDialog").modal('show');
    },
    _closed:function (points,okBtn) {

    },
    closed:function (callback) {
        this._closed = callback;
    }
};
MapMarkDialog.init();



