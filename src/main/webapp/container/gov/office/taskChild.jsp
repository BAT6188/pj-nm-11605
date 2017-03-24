<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.net.URLDecoder" %>
<%
    String parentTaskId = request.getParameter("parentTaskId");
    String parentTaskName = request.getParameter("parentTaskName");
    if(StringUtils.isNotBlank(parentTaskName)){
        parentTaskName= URLDecoder.decode(parentTaskName,"utf-8");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>子任务</title>
    <style>
        #headTitle{
            overflow:hidden;
            vertical-align:middle;
        }
        #headTitle a{
            float:right;
            display:inline-block;
            text-decoration:none;
            text-align:center;
        }
    </style>
    <script>
        var parentTaskId='<%=parentTaskId==null?"":parentTaskId%>';
        var parentTaskName='<%=parentTaskName==null?"":parentTaskName%>';
        if(parentTaskId!='null'){
            $('#headTitle').show();
            $('#headParentTaskName').html(parentTaskName);
        }
        function backToParent(){
            var url = rootPath + "/container/gov/office/task.jsp";
            pageUtils.toUrl(url);
        }
    </script>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <div id="headTitle" href="javascript:void(0)" class="list-group-item active" style="z-index: 0;cursor: default;font-size: 15px;display: none;">
                任务类型：<span id="headParentTaskName"></span>
                <a class="btn btn-info" href="javascript:backToParent()">
                    <span class="glyphicon glyphicon-backward"></span> 返回
                </a>
            </div>
            <div class="dealBox">
                <div class="sideTitle left">
                        <span class="blueMsg">
                            <img class="tipImg" src="<%=request.getContextPath()%>/common/images/searchTip.png" alt=""/>
                            <span class="text">查询</span>
                        </span>
                </div>
                <div class="queryBox marginLeft0">
                        <form >
                            <div class="form-inline">
                                <div class="form-group">
                                    <label for="">任务名称：</label> <input type="text" name="taskName" style="width: 180px;" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <label for="" class="labelMarginLeft">任务状态：</label>
                                    <select name="taskRemark" class="form-control">
                                        <option value="">全部</option>
                                        <option value="0">未发布</option>
                                        <option value="1">未完成</option>
                                        <option value="2">已完成</option>
                                        <option value="3">已办结</option>
                                    </select>
                                </div>
                            </div>
                            <p></p>
                            <div class="form-inline">
                                <div class="form-group">
                                    <label for="">发布时间：</label>
                                    <div id="" class="input-group date form_datetime searDate" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                        <input class="form-control" size="16" name="start_publishTime"  type="text" value="" readonly>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                    </div>
                                    -
                                    <div class="input-group date form_datetime searDate" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                        <input class="form-control" size="16" name="end_publishTime" type="text" value="" readonly>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                    </div>
                                </div>
                            </div>
                        </form>
                    <p></p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#taskChildForm">
                        <i class="btnIcon add-icon"></i><span>新增子任务</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#taskForm">
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
<div class="modal fade" id="taskChildForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 903px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">年度任务<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="firstTaskName" name="firstTaskName" class="form-control" readonly
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="" class="col-sm-2 control-label">任务类型<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="parentTaskId" name="parentTaskId" class="form-control"/>
                            <input type="text" id="parentTaskName" name="parentTaskName" class="form-control" readonly/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">工作任务<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="id" name="id">
                            <textarea type="text" rows="4" id="taskContent" name="taskContent" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">完成时限<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="deadline" name="deadline" type="text" value="" data-message="不能为空"
                                       data-easytip="position:top;class:easy-red;" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dutyLeader" class="col-sm-2 control-label">责任领导<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="dutyLeader" name="dutyLeader" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="dutyDepartment" class="col-sm-2 control-label">责任单位<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="dutyDepartment" name="dutyDepartment" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dispatchDutyLeader" class="col-sm-2 control-label">调度责任领导<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="dispatchDutyLeader" name="dispatchDutyLeader" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="dispatchDutyDepartment" class="col-sm-2 control-label">调度责任科室<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="dispatchDutyDepartment" name="dispatchDutyDepartment" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="taskRemark" class="col-sm-2 control-label">任务备注<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea type="text" rows="5" id="taskRemark" name="taskRemark" class="form-control"
                                      data-message="不能为空"
                                      data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">附件</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="removeId" name="removeId" class="form-control">
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
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/office/scripts/taskChild.js"></script>
</body>
</html>
