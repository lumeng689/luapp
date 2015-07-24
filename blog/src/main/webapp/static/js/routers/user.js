/**
 * Created by lum on 2015/7/14.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    '../models/user',
    '../views/user/user_list',
    '../views/user/user'
], function ($, _, Backbone, Marionette, User, UserListView, UserView) {
    'use strict';

    var userRouter = Marionette.AppRouter.extend({
        routes: {
            'user': 'userList',
            'user/add': 'addUser',
            'user/:id': 'viewUser',
            'user/:id/edit': 'editUser'
        },
        initialize: function (options) {
            this.container = options.container;
            console.log('---------init user router----------');
            this.on('route', function(){console.log('route')});
        },
        userList: function () {
            console.log('---------enter user list----------');
            this.container.show(new UserListView());
        },
        addUser: function() {
            console.log('---------enter add user----------');
            this.container.show(new UserView({type:'add'}));
        },
        viewUser: function (id) {
            console.log('---------enter view user----------');
            var user = new User({id: id})
            this.container.show(new UserView({type: 'view',model: user}));
        },
        editUser: function (id) {
            console.log('---------enter edit user----------');
            this.container.show(new UserView({type: 'edit',id: id}));
            //this.container.show();
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