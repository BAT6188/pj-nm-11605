<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/12
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String id=request.getParameter("id");
    %>
    <title>企业台账</title>
    <jsp:include page="/common/common_ztree.jsp"></jsp:include>
    <script type="text/javascript">
        var id='<%=id%>';
        var enterpriseData;
        $(function(){
            $.ajax({
                url: rootPath + "/action/S_enterprise_Enterprise_getEnterpriseInfo.action",
                type:"post",
                async:false,
                data:{"id":id},//阻止深度序列化，向后台传递数组
                dataType:"json",
                success:function(data){
                    enterpriseData = data;
                }
            });
        })
    </script>
    <style>
        .menuDiv h3{
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="wrap">
    <div class="menu-left left">
        <div class="scrollContent" >
            <ul id="treeDemo1" class="ztree"></ul>
        </div>
    </div>
    <div class="main-right right level3MenuContent">
        <%--<jsp:include page="enterpriseInfo.jsp"></jsp:include>--%>
    </div>
</div>
<script type="text/javascript">
    $(".scrollContent").slimScroll({
        height:"100%",
        railOpacity:.9,
        alwaysVisible:!1
    });

    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "code",
                pIdKey: "parentCode",
                rootPId: -1
            }
        },
        height:500,
        width:200,
        view: {
            showLine: false
        },
        async: {
            enable: true,
            url:rootPath + "/S_dict_Dict_multipleList.action",
            autoParam:["code"],
            otherParam:{"code":"industryType"},
            dataFilter: null
        },
        callback: {
            onDblClick: function(event, treeId, treeNode){
                if(treeNode.check_Child_State == -1){
                    $('#'+v).val(treeNode.name);
                    $('#'+v+'ModalClose').trigger('click');
                }
            }
        }
    };
    $.fn.zTree.init($("#treeDemo1"), setting);
</script>
</body>
</html>
