<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<iframe src="http://110.19.109.61:9875/" id="ifr" width="1000" height="900"></iframe>
</body>
</html>
<script>
    $("#ifr").width($(window).width()-112)
    $("#ifr").height($(window).height())
</script>
