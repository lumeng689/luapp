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
            header: '#header',
            content: '#main',
            footer: '#footer',
            flashes: '.application__flashes',
            overlay: '.application__overlay'
        },
        childEvents: {
            'select:menu': function (childView, menuItem) {
                console.log('child select menu');
            },
            render: function (childView) {
                console.log('child view render.......');
            }
        }
    });

    return layout;
});