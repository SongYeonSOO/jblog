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
	$(function() {
		//ajax ; 나중에 하자
		$("#login-image").click(function() {
			var id = $("#id").val();
			var passwd = $("#passwd").val();

			if (id == "") {
				alert("아이디를 입력하세요");
				$("#id").val("").focus();
				return false;
			}
			if (passwd == "") {
				alert("비밀번호를 입력하세요");
				$("#passwd").val("").focus();
				return false;
			}
			
			$.ajax({
				//ajax가 js가 참조할 수 있는 객체로 만들어줌! 

				url : "/jblog/user/login?id=" + id + "&passwd=" + passwd, //요청 url
				type : "get",
				dataType : "json",
				data : "",
				// 성공 시 call-back
				success : function(response) {
					//			var returnedData = JSON.parse(response);

					if (response.result != "success") {

						return;
					}

					if (response.data == false) {
						console.log("success:odata false");
						alert("아이디와 비밀번호가 일치하지 않습니다.");

						//email칸 비우고 다시 입력할 수 있도록
 						$("#id").val("").focus();
						$("#passwd").val("").focus(); 

						return;
					}
					console.log("succ&true");
					window.location.replace("/jblog/main");

				},
				// 실패시 call-back
				error : function(jqXHR, status, error) {

					console.log("error: ok");

				}

			});
		});

	});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/include/menu.jsp">
			<c:param name="menu" value="" />
		</c:import>

		<form class="login-form" id="login-form" name="login-form">
			<label>아이디</label> <input type="text" name="id" id="id"> <label>패스워드</label>
			<input type="text" name="passwd" id="passwd">
			<input type="submit" id="login-button" value="로그인" style="display:none"><img id="login-image" src="${pageContext.request.contextPath}/assets/images/login.png">
		</form>				
	</div>
</body>
</html>
