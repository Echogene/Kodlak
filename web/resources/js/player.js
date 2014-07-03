/**
 * @typedef {{
 *     id: string,
 *     name: string,
 *     top: number,
 *     left: number
 * }}
 */
var Player;

/**
 * @param {function(Player)=} onSuccess a function that takes the name of the added player
 * @constructor
 * @implements Control
 */
function AddPlayerControl(onSuccess) {
	Control.call(this);
	this.name = "";
	this.onSuccess = onSuccess;
}

AddPlayerControl.prototype = Object.create(Control.prototype);
AddPlayerControl.prototype.constructor = AddPlayerControl;

AddPlayerControl.prototype.create = function() {
	var control = $('<div/>').addClass('addPlayer control');
	this.control = control;

	var input = $('<input/>');
	input.val(this.name);
	var owner = this;
	input.keypress(function(e) {
		if (e.keyCode === 13) {
			owner._submit();
		} else if (e.keyCode === 27) {
			owner.cancel();
		}
	});
	this.input = input;
	control.append(input);

	var button = $('<button/>');
	button.text("Add player");
	button.click(function() {
		owner._submit();
	});
	this.button = button;
	control.append(button);

	return control;
};

AddPlayerControl.prototype._submit = function() {
	this.name = this.input.val();
	if (this.name.trim() !== "") {
		var owner = this;
		$.getJSON(
			"addPlayer.do",
			{
				name: owner.name,
				top: getPercentageTop(owner.control),
				left: getPercentageLeft(owner.control)
			},
			owner._onSuccess.bind(owner)
		);
	}
};

AddPlayerControl.prototype._onSuccess = function(player) {
	if (this.onSuccess) {
		this.onSuccess(player);
	}
	this.control.remove();
	this.name = "";
//	this.button.finish();
//	var oldBackgroundColor = this.button.css("background-color");
//	this.button.css("background-color", "#20f020");
//	this.button.animate({"background-color": oldBackgroundColor}, 1000);
};

AddPlayerControl.prototype.cancel = function() {
	this.control.remove();
};

//----------------------------------------------------------------------------------------------------------------------

function PlayerControl(player) {
	this.player = player;
	this.mode = 'read';
}

PlayerControl.prototype.create = function() {
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
	control.mouseup(function(e) {
		if (e.which == 2) {
			owner.remove();
		}
	});
	this.control = control;

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

	return control;
};

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
		"editPlayer.do",
		owner.player
	);
	this.text.text(this.player.name);
	this.cancel();
};

PlayerControl.prototype.remove = function() {
	var owner = this;
	$.post(
		"deletePlayer.do",
		{id: owner.player.id}
	);
	this.control.remove();
};

PlayerControl.prototype.cancel = function() {
	this.control.removeClass('edit');
	this.mode = 'read';
	this.control.addClass('read');
};