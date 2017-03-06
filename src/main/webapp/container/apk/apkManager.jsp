<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>移动端管理</title>
    <%--<%@include file="/common/msgSend/msgSend.jsp"%>--%>
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
                            <%--<div class="form-group">
                                <label for="">提醒名称：</label> <input type="text" id="s_title" style="width: 180px;" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="">提醒时间：</label>
                                <div id="" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                    <input class="form-control" size="16" id="start_alertTime"  type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                -
                                <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                    <input class="form-control" size="16" id="end_alertTime"  type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                            </div>--%>
                        </form>
                    <p></p>
                </div>
                <%--<button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button type="button" class="btn btn-default" onclick="resetQuery()"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>--%>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#demoForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <%--<button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#demoForm">
                        <i class="btnIcon edit-icon"></i><span>修改</span>
                    </button>--%>
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
<div class="modal fade" id="demoForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 905px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="apkVersionNum" class="col-sm-2 control-label">版本号<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="number" id="apkVersionNum" name="apkVersionNum"class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;">
                            <input type="hidden" id="removeId" name="removeId">
                        </div>
                        <label for="apkVersionCode" class="col-sm-2 control-label">编码<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="apkVersionCode" name="apkVersionCode" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="apkName" class="col-sm-2 control-label">名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="apkName" name="apkName"class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="updateInfo" class="col-sm-2 control-label">更新信息<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea type="text" rows="4" cols="50" id="updateInfo" name="updateInfo" class="form-control"
                                      data-message="不能为空"
                                      data-easytip="position:top;class:easy-red;"
                            ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">apk文件：</label>
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
<script src="<%=request.getContextPath()%>/container/apk/scripts/apkManager.js"></script>
</body>
</html>
