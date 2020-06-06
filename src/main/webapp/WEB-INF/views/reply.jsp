<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common-meta.jsp"></jsp:include>
<title>댓글</title>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/reply.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/common-header.jsp"></jsp:include>
	<h4>원글: ${post.title}</h4>
	<form action="/blog/article/replyPost" method="post">
	<input type="hidden" value="${post.seq}" name="parent">
		 <div class="form-group">
		    <input type="text" class="form-control" id="title" name="title" placeholder="제목">
		 </div>
 		 <div class="form-group">
			<textarea class="form-control" rows="10" name="contents"></textarea>
		 </div>
		 <div class="form-group">
		 	<input class="form-control" style="width:auto; display:inline-block;" name="writer" type="text" placeholder="이름">
		 	<input class="form-control" style="width:auto; display:none;"  name="pwd" type="text" placeholder="비밀번호" id="pwd"> 
		 </div>
		 <div class="form-check" style="top: -43px;left: 420px">
			<input class="form-check-input" type="checkbox" value="" id="defaultCheck">
			<label class="form-check-label" for="defaultCheck1">
			    secret
			 </label>
		 </div>
		 <div class="form-group">
		 	<input class="form-control btn btn-primary" type="submit" value="입력">
		 </div>
	</form>
 	<input class="form-control btn btn-danger" type="button" value="취소">
	
</body>
</html>