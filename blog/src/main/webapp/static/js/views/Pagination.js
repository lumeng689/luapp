/**
 * Created by lum on 2015/7/17.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'templates'
], function ($, _, Backbone, Templates) {
    'use strict';

    var pagination = {
        events: {
            'click a.first': "first",
            'click a.last': "last",
            'click a.pre': "pre",
            'click a.next': "next"
        },
        initialize: function () {
            console.log('------pagination init------');
        },
        first: function () {
            console.log('------*go to first*------');
        },
        last: function () {
            console.log('------*go to last*------');
        },
        pre: function () {
            console.log('------*go to pre*------');
        },
        next: function () {
            console.log('------*go to next*------');
        }
    };
});