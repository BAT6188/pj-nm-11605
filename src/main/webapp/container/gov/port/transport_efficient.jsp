<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/12
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>传输有效率</title>
    <style type="text/css">
        body{
            margin:0px;
            padding:0px;
        }

        a{
            display:block;
        }
        a:link,a:visited  {
            text-decoration: none;
            color:#000000;
        }
        .menu{
            margin:0px 0px;
            padding-left:10px;
            width:510px;
            height:30px;
            cursor:pointer
        }
        .menu ul{
            margin:0px;
            padding:0px;
        }
        .menu ul li{
            position:relative;
            float:left;
            list-style:none;
            padding:0px;
            border:1px dotted;
            font-size:12px;
            width:80px;
            text-align: center;
            margin:0px;
            background:#daecf4;
        }
        .menu ul li ul{
            display:none;
            z-index: 99999;
        }
        .menu ul li:hover ul{
            display:block;
            position: absolute;
            left: 0px; top: 27px;
        }

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
                    <p>
                        <label for="s_enterpriseName">企业名称：</label> <input type="text" id="s_enterpriseName" class="form-control" />
                    </p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <div id="total" class="k-toolbar k-grid-toolbar">
                    <table border="0px" cellspacing="1" cellpadding="0" overflow="hidden">
                        <div class="menu" id="statistical">
                            <ul id="Hyear">
                                <li><a id="selYear" href="#">年份</a>
                                    <ul id="year">
                                        <%--<li></li>--%>
                                    </ul>
                                </li>
                                <li><a class="tm" href="#">第一季度</a>
                                    <ul>
                                        <li class="month" value="01">1月份</li>
                                        <li class="month" value="02">2月份</li>
                                        <li class="month" value="03">3月份</li>
                                    </ul>
                                </li>
                                <li><a class="tm" href="#">第二季度</a>
                                    <ul>
                                        <li class="month" value="04">4月份</li>
                                        <li class="month" value="05">5月份</li>
                                        <li class="month" value="06">6月份</li>
                                    </ul>
                                </li>
                                <li><a class="tm" href="#">第三季度</a>
                                    <ul>
                                        <li class="month" value="07">7月份</li>
                                        <li class="month" value="08">8月份</li>
                                        <li class="month" value="09">9月份</li>
                                    </ul>
                                </li>
                                <li><a class="tm" href="#">第四季度</a>
                                    <ul>
                                        <li class="month" value="10">10月份</li>
                                        <li class="month" value="11">11月份</li>
                                        <li class="month" value="12">12月份</li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </table>
                </div>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#scfForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#scfForm">
                        <i class="btnIcon edit-icon"></i><span>修改</span>
                    </button>
                </p>
            </div>
            <div class="tableBox">
                <table id="table" class="table table-striped table-responsive">
                </table>
            </div>
        </div>
    </div>
</div>

<!--添加表单-->
<div class="modal fade" id="scfForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title" id="demoFormTitle">添加水污染治理设施</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">企业名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="text" id="enterpriseName" name="enterpriseName" class="form-control"
                                   data-message="用户名不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="blockName" class="col-sm-2 control-label">所属网格名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="blockName" name="blockName" class="form-control"
                                   data-message="所属网格名称不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="startTime" class="col-sm-2 control-label">开始时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="datetimepicker" class="input-group date form_date col-md-10" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                <input class="form-control" id="startTime" name="startTime" size="16" type="text" value="" readonly
                                       data-message="时间不能为空"
                                       data-easytip="position:top;class:easy-red;"
                                />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <label for="endTime" class="col-sm-2 control-label">结束时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="datetimepicker2" class="input-group date form_date col-md-10" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                <input class="form-control" id="endTime" name="endTime" size="16" type="text" value="" readonly
                                       data-message="时间不能为空"
                                       data-easytip="position:top;class:easy-red;"
                                />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>

                        </div>

                    </div>
                    <div class="form-group">
                        <label for="transpor" class="col-sm-2 control-label">传输率<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="transpor" name="transpor" class="form-control"
                                   data-message="工艺处理不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="efficient" class="col-sm-2 control-label">有效率<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="efficient" name="efficient" class="form-control"
                                   data-message="有效率不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ratio" class="col-sm-2 control-label">传输有效率<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="ratio" name="ratio" class="form-control"
                                   data-message="传输有效率不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-2 control-label">备注：</label>
                        <div class="col-sm-10">
                            <textarea type="text" id="remark" name="remark" class="form-control" rows="5"
                                   data-message="设计处理能力不为空"
                                   data-easytip="position:top;class:easy-red;"
                            ></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="<%=request.getContextPath()%>/container/gov/port/scripts/transport_efficient.js"></script>

</body>
</html>
