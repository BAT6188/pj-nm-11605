<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>人员信息管理</title>
    <%--<link href="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/metrStyle-cd/metroStyle.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/common/scripts/ztree-3.5.24/jquery.ztree.all.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/slimScroll/jquery.slimscroll.js"></script>--%>
    <link href="<%=request.getContextPath()%>/common/scripts/cropbox/css/cropbox.css" rel="stylesheet">
    <style>
        .nav-tabs li a{
            font-size: 15px;
            color: #0a36e9;
        }
        .active a{
            color: black;
        }
        #scfForm td{
            height: 50px;
        }
        .upHeadImage{
            cursor: pointer;
        }
    </style>
    <script src="<%=request.getContextPath()%>/common/scripts/cropbox/js/cropbox.js"></script>
    <script>
        $('.modal-body').attr('style','max-height: '+pageUtils.getFormHeight()+'px;overflow-y: auto;overflow-x: hidden;padding:10px;');
        $('#orgDiv').prepend(pageUtils.loading());
        var options =
        {
            thumbBox: '.thumbBox',
            spinner: '.spinner',
            defaultImgSrc:'common/scripts/cropbox/image/portrait.png',
            imgSrc: 'common/scripts/cropbox/image/portrait.png'
        }
        var cropper = $('.imageBox').cropbox(options);
        $('#upload-file').on('change', function(){
            var reader = new FileReader();
            reader.onload = function(e) {
                options.imgSrc = e.target.result;
                cropper = $('.imageBox').cropbox(options);
            }
            reader.readAsDataURL(this.files[0]);
            this.files = [];
        })
        $('#btnCrop').on('click', function(){
            var img = cropper.getDataURL();
            setHeadImage(img);
            $('#headImageModal').modal('hide');
            $('#headImage').val(img);
        })
        $('#btnZoomIn').on('click', function(){
            cropper.zoomIn();
        })
        $('#btnZoomOut').on('click', function(){
            cropper.zoomOut();
        })
        var disabledType = false;
        $('#image-uploader-gallery').click(function(event){
            if(disabledType){
                event.preventDefault();
            }else{
                cropper = $('.imageBox').cropbox(options);
                $('#headImageModal').modal('show');
            }
        });
        function setHeadImage(img){
            if(!img){img=options.defaultImgSrc;}
            options.imgSrc = img;
            $('#headImageDiv').html('');
            $('#headImageDiv').append('<img src="'+img+'" align="absmiddle" style="width:180px;margin-top:4px;border-radius:0px;box-shadow:0px 0px 12px #7E7E7E;">');//<p>180px*180px</p>
        }
    </script>
