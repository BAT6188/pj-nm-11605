/**
 * Created by liuyifan on 2016/10/26.
 * 级联加载 网格级别和网格
 * blockLevelSelector和blockSelector建议用class选择器
 */
function loadBlockLevelAndBlockOption(blockLevelSelector,blockSelector) {
    function appendOption(selector,options) {
        $(selector).empty();
        $(selector).append($("<option>").val('').text("请选择"));
        $.each(options,function (i,v) {
            var option = $("<option>").val(v.code).text(v.name);
            $(selector).append(option);
        })
    }

    function loadBlockOption(blockLevelId) {
        $.ajax( {
            url: rootPath + "/action/S_composite_Block_list.action",
            method:'post',
            async :false,
            dataType: "json",
            data: {
                page:1,
                pageSize:1000,
                blockLevelId:3
            },
            success: function( msg ) {
                var data=msg.rows
                $.each(data,function (i,v) {
                    v.code=v.id;
                    v.name=v.orgName;
                })
                appendOption(blockSelector,data)
            }
        } );
    }

    $.ajax( {
        url: rootPath + "/action/S_composite_BlockLevel_list.action",
        method:'post',
        async :false,
        dataType: "json",
        success: function( msg ) {
            var data=msg.rows
            $.each(data,function (i,v) {
                v.code=v.id;
            })
            appendOption(blockLevelSelector,data)
            loadBlockOption()
        }
    } );

    $(blockLevelSelector).change(function(){
        loadBlockOption($(blockLevelSelector).val())

    });


}

