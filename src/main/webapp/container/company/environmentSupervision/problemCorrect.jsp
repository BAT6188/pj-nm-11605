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
    <title>存在的问题及整改情况</title>
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
                                <label for="">存在问题：</label>
                                <select name="problemType" class="form-control">
                                    <option value="">全部</option>
                                    <option value="1">安全隐患</option>
                                    <option value="2">总量减排</option>
                                    <option value="3">非法排污</option>
                                    <option value="4">未批先建</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="">问题进度：</label>
                                <select name="progress" class="form-control">
                                    <option value="">全部</option>
                                    <option value="1">暂存</option>
                                    <option value="2">已消耗</option>
                                </select>
                            </div>
                        </form>
                    <p></p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button type="button" class="btn btn-default" onclick="resetQuery()"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <%--<button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#demoForm">--%>
                        <%--<i class="btnIcon add-icon"></i><span>新建</span>--%>
                    <%--</button>--%>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#demoForm">
                        <i class="btnIcon edit-icon"></i><span>反馈</span>
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
<div class="modal fade" id="demoForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 823px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">创建时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="createTime"  name="createTime" type="text" value="" data-message="不能为空"
                                       data-easytip="position:top;class:easy-red;" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                        <label for="" class="col-sm-2 control-label">问题类型<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select name="problemType" id="problemType" class="form-control">
                                <option value="1">安全隐患</option>
                                <option value="2">总量减排</option>
                                <option value="3">非法排污</option>
                                <option value="4">未批先建</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">问题描述：</label>
                        <div class="col-sm-10">
                            <textarea id="problemDesc" name="problemDesc" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">整改情况：</label>
                        <div class="col-sm-10">
                            <textarea id="correctDesc" name="correctDesc" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-primary" id="send"></button>--%>
                <button type="button" class="btn btn-primary" id="save">发送</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/company/environmentSupervision/scripts/problemCorrect.js"></script>
</body>
</html>
