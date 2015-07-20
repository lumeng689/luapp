define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'juicer',
    'templates'
], function ($, _, Backbone, Marionette, juicer, Templates) {
    'use strict';

    var index = Marionette.ItemView.extend({
        //el: "#main",
        template: Templates.index,
        className: 'index',
        initialize: function () {
            console.log('...index init....');
        }
    });

    return index;

    //var IndexView = Backbone.View.extend({
    //    initialize: function () {
    //        this.compiled = _.template(tpl);
    //    },
    //    render: function () {
    //        this.$el.html(this.compiled());
    //        return this;
    //    },
    //    modelDestroyed: function () {
    //    }
    //});
    //
    //return IndexView;
});