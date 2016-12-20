<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%
        String enterpriseId = request.getParameter("enterpriseId");
        enterpriseId = enterpriseId != null ? enterpriseId : "";
        String gis = request.getParameter("viewType");
    %>
    <link href="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/metrStyle-cd/metroStyle.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/jquery.ztree.all.js"></script>
    <%--<script src="<%=request.getContextPath()%>/container/gov/composite/scripts/selectBlockTree.js"></script>--%>
    <title>一张图综合检测预警</title>
    <script>
        var enterpriseId = "<%=enterpriseId%>";
        var viewType = "<%=gis%>";
    </script>
    <style type="text/css">
        /*企业平面图展示设备信息，替换bootstrap样式*/
        .popover-content{
            padding: 0;
        }
        #videoBtn{
            width:100px;
            position:absolute;
            left:8%;
            top:100px;
            z-index:1
        }
    </style>
</head>
<body>
<div class="content clearfix" style="overflow: auto">
    <div class="wrap" >
        <div class="tree-left left" id="button" style="overflow: hidden;z-index: 1">
            <div class="input-group input-group-sm"  style="z-index: 1;" id="inputBtn">
                <input type="text" class="form-control" id="searchText" placeholder="查  询"/>
                <span class="input-group-btn">
                        <button class="btn btn-default" data-status="false" id="switchBtn" type="button"><span class="glyphicon glyphicon-sort"></span></button>
                </span>
                <span class="input-group-btn">
                        <button class="btn btn-default" id="searchBtn" type="button"><span class="glyphicon glyphicon-search"></span></button>
                </span>
            </div>
            <div class="ztree oneImageTree" id="blockZtree">
            </div>
        </div>
        <div class="input-group input-group-sm"  style="z-index: 1;padding-left: 500px; display: none;" id="videoBtn">
            <input type="text" class="form-control" style="width: 200px;" id="searchContent" value="5" placeholder="企业周边视频查询"/>
            <span class="input-group-addon">(公里)</span>
            <span class="input-group-btn">
                <button class="btn btn-default" id="searBtn" type="button"><span class="glyphicon glyphicon-search"></span></button>
            </span>
        </div>
        <div>
            <span id="circleLayerBtn" data-layer-type="vector" style="display:inline-block;width:42px;height:42px;cursor:pointer; position:absolute;right:80px;
            top:12px;z-index:1;background-image: url('<%=request.getContextPath()%>/common/gis/images/tool_quanxuan.png')"></span>
        </div>
        <div class="main-right right" style="overflow: hidden">
            <iframe id="mapFrame" name="mapFrame" src="${pageContext.request.contextPath}/common/gis/map.jsp" style="overflow: hidden;" frameborder="0"></iframe>

        </div>
    </div>
</div>
<div class="popover fade bottom in" role="tooltip" id="planeMap_popover" style="display: none; top: 361px; left: 499px;">
    <div class="arrow" style="left: 21%;"></div>
    <h3 class="popover-title">废气排口信息</h3>
    <div class="popover-content" style="overflow-y: auto">

    </div>
</div>


<%@include file="/container/gov/composite/enterprise_info_dialog.jsp" %>
<%@include file="/container/gov/composite/portstatus_form_view.jsp" %>
<%@include file="/container/gov/composite/noise_form_view.jsp" %>
<%@include file="/container/gov/composite/dust_form_view.jsp" %>
<%@include file="/container/gov/composite/enterprise_plotting.jsp" %>
<%@include file="/container/gov/composite/air_form_view.jsp" %>
<script src="<%=request.getContextPath()%>/container/gov/composite/scripts/one_image.js"></script>
</body>
</html>
