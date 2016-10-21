/**
 * Created by Administrator on 2016/10/21.
 */
loadInfo();
function loadInfo(){
    $.ajax({
        url: rootPath + "/action/S_port_PortThreshold_list.action",
        type:"post",
        async:false,
        data:{"enterpriseId":enterpriseId,"type":"WW"},//阻止深度序列化，向后台传递数组
        dataType:"json",
        success:function(data){
            if(data.total<4){
                setEditType();
            }
        }
    });
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
//初始化表单验证
var ef = $('#wasteWaterForm').easyform({
    success:function (ef) {
        var entity = $('#wasteWaterForm').formSerializeObject();
        console.log(entity);
    }
});
$("#saveForm").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    var entity = $('#wasteWaterForm').formSerializeObject();
    console.log(entity);
});
$('#resetEditForm').click(function(){
    $('#wasteWaterForm')[0].reset();
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
                tip.close();
            }
        })
        $(this).blur(function(){
            if(this.value==""){
                tip.show(this.title+"不能为空！");
                $(this).focus();
            }else{
                tip.close();
            }
        })
    })
}
var thisUrl = rootPath +'/container/gov/enterprise/portThreshold/wasteWaterPT.jsp?id='+enterpriseId;
function reloadThisPage(){
    $('.main-right').html(pageUtils.loading()); // 设置页面加载时的loading图片
    $('.main-right').load(thisUrl); // ajax加载页面
}
