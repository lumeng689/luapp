define([
    'jquery',
    'underscore',
    'backbone',
    '../collections/menu',
    '../views/blog',
    '../views/index',
    '../views/login'
], function ($, _, Backbone, Menu, BlogView, IndexView, LoginView) {
    'use strict';

    var BlogRouter = Backbone.Router.extend({
        routes: {
            '': 'index',
            'login': 'login',
            'index': 'index',
            'article': 'articleList',
            'article/new': "addArticle",
            'article/:id': 'viewArticle',
            'article/:id/edit': 'editArticle'
        },

        initialize: function () {
            var menu = new Menu();
            this.blogView = new BlogView({collection: menu});
        },

        index: function () {
            if (!this.indexView) {
                this.indexView = new IndexView();
            }
            this.blogView.show(this.indexView);
        },

        login: function () {
            if (!this.loginView) {
                this.loginView = new LoginView();
            }
            this.blogView.showDialog(this.loginView);
        },

        article: function () {
            // TODO
        },

        addArticle: function () {
            // TODO
        },

        viewArticle: function (id) {
            // TODO
        },

        editArticle: function () {
            // TODO
        }
    });

    return BlogRouter;
});