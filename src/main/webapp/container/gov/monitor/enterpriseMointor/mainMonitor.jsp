<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%--<jsp:include page="/common/common_include.jsp" flush="true"/>--%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8">
    <title>污染源实时监控</title>
</head>
<body>
<div class="content content1 clearfix">
    <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;font-size: 18px;z-index: 0">污染源列表</a>
    <div class="wrap">
        <div class="mainBox">
            <div class="dealBox">
                <div class="sideTitle left">
                        <span class="blueMsg">
                            <img class="tipImg" src="<%=request.getContextPath()%>/common/images/searchTip.png" alt=""/>
                            <span class="text">查询</span>
                        </span>
                </div>
                <div class="queryBox marginLeft0">
                    <form role="form" id="searchform">
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="name">&nbsp;&nbsp;企业名称：</label><input type="text" id="name" name="name" class="form-control" />
                                <label for="monitorTime" class="labelMarginLeft">超标状态：</label>
                                <select style="width: 300px;" class="form-control" id="monitorStatus" name="monitorStatus">
                                    <option value="">全部</option>
                                    <option value="01">未超标</option>
                                    <option value="02">已超标</option>
                                </select>
                            </div>
                        </div>
                    </form>
                    <p></p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="resetSearch" type="button" class="btn btn-default" >重置</button>
            </div>
            <div class="tableBox">
                <table id="table" class="table table-striped table-responsive">
                </table>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="lookInMap" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 95%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">地图显示</h4>
            </div>
            <div class="modal-body">
                <iframe id="mapFrame" name="mapFrame" src="${pageContext.request.contextPath}/common/gis/map.jsp" frameborder="0"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/monitor/enterpriseMointor/scripts/mainMonitor.js"></script>
</body>
</html>
