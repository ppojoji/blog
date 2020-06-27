<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/common/common-meta.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>
<script>var sender="${sessionScope.LOGIN_USER.id}"</script>
<script type="text/javascript" src="/blog/resources/js/common/time-util.js"></script>
<script type="text/javascript" src="/blog/resources/js/note-write.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/common-header.jsp"></jsp:include>
<h3>여기서 쪽지 보냄</h3>
	<form id="frm">
		<input type="hidden" id="receiver" value="${receiver}">
		<div><span>test</span></div>
		<div style="margin-bottom: 1rem;">
			<input class="form-control" type="text" id="sender" placeholder="보내는 사람 email">
		</div>
		<div>
			<textarea class="form-control" id="content" placeholder="내용"></textarea>
		</div>
		<div>
			<button id="btnSend" class="btn btn-primary form-control">쪽지 보내기</button>
		</div>
	</form>
</body>
</html>