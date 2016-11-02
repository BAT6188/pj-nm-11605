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
        that.hwmap.clearOverlays();
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
        that.hwmap.addMarker({
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
    showEnterpriseInfoWin:function(enterprise){
        var height =250;
        var infoHtml = "<div>";
        infoHtml +="<table class='table'>" +
            "<tr><td style='text-align: left;width: 70px;'>企业名称:</td><td style='text-align: left;'>"+enterprise.name+"</td></tr>"+
            "<tr><td style='text-align: left;'>法人代表:</td><td style='text-align: left;'>"+enterprise.artificialPerson+"</td></tr>"+
            "<tr><td style='text-align: left;'>联系电话:</td><td style='text-align: left;'>"+enterprise.apPhone+"</td></tr>"+
            "<tr><td style='text-align: left;'>单位地址:</td><td style='text-align: left;'>"+enterprise.address+"</td></tr>"+
            "<tr><td style='text-align: left;'>所属网格:</td><td style='text-align: left;'>"+""+"</td></tr>"+
            "</table>"+
            "<div class='btn-group btn-group-sm pull-right' style='position: absolute;text-align: right;bottom: 0;right: 5px;'>" +
                //"<a id='mainInfo' data-id='"+enterprise.id+"' class='btn btn-primary' href='javascript:void(0);'>企业台账</a>" +
            "<a id='enterprisePlan' data-id='"+enterprise.id+"' class='btn btn-primary' href='javascript:void(0);'>企业平面图</a>" +
            "</div>";
        infoHtml+="</div>";

        //显示信息窗口
        var infoDOM = this.hwmap.showInfoWindow({
            x:enterprise.longitude,
            y:enterprise.latitude,
            width:240,
            height:height,
            html:infoHtml,
            title:"企业信息"
        });
        //绑定企业平面图按钮事件
        $(infoDOM).find("#enterprisePlan").bind("click", function () {
            if (!enterprise.planeMap) {
                Ewin.alert({message:"该企业未上传平面图"});
            }else{
                PlottingDialog.dialog({
                    show:true,
                    mode:"view",
                    attachmentId:enterprise.planeMap
                });
            }
        });
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



