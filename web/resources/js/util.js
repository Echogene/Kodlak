var getPercentageTop = function(element) {
	var parent = element.parent();
	return (element.offset().top  - parent.offset().top) / parent.height() * 100;
};

var getPercentageLeft = function(element) {
	var parent = element.parent();
	return (element.offset().left - parent.offset().left) / parent.width() * 100;
};

var flashBackground = function(element, color) {
	element.finish();
	var oldBackgroundColor = element.css('background-color');
	element.css('background-color', color);
	element.animate(
		{'background-color': oldBackgroundColor},
		1000,
		function() {
			element.css('background-color', '');
		}
	);
};

String.prototype.capitalizeFirstLetter = function() {
	return this.charAt(0).toUpperCase() + this.slice(1);
};