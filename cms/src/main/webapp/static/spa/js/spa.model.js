/*
 * spa.model.js
 * Model module
*/

/*jslint         browser : true, continue : true,
  devel  : true, indent  : 2,    maxerr   : 50,
  newcap : true, nomen   : true, plusplus : true,
  regexp : true, sloppy  : true, vars     : false,
  white  : true
*/

/*global $, spa */

spa.model = (function (){
    'use strict';

    var
        configMap = {anon_id: 'a0'},
        stateMap = {
        	anon_user     : null,
        	people_cid_map: {},
        	people_db     : TAFFY()
        },
        isFakeData = true,
        personProto, makePerson, people, initModule;

        personProto = {
        	get_is_user : function() {
        		return this.cid === stateMap.user.cid;
        	},
        	get_is_anon : function() {
        		return this.cid === stateMap.anon_user.cid;
        	}
        };

        makePerson = function(person_map) {
        	var person,
        	    cid     = person_map.cid,
        	    css_map = person_map.css_map,
        	    id      = person_map.id,
        	    name    = person_map.name;

        	if (cid === undefined || !name) {
        		throw 'client id and name required';
        	}

        	person = Object.create(personProto);
        };

	return {}; 
}());
