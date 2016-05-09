<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp">
			<c:param name="header" value="" />
		</c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="/jblog/blog/${blogId}/blog-admin-basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="/jblog/blog/${blogId}/blog-admin-write">글작성</a></li>
				</ul>
				<table class="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					<c:forEach items="${category}" var="cvo" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${cvo.name}</td>
							<td>${cvo.post_count}</td>
							<td>${cvo.description}</td>
							
							
				
							<td>
							<c:if test="${cvo.post_count==0}">
							<a href="/jblog/blog/${blogId}/blog-admin-categorydelete/${cvo.category_no}"><img
								src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a>
								</c:if></td>
								
						</tr>
					</c:forEach>
				</table>
				<form action="/jblog/blog/${blogId}/blog-admin-categorying" method="post">
					<h4 class="n-c">새로운 카테고리 추가</h4>
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input type="text" name="name"></td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input type="text" name="description"></td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td><input type="submit" value="카테고리 추가"></td>
						</tr>
					</table>
				</form>

			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp">
			<c:param name="footer" value="" />
		</c:import>
	</div>
</body>
</html>