$(document).ready(function(){
	
	$.ajax({
		url: '/blog/api/myinfo',
		method: 'GET',
		success: function(res) {
			console.log(res)
		}
	})
})

$(document).ready(function(){
	// 2. 나은 방법
//	$('#blog-list-body').click(function(e){
//		var clicked = $(e.target)
//		if(clicked.is('input')) {			
//			console.log(clicked)
//		}
//	})
	// 3. 더 간결한 방법
	$('#blog-list-body').on('click', 'input.btn-open', function(e){
		var clicked = $(e.target)
		var seq = clicked.data('seq');
		
		$.ajax({
			url:'/blog/api/toggle',
			method: "POST",
			data: {
				seq: seq
			},
			success: function(res){
				if(res.open)
				{
					clicked.val("공개중");
					clicked.removeClass("btn-warning").addClass("btn-primary");
				}
				else{
					clicked.val("비공개중")
					clicked.removeClass("btn-primary").addClass("btn-warning");
				}
				console.log(res);
			}
		})
		console.log(clicked)
	})
	$('#blog-list-body').on('click','input.btn-del',function(e){
		var clicked = $(e.target)
		var seq = clicked.data('seq');
		$.ajax({
			url:'/blog/article/api/delete' , // "/blog/del/3733"
			method:"POST",
			data: {
				pid : seq
			},
			success: function(res){
				var tr = clicked.closest('tr')
				tr.remove()
				console.log(res);
			}
		})
		console.log("eeeee");
	})
	$('#mypost-tab').on('shown.bs.tab', function(e){
		// console.log('내 글 로드 시작')
		$.ajax({
			url: '/blog/api/mypost',
			method: 'GET',
			success: function(res) {
				for(var i=0; i<res.writerPost.length;i++){
					var state = res.writerPost[i].open // true/false
					var btnType;
					if(state === true) {
						state = '공개중'
						btnType = "btn-primary"	
					} else {
						state = '비공개중'
						btnType = "btn-warning"
					}
					
					$('#blog-list-body').append(
						`<tr>
							<td>${res.writerPost[i].title}</td>
							<td><input data-seq="${res.writerPost[i].seq}" 
								type="button" 
								class="btn-open btn ${btnType}" value="${state}"></td>
							<td><input type="button" data-seq="${res.writerPost[i].seq}" class="btn-del" value="삭제"></td>
						</tr>`)
				} // end for
				
				// 1. 나쁜 방법 - 모든 버튼을 찾아서 일일이 클릭 리스너를 걸어줌
				/*
				$('#blog-list-body input[type="button"]').on('click', function(e){
					var btn = $(e.target)
					console.log(btn.data('seq'))
				})
				*/
			}
		})
	})
	$('#message').on('shown.bs.tab', function(e){
		$.ajax({
			url : '/blog/api/Message', 
			method : 'GET' ,
			success(res) {
				for(var i=0; i<res.messages.length; i++){
					
					var readClass = ''
					
					if(res.messages[i].readtime == null){
						readClass = "un-read";
					}	
					
					var content = res.messages[i].content
					if(content.length > 10){
						content = content.substring(0, 10) + "..."
					}
					
					var time = res.messages[i].sendtime
					var sendtime = time.substring(0,16)
					
					var time1 = res.messages[i].readtime
					var readtime = '';
					if(time1 != null){
						readtime = time1.substring(0,16)
					}else{
						readtime="읽지 않음"
					}
					
					$("#msg-body").append(
							`
					<tr class="${readClass}">
						<td><input type="text" value="${res.messages[i].sender}" disabled></td>
						<td><input type="text" value="${content}" disabled></td>
						<td><input type="text" value="${sendtime}" disabled></td>
						<td><input type="text" value="${readtime}" disabled></td>
						<td><input type="button" data-seq="${res.messages[i].seq}" class="btn-message-del" value="삭제"></td>
						<td><input type="button" data-seq="${res.messages[i].seq}" class="btn-message-read" value="읽기"></td>
					</tr>	
					`
							)
				}
				
				console.log(res);
			},
			/*
			success: function(res) {
				
			}
			*/
		})
	})
	$('#msg-body').on('click','input.btn-message-del',function(e){
		var clicked = $(e.target)
		var seq = clicked.data('seq');
		
		$.ajax({
			url:'/blog/article/api/messageDelete' , 
			method:"POST",
			data: {
				seq : seq
			},
			success: function(res){
				var tr = clicked.closest('tr')
				tr.remove()
				console.log(res);
			}
		})
		//console.log("eeeee");
	}).on('click', 'input.btn-message-read', function(e){
		var msgSeq = $(e.target).data('seq')
		
//		var readtime = '';
//		
//		if(readtime == null){
//			readtime = new Date().getTime()
//		}
		
		$.ajax({
			url:`/blog/article/api/messageRead/${msgSeq}`,
			method: 'GET',
			success(res) {
				console.log(res); // res.msg.content, res.msg.sender
				$('.modal-title').text(res.msg.sender)
				$('.modal-body').text(res.msg.content)
				$('#noteModal').modal('show')				
			}
		})
	})
//	$(".btn-message-read").click(function(){
//		
//	})
})