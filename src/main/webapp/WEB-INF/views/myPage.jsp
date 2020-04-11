<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mypage.js"></script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/common-header.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
			
			<div class="form-group">
				<label>아이디</label>
				<input class="form-control" type="text" readonly>
			</div>
			
			<div class="form-group">
				<label>이메일</label>
				<input class="form-control" type="text" readonly>
			</div>
			
			<div class="form-group">
				<label>탈퇴합니다</label>
				<input type="button" class="form-control" value="탈퇴">
			</div>
			
			</div>
		</div>
	</div>
</div>
</body>
</html>