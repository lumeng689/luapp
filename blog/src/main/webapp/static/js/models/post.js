/**
 * Created by lum on 2015/7/10.
 */
define([
    'jquery',
    'underscore',
    'backbone'], function ($, _, Backbone) {
    'use strict';

    var post = Backbone.Model.extend({
        defaults: {},
        initialize: function () {
        }
    });

    return post;
});