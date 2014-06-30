

function PlayerCanvas() {
	this.players = [];
}

PlayerCanvas.prototype.create = function() {
	var canvas = $('<div/>').addClass('playerCanvas');
	this.canvas = canvas;

	var owner = this;
	canvas.dblclick(function(e) {
		var relativeX = (e.pageX - canvas.position().left) / canvas.width() * 100;
		var relativeY = (e.pageY - canvas.position().top) / canvas.height() * 100;
		var addPlayerControl = new AddPlayerControl(function(player) {
			owner._renderPlayer(player);
		});
		var control = addPlayerControl.create();
		canvas.append(control);
		control.css({
			position: 'absolute',
			top: relativeY + '%',
			left: relativeX + '%'
		});
		addPlayerControl.input.focus();
	});

	return canvas;
};

PlayerCanvas.prototype.refresh = function() {
	var owner = this;
	$.getJSON(
		"getPlayers.do",
		{},
		function(data) {
			owner.canvas.empty();
			$.each(data, function() {
				owner._renderPlayer(this);
			});
		}
	);
};

PlayerCanvas.prototype._renderPlayer = function(player) {
	var playerControl = new PlayerControl(player);

	var control =  playerControl.create();
	this.canvas.append(control);
};