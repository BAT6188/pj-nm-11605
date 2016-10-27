/**
 * Created by Administrator on 2016/10/21.
 */
var formDiv = $('#wasteWaterForm');
var updateSuccessMsg = "保存成功！";
loadInfo();
function loadInfo(){
    $.ajax({
        url: rootPath + "/action/S_port_PortThreshold_list.action",
        type:"post",
        async:false,
        data:{"enterpriseId":enterpriseId,"type":"WW"},//阻止深度序列化，向后台传递数组
        dataType:"json",
        success:function(data){
            if(data.total>0){
                $.each(data.rows,function(k,v){
                    setFormEntity(v);
                });
            }else{
                setEditType();
            }
        }
    });
}
function setFormEntity(entity){
    $('#'+entity.pollutantCode+'Form').find('.form-control').each(function(){
        $(this).val(entity[this.name]);
    })
}
$('#toEditForm').click(function(){
    setEditType();
});
function setEditType(){
    $('#firstInput').focus();
    $('#toEditForm').hide();
    $('.form-control').removeAttr('readonly');
    $('.editBtn').show();
}
function setLookType(){
    //$('#firstInput').focus();
    $('#toEditForm').show();
    $('.form-control').attr('readonly','readonly');
    $('.editBtn').hide();
}
//初始化表单验证
var ef = formDiv.easyform({
    success:function (ef) {
        var entity = formDiv.formSerializeObject();
        console.log(entity);
    }
});
$("#saveForm").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    if(checkInputs()){
        var updateSuccessNumber = 0;
        formDiv.find('form').each(function(){
            var entity = $(this).formSerializeObject();
            saveAjax(entity,function (msg) {
                if(msg.success){
                    $(this).find('input[name="id"]').val(msg.id);
                    updateSuccessNumber +=1;
                }
                if(updateSuccessNumber==4){
                    setLookType();
                    Ewin.alert(updateSuccessMsg);
                    //formDiv.BootstrapAlertMsg('success',"保存数据成功!",2000);
                }
            });
        })
    }else{
        return;
    }
});
$('#resetEditForm').click(function(){
    formDiv.find('form').each(function(){
        $(this)[0].reset();
    })
})
$('#cancelEditForm').click(function(){
    reloadThisPage();
})
setInputs();
function setInputs(){
    var inputs = $('.form-group').find('input');
    $.each(inputs,function(k,v){
        var tip = $(this).easytip();
        $(this).keyup(function(){
            //this.value=this.value.replace(/[^\d.]/g,'');
            var re = new RegExp(/[^\d.]/g);
            if(re.test(this.value)){
                tip.show("请输入数字类型数据！");
                this.value=this.value.replace(/[^\d.]/g,'');
            }else{
                if(($(this).attr('data-easyform'))=='checknumber'){
                    if(this.value>-1 && this.value<100){
                        tip.close();
                    }else{
                        tip.show("请输入小于100的非负数！");
                        this.value = "";
                    }
                }else{
                    tip.close();
                }
            }
        })
    })
}
function checkInputs(){
    var inputs = $('.form-group').find('input');
    var inputLength = inputs.length;
    var checkedLength = 0;
    $.each(inputs,function(k,v){
        var tip = $(this).easytip();
        if(this.value==""){
            tip.show(this.title+"不能为空！");
        }else{
            checkedLength +=1;
        }
    })
    if(inputLength==checkedLength){
        return true;
    }else{
        return false;
    }
}
//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_port_PortThreshold_save.action",
        type:"post",
        data:entity,
        dataType:"json",
        success:callback
    });
}
var thisUrl = rootPath +'/container/gov/enterprise/portThreshold/wasteWaterPT.jsp?id='+enterpriseId;
function reloadThisPage(){
    $('.main-right').html(pageUtils.loading()); // 设置页面加载时的loading图片
    $('.main-right').load(thisUrl); // ajax加载页面
}
