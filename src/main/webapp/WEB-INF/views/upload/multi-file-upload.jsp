<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 여러개 업로드</title>
</head>
<body>
<h3>파일 여러개 업로드</h3>
<form action="${pageContext.request.contextPath}/upload/multi" method="post" enctype="multipart/form-data">
<input type="text" name="title">
<input type="file" name="files">
<input type="file" name="files">
<input type="submit" value="다중업로드">
</form>
</body>
</html>
