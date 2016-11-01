var MapDialog = {
    hwmap:undefined,
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
        mapWindow.initMap = function (hwmapCommon,mapContainer) {
            $(mapContainer).width(mapFrame.width());
            $(mapContainer).height(mapFrame.height());
        };
        mapWindow.initMapFinish = function (hwmapCommon) {
            that.hwmap = hwmapCommon;
        };
    },
    /**
     * 加载企业到地图
     */
    loadEnterprise:function (ids) {
        var that = this;
        $.ajax({
            url:rootPath + "/action/S_enterprise_Enterprise_findByIds.action",
            type:"post",
            dataType:"json",
            data:$.param({'ids':ids},true),
            success:function (result) {
                if (result && result.length > 0){
                    for(var i = 0; i < result.length; i++){
                        var enterprise = result[i];
                        that.addEnterpriseMark(enterprise);
                    }
                }
            }
        });
    },
    addEnterpriseMark:function (enterprise) {
        var x = enterprise.longitude;
        var y = enterprise.latitude;
        if (!x || !y) {
            return false;
        }
        var that = this;
        this.hwmap.addMarker({
            id:enterprise.id,
            data:enterprise,
            imgSrc:"images/markers/company.png",
            width:40,
            height:40,
            x:enterprise.longitude,
            y:enterprise.latitude,
            click:function (gra) {
                that.showEnterpriseInfoWin(gra.data);
            }
        },this.enterpriseLayer);
    },
    /**
     * 设置标绘模式
     * @param mode: point polyline polygon
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
MapDialog.init();



