<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/12
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common_include.jsp"%>
<%
    String enterpriseId = request.getParameter("enterpriseId");
%>
<!DOCTYPE html>
<html>
<head>
    <title>工矿图</title>
</head>
<body>
<div  id="buttonDiv" style="margin-left: 10px;margin-top:10px;display: none">
    <button id="a" type="button" class="btn btn-info">1#图</button>
    <button id="b" type="button" class="btn btn-info">2#图</button>
</div>
<div  >
    <iframe id="gktImg" src="" style="width: 100%;height: 100%" frameborder="0" scrolling="no"></iframe>
</div>


</body>
</html>
<script>
    //目前只有三家企业有工矿图，工矿图较少，故页面写死链接
    //如果有工矿图的企业多了，需要在企业表中添加工矿图字段，字段中存链接数组，页面循环数组即可得出所有工矿图
    var enterpriseId = '<%=enterpriseId%>';
    $(document).ready(function () {
        if ('12be9832e811477abaa8eea11289b000'==enterpriseId){
            var a="http://10.1.1.66:8088/rsa/diagram/openplant/gview/diagram.html?fileName=EE_DSRD_01.zxml"
            var b="http://s1.dwstatic.com/group1/M00/2B/64/2494ca02abcaa099d9e3bc119a979e24.jpg"
            $("#gktImg").attr("src",a);
            $("#buttonDiv").show();

            $("#a").click(function () {
                $("#gktImg").attr("src",a);
            })
            $("#b").click(function () {
                $("#gktImg").attr("src",b);
            })
        }

        if ('4b1ee0438e194060875b9b0e467de8eb'==enterpriseId){
            var a="http://s1.dwstatic.com/group1/M00/A7/62/42fbf85aa3d04453348b5fecb53066b4.jpg"
            var b="http://s1.dwstatic.com/group1/M00/2B/64/2494ca02abcaa099d9e3bc119a979e24.jpg"
            $("#gktImg").attr("src",a);
            $("#buttonDiv").show();

            $("#a").click(function () {
                $("#gktImg").attr("src",a);
            })
            $("#b").click(function () {
                $("#gktImg").attr("src",b);
            })
        }

        if ('47238ece846c453d9320766749aabf76'==enterpriseId){
            var a="http://tu.duowan.com/gallery/127919.html#p24"
            $("#gktImg").attr("src",a);
        }
    })
</script>
