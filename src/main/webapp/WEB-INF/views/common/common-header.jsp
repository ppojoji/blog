<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="blog-header">
	<h3>개인 블로그</h3>
		
	<div class="menu">
        <a class="menu-item" href="<c:url value="/"/>">HOME</a>
        <c:if test="${ not empty LOGIN_USER}"><a href="<c:url value="/myPage"/>">${LOGIN_USER.id}</a></c:if>
        <c:if test="${ not empty LOGIN_USER}"><a href="<c:url value="/logout"/>">LOGOUT</a></c:if>
        <c:if test="${ empty LOGIN_USER}"><a class="menu-item" href="<c:url value="/login"/>">LOGIN</a></c:if>
    </div>
</div>