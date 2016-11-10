//@ sourceURL=message_dialog.js
var MessageDialog = function () {
    var dialog= {
        _modal:$("#messageDialog"),
        _msgTableBody:$("#messageDialog").find(".modal-body table tbody"),
        _countElement:undefined,
        _refreshCountClock:undefined,
        REFRESH_DELAY:5000,
        _userId:undefined,
        _loadedMsgTraceList:[],
        RECEIVE_STATUS_UNRECEIVE:"1",
        init:function () {
            var that = this;
            dict.init('msg_type','msg_receive_status');

            that.modal("show.bs.modal", function () {
                //that.sendMsg();
                //打开窗口前，刷新列表
                that.loadMsgListToTable(that.getUserId());
            });
            that.modal("shown.bs.modal", function () {
                //打开窗口后，设置所有未消息为已读
                var loadedMsgList = that.getLoadedMsgTraceList();
                var viewedUnReceiveTraceIds = [];
                for (var i =0; i < loadedMsgList.length; i++) {
                    var msgTrace = loadedMsgList[i];
                    if (msgTrace.receiveStatus == that.RECEIVE_STATUS_UNRECEIVE){
                        viewedUnReceiveTraceIds.push(msgTrace.id);
                    }
                }
                that.setMsgTraceReceive(viewedUnReceiveTraceIds);
            });
        },
        modal:function () {
            if (arguments.length == 1) {
                if (typeof arguments[0] == "string") {
                    this._modal.modal(arguments[0]);
                }
                if (typeof arguments[0] == "object") {
                    var options = arguments[0];
                    if (options['userId']) {
                        this.setUserId(options['userId']);
                    }
                    if (options['countElement']) {
                        this.setCountElement(options['countElement']);
                    }
                }

            }else if (arguments.length == 2) {
                this._modal.on(arguments[0],arguments[1]);
            }else{
                this._modal.modal("toggle");
            }
        },
        startRefreshMsgCountClock:function () {
            var that = this;
            that.refreshMsgCount();
            that.stopRefreshCountClock();
            var refreshCountClock = setInterval(function () {
                if (that.getCountElement()) {
                    that.refreshMsgCount();
                }
            },MessageDialog.REFRESH_DELAY);
            that.setRefreshCountClock(refreshCountClock);
        },
        refreshMsgCount:function () {
            var newMsgCount = this.getNewMsgCountByUserId(this.getUserId());
            this.setMsgCount(newMsgCount);
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
                            that.refreshMsgCount();
                        }
                    }
                });
            }
        },
        loadMsgListToTable:function(userId) {
            var that = this;
            that.setLoadedMsgTraceList([]);
            that.getUserMsgList(userId,function (msgTraceList) {
                //载入显示列表

                if (msgTraceList && msgTraceList.length > 0) {
                    for(var i =0; i < msgTraceList.length; i++) {
                        that.addMsgToTable(msgTraceList[i]);
                    }

                }else{
                    that._msgTableBody.html("<tr><td class='text-center'>暂无消息</td></tr>");
                }
            })
        },
        getUserMsgList:function (userId,callback) {
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
        addMsgToTable:function(msgTrace) {
            var that = this;
            if (msgTrace && msgTrace.message){
                var msg = msgTrace.message;
                var receiveStatusName = "";
                var dictCodeMsgReceiveStatus = 'msg_receive_status';
                if (msgTrace.receiveStatus) {
                    receiveStatusName = dict.get(dictCodeMsgReceiveStatus,msgTrace.receiveStatus);
                }else {//默认显示未接收
                    receiveStatusName = dict.get(dictCodeMsgReceiveStatus,that.RECEIVE_STATUS_UNRECEIVE);
                }
                var dictCodeMsgType = 'msg_type';
                var msgTrHtml = '<tr>'+
                    '<td style="width: 80px;"><span>'+dict.get(dictCodeMsgType,msg.msgType)+'</span></td>'+
                    '<td><span>'+that.filterUndefine(msg.title)+'</span></td>'+
                    '<td><span>'+that.filterUndefine(msg.content)+'</span></td>'+
                    '<td style="width: 140px;"><span>'+that.filterUndefine(msg.createTime)+'</span></td>'+
                    '<td style="width: 80px;"><span class="text-danger">'+receiveStatusName+'</span></td>'+
                    '<td><button type="button" class="btn btn-primary btn-sm btn-details" data-details-url="'+msg.detailsUrl+'">详情</button></td>'+
                    '</tr>';
                var msgTr = $(msgTrHtml);
                msgTr.find(".btn-details").bind("click",function () {
                    var detailsUrl = $(this).data("details-url");
                    pageUtils.toUrl(detailsUrl);
                });
                that._msgTableBody.append(msgTr);
                that.pushToLoadedMsgTraceList(msgTrace);
            }else{
                return false;
            }

        },
        getNewMsgCountByUserId:function (userId) {
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
        sendMsg:function () {
            var receivers = [];
            var receiver1 = {receiverId:'dev',receiverName:'开发人员'};
            receivers.push(receiver1);
            var receiver2 = {receiverId:'hbzz',receiverName:'环保站长'};
            receivers.push(receiver2);
            var msg = {
                'msgType':1,
                'title':'消息标题',
                'content':'消息内容'
            };
            pageUtils.sendMessage(msg, receivers);
        },
        filterUndefine:function (str) {
            if (str){
                return str;
            }else{
                return "";
            }
        },
        setUserId:function (userId) {
            this._userId = userId;
        },
        getUserId:function () {
            return this._userId;
        },
        pushToLoadedMsgTraceList:function (msgTrace) {
            this._loadedMsgTraceList.push(msgTrace);
        },
        setLoadedMsgTraceList:function (msgList) {
            this._loadedMsgTraceList = msgList;
        },
        getLoadedMsgTraceList:function () {
            return this._loadedMsgTraceList;
        },
        setMsgCount:function (count) {
            if (this._countElement && this._countElement.text) {
                this._countElement.text(count);
            }
        },
        setCountElement:function (countElement) {
            this._countElement = countElement;
            if (countElement) {
                this.startRefreshMsgCountClock();
            }
        },
        getCountElement:function () {
            return this._countElement;
        },
        setRefreshCountClock:function (refreshCountClock) {
            this._refreshCountClock = refreshCountClock;
        },
        getRefreshCountClock:function () {
            return this._refreshCountClock;
        },
        stopRefreshCountClock:function () {
            if (this.getRefreshCountClock()) {
                clearInterval(this.getRefreshCountClock());
                this.setRefreshCountClock(undefined);
            }
        }



    };
    dialog.init();
    return dialog;
}();



