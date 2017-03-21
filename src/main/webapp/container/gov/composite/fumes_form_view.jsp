<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>餐饮油烟监测查看dialog</title>
</head>
<body style="overflow: hidden;">
<!--油烟实时监控-->
<div class="modal fade" id="liveFumesModal" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog liveFumesDialog" style="width: 1000px;" id="setF">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">油烟实时监控</h4>
            </div>
            <div class="modal-body">
                <%--餐饮油烟历史数据--%>
                    <div class="row clearfix">
                        <div class="col-md-4 column">
                            <div class="scrollContent" >
                                <ul id="enterpriseFumesPortZTree" class="ztree"></ul>
                            </div>
                        </div>
                        <div class="col-md-8 column">
                            <div class="tableBox">
                                <table id="liveFumesTable" class="table table-striped table-responsive">
                                </table>
                            </div>
                        </div>
                    </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/composite/scripts/fumes_form_view.js"></script>
</html>
