<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%
        String enterpriseId=request.getParameter("id");
    %>
    <title>燃煤锅炉信息</title>
    <script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
    <script type="text/javascript">
        var enterpriseId='<%=enterpriseId%>';
    </script>
</head>
<body>
<div class="content content1 clearfix">
    <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;">燃煤锅炉信息列表</a>
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
                    <form role="form" id="searchform">
                    <div class="form-inline">
                        <div class="form-group">
                            <label for="portNumber">设备名称：</label> <input type="text" id="portNumber" name="name" class="form-control" />
                            <label for="time" class="labelMarginLeft">建成时间：</label>
                            <div id="startTimeDiv" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                <input class="form-control" size="16" type="text" id="startTime" name="startTime" value="" readonly placeholder="开始时间">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                            —
                            <div id="endTimeDiv" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                <input class="form-control" size="16" type="text" id="endTime" name="endTime" value="" readonly placeholder="结束时间">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    </form>
                    </p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#boilerForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#boilerForm">
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
<div class="modal fade" id="boilerForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="boilerModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 900px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加燃煤锅炉信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="model" class="col-sm-2 control-label">锅炉型号<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id" class="form-control">
                            <input type="hidden" id="createTime" name="createTime" class="form-control">
                            <input type="text" id="model" name="model" class="form-control"
                                   data-message="锅炉型号不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="name" class="col-sm-2 control-label">设备名称<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="设备名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="userName" class="col-sm-2 control-label">使用单位：</label>
                        <div class="col-sm-10">
                            <input type="text" id="userName" name="userName" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="userAddress" class="col-sm-2 control-label">使用单位地址：</label>
                        <div class="col-sm-10">
                            <input type="text" id="userAddress" name="userAddress" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="buildTime" class="col-sm-2 control-label">建成时间<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <div id="buildTimeDiv" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                <input class="form-control" size="16" type="text" id="buildTime" name="buildTime" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <label for="purpose" class="col-sm-2 control-label">锅炉用途：</label>
                        <div class="col-sm-4">
                            <input type="text" id="purpose" name="purpose" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isImportPosition" class="col-sm-2 control-label">是否位于地级及以上城区<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isImportPosition">
                            <label class="checkbox-inline">
                                <input type="radio" name="isImportPosition" id="isImportPosition1" value="1" data-message="请选择是否特殊监管对象">是
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="isImportPosition" id="isImportPosition0" value="0">否
                            </label>
                        </div>
                        <label for="scale" class="col-sm-2 control-label">锅炉规模：</label>
                        <div class="col-sm-4">
                            <input type="text" id="scale" name="scale" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dustType" class="col-sm-2 control-label">除尘方式：<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <select id="boilerDustType" name="dustType" class="form-control" >
                            </select>
                        </div>
                        <label for="sulfurType" class="col-sm-2 control-label">脱硫方式：</label>
                        <div class="col-sm-4">
                            <select id="boilerSulfurType" name="sulfurType" class="form-control" >
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="saltpetreType" class="col-sm-2 control-label">脱硝方式：<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <select id="boilerSaltpetreType" name="saltpetreType" class="form-control" >
                            </select>
                        </div>
                        <label for="fuelType" class="col-sm-2 control-label">燃料种类：</label>
                        <div class="col-sm-4">
                            <select id="boilerFuelType" name="fuelType" class="form-control" >
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="attachmentId" name="attachmentId" class="form-control">
                            <input type="hidden" id="removeId" name="removeId" class="form-control">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary saveBtn" id="save" style="display: none">保存</button>
                <button id="cancelBtn" type="button" class="btn btn-default saveBtn" data-dismiss="modal" style="display: none">取消</button>
                <button type="button" class="btn btn-default lookBtn" data-dismiss="modal" style="display: none">关闭</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/enterprise/proDeviceInfo/scripts/boilerList.js"></script>
<script>
    $(function(){
        initSelect();
        $('.form_date').datetimepicker({
            language:   'zh-CN',
            weekStart: 1,
            todayBtn:  1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });
    });
    /*初始化选择菜单*/
    function initSelect(){
        /*数据字典*/
        var dictData = dict.getDctionnary({code:['boilerDustType','boilerSulfurType','boilerSaltpetreType','boilerFuelType']});
        $.each(dictData,function(k,v){
            var optionsHtml = '';
            $.each(v,function(i,obj){
                optionsHtml +='<option value="'+ obj.code+'">'+ obj.name+'</option>';
            })
            $('#'+k).append(optionsHtml);
        });
    }
</script>
</body>
</html>
