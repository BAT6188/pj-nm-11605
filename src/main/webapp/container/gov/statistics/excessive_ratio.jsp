<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/12
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>超标同期对比</title>
    <script src="${pageContext.request.contextPath}/common/scripts/highcharts/highcharts.js"></script>
    <script src="${pageContext.request.contextPath}/common/scripts/highcharts/modules/exporting.js"></script>
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
                            <label for="s_name">姓名：</label> <input type="text" id="s_name" style="width: 180px;" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="">日期：</label>
                            <div id="datetimepicker1" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="start_createTime"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="end_createTime"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </form>
                    <p></p>
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="">同期对比：</label>
                            <div id="datetimepicker2" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="sendTime">
                                <input class="form-control" size="16" id="startTime"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="sendTime">
                                <input class="form-control" size="16" id="endtime"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                </div>
            </div>
            <div class="tableBox">
                <div class="chart-box">
                    <div class="chart-list">
                        <ul class="clearfix">
                            <li class="active"><a href="javascript:;">柱状图</a></li>
                            <li><a href="javascript:;">饼状图</a></li>
                            <li><a href="javascript:;">折线图</a></li>
                        </ul>
                    </div>
                    <div class="chart-content">
                        <div class="chartBox chartBox1">
                            <div class="chart">
                                <img src="<%=request.getContextPath()%>/common/images/tree/chart1.png" alt=""/>
                            </div>
                        </div>
                        <div class="chartBox chartBox2">
                            <div class="chart">
                                <img src="<%=request.getContextPath()%>/common/images/tree/chart2.png" alt=""/>
                            </div>
                        </div>
                        <div class="chartBox chartBox3">
                            <div class="chart">
                                <img src="<%=request.getContextPath()%>/common/images/tree/chart3.png" alt=""/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="<%=request.getContextPath()%>/container/gov/statistics/scripts/excessive_ratio.js"></script>

</body>
</html>
