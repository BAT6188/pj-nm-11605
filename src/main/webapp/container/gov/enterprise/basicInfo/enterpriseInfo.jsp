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
    <%--<jsp:include page="/common/common_include.jsp" flush="true"/>--%>
    <%
        String handleType=request.getParameter("handleType");
        String id=request.getParameter("id");
    %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8">
    <title>修改企业信息</title>
    <script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/jquery.form.js"></script>
    <link href="<%=request.getContextPath()%>/common/scripts/notyf/notyf.min.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/common/scripts/notyf/notyf.js"></script>
    <%--ztree--%>
    <jsp:include page="/common/common_ztree.jsp" flush="true"/>
    <%--select--%>
    <%--<jsp:include page="/common/common_select.jsp" flush="true"/>--%>
    <script type="text/javascript">
        var enterpriseId;
        var handleTypeValue = "<%=handleType%>";
        handleType = handleTypeValue=="null"?handleType:handleTypeValue;
    </script>
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
</head>
<body>
<div class="form-div" style="width: 99%;">
    <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="z-index: 0"></a>
    <form class="form-horizontal" role="form" id="enterpriseForm" method="post" style="margin-top: 20px;">
        <input type="hidden" id="id" name="id" class="form-control" />
        <input type="hidden" id="createTime" name="createTime" class="form-control" />
        <input type="hidden" id="isDel" name="isDel" class="form-control" />
        <input type="hidden" id="delerCode" name="delerCode" class="form-control" />
        <input type="hidden" id="delerName" name="delerName" class="form-control" />
        <input type="hidden" id="delTime" name="delTime" class="form-control" />
        <input type="hidden" id="delOpinion" name="delOpinion" class="form-control" />
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">单位名称<span class="text-danger">(*)</span>：</label>
            <div class="col-sm-4">
                <input type="text" id="name" name="name" class="form-control needshow needCheck" data-message="" data-easyform="notnull" title="单位名称"/>
            </div>
            <label for="status" class="col-sm-2 control-label">企业运行状态：</label>
            <div class="col-sm-4">
                <fieldset class="fieldset">
                <select class="form-control needshow" id="status" name="status">
                    <option value="1">运营中</option>
                    <option value="0">未运营</option>
                </select>
                </fieldset>
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">所属网格<span class="text-danger">(*)</span>：</label>
            <div class="col-sm-4">
                <select class="form-control needshow" id="blockLevelId" name="blockLevelId">
                </select>
            </div>
            <label for="name" class="col-sm-2 control-label">所属网格<span class="text-danger">(*)</span>：</label>
            <div class="col-sm-4">
                <select class="form-control needshow" id="blockId" name="blockId">
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="address" class="col-sm-2 control-label">单位地址<span class="text-danger">(*)</span>：</label>
            <div class="col-sm-4">
                <input type="text" id="address" name="address" class="form-control needshow needCheck" data-message="" data-easyform="notnull" title="单位地址"/>
            </div>
            <label for="pollutantCode" class="col-sm-2 control-label">污染源代码<span class="text-danger">(*)</span>：</label>
            <div class="col-sm-4">
                <input type="text" id="pollutantCode" name="pollutantCode" class="form-control needshow needCheck" data-message="" data-easyform="notnull" title="污染源代码"/>
            </div>
        </div>
        <div class="form-group">
            <label for="longitude" class="col-sm-2 control-label">经度：</label>
            <div class="col-sm-4">
                <input type="text" id="longitude" name="longitude" class="form-control" readonly
                       data-easyform="null;"/>
            </div>
            <label for="latitude" class="col-sm-2 control-label">纬度：</label>
            <div class="col-sm-4">
                <div class="input-group">
                    <input type="text" class="form-control" id="latitude" name="latitude" readonly
                           data-easyform="null;"/>
					<span class="input-group-btn">
						<button class="btn btn-default formBtn" type="button" id="mapMarkBtn">
                            标注
                        </button>
					</span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="zipCode" class="col-sm-2 control-label">邮政编码：</label>
            <div class="col-sm-4">
                <input type="text" id="zipCode" name="zipCode" class="form-control needshow needCheck" data-message="" data-easyform="number" title="邮政编码"/>
            </div>
            <label for="orgCode" class="col-sm-2 control-label">组织机构代码<span class="text-danger">(*)</span>：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control needshow" id="orgCode" name="orgCode">
                <%--<div class="input-group">
                    <input type="text" class="form-control needshow" id="orgCode" name="orgCode">
					<span class="input-group-btn">
						<button class="btn btn-default formBtn" type="button">
                            选择
                        </button>
					</span>
                </div>--%>
            </div>
        </div>
        <div class="form-group">
            <label for="artificialPerson" class="col-sm-2 control-label">法定代表人<span class="text-danger">(*)</span>：</label>
            <div class="col-sm-4">
                <input type="text" id="artificialPerson" name="artificialPerson" class="form-control needshow needCheck" data-message="" data-easyform="notnull" title="法定代表人">
            </div>
            <label for="apPosition" class="col-sm-2 control-label">法定代表人职务<span class="text-danger">(*)</span>：</label>
            <div class="col-sm-4">
                <input type="text" id="apPosition" name="apPosition" class="form-control needshow needCheck" data-message="" data-easyform="notnull" title="法定代表人职务"/>
            </div>
        </div>
        <div class="form-group">
            <label for="apPhone" class="col-sm-2 control-label">法定代表人电话：</label>
            <div class="col-sm-4">
                <input type="text" id="apPhone" name="apPhone" class="form-control needshow needCheck" data-message="" data-easyform="number" title="法定代表人电话"/>
            </div>
            <div class="col-sm-6"></div>
        </div>
        <div class="form-group">
            <label for="envPrincipal" class="col-sm-2 control-label">环保负责人<span class="text-danger">(*)</span>：</label>
            <div class="col-sm-4">
                <input type="text" id="envPrincipal" name="envPrincipal" class="form-control needshow needCheck" data-message="" data-easyform="notnull" title="环保负责人"/>
            </div>
            <label for="epPosition" class="col-sm-2 control-label">环保负责人职务<span class="text-danger">(*)</span>：</label>
            <div class="col-sm-4">
                <input type="text" id="epPosition" name="epPosition" class="form-control needshow"/>
            </div>
        </div>
        <div class="form-group">
            <label for="epPhone" class="col-sm-2 control-label">环保负责人电话：</label>
            <div class="col-sm-4">
                <input type="text" id="epPhone" name="epPhone" class="form-control needshow needCheck" data-message="" data-easyform="number" title="法定代表人电话"/>
            </div>
            <div class="col-sm-6"></div>
        </div>
        <div class="form-group">
            <label for="pollutantType" class="col-sm-2 control-label">污染源类型<span class="text-danger">(*)</span>：</label>
            <div class="col-sm-10" id="pollutantType">
                <fieldset class="fieldset">
                <label class="checkbox-inline">
                    <input type="checkbox" id="pollutantType01" name="pollutantType" value="01" class="needCheck" data-message="" data-easyform="checkbox" title="污染源类型">废水
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" id="pollutantType02" name="pollutantType" value="02">废气
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" id="pollutantType03" name="pollutantType" value="03">污水处理厂
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" id="pollutantType04" name="pollutantType" value="04">重金属
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" id="pollutantType05" name="pollutantType" value="05">畜禽养殖
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" id="pollutantType06" name="pollutantType" value="06">固废
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" id="pollutantType07" name="pollutantType" value="07">危险废物
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" id="pollutantType08" name="pollutantType" value="08">省级实验室
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" id="pollutantType09" name="pollutantType" value="09">二级以上医院
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" id="pollutantType10" name="pollutantType" value="10">其他
                </label></fieldset>
            </div>
        </div>
        <div class="form-group">
            <label for="pollutantLevel" class="col-sm-2 control-label">污染源管理级别<span class="text-danger">(*)</span>：</label>
            <div class="col-sm-10" id="pollutantLevel">
                <fieldset class="fieldset">
                <label class="checkbox-inline">
                    <input type="radio" name="pollutantLevel" id="pollutantLevel01" value="01" class="needCheck" data-message="" data-easyform="radio" title="污染源管理级别">国控
                </label>
                <label class="checkbox-inline">
                    <input type="radio" name="pollutantLevel" id="pollutantLevel02" value="02">省（区）控
                </label>
                <label class="checkbox-inline">
                    <input type="radio" name="pollutantLevel" id="pollutantLevel03" value="03">市控
                </label>
                <label class="checkbox-inline">
                    <input type="radio" name="pollutantLevel" id="pollutantLevel04" value="04">其他
                </label></fieldset>
            </div>
        </div>
        <div class="form-group">
            <label for="superviseType" class="col-sm-2 control-label">排污单位监管类型：</label>
            <div class="col-sm-4">
                <fieldset class="fieldset">
                <select class="form-control needshow"  id="superviseType" name="superviseType">
                    <option value="">---请选择---</option>
                    <option value="01">重点排污单位</option>
                    <option value="02">一般排污单位</option>
                </select></fieldset>
            </div>
            <label for="isSpecial" class="col-sm-2 control-label">是否特殊监管对象<span class="text-danger">(*)</span>：</label>
            <div class="col-sm-4" id="isSpecial">
                <fieldset class="fieldset">
                <label class="checkbox-inline">
                    <input type="radio" name="isSpecial" id="isSpecial1" value="1" class="needCheck" data-easyform="radio" title="是否特殊监管对象" data-message="请选择是否特殊监管对象">是
                </label>
                <label class="checkbox-inline">
                    <input type="radio" name="isSpecial" id="isSpecial0" value="0">否
                </label></fieldset>
            </div>
        </div>
        <div class="form-group">
            <label for="registType" class="col-sm-2 control-label">登记注册类型：</label>
            <div class="col-sm-4">
                <fieldset class="fieldset">
                <select class="form-control needshow"  id="registType" name="registType">
                    <option value="">---请选择---</option>
                </select></fieldset>
            </div>
            <label for="registTime" class="col-sm-2 control-label">登记注册时间<span class="text-danger">(*)</span>：</label>
            <div class="col-sm-4">
                <div id="datetimepicker1" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" type="text" id="registTime" name="registTime" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="affiliation" class="col-sm-2 control-label">隶属关系：</label>
            <div class="col-sm-4">
                <fieldset class="fieldset">
                <select class="form-control needshow"  id="affiliation" name="affiliation">
                    <option value="">---请选择---</option>
                </select></fieldset>
            </div>
            <label for="scale" class="col-sm-2 control-label">排污单位规模：</label>
            <div class="col-sm-4">
                <fieldset class="fieldset">
                <select class="form-control needshow"  id="scale" name="scale">
                    <option value="">---请选择---</option>
                </select></fieldset>
            </div>
        </div>
        <div class="form-group">
            <label for="industryType" class="col-sm-2 control-label">行业类别：</label>
            <div class="col-sm-4">
                <div class="input-group">
                    <input type="text" class="form-control" id="industryType" name="industryType" readonly>
					<span class="input-group-btn">
						<button class="btn btn-default formBtn" type="button" data-toggle="modal" data-target="#industryTypeModal">
                            选择
                        </button>
					</span>
                </div>
            </div>
            <label class="col-sm-2 control-label"></label>
            <div class="col-sm-4">

            </div>
        </div>
        <div class="form-group">
            <label for="area" class="col-sm-2 control-label">行政区：</label>
            <div class="col-sm-4">
                <input type="text" class="form-control needshow" id="area" name="area">
                <%--<div class="input-group">
                    <input type="text" class="form-control needshow" id="area" name="area">
					<span class="input-group-btn">
						<button class="btn btn-default formBtn" type="button" data-toggle="modal" data-target="#areaModal">
                            选择
                        </button>
					</span>
                </div>--%>
            </div>
            <label for="industrialPark" class="col-sm-2 control-label">所在工业园区名称：</label>
            <div class="col-sm-4">
                <fieldset class="fieldset">
                <select class="form-control needshow"  id="industrialPark" name="industrialPark">
                    <option value="">---请选择---</option>
                </select></fieldset>
            </div>
        </div>
        <div class="form-group">
            <label for="valley" class="col-sm-2 control-label">所属流域：</label>
            <div class="col-sm-4">
                <div class="input-group">
                    <input type="text" class="form-control" id="valley" name="valley" readonly>
					<span class="input-group-btn">
						<button class="btn btn-default formBtn" type="button" data-toggle="modal" data-target="#valleyModal">
                            选择
                        </button>
					</span>
                </div>
            </div>
            <label class="col-sm-2 control-label"></label>
            <div class="col-sm-4">

            </div>
        </div>
        <div class="form-group">
            <label for="openDate" class="col-sm-2 control-label">建成时间（开业时间）：</label>
            <div class="col-sm-4">
                <div id="datetimepicker2" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="openDate" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" type="text" id="openDate" name="openDate" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
            <label for="extensionDate" class="col-sm-2 control-label">最近扩建时间：</label>
            <div class="col-sm-4">
                <div id="datetimepicker3" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="extensionDate" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" type="text" id="extensionDate" name="extensionDate" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="planeMap" class="col-sm-2 control-label">平面图：</label>
            <div class="col-sm-10">
                <input type="hidden" id="planeMap" name="planeMap" class="form-control">
                <button type="button" id="lookPlaneMapBtn" class="btn btn-info" style="display: none" onclick="lookPlaneMap()">查看平面图</button>
                <%--<jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>--%>
                <div id="fine-uploader-planemap"></div>
            </div>
        </div>
        <div class="form-group">
            <label for="attachmentId" class="col-sm-2 control-label">附件区：</label>
            <div class="col-sm-10">
                <input type="hidden" id="attachmentId" name="attachmentId" class="form-control">
                <input type="hidden" id="removeId" name="removeId" class="form-control">
                <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                <div id="fine-uploader-gallery"></div>
            </div>
        </div>
        <div class="form-group">
            <label for="orgInfo" class="col-sm-2 control-label">排污单位介绍：</label>
            <div class="col-sm-10">
                <textarea class="form-control needshow" id="orgInfo" name="orgInfo" rows="3"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="envDesc" class="col-sm-2 control-label">周边环境敏感点：</label>
            <div class="col-sm-10">
                <textarea class="form-control needshow" id="envDesc" name="envDesc" rows="3"></textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-3"></div>
            <div class="col-sm-2">
                <button id="saveForm" type="button" class="btn btn-success addBtn" style="display: none">保存</button>
                <button id="editForm" type="button" class="btn btn-success editBtn" style="display: none">保存</button>
                <button id="toEditForm" type="button" class="btn btn-success lookBtn" style="display: none">编辑</button>
            </div>
            <div class="col-sm-2">
                <button id="resetAddForm" type="button" class="btn btn-default addBtn" style="display: none">置空</button>
                <button id="resetEditForm" type="button" class="btn btn-default editBtn" style="display: none">置空</button>
            </div>
            <div class="col-sm-2">
                <button id="cancelAddForm" type="button" class="btn btn-warning addBtn" style="display: none">取消</button>
                <button id="cancelEditForm" type="button" class="btn btn-warning editBtn" style="display: none">取消</button>
                <button id="backList" type="button" class="btn btn-warning lookBtn" style="display: none">返回</button>
            </div>
            <div class="col-sm-3"></div>
        </div>
        </fieldset>
    </form>
