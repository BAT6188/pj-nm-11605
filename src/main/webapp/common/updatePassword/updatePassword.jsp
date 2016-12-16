<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改密码</title>
</head>
<body style="overflow: hidden;">

<!-- 修改密码 -->
<div class="modal fade" id="updatePasswordDialog" tabindex="-1" role="dialog" aria-labelledby="" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title form-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="oldPassword" class="col-sm-2 control-label">原密码：</label>
                        <div class="col-sm-4">
                            <input type="password" id="oldPassword" name="oldPassword" class="form-control" data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="newPassword" class="col-sm-2 control-label">新密码：</label>
                        <div class="col-sm-4">
                            <input type="password" id="newPassword" name="newPassword" class="form-control" data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="updateBtn">修改</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/updatePassword/script/updatePassword.js"></script>
</body>
</html>
