<%@ page import="com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/10
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%
        Enterprise enterprise = (Enterprise) request.getSession().getAttribute("session");
    %>
    <title>隐患自查自报</title>
    <script type="text/javascript">
        var enterpriseId = "<%=enterprise != null ? enterprise.getId():""%>";
        var enterpriseName = "<%=enterprise != null ? enterprise.getName():""%>";
        console.log(enterpriseName);
        console.log(enterpriseId);
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
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="s_name">隐患名称：</label> <input type="text" id="s_name" name="name" style="width: 180px;" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="">发现日期：</label>
                            <div id="" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="start_findDate" name="start_findDate"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="end_findDate" name="end_findDate"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </form>
                    <p></p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#demoForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#demoForm">
                        <i class="btnIcon edit-icon"></i><span>修改</span>
                    </button>
                    <%--<button id="feedback" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#feedbackForm">--%>
                        <%--<i class="btnIcon edit-icon"></i><span>反馈</span>--%>
                    <%--</button>--%>
                    <button id="remove" type="button" class="btn btn-sm btn-danger">
                        <i class="btnIcon delf-icon"></i><span>删除</span>
                    </button>
                    <button id="export" type="button" class="btn btn-sm btn-success">
                        <span class="glyphicon glyphicon-export"></span>导出
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
<div class="modal fade" id="demoForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 916px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">隐患名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>

                        <label for="place" class="col-sm-2 control-label">隐患部位<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="place" name="place" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-2 control-label">隐患信息描述<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="description" name="description" class="form-control" rows="4" cols="50"
                                      data-message="不能为空"
                                      data-easytip="position:top;class:easy-red;">
                            </textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="principal" class="col-sm-2 control-label">整改负责人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="principal" name="principal" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>

                        <label for="linkPhone" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="linkPhone" name="linkPhone" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="findDate" class="col-sm-2 control-label">发现日期<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="findDate"  name="findDate" type="text" readonly
                                       data-message="不能为空"
                                       data-easytip="position:top;class:easy-red;"
                                />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>

                        <label for="finishDate" class="col-sm-2 control-label">整改完成日期<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="finishDate"  name="finishDate" type="text" readonly
                                       data-message="不能为空"
                                       data-easytip="position:top;class:easy-red;"
                                />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="solution" class="col-sm-2 control-label">整改措施方案<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="solution" name="solution" class="form-control" rows="4" cols="50"
                                      data-message="不能为空"
                                      data-easytip="position:top;class:easy-red;">
                            </textarea>
                        </div>
                    </div>
                    <div class="form-group query">
                        <label for="" class="col-sm-2 control-label">反馈时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input class="form-control" id="feedbackTime" type="text"/>
                        </div>

                    </div>
                    <div class="form-group query">
                        <label for="" class="col-sm-2 control-label">反馈内容<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="feedbackContent"  class="form-control" rows="4" cols="50"></textarea>
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

<!--反馈表单-->
<div class="modal fade" id="feedbackForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 916px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">反馈</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">反馈企业<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input class="form-control" id="enterpriseName"  name="enterpriseName" type="text" />
                        </div>

                    </div>

                    <div class="form-group">
                        <label for="findDate" class="col-sm-2 control-label">反馈时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input class="form-control" id="feedbackTime_feedbackForm"  name="feedbackTime_feedbackForm" type="text" disabled/>
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="solution" class="col-sm-2 control-label">反馈内容<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="feedbackContent_feedbackForm" name="feedbackContent_feedbackForm" class="form-control" rows="4" cols="50"  data-message="不能为空"
                                      data-easytip="position:top;class:easy-red;"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="saveFeedback">发送</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/company/dangerInspection/scripts/dangerInspection.js"></script>
</body>
</html>

