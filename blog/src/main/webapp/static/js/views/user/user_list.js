/**
 * Created by lum on 2015/7/15.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'ext',
    'marionette',
    '../common/dialog1',
    'juicer',
    'templates',
    'nprogress',
    'backbone.paginator',
    'backgrid',
    'backgrid-paginator',
    'backgrid-text-cell',
    'bootstrap',
    'ztree',
    'jquery.uriAnchor'
], function ($, _, Backbone, ext, Marionette, GUDialog, juicer, Templates, nprogress, Paginator, Backgrid) {
    'use strict';

    var userList = Marionette.ItemView.extend({
        //el: "#footer",
        template: Templates.user.list,
        ui: {
            selGroupInput: '#citySel',
            selGroupBtn: '#menuBtn',
            selGroupAndUser: '#selGroupAndUser'
        },
        events: {
            'click @ui.selGroupInput': 'showMenu',
            'click @ui.selGroupBtn': 'showMenu',
            'click @ui.selGroupAndUser': 'showGroupUserDialog'
        },
        initialize: function () {
            console.log('...user list init....');
            Backbone.trigger('selMenu', 'user');
            this.on('attach', this.renderTree);
            this.on('attach', this.renderGrid);

            this.on('dom:refresh', function () {
                console.log('refresh!!!');
            })
        },
        showMenu: function () {
            var cityObj = $("#citySel");
            var cityOffset = $("#citySel").offset();
            $("#menuContent").css({
                left: cityOffset.left + "px",
                top: cityOffset.top + cityObj.outerHeight() + "px"
            }).slideDown("fast");

            var me = this;
            $("body").bind("mousedown", function(event){
                if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
                    me.hideMenu();
                }
            });
        },
        hideMenu: function () {
            $("#menuContent").fadeOut("fast");
            $("body").unbind("mousedown", this.onBodyDown);
        },
        onCheck: function (e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeSelDemo"),
                nodes = zTree.getCheckedNodes(true),
                v = "";
            for (var i = 0, l = nodes.length; i < l; i++) {
                v += nodes[i].name + ",";
            }
            if (v.length > 0) v = v.substring(0, v.length - 1);
            var cityObj = $("#citySel");
            cityObj.attr("value", v);
        },
        renderTree: function () {
            var me = this;
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
                },
                callback: {
                    onClick: function (event, treeId, treeNode) {
                        console.log(treeNode.tId + ", " + treeNode.name);
                        me.users.queryParams.groupId = treeNode.tId;
                        $.uriAnchor.setAnchor({
                            page: 'profile',
                            slider: 'confirm',
                            color: 'red'
                        });

                        nprogress.start();

                        var state = $.uriAnchor.makeAnchorMap();
                    }
                }
            };

            $.fn.zTree.init($("#treeDemo"), setting);

            var setting2 = {
                check: {
                    enable: true,
                    chkboxType: {"Y": "", "N": ""}
                },
                view: {
                    dblClickExpand: false
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    beforeClick: this.beforeClick,
                    onCheck: this.onCheck
                }
            };

            var zNodes = [
                {id: 1, pId: 0, name: "北京"},
                {id: 2, pId: 0, name: "天津"},
                {id: 3, pId: 0, name: "上海"},
                {id: 6, pId: 0, name: "重庆"},
                {id: 4, pId: 0, name: "河北省", open: true, nocheck: true},
                {id: 41, pId: 4, name: "石家庄"},
                {id: 42, pId: 4, name: "保定"},
                {id: 43, pId: 4, name: "邯郸"},
                {id: 44, pId: 4, name: "承德"},
                {id: 5, pId: 0, name: "广东省", open: true, nocheck: true},
                {id: 51, pId: 5, name: "广州"},
                {id: 52, pId: 5, name: "深圳"},
                {id: 53, pId: 5, name: "东莞"},
                {id: 54, pId: 5, name: "佛山"},
                {id: 6, pId: 0, name: "福建省", open: true, nocheck: true},
                {id: 61, pId: 6, name: "福州"},
                {id: 62, pId: 6, name: "厦门"},
                {id: 63, pId: 6, name: "泉州"},
                {id: 64, pId: 6, name: "三明"}
            ];

            $.fn.zTree.init($("#treeSelDemo"), setting2, zNodes);
        },
        renderGrid: function () {

            // Works exactly like Backbone.Collection.
            var UsersPage = Backbone.PageableCollection.extend({
                url: "./user/",

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
                    return {totalRecords: resp.pages.totalRecords};
                },

                // get the actual records
                parseRecords: function (resp, options) {
                    return resp.pages.records;
                }

            });


            var users = new UsersPage();
            this.users = users;

            var grid = new Backgrid.Grid({
                columns: [{
                    name: "id",
                    cell: Backgrid.IntegerCell.extend({orderSeparator: ''}),
                    sortable: false,
                    editable: false
                }, {
                    name: "name",
                    cell: "uri",
                    sortable: false,
                    editable: false,
                    target: "",
                    formatter: Backgrid.CellFormatter
                }, {
                    name: "job",
                    cell: "string", // See the TextCell extension
                    sortable: false
                }, {
                    name: "desc",
                    cell: "text"
                }],

                collection: users
            });

            var paginator = new Backgrid.Extension.Paginator({
                collection: users
            });

            $("#grid").append(grid.render().$el);
            $("#paginator").append(paginator.render().$el);

            users.fetch({reset: true});
        },
        showGroupUserDialog: function(options) {
            var d = new GUDialog();
            d.show();
        }
    });
    return userList;
});