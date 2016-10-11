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
    <title>大气污染治理设施</title>
</head>
<body>
<div class="container-fluid">
    <!--搜索区域-->
    <div class="alert">
        <form class="form-inline search-form">
            <div class="form-group">
                <label for="name">设施名称</label>
                <input type="text" id="s_name" class="form-control" />
            </div>
            <div class="form-group">
                <label for="s_crafts">处理工艺</label>
                <input type="text" id="s_crafts" class="form-control" />
            </div>
            <div class="form-group">
                <label for="s_status">运行状态</label>
                <div class="radio">
                    <label><input type="radio" value="1" name="s_status">正常</label>
                    <label><input type="radio" value="0" name="s_status">异常</label>
                </div>
            </div>
            <button id="search" type="button" class="btn btn-success" >查询</button>
            <button id="searchFix" type="button" class="btn btn-default" >重置查询</button>
        </form>
    </div>

    <!--列表区域-->
    <div id="toolbar">
        <button id="add" type="button" class="btn btn-primary" data-toggle="modal" data-target="#scfForm" >新增</button>
        <button id="update" type="button" class="btn btn-warning" data-toggle="modal" data-target="#scfForm" >修改</button>
        <button id="remove" type="button" class="btn btn-danger" >删除</button>
    </div>
    <table id="table"
           data-toolbar="#toolbar"
           data-pagination="true"
           data-side-pagination="server"
            >
    </table>
</div>

<!--添加表单-->
<div class="modal fade" id="scfForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 900px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title" id="demoFormTitle">添加大气污染治理设施</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">设施名称</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="用户名不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="createTime" class="col-sm-2 control-label">建设日期</label>
                        <div class="col-sm-4">
                            <div id="datetimepicker" class="input-group date form_date col-md-10" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                <input class="form-control" id="createTime" name="createTime" size="16" type="text" value="" readonly
                                       data-message="时间不能为空"
                                       data-easytip="position:top;class:easy-red;"
                                />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>

                        </div>
                        <label for="status" class="col-sm-2 control-label">运行状态</label>
                        <div class="col-sm-4 radio">
                            <label><input type="radio" value="1" checked name="status">正常</label>
                            <label><input type="radio" value="0" name="status">异常</label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="openDate" class="col-sm-2 control-label">投运日期</label>
                        <div class="col-sm-4">
                            <div id="datetimepicker2" class="input-group date form_date col-md-10" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                <input class="form-control" id="openDate" name="openDate" size="16" type="text" value="" readonly
                                       data-message="时间不能为空"
                                       data-easytip="position:top;class:easy-red;"
                                />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>

                        </div>
                        <label for="crafts" class="col-sm-2 control-label">工艺处理</label>
                        <div class="col-sm-4">
                            <input type="text" id="crafts" name="crafts" class="form-control"
                                   data-message="工艺处理不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="ability" class="col-sm-2 control-label">设计处理能力</label>
                        <div class="col-sm-10">
                            <input type="text" id="ability" name="ability" class="form-control"
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
                <button type="button" class="btn btn-primary" id="save">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="scripts/gascontrolfacility.js"></script>
</body>
</html>
