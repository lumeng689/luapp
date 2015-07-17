/**
 * Created by lum on 2015/7/15.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'ext',
    'marionette',
    'juicer',
    'artDialog',
    'templates'
], function ($, _, Backbone, ext, Marionette, juicer, artDialog, Templates) {
    'use strict';

    var userList = Marionette.ItemView.extend({
        //el: "#footer",
        template: Templates.user.list,
        events: {
            'click .btn': 'clickBtn'
        },
        initialize: function () {
            console.log('...user list init....');
            Backbone.trigger('selMenu', 'user');
        },
        clickBtn: function () {
            var d = dialog({
                title: '欢迎',
                content: '欢迎使用 artDialog 对话框组件！'
            });
            d.showModal();
        }
    });

    debugger;

    return userList;
});