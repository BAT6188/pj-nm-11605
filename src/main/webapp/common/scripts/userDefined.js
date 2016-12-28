var pageUtils = {
    MSG_TYPE_SCHEDULE:"1",
    MSG_TYPE_MEETINGNOTICE:"2",
    MSG_TYPE_PUBINFO : "3",
    MSG_TYPE_POLLUTANTPAYMENT : "4",
    MSG_TYPE_CREATEMODEDETAIL:"5",
    MSG_TYPE_MONITOR_OFFICE:"6",//监察大队办公室发送给环保站
    MSG_TYPE_MONITOR_CASE:"7",//监控中心发送给监察大队
    MSG_TYPE_law_manage:"8",//监察大队发送给环保站
    MSG_TYPE_trustMonitor:"9",//申请委托监测发送给接收委托监测
    MSG_TYPE_receiveTrustMonitor:"10",//接收委托监测发送给监测站办公室
    MSG_TYPE_monitoring_station_office:"11",//监测站办公室发送给监测站站长
    MSG_TYPE_monitoring_station_master:"12",//监测站站长发送给监测人员
    FROM_HEIGHT:600,
    PAGE_SIZE:10,

    updateSelfReadStatus:function (url,id,selfReadStatus) {
        $.ajax({
            url:url,
            type:"post",
            data:{id:id,selfReadStatus:selfReadStatus},
            success:function (msg) {
                if (msg=='ok'){
                    console.log("保存成功")
                }

            }
        });
    },

    sendParamDataToString:function (data) {
        console.log("sendParamDataToString:"+JSON.stringify(data))
        var d=""
        // data: {"personObj":[{"0":true,"couldChose":true,"icon":"common/images/ztree/head_male_man_user.png","id":"402883b3577422f00157fa2def64042f","mobilePhone":"13604779948","name":"张一乐","parentId":"402883b3577422f00157f9d874d103e9","pinyinCodes":"ZYL,zhangyile","userId":"zhangyile","level":1,"tId":"choseZtree1479095001232_3","parentTId":"choseZtree1479095001232_1","open":false,"isParent":false,"zAsync":true,"isFirstNode":false,"isLastNode":false,"isAjaxing":false,"checked":false,"checkedOld":false,"nocheck":false,"chkDisabled":false,"halfCheck":false,"check_Child_State":-1,"check_Focus":false,"isHover":true,"editNameFlag":false}],"sourceId":"ba1750b4768647aba501e8a583c3c0bc"}
        $.each(data.personObj,function (i,v) {
            d+="&ids="+v.id
            d+="&names="+v.name

        })
        d+="&sourceId="+data.sourceId.id
        return d;
    },

    /**
     * 发送系统消息
     * @param msg 消息内容 {'msgType':pageUtils.MSG_TYPE_SCHEDULE,
                            'title':'消息标题',
                            'content':'消息内容',
                            businessId:业务数据id,
                            alertTime:"2016-11-11 11:15:04"  //可选：默认当前发送时间
                         }
     * @param receivers 接收人数组 item:{receiverId:userId,receiverName:userName}
     */
    sendMessage:function (msg,receivers) {
        if (msg && receivers && receivers.length > 0) {
            var that = this;
            var typeMapUrl = {};
            typeMapUrl[that.MSG_TYPE_SCHEDULE] = 'container/gov/detect/schedule.jsp';
            typeMapUrl[that.MSG_TYPE_MEETINGNOTICE] = 'container/gov/office/meetingnotice.jsp';
            typeMapUrl[that.MSG_TYPE_PUBINFO] = 'container/gov/office/pubinfo.jsp';
            typeMapUrl[that.MSG_TYPE_POLLUTANTPAYMENT] = 'container/gov/exelaw/pollutantPayment.jsp';
            typeMapUrl[that.MSG_TYPE_CREATEMODEDETAIL] = 'container/gov/office/createModeDetailForUpload.jsp';
            typeMapUrl[that.MSG_TYPE_MONITOR_OFFICE] = 'container/gov/dispatch/lawManage.jsp?role=env_pro_sta';
            typeMapUrl[that.MSG_TYPE_MONITOR_CASE] = 'container/gov/dispatch/lawManage.jsp?role=monitor_master';
            typeMapUrl[that.MSG_TYPE_law_manage] = 'container/gov/dispatch/lawManage.jsp?role=env_pro_sta';
            typeMapUrl[that.MSG_TYPE_trustMonitor] = 'container/gov/exelaw/receiveTrustMonitor.jsp';
            typeMapUrl[that.MSG_TYPE_receiveTrustMonitor] = 'container/gov/detect/monitoring_station_office.jsp';
            typeMapUrl[that.MSG_TYPE_monitoring_station_office] = 'container/gov/detect/monitoring_station_master.jsp';
            typeMapUrl[that.MSG_TYPE_monitoring_station_master] = 'container/gov/detect/monitoring_station_person.jsp';
            msg.senderId = userId;
            msg.senderName = userName;
            msg.detailsUrl = typeMapUrl[msg.msgType];
            msg.receivers = JSON.stringify(receivers);
            var sendResult = false;
            $.ajax({
                url:rootPath + "/action/S_alert_Message_sendMessage.action",
                type:"post",
                dataType:"json",
                data:msg,
                success:function (result) {
                    sendResult = result;
                }
            });
            return sendResult;
        }else{
            return false;
        }

    },

    /**
     * 操作日志
     * @param log {opType:'1',opModule:'监控中心',opContent:'',refTableId:''}
     * @param opType 操作类型 1新增，2修改，3删除，4发送
     * @param model 操作模块 (如：企业台账，阀值管理。。)
     * @param content 操作内容 (自定义 如"id为***的基本信息")
     * @param refTableId 关联数据ID(可为null)
     * @return
     */
    saveOperationLog:function (log) {
        $.ajax({
            url:rootPath + "/action/S_alert_SysOperationLog_saveOperationLog.action",
            type:"post",
            data:log,
            success:function (msg) {
                if (msg=='ok'){
                    console.log("日志保存成功")
                }

            }
        });
    },

    /**
     *  从配置字典中加载select标签选项
     * @param selector
     * @param code  code={code:"monitor_office_source"};
     */
    appendOptionFromDictCode:function(selector,code) {
        var options=dict.getDctionnary(code)
        $.each(options,function (i,v) {
            var option = $("<option>").val(v.code).text(v.name);
            $(selector).append(option);
        })
    },

    toUrl:function (url) {
        var isMainJsp = location.href.indexOf("main.jsp") > 0 ? true:false;
        if (isMainJsp){
            $("#level2content").html("");
            $("#level2content").load(url,arguments[1]);
        }else{
            //查找跳转页面的所在的主菜单
            var urlMainMenu = "";
            var subMenuId = "";
            for(var mainMenuCode in this._subMenu) {
                var subMenus = this._subMenu[mainMenuCode];
                for (var i = 0; i < subMenus.length; i++) {
                    var subMenu = subMenus[i];
                    if (subMenu.url && subMenu.url.indexOf(url) != -1) {
                        subMenuId = subMenu.id;
                        urlMainMenu = mainMenuCode;
                    }
                }
            }
            if (urlMainMenu) {
                location.href = rootPath + "/main.jsp?menuCode="+urlMainMenu+"&subMenuId="+subMenuId+"&SToken=" + SToken;
            }else{
                Ewin.alert("未找到地址");
            }
        }

    },
    findAttachmentIds: function (businessId,attachmentType) {
        var ids = [];
        var attachments = this.findAttachment(businessId, attachmentType);
        for (var i = 0; i < attachments.length; i++) {
            ids.push(attachments[i].id);
        }
        return ids;
    },
    findAttachment: function (businessId,attachmentType) {
        var attachments = [];
        $.ajax({
            url:rootPath + "/action/S_attachment_Attachment_listAttachment.action",
            type:"post",
            async:false,
            dataType:"json",
            data:{'businessId':businessId,'attachmentType':attachmentType},
            success:function (result) {
                if (result && result.length > 0){
                    for(var i = 0; i < result.length; i++){
                        attachments.push(result[i]);
                    }
                }
            }
        });
        return attachments;
    },

    getTableHeight:function () {
        //console.log($('.dealBox').outerHeight(true)+$('.banner').outerHeight(true)+$('.linear').outerHeight(true));
        return $(window).height() - $('.dealBox').outerHeight(true) - $('.banner').outerHeight(true)-$('.linear').outerHeight(true)-8;
    },
    /**
     * 获取表单高度
     * @param fitHeight 无滚动条的高度
     * @returns {number}
     */
    getFormHeight:function (fitHeight) {
        var screenFitHeihgt = $(window).height()-220;
        var heights = [fitHeight,screenFitHeihgt,this.FROM_HEIGHT];
        heights.sort();
        return heights[0];
    },
    /**
     * 转换bootstrapTable 参数为本地参数
     * @param params
     * @returns {{}}
     */
    localParams: function localParams(params) {
        var localParams = {};
        //分页参数
        localParams.take = params.limit;
        localParams.skip = params.offset;
        localParams.page = params.offset / params.limit + 1;
        localParams.pageSize = params.limit;
        var jsonData = $('.queryBox').find('form').formSerializeObject();
        if(!$.isEmptyObject(jsonData)){
            $.extend(localParams,jsonData);
            //Object.assign(localParams, jsonData);
        }
        return localParams;
    },
    /**
     * 序列化特定的查询区域form
     * @param params
     * @param queryForm
     * @returns {{}}
     */
    getBaseParams:function (params,queryForm){
        if(!queryForm){
            queryForm = $('.queryBox').find('form');
        }
        var localParams = {};
        //分页参数
        localParams.take = params.limit;
        localParams.skip = params.offset;
        if(params.offset){
            localParams.page = params.offset / params.limit + 1;
        }else{
            localParams.page = 1;
        }
        localParams.pageSize = params.limit;
        var jsonData = $(queryForm).formSerializeObject();
        if(!$.isEmptyObject(jsonData)){
            $.extend(localParams,jsonData);
            //Object.assign(localParams, jsonData);
        }
        return localParams;
    },
    /**
     * 获取radio Value
     * @param name
     */
    getRadioValue:function (name) {
        var rv;
        $("input[name='"+name+"']").each(function (index, radio) {
            if ($(radio).prop("checked")) {
                rv = $(radio).val()+"";
            }
        });
        return rv;
    },
    /**
     * 设置radio value
     * @param name
     * @param value
     */
    setRadioValue:function (name, value) {
        $("input[name='"+name+"']").each(function (index,radio) {
            var rv = $(radio).val();
            if (value && rv==value) {
                $(radio).prop("checked",true);
            }else{
                $(radio).prop("checked",false);
            }
        });
    },
    getStr:function (str) {
        if (str){
            return str;
        }
        return ""

    },
    filterUndefine:function (str) {
        if (str){
            return str;
        }else{
            return "";
        }
    },
    /**
     * 截取字符前10位 日期串获取前10位
     * @param str
     * @returns {string}
     */
    sub10:function (str) {
        if(str){
            return str.substr(0,10);
        }
    },
    /**
     * 截取字符前16位 年月日时分
     * @param str
     * @returns {string}
     */
    sub16:function (str) {
        if(str){
            return str.substr(0,16);
        }
    },

    /**
     * 名字列表
     * @param str
     * @returns {string}
     */
    sub30:function (str) {
        if(str){
            return str.substr(0,30);
        }
    },
    //加载菜单
    _mainMenu: [],//主菜单
    _subMenu:{}, //子菜单
    _getSubMenu: function(data) {//该函数取得被指定菜单激活的下拉式菜单或子菜单的句柄。
        var that = this;
        var items = [];//定义一个空数组items
        $.each(data, function (k, v) {//data目标数组，k数组的下标，v数组的元素
            var item = {
                id: v.id,
                text: v.text,
                url: v.navUrl
            }, children;

            items.push(item);

            children = v.childrenData;
            if (children && children.length > 0) {
                item.items = that._getSubMenu(children);
                item.expanded = true;
                delete item.url;
            }
        });
        return items.length > 0 ? items : null;
    },
    loadMenu:function (callback) {
        var that = this;
        if (this._mainMenu.length == 0) {
            //加载菜单
            $.ajax({
                url: apportalRootPath + '/main/app.action',
                dataType: 'jsonp',
                jsonp: '_ca11back',
                data: {
                    method: 'appMenuItem',
                    appId: '402883b3577422f00157743c07f10002',
                    subSysId: '402883b3577422f00157743f33440003',
                    requestMode: 'ajax',
                    SToken: SToken
                },
                success: function (data) {
                    data = JSON.parse(data);//此示例使用 JSON.parse 将 JSON 字符串转换为对象
                    //初始化菜单数据结构
                    $.each(data.childrenData, function (k, v) {
                        that._mainMenu.push({
                            code: v.name,
                            text: v.text
                        });
                        that._subMenu[v.name] = that._getSubMenu(v.childrenData);
                    });

                    callback(that._mainMenu,that._subMenu);
                }
            });
        }else{
            callback(that._mainMenu,that._subMenu);
        }
    },
    loading:function(msg){
        var showMsg = '数据载入中，请稍后......';
        if(msg!=undefined && msg!=""){
            showMsg = msg;
        }
        var returnMsg = '<table id="loadingHTML" width=100% height=100% border=0 align=center valign=middle>'
        + '<tr height=50%><td align=center>&nbsp;</td></tr>'
        + '<tr><td align=center></td></tr>'
        + '<tr><td align=center>'+showMsg+'</td></tr>'
        + '<tr height=50%><td align=center>&nbsp;</td></tr>'
        + '</table>';
        return returnMsg;
    },
    /**
     * 导入页面
     * @param id
     * @param url
     */
    loadPageOfContent:function(id,url){
        $(id).html(pageUtils.loading()); // 设置页面加载时的loading图片
        $(id).load(url); // ajax加载页面
    },
    /**
     * 检查开始时间和结束时间
     * @param startTime
     * @param endTime
     */
    checkSearchFormTimes:function(startTime,endTime){
        if( (startTime && !endTime) || ((!startTime && !endTime) || startTime<=endTime)){
            return true;
        }else{
            /*if(startTime && !endTime){
                Ewin.alert("缺少结束时间！");
            }*/
            if(!startTime && endTime){
                Ewin.alert("缺少 [开始时间]！");
            }
            if(startTime>endTime){
                Ewin.alert("[结束时间] 要小于 [开始时间]！");
            }
            return false;
        }
    },
    getAllBlockMap:function(){
        var returnData={};
        $.ajax({
            url: rootPath + "/action/S_composite_BlockLevel_getAllBlockLevelAndBlock.action",
            method:'post',
            async :false,
            dataType:"json",
            success:function(data) {
                if(data){
                    $.each(data,function(k,v){
                        returnData[v.id] = v;
                    })
                }
            }
        });
        return returnData;
    }
};
(function($){
    $.fn.formSerializeObject = function(){
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if(this.value != undefined && this.value !=''){
                if (o[this.name] !== undefined) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            }
        });
        return o;
    };
    $.fn.allFormSerializeObject = function(){
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
    var BootstrapAlerts={
        'warning':'警告！',
        'success':'提示：',
        'info':'信息：',
        'danger':'错误！'
    }
    $.fn.BootstrapAlertMsg = function(type,msg,time){
        var id = 'BootstrapAlert'+(new Date().getTime());
        var html = '<div id="'+id+'" class="alert alert-'+type+'" style="font-size: 15px;text-align: center;">'
            + '<a href="#" class="close" data-dismiss="alert">'
            + '&times;</a>'
            + '<strong>'+BootstrapAlerts[type]+'</strong>'+msg+'</div>';
        $(this).prepend(html);
        setTimeout(function(){
            $('#'+id).remove();
        },time);
    };
    $.fn.BootstrapConfirm = function(msg,func){
        var id = 'BootstrapConfirm'+(new Date().getTime());
        var html = '<div id="'+id+'" class="alert alert-info" style="font-size: 15px;text-align: center;">'
            + '<a href="#" class="close" data-dismiss="alert">'
            + '&times;</a>'
            + '<strong>提示：</strong>'+msg
            + '<br/><button id="BootstrapConfirmMakeSure" type="button" class="btn btn-primary">确定</button>'
            + '<button id="BootstrapConfirmCancel" type="button" class="btn btn-default" style="margin-left: 20px;">取消</button></div>';
        $(this).prepend(html);
        $('#BootstrapConfirmMakeSure').click(function(){
            $('#'+id).remove();
            func();
        })
        $('#BootstrapConfirmCancel').click(function(){
            $('#'+id).remove();
        })
    };
    $.fn.BootstrapExport = function(clickObj,clickOptions){
        var that = this,
            currentTimeId = new Date().valueOf(),
            defaultOptions = {
                fileName:'下载文件'+new Date().valueOf(),
                type: 'excel',
                escape: false,
                dropMenu:{
                    0:{type:'selected',name:'下载已选择数据'},
                    1:{type:'basic',name:'下载当前页数据'},
                    2:{type:'all',name:'下载所有数据'}
                }
            },
            downloadTable,
            hiddenTableDiv,
            downloadTableData=[],
            downloadOptions={};
        var ExportObj = {
            init:function(){
                $('.hiddenTableDiv').remove();
                var initThat = this;
                initThat.createDownLoad();
                var thisOptions = $.extend({}, defaultOptions, clickOptions);
                if(clickObj){
                    var ulHtml = '<div class="export btn-group">'
                        + '<button id="export" type="button" class="btn btn-sm btn-success" data-toggle="dropdown">'
                        + '<span class="glyphicon glyphicon-export"></span>导出</button>'
                        + '<ul id="dropdownMenu'+currentTimeId+'" class="dropdown-menu" role="menu">';
                    $.each(thisOptions.dropMenu,function(k,v){
                        ulHtml +='<li data-type="' + v.type + '"><a href="javascript:void(0)">'+v.name+'</a></li>';
                    });
                    ulHtml +='</ul></div>';
                    $(clickObj).replaceWith(ulHtml);
                    $('#dropdownMenu'+currentTimeId).find('li').click(function(){
                        thisOptions.exportDataType = $(this).data('type');
                        initThat.exportTable(thisOptions);
                    })
                }
            },
            createDownLoad:function(){
                var tableId = "gridTable"+currentTimeId,hiddenTableDivId = "hiddenTableDiv"+currentTimeId;
                $('body').append('<div id="'+hiddenTableDivId+'" style="display: none" class="hiddenTableDiv"></div>');
                hiddenTableDiv = $('#'+hiddenTableDivId);
                hiddenTableDiv.append('<table id="'+tableId+'" class="table table-striped table-responsive"></table>');
                downloadTable = $('#'+tableId);
                var tableOptions = that.bootstrapTable('getOptions');
                var tableClumns = tableOptions.columns[0];
                that.bootstrapTable('refreshOptions',{onLoadSuccess:function(data){
                    var newColumns = [];
                    var columnIndex = 0;
                    $.each(tableClumns,function(k,v){
                        if(v.isDown){
                            v.visible = true;
                            newColumns[columnIndex] = v;
                            columnIndex +=1;
                        }
                    });
                    var newTableOptions= {
                        contentType: tableOptions.contentType,
                        sidePagination:tableOptions.sidePagination,
                        url: tableOptions.url,
                        height: tableOptions.height,
                        pageSize:data.total,
                        pageList:tableOptions.pageList,
                        method:tableOptions.method,
                        pagination:tableOptions.pagination,
                        queryParams:tableOptions.queryParams,
                        columns:newColumns,
                        onLoadSuccess:function(downloadData){
                            downloadTableData = downloadData;
                        }
                    };
                    downloadTable.bootstrapTable(newTableOptions);
                }});
            },
            exportTable:function(options){
                options = $.extend({}, defaultOptions, options);
                var exportObj = this;
                downloadOptions = options;
                switch (options.exportDataType){
                    case 'basic':
                        var currentData = that.bootstrapTable('getData',{useCurrentPage:true});
                        downloadTable.bootstrapTable('load',this.getLoadData(currentData));
                        exportObj.doExport();
                        downloadTable.bootstrapTable('load',downloadTableData);
                        break;
                    case 'selected':
                        var selectedData = that.bootstrapTable('getAllSelections');
                        if(selectedData.length>0){
                            downloadTable.bootstrapTable('load',this.getLoadData(selectedData));
                            exportObj.doExport();
                            downloadTable.bootstrapTable('load',downloadTableData);
                            break;
                        }else{
                            Ewin.alert('没有选择的数据可下载！');
                            break;
                        }
                    default:
                        downloadTable.bootstrapTable('refreshOptions',{
                            onLoadSuccess:function(downloadData){
                                downloadTableData = downloadData;
                                downloadTable.bootstrapTable('load',downloadTableData);
                                exportObj.doExport();
                            }
                        });
                }
            },
            doExport:function(){
                hiddenTableDiv.show();
                downloadTable.tableExport(downloadOptions);
                hiddenTableDiv.hide();
            },
            getLoadData:function(data){
                return {
                    rows:data,
                    total:data.length
                }
            }
        }
        ExportObj.init();
        return ExportObj;
    };
})(jQuery);

