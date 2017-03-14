//@ sourceURL=map_mark.js
var MapMarkDialog = function () {
    var $dialog = $("#markDialog"),points, hwmap, currentMode = "point",mapCallback;
    var currentOverlayId;
    $(".modalClose").click(function () {
        $dialog.modal('hide');
    })
    function dragPoint() {//标点
        showBtn();
        hwmap.dragPoint({
            id:"mark_"+(new Date()).getTime(),
            image:rootPath+"/common/gis/images/markers/mark.png",
            width:30,
            height:30,
            autoExit:false,
            before:function () {
                if(currentOverlayId){
                    hwmap.removeOverlay(currentOverlayId);
                }
                // hwmap.clearOverlays();
            },
            after:function (mark) {
                points = mark.point;
                currentOverlayId = mark.id;
            }
        });
    }
    function dragLine() {//标线
        showBtn();
        hwmap.dragLine({
            id:"polyline_"+(new Date()).getTime(),
            autoExit:false,
            before:function () {
                hwmap.clearOverlays();
            },
            after:function (polyline) {
                var pointsStr = "";
                for (var i = 0; i < polyline.points.length; i++){
                    var point = polyline.points[i];
                    pointsStr += point.x + "," + point.y + ";";
                }
                points = pointsStr;
            }
        });
    }
    function dragPolygon() {//标区域
        showBtn();
        hwmap.dragPolygon({
            id:"polygon_"+(new Date()).getTime(),
            autoExit:false,
            before:function () {
                hwmap.clearOverlays();
            },
            after: function (polygon) {
                var pointsStr = "";
                for (var i = 0; i < polygon.points.length; i++){
                    var point = polygon.points[i];
                    pointsStr += point.x + "," + point.y + ";";
                }
                points = pointsStr;
            }
        });
    }

    function switchViewMode(){
        if (hwmap) {
            hwmap.pan();
        }
        hideBtn();

    }
    function showBtn() {
        $dialog.find("#markDialogOK").show();
        $dialog.find("#markDialogCancel").text("取消");
    }
    function hideBtn() {
        $dialog.find("#markDialogOK").hide();
        $dialog.find("#markDialogCancel").text("关闭");
    }

    /**
     * 标注查看
     * @param options{type:标注类型,points}
     */
    function addShape(options) {
        if (!options) return;
        var type = options.type;
        var pointsStr = options.points;
        var shapeId = new Date().getTime();
        switch (type){
            case dialog.VIEW_POINT:
                var point = pointsStr.split(",");

                hwmap.addMarker({
                    id:shapeId,
                    x: point[0],
                    y: point[1],
                    image:rootPath+"/common/gis/images/markers/mark.png",
                    callback:function (mark) {
                        currentOverlayId = mark.id;
                    }
                });
                break;
            case dialog.VIEW_POLYLINE:
                var points = hwmap.MapTools.strToPoints(pointsStr);
                hwmap.addPolyline({
                    id:shapeId,
                    points: points
                });
                break;
            case dialog.VIEW_POLYGON:
                if ($.isArray(pointsStr)) {
                    var pointsArr = pointsStr;
                    for(var i = 0;i < pointsArr.length; i++) {
                        var points = hwmap.MapTools.strToPoints(pointsArr[i]);
                        hwmap.addPolygon({
                            id:shapeId+i,
                            points: points
                        });
                    }
                }else if($.type(pointsStr) == "string"){
                    var points = hwmap.MapTools.strToPoints(pointsStr);
                    hwmap.addPolygon({
                        id:shapeId,
                        points: points
                    });
                }

                break;
            default:
                Ewin.alert("未找到的查看类型"+type);
        }
    }
    function resetSize(){
        //初始化dialog大小
        var markDialogModal = $("#markDialog").find(".modal-dialog")
            .width($(window).width()-100);
        var modalBody = markDialogModal.find(".modal-body");
        modalBody.css("padding", 0);
        markDialogModal.find("iframe").css({
            width:markDialogModal.width()-3,
            height:$(window).height()-220,
            marginBottom:'-10px'
        });
    }
    var dialog = {
        MODE_POINT:"point",
        MODE_POLYLINE:"polyline",
        MODE_POLYGON:"polygon",
        MODE_VIEW:"view",

        VIEW_POINT:"point",
        VIEW_POLYLINE:"polyline",
        VIEW_POLYGON:"polygon",

        init:function () {
            var that = this;
            //初始化dialog大小
            resetSize();
            var markDialogModal = $("#markDialog").find(".modal-dialog");
            var mapFrame = markDialogModal.find("iframe");
            //获取map对象
            var mapWindow = mapFrame[0].contentWindow;
            mapWindow.initMap = function (hwmapCommon,mapContainer) {
                console.log("initMap-FrameSize:"+mapFrame.width()+","+mapFrame.height());
                $(mapContainer).width(mapFrame.width());
                $(mapContainer).height(mapFrame.height());
            };
            mapWindow.initMapFinish = function (hwmapCommon) {
                hwmap = hwmapCommon;
                that.setMode(currentMode);
            };

            $("#markDialogOK").bind('click', function (e) {
                var result = that._closed(points);
                if (!(result === false)) {
                    $('#markDialog').modal('hide');
                }
            });
        },

        /**
         * 设置标绘模式
         * @param mode: point polyline polygon view
         */
        setMode:function (mode,options) {
            if (!hwmap) {
                currentMode = mode;
                return;
            }
            hwmap.clearOverlays();
            if(options != null) {
                addShape(arguments[1].backgroundOverlay);
                addShape(arguments[1].coordinatePoint);
            }
            switch (mode) {
                case this.MODE_POINT:
                    dragPoint();
                    break;
                case this.MODE_POLYLINE:
                    dragLine();
                    break;
                case this.MODE_POLYGON:
                    dragPolygon();
                    break;
                case this.MODE_VIEW:
                    addShape(arguments[1]);
                    switchViewMode();
                    break;
                default:
                    Ewin.alert("未找到的模式"+mode);
            }
        },
        open:function () {
            //初始化dialog大小
            resetSize();
            $("#markDialog").modal('show');
        },
        _closed:function (points,okBtn) {

        },
        closed:function (callback) {
            this._closed = callback;
        }
    };
    dialog.init();
    return dialog;

}();
/*//标注调用
 MapMarkDialog.closed(function (mark) {
     if (mark) {
        $("#points").val(mark);
     }else{
         Ewin.alert({message:"请选择坐标"});
         return false;
     }
 });
MapMarkDialog.setMode(MapMarkDialog.MODE_POINT);
MapMarkDialog.open();
//标注查看调用
MapMarkDialog.setMode(MapMarkDialog.MODE_VIEW,{type:MapMarkDialog.VIEW_POINT,"points":"x,y"});
MapMarkDialog.open();*/


