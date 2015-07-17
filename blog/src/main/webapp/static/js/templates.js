/**
 * Created by lum on 2015/7/14.
 */
define(function (require) {
    'use strict';

    return {
        menuItemView: require('text!templates/menu.html'),
        header: require('text!templates/header.html'),
        index: require('text!templates/index.html'),
        footer: require('text!templates/footer.html'),
        user: {
            list: require('text!templates/user/user_list.html')
        },
        app: {
            index: require('text!templates/app/app_index.html')
        },
        dashboard: {
            index: require('text!templates/dashboard/dashboard_index.html')
        }
    };
});