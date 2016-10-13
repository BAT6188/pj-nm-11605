$(function(){
    //获取map对象
    var mapWindow = $("#mapFrame")[0].contentWindow;
    var hwmap;
    mapWindow.initMapFinish = function (hwmapCommon) {
        hwmap = hwmapCommon;
    };

    //通过dialog传递返回值
    var dialog = {};
    if (frameElement.dialog) {
        dialog = frameElement.dialog;
    }

    $(".point").bind('click',function () {
        hwmap.clearOverlays();
        hwmap.dragPoint({
            id:"mark_"+(new Date()).getTime(),
            imgSrc:"images/markers/mark.png",
            width:30,
            height:30,
            callback:function (mark) {
                dialog.marks = mark.point;
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
                dialog.marks = pointsStr;
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
                dialog.marks = pointsStr;
            }
        });
    });
})