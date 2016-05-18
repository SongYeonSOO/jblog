<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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

<!-- 
<script type="text/javascript">
</script>
 -->
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/include/menu.jsp">
			<c:param name="menu" value="" />
		</c:import>
		<form class="search-form">
			<fieldset>
				<input type="text" name="kwd" value="검색기능은 안됩니다." /> <input
					type="submit" value="검색" />
			</fieldset>
			<fieldset>
				<input type="radio" name="title" value="blog-title"> <label>블로그
					제목</label> <input type="radio" name="which" value="tag"> <label>태그</label>
				<input type="radio" name="id" value="blog-user"> <label>블로거</label>


				<!-- 블로그  출력 -->
				<table>
					<c:forEach items="${blist}" var="bvo" varStatus="status">
						<tr>
							<td>${requestScope.no-status.index}</td>
							<td style="text-align: left; padding-left: 0px"><a
								href="/jblog/blog/${bvo.no}">${bvo.title}</a></td>
							<td>${bvo.id}</td>
						</tr>
					</c:forEach>
				</table>
			</fieldset>
			<div class="pager">
				<ul>

					<c:if test="${pageinfo.currentpage > 5}">
						<li><a
							href="/jblog/main?page=${pageinfo.beginpage-1}&kwd=${kwd}">◀</a></li>
					</c:if>
					<c:forEach begin="${pageinfo.beginpage}" end="${pageinfo.maxpage}"
						var="viewpage">
						<c:choose>
							<c:when test="${viewpage==pageinfo.currentpage}">
								<li class="selected"><a
									href="/jblog/main?page=${viewpage}&kwd=${kwd}">${viewpage}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/jblog/main?page=${viewpage}&kwd=${kwd}">${viewpage}</a></li>
							</c:otherwise>
						</c:choose>

					</c:forEach>
					<c:if test="${pageinfo.totalpage != pageinfo.maxpage}">
						<li><a
							href="/jblog/main?page=${pageinfo.maxpage+1}&kwd=${kwd}">▶</a></li>
					</c:if>

				</ul>
			</div>
		</form>
	</div>
</body>
</html>