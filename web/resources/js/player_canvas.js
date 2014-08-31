/**
 * A canvas on which players can be created, edited and deleted.
 * @param {boolean} editable whether we can create or delete players
 * @param {RoleSection=} roleSection
 * @constructor
 * @implements Control
 */
function PlayerCanvas(editable, roleSection) {
	this._editable = editable;
	this._roleSection = roleSection;
}

/**
 * @inheritDoc
 * @returns {HTMLElement}
 */
PlayerCanvas.prototype.create = function() {
	var canvas = $('<div/>').addClass('playerCanvas');
	this.canvas = canvas;

	var owner = this;
	if (this._editable) {
		canvas.dblclick(function (e) {
			var relativeX = (e.pageX - canvas.offset().left) / canvas.width() * 100;
			var relativeY = (e.pageY - canvas.offset().top) / canvas.height() * 100;
			var addPlayerControl = new AddPlayerControl(function (player) {
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
	}

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
	var playerControl = new PlayerControl(player, this._editable, this._roleSection);

	var control =  playerControl.create();
	this.canvas.append(control);
};