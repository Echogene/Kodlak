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
}

RoleSection.prototype.create = function() {
	var control = $('<div/>').addClass('role section');
	this._control = control;

	var heading = $('<div/>').addClass('heading');
	heading.text('Roles');
	control.append(heading);

	var roles = $('<div/>').addClass('roles');
	this._roleSection = roles;
	control.append(roles);

	var buttons = $('<div/>').addClass('buttons');

	var addRoleButton = $('<button/>').addClass('addRole');
	addRoleButton.text("Add role");
	buttons.append(addRoleButton);

	control.append(buttons);

	var owner = this;
	$.each(
		this._roles,
		function(roleName, number) {
			var controle = new RoleControl(roleName, number);
			var controleContent = controle.create();
			roles.append(controleContent);

			owner._controles[roleName] = controle;
		}
	);

	return control;
};

/**
 * Add a role to the section
 * @param {string} name The name of the role
 */
RoleSection.prototype.addRole = function(name) {
	var owner = this;
	$.post(
		'addRole.do',
		{roleName: name},
		function() {
			if (owner._roles[name]) {
				owner._roles[name] = owner._roles[name] + 1;
			} else {
				owner._roles[name] = 1;
			}
			if (owner._roleSection) {
				if (owner._controles[name]) {
					owner._controles[name].increase();
				} else {
					var controle = new RoleControl(name, owner._roles[name]);
					owner._roleSection.append(controle.create());

					owner._controles[name] = controle;
				}
			}
		}
	);
};