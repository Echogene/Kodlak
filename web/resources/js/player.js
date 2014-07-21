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
 * A control that adds a player to the game upon finishing.
 * @param {function(Player)=} onSuccess a function that takes the added player returned from the server
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
 * What to do after the player when it has been returned from the server.  Use the function passed into the constructor.
 * And afterwards, remove the control.
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

//----------------------------------------------------------------------------------------------------------------------

/**
 * A control representing a player.  Double click to edit the name and middle click to delete it.
 * @param {Player} player
 * @constructor
 * @implements EditableControl
 */
function PlayerControl(player) {
	EditableControl.call(this);
	this.player = player;
	this.mode = 'read';
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
	input.keypress(function(e) {
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
	text.dblclick(function(e) {
		owner.control.removeClass('read');
		owner.mode = 'edit';
		owner.control.addClass('edit');
		owner.input.select();
		e.stopPropagation();
	});
	this.text = text;
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

			owner.addRole(controle.name, function() {
				ui.draggable.data('dropped', true);
				flashBackground(control, '#20f020');
				controle.decrease();
			});

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
	control.mouseup(function(e) {
		if (e.which == 2) {
			owner.remove();
		}
	});
	this.control = control;
	return control;
};

PlayerControl.prototype._createRoles = function() {
	var roles = $('<div/>').addClass('roles');
	var owner = this;
	$.each(
		this.player.roles,
		function(index, roleName) {
			roles.append(owner._createRoleText(roleName));
		}
	);
	return roles;
};

PlayerControl.prototype._createRoleText = function(rawRoleName) {
	var roleText = $('<div/>').addClass('role');
	return roleText.text(rawRoleName.capitalizeFirstLetter())
};

/**
 * @param {string} roleName
 * @param {function} onSuccess
 */
PlayerControl.prototype.addRole = function(roleName, onSuccess) {
	var owner = this;
	$.post(
		'addRoleToPlayer.do',
		{
			playerId: owner.player.id,
			roleName: roleName.toLowerCase()
		},
		onSuccess
	).fail(
		function() {
			flashBackground(owner.control, '#f03020');
		}
	);
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