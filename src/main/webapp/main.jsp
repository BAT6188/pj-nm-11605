<%@ page import="com.harmonywisdom.apportal.common.configuration.ConfigureManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <%@include file="/common/common_include.jsp"%>
    <script>
        var menuCode = '${param.menuCode}';
        var subMenuId = '${param.subMenuId}';
        var firsetLoad = true;
    </script>
    <title>东胜环保</title>
</head>
<body style="overflow: hidden">
<div class="container">
    <div class="banner clearfix">
        <div class="logoDiv left">
            <img src="<%=request.getContextPath()%>/common/images/indexlogo.png" alt=""/>
        </div>
        <div class="opDiv right">
            <ul>
                <li><a href="javascript:void(0);" id="apkDownBtn" title="扫一扫二维码下载"  data-toggle="modal" data-target="#apkModal"><img width="25" height="25" src="<%=request.getContextPath()%>/container/apk/images/appDownload.png"/><span class="text">扫码下载</span></a></li>
                <li><a href="javascript:void(0);" id="mainSmsSendBtn"><img src="<%=request.getContextPath()%>/common/images/mail-icon.png" alt=""/><span class="text">发送短信</span></a></li>
                <li class="divider"><i class="short-divider"></i></li>
                <li class="user"><a href="javascript:;" id="updatePasswordBtn"><img src="<%=request.getContextPath()%>/common/images/user.jpg" alt=""/><span class="text"><%=userName%></span></a></li>
                <li><a href="javascript:void(0);" class="msg-icon" id="msgListBtn"><span class="new-icon" id="msgCountSpan">0</span></a></li>
                <li class="divider"><i class="long-divider"></i></li>
                <li><a href="javascript:void(0);"><img src="<%=request.getContextPath()%>/common/images/loginout-icon.png" onclick='window.location.href = "<%=ConfigureManager.getInstance().getSsoConfig().getSsoGateWaySite()%>/logout.action";' alt="退出登陆"/></a></li>
            </ul>
        </div>
    </div>
    <div class="box box5">
        <div class="nav-menu linear ">
            <ul id="level2Menu" class="navList">
                <%--<li class="list1 linear-hover"><a href="javascript:;">日程安排</a></li>
                <li class="list2"><a href="javascript:;">委托监测</a></li>
                <li class="list2"><a href="javascript:;">企业委托监测</a></li>
                <li class="list2"><a href="javascript:;">空气质量监测</a></li>--%>
            </ul>
        </div>
        <div id="level2content" class="content show clearfix" style="margin: 5px 5px 8px;">
        </div>
        <div class="siderNav" style="z-index: 9999">
            <ul>
                <%--<li><a href="compre-supervision.html"><dl><dt><img src="<%=request.getContextPath()%>/common/images/side-composite-icon.png" alt=""/></dt><dd>综合监管</dd></dl></a></li>
                <li><a href="pollution-23.html"><dl><dt><img src="<%=request.getContextPath()%>/common/images/side-monitor-icon.png" alt=""/></dt><dd>污染源监控</dd></dl></a></li>
                <li><a href="compre-dispatch.html"><dl><dt><img src="<%=request.getContextPath()%>/common/images/side-dispatch-icon.png" alt=""/></dt><dd>综合调度</dd></dl></a></li>
                <li><a href="law-supervision.html"><dl><dt><img src="<%=request.getContextPath()%>/common/images/side-exelaw-icon.png" alt=""/></dt><dd>执法监管</dd></dl></a></li>
                <li><a href="compre-monitor.html"><dl><dt><img src="<%=request.getContextPath()%>/common/images/side-detect-icon.png" alt=""/></dt><dd>综合监测</dd></dl></a></li>
                <li><a href="daily-office.html"><dl><dt><img src="<%=request.getContextPath()%>/common/images/side-office-icon.png" alt=""/></dt><dd>日常办公</dd></dl></a></li>
                <li><a href="compre-statistics.html"><dl><dt><img src="<%=request.getContextPath()%>/common/images/side-statistics-icon.png" alt=""/></dt><dd>综合统计</dd></dl></a></li>
                <li><a href="foreign-mission.html"><dl><dt><img src="<%=request.getContextPath()%>/common/images/side-dsyjt-icon.png" alt=""/></dt><dd>对外宣教</dd></dl></a></li>--%>
            </ul>
        </div>
    </div>
</div>
<div class="modal fade" id="apkModal" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 315px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">扫一扫下载客户端</h4>
            </div>
            <div class="modal-body" style="display: table-cell; vertical-align:middle; text-align:center; display: block;">
                <img style="vertical-align:middle;" src="<%=request.getContextPath()%>/container/apk/images/appDownload.png" alt="扫一扫二维码下载"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<%@include file="/container/gov/alert/message_dialog.jsp"%>
<%@include file="/container/gov/alert/messagetrace.jsp"%>
<%@include file="/common/msgSend/msgSend.jsp"%>
<%--<%@include file="/common/paizhao/paizhao.jsp"%>--%>
<%@include file="/common/updatePassword/updatePassword.jsp"%>
<%@include file="/common/portAlert/portAlert.jsp"%>
<script src="common/scripts/main_css.js"></script>
<script src="common/scripts/main.js"></script>
<script>
</script>
</body>
</html>
