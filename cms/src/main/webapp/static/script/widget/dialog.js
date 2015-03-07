define(function(){
	var dialog = function(opt) {
		opt = opt || {};

		this.opt = opt;
	};

	return dialog;
});

require(['widget/dialog']);