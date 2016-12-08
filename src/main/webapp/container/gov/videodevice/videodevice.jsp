<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/8
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>公安视频</title>
    <jsp:include page="/common/common_include.jsp" flush="true"/>
    <script src="<%=request.getContextPath()%>/container/gov/enterprise/scripts/pageset.js"></script>


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
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="addr" class="ui-widget">视频安装地址：</label> <input type="text" name="addr" style="width: 180px;" class="form-control" />
                            <label for="type">摄像头类型：</label>
                            <select class="form-control" name="type">
                                <option value="">请选择</option>
                                <option value="1">普通</option>
                                <option value="2">高空</option>
                            </select>
                        </div>
                    </form>
                    <p></p>

                </div>

                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#scfForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#scfForm">
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
<div class="modal fade" data-backdrop="static" id="scfForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title" id="demoFormTitle">添加公安视频</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="deviceId" class="col-sm-2 control-label">设备id<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="text" id="deviceId" name="deviceId" class="form-control"
                                   data-message="用户名不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="channalId" class="col-sm-2 control-label">通道id<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="channalId" name="channalId" class="form-control"
                                   data-message="通道id不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>

                    </div>

                    <div class="form-group">
                        <label for="longitude" class="col-sm-2 control-label">经度<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="longitude" name="longitude" class="form-control"
                                   data-message="经度不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="latitude" class="col-sm-2 control-label">纬度<span class="text-danger">*</span>：</label>
                        <div class="col-sm-3">
                            <div class="rows">
                                <div class="col-sm-9">
                                    <input type="text" id="latitude" name="latitude" class="form-control"
                                           data-message="纬度不为空"
                                           data-easytip="position:top;class:easy-red;"
                                    />
                                </div>
                                <div class="col-sm-3">
                                  <span class="input-group-btn col-sm-1">
                                                <button class="btn btn-default formBtn tagging" type="button" id="mapMarkBtn">
                                                    标注
                                                </button>
                                            </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="addr" class="col-sm-3 control-label">视频安装地址<span class="text-danger">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" id="addr" name="addr" class="form-control"
                                   data-message="视频安装地址不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="type" class="col-sm-2 control-label">摄像头类型<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select style="width: 100%" class="form-control"  id="type" name="type">
                                <option value="1">普通</option>
                                <option value="2">高空</option>
                            </select>
                        </div>

                    </div>

                    <div class="form-group" style="display: none">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save">保存</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<%@include file="/common/gis/map_mark.jsp"%>
<script src="<%=request.getContextPath()%>/container/gov/videodevice/scripts/videodevice.js"></script>
</body>
</html>
