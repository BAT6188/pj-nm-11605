<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>办结管理</title>
    <script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
</head>
<body>
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
                            <label for="s_enterpriseName">企业名称：</label> <input type="text" id="s_enterpriseName" name="s_enterpriseName" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label for="">事件时间：</label>
                            <div id="" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="">
                                <input class="form-control" size="16" id="start_eventTime"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="end_eventTime"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </form>
                    <p></p>
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="s_source">信息来源：</label>
                            <select id="s_source" name="s_source" class="form-control" style="width: 299px;">
                                <option value="">全部</option>
                                <option value="1">12369</option>
                                <option value="2">区长热线</option>
                                <option value="3">市长热线</option>
                                <option value="4">现场监察</option>
                                <option value="0">监控中心</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="">所属区域：</label>
                            <select class="form-control s_blockLevelId" style="width: 266px;">
                            </select>
                            -
                            <select class="form-control s_blockId" style="width: 266px;">
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

<script src="<%=request.getContextPath()%>/container/gov/dispatch/scripts/loadBlockLevelAndBlockOption.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/exelaw/scripts/overManage.js"></script>
</body>
</html>
