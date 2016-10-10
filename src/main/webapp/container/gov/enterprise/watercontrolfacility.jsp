<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/9
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/common_include.jsp"%>
    <title>水污染治理设施</title>
</head>
<body>
<div class="container-fluid">
    <!--搜索区域-->
    <div class="alert">
        <form class="form-inline" role="form">
            <div class="form-group">
                <label for="name">设施名称</label>
                <input type="text" id="s_name" class="form-control" />
            </div>
            <div class="form-group">
                <label for="name">运行情况</label>
                <input type="text" id="s_status" class="form-control" />
            </div>
            <button id="search" type="button" class="btn btn-success" >查询</button>
            <button id="searchFix" type="button" class="btn btn-default" >重置查询</button>
        </form>
    </div>

    <!--列表区域-->
    <div id="toolbar">
        <button id="add" type="button" class="btn btn-primary" data-toggle="modal" data-target="#demoForm" >新增</button>
        <button id="update" type="button" class="btn btn-info" data-toggle="modal" data-target="#demoForm" >修改</button>
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
<div class="modal fade" id="demoForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="demoFormTitle">添加大气污染治理设施</h4>
                <input type="hidden" id="id">
                <input type="hidden" id="removeId">
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">设施名称</label>
                        <div class="col-sm-10">
                            <input type="text" id="name" class="form-control"
                                   data-message="用户名不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createTime" class="col-sm-2 control-label">建设日期</label>
                        <div class="col-sm-10">
                            <input type="text" id="createTime" class="form-control"
                                   data-message="时间不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="status" class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-10">
                            <input type="text" id="status" class="form-control"
                                   data-message="状态不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="openDate" class="col-sm-2 control-label">投运日期</label>
                        <div class="col-sm-10">
                            <input type="text" id="openDate" class="form-control"
                                   data-message="投运日期不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="crafts" class="col-sm-2 control-label">工艺处理</label>
                        <div class="col-sm-10">
                            <input type="text" id="crafts" class="form-control"
                                   data-message="工艺处理不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ability" class="col-sm-2 control-label">设计处理能力</label>
                        <div class="col-sm-10">
                            <input type="text" id="ability" class="form-control"
                                   data-message="设计处理能力不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="realAbility" class="col-sm-2 control-label">实际处理能力</label>
                        <div class="col-sm-10">
                            <input type="text" id="realAbility" class="form-control"
                                   data-message="设计处理能力不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enterpriseId" class="col-sm-2 control-label">企业Id</label>
                        <div class="col-sm-10">
                            <input type="text" id="enterpriseId" class="form-control"
                                   data-message="设计处理能力不为空"
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
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="scripts/watercontrolfacility.js"></script>
</body>
</html>