</head>
<body>
<div class="wrap">
    <div class="menu-left left">
        <div id="myTabContent" class="tab-content" style="margin-left: 10px;">
            <div class="tab-pane fade in active" id="orgDiv">
                <div id="orgScrollContent" class="scrollContent">
                    <ul id="orgZtree" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
    <div class="main-right right level3MenuContent">
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
                            <form class="form-inline" id="searchform">
                                <input type="hidden" id="s_orgId" name="orgId" class="form-control hidden" />
                                <input type="hidden" id="s_blockLevelId" name="blockLevelId" class="form-control hidden" />
                                <input type="hidden" id="s_blockId" name="blockId" class="form-control hidden" />
                                <label for="s_name">姓名：</label> <input type="text" id="s_name" name="name" class="form-control" />
                                <label for="s_department">部门：</label> <input type="text" id="s_department" name="department" class="form-control" />
                                <label for="s_position">职务：</label><input type="text" id="s_position" name="position" class="form-control" />
                            </form>
                        </div>
                        <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                        <button id="reset" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                        <br/><br>
                        <p class="btnListP">
                            <button id="add" type="button" class="btn btn-sm btn-success orgBtn" data-toggle="modal" data-target="#scfForm">
                                <i class="btnIcon add-icon"></i><span>新建</span>
                            </button>
                            <button id="update" type="button" class="btn btn-sm btn-warning orgBtn" data-toggle="modal" data-target="#scfForm">
                                <i class="btnIcon edit-icon"></i><span>修改</span>
                            </button>
                            <button id="remove" type="button" class="btn btn-sm btn-danger orgBtn">
                                <i class="btnIcon delf-icon"></i><span>删除</span>
                            </button>
                            <button id="export" type="button" class="btn btn-sm btn-success">
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
        <div class="modal fade" data-backdrop="static" id="scfForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width: 800px">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title form-title">添加人员信息</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" role="form">
                            <table width="100%;">
                                <tbody>
                                    <tr>
                                        <td><label for="name" class="col-sm-4 control-label">姓名<span class="text-danger">*</span>：</label>
                                            <div class="col-sm-8 leftDiv">
                                                <input type="hidden" id="id" name="id" class="form-control">
                                                <input type="hidden" id="orgId" name="orgId" class="form-control">
                                                <input type="hidden" id="type" name="type" class="form-control" />
                                                <input type="hidden" id="headImage" name="headImage" class="form-control" />
                                                <input type="hidden" id="apportalUserId" name="apportalUserId" class="form-control">
                                                <input type="hidden" id="removeId" name="removeId" class="form-control">
                                                <input type="text" id="name" name="name" class="form-control"
                                                       data-message="姓名不能为空"
                                                       data-easytip="position:top;class:easy-red;"
                                                />
                                            </div></td>
                                        <td rowspan="4">
                                            <label for="name" class="col-sm-4 control-label" style="margin-top: 20%;">头像：</label>
                                            <div class="col-sm-8">
                                                <div id="image-uploader-gallery" class="upHeadImage">
                                                    <div id="headImageDiv" class="qq-uploader-selector qq-uploader"></div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <p></p>
                                    <tr>
                                        <td><label for="position" class="col-sm-4 control-label">职务<span class="text-danger">*</span>：</label>
                                            <div class="col-sm-8">
                                                <input type="text" id="position" name="position" class="form-control"
                                                       data-message="职务不能为空"
                                                       data-easytip="position:top;class:easy-red;"
                                                />
                                            </div></td>
                                    </tr>
                                    <p></p>
                                    <tr>
                                        <td><label for="department" class="col-sm-4 control-label">所属部门<span class="text-danger">*</span>：</label>
                                            <div class="col-sm-8">
                                                <input type="text" id="department" name="department" class="form-control"
                                                       data-message="所属部门不能为空"
                                                       data-easytip="position:top;class:easy-red;"
                                                />
                                            </div></td>
                                    </tr>
                                    <p></p>
                                    <tr>
                                        <td><label for="address" class="col-sm-4 control-label">单位地址<span class="text-danger">*</span>：</label>
                                            <div class="col-sm-8">
                                                <input type="address" id="address" name="address" class="form-control"
                                                       data-message="单位地址不能为空"
                                                       data-easytip="position:top;class:easy-red;"
                                                />
                                            </div></td>
                                    </tr>
                                    <p></p>
                                    <tr>
                                        <td>
                                            <label for="tel" class="col-sm-4 control-label">座机号码<span class="text-danger">*</span>：</label>
                                            <div class="col-sm-8">
                                                <input type="text" id="tel" name="tel" class="form-control"
                                                       data-message="座机号码不能为空"
                                                       data-easytip="position:top;class:easy-red;"
                                                />
                                            </div>
                                        </td>
                                        <td>
                                            <label for="phone" class="col-sm-4 control-label">手机号码<span class="text-danger">*</span>：</label>
                                            <div class="col-sm-8">
                                                <input type="text" id="phone" name="phone" class="form-control"
                                                       data-message="手机号码不能为空"
                                                       data-easytip="position:top;class:easy-red;"
                                                />
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" >
                                            <label for="summary" class="col-sm-2 control-label">简介：</label>
                                            <div class="col-sm-10">
                                                <textarea id="summary" name="summary" class="form-control" rows="4" cols="50" placeholder="请填写人员简介"></textarea>
                                            </div>
                                        </td>
                                    </tr>
                                    <p></p>
                                    <tr>
                                        <td colspan="2" >
                                            <label for="attachment" class="col-sm-2 control-label">附件：</label>
                                            <div class="col-sm-10">
                                                <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                                                <div id="fine-uploader-gallery"></div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <%--<div class="form-group">
                                <label for="name" class="col-sm-2 control-label">姓名<span class="text-danger">*</span>：</label>
                                <div class="col-sm-4">
                                    <input type="hidden" id="id" name="id" class="form-control">
                                    <input type="hidden" id="orgId" name="orgId" class="form-control">
                                    <input type="hidden" id="apportalUserId" name="apportalUserId" class="form-control">
                                    <input type="hidden" id="removeId" name="removeId" class="form-control">
                                    <input type="text" id="name" name="name" class="form-control"
                                           data-message="姓名不能为空"
                                           data-easytip="position:top;class:easy-red;"
                                    />
                                </div>
                                <label for="department" class="col-sm-2 control-label">所属部门<span class="text-danger">*</span>：</label>
                                <div class="col-sm-4">
                                    <input type="text" id="department" name="department" class="form-control"
                                           data-message="所属部门不能为空"
                                           data-easytip="position:top;class:easy-red;"
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="position" class="col-sm-2 control-label">职务<span class="text-danger">*</span>：</label>
                                <div class="col-sm-4">
                                    <input type="text" id="position" name="position" class="form-control"
                                           data-message="职务不能为空"
                                           data-easytip="position:top;class:easy-red;"
                                    />
                                </div>
                                <label for="address" class="col-sm-2 control-label">单位地址<span class="text-danger">*</span>：</label>
                                <div class="col-sm-4">
                                    <input type="address" id="address" name="address" class="form-control"
                                           data-message="单位地址不能为空"
                                           data-easytip="position:top;class:easy-red;"
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="tel" class="col-sm-2 control-label">座机号码<span class="text-danger">*</span>：</label>
                                <div class="col-sm-4">
                                    <input type="text" id="tel" name="tel" class="form-control"
                                           data-message="单位地址不能为空"
                                           data-easytip="position:top;class:easy-red;"
                                    />
                                </div>
                                <label for="phone" class="col-sm-2 control-label">手机号码<span class="text-danger">*</span>：</label>
                                <div class="col-sm-4">
                                    <input type="text" id="phone" name="phone" class="form-control"
                                           data-message="单位地址不能为空"
                                           data-easytip="position:top;class:easy-red;"
                                    />
                                </div>
                            </div>
                            <div class="form-group blockBtn" style="display: none">
                                <label for="apportalUserName" class="col-sm-2 control-label">关联系统用户：</label>
                                <div class="col-sm-4">
                                    <input type="text" id="apportalUserName" name="apportalUserName" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="attachment" class="col-sm-2 control-label">附件：</label>
                                <div class="col-sm-10">
                                    <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                                    <div id="fine-uploader-gallery"></div>
                                </div>
                            </div>--%>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="save">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="headImageModal" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width: 650px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel">头像上传</h4>
                    </div>
                    <div class="modal-body">
                        <div class="mycontainer">
                            <div class="imageBox">
                                <div class="thumbBox"></div>
                                <div class="spinner" style="display: none">Loading...</div>
                            </div>
                            <div class="action">
                                <div class="new-contentarea tc">
                                    <a href="javascript:void(0)" class="upload-img">
                                        <label for="upload-file">上传图像</label>
                                    </a>
                                    <input type="file" class="" name="upload-file" id="upload-file" />
                                </div>
                                <input type="button" id="btnCrop"  class="Btnsty_peyton" value="裁切">
                                <input type="button" id="btnZoomIn" class="Btnsty_peyton" value="+"  >
                                <input type="button" id="btnZoomOut" class="Btnsty_peyton" value="-" >
                            </div>
                            <%--<div class="cropped"></div>--%>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <%--<button type="button" class="btn btn-primary">
                            提交更改
                        </button>--%>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
    </div>
</div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/office/scripts/contacts.js"></script>
</body>
</html>
