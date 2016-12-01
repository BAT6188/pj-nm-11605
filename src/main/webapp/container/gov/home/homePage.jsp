<%@ page import="com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/28
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
    <style>
        .panel-icon{
            padding: 15px 20px;
            margin-top: -6px;
            float: left;
        }
        .icon-notice{background: url("<%=request.getContextPath()%>/container/gov/home/images/info-notice.png") no-repeat center;}
        .icon-unusual{background: url("<%=request.getContextPath()%>/container/gov/home/images/unusual-info.png") no-repeat center;}
        .icon-monitor{background: url("<%=request.getContextPath()%>/container/gov/home/images/real-monitor.png") no-repeat center;}
        .icon-more{background: #5E96DE url("<%=request.getContextPath()%>/container/gov/home/images/more-icon.png") no-repeat center;float: right;margin-top: -8px;}
        .icon-more:hover{background-color: #2A6CC1;}
        .noborderPanel .panel{border: none;}
        .noborderPanel .panel-heading{padding: 9px 15px;}
        .panelList-icon{display: block;width: 16px;height: 16px;}
        .mail-icon{background: url("<%=request.getContextPath()%>/container/gov/home/images/mail-icon.png") no-repeat center;}
        .dialog-icon{background: url("<%=request.getContextPath()%>/container/gov/home/images/chart-icon.png") no-repeat center;}
        .error-icon{background: url("<%=request.getContextPath()%>/container/gov/home/images/unusual.png") no-repeat center;}
        .noborderPanel .row{ margin-left: 0px;  margin-right: 0px;  }
        .noborderPanel .col-sm-6,.noborderPanel .col-sm-12{padding-left: 5px;padding-right: 5px;}
        .noborderPanel .table>tbody>tr>td{border-top: none;}
        .noborderPanel .table>tbody>tr>td:first-child{width: 40px;}
        .content1{
            margin: 0px;
            padding:0px;
            overflow-y: hidden;
        }

        .box_l{ padding-top:5%;margin-left:15%; margin-right:5%; height:100%;width:100%;background:url("<%=request.getContextPath()%>/container/gov/home/images/day.png") no-repeat left top;}
        .daybox {
            width: 160px;
            margin: 106px auto 0 auto;
            overflow: hidden;
            border: 1px solid #c4976c;
            text-align: center;
            font-size: 14px
        }

    </style>

</head>
<body>
<div class="container">
    <div class="content content1 clearfix">
        <div class="wrap noborderPanel">
            <div class="row">

                <div class="col-md-3" >

                    <div class="row">
                        <div class="col-md-12" style="margin: 0px;padding: 0px;">
                            <div class="panel panel-primary" style="overflow-y: hidden">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <i class="panel-icon icon-notice"></i>
                                        实时监测数据
                                        <i class="panel-icon icon-more"></i>
                                    </h3>
                                </div>
                                <div class="panel-body" style="overflow: auto">
                                        <ul id="myTab" class="nav nav-tabs">
                                            <li class="active">
                                                <a href="#wasteGas" data-toggle="tab">
                                                    废气排口
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#WasteWater" data-toggle="tab">
                                                    废水排口
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#noises" data-toggle="tab">
                                                    噪声源排口
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#fumes" data-toggle="tab">
                                                    油烟排口
                                                </a>
                                            </li>
                                        </ul>
                                        <div id="myTabContent" class="tab-content">
                                            <div class="tab-pane fade in active" id="wasteGas">
                                                <table class="table table-striped" id="gasTable" >
                                                    <tbody>
                                                        <tr>
                                                        <td><i class="panelList-icon mail-icon"></i></td>
                                                        <td><span>名称</span></td>
                                                        <td><span>时间</span></td>
                                                        <td><span >废气流量</span></td>
                                                        <td><span >氧含量</span></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="tab-pane fade" id="WasteWater">
                                                <table class="table table-striped" id="waterTable" >
                                                    <tbody>
                                                    <tr>
                                                        <td><i class="panelList-icon mail-icon"></i></td>
                                                        <td><span>名称</span></td>
                                                        <td><span>时间</span></td>
                                                        <td><span >流量</span></td>
                                                        <td><span >化学需氧量</span></td>
                                                        <td><span >氨氮</span></td>
                                                        <td><span >ph值</span></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="tab-pane fade" id="noises">
                                                <table class="table table-striped" id="noiseTable" >
                                                    <tbody>
                                                    <tr>
                                                        <td><i class="panelList-icon mail-icon"></i></td>
                                                        <td><span>监测点</span></td>
                                                        <td><span>时间</span></td>
                                                        <td><span >Leq(db)</span></td>
                                                        <td><span >Lmax(dB)</span></td>
                                                        <td><span >Lmin(dB)</span></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="tab-pane fade" id="fumes">
                                                <table class="table table-striped" id="fumesTable" >
                                                    <tbody>
                                                    <tr>
                                                        <td><i class="panelList-icon mail-icon"></i></td>
                                                        <td><span>名称</span></td>
                                                        <td><span>时间</span></td>
                                                        <td><span >油烟(mg/L)</span></td>
                                                        <td><span >烟气温度</span></td>
                                                        <td><span >烟气湿度</span></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="row" style="margin: 0px;padding: 0px;">
                        <div class="col-md-12" style="margin: 0px;padding: 0px;">
                            <div class="panel panel-primary" style="overflow-y: auto">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <i class="panel-icon icon-unusual"></i>
                                        最近3个月执法情况统计
                                        <i class="panel-icon icon-more"></i>
                                    </h3>
                                </div>
                                <div class="panel-body" >
                                    <div id="container" style="min-width:100%;min-height:100%;text-align: center;width:90%;"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>


                <div class="col-md-6" style="margin: 0px;padding: 0px;">

                    <div class="col-md-12" style="margin: 0px;padding: 0px;">
                        <div class="panel panel-primary" style="overflow-y: auto">
                            <div class="panel-body2" style="height: 60%;margin: 0px;padding: 0px;">

                                <ul id="links"></ul>

                            </div>
                        </div>
                    </div>

                </div>

                <div class="col-md-3" >

                    <div class="row">
                        <div class="col-md-12" style="margin: 0px;padding: 0px;">
                            <div class="panel panel-primary" style="overflow:hidden; ">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <i class="panel-icon icon-monitor"></i>
                                        天气信息
                                        <i class="panel-icon icon-more"></i>
                                    </h3>
                                </div>
                                <div class="panel-body" >
                                    <%--<iframe id='time' name="time" src="http://www.weather.com.cn/weather/101080701.shtml" width="100%" height="100%" frameborder="0" scrolling="no" ></iframe>--%>

                                        <div class="box_l " style="display: block;">
                                            <table  id="airTable" style="margin-top: 35%;margin-left: 15%;">

                                            </table>
                                        </div>

                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="row" >
                        <div class="col-md-12" style="margin: 0px;padding: 0px;">
                            <div class="panel panel-primary" style="overflow-y: auto">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <i class="panel-icon icon-monitor"></i>
                                        摄像头监控
                                        <i class="panel-icon icon-more"></i>
                                    </h3>
                                </div>
                                <div class="panel-body" >
                                    <img src="<%=request.getContextPath()%>/container/gov/home/images/01.png" alt="" style="width: 48%;height: 48%;"/>
                                    <img src="<%=request.getContextPath()%>/container/gov/home/images/02.png" alt="" style="width: 48%;height: 48%;"/>
                                    <img src="<%=request.getContextPath()%>/container/gov/home/images/03.png" alt="" style="width: 48%;height: 48%;"/>
                                    <img src="<%=request.getContextPath()%>/container/gov/home/images/04.png" alt="" style="width: 48%;height: 48%;"/>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>

            </div>
    </div>
    <div class="content content2 clearfix level3MenuContent">
    </div>
</div>
</div>

<script src="<%=request.getContextPath()%>/common/scripts/main_css.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/home/scripts/homePage.js"></script>
<script>
    $(".panel-body").css("height", pageUtils.getTableHeight()*0.46);
</script>

</body>
</html>