/*消息提示框*/
(function ($) {

    window.Ewin = function () {
        var html = '<div id="[Id]" class="modal fade" style="z-index:9999;" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog modal-sm">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '<p>[Message]</p>' +
            '</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>' +
            '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';


        var dialogdHtml = '<div id="[Id]" class="modal fade" style="z-index:9999;" data-backdrop="static"  tabindex="-1" role="dialog" aria-labelledby="dailogModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
        var generateId = function () {
            var date = new Date();
            return 'mdl' + date.valueOf();
        }
        var init = function (options) {
            options = $.extend({}, {
                title: "操作提示",
                message: "提示内容",
                btnok: "确定",
                btncl: "取消",
                width: 200,
                auto: false
            }, options || {});
            var modalId = generateId();
            var content = html.replace(reg, function (node, key) {
                return {
                    Id: modalId,
                    Title: options.title,
                    Message: options.message,
                    BtnOk: options.btnok,
                    BtnCancel: options.btncl
                }[key];
            });
            $('body').append(content);
            $('#' + modalId).modal({
                width: options.width,
                backdrop: 'static'
            });
            $('#' + modalId).on('hide.bs.modal', function (e) {
                $('body').find('#' + modalId).remove();
            });
            return modalId;
        }

        return {
            alert: function (options) {
                if (typeof options == 'string') {
                    options = {
                        message: options,
                        hideTimes:5000
                    };
                }
                var defaultHideTime = options.hideTimes>0?options.hideTimes:5000;
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-success').addClass('btn-primary');
                modal.find('.cancel').hide();
                setTimeout(function(){
                    modal.find('.ok').trigger('click');
                },defaultHideTime);
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () { callback(true); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            confirm: function (options) {
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
                modal.find('.cancel').show();
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () { callback(true); });
                            modal.find('.cancel').click(function () { callback(false); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            dialog: function (options) {
                options = $.extend({}, {
                    title: 'title',
                    url: '',
                    width: 800,
                    height: 550,
                    onReady: function () { },
                    onShown: function (e) { }
                }, options || {});
                var modalId = generateId();

                var content = dialogdHtml.replace(reg, function (node, key) {
                    return {
                        Id: modalId,
                        Title: options.title
                    }[key];
                });
                $('body').append(content);
                var target = $('#' + modalId);
                target.find('.modal-body').load(options.url);
                if (options.onReady())
                    options.onReady.call(target);
                target.modal();
                target.on('shown.bs.modal', function (e) {
                    if (options.onReady(e))
                        options.onReady.call(target, e);
                });
                target.on('hide.bs.modal', function (e) {
                    $('body').find(target).remove();
                });
            }
        }
    }();
    Date.prototype.format = function(format){
        var o = {
            "M+" : this.getMonth()+1, //month
            "d+" : this.getDate(), //day
            "h+" : this.getHours(), //hour
            "m+" : this.getMinutes(), //minute
            "s+" : this.getSeconds(), //second
            "q+" : Math.floor((this.getMonth()+3)/3), //quarter
            "S" : this.getMilliseconds() //millisecond
        }

        if(/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }

        for(var k in o) {
            if(new RegExp("("+ k +")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
            }
        }
        return format;
    }

    /**
     * 重置按钮
     */
    window.resetQuery=function() {
        var forms=$('.queryBox').find('form');
        $.each(forms,function (i,v) {
            v.reset();
        })
        $(".queryBox").find("input[type!='radio'][type!='checkbox']").val("");
    }


    /**
     * 加载<select>标签的选项
     * @param selector
     * @param optionsSetting  例如：{code:"id",name:"orgName"}
     */
    window.ajaxLoadOption=function(url,selector,optionsSetting){
        $.ajax({
            url: url,
            type:"post",
            dataType:"json",
            success:function (options) {
                $(selector).empty();
                $(selector).append($("<option>").val('').text("全部"));
                $.each(options,function (i,v) {
                    var option = $("<option>").val(v[optionsSetting.code]).text(v[optionsSetting.name]);
                    $(selector).append(option);
                })
            }
        });
    }

})(jQuery);