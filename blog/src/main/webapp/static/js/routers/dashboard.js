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
        initialize: function (options) {
            this.container = options.container;
            console.log('---------init dashboard router----------');
        },
        dashboard: function () {
            console.log('---------enter dashboard index----------');
            this.container.show(new DashboardView());
        }
    });

    return dashboardRouter;
});