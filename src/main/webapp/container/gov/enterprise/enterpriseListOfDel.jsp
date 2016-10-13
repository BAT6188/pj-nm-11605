<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/common/common_include.jsp" flush="true"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8">
    <title>删除排污单位列表</title>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <div class="dealBox">
                <form class="form-horizontal" role="form" id="searchform">
                    <div class="form-group">
                        <label for="name" class="col-sm-1 control-label">单位名称：</label>
                        <div class="col-sm-4">
                            <input type="text" id="name" name="name" class="form-control" />
                        </div>
                        <label for="pollutantType" class="col-sm-2 control-label">单位类型：</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="pollutantType" name="pollutantType">
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

                    <div class="form-group">
                        <label for="orgCode" class="col-sm-1 control-label">组织机构代码：</label>
                        <div class="col-sm-4">
                            <input type="text" id="orgCode" name="orgCode" class="form-control" />
                        </div>
                        <label for="superviseType" class="col-sm-2 control-label">排污单位监管类型：</label>
                        <div class="col-sm-4">
                            <select style="width: 100%" class="form-control"  id="superviseType" name="superviseType">
                                <option value="">全部</option>
                                <option value="01">重点排污单位</option>
                                <option value="02">一般排污单位</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="area" class="col-sm-1 control-label">所属行政区：</label>
                        <div class="col-sm-4">
                            <input type="text" id="area" name="area" class="form-control" />
                        </div>
                        <label for="isSpecial" class="col-sm-2 control-label">特殊监管排污单位：</label>
                        <div class="col-sm-4">
                            <select style="width: 100%" class="form-control"  id="isSpecial" name="isSpecial">
                                <option value="">全部</option>
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-9">

                        </div>
                        <div class="col-sm-1" style="text-align: right;">
                            <button id="search" type="button" class="btn btn-success" >查询</button>
                        </div>
                        <div class="col-sm-1" style="text-align: right;">
                            <button id="searchFix" type="button" class="btn btn-default" >重置</button>
                        </div>
                    </div>
                </form>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/enterprise/scripts/enterpriseListOfDel.js"></script>
</body>
</html>
