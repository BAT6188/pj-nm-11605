<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>空气质量监测</title>
</head>
<body>
<iframe src="http://110.19.109.61:9875/" id="ifr" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
</body>
</html>
<script>
    $("#ifr").width($(window).width()-20)
    $("#ifr").height($(window).height()-$(".banner").outerHeight()-65)
</script>
