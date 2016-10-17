var SMSSendDialog = {
    _points:undefined,
    init:function () {
        var that = this;
        //初始化dialog大小
        var markDialogModal = $("#markDialog").find(".modal-dialog")
            .width($(window).width()-100);
        var mapFrame = $("#mapFrame").css({
            width:markDialogModal.width()-3,
            height:$(window).height()-200,
            marginBottom:'-10px'
        });
        $("#smsSendDialogOK").bind('click', function (e) {
            var result = that._closed(that._points);
            if (!(result === false)) {
                $('#markDialog').modal('hide');
            }
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
SMSSendDialog.init();




