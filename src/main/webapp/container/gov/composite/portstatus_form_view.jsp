<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>排口超标查看dialog</title>
</head>
<body style="overflow: hidden;">
<!--排口超标查看dialog-->
<div class="modal fade" id="portStatusViewDialog" style="z-index: 9999;" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="otherProductModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 900px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">超标记录信息</h4>
            </div>
            <div class="modal-body" style="overflow-y: auto;">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="portName" class="col-sm-2 control-label">排口名称：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id" class="form-control">
                            <input type="hidden" id="createTime" name="createTime" class="form-control">
                            <input type="text" id="portName" name="portName" class="form-control"/>
                        </div>
                        <label for="portNumber" class="col-sm-2 control-label">排口编码：</label>
                        <div class="col-sm-4">
                            <input type="text" id="portNumber" name="portNumber" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pollutantCode" class="col-sm-2 control-label">污染物编码：</label>
                        <div class="col-sm-4">
                            <input type="text" id="pollutantCode" name="pollutantCode" class="form-control">
                        </div>
                        <label for="portStatus" class="col-sm-2 control-label">状态：</label>
                        <div class="col-sm-4 isRadio" id="portStatus">
                            <label class="checkbox-inline">
                                <input type="radio" name="portStatus" id="portStatus0" value="0">正常
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="portStatus" id="portStatus1" value="1">超标
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="portStatus" id="portStatus2" value="2">异常
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="startTime" class="col-sm-2 control-label">开始时间：</label>
                        <div class="col-sm-4">
                            <input type="text" id="startTime" name="startTime" class="form-control">
                        </div>
                        <label for="endTime" class="col-sm-2 control-label">结束时间：</label>
                        <div class="col-sm-4">
                            <input type="text" id="endTime" name="endTime" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="minValue" class="col-sm-2 control-label">最小值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="minValue" name="minValue" class="form-control">
                        </div>
                        <label for="maxValue" class="col-sm-2 control-label">最大值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="maxValue" name="maxValue" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="solution" class="col-sm-2 control-label">解决方案：</label>
                        <div class="col-sm-10">
                            <textarea id="solution" name="solution" class="form-control" rows="5"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="attachmentId" name="attachmentId" class="form-control">
                            <input type="hidden" id="removeId" name="removeId" class="form-control">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="portStatusViewDialog-fine-uploader-gallery"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default lookBtn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/composite/scripts/portstatus_form_view.js"></script>
</html>
