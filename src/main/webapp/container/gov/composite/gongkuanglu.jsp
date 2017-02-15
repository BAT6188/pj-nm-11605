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
    <iframe id="gktIframe" src="" style="width: 100%;height: 100%" frameborder="0" scrolling="no"></iframe>
</div>


</body>
</html>
<script>
    //目前只有三家企业有工矿图，工矿图较少，故页面写死链接
    //如果有工矿图的企业多了，需要在企业表中添加工矿图字段，字段中存链接数组，页面循环数组即可得出所有工矿图
    var enterpriseId = '<%=enterpriseId%>';
    $(document).ready(function () {
        // 12be9832e811477abaa8eea11289b000   国电东胜热电厂
        // 4b1ee0438e194060875b9b0e467de8eb   北郊热电
        // dc57191570e04a24b245db823dde9846   北郊水质净化厂
        if ('12be9832e811477abaa8eea11289b000'==enterpriseId){
            var a="http://10.15.208.154:8088/rsa/diagram/openplant/gview/diagram.html?fileName=EE_DSRD_01.zxml"
            var b="http://10.15.208.154:8088/rsa/diagram/openplant/gview/diagram.html?fileName=EE_DSRD_02.zxml"
//            var a="http://news.qq.com/a/20170113/000461.htm?pgv_ref=aio2015&ptlang=2052#p=1"
//            var b="http://qiye.163.com/login/?from=ym"
            $("#gktIframe").attr("src",a);
            $("#buttonDiv").show();

            $("#a").click(function () {
                $("#gktIframe").attr("src",a);
            })
            $("#b").click(function () {
                $("#gktIframe").attr("src",b);
            })
        }

        if ('4b1ee0438e194060875b9b0e467de8eb'==enterpriseId){
            var a="http://10.1.1.66:8088/rsa/diagram/openplant/gview/diagram.html?fileName=EE_DSBJ_01.zxml"
            var b="http://10.1.1.66:8088/rsa/diagram/openplant/gview/diagram.html?fileName=EE_DSBJ_02.zxml"
            $("#gktIframe").attr("src",a);
            $("#buttonDiv").show();

            $("#a").click(function () {
                $("#gktIframe").attr("src",a);
            })
            $("#b").click(function () {
                $("#gktIframe").attr("src",b);
            })
        }

        if ('dc57191570e04a24b245db823dde9846'==enterpriseId){
            var a="http://10.1.1.66:8088/rsa/diagram/openplant/gview/diagram.html?fileName=EE_DSBJSC_01.zxml"
            $("#gktIframe").attr("src",a);
        }
    })
</script>
