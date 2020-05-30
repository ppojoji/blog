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
	<div id="searchOutput">
		검색 결과 : <span id="cnt"></span> <a href="#" id="searchClose">닫기</a>
	</div>
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
	<!-- search -->
	<div class="form-group row justify-content-center">
		<div class="w100" style="padding-right:10px">
			<select class="form-control form-control-sm" name="searchType" id="searchType">
				<option value="title">제목</option>
				<option value="Contents">본문</option>
				<option value="writer">작성자</option>
			</select>
		</div>
		<div class="w300" style="padding-right:10px">
			<input type="text" class="form-control form-control-sm" name="keyword" id="keyword">
		</div>
		<div>
			<button class="btn btn-sm btn-primary" name="btnSearch" id="btnSearch">검색</button>
		</div>
	</div>
	<!-- search -->
</div>
</body>
</html>