/**
 * 
 */
var url = location.href
var token = url.split('/')
var seq = token[token.length-1]

function loadReply() {
	$.ajax({
		url:'/blog/article/replies/'+seq,
		method:'GET',
		success:function(res){
			console.log(res);
			for(var i=0; i<res.length; i++){
				var t = res[i].replyTime
				var time = timeDiff(t, new Date().getTime())
				$("#reply-area").append(
					`<li><span>${res[i].title}</span> - <span>${res[i].writer}</span><span>(${time})</span></li>`
				)
			}
		}
	})
}
function renderPost(post) {
	var t = post.creationDate
	var time = timeDiff(t, new Date().getTime())
	
	var myDate = new Date(t);
	var date = myDate.getFullYear() + "-" + (myDate.getMonth()+1) + "-" + myDate.getDate() +
	" " + myDate.getHours() + "h" + myDate.getMinutes() + "m";
	
	$('#blog-detail-body').append
	(
		`<tr>
			<td>${post.title}</td>
		</tr>
		<tr>	
			<td>${post.contents}</td>	
		</tr>
		<tr>	
			<td>${post.writer.id}</td>
		</tr>
		<tr>	
			<td>${post.viewCount}</td>
		</tr>
		<tr>	
			<td id="time">${time}</td>
		</tr>
		<tr>	
			<td id="myDate">${date}</td>
		</tr>`
	)
	
	var html = '<tr><td colspan="5">'
	for(var i=0; i<post.upFiles.length; i++)
	{
		html += `<div><a href="${ctxpath}/upfile/${post.upFiles[i].genName}">${post.upFiles[i].originalName}</a>(${post.upFiles[i].fileSize}byte)</div>`
	}
	html += '</td></tr>'
		
	$('#blog-detail-body').append(html)
	
	$('#update-url').attr('href', '/blog/article/update?pid=' + post.seq );
//	$("#title").html(post.title);
//	$("#content").html(post.contents);
	$('#myDate').hide();
	$('#time').click(function(){
		$('#time').hide();
		$('#myDate').show();
	});
	$('#myDate').click(function(){
		$('#myDate').hide();
		$('#time').show();
	});
	
	loadReply()
}
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
				renderPost(res.post)
			} else if(res.cause === 'VIEW_PASS'){
				// 비번 입력 양식을 화면에 끼워넣음
				$('#pass-wrapper').show()
				$('#btnPass').click(function(){
					var passwd = $('#pass').val();
					$.ajax({
						url:"/blog/api/post/pass/" + seq,
						method:"POST",
						data:{
							pass: passwd
						},
						success : function(res) {
							if(res.success) {
								renderPost(res.post);
								$('#pass-wrapper').remove()
							} else {
								alert('틀렸다')
							}
						}
					})
				});
			} else {
				alert(res.cause)				
			}
		},
		error(e, res){
			;
		}
	})

	
$(document).ready(function() {
	$("#btnReply").click(function(){
		document.location.href = '/blog/article/reply?pid='+seq; // [GET]
	})
})