/**
 * Created by lum on 2015/7/20.
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

    var layout = Marionette.LayoutView.extend({
        el: '.application',
        template: Templates.layout,

        regions: {
            header: '.application__header',
            flashes: '.application__flashes',
            content: '.application__content',
            overlay: '.application__overlay'
        }
    });

    return layout;
});