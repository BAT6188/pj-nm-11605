<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>废水废气监测查看dialog</title>
</head>
<body style="overflow: hidden;">
<!--油烟实时监控-->
<div class="modal fade" id="liveWaterGasModal" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog liveWaterGasDialog" style="width: 80%;" id="setWG">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">废水废气实时监控</h4>
            </div>
            <div class="modal-body">
                <%--餐饮油烟历史数据--%>
                    <div class="row clearfix">
                        <div class="col-md-3 column">
                            <div class="scrollContent" >
                                <ul id="enterpriseWaterGasPortZTree" class="ztree"></ul>
                            </div>
                        </div>
                        <div class="col-md-9 column">
                            <div class="tableBox">
                                <table id="liveWaterGasTable" class="table table-striped table-responsive">
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
<script type="text/javascript" src="<%=request.getContextPath()%>/container/gov/composite/scripts/wg_form_view.js"></script>
</html>
