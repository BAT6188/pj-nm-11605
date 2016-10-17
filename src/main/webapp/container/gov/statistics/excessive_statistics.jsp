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
    <title>超标统计</title>
    <script src="${pageContext.request.contextPath}/common/scripts/highcharts/highcharts.js"></script>
    <script src="${pageContext.request.contextPath}/common/scripts/highcharts/modules/exporting.js"></script>
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
                            <label for="s_name">企业名称：</label> <input type="text" id="s_name" style="width: 180px;" class="form-control" />
                            <input id="selCompanyBtn" style="color: #fff;background-color: #449d44;border-color: #398439; width:15%;" type="button" value="选择" class="form-control" data-toggle="modal" data-target="#demoForm"/>
                        </div>
                        <div class="form-group">
                            <label for="">日期：</label>
                            <div id="datetimepicker1" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="sendTime">
                                <input class="form-control" size="16" id="start_createTime"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="sendTime">
                                <input class="form-control" size="16" id="end_createTime"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
            </div>
            <div class="tableBox">
                    <div class="chart-box">
                        <div class="chart-list">
                            <ul class="clearfix" >
                                <li id="columnBtn" data-checked="1"><a href="javascript:;">柱状图</a></li>
                                <li id="pieBtn" data-checked="2"><a href="javascript:;">饼状图</a></li>
                                <li id="lineBtn" data-checked="3"><a href="javascript:;">折线图</a></li>
                            </ul>
                        </div>
                        <%--<div class="chart-content">--%>
                            <%--<div class="chartBox chartBox1">--%>
                                <%--<div class="chart">--%>
                                    <div id="container" style="min-width:100%;min-height:100%;text-align: center;width:90%;padding-left: 5px;"></div>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="chartBox chartBox2">--%>
                                <%--<div class="chart">--%>
                                    <%--<img src="<%=request.getContextPath()%>/common/images/tree/chart2.png" alt=""/>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="chartBox chartBox3">--%>
                                <%--<div class="chart">--%>
                                    <%--<img src="<%=request.getContextPath()%>/common/images/tree/chart3.png" alt=""/>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
            </div>

        </div>
    </div>
</div>
<!--选择企业-->
<div class="modal fade" id="demoForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">


                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save">保存</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<script src="<%=request.getContextPath()%>/container/gov/statistics/scripts/excessive_statistics.js"></script>
</body>
</html>
