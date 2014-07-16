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
};

RoleControl.prototype._finishDragging = function() {
	this._control.remove();
};

//----------------------------------------------------------------------------------------------------------------------

/**
 *
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
		this._rolesInOrder.push(name);
		this._rolesInOrder.sort();
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
 * @param {function(string)=} onSuccess
 * @constructor
 * @implements {EditableControl}
 */
function AddRoleControl(onSuccess) {
	EditableControl.call(this);

	this._onSuccess = onSuccess;

	this._mode = 'read';
}

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

AddRoleControl.prototype._updateMode = function(mode) {
	this._control.removeClass(this._mode);
	this._mode = mode;
	this._control.addClass(this._mode);
};

AddRoleControl.prototype.cancel = function() {
	this._updateMode('read');
};

AddRoleControl.prototype.finish = function() {
	var roleName = this._addRoleInput.val().trim().toLowerCase();
	if (this._onSuccess && roleName !== '') {
		this._onSuccess(roleName);
	}
	this._addRoleInput.select();
};

AddRoleControl.prototype.fail = function() {
	flashBackground(this._addRoleInput, '#f03020');
};