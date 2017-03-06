$(function () {
    var portAlertDialog=$("#portAlertDialog");
    if ('Y'==isJkzx){
        setTimeout(alert,600000);//十分钟一次
        // setTimeout(alert,5000);//5秒钟一次
    }
    function alert() {
        var alertEnterpris = queryAlertEnterpriseList();
        if (alertEnterpris.length>0){
            var s ='<audio id="baojing" src="'+rootPath+'/container/gov/composite/baojing.wav" autoplay="autoplay" loop="loop"/>';
            $('body').append(s);
            setTimeout("$('#baojing').remove()",10000);
            var li="";
            var input='';
            $.each(alertEnterpris,function (i,v) {
                li+='<li><label class="col-sm-12">'+v.name+'</label></li>';
                // input+='<input type="hidden" name="enterpriseId" value="'+v.id+'"/>';
            })
            var html='<ul>'+li+'</ul>'+input;
            $('#alertEnterpriseList').html(html);
            portAlertDialog.modal("show")

        }
    }

    $("#alertEnterpriseLook").click(function () {
        setTimeout(alert,600000);
        // var entity = portAlertDialog.find("form").formSerializeObject();
        // jumpToMap(entity[0]);
    })

    function jumpToMap(enterpriseId){
        pageUtils.toUrl(rootPath + "/container/gov/composite/one_image.jsp",{enterpriseId:enterpriseId})
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