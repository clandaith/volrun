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

$(window).scroll(function() {
    if ($(this).scrollTop() < 100) {
        $("#footer").hide();
    }
    else {
        $("#footer").show();
    }
});
