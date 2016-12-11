//@ sourceURL=one_image.js
var OneImagePage = function () {
    var MINE_IMAGE_URL = "gongkuanglu.jsp";

    var Constant = {
        //数据类型标识
        GASPORT_FLAG: "GasPort",
        WATERPORT_FLAG: "WaterPort",
        NOISEPORT_FLAG: "NoisePort",
        DUSTPORT_FLAG: "DustPort",
        FUMESPORT_FLAG: "FumesPort",
        ENTERPRISE_FLAG: "Enterprise",
        VILLAGEENV_FLAG: "VillageEnv",
        VIDEO_FLAG: "Video",
        VIDEO_DEVICE_FLAG: "VideoDevice",
        CLOCK_DELAY:30000
    };
    var page = {
        zTree:undefined,
        hwmap:undefined,
        ztreeFinished:false,
        hwmapFinished:false,
        //地图图层
        MAP_LAYER_ENTERPRISE:"EnterpriseLayer",
        MAP_LAYER_BLOCK:"BlockLayer",
        MAP_LAYER_VILLAGE:"VillageEnvLayer",
        MAP_LAYER_VIDEOPORT:"VideoLayer",
        MAP_LAYER_NOISEPORT:"NoisePortLayer",
        MAP_LAYER_DUSTPORT:"DustPortLayer",
        MAP_LAYER_VIDEO_DEVICE:"VideoDeviceLayer",

        height:$(window).height()-125,
        init: function () {
            var VIEWTYPE_GIS = "gis",
                VIEWTYPE_TREE = "tree";
            this.initTree();
            // if (viewType == VIEWTYPE_GIS) {
                $(".main-right").css("float", "left");
                $(".main-right").width("100%");

                $(".tree-left").css(
                    "position","absolute",
                    "left","80px",
                    "top","30px",
                    "z-index","1"
                );
                $(".tree-left").css("height","30px");
                $(".tree-left").css("width","21%");

                $("#switchBtn").bind('click',function(){
                    var status = $(this).attr("data-status");
                    if (status == "false") {
                        $("#button").css('height',pageUtils.getTableHeight(),'overflow-y','hiddden');
                        // $("#名称").attr("属性名","属性值");
                        status = $("#switchBtn").attr("data-status","true");
                        $(".tree-left").css("overflow","auto");

                    }else if(status == "true"){
                        $(".main-right").css("float", "left");
                        $(".main-right").width("100%");
                        $(".tree-left").css("overflow","hidden");

                        $(".tree-left").css(
                            "position","absolute",
                            "left","80px",
                            "top","30px",
                            "z-index","1"
                        );
                        $(".tree-left").css("height","30px");
                        $(".tree-left").css("width","21%");

                        status = $("#switchBtn").attr("data-status","false");
                    }

                });
            // }
            this.initMap();

            var that = this;
            dict.init('noiseType','noiseDischargeStandard','noiseFnType');
            //定时加载排口，企业报警
            var alertTimer = setInterval(function () {
                that.refreshPortStatusToMap();
            }, Constant.CLOCK_DELAY);
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
                    url:rootPath+"/action/S_composite_BlockLevel_getOneImageTree.action",
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
                        that.ztreeFinished = true;
                        //清空地图数据
                        if (that.hwmap) {
                            that.hwmap.clear();
                        }
                        //如果地图加载完成，默认选中监控节点
                        if (that.hwmapFinished) {
                            that.defaultSelected();
                        }
                    },
                    onClick:function (event, treeId, treeNode) {
                        //如果是子节点，并且已在地图上显示，地图移动到对应的位置
                        var isChildNode = !treeNode.isParent;
                        if ( isChildNode && treeNode.checked) {
                            var overlay = that.hwmap.getOverlay(treeNode.id);
                            if (overlay) {
                                if (overlay.x && overlay.y) {
                                    that.hwmap.centerAt(marker.x, marker.y);
                                }else if (overlay.points){
                                    var center = that.hwmap.MapTools.getPolygonCenter(overlay.points);
                                    that.hwmap.centerAt(center.x, center.y);
                                }

                            }

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
                            if (treeNode.isParent){//如果是主节点，清空子图层
                                that.hwmap.removeLayer(treeNode.type+"Layer");
                                var children = treeNode.children;
                                for(var i = 0; i < children.length; i++) {
                                    var vnode = children[i];
                                    that.hwmap.removeLayer(vnode.id);
                                }
                            }else{
                                that.hwmap.removeOverlay(treeNode.id);//否清除对应的标绘
                                //删除相关联的图层
                                that.hwmap.removeLayer(treeNode.id);
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
                that.hwmapFinished = true;
                //如果左侧树加载完成，默认选中监控节点
                if (that.ztreeFinished) {
                    that.defaultSelected();
                }

            };
        },
        defaultSelected:function () {
            var that = this;
            if (enterpriseId) {
                that.selectEnterpriseById(enterpriseId);
            }else{
                that.selectAllMonitorNode();
            }
        },
        /**
         * 默认选中所有监控节点
         */
        selectAllMonitorNode:function () {
            var that = this;
            //默认显示 噪声
            var nnode = that.zTree.getNodesByParam("type",Constant.NOISEPORT_FLAG);
            if(nnode[0]){
                that.zTree.checkNode(nnode[0],true,true,true);
            }
            //沙尘暴排口
            var dnode = that.zTree.getNodesByParam("type",Constant.DUSTPORT_FLAG);
            if(dnode[0]){
                that.zTree.checkNode(dnode[0],true,true,true);
            }
            //企业
            var enode = that.zTree.getNodesByParam("type",Constant.ENTERPRISE_FLAG);
            if(enode[0]){
                that.zTree.checkNode(enode[0],true,true,true);
            }
        },
        selectEnterpriseById:function (eid){
            var that = this;
            //企业
            var enode = that.zTree.getNodesByParam("id",eid);
            if(enode[0]){
                that.zTree.checkNode(enode[0],true,true,true);
            }
        },

        /**
         * 获取数据数据所有的id
         * @param datas
         * @returns {Array}
         */
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
         * 更新标绘信息
         * @param markId 标绘id
         * @param image 标绘图标
         * @param data marker绑定数据
         */
        updateMarker:function (markId, image, data) {
            var mark = this.hwmap.getOverlay(markId);
            if (!mark) {
                return;
            }
            if (image) {
                mark.imgSrc = image;
            }
            if (data) {
                mark.data = data;
            }
            this.hwmap.updateMarker(mark);
        },

        /**
         * 加载刷新设备状态
         * 并设置地图显示图标 及弹窗显示
         */
        refreshPortStatusToMap:function () {
            this.refreshEnterprisePortStatusToMap();
            this.refreshNoisePortStatusToMap();
            this.refreshDustPortStatusToMap();
        },
        refreshEnterprisePortStatusToMap:function () {
            var that = this;
            //获取地图显示的markId
            var enterpriseMarkers = that.hwmap.getOverlays(that.MAP_LAYER_ENTERPRISE);
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
                        for (var i = 0; i < enterpriseAlertStatus.length; i++) {
                            var eas = enterpriseAlertStatus[i];
                            var image = that.portStatusMapMarkerIconUtil.getIcon(Constant.ENTERPRISE_FLAG,eas.status);
                            that.updateMarker(eas.id, image);
                        }
                    }
                }
            });
        },
        /**
         * 刷新噪音排口 状态
         * 并更新地图显示排口图标 及弹窗显示
         */
        refreshNoisePortStatusToMap:function () {
            var that = this;
            //获取地图显示的markId
            var noiseMarkers = that.hwmap.getOverlays(that.MAP_LAYER_NOISEPORT);
            //获取id 查询设备状态
            var ids = that.getIds(noiseMarkers);
            if (ids.length <= 0) {
                return;
            }
            that.loadNoisePort(ids, function (noises) {
                if (noises && noises.length > 0) {
                    for (var i = 0; i < noises.length; i++) {
                        var noise = noises[i];
                        var image = that.portStatusMapMarkerIconUtil.getIcon(Constant.NOISEPORT_FLAG,noise.portStatus);
                        that.updateMarker(noise.id, image, noise);
                    }
                }
            });
        },

        /**
         * 刷新噪音排口 最新状态
         * 并更新地图显示排口图标 及弹窗显示
         */
        refreshDustPortStatusToMap:function () {
            var that = this;
            //获取地图显示的markId
            var dustMarkers = that.hwmap.getOverlays(that.MAP_LAYER_DUSTPORT);
            //获取id 查询设备状态
            var ids = that.getIds(dustMarkers);
            if (ids.length <= 0) {
                return;
            }
            that.loadDustPort(ids, function (dusts) {
                if (dusts && dusts.length > 0) {
                    for (var i = 0; i < dusts.length; i++) {
                        var dust = dusts[i];
                        var image = that.portStatusMapMarkerIconUtil.getIcon(Constant.DUSTPORT_FLAG,dust.portStatus);
                        that.updateMarker(dust.id, image, dust);
                    }
                }
            });
        },

        portStatusMapMarkerIconUtil:function () {
            var iconUtil = {
                _init:function () {
                    this[Constant.GASPORT_FLAG] = {
                        "0":rootPath+"/common/gis/images/markers/gas_n.png",
                        "1":rootPath+"/common/gis/images/markers/gas_w.gif",
                        "2":rootPath+"/common/gis/images/markers/gas_e.png"
                    };
                    this[Constant.WATERPORT_FLAG] = {
                        "0":rootPath+"/common/gis/images/markers/water_n.png",
                        "1":rootPath+"/common/gis/images/markers/water_w.gif",
                        "2":rootPath+"/common/gis/images/markers/water_e.png"
                    };
                    this[Constant.NOISEPORT_FLAG] = {
                        '0': rootPath+'/common/gis/images/markers/noise_n.png',
                        '1': rootPath+'/common/gis/images/markers/noise_w.gif',
                        '2': rootPath+'/common/gis/images/markers/noise_e.png'
                    };
                    this[Constant.DUSTPORT_FLAG] = {
                        '0': rootPath+'/common/gis/images/markers/dust_n.png',
                        '1': rootPath+'/common/gis/images/markers/dust_w.gif',
                        '2': rootPath+'/common/gis/images/markers/dust_e.png'
                    };
                    this[Constant.FUMESPORT_FLAG] = {
                        "0":rootPath+"/common/gis/images/markers/fumes_n.png",
                        "1":rootPath+"/common/gis/images/markers/fumes_w.gif",
                        "2":rootPath+"/common/gis/images/markers/fumes_e.png"
                    };
                    this[Constant.ENTERPRISE_FLAG] = {
                        '0': rootPath+'/common/gis/images/markers/enterprise_n.png',
                        '1': rootPath+'/common/gis/images/markers/enterprise_w.gif'
                    };
                    this[Constant.VIDEO_FLAG] = {
                        '0': rootPath+'/common/gis/images/markers/camera.png'
                    };
                },
                getIcon:function (type,status) {
                    var iconMap = this[type];
                    if (iconMap) {
                        var icon = iconMap[status];
                        if (!icon) {//没有找到状态图标，返回正常状态图标
                            icon = iconMap["0"];
                        }
                        return icon;
                    }
                }
            };
            iconUtil._init();
            return iconUtil;
        }(),



        /**
         * 加载噪音排口
         * @param ids
         */
        loadNoisePort:function (ids,callback) {
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
            var image = that.portStatusMapMarkerIconUtil.getIcon(Constant.NOISEPORT_FLAG,noisePort.portStatus);
            this.hwmap.addMarker({
                id:noisePort.id,
                data:noisePort,
                type:Constant.NOISEPORT_FLAG,
                image:image,
                width:34,
                height:39,
                x:noisePort.longitude,
                y:noisePort.latitude,
                click:function (gra) {
                    that.showNoisePortInfoWin(gra.data);
                }
            },this.MAP_LAYER_NOISEPORT);
        },

        showNoisePortInfoWin:function(noisePort){

            var infoHtml = "<div>";
            infoHtml +="<table class='table'>" +
                "<tr><td style='text-align: right;width: 100px;'>噪声监测点:</td><td style='text-align: left;'>"+noisePort.name+"</td></tr>"+
                "<tr><td style='text-align: right;'>噪声源编号:</td><td style='text-align: left;'>"+pageUtils.filterUndefine(noisePort.number)+"</td></tr>"+
                "<tr><td style='text-align: right;'>监测点位置:</td><td style='text-align: left;'>"+pageUtils.filterUndefine(noisePort.position)+"</td></tr>"+
                "<tr><td style='text-align: right;'>噪声源类型:</td><td style='text-align: left;'>"+dict.get("noiseType",noisePort.noiseType)+"</td></tr>"+
                "<tr><td style='text-align: right;'>功能区类别:</td><td style='text-align: left;'>"+dict.get("noiseFnType",noisePort.noiseFnType)+"</td></tr>";
            infoHtml += "</table>";
            //添加按钮
            infoHtml+="<div class='btn-group btn-group-sm pull-right' style='text-align: right;bottom: 0;right: 5px;'>";
            infoHtml+="<button data-port-id='"+noisePort.id+"' class='btn btn-primary show-info-btn' href='javascript:void(0);'>监测点详情</button>";
            //超标按钮
            var statusBtnText = "";
            if (noisePort.portStatus == "1") {
                statusBtnText+="超标信息";
            }else if (noisePort.portStatus == "2") {
                statusBtnText+="异常信息";
            }else {
                statusBtnText = "";
            }
            var statusBtnHtml = "<button data-port-id='" + noisePort.id + "' class='btn btn-primary show-status-btn' href='javascript:void(0);'>"+statusBtnText+"</button>";
            if(statusBtnText){
                infoHtml+=statusBtnHtml;
            }
            infoHtml+="</div>";
            //添加按钮结束
            infoHtml += "</div>";
            //显示信息窗口
            var infoWindowDom = this.hwmap.showInfoWindow({
                x:noisePort.longitude,
                y:noisePort.latitude,
                width:300,
                height:280,
                html:infoHtml,
                title:"噪音监测设备"
            });
            $(infoWindowDom).find(".show-info-btn").bind("click",function () {
                var portId = $(this).data("port-id");
               //打开噪音详情表单
                NoiseFormViewDialog.modal({
                    id:portId
                });
                NoiseFormViewDialog.modal("show");
            });
            $(infoWindowDom).find(".show-status-btn").bind("click",function () {
                var portId = $(this).data("port-id");
                var text = $(this).text();
                var result = PortStatusFormView.setPortId(portId);
                if (result) {
                    PortStatusFormView.open();
                }else{
                    Ewin.alert({message:"未找到"+text});
                }

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
            var image = that.portStatusMapMarkerIconUtil.getIcon(Constant.DUSTPORT_FLAG,dustPort.portStatus);
            this.hwmap.addMarker({
                id:dustPort.id,
                data:dustPort,
                type:Constant.DUSTPORT_FLAG,
                image:image,
                width:33,
                height:37,
                x:dustPort.longitude,
                y:dustPort.latitude,
                click:function (gra) {
                    that.showDustPortInfoWin(gra.data);
                }
            },this.MAP_LAYER_DUSTPORT);
        },

        showDustPortInfoWin:function(dustPort){


            var infoHtml = "<div>";
            infoHtml +="<table class='table'>" +
                "<tr><td style='text-align: right;width: 100px;'>噪声监测点:</td><td style='text-align: left;'>"+dustPort.name+"</td></tr>"+
                "<tr><td style='text-align: right;'>噪声源编号:</td><td style='text-align: left;'>"+pageUtils.filterUndefine(dustPort.number)+"</td></tr>"+
                "<tr><td style='text-align: right;'>经度:</td><td style='text-align: left;'>"+dict.get("noiseType",dustPort.longitude)+"</td></tr>"+
                "<tr><td style='text-align: right;'>纬度:</td><td style='text-align: left;'>"+dict.get("noiseFnType",dustPort.latitude)+"</td></tr>"+
                "<tr><td style='text-align: right;'>监测点位置:</td><td style='text-align: left;'>"+pageUtils.filterUndefine(dustPort.position)+"</td></tr>";

            infoHtml += "</table>";
            //添加按钮
            infoHtml+="<div class='btn-group btn-group-sm pull-right' style='text-align: right;bottom: 0;right: 5px;'>";
            infoHtml+="<button data-port-id='"+dustPort.id+"' class='btn btn-primary show-info-btn' href='javascript:void(0);'>监测点详情</button>";
            //超标按钮
            var statusBtnText = "";
            if (dustPort.portStatus == "1") {
                statusBtnText+="超标信息";
            }else if (dustPort.portStatus == "2") {
                statusBtnText+="异常信息";
            }else {
                statusBtnText = "";
            }
            var statusBtnHtml = "<button data-port-id='" + dustPort.id + "' class='btn btn-primary show-status-btn' href='javascript:void(0);'>"+statusBtnText+"</button>";
            if(statusBtnText){
                infoHtml+=statusBtnHtml;
            }
            infoHtml+="</div>";
            //添加按钮结束
            infoHtml += "</div>";


            //显示信息窗口
            var infoWindowDom = this.hwmap.showInfoWindow({
                x:dustPort.longitude,
                y:dustPort.latitude,
                width:300,
                height:280,
                html:infoHtml,
                title:"沙尘暴监测设备"
            });
            $(infoWindowDom).find(".show-info-btn").bind("click",function () {
                var portId = $(this).data("port-id");
                //打开噪音详情表单
                DustFormViewDialog.modal({
                    id:portId
                });
                DustFormViewDialog.modal("show");
            });
            $(infoWindowDom).find(".show-status-btn").bind("click",function () {
                var portId = $(this).data("port-id");
                var text = $(this).text();
                var result = PortStatusFormView.setPortId(portId);
                if (result) {
                    PortStatusFormView.open();
                }else{
                    Ewin.alert({message:"未找到"+text});
                }

            });
        },

        /**
         * 加载网格区域
         */
        loadBlockToMap:function (ids) {
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
            if (block.blockLevelId != "2") {
                that.add134BlockArea(block);
            }else{//级别2使用下级网格坐标数据
                that.add2BlockArea(block);
            }
        },
        /**
         * 加载1，3，4级别网格坐标数据
         */
        add134BlockArea:function (block) {
            var that = this;
            var points = this.hwmap.MapTools.strToPoints(block.areaPoints);
            if (!points || points.length <=0) {
                return;
            }
            var blockLevelMapColor = {
                1:{
                    fillColor:"#CBF7EF",
                    lineColor:"#0AC3E9",
                    lineWeight:6
                },
                2:{
                    fillColor:"#EFFFAB",
                    lineColor:"#C1D964",
                    lineWeight:6
                },
                3:{
                    fillColor:"#CFE962",
                    lineColor:"#F7F5BD",
                    lineWeight:6
                },
                4:{
                    fillColor:"#F5A63C",
                    lineColor:"#F7F5BD",
                    lineWeight:6
                }
            };
            var blockColor = blockLevelMapColor[block.blockLevelId];
            this.hwmap.addPolygon({
                id:block.id,
                data:block,
                points:points,
                fillColor:blockColor.fillColor,
                lineColor:blockColor.lineColor,
                lineWeight:blockColor.lineWeight,
                lineType:that.hwmap.LINE_TYPE_SOLID,
                lineOpacity:2,
                opacity:0.6,
                click:function (gra) {
                    var block = gra.data;
                    that.showBlockInfoWin(block);
                }
            },that.MAP_LAYER_BLOCK);
        },
        add2BlockArea:function (block) {
            var that = this;
            var pointsArray = that.loadChildrenBlockPoints(block.id);
            if (!pointsArray || pointsArray.length <=0) {
                return;
            }
            for(var i = 0; i < pointsArray.length; i++) {
                var points = that.hwmap.MapTools.strToPoints(pointsArray[i]);
                block.areaPoints = pointsArray[i];
                this.hwmap.addPolygon({
                    id:block.id+i,
                    data:block,
                    points:points,
                    fillColor:"#EFFFAB",
                    lineColor:"#C1D964",
                    lineWeight:6,
                    lineType:that.hwmap.LINE_TYPE_SOLID,
                    lineOpacity:2,
                    opacity:0.6,
                    click:function (gra) {
                        var block = gra.data;
                        that.showBlockInfoWin(block);
                    }
                },block.id);
            }

        },
        loadChildrenBlockPoints: function(parentBlockId) {
            var points = [];
            $.ajax({//不为空，加载数据
                url: rootPath + "/action/S_composite_Block_findChildrenBlock.action",
                type: "post",
                async:false,
                dataType: "json",
                data: {"parentBlockId": parentBlockId},
                success: function (blocks) {
                    if (blocks && blocks.length > 0){
                        for(var i =0; i < blocks.length; i++) {
                            var block = blocks[i];
                            if (block.areaPoints) {
                                points.push(block.areaPoints);
                            }
                        }
                    }

                }
            });
            return points;
        },
        showBlockInfoWin:function (block) {
            var height =227;
            var infoHtml = "<div>";
            infoHtml +="<table class='table table-condensed' style='margin-bottom: 10px;'>" +
                "<tr><td style='text-align: left;width: 100px;'>网格级别:</td><td style='text-align: left;'>"+block.blockLevelName+"</td></tr>"+
                "<tr><td style='text-align: left;'>单位名称:</td><td style='text-align: left;'>"+block.orgName+"</td></tr>"+
                "<tr><td style='text-align: left;'>单位地址:</td><td style='text-align: left;'>"+block.orgAddress+"</td></tr>"+
                "<tr><td style='text-align: left;'>网格负责人:</td><td style='text-align: left;'>"+block.principal+"</td></tr>"+
                "<tr><td style='text-align: left;'>联系电话:</td><td style='text-align: left;'>"+block.principalPhone+"</td></tr>"+
                "</table>";
            infoHtml+="</div>";
            var points = this.hwmap.MapTools.strToPoints(block.areaPoints);
            var center = this.hwmap.MapTools.getPolygonCenter(points);
            //显示信息窗口
            this.hwmap.showInfoWindow({
                x:center.x,
                y:center.y,
                width:370,
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
            var image = that.portStatusMapMarkerIconUtil.getIcon(Constant.ENTERPRISE_FLAG);
            this.hwmap.addMarker({
                id:enterprise.id,
                data:enterprise,
                type:Constant.ENTERPRISE_FLAG,
                image:image,
                width:40,
                height:40,
                x:enterprise.longitude,
                y:enterprise.latitude,
                click:function (gra) {
                    that.showEnterpriseInfoWin(gra.data);
                }
            },this.MAP_LAYER_ENTERPRISE);
        },
        showEnterpriseInfoWin:function(enterprise){
            var infoHtml = "<div>";
            infoHtml +="<table class='table table-condensed' style='margin-bottom: 10px;'>" +
                "<tr><td style='text-align: center;width: 80px;'>企业名称:</td><td style='text-align: left;width: 120px;'>"+enterprise.name+"</td></tr>"+
                "<tr><td style='text-align: center;'>企业法人:</td><td style='text-align: left;'>"+enterprise.artificialPerson+"</td></tr>"+
                "<tr><td style='text-align: center;'>环保负责人:</td><td style='text-align: left;'>"+enterprise.envPrincipal+"</td></tr>"+
                "<tr><td style='text-align: center;'>联系电话:</td><td style='text-align: left;'>"+enterprise.apPhone+"</td></tr>"+
                "<tr><td style='text-align: center;'>单位地址:</td><td style='text-align: left;'>"+enterprise.address+"</td></tr>"+
                "<tr><td style='text-align: center;'>所属网格:</td><td style='text-align: left;'>"+""+"</td></tr>"+
                "</table>"+
                "<div class='btn-group btn-group-sm pull-right' style='text-align: right;bottom: 0;right: 5px;'>" +
                "<button id='mainInfo' data-id='"+enterprise.id+"' class='btn btn-primary' href='javascript:void(0);'>企业台账</button>" +
                "<button id='enterprisePlan' data-id='"+enterprise.id+"' class='btn btn-primary' href='javascript:void(0);'>企业平面图</button>" +
                "<button id='mineImage' data-id='"+enterprise.id+"' class='btn btn-primary' href='javascript:void(0);'>工矿图</button>" +
                // "<button id='surroundingVideo' data-id='"+enterprise.id+"' class='btn btn-primary' href='javascript:void(0);'>周边视频</button>" +
                "</div>";
            infoHtml+="</div>";

            //显示信息窗口
            var infoDOM = this.hwmap.showInfoWindow({
                x:enterprise.longitude,
                y:enterprise.latitude,
                width:370,
                height:320,
                html:infoHtml,
                title:"企业信息"
            });
            //绑定企业台账按钮事件
            $(infoDOM).find("#mineImage").bind("click", function () {
                window.open(MINE_IMAGE_URL);
            });
            //绑定企业台账按钮事件
            $(infoDOM).find("#mainInfo").bind("click", function () {
                EnterpriseInfoDialog.show(enterprise.id);
            });
            var that = this;
            //绑定企业平面图按钮事件
            $(infoDOM).find("#enterprisePlan").bind("click", function () {
                //获取企业平面图附件
                var attachments = pageUtils.findAttachment(enterprise.id, "planeMap");

                if (attachments.length <= 0) {
                    Ewin.alert({message:"该企业未上传平面图"});
                }else{

                    var markers = that.getPlaneMapPortMakerByEid(enterprise.id);
                    //获取企业所有排口的标绘信息
                    PlottingDialog.dialog({
                        show:true,
                        mode:"view",
                        data:markers,
                        attachments:attachments,
                        change:function () {
                            //隐藏提示框
                            $("#planeMap_popover").hide();
                        },
                        closed:function (modal) {
                            //隐藏提示框
                            $("#planeMap_popover").hide();
                            //清除定时器
                            if (PlottingDialog.planeMapAlerClock) {
                                clearInterval(PlottingDialog.planeMapAlerClock);
                            }
                        }
                    });
                    //定时平面图设备状态刷新
                    PlottingDialog.planeMapAlerClock  = setInterval(function () {
                        var markers = that.getPlaneMapPortMakerByEid(enterprise.id);
                        PlottingDialog.data(markers);
                    }, Constant.CLOCK_DELAY);
                }
            });
        },

        /**
         * 获取企业平面图标绘makers
         * @param eid
         */
        getPlaneMapPortMakerByEid:function (eid) {
            var that = this;
            var portsMap = that.findMarkedPorts(eid);
            var markers = [];
            if (portsMap) {
                function getTitle(portType) {
                    var title = "排口信息";
                    if (portType == Constant.GASPORT_FLAG) {
                        return "废气"+title;
                    }else if (portType == Constant.WATERPORT_FLAG) {
                        return "废水"+title;
                    }else if (portType == Constant.FUMESPORT_FLAG) {
                        return "油烟"+title;
                    }else if (portType == Constant.NOISEPORT_FLAG) {
                        return "噪声"+title;
                    }
                    return title;
                }
                for(var portType in portsMap) {
                    var ports = portsMap[portType];
                    if (ports && ports.length > 0) {
                        for(var i =0; i < ports.length; i++) {
                            var port = ports[i];
                            if (port.planeMapMark) {
                                //设置marker属性
                                var mark = JSON.parse(port.planeMapMark);
                                if (typeof mark == "string") {
                                    continue;
                                }
                                mark.id = port.id;
                                mark.src = that.portStatusMapMarkerIconUtil.getIcon(portType, port.portStatus);
                                mark.attrs = {
                                    portType:portType,
                                    port:port,
                                    popoverIsShow:false
                                };
                                mark.click = function (e) {
                                    var $this = $(this.node);
                                    var attrs = this.data("attrs");
                                    if (attrs.portType == Constant.VIDEO_FLAG){//视频设备,直接打开视频查看窗口
                                        that.openVideoWin(attrs.port.equipmentId);
                                    }else{//排口设备显示信息弹窗
                                        var html = that.getPortPopHtml(attrs.portType,attrs.port);
                                        //更新弹出窗口位置
                                        var offset = $this.position();
                                        $("#planeMap_popover").css("top",offset.top-40);
                                        $("#planeMap_popover").css("left",offset.left-40);
                                        //$("#planeMap_popover").css("left",offset.left);
                                        $("#planeMap_popover").find(".popover-title").text(getTitle(attrs.portType));
                                        $("#planeMap_popover").find(".popover-content").html(html);
                                        $("#planeMap_popover").toggle();
                                        //绑定超标信息按钮事件
                                        $("#planeMap_popover").find(".show-status-btn").bind("click", function () {
                                            var portId = $(this).data("port-id");
                                            var text = $(this).text();
                                            var result = PortStatusFormView.setPortId(portId);
                                            if (result) {
                                                PortStatusFormView.open();
                                            } else {
                                                Ewin.alert({message: "未找到" + text});
                                            }
                                        });
                                    }
                                };

                                markers.push(mark);
                            }

                        }
                    }

                }
            }
            return markers;

        },

        /**
         * 获取企业排口弹出框提示html
         * @param portType
         * @param port
         * @returns {string}
         */
        getPortPopHtml: function (portType, port) {
            var popHtml = "";
            if (portType == Constant.GASPORT_FLAG) {
                popHtml = this.getGasPopHtml(port);
            }else if(portType == Constant.WATERPORT_FLAG){
                popHtml = this.getWaterPopHtml(port);
            }else if(portType == Constant.FUMESPORT_FLAG){
                popHtml = this.getFumesPopHtml(port);
            }else{
                popHtml += "<div>未找到的设备类型</div>";
            }
            return popHtml;

        },
        GAS_MONITOR_ITEM_MAP:{
            'isNitrogen': {'field':'nitrogen','label':'氮氧化物(mg/m<sup>3</sup>)'},
            'isSulfur': {'field':'sulfur','label':'二氧化硫(mg/m<sup>3</sup>)'},
            'isGasFlow': {'field':'gasFlow','label':'废气流量(m<sup>3</sup>/h)'},
            'isDust': {'field':'dust','label':'烟尘(mg/m<sup>3</sup>)'},
            'isOxygen': {'field':'oxygen','label':'氧含量(%)'}
        },
        /**
         * 获取企业平面图废气提示框
         * @param port
         */
        getGasPopHtml:function (gasPort) {
            var popHtml = "";
            popHtml +="<table class='table table-bordered table-condensed' style='margin-bottom: 0;'>" +
                "<tr><td style='text-align: right;width: 130px;'>排口名称:</td><td style='text-align: left;width: 130px;'>"+gasPort.name+"</td></tr>"+
                "<tr><td style='text-align: right;'>监测时间:</td><td style='text-align: left;'>"+pageUtils.getStr(gasPort.monitorTime)+"</td></tr>";

            //展示噪声排口监测值
            for(var isMonitor in this.GAS_MONITOR_ITEM_MAP) {
                if (gasPort[isMonitor] == "1") {
                    var lable = this.GAS_MONITOR_ITEM_MAP[isMonitor].label;
                    var valueField = this.GAS_MONITOR_ITEM_MAP[isMonitor].field;
                    var value = pageUtils.getStr(gasPort[valueField]);
                    popHtml+="<tr><td style='text-align: right;'>"+lable+":</td><td style='text-align: left;'>"+value+"</td></tr>";
                }
            }
            if (gasPort.portStatus == "1") {
                popHtml+="<tr><td colspan='2' style='text-align: right;'><button class='btn btn-primary btn-sm show-status-btn' data-port-id='"+gasPort.id+"'>超标信息</button></td></tr>";
            }else if (gasPort.portStatus == "2") {
                popHtml+="<tr><td colspan='2' style='text-align: right;'><button class='btn btn-primary btn-sm show-status-btn' data-port-id='"+gasPort.id+"'>异常信息</button></td></tr>";
            }else {

            }

            popHtml += "</table>";

            return popHtml;
        },
        WATER_MONITOR_ITEM_MAP:{
            'isFlow': {'field':'flow','label':'流量(L/s))'},
            'isOxygen': {'field':'oxygen','label':'化学需氧量(mg/L)'},
            'isNitrogen': {'field':'nitrogen','label':'氨氮(mg/L)'},
            'isPh': {'field':'ph','label':'ph值(mg/L)'}
        },
        /**
         * 获取企业平面图废气提示框
         * @param port
         */
        getWaterPopHtml:function (waterPort) {
            var popHtml = "";
            popHtml +="<table class='table table-bordered table-condensed' style='margin-bottom: 0;'>" +
                "<tr><td style='text-align: right;width: 130px;'>排口名称:</td><td style='text-align: left;width: 130px;'>"+waterPort.name+"</td></tr>"+
                "<tr><td style='text-align: right;'>监测时间:</td><td style='text-align: left;'>"+pageUtils.getStr(waterPort.monitorTime)+"</td></tr>";

            //展示噪声排口监测值
            for(var isMonitor in this.WATER_MONITOR_ITEM_MAP) {
                if (waterPort[isMonitor] == "1") {
                    var lable = this.WATER_MONITOR_ITEM_MAP[isMonitor].label;
                    var valueField = this.WATER_MONITOR_ITEM_MAP[isMonitor].field;
                    var value = pageUtils.getStr(waterPort[valueField]);
                    popHtml+="<tr><td style='text-align: right;'>"+lable+":</td><td style='text-align: left;'>"+value+"</td></tr>";
                }
            }
            if (waterPort.portStatus == "1") {
                popHtml+="<tr><td colspan='2' style='text-align: right;'><button class='btn btn-primary btn-sm show-status-btn' data-port-id='"+waterPort.id+"'>超标信息</button></td></tr>";
            }else if (waterPort.portStatus == "2") {
                popHtml+="<tr><td colspan='2' style='text-align: right;'><button class='btn btn-primary btn-sm show-status-btn' data-port-id='"+waterPort.id+"'>异常信息</button></td></tr>";
            }else {

            }

            popHtml += "</table>";

            return popHtml;
        },
        FUMES_MONITOR_ITEM_MAP:{
            'isFumes': {'field':'fumes','label':'油烟(mg/L)'},
            'isTemperature': {'field':'temperature','label':'烟气温度(°C)'},
            'isHumidity': {'field':'humidity','label':'烟气湿度(%)'}
        },
        /**
         * 获取企业平面图废气提示框
         * @param port
         */
        getFumesPopHtml:function (fumesPort) {
            var popHtml = "";
            popHtml +="<table class='table table-bordered table-condensed' style='margin-bottom: 0;'>" +
                "<tr><td style='text-align: right;width: 130px;'>排口名称:</td><td style='text-align: left;width: 130px;'>"+fumesPort.name+"</td></tr>"+
                "<tr><td style='text-align: right;'>监测时间:</td><td style='text-align: left;'>"+pageUtils.getStr(fumesPort.monitorTime)+"</td></tr>";

            //展示噪声排口监测值
            for(var isMonitor in this.FUMES_MONITOR_ITEM_MAP) {
                if (fumesPort[isMonitor] == "1") {
                    var lable = this.FUMES_MONITOR_ITEM_MAP[isMonitor].label;
                    var valueField = this.FUMES_MONITOR_ITEM_MAP[isMonitor].field;
                    var value = pageUtils.getStr(fumesPort[valueField]);
                    popHtml+="<tr><td style='text-align: right;'>"+lable+":</td><td style='text-align: left;'>"+value+"</td></tr>";
                }
            }
            if (fumesPort.portStatus == "1") {
                popHtml+="<tr><td colspan='2' style='text-align: right;'><button class='btn btn-primary btn-sm show-status-btn' data-port-id='"+fumesPort.id+"'>超标信息</button></td></tr>";
            }else if (fumesPort.portStatus == "2") {
                popHtml+="<tr><td colspan='2' style='text-align: right;'><button class='btn btn-primary btn-sm show-status-btn' data-port-id='"+fumesPort.id+"'>异常信息</button></td></tr>";
            }else {

            }

            popHtml += "</table>";

            return popHtml;
        },
        getVideoPopHtml:function (video) {
            var popHtml = "";
            popHtml +="<table class='table table-bordered table-condensed' style='margin-bottom: 0;'>" +
            "<tr><td style='text-align: left;width: 90px;'>摄像头名称:</td><td style='text-align: left;'>"+pageUtils.getStr(video.name)+"</td></tr>"+
            "<tr><td style='text-align: left;'>所属单位:</td><td style='text-align: left;'>"+pageUtils.getStr(video.unitName)+"</td></tr>";
            popHtml+="<tr><td colspan='2' style='text-align: right;'><button class='btn btn-primary btn-sm show-video-btn' data-port-id='"+video.equipmentId+"'>查看监控</button></td></tr>";
            popHtml += "</table>";

            return popHtml;
        },
        /**
         * 查询企业所有已标绘的排口，及视频
         * @param enterpriseId
         */
        findMarkedPorts:function (enterpriseId) {
            var result;
            $.ajax({
                url:rootPath + "/action/S_enterprise_Enterprise_queryMarkedPortsByEid.action",
                type:"post",
                async:false,
                dataType:"json",
                data:{'id':enterpriseId},
                success:function (portsMap) {
                    result = portsMap
                }
            });
            return result;
        },
        /**
         * 加载农村及摄像头
         */
        loadVillageEnvToMap:function (ids) {
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
                            that.addVideos(videos,village.id);

                        }
                    }
                }
            });

        },
        addVillageArea:function (village) {
            var that = this;
            var points = this.hwmap.MapTools.strToPoints(village.points);
            if(!points){
                return false;
            }
            this.hwmap.addPolygon({
                id:village.id,
                data:village,
                points:points,
                fillColor:"#FF0000",
                lineColor:"#2D2424",
                lineWeight:2,
                lineType:that.hwmap.LINE_TYPE_SOLID,
                lineOpacity:0.7,
                opacity:0.2,
                click:function (gra) {
                    var village = gra.data;
                    that.showVillageInfoWin(village);
                }
            },that.MAP_LAYER_VILLAGE);
            return true;
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
        addVideos: function (videos,villageId) {
            if (videos && videos.length > 0) {
                for(var i = 0; i < videos.length; i++) {
                    this.addVideo(videos[i],villageId);
                }
            }
        },
        addVideo:function (video,villageId) {
            var x = video.longitude;
            var y = video.latitude;
            if (!x || !y) {
                return false;
            }
            var that = this;
            this.hwmap.addMarker({
                id:video.id,
                data:video,
                image:"images/markers/camera.png",
                width:32,
                height:32,
                x:video.longitude,
                y:video.latitude,
                click:function (gra) {
                    var video = gra.data;
                    that.openVideoWin(video.equipmentId);
                }
            }, villageId);
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
        },
        openVideoWin:function (eid) {

            window.open("http://baidu.boosj.com/watch/813504793906110606.html?page=videoMultiNeed");
        },

        /**
         * 加载企业到地图
         */
        loadVideoDeviceToMap:function (ids) {
            var that = this;
            $.ajax({
                url:rootPath + "/action/S_videodevice_VideoDevice_findByIds.action",
                type:"post",
                dataType:"json",
                data:$.param({'ids':ids},true),
                success:function (result) {
                    if (result && result.length > 0){
                        that.addVideoDevices(result);
                    }
                }
            });

        },

        /**
         * 公安视频
         * @param videoDevices
         * @param villageId
         */
        addVideoDevices: function (videoDevices) {
            if (videoDevices && videoDevices.length > 0) {
                for(var i = 0; i < videoDevices.length; i++) {
                    this.addVideoDevice(videoDevices[i]);
                }
            }
        },
        addVideoDevice:function (videoDevice) {
            var x = videoDevice.longitude;
            var y = videoDevice.latitude;
            if (!x || !y) {
                return false;
            }
            var that = this;
            this.hwmap.addMarker({
                id:videoDevice.id,
                data:videoDevice,
                image:"images/markers/camera.png",
                width:32,
                height:32,
                x:videoDevice.longitude,
                y:videoDevice.latitude,
                click:function (gra) {
                    var videoDevice = gra.data;
                    that.openVideoDeviceWin(videoDevice);
                }
            }, that.MAP_LAYER_VIDEO_DEVICE);
        },
        openVideoDeviceWin:function(videoDevice){
            var url = rootPath+"/container/gov/videodevice/video_device_control.jsp";
            var params = videoDevice;
            var dialogWidth = "1000px";
            var dialogHeight = "600px";
            if (window.showModelessDialog) {
                window.showModelessDialog(url, params, "dialogWidth=" + dialogWidth + ";dialogHeight=" + dialogHeight + ";");
            }else if (window.showModalDialog) {
                window.showModalDialog(url, params, "dialogWidth=" + dialogWidth + ";dialogHeight=" + dialogHeight + ";");
            }else{
                window.videoDevice = videoDevice;
                window.open(url, "视频播放");
            }

        }


    };

    page.init();
    return page;
}();
