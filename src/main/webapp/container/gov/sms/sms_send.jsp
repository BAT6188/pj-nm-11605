<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/common_include.jsp" %>
    <title>通用发送短信dialog</title>
</head>
<body style="overflow: hidden;">

<!--发送短信dialog-->
<div class="modal fade" id="smsSendDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">地图标绘</h4>
            </div>
            <div class="modal-body" style="padding: 0;">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="smsSendDialogOK">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/office/scripts/sms_send.js"></script>
</body>
</html>
