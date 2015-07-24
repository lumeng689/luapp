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
        ui:{
            showMask: '#showMask'
        },
        events: {
            'click @ui.showMask' : 'showMask'
        },
        className: 'index',
        initialize: function () {
            console.log('...index init....');
            debugger;
            this.triggerMethod('select:menu', 'index');
        },
        showMask: function(){
            $("#mask").css("height",$(document).height());
            $("#mask").css("width",$(document).width());
            $("#mask").show();
        },
        hideMask: function(){
            $("#mask").hide();
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