var testAjax = function() {
	$.getJSON("getPlayers.do", {
			parameter: $("#nameInput").val()
		},
		function() {
			$(document.body).append($('<div/>').text("hello"));
		}
	);
};

function AddPlayerControl() {
	this.name = "";
}

AddPlayerControl.prototype.create = function() {
	var control = $('<div/>').addClass('addPlayer control');
	this.control = control;

	var input = $('<input/>');
	input.val(this.name);
	var owner = this;
	input.keypress(function(e) {
		if (e.which === 13) {
			owner._submit();
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
				top: owner.control.position().top / owner.control.parent().height() * 100,
				left: owner.control.position().left / owner.control.parent().width() * 100
			},
			owner._onSuccess.bind(owner)
		);
	}
};

AddPlayerControl.prototype._onSuccess = function() {
	this.name = "";
	this.input.val(this.name);

	this.control.remove();
//	this.button.finish();
//	var oldBackgroundColor = this.button.css("background-color");
//	this.button.css("background-color", "#20f020");
//	this.button.animate({"background-color": oldBackgroundColor}, 1000);
};