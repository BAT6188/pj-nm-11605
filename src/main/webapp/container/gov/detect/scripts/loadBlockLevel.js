/**
 * Created by liuyifan on 2016/10/26.
 * 级联加载 网格级别和网格
 */
function loadBlockLevelAndBlockOption(blockLevelId) {
    function appendOption(selector,options) {
        $(selector).empty();
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
                blockLevelId:blockLevelId
            },
            success: function( msg ) {
                var data=msg.rows
                $.each(data,function (i,v) {
                    v.code=v.id;
                    v.name=v.orgName;
                })
                appendOption("#blockId",data)
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
            appendOption("#blockLevelId",data)
            loadBlockOption(1)
        }
    } );

    $("#blockLevelId").change(function(){
        loadBlockOption($("#blockLevelId").val())

    });


}

