<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%
        String enterpriseId=request.getParameter("id");
    %>
    <title>废气排口</title>
    <script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
    <script type="text/javascript">
        var enterpriseId='<%=enterpriseId%>';
        $(function(){
            initSelect();
        });
        /*初始化选择菜单*/
        function initSelect(){
            var dictData = dict.getDctionnary({code:['grasDischargeStandard','monitorType','dischargeMode']});
            /*数据字典*/
            $.each(dictData,function(k,v){
                var optionsHtml = '';
                $.each(v,function(i,obj){
                    optionsHtml +='<option value="'+ obj.code+'">'+ obj.name+'</option>';
                })
                $('#'+k).append(optionsHtml);
            });
        }
    </script>
</head>
<body>
<div class="content content1 clearfix">
    <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;">废气排口列表</a>
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
                    <form class="form-inline" role="form" id="searchform">
                        <label for="number">排口编号：</label> <input type="text" id="portNumber" name="number" class="form-control" />
                        <label for="name">排口名称：</label> <input type="text" id="portName" name="name" class="form-control" />
                    </form>
                    </p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#grasForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#grasForm">
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
<div class="modal fade" id="grasForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="grasModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 850px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加废气排口</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="number" class="col-sm-2 control-label">排口编号<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id" class="form-control">
                            <input type="hidden" id="createTime" name="createTime" class="form-control">
                            <input type="text" id="number" name="number" class="form-control"
                                   data-message="排口编号不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="name" class="col-sm-2 control-label">排口名称<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="排口名称不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="position" class="col-sm-2 control-label">排口位置：</label>
                        <div class="col-sm-4">
                            <input type="text" id="position" name="position" class="form-control">
                        </div>
                        <label for="dischargeMode" class="col-sm-2 control-label">排放方式：</label>
                        <div class="col-sm-4">
                            <select id="dischargeMode" name="dischargeMode" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dischargeDevice" class="col-sm-2 control-label">排放设备：</label>
                        <div class="col-sm-4">
                            <input type="text" id="dischargeDevice" name="dischargeDevice" class="form-control"/>
                        </div>
                        <label for="dischargeStandard" class="col-sm-2 control-label">排放标准：</label>
                        <div class="col-sm-4">
                            <select id="grasDischargeStandard" name="dischargeStandard" class="form-control" >
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="monitorType" class="col-sm-2 control-label">监测类型：</label>
                        <div class="col-sm-4">
                            <select id="monitorType" name="monitorType" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isNitrogen" class="col-sm-2 control-label">监测氮氧<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isNitrogen">
                            <label class="checkbox-inline"><input type="radio" name="isNitrogen" id="isNitrogen1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测氮氧">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isNitrogen" id="isNitrogen0" value="0">否</label>
                        </div>
                        <label for="isSulfur" class="col-sm-2 control-label">监测二氧化硫<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isSulfur">
                            <label class="checkbox-inline"><input type="radio" name="isSulfur" id="isSulfur1" value="1" data-easytip="class:easy-red;"  data-message="请选择是否监测二氧化硫">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isSulfur" id="isSulfur0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isGasFlow" class="col-sm-2 control-label">监测废气流量<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isGasFlow">
                            <label class="checkbox-inline"><input type="radio" name="isGasFlow" id="isGasFlow1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测废气流量">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isGasFlow" id="isGasFlow0" value="0">否</label>
                        </div>
                        <label for="isOxygen" class="col-sm-2 control-label">监测氧含量<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isOxygen">
                            <label class="checkbox-inline"><input type="radio" name="isOxygen" id="isOxygen1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测氧含量">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isOxygen" id="isOxygen0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isDust" class="col-sm-2 control-label">监测烟尘<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isDust">
                            <label class="checkbox-inline"><input type="radio" name="isDust" id="isDust1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测烟尘">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isDust" id="isDust0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="attachmentId" name="attachmentId" class="form-control" data-easyform="null;">
                            <input type="hidden" id="removeId" name="removeId" class="form-control" data-easyform="null;">
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
<script src="<%=request.getContextPath()%>/container/gov/enterprise/basicInfo/scripts/grasPortList.js"></script>
</body>
</html>
