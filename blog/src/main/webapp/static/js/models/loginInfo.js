/**
 * Created by lum on 2015/7/9.
 */
define([
    'jquery',
    'underscore',
    'backbone'], function($, _, Backbone) {
    'use strict';

    var loginInfo = Backbone.Model.extend({
        defaults: {
            'username': '',
            'password': '',
            'validatecode': ''
        },
        validate: function(attrs) {
            if(_.isEmpty(attrs.username)) return '用户名不能为空';
            if(_.isEmpty(attrs.password)) return '密码不能为空';
        },
        initialize: function() {
            console.log('=====loginInfo created=====');
        }
    });

    return loginInfo;
});
