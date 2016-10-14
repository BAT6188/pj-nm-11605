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
    <%@include file="/common/common_include.jsp"%>
    <title>执法同期对比分析</title>
    <script src="${pageContext.request.contextPath}/common/scripts/highcharts/highcharts.js"></script>
    <script src="${pageContext.request.contextPath}/common/scripts/highcharts/modules/exporting.js"></script>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <div class="dealBox">
                <%--<div class="sideTitle left">--%>
                <%--<span class="blueMsg">--%>
                <%--<img class="tipImg" src="<%=request.getContextPath()%>/common/images/searchTip.png" alt=""/>--%>
                <%--<span class="text">查询</span>--%>
                <%--</span>--%>
                <%--</div>--%>
                    <p>
                    <div class="row">
                        <div class="col-md-4" >
                            <label for="s_name" class="col-sm-4 control-label text-right ">企业名称：</label> <input id="s_name" class="form-control" type="text"/>
                        </div>
                        <div class="col-md-8" >
                            <label for="startTime" class="col-sm-1 control-label text-right">日期:</label>
                            <div class="col-sm-4">
                                <div id="datetimepicker" class="input-group date form_date col-md-10" data-date="" data-date-format="yyyy-mm" data-link-field="dtp_input" data-link-format="yyyy-mm">
                                    <input class="form-control" id="startTime" name="startTime" size="16" type="text" value="" readonly
                                           data-message="时间不能为空"
                                           data-easytip="position:top;class:easy-red;"
                                    />
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div id="datetimepicker2" class="input-group date form_date col-md-10" data-date="" data-date-format="yyyy-mm" data-link-field="dtp_input" data-link-format="yyyy-mm">
                                    <input class="form-control" id="endTime" name="endTime" size="16" type="text" value="" readonly
                                           data-message="时间不能为空"
                                           data-easytip="position:top;class:easy-red;"
                                    />
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    </p>
                    <p>
                        <label for="contrastTime" class="col-sm-1 control-label text-right">同期对比:</label>
                    <div class="col-sm-8">
                        <div class="col-sm-4">
                            <div id="datetimepicker3" class="input-group date form_date col-md-10" data-date="" data-date-format="yyyy-mm" data-link-field="dtp_input" data-link-format="yyyy-mm">
                                <input class="form-control" id="firstTime" name="firstTime" size="16" type="text" value="" readonly
                                       data-message="时间不能为空"
                                       data-easytip="position:top;class:easy-red;"
                                />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div id="datetimepicker4" class="input-group date form_date col-md-10" data-date="" data-date-format="yyyy-mm" data-link-field="dtp_input" data-link-format="yyyy-mm">
                                <input class="form-control" id="lastTime" name="lastTime" size="16" type="text" value="" readonly
                                       data-message="时间不能为空"
                                       data-easytip="position:top;class:easy-red;"
                                />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    </p>
                <p>
                    <div class="queryBox marginLeft0" style="margin-left: 60% ">
                <p>
                    <label for="">执法类型：</label>
                    <select class="form-control" name="" id="">
                        <option value="1">信访</option>
                        <option value="2">例行检查</option>
                        <option value="3">12369</option>
                        <option value="4">区长热线</option>
                        <option value="5">市长热线</option>
                    </select>
                </p>
            </div>
            <button type="button" class="btn btn-md btn-success queryBtn" style="margin-left: 40%"><i class="btnIcon query-icon"></i><span>查询</span></button>
            </p>
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


<script src="<%=request.getContextPath()%>/container/gov/statistics/scripts/law_statistics.js"></script>

</body>
</html>
