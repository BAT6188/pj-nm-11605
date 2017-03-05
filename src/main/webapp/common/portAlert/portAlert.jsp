<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>报警提示</title>
</head>
<body style="overflow: hidden;">

<!-- 修改密码 -->
<div class="modal fade" id="portAlertDialog" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title form-title">超标企业</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group" style="padding-left: 28px;" id="alertEnterpriseList">
                        无
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal" id="alertEnterpriseLook">查看</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/portAlert/script/portAlert.js"></script>
</body>
</html>
