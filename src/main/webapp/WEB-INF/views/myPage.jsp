<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common-meta.jsp"></jsp:include>
<title>마이 페이지</title>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>
<script type="text/javascript" src="/blog/resources/js/common/time-util.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mypage.js"></script>
<link href="/blog/resources/css/myPage.css" rel="stylesheet" type="text/css">
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
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" data-toggle="tab" href="#email">이메일</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" data-toggle="tab" href="#leave">탈퇴</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" id="mypost-tab" data-toggle="tab" href="#mypost">내가쓴글</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" id="message" data-toggle="tab" href="#myMessage">쪽지</a>
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
	  <div class="tab-pane container fade" id="mypost">
	  	<label>내글</label>
		<input type="button" id="mypost" class="form-control" value="내글">
		<table class="table">
			<thead>
				<tr>
					<td>제목</td>
					<td>상태</td>
					<td>삭제</td>
				</tr>
			</thead>
			<tbody id="blog-list-body">
				<!-- <tr>
					<td><a href="/blog/article/sddddd">글제목</a></td>
				</tr>
				 -->
			</tbody>
		</table>
	  </div>
	  <div class="tab-pane container fade" id="myMessage">
	  <h3>내 쪽지함</h3>
  	<table class="table">
		<thead>
			<tr>
				<td>보낸 사람</td>
				<td>내용(20글자)</td>
				<td>보낸시간</td>
				<td>읽은시간</td>
				<td>삭제</td>
				<td>읽기</td>
			</tr>
		</thead>
		<tbody id="msg-body">
		<!-- 
			<tr class="un-read">
				<td>다다앙</td>
				<td>안녕하세요...</td>
				<td>333333</td>
				<td>안읽음</td>
			</tr>
			<tr>
				<td>다다앙</td>
				<td>안녕하세요...</td>
				<td>333333</td>
				<td>안읽음</td>
			</tr>
		 -->
		</tbody>
	</table>
	  </div>
	</div>
</div>
<div class="modal" id="noteModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" id="write_reply">답장쓰기</button>
        <button type="button" class="btn btn-secondary" id="modal_cancel" data-dismiss="modal">Close</button>
      </div>
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