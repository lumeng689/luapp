/**
 * Created by lum on 2015/7/13.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'juicer',
    './routers/index',
    './routers/user',
    './routers/app',
    './routers/dashboard',
    './views/header',
    './views/index',
    './views/footer',
    './collections/menu',
    'i18n!nls/messages',

], function ($, _, Backbone, Marionette, juicer,
             IndexRouter, UserRouter, AppRouter, DashboardRouter,
             HeaderView, IndexView, FooterView,
             Menu, I18N) {
    'use strict';

    var App = Marionette.Application.extend({
        initialize: function () {
            console.log('--------app init--------');
        }
    });
    console.log(I18N.welcome);
    juicer.register('getUrl', function (path) {
        return require.toUrl(path);
    });

    Marionette.Renderer.render = function (template, data) {
        return juicer(template, data);
    };

    //$.validator.setDefaults({
    //    //debug: true,
    //    errorClass: 'error'
    //});

    require(['jquery_validate'], function() {
        // 自定义校验方法
        $.validator.addMethod("zipcode", function(value, element) {
            return this.optional(element) || /^\d{5}$/.test(value);
        }, "The specified US ZIP Code is invalid");

        // 加载国际化语言
        require(['jquery_validate/localization/messages_' + Global.lang], function(){
            console.log('------------load message success------------');
        });
    } );

    var app = new App();

    // 划分页面区域
    app.addRegions({
        header: "#header",
        main: "#main",
        footer: "#footer"
    });

    app.addInitializer(function () {
        var menu = new Menu();
        app.header.show(new HeaderView({collection: menu}));
        //
        app.footer.show(new FooterView());
    });

    app.on('before:start', function () {
        console.log('......before app start......');
    });

    app.on('start', function () {
        console.log('......on app start......');
        var indexRouter = new IndexRouter();
        var userRouter = new UserRouter();
        var appRouter = new AppRouter();
        var dashboardRouter = new DashboardRouter();
        Backbone.history.start();
    });

    return window.app = app;
});