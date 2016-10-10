<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <title>创模建设信息管理</title>
</head>
<body>
<div class="container-fluid">
    <!--搜索区域-->
    <div class="alert">
        <form class="form-inline" role="form">
            <div class="form-group">
                <label for="title">标题</label>
                <input type="text" id="s_title" class="form-control" />
            </div>
            <div class="form-group">
                <label for="pubTime">时间</label>
                <input type="text" id="s_pubTime" class="form-control" />
            </div>
            <button id="search" type="button" class="btn btn-success" >查询</button>
            <button id="searchFix" type="button" class="btn btn-default" >重置查询</button>
        </form>
    </div>

    <!--列表区域-->
    <div id="toolbar">
        <button id="add" type="button" class="btn btn-primary" data-toggle="modal" data-target="#creationModeForm" >新增</button>
        <button id="update" type="button" class="btn btn-info" data-toggle="modal" data-target="#creationModeForm" >修改</button>
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
           data-detail-formatter="detailFormatter"
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
           data-response-handler="responseHandler">
    </table>
</div>
<!--添加表单-->
<div class="modal fade" id="creationModeForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="creationModeFormTitle" >添加创模建设信息</h4>
                <input type="hidden" id="id">
                <input type="hidden" id="removeId">
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-10">
                            <input type="text" id="title" class="form-control"
                                   data-message="标题不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pubOrgName" class="col-sm-2 control-label">发布单位</label>
                        <div class="col-sm-10">
                            <input type="text" id="pubOrgName" class="form-control"
                                   data-message="发布单位不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pubTime" class="col-sm-2 control-label">发布时间</label>
                        <div class="col-sm-10">
                            <input type="text" id="pubTime" class="form-control"
                                   data-message="发布时间不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">内容概要</label>
                        <div class="col-sm-10">
                            <textarea id="content" class="form-control" rows="5"
                                      data-message="发布时间不能为空"
                                      data-easytip="position:top;class:easy-red;"
                            ></textarea>
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
                <button type="button" class="btn btn-primary" id="saveCreationMode">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="scripts/creationmode.js"></script>
</body>
</html>
