<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common-meta.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>
<title>OAuth 결과</title>

</head>
<body>
<h3>${responseServerName}</h3>

</body>
<script type="text/javascript">
$(document).ready(function() {

	// First, parse the query string
	var params = {}, queryString = location.hash.substring(1),
	    regex = /([^&=]+)=([^&]*)/g, m;
	while (m = regex.exec(queryString)) {
	  params[decodeURIComponent(m[1])] = decodeURIComponent(m[2]);
	}
	/*
	param.access_token = 'sdalfkasd;lk3jqwe9fiwejf'
	*/
	
	$.ajax({
		url: '/blog/oauth/imgur/saveToken',
		method: 'POST',
		data: JSON.stringify(params),
		contentType: 'application/json; charset=utf-8',
		success(res) {
			console.log(res)
		},
		error(err) {
			console.log(err)
		}
	})
	
})

</script>
</html>