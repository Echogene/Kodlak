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

//----------------------------------------------------------------------------------------------------------------------

/**
 * A section of the side bar for addition and assignment of roles.
 * @constructor
 * @implements SidebarSection
 */
function RoleSection() {
	/**
	 * @type {Object.<string, number>}
	 * @private
	 */
	this._roles = {};
	/**
	 * @type {Object.<string, RoleControl>}
	 * @private
	 */
	this._controles = {};

	/**
	 * @type {Array.<string>}
	 * @private
	 */
	this._rolesInOrder = [];
}

/**
 * @inheritDoc
 */
RoleSection.prototype.create = function() {
	var owner = this;
	$.ajax({
		type: 'GET',
		url: 'getAvailableRoles.do',
		dataType: 'json',
		success: function(data) {
			$.each(data, function(index, name) {
				owner._addRole(name);
			});
		},
		async: false
	});

	var control = $('<div/>').addClass('role section');
	this._control = control;

	var heading = $('<div/>').addClass('heading');
	heading.text('Roles');
	control.append(heading);

	var roles = $('<div/>').addClass('roles');
	this._roleSection = roles;
	control.append(roles);

	var buttons = $('<div/>').addClass('buttons');

	var addRoleControl = new AddRoleControl(this.addRole.bind(this));
	this._addRoleControl = addRoleControl;
	buttons.append(addRoleControl.create());

	control.append(buttons);

	$.each(
		this._rolesInOrder,
		function(index, roleName) {
			owner._addControle(roleName, owner._roles[roleName]);
		}
	);

	return control;
};

/**
 * Add a role to the section and post up this fact to the server.
 * @param {string} name The name of the role
 */
RoleSection.prototype.addRole = function(name) {
	var owner = this;
	$.post(
		'addRole.do',
		{roleName: name},
		owner._addRole.bind(owner, name)
	).fail(owner._addRoleControl.fail.bind(owner._addRoleControl));
};

RoleSection.prototype._addRole = function(name) {
	if (this._roles[name]) {
		this._roles[name] = this._roles[name] + 1;
	} else {
		this._roles[name] = 1;
		insertIntoSortedArray(name, this._rolesInOrder);
	}
	if (this._roleSection) {
		if (this._controles[name]) {
			this._controles[name].increase();
		} else {
			this._addControle(name, this._roles[name]);
		}
	}
};

RoleSection.prototype._addControle = function(name, number) {
	var controle = new RoleControl(name, number);
	var controleContent = controle.create();

	var index = this._rolesInOrder.indexOf(name);
	insertElementAt(this._roleSection, controleContent, index);

	this._controles[name] = controle;
};

//----------------------------------------------------------------------------------------------------------------------

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