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
		//processData : false,
        //contentType : false,
		// data:{}
		success(res) {
			// 타입 변수이름 = '아마아'
			// var 
			console.log('응답', res)
			if(res.success) {
				var t = res.post.creationDate
				var time = timeDiff(t, new Date().getTime())
				
				var myDate = new Date(t);
				var date = myDate.getFullYear() + "-" + (myDate.getMonth()+1) + "-" + myDate.getDate() +
				" " + myDate.getHours() + "h" + myDate.getMinutes() + "m";
				
				$('#blog-detail-body').append
				(
					`<tr>
						<td>${res.post.title}</td>
					</tr>
					<tr>	
						<td>${res.post.contents}</td>	
					</tr>
					<tr>	
						<td>${res.post.writer.id}</td>
					</tr>
					<tr>	
						<td>${res.post.viewCount}</td>
					</tr>
					<tr>	
						<td id="time">${time}</td>
					</tr>
					<tr>	
						<td id="myDate">${date}</td>
					</tr>`
				)
				
				var html = '<tr><td colspan="5">'
				for(var i=0; i<res.post.upFiles.length; i++)
				{
					html += `<div><a href="${ctxpath}/upfile/${res.post.upFiles[i].genName}">${res.post.upFiles[i].originalName}</a>(${res.post.upFiles[i].fileSize}byte)</div>`
				}
				html += '</td></tr>'
					
				$('#blog-detail-body').append(html)
				
				$('#update-url').attr('href', '/blog/article/update?pid=' + res.post.seq );
//				$("#title").html(res.post.title);
//				$("#content").html(res.post.contents);
				$('#myDate').hide();
				$('#time').click(function(){
					$('#time').hide();
					$('#myDate').show();
				});
				$('#myDate').click(function(){
					$('#myDate').hide();
					$('#time').show();
				});
			} else {
				alert(res.cause)
			}
		},
		error(e, res){
			;
		}
	})
