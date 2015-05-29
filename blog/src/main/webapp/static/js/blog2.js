$(function(){
    // 登录视图
    var LoginView = Backbone.Router.extend({
        // TODO
        el: "#login",
        wrapper: "#wrapper"
    });

    // 定义页面路由
    var BlogRouter = Backbone.Router.extend({
        routes: {
            "login": "login",
            "index": "index",
            "article/:id": "article"
        },

        initialize: function() {
            this.loginView = new LoginView();
        },

        login: function() {
            // TODO
            this.loginView.show();
        },

        index: function() {
            // TODO
        },

        article: function(id) {
            // TODO
        }
    });

    var blogRouter = new BlogRouter();
});