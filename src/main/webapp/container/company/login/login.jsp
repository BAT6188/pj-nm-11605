<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/5
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><head lang="en">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <title>东胜环保</title>
    <link href="<%=request.getContextPath()%>/common/css/pageStyle.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/container/company/login/style/login.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/demo/easyform/js/jquery-2.1.0.min.js"></script>
    <script type="text/javascript">
        var rootPath = '<%=request.getContextPath()%>';
    </script>
</head>
<body>
<div class="content clearfix">
    <div class="login-title">
        <%--<div class="client-type">企业端</div>--%>
        <img src="<%=request.getContextPath()%>/container/company/login/images/titlelogo_qiye.png" alt="">
    </div>
    <div class="login-box">
        <div class="login">
            <form id="login-form" name="login" action="${pageContext.request.contextPath}/LoginServlet?LoginMode=ajax" method="post">

                <p> <label for="">用户名</label>：
                    <input type="text" class="uname" id="j_username" name="j_username">
                </p>
                <p>
                    <label for="">密&nbsp;码</label>：
                    <input type="password" class="upwd" id="j_password" name="j_password">
                </p>
                <p id="message" class="tipMsg" style="dispplay:block;height:28px;"></p>
                <p class="btnP">
                    <button id="doLogin" type="button" class="btn loginBtn"></button>
                    <button class="btn resetBtn" type="reset"></button>
                </p>
            </form>
        </div>
    </div>
</div>
<p class="copyrightP"><span>版权所有：东胜环保局</span><span>技术支持：航天正通汇智科技股份有限公司</span></p>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/company/login/scripts/login.js"></script>

</body></html>
