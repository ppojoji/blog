$(document).ready(function(){
	function sendNote() {
		var content = $("#content").val()
		var sender = $('#sender').val()
		var receiver = $("#receiver").val()
		$.ajax({
			url: "/blog/note/sendMessage" , 
			method : "POST",
			data: {
				content : content , 
				sender: sender,
				receiver : receiver
			},
			success(res){
				console.log(res);
			}
		})
		
	}
	
//	$("#btnSend").click(function() {
//		sendNote();
//	});
	$('#frm').submit(function(e){
		e.preventDefault()
		sendNote()
		
	})
});