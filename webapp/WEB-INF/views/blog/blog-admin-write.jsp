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
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(function(){
$("#writing").submit(function(){
	//1. 제목 유효성 체크
	var catename = $("#title").val();
	if (catename == "") {
		alert("제목 없다!");
		$("#title").val("").focus();
		return false;
	}
	//2. 내용 유효성 체크
	var conename = $("#textcontent").val();
	if (conename == "") {
		alert("내용 없다!");
		$("#textcontent").val("").focus();
		return false;
	}
	return true;
	});
	
});
</script>
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
					<li><a href="/jblog/blog/${blogId}/blog-admin-category">카테고리</a></li>
					<li class="selected">글작성</li>
				</ul>
				<form action="/jblog/blog/${blogId}/blog-admin-writing" method="post" id="writing">
					<table class="admin-cat-write">
						<tr>
							<td class="t">제목</td>
							<td><input type="text" size="60" name="title" id="title">
							 <select name="category">
									<c:forEach items="${category}" var="category">
										<option value="${category.category_no}">${category.name}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td class="t">내용</td>
							<td><textarea name="content" id="textcontent"></textarea></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td class="s"><input type="submit" value="포스트하기"></td>
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