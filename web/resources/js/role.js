/**
 * @typedef {{
 *     name: string
 * }}
 */
var Role;

/**
 * A control representing a role.  AKA "Controle"
 * @param {Role} role
 * @param {number} number
 * @constructor
 * @implements Control
 */
function RoleControl(role, number) {
	this._role = role;
	this._number = number;
}

RoleControl.prototype.create = function() {
	var control = $('<div/>').addClass('role control');
	this._control = control;

	var name = $('<span/>').addClass('text');
	name.text(this._role.name);
	control.append(name);

	var number = $('<span/>').addClass('number');
	number.text(this._number);
	this._numberSpan = number;
	control.append(number);

	this._setupDragging();

	return control;
};

RoleControl.prototype._setupDragging = function() {
	var owner = this;
	if (this._number > 0) {
		this._control.draggable({
			stop: function() {
				owner.decrease();
			},
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
	if (this._number === 0) {
		this._control.addClass('empty');
	}
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
	 * @type {Object.<string, {role: Role, number: number}>}
	 * @private
	 */
	this._roles = {};
}

RoleSection.prototype.create = function() {
	var control = $('<div/>').addClass('role section');
	this._control = control;

	var heading = $('<div/>').addClass('heading');
	heading.text('Roles');
	control.append(heading);

	var roles = $('<div/>').addClass('roles');
	control.append(roles);

	$.each(
		this._roles,
		/**
		 * @this {{role: Role, number: number}}
		 */
		function() {
			var controle = new RoleControl(this.role, this.number);
			var controleContent = controle.create();
			roles.append(controleContent);
		}
	);

	return control;
};

/**
 * Add a role to the section
 * @param {Role} role
 */
RoleSection.prototype.addRole = function(role) {
	var name = role.name;
	if (this._roles[name]) {
		this._roles[name].number = this._roles[name].number + 1;
	} else {
		this._roles[name] = {role: role, number: 1};
	}
	if (this._control) {
		var controle = new RoleControl(role, this._roles[name].number);
		this._control.append(controle.create());
	}
};