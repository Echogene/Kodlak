/**
 * A canvas on which players can be created, edited and deleted.
 * @constructor
 * @implements Control
 */
function PlayerCanvas() {
}

/**
 * @inheritDoc
 * @returns {HTMLElement}
 */
PlayerCanvas.prototype.create = function() {
	var canvas = $('<div/>').addClass('playerCanvas');
	this.canvas = canvas;

	var owner = this;
	canvas.dblclick(function(e) {
		var relativeX = (e.pageX - canvas.offset().left) / canvas.width() * 100;
		var relativeY = (e.pageY - canvas.offset().top) / canvas.height() * 100;
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

/**
 * Refresh the canvas.  Get all players from the server and render a control for each of them.
 */
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

/**
 * Create a control for the given player, placing it on the canvas.
 * @param {Player} player
 * @private
 */
PlayerCanvas.prototype._renderPlayer = function(player) {
	var playerControl = new PlayerControl(player);

	var control =  playerControl.create();
	this.canvas.append(control);
};