$(document).ready(function() {
	
	$('#loginForm').submit(function(e) {
		/*
		 * default
		 * <a href="http://ddd">clkc</
		 */
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
					location.href = '/blog'
				} else {
					// 여기는 로그인 실피했을때입니다.
					var msg = '아이디나 비번이 틀리다'
					// ref : https://jdm.kr/blog/27
					// .val('dlasdkfdlsk')
					$('#error').show().text(msg)
				}
			} 	
		});
		
	})
})