$(function () {
    var portAlertDialog=$("#portAlertDialog");
    alert();
    // setInterval(alert,30000);
    function alert() {
        var alertEnterpris = queryAlertEnterpriseList();
        if (alertEnterpris.length>0){
            var s ='<audio id="baojing" src="'+rootPath+'/container/gov/composite/baojing.wav" autoplay="autoplay" loop="loop"/>';
            $('body').append(s);
            setTimeout("$('#baojing').remove()",10000);
            var li="";
            $.each(alertEnterpris,function (i,v) {
                li+='<li><label class="col-sm-12">'+v.name+'</label></li>';
            })
            var ul='<ul>'+li+'</ul>';
            $('#alertEnterpriseList').html(ul);
            portAlertDialog.modal("show")

        }
    }

    $("#alertEnterpriseLook").click(function () {

        jumpToMap('2284c29d93cb4492a201bb9dd1e4f53b');
    })

    function jumpToMap(enterpriseId){
        pageUtils.toUrl(rootPath + "/container/gov/composite/lookEnterpriseInMap.jsp",{enterpriseId:enterpriseId})
    }

    function queryAlertEnterpriseList() {
        var value;
        $.ajax({
            url:rootPath + "/action/S_enterprise_Enterprise_queryAlertEnterpriseList.action",
            type:"post",
            async:false,
            dataType:"json",
            success:function (alertEnterpriseList) {
                value = alertEnterpriseList;
            }
        });
        return value;
    }


});