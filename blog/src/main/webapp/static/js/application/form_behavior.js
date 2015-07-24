/**
 * Created by lum on 2015/7/22.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'backbone.syphon',
    'juicer',
    'templates'
], function ($, _, Backbone, Marionette, Syphon, juicer, Templates) {
    'use strict';

    var behavior = Marionette.Behavior.extend({
        events: {
            'submit form': 'handleSubmit'
        },
        initialize: function () {
            debugger;
            this.listenTo(this.view.options.model, 'change', this.onChange);
        },
        serialize: function () {
            this.view.form = Syphon.serialize(this);
        },
        deserialize: function () {
            return Syphon.deserialize(this, this.view.form);
        },
        onChange: function () {
            this.view.form = this.view.model.attributes;
            this.deserialize();
        },
        onBeforeRender: function () {
            if (this.view.form) {
                this.serialize();
            }
        },
        onDomRefresh: function () {
            if(!this.view.form) {
                this.view.form = this.view.model.attributes;
            }

            this.deserialize();
        },
        handleSubmit: function (event) {
            event.preventDefault();
            this.view.form = Syphon.serialize(this);
        }
    });

    return behavior;
});