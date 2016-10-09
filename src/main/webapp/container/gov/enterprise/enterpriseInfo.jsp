<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/8
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>企业详细信息</title>
    <meta name="description" content="">
    <meta http-equiv="cleartype" content="on">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/demo/easyform/css/platform-1.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/demo/easyform/js/easyform/easyform.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/demo/easyform/css/tab.css">

    <script src="<%=request.getContextPath()%>/demo/easyform/js/jquery-2.1.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/demo/easyform/js/easyform/easyform.js"></script>
</head>
<body>
<div class="form-div">
    <form id="reg-form" action="" method="post">

        <table>
            <tr>
                <td>单位名称</td>
                <td><input name="name" type="text" id="name">
                </td>
                <td>企业运行状态</td>
                <td><input name="status" type="text" id="status">
                </td>
            </tr>
            <tr>
                <td>单位地址</td>
                <td><input name="address" type="text" id="address"></td>
                <td>污染源代码</td>
                <td><input name="pollutantCode" type="text" id="pollutantCode"></td>
            </tr>
            <tr>
                <td>经度</td>
                <td><input name="longitude" type="text" id="longitude" ></td>
                <td>纬度</td>
                <td><input name="latitude" type="text" id="latitude" ></td>
            </tr>
            <tr>
                <td>邮政编码</td>
                <td><input name="zipCode" type="text" id="zipCode" ></td>
                <td>组织机构代码</td>
                <td><input name="orgCode" type="text" id="orgCode" ></td>
            </tr>
            <tr>
                <td>法定代表人</td>
                <td><input name="artificialPerson" type="text" id="artificialPerson" ></td>
                <td>法人代表职务</td>
                <td><input name="apPosition" type="text" id="apPosition" ></td>
            </tr>
            <tr>
                <td>法定代表人电话</td>
                <td colspan="3"><input name="apPhone" type="text" id="apPhone" ></td>
            </tr>
            <tr>
                <td>环保负责人</td>
                <td><input name="envPrincipal" type="text" id="envPrincipal" ></td>
                <td>环保负责人职务</td>
                <td><input name="epPosition" type="text" id="epPosition" ></td>
            </tr>
            <tr>
                <td>环保负责人电话</td>
                <td colspan="3"><input name="epPhone" type="text" id="epPhone" ></td>
            </tr>
            <tr>
                <td>污染源类型</td>
                <td colspan="3"></td>
            </tr>
        </table>

        <div class="buttons" style="margin-top: 50px;">
            <input value="注 册" type="submit" style="margin-right:20px; margin-top:20px;">
            <input value="我有账号，我要登录" type="button" style="margin-right:45px; margin-top:20px;">
        </div>

        <br class="clear">
    </form>
</div>
</body>
</html>
