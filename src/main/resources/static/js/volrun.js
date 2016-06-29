$(document).ready(function(){
	$("#logout_link").click(function(){
		$.post("/logout");
	});
});


$(document).ready(function(){
  $('.dropdown a.test').on("click", function(e){
    $(this).next('ul').toggle();
    e.stopPropagation();
    e.preventDefault();
  });
});
