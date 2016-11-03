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
        /*.menu{*/
            /*margin:0px 0px;*/
            /*padding-left:10px;*/
            /*width:510px;*/
            /*height:30px;*/
            /*cursor:pointer*/
        /*}*/
        /*.menu ul{*/
            /*margin:0px;*/
            /*padding:0px;*/
        /*}*/
        /*.menu ul li{*/
            /*position:relative;*/
            /*float:left;*/
            /*list-style:none;*/
            /*padding:0px;*/
            /*border:1px dotted;*/
            /*font-size:12px;*/
            /*width:80px;*/
            /*text-align: center;*/
            /*margin:0px;*/
            /*background:#daecf4;*/
        /*}*/
        /*.menu ul li ul{*/
            /*display:none;*/
            /*z-index: 99999;*/
        /*}*/
        /*.menu ul li:hover ul{*/
            /*display:block;*/
            /*position: absolute;*/
            /*left: 0px; top: 27px;*/
        /*}*/
        .ui-autocomplete { z-index:2147483647; }
        /*.nav ul {*/
            /*display: none;*/
        /*}*/
        #total{
            padding-left: 11%;
            margin-top: 1%;
        }
        .dealBox button {
             min-width: 0px;
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
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="s_name" class="ui-widget">企业名称：</label> <input type="text" id="s_name" style="width: 180px;" class="form-control" />
                            <%--<input id="selCompanyBtn" style="color: #fff;background-color: #449d44;border-color: #398439; width:15%;" type="button" value="选择" class="form-control" data-toggle="modal" data-target="#demoForm"/>--%>
                        </div>
                        <div class="form-group">
                            <label for="">日期：</label>
                            <div id="datetimepicker1" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="sendTime">
                                <input class="form-control" size="16" id="start_createTime"  type="text" value="" readonly placeholder="开始时间">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="sendTime">
                                <input class="form-control" size="16" id="end_createTime"  type="text" value="" readonly placeholder="结束时间">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button type="button" class="btn btn-default" onclick="resetQuery()"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>



                <div id="total" class="k-toolbar k-grid-toolbar">
                    <table border="0px" cellspacing="1" cellpadding="0" overflow="hidden">
                            <div class="btn-group" id="statistical">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" id="selYear">年份</button>
                                    <button type="button" class="btn btn-default dropdown-toggle"
                                            data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">切换下拉菜单</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu" id="year">

                                    </ul>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default tm">第一季度</button>
                                    <button type="button" class="btn btn-default dropdown-toggle"
                                            data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">切换下拉菜单</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li class="month" value="01"><a href="#">1月</a></li>
                                        <li class="month" value="02"><a href="#">2月</a></li>
                                        <li class="month" value="03"><a href="#">3月</a></li>
                                    </ul>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default tm">第二季度</button>
                                    <button type="button" class="btn btn-default dropdown-toggle"
                                            data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">切换下拉菜单</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li class="month" value="04"><a href="#">4月</a></li>
                                        <li class="month" value="05"><a href="#">5月</a></li>
                                        <li class="month" value="06"><a href="#">6月</a></li>
                                    </ul>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default tm">第三季度</button>
                                    <button type="button" class="btn btn-default dropdown-toggle"
                                            data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">切换下拉菜单</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li class="month" value="07"><a href="#">7月</a></li>
                                        <li class="month" value="08"><a href="#">8月</a></li>
                                        <li class="month" value="09"><a href="#">9月</a></li>
                                    </ul>
                                </div>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default tm">第四季度</button>
                                    <button type="button" class="btn btn-default dropdown-toggle"
                                            data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">切换下拉菜单</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li class="month" value="10"><a href="#">10月</a></li>
                                        <li class="month" value="11"><a href="#">11月</a></li>
                                        <li class="month" value="12"><a href="#">12月</a></li>
                                    </ul>
                            </div>
                            <%--<ul class="nav nav-tabs" id="Hyear">--%>
                            <%--<li class="dropdown" >--%>
                                <%--<a class="dropdown-toggle" data-toggle="dropdown" href="#">--%>
                                    <%--年份 <span class="caret"></span>--%>
                                <%--</a>--%>
                                <%--<ul class="dropdown-menu" id="year">--%>
                                <%--</ul>--%>
                            <%--</li>--%>
                            <%--<li class="dropdown">--%>
                                <%--<a class="dropdown-toggle" data-toggle="dropdown" href="#">--%>
                                    <%--第一季度 <span class="caret"></span>--%>
                                <%--</a>--%>
                                <%--<ul class="dropdown-menu">--%>
                                    <%--<li><a href="#">1月份</a></li>--%>
                                    <%--<li><a href="#">2月份</a></li>--%>
                                    <%--<li><a href="#">3月份</a></li>--%>
                                <%--</ul>--%>
                            <%--</li>--%>
                            <%--<li class="dropdown">--%>
                                <%--<a class="dropdown-toggle" data-toggle="dropdown" href="#">--%>
                                    <%--第二季度 <span class="caret"></span>--%>
                                <%--</a>--%>
                                <%--<ul class="dropdown-menu">--%>
                                    <%--<li><a href="#">4月份</a></li>--%>
                                    <%--<li><a href="#">5月份</a></li>--%>
                                    <%--<li><a href="#">6月份</a></li>--%>
                                <%--</ul>--%>
                            <%--</li>--%>
                            <%--<li class="dropdown">--%>
                                <%--<a class="dropdown-toggle" data-toggle="dropdown" href="#">--%>
                                    <%--第三季度 <span class="caret"></span>--%>
                                <%--</a>--%>
                                <%--<ul class="dropdown-menu">--%>
                                    <%--<li><a href="#">7月份</a></li>--%>
                                    <%--<li><a href="#">8月份</a></li>--%>
                                    <%--<li><a href="#">9月份</a></li>--%>
                                <%--</ul>--%>
                            <%--</li>--%>
                            <%--<li class="dropdown">--%>
                                <%--<a class="dropdown-toggle" data-toggle="dropdown" href="#">--%>
                                    <%--第四季度 <span class="caret"></span>--%>
                                <%--</a>--%>
                                <%--<ul class="dropdown-menu">--%>
                                    <%--<li><a href="#">10月份</a></li>--%>
                                    <%--<li><a href="#">11月份</a></li>--%>
                                    <%--<li><a href="#">12月份</a></li>--%>
                                <%--</ul>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                            <%--<ul id="Hyear">--%>
                                <%--<li><a id="selYear" href="#">年份</a>--%>
                                    <%--<ul id="year">--%>
                                        <%--<li></li>--%>
                                    <%--</ul>--%>
                                <%--</li>--%>
                                <%--<li><a class="tm" href="#">第一季度</a>--%>
                                    <%--<ul>--%>
                                        <%--<li class="month" value="01">1月份</li>--%>
                                        <%--<li class="month" value="02">2月份</li>--%>
                                        <%--<li class="month" value="03">3月份</li>--%>
                                    <%--</ul>--%>
                                <%--</li>--%>
                                <%--<li><a class="tm" href="#">第二季度</a>--%>
                                    <%--<ul>--%>
                                        <%--<li class="month" value="04">4月份</li>--%>
                                        <%--<li class="month" value="05">5月份</li>--%>
                                        <%--<li class="month" value="06">6月份</li>--%>
                                    <%--</ul>--%>
                                <%--</li>--%>
                                <%--<li><a class="tm" href="#">第三季度</a>--%>
                                    <%--<ul>--%>
                                        <%--<li class="month" value="07">7月份</li>--%>
                                        <%--<li class="month" value="08">8月份</li>--%>
                                        <%--<li class="month" value="09">9月份</li>--%>
                                    <%--</ul>--%>
                                <%--</li>--%>
                                <%--<li><a class="tm" href="#">第四季度</a>--%>
                                    <%--<ul>--%>
                                        <%--<li class="month" value="10">10月份</li>--%>
                                        <%--<li class="month" value="11">11月份</li>--%>
                                        <%--<li class="month" value="12">12月份</li>--%>
                                    <%--</ul>--%>
                                <%--</li>--%>
                            <%--</ul>--%>
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
                    <button id="remove" type="button" class="btn btn-sm btn-danger">
                        <i class="btnIcon delf-icon"></i><span>删除</span>
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
<div class="modal fade" data-backdrop="static" id="scfForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                        <label for="remark" class="col-sm-2 control-label">备注*：</label>
                        <div class="col-sm-10">
                            <textarea  id="remark" name="remark" class="form-control" rows="5"
                                       data-message="备注不能为空"
                                       data-easytip="position:top;class:easy-red;"
                            ></textarea>
                        </div>
                    </div>
                    <div class="form-group" style="display: none;">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" >
                <button type="button" class="btn btn-primary" id="save">保存</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="<%=request.getContextPath()%>/container/gov/port/scripts/transport_efficient.js"></script>
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
            }

        } );

        $( "#enterpriseName" ).autocomplete({
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
            }

        } );

    } );
</script>


</body>
</html>
