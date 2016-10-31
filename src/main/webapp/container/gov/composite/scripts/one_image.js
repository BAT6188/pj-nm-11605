//@ sourceURL=one_image.js
var OneImagePage = function () {
    var page = {
        hwmap:undefined,
        enterpriseLayer:"EnterpriseLayer",
        blockLayer:"BlockLayer",
        villageLayer:"VillageEnvLayer",
        dustPortLayer:"DustPortLayer",
        videoPortLayer:"VideoLayer",
        height:$(window).height()-125,
        init: function () {
            this.initMap();
            this.initTree();
            //添加加载沙尘暴排口方法
            //地图加载调试
        },
        initTree:function () {
            var that = this;
            $(".tree-left").height(that.height);
            var ztreeEle = $(".oneImageTree");
            var ztree = $.fn.zTree.init(ztreeEle, {
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
                            that["load"+treeNode.type](ids);
                        }else{//取消选择 清除数据
                            if (treeNode.isParent){//如果是主节点，清空对应的图层
                                that.hwmap.removeLayer(treeNode.type+"Layer");
                            }else{
                                that.hwmap.removeOverlay(treeNode.id);//否清除对应的标绘
                            }
                        }


                    }
                }
            });
            $("#searchBtn").bind("click",function () {
                ztree.setting.async.otherParam = {"searchText": $("#searchText").val()};
                ztree.reAsyncChildNodes(null, "refresh");
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
            };
        },

        /**
         * 添加沙尘暴排口
         * @param ids
         */
        loadDustPort:function (ids) {
            var that = this;
            $.ajax({
                url:rootPath + "/action/S_port_DustPort_findByIds.action",
                type:"post",
                dataType:"json",
                data:$.param({'ids':ids},true),
                success:function (result) {
                    if (result && result.length > 0){
                        for(var i = 0; i < result.length; i++){
                            var dustPort = result[i];
                            that.addDustPortMark(dustPort);
                        }
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
                imgSrc:"images/markers/dust.png",
                width:33,
                height:37,
                x:dustPort.longitude,
                y:dustPort.latitude,
                click:function (gra) {
                    that.showDustPortInfoWin(gra.data);
                }
            },this.dustPortLayer);
        },
        //噪声排口是否监测和监测值映射。方便加载
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
                "<tr><td style='text-align: left;width: 70px;'>监测点:</td><td style='text-align: left;'>"+dustPort.name+"</td></tr>"+
                "<tr><td style='text-align: left;'>监测时间:</td><td style='text-align: left;'>"+dustPort.monitorTime+"</td></tr>"+
                "<tr><td style='text-align: left;'>能见度:</td><td style='text-align: left;'>"+pageUtils.getStr(dustPort.visibility)+"</td></tr>";

                //展示噪声排口监测值
            for(var isMonitor in this.dustMonitorMap) {
                if (dustPort[isMonitor] == "1") {
                    var lable = this.dustMonitorMap[isMonitor].label;
                    var valueField = this.dustMonitorMap[isMonitor].field;
                    var value = pageUtils.getStr(dustPort[valueField]);
                    infoHtml+="<tr><td style='text-align: left;'>"+lable+":</td><td style='text-align: left;'>"+value+"</td></tr>";
                }
            }
            infoHtml+="<tr><td style='text-align: left;'>昼间上限(dB):</td><td style='text-align: left;'>"+pageUtils.getStr(dustPort.dayMax)+"</td></tr>"+
                "<tr><td style='text-align: left;'>夜间上限(dB):</td><td style='text-align: left;'>"+pageUtils.getStr(dustPort.nightMax)+"</td></tr>"+
                "</table>";
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
                            that.addVillageArea(village);
                            var videos = village.videos;
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
                imgSrc:"images/markers/camera.png",
                width:32,
                height:32,
                x:video.longitude,
                y:video.latitude,
                click:function (gra) {
                    that.showVideoInfoWin(gra.data);
                }
            },this.enterpriseLayer);
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
