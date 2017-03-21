<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
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
            /*color: #e4393c;*/
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
        .ui-autocomplete { z-index:2147483647; }
    </style>
    <style>
        .Node-frame-menubar {
            width: auto;
            height: 400px;
            position: relative;
            left: 0px;
            border-right: 1px solid #e5e5e5;
            padding: 10px;
        }
        .list-group-item{
            cursor: default;
        }
    </style>
    <%
        String enterpriseId =request.getParameter("id");
    %>
    <script type="text/javascript">
        var enterpriseId='<%=enterpriseId%>';
        $('.modal-body').attr('style','max-height: '+pageUtils.getFormHeight()+'px;overflow-y: auto;overflow-x: hidden;padding:10px;');
        //$('.media-body').attr('style','max-height: '+pageUtils.getFormHeight()+'px;overflow-y: auto;overflow-x: hidden;padding:10px;');
    </script>
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
                <div class="queryBox marginLeft0" >
                    <form class="form-inline" role="form" id="searchform">
                        <div class="form-group">
                            <label for="name">项目名称：</label> <input type="text" id="name" name="name" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="buildNature">建设性质：</label>
                            <select id="buildNature" name="buildNature" class="form-control" style="width: 301px;">
                                <option value="">请选择</option>
                                <option value="1">新建</option>
                                <option value="2">改扩建</option>
                                <option value="3">技术改造</option>
                            </select>
                        </div>
                     <p></p>
                        <div class="form-group">
                            <label for="area">&nbsp;行政区：</label>
                            <input type="text" id="area" name="area" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="">批复时间：</label>
                            <div  class="input-group date searchInput start_Time" data-date="" data-date-format="yyyy-mm-dd" data-link-field="replyTime">
                                <input class="form-control" size="16" id="startDate" name="startDate"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div class="input-group date searchInput end_Time" data-date="" data-date-format="yyyy-mm-dd" data-link-field="replyTime">
                                <input class="form-control" size="16" id="endDate" name="endDate"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button type="button" id="searchFix" class="btn btn-default"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <br><br>
                <p class="btnListP">
                    <%--<button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#hpForm">
                        <i class="btnIcon add-icon"></i><span>新增(环评)</span>
                    </button>
                    <button id="addYS" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#ysForm">
                        <i class="btnIcon add-icon"></i><span>新增(验收)</span>
                    </button>--%>
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal"
                            data-target="#buildForm">
                        <i class="btnIcon add-icon"></i><span>新增</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#buildForm">
                        <i class="btnIcon edit-icon"></i><span>修改</span>
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

<!--项目-->
<div class="modal fade " data-backdrop="static" id="buildForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="min-width: 1200px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close modalClose"  aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="ModalLabel">建设项目</h4>
            </div>
            <div class="media-body">
                <div class="tableDiv" style="max-height: 600px;overflow-y: auto;overflow-x: hidden;">
                    <form class="form-horizontal"  action="" style="margin: 0 10px;" role="form">
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="table table-bordered table-responsive">
                                    <tbody>
                                    <tr>
                                        <td class="success" rowspan="5">基本信息</td>
                                        <td class="success text-red">项目名称：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input type="hidden" class="basedata" id="type" name="type" value="">
                                                    <%--<input type="hidden" class="basedata" id="id" name="id" value="">--%>
                                                    <input type="hidden" id="id" name="id" class="basedata">
                                                    <input type="hidden" id="replyTime" name="replyTime" class="basedata">
                                                    <input type="hidden" class="basedata" id="removeId" name="removeId" value="">
                                                    <input class="form-control basedata"  type="text" id="name" name="name"
                                                           data-message="项目名称不能为空"
                                                           data-easytip="position:top;class:easy-red;"
                                                    />
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">环境保护管理类别：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9 basedata" name="envManagType">
                                                    <div class="radio-inline ">
                                                        <label ><input type="radio" class="envManagType1" name="envManagType" value="1"/>报告书</label>
                                                        <label ><input type="radio" class="envManagType2" name="envManagType" value="2"/>报告表</label>
                                                        <label ><input type="radio" class="envManagType3" name="envManagType" value="3"/>登记表</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">项目性质：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9 basedata" name="buildNature">
                                                    <div class="radio-inline" >
                                                        <label ><input type="radio" class="buildNature1" name="buildNature" value="1"/>新建</label>
                                                        <label ><input type="radio" class="buildNature2" name="buildNature" value="2"/>改扩建</label>
                                                        <label ><input type="radio" class="buildNature3" name="buildNature" value="3"/>技术改造</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">行政区：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input class="form-control basedata" type="text" id="area" name="area"
                                                    />
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">建设地点：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="buildAddress" name="buildAddress" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">行业类别：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="industryType" name="industryType" class="form-control basedata" type="text"/>
                                                </div>
                                                <div class="col-sm-2 selectBtn">
                                                    <button class="btn btn-default formBtn" type="button" data-toggle="modal" data-target="#industryTypeModal">
                                                        选择
                                                    </button>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">建设内容及规模：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="content" name="content" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">总投资(万元)：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="investment" name="investment" class="form-control basedata" type="number"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">环保投资(万元)：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="envInvestment" name="envInvestment" class="form-control basedata" type="number"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success ">所占比例：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="proportion" name="proportion" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td rowspan="3" class="success">建设单位</td>
                                        <td class="success text-red">单位名称：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="builderName" name="builderName" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success ">联系电话：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="builderTel" name="builderTel" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">通讯地址：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="builderAddress" name="builderAddress" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success ">邮政编码：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input  id="builderZipCode" name="builderZipCode" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">法人代表：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="builderAP" name="builderAP" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success">联系人：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="builderLinkman" name="builderLinkman" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success">附件</td>
                                        <td class="align-left" colspan="4">
                                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                                            <div id="build-fine-uploader-gallery"></div>
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
                <button type="button" class="btn btn-primary saveButton" id="buildSave">保存</button>
                <button type="button" class="btn btn-default btn-cancel modalClose" >取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--环评添加表单-->
