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
    'backbone.paginator',
    'backgrid',
    'backgrid-paginator',
    'backgrid-text-cell'
], function ($, _, Backbone, Marionette, juicer, Templates, Paginator, Backgrid) {
    'use strict';

// Works exactly like Backbone.Collection.
    var UsersPage = Backbone.PageableCollection.extend({
        url: "/user/",

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
            debugger;
            return {totalRecords: resp.pages.totalRecords};
        },

        // get the actual records
        parseRecords: function (resp, options) {
            debugger;
            return resp.pages.records;
        }

    });

    var UserListView = Backbone.Grid.extend({
        selIds: [],
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
        ui: {
            grid: '#grid',
            paginator: '#paginator'
        },
        initialize: function (options) {
            this.collection = new UsersPage();
            this.el = options.el;
            // fetch data;
            this.collection.fetch({reset: true});
        },
        initGrid: function () {
        }
    });
});