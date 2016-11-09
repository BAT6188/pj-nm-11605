<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/17
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String Date = sdf.format(new Date());
    %>
    <title>空气质量统计</title>
    <style type="text/css">
        .chart-list {
            text-align: center;
            height: 42px;
        }
        .chart-list li {
            float: left;
            width: 33.33%;
            height: 100%;
        }
    </style>
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
                            <label for="">空气质量：</label>
                            <select class="form-control" name="" id="airType">
                                <option value="">请选择</option>
                                <option value="1">优</option>
                                <option value="2">良</option>
                                <option value="3">轻度污染</option>
                                <option value="4">中度污染</option>
                                <option value="5">重度污染</option>
                                <option value="6">严重污染</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="">日期：</label>
                            <div id="datetimepicker1" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm" data-link-field="sendTime">
                                <input class="form-control" size="16" id="start_createTime"  type="text" value="" readonly placeholder="开始时间">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm" data-link-field="sendTime">
                                <input class="form-control" size="16" id="end_createTime"  type="text" value="" readonly placeholder="结束时间">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                        <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                        <button type="button" class="btn btn-default" onclick="resetQuery()"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                    </form>
                    <p></p>
                    <form class="form-inline">

                        <div class="form-group">
                            <label for="">同期对比：</label>
                            <div id="datetimepicker2" class="input-group date form_datetimes" data-date="" data-date-format="yyyy" data-link-field="sendTime">
                                <input class="form-control" size="16" id="startTime"  type="text" value="<%=Date%>" readonly placeholder="请选择年份">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <button type="button" id="sbYear" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>上半年</span></button>
                            <button type="button" id="xbYear" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>下半年</span></button>
                            <%--<div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">--%>
                                <%--<input class="form-control" size="16" id="endTime"  type="text" value="" readonly>--%>
                                <%--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>--%>
                                <%--<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>--%>
                            <%--</div>--%>
                        </div>
                    </form>
                </div>
            </div>
            <div class="tableBox">
                <div class="chart-box">
                    <div class="chart-list">
                        <ul class="clearfix">
                            <li id="columnBtn" data-checked="1"><a href="javascript:;">柱状图</a></li>
                            <li id="pieBtn" data-checked="2"><a href="javascript:;">饼状图</a></li>
                            <li id="lineBtn" data-checked="3"><a href="javascript:;">折线图</a></li>
                        </ul>
                    </div>
                    <div id="container" style="min-width:100%;min-height:100%;text-align: center;width:100%;"></div>
                    <div style="width:50%; height:100%;float:left;">
                        <div id="container1" style="min-width:100%;min-height:100%;text-align: center;width:100%;"></div>
                    </div>
                    <div style="width:50%; height:100%;float:right;">
                        <div id="container2" style="min-width:100%;min-height:100%;text-align: center;width:100%;display:none;"></div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/container/gov/statistics/scripts/air_quality_ratio.js"></script>

</body>
</html>
