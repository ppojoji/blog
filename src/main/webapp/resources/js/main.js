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
				renderPosts(res.posts,res.limit, function(title) {
					return title
				})
			},
			error(e, res){
				;
			}
		})
	}
	 function searchConverter (title, searchKeyword) {
		// var searchKeyword = $('#keyword').val();
		while (title.includes(searchKeyword)) {
			title = title.replace(searchKeyword,`<span class="hl">{}</span>`)						
		}
		// '{}' => '파일'
		while(title.includes(`<span class="hl">{}</span>`)){
			title = title.replace("{}",searchKeyword);
		}
		return title;
	}
	/*
	 * 
	 renderPosts(posts, 11111, function(title) {
	    return '';
	 ))
	*/
	function renderPosts(posts, limit, fnConverter) {
		
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
			var title = fnConverter(posts[i].title)
			var receiver = posts[i].writer.seq
			var cateName = ''
			if(posts[i].category != null){
				cateName = posts[i].category.name
			}
			$('#blog-list-body').append(
				`<tr>
					<td>${imgTag}<a href="/blog/article/pageReadPost/${posts[i].seq}">${title}${cntText}</a></td>
					<td><a href="/blog/cate/${cateName}">${cateName}</a></td>
					<td>${posts[i].viewCount}</td>
					<td><a href="/blog/note/writeMessage/${receiver}">${posts[i].writer.id}</a></td>
					<td>${time}</td>
				</tr>`)
		}
	}
	// $( css-selector-문법)
	// 1. 서버로 게시글 내려받음
	// 객체 리터럴
	loadPosts();
	
	function keyword(){
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
				renderPosts(res.search,21600000, function (title) {
					var searchKeyword = $('#keyword').val();
					return searchConverter(title,searchKeyword)
				}) 
				$('#searchOutput').show();
				$('#cnt').text(res.search.length + '건');
			}
		});
		console.log(url);
	}
	
	function multiKeyword(){
		var url = `${ctxpath}/api/multiSearch`;
		var param = $('input[name="multiSearchType"]').serialize()
		param += '&multiKeyword=' +  $('#multiKeyword').val()
		
		$.ajax({
			url: url,
			method:'GET',
			data: param,
//			data:{
//				multiSearchType : $('input[name="multiSearchType"]').serialize()
//			,	multiKeyword : $('#multiKeyword').val()
//				},
			success: function(res) {
				console.log(res);
				renderPosts(res.multiSearch,21600000, function (title) {
					var searchKeyword = $('#multiKeyword').val();
					return searchConverter(title,searchKeyword)
				})
				$('#searchOutput').show();
				$('#cnt').text(res.multiSearch.length + '건');
			}
		});
		console.log(url);
	}
	//단일 엔터버튼 눌렀을때
	$(document).ready(function(){
		$("#keyword").keydown(function (key){
			if(key.keyCode == 13){
				keyword();
			}

		});
	});
	
	//멀티 엔터버튼 눌렀을때
	$(document).ready(function(){
		$("#multiKeyword").keydown(function (key){
			if(key.keyCode == 13){
				multiKeyword();
			}
		});
	});
	
	//단일 검색버튼 눌렀을때
	$(document).on('click', '#btnSearch', function(e){
		e.preventDefault();
		keyword();
	});	
	
	$(document).on('click','#searchClose',function(e){
		e.preventDefault()
		loadPosts();
		$('#searchOutput').hide();
	});
	
	//멀티 검색버튼 눌렀을때
	$(document).on('click', '#btnSearch2', function(e){
		e.preventDefault();
		multiKeyword();
	});	
})