<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>沙尘暴监测点</title>
    <script type="text/javascript">

    </script>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;z-index: 0;">沙尘暴监测点列表</a>
            <div class="dealBox">
                <div class="sideTitle left">
                        <span class="blueMsg">
                            <img class="tipImg" src="<%=request.getContextPath()%>/common/images/searchTip.png" alt=""/>
                            <span class="text">查询</span>
                        </span>
                </div>
                <div class="queryBox marginLeft0">
                    <p>
                    <form class="form-horizontal" role="form" id="searchform">
                        <label for="number">监测点编号：</label> <input type="text" id="portNumber" name="number" class="form-control" />
                        <label for="name" class="labelMarginLeft">监测点名称：</label> <input type="text" id="portName" name="name" class="form-control" />
                    </form>
                    </p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#dustForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#dustForm">
                        <i class="btnIcon edit-icon"></i><span>修改</span>
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
<div class="modal fade" id="dustForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="dustModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 900px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加沙尘暴监测点</h4>
            </div>
            <div class="modal-body">
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
                        <div class="col-sm-4">
                            <input type="text" id="position" name="position" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isPm" class="col-sm-2 control-label">监测PM10<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isPm">
                            <label class="checkbox-inline"><input type="radio" name="isPm" id="isPm1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isPm" id="isPm0" value="0">否</label>
                        </div>
                        <label for="isTsp" class="col-sm-2 control-label">监测TSP<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isTsp">
                            <label class="checkbox-inline"><input type="radio" name="isTsp" id="isTsp1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isTsp" id="isTsp0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isTemperature" class="col-sm-2 control-label">监测温度<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isTemperature">
                            <label class="checkbox-inline"><input type="radio" name="isTemperature" id="isTemperature1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isTemperature" id="isTemperature0" value="0">否</label>
                        </div>
                        <label for="isHumidity" class="col-sm-2 control-label">监测湿度<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isHumidity">
                            <label class="checkbox-inline"><input type="radio" name="isHumidity" id="isHumidity1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isHumidity" id="isHumidity0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isAirPressure" class="col-sm-2 control-label">监测气压<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isAirPressure">
                            <label class="checkbox-inline"><input type="radio" name="isAirPressure" id="isAirPressure1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isAirPressure" id="isAirPressure0" value="0">否</label>
                        </div>
                        <label for="isWindDirection" class="col-sm-2 control-label">监测风向<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isWindDirection">
                            <label class="checkbox-inline"><input type="radio" name="isWindDirection" id="isWindDirection1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isWindDirection" id="isWindDirection0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isWindSpeed" class="col-sm-2 control-label">监测风速<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isWindSpeed">
                            <label class="checkbox-inline"><input type="radio" name="isWindSpeed" id="isWindSpeed1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isWindSpeed" id="isWindSpeed0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="attachmentId" name="attachmentId" class="form-control">
                            <input type="hidden" id="removeId" name="removeId" class="form-control">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary saveBtn" id="save" style="display: none">保存</button>
                <button id="cancelBtn" type="button" class="btn btn-default saveBtn" data-dismiss="modal" style="display: none">取消</button>
                <button type="button" class="btn btn-default lookBtn" data-dismiss="modal" style="display: none">关闭</button>
            </div>
        </div>
    </div>
</div>
<%@include file="/common/gis/map_mark.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/monitor/dustMonitor/scripts/dustPort.js"></script>
</body>
</html>
