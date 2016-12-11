<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String enterpriseId = request.getParameter("id");
%>
<script>
    var enterpriseId='<%=enterpriseId%>'

    $('.modal-body').attr('style','max-height: '+pageUtils.getFormHeight()+'px;overflow-y: auto;overflow-x: hidden;padding:10px;');
</script>
<!DOCTYPE html>
<html>
<head>
    <title>排污申报</title>
</head>
<style>
    .ui-autocomplete { z-index:2147483647; }

    #total{
        padding-left: 11%;
        margin-top: 1%;
    }

    .dealBox button {
        min-width: 0px;
    }
</style>
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
                        <form class="form-inline">
                            <div class="form-group">
                                <label for="">企业名称：</label> <input type="text" id="s_enterpriseName" name="enterpriseName" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="">是否缴费：</label>
                                <select id="s_paymentStatus" name="paymentStatus" class="form-control">
                                <option value="">全部</option>
                                <option value="0">未缴费</option>
                                <option value="1">已缴费</option>
                                <option value="2">未按时缴费</option>
                            </select>
                            </div>
                        </form>

                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>

                <div id="total" class="k-toolbar k-grid-toolbar">
                    <label for="">缴费日期：</label>
                    <table border="0px" cellspacing="1" cellpadding="0" overflow="hidden">
                        <div class="btn-group" id="dropdownMenu">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default" id="selYear">年份</button>
                                <button type="button" class="btn btn-default dropdown-toggle"
                                        data-toggle="dropdown">
                                    <span class="caret"></span>
                                    <span class="sr-only">切换下拉菜单</span>
                                </button>
                                <ul class="dropdown-menu" role="menu" id="year">

                                </ul>
                            </div>
                            <div class="btn-group">
                                <button type="button" class="btn btn-default tm">第一季度</button>
                                <button type="button" class="btn btn-default dropdown-toggle"
                                        data-toggle="dropdown">
                                    <span class="caret"></span>
                                    <span class="sr-only">切换下拉菜单</span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li class="month" value="01"><a href="#">1月</a></li>
                                    <li class="month" value="02"><a href="#">2月</a></li>
                                    <li class="month" value="03"><a href="#">3月</a></li>
                                </ul>
                            </div>
                            <div class="btn-group">
                                <button type="button" class="btn btn-default tm">第二季度</button>
                                <button type="button" class="btn btn-default dropdown-toggle"
                                        data-toggle="dropdown">
                                    <span class="caret"></span>
                                    <span class="sr-only">切换下拉菜单</span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li class="month" value="04"><a href="#">4月</a></li>
                                    <li class="month" value="05"><a href="#">5月</a></li>
                                    <li class="month" value="06"><a href="#">6月</a></li>
                                </ul>
                            </div>
                            <div class="btn-group">
                                <button type="button" class="btn btn-default tm">第三季度</button>
                                <button type="button" class="btn btn-default dropdown-toggle"
                                        data-toggle="dropdown">
                                    <span class="caret"></span>
                                    <span class="sr-only">切换下拉菜单</span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li class="month" value="07"><a href="#">7月</a></li>
                                    <li class="month" value="08"><a href="#">8月</a></li>
                                    <li class="month" value="09"><a href="#">9月</a></li>
                                </ul>
                            </div>
                            <div class="btn-group">
                                <button type="button" class="btn btn-default tm">第四季度</button>
                                <button type="button" class="btn btn-default dropdown-toggle"
                                        data-toggle="dropdown">
                                    <span class="caret"></span>
                                    <span class="sr-only">切换下拉菜单</span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li class="month" value="10"><a href="#">10月</a></li>
                                    <li class="month" value="11"><a href="#">11月</a></li>
                                    <li class="month" value="12"><a href="#">12月</a></li>
                                </ul>
                            </div>

                        </div>
                    </table>
                </div>

                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#demoForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#demoForm">
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
<div class="modal fade" id="demoForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 847px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">排污申报</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="removeId" name="removeId">
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">企业名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="enterpriseName" name="enterpriseName" class="form-control noEdit" />
                            <input type="hidden" id="enterpriseId" name="enterpriseId"/>
                        </div>

                        <label for="enterpriseAP" class="col-sm-2 control-label">企业联系人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="enterpriseAP" name="enterpriseAP" class="form-control noEdit"
                            />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="apPhone" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="apPhone" name="apPhone" class="form-control"
                                   data-message="企业名称不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>

                        <label for="payMoney" class="col-sm-2 control-label">缴费金额<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="payMoney" name="payMoney" class="form-control"
                            />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="registDate" class="col-sm-2 control-label">登记日期<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="" class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd">
                                <input class="form-control" size="16" type="text" id="registDate" name="registDate" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>

                        <label for="payDate" class="col-sm-2 control-label">缴费日期<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="" class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd">
                                <input class="form-control" size="16" type="text" id="payDate" name="payDate" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="alertDate" class="col-sm-2 control-label">提醒日期一<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="" class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii">
                                <input class="form-control" size="16" type="text" id="alertDate" name="alertDate" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>

                        <label for="realertDate" class="col-sm-2 control-label">提醒日期二<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="" class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii">
                                <input class="form-control" size="16" type="text" id="realertDate" name="realertDate" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="paymentStatus" class="col-sm-2 control-label">缴费状态<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select id="paymentStatus" name="paymentStatus" class="form-control">
                                <option value="0">未缴费</option>
                                <option value="1">已缴费</option>
                                <option value="2">未按时缴费</option>
                            </select>
                        </div>

                    </div>

                    <div class="form-group">
                        <label for="remark" class="col-sm-2 control-label">备注<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="remark" name="remark" class="form-control" rows="4" cols="50" placeholder=""></textarea>
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
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/enterprise/scripts/pollutantPayment.js"></script>
</body>
</html>
