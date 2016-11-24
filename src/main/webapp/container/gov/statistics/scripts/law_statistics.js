/**
 * Created by Administrator on 2016/10/13.
 */
//@ sourceURL=law_statistics.js
$(function(){
    var highchart = $("#container");
    var valueChart = '1';

    //初始化日期组件
    $('.form_datetime').datetimepicker({
        language:   'zh-CN',
        autoclose: 1,
        startView: 3,//月视图
        minView: 3
    });
    
    //获取2016上半年时间
    var year = new Date().getFullYear();
    var startYdate = year +　'-'+'01' + '-'+'01';
    var lastYdate = year + '-'+ '06' + '-'+ '30';

    intoPage();//执行初始化

    function intoPage(){

        $('#columnBtn').css('background','#0099FF');
        // getColumnHighChartData(startYdate,lastYdate);
        search(valueChart,'','',startYdate,lastYdate);

    }

    var name;
    //查询按钮
    $("#search").bind('click',function(){
        name = $("#s_name").val();
        var lawType = $("#lawType").val();
        var startYdate = $("#start_createTime").val();
        var lastYdate = $("#end_createTime").val();
        search(valueChart,name,lawType,startYdate,lastYdate);
    });
    
    function search(valueChart,name,lawType,startYdate,lastYdate){
        if(valueChart == '2'){
            getPieHighChartData(name,lawType,startYdate,lastYdate)
        }else if(valueChart == '3'){
            getLineHighData(name,lawType,startYdate,lastYdate);
        }else{
            getColumnHighChartData(name,lawType,startYdate,lastYdate);
        }
    }
    
    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        valueChart = $("#columnBtn").attr("data-checked");
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        // getColumnHighChartData(startYdate,lastYdate);
        search(valueChart,'','',startYdate,lastYdate);
        
    });
    
    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        valueChart = $("#pieBtn").attr("data-checked");
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background','#fff');
        $("#lineBtn").css('background','#fff');
        // getPieHighChartData(startYdate,lastYdate);
        search(valueChart,'','',startYdate,lastYdate);
    });



    //线状图按钮
    $("#lineBtn").bind('click',function(){
        valueChart = $("#lineBtn").attr("data-checked");
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        // getLineHighData(startYdate,lastYdate);
        search(valueChart,'','',startYdate,lastYdate);
    });

    //柱状图获取后台数据
    function getColumnHighChartData(name,lawType,startYdate,lastYdate){
        // var categories = ["1月","2月","3月","4月","5月","6月"];
        // var series = [];
        //
        // var gongye = {name: "月份", data: [1,2,3,3,4,3.55]};
        // series.push(gongye);
        //
        // colMchart(categories, series);
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispatchTask_getColumnHighChart.action",
            type:'post',
            data:{name:name,lawType:lawType,startYdate:startYdate,lastYdate:lastYdate},
            dataType:'json',
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y;
                var ylist = [];
                if(list && list.length>0){
                    for (var i=0; i<list.length; i++) {
                        ylist.push(parseInt(list[i]));
                    }
                }
                var preMonth = [];//定义查询月份的数组
                var preValue = [];//定义对应月份为0的一组数据
                var startMonth= startYdate.substring(0,7);
                var strStartMonth = startMonth.replace('-','');

                var endMonth= lastYdate.substring(0,7);
                var strEndMonth= endMonth.replace('-','');

                var startYear = startYdate.substring(0,4);
                var endYear = lastYdate.substring(0,4);
                if(startYear == endYear){
                    for(var i = strStartMonth; i <= strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                }else{
                    var startTime = startYear + '12';
                    var firstTime = endYear +'01';
                    for(var i=strStartMonth;i<=startTime;i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                    for(var i=firstTime; i<=strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                }
                console.log(preMonth);
                console.log(preValue);
                var month = categories;//后台取出的2组数据
                var value = ylist;
                if(month && month.length >0){
                    for(var i = 0; i < month.length;i++){
                        var m = month[i];
                        for (var j = 0; j < preMonth.length; j++){
                            if (m == preMonth[j]) {
                                preValue[j] = value[i];
                            }
                        }

                    }
                }
                console.log(preMonth);
                console.log(preValue);

                var series1 = {name: "执法次数", data:preValue};
                series.push(series1);
                colMchart(preMonth,series,startYdate,lastYdate);
            }
        });
    }

    //饼状图获取后台数据
    function getPieHighChartData(name,lawType,startYdate,lastYdate){
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispatchTask_getColumnHighChart.action",
            type: 'post',
            data: {name:name,lawType:lawType,startYdate: startYdate, lastYdate: lastYdate},
            dataType: 'json',
            success:function(data){
                var categories = data['x'];
                var series1 = data['y'];
                var series = [{
                    name:"执法次数:(次)",
                    data:[]
                }];
                var preMonth = [];//定义查询月份的数组
                var preValue = [];//定义对应月份为0的一组数据
                var startMonth= startYdate.substring(0,7);
                var strStartMonth = startMonth.replace('-','');

                var endMonth= lastYdate.substring(0,7);
                var strEndMonth= endMonth.replace('-','');

                var startYear = startYdate.substring(0,4);
                var endYear = lastYdate.substring(0,4);
                if(startYear == endYear){
                    for(var i = strStartMonth; i <= strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                }else{
                    var startTime = startYear + '12';
                    var firstTime = endYear +'01';
                    for(var i=strStartMonth;i<=startTime;i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                    for(var i=firstTime; i<=strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                }
                console.log(preMonth);
                console.log(preValue);
                var month = categories;//后台取出的2组数据
                var value = series1;
                if(month && month.length>0){
                    for(var i = 0; i < month.length;i++){
                        var m = month[i];
                        for (var j = 0; j < preMonth.length; j++){
                            if (m == preMonth[j]) {
                                preValue[j] = value[i];
                            }
                        }

                    }
                }
                console.log(preMonth);
                console.log(preValue);

                for (var i = 0; i < preValue.length; i++) {
                    series[0].data.push({name:preMonth[i],y: parseInt(preValue[i])});
                }
                pieMchart(series,startYdate,lastYdate);
            }
        });

    }
    
    //线状图获取后台数据
    function getLineHighData(name,lawType,startYdate,lastYdate){
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispatchTask_getColumnHighChart.action",
            type:"post",
            data:{name:name,lawType:lawType,startYdate:startYdate,lastYdate:lastYdate},
            dataType:"json",
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y;
                var ylist = [];
                if(list && list.length>0){
                    for (var i=0; i<list.length; i++) {
                        ylist.push(parseInt(list[i]));
                    }
                }

                var preMonth = [];
                var preValue = [];
                var startMonth= startYdate.substring(0,7);
                var strStartMonth = startMonth.replace('-','');

                var endMonth= lastYdate.substring(0,7);
                var strEndMonth= endMonth.replace('-','');

                var startYear = startYdate.substring(0,4);
                var endYear = lastYdate.substring(0,4);
                if(startYear == endYear){
                    for(var i = strStartMonth; i <= strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                }else{
                    var startTime = startYear + '12';
                    var firstTime = endYear +'01';
                    for(var i=strStartMonth;i<=startTime;i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                    for(var i=firstTime; i<=strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                }
                console.log(preMonth);
                console.log(preValue);
                var month = categories;
                var values = ylist;
                if(month && month.length>0){
                    for(var i=0;i< month.length;i++){
                        var m = month[i];
                        for(var j=0; j<preMonth.length;j++){
                            if(m==preMonth[j]){
                                preValue[j] = values[i];
                            }
                        }
                    }
                }
                console.log(preMonth);
                console.log(preValue);


                var series1 = {name: "执法次数", data:preValue};
                series.push(series1);
                lineMchart(preMonth,series,startYdate,lastYdate);

            }
        })
    }


    //柱状图highchart
    function colMchart(preMonth, series,startYdate,lastYdate){
        if(startYdate == '2016-01-01'){
            titleSub= '2016上半年执法统计'
        }else{
            titleSub = startYdate+'月至'+lastYdate+'月执法统计';
        }
        highchart.highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: titleSub
            },
            xAxis: {
                categories: preMonth,
                title:{
                    text:'月份'
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '数量'
                }
            },
            plotOptions: {
                column: {
                    dataLabels: {
                        enabled: true
                    }
                },
                series : {
                    cursor: 'pointer',
                    events : {
                        click: function(e) {
                            console.log(e.point.category);
                            $("#lawListForm").modal('show');
                            var pointTime = e.point.category;
                            var firstTime = pointTime + "-"+"01";
                            var lastTime = pointTime + "-"+"31";
                            initlawTable(firstTime,lastTime);
                        }
                    }
                }

            },
            legend: {
                enabled: true
            },
            
            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}月</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} 次</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
            },
            exporting: {
                enabled:false
            },
            series: series
        });

    }

    //饼状图highChart
    function pieMchart(series,startYdate,lastYdate){
        if(startYdate == '2016-01-01'){
            titleSub= '2016上半年执法统计'
        }else{
            titleSub = startYdate+'月至'+lastYdate+'月执法统计';
        }
        highchart.highcharts({
            chart: {
                type: 'pie'
            },
            title: {
                text: titleSub
            },
            // tooltip: {
            //     pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            // },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}月</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    },
                    showInLegend: true
                },
                series : {
                    cursor: 'pointer',
                    events : {
                        click: function(e) {
                            console.log(e.point.name);
                            $("#lawListForm").modal('show');
                            var pointTime = e.point.category;
                            var firstTime = pointTime + "-"+"01";
                            var lastTime = pointTime + "-"+"31";
                            initlawTable(firstTime,lastTime);
                        }
                    }
                }
            },
            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}月</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} 家</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
            },
            exporting: {
                enabled:false
            },
            series: series
        });
    }
    

    //线状图highChart
    function lineMchart(preMonth,series,startYdate,lastYdate){
        if(startYdate == '2016-01-01'){
            titleSub= '2016上半年执法统计'
        }else{
            titleSub = startYdate+'月至'+lastYdate+'月执法统计';
        }
        highchart.highcharts({
            chart: {
                type: 'line'
            },
            title: {
                text: titleSub
            },
            // subtitle: {
            //     text: 'Source: WorldClimate.com'
            // },
            xAxis: {
                categories: preMonth,
                title:{
                    text:'月份'
                }
            },
            yAxis: {
                title: {
                    text: '数量'
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    }
                },
                series : {
                    cursor: 'pointer',
                    events : {
                        click: function(e) {
                            console.log(e.point.category);
                            $("#lawListForm").modal('show');
                            var pointTime = e.point.category;
                            var firstTime = pointTime + "-"+"01";
                            var lastTime = pointTime + "-"+"31";
                            initlawTable(firstTime,lastTime);
                        }
                    }
                }

            },
            legend: {
                enabled: true
            },
            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}月</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} 次</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
            },
            exporting: {
                enabled:false
            },
            series: series

        });
    }

    /********************  查询执法管理列表  ********************/
    var lawTable = $('#lawTable');
    function initlawTable(firstTime,lastTime) {
        lawTable.bootstrapTable('destroy');
        lawTable.bootstrapTable({
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            sidePagination:"server",
            url: rootPath+"/action/S_dispatch_DispatchTask_list.action?firstTime="+firstTime+"&lastTime="+lastTime,
            method:'post',
            pagination:true,
            clickToSelect:true,//单击行时checkbox选中
            queryParams:pageUtils.localParams,
            columns: [
                {
                    title:"全选",
                    checkbox: true,
                    align: 'center',
                    radio:false,  //  true 单选， false多选
                    valign: 'middle'
                },
                {
                    title: 'ID',
                    field: 'id',
                    align: 'center',
                    valign: 'middle',
                    sortable: false,
                    visible:false
                },
                {
                    title: '事件时间',
                    field: 'eventTime',
                    sortable: false,
                    align: 'center',
                    editable: false,
                    formatter:function (value, row, index) {
                        return pageUtils.sub16(value);
                    }
                },
                {
                    title: '接电人',
                    field: 'answer',
                    editable: false,
                    sortable: false,
                    align: 'center',
                    visible:false
                },

                {
                    title: '企业名称',
                    field: 'enterpriseName',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },

                {
                    title: '信息来源',
                    field: 'source',
                    editable: false,
                    sortable: false,
                    align: 'center',
                    formatter:function (value, row, index) {
                        if(1==value){
                            value="12369"
                        }else if (2==value){
                            value="区长热线"
                        }else if (3==value){
                            value="市长热线"
                        }else if (4==value){
                            value="现场监察"
                        }else if (0==value){
                            value="监控中心"
                        }
                        return value;
                    }
                },
                {
                    title: '所属网格',
                    field: 'blockName',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    field: 'reason',
                    title: '原因',
                    sortable: false,
                    align: 'center',
                    editable: false,
                    formatter:function (value, row, index) {
                        if(1==value){
                            value="异常"
                        }else if(2==value){
                            value="超标"
                        }
                        return value;
                    }
                },
                {
                    field: 'envProStaPersonNameList',
                    title: '发送人',
                    sortable: false,
                    align: 'center',
                    editable: false
                },

                {
                    field: 'monitorCaseId',
                    sortable: false,
                    align: 'center',
                    editable: false,
                    visible:false
                },
                {
                    field: 'overValue',
                    title: '超标值',
                    sortable: false,
                    align: 'center',
                    editable: false,
                    visible:false
                },
                {
                    field: 'thrValue',
                    title: '超标阀值',
                    sortable: false,
                    align: 'center',
                    editable: false,
                    visible:false
                },
                {
                    field: 'sendRemark',
                    title: '备注',
                    sortable: false,
                    align: 'center',
                    editable: false,
                    visible:false
                }
            ]
        });
        // sometimes footer render error.
        setTimeout(function () {
            lawTable.bootstrapTable('resetView');
        }, 200);


        $(window).resize(function () {
            // 重新设置表的高度
            lawTable.bootstrapTable('resetView', {
                height: pageUtils.getTableHeight()
            });
        });
    }



});
