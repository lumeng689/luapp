<%@ page contentType="text/html;charset=utf-8" %>
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
    <link rel="stylesheet" type="text/css" href="${ctx}/static/vendor/bootstrap-3.3.5-dist/css/bootstrap.css">
</head>
<body>

<div id="page" class="container">
    <div class="header clearfix">
        <nav>
            <ul id="menu" class="nav nav-pills pull-right">  <!---
                <li role="presentation" class="active"><a href="#/index">首页</a></li>
                <li role="presentation"><a href="#/index">About</a></li>
                <li role="presentation"><a href="#/index">Contact</a></li>
                <li role="presentation"><a href="#/login">登录</a></li>
                -->
            </ul>
        </nav>
        <h3 class="text-muted">Project name</h3>
    </div>

    <div id="main" class="row"></div>

    <div class="row">
        <div class="col-lg-6">
            <h4>Subheading</h4>
            <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

            <h4>Subheading</h4>
            <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

            <h4>Subheading</h4>
            <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>

        <div class="col-lg-6">
            <h4>Subheading</h4>
            <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

            <h4>Subheading</h4>
            <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

            <h4>Subheading</h4>
            <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; Company 2014</p>
    </footer>

</div> <!-- /container -->

<script data-main="${ctx}/static/js/main" src="${ctx}/static/js/node_modules/requirejs/require.js"></script>
</body>
</html>
