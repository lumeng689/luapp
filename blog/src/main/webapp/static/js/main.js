/*global require*/
'use strict';

require.config({
    shim: {
        underscore: {
            exports: '_'
        },
        backbone: {
            deps: [
                'underscore',
                'jquery'
            ],
            exports: 'Backbone'
        },
        backboneLocalstorage: {
            deps: ['backbone'],
            exports: 'Store'
        },
        semantic: {
            deps: [
                'jquery'
            ],
            exports: 'semantic'
        }
    },
    paths: {
        jquery: './node_modules/jquery/dist/jquery',
        underscore: './node_modules/underscore/underscore',
        backbone: './node_modules/backbone/backbone',
        backboneLocalstorage: './node_modules/backbone.localstorage/backbone.localStorage',
        semantic: '../vendor/semantic-ui/semantic',
        async: './node_modules/async/lib/async',
        text: './node_modules/requirejs-text/text',
        juicer: './node_modules/juicer/src/juicer'
    }
});

require(['jquery',
    'underscore',
    'backbone',
    './routers/router'], function ($, _, Backbone, BlogRouter) {

    // start...
    var router = new BlogRouter();
    Backbone.history.start();
});