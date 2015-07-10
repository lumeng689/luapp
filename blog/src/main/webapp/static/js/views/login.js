define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/login.html'
], function($, _, Backbone, tpl){
    'use strict';

    var loginView = Backbone.View.extend({
        initialize: function() {
            this.compiled = _.template(tpl);
        },
        render: function() {
            this.$el.html(this.compiled());
            return this;
        }
    });

    return loginView;
});