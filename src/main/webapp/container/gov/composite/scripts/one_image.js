//@ sourceURL=one_image.js
var OneImagePage = function () {
    var page = {
        zTree:undefined,
        hwmap:undefined,
        ENTERPRISE_FLAG:"Enterprise",
        NOISEPORT_FLAG:"NoisePort",
        DUSTPORT_FLAG:"DustPort",

        enterpriseLayer:"EnterpriseLayer",
        blockLayer:"BlockLayer",
        villageLayer:"VillageEnvLayer",
        noisePortLayer:"NoisePortLayer",
        dustPortLayer:"DustPortLayer",
        videoPortLayer:"VideoLayer",
        height:$(window).height()-125,
        init: function () {
            this.initMap();
            this.initTree();
            var that = this;
            //定时加载排口，企业报警
            var alertTimer = setInterval(function () {
                that.loadPortNewStatus();
            }, 5000);
            $(window).one("menuchange",function () {
                clearInterval(alertTimer);
            });

        },
        initTree:function () {
            var that = this;
            $(".tree-left").height(that.height);
            var ztreeEle = $(".oneImageTree");
            that.zTree = $.fn.zTree.init(ztreeEle, {
                height:that.height,
                width:200,
                view: {
                    showIcon:false,
                    showLine: false

                },
                async: {
                    enable: true,
                    url:rootPath+"/action/S_composite_BlockLevel_getOneImageTree.action"
                },
                check: {
                    enable: true
                },
                callback:{
                    onAsyncSuccess:function (event, treeId, treeNode, msg) {
                        var nodes = JSON.parse(msg);
                        if (!nodes || nodes.length <=0) {
                            ztreeEle.BootstrapAlertMsg('success',"没有查询结果。",3000);
                        }
                    },
                    onCheck:function (event, treeId, treeNode) {
                        if (treeNode.checked) {//选中 加载对应的数据
                            var ids = [];
                            ids.push(treeNode.id);
                            if (treeNode.children && treeNode.children.length > 0) {
                                var children = treeNode.children;
                                for(var i = 0; i < children.length; i++){
                                    var childNode = children[i];
                                    ids.push(childNode.id);
                                }

                            }
                            that["load"+treeNode.type+"ToMap"](ids);
                        }else{//取消选择 清除数据
                            if (treeNode.isParent){//如果是主节点，清空对应的图层
                                that.hwmap.removeLayer(treeNode.type+"Layer");
                            }else{
                                that.hwmap.removeOverlay(treeNode.id);//否清除对应的标绘
                            }
                            that.hwmap.hideInfoWindow();
                        }


                    }
                }
            });
            $("#searchBtn").bind("click",function () {
                that.zTree.setting.async.otherParam = {"searchText": $("#searchText").val()};
                that.zTree.reAsyncChildNodes(null, "refresh");
            })
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
                //默认显示 噪声 沙尘暴排口和 企业
                var nnode = that.zTree.getNodesByParam("type",that.NOISEPORT_FLAG);
                var dnode = that.zTree.getNodesByParam("type",that.DUSTPORT_FLAG);
                var enode = that.zTree.getNodesByParam("type",that.ENTERPRISE_FLAG);
                that.zTree.checkNode(nnode[0],true,true,true);
                that.zTree.checkNode(dnode[0],true,true,true);
                that.zTree.checkNode(enode[0],true,true,true);
            };
        },

        getIds:function (datas) {
            var ids = [];
            for (var i = 0; i < datas.length; i++) {
                if (datas[i]) {
                    ids.push(datas[i].id);
                }

            }
            return ids;
        },
        /**
         * 根据排口超标记录状态 设置标绘是否报警
         * @param portStatus
         */
        setMarkerImage:function (markId, image) {
            var mark = this.hwmap.getOverlay(markId);
            if (!mark) {
                return;
            }
            mark.imgSrc = image;
            this.hwmap.updateMarker(mark);
        },

        /**
         * 加载设备超标历史记录
         * 并设置地图显示图标
         */
        loadPortNewStatus:function () {
            this.loadEnterprisePortNewStatus();
            this.loadNoisePortNewStatus();
            this.loadDustPortNewStatus();
        },
        loadEnterprisePortNewStatus:function () {
            var that = this;
            //获取地图显示的markId
            var markers = [];
            var enterpriseMarkers = that.hwmap.getOverlays(that.enterpriseLayer);
            //获取id 查询设备状态
            var ids = that.getIds(enterpriseMarkers);
            if (ids.length <= 0) {
                return;
            }
            $.ajax({
                url:rootPath + "/action/S_enterprise_Enterprise_queryEnterpriseAlertStatus.action",
                type:"post",
                dataType:"json",
                data:$.param({'ids':ids},true),
                success:function (enterpriseAlertStatus) {
                    if (enterpriseAlertStatus && enterpriseAlertStatus.length > 0) {
                        var statusMapImage = {
                            '0': 'images/markers/company.png',
                            '1': 'images/markers/company_alert.gif'
                        };
                        for (var i = 0; i < enterpriseAlertStatus.length; i++) {
                            var eas = enterpriseAlertStatus[i];
                            var image = statusMapImage[eas.status];
                            if (!image) {
                                image = 'images/markers/company.png';
                            }
                            that.setMarkerImage(eas.id, image);
                        }
                    }
                }
            });
        },
        /**
         * 加载噪音排口 最新状态
         * 并更新地图显示排口图标
         */
        loadNoisePortNewStatus:function () {
            var that = this;
            //获取地图显示的markId
            var markers = [];
            var noiseMarkers = that.hwmap.getOverlays(that.noisePortLayer);
            //获取id 查询设备状态
            var ids = that.getIds(noiseMarkers);
            if (ids.length <= 0) {
                return;
            }
            that.loadNoisePort(ids, function (noises) {
                if (noises && noises.length > 0) {
                    var statusMapImage = {
                        '0': 'images/markers/noise.png',
                        '1': 'images/markers/company_alert.gif',
                        '2': 'images/markers/bike0.png'
                    };
                    for (var i = 0; i < noises.length; i++) {
                        var noise = noises[i];
                        var image = statusMapImage[noise.portStatus];
                        if (!image) {
                            image = 'images/markers/bike0.png';
                        }
                        that.setMarkerImage(noise.id, image);
                    }
                }
            });
        },

        /**
         * 加载噪音排口 最新状态
         * 并更新地图显示排口图标
         */
        loadDustPortNewStatus:function () {
            var that = this;
            //获取地图显示的markId
            var markers = [];
            var dustMarkers = that.hwmap.getOverlays(that.dustPortLayer);
            //获取id 查询设备状态
            var ids = that.getIds(dustMarkers);
            if (ids.length <= 0) {
                return;
            }
            that.loadDustPort(ids, function (dusts) {
                if (dusts && dusts.length > 0) {
                    var statusMapImage = {
                        '0': 'images/markers/dust.png',
                        '1': 'images/markers/company_alert.gif',
                        '2': 'images/markers/noise.png'
                    };
                    for (var i = 0; i < dusts.length; i++) {
                        var dust = dusts[i];
                        var image = statusMapImage[dust.portStatus];
                        if (!image) {
                            image = 'images/markers/bike0.png';
                        }
                        that.setMarkerImage(dust.id, image);
                    }
                }
            });
        },




        /**
         * 加载噪音排口
         * @param ids
         */
        loadNoisePort:function (ids,callback) {
            var that = this;
            $.ajax({
                url:rootPath + "/action/S_port_NoisePort_findByIds.action",
                type:"post",
                dataType:"json",
                data:$.param({'ids':ids},true),
                success:function (result) {
                    callback(result);
                }
            });

        },
        /**
         * 添加噪音排口到地图
         */
        loadNoisePortToMap:function (ids) {
            var that = this;
            this.loadNoisePort(ids,function (noises) {
                if (noises && noises.length > 0){
                    for(var i = 0; i < noises.length; i++){
                        var noisePort = noises[i];
                        that.addNoisePortMark(noisePort);
                    }
                }
            });
        },
        addNoisePortMark:function (noisePort) {
            var x = noisePort.longitude;
            var y = noisePort.latitude;
            if (!x || !y) {
                return false;
            }
            var that = this;
            this.hwmap.addMarker({
                id:noisePort.id,
                data:noisePort,
                type:that.NOISEPORT_FLAG,
                imaSrc:"images/markers/noise.png",
                width:34,
                height:39,
                x:noisePort.longitude,
                y:noisePort.latitude,
                click:function (gra) {
                    that.showNoisePortInfoWin(gra.data);
                }
            },this.noisePortLayer);
        },
        //噪声排口是否监测和监测值映射。方便加载
        noiseMonitorMap:{
            'isLeqdb': {'field':'leqdb','lable':'Leq(db)'},
            'isSd': {'field':'sd','lable':'sd'},
            'isLmax': {'field':'lmax','lable':'Lmax(dB)'},
            'isLmin': {'field':'lmin','lable':'Lmin(dB)'},
            'isLFive': {'field':'lFive','lable':'L5(dB)'},
            'isLTen': {'field':'lTen','lable':'L10(dB)'},
            'isLFifty': {'field':'lFifty','lable':'L50(dB)'},
            'isLNinety': {'field':'lNinety','lable':'L90(dB)'},
            'isLNinetyFive': {'field':'lNinetyFive','lable':'L95(dB)'},
            'isLe': {'field':'le','lable':'Le'}

        },
        showNoisePortInfoWin:function(noisePort){

            var height =250;
            var infoHtml = "<div>";
            infoHtml +="<table class='table'>" +
                "<tr><td style='text-align: right;width: 100px;'>监测点:</td><td style='text-align: left;'>"+noisePort.name+"</td></tr>"+
                "<tr><td style='text-align: right;'>监测时间:</td><td style='text-align: left;'>"+noisePort.monitorTime+"</td></tr>";

            //展示噪声排口监测值
            for(var isMonitor in this.noiseMonitorMap) {
                if (noisePort[isMonitor] == "1") {
                    var lable = this.noiseMonitorMap[isMonitor].lable;
                    var valueField = this.noiseMonitorMap[isMonitor].field;
                    var value = pageUtils.getStr(noisePort[valueField]);
                    infoHtml+="<tr><td style='text-align: right;'>"+lable+":</td><td style='text-align: left;'>"+value+"</td></tr>";
                }
            }
            infoHtml+="<tr><td style='text-align: right;'>昼间上限(dB):</td><td style='text-align: left;'>"+noisePort.dayMax+"</td></tr>"+
                "<tr><td style='text-align: right;'>夜间上限(dB):</td><td style='text-align: left;'>"+noisePort.nightMax+"</td></tr>"+
                "</table>";
            infoHtml += "</div>";


            //显示信息窗口
            this.hwmap.showInfoWindow({
                x:noisePort.longitude,
                y:noisePort.latitude,
                width:300,
                height:height,
                html:infoHtml,
                title:"噪音监测设备"
            });
        },

        /**
         * 添加沙尘暴排口
         * @param ids
         */
        loadDustPort:function (ids,callback) {
            var that = this;
            $.ajax({
                url:rootPath + "/action/S_port_DustPort_findByIds.action",
                type:"post",
                dataType:"json",
                data:$.param({'ids':ids},true),
                success:function (result) {
                    callback(result);
                }
            });

        },
        /**
         * 添加沙尘暴排口到地图
         * @param ids
         */
        loadDustPortToMap:function (ids) {
            var that = this;
            that.loadDustPort(ids, function (dusts) {
                if (dusts && dusts.length > 0) {
                    for (var i = 0; i < dusts.length; i++) {
                        var dustPort = dusts[i];
                        that.addDustPortMark(dustPort);
                    }
                }
            });

        },
        addDustPortMark:function (dustPort) {
            var x = dustPort.longitude;
            var y = dustPort.latitude;
            if (!x || !y) {
                return false;
            }
            var that = this;
            this.hwmap.addMarker({
                id:dustPort.id,
                data:dustPort,
                type:that.DUSTPORT_FLAG,
                imaSrc:"images/markers/dust.png",
                width:33,
                height:37,
                x:dustPort.longitude,
                y:dustPort.latitude,
                click:function (gra) {
                    that.showDustPortInfoWin(gra.data);
                }
            },this.dustPortLayer);
        },
        //沙尘暴排口是否监测和监测值映射。方便加载
        dustMonitorMap:{
            'isPm': {'field':'pm','label':'PM(mg/m3)'},
            'isTsp': {'field':'tsp','label':'TSP(mg/m3)'},
            'isTemperature': {'field':'temperature','label':'温度(.C)'},
            'isHumidity': {'field':'lmin','label':'湿度(%)'},
            'isAirPressure': {'field':'lFive','label':'气压(hpa)'},
            'isWindDirection': {'field':'lTen','label':'风向(度)'},
            'isWindSpeed': {'field':'lFifty','label':'风速(m/s)'}

        },
        showDustPortInfoWin:function(dustPort){

            var height =250;
            var infoHtml = "<div>";
            infoHtml +="<table class='table'>" +
                "<tr><td style='text-align: right;width: 70px;'>监测点:</td><td style='text-align: left;'>"+dustPort.name+"</td></tr>"+
                "<tr><td style='text-align: right;'>监测时间:</td><td style='text-align: left;'>"+dustPort.monitorTime+"</td></tr>"+
                "<tr><td style='text-align: right;'>能见度:</td><td style='text-align: left;'>"+pageUtils.getStr(dustPort.visibility)+"</td></tr>";

                //展示噪声排口监测值
            for(var isMonitor in this.dustMonitorMap) {
                if (dustPort[isMonitor] == "1") {
                    var lable = this.dustMonitorMap[isMonitor].label;
                    var valueField = this.dustMonitorMap[isMonitor].field;
                    var value = pageUtils.getStr(dustPort[valueField]);
                    infoHtml+="<tr><td style='text-align: right;'>"+lable+":</td><td style='text-align: left;'>"+value+"</td></tr>";
                }
            }
            infoHtml += "</div>";


            //显示信息窗口
            this.hwmap.showInfoWindow({
                x:dustPort.longitude,
                y:dustPort.latitude,
                width:300,
                height:height,
                html:infoHtml,
                title:"沙尘暴监测设备"
            });
        },

        /**
         * 加载网格区域
         */
        loadBlock:function (ids) {
            var that = this;
            $.ajax({
                url:rootPath + "/action/S_composite_Block_findByIds.action",
                type:"post",
                dataType:"json",
                data:$.param({'ids':ids},true),
                success:function (result) {
                    if (result && result.length > 0){
                        for(var i = 0; i < result.length; i++){
                            var block = result[i];
                            that.addBlockArea(block);
                        }
                    }
                }
            });
        },
        addBlockArea:function (block) {
            var that = this;
            var points = this.hwmap.MapTools.strToPoints(block.areaPoints);
            if (!points || points.length <=0) {
                return;
            }

            this.hwmap.addPolygon({
                id:block.id,
                data:block,
                points:points,
                fillColor:"#C3F7EE",
                lineColor:"#00C0E8",
                lineWeight:1,
                lineType:that.hwmap.LINE_TYPE_SOLID,
                lineOpacity:2,
                opacity:0.6,
                click:function (gra) {
                    var block = gra.data;
                    that.showBlockInfoWin(block);
                }
            },that.blockLayer);
        },
        showBlockInfoWin:function (block) {
            var height =227;
            var infoHtml = "<div>";
            infoHtml +="<table class='table'>" +
                "<tr><td style='text-align: left;width: 70px;'>网格级别:</td><td style='text-align: left;'>"+block.blockLevelName+"</td></tr>"+
                "<tr><td style='text-align: left;'>单位名称:</td><td style='text-align: left;'>"+block.orgName+"</td></tr>"+
                "<tr><td style='text-align: left;'>单位地址:</td><td style='text-align: left;'>"+block.orgAddress+"</td></tr>"+
                "<tr><td style='text-align: left;'>负责人:</td><td style='text-align: left;'>"+block.principal+"</td></tr>"+
                "<tr><td style='text-align: left;'>联系电话:</td><td style='text-align: left;'>"+block.principalPhone+"</td></tr>"+
                "</table>";
            infoHtml+="</div>";
            var points = this.hwmap.MapTools.strToPoints(block.areaPoints);
            var center = this.hwmap.MapTools.getPolygonCenter(points);
            //显示信息窗口
            this.hwmap.showInfoWindow({
                x:center.x,
                y:center.y,
                width:240,
                height:height,
                html:infoHtml,
                title:"网格信息"
            });

        },
        /**
         * 加载企业到地图
         */
        loadEnterpriseToMap:function (ids) {
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
                type:that.ENTERPRISE_FLAG,
                imaSrc:"images/markers/company.png",
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
                EnterpriseInfoDialog.show(enterprise.id);
            });
            var that = this;
            //绑定企业平面图按钮事件
            $(infoDOM).find("#enterprisePlan").bind("click", function () {
                var attachIds = pageUtils.findAttachmentIds(enterprise.id, "planeMap");
                if (attachIds.length <= 0) {
                    Ewin.alert({message:"该企业未上传平面图"});
                }else{
                    PlottingDialog.dialog({
                        show:true,
                        mode:"view",
                        attachmentId:attachIds
                    });

                }


            });
        },
        /**
         * 加载农村及摄像头
         */
        loadVillageEnv:function (ids) {
            var that = this;
            $.ajax({
                url:rootPath + "/action/S_composite_VillageEnv_findByIds.action",
                type:"post",
                dataType:"json",
                data:$.param({'ids':ids},true),
                success:function (result) {
                    if (result && result.length > 0){
                        for(var i = 0; i < result.length; i++){
                            var village = result[i];
                            var videos = village.videos;
                            that.addVillageArea(village);
                            that.addVideos(videos);

                        }
                    }
                }
            });

        },
        addVillageArea:function (village) {
            var that = this;
            var points = this.hwmap.MapTools.strToPoints(village.points);

            this.hwmap.addPolygon({
                id:village.id,
                data:village,
                points:points,
                fillColor:"#FF0000",
                lineColor:"#2D2424",
                lineWeight:1,
                lineType:that.hwmap.LINE_TYPE_SOLID,
                lineOpacity:0.7,
                opacity:0.2,
                click:function (gra) {
                    var village = gra.data;
                    that.showVillageInfoWin(village);
                }
            },that.villageLayer);
        },
        showVillageInfoWin:function(village){
            var height =260;
            var infoHtml = "<div>";
            infoHtml +="<table class='table'>" +
                "<tr><td style='text-align: left;width: 90px;'>乡镇名称:</td><td style='text-align: left;'>"+pageUtils.getStr(village.name)+"</td></tr>"+
                "<tr><td style='text-align: left;'>所属网格:</td><td style='text-align: left;'>"+pageUtils.getStr(village.blockName)+"</td></tr>"+
                "<tr><td style='text-align: left;'>网格负责人:</td><td style='text-align: left;'>"+pageUtils.getStr(village.principal)+"</td></tr>"+
                "<tr><td style='text-align: left;'>负责人联系:</td><td style='text-align: left;'>"+pageUtils.getStr(village.principalPhone)+"</td></tr>"+
                "<tr><td style='text-align: left;'>环境详情:</td><td style='text-align: left;'>"+pageUtils.getStr(village.description)+"</td></tr>"+
                "</table>";
            infoHtml+="</div>";
            var points = this.hwmap.MapTools.strToPoints(village.points);
            var center = this.hwmap.MapTools.getPolygonCenter(points);
            //显示信息窗口
            this.hwmap.showInfoWindow({
                x:center.x,
                y:center.y,
                width:240,
                height:height,
                html:infoHtml,
                title:"农村生态环境"
            });
        },
        addVideos: function (videos) {
            if (videos && videos.length > 0) {
                for(var i = 0; i < videos.length; i++) {
                    this.addVideo(videos[i]);
                }
            }
        },
        addVideo:function (video) {
            var x = video.longitude;
            var y = video.latitude;
            if (!x || !y) {
                return false;
            }
            var that = this;
            this.hwmap.addMarker({
                id:video.id,
                data:video,
                imaSrc:"images/markers/camera.png",
                width:32,
                height:32,
                x:video.longitude,
                y:video.latitude,
                click:function (gra) {
                    that.showVideoInfoWin(gra.data);
                }
            },this.villageLayer);
        },
        showVideoInfoWin:function(video){
            var height =150;
            var infoHtml = "<div>";
            infoHtml +="<table class='table'>" +
                "<tr><td style='text-align: left;width: 90px;'>摄像头名称:</td><td style='text-align: left;'>"+video.name+"</td></tr>"+
                "<tr><td style='text-align: left;'>所属单位:</td><td style='text-align: left;'>"+video.unitName+"</td></tr>"+
                "</table>"+
                "<div class='btn-group btn-group-sm pull-right' style='position: absolute;text-align: right;bottom: 0;right: 5px;'>" +
                "<a id='viewVideo' data-id='"+video.equipmentId+"' class='btn btn-primary' href='javascript:void(0);'>查看监控</a>" +
                "</div>";
            infoHtml+="</div>";

            //显示信息窗口
            var infoDOM = this.hwmap.showInfoWindow({
                x:video.longitude,
                y:video.latitude,
                width:240,
                height:height,
                html:infoHtml,
                title:"摄像头信息"
            });
            //绑定查看监控按钮事件
            $(infoDOM).find("#viewVideo").bind("click", function () {
                var eid = $(this).data("id");
                //console.log("视频设备id:"+eid);
            });

        }

    };
    page.init();
    return page;
}();
