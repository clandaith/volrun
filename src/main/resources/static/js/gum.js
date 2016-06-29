$(document).ready(function(){
	$("#logout_link").click(function(){
		$.post("/logout");
	});
});