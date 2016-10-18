var OneImagePage = function () {
    var page = {
        init: function () {
            this.initMap();
            this.initTree();
        },
        initTree:function () {
            $(".tree-left").height($(window).height());
            $.fn.zTree.init($(".oneImageTree"), {
                height:500,
                width:200,
                view: {
                    showIcon:false,
                    showLine: false

                },
                async: {
                    enable: true,
                    url:rootPath+"/action/S_composite_BlockLevel_getBlockTree.action"
                },
                check: {
                    enable: true
                }
            });
        },
        initMap: function () {
            //初始化地图大小
            var mapFrame = $("#mapFrame").css({
                width:$(".main-right").width(),
                height:$(window).height(),
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
        }
    };
    page.init();
    return page;
}();
