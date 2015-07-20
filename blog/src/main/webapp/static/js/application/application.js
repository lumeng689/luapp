/**
 * Created by lum on 2015/7/20.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'backbone.radio',
    'nprogress',
    './layout'
], function ($, _, Backbone, Marionette, Radio, nprogress, LayoutView) {
    'use strict';

    var routerChannel = Radio.channel('router');
    nprogress.configure({
        showSpinner: false
    });

    var application = Marionette.Application.extend({
        initialize: function () {
            this.$body = $(document.body);
            this.layout = new LayoutView();
            this.layout.render();

            this.listenTo(routerChannel, {
                'before:enter:route': this.onBeforeEnterRoute,
                'enter:route': this.onEnterRoute,
                'error:route': this.onErrorRoute
            });
        },
        onBeforeEnterRoute: function () {
            this.transitioning = true;
            _.defer(function () {
                if (this.transitioning) {
                    nprogress.start();
                }
            });
        },
        onEnterRoute: function () {
            this.transitioning = false;
            this.$body.scrollTop(0);
            nprogress.done();
        },
        onErrorRoute: function () {
            this.transitioning = false;
            nprogress.done(true);
        }
    });

    return application;
});