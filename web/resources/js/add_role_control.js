/**
 * A control to add a role to the role section.
 * @param {function(string)=} onSuccess
 * @constructor
 * @implements {EditableControl}
 */
function AddRoleControl(onSuccess) {
	EditableControl.call(this);

	this._onSuccess = onSuccess;

	this._mode = 'read';
}

/**
 * @inheritDoc
 */
AddRoleControl.prototype.create = function() {
	var owner = this;
	var control = $('<div/>').addClass('control addRole ' + this._mode);

	var addRoleButton = $('<button/>');
	addRoleButton.text("Add roles");
	addRoleButton.click(function() {
		owner._updateMode('edit');
		owner._addRoleInput.select();
	});
	this._addRoleButton = addRoleButton;
	control.append(addRoleButton);

	var addRoleInput = $('<input/>');
	this._addRoleInput = addRoleInput;
	addRoleInput.keypress(function(e) {
		if (e.keyCode === 13) {
			owner.finish();
		} else if (e.keyCode === 27) {
			owner.cancel();
		}
	});
	this._control = control;
	control.append(addRoleInput);

	return control;
};

/**
 * Change the mode (edit or read-only) of this control.  This is done by changing the class name of the main container,
 * which changes the visibility of the sub controls (relying on the CSS).
 * @param {string} mode 'edit' for edit mode, 'read' for read-only mode
 * @private
 */
AddRoleControl.prototype._updateMode = function(mode) {
	this._control.removeClass(this._mode);
	this._mode = mode;
	this._control.addClass(this._mode);
};

/**
 * @inheritDoc
 */
AddRoleControl.prototype.cancel = function() {
	this._updateMode('read');
};

/**
 * Call the onSuccess function but continue in edit mode so you can keep adding roles.
 */
AddRoleControl.prototype.finish = function() {
	var roleName = this._addRoleInput.val().trim().toLowerCase();
	if (this._onSuccess && roleName !== '') {
		this._onSuccess(roleName);
	}
	this._addRoleInput.select();
};

/**
 * Flash red to say that the role could not be added.  This is because the server could not accept the name.
 */
AddRoleControl.prototype.fail = function() {
	flashBackground(this._addRoleInput, '#f03020');
};