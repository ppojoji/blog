/**
 * 
 */
var url = location.href
var token = url.split('/')
var seq = token[token.length-1]

	$.ajax ({
		//    /blog/api/posts
		url: '/blog/api/readPosts/' + seq,
		method: 'GET',
		// data:{}
		success(res) {
			// 타입 변수이름 = '아마아'
			// var 
			console.log('응답', res)
			if(res.success) {
				var t = res.post.creationDate
				var time = timeDiff(t, new Date().getTime())
				$('#blog-detail-body').append
				(
					`<tr>
						<td>${res.post.title}</td>
						<td>${res.post.contents}</td>
						<td>${res.post.writer.id}</td>
						<td>${res.post.viewCount}</td>
						<td>${time}</td>
					</tr>`
				)
				$('#update-url').attr('href', '/blog/article/update?pid=' + res.post.seq );
//				$("#title").html(res.post.title);
//				$("#content").html(res.post.contents);

			} else {
				alert(res.cause)
			}
		},
		error(e, res){
			;
		}
	})