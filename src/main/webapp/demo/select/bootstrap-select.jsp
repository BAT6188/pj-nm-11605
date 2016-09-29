<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>选中组件</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link href="<%=request.getContextPath()%>/scripts/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/scripts/font-awesome-4.6.3/css/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/scripts/select2-4.0.3/css/select2.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/pageStyle.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/scripts/jquery1.12.4/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/bootstrap-3.3.7/js/bootstrap.js"></script>

    <script src="<%=request.getContextPath()%>/scripts/select2-4.0.3/js/select2.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/select2-4.0.3/js/i18n/zh-CN.js"></script>

    <style>
        select {
            width: 200px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="alert alert-info"> 选择组件</div>

    <button  type="button" class="btn btn-default" data-id="enable" >enable</button>
    <button  type="button" class="btn btn-default" data-id="disable"  >disable</button>
    <button  type="button" class="btn btn-default" data-id="reset"  >重置数据</button>
    <p></p>
    <div class="alert alert-info">基础选择框--单选</div>
    <p></p>
    <select id="js-example-basic-single">
        <option value="AL">Alabama</option>
        <option value="WY">Wyoming</option>
    </select>

    <p></p>
    <div class="alert alert-info">基础选择框--多选</div>
    <p></p>
    <select id="js-example-basic-multiple" multiple="multiple" >
        <option value="AL">Alabama</option>
        <option value="WY">Wyoming</option>
        <option value="AL">Alabama1</option>
        <option value="WY">Wyoming1</option>
        <option value="AL">Alabama2</option>
        <option value="WY">Wyoming2</option>
    </select>

    <p></p>
    <div class="alert alert-info">从js数组</div>
    <p></p>
    <select id="js-example-data-array" >
    </select>


    <p></p>
    <div class="alert alert-info">从js数组</div>
    <p></p>
    <select id="js-example-data-array-selected" multiple="multiple" >
        <option value="2" selected="selected">duplicate</option>
    </select>


    <p></p>
    <div class="alert alert-info">远程加载数据</div>
    <p></p>
    <select id="js-data-example-ajax">
    </select>
</div>


</body>
</html>

<script>
    $('#js-example-basic-single').select2({
        language: "zh-CN",
        placeholder: "请选择",
        minimumResultsForSearch: Infinity,
        allowClear: true
    });
    $('#js-example-basic-single').on("change", function (e)
    {
        alert(e);
    });

    $('#js-example-basic-multiple').select2({
        language: "zh-CN",
        placeholder: "请选择",
        maximumSelectionLength: 2,
        allowClear: true
    });
    var data = [{ id: 0, text: 'enhancement' }, { id: 1, text: 'bug' }, { id: 2, text: 'duplicate' }, { id: 3, text: 'invalid' }, { id: 4, text: 'wontfix' }];
    $("#js-example-data-array").select2({
        language: "zh-CN",
        placeholder: "请选择",
        allowClear: true,
        data: data
    });
    $("#js-example-data-array-selected").select2({
        language: "zh-CN",
        placeholder: "请选择",
        allowClear: true,
        data: data
    });
    $("#js-data-example-ajax").select2({
        language: "zh-CN",
        placeholder: "请选择",
        allowClear: true,
        ajax: {
            url: "../bootstrap-table/fromdata.json",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    q: params.term, // search term
                    page: params.page
                };
            },
            processResults: function (data, params) {
                params.page = params.page || 1;
                return {
                    results: data.rows,
                    pagination: {
                        more: (params.page * 30) < data.total
                    }
                };
            },
            cache: true
        },
        escapeMarkup: function (markup) { return markup; }, // let our custom formatter work
        minimumInputLength: 0,
        templateResult: formatRepo,
        templateSelection: formatRepoSelection
    });
    function formatRepo (repo) {
        return repo.name;
    }

    function formatRepoSelection (repo) {
        if(repo.id){
            return repo.name+"/"+repo.id;
        }else{
            return "";
        }
    }


    $("button").on("click",function(e){
        switch ($(this).attr("data-id")){
            case "enable":
                $("#js-example-basic-single").prop("disabled", false);
                break;
            case "disable":
                $("#js-example-basic-single").prop("disabled", true);
                break;
            case "reset":
                $("#js-example-basic-single").empty();
                $("#js-example-basic-single").select2({data:data});
                break;
        }
    });
</script>
