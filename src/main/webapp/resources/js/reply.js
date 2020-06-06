$(document).ready(function(){
	$("#defaultCheck").click(function(e){
		if ($("input:checkbox[id='defaultCheck']").is(":checked") == true) {
			$("#pwd").css('display', 'inline-block')			
		} else {
			$("#pwd").hide()
		}
	});
});