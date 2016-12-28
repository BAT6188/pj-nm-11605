<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>党员管理</title>
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
        #headImageDiv > img {
            margin-top: -50px;
            margin-left: -20px;
        }
    </style>
    <script>
        $('.modal-body').attr('style','max-height: '+pageUtils.getFormHeight()+'px;overflow-y: auto;overflow-x: hidden;padding:10px;');
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
                                <input type="hidden" id="s_orgId" name="partyOrgId" class="form-control hidden" />
                            </form>
                        </div>

                        <br/><br>
                        <p class="btnListP">
                            <button id="add" type="button" class="btn btn-sm btn-success orgBtn">
                                <i class="btnIcon add-icon"></i><span>添加</span>
                            </button>
                            <button id="remove" type="button" class="btn btn-sm btn-danger orgBtn">
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
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/office/scripts/partyOrgIPerson.js"></script>
</body>
</html>
