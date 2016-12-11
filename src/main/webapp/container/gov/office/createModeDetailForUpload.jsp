<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>指标及重点工程</title>
    <style>
        a{
            color: #0b0c0d;
        }
        .nav-tabs > li.active > a, .nav-tabs > li.active > a:hover, .nav-tabs > li.active > a:focus{
            font-weight: bolder;
        }
        .qq-uploader{
            min-height: 50px;
            max-height: 115px;
        }
    </style>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <ul id="myTab" class="nav nav-tabs">
                <li>
                    <a href="#home" data-toggle="tab" onclick="changeTab(1)">指标任务</a>
                </li>
                <li><a href="#ios" data-toggle="tab" onclick="changeTab(2)">重点工程</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="dealBox">
                    <div class="sideTitle left">
                        <span class="blueMsg">
                            <img class="tipImg" src="<%=request.getContextPath()%>/common/images/searchTip.png" alt=""/>
                            <span class="text">查询</span>
                        </span>
                    </div>
                    <div class="queryBox marginLeft0">
                        <form class="form-inline">
                            <input type="hidden" id="type" name="type">
                            <div class="form-group">
                                <label>上报状态：</label>
                                <select id="" name="completeStatus" class="form-control">
                                    <option value="">全部</option>
                                    <option value="0">未完成</option>
                                    <option value="1">完成</option>
                                </select>
                            </div>
                        </form>
                        <p></p>
                    </div>
                    <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                    <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                    <p class="btnListP">
                        <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#demoForm">
                            <i class="btnIcon edit-icon"></i><span>上报</span>
                        </button>
                        <%--<button id="remove" type="button" class="btn btn-sm btn-danger">
                            <i class="btnIcon delf-icon"></i><span>删除</span>
                        </button>--%>

                    </p>
                </div>
                <div class="tableBox">
                    <table id="table" class="table table-striped table-responsive">
                    </table>
                </div>
            </div>


        </div>
    </div>
</div>
<!--添加表单-->
<div class="modal fade" id="demoForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 905px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="registerPerson" class="col-sm-2 control-label">责任部门<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <select id="responsibleDepartmentId" name="responsibleDepartmentId" class="form-control responsibleDepartment">
                            </select>
                        </div>
                        <label for="" class="col-sm-2 control-label">上报截止时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="deadline"  name="deadline" type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label"><span class="contentLabel">指标内容</span><span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="content" name="content" class="form-control" style="height: 267px;" placeholder=""></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">创模进度附件：</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">年度工作总结附件：</label>
                        <div class="col-sm-10">
                            <div id="fine-uploader-gallery2"></div>
                        </div>
                    </div>
                    <div class="form-group material">
                        <label for="attachment" class="col-sm-2 control-label">支撑材料附件：</label>
                        <div class="col-sm-10">
                            <div id="fine-uploader-gallery3"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save">上报</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script>
    var type=1
    var content='指标内容'
    function changeTab(f) {
        if (f==1){
            $("#typeBtn").text("新增指标")
            $(".material").hide()

            content='指标内容'
            $(".contentLabel").text(content)
        }else{
            $("#typeBtn").text("新增重点工程")
            $(".material").show()

            content='工程任务'
            $(".contentLabel").text(content)
        }
        type=f;
        $("#type").val(type)
        gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
        $(".th-inner:eq(1)").text(content)
    }

    function ajaxLoadStringtoOption(url,selector,optionsSetting,isShowAll){
        $.ajax({
            url: url,
            type:"post",
            success:function (options) {
                options=JSON.parse(options)
                $.each(options,function (i,v) {
                    var option = $("<option>").val(v[optionsSetting.code]).text(v[optionsSetting.name]);
                    $(selector).append(option);
                })
            }
        });
    }

    $(function () {
        $('#myTab a:first').tab('show')
        $(".material").hide()
        $("#type").val(type)
        ajaxLoadStringtoOption(rootPath+"/action/S_office_CreateModeDetail_getOrgList.action",".responsibleDepartment",{code:"orgId",name:"orgName"})
    });
</script>
<script src="<%=request.getContextPath()%>/container/gov/office/scripts/createModeDetailForUpload.js"></script>
</body>
</html>
