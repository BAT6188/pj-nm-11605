<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%--<jsp:include page="/common/common_include.jsp" flush="true"/>--%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8">
    <title>删除排污单位列表</title>
        <script>
            $(function(){
                $('#searchform').submit(function(e){
                    e.preventDefault();
                });
                $('#searchform').bind('keypress',function(event){
                    if(event.keyCode == "13") $('#search').click();
                });
            })
        </script>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;font-size: 15px;">已删除排污档案列表</a>
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
                                <label for="name">&nbsp;单位名称：</label> <input type="text" id="name" name="name" class="form-control" />
                                <label for="delOpinion" class="labelMarginLeft">&nbsp;删除意见：</label> <input type="text" id="delOpinion" name="delOpinion" class="form-control" />
                            </div>
                        </div>
                        <p></p>
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="delerName">&nbsp;&nbsp;操作人：</label> <input type="text" id="delerName" name="delerName" class="form-control" />
                               <%-- <label for="area"  class="labelMarginLeft">所属行政区：</label> <input type="text" id="area" name="area" class="form-control" />--%>
                            </div>
                        </div>
                        <p></p>
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="delTime">操作时间段：</label>
                                <div id="datetimepicker1" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                    <input class="form-control" size="16" type="text" id="startTime" name="startTime" value="" readonly placeholder="开始时间">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                                —
                                <div id="datetimepicker2" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                    <input class="form-control" size="16" type="text" id="endTime" name="endTime" value="" readonly placeholder="结束时间">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="dealBox">
                    <p class="btnListP">
                        <%--<button id="add" type="button" class="btn btn-sm btn-success">
                            <i class="btnIcon add-icon"></i><span>新增</span>
                        </button>--%>
                        <%--<button id="update" type="button" class="btn btn-sm btn-warning">
                            <i class="btnIcon edit-icon"></i><span>修改</span>
                        </button>--%>
                        <button id="remove" type="button" class="btn btn-sm btn-danger">
                            <i class="btnIcon delf-icon"></i><span>删除</span>
                        </button>
                        <button id="export" type="button" class="btn btn-sm btn-success" >
                            <span class="glyphicon glyphicon-export"></span>导出
                        </button>
                    </p>
                </div>
            </div>
            <div class="tableBox">
                <table id="table" class="table table-striped table-responsive">
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/enterprise/scripts/enterpriseListOfDel.js"></script>
</body>
</html>
