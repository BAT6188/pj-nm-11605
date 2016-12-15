// $(document).ready(function() {
//     $.ajax({
//         //timeout: 30000,
//         url: "http://124.67.69.107:82/apportal",
//         type: "post",
//         data: {j_username:"qxj",j_password:"123456"},
//         dataType: "json",
//         success: function (result) {
//             if(result.status == "success"){
//                 var dsyjtUrl = "http://124.67.69.107:82/dsyjt/" + result.text;
//                 $("#mainFrame").attr("src",dsyjtUrl);
//
//             }
//             if(result.status == "error"){
//                 $("#message").html("用户名或密码有误,请再次输入！");
//             }
//         },
//         error: function () {
//             $("#message").html("服务器错误，请稍后重试.");
//         }
//     });
// });
//
