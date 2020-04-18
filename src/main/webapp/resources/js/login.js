$(document).ready(function() {
	
	$('#loginForm').submit(function(e) {
		e.preventDefault()
		$.ajax({
			url:'/blog/api/login',
			method: 'POST',	
			data: {
				id : $("#id").val(),
				pwd : $("#pwd").val()
			},
			success: function(res){
				console.log(res)
				if(res.success) {
					// 얘는 히스토리를 남김. location.href = res.nextUrl
					location.replace(res.nextUrl)
				} else {
					// 여기는 로그인 실패했을때입니다.
					var msg = '아이디나 비번이 틀리다'
					// ref : https://jdm.kr/blog/27
					// .val('dlasdkfdlsk')
					$('#error').show().text(msg)
				}
			} 	
		});
		
	})
})