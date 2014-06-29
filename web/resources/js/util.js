var getPercentageTop = function(element) {
	return element.position().top / element.parent().height() * 100;
};

var getPercentageLeft = function(element) {
	return element.position().left / element.parent().width() * 100;
};