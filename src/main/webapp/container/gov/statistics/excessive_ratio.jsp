<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/12
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String date= format.format(new Date());
        int strDate = Integer.parseInt(date)-1;
    %>
    <title>超标同期对比</title>
    <style type="text/css">
        .chart-list {
            text-align: center;
            height: 42px;
        }
        .chart-list li {
            float: left;
            width: 33.33%;
            height: 100%;
        }
        .ui-autocomplete { z-index:2147483647; }

    </style>
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
                            <label for="s_name">企业名称：</label> <input type="text" id="s_name" style="width: 180px;" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="">日期<span style="color: #f00;">(与上一年同时期对比)</span>：</label>
                            <div id="datetimepicker1" class="input-group date form_datetime1" data-date="" data-date-format="yyyy-mm" data-link-field="sendTime">
                                <input class="form-control" size="16" id="start_createTime"  type="text" value="" readonly placeholder="开始时间">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div id="datatimes" class="input-group date form_datetime2" data-date="" data-date-format="yyyy-mm" data-link-field="sendTime">
                                <input class="form-control" size="16" id="end_createTime"  type="text" value="" readonly placeholder="结束时间">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                        <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                        <button type="button" class="btn btn-default" onclick="resetQuery()"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                    </form>
                    <p></p>
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="">同期对比：</label>
                            <div id="" class="input-group  date" >
                                <input class="form-control" size="16" id="endtime"  type="text" style="border-radius:3px;" value="<%=strDate%>" readonly placeholder="请选择年份" disabled>
                            </div>
                            -
                            <div id="datetimepicker2" class="input-group date form_datetimes" data-date="" data-date-format="yyyy" data-link-field="sendTime">
                                <input class="form-control" size="16" id="startTime"  type="text" value="<%=date%>" readonly placeholder="请选择年份">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>

                            <%--<div class="input-group date form_datetimes" data-date="" data-date-format="yyyy" data-link-field="sendTime">--%>
                                <%--<input class="form-control" size="16" id="endtime"  type="text" value="<%=strDate%>" readonly>--%>
                                <%--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>--%>
                                <%--<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>--%>
                            <%--</div>--%>
                            <button type="button" id="sbYear" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>上半年</span></button>
                            <button type="button" id="xbYear" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>下半年</span></button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="tableBox">
                <div class="chart-box">
                    <div class="tabbable chart-list">
                        <ul class="clearfix">
                            <li id="columnBtn" class="active" data-checked="1"><a href="#panel-left" data-toggle="tab">柱状图</a></li>
                            <li id="pieBtn"  data-checked="2"><a href="#panel-center" data-toggle="tab">饼状图</a></li>
                            <li id="lineBtn"  data-checked="3"><a href="#panel-right" data-toggle="tab">折线图</a></li>
                        </ul>
                    </div>
                    <div class="tab-content">
                        <div class="tab-pane active" id="panel-left" style="min-width:100%;min-height:100%;text-align: center;width:100%;">
                        </div>
                        <div class="tab-pane" id="panel-center">
                            <div class="row clearfix">
                                <div class="col-md-6 column">
                                    <div id="container1" style="min-width:100%;min-height:100%;text-align: center;width:100%;"></div>
                                </div>
                                <div class="col-md-6 column">
                                    <div id="container2" style="min-width:100%;min-height:100%;text-align: center;width:100%;"></div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="panel-right" style="min-width:100%;min-height:100%;text-align: center;width:100%;">
                        </div>
                    </div>
                    <%--<div id="container" style="min-width:100%;min-height:100%;text-align: center;width:100%;"></div>
                    <div style="width:50%; height:100%;float:left;">
                        <div id="container1" style="min-width:100%;min-height:100%;text-align: center;width:100%;"></div>
                    </div>
                    <div style="width:50%; height:100%;float:right;">
                        <div id="container2" style="min-width:100%;min-height:100%;text-align: center;width:100%;display:none;"></div>
                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</div>

<%--超标记录同期对比列表（线状图）（柱状图）--%>
<div class="modal fade" id="excessiveRatioListForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1017px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">超标记录同期对比列表</h4>
            </div>
            <div class="modal-body">
                <div class="tableBox">
                    <table id="excessiveRatioTable" class="table table-striped table-responsive">
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="closeBtn" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<%--超标记录同期对比列表(饼状图)--%>
<div class="modal fade" id="excessiveRatioListForm2" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1017px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">超标记录同期对比列表</h4>
            </div>
            <div class="modal-body">
                <div class="tableBox">
                    <table id="excessiveRatioTable2" class="table table-striped table-responsive">
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="closeBtn2" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>



<script src="<%=request.getContextPath()%>/container/gov/statistics/scripts/excessive_ratio.js"></script>
<script type="text/javascript">
    $( function() {

        $( "#s_name" ).autocomplete({
            source: function( request, response ) {
                $.ajax( {
                    url: rootPath + "/action/S_enterprise_Enterprise_list.action",
                    dataType: "json",
                    type:'post',
                    data: {
                        name: request.term
                    },
                    success: function( data ) {
                        for(var i = 0;i<data.rows.length;i++){
                            console.log(data.rows[i].name);
                            var result = [];
                            for(var i = 0; i <  data.rows.length; i++) {
                                result.push(data.rows[i].name);
                            }
                            response( result);
                        }
                    }
                } );
            },
        } );
    } );

</script>

</body>
</html>
