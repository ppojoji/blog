$(document).ready(function(){
	$("#upform").submit(function(e){
		// form 에서 버튼 클릭 기본 동작 : 폼을 전송해버림
		e.preventDefault()
		
		var form = new FormData()
		form.append('title', '제목 제목 제목')
		form.append('files', $('#f0')[0].files[0])
		form.append('files', $('#f1')[0].files[0])
		
		$.ajax({
			url: '/blog/upload/ajax',
			method: 'POST',
			data: form,
			/*
			 * processData : k=v&k1=v1
			 */
            processData : false,
            contentType : false,
			
            success(res) {
				console.log(res)
			},
			error(e) {
				console.log(e)
			}
		})
		
	});
	
//	$('#btn-up').click(function(e){
//		e.preventDefault()
//		
//	})
});