<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>

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
	$.ajax({
		url: url,
		method:'POST',
		data: {
			title: $("#title").val(), // input, select, textarea..
			contents:$("#content").val(),
			postSeq:$("#postSeq").val()
		},
		success: function(res){
			if(res.success)
			{
				location.href="/blog"	
			}
		}
	})
} )
</script>

</html>