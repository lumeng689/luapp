/**
 * Created by lum on 2015/7/14.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'juicer',
    'templates'
], function ($, _, Backbone, Marionette, juicer, Templates) {
    'use strict';

    var header = Marionette.ItemView.extend({
        //el: "#header",
        template: Templates.header,
        ui: {
            menuItem: '#menu>li'
        },
        events: {
            "click @ui.menuItem": "clickMenu",
        },
        //collectionEvents: {
        //    all: 'render'
        //},
        initialize: function () {
            debugger;
            //var me = this;
            //this.collection.on('sync', function () {
            //    debugger;
            //    me.render();
            //});
            //debugger;
            //this.collection.bindAll(this.render, this);
            //this.bind(this.collection, 'all', this.render, this);
            //this.collection.fetch();
            this.listenTo(Backbone, 'selMenu', this.selectMenu);
            var promise = this.collection.fetch();
            $.when(promise).then(_.bind(this.render, this));
        },
        serializeData: function () {
            if (!this.collection.models) {
                return {};
            }
            var menu = [];

            _.each(this.collection.models, function (element, index, list) {
                menu.push(element.toJSON());
            });

            var url = window.location.hash;
            if (url && url.length > 0) {
                url = url.substr(1);
            }
            return {items: menu, selItem: url};
        },
        onRender: function () {
            console.log('--====on render====--');
        },
        clickMenu: function (event) {
            this.ui.menuItem.removeClass('active');
            $(event.target).parent('li').addClass('active');
        },
        selectMenu: function (data) {
            console.log('..select menu....' + data);
        }
    });
    return header;
});