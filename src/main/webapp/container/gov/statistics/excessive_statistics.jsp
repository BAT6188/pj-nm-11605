<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/9
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/common_include.jsp"%>
    <title>超标统计</title>
    <script src="${pageContext.request.contextPath}/common/scripts/highcharts/highcharts.js"></script>
    <script src="${pageContext.request.contextPath}/common/scripts/highcharts/modules/exporting.js"></script>
    <style type="text/css">
       table{
           top:0px;
           cursor: pointer;
           background-color: #dddddd;
       }
       table tr td {
           text-align: center;
           font-weight: bold;
           border-bottom: 0px;
           border-right: 0px;
           height: 5px;
       }

    </style>
</head>
<body>
<div class="container-fluid">
    <!--搜索区域-->
    <div class="alert">
        <form class="form-inline" role="form">
            <div class="form-group">
                <label for="name">企业名称</label>
                <input type="text" id="s_name" class="form-control" />
            </div>
            <div class="form-group">
                <label for="name">日期</label>
                <input type="text" id="s_status" class="form-control" />
            </div>
            <button id="search" type="button" class="btn btn-success" >查询</button>
            <button id="searchFix" type="button" class="btn btn-default" >重置查询</button>
        </form>
    </div>
</div>

    <!--highcharts图形区-->
    <table border="1px" style="width: 25%;height: 5%;margin-left: 5px;">
        <tr>
            <td>柱状图</td>
            <td>饼状图</td>
            <td>折线图</td>
        </tr>
    </table>
    <div id="container" style="min-width:80%;min-height:75%;text-align: center;width:90%;padding-left: 5px;"></div>

<script src="scripts/excessive_statistics.js"></script>
</body>
</html>
