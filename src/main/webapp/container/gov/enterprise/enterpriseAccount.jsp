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
    <title>企业管理列表</title>
    <style>
        .menuDiv h3{
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="content clearfix">
    <div class="wrap">
        <div class="menu-left left">
            <div class="menuDiv">
                <h3 onclick="loadPageInEnterprise('enterpriseListOfRun.jsp')"><a href="javascript:void(0)">排污单位管理</a></h3>
                <ul>
                    <li class="curLi"><a href="javascript:loadPageInEnterprise('enterpriseListOfRun.jsp')">排污单位列表</a></li>
                </ul>
            </div>
            <div class="menuDiv">
                <h3 onclick="loadPageInEnterprise('enterpriseListOfDel.jsp')"><a href="javascript:void(0)">删除排污单位管理</a></h3>
                <ul>
                    <li class="curLi"><a href="javascript:loadPageInEnterprise('enterpriseListOfDel.jsp')">删除排污单位列表</a></li>
                </ul>
            </div>
        </div>
        <div class="main-right right level3MenuContent">
            <%--<jsp:include page="enterpriseInfo.jsp"></jsp:include>--%>
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
        loadPageInEnterprise('enterpriseListOfRun.jsp');
    });
    function loadPageInEnterprise(url){
        var headUrl = rootPath +"/container/gov/enterprise/";
        //$(".main-right").load(url);
        $('.main-right').html(pageUtils.loading()); // 设置页面加载时的loading图片
        $('.main-right').load(headUrl+url); // ajax加载页面
    }
</script>
</body>
</html>