<div class="modal fade buildForm" data-backdrop="static" id="hpForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="min-width: 1200px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close modalClose"  aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="hpModalLabel">建设项目环评及其他许可情况</h4>
            </div>
            <div class="media-body">
                <div class="tableDiv" style="max-height: 600px;overflow-y: auto;overflow-x: hidden;">
                    <form class="form-horizontal"  action="" style="margin: 0 10px;" role="form">
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="table table-bordered table-responsive">
                                    <tbody>
                                    <tr>
                                        <td class="success" rowspan="5">基本信息</td>
                                        <td class="success text-red">项目名称：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input type="hidden" class="basedata" id="type" name="type" value="">
                                                    <%--<input type="hidden" class="basedata" id="id" name="id" value="">--%>
                                                    <input type="hidden" id="hp_projectId" name="hp_projectId" class="basedata">
                                                    <input type="hidden" class="basedata" id="hpremoveId" name="removeId" value="">
                                                    <input class="form-control basedata"  type="text" id="name" name="name"
                                                           data-message="项目名称不能为空"
                                                           data-easytip="position:top;class:easy-red;"
                                                    />
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">环境保护管理类别：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9 basedata" name="envManagType">
                                                    <div class="radio-inline ">
                                                        <label ><input type="radio" class="envManagType1" name="envManagType" value="1"/>报告书</label>
                                                        <label ><input type="radio" class="envManagType2" name="envManagType" value="2"/>报告表</label>
                                                        <label ><input type="radio" class="envManagType3" name="envManagType" value="3"/>登记表</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">项目性质：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9 basedata" name="buildNature">
                                                    <div class="radio-inline" >
                                                        <label ><input type="radio" class="buildNature1" name="buildNature" value="1"/>新建</label>
                                                        <label ><input type="radio" class="buildNature2" name="buildNature" value="2"/>改扩建</label>
                                                        <label ><input type="radio" class="buildNature3" name="buildNature" value="3"/>技术改造</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">行政区：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input class="form-control basedata" type="text" id="area" name="area"
                                                    />
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">建设地点：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="buildAddress" name="buildAddress" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">行业类别：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="industryType" name="industryType" class="form-control basedata" type="text"/>
                                                </div>
                                                <div class="col-sm-2 selectBtn">
                                                    <button class="btn btn-default formBtn basedata" type="button" data-toggle="modal" data-target="#industryTypeModal">
                                                        选择
                                                    </button>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">建设内容及规模：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="content" name="content" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">总投资(万元)：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="investment" name="investment" class="form-control basedata" type="number"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">环保投资(万元)：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="envInvestment" name="envInvestment" class="form-control basedata" type="number"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success ">所占比例：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="proportion" name="proportion" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td rowspan="3" class="success">建设单位</td>
                                        <td class="success text-red">单位名称：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="builderName" name="builderName" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success ">联系电话：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="builderTel" name="builderTel" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">通讯地址：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="builderAddress" name="builderAddress" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success ">邮政编码：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input  id="builderZipCode" name="builderZipCode" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">法人代表：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="builderAP" name="builderAP" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success">联系人：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="builderLinkman" name="builderLinkman" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td rowspan="3" class="success">评价单位</td>
                                        <td class="success text-red">单位名称：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="idEIA" name="id" class="form-control otherdata" type="hidden"/>
                                                    <input name="euName" id="euName" class="form-control otherdata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success ">联系电话：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="euTel" name="euTel" class="form-control otherdata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success ">通讯地址：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="euAddress" name="euAddress" class="form-control otherdata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success">邮政编码：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="euZipCode" name="euZipCode" class="form-control otherdata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success ">证书编号：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="certificateCode" name="certificateCode" class="form-control otherdata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success">评价经费(万元)：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="certificateMoney" name="certificateMoney" class="form-control otherdata" type="number"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td rowspan="3" class="success">审批结果</td>
                                        <td class="success text-red">批复时间：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <div id="replyEIATimeContent" class="input-group date form_date" data-date="" data-link-field="replyEIATime" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                                        <input class="form-control otherdata"  name="replyEIATime" size="16" type="text" value="" readonly
                                                               data-message="批复时间不能为空"
                                                               data-easytip="position:top;class:easy-red;"
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
                                                <div class="col-sm-9">
                                                    <input id="replyEIACode" name="replyEIACode" class="form-control otherdata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">审批部门：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="replyEIAOrg" name="replyEIAOrg" class="form-control otherdata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success">是否许可：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9 otherdata" name="isEIALicense">
                                                    <div class="radio-inline">
                                                        <label><input type="radio" class="isEIALicense1" name="isEIALicense" value="1"/>是</label>
                                                        <label><input type="radio" class="isEIALicense0" name="isEIALicense" value="0"/>否</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">批复意见：</td>
                                        <td colspan="3">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <textarea class="form-control otherdata" name="replyEIAOpinion" id="replyEIAOpinion" cols="30" rows="5"></textarea>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success">附件</td>
                                        <td class="align-left" colspan="4">
                                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                                            <div id="hp-fine-uploader-gallery"></div>
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
                <button type="button" class="btn btn-primary saveButton" id="hpsave">保存</button>
                <button type="button" class="btn btn-default btn-cancel modalClose" >取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--验收添加表单-->
