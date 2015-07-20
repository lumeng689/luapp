/**
 * Created by lum on 2015/7/20.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'juicer',
    'backbone.service',
    './header',
    '../collections/menu'
], function ($, _, Backbone, Marionette, juicer, Service, HeaderView, Menu) {
    'use strict';

    var HeaderService = Service.extend({
        setup: function (options) {
            this.option.container = options.container;
        },
        start: function () {
            this.collection = new Menu();
            this.view = new HeaderView({collection: this.collection});
            this.container.show(this.view);
        },
        requests: {
            add: 'add',
            remove: 'remove',
            activate: 'activate',
        },
        add: function (model) {
            this.collection.add(model);
        },
        remove: function (model) {
            model = this.collection.findWhere(model);
            this.collection.remove(model);
        },
        activate: function (model) {
            this.collection.invoke('set', 'active', false);
            model = this.collection.findWhere(model);
            if (model) {
                model.set('active', 'true');
            }
        }
    });

    return new HeaderService();
});
