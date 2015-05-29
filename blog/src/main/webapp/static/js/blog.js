define([
    'jquery',
    'underscore',
    'backbone',
    './routers/router'
],function($, _, Backbone, BlogRouter){
    'use strict';

    var initialize =function() {
        var router = new BlogRouter();
        Backbone.history.start();
    }

    return {
        initialize: initialize
    }
});