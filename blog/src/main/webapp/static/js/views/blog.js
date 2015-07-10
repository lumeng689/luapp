/**
 * Created by lum on 2015/7/10.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'juicer',
    'text!templates/menu.html'
], function ($, _, Backbone, juicer, menuTpl) {
    'use strict';

    var blogView = Backbone.View.extend({
        el: '#page',
        events: {
            'click .menuItem': 'changePage'
        },
        initialize: function () {
            this.compiled_menus = juicer(menuTpl);
            console.log('*******init blog view****');
            this.render();
        },
        render: function () {
            var me = this;
            this.collection.fetch({
                success: function (collection, resp, options) {
                    console.log('successs!!!!!!!!');
                    var menu = [];

                    _.each(collection.models, function (element, index, list) {
                        menu.push(element.toJSON());
                    });

                    var menuHtml = me.compiled_menus.render({menu: menu, selIndex: 0});
                    console.log("===" + menuHtml);
                    me.$el.find('#menu').append(menuHtml);
                }
            });

            console.log('--------begin render blog view-----------');
        },
        show: function (view, callback) {
            if (this.currentView) {
                this.currentView.close();
            }

            this.currentView = view;
            this.currentView.render(callback);
            $("#main").hide().html(this.currentView.el).fadeIn();
        },
        showDialog: function (view) {
            //ss
        },
        changePage: function(event) {
            this.$el.find('#menu>li').removeClass('active');
            $(event.target).parent('li').addClass('active');
        }
    });

    return blogView;
});