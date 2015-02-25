$(document).ready(function() {
	//Handle all broken images
	$("img").error(function () {
		$(this).hide();
		$(this).attr('src', '');
		$(this).unbind("error").attr("class", "icon-large icon-user");		
	});
});