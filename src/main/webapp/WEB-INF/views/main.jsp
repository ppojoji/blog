<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>

<script type="text/javascript" src="/blog/resources/js/main.js"></script>

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
	
	<div id="blog-list">
		<div id="blog-list-body">
			<!-- <div>ddddd </div>
			<div>ddddd </div> -->
		</div>
		
		<div id="pgn"></div>
	</div>
	
	
</div>
</body>
</html>