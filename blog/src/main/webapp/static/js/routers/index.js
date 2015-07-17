/**
 * Created by lum on 2015/7/14.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    '../views/index'
], function ($, _, Backbone, Marionette, IndexView) {
    'use strict';

    var indexRouter = Marionette.AppRouter.extend({
        routes: {
            '': 'index',
            'index': 'index',
        },
        initialized: function () {
            debugger;
            console.log('---------init index router----------');
        },
        index: function () {
            console.log('---------enter index list----------');
            app.main.show(new IndexView());
        },
    });

    return indexRouter;
});