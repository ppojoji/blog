<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 하나 딸랑 업로드</title>
</head>
<body>
<h3>파일 하나 딸랑 업로드</h3>
<form action="${pageContext.request.contextPath}/upload/single" method="post" enctype="multipart/form-data">
<input type="text" name="title">
<input type="file" name="file">
<input type="submit" value="하나업로드">
</form>
</body>
</html>
