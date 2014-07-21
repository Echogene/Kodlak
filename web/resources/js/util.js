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

/**
 * @param {jQuery} element
 * @param {jQuery} toInsert
 * @param {number} index
 */
var insertElementAt = function(element, toInsert, index) {
	var children = element.children();
	if (index === 0) {
		element.prepend(toInsert);
	} else if (index < children.length) {
		children.eq(index - 1).after(toInsert);
	} else if (index == children.length) {
		element.append(toInsert);
	}
};

/**
 * Find the index where an item should fit into an array (with splice).
 * @see http://stackoverflow.com/questions/1344500/efficient-way-to-insert-a-number-into-a-sorted-array-of-numbers
 * @param {T} item
 * @param {Array.<T>} array
 * @param {function(T, T): boolean} comparator
 * @returns {number} the index of the array into which the given item would fit
 * @template T
 */
var findLocationFor = function(item, array, comparator) {
	var low = 0,
		high = array.length;

	while (low < high) {
		var mid = low + high >>> 1;
		if (comparator(array[mid], item)) {
			low = mid + 1;
		} else {
			high = mid;
		}
	}
	return low;
};

/**
 * Find the index where an item should fit into an array (with splice).
 * @param {T} item
 * @param {Array.<T>} sortedArray
 * @param {function(T, T): boolean=} comparator
 * @returns {number} the index of the array into which the given item was fit
 * @template T
 */
var insertIntoSortedArray = function(item, sortedArray, comparator) {
	if (!comparator) {
		comparator = function(a, b) {
			return a < b;
		};
	}
	var index = findLocationFor(item, sortedArray, comparator);
	sortedArray.splice(index, 0, item);
	return index;
};