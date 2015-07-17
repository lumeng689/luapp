/**
 * Created by lum on 2015/7/14.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    '../views/user/user_list'
], function ($, _, Backbone, Marionette, UserListView) {
    'use strict';

    var userRouter = Marionette.AppRouter.extend({
        routes: {
            'user': 'user',
            'user/:id': 'viewUser',
            'user/:id/edit': 'editUser'
        },
        initialized: function () {
            debugger;
            console.log('---------init user router----------');
        },
        user: function () {
            console.log('---------enter user list----------');
            app.main.show(new UserListView());
        },
        viewUser: function () {
            console.log('---------enter view user----------');
        },
        editUser: function () {
            console.log('---------enter edit user----------');
        }
    });

    return userRouter;
    //var userRouter = Backbone.Router.extend({
    //    routes: {
    //        'user': 'user',
    //        'user/:id': 'viewUser',
    //        'user/:id/edit': 'editUser'
    //    },
    //    initialized: function () {
    //        console.log('---------init user router----------');
    //    },
    //    user: function () {
    //        console.log('---------enter user list----------');
    //    },
    //    viewUser: function () {
    //        console.log('---------enter view user----------');
    //    },
    //    editUser: function () {
    //        console.log('---------enter edit user----------');
    //    }
    //});
    //
    //return userRouter;
});