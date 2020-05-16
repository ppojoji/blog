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
	/*
	*/
})