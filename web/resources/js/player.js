/**
 * @param {function(string)=} onSuccess a function that takes the name of the added player
 * @constructor
 */
function AddPlayerControl(onSuccess) {
	this.name = "";
	this.onSuccess = onSuccess;
}

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
		$.post(
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

AddPlayerControl.prototype._onSuccess = function() {
	if (this.onSuccess) {
		this.onSuccess(this.name);
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