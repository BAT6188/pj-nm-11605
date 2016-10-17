<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/common_include.jsp"%>
    <title>短信发送记录</title>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <div class="dealBox">
                <div class="sideTitle left">
                        <span class="blueMsg">
                            <img class="tipImg" src="<%=request.getContextPath()%>/common/images/searchTip.png" alt=""/>
                            <span class="text">查询</span>
                        </span>
                </div>
                <div class="queryBox marginLeft0">
                    <p>
                        <label for="s_senderId">发送人编码：</label> <input type="text" id="s_senderId" class="form-control" />
                        <label for="s_senderName">发送人姓名：</label> <input type="text" id="s_senderName" class="form-control" />
                    </p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#smsRecordForm">
                        <i class="btnIcon add-icon"></i><span>发送短信</span>
                    </button>
                    <button id="remove" type="button" class="btn btn-sm btn-danger">
                        <i class="btnIcon delf-icon"></i><span>删除</span>
                    </button>
                </p>
            </div>
            <div class="tableBox">
                <table id="table" class="table table-striped table-responsive">
                </table>
            </div>
        </div>
    </div>
</div>
<!--添加表单-->
<div class="modal fade" id="smsRecordForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="senderId" class="col-sm-3 control-label">发送人编码：</label>
                        <div class="col-sm-9">
                            <input type="hidden" id="id" name="id">
                            <input type="text" id="senderId" name="senderId" class="form-control"/>
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="senderName" class="col-sm-3 control-label">发送人姓名：</label>
                        <div class="col-sm-9">
                            <input type="text" id="senderName" name="senderName" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sendTime" class="col-sm-3 control-label">发送人时间：</label>
                        <div class="col-sm-9">
                            <input type="text" id="sendTime" name="sendTime" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-3 control-label">短信内容：</label>
                        <div class="col-sm-9">
                            <textarea type="text" id="content" rows="5" name="content" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="receiverListBtn" class="col-sm-3 control-label">接收人列表：</label>
                        <button type="button" class="btn btn-primary" id="receiverListBtn" data-toggle="modal" data-target="#smsReceiverDialog">查看</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save">保存</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--短信接收人列表Dialog-->
<div class="modal fade" id="smsReceiverDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">短信接收人列表</h4>
            </div>
            <div class="modal-body">
                <div class="tableBox">
                    <table id="receiverListTable" class="table table-striped table-responsive">
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/sms/scripts/sms_recode.js"></script>
</body>
</html>
