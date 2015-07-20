/*global require*/
'use strict';

require.config({

    locale: Global.lang,

    shim: {
        underscore: {
            exports: '_'
        },
        'underscore.string': {
            deps: ['underscore']
        },
        backbone: {
            deps: [
                'underscore',
                'jquery'
            ],
            exports: 'Backbone'
        },
        ext: {
            deps: ['underscore', 'backbone']
        },
        marionette: {
            deps: ['backbone'],
            exports: 'Backbone.Marionette'
        },
        backboneLocalstorage: {
            deps: ['backbone'],
            exports: 'Backbone.localstorage'
        },
        bootstrap: {
            deps: [
                'jquery'
            ],
            exports: 'bootstrap'
        },
        artDialog: {
            deps: [
                'jquery'
            ],
            exports: 'artDialog'
        },
        jqueryFileUpload: {
            deps: [
                'jquery', 'jqueryIframetransport'
            ],
            exports: 'jQuery.fn.fileupload'
        },
        'jquery_validate': {
            deps: [
                'jquery'
            ],
            exports: 'jQuery.fn.validator'
        },
        ztree: {
            deps: [
                'jquery'
            ],
            exports: 'jQuery.fn.zTree'
        },
        backgrid: {
            deps: [
                'jquery',
                'underscore',
                'backbone'
            ],
            exports: 'Backgrid'
        },
        'backgrid-paginator': {
            deps: [
                'backgrid'
            ]
        },
        'backgrid-text-cell': {
            deps: [
                'backgrid'
            ]
        }
    },
    packages: [
        {
            name: 'jquery_validate',
            location: '../vendor/jquery-validation-1.14.0/dist',
            main: 'jquery.validate'
        }
    ],
    paths: {
        jquery: './node_modules/jquery/dist/jquery',
        underscore: './node_modules/underscore/underscore',
        'underscore.string': './node_modules/underscore.string/dist/underscore.string',
        backbone: './node_modules/backbone/backbone',
        backboneLocalstorage: './node_modules/backbone.localstorage/backbone.localStorage',
        'backbone.radio': './node_modules/backbone.radio/build/backbone.radio',
        'backbone.paginator': './node_modules/backbone.paginator/lib/backbone.paginator',
        //'backbone.service': './node_modules/backbone.service/dist/backbone.service',
        ext: './lib/ext',
        bootstrap: '../vendor/bootstrap-3.3.5-dist/js/bootstrap',
        async: './node_modules/async/lib/async',
        text: './node_modules/requirejs-text/text',
        i18n: '../vendor/i18n/i18n',
        juicer: './node_modules/juicer/src/juicer',
        marionette: './node_modules/backbone.marionette/lib/backbone.marionette',
        artDialog: '../vendor/art-dialog-6.0.4/dist/dialog',
        echarts: '../vendor/echarts-2.2.5/source',
        zrender: '../vendor/zrender/src',
        jqueryFileUpload: '../vendor/jquery-file-upload-9.10.4/jquery.fileupload',
        'jquery.ui.widget': '../vendor/jquery-file-upload-9.10.4/vendor/jquery.ui.widget',
        jqueryIframetransport: '../vendor/jquery-file-upload-9.10.4/jquery.iframe-transport',
        // 'jquery_validate': '../vendor/jquery-validation-1.14.0/dist/jquery.validate',
        nprogress: './node_modules/nprogress/nprogress',
        ztree: '../vendor/zTree_v3/js/jquery.ztree.all-3.5',
        backgrid: '../vendor/backgrid-0.3.5/backgrid',
        'backgrid-paginator': '../vendor/backgrid-0.3.5/paginator/backgrid-paginator',
        'backgrid-text-cell': '../vendor/backgrid-0.3.5/text-cell/backgrid-text-cell'
    }
});

require(['./application/application',
        'juicer',
        './routers/index',
        './routers/user',
        './routers/app',
        './routers/dashboard',
        './views/header',
        './views/footer',
        './collections/menu',
    ],
    function (Application, juicer, IndexRouter, UserRouter, AppRouter, DashboardRouter, HeaderView, FooterView,
              Menu) {

        juicer.register('getUrl', function (path) {
            return require.toUrl(path);
        });

        Marionette.Renderer.render = function (template, data) {
            return juicer(template, data);
        };

        require(['jquery_validate'], function () {
            // 自定义校验方法
            $.validator.addMethod("zipcode", function (value, element) {
                return this.optional(element) || /^\d{5}$/.test(value);
            }, "The specified US ZIP Code is invalid");

            // 加载国际化语言
            require(['jquery_validate/localization/messages_' + Global.lang], function () {
                console.log('------------load message success------------');
            });
        });

        $(document).ajaxError(function () {
            alert('ajax error!!!!!!');
        });

        var application = new Application();

        var menu = new Menu();
        application.layout.header.show(new HeaderView({collection: menu}));
        //
        application.layout.footer.show(new FooterView());

        application.index = new IndexRouter({
            container: application.layout.content
        });
        application.user = new UserRouter({
            container: application.layout.content
        });
        application.app = new AppRouter({
            container: application.layout.content
        });
        application.dashboard = new DashboardRouter({
            container: application.layout.content
        });

        Backbone.history.start();
    });