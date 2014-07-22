/**
 * A control representing a role.  AKA "Controle"
 * @param {string} name
 * @param {number} number
 * @constructor
 * @implements Control
 */
function RoleControl(name, number) {
	this.name = name;
	this._number = number;
}

/**
 * @inheritDoc
 */
RoleControl.prototype.create = function() {
	var control = $('<div/>').addClass('role control');
	control.data('controle', this);
	this._control = control;

	var name = $('<span/>').addClass('text');
	name.text(this.name.capitalizeFirstLetter());
	control.append(name);

	var number = $('<span/>').addClass('number');
	number.text(this._number);
	this._numberSpan = number;
	control.append(number);

	var owner = this;
	control.mouseup(function(e) {
		if (e.which == 2) {
			owner.removeRole();
		}
	});

	this._setupDragging();

	return control;
};

RoleControl.prototype._setupDragging = function() {
	if (this._number > 0) {
		this._control.draggable({
			revert: "invalid",
			helper: 'clone',
			zIndex: 100,
			opacity: 0.75
		});
		if (this._control.draggable('option', 'disabled')) {
			this._control.draggable('enable');
		}
	} else {
		this._control.draggable('disable');
	}
};

RoleControl.prototype.decrease = function() {
	this._number = this._number - 1;
	this._setupDragging();
	this._numberSpan.text(this._number);
	flashBackground(this._numberSpan, '#a0a0a0');
	if (this._number === 0) {
		this._control.addClass('empty');
	}
};

RoleControl.prototype.increase = function() {
	this._number = this._number + 1;
	this._setupDragging();
	this._numberSpan.text(this._number);
	flashBackground(this._numberSpan, '#a0a0a0');
	if (this._number === 1) {
		this._control.removeClass('empty');
	}
};

RoleControl.prototype.removeRole = function() {
	if (this._number === 0) {
		return;
	}
	var owner = this;
	$.post(
		'removeRole.do',
		{roleName: owner.name},
		owner.decrease.bind(owner)
	)
};