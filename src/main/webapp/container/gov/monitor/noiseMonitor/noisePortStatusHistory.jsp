<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%
        String id=request.getParameter("id");
    %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8">
    <title>噪声源排口实时数据</title>
    <script type="text/javascript">

    </script>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;font-size: 15px;z-index: 0;">噪声源 实时数据</a>
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
                                <label for="name">监测点：</label> <input type="text" id="portName" name="name" class="form-control" />
                                <label for="monitorTime" class="labelMarginLeft">监测时间段：</label>
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
                                <%--<label for="monitorStatus" class="labelMarginLeft">超标状态：</label>
                                <select style="width: 300px;" class="form-control" id="monitorStatus" name="monitorStatus">
                                    <option value="">全部</option>
                                    <option value="over">超标</option>
                                    <option value="max">异常上限</option>
                                    <option value="min">异常下限</option>
                                </select>--%>
                            </div>
                        </div>
                    </form>
                    <p></p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="resetSearch" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
            </div>
            <div class="tableBox">
                <table id="table" class="table table-striped table-responsive">
                </table>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/monitor/noiseMonitor/scripts/noisePortStatusHistory.js"></script>
</body>
</html>
