$(document).ready(function(){
	/*
	 * FIXME 이것도 원래는 똑같은 메소드입니다.
	 */
	
	function renderPosts(posts, targetEl) {
		var tr =`<tr>
		   		<td>{{title}}</td>
		   		<td><button class="btn btn-primary btn-sm" data-seq="{seq}">{open}</button></td>
		   	</tr>`
		
		$(targetEl).empty()	
		for(var i=0;i<posts.length;i++)
		{
			var html = tr.replace('{{title}}', posts[i].title)
			html = html.replace('{open}', posts[i].open === 'Y'? '비공개': '공개')
			html = html.replace('{seq}', posts[i].seq)
			$(targetEl).append(html)
		}
	}
	$("#openTab").on('shown.bs.tab',function(e){
		$.ajax({
			url:'/blog/posts/public',
			method: 'GET',
			success: function(res){
				renderPosts(res, "#open-body")
			}
		})
	})
	$("#privTab").on('shown.bs.tab',function(e){
		$.ajax({
			url:'/blog/posts/private',
			method:'GET',
			success:function(res){
				renderPosts(res, "#priv-body")
			}
		})
	})
	// 지주 쓰이는 테크닉
	$('#open-body').on('click', 'button', function(e){
		var target = $(e.target)
		var postSeq = target.data('seq')
		/*
		 *  /blog   : /post/4589/public
		 *            /post/21342/private
		 */
		$.ajax({
			// url:'/blog/post/' + postSeq + '/private',
			url:`/blog/post/${postSeq}/private`,
			method:'POST',
			success:function(res){
				
				if(res.success) {
					target.closest("tr").remove();
				} else {
					alert('오류다!!!')
				}
				
			}
		})
		// ref: https://getbootstrap.com/docs/4.0/components/navs/
		// 탭 켜기
	})
		$('#priv-body').on('click', 'button', function(e){
		var target = $(e.target)
		var postSeq = target.data('seq')
		/*
		 *  /blog   : /post/4589/public
		 *            /post/21342/private
		 */
		$.ajax({
			// url:'/blog/post/' + postSeq + '/private',
			url:`/blog/post/${postSeq}/public`,
			method:'POST',
			success:function(res){
				
				if(res.success) {
					target.closest("tr").remove();
				} else {
					alert('오류다!!!')
				}
				
			}
		})
		// ref: https://getbootstrap.com/docs/4.0/components/navs/
		// 탭 켜기
	})
	$("#openTab").tab('show')
})