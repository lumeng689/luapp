define([
    'jquery',
    'underscore',
    'backbone',
    'templates'
], function($, _, Backbone, Templates){
    'use strict';

    var loginView = Backbone.View.extend({
        initialize: function() {
            this.compiled = _.template(Templates.menuItemView);
        },
        render: function() {
            this.$el.html(this.compiled());
            return this;
        }
    });

    return loginView;
});