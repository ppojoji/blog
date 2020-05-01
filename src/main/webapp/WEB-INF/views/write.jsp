<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common-meta.jsp"></jsp:include>
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.16/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.16/dist/summernote.min.js"></script>

<!-- <link href="/blog/resources/css/read.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/blog/resources/js/read.js"></script> -->
<style type="text/css">
.err {
	display: none;
}
</style>
</head>
<body>
<div>
	<!-- 산단 헤더-->
	<!-- 본문 -->
	   <!-- 글 리스트 -->
	       <!-- 리스트 출력 -->
	       <!-- 페이지네이션  -->
	       
	   <!-- 상세글 보기 -->
	       <!-- 글 내용 추가 -->
	       <!-- 답글 영역 -->
	       
	<jsp:include page="/WEB-INF/views/common/common-header.jsp"></jsp:include>
	<h4>${ppp.title}</h4>
	<div id="blog-container">
		<div>
			<input type="hidden" id="postSeq" value="${ppp.seq}">
			<input type="hidden" id="mode" value="${update}"> 
			<input class="form-elem" id="title" type="text" placeholder="글제목" value="${ppp.title}">
		</div>
		
		<div>
			<textarea class="form-elem" id="content">${ppp.contents}</textarea>
		</div>
		<div>
			<input type="file" name="files" id="file">
			<input type="file" name="files" id="file0">
			<input type="file" name="files" id="file1">
		</div>
		<div>
			<button id="btnSubmit">글작성</button>
		</div>
		
	</div>
	
</div>
</body>
<script type="text/javascript">
$("#btnSubmit").click( function(){
	var url = null
	if( $("#mode").val() === "true")
	{
		url = '/blog/article/api/update/${ppp.seq}'
	} else {
		url = '/blog/article/api/write'
	}
	var form = new FormData()
	
	form.append('title',$("#title").val())
	form.append('contents', $("#content").val())
	form.append('postSeq', $("#postSeq").val())
	form.append('files', $('#file')[0].files[0])
	form.append('files', $('#file0')[0].files[0])
	form.append('files', $('#file1')[0].files[0])
	$.ajax({
		url: url,
		method:'POST',
		data: form,
		// data: {
		//	title: $("#title").val(), // input, select, textarea..
		//	contents:$("#content").val(),
		//	postSeq:$("#postSeq").val()
		//},
		processData : false,
        contentType : false,
		success: function(res){
			if(res.success)
			{
				location.href="/blog"	
			}
		}
	})
} )

$(document).ready(function() {
  $('#content').summernote();
});
</script>

</html>