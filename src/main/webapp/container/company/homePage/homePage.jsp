<%@ page import="com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/10
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <%--<%@include file="/common/common_include.jsp"%>--%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit|ie-comp|ie-stand" />
    <link rel='icon' href='images/company.ico ' type='image/x-ico'/>
    <title>首页</title>
        <jsp:include page="/common/common_ztree.jsp"></jsp:include>
        <%
            Enterprise enterprise = (Enterprise) request.getSession().getAttribute("session");
        %>
        <script type="text/javascript">
            var enterpriseId = "<%=enterprise != null ? enterprise.getId():""%>";
            $(function(){
                $('#level3MenuContent').html(pageUtils.loading());
            })
        </script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/container/company/homePage/css/style.css"/>
    <style>
        .panel-icon{
            padding: 15px 20px;
            margin-top: -6px;
            float: left;
        }
        .icon-notice{background: url("<%=request.getContextPath()%>/container/company/homePage/images/info-notice.png") no-repeat center;}
        .icon-unusual{background: url("<%=request.getContextPath()%>/container/company/homePage/images/unusual-info.png") no-repeat center;}
        .icon-monitor{background: url("<%=request.getContextPath()%>/container/company/homePage/images/real-monitor.png") no-repeat center;}
        .icon-more{background: #5E96DE url("<%=request.getContextPath()%>/container/company/homePage/images/more-icon.png") no-repeat center;float: right;margin-top: -8px;}
        .icon-more:hover{background-color: #2A6CC1;}
        .noborderPanel .panel{border: none;}
        .noborderPanel .panel-heading{padding: 9px 15px;}
        .panelList-icon{display: block;width: 16px;height: 16px;}
        .mail-icon{background: url("<%=request.getContextPath()%>/container/company/homePage/images/mail-icon.png") no-repeat center;}
        .dialog-icon{background: url("<%=request.getContextPath()%>/container/company/homePage/images/chart-icon.png") no-repeat center;}
        .error-icon{background: url("<%=request.getContextPath()%>/container/company/homePage/images/unusual.png") no-repeat center;}
        .noborderPanel .row{ margin-left: 0px;  margin-right: 0px;  }
        .noborderPanel .col-sm-6,.noborderPanel .col-sm-12{padding-left: 5px;padding-right: 5px;}
        .noborderPanel .table>tbody>tr>td{border-top: none;}
        .noborderPanel .table>tbody>tr>td:first-child{width: 40px;}

        .panel-body{
            height:280px;
        }
        /*.panel-body2{*/
            /*height:450px;*/
        /*}*/

        .menu-left{
            margin-top: 7px;
            /*height:753px;*/
            overflow-y:hidden;
        }
        #level2content{
            overflow-y: hidden;
        }

    </style>

</head>
<body>
<div class="container">
        <div class="content content1 clearfix">
            <div class="wrap noborderPanel">
                <div class="row" >
                    <div class="col-sm-6">
                        <div class="panel panel-primary" style="overflow-y: auto">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    <i class="panel-icon icon-notice"></i>
                                    信息公告
                                    <i class="panel-icon icon-more"></i>
                                </h3>
                            </div>
                            <div class="panel-body">
                                <table class="table table-responsive table-condensed" id="tablegrid">
                                    <tbody>
                                    <tr>
                                        <td><i class="panelList-icon mail-icon"></i></td>
                                        <td><span>标题</span></td>
                                        <td><span>发布时间</span></td>
                                        <td><span >发布单位</span></td>
                                        <td><span >操作</span></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="panel panel-primary" style="overflow-y: auto">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    <i class="panel-icon icon-unusual"></i>
                                    超标异常信息
                                    <i class="panel-icon icon-more"></i>
                                </h3>
                            </div>
                            <div class="panel-body">
                                <table class="table table-responsive table-condensed" id="excessTable">
                                    <tbody>
                                    <tr>
                                        <td><i class="panelList-icon error-icon"></i></td>
                                        <td><span>排口编号</span></td>
                                        <td><span>排口名称</span></td>
                                        <td><span >超标时间</span></td>
                                        <td><span >监测指标</span></td>
                                        <td><span >状态</span></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    <i class="panel-icon icon-monitor"></i>
                                    实时监测数据
                                    <i class="panel-icon icon-more"></i>
                                </h3>
                            </div>
                            <div class="panel-body2">
                                <%--<div class="wrap">--%>
                                    <div class="menu-left left">
                                        <div id="scrollContent" style="height:450px;">
                                            <ul id="enterpriseZTree" class="ztree"></ul>
                                        </div>
                                    </div>
                                    <div id="level3MenuContent" class="main-right right">
                                        <%--<jsp:include page="enterpriseInfo.jsp"></jsp:include>--%>
                                    </div>
                                <%--</div>--%>
                                <%--<table class="table table-responsive">--%>
                                    <%--<tbody>--%>
                                    <%--<tr>--%>
                                        <%--<td><i class="panelList-icon error-icon"></i></td>--%>
                                        <%--<td><span>每周例会通知</span></td>--%>
                                        <%--<td><span>2016//8/29 15:12</span></td>--%>
                                        <%--<td><span class="text-danger">未处理</span></td>--%>
                                    <%--</tr>--%>
                                    <%--</tbody>--%>
                                <%--</table>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="content content2 clearfix">
        </div>
    </div>
</div>
<%--查看信息公告详情--%>
<div class="modal fade modal-temp" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="height: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">未缴费提醒</h4>
            </div>
            <div class="modal-body">
                <%--<form class="form-horizontal" role="form">--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">企业名称：</label>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<input type="text" class="form-control" placeholder="请输入企业名称...">--%>
                        <%--</div>--%>
                        <%--<label class="col-sm-2 control-label">企业法人：</label>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<input type="text" class="form-control" placeholder="请输入企业法人...">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
                <%--<div class="tableBox">--%>
                    <%--<table id="lawTable" class="table table-striped table-responsive">--%>
                    <%--</table>--%>
                <%--</div>--%>
            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-primary">保存</button>--%>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="<%=request.getContextPath()%>/common/scripts/main_css.js"></script>
<script src="<%=request.getContextPath()%>/container/company/homePage/scripts/homePage.js"></script>
<script>
</script>

</body>
</html>
