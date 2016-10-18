<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/common_include.jsp" %>
    <title>通用发送短信dialog</title>
</head>
<body style="overflow: hidden;">

<!--发送短信dialog-->
<div class="modal fade" id="smsSendDialog" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">发送短信</h4>
            </div>
            <div class="modal-body" style="padding: 0;">
                <div class="content clearfix show">
                    <div class="wrap">
                        <!--左侧树内容html-->
                        <div class="tree-left left" style="width: 30%;height: 480px">
                            <div class="orgTree"></div>
                            <img src="<%=request.getContextPath()%>/common/images/tree/tree2.png" alt=""/>
                        </div>
                        <!--右侧内容html-->
                        <div class="main-right right" style="width: 69.5%">
                            <div class="container-fluid">
                                <div class="row">
                                    <p></p>
                                    <button type="button" class="btn btn-primary col-sm-offset-5">短信内容</button>
                                    <p></p>

                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <textarea class="form-control" rows="6"></textarea>
                                    </div>
                                </div>
                                <div class="row">
                                    <p></p>
                                    <button type="button" class="btn btn-primary col-sm-offset-5">已选择列表</button>
                                    <p></p>
                                </div>
                                <div class="row">
                                    <div class="tableBox">
                                        <table class="table table-striped table-responsive">
                                        </table>
                                    </div>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="smsSendDialogOK">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/sms/scripts/sms_send.js"></script>
</body>
</html>
