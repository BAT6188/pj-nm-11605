<%@ page import="com.harmonywisdom.apportal.common.configuration.ConfigureManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <%@include file="/common/common_include.jsp"%>
    <title>东胜环保</title>
</head>
<body>
<div class="main clearfix">
    <div class="banner clearfix">
        <div class="logoDiv left">
            <img src="<%=request.getContextPath()%>/common/images/indexlogo.png" alt=""/>
        </div>
        <div class="opDiv right">
            <ul>
                <li><a href="#"><img src="<%=request.getContextPath()%>/common/images/mail-icon.png" alt=""/><span class="text">发送短信</span></a></li>
                <li class="divider"><i class="short-divider"></i></li>
                <li class="user"><a href="javascript:;"><img src="<%=request.getContextPath()%>/common/images/user.png" alt=""/><span class="text"><%=userName%></span></a></li>
                <li><a href="#" class="msg-icon"><span class="new-icon">0</span></a></li>
                <li class="divider"><i class="long-divider"></i></li>
                <li><a href="#"><img src="<%=request.getContextPath()%>/common/images/loginout-icon.png" onclick='window.location.href = "<%=ConfigureManager.getInstance().getSsoConfig().getSsoGateWaySite()%>/logout.action";' alt="退出登陆"/></a></li>
            </ul>
        </div>
    </div>
    <div class="main-content">
        <div class="link-list">
            <ul class="clearfix">
                <%--<li><a href="main.jsp?SToken=${param.SToken}"><img src="<%=request.getContextPath()%>/common/images/composite-img.png" alt=""/></a></li>
                <li><a href="main.jsp?SToken=${param.SToken}"><img src="<%=request.getContextPath()%>/common/images/exelaw-img.png" alt=""/></a></li>
                <li><a href="main.jsp?SToken=${param.SToken}"><img src="<%=request.getContextPath()%>/common/images/statistics-img.png" alt=""/></a></li>
                <li><a href="main.jsp?SToken=${param.SToken}"><img src="<%=request.getContextPath()%>/common/images/monitor-img.png" alt=""/></a></li>
                <li><a href="main.jsp?SToken=${param.SToken}"><img src="<%=request.getContextPath()%>/common/images/dispatch-img.png" alt=""/></a></li>
                <li><a href="main.jsp?SToken=${param.SToken}"><img src="<%=request.getContextPath()%>/common/images/office-img.png" alt=""/></a></li>
                <li><a href="main.jsp?SToken=${param.SToken}"><img src="<%=request.getContextPath()%>/common/images/dsyjt-img.png" alt=""/></a></li>
                <li><a href="main.jsp?SToken=${param.SToken}"><img src="<%=request.getContextPath()%>/common/images/detect-img.png" alt=""/></a></li>--%>
            </ul>
        </div>
        <div class="bg-icon">
            <img src="<%=request.getContextPath()%>/common/images/bg-icon.png" alt=""/>
        </div>
    </div>
</div>
<p class="copyrightP"><span>版权所有：东胜环保局</span><span>技术支持：航天正通汇智科技股份有限公司</span></p>
<!--样式js-->
<script>
    function loadHeight(){
        var main = document.querySelector(".main");
        var mainContent = document.querySelector(".main-content");
        mainContent.style.height=(main.offsetHeight - 76)+"px";//右侧列表宽度自适应
    }
    window.onload=loadHeight();
    window.onresize=loadHeight();
    //加载主菜单
    pageUtils.loadMenu(function (mainMenu) {
        //加载一级主菜单
        for(var i = 0; i < mainMenu.length; i++){
            var menu = mainMenu[i];
            var li = $('<li><a href="main.jsp?menuCode='+menu.code+'&SToken='+SToken+'"><img src="<%=request.getContextPath()%>/common/images/'+menu.code+'-img.png" alt="'+menu.text+'"/></a></li>');
            $(".link-list>ul").append(li);
        }
    })
</script>
</body>
</html>
