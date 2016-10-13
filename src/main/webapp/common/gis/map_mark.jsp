<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/common_include.jsp"%>

    <title>通用标绘页面</title>
    <link rel="stylesheet" href="css/map_mark.css" type="text/css">
</head>
<body style="overflow: hidden;">

<!--选择坐标dialog-->
<div class="modal fade" id="markDialog" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">地图标绘</h4>
            </div>
            <div class="modal-body" style="padding: 0;">
                <iframe id="mapFrame" name="mapFrame" src="${pageContext.request.contextPath}/common/gis/map.jsp" style="overflow: hidden;" frameborder="0"></iframe>
                <div class="navbar" style="display:block;">
                    <ul>
                        <li><span class="point"></span></li>
                        <li><span class="polyline"></span></li>
                        <li><span class="polygon"></span></li>
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="markSave">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="script/map_mark.js"></script>
</body>
</html>
