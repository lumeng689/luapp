define([
    'jquery',
    'underscore',
    'backbone',
    '../views/index'
],function($, _, Backbone, IndexView){
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

        initialize: function() {
            this.indexView = new IndexView();
        },

        blogView: {
            show: function(view, callback) {
                if (this.currentView) {
                    this.currentView.close();
                }

                this.currentView = view;
                this.currentView.render(callback);
                $("#main").hide().html(this.currentView.el).fadeIn();
            }
        },

        index: function() {
            // TODO
            if (!this.indexView) {
                this.indexView = new IndexView();
            }
            this.blogView.show(this.indexView);
        },

        login: function() {
            // TODO
            this.blogView.show();
        },

        article: function() {
            // TODO
        },

        addArticle: function() {
            // TODO
        },

        viewArticle: function(id) {
            // TODO
        },

        editArticle: function() {
            // TODO
        }
    });

    return BlogRouter;
});