<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%--<jsp:include page="/common/common_include.jsp" flush="true"/>--%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8">
    <title>删除排污单位列表</title>
</head>
<body>
<div class="content content1 clearfix">
    <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;font-size: 20px;">已删除排污档案列表</a>
    <div class="wrap">
        <div class="mainBox">
            <div class="dealBox">
                <form class="form-horizontal" role="form" id="searchform">
                    <div class="form-group">
                        <label for="name" class="col-sm-1 control-label">单位名称：</label>
                        <div class="col-sm-4">
                            <input type="text" id="name" name="name" class="form-control" />
                        </div>
                        <label for="delOpinion" class="col-sm-2 control-label">删除意见：</label>
                        <div class="col-sm-4">
                            <input type="text" id="delOpinion" name="delOpinion" class="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="delerName" class="col-sm-1 control-label">操作人：</label>
                        <div class="col-sm-4">
                            <input type="text" id="delerName" name="delerName" class="form-control" />
                        </div>
                        <label for="area" class="col-sm-2 control-label">所属行政区：</label>
                        <div class="col-sm-4">
                            <input type="text" id="area" name="area" class="form-control" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="area" class="col-sm-1 control-label">操作时间：</label>
                        <div class="col-sm-4">
                            <div id="datetimepicker1" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                <input class="form-control" size="16" type="text" id="startTime" name="startTime" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div  class="col-sm-6">
                            —
                        </div>
                        <div  class="col-sm-6">
                            <div id="datetimepicker2" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                <input class="form-control" size="16" type="text" id="endTime" name="endTime" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-9">

                        </div>
                        <div class="col-sm-1" style="text-align: right;">
                            <button id="search" type="button" class="btn btn-success" >查询</button>
                        </div>
                        <div class="col-sm-1" style="text-align: right;">
                            <button id="searchFix" type="button" class="btn btn-default" >重置</button>
                        </div>
                    </div>
                </form>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success">
                        <i class="btnIcon add-icon"></i><span>新增</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning">
                        <i class="btnIcon edit-icon"></i><span>修改</span>
                    </button>
                    <button id="remove" type="button" class="btn btn-sm btn-danger">
                        <i class="btnIcon delf-icon"></i><span>删除</span>
                    </button>
                    <button id="export" type="button" class="btn btn-sm btn-success" >
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
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/enterprise/scripts/enterpriseListOfDel.js"></script>
</body>
</html>
