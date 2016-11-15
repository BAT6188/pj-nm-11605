<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>噪音排口查看dialog</title>
</head>
<body style="overflow: hidden;">
<!--噪音排口查看dialog-->
<div class="modal fade" id="dustFormViewDialog" style="z-index: 9999;" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="otherProductModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 900px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">噪音排口信息</h4>
            </div>
            <div class="modal-body" style="overflow-y: auto;">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="number" class="col-sm-2 control-label">监测点编号<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id" class="form-control">
                            <input type="hidden" id="createTime" name="createTime" class="form-control">
                            <input type="text" id="number" name="number" class="form-control"
                                   data-message="编号不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="name" class="col-sm-2 control-label">监测点名称<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="监测点名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="longitude" class="col-sm-2 control-label">经度：</label>
                        <div class="col-sm-4">
                            <input type="text" id="longitude" name="longitude" class="form-control" readonly
                                   data-message="监测点经度不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="latitude" class="col-sm-2 control-label">纬度：</label>
                        <div class="col-sm-4">
                            <div class="input-group">
                                <input type="text" class="form-control" id="latitude" name="latitude" readonly
                                       data-message="监测点纬度不能为空"
                                       data-easytip="position:top;class:easy-red;"/>
                                <span class="input-group-btn">
                                    <button class="btn btn-default formBtn" type="button" id="mapMarkBtn">
                                        标注
                                    </button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="position" class="col-sm-2 control-label">监测点位置：</label>
                        <div class="col-sm-10">
                            <input type="text" id="position" name="position" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pm" class="col-sm-2 control-label">监测PM10<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="pm" name="pm" class="form-control">
                        </div>
                        <label for="tsp" class="col-sm-2 control-label">监测TSP<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="tsp" name="tsp" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="temperature" class="col-sm-2 control-label">监测温度<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="temperature" name="temperature" class="form-control">
                        </div>
                        <label for="humidity" class="col-sm-2 control-label">监测湿度<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="humidity" name="humidity" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="airPressure" class="col-sm-2 control-label">监测气压<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="airPressure" name="airPressure" class="form-control">
                        </div>
                        <label for="windDirection" class="col-sm-2 control-label">监测风向<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="windDirection" name="windDirection" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="windSpeed" class="col-sm-2 control-label">监测风速<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="windSpeed" name="windSpeed" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="attachmentId" name="attachmentId" class="form-control">
                            <input type="hidden" id="removeId" name="removeId" class="form-control">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="dustFormViewDialog-fine-uploader-gallery"></div>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/composite/scripts/dust_form_view.js"></script>
</html>
