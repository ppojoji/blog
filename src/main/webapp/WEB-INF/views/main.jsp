<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common-meta.jsp"></jsp:include>
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>
<script type="text/javascript" src="/blog/resources/js/common/time-util.js"></script>
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
	<table class="table">
		<thead>
			<tr>
				<td>제목</td>
				<td>조회수</td>
				<td>작성자</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody id="blog-list-body">
			<!-- <tr>
				<td><a href="/blog/article/sddddd">글제목</a></td>
			</tr>
			 -->
		</tbody>
	</table>
</div>
</body>
</html>