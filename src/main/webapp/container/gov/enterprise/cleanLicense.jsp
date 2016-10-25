<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>清洁生产审核</title>
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
                        <%--<label for="t_name">清洁生产审核名称</label>
                        <input type="text" id="t_name" class="form-control" />--%>
                        <%--<label for="t_recordDate">有效期起始日期 ：</label><input type="text" id="t_recordDate" class="form-control" />--%>
                        <%--<label for="t_recordDate">有效期结束日期：</label><input type="text" id="t_recordDate" class="form-control" />--%>
                    <%--</p>--%>
                        <form class="form-inline">
                            <div class="form-group">
                                <label for="t_name">清洁生产审核名称：</label>
                                <input type="text" id="t_name" class="form-control" />
                            </div>
                        </form>
                        <p/>
                        <form class="form-inline">
                            <div class="form-group">
                                <label for="t_startDate">有效期起始日期：</label>
                                <div id="t_startDateContent" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="startDate">
                                    <input class="form-control" size="16" id="t_startDate"  type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="t_endDate">有效期结束日期：</label>
                                <div id="t_endDateContent" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="endDate">
                                    <input class="form-control" size="16" id="t_endDate"  type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                            </div>
                        </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <br><br>
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
<div class="modal fade" id="scfForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加清洁生产审核</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">清洁生产审核名称*：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="清洁生产审核名称不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="startDate" class="col-sm-2 control-label">有效起始日期*：</label>
                        <div class="col-sm-4">
                            <div id="startDateContent" class="input-group date form_date" data-date="" data-link-field="startDate" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                <input class="form-control" id="startDate" name="startDate" size="16" type="text" value="" readonly
                                       data-message="有效起始日期不能为空"
                                       data-easytip="position:top;class:easy-red;">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>

                        </div>
                        <label for="endDate" class="col-sm-2 control-label">有效结束日期*：</label>
                        <div class="col-sm-4">
                            <div id="endDateContent" class="input-group date form_date" data-date="" data-link-field="endDate" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                <input class="form-control" id="endDate" name="endDate" size="16" type="text" value="" readonly
                                       data-message="有效结束日期不能为空"
                                       data-easytip="position:top;class:easy-red;">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="pubOrg" class="col-sm-2 control-label">发证机关*：</label>
                        <div class="col-sm-4">
                            <input type="text" id="pubOrg" name="pubOrg" class="form-control"
                                   data-message="发证机关不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="pubDate" class="col-sm-2 control-label">发证日期*：</label>
                        <div class="col-sm-4">
                            <div id="pubDateContent" class="input-group date form_date" data-date="" data-link-field="pubDate" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                <input class="form-control" id="pubDate" name="pubDate" size="16" type="text" value="" readonly
                                       data-message="发证日期不能为空"
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
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/enterprise/scripts/cleanLicense.js"></script>
</body>
</html>
