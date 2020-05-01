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
	// $( css-selector-문법)
	// 1. 서버로 게시글 내려받음
	// 객체 리터럴
	$.ajax ({
		//    /blog/api/posts
		url: '/blog/api/posts',
		method: 'GET',
		// data:{}
		success(res) {
			// 타입 변수이름 = '아마아'
			// var 
			console.log('응답', res)
			for(var i = 0 ; i < res.posts.length ; i++) {
				// console.log(res[i])
				// old version
				// ${.... } : jsp 
				
				// $('#blog-list-body').append('<div><a href="/blog/article/' + res[i].seq + '">'+ res[i].title + '</a></div>')
				// es6 multiline text 문법
				// $('#blog-list-body').append(`<div><a href="/blog/article/pageReadPost/${res[i].seq}">${res[i].title}</a></div>`)
				var t = res.posts[i].creationDate
				var time = timeDiff(t, new Date().getTime())
				var diff = new Date().getTime() - res.posts[i].creationDate
				var imgTag = ''
				if (diff < res.limit ) {
					// [new]를 붙어야 함
					imgTag = `<img src="${ctxpath}/resources/images/icon_new.png" width="28" height="28">`
				}
				$('#blog-list-body').append(
					`<tr>
						<td>${imgTag}<a href="/blog/article/pageReadPost/${res.posts[i].seq}">${res.posts[i].title}</a></td>
						<td>${res.posts[i].viewCount}</td>
						<td>${res.posts[i].writer.id}</td>
						<td>${time}</td>
					</tr>`)
			}
		},
		error(e, res){
			;
		}
	})
})