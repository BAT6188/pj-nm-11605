/**
 * Created by Administrator on 2016/10/12.
 */
//@ sourceURL=sewage_declaration.js
$(function(){
    var highchart = $("#container");

    var year = new Date().getFullYear();
    var startYdate = year +　'-'+'01' + '-'+'01';
    var lastYdate = year + '-'+ '06' + '-'+ '30';
    
    //初始化日期组件
    $('.form_datetime').datetimepicker({
        language:   'zh-CN',
        autoclose: 1,
        startView: 3,//月视图
        minView: 3
    });
    
    initPage();//页面初始化
    var valueChart = '1';

    function initPage(){
        $('#columnBtn').css('background','#0099FF');
        search(valueChart,'',startYdate,lastYdate);
        // sewageColumnCahrt(startYdate,lastYdate);
    }

    var name;
    //查询按钮
    $("#search").bind('click',function(){
        name = $("#s_name").val();
        var startYdate = $("#start_createTime").val();
        var lastYdate = $("#end_createTime").val();
        search(valueChart,name,startYdate,lastYdate);
    });

    function search(valueChart,name,startYdate,lastYdate){
        if(valueChart == '2'){
            sewagePieChart(name,startYdate,lastYdate);
        }else if(valueChart == '3'){
            sewageLineCahrt(name,startYdate,lastYdate);
        }else{
            sewageColumnCahrt(name,startYdate,lastYdate);
        }


    }

    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        valueChart = $("#columnBtn").attr("data-checked");
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,'',startYdate,lastYdate);
    });

    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        valueChart = $("#pieBtn").attr("data-checked");
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background-color','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,'',startYdate,lastYdate);
    });


    //线状图按钮
    $("#lineBtn").bind('click',function(){
        valueChart = $("#lineBtn").attr("data-checked");
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        search(valueChart,'',startYdate,lastYdate);
    });

    //柱状图获取后台数据
    function sewageColumnCahrt(name,startYdate,lastYdate){
        $.ajax({
            url:rootPath + "/action/S_exelaw_PollutantPayment_getSewageColumn.action",
            type:'post',
            dataType:'json',
            data:{name:name,startYdate:startYdate,lastYdate:lastYdate},
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y1;
                var ylist = [];
                var list2 = data.y2;
                var ylist2 = [];
                if(list && list.length>0){
                    for (var i=0; i<list.length; i++) {
                        ylist.push(parseInt(list[i]));
                    }
                }
                if(list2 && list.length>0){
                    for (var i=0; i<list2.length; i++) {
                        ylist2.push(parseInt(list2[i]));
                    }
                }
                var preMonth = [];//定义查询月份的数组
                var preValue1 = [];//定义对应月份为0的一组数据
                var preValue2 = [];//定义对应月份为0的一组数据
                
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
                        preValue1.push(0);
                        preValue2.push(0);
                    }
                }else{
                    var startTime = startYear + '12';
                    var firstTime = endYear +'01';
                    for(var i=strStartMonth;i<=startTime;i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue1.push(0);
                        preValue2.push(0);
                    }
                    for(var i=firstTime; i<=strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue1.push(0);
                        preValue2.push(0);
                    }
                }
                
                var month = categories;//后台取出的2组数据
                var value = ylist;
                var arr_value = ylist2;
                if(month && month.length>0){
                    for(var i = 0; i < month.length;i++){
                        var m = month[i];
                        for (var j = 0; j < preMonth.length; j++){
                            if (m == preMonth[j]) {
                                preValue1[j] = value[i];
                            }
                        }
                        for (var k = 0; k < preMonth.length; k++){
                            if (m == preMonth[k]) {
                                preValue2[k] = arr_value[i];
                            }
                        }

                    }
                }
                

                var series1 = {name: "已缴费",color: 'rgb(124, 181, 236)', data:preValue1};
                var series2 = {name: "未缴费",color:'#FF8800', data:preValue2};
                series.push(series1);
                series.push(series2);
                colMchart(preMonth,series,startYdate,lastYdate);
            }
        });
        // var categories = ['1月', '2月', '3月', '4月', '5月','6月'];
        // var series = [];
        //
        // var yijiaofei = {name: '已缴费企业', color: 'rgb(124, 181, 236)', data: [40, 36, 20, 40, 30,43]};
        // var weijiaofei = {name: '未缴费企业', color:'#FF8800', data: [3, 7, 14, 3, 13,2]};
        // series.push(yijiaofei);
        // series.push(weijiaofei);
        // colMchart(categories,series);

    }
    
    
    //饼状图获取后台数据
    function sewagePieChart(name,startYdate,lastYdate){
        $.ajax({
            url: rootPath + "/action/S_exelaw_PollutantPayment_getSewagePie.action",
            type:'post',
            dataType:'json',
            data:{name:name,startYdate:startYdate,lastYdate:lastYdate},
            success:function(data){
                var categories = data['x'];
                var series1 = data['y'];
                var series = [{
                    name:"已缴费企业:(家)",
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
                pieMchart(series,startYdate,lastYdate)
            }
        });
    }

    
    
    
    //线状图获取后台数据
    function sewageLineCahrt(name,startYdate,lastYdate){
        $.ajax({
            url:rootPath + "/action/S_exelaw_PollutantPayment_getSewageColumn.action",
            type:'post',
            dataType:'json',
            data:{name:name,startYdate:startYdate,lastYdate:lastYdate},
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y1;
                var ylist = [];
                var list2 = data.y2;
                var ylist2 = [];
                if(list && list.length>0){
                    for (var i=0; i<list.length; i++) {
                        ylist.push(parseInt(list[i]));
                    }
                }
                if(list2 && list.length>0){
                    for (var i=0; i<list2.length; i++) {
                        ylist2.push(parseInt(list2[i]));
                    }
                }
                var preMonth = [];//定义查询月份的数组
                var preValue1 = [];//定义对应月份为0的一组数据
                var preValue2 = [];//定义对应月份为0的一组数据
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
                        preValue1.push(0);
                        preValue2.push(0);
                    }
                }else{
                    var startTime = startYear + '12';
                    var firstTime = endYear +'01';
                    for(var i=strStartMonth;i<=startTime;i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue1.push(0);
                        preValue2.push(0);
                    }
                    for(var i=firstTime; i<=strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue1.push(0);
                        preValue2.push(0);
                    }
                }
                var month = categories;//后台取出的2组数据
                var value = ylist;
                var arr_value = ylist2;
                if(month && month.length>0){
                    for(var i = 0; i < month.length;i++){
                        var m = month[i];
                        for (var j = 0; j < preMonth.length; j++){
                            if (m == preMonth[j]) {
                                preValue1[j] = value[i];
                            }
                        }
                        for (var k = 0; k < preMonth.length; k++){
                            if (m == preMonth[k]) {
                                preValue2[k] = arr_value[i];
                            }
                        }

                    }
                }


                var series1 = {name: "已缴费",color: 'rgb(124, 181, 236)', data:preValue1};
                var series2 = {name: "未缴费",color:'#FF8800', data:preValue2};
                series.push(series1);
                series.push(series2);
                lineMchart(preMonth,series,startYdate,lastYdate);


            }
        });


    }
    
    
    
    //柱状图highchart
    function colMchart(preMonth,series,startYdate,lastYdate){
        if(startYdate == '2016-01-01'){
            titleSub= '2016年上半年企业排污申报费用统计'
        }else{
            titleSub = startYdate+'月至'+lastYdate+'月企业排污申报费用统计';
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
                    text: '企业数量'
                    // align: 'high'
                },
                labels: {
                    overflow: 'justify'
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
                            $("#sewageListForm").modal('show');
                            var pointTime = e.point.category;
                            var firstTime = pointTime + "-"+"01";
                            var lastTime = pointTime + "-"+"31";
                            var paymentStatus = 1;
                            var unpaidStatus = 0;
                            initTable2(firstTime,lastTime,paymentStatus,unpaidStatus);
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
            titleSub= '2016年上半年企业排污申报费用统计'
        }else{
            titleSub = startYdate+'月至'+lastYdate+'月企业排污申报费用统计';
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
                series : {
                    cursor: 'pointer',
                    events : {
                        click: function(e) {
                            console.log(e.point.name);
                            $("#sewageListForm").modal('show');
                            var pointTime = e.point.name;
                            var firstTime = pointTime + "-"+"01";
                            var lastTime = pointTime + "-"+"31";
                            var StrStatus = 1;
                            initTable(firstTime,lastTime,StrStatus);
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


    /**
     * 折线图highchart
     */
    function lineMchart(preMonth,series,startYdate,lastYdate){
        if(startYdate == '2016-01-01'){
            titleSub= '2016年上半年企业排污申报费用统计'
        }else{
            titleSub = startYdate+'月至'+lastYdate+'月企业排污申报费用统计';
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
                            $("#sewageListForm").modal('show');
                            var pointTime = e.point.category;
                            var firstTime = pointTime + "-"+"01";
                            var lastTime = pointTime + "-"+"31";
                            var paymentStatus = 1;
                            var unpaidStatus = 0;
                            initTable2(firstTime,lastTime,paymentStatus,unpaidStatus);
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

    /**============grid 排污申报列表(饼状图)============**/
    var sewageTable = $('#sewageTable');
    function initTable(firstTime,lastTime,StrStatus) {
        sewageTable.bootstrapTable('destroy');
        sewageTable.bootstrapTable({
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            sidePagination:"server",
            url: rootPath+"/action/S_exelaw_PollutantPayment_list.action?startYdate="+firstTime+"&lastYdate="+lastTime+"&StrStatus="+StrStatus,
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
                    field: 'enterpriseId',
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
                    title: '企业法人',
                    field: 'enterpriseAP',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '联系方式',
                    field: 'apPhone',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '缴费金额',
                    field: 'payMoney',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '登记日期',
                    field: 'registDate',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '缴费日期',
                    field: 'payDate',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '距缴费日期',
                    field: 'rangeDays',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '缴费状态',
                    field: 'paymentStatus',
                    sortable: false,
                    align: 'center',
                    editable: false,
                    formatter:function (value, row, index) {
                        if (0==value){
                            value="未缴费"
                        }else if(1==value){
                            value="已缴费"
                        }else if(2==value){
                            value="未按时缴费"
                        }
                        return value;
                    }
                }
            ]
        });
        // sometimes footer render error.
        setTimeout(function () {
            sewageTable.bootstrapTable('resetView');
        }, 200);


        $(window).resize(function () {
            // 重新设置表的高度
            sewageTable.bootstrapTable('resetView', {
                height: pageUtils.getTableHeight()
            });
        });
    }


    /**============grid 排污申报列表(线状图)（柱状图）============**/
    var sewageTable2 = $('#sewageTable2');
    function initTable2(firstTime,lastTime,paymentStatus,unpaidStatus) {
        sewageTable.bootstrapTable('destroy');
        sewageTable.bootstrapTable({
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            sidePagination:"server",
            url: rootPath+"/action/S_exelaw_PollutantPayment_sewagelist.action?firstTime="+firstTime+"&lastTime="+lastTime+"&paymentStatus="+paymentStatus+"&unpaidStatus="+unpaidStatus,
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
                    field: 'enterpriseId',
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
                    title: '企业法人',
                    field: 'enterpriseAP',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '联系方式',
                    field: 'apPhone',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '缴费金额',
                    field: 'payMoney',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '登记日期',
                    field: 'registDate',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '缴费日期',
                    field: 'payDate',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '缴费状态',
                    field: 'paymentStatus',
                    sortable: false,
                    align: 'center',
                    editable: false,
                    formatter:function (value, row, index) {
                        if (0==value){
                            value="未缴费"
                        }else if(1==value){
                            value="已缴费"
                        }else if(2==value){
                            value="未按时缴费"
                        }
                        return value;
                    }
                }
            ]
        });
        // sometimes footer render error.
        setTimeout(function () {
            sewageTable2.bootstrapTable('resetView');
        }, 200);


        $(window).resize(function () {
            // 重新设置表的高度
            sewageTable2.bootstrapTable('resetView', {
                height: pageUtils.getTableHeight()
            });
        });
    }
    
    
});