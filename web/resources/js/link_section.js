/**
 * A section of the side bar that is a single anchor element.
 * @param {string} url the url of the link
 * @param {string} text the text for the anchor
 * @constructor
 * @implements SidebarSection
 */
function LinkSection(url, text) {
	/**
	 * @type {string}
	 * @private
	 */
	this._url = url;
	/**
	 * @type {string}
	 * @private
	 */
	this._text = text;
}

/**
 * @inheritDoc
 */
LinkSection.prototype.create = function () {
	var control = $('<a/>').addClass('link section');
	control.attr('href', this._url);
	control.text(this._text);
	this._control = control;

	return control;
};