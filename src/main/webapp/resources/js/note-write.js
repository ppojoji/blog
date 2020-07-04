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
				if(res.success) {					
					alert('쪽지 썼다')
				} else {
					alert('실패다')
				}
				console.log(res);
			},
			error(e) {
				alert('족지 못보냄')
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