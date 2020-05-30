function loadPosts() {
	$.ajax ({
		url: '/blog/api/posts',
		method: 'GET',
		success(res) {
			console.log('응답', res)
		},
		error(e, res){
			;
		}
	});
}
$(document).ready(function(){
	
	function loadPosts(){
		$.ajax ({
			//    /blog/api/posts
			url: '/blog/api/posts',
			method: 'GET',
			// data:{}
			success(res) {
				// 타입 변수이름 = '아마아'
				// var 
				console.log('응답', res)
				renderPosts(res.posts,res.limit)
			},
			error(e, res){
				;
			}
		})
	}
	
	function renderPosts(posts, limit) {
		
		$('#blog-list-body').empty();
		for(var i = 0 ; i < posts.length ; i++) {
			// console.log(res[i])
			// old version
			// ${.... } : jsp 
			
			// $('#blog-list-body').append('<div><a href="/blog/article/' + res[i].seq + '">'+ res[i].title + '</a></div>')
			// es6 multiline text 문법
			// $('#blog-list-body').append(`<div><a href="/blog/article/pageReadPost/${res[i].seq}">${res[i].title}</a></div>`)
			var t = posts[i].creationDate
			var time = timeDiff(t, new Date().getTime())
			var diff = new Date().getTime() - posts[i].creationDate
			var imgTag = ''
			if (diff < limit ) {
				// [new]를 붙어야 함
				imgTag = `<img src="${ctxpath}/resources/images/icon_new.png" width="28" height="28">`
			}
			var cntText = ''
			if(posts[i].replyCount) {
				cntText = '(' + posts[i].replyCount + ')'
			}
			$('#blog-list-body').append(
				`<tr>
					<td>${imgTag}<a href="/blog/article/pageReadPost/${posts[i].seq}">${posts[i].title}${cntText}</a></td>
					<td>${posts[i].viewCount}</td>
					<td>${posts[i].writer.id}</td>
					<td>${time}</td>
				</tr>`)
		}
	}
	// $( css-selector-문법)
	// 1. 서버로 게시글 내려받음
	// 객체 리터럴
	loadPosts();
	$(document).on('click', '#btnSearch', function(e){
		e.preventDefault();

		var url = `${ctxpath}/api/search`;
		
		$.ajax({
			url: url,
			method:'GET',
			data:{
				searchType : $('#searchType').val()
			,	keyword : $('#keyword').val()
				},
			success: function(res) {
				console.log(res);
				renderPosts(res.search,21600000)
				$('#searchOutput').show();
				$('#cnt').text(res.search.length + '건');
			}
		});
		
		// location.href = url;
		console.log(url);

	});	
	$(document).on('click','#searchClose',function(e){
		e.preventDefault()
		loadPosts();
		$('#searchOutput').hide();
	});
})