<div class="modal fade " data-backdrop="static" id="ysForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="min-width: 1200px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close modalClose"  aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="ysModalLabel">新增建设项目竣工环境保护验收审批信息</h4>
            </div>
            <div class="media-body">
                <div class="tableDiv" style="max-height: 600px;overflow-y: auto;overflow-x: hidden;">
                    <form class="form-horizontal" role="form" action="" style="margin: 0 10px;">
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="table table-bordered table-responsive">
                                    <tbody>
                                    <tr>
                                        <td class="success" rowspan="8">基本信息</td>
                                        <td class="success text-red">建设项目名称(验收申请)：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input type="hidden" id="ys_projectId" name="ys_projectId" class="basedata">
                                                    <input type="hidden"  name="type" class="basedata">
                                                    <input type="hidden" id="ysremoveId"  name="removeId" class="basedata">
                                                    <input type="text"  name="name" class="form-control basedata  "/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">建设项目名称(环评批复)：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input id="idAcceptance" name="id" class="form-control otherdata" type="hidden"/>
                                                    <input  name="name" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">项目性质：</td>
                                        <td>
                                            <div class="row">
                                                    <div class="col-sm-9 basedata" name="buildNature">
                                                    <div class="radio-inline" >
                                                        <label for="buildNature"><input type="radio" class="buildNature1"  name="buildNature" value="1"/>新建</label>
                                                        <label for="buildNature"><input type="radio" class="buildNature2"  name="buildNature" value="2"/>改扩建</label>
                                                        <label for="buildNature"><input type="radio" class="buildNature3"  name="buildNature" value="3"/>技术改造</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success"></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">行业类别：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input  name="industryType" class="form-control basedata" type="text"/>
                                                </div>
                                               <%-- <div class="col-sm-2 selectBtn">
                                                    <button class="btn btn-default formBtn basedata" type="button" data-toggle="modal" data-target="#industryTypeModal">
                                                        选择
                                                    </button>
                                                </div>--%>
                                            </div>
                                        </td>
                                        <td class="success text-red"></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">行政区：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input name="area" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success">建设地点：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input  name="buildAddress" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">环评批复单位：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input  name="euName" class="form-control hpdata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">环评批复文号：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input  name="replyEIACode" class="form-control hpdata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">环评批复时间：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <div id="TimeContent" class="input-group date form_date" data-date="" data-link-field="replyTime" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                                        <input class="form-control hpdata"  name="replyEIATime" size="16" type="text" value="" readonly
                                                        >
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                            </div>
                                        </td>
                                        <td class="success">竣工验收单位：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input  name="acceptOrg" class="form-control builddata" type="text"
                                                            data-message="竣工验收单位不能为空"
                                                            data-easytip="position:top;class:easy-red;"
                                                    />
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success">竣工验收时间：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <div id="acceptTimeContent" class="input-group date form_date" data-date="" data-link-field="acceptTime" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                                        <input class="form-control builddata"  name="acceptTime" size="16" type="text" value="" readonly
                                                               data-message="竣工验收时间不能为空"
                                                               data-easytip="position:top;class:easy-red;"
                                                        >
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success"></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">实际总投资(万元)：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input name="investment" class="form-control basedata" type="number"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">实际环保投资(万元)：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input name="envInvestment" class="form-control basedata" type="number"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td rowspan="3" class="success">建设单位</td>
                                        <td class="success text-red">建设单位：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input  name="builderName"  class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success ">邮政编码：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input  name="builderZipCode" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">法人代表：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input  name="builderAP" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">联系人：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input  name="builderLinkman" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">联系电话：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input  name="builderTel" class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">通讯地址：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input name="builderAddress"  class="form-control basedata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td rowspan="3" class="success">审批结果</td>
                                        <td class="success text-red">批复时间：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <div id="replyTime_Content" class="input-group date form_date" data-date="" data-link-field="replyAccTime" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                                        <input class="form-control otherdata"  name="replyAccTime" size="16" type="text" value="" readonly
                                                               data-message="批复时间不能为空"
                                                               data-easytip="position:top;class:easy-red;"
                                                        >
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success text-red">验收批复文号：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input name="replyAccCode"  class="form-control otherdata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">审批部门：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9">
                                                    <input  name="replyAccOrg" class="form-control otherdata" type="text"/>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="success">是否许可：</td>
                                        <td>
                                            <div class="row">
                                                <div class="col-sm-9 otherdata" name="isAccLicense">
                                                    <div class="radio-inline " >
                                                        <label ><input type="radio" class="isAccLicense1" name="isAccLicense" value="1"/>是</label>
                                                        <label ><input type="radio" class="isAccLicense0" name="isAccLicense" value="0"/>否</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success text-red">批复意见：</td>
                                        <td colspan="3">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <textarea class="form-control otherdata" name="replyAccOpinion"  cols="30" rows="5"></textarea>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success">附件</td>
                                        <td class="align-left" colspan="4">
                                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                                            <div id="ys-fine-uploader-gallery"></div>
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
                <button type="button" class="btn btn-primary saveButton" id="ysSave">保存</button>
                <button type="button" class="btn btn-default btn-cancel modalClose" >取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<%----------------------------------------------------弹出框区域---------------------------------------------------------------------%>
<!-- 行业类别模态框 start -->
<div class="modal fade" id="industryTypeModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close modalClose"  aria-hidden="true">
                    &times;
                </button>
                <h5 class="modal-title" id="myModalLabel">
                    行业类别
                </h5>
            </div>
            <div class="modal-body">
                <div class="Node-frame-menubar">
                    <div class="scrollContent" >
                        <ul id="industryTypeTree" class="ztree"></ul>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="industryTypeModalClose" type="button" class="btn btn-default modalClose" >关闭</button>
                <button id="industryTypeModalSure" type="button" class="btn btn-primary">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 行业类别模态框 end -->

<script src="<%=request.getContextPath()%>/container/gov/enterprise/scripts/buildproject.js"></script>
</body>
</html>
