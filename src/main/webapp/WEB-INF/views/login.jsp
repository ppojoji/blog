<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common-meta.jsp"></jsp:include>
<title>로그인</title>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>

<link href="/blog/resources/css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login.js"></script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/common-header.jsp"></jsp:include>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<div id="error" class="alert alert-danger mt-3">여기에 오류 메세지 나옴</div>
				<form id="loginForm">
				  <div class="form-group">
				    <label for="id">아이디</label>
				    <input type="text" class="form-control" id="id">
				  </div>
				  <div class="form-group">
				    <label for="pwd">비밀번호</label>
				    <input type="password" class="form-control" id="pwd">
				  </div>
				  <div class="form-group">
				    	<label>
						    <input type="checkbox" id="useCookie" > 로그인 유지
					    </label>
				  </div>
				  <button type="submit" class="btn btn-primary">로그인</button>
				</form>
			</div>
			<!-- <div class="col-8">75%</div>
			<div class="col-3">25%</div>
			<div class="col-3">25%</div> -->
		</div>
	</div>
</div>
</body>
</html>