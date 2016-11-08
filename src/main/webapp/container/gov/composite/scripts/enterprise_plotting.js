//@ sourceURL=enterprise_plotting.js
var PlottingDialog = function(){
    var plotting = {
        modal:$("#plottingDialog"),
        modalBody:$("#plottingDialog").find(".modal-body"),
        nav:$("#plottingDialog").find(".nav-pills"),
        _basePlottingElement:$('#plottingPaper'),
        _idMapPlotting:{},//平面图id映射plotting对象
        _currentPId:undefined,
        _width:$(window).width()-100,
        _height:$(window).height()-200,
        _init:function (attachments) {
            var that = this;
            //初始化dialog宽度
            that.modal.find(".modal-dialog")
                .width(that._width);

            that.nav.html("");
            if (attachments.length > 1){//多个平面图
                that._height = $(window).height()-200-48;
                //显示导航菜单
                that.nav.show();
            }else{
                //隐藏导航菜单
                that.nav.hide();
            }
            that._basePlottingElement.height(that._height);
            for(var i =0; i < attachments.length; i++) {
                var attachment = attachments[i];
                //创建平面图
                var plotting = that._createPlotting(attachment.id);
                that._idMapPlotting[plotting.id] = plotting;
                var pElement = plotting.element;
                //pElement.height(that._height);
                //标绘模式下，清除已标绘元素
                pElement.bind("mousedown",function () {
                    var currentPid = $(this).attr("id");
                    for(var pid in that._idMapPlotting) {
                        var plotting = that._idMapPlotting[pid];
                        var shapes = plotting.shapes;
                        if (plotting.plotting.mode() == "point") {
                            if ((pid == pid && shapes.length>1) || (pid != currentPid && shapes.length > 0)) {
                                var marker = shapes.shift();
                                marker.remove();
                            }
                        }

                    }
                });
                pElement.bind("plotting", function (e, shape) {
                    var plottingId = $(this).attr("id");
                    var thisPlotting = that.getPlottingByid(plottingId);
                    var shapes = thisPlotting.shapes;
                    var isUpdate = false;
                    for(var i = 0; i < shapes.length; i++){
                        var iShape = shapes[i];
                        if (iShape.id == shape.id) {
                            shapes.slice(i,1,shape);//更新
                            isUpdate = true;
                        }
                    }
                    if (!isUpdate) {//没有更新,添加
                        shapes.push(shape);
                    }


                });

                //添加导航菜单
                var item = $('<li data-id="'+attachment.id+'"><a href="#">' + attachment.name + '</a></li>');
                item.click(function () {

                    //更改样式
                    $(this).addClass("active").siblings().removeClass("active");
                    var pid = $(this).data("id");
                    var thisPlotting = that.getPlottingByid(pid);
                    var pEle = thisPlotting.element;
                    pEle.show();
                    pEle.siblings(".plotting").hide();
                    var plottingObj = thisPlotting.plotting;
                    setTimeout(function () {
                        var offset = pEle.offset();
                        var poOffset = {
                            top:-offset.top,
                            left:-offset.left
                        };
                        if (plottingObj) {
                            plottingObj.offset(poOffset);
                        }

                    },500);
                    var prePid = that._currentPId;
                    if (typeof that.options["change"] == "function") {
                        that.options["change"](that.modal,that.getPlottingByid(prePid),thisPlotting);
                    }
                    that._currentPId = pid;

                });
                item.appendTo(that.nav);
            }




            //绑定操作按钮事件
            $(".glyphicon-zoom-in").bind('click',function () {
                that.zoomIn();
            });
            $(".glyphicon-zoom-out").bind('click',function () {
                that.zoomOut();
            });
            $(".glyphicon-fullscreen").bind('click',function () {
                that.zoom(1);
            });
            $(".glyphicon-move").bind('click',function () {
                that._mode('pan');
            });

            $(".glyphicon-map-marker").bind('click',function () {
                that._mode('point');
            });

            that.modal.on('hidden.bs.modal',function () {
                //清除plotting对象
                that._clear();
            });
            that.modal.modal('hide');
            that.modal.find(".btn-save").bind('click', function (e) {
                var result = that._closed(that.data()[0]);
                if (!(result === false)) {
                    that.modal.modal('hide');
                }

            });
        },
        select:function (index) {
            var lis = this.nav.find("li");
            if (lis.length>index) {
                lis[index].click();
            }
        },
        getCurrentPlotting:function () {
          return this.getPlottingByid(this._currentPId);
        },
        getPlottingByid:function (id) {
            return this._idMapPlotting[id]
        },
        _getCurrentPlottingObj:function () {
          var plotting = this.getCurrentPlotting();
            if (plotting) {
                return plotting.plotting;
            }
        },
        zoom:function (level) {
            var plotting = this._getCurrentPlottingObj();
            plotting.zoom(level);
        },
        /**
         * 放大
         */
        zoomIn:function () {
            var plotting = this._getCurrentPlottingObj();
            var zoomLevel = plotting._zoomLevel+0.1;
            this.zoom(zoomLevel);
        },
        /**
         * 缩小
         */
        zoomOut:function () {
            var plotting = this._getCurrentPlottingObj();
            var zoomLevel=plotting._zoomLevel-0.1;
            if (!(zoomLevel<0)) {
                this.zoom(zoomLevel);
            }
        },
        _mode:function (mode) {
            var plotting = this._getCurrentPlottingObj();
            plotting.mode(mode);
        },

        /**
         * 设置标绘模式
         * @param mode marker
         */
        setMode:function (mode) {
            //this.modalBody.find(".glyphicon").css("display","block");
            if (mode == "marker") {
                this.modalBody.find(".glyphicon-map-marker").show();
                this.modal.find(".btn-save").show("hide");
                this.modal.find(".btn-cancel").text("取消");
            }else if (mode == "view") {
                this.modalBody.find(".glyphicon-map-marker").hide();
                this.modal.find(".btn-save").hide();
                this.modal.find(".btn-cancel").text("关闭");
            }

        },
        _createPlotting:function (attachmentId) {
            var that = this;
            var newEle = that._basePlottingElement.clone().hide().appendTo(that.modalBody);
            newEle.attr("id",attachmentId);
            //初始化平面图
            var plottingObj = newEle.plotting({
                action: 'plotting',
                bg: rootPath + '/action/S_attachment_Attachment_download.action?id=' + attachmentId,
                icon: {
                    size: 32,
                    url: rootPath + "/common/gis/images/markers/mark.png"
                    /*offset: {//元素显示时通过offset()设置plotting offset
                        left: -50,
                        top: -125
                    }*/
                },
                width: that._width,
                height: that._height
            }).data('Plotting');

            newEle.one("init",function () {
                var pid = $(this).attr("id");
                var plotting = that.getPlottingByid(pid);
                plotting.plotting = plotting._plotting;
                //默认选中第一个平面图
                if (!that._currentPId) {
                    that.select(0);
                }
            });
            var plotting = {
                id: attachmentId,
                element:newEle,
                plotting:undefined,//平面图初始化完成再赋值
                _plotting:plottingObj,
                shapes:[]
            };
            return plotting;

        },
        _setAttachmentId:function (attachmentId) {
            this._init([{id:attachmentId}]);
        },
        _setAttachments:function (attachments) {
            this._init(attachments);
        },
        show:function () {
            this.modal.modal('show');
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
                    this.options = options;
                    if (options["attachmentId"]) {
                        this._setAttachmentId(options["attachmentId"]);
                    }
                    if (options["attachments"]) {
                        this._setAttachments(options["attachments"]);
                    }
                    if (options["mode"]) {
                        this.setMode(options["mode"]);
                    }
                    if (options["callback"]) {
                        this.closed(options["callback"]);
                    }
                    if (options["closed"]) {
                        var that = this;
                        this.modal.on("hide.bs.modal",function () {
                            options["closed"](that.modal);
                        });
                    }
                    if (options["change"]) {

                    }
                    if (options["show"] !== false) {
                        this.show();
                    }
                    if (options["data"]) {
                        var data = options["data"];
                        this.data(data, true);

                    }
                }

            }
        },
        /**
         * 添加数据 or 设置数据
         * @param data
         * @param isClear 是否清空已有数据
         */
        data:function (data,isClear) {
            if(arguments.length == 0){//获取数据
                var resultShapes = [];
                for(var pid in this._idMapPlotting) {
                    var plotting = this._idMapPlotting[pid];
                    var plottingObj = plotting.plotting;
                    var datas = plottingObj.data();
                    for(var i =0; i < datas.length; i++){
                        var data = datas[i];
                        data.pid = plotting.id;
                        resultShapes.push(data);
                    }
                }
                return resultShapes;
            }else{//设置数据
                if (isClear === true) {
                    this._clearShapes();
                }
                if (typeof(data) === "string") {
                    data = JSON.parse(data);
                }
                if (Array.isArray(data)) {

                }else if (typeof(data) === "object") {
                    data = [data];
                }
                var that = this;
                //按pid进行分组
                var pidMapShapes = {};
                for(var i = 0; i < data.length; i++) {
                    var shape = data[i];
                    if (!shape.pid) {
                        continue;
                    }
                    var pShaes = pidMapShapes[shape.pid];
                    if (!pShaes) {
                        pidMapShapes[shape.pid] = [];
                    }
                    pidMapShapes[shape.pid].push(shape);
                }
                for(var pid in pidMapShapes) {
                    var plotting = that.getPlottingByid(pid);
                    //平面图不存在跳过
                    if (!plotting) {
                        continue;
                    }
                    var shapes = pidMapShapes[pid];
                    var plottingObj = plotting.plotting;
                    if (plottingObj) {//如果平面图已初始化好，直接添加
                        plottingObj.data(shapes);
                    }else{//添加平面图init事件添加数据
                        var plottingElement = plotting.element;
                        plottingElement.data("preAddShapses",shapes);
                        plottingElement.one("init",function () {
                            var pid = $(this).attr("id");
                            var preShapse = $(this).data("preAddShapses");
                            var plotting = that.getPlottingByid(pid);
                            var plottingObj = plotting.plotting;
                            plottingObj.data(preShapse);
                        });
                    }
                }

            }


        },
        _clear:function () {
            for(var pid in this._idMapPlotting) {
                var plotting = this._idMapPlotting[pid];
                var pEle = plotting.element;
                pEle.remove();
            }
            this._basePlottingElement.show();
            this._idMapPlotting = {};
            this._currentPId = undefined;
        },
        _clearShapes:function () {
            for(var pid in this._idMapPlotting) {
                var plotting = this._idMapPlotting[pid];
                var shapes = plotting.shapes;
                for(var i =0; i < shapes.length; i++){
                    var shape = shapes[i];
                    shape.remove();
                }
                plotting.shapes = [];
            }

        }
    };

    return plotting;
}();
//查看调用
/*PlottingDialog.dialog({
    show:true,
    mode:"view",
     attachments:[{
     id:"1d2db7897b3841739e9651a1f16e07bc",
     name:"平面图2.jpg"
     },{
     id:"c089ca2c63cc4710aa9eb83e946abfb9",
     name:"平面图2.jpg"
     }],
});*/
//标绘调用
/*PlottingDialog.dialog({
    show:true,
    mode:"marker",
    attachments:[{
        id:"1d2db7897b3841739e9651a1f16e07bc",
        name:"平面图2.jpg"
    },{
        id:"c089ca2c63cc4710aa9eb83e946abfb9",
        name:"平面图2.jpg"
    }],
    callback:function (marker) {
        console.dir(marker);
    },
    closed:function(){
    }
});*/




