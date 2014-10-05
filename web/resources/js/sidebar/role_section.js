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
				owner.addRoleWithoutPost(name);
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
 * Post up an add role request to the server and if it succeeds (the role name is accepted by the server), then add the
 * role to this section.  If it fails, show that in the add role control.
 * @param {string} name The name of the role
 */
RoleSection.prototype.addRole = function(name) {
	var owner = this;
	$.post(
		'addRole.do',
		{roleName: name},
		owner.addRoleWithoutPost.bind(owner, name)
	).fail(owner._addRoleControl.fail.bind(owner._addRoleControl));
};

/**
 * Add a role to the section.  If a control already exists for the given name, then that control gets its number
 * incremented.
 * @param {string} name
 */
RoleSection.prototype.addRoleWithoutPost = function(name) {
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