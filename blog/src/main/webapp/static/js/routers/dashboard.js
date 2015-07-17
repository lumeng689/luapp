/**
 * Created by lum on 2015/7/14.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    '../views/dashboard/dashboard'
], function ($, _, Backbone, Marionette, DashboardView) {
    'use strict';

    var dashboardRouter = Marionette.AppRouter.extend({
        routes: {
            'dashboard': 'dashboard'
        },
        initialized: function () {
            console.log('---------init dashboard router----------');
        },
        dashboard: function () {
            console.log('---------enter dashboard index----------');
            app.main.show(new DashboardView());
        }
    });

    return dashboardRouter;
});