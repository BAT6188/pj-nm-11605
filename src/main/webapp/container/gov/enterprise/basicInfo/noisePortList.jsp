<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%
        String enterpriseId=request.getParameter("id");
    %>
    <title>噪声源</title>
    <script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
    <script type="text/javascript">
        var enterpriseId='<%=enterpriseId%>';
    </script>
</head>
<body>
<div class="content content1 clearfix">
    <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;">噪声源排口列表</a>
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
                    <form class="form-horizontal" role="form" id="searchform">
                        <label for="number">噪声源编号：</label> <input type="text" id="portNumber" name="number" class="form-control" />
                        <label for="name">噪声源名称：</label> <input type="text" id="portName" name="name" class="form-control" />
                    </form>
                    </p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#noiseForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#noiseForm">
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
<div class="modal fade" id="noiseForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="noiseModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 900px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加噪声源</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="number" class="col-sm-2 control-label">噪声源编号<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id" class="form-control">
                            <input type="hidden" id="createTime" name="createTime" class="form-control">
                            <input type="text" id="number" name="number" class="form-control"
                                   data-message="噪声源编号不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="name" class="col-sm-2 control-label">噪声源名称<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="噪声源名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="position" class="col-sm-2 control-label">噪声源位置：</label>
                        <div class="col-sm-4">
                            <input type="text" id="position" name="position" class="form-control">
                        </div>
                        <label for="noiseType" class="col-sm-2 control-label">噪声源类型：</label>
                        <div class="col-sm-4">
                            <select id="noiseType" name="noiseType" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dischargeStandard" class="col-sm-2 control-label">排放标准：</label>
                        <div class="col-sm-4">
                            <select id="noiseDischargeStandard" name="dischargeStandard" class="form-control">
                            </select>
                        </div>
                        <label for="monitorFrequency" class="col-sm-2 control-label">监测频次：</label>
                        <div class="col-sm-4">
                            <select id="monitorFrequency" name="monitorFrequency" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="fnType" class="col-sm-2 control-label">功能区类别：</label>
                        <div class="col-sm-4">
                            <select id="noiseFnType" name="fnType" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dayMax" class="col-sm-2 control-label">昼间标准值<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="dayMax" name="dayMax" class="form-control"
                                   data-easyform="number;"
                                   data-message="昼间标准值不能为空、必须为数字类型"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="nightMax" class="col-sm-2 control-label">夜间标准值<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="nightMax" name="nightMax" class="form-control"
                                   data-easyform="number;"
                                   data-message="夜间标准值不能为空、必须为数字类型"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isLeqdb" class="col-sm-2 control-label">监测Leq<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isLeqdb">
                            <label class="checkbox-inline"><input type="radio" name="isLeqdb" id="isLeqdb1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isLeqdb" id="isLeqdb0" value="0">否</label>
                        </div>
                        <label for="isSd" class="col-sm-2 control-label">监测sd<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isSd">
                            <label class="checkbox-inline"><input type="radio" name="isSd" id="isSd1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isSd" id="isSd0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isLmax" class="col-sm-2 control-label">监测Lmax<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isLmax">
                            <label class="checkbox-inline"><input type="radio" name="isLmax" id="isLmax1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isLmax" id="isLmax0" value="0">否</label>
                        </div>
                        <label for="isLmin" class="col-sm-2 control-label">监测Lmin<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isLmin">
                            <label class="checkbox-inline"><input type="radio" name="isLmin" id="isLmin1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isLmin" id="isLmin0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isLFive" class="col-sm-2 control-label">监测L5<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isLFive">
                            <label class="checkbox-inline"><input type="radio" name="isLFive" id="isLFive1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isLFive" id="isLFive0" value="0">否</label>
                        </div>
                        <label for="isLTen" class="col-sm-2 control-label">监测L10<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isLTen">
                            <label class="checkbox-inline"><input type="radio" name="isLTen" id="isLTen1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isLTen" id="isLTen0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isLFifty" class="col-sm-2 control-label">监测L50<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isLFifty">
                            <label class="checkbox-inline"><input type="radio" name="isLFifty" id="isLFifty1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isLFifty" id="isLFifty0" value="0">否</label>
                        </div>
                        <label for="isLNinety" class="col-sm-2 control-label">监测L90<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isLNinety">
                            <label class="checkbox-inline"><input type="radio" name="isLNinety" id="isLNinety1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isLNinety" id="isLNinety0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isLNinetyFive" class="col-sm-2 control-label">监测L95<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isLNinetyFive">
                            <label class="checkbox-inline"><input type="radio" name="isLNinetyFive" id="isLNinetyFive1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isLNinetyFive" id="isLNinetyFive0" value="0">否</label>
                        </div>
                        <label for="isLe" class="col-sm-2 control-label">监测Le<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isLe">
                            <label class="checkbox-inline"><input type="radio" name="isLe" id="isLe1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isLe" id="isLe0" value="0">否</label>
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
<script src="<%=request.getContextPath()%>/container/gov/enterprise/basicInfo/scripts/noisePortList.js"></script>
<script>
    $(function(){
        initSelect();
        initZTree();
    });
    /*初始化选择菜单*/
    function initSelect(){
        /*数据字典*/
        var dictData = dict.getDctionnary({code:['noiseType','noiseDischargeStandard','noiseFnType','monitorFrequency']});
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
