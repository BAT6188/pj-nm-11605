<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>日期组件</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link href="<%=request.getContextPath()%>/common/scripts/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/common/scripts/font-awesome-4.6.3/css/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/common/scripts/bootstrap-datetimepicker2.3.11/bootstrap-datetimepicker.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/pageStyle.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/common/scripts/jquery1.12.4/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/bootstrap-3.3.7/js/bootstrap.js"></script>

    <script src="<%=request.getContextPath()%>/common/scripts/bootstrap-datetimepicker2.3.11/bootstrap-datetimepicker.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/bootstrap-datetimepicker2.3.11/locales/bootstrap-datetimepicker.zh-CN.js"></script>

</head>
<body>
<div class="container">
    <div class="alert alert-info"> 日期组件</div>

    <button  type="button" class="btn btn-default" data-id="show" >显示</button>
    <button  type="button" class="btn btn-default" data-id="hide"  >隐藏</button>
    <button  type="button" class="btn btn-default" data-id="update"  >以输入值为当前值</button>
    <button  type="button" class="btn btn-default" data-id="setStart"  >限制开始</button>
    <button  type="button" class="btn btn-default" data-id="setEnd"  >限制结束</button>
    <button  type="button" class="btn btn-default" data-id="setStartNull"  >解除限制开始</button>
    <button  type="button" class="btn btn-default"  data-id="setEndNull" >解除限制结束</button>
    <button  type="button" class="btn btn-default"  data-id="remove" >删除</button>

    <div class="alert alert-info"> 日期时间组件</div>
    <p></p>
    <div id="datetimepicker" class="input-group date form_datetime col-md-5" data-date="1979-09-16 05:25" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input1">
        <input class="form-control" size="16" type="text" value="" readonly>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
    </div>
    <input type="hidden" id="dtp_input1" value="" /><br/>

    <p></p>
    <div class="alert alert-info"> 日期</div>
    <p></p>
    <div id="datetimepicker2" class="input-group date form_date col-md-5" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
        <input class="form-control" size="16" type="text" value="" readonly>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
    </div>
    <input type="hidden" id="dtp_input2" value="" /><br/>
    <p></p>
    <div class="alert alert-info"> 日期时间</div>
    <p></p>
    <div id="datetimepicker3" class="input-group date form_time col-md-5" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
        <input class="form-control" size="16" type="text" value="" readonly>
        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
    </div>
    <input type="hidden" id="dtp_input3" value="" /><br/>

    <p></p>
    <div class="alert alert-info"> 日期</div>
    <p></p>
    <div id="datetimepicker4"></div>
    <p></p>
</div>


</body>
</html>

<script>
    $('#datetimepicker').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('#datetimepicker2').datetimepicker({
        language:   'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('#datetimepicker3').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0,
        minuteStep:5,
        pickerPosition: "bottom-left"
    });
    $('#datetimepicker4').datetimepicker();

    $("button").on("click",function(e){
        switch ($(this).attr("data-id")){
            case "show":
                // 显示
                $('#datetimepicker').datetimepicker('show');
                break;
            case "hide":
                // 隐藏
                $('#datetimepicker').datetimepicker('hide');
                break;
            case "update":
                // 使用当前输入框中的值更新日期时间选择器。
                $('#datetimepicker').datetimepicker('update');
                break;
            case "setStart":
                // 设置限制开始时间
                $('#datetimepicker').datetimepicker('setStartDate', '2012-01-01');
                break;
            case "setEnd":
                //给日期时间选择器设置结束日期。
                $('#datetimepicker').datetimepicker('setEndDate', '2012-01-01');
                break;
            case "setStartNull":
                // 清除开始时间
                $('#datetimepicker').datetimepicker('setStartDate');
                break;
            case "setEndNull":
                // 清除结束时间
                $('#datetimepicker').datetimepicker('setEndDate');
                break;
            case "remove":
                // 删除组件
                $('#datetimepicker').datetimepicker('remove');
                break;
        }
    });
    $('#datetimepicker').on('changeDate', function(ev){

    });
</script>
