<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common-meta.jsp"></jsp:include>
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>

<!-- <link href="/blog/resources/css/read.css" rel="stylesheet" type="text/css"> -->
<script type="text/javascript" src="/blog/resources/js/common/time-util.js"></script>
<script type="text/javascript" src="/blog/resources/js/read.js"></script>
<link href="/blog/resources/css/read.css" rel="stylesheet" type="text/css">
<style type="text/css">
.err {
	display: none;
}
#pass-wrapper {
	display: none;
}
#pwd-form {
    border: 1px solid #ccc;
    padding: .25em;
    display: inline-block;
    background-color: #eee;
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
	<%-- <form action="${pageContext.request.contextPath}/upload/single" method="post" enctype="multipart/form-data"> --%>
		<table class="table">
			<thead>
				<tr>
					<td>제목</td>
					<td>내용</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody id="blog-detail-body">
				<!-- <tr>
					<td>글제목</td>
				</tr> -->
				 
			</tbody>
		</table>
	<!-- </form> -->
	<div id="pass-wrapper">
		<table>
			<tr>
				<td><input type="password" placeholder="비밀번호" id="pass"></td>
				<td><input type="button" value="입력" id="btnPass"></td>
			</tr>
		</table>
	</div>	
	<div id="blog-detail">
		<!-- FIXME  글제목, 글내용, 작성자, 조회수, 작성일 정보를 화면에 추가해봅니다. -->
		<!-- <div id="blog-detail-body">
			<div id="title"></div>
			<div id="content"></div>
		</div> -->
		<div class="err no_such_post">글이 존재하지 않습니다.</div>
		<div id="pgn">
			<a href="${pageContext.request.contextPath}">목록으로</a>
		</div>
		<div>
			<a id="update-url" href="#">글수정</a>
			<!-- <a id="delete-url" href="#">글삭제</a> -->
		</div>
		<div>
			 <button id="btnDelete">글삭제</button>
			 
			 <button id="btnReply">댓글</button>
			  
		</div>
		<div>
		 	<ul id="reply-area">
		 		<!-- 
		 		<li><span>이것은 답글입니다.</span> - <span>글쓴이</span><span>(2020-03-03)</span></li>
		 		<li><span>이것은 답글입니다.</span> - <span>글쓴이</span><span>(2020-03-03)</span></li>
		 		<li><span>이것은 답글입니다.</span> - <span>글쓴이</span><span>(2020-03-03)</span></li>
		 		 -->
		 	</ul>
		</div>
	</div>
</div>
<div id="pwd-form">
	<input type="hidden" id="repl-seq" name="seq">
	<input type="text" id="repl-pwd" name="pwd">
	<input type="button" value="확인" id="btnCheckPwd">
</div>
</body>
<script type="text/javascript">
	$("#btnDelete").click( function(){
		if(confirm("삭제 하시겠습니까?"))
		{
			$.ajax({
				url:'/blog/article/api/delete',
				method:'POST',
				 data: {
					pid:seq
				}, 
				success: function(res)
				{
					if(res.success)
					{
						location.href="/blog"	
					}
				}
			})
		}	
	})	
;
</script>
</html>