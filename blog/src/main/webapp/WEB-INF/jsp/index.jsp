<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/jsp/include.jsp" %>

<!DOCTYPE html>
<html data-framework="backbonejs">
<head>
    <title>blog</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" type="text/css" href="${ctx}/static/vendor/semantic-ui/semantic.css">
</head>
<body id="home">

<div class="ui masthead segment">
    <div class="ui page grid">
        <div class="column">
            <div class="ui inverted blue menu">
                <div class="header item"><a href="#/index">博客</a></div>
                <div class="right menu">
                    <div class="ui mobile dropdown link item">
                        技术
                        <i class="dropdown icon"></i>
                        <div class="menu">
                            <a class="item">java</a>
                            <a class="item">js</a>
                            <a class="item">css</a>
                        </div>
                    </div>
                    <div class="ui dropdown link item">
                        实践
                        <i class="dropdown icon"></i>

                        <div class="menu">
                            <a class="item">maven</a>
                            <a class="item">gradle</a>
                            <a class="item">前端</a>
                        </div>
                    </div>
                    <a class="item">生活</a>
                    <a class="item" href="#/login">登录</a>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="main" class="ui vertical feature segment">
</div>
<!--
<script type="text/javascript" src="${ctx}/static/third/semantic-ui/semantic.js"></script>
-->
<script data-main="${ctx}/static/js/main" src="${ctx}/static/js/node_modules/requirejs/require.js"></script>
</body>
</html>