</div>

<%----------------------------------------------------弹出框区域---------------------------------------------------------------------%>
<!-- 行业类别模态框 start -->
<div class="modal fade" id="industryTypeModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
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
                <button id="industryTypeModalClose" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="industryTypeModalSure" type="button" class="btn btn-primary">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 行业类别模态框 end -->
<!-- 行政区模态框 start -->
<div class="modal fade" id="areaModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="areaModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h5 class="modal-title" id="areaModalLabel">
                    行政区
                </h5>
            </div>
            <div class="modal-body">
                <div class="Node-frame-menubar">
                    <div class="scrollContent" >
                        <ul id="areaTree" class="ztree"></ul>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="areaModalClose" type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button id="areaModalSure" type="button" class="btn btn-primary">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 行政区模态框 end -->
<!-- 所属流域模态框 start -->
<div class="modal fade" id="valleyModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="valleyModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h5 class="modal-title" id="valleyModalLabel">
                    所属流域
                </h5>
            </div>
            <div class="modal-body">
                <div class="Node-frame-menubar">
                    <div class="scrollContent" >
                        <ul id="valleyTree" class="ztree"></ul>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="valleyModalClose" type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button id="valleyModalSure" type="button" class="btn btn-primary">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 所属流域模态框 end -->
<%@include file="/common/gis/map_mark.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/enterprise/basicInfo/scripts/enterpriseInfo.js"></script>
</body>
</html>
