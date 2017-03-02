<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%
        String enterpriseId = request.getParameter("enterpriseId");
        enterpriseId = enterpriseId != null ? enterpriseId : "";
    %>
    <link href="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/metrStyle-cd/metroStyle.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/jquery.ztree.all.js"></script>
    <title>一张图综合检测预警</title>
    <script>
        var enterpriseId = "<%=enterpriseId%>";
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
        .modal-dialog{
            display: table-cell;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="tree-left left" style="display: none;">
            <div class="input-group input-group-sm" style="z-index: 0;">
                <input type="text" class="form-control" id="searchText" placeholder="查  询"/>
                <span class="input-group-btn">
                        <button class="btn btn-default" id="searchBtn" type="button"><span class="glyphicon glyphicon-search"></span></button>
                </span>
            </div>
            <div class="ztree oneImageTree">
            </div>
        </div>
        <div class="input-group input-group-sm"  style="z-index: 1;padding-left: 500px; display: none;" id="videoBtn">
            <input type="text" class="form-control" style="width: 200px;" id="searchContent" value="5" placeholder="企业周边视频查询"/>
            <span class="input-group-addon">(公里)</span>
            <span class="input-group-btn">
                <button class="btn btn-default" id="searBtn" type="button"><span class="glyphicon glyphicon-search"></span></button>
            </span>
        </div>
        <div class="mainBox">
            <iframe id="mapFrame" name="mapFrame" src="${pageContext.request.contextPath}/common/gis/map.jsp" style="overflow: hidden;width: 100%;height: 100%" frameborder="0"></iframe>
        </div>
    </div>
</div>
<div class="popover fade bottom in" role="tooltip" id="planeMap_popover" style="display: none; top: 361px; left: 499px;">
    <div class="arrow" style="left: 21%;"></div>
    <h3 class="popover-title">废气排口信息</h3>
    <div class="popover-content" style="overflow-y: auto">

    </div>
</div>
<%--<button id="liveWaterGas" style="display: none;">liveWaterGas</button>--%>

<script>
//    $("#liveWaterGas").bind("click",function(){
////        var enterpriseId = $(infoDOM).find("#liveWaterGas").attr("data-id");
//        var h=$(window).height()-120;
//        $("#setW").css('width',$(window).width());
//        $('.modal-body').attr('style','max-height: '+h+'px;overflow-y: auto;overflow-x: hidden;padding:10px;');
//        $("#liveWaterGasModal").modal('show');
//        var url='/container/gov/monitor/enterpriseMointor/lookMonitor.jsp?id=07316bfb0d4844a49bd535516b20e0b4';
//        $('#contentliveWaterGas').load(rootPath+url);
//    });
//    $('#liveWaterGas').trigger('click');
</script>


<%@include file="/container/gov/composite/enterprise_info_dialog.jsp" %>
<%@include file="/container/gov/composite/portstatus_form_view.jsp" %>
<%@include file="/container/gov/composite/noise_form_view.jsp" %>
<%@include file="/container/gov/composite/dust_form_view.jsp" %>
<%@include file="/container/gov/composite/enterprise_plotting.jsp" %>
<!--实时监控-->
<div class="modal fade" id="liveWaterGasModal" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="" id="setW">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="demoFormTitle">实时监控</h4>
            </div>
            <div class="modal-body">
                <div id="contentliveWaterGas">load...</div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="<%=request.getContextPath()%>/container/gov/composite/scripts/one_image.js"></script>
</body>
</html>
