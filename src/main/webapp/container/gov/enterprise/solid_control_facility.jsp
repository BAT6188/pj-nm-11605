<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/common_include.jsp"%>
    <title>固体废物治理设施</title>
</head>
<body>
<div class="container-fluid">
    <div class="alert">
        <form class="form-inline">
            <div class="form-group">
                <label for="s_name">设备名称</label>
                <input type="text" id="s_name" class="form-control" />
            </div>
            <div class="form-group">
                <label for="s_crafts">处理工艺</label>
                <input type="text" id="s_crafts" class="form-control" />
            </div>
            <div class="form-group">
                <label for="s_status">运行状态</label>
                <input type="text" id="s_status" class="form-control" />
            </div>
            <button id="search" type="button" class="btn btn-success" >查询</button>
            <button id="searchFix" type="button" class="btn btn-default" >重置查询</button>
        </form>
    </div>
    <div id="toolbar">
        <button id="add" type="button" class="btn btn-primary" data-toggle="modal" data-target="#scfForm" >新增</button>
        <button id="update" type="button" class="btn btn-info" data-toggle="modal" data-target="#scfForm" >修改</button>
        <button id="remove" type="button" class="btn btn-danger" >删除</button>
    </div>
    <table id="table"
           data-toolbar="#toolbar"
           data-show-header="true"
           data-card-view="false"
           data-search="false"
           data-show-refresh="false"
           data-show-toggle="false"
           data-show-columns="false"
           data-show-export="false"
           data-detail-view="false"
           data-minimum-count-columns="2"
           data-show-pagination-switch="false"
           data-pagination="true"
           data-id-field="id"
           data-page-list="[10, 20, 30]"
           data-show-footer="false"
           data-side-pagination="server"
           data-striped="true"
           data-sort-name="id"
           data-sort-order="asc"
           data-click-to-select="true"
           >
    </table>
</div>
<!--添加表单-->
<div class="modal fade" id="scfForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 900px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加固体废物治理设施</h4>
                <input type="hidden" id="id">
                <input type="hidden" id="removeId">
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">设施名称</label>
                        <div class="col-sm-10">
                            <input type="text" id="name" class="form-control"
                                   data-message="设施名称不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createTime" class="col-sm-2 control-label">建设日期</label>
                        <div class="col-sm-4">
                            <div id="createTimeContent" class="input-group date form_date" data-date="" data-link-field="createTime" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                <input class="form-control" size="16" type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                            <input type="hidden" id="createTime" />
                        </div>
                        <label for="status" class="col-sm-2 control-label">运行状态</label>
                        <div class="col-sm-4 radio">
                            <label><input type="radio" value="1" name="status">正常</label>
                            <label><input type="radio" value="0" name="status">异常</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="openDate" class="col-sm-2 control-label">投运日期</label>
                        <div class="col-sm-4">
                            <div id="openDateContent" class="input-group date form_date" data-date="" data-link-field="openDate" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                <input class="form-control" size="16" type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                            <input type="hidden" id="openDate" class="form-control" />

                        </div>
                        <label for="crafts" class="col-sm-2 control-label">处理工艺</label>
                        <div class="col-sm-4">
                            <input type="text" id="crafts" class="form-control"
                                   data-message="处理工艺不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ability" class="col-sm-2 control-label">设计处理能力</label>
                        <div class="col-sm-4">
                            <input type="text" id="ability" class="form-control"
                                   data-message="设计处理能力不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="realAbility" class="col-sm-2 control-label">实际处理能力</label>
                        <div class="col-sm-4">
                            <input type="text" id="realAbility" class="form-control"
                                   data-message="实际处理能力不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="saveDemo">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<%--<!--查看表单-->
<div class="modal fade" id="scfFormView" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">查看固体废物治理设施</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">设施名称</label>
                        <div class="col-sm-10">
                             <span id="v_name"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createTime" class="col-sm-2 control-label">建设日期</label>
                        <div class="col-sm-4">
                            <span id="v_createTime"></span>
                        </div>
                        <label for="status" class="col-sm-2 control-label">运行状态</label>
                        <div class="col-sm-4 radio">
                            <span id="v_status"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="openDate" class="col-sm-2 control-label">投运日期</label>
                        <div class="col-sm-4">
                            <span id="v_openDate"></span>
                        </div>
                        <label for="crafts" class="col-sm-2 control-label">处理工艺</label>
                        <div class="col-sm-4">
                            <span id="v_crafts"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ability" class="col-sm-2 control-label">设计处理能力</label>
                        <div class="col-sm-4">
                            <span id="v_ability"></span>
                        </div>
                        <label for="realAbility" class="col-sm-2 control-label">实际处理能力</label>
                        <div class="col-sm-4">
                            <span id="v_realAbility"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery-view"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>--%>
<script src="scripts/solid_control_facility.js"></script>
</body>
</html>
