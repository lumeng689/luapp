/**
 * Created by lum on 2015/7/10.
 */
define([
    'backbone'], function (Backbone) {
    'use strict';

    var menuItem = Backbone.Model.extend({
        defaults: {
            label: '',
            url: ''
        },
        initialize: function () {
        }
    });

    return menuItem;
});