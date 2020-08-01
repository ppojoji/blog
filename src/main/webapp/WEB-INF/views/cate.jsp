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
					<td>번호</td>
					<td>내용</td>
				</tr>
			</thead>
			<tbody id="blog-detail-body">
				<!-- <tr>
					<td>글제목</td>
				</tr> -->
				 
			</tbody>
		</table>
	<!-- </form> -->
</div>
</body>

</html>