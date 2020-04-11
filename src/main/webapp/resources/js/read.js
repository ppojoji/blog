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
				$("#title").html(res.post.title);
				$("#content").html(res.post.contents);
				$('#update-url').attr('href', '/blog//article/update?pid=' + res.post.seq );
				//$('#delete-url').attr('href', '/blog//article/delete?pid=' + res.post.seq );
			} else {
				alert(res.cause)
			}
		},
		error(e, res){
			;
		}
	})