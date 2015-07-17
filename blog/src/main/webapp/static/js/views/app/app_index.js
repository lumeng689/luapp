/**
 * Created by lum on 2015/7/16.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'juicer',
    'artDialog',
    'templates',
    'jqueryFileUpload'
], function ($, _, Backbone, Marionette, juicer, artDialog, Templates) {
    'use strict';

    var app = Marionette.ItemView.extend({
        //el: "#footer",
        template: Templates.app.index,
        events: {
            'click .btn.click': 'clickBtn',
            'click .btn.ret': 'retBtn'
        },
        ui: {
            uploadForm: '#uploadForm'
        },
        initialize: function () {
            console.log('init app.........');
            this.on('attach', this.initCtrl);
        },
        initCtrl: function () {
            debugger;
            $('#fileupload').fileupload({
                url: '/app/upload'
            });
        }
    });

    return app;
});