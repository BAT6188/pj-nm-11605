<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>超标记录</title>
    <script type="text/javascript">
        $(function(){
            setBlock('#blockLevelId','#blockId');
        });
        function setBlock(pSelector,cSelector){
            var pBlock = $(pSelector),cBlock = $(cSelector),blockMap={};
            var blockLevel = searchForAjax("/action/S_composite_BlockLevel_list.action",null);
            if(blockLevel.total){
                $.each(blockLevel.rows,function(k,v){
                    pBlock.append($("<option>").val(v.id).text(v.name));
                    var child = searchForAjax("/action/S_composite_Block_findLevelById.action",{blockLevelId: v.id});
                    blockMap[v.id] = child;
                });
                pBlock.change(function(){
                    var pid = $(this).val();
                    var childData = blockMap[pid];
                    cBlock.empty();
                    cBlock.append($("<option>").val("").text("全部"));
                    $.each(childData,function(k,v){
                        cBlock.append($("<option>").val(v.id).text(v.orgName));
                    });
                });
            }
        }
        function searchForAjax(url,entity) {
            var returnData;
            $.ajax({
                url: rootPath + url,
                method:'post',
                async :false,
                data:entity,
                dataType:"json",
                success:function(data) {
                    returnData = data;
                }
            });
            return returnData;
        }
    </script>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;z-index: 0">超标记录列表</a>
            <div class="dealBox">
                <div class="sideTitle left">
                        <span class="blueMsg">
                            <img class="tipImg" src="<%=request.getContextPath()%>/common/images/searchTip.png" alt=""/>
                            <span class="text">查询</span>
                        </span>
                </div>
                <div class="queryBox marginLeft0">
                    <form class="form-horizontal" role="form" id="searchform">
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="enterpriseName">企业名称：</label> <input type="text" style="width: 238px;" id="enterpriseName" name="enterpriseName" class="form-control">
                                <label for="blockLevelId" class="labelMarginLeft">&nbsp;所属网格：</label>
                                <select class="form-control" id="blockLevelId" name="blockLevelId" style="width: 238px;">
                                    <option value="">全部</option>
                                </select>
                                —
                                <select class="form-control" id="blockId" name="blockId" style="width: 238px;">
                                    <option value="">全部</option>
                                </select>
                            </div>
                        </div>
                        <p></p>
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="enterpriseType">企业类型：</label>
                                <select style="width: 238px;" class="form-control"  id="enterpriseType" name="enterpriseType">
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
                                <label for="monitorTime" class="labelMarginLeft">超标时间段：</label>
                                <div id="datetimepicker1" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                    <input class="form-control" size="16" type="text" id="startTime" name="startTime" value="" readonly placeholder="开始时间">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                                —
                                <div id="datetimepicker2" class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                    <input class="form-control" size="16" type="text" id="endTime" name="endTime" value="" readonly placeholder="结束时间">
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        <%--<p></p>
                        <div class="form-inline">
                            <div class="form-group">

                            </div>
                        </div>--%>
                    </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
            </div>
            <div class="tableBox">
                <table id="table" class="table table-striped table-responsive">
                </table>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/monitor/monitorLog/scripts/portStatusHistory.js"></script>
<%--<%@include file="/common/msgSend/msgSend.jsp"%>--%>
</body>
</html>
