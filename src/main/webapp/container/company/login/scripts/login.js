$(document).ready(function() {


    $("#doLogin").click(function () {
        if($("#j_username").val() == "" || $("#j_password").val() == ""){
            $("#message").html("用户名与密码不能为空！");
            return ;
        }
        var params = $("#login-form").serialize();
        //提交登入
        $.ajax({
            //timeout: 30000,
            url: rootPath + "/action/S_enterprise_Enterprise_doLogin.action",
            type: "post",
            data: params,
            dataType: "json",
            success: function (statusCode) {
                if(statusCode == 1){
                    window.location.href = rootPath + '/container/company/login/index.jsp';
                }else{
                    $("#message").html("用户名或密码有误,请再次输入！");
                }
            },
            error: function () {
                $("#message").html("服务器错误，请稍后重试.");
            }
        });
    });
    

    $('body').keyup(function (e) {
        if (e.keyCode === 13) {
            $("#doLogin").click();
        }
    });

    if (window != window.top) {
        window.top.location.href = window.location.href;
    }


});

