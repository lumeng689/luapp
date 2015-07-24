/**
 * Created by lum on 2015/7/24.
 */

define([
    'jquery',
    'underscore',
    'backbone',
    'marionette',
    'templates',
    'ztree'
], function ($, _, Backbone, Marionette, Templates) {
    'use strict';

    var GroupView = Marionette.ItemView.extend({
        template: false,
        tagName: 'ul',
        className: 'ztree',
        selIds: [],
        initialize: function (options) {
            var options = options || {};
            this.options = options;
            if (this.options.el) {
                this.el = this.options.el;
            }
            if (this.options.selIds) {
                this.selIds = this.options.selIds;
            }

            this.on('before:render', this.renderTree);
        },
        renderTree: function () {
            var me = this;
            //
            var setting = {
                async: {
                    enable: true,
                    url: "/user/getNodes",
                    autoParam: ["id", "name=n", "level=lv"],
                    otherParam: {"otherParam": "zTreeAsyncTest"},
                    dataFilter: function (treeId, parentNode, childNodes) {
                        if (!childNodes) return null;
                        for (var i = 0, l = childNodes.length; i < l; i++) {
                            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
                        }
                        return childNodes;
                    }
                },
                callback: {
                    onClick: function (event, treeId, treeNode) {
                        if (this.options.onClick) {
                            this.options.onClick(event, treeId, treeNode);
                        }
                    },
                    onExpand: function (event, treeId, treeNode) {
                        var zTree = $.fn.zTree.getZTreeObj(treeId);
                        if (treeNode.checked) {
                            for (var i = 0; i < treeNode.children.length; i++) {
                                zTree.checkNode(treeNode.children[i], true);
                            }
                        }
                    },
                    onNodeCreated: function(event, treeId, treeNode){
                        debugger;
                        if (me.selIds && _.contains(me.selIds, treeNode.id)) {
                            var zTree = $.fn.zTree.getZTreeObj(treeId);
                            zTree.checkNode(treeNode, true);
                        }
                    }
                },
                check: {
                    enable: true,
                    chkStyle: 'checkbox',
                    chkboxType: {"Y": "ps", "N": "ps"}
                }
            };
            $.fn.zTree.init($(this.el), setting);
        }
    });

    return GroupView;
});