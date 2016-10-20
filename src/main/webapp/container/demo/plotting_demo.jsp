<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link href="<%=request.getContextPath()%>/common/scripts/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/demo/easyform/js/jquery-2.1.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/common/scripts/bootstrap-3.3.7/js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/common/scripts/plotting/raphael.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/common/scripts/plotting/plotting.js"></script>
    <script>
        var rootPath = '<%=request.getContextPath()%>';
    </script>
    <title>平面图</title>
</head>
<body>
<button data-type="point">Point</button>
<button data-type="polygon">Polygon</button>
<button data-type="circle">Circle</button>
<button data-type="rectangle">Rectangle</button>
<button data-type="select">Select</button>
<button data-type="delete">Delete</button>
<button data-type="pan">Pan</button>
<button data-type="get-data">Get Data</button>
<button data-type="load-data">Load Data</button>
<button data-type="zoom-origin">Original Size</button>
<button data-type="zoom-fit">Fit</button>
<button data-type="print-data">Print Data</button>
<div id="paper" style="margin-left: 30px;"></div>
</body>
<script type="text/javascript">
    jQuery(function ($) {
        var plotting = $('#paper').plotting({
            action: 'plotting',
            bg: rootPath+'/action/S_attachment_Attachment_download.action?id=150e91318fae4253b7c7a15036efabe8',
            icon:{
                size:32,
                url:rootPath+"/common/gis/images/markers/mark.png",
                offset:{
                    left: 0,
                    top: 0
                }
            },
            width: 640,
            height: 480
        }).data('Plotting');

        $('button').click(function (e) {
            var act = $(this).data('type');
            if (act.indexOf('-') === -1) {
                plotting.mode($(this).data('type'));
            } else {
                switch (act) {
                    case 'zoom-origin':
                        plotting.zoom(1);
                        break;
                    case 'zoom-fit':
                        plotting.zoom(0);
                        break;
                    case 'print-data':
                        plotting.forEach(function (el) {
                            console.log(el);
                        });
                        break;
                    case 'get-data':
                        var data = plotting.data();
                        console.log(JSON.stringify(data));
                        break;
                    case 'load-data':
                        data = [{
                            "x": 275,
                            "y": 132,
                            "width": 32,
                            "height": 32,
                            "src": rootPath+"/common/gis/images/markers/mark.png",
                            "type": "image",
                            "attrs": {
                                "id": "flash",
                                "foo": "bar"
                            }
                        }, {
                            "x": 328,
                            "y": 181,
                            "width": 32,
                            "height": 32,
                            "src": "icon.png",
                            "type": "image"
                        }, {
                            "x": 200,
                            "y": 164,
                            "width": 45,
                            "height": 37,
                            "rx": 0,
                            "ry": 0,
                            "fill": "#ff0",
                            "stroke": "#f00",
                            "type": "rect"
                        }, {
                            "cx": 129,
                            "cy": 42,
                            "r": 29.206163733020468,
                            "fill": "none",
                            "stroke": "#000",
                            "type": "circle"
                        }, {
                            "fill": "none",
                            "stroke": "#000",
                            "path": "M793 145 L872 149 L836 204 L746 203 L730 121 L793 145",
                            "type": "path"
                        }];
                        plotting.data(data);

                        setTimeout(function () {
                            plotting.flash(plotting.getById('flash'), '#f00', 500);
                        }, 500);

                        setTimeout(function () {
                            plotting.stopFlash(plotting.getById('flash'));
                        }, 5000);
                        break;
                }
            }
        });
    });
</script>
</html>
