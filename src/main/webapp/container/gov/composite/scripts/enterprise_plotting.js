var PlottingDialog = function(){
    var dialog = {
        _model:$("#plottingDialog"),
        _plottingEle:$('#plottingPaper'),
        _plotting:undefined,
        _width:$(window).width()-100,
        _height:$(window).height()-200,
        init:function () {
            var that = this;
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

            });
        },
        /**
         * 设置标绘模式
         * @param mode point polyline polygon
         */
        setMode:function (mode) {
            $("." + mode).css('display','block').siblings().hide();
        },
        _initPloting:function (attachmentId) {
            var that = this;
            that._plottingEle.html("");
            //初始化平面图
            that._plotting = that._plottingEle.plotting({
                action: 'plotting',
                bg: rootPath+'/action/S_attachment_Attachment_download.action?id='+attachmentId,
                icon:{
                    size:32,
                    url:rootPath+"/common/gis/images/markers/mark.png",
                    offset:{
                        left: 0,
                        top: 0
                    }
                },
                width: that._width,
                height: that._height
            }).data('Plotting');
            console.log(that._plottingEle.html());
            that._plotting.mode('pan');

        },
        setAttachmentId:function (attachmentId) {
            this._initPloting(attachmentId);
        },
        open:function () {
            this._model.modal('show');
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
//PlottingDialog.open();



