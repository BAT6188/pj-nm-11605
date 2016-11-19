<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%--<jsp:include page="/common/common_include.jsp" flush="true"/>--%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8">
    <title>系统操作日志</title>
    <script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
</head>
<body>
<div class="content content1 clearfix">
    <%--<a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;font-size: 20px;z-index: 0">系统操作日志列表</a>--%>
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
                    <form role="form" id="searchform">
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="opModule">操作模块：</label><input type="text" id="opModule" name="opModule" class="form-control" style="margin-left: 6px;"/>
                                <div class="form-group">
                                    <label for="delTime"  class="labelMarginLeft">操作时间段：</label>
                                    <div id="datetimepicker1" class="input-group date form_date" data-date=""  data-date-format="yyyy-mm-dd" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                        <input class="form-control" size="16" type="text" id="startTime" name="startTime" value="" readonly placeholder="开始时间">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                    —
                                    <div id="datetimepicker2" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                        <input class="form-control" size="16" type="text" id="endTime" name="endTime" value="" readonly placeholder="结束时间">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <p></p>
                        <div class="form-inline">
                            <label for="opUser">操作人员：</label> <input type="text" id="opUser" name="opUser" class="form-control" />
                            <label for="opType" class="labelMarginLeft">&nbsp;操作类型：</label>
                            <select style="width: 238px;" class="form-control"  id="opType" name="opType">
                                <option value="">全部</option>
                            </select>
                        </div>
                    </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
            </div>
            <div class="tableBox">
                <table id="table" class="table table-striped table-responsive">
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/office/scripts/sysOperationLog.js"></script>
</body>
</html>
