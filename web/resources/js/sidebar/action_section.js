/**
 * A section of the side bar that is a single button.
 * @param {function} onClick what the button should do when clicked
 * @param {string} text the text on the button
 * @constructor
 * @implements SidebarSection
 */
function ActionSection(onClick, text) {
	/**
	 * @type {string}
	 * @private
	 */
	this._onClick = onClick.bind(this);
	/**
	 * @type {string}
	 * @private
	 */
	this._text = text;
}

/**
 * @inheritDoc
 */
ActionSection.prototype.create = function () {
	var control = $('<button/>').addClass('action section');
	control.click(this._onClick);
	control.text(this._text);
	this._control = control;

	return control;
};