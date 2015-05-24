
spa.shell = (function(){
	var configMap = {
		main_html: ''
		            + '<div class="spa-shell-head">               '
			        + '	<div class="spa-shell-head-logo"></div>   '
			        + '	<div class="spa-shell-head-acct"></div>   '
			        + '	<div class="spa-shell-head-search"></div> '
			        + '</div>                                     '
			        + '<div class="spa-shell-main">               '
			        + '	<div class="spa-shell-main-nav"></div>    '
			        + '	<div class="spa-shell-main-content"></div>'
			        + '</div>                                     '
			        + '<div class="spa-shell-foot"></div>         '
			        + '<div class="spa-shell-chat"></div>         '
			        + '<div class="span-shell-modal"></div>       '
	},
	stateMap = {$container: null},
	jQueryMap = {},
	setJQueryMap, initModule;

	setJQueryMap = function(){
		var $container = stateMap.$container;
		jQueryMap = {$container: $container};
	};

	initModule = function($container){
		stateMap.$container = $container;
		$container.html(configMap.main_html);
		setJQueryMap();
	};

	return {initModule: initModule};
})();