/**
 * Created by lum on 2015/7/10.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    '../models/post'
], function ($, _, Backbone, Post) {
    'use strict';

    var posts = Backbone.Collection().extend({
        model: Post
    });

    return posts;
});