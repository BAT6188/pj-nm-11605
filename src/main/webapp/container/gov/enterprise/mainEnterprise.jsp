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
    <jsp:include page="/common/common_include.jsp" flush="true"/>
    <%
        String handleType=request.getParameter("handleType");
        String id=request.getParameter("id");
    %>
    <title>企业台账</title>
    <script type="text/javascript">
        var handleType='<%=handleType%>';
        var id='<%=id%>';
    </script>
</head>
<body>
<div class="container">
    <div class="box box1">
        <div class="content content1 clearfix">
            <div class="wrap">
                <div class="menu-left left">
                    <div class="menuDiv">
                        <h3><a href="javascript:;">基础信息</a></h3>
                        <ul>
                            <li class="curLi"><a href="javascript:loadPage('enterpriseInfo.jsp?handleType=<%=handleType%>&id=<%=id%>')">基本信息</a></li>
                            <li><a href="javascript:loadPage('enterpriseListOfRun.jsp')">废气排口</a></li>
                            <li><a href="#">废水排口</a></li>
                            <li><a href="#">噪声源</a></li>
                            <li><a href="#">主要产品及规模</a></li>
                        </ul>
                    </div>
                    <div class="menuDiv">
                        <h3><a href="javascript:;">生产设备信息</a></h3>
                        <ul>
                            <li><a href="javascript:;">燃煤锅炉信息</a></li>
                            <li><a href="javascript:;">窑炉信息</a></li>
                            <li><a href="javascript:;">其他生产设备信息</a></li>
                        </ul>
                    </div>
                    <div class="menuDiv">
                        <h3><a href="javascript:;">污染防治设施建设和运营信息</a></h3>
                        <ul>
                            <li><a href="javascript:;">水污染治理设施建设和运营情况</a></li>
                            <li><a href="javascript:;">大气污染治理设施建设和运营情况</a></li>
                            <li><a href="javascript:;">固体废物贮存及治理设施建设和运营情况</a></li>
                            <li><a href="javascript:;">噪声污染治理设施建设和运营情况</a></li>
                            <li><a href="javascript:;">监测设备信息</a></li>
                        </ul>
                    </div>
                    <div class="menuDiv">
                        <h3><a href="javascript:;">建设项目环评及其他许可情况</a></h3>
                        <ul>
                            <li><a href="javascript:;">建设项目环评及验收信息</a></li>
                            <li><a href="javascript:;">排污许可证信息</a></li>
                            <li><a href="javascript:;">清洁生产审核</a></li>
                        </ul>
                    </div>
                    <div class="menuDiv">
                        <h3><a href="javascript:;">突发环境事件应急预案</a></h3>
                        <ul>
                            <li><a href="javascript:;">突发环境事件应急预案</a></li>
                        </ul>
                    </div>
                    <div class="menuDiv">
                        <h3><a href="javascript:;">环境监管信息</a></h3>
                        <ul>
                            <li><a href="javascript:;">信访投诉</a></li>
                            <li><a href="javascript:;">现场检查（勘察）笔录</a></li>
                            <li><a href="javascript:;">行政处罚</a></li>
                            <li><a href="javascript:;">存在的问题及整改情况</a></li>
                            <li><a href="javascript:;">排污收费</a></li>
                        </ul>
                    </div>
                    <div class="menuDiv">
                        <h3><a href="javascript:;">其他环境信息</a></h3>
                        <ul>
                            <li><a href="javascript:;">环境自行监测方案</a></li>
                            <li><a href="javascript:;">其他环境信息</a></li>
                        </ul>
                    </div>
                    <div class="menuDiv">
                        <h3><a href="javascript:;">自查自报</a></h3>
                        <ul>
                            <li><a href="javascript:;">自查自报</a></li>
                        </ul>
                    </div>
                </div>
                <div class="main-right right">
                    <%--<jsp:include page="enterpriseInfo.jsp"></jsp:include>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/enterprise/scripts/pageset.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/enterprise/scripts/mainEnterprise.js"></script>
<script type="text/javascript">
    var mSwitch = new MenuSwitch("menuDiv");
    mSwitch.setDefault(0);
    mSwitch.setPrevious(false);
    mSwitch.init();
    $(function(){
        loadPage('enterpriseInfo.jsp?handleType='+handleType+'&id='+id);
    });
    function loadPage(url){
        $(".main-right").load(url);
    }
</script>
</body>
</html>