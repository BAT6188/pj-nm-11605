<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/9
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String enterpriseId = request.getParameter("id");
%>
<script>
    var enterpriseId='<%=enterpriseId%>'
</script>
<!DOCTYPE html>
<html>
<head>

    <title>现场监察</title>
    <style>
        .ui-autocomplete { z-index:2147483647;}
    </style>
    <script>
    </script>
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
                    <form class="form-inline" id="searchform">
                            <div class="form-group">
                            <label for="s_checkPeople">监察人员：</label> <input type="text" id="s_checkPeople" name="checkPeople" class="form-control" />
                        </div>
                    </form >
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
                    <%--<button id="remove" type="button" class="btn btn-sm btn-danger">--%>
                        <%--<i class="btnIcon delf-icon"></i><span>删除</span>--%>
                    <%--</button>--%>
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
                <h4 class="modal-title form-title" id="demoFormTitle">添加现场监察</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <input type="hidden" id="id" name="id" class="form-control">
                    <input type="hidden" id="dispatchId" name="dispatchId" class="form-control">
                    <input type="hidden" id="removeId" name="removeId" class="form-control">
                    <input type="hidden" name="enterpriseId" id="enterpriseId" class="form-control">
                    <input type="hidden" type="text" id="enterpriseName" name="enterpriseName"/>
                    <input type="hidden" id="blockLevelId" name="blockLevelId">
                    <input type="hidden" id="blockId" name="blockId"/>
                    <div class="form-group">
                        <label for="checkPeople" class="col-sm-2 control-label">监察人员<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="checkPeople" name="checkPeople" class="form-control"
                                   data-message="监察人员不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="monitoringTime" class="col-sm-2 control-label">监察时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                        <div id="datetimepicker" class="input-group date form_date col-md-12" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                            <input class="form-control" id="monitoringTime" name="monitoringTime" size="16" type="text" value=""
                                   data-message="监察时间不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="isNotProblem" class="col-sm-3 control-label">是否存在问题<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4 radio">
                            <label><input type="radio" value="1" name="isNotProblem">是</label>
                            <label><input type="radio" value="2" name="isNotProblem" checked>否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sendRemark" class="col-sm-2 control-label">备注：</label>
                        <div class="col-sm-10">
                            <textarea  id="sendRemark" name="sendRemark" class="form-control" rows="5"
                            ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachmentIds" class="col-sm-2 control-label">附件：</label>
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
<%@include file="/common/paizhao/paizhao.jsp"%>
<script src="<%=request.getContextPath()%>/container/gov/dispatch/scripts/loadBlockLevelAndBlockOption.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/enterprise/scripts/check.js"></script>
</body>
</html>
