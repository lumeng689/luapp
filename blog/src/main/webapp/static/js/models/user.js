/**
 * Created by lum on 2015/7/22.
 */
define([
    'backbone'], function (Backbone) {
    'use strict';

    var urlError = function () {
        throw new Error('A "url" property or function must be specified');
    };

    var user = Backbone.Model.extend({
        defaults: {
            name: '',
            job: '',
            desc: ''
        },
        urlRoot: '/user',
        initialize: function () {
        },
        validate: function (attrs) {
            var attrs = attrs || {};
            return '';
        },
        url: function () {
            var base =
                _.result(this, 'urlRoot') ||
                _.result(this.collection, 'url') ||
                urlError();
            if (this.isNew()) return base.replace(/([^\/])$/, '$1/') + 'add';
            var id = this.id || this.attributes[this.idAttribute];
            return base.replace(/([^\/])$/, '$1/') + encodeURIComponent(id);
        },
        parse: function (resp, options) {
            return resp.data;
        }
    });

    return user;
});