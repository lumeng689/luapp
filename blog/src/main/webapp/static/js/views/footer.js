/**
 * Created by lum on 2015/7/14.
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

    var footer = Marionette.ItemView.extend({
        //el: "#footer",
        template: Templates.footer,
        initialize: function () {
            console.log('...footer init....');
        }
    });

    return footer;
});