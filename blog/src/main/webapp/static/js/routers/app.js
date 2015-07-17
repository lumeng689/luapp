/**
 * Created by lum on 2015/7/16.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    '../views/app/app_index'
], function ($, _, Backbone, Marionette, AppIndexView) {
    'use strict';

    var appRouter = Marionette.AppRouter.extend({
        routes: {
            'app': 'appIndex',
            'app/:id': 'viewApp',
            'app/:id/edit': 'editApp'
        },
        initialized: function () {
            debugger;
            console.log('---------init app router----------');
        },
        appIndex: function () {
            console.log('---------enter app list----------');
            debugger;
            app.main.show(new AppIndexView());
        },
        viewApp: function () {
            console.log('---------enter view app----------');
        },
        editApp: function () {
            console.log('---------enter edit app----------');
        }
    });

    return appRouter;
});