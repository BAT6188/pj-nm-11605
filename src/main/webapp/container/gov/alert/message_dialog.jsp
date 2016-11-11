<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>消息列表弹出框</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/common/gis/css/map_mark.css" type="text/css">
</head>
<body style="overflow: hidden;">

<!--消息列表弹出框-->
<div class="modal fade" id="messageDialog" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
<script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/alert/script/message_dialog.js"></script>
</body>
</html>
