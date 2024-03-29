<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%--<jsp:include page="/common/common_include.jsp" flush="true"/>--%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8">
    <title>排污单位列表</title>
    <script src="<%=request.getContextPath()%>/common/scripts/jquery.form.js"></script>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;font-size: 20px;">排污档案列表</a>
            <div class="dealBox">
                <div class="sideTitle left">
                        <span class="blueMsg">
                            <img class="tipImg" src="<%=request.getContextPath()%>/common/images/searchTip.png" alt=""/>
                            <span class="text">查询</span>
                        </span>
                </div>
                <div class="queryBox marginLeft0">
                    <form role="form" id="searchform">
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="name">&nbsp;&nbsp;单位名称：</label><input type="text" id="name" name="name" class="form-control" />
                                <label for="pollutantType" class="labelMarginLeft">&nbsp;&nbsp;&nbsp;&nbsp;单位类型：</label>
                                <select style="width: 300px;" class="form-control" id="pollutantType" name="pollutantType">
                                    <option value="">全部</option>
                                    <option value="01">废水</option>
                                    <option value="02">废气</option>
                                    <option value="03">污水处理厂</option>
                                    <option value="04">重金属</option>
                                    <option value="05">畜禽养殖</option>
                                    <option value="06">固废</option>
                                    <option value="07">危险废物</option>
                                    <option value="08">省级实验室</option>
                                    <option value="09">二级以上医院</option>
                                    <option value="10">其他</option>
                                </select>
                            </div>
                        </div>
                        <p></p>
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="orgCode">组织机构代码：</label><input type="text" id="orgCode" name="orgCode" class="form-control" />
                                <label for="superviseType" class="labelMarginLeft">排污单位监管类型：</label>
                                <select style="width: 300px;" class="form-control"  id="superviseType" name="superviseType">
                                    <option value="">全部</option>
                                    <option value="01">重点排污单位</option>
                                    <option value="02">一般排污单位</option>
                                </select>
                            </div>
                        </div>
                        <p></p>
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="area">&nbsp;所属行政区：</label><input type="text" id="area" name="area" class="form-control" />
                                <label for="isSpecial" class="labelMarginLeft">特殊监管排污单位：</label>
                                <select style="width: 300px;" class="form-control"  id="isSpecial" name="isSpecial">
                                    <option value="">全部</option>
                                    <option value="1">是</option>
                                    <option value="0">否</option>
                                </select>
                            </div>
                        </div>
                    </form>
                    <p></p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="resetSearch" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success">
                        <i class="btnIcon add-icon"></i><span>新增</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning">
                        <i class="btnIcon edit-icon"></i><span>修改</span>
                    </button>
                    <button id="remove" type="button" class="btn btn-sm btn-danger">
                        <i class="btnIcon delf-icon"></i><span>删除</span>
                    </button>
                    <button id="export" type="button" class="btn btn-sm btn-success" >
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
<div class="modal fade" id="addNewEnterpriseForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 60%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #0d8ddb">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h5 class="modal-title form-title">新增企业信息</h5>
            </div>
            <div class="modal-body">

            </div>
            <div class="modal-footer">
                <button id="saveForm" type="button" class="btn btn-success addBtn">保存</button>
                <button id="resetAddForm" type="button" class="btn btn-default addBtn">置空</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--删除表单-->
<div class="modal fade" id="delEnterpriseModal" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #0d8ddb">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h5 class="modal-title form-title">删除原因及意见</h5>
            </div>
            <div class="modal-body">
                <form id="deleteEnterpriseForm" class="form-horizontal" role="form">
                    <input type="hidden" id="enterpriseId" name="id" value=""/>
                    <textarea class="form-control" id="delOpinion" name="delOpinion" rows="5"></textarea>
                </form>
            </div>
            <div class="modal-footer">
                <button id="makeSureDel" type="button" class="btn btn-success addBtn">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/company/companyAccount/scripts/enterpriseListOfRun.js"></script>
<script>
    $(function(){
        //pageUtils.loadPageOfContent('.modal-body',rootPath+'/container/gov/enterprise/basicInfo/enterpriseInfo.jsp?handleType=add')
    })
</script>
</body>
</html>
