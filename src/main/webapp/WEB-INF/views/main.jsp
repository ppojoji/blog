<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common-meta.jsp"></jsp:include>
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
	<table class="table">
		<thead>
			<tr>
				<td>제목</td>
				<td>조회수</td>
				<td>작성자</td>
			</tr>
		</thead>
		<tbody id="blog-list-body">
			<!-- <tr>
				<td><a href="/blog/article/sddddd">글제목</a></td>
			</tr>
			 -->
		</tbody>
	</table>
<!-- 	<div id="blog-list">
		<div id="blog-list-body">
			<table class="table">
			  <thead class="thead-light">
			    <tr>
			      <th scope="col">#</th>
			      <th scope="col">First</th>
			      <th scope="col">Last</th>
			      <th scope="col">Handle</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      <th scope="row">1</th>
			      <td>Mark</td>
			      <td>Otto</td>
			      <td>@mdo</td>
			    </tr>
			    <tr>
			      <th scope="row">2</th>
			      <td>Jacob</td>
			      <td>Thornton</td>
			      <td>@fat</td>
			    </tr>
			    <tr>
			      <th scope="row">3</th>
			      <td>Larry</td>
			      <td>the Bird</td>
			      <td>@twitter</td>
			    </tr>
			  </tbody>
			</table>
		</div>
		
		<div id="pgn"></div>
	</div> -->
	
	
</div>
</body>
</html>