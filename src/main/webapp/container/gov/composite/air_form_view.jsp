<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>空气质量监测查看dialog</title>
</head>
<body style="overflow: hidden;">
<!--噪音排口查看dialog-->
<div class="modal fade" id="airFormViewDialog" style="z-index: 9999;" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="otherProductModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 900px ">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">空气质量监测信息</h4>
            </div>
            <div class="modal-body" style="overflow-y: auto;">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="airMonitoringName" class="col-sm-2 control-label">监测点名称：</label>
                        <div class="col-sm-4">
                            <input type="text" id="airMonitoringName" name="airMonitoringName" class="form-control"
                                   data-message="监测点名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="monitoringNumber" class="col-sm-2 control-label">监测点编号：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="createTime" name="createTime" class="form-control">
                            <input type="text" id="id" name="id" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">

                        <label for="monitoringTime" class="col-sm-2 control-label">最新监测时间：<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="createTimeContent" class="input-group date form_date col-md-12" data-date="" data-link-field="monitoringTime" data-date-format="yyyy-mm-dd hh:ii" data-link-format="yyyy-mm-dd">
                                <input class="form-control" id="monitoringTime" name="monitoringTime" size="16" type="text" value="" readonly
                                       data-message="监测时间不能为空"
                                       data-easytip="position:top;class:easy-red;">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>

                        <label for="airIndex" class="col-sm-2 control-label">AQI指数：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="airIndex" name="airIndex" class="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="airQualityGrade" class="col-sm-2 control-label">空气质量等级：</label>
                        <div class="col-sm-4">
                            <input type="text" id="airQualityGrade" name="airQualityGrade" class="form-control" readonly
                                   data-message="监测点经度不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="primaryPollutant" class="col-sm-2 control-label">首要污染物：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="primaryPollutant" name="primaryPollutant" readonly
                                   data-message="监测点纬度不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group" style="display: none">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="attachmentId" name="attachmentId" class="form-control">
                            <input type="hidden" id="removeId" name="removeId" class="form-control">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="noiseFormViewDialog-fine-uploader-gallery"></div>
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

<%--空气质量历史数据--%>
<div class="modal fade" data-backdrop="static" id="airEquipmentForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">空气质量历史数据—"<span id="airName"></span>"</h4>
            </div>
            <div class="modal-body">
                <table id="airEquipmentTable" class="table table-striped table-responsive">
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/composite/scripts/air_form_view.js"></script>
</html>
