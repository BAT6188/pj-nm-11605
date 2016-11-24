/**
 * Created by Administrator on 2016/10/10.
 */
//@ sourceURL=excessive_statistics.js
$(function(){

    var highchart = $("#container");
    var year = new Date().getFullYear();
    var startYdate = year +　'-'+'01' + '-'+'01';
    var lastYdate = year + '-'+ '06' + '-'+ '30';



    initPage();//执行初始化
    var valueChart = '1';
    function initPage(){

        //初始化日期组件
        $('.form_datetime').datetimepicker({
            language:   'zh-CN',
            autoclose: 1,
            startView: 3,//月视图
            minView: 3
        });

        $('#columnBtn').css('background','#0099FF');
        // getColumnHighChartData('',startYdate,lastYdate);
        search(valueChart,'',startYdate,lastYdate);
    }

    //查询按钮
    $("#search").bind('click',function(){
        var sName = $("#s_name").val();
        var startYdate = $("#start_createTime").val();
        var lastYdate = $("#end_createTime").val();
        search(valueChart,sName,startYdate,lastYdate);
    });

    function search(valueChart,sName,startYdate,lastYdate) {
        if(valueChart == '2'){
            getPieHighChartData(sName,startYdate,lastYdate)
        }else if(valueChart == '3'){
            getLineHighData(sName,startYdate,lastYdate);
        }else{
            getColumnHighChartData(sName,startYdate,lastYdate);
        }
    }

    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        valueChart = $("#columnBtn").attr("data-checked");
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,'',startYdate,lastYdate);

        // getColumnHighChartData('',startYdate,lastYdate);
    });
    
    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        valueChart = $("#pieBtn").attr("data-checked");
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background-color','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,'',startYdate,lastYdate);

        // getPieHighChartData('',startYdate,lastYdate);

    });

    //线状图按钮
    $("#lineBtn").bind('click',function(){
        valueChart = $("#lineBtn").attr("data-checked");
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        search(valueChart,'',startYdate,lastYdate);

        // getLineHighData(valueChart,'',startYdate,lastYdate);
    });

    /**
     * 柱状图获取数据
     */
    function getColumnHighChartData(sName,startYdate,lastYdate){
        $.ajax({
            url:rootPath + "/action/S_port_PortStatusHistory_getColumnHighChart.action",
            type:"post",
            data:{sName:sName,startYdate:startYdate,lastYdate:lastYdate},
            dataType:"json",
            success:function (data) {
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

                var series1 = {name: "超标次数", data:preValue};
                series.push(series1);
                colMchart(preMonth,series,startYdate,lastYdate);
            }
        });
    }

    //获取饼状图数据
    function getPieHighChartData(sName,startYdate,lastYdate){
        $.ajax({
            url: rootPath + "/action/S_port_PortStatusHistory_getColumnHighChart.action",
            type:"post",
            data:{sName:sName,startYdate:startYdate,lastYdate:lastYdate},
            dataType:"json",
            success:function(data){
                var categories = data['x'];
                var series1 = data['y'];
                var series = [{
                    name:"超标次数:(次)",
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

    //获取线状图数据
    function getLineHighData(sName,startYdate,lastYdate){
        $.ajax({
            url: rootPath + "/action/S_port_PortStatusHistory_getColumnHighChart.action",
            type:"post",
            data:{name:sName,startYdate:startYdate,lastYdate:lastYdate},
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


                var series1 = {name: "超标次数", data:preValue};
                series.push(series1);
                lineMchart(preMonth,series,startYdate,lastYdate);

            }
        })
    }

    /**
     * 柱状图highchart
     * @param categories
     * @param series
     */
    function colMchart(preMonth,series,startYdate,lastYdate) {
        if(startYdate == '2016-01-01'){
            titleSub= '2016年上半年超标统计'
        }else{
            titleSub = startYdate+'至'+lastYdate+'超标统计';
        }

        highchart.highcharts({
            chart: {
                type: 'column',
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
                allowDecimals:false,//是否允许为小数
                min: 0,
                title: {
                    text: '数量'
                }
            },
            tooltip: {
                // pointFormatter: function() {
                //     return '<span style="color:{'+this.series.color+'};width: 10%;"><b>'+(this.x+1)+'</b>月</span> '+
                //         this.series.name+': <b>'+this.y+'</b><br/>.'
                // }
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}月</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} 次</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
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
                            $("#excessiveListForm").modal('show');
                            var pointTime = e.point.category;
                            var firstTime = pointTime + "-"+"01";
                            var lastTime = pointTime + "-"+"31";
                            initlawTable(firstTime,lastTime);
                        }
                    }
                }
            },
            exporting: {
                enabled:false
            },
            series: series
        });
    }

    /**
     * 饼状图highchart
     */
    function pieMchart(series,startYdate,lastYdate){
        if(startYdate == '2016-01-01'){
            titleSub= '2016年上半年超标统计'
        }else{
            titleSub = startYdate+'至'+lastYdate+'超标统计';
        }

        highchart.highcharts({
            chart: {
                type: 'pie'
            },
            title: {
                text: titleSub
            },
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
                series: {
                    cursor: 'pointer',
                    events: {
                        click: function(e) {
                            console.log(e.point.name);
                            $("#excessiveListForm").modal('show');
                            var pointTime = e.point.name;
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

    /**
     * 折线图highchart
     */
    function lineMchart(preMonth,series,startYdate,lastYdate){
        if(startYdate == '2016-01-01'){
            titleSub= '2016年上半年超标统计'
        }else{
            titleSub = startYdate+'至'+lastYdate+'超标统计';
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
                            console.log("111");
                            $("#excessiveListForm").modal('show');
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


    /********************  查询超标异常记录列表  ********************/
    var excessiveTable = $('#excessiveTable');
    function initlawTable(firstTime,lastTime) {
        excessiveTable.bootstrapTable('destroy');
        excessiveTable.bootstrapTable({
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            sidePagination:"server",
            url: rootPath+"/action/S_port_PortStatusHistory_list.action?startTime="+firstTime+"&endTime="+lastTime+"&strStatus="+"1",
            method:'post',
            pagination:true,
            clickToSelect:true,//单击行时checkbox选中
            queryParams:pageUtils.localParams,
            columns: [
                {
                    title:"全选",
                    checkbox: true,
                    align: 'center',
                    radio:true,  //  true 单选， false多选
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
                    title: '企业名称',
                    field: 'enterpriseName',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '标题',
                    field: 'res_title',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '状态开始时间',
                    field: 'startTime',
                    editable: false,
                    sortable: false,
                    align: 'center',
                    formatter:function (value, row, index) {
                        return pageUtils.sub16(value);
                    }
                },
                {
                    title: '状态',
                    field: 'portStatus',
                    editable: false,
                    sortable: false,
                    align: 'center',
                    formatter:function (value, row, index) {
                        if(0==value){
                            value="正常"
                        }else if (1==value){
                            value="超标"
                        }else if (2==value){
                            value="异常"
                        }
                        return value;
                    }
                },
                {
                    field: 'solution',
                    title: '解决方案',
                    sortable: false,
                    align: 'center',
                    editable: false
                }

            ]
        });
        // sometimes footer render error.
        setTimeout(function () {
            excessiveTable.bootstrapTable('resetView');
        }, 200);

        $(window).resize(function () {
            // 重新设置表的高度
            excessiveTable.bootstrapTable('resetView', {
                height: pageUtils.getTableHeight()
            });
        });
    }


});


