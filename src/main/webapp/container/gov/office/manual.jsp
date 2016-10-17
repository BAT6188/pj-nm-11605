<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/common_include.jsp"%>

    <title>环保手册</title>
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
                        <label for="s_fileName">文件名称：</label> <input type="text" id="s_fileName" class="form-control" />
                        <label for="s_type">公文类型：</label> <select style="width: 100%" class="form-control"  id="s_type" name="s_type">
                        <option value="1">法律法规</option>
                        <option value="2">行业标准</option>
                        <option value="3">监察指南</option>
                        <option value="4">知识案例</option>
                    </select>
                    </p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#scfForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#scfForm">
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
<div class="modal fade" data-backdrop="static" id="scfForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加环保手册</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="fileName" class="col-sm-2 control-label">文件名称*：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="text" id="fileName" name="fileName" class="form-control"
                                   data-message="文件名称不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="createTime" class="col-sm-2 control-label">创建时间*：</label>
                        <div class="col-sm-4">
                            <div id="createTimeContent" class="input-group date form_date" data-date="" data-link-field="createTime" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                <input class="form-control" id="createTime" name="createTime" size="16" type="text" value="" readonly
                                       data-message="创建时间不能为空"
                                       data-easytip="position:top;class:easy-red;">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>

                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-2 control-label">类型*：</label>
                        <div class="col-sm-4">
                            <select style="width: 100%" class="form-control"  id="type" name="type" data-message="类型不能为空"
                                    data-easytip="position:top;class:easy-red;">
                                <option value="1">法律法规</option>
                                <option value="2">行业标准</option>
                                <option value="3">监察指南</option>
                                <option value="4">知识案例</option>
                            </select>
                        </div>
                        <label for="level" class="col-sm-2 control-label">级别*：</label>
                        <div class="col-sm-4">
                            <select style="width: 100%" class="form-control"  id="level" name="level" data-message="类型不能为空"
                                    data-easytip="position:top;class:easy-red;">
                                <option value="1">一级</option>
                                <option value="2">二级</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enactOrgName" class="col-sm-2 control-label">制订单位*：</label>
                        <div class="col-sm-4">
                            <input type="text" id="enactOrgName" name="enactOrgName" class="form-control"
                                   data-message="制订单位不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="pubTime" class="col-sm-2 control-label">发布时间*：</label>
                        <div class="col-sm-4">
                            <div id="pubTimeContent" class="input-group date form_date" data-date="" data-link-field="pubTime" data-date-format="yyyy-mm-dd" data-link-format="yyyy-mm-dd">
                                <input class="form-control" id="pubTime" name="pubTime" size="16" type="text" value="" readonly
                                       data-message="发布时间不能为空"
                                       data-easytip="position:top;class:easy-red;">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>

                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">内容摘要*：</label>
                        <div class="col-sm-10">
                            <textarea  id="content" name="content" class="form-control" rows="5"
                                       data-message="内容摘要不能为空"
                                       data-easytip="position:top;class:easy-red;"
                            ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="fitRange" class="col-sm-2 control-label">适用范围*：</label>
                        <div class="col-sm-10">
                            <textarea  id="fitRange" name="fitRange" class="form-control" rows="5"
                                       data-message="适用范围不能为空"
                                       data-easytip="position:top;class:easy-red;"
                            ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-2 control-label">备注*：</label>
                        <div class="col-sm-10">
                            <textarea  id="remark" name="remark" class="form-control" rows="5"
                                       data-message="备注不能为空"
                                       data-easytip="position:top;class:easy-red;"
                            ></textarea>
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
<script src="<%=request.getContextPath()%>/container/gov/office/scripts/manual.js"></script>
</body>
</html>
