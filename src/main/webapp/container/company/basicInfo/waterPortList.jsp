<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>废水排口</title>
    <script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
    <link href="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/metrStyle-cd/metroStyle.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/jquery.ztree.all.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/slimScroll/jquery.slimscroll.js"></script>
    <script type="text/javascript">
        var enterpriseId=enterpriseData.id;
    </script>
</head>
<body>
<div class="content content1 clearfix">
    <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;">废水排口列表</a>
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
                        <label for="number">排口编号：</label> <input type="text" id="portNumber" name="number" class="form-control" />
                        <label for="name">排口名称：</label> <input type="text" id="portName" name="name" class="form-control" />
                    </form>
                    </p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#waterForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#waterForm">
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
<div class="modal fade" id="waterForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="waterModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 930px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加废水排口</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="number" class="col-sm-2 control-label">排口编号<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id" class="form-control">
                            <input type="hidden" id="enterpriseId" name="enterpriseId" class="form-control">
                            <input type="hidden" id="createTime" name="createTime" class="form-control">
                            <input type="text" id="number" name="number" class="form-control"
                                   data-message="排口编号不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="name" class="col-sm-2 control-label">排口名称<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="设施名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="position" class="col-sm-2 control-label">排口位置：</label>
                        <div class="col-sm-4">
                            <input type="text" id="position" name="position" class="form-control">
                        </div>
                        <label for="monitorType" class="col-sm-2 control-label">监测类型：</label>
                        <div class="col-sm-4">
                            <select id="monitorType" name="monitorType" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="longitude" class="col-sm-2 control-label">经度：</label>
                        <div class="col-sm-4">
                            <input type="text" id="longitude" name="longitude" class="form-control" readonly >
                        </div>
                        <label for="latitude" class="col-sm-2 control-label">纬度：</label>
                        <div class="col-sm-4">
                            <div class="input-group">
                                <input type="text" class="form-control" id="latitude" name="latitude" readonly>
                                <span class="input-group-btn">
                                    <button class="btn btn-default formBtn" type="button" id="mapMarkBtn">
                                        标注
                                    </button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dischargeStandard" class="col-sm-2 control-label">排放标准：</label>
                        <div class="col-sm-4">
                            <select id="waterDischargeStandard" name="dischargeStandard" class="form-control">
                            </select>
                        </div>
                        <label for="dischargeDirection" class="col-sm-2 control-label">排放去向：</label>
                        <div class="col-sm-4">
                            <select id="waterDischargeDirection" name="dischargeDirection" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="valley" class="col-sm-2 control-label">受纳水体：</label>
                        <div class="col-sm-4">
                            <div class="input-group">
                                <input type="text" class="form-control" id="valley" name="valley" readonly >
                                <span class="input-group-btn">
                                    <button class="btn btn-default formBtn" type="button" data-toggle="modal" data-target="#valleyModal">
                                        选择
                                    </button>
                                </span>
                            </div>
                        </div>
                        <label for="valleyFnType" class="col-sm-2 control-label">功能区类别：</label>
                        <div class="col-sm-4">
                            <select id="valleyFnType" name="valleyFnType" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="singleInstallType" class="col-sm-2 control-label">标志牌安装形式：</label>
                        <div class="col-sm-4">
                            <select id="singleInstallType" name="singleInstallType" class="form-control">
                                <option value="平提">平提</option>
                                <option value="立提">立提</option>
                                <option value="立警">立警</option>
                                <option value="平警">平警</option>
                            </select>
                        </div>
                        <label for="dischargeMode" class="col-sm-2 control-label">排放规律：</label>
                        <div class="col-sm-4">
                            <select id="dischargeMode" name="dischargeMode" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="status" class="col-sm-2 control-label">状态<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="status">
                            <label class="checkbox-inline">
                                <input type="radio" name="status" id="status1" value="1" data-easytip="class:easy-red;" data-message="请选择废水排口状态">在用
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="status" id="status0" value="0">停用
                            </label>
                        </div>
                        <label for="isWaterIntake" class="col-sm-2 control-label">是否进水口<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isWaterIntake">
                            <label class="checkbox-inline">
                                <input type="radio" name="isWaterIntake" id="isWaterIntake1" value="1" data-easytip="class:easy-red;" data-message="请选择是否为进水口">是
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="isWaterIntake" id="isWaterIntake0" value="0">否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-2 control-label">备注：</label>
                        <div class="col-sm-10">
                            <textarea class="form-control needshow" id="remark" name="remark" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isFlow" class="col-sm-2 control-label">监测流量<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isFlow">
                            <label class="checkbox-inline"><input type="radio" name="isFlow" id="isFlow1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isFlow" id="isFlow0" value="0">否</label>
                        </div>
                        <label for="isOxygen" class="col-sm-2 control-label">监测化学需氧量<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isOxygen">
                            <label class="checkbox-inline"><input type="radio" name="isOxygen" id="isOxygen1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isOxygen" id="isOxygen0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isNitrogen" class="col-sm-2 control-label">监测氨氮<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isNitrogen">
                            <label class="checkbox-inline"><input type="radio" name="isNitrogen" id="isNitrogen1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isNitrogen" id="isNitrogen0" value="0">否</label>
                        </div>
                        <label for="isPh" class="col-sm-2 control-label">监测氧含量<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isPh">
                            <label class="checkbox-inline"><input type="radio" name="isPh" id="isPh1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测该项">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isPh" id="isPh0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="planeMap" class="col-sm-2 control-label">平面图<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4" id="planeMap">
                            <input type="hidden" id="planeMapMark" name="planeMapMark" class="form-control">
                            <button type="button" class="btn btn-info lookBtn" style="display: none" onclick="lookPlaneMap()">查看平面图标注</button>
                            <button type="button" class="btn btn-primary saveBtn" style="display: none" onclick="makePlaneMap()">平面图标注</button>
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
<!-- 所属流域模态框 start -->
<div class="modal fade" id="valleyModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="valleyModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h5 class="modal-title" id="valleyModalLabel">
                    受纳水体
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
<script src="<%=request.getContextPath()%>/container/company/basicInfo/scripts/waterPortList.js"></script>
<script>
    $(function(){
        initMapBtn();
        initSelect();
        initZTree();
    });
    /*初始化标注按钮*/
    function initMapBtn(){
        //绑定markDialog关闭事件
        MapMarkDialog.closed(function (mark) {
            if (mark) {
                $("#longitude").val(mark.x);
                $("#latitude").val(mark.y);
            }else{
                Ewin.alert({message:"请选择坐标"});
                return false;
            }
        });
        $('#mapMarkBtn').bind('click', function () {
            //设置标绘模式
            MapMarkDialog.setMode("point");
            MapMarkDialog.open();
        });
    }
    /*初始化选择菜单*/
    function initSelect(){
        /*数据字典*/
        var dictData = dict.getDctionnary({code:['waterDischargeStandard','waterDischargeDirection','valleyFnType','dischargeMode','monitorType']});
        $.each(dictData,function(k,v){
            var optionsHtml = '';
            $.each(v,function(i,obj){
                optionsHtml +='<option value="'+ obj.code+'">'+ obj.name+'</option>';
            })
            $('#'+k).append(optionsHtml);
        });
    }
    /*初始化 树结构*/
    $(".scrollContent").slimScroll({
        height:"100%",
        railOpacity:.9,
        alwaysVisible:!1
    });
    function initZTree(){
        var treeCode = ['valley'];
        $.each(treeCode,function(k,v){
            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "code",
                        pIdKey: "parentCode",
                        rootPId: -1
                    }
                },
                height:500,
                width:200,
                view: {
                    showLine: false
                },
                async: {
                    enable: true,
                    url:rootPath + "/S_dict_Dict_multipleList.action",
                    autoParam:["code"],
                    otherParam:{"code":v},
                    dataFilter: null
                },
                callback: {
                    onDblClick: function(event, treeId, treeNode){
                        if(treeNode.check_Child_State == -1){
                            $('#'+v).val(treeNode.name);
                            $('#'+v+'ModalClose').trigger('click');
                        }
                    }
                }
            };
            var zTree = $.fn.zTree.init($('#'+v+'Tree'), setting);
            $('#'+v+'ModalSure').click(function(){
                var nodes = zTree.getSelectedNodes();
                var selectNode = nodes[0];
                if(selectNode.check_Child_State == -1){
                    $('#'+v).val(selectNode.name);
                    $('#'+v+'ModalClose').trigger('click');
                }
            })
        })
    }
</script>
</body>
</html>
