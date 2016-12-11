<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息公告系统</title>

    <%
        //portal中配置的根据用户角色
        String role = request.getParameter("role");
    %>
    <script>
        var role='<%=role%>'
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
                                <label >标题：</label> <input type="text"  name="title"   style="width: 180px;" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label >信息类型：</label> <input type="text"  name="type" style="width: 180px;" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label>发布时间：</label>
                                <div id="s_pubTimeContent" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="pubTime">
                                    <input class="form-control" size="16"  name="startTime"  type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                -
                                <div id="t_startCreateDateContent" class="input-group date searchInput form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="pubTime">
                                    <input class="form-control" size="16" name="endTime"  type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                            </div>
                        </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <br/> <br/>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#scfForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <%--<button type="button" class="btn btn-primary" id="pub">发布</button>--%>
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
    <div class="modal-dialog"  style="width: 900px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加信息公告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">标题<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="pubOrgId" name="pubOrgId">
                            <input type="hidden" id="userName" name="userName">
                            <input type="hidden" id="status" name="status">
                            <input type="hidden" id="userID" name="userID">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="text" id="title" name="title" class="form-control"
                                   data-message="标题不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="pubTime" class="col-sm-2 control-label">发布时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="pubTimeContent" class="input-group date form_date" data-date="" data-link-field="pubTime" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                <input class="form-control" id="pubTime" name="pubTime" size="16" type="text" value="" readonly
                                       data-message="发布时间不能为空"
                                       data-easytip="position:top;class:easy-red;">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pubOrgName" class="col-sm-2 control-label">发布单位<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="pubOrgName" name="pubOrgName" class="form-control"
                                   data-message="发布单位不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="type" class="col-sm-2 control-label">公告类型<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="type" name="type" class="form-control"
                                   data-message="公告类型不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="grade" class="col-sm-2 control-label">查看权限<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10" id="grade">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">公告详情<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea  id="content" name="content" class="form-control" rows="5"
                                       data-message="公告详情不能为空"
                                       data-easytip="position:top;class:easy-red;"
                            ></textarea>
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
                <button type="button" class="btn btn-md btn-warning needHide" id="pub">发布</button>
                <button type="button" class="btn btn-primary" id="save">保存</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/office/scripts/pubinfo.js"></script>
</body>
</html>
