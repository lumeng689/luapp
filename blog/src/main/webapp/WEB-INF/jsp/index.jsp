<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/jsp/include.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>blog</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <link rel="stylesheet" type="text/css" href="${ctx}/static/third/semantic-ui/semantic.css">
    <script type="text/javascript" src="${ctx}/static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/lib/underscore-1.5.0.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/lib/backbone-1.2.0.js"></script>
    <script type="text/javascript" src="${ctx}/static/third/semantic-ui/semantic.js"></script>
</head>
<body id="home">

<div class="ui masthead segment">
    <div class="ui page grid">
        <div class="column">
            <div class="ui inverted blue menu">
                <div class="header item">博客</div>
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
                    <a class="item">登录</a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="right">
        <div class="ui header">你确定要创建一个视频教程专辑吗？</div>
        <p>如果你确定要创建视频专辑专辑请单击确定。</p>
        <p>如果你觉得暂时还不需要创建视频教程专辑，请点击否。</p>
    </div>
</div>
<div class="actions">
    <div class="ui black button">
        不创建
    </div>
    <div class="ui positive right labeled icon button">
        创建
        <i class="checkmark icon"></i>
    </div>
</div>
</div>
</div>

<script type="text/javascript" src="${ctx}/static/js/blog.js"></script>
</body>
</html>
