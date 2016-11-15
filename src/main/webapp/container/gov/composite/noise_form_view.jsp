<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>噪音排口查看dialog</title>
</head>
<body style="overflow: hidden;">
<!--噪音排口查看dialog-->
<div class="modal fade" id="noiseFormViewDialog" style="z-index: 9999;" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="otherProductModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 900px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">噪音排口信息</h4>
            </div>
            <div class="modal-body" style="overflow-y: auto;">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">噪声监测点：</label>
                        <div class="col-sm-4">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="监测点名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="number" class="col-sm-2 control-label">噪声源编号：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id" class="form-control">
                            <input type="hidden" id="createTime" name="createTime" class="form-control">
                            <input type="text" id="number" name="number" class="form-control"/>
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
                            <input type="text" class="form-control" id="latitude" name="latitude" readonly
                                   data-message="监测点纬度不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="position" class="col-sm-2 control-label">监测点位置：</label>
                        <div class="col-sm-4">
                            <input type="text" id="position" name="position" class="form-control">
                        </div>
                        <label for="noiseType" class="col-sm-2 control-label">噪声源类型：</label>
                        <div class="col-sm-4">
                            <select id="noiseType" name="noiseType" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dischargeStandard" class="col-sm-2 control-label">排放标准：</label>
                        <div class="col-sm-4">
                            <select id="noiseDischargeStandard" name="dischargeStandard" class="form-control">
                            </select>
                        </div>
                        <label for="fnType" class="col-sm-2 control-label">功能区类别：</label>
                        <div class="col-sm-4">
                            <select id="noiseFnType" name="fnType" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dayMax" class="col-sm-2 control-label">昼间标准值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="dayMax" name="dayMax" class="form-control"
                                   data-easyform="number;"
                                   data-message="昼间标准值不能为空、必须为数字类型"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="nightMax" class="col-sm-2 control-label">夜间标准值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="nightMax" name="nightMax" class="form-control"
                                   data-easyform="number;"
                                   data-message="夜间标准值不能为空、必须为数字类型"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="leqdb" class="col-sm-2 control-label">监测Leq：</label>
                        <div class="col-sm-4 " id="isLeqdb">
                            <input type="text" id="leqdb" name="leqdb" class="form-control" />
                        </div>
                        <label for="sd" class="col-sm-2 control-label">监测sd：</label>
                        <div class="col-sm-4 " >
                            <input type="text" id="sd" name="sd" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lmax" class="col-sm-2 control-label">监测Lmax：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="lmax" name="lmax" class="form-control" />
                        </div>
                        <label for="lmin" class="col-sm-2 control-label">监测Lmin：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="lmin" name="lmin" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lFive" class="col-sm-2 control-label">监测L5：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="lFive" name="lFive" class="form-control" />
                        </div>
                        <label for="lTen" class="col-sm-2 control-label">监测L10：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="lTen" name="lTen" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lFifty" class="col-sm-2 control-label">监测L50：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="lFifty" name="lFifty" class="form-control" />
                        </div>
                        <label for="isLNinety" class="col-sm-2 control-label">监测L90：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="isLNinety" name="isLNinety" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lNinetyFive" class="col-sm-2 control-label">监测L95：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="lNinetyFive" name="lNinetyFive" class="form-control" />
                        </div>
                        <label for="le" class="col-sm-2 control-label">监测Le：</label>
                        <div class="col-sm-4 ">
                            <input type="text" id="le" name="le" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
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
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/composite/scripts/noise_form_view.js"></script>
</html>
