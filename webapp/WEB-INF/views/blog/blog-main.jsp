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
	src="/jblog/assets/js/jquery/jquery-1.9.0.js">
	
</script>
<script type="text/javascript">
	$(function() {
		//ajax ; 나중에 하자
		$(".blog-list a").click(function() {

		})
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp">
			<c:param name="header" value="" />
		</c:import>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:choose>
					<c:when test="${not empty onepvo}">
							<h4>${onepvo.title}</h4>
							<h5> 작성일: ${onepvo.reg_date}</h5>
							<p>${onepvo.content}</p>
							<div id="deletediv">
							<c:if test="${not empty authUser && authUser.id == blogId}">
							<a href="/jblog/blog/${blogId}/post-delete/${onepvo.post_no}"><img src="${pageContext.request.contextPath}/assets/images/delete.png"></a>
							</c:if>
							</div>
					</c:when>
					<c:when test="${empty plist}">
							<h4>내용이 없습니다</h4>											
					</c:when>
					</c:choose>
				</div>

				<ul class="blog-list">

					<c:forEach items="${plist}" var="pvo">
						<li><a href="/jblog/blog/${blogId}/${category_no}?no=${pvo.post_no}">${pvo.title}</a>
							<span>${pvo.reg_date}</span></li>
							
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="/jblog${bvo.logo}" alt="로고를 삽입해주세요">

			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>

				<!--  default가 미분류 -->
				<c:forEach items="${category}" var="cvo">
					<c:choose>
					<c:when test="${cvo.category_no==category_no}">
							<li><a href="/jblog/blog/${blogId}/${cvo.category_no}"><strong>${cvo.name}</strong></a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/jblog/blog/${blogId}/${cvo.category_no}">${cvo.name}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>

		<c:import url="/WEB-INF/views/include/footer.jsp">
			<c:param name="footer" value="" />
		</c:import>
	</div>
</body>
</html>