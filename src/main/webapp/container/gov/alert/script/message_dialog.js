//@ sourceURL=message_dialog.js
var MessageDialog = function () {
    var userId,
        msgListModal = $("#messageListDialog"),
        msgListTable = msgListModal.find(".modal-body table tbody"),
        moreLinkDiv = msgListModal.find(".more-link"),
        msgListData = [];

    var newsCountElement,
        refreshNewsCountClock,
        REFRESH_DELAY = 5000;

    var RECEIVE_STATUS_UNRECEIVE = "1" ;
    var dialog= {
        init:function () {
            var that = this;
            dict.init('msg_type','msg_receive_status');

            that.modal("show.bs.modal", function () {
                //that.testSendMsg();
                //打开窗口前，刷新列表
                that.loadMsgList(that.getUserId());
            });
            that.modal("shown.bs.modal", function () {
                //打开窗口后，设置所有未消息为已读
                var loadedMsgList = that.getMsgListData();
                var viewedUnReceiveTraceIds = [];
                for (var i =0; i < loadedMsgList.length; i++) {
                    var msgTrace = loadedMsgList[i];
                    if (msgTrace.receiveStatus == RECEIVE_STATUS_UNRECEIVE){
                        viewedUnReceiveTraceIds.push(msgTrace.id);
                    }
                }
                that.setMsgTraceReceive(viewedUnReceiveTraceIds);
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
        testSendMsg:function () {
            var receivers = [];
            var receiver1 = {receiverId:'dev',receiverName:'开发人员'};
            receivers.push(receiver1);
            var receiver2 = {receiverId:'hbzz',receiverName:'环保站长'};
            receivers.push(receiver2);
            var msg = {
                'msgType':1,
                'title':'消息标题',
                'content':'消息内容',
                'businessId':'123123'
            };
            pageUtils.sendMessage(msg, receivers);
        },
        /**
         * 加载消息列表
         * @param userId
         */
        loadMsgList:function(userId) {
            var that = this;
            //第一次加载未消息
            that.getUserNewMsgList(userId, function (newMsgTraceList) {
                if (newMsgTraceList && newMsgTraceList.length >0) {
                    //如果有新消息清空列表只显示新消息
                    that.clearTable();
                    that.updateMoreLink();
                    that.addMsgTraceListToTable(newMsgTraceList);
                }else{
                    that.getUserHistoryMsgList(userId,function (historyMsgList) {
                        that.addMsgTraceListToTable(historyMsgList);
                    })
                }

            });
        },


        /**
         * 添加消息数据到列表表格
         * @param msgTraceList
         */
        addMsgTraceListToTable: function(msgTraceList){
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
                var dictCodeMsgReceiveStatus = 'msg_receive_status';
                var unReceive = (!msgTrace.receiveStatus || msgTrace.receiveStatus ==RECEIVE_STATUS_UNRECEIVE);
                if (unReceive) {//显示未接收
                    receiveStatusName = dict.get(dictCodeMsgReceiveStatus,RECEIVE_STATUS_UNRECEIVE);
                }else {//已接收
                    receiveStatusName = dict.get(dictCodeMsgReceiveStatus,msgTrace.receiveStatus);
                }
                var dictCodeMsgType = 'msg_type';
                var msgTrHtml = '<tr>'+
                    '<td style="width: 80px;"><span>'+dict.get(dictCodeMsgType,msg.msgType)+'</span></td>'+
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
                    pageUtils.toUrl(rootPath+"/"+detailsUrl);
                });
                msgListTable.append(msgTr);
                that.pushToMsgListData(msgTrace);
            }else{
                return false;
            }

        },
        updateMoreLink:function () {
            var that = this;
            var moreLink = $("<a href='javascript:void(0);' style='color: #337ab7;' >点击加载更多历史消息</a>");
            moreLink.bind("click",function () {
                that.getUserHistoryMsgList(userId, function (historyMsgTraceList) {
                    that.addMsgTraceListToTable(historyMsgTraceList);
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
        setMsgTraceReceive:function (traceIds) {
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
                    }
                });
            }
        },

        getNewsCountByUserId:function (userId) {
            var messageCount;
             $.ajax({
                 url:rootPath + "/action/S_alert_MessageTrace_getNewMsgCountByUserId.action",
                 type:"post",
                 async:false,
                 dataType:"json",
                 data:{"userId":userId},
                 success:function (result) {
                     messageCount = result;
                 }
             });
            return messageCount;
        },


        startRefreshNewsCountClock:function () {
            var that = this;
            that.refreshNewsCount();
            that.stopRefreshNewsCountClock();

            var refreshCountClock = setInterval(function () {
                if (that.getNewsCountElement()) {
                    that.refreshNewsCount();
                }
            },REFRESH_DELAY);
            that.setRefreshNewsCountClock(refreshCountClock);
        },
        stopRefreshNewsCountClock:function () {
            if (this.getRefreshNewsCountClock()) {
                clearInterval(this.getRefreshNewsCountClock());
                this.setRefreshNewsCountClock(undefined);
            }
        },
        refreshNewsCount:function () {
            var newMsgCount = this.getNewsCountByUserId(this.getUserId());
            this.setNewsCount(newMsgCount);
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
        getNewsCountElement:function () {
            return newsCountElement;
        },
        setRefreshNewsCountClock:function (refreshCountClock) {
            refreshNewsCountClock = refreshCountClock;
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
            msgListData.push(msgTrace);
        },
        setMsgListData:function (msgList) {
            msgListData = msgList;
        },
        getMsgListData:function () {
            return msgListData;
        },
        clearTable:function () {
            this.setMsgListData([]);
            msgListTable.html("");
        }

    };
    dialog.init();
    return dialog;
}();



