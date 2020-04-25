<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common-meta.jsp"></jsp:include>
<title>마이 페이지</title>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mypage.js"></script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/common-header.jsp"></jsp:include>
<%-- 	<div class="container-fluid">
		<div class="row">
			<div class="col">
			
			<div class="form-group">
				<label>아이디</label>
				<input id="id" class="form-control" type="text" value="${LOGIN_USER.id}" readonly>
			</div>
			
			<div class="form-group">
				<label>이메일</label>
				<input id="email" class="form-control" type="text" value="${LOGIN_USER.email}" readonly>
			</div>
			
			<div class="form-group">
				<label>탈퇴합니다</label>
				<input type="button" id="userDelete" class="form-control" value="탈퇴">
			</div>
			
			</div>
		</div>
	</div> --%>
	<ul class="nav nav-tabs">
	  <li class="nav-item">
	    <a class="nav-link active" data-toggle="tab" href="#id">아이디</a>
	    <div class="form-group">
		</div>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" data-toggle="tab" href="#email">이메일</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" data-toggle="tab" href="#leave">탈퇴</a>
	  </li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
	  <div class="tab-pane container active" id="id">
	  	<label>아이디</label>
		<input id="id" class="form-control" type="text" value="${LOGIN_USER.id}" readonly>
	  </div>
	  <div class="tab-pane container fade" id="email">
	  	<label>이메일</label>
		<input id="email" class="form-control" type="text" value="${LOGIN_USER.email}" readonly>
	  </div>
	  <div class="tab-pane container fade" id="leave">
	  	<label>탈퇴합니다</label>
		<input type="button" id="userDelete" class="form-control" value="탈퇴">
	  </div>
	</div>
</div>
</body>
<script type="text/javascript">
	$("#userDelete").click( function(){
		if(confirm("회원 탈퇴 하시겠습니까?"))
		{
			$.ajax({
				url:'/blog/article/api/userDelete',
				method:'POST',
				 data: {
					id:$("#id").val(),
					email:$("#email").val(),
				}, 
				success: function(res)
				{
					if(res.success)
					{
						location.href="/blog"	
					}
				}
			})
		}	
	});	
</script>
</html>