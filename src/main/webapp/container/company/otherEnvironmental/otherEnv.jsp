<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/8
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8">
    <title>其他环境信息</title>
    <script type="text/javascript">
        var enterpriseId=enterpriseData.id;
        $('input[name="enterpriseId"]').val(enterpriseId);
    </script>
    <style>
        .list-group-item{
            cursor: default;
        }
        .success{
            text-align: right;
            font-size: 15px;
        }
    </style>
</head>
<body>
<div class="form-div" style="width: 100%;">
    <a id="headTitle" href="javascript:void(0)" class="list-group-item active">其他环境信息</a>
    <div style="width: 100%">
        <div class="tableDiv" id="otherEnvFormDiv" style="max-height: 600px;overflow-y: auto;overflow-x: hidden;">
            <form class="form-horizontal" id="OtherEnvForm" action="" style="margin: 0 10px;margin-top: 10px;" role="form">
                <input type="hidden" name="id" class="form-control" />
                <input type="hidden" name="enterpriseId" value="" class="form-control"/>
                <input type="hidden" name="createTime" value="" class="form-control" />
                <div class="row">
                    <div class="col-sm-12">
                        <table class="table table-bordered table-responsive">
                            <tbody>
                            <tr>
                                <td class="col-sm-3 success">环境制度建设情况</td>
                                <td colspan="4" class="col-sm-9">
                                    <div class="col-sm-12">
                                        <input type="text" id="envBuildDesc" name="envBuildDesc" title="环境制度建设情况" class="form-control needCheck"
                                               data-message="不能为空"
                                               data-easytip="position:top;class:easy-red;"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td  class="success">是否通过ISO14001环境管理体系认证</td>
                                <td colspan="4">
                                    <div class="col-sm-12 isRadio" id="isISO">
                                        <label class="checkbox-inline">
                                            <input type="radio" name="isISO" id="isISO1" value="1" class="needCheck"  data-easytip="class:easy-red;" data-message="请选择是否通过认证">已通过
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="radio" name="isISO" id="isISO0" value="0">未通过
                                        </label>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="success">环境制度建设情况</td>
                                <td  colspan="4">
                                    <div class="col-sm-12">
                                        <textarea class="form-control needshow" id="otherInfo" name="otherInfo" rows="5"></textarea>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="success">附件</td>
                                <td  colspan="4">
                                    <div class="col-sm-12">
                                        <input type="hidden" id="attachmentId" name="attachmentId" class="form-control">
                                        <input type="hidden" id="removeId" name="removeId" class="form-control">
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
        <div class="form-horizontal">
            <div class="form-group">
                <div class="col-sm-4"></div>
                <div class="col-sm-2">
                    <button id="saveForm" type="button" class="btn btn-success editBtn" style="display: none">保存</button>
                </div>
                <div class="col-sm-2">
                    <button id="toEditForm" type="button" class="btn btn-success lookBtn">编辑</button>
                    <button id="resetEditForm" type="button" class="btn btn-default editBtn" style="display: none">置空</button>
                </div>
                <div class="col-sm-2">
                    <button id="cancelEditForm" type="button" class="btn btn-warning editBtn" style="display: none">取消</button>
                </div>
                <div class="col-sm-2"></div>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/company/otherEnvironmental/scripts/otherEnv.js"></script>
</body>
</html>
