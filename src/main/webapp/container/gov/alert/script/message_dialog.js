//@ sourceURL=message_dialog.js
var MessageDialog = function () {
    var userId,
        msgListModal = $("#messageListDialog"),
        msgListTable = msgListModal.find(".modal-body table tbody"),
        moreLinkDiv = msgListModal.find(".more-link"),
        msgListData = [];
    //news 新消息
    var newsCountElement,
        refreshNewsCountClock,
        REFRESH_DELAY = 30000;
    var msgModal = $("#messageDialog"),
        msgForm = msgModal.find("form"),
        isAlert = true,
        MSG_FORM_DATA_BIND_KEY = "msg",
        newsRank = [];
    if (msgIsAlert == "false") {
        isAlert = false;
    }

    var RECEIVE_STATUS_UNRECEIVE = "1",
        RECEIVE_STATUS_RECEIVED = "2",
        DICT_CODE_MSG_TYPE = "msg_type",
        DICT_CODE_MSG_RECEIVE_STATUS = "msg_receive_status";
    var dialog= {
        init:function () {
            var that = this;
            //that.testSendMsg();
            initMsgModal();
            dict.init(DICT_CODE_MSG_TYPE,DICT_CODE_MSG_RECEIVE_STATUS);

            that.modal("show.bs.modal", function () {
                //打开窗口前，刷新列表
                that.loadMsgListToTable(that.getUserId());
            });
            that.modal("shown.bs.modal", function () {
                //打开窗口后，设置所有未消息为已读
                var loadedMsgList = that.getMsgListData();
                var viewedUnReceiveTraceIds = [];
                for (var i =0; i < loadedMsgList.length; i++) {
                    var msgTrace = loadedMsgList[i];
                    if (msgTrace.receiveStatus == RECEIVE_STATUS_UNRECEIVE){
                        viewedUnReceiveTraceIds.push(msgTrace.id);
                        msgTrace.receiveStatus = RECEIVE_STATUS_RECEIVED;
                    }
                }
                that.setMsgTraceReceive(viewedUnReceiveTraceIds);
            });
            that.modal("hidden.bs.modal", function () {
                //关闭窗口同步数据状态到表格
                that.syncDataToTable();
            });
        },
        modal:function () {
            if (arguments.length == 1) {
                if (typeof arguments[0] == "string") {
                    msgListModal.modal(arguments[0]);
                }
                if (typeof arguments[0] == "object") {
                    var options = arguments[0];
                    if (options['userId']) {
                        this.setUserId(options['userId']);
                    }
                    if (options['countElement']) {
                        this.setNewsCountElement(options['countElement']);
                    }
                }

            }else if (arguments.length == 2) {
                msgListModal.on(arguments[0],arguments[1]);
            }else{
                msgListModal.modal("toggle");
            }
        },
        i:0,
        testSendMsg:function () {
            var receivers = [];
            var receiver1 = {receiverId:'msg',receiverName:'消息测试'};
            receivers.push(receiver1);
            /*var receiver2 = {receiverId:'hbzz',receiverName:'环保站长'};
            receivers.push(receiver2);    */
            var msg = {
                'msgType':1,
                'title':'消息标题'+(this.i++),
                'content':'消息内容',
                'businessId':'123123'
            };
            pageUtils.sendMessage(msg, receivers);
        },
        /**
         * 加载消息列表
         * @param userId
         */
        loadMsgListToTable:function(userId) {
            var that = this;
            //第一次加载未消息
            that.getUserNewMsgList(userId, function (newMsgList) {
                if (newMsgList && newMsgList.length >0) {
                    //如果有新消息,清空列表只显示新消息
                    that.clearMsgList();
                    that.updateMoreLink();
                    that.addMsgListToTableAndData(newMsgList);
                }else if(!msgListData || msgListData <= 0){//如果列表为空，加载历史消息，否则不加载
                    that.getUserHistoryMsgList(userId,function (historyMsgList) {
                        that.addMsgListToTableAndData(historyMsgList);
                    })
                }else{
                    //没有新消息，消息列表有其他消息，所以什么也不做。
                }

            });
        },
        syncDataToTable:function () {
            this.clearMsgListTable();
            this.addMsgListToTable(msgListData);

        },
        addMsgListToTableAndData:function (msgTraceList) {
            this.addMsgListToTable(msgTraceList);
            this.pushToMsgListData(msgTraceList);
        },

        /**
         * 添加消息数据到列表表格
         * @param msgTraceList
         */
        addMsgListToTable: function(msgTraceList){
            var that = this;
            if (msgTraceList && msgTraceList.length > 0) {
                for(var i =0; i < msgTraceList.length; i++) {
                    that.addMsgToTable(msgTraceList[i]);
                }
                that.updateMoreLink();
            }else{
                var loadedList = that.getMsgListData();
                if (loadedList && loadedList.length > 0) {
                    moreLinkDiv.html("没有更多消息了");
                }else{
                    moreLinkDiv.html("暂无消息");
                }
            }
        },
        addMsgToTable:function(msgTrace) {
            var that = this;
            if (msgTrace && msgTrace.message){
                var msg = msgTrace.message;
                var receiveStatusName = "";
                var unReceive = (!msgTrace.receiveStatus || msgTrace.receiveStatus ==RECEIVE_STATUS_UNRECEIVE);
                if (unReceive) {//显示未接收
                    receiveStatusName = dict.get(DICT_CODE_MSG_RECEIVE_STATUS,RECEIVE_STATUS_UNRECEIVE);
                }else {//已接收
                    receiveStatusName = dict.get(DICT_CODE_MSG_RECEIVE_STATUS,msgTrace.receiveStatus);
                }
                var msgTrHtml = '<tr>'+
                    '<td style="width: 80px;"><span>'+dict.get(DICT_CODE_MSG_TYPE,msg.msgType)+'</span></td>'+
                    '<td><span>'+that.filterUndefine(msg.title)+'</span></td>'+
                    '<td><span>'+that.filterUndefine(msg.content)+'</span></td>'+
                    '<td style="width: 140px;"><span>'+that.filterUndefine(msg.alertTime)+'</span></td>'+
                    '<td style="width: 80px;"><span class="'+(unReceive?"text-danger":"")+'">'+receiveStatusName+'</span></td>'+
                    '<td><button type="button" class="btn btn-primary btn-sm btn-details" data-details-url="'+msg.detailsUrl+'">详情</button></td>'+
                    '</tr>';
                var msgTr = $(msgTrHtml);
                //绑定详情按钮click to跳转
                msgTr.find(".btn-details").bind("click",function () {
                    var detailsUrl = $(this).data("details-url");
                    msgListModal.modal("hide");
                    pageUtils.toUrl(rootPath+"/"+detailsUrl);

                });
                msgListTable.append(msgTr);
                return true;
            }else{
                return false;
            }

        },
        updateMoreLink:function () {
            var that = this;
            var moreLink = $("<a href='javascript:void(0);' style='color: #337ab7;' >点击加载更多历史消息</a>");
            moreLink.bind("click",function () {
                that.getUserHistoryMsgList(userId, function (historyMsgTraceList) {
                    that.addMsgListToTableAndData(historyMsgTraceList);
                });
            });
            moreLinkDiv.html("");
            moreLinkDiv.append(moreLink);
        },

        getUserNewMsgList:function (userId, callback) {
            $.ajax({
                url:rootPath + "/action/S_alert_MessageTrace_getUserMsgList.action",
                type:"post",
                dataType:"json",
                data:{"userId":userId},
                success:function (msgTraceList) {
                    callback(msgTraceList);
                }
            });
        },
        getUserHistoryMsgList:function (userId,callback) {
            var msgTraceList = this.getMsgListData();
            var oldMsgAlertTime = "";//当前已显示的最旧一条消息id
            if (msgTraceList && msgTraceList.length > 0) {
                var msg = msgTraceList[msgTraceList.length-1].message;
                oldMsgAlertTime = msg.alertTime;
            }else{
                oldMsgAlertTime = new Date().format("yyyy-MM-dd hh:mm:ss");
            }
            $.ajax({
                url:rootPath + "/action/S_alert_MessageTrace_getHistoryByUserId.action",
                type:"post",
                dataType:"json",
                data:{"userId":userId,"oldMsgAlertTime":oldMsgAlertTime},
                success:function (msgTraceList) {
                    callback(msgTraceList);
                }
            });
        },

        /**
         * 设置已查看消息为已读
         */
        setMsgTraceReceive:function (traceIds,callback) {
            var that = this;
            if (traceIds && traceIds.length >0) {
                $.ajax({
                    url:rootPath + "/action/S_alert_MessageTrace_setStatusReceived.action",
                    type:"post",
                    dataType:"json",
                    data:$.param({"ids":traceIds},true),
                    success:function (count) {
                        if (count){
                            that.refreshNewsCount();
                        }
                        if (callback) {
                            callback(count);
                        }

                    }
                });
            }
        },

        getNewsCountByUserId:function (userId,callback) {
             $.ajax({
                 url:rootPath + "/action/S_alert_MessageTrace_getNewMsgCountByUserId.action",
                 type:"post",
                 dataType:"json",
                 data:{"userId":userId},
                 success:function (messageCount) {
                     if ($.isFunction(callback)) {
                         callback(messageCount);
                     }
                 }
             });
        },

        refreshCountAndLoadNewsRank:function () {
            var that = this;
            that.refreshNewsCount(function (newMsgCount) {
                //如果新消息数量大于0，并且提醒标识为真，获取新消息并设置消息队列
                if (newMsgCount > 0 && isAlert) {
                    that.getUserNewMsgList(that.getUserId(), function (newMsgList) {
                        setNewsRank(newMsgList);
                    });
                }
            });

        },

        startRefreshNewsCountClock:function () {
            var that = this;
            that.refreshCountAndLoadNewsRank();
            that.stopRefreshNewsCountClock();

            var refreshCountClock = setInterval(function () {
                that.refreshCountAndLoadNewsRank();
            },REFRESH_DELAY);
            that.setRefreshNewsCountClock(refreshCountClock);
        },
        stopRefreshNewsCountClock:function () {
            if (this.getRefreshNewsCountClock()) {
                clearInterval(this.getRefreshNewsCountClock());
                this.setRefreshNewsCountClock(undefined);
            }
        },
        refreshNewsCount:function (callback) {
            var that = this;
            that.getNewsCountByUserId(that.getUserId(),function (newMsgCount) {
                that.setNewsCount(newMsgCount);
                if ($.isFunction(callback)){
                    callback(newMsgCount);
                }
            });
        },
        setNewsCount:function (count) {
            if (newsCountElement && newsCountElement.text) {
                newsCountElement.text(count);
            }
        },
        setNewsCountElement:function (countElement) {
            newsCountElement = countElement;
            if (countElement) {
                this.startRefreshNewsCountClock();
            }
        },
        setRefreshNewsCountClock:function (refreshCountClock_p) {
            refreshNewsCountClock = refreshCountClock_p;
        },
        getRefreshNewsCountClock:function () {
            return refreshNewsCountClock;
        },
        filterUndefine:function (str) {
            if (str){
                return str;
            }else{
                return "";
            }
        },
        setUserId:function (userId_p) {
            userId = userId_p;
        },
        getUserId:function () {
            return userId;
        },
        pushToMsgListData:function (msgTrace) {
            if ($.isArray(msgTrace)) {
                msgListData = msgListData.concat(msgTrace);
                return true;
            }else if($.type(msgTrace) == "object"){
                msgListData.push(msgTrace);
                return true;
            }else{
                return false;
            }


        },
        setMsgListData:function (msgList) {
            msgListData = msgList;
        },
        getMsgListData:function () {
            return msgListData;
        },
        clearMsgList:function () {
            this.setMsgListData([]);
            setNewsRank([]);
            this.clearMsgListTable();
        },
        clearMsgListTable:function () {
            msgListTable.html("");
        }
    };

    function initMsgModal() {
        disabledMsgForm(true);
        msgModal.on("hidden.bs.modal",function () {
            checkNewsRankAndPopup();
        });
        msgModal.find(".btn-accept").bind("click", function () {
            var msg = msgForm.data(MSG_FORM_DATA_BIND_KEY);
            if (msg && msg.id) {
                //设置已读
                dialog.setMsgTraceReceive([msg.id]);
            }
        });
        msgModal.find(".btn-details").bind("click", function () {
            var msg = msgForm.data(MSG_FORM_DATA_BIND_KEY);
            if (msg && msg.message && msg.message.detailsUrl) {
                //设置已读
                dialog.setMsgTraceReceive([msg.id],function (count) {
                    //跳转页面
                    pageUtils.toUrl(rootPath+"/"+msg.message.detailsUrl);
                });
            }
        });
        msgModal.find(".btn-notAlert").bind("click", function () {
            Ewin.confirm({ message: "确认不再提醒其他新消息吗？" }).on(function (e) {
                if (!e) {
                    return;
                }
                //清空消息列表
                setNewsRank([]);
                setIsAlert(false);
                //设置已读
                var msg = msgForm.data(MSG_FORM_DATA_BIND_KEY);
                if (msg && msg.id) {
                    dialog.setMsgTraceReceive([msg.id]);
                }
                msgModal.modal("hide");
            });
        });
        msgModal.find(".btn-later").bind("click", function () {
            setIsAlert(false);
        });


    }

    function checkNewsRankAndPopup() {
        if (newsRank && newsRank.length > 0) {
            var msg = newsRank.pop();
            popupNewMsg(msg);
        }
    }
    function popupNewMsg(msg){
        var result = setMsgFormData(msg);
        if (result) {
            msgModal.modal("show");
        }

    }
    function setMsgFormData(msg) {
        resetMsgForm();
        if (msg && msg.message) {
            msgForm.data(MSG_FORM_DATA_BIND_KEY,msg);
            var mainMsg = msg.message;
            $("#message_title").val(mainMsg.title);
            $("#message_msgType").val(dict.get(DICT_CODE_MSG_TYPE,mainMsg.msgType));
            $("#message_senderName").val(mainMsg.senderName);
            $("#message_alertTime").val(mainMsg.alertTime);
            $("#message_content").val(mainMsg.content);
            return true;
        }else{
            return false;
        }
    }
    function disabledMsgForm(disabled) {
        msgForm.find(".form-control").attr("disabled",disabled);
    }
    /**
     * 重置表单
     */
    function resetMsgForm() {
        msgForm[0].reset();
        msgForm.removeData(MSG_FORM_DATA_BIND_KEY);
    }

    function setNewsRank(newMsges){
        newsRank = newMsges ;
        checkNewsRankAndPopup();
    }
    function setIsAlert(isAlert_p) {
        isAlert = isAlert_p;
        $.ajax({
            url:rootPath + "/action/S_alert_Message_setIsAlert.action",
            type:"post",
            dataType:"json",
            data:{"isAlert":isAlert_p},
            success:function (success) {

            }
        });
    }
    dialog.init();
    return dialog;
}();



