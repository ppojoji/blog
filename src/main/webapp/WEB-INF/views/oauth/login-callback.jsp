<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/views/common/common-meta.jsp"></jsp:include>
<title>로그인 확인...</title>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>

<link href="/blog/resources/css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/oauth/naver.js"></script>
</head>
<body>

</body>
<script type="text/javascript">
$(document).ready(function() {
	window.Naver.prepareCallback()
})
</script>
</html>