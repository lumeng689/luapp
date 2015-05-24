/* jslint settings */
// Module /spa/
// Provides chat slider capability
// 
// 
// 

var spa = (function(){
	var initModule = function ($container) {
		spa.shell.initModule($container);
	};

	return {initModule: initModule};
})();
// var spa = (function($){
// 	var configMap = {
// 		extended_height:434,
// 		extended_title: 'Click to retract',
// 		retracted_height: 16,
// 		retracted_title: 'Click to extend',
// 		template_html:'<div class="spa-slider"><\/div>'
// 	},
//     // Declare all other module scope variables
// 	$chatSlider,toggleSlider,onClickSlider,initModule;

// 	toggleSlider = function() {
// 		var slider_height = $chatSlider.height();
// 		if (slider_height == configMap.retracted_height) {
// 			$chatSlider
// 			    .animate({height: configMap.extended_height})
// 			    .attr('title', configMap.extended_title);

// 			return true;
// 		} else {
// 			$chatSlider
// 			    .animate({height: configMap.retracted_height})
// 			    .attr('title', configMap.retracted_title);
// 			return true;
// 		}

// 		return false;
// 	};

// 	onClickSlider = function(event){
// 		toggleSlider();
// 		return false;
// 	};

// 	initModule = function($container){
// 		debugger;
// 		// render html
// 		$container.html(configMap.template_html);
// 		$chatSlider = $container.find('.spa-slider');
// 		$chatSlider.attr('title',configMap.retracted_title)
// 		           .click(onClickSlider);

// 		return true;
// 	};

// 	return {initModule: initModule};
// }());