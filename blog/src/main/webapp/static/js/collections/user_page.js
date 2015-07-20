/**
 * Created by lum on 2015/7/20.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'backbone.paginator',
], function ($, _, Backbone, Paginator) {
    'use strict';

    var userPages = Backbone.PageableCollection.extend({
        url: './user',
        state: {},
        queryParams: {},
        parseState: function () {
        },
        parseRecords: function () {
        }
    });

    return userPages;
});