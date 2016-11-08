<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link href="<%=request.getContextPath()%>/container/gov/composite/css/enterprise_plotting.css" rel="stylesheet">
    <script type="text/javascript" src="<%=request.getContextPath()%>/common/scripts/plotting/raphael.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/common/scripts/plotting/plotting.js"></script>
    <title>企业平面图展示及维护Dialog</title>
    <style type="text/css">
        /*导航样式*/
        a{
            color: #337ab7;
        }
    </style>
</head>
<body style="overflow: hidden;">

<!--企业平面图dialog-->
<div class="modal fade" id="plottingDialog" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">企业平面图</h4>
            </div>
            <div class="modal-body" style="padding: 0;">
                <ul class="nav nav-pills">
                    <%--<li class="active"><a href="#">Home</a></li>
                    <li><a href="#">SVN</a></li>
                    <li><a href="#">iOS</a></li>
                    <li><a href="#">VB.Net</a></li>
                    <li><a href="#">Java</a></li>
                    <li><a href="#">PHP</a></li>--%>
                </ul>
                <div id="plottingPaper" class="plotting" style="overflow: auto;">

                </div>
                <div class="navbar" style="display:block;">
                    <ul>
                        <li><span class="glyphicon glyphicon-zoom-in" title="放大"> </span></li>
                        <li><span class="glyphicon glyphicon-zoom-out" title="缩小"> </span></li>
                        <li><span class="glyphicon glyphicon-fullscreen" title="原始尺寸"> </span></li>
                        <li><span class="glyphicon glyphicon-move" title="平移"> </span></li>
                        <li><span class="glyphicon glyphicon-map-marker" title="标绘"> </span></li>
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary btn-save" id="save">保存</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/composite/scripts/enterprise_plotting.js"></script>
</html>
