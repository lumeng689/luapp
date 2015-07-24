<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/include.jsp" %>

<!DOCTYPE html>
<html data-framework="backbonejs">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/js/node_modules/nprogress/nprogress.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/vendor/bootstrap-3.3.5-dist/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/vendor/art-dialog-6.0.4/css/ui-dialog.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/vendor/zTree_v3/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/vendor/backgrid-0.3.5/backgrid.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/vendor/backgrid-0.3.5/paginator/backgrid-paginator.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/vendor/backgrid-0.3.5/text-cell/backgrid-text-cell.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${ctx}/static/js/lib/html5shiv.min.js"></script>
    <script src="${ctx}/static/js/lib/respond.min.js"></script>
    <![endif]-->
    <script>
        window.Global = {
            context_path: '${contextPath}',
            lang: '${lang}'
        };
    </script>
    <style type="text/css">
        .mask {
            position: absolute;
            top: 0px;
            filter: alpha(opacity=60);
            background-color: #777;
            z-index: 1002;
            left: 0px;
            opacity: 0.5;
            -moz-opacity: 0.5;
        }

        .progress2 {
            background-image: url('${ctx}/static/images/progress.gif');
            background-repeat: no-repeat;
            height: 0;
            margin: 110px auto 15px;
            overflow: hidden;
            padding: 230px 0 0;
            position: relative;
            width: 540px;
            z-index: 2;
        }
    </style>
</head>
<body>

<div id="page" class="application container">
</div>
<!-- /container -->
<script language="javascript" type="text/javascript" src="${ctx}/static/js/node_modules/requirejs/require.js"
        data-main="${ctx}/static/js/main"></script>
<script language="javascript" type="text/javascript" src="${ctx}/static/js/lib/ext.js"></script>
<script language="javascript" type="text/javascript"
        src="${ctx}/static/vendor/echarts-2.2.5/source/echarts.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/static/vendor/My97DatePicker/WdatePicker.js"></script>
</body>
</html>
