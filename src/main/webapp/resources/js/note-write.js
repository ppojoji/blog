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
					//alert('쪽지 썼다')
					toastr.success("쪽지 보냈습니다.",{timeOut: 3000},{closeButton: true});
				} else {
					//alert('실패다')
					toastr.error("쪽지 보내기가 실패했습니다.",{timeOut: 3000},{closeButton: true});
				}
				console.log(res);
			},
			error(e) {
				toastr.error("쪽지 못보냄",{timeOut: 3000},{closeButton: true});
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