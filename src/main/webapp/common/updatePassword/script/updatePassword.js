var updatePasswordDialog=$("#updatePasswordDialog");

$("#updatePasswordBtn").click(function () {
    updatePasswordDialog.modal("show")
})

function saveAjax_updatePasswordDialog(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_alert_User_govUpdatePwd.action",
        type:"post",
        data:entity,
        dataType:"json",
        success:callback
    });
}

var ef_updatePasswordDialog = updatePasswordDialog.easyform({
    success:function (ef_updatePasswordDialog) {
        var entity = $("#updatePasswordDialog").find("form").formSerializeObject();
        saveAjax_updatePasswordDialog(entity,function (msg) {
            if(1==msg){
                Ewin.alert("密码修改成功")
                updatePasswordDialog.modal('hide');
            }else {
                Ewin.alert("原密码错误")
            }

        });
    }
});

$("#updateBtn").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef_updatePasswordDialog.submit(false);
});