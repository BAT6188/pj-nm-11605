//@ sourceURL=enterprise_plotting.js
var PlottingDialog = function(){
    var dialog = {
        _model:$("#plottingDialog"),
        _plottingEle:$('#plottingPaper'),
        _plotting:undefined,
        _width:$(window).width()-100,
        _height:$(window).height()-200,
        init:function (attachmentIds) {
            var that = this;
            that._initPloting(attachmentIds);
            //初始化dialog大小
            that._model.find(".modal-dialog")
                .width(that._width);
            that._plottingEle.height(that._height);

            var zoomLevel = 1;
            //绑定操作按钮事件
            $(".glyphicon-zoom-in").bind('click',function () {
                zoomLevel = that._plotting._zoomLevel+0.1;
                that._plotting.zoom(zoomLevel);
            });
            $(".glyphicon-zoom-out").bind('click',function () {
                zoomLevel=that._plotting._zoomLevel-0.1;
                if (!(zoomLevel<0)) {
                    that._plotting.zoom(zoomLevel);
                }
            });
            $(".glyphicon-fullscreen").bind('click',function () {
                that._plotting.zoom(1);
            });
            $(".glyphicon-move").bind('click',function () {
                that._plotting.mode('pan');
            });

            $(".glyphicon-map-marker").bind('click',function () {
                that._plotting.mode('point');
            });
            //清除已标绘元素
            var markers = [];
            that._plottingEle.bind("mousedown",function () {
                if (that._plotting.mode() == "point" && markers.length>1) {
                    var marker = markers.shift();
                    marker.remove();
                }
            });
            that._plottingEle.bind("plotting", function (e, shape) {
                markers.push(shape);
            });
            that._model.find(".btn-save").bind('click', function (e) {
                var result = that._closed(JSON.stringify(that._plotting.data()[0]));
                if (!(result === false)) {
                    that._model.modal('hide');
                }
            });
        },
        /**
         * 设置标绘模式
         * @param mode point polyline polygon
         */
        setMode:function (mode) {
            if (mode == "marker") {
                $(".glyphicon-map-marker").show();
                this._model.find(".btn-save").show("hide");
                this._model.find(".btn-cancel").text("取消");
            }else if (mode == "view") {
                $(".glyphicon-map-marker").hide();
                this._model.find(".btn-save").hide();
                this._model.find(".btn-cancel").text("关闭");
            }

        },
        _initPloting:function (attachmentId) {
            var that = this;
            var parent = that._plottingEle.html("").parent();
            var newEle = that._plottingEle.clone().appendTo(parent);
            that._plottingEle.remove();
            that._plottingEle = newEle;
            //初始化平面图
            that._plotting = that._plottingEle.plotting({
                action: 'plotting',
                bg: rootPath+'/action/S_attachment_Attachment_download.action?id='+attachmentId,
                icon:{
                    size:32,
                    url:rootPath+"/common/gis/images/markers/mark.png",
                    offset:{
                        left: -50,
                        top: -87
                    }
                },
                width: that._width,
                height: that._height
            }).data('Plotting');
            that._plotting.mode('pan');

        },
        setAttachmentId:function (attachmentId) {
            if (typeof(attachmentId) == "object") {
                this.init(attachmentId[0]);
            }else if (typeof(attachmentId) === "string") {
                this.init(attachmentId);
            }

        },
        show:function () {
            this._model.modal('show');
        },
        _closed:function (points,okBtn) {

        },
        closed:function (callback) {
            this._closed = callback;
        },
        dialog:function () {
            if (arguments.length == 1) {
                if (typeof(arguments[0]) == "string") {
                    if (this[arguments[0]]){
                        this[arguments[0]]();
                    }
                }else if (typeof(arguments[0]) == "object"){
                    var options = arguments[0];
                    if (options["attachmentId"]) {
                        this.setAttachmentId(options["attachmentId"]);
                    }
                    if (options["mode"]) {
                        this.setMode(options["mode"]);
                    }
                    if (options["callback"]) {
                        this.closed(options["callback"]);
                    }
                    if (options["show"] !== false) {
                        this.show();
                    }
                    if (options["data"]) {
                        var data = options["data"];
                        if (typeof(data) === "string") {
                            data = JSON.parse(data);
                        }
                        if (typeof(data) === "object") {
                            data = [data];
                        }
                        var that = this;
                        this._plottingEle.one("init",function () {
                            that._plotting.data(data);
                        });

                    }
                }

            }
        }
    };

    return dialog;
}();
//查看调用
/*PlottingDialog.dialog({
    show:true,
    mode:"view",
    attachmentId:enterprise.planeMap
});*/
//标绘调用
/*PlottingDialog.dialog({
    show:true,
    mode:"marker",
    attachmentId:enterprise.planeMap,
    callback:function (marker) {
        console.dir(marker);
    }
});*/



