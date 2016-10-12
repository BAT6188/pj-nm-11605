<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <title>监控中心</title>

    <link href="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/metrStyle-cd/metroStyle.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/jquery.ztree.all.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/slimScroll/jquery.slimscroll.js"></script>
</head>
<style>

    .Node-frame-menubar {
        width: 227px;
        height: 500px;
        float: left;
        position: relative;
        left: 0px;
        border-right: 1px solid #e5e5e5;
        padding: 10px;
    }
</style>
<body>
<div class="container-fluid">
    <!--搜索区域-->
    <div class="alert">
        <form class="form-inline" role="form">
            <div class="form-group">
                <label for="searchEnterpriseName">企业名称</label>
                <input type="text" id="searchEnterpriseName" class="form-control" />
                &nbsp;&nbsp;&nbsp;&nbsp;
                <label for="">调度时间</label>
                <div id="datetimepicker1" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                    <input class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
                <input type="hidden" id="start_sendTime" value="" />
                ~
                <div id="datetimepicker2" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                    <input class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
                <input type="hidden" id="end_sendTime" value="" />
            </div>
            <div class="form-group">
                <label for="blockNname">调度网格</label>
                <input type="text" id="blockNname" class="form-control" />
                &nbsp;&nbsp;&nbsp;&nbsp;
                <label for="reason">原  因</label>
                <input type="text" id="reason" class="form-control" />
            </div>
            <button id="search" type="button" class="btn btn-success" >查询</button>
            <button id="searchFix" type="button" class="btn btn-default" >重置查询</button>
        </form>
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
<div class="modal fade" id="demoForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog" style="width:642px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="demoFormTitle">调度单</h4>
                <input type="hidden" id="id">
                <input type="hidden" id="removeId">
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="eventTime" class="col-sm-2 control-label">事件时间：</label>
                        <div class="col-sm-4">
                            <input type="text" id="eventTime" class="form-control"
                            />
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">企业名称：</label>
                        <div class="col-sm-4">
                            <input type="text" id="enterpriseName" class="form-control"
                                   data-message="企业名称不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="blockName" class="col-sm-2 control-label">所属网格：</label>
                        <div class="col-sm-4">
                            <input type="text" id="blockName" class="form-control"
                            />
                        </div>

                        <label for="blockLevelName" class="col-sm-2 control-label"></label>
                        <div class="col-sm-4">
                            <input type="text" id="blockLevelName" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="supervisor" class="col-sm-2 control-label">监管人员：</label>
                        <div class="col-sm-4">
                            <input type="text" id="supervisor" class="form-control"
                            />
                        </div>

                        <label for="supervisorPhone" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" id="supervisorPhone" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="overValue" class="col-sm-2 control-label">超标值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="overValue" class="form-control"
                            />
                        </div>

                        <label for="thrValue" class="col-sm-2 control-label">超标阀值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="thrValue" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">事件内容：</label>
                        <div class="col-sm-10">
                            <textarea id="content" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <hr/>

                    <div class="form-group">
                        <label for="senderName" class="col-sm-2 control-label">发送人：</label>
                        <div class="col-sm-4">
                            <input type="text" id="senderName" class="form-control"
                            />
                        </div>

                        <label for="sendTime" class="col-sm-2 control-label">发送时间：</label>
                        <div class="col-sm-4">
                            <input type="text" id="sendTime" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sendRemark" class="col-sm-2 control-label"> 备注：</label>
                        <div class="col-sm-10">
                            <textarea id="sendRemark" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="smsSend">短信发送</button>
                <button type="button" class="btn btn-primary" id="send" data-toggle="modal" data-target="#selectPeopleForm">发送</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!--添加表单-->
<div class="modal fade" id="selectPeopleForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog" style="width:882px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="demoFormTitle2">人员选择</h4>
                <input type="hidden" id="id2">
                <input type="hidden" id="removeId2">
            </div>
            <div class="modal-body">
                <div class="Node-frame-menubar">
                    <div class="scrollContent" >
                        <ul id="treeDemo1" class="ztree"></ul>
                    </div>
                </div>
<table><tr><td>11</td><td>22</td></tr><tr><td>11</td><td>22</td></tr><tr><td>11</td><td>22</td></tr><tr><td>11</td><td>22</td></tr></table>
            </div>
            <div class="modal-footer" style="clear: both;">
                <button type="button" class="btn btn-primary" id="send2" data-toggle="modal" data-target="#demoForm">发送</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script src="scripts/MonitorCase.js"></script>
</body>
</html>
