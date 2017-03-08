$(function () {
    var portAlertDialog=$("#portAlertDialog");
    if ('gaolei'==userId){
        setInterval(alert,8000);//30秒钟一次
    }

    function setPortAlertStatus(id,portAlertStatus) {
        $.ajax({
            url:rootPath + "/action/S_dispatch_MonitorCase_setPortAlertStatus.action",
            type:"post",
            data:{id:id,portAlertStatus:portAlertStatus},
            dataType:"json",
            success:function (msg) {
               console.log(msg)
            }
        });
    }
    function alert() {
        var alertEnterpris = queryAlertEnterpriseList();
        if (alertEnterpris.length>0){
            var s ='<audio id="baojing" src="'+rootPath+'/container/gov/composite/baojing.wav" autoplay="autoplay" loop="loop"/>';
            $('body').append(s);
            setTimeout("$('#baojing').remove()",10000);
            var li="";
            var enterpriseId='';
            var arr=[];
            $.each(alertEnterpris,function (i,v) {
                setPortAlertStatus(v.id,1);
                if (arr.indexOf(v.enterpriseId)==-1){
                    arr.push(v.enterpriseId);
                    li+='<li><label class="col-sm-12">'+v.enterpriseName+'</label></li>';
                    enterpriseId+='<input type="hidden" name="enterpriseId" value="'+v.enterpriseId+'"/>';
                }
            })

            var html='<ul>'+li+'</ul>'+enterpriseId;
            $('#alertEnterpriseList').html(html);
            portAlertDialog.modal("show")
        }
    }

    $("#alertEnterpriseLook").click(function () {
        $('#baojing').remove();
        // var entity = portAlertDialog.find("form").formSerializeObject();
        // setTimeout(alert,30000);//30秒钟一次

        // jumpToMap(entity[0]);
    })

    function jumpToMap(enterpriseId){
        pageUtils.toUrl(rootPath + "/container/gov/composite/one_image.jsp",{enterpriseId:enterpriseId})
    }

    function queryAlertEnterpriseList() {
        var value;
        $.ajax({
            url:rootPath + "/action/S_dispatch_MonitorCase_queryAlertEnterpriseList.action",
            type:"post",
            async:false,
            success:function (alertEnterpriseList) {
                value = JSON.parse(alertEnterpriseList);
            }
        });
        return value;
    }


});