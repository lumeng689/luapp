/**
 * Created by lum on 2015/7/22.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'juicer',
    'templates',
    '../../models/user',
    '../../application/form_behavior'
], function ($, _, Backbone, Marionette, juicer, Templates, User, FormBehavior) {
    'use strict';

    var user = Marionette.ItemView.extend({
        template: Templates.user.item,
        events: {
            'submit form': 'handleSubmit'
        },
        //behaviors: {
        //    form: {behaviorClass: FormBehavior}
        //},
        templateHelpers: function () {
            return {
                errors: this.errors
            };
        },
        initialize: function (options) {
            //
            this.type = options.type;// add view edit
            this.model = options.model || new User({});
            debugger;
            this.on('attach', this.initView);

            if (!this.model.isNew) {
                var promise = this.model.fetch();
                $.when(promise).then(_.bind(this.render, this));
            }
        },
        initView: function () {
            if (this.type == 'add' || this.type == 'edit') {
                $("#userForm").validate();
            }
        },
        handleSubmit: function () {
            var errors = this.model.validate(this.form);
            if (errors) {
                this.errors = errors;
                this.render();
            } else {
                console.log('====cccccccc');
                this.model.set(this.form);
                debugger;
                this.model.save();
                // return to list
                Backbone.history.navigate('user', {trigger: true});
            }
        },
        warnBeforeDestroy: function () {
            alert("确定要离开本页面吗?");
            this.destroy();
        }
    });

    return user;
})
;