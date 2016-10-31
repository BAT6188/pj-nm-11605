<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/12
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String id=request.getParameter("id");
    %>
    <title>企业台账</title>
    <jsp:include page="/common/common_ztree.jsp"></jsp:include>
    <script type="text/javascript">
        var enterpriseId='<%=id%>';
    </script>
    <style>
        .menuDiv h3{
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="wrap">
    <div class="menu-left left">
        <div class="scrollContent" >
            <ul id="enterpriseZTree" class="ztree"></ul>
        </div>
    </div>
    <div id="level3MenuContent" class="main-right right">
        <%--<jsp:include page="enterpriseInfo.jsp"></jsp:include>--%>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/monitor/scripts/lookMointor.js"></script>
<script type="text/javascript">
    $(function(){

    })
</script>
</body>
</html>
