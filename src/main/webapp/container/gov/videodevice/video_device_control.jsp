<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit|ie-comp|ie-stand" />
    <title>视频监控-云台控制</title>
    <link href="${pageContext.request.contextPath}/container/gov/videodevice/css/video_device_control.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="videoBox">
        <div class="controlBtn videoPause"></div>
        <div class="video">
        </div>
    </div>
    <div class="controlBox">
        <div class="inner">
            <h2>云台监控</h2>
            <div class="direction">
                <a class="topDir" href="javascript:;"></a>
                <a class="bottomDir" href="javascript:;"></a>
                <a class="leftDir" href="javascript:;"></a>
                <a class="rightDir" href="javascript:;"></a>
            </div>
            <p class="btnList"><a href="javascript:;">灯光</a><a href="javascript:;">控件下载</a><a class="active" href="javascript:;">雨刷</a></p>
            <div class="progressBar"><span class="speed">速度</span><div class="bg"><div class="bgcolor"></div></div><div class="bt"></div><div class="text"></div></div>
            <div class="progressBar"><span class="speed">亮度</span><div class="bg"><div class="bgcolor"></div></div><div class="bt"></div><div class="text"></div></div>
            <div class="progressBar"><span class="speed">色度</span><div class="bg"> <div class="bgcolor"></div></div><div class="bt"></div><div class="text"></div></div>
            <div class="opList">
                <p><span class="cut"></span><span class="opText">光圈</span><span class="add"></span></p>
                <p><span class="cut"></span><span class="opText">焦距</span><span class="add"></span></p>
                <p><span class="cut"></span><span class="opText">变倍</span><span class="add"></span></p>
            </div>
            <p class="recP"><span>录制位置</span><input class="showInput" type="text" value="00:21/03:34"/></p>
            <p class="recP"><span>录制时间</span><input class="showInput" type="text" value="00:40/03:34"/></p>
            <div class="controlIcon pause"></div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/common/scripts/jquery2.2.4/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/container/gov/videodevice/scripts/video_device_control_css.js"></script>
<script src="${pageContext.request.contextPath}/container/gov/videodevice/scripts/video_device_control.js"></script>
</html>
