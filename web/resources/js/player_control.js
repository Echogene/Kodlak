/**
 * @typedef {{
 *     id: string,
 *     name: string,
 *     top: number,
 *     left: number,
 *     roles: Array.<String>
 * }}
 */
var Player;

/**
 * A control representing a player.  Double click to edit the name and middle click to delete it.
 * @param {Player} player
 * @param {boolean} editable whether the player can be deleted or roles unassigned
 * @param {RoleSection} roleSection
 * @constructor
 * @implements EditableControl
 */
function PlayerControl(player, editable, roleSection) {
	EditableControl.call(this);
	this.player = player;
	this.mode = 'read';
	this._editable = editable;
	this._rolesInOrder = [];
	this._roleSection = roleSection;
}

PlayerControl.prototype = Object.create(EditableControl.prototype);
PlayerControl.prototype.constructor = PlayerControl;

/**
 * @inheritDoc
 * @returns {HTMLElement}
 */
PlayerControl.prototype.create = function() {
	var control = this._createMainControl();
	var owner = this;

	var input = $('<input/>');
	input.val(this.player.name);
	input.keypress(function (e) {
		if (e.keyCode === 13) {
			owner.finish();
		} else if (e.keyCode === 27) {
			owner.cancel();
		}
	});
	this.input = input;
	control.append(input);

	var text = $('<span/>');
	text.text(this.player.name);
	text.dblclick(function (e) {
		owner.control.removeClass('read');
		owner.mode = 'edit';
		owner.control.addClass('edit');
		owner.input.select();
		e.stopPropagation();
	});
	this.text = text;
	if (this._editable) {
		text.mouseup(function (e) {
			if (e.which == 2) {
				owner.remove();
			}
		});
	}
	control.append(text);

	control.append(this._createRoles());

	return control;
};

PlayerControl.prototype._createMainControl = function() {
	var control = $('<div/>').addClass('player control ' + this.mode);
	control.css({
		top: this.player.top + '%',
		left: this.player.left + '%'
	});
	var owner = this;
	control.draggable({
		stop: function() {
			owner.finish();
		},
		containment: 'parent'
	});
	control.droppable({
		drop: function(event, ui) {
			/** @type {RoleControl} */
			var controle = ui.draggable.data('controle');

			owner.addRole(controle);

			control.removeClass('dropping');
		},
		over: function() {
			control.addClass('dropping');
		},
		out: function() {
			control.removeClass('dropping');
		},
		tolerance: 'pointer'
	});
	this.control = control;
	return control;
};

PlayerControl.prototype._createRoles = function() {
	var roles = $('<div/>').addClass('roles');
	this._roles = roles;
	var owner = this;
	$.each(
		this.player.roles,
		function(index, roleName) {
			owner.addRoleWithoutPost(roleName);
		}
	);
	return roles;
};

PlayerControl.prototype._createRoleText = function(rawRoleName) {
	var roleText = $('<div/>').addClass('role');
	var owner = this;
	if (owner._editable) {
		roleText.mouseup(function(e) {
			if (e.which == 2) {
				owner._unassignRole(rawRoleName);
			}
		});
	}
	return roleText.text(rawRoleName.capitalizeFirstLetter())
};

PlayerControl.prototype._unassignRole = function(roleName) {
	var owner = this;
	$.post(
		'removeRoleFromPlayer.do',
		{
			playerId: owner.player.id,
			roleName: roleName
		},
		function() {
			flashBackground(owner.control, '#20f020');
			owner._roleSection.addRoleWithoutPost(roleName);

			owner._removeRole(roleName);
		}
	)
};

PlayerControl.prototype._removeRole = function(roleName) {
	var index = this._rolesInOrder.indexOf(roleName);
	this._rolesInOrder.splice(index, 1);
	removeElementAt(this._roles, index);
};

/**
 * @param {RoleControl} controle
 */
PlayerControl.prototype.addRole = function(controle) {
	var owner = this;
	$.post(
		'addRoleToPlayer.do',
		{
			playerId: owner.player.id,
			roleName: controle.name.toLowerCase()
		},
		function() {
			flashBackground(owner.control, '#20f020');
			controle.decrease();

			owner.addRoleWithoutPost(controle.name);
		}
	).fail(
		function() {
			flashBackground(owner.control, '#f03020');
		}
	);
};

PlayerControl.prototype.addRoleWithoutPost = function(roleName) {
	var index = insertIntoSortedArray(roleName, this._rolesInOrder);
	insertElementAt(this._roles, this._createRoleText(roleName), index);
};

/**
 * @inheritDoc
 */
PlayerControl.prototype.finish = function() {
	this.player.name = this.input.val();
	this.player.top = getPercentageTop(this.control);
	this.player.left = getPercentageLeft(this.control);
	this.control.css({
		top: this.player.top + '%',
		left: this.player.left + '%'
	});
	var owner = this;
	$.post(
		'editPlayer.do',
		owner.player
	);
	this.text.text(this.player.name);
	this.cancel();
};

/**
 * Delete the player and remove the control.
 */
PlayerControl.prototype.remove = function() {
	var owner = this;
	$.post(
		'deletePlayer.do',
		{id: owner.player.id}
	);
	this.control.remove();
};

/**
 * @inheritDoc
 */
PlayerControl.prototype.cancel = function() {
	this.control.removeClass('edit');
	this.mode = 'read';
	this.control.addClass('read');
};