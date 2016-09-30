<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>Demo</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link href="<%=request.getContextPath()%>/scripts/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/scripts/font-awesome-4.6.3/css/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/scripts/bootstrap-table-1.11.0/bootstrap-table.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/pageStyle.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/scripts/jquery1.12.4/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/bootstrap-3.3.7/js/bootstrap.js"></script>

    <script src="<%=request.getContextPath()%>/scripts/bootstrap-table-1.11.0/bootstrap-table.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.js"></script>

    <script src="<%=request.getContextPath()%>/scripts/bootstrap-table-1.11.0/x-editable/bootstrap-editable.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/bootstrap-table-1.11.0/extensions/editable/bootstrap-table-editable.js"></script>

    <script src="<%=request.getContextPath()%>/scripts/bootstrap-table-1.11.0/table-export/tableExport.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/bootstrap-table-1.11.0/extensions/export/bootstrap-table-export.js"></script>

</head>
<body>
<div class="container">
    <!--搜索区域-->
    <div class="alert alert-info">
        <form class="form-inline" role="form">
            <div class="form-group">
                <label for="name">姓名</label>
                <input type="text" id="s_name" class="form-control" />
            </div>
            <div class="form-group">
                <label for="age">年龄</label>
                <input type="text" id="s_age" class="form-control" />
            </div>
            <button id="search" type="button" class="btn btn-default" >查询</button>
            <button id="searchFix" type="button" class="btn btn-default" >重置查询</button>
        </form>
    </div>

    <!--列表区域-->
    <div id="toolbar">
        <button id="add" type="button" class="btn btn-default" data-toggle="modal" data-target="#demoForm" >新增</button>
        <button id="remove" type="button" class="btn btn-default" >删除</button>
    </div>
    <table id="table"
           data-toolbar="#toolbar"
           data-show-header="true"
           data-card-view="false"
           data-search="false"
           data-show-refresh="true"
           data-show-toggle="false"
           data-show-columns="false"
           data-show-export="true"
           data-detail-view="false"
           data-detail-formatter="detailFormatter"
           data-minimum-count-columns="2"
           data-show-pagination-switch="false"
           data-pagination="true"
           data-id-field="id"
           data-page-list="[10, 20, 30]"
           data-show-footer="false"
           data-side-pagination="server"
           data-url="<%=request.getContextPath()%>/action/S_demo_Demo_list.action"
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
                <h4 class="modal-title" id="demoFormTitle">添加Demo</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-10">
                            <input type="text" id="name" class="form-control col-sm-10" />
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄</label>
                        <div class="col-sm-10">
                            <input type="text" id="age" class="form-control" />
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
<script src="scripts/demo.js"></script>
</body>
</html>
