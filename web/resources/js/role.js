/**
 * @typedef {{
 *     name: string
 * }}
 */
var Role;

/**
 * A control representing a role.  AKA "Controle"
 * @param {Role} role
 * @constructor
 * @implements Control
 */
function RoleControl(role) {
	this._role = role;
}

RoleControl.prototype.create = function() {
	var control = $('<div/>').addClass('role control');

	control.text(this._role.name);

	control.draggable({helper: 'clone', zIndex: 100, opacity: 0.75});

	return control;
};

//----------------------------------------------------------------------------------------------------------------------

/**
 *
 * @constructor
 * @implements SidebarSection
 */
function RoleSection() {
	/**
	 * @type {Array.<Role>}
	 * @private
	 */
	this._roles = [];
}

RoleSection.prototype.create = function() {
	var control = $('<div/>').addClass('role section');
	this._control = control;

	var heading = $('<div/>').addClass('heading');
	heading.text('Roles');
	control.append(heading);

	var first = true;
	$.each(
		this._roles,
		/**
		 * @this {Role}
		 */
		function() {
			var controle = new RoleControl(this);
			var controleContent = controle.create();
			if (first) {
				controleContent.addClass('first');
			}
			control.append(controleContent);
			first = false;
		}
	);

	return control;
};

/**
 * Add a role to the section
 * @param {Role} role
 */
RoleSection.prototype.addRole = function(role) {
	this._roles.push(role);
	if (this._control) {
		var controle = new RoleControl(role);
		this._control.append(controle.create());
	}
};