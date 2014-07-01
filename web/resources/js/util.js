var getPercentageTop = function(element) {
	var parent = element.parent();
	return (element.offset().top  - parent.offset().top) / parent.height() * 100;
};

var getPercentageLeft = function(element) {
	var parent = element.parent();
	return (element.offset().left - parent.offset().left) / parent.width() * 100;
};