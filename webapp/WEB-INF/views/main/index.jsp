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
				<input type="text" name="keyword" value="검색기능은 안됩니다." /> 
				<input type="submit" value="검색" />
			</fieldset>
			<fieldset>
				<input type="radio" name="which" value="blog-title"> <label>블로그
					제목</label> <input type="radio" name="which" value="tag"> <label>태그</label>
				<input type="radio" name="which" value="blog-user"> <label>블로거</label>


				<!-- 블로그  출력 -->
				<table>
					<c:forEach items="${list}" var="vo" varStatus="status">
						<tr>
							<td>${requestScope.boardno-status.index}</td>
							<td style="text-align: left; padding-left: 0px"><a
								href="/jblog/blog/blog-main?no=${vo.no}">${vo.title}</a></td>
							<td>${vo.id}</td>
						</tr>
					</c:forEach>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>