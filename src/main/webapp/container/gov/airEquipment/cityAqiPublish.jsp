<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/9
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>空气质量在线监测</title>
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
                            <label for="quality" class="labelMarginLeft">空气质量等级：</label>
                            <select class="form-control" id="quality" name="quality" style="width: 238px;">
                                <option value="">全部</option>
                                <option value="优">优</option>
                                <option value="良">良</option>
                                <option value="轻度污染">轻度污染</option>
                                <option value="中度污染">中度污染</option>
                                <option value="重度污染">重度污染</option>
                                <option value="严重污染">严重污染</option>
                            </select>
                            <label for="startTime" class="labelMarginLeft">监测时间段：</label>
                            <div id="datetimepicker1" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                <input class="form-control" size="16" type="text" id="startTime" name="startTime" value="" readonly placeholder="开始时间">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                            —
                            <div id="datetimepicker2" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                <input class="form-control" size="16" type="text" id="endTime" name="endTime" value="" readonly placeholder="结束时间">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </form >
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <%--<p class="btnListP">--%>
                    <%--<button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#scfForm">--%>
                        <%--<i class="btnIcon add-icon"></i><span>新建</span>--%>
                    <%--</button>--%>
                    <%--<button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#scfForm">--%>
                        <%--<i class="btnIcon edit-icon"></i><span>修改</span>--%>
                    <%--</button>--%>
                    <%--<button id="remove" type="button" class="btn btn-sm btn-danger">--%>
                    <%--<i class="btnIcon delf-icon"></i><span>删除</span>--%>
                    <%--</button>--%>
                <%--</p>--%>
            </div>
            <div class="tableBox">
                <table id="table" class="table table-striped table-responsive">
                </table>
            </div>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/container/gov/airEquipment/scripts/cityAqiPublish.js"></script>
</body>
</html>
