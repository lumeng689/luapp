/**
 * Created by lum on 2015/7/10.
 */
(function () {
    var root = this;

    var util = function(){};

    if (typeof define === 'function' && define.amd) {
        define('util', [], function(){
            return util;
        });
    }
}).call(this);