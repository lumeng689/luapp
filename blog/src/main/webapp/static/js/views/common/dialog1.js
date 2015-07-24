/**
 * Created by lum on 2015/7/24.
 */

define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'juicer',
    'templates',
    'artDialog',
    './group_tree',
    'backbone.paginator',
    'backgrid',
    'backgrid-paginator',
    'backgrid-text-cell'
], function ($, _, Backbone, Marionette, juicer, Templates, artDialog,GroupView, Paginator, Backgrid) {
    'use strict';

    var cc = Marionette.Object.extend({
        initialize: function (options) {
            console.log('-------------------');
            this.d = dialog({
                title: '请选择用户和组',
                onshow: function () {
                    this.content($('#group-user-div')[0]);
                    this.groupView = new GroupView({selIds:['1.1','1.2']});
                    $(this.node).find('#groupTree').append(this.groupView.render().$el);
                },
                ok: function () {
                    var value = $('#xxx').val();
                    this.close(value);
                    this.remove();
                }
            });

            this.d.addEventListener('close', function () {
                //alert(this.returnValue);
                // 销毁组件
                this.groupView.destroy();
            });
        },
        show: function () {
            this.d.show();
        }
    });

    return cc;
});