<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/common/common-meta.jsp"></jsp:include>
<title>관리자 페이지</title>
<jsp:include page="/WEB-INF/views/common/common-import.jsp"></jsp:include>
<script type="text/javascript" src="/blog/resources/js/admin.js"></script>
</head>
<body>
<div>
	<jsp:include page="/WEB-INF/views/common/common-header.jsp"></jsp:include>
	<ul class="nav nav-tabs">
	  <li class="nav-item">
	    <a class="nav-link" data-toggle="tab" href="#open" id="openTab">공개</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" data-toggle="tab" href="#private" id="privTab">비공개</a>
	  </li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
	  <div class="tab-pane container" id="open">
	  <!-- 
	  	<label>공개</label>
		<input id="open" class="form-control" type="checkbox" readonly>
	   -->
	   <table class="table">
	   	<tbody id="open-body">
		   	<!-- 
		   	<tr>
		   		<td>ㅁ임ㄴ이ㅏㄹ닝ㄹ</td>
		   		<td><button class="btn btn-primary btn-sm">비공개</button></td>
		   	</tr>
		   	 -->
	   	</tbody>
	   </table>
	  </div>
	  <div class="tab-pane container fade" id="private">
	  	<table class="table">
	  		<tbody id="priv-body">
	  			
	  		</tbody>
	  	</table>
		<input id="private" class="form-control" type="checkbox" readonly>
	  </div>
	</div>
	<div>
		<input type="button" value="저장">
	</div>
</div>
</body>
</html>