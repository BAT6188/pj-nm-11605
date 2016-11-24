<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%
    String enterpriseId = request.getParameter("id");
%>
<head>
    <title>环境自行监测方案</title>

    <script type="text/javascript">
        var enterpriseId='<%=enterpriseId%>';
        $('.modal-body').attr('style','max-height: '+pageUtils.getFormHeight()+'px;overflow-y: auto;overflow-x: hidden;padding:10px;');
    </script>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;">环境自行监测方案列表</a>
            <div class="dealBox">
                <div class="sideTitle left">
                        <span class="blueMsg">
                            <img class="tipImg" src="<%=request.getContextPath()%>/common/images/searchTip.png" alt=""/>
                            <span class="text">查询</span>
                        </span>
                </div>
                <div class="queryBox marginLeft0">
                    <p>
                    <form class="form-inline" role="form" id="searchform">
                        <div class="form-group">
                            <label for="attachmentName">附件名：</label> <input class="form-control" size="16" type="text" name="attachmentName">
                        </div>
                        <div class="form-group">
                            <label for="year" class="labelMarginLeft">年份：</label>
                            <div id="s_datetimepicker" class="input-group date form_date" data-date="" data-date-format="yyyy" data-link-field="registTime" data-link-format="yyyy">
                                <input class="form-control" style="width: 240px;" size="16" type="text" name="year" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </form>
                    </p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#EnvTestProgramForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#EnvTestProgramForm">
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
<div class="modal fade" id="EnvTestProgramForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="EnvTestProgramModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加环境自行监测方案</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" style="margin-right: 20px;">
                    <div class="form-group">
                        <label for="year" class="col-sm-3 control-label">选择年份<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-9">
                            <input type="hidden" id="id" name="id" class="form-control">
                            <input type="hidden" id="pubStatus" name="pubStatus" class="form-control">
                            <input type="hidden" id="enterpriseId" name="enterpriseId" class="form-control">
                            <input type="hidden" id="createTime" name="createTime" class="form-control">
                            <div id="datetimepicker" class="input-group date form_date" data-date="" data-date-format="yyyy" data-link-field="registTime" data-link-format="yyyy">
                                <input class="form-control" size="16" type="text" id="year" name="year" value="" readonly
                                       data-message="年份"
                                       data-easytip="position:top;class:easy-red;"/>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachmentName" class="col-sm-3 control-label">附件名称：</label>
                        <div class="col-sm-9">
                            <input type="text" id="attachmentName" name="attachmentName" class="form-control" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-3 control-label">附件：</label>
                        <div class="col-sm-9">
                            <input type="hidden" id="attachmentId" name="attachmentId" class="form-control">
                            <input type="hidden" id="removeId" name="removeId" class="form-control">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-3 control-label">主要内容：</label>
                        <div class="col-sm-9">
                            <textarea class="form-control needshow" id="content" name="content" rows="5"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success saveBtn" id="pubBtn" style="display: none">发布</button>
                <button type="button" class="btn btn-primary saveBtn" id="save" style="display: none">保存</button>
                <button id="cancelBtn" type="button" class="btn btn-default saveBtn" data-dismiss="modal" style="display: none">取消</button>
                <button type="button" class="btn btn-default lookBtn" data-dismiss="modal" style="display: none">关闭</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/enterprise/scripts/EnvTestProgram.js"></script>
</body>
</html>
