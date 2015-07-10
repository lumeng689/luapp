define([
    'jquery',
    'underscore',
    'backbone',
    'text!templates/index.html'
], function($, _, Backbone, tpl){
    'use strict';

    var IndexView = Backbone.View.extend({
        initialize: function() {
            this.compiled = _.template(tpl);
        },
        render: function() {
            this.$el.html(this.compiled());
            return this;
        }
    });

    return IndexView;
});