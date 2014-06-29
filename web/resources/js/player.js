var testAjax = function() {
	$.getJSON("getPlayers.do", {
			parameter: $("#nameInput").val()
		},
		function() {
			$(document.body).append($('<div/>').text("hello"));
		}
	);
};