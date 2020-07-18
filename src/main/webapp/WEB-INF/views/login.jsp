<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common-meta.jsp"></jsp:include>
<title>로그인</title>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>

<link href="/blog/resources/css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/oauth/naver.js"></script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/common-header.jsp"></jsp:include>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-12 naver-login-bar">
				<a href="#" id="login-link"><img src="https://static.nid.naver.com/oauth/big_g.PNG?version=js-2.0.0" height="60"></a>
			</div>
			<div class="col-12">
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
				  <a href="#" class="btn btn-primary btn-find-pass">비번 찾기</a>
				</form>
			</div>
			<!-- <div class="col-8">75%</div>
			<div class="col-3">25%</div>
			<div class="col-3">25%</div> -->
		</div>
	</div>
</div>
<!-- The Modal -->
<div class="modal" id="passModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <input type="text" id="email" name="email" >
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
      	<button type="button" class="btn btn-primary btn-pw-reset" data-dismiss="modal">비번 재설정</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
</body>
<script type="text/javascript">window.Naver.prepareLogin()</script>
</html>