<%@ page language="java" pageEncoding="utf8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>短信发送</title>
    <link href="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/metrStyle-cd/metroStyle.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/jquery.ztree.all.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/slimScroll/jquery.slimscroll.js"></script>
    <style type="text/css">
        .Node-frame-menubar {
            width: 250px;
            height: 500px;
            float: left;
            position: relative;
            left: 0px;
            border-right: 1px solid #e5e5e5;
            padding: 10px;
            color: #337ab7;
        }
    </style>
</head>
<body>
<div class="modal fade" id="selectPeopleDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width:882px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="formTitle"></h4>
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
                <button type="button" class="btn btn-primary" id="sendTo" data-toggle="modal">发送</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="<%=request.getContextPath()%>/common/msgSend/scripts/selectPeople.js"></script>
<script src="<%=request.getContextPath()%>/common/msgSend/scripts/jsMap.js"></script>
</body>
</html>