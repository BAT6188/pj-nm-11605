<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>任务发布</title>
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
                        <form >
                            <div class="form-inline">
                                <div class="form-group">
                                    <label for="">年度任务：</label> <input type="text" name="taskName" style="width: 150px;" class="form-control" />
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
                            </div>
                        </form>
                    <p></p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#taskForm">
                        <i class="btnIcon add-icon"></i><span>新增</span>
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

<!--添加表单大任务-->
<div class="modal fade" id="taskForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 600px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="" class="col-sm-3 control-label">年度任务<span class="text-danger">*</span>：</label>
                        <div class="col-sm-9">
                            <input type="hidden" id="id" name="id">
                            <input type="text" id="taskName" name="taskName" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-3 control-label">发布科室<span class="text-danger">*</span>：</label>
                        <div class="col-sm-9">
                            <input type="text" id="taskCreateDepartment" name="taskCreateDepartment" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-3 control-label">发布时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-9">
                            <div class="input-group date form_datetime editDatetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="taskPubTime" name="taskPubTime" type="text" value="" data-message="不能为空"
                                       data-easytip="position:top;class:easy-red;" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="taskRemark" class="col-sm-3 control-label">任务备注：</label>
                        <div class="col-sm-9">
                            <textarea type="text" rows="2" id="taskRemark" name="taskRemark" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-3 control-label">附件</label>
                        <div class="col-sm-9">
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
<script src="<%=request.getContextPath()%>/container/gov/office/scripts/taskPublish.js"></script>
</body>
</html>
