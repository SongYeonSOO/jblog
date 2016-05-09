<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="menu">
	<h1 class="logo">
		<a href="/jblog/main" id="main-logo"><span>JBlog</span></a>
	</h1>
	<ul class="menu">
		<c:choose>
			<c:when test="${empty authUser}">
				<li><a href="/jblog/user/loginform">로그인</a></li>
				<li><a href="/jblog/user/join">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="/jblog/user/logout">로그아웃</a></li>
				<li><a href="/jblog/blog/${authUser.id}">내블로그</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
