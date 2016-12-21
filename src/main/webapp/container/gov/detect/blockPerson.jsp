<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>人员信息管理</title>
    <%--<link href="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/metrStyle-cd/metroStyle.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/jquery.ztree.all.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/slimScroll/jquery.slimscroll.js"></script>--%>
    <style>
        .nav-tabs li a{
            font-size: 15px;
            color: #0a36e9;
        }
        .active a{
            color: black;
        }
    </style>
    <script>
        $('.shouldSet').attr('style','max-height: '+pageUtils.getFormHeight()+'px;overflow-y: auto;overflow-x: hidden;padding:10px;');
    </script>
</head>
<body>
<div class="wrap">
    <div class="menu-left left">
        <div id="myTabContent" class="tab-content" style="margin-left: 10px;">
            <div id="blockScrollContent" class="scrollContent">
                <ul id="blockZtree" class="ztree"></ul>
            </div>
        </div>
    </div>
    <div class="main-right right level3MenuContent">
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
                            <form class="form-inline" id="searchform">
                                <div class="form-group">
                                    <input type="hidden" id="s_blockName" name="blockName" class="form-control hidden"/>
                                    <input type="hidden" id="s_orgId" name="orgId" class="form-control hidden" />
                                    <input type="hidden" id="s_blockLevelId" name="blockLevelId" class="form-control hidden" />
                                    <input type="hidden" id="s_blockId" name="blockId" class="form-control hidden" />
                                    <label for="s_name">姓名：</label> <input type="text" id="s_name" name="name" class="form-control" />
                                    <label for="s_department">部门：</label> <input type="text" id="s_department" name="department" class="form-control" />
                                </div>
                                <br/><br>
                                <div class="form-group">
                                    <label for="s_position">职务：</label><input type="text" id="s_position" name="position" class="form-control" />

                                </div>
                            </form>
                        </div>
                        <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                        <button id="reset" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>

                        <br/><br>
                        <p class="btnListP">
                            <button id="add" type="button" class="btn btn-sm btn-success blockBtn" data-toggle="modal" data-target="#scfForm">
                                <i class="btnIcon add-icon"></i><span>新建</span>
                            </button>
                            <button id="update" type="button" class="btn btn-sm btn-warning blockBtn" data-toggle="modal" data-target="#scfForm">
                                <i class="btnIcon edit-icon"></i><span>修改</span>
                            </button>
                            <%--<button id="refPerson" type="button" class="btn btn-sm btn-info blockBtn">
                                <i class="btnIcon edit-icon"></i><span>关联</span>
                            </button>--%>
                            <button id="remove" type="button" class="btn btn-sm btn-danger orgBtn">
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
        <div class="modal fade" data-backdrop="static" id="scfForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width: 800px">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title form-title">添加人员信息</h4>
                    </div>
                    <div class="modal-body shouldSet">
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">姓名<span class="text-danger">*</span>：</label>
                                <div class="col-sm-4">
                                    <input type="hidden" id="id" name="id" class="form-control">
                                    <input type="hidden" id="type" name="type" class="form-control">
                                    <input type="hidden" id="orgId" name="orgId" class="form-control">
                                    <input type="hidden" id="apportalUserId" name="apportalUserId" class="form-control">
                                    <input type="hidden" id="removeId" name="removeId" class="form-control">
                                    <input type="hidden" id="blockLevelId" name="blockLevelId" class="form-control">
                                    <input type="hidden" id="blockId" name="blockId" class="form-control"/>
                                    <input type="text" id="name" name="name" class="form-control"
                                           data-message="姓名不能为空"
                                           data-easytip="position:top;class:easy-red;"
                                    />
                                </div>


                                <label for="position" class="col-sm-2 control-label">网格职务：<span class="text-danger">*</span>：</label>
                                <div class="col-sm-4">
                                    <select  id="position" name="position" class="form-control">
                                        <option value="1">责任领导</option>
                                        <option value="2">分管领导</option>
                                        <option value="3">主要负责人</option>
                                        <option value="4">直接负责人</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="phone" class="col-sm-2 control-label">手机号码<span class="text-danger">*</span>：</label>
                                <div class="col-sm-4">
                                    <input type="text" id="phone" name="phone" class="form-control"
                                           data-message="单位地址不能为空"
                                           data-easytip="position:top;class:easy-red;"
                                    />
                                </div>

                                <label for="address" class="col-sm-2 control-label">单位地址：<span class="text-danger">*</span>：</label>
                                <div class="col-sm-4">
                                    <input type="address" id="address" name="address" class="form-control"
                                           data-message="单位地址不能为空"
                                           data-easytip="position:top;class:easy-red;"
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="blockId" class="col-sm-2 control-label">所属网格:<span class="text-danger">*</span>：</label>
                                <div class="col-sm-4">
                                    <input type="text" id="blockName" name="blockName" readonly class="form-control"
                                           data-message="单位地址不能为空"
                                           data-easytip="position:top;class:easy-red;"
                                    />
                                </div>
                                <label for="sort" class="col-sm-2 control-label">排序:<span class="text-danger">*</span>：</label>
                                <div class="col-sm-4">
                                    <input type="text" id="sort" name="sort" class="form-control"
                                           data-message="单位地址不能为空"
                                           data-easytip="position:top;class:easy-red;"
                                    />
                                </div>

                            </div>
                            <div class="form-group blockBtn">
                                <label for="apportalUserName" class="col-sm-2 control-label">关联系统用户：</label>
                                <div class="col-sm-10">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="apportalUserName" name="apportalUserName" readonly/>
                                        <span class="input-group-btn">
                                            <button class="btn btn-default formBtn" type="button" id="refPerson">
                                                选择
                                            </button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="blockDuty" class="col-sm-2 control-label">职责：<span class="text-danger">*</span>：</label>
                                <div class="col-sm-10">
                                    <textarea  id="blockDuty" name="blockDuty" class="form-control" rows="5"
                                       data-message="职责不能为空"
                                       data-easytip="position:top;class:easy-red;"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="attachment" class="col-sm-2 control-label">附件：</label>
                                <div class="col-sm-10">
                                    <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                                    <div id="fine-uploader-gallery"></div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="save">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/detect/scripts/blockPerson.js"></script>
</body>
</html>
