/**
 * Created by lum on 2015/7/15.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'ext',
    'marionette',
    'juicer',
    'templates',
    'backbone.paginator',
    'backgrid',
    'backgrid-paginator',
    'backgrid-text-cell',
    'bootstrap',
    'ztree'
], function ($, _, Backbone, ext, Marionette, juicer, Templates, Paginator, Backgrid) {
    'use strict';

    var userList = Marionette.ItemView.extend({
        //el: "#footer",
        template: Templates.user.list,
        events: {},
        initialize: function () {
            console.log('...user list init....');
            Backbone.trigger('selMenu', 'user');
            this.on('attach', this.renderTree);
            this.on('attach', this.renderGrid);
        },
        renderTree: function () {
            //
            var setting = {
                async: {
                    enable: true,
                    url: "../user/getNodes",
                    autoParam: ["id", "name=n", "level=lv"],
                    otherParam: {"otherParam": "zTreeAsyncTest"},
                    dataFilter: function (treeId, parentNode, childNodes) {
                        if (!childNodes) return null;
                        for (var i = 0, l = childNodes.length; i < l; i++) {
                            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
                        }
                        return childNodes;
                    }
                }
            };

            $.fn.zTree.init($("#treeDemo"), setting);
        },
        renderGrid: function(){

            // Works exactly like Backbone.Collection.
            var Issues = Backbone.PageableCollection.extend({

                url: "https://api.github.com/search/issues",

                // Initial pagination states
                state: {
                    pageSize: 15,
                    sortKey: "updated",
                    order: 1
                },

                // You can remap the query parameters from `state` keys from
                // the default to those your server supports
                queryParams: {
                    totalPages: null,
                    totalRecords: null,
                    sortKey: "sort",
                    q: "state:closed repo:jashkenas/backbone"
                },

                // get the state from Github's search API result
                parseState: function (resp, queryParams, state, options) {
                    return {totalRecords: resp.total_count};
                },

                // get the actual records
                parseRecords: function (resp, options) {
                    return resp.items;
                }

            });

            var issues = new Issues();

            var grid = new Backgrid.Grid({
                columns: [{
                    name: "id",
                    cell: Backgrid.IntegerCell.extend({ orderSeparator: '' }),
                    sortable: false,
                    editable: false
                }, {
                    name: "title",
                    cell: "string",
                    sortable: false,
                    editable: false
                }, {
                    name: "body",
                    cell: "text", // See the TextCell extension
                    sortable: false
                }, {
                    name: "comments",
                    cell: "integer"
                }],

                collection: issues
            });

            var paginator = new Backgrid.Extension.Paginator({
                collection: issues
            });

            $("#grid").append(grid.render().$el);
            $("#paginator").append(paginator.render().$el);

            issues.fetch({reset: true});

        }
    });
    return userList;
});