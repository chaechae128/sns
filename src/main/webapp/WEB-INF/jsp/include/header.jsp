<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="h-100 d-flex justify-content-between align-items-center">
	<%-- logo --%>
	<div>
		<h1>Marondalgram</h1>
	</div>
	
	<%-- 로그인 정보 --%>
	<div>
		<%-- 로그인 시 --%>
		<c:if test="${not empty userId}">
			<span><a href="/user/profile-view">${userName}</a>님 안녕하세요</span>
			<a href="/user/sign-out" class="ml-2 text-white font-weight-bold">로그아웃</a>
		</c:if>
		<%-- 로그아웃 시 --%>
		<c:if test="${empty userId}">
			<a href="/user/sign-in-view" class="ml-2 text-white font-weight-bold">로그인</a>
		</c:if>
	</div>
</div>