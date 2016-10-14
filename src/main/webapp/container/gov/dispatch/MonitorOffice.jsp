<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>监察大队</title>
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
                    <p>
                        <label for="connTime">接电时间：</label> <input type="text" id="connTime" class="form-control" />
                        <label for="enterpriseName">投诉对象：</label> <input type="text" id="enterpriseName" class="form-control" />
                        <label for="source">信息来源：</label> <input type="text" id="source" class="form-control" />
                    </p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#eventMsg">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#eventMsg">
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

<!--事件信息-->
<div class="modal fade" id="eventMsg" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog" style="width:800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="demoFormTitle">事件信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="eventTime" class="col-sm-2 control-label">接电时间：</label>
                        <div class="col-sm-4">
                            <input type="text" id="eventTime" name="eventTime" class="form-control"
                            />
                        </div>

                        <label for="answer" class="col-sm-2 control-label">接电人：</label>
                        <div class="col-sm-4">
                            <input type="text" id="answer" name="answer" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">投诉对象：</label>
                        <div class="col-sm-4">
                            <input type="text" id="enterpriseName" name="enterpriseName" class="form-control"  disabled
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>

                        <label for="source" class="col-sm-2 control-label">信息来源：</label>
                        <div class="col-sm-4">
                            <input type="text" id="source" class="form-control"  disabled
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="blockLevelName" class="col-sm-2 control-label">所属网格：</label>
                        <div class="col-sm-4">
                            <input type="text" id="blockLevelName" class="form-control"  disabled
                            />
                        </div>

                        <label for="blockName" class="col-sm-2 control-label"></label>
                        <div class="col-sm-4">
                            <input type="text" id="blockName" class="form-control" disabled
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="supervisor" class="col-sm-2 control-label">企业负责人：</label>
                        <div class="col-sm-4">
                            <input type="text" id="supervisor" class="form-control" disabled
                            />
                        </div>

                        <label for="supervisorPhone" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" id="supervisorPhone" class="form-control" disabled
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">来电详情：</label>
                        <div class="col-sm-10">
                            <textarea id="content" name="content" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <hr/>

                    <div class="form-group">
                        <label for="senderName" class="col-sm-2 control-label">处理人：</label>
                        <div class="col-sm-4">
                            <input type="text" id="senderName" class="form-control" disabled
                            />
                        </div>

                        <label for="" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" class="form-control"
                            />
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
                <button type="button" class="btn btn-primary" id="smsSend" data-toggle="modal" data-target="#smsSendForm">短信发送</button>
                <button type="button" class="btn btn-primary" id="save" >发送</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!--人员选择-->
<div class="modal fade" id="selectPeopleForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true" >
    <div class="modal-dialog" style="width:882px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="demoFormTitle2">人员选择</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="Node-frame-menubar">
                            <div class="scrollContent" >
                                <ul id="treeDemo1" class="ztree"></ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-9">
                        <div class="mainBox">
                            <div class="tableBox">
                                <table id="selectPeopleTable" class="table table-striped table-responsive">
                                </table>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
            <div class="modal-footer" style="clear: both;">
                <button type="button" class="btn btn-primary" id="sendTo" data-toggle="modal" data-target="#selectPeopleForm,#systemSendForm">发送</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!--查看 状态跟踪-->
<div class="modal fade" id="officeForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加固体废物治理设施</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">设施名称：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="设施名称不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/dispatch/scripts/monitorOffice.js"></script>
</body>
</html>
