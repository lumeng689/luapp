/**
 * Created by lum on 2015/7/17.
 *
 * aim to extend backbone
 */
(function (root, factory) {
    'use strict';
    if (typeof exports === 'object' && typeof require === 'function') {
        module.exports = factory(require('backbone'), require('underscore'));
    } else if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define('ext', ['backbone', 'underscore'], function (Backbone, _) {
            return factory(Backbone || root.Backbone, _ || root._);
        });
    } else {
        factory(Backbone, _);
    }
})(this, function (Backbone, _) {
    'use strict';

    var BackboneViewExtensions = {
        mixin: function (from) {
            var to = this.prototype;

            _.defaults(to, from);
            _.defaults(to.events, from.events);
            _.defaults(to.triggers, from.triggers);
            this.extendMethod(to, from, "initialize");
            this.extendMethod(to, from, "render");
        },
        extendMethod: function (to, from, methodName) {
            if (!_.isUndefined(from[methodName])) {
                // 保存旧方法
                var old = to[methodName];
                to[methodName] = function () {
                    // 执行old
                    var oldReturn = old.apply(this, arguments);
                    // 执行新方法
                    from[methodName].apply(this, arguments);
                    // 返回旧方法值
                    return oldReturn;
                }
            }
        }
    };
    _.extend(Backbone.View.prototype, BackboneViewExtensions);
});