<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/common_include.jsp"%>
    <title>消息跟踪列表</title>
    <script>
        var businessId = "${param.businessId}";
    </script>
</head>
<body>

<!--选择坐标dialog-->
<div class="modal fade" id="messageTraceModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">消息跟踪</h4>
            </div>
            <div class="modal-body" style="padding: 0;">
                <div class="content content1 clearfix">
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
                                    <form class="form-inline">
                                        <div class="form-group">
                                            <label for="s_receiverName">姓名：</label> <input type="text" id="s_receiverName" name="receiverName" style="width: 180px;" class="form-control" />
                                        </div>
                                        <div class="form-group">
                                            <label for="s_receiveStatus">状态：</label>
                                            <select style="width: 90px;" class="form-control"  id="s_receiveStatus" name="receiveStatus">
                                                <option value="">全部</option>
                                                <option value="1">未接收</option>
                                                <option value="2">已接收</option>
                                            </select>
                                        </div>
                                    </form>
                                </div>
                                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                                <button type="button" class="btn btn-default" onclick="resetQuery()"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                            </div>
                            <div class="tableBox">
                                <table id="table" class="table table-striped table-responsive">
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="<%=request.getContextPath()%>/container/gov/alert/script/messagetrace.js"></script>
</body>
</html>
