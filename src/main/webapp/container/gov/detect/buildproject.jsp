<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/common_include.jsp"%>
    <title>建设项目环评及验收信息</title>
    <style>
        .tableDiv  .radio-inline label{margin-right: 25px;}
        .tableDiv .table>tbody>tr>td{
            vertical-align: middle;
            padding: 4px;
        }
        td{
            text-align: right;
        }
        .tableDiv .input-group.date{
            width: 270px;
        }
        .text-red{
            color: #e4393c;
        }
        .col-sm-2.selectBtn{
            padding-left: 0px;
        }
        .tableDiv .radio-inline{
            width: 100%;
            text-align: left;
        }
        .align-left{
            text-align: left;
        }
        .tableDiv .form-control{
            padding: 4px 8px;
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
                            <label for="s_name">项目名称：</label> <input type="text" id="s_name" name="s_name" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="s_buildNature">建设性质：</label>
                            <select id="s_buildNature" name="s_buildNature" class="form-control" style="width: 301px;">
                                <option value="">请选择</option>
                                <option value="1">新建</option>
                                <option value="2">改扩建</option>
                                <option value="3">技术改造</option>
                            </select>
                        </div>
                    </form>
                    <p></p>
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="s_area">行政区：</label>
                            <input type="text" id="s_area" name="s_area" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="">批复时间：</label>
                            <div id="datetimepicker1" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" id="start_sendTime" size="16" type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div id="datetimepicker2" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" id="end_sendTime" size="16" type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <br><br>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#hpForm">
                        <i class="btnIcon add-icon"></i><span>新增(环评)</span>
                    </button>
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#ysForm">
                        <i class="btnIcon add-icon"></i><span>新增(验收)</span>
                    </button>
                    <button id="remove" type="button" class="btn btn-sm btn-danger">
                        <i class="btnIcon delf-icon"></i><span>删除</span>
                    </button>
                    <button id="export" type="button" class="btn btn-sm btn-success">
                        <span class="glyphicon glyphicon-export"></span>导出
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
<div class="modal fade" data-backdrop="static" id="hpForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="min-width: 1200px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="hpModalLabel">新增建设项目竣工环境保护验收审批信息</h4>
            </div>
            <div class="media-body">
                <div class="tableDiv" style="max-height: 600px;overflow-y: auto;overflow-x: hidden;">
                    <form class="form-horizontal" action="" style="margin: 0 10px;" role="form">
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="table table-bordered table-responsive">
                                    <tbody>
                                    <tr>
                                        <td class="success" rowspan="5">基本信息</td>
                                        <td class="success text-red">建设项目名称(验收申请):</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input type="hidden" id="id" name="id">
                                                    <input type="hidden" id="removeId" name="removeId">
                                                    <input class="form-control" type="text" id="name" name="name"
                                                           data-message="项目名称不能为空"
                                                           data-easytip="position:top;class:easy-red;"
                                                    />
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">环境保护管理类别:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <div class="radio-inline">
                                                        <label for="EnvManagType"><input type="radio" id="EnvManagType" name="EnvManagType" value="1"/>报告书</label>
                                                        <label for="EnvManagType"><input type="radio" id="EnvManagType" name="EnvManagType" value="2"/>报告表</label>
                                                        <label for="EnvManagType"><input type="radio" id="EnvManagType" name="EnvManagType" value="3"/>登记表</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">项目性质:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <div class="radio-inline">
                                                        <label for="buildNature"><input type="radio" id="buildNature" name="buildNature" value="1"/>新建</label>
                                                        <label for="buildNature"><input type="radio" id="buildNature" name="buildNature" value="2"/>改扩建</label>
                                                        <label for="buildNature"><input type="radio" id="buildNature" name="buildNature" value="3"/>技术改造</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">行政区：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input class="form-control" type="text" id="area" name="area"
                                                    />
                                                </div>
                                                <div class="col-sm-2 selectBtn">
                                                    <button class="btn btn-sm btn-success">选择</button>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">建设地点:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="buildAddress" name="buildAddress" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">行业类别：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="industryType" name="industryType" class="form-control" type="text"/>
                                                </div>
                                                <div class="col-sm-2 selectBtn">
                                                    <button class="btn btn-sm btn-success">选择</button>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">建设内容及规模：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="content" name="content" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">总投资(万元)：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="investment" name="investment" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">环保投资(万元)：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="EnvInvestment" name="EnvInvestment" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success ">所占比例：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="proportion" name="proportion" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td rowspan="3" class="success">建设单位</td>
                                        <td class="success text-red">单位名称：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="builderName" name="builderName" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success ">联系电话：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="builderTel" name="builderTel" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">通讯地址：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="builderAddress" name="builderAddress" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success ">邮政编码：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input  id="builderZipCode" name="builderZipCode" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">法人代表：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="builderAP" name="builderAP" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success">联系人：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="builderLinkman" name="builderLinkman" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td rowspan="3" class="success">评价单位</td>
                                        <td class="success text-red">单位名称：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input name="euName" id="euName" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success ">联系电话：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="euTel" name="euTel" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success ">通讯地址：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="euAddress" name="euAddress" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success">邮政编码：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="euZipCode" name="euZipCode" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success ">证书编号：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="certificateCode" name="certificateCode" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success">评价经费(万元)：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="certificateMoney" name="certificateMoney" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td rowspan="3" class="success">审批结果</td>
                                        <td class="success text-red">批复时间：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <div id="replyTimeContent" class="input-group date form_date" data-date="" data-link-field="pubTime" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                                        <input class="form-control" id="replyTime" name="replyTime" size="16" type="text" value="" readonly
                                                              >
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">批复文号：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="replyCode" name="replyCode" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">审批部门：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="replyOrg" name="replyOrg" class="form-control" type="text"/>
                                                </div>
                                                <div class="col-sm-2 selectBtn">
                                                    <button class="btn btn-sm btn-success">选择</button>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success">是否许可：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <div class="radio-inline">
                                                        <label for="isLicense"><input type="radio" id="isLicense" name="isLicense" value="1"/>是</label>
                                                        <label for="isLicense"><input type="radio" id="isLicense" name="isLicense" value="0"/>否</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">批复意见:</td>
                                        <td colspan="3">
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <textarea class="form-control" name="replyOpinion" id="replyOpinion" cols="30" rows="3"></textarea>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success">附件</td>
                                        <td class="align-left" colspan="4">
                                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                                            <div id="fine-uploader-gallery"></div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="hpsave">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--添加表单-->
<div class="modal fade" data-backdrop="static" id="ysForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="min-width: 1200px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="ysModalLabel">新增建设项目竣工环境保护验收审批信息</h4>
            </div>
            <div class="media-body">
                <div class="tableDiv" style="max-height: 600px;overflow-y: auto;overflow-x: hidden;">
                    <form class="form-horizontal" action="" style="margin: 0 10px;">
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="table table-bordered table-responsive">
                                    <tbody>
                                    <tr>
                                        <td class="success" rowspan="8">基本信息</td>
                                        <td class="success text-red">建设项目名称(验收申请):</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input type="hidden" id="id" name="id">
                                                    <input type="hidden" id="removeId" name="removeId">
                                                    <input class="form-control" type="text" id="name" name="name"
                                                    />
                                                </div>
                                                <div class="col-sm-2 selectBtn">
                                                    <button class="btn btn-sm btn-success">选择</button>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">建设项目名称(环评批复):</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="name" name="name" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">项目性质:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <div class="radio-inline">
                                                        <label for="buildNature"><input type="radio" id="buildNature" name="buildNature" value="1"/>新建</label>
                                                        <label for="buildNature"><input type="radio" id="buildNature" name="buildNature" value="2"/>改扩建</label>
                                                        <label for="buildNature"><input type="radio" id="buildNature" name="radioGroup" value="3"/>技术改造</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success"></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">行业类别:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input  id="industryType" name="" class="form-control" type="text"/>
                                                </div>
                                                <div class="col-sm-2 selectBtn">
                                                    <button class="btn btn-sm btn-success">选择</button>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red"></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">行政区:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="area" name="area" class="form-control" type="text"/>
                                                </div>
                                                <div class="col-sm-2 selectBtn">
                                                    <button class="btn btn-sm btn-success">选择</button>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success">建设地点:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="buildAddress" name="buildAddress" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">环评批复单位:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="euName" name="euName" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">环评批复文号:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="replyCode" name="replyCode" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">环评批复时间:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <div class="input-group date form_date" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                                        <input class="form-control" size="6" type="text" id="replyTime"  name="replyTime" value="" readonly>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success">竣工验收单位:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="acceptOrg" name="acceptOrg" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success">竣工验收时间:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <div class="input-group date form_date" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                                        <input class="form-control" size="6" id="acceptTime" name="acceptTime" type="text" value="" readonly>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success"></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">实际总投资(万元):</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">实际环保投资(万元):</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td rowspan="3" class="success">建设单位</td>
                                        <td class="success text-red">建设单位:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="builderName" name="builderName"  class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">邮政编码:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="builderZipCode" name="builderZipCode" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">法人代表:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="builderAP" name="builderAP" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">联系人:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="builderLinkman" name="builderLinkman" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">联系电话:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input id="builderTel" name="builderTel" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">通讯地址:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input name="builderAddress" id="builderAddress" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td rowspan="3" class="success">审批结果</td>
                                        <td class="success text-red">批复时间:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <div class="input-group date form_date" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                                                        <input class="form-control" size="6" id="replyTime1" name="replyTime" type="text" value="" readonly>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">验收批复文号:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <input name="replyCode" id="replyCode" class="form-control" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">审批部门:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="replyOrg" name="replyOrg" class="form-control" type="text"/>
                                                </div>
                                                <div class="col-sm-2 selectBtn">
                                                    <button class="btn btn-sm btn-success">选择</button>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success">是否许可:</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <div class="radio-inline">
                                                        <label for="isLicense"><input type="radio" id="isLicense" name="isLicense" checked/>是</label>
                                                        <label for="isLicense"><input type="radio" id="isLicense" name="isLicense"/>否</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">批复意见:</td>
                                        <td colspan="3">
                                            <div class="row">
                                                <div class="col-sm-11">
                                                    <textarea class="form-control" name="replyOpinion" id="replyOpinion" cols="30" rows="3"></textarea>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success">附件</td>
                                        <td class="align-left" colspan="4">
                                            <div class="col-sm-10">
                                                <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                                                <div id="fine-uploader-gallery"></div>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="ysSave">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="<%=request.getContextPath()%>/container/gov/detect/scripts/buildproject.js"></script>
</body>
</html>
