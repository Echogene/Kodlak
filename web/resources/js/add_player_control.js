/**
 * A control with a single input box that takes the content of the box and creates a player with a name equal to the
 * content.
 * @param {function(Player)=} onSuccess a function that takes the new player object returned from the server
 * @constructor
 * @implements EditableControl
 */
function AddPlayerControl(onSuccess) {
	EditableControl.call(this);
	this.name = '';
	this.onSuccess = onSuccess;
}

AddPlayerControl.prototype = Object.create(EditableControl.prototype);
AddPlayerControl.prototype.constructor = AddPlayerControl;

/**
 * @inheritDoc
 * @return {HTMLElement}
 */
AddPlayerControl.prototype.create = function() {
	var control = $('<div/>').addClass('addPlayer control');
	this.control = control;

	var input = $('<input/>');
	input.val(this.name);
	var owner = this;
	input.keypress(function(e) {
		if (e.keyCode === 13) {
			owner.finish();
		} else if (e.keyCode === 27) {
			owner.cancel();
		}
	});
	this.input = input;
	control.append(input);

	var button = $('<button/>');
	button.text('Add player');
	button.click(function() {
		owner.finish();
	});
	this.button = button;
	control.append(button);

	return control;
};

/**
 * @inheritDoc
 */
AddPlayerControl.prototype.finish = function() {
	this.name = this.input.val();
	if (this.name.trim() !== '') {
		var owner = this;
		$.getJSON(
			'addPlayer.do',
			{
				name: owner.name,
				top: getPercentageTop(owner.control),
				left: getPercentageLeft(owner.control)
			},
			owner._onSuccess.bind(owner)
		);
	}
};

/**
 * What to do with the new player when it has been returned from the server.  Use the function originally passed into
 * the constructor, and remove the control.
 * @param {Player} player the player passed down from the server
 * @private
 */
AddPlayerControl.prototype._onSuccess = function(player) {
	if (this.onSuccess) {
		this.onSuccess(player);
	}
	this.control.remove();
	this.name = '';
};

/**
 * @inheritDoc
 */
AddPlayerControl.prototype.cancel = function() {
	this.control.remove();
};