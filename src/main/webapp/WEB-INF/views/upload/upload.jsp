<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common-meta.jsp"></jsp:include>
<title>AJAX 업로드 저장</title>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>
<script type="text/javascript" src="/blog/resources/js/common/upload.js"></script>
</head>
<body>
<h3>AJAX 업로드 저장</h3>
<form id="upform">
	<input type="file" id="f0">
	<input type="file" id="f1">
	<input type="submit" value="하나업로드" id="btn-up">
</form>
</body>
</html>
