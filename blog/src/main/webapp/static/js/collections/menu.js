/**
 * Created by lum on 2015/7/10.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    '../models/menuItem'
], function ($, _, Backbone, MenuItem) {
    'use strict';

    var menu = Backbone.Collection.extend({
        model: MenuItem,
        url: '/menu',
        initialize: function() {
            console.log('--------reset-----');
        }
    });

    return menu;
});