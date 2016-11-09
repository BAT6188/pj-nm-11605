<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%
        String enterpriseId =request.getParameter("id");
    %>
    <title>突发环境事件应急预案</title>
    <script type="text/javascript">
            var enterpriseId='<%=enterpriseId%>'
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
                    <%--<p>--%>
                        <%--<label for="t_recordDate">备案日期</label><input type="text" id="t_recordDate" class="form-control" />--%>
                        <%--<label for="t_attnPerson">负责人</label><input type="text" id="t_attnPerson" class="form-control" />--%>
                    <%--</p>--%>
                        <form class="form-inline" id="searchform">
                            <div class="form-group">
                                <label for="">备案日期：</label>
                                <div id="startTimeDatetimepicker" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="startDate">
                                    <input class="form-control" id="startTime" size="16" type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                -
                                <div id="endTimeDatetimepicker" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="endDate">
                                    <input class="form-control" id="endTime" size="16" type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="t_attnPerson">经办人：</label><input type="text" id="t_attnPerson" class="form-control" />
                            </div>
                        </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="reset" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <br/>
                <br/>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#scfForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#scfForm">
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
<div class="modal fade" data-backdrop="static" id="scfForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加突发环境事件应急预案</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">排污单位名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="text" id="enterpriseName" name="enterpriseName" class="form-control"
                                   data-message="排污单位名称不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="recordCode" class="col-sm-2 control-label">备案编号<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                                <input type="text" id="recordCode" name="recordCode" class="form-control"
                                       data-message="备案编号不能为空"
                                       data-easytip="position:top;class:easy-red;"
                                />

                        </div>
                        <label for="acceptOrg" class="col-sm-2 control-label">受理机关<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="acceptOrg" name="acceptOrg" class="form-control"
                                   data-message="受理机关不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attnPerson" class="col-sm-2 control-label">经办人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="attnPerson" name="attnPerson" class="form-control"
                                   data-message="经办人不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="attnPhone" class="col-sm-2 control-label">经办人电话<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="attnPhone" name="attnPhone" class="form-control"
                                   data-message="经办人电话不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="recordDate" class="col-sm-2 control-label">备案日期<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="recordDateContent" class="input-group date form_date" data-date="" data-link-field="recordDate" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                <input class="form-control" id="recordDate" name="recordDate" size="16" type="text" value="" readonly
                                       data-message="备案日期不能为空"
                                       data-easytip="position:top;class:easy-red;">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
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
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/company/emergencyPlan/scripts/enterpriseplan.js"></script>
</body>
</html>
