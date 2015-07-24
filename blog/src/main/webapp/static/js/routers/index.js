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
        initialize: function (options) {
            this.container = options.container;
            console.log('---------init index router----------');
            this.on("route", this.onBeforeEnter, this);
        },
        onBeforeEnter: function(){
            console.log('---------on before enter----------');
        },
        index: function () {
            this.view = new IndexView();
            console.log('---------enter index list----------');
            this.container.show(this.view);
        },
    });

    return indexRouter;
});