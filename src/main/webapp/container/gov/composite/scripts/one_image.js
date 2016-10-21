var OneImagePage = function () {
    var page = {
        hwmap:undefined,
        height:$(window).height()-125,
        init: function () {
            this.initMap();
            this.initTree();
        },
        initTree:function () {
            var that = this;
            $(".tree-left").height(that.height);
            $.fn.zTree.init($(".oneImageTree"), {
                height:that.height,
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
            var that = this;
            //初始化地图大小
            var mapFrame = $("#mapFrame").css({
                width:$(".main-right").width(),
                height:that.height,
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
                that.loadEnterprise();
            };
        },
        /**
         * 加载企业到地图
         */
        loadEnterprise:function () {
            var that = this;
            $.ajax({
                url:rootPath + "/action/S_enterprise_Enterprise_getAll.action",
                type:"post",
                dataType:"json",
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
            });
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
                "<a id='mainInfo' data-id='"+enterprise.id+"' class='btn btn-primary' href='javascript:void(0);'>企业台账</a>" +
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
            //绑定企业台账按钮事件
            $(infoDOM).find("#mainInfo").bind("click", function () {

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
        }
    };
    page.init();
    return page;



}();
