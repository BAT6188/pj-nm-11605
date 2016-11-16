<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>消息列表弹出框</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/common/gis/css/map_mark.css" type="text/css">
</head>
<body style="overflow: hidden;">

<!--消息列表弹出框-->
<div class="modal fade" id="messageListDialog" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="messageListDialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">消息列表</h4>
            </div>
            <div class="modal-body" style="padding: 0;height: 265px;overflow-y: scroll;">
                <table class="table table-responsive" style="margin-bottom: 0px;">
                    <tbody>
                        <%--<tr>
                            <td><span>消息类型</span></td>
                            <td><span>消息标题</span></td>
                            <td><span>消息内容</span></td>
                            <td><span>2016//8/29 15:12</span></td>
                            <td><span class="text-danger">未接收</span></td>
                            <td><button type="button" class="btn btn-primary btn-sm">详情</button></td>
                        </tr>--%>
                    </tbody>
                </table>
                <div class="text-center more-link"><span>暂无消息</span></div>
            </div>
        </div>
    </div>
</div>
<!--消息列表弹出框-->
<div class="modal fade" id="messageDialog" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="messageDialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">新消息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="message_title" class="col-sm-2 control-label">标题：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="message_id" name="id">
                            <input type="text" id="message_title" name="title" class="form-control" />
                        </div>
                        <label for="message_msgType" class="col-sm-2 control-label">消息类型：</label>
                        <div class="col-sm-3">
                            <input type="text" id="message_msgType" name="msgType" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="message_senderName" class="col-sm-2 control-label">发送人：</label>
                        <div class="col-sm-3">
                            <input type="text" id="message_senderName" name="senderName" class="form-control" />
                        </div>
                        <label for="message_alertTime" class="col-sm-2 control-label">发送时间：</label>
                        <div class="col-sm-3">
                            <input type="text" id="message_alertTime" name="alertTime" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="message_content" class="col-sm-2 control-label">消息内容：</label>
                        <div class="col-sm-10">
                            <textarea id="message_content" name="content" class="form-control"></textarea>
                        </div>
                    </div>
                    
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary btn-details">详情</button>
                <button type="button" class="btn btn-default btn-accept" data-dismiss="modal">接收</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/alert/script/message_dialog.js"></script>
</body>
</html>
