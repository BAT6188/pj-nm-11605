<%@ page import="com.harmonywisdom.apportal.common.configuration.ConfigureManager" %>
<%@ page import="com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <%@include file="/common/common_include.jsp"%>
    <%
        Enterprise enterprise = (Enterprise) request.getSession().getAttribute("session");
    %>
    <script>
        var menuCode = '${param.menuCode}';
        var enterpriseId = "<%=enterprise != null ? enterprise.getId():""%>";
        console.log(enterpriseId);
    </script>
    <title>东胜环保</title>
    <style type="text/css">
        .client-type{
            color: #bb0000;
            font-weight: bold;
            font-size: 24px;
            font-style: italic;
            width:80px;
            height: 32px;
            position: absolute;
            left: 36%;
            top: 3%;
        }

        .logoDiv>.logo{position: relative;}
        .com-icon{position: absolute;top: -25px;right: -85px;}

    </style>
</head>
<body style="overflow: hidden">
<div class="container">
    <div class="banner clearfix">
        <div class="logoDiv left">
            <span class="logo"><img src="<%=request.getContextPath()%>/common/images/indexlogo.png" alt=""/><img class="com-icon" src="<%=request.getContextPath()%>/container/company/homePage/images/com-icon.png" alt=""/></span>
        </div>
        <div class="opDiv right">
            <ul>
                <%--<li><a href="#"><img src="<%=request.getContextPath()%>/common/images/mail-icon.png" alt=""/><span class="text">发送短信</span></a></li>--%>
                <%--<li class="divider"><i class="short-divider"></i></li>--%>
                <%--<li class="user"><a href="javascript:;"><img src="<%=request.getContextPath()%>/common/images/user.jpg" alt=""/><span class="text"><%=userName%></span></a></li>--%>
                <%--<li><a href="#" class="msg-icon"><span class="new-icon">0</span></a></li>--%>
                <li class="divider"><i class="long-divider"></i></li>
                <li><a href="#"><img src="<%=request.getContextPath()%>/common/images/loginout-icon.png" onclick='window.location.href = "${pageContext.request.contextPath}/container/company/login/login.jsp";' alt="退出登陆"/></a></li>
            </ul>
        </div>
    </div>
    <div class="box box5">
        <div class="nav-menu linear ">
            <ul id="level2Menu" class="navList">
                <%--<li class="list1 linear-hover"><a href="javascript:;">首页</a></li>--%>
                <%--<li class="list1 "><a href="javascript:;">预警及排污超标处理情况报送</a></li>--%>
                <%--<li class="list2 "><a href="javascript:;">隐患自查自报</a></li>--%>
                <%--<li class="list2 "><a href="javascript:;">信息公告</a></li>--%>
                <%--<li class="list2 "><a href="javascript:;">一企一档企业台账</a></li>--%>
            </ul>
        </div>
        <div id="level2content" class="content show clearfix" style="margin: 5px 5px 8px;">
        </div>
</div>

<script src="<%=request.getContextPath()%>/common/scripts/main_css.js"></script>
<script src="<%=request.getContextPath()%>/container/company/login/scripts/index.js"></script>
<script>
</script>
</body>
</html>
