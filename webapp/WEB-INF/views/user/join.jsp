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
		$("#img-checkid0").show();
		//ajax ; 나중에 하자
		$("#join-submit").click(function() {
			
			//submit해도 무시당함  return false;
			//return true; 일때만 가능

			//1. 이름 유효성 체크
			var name = $("#name").val();
			if (name == "") {
				alert("이름 없다!");
				$("#name").val("").focus();
				return false;
			}

			//2. 이메일 유효성 체크
			// 2-1. 입력체크
			var id = $("#blog-id").val();
			if (id == "") {
				alert("id 없다!");
				$("#blog-id").val("").focus();
				return false;
			}
			//2-2. 이메일 중복 체크
			if ($("#img-checkid").is(":visible") == false) {
				alert("id 체크!!!!!!!");
				return false;
			}

			//3. 패스워드 유효성 체크
			var passwd = $("#passwd").val();
			if (passwd == "") {
				alert("pw 없다!");
				$("#passwd").val("").focus();
				return false;
			}

			//4. checkbox 유효성 체크
			var check = $("#agree-prov").is(":checked");
			if (check == false) {
				alert("약관 동의!!!!!!!!!!");
				return false;
			}
	 		$("#join-form").submit();
	 	});
		
		$("#blog-id").change(function() {
			$("#img-checkid0").show();
			$("#img-checkid").hide();

		});
		$("#img-checkid0").click(function() {
			var id = $("#blog-id").val(); // value가 아니라 val이라는 ftn
			if (id == "") {
				alert("id 없다!");
				$("#blog-id").val("").focus();
				return false;
			}

				$.ajax({
				//ajax가 js가 참조할 수 있는 객체로 만들어줌! 

				url : "/jblog/user/checkid?id=" + id, //요청 url
				type : "get", //통신 방식 get/post
				dataType : "json", //수신데이터타입
				data : "", //post방식인 경우 서버에 전달할 parameter data

				// contentType:""					보내는 data가 json형식인 경우 반드시 post방식으로 보내야 한다 
				//contentType:"application/json"
				//data: "{"a":"checkid",id:"kickscar@gmail.com"}""
				// 성공 시 call-back
				success : function(response) {
				
					if (response.result != "success") {
						return;
					}

					if (response.data == false) {
						alert("이미 존재하는 이메일 다른 거");

						//id칸 비우고 다시 입력할 수 있도록
						$("#blog-id").val("").focus();
						return false;
					}

					//사용가능한 이메일
					//api 함수의 특정 api를 보이는 것은 show를 이용함
					$("#img-checkid0").hide();
					$("#img-checkid").show();
				},
				// 실패시 call-back
				error : function(jqXHR, status, error) {
					console.error(status + ":" + error);
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

		<form class="join-form" id="join-form" method="post"
			action="/jblog/user/joinning">
			<label class="block-label" for="name">이름</label> <input id="name"
				name="name" type="text" value=""> <label class="block-label"
				for="blog-id">아이디</label> <input id="blog-id" name="id" type="text">
			<input id="btn-checkid" type="button" value="id 중복체크" style="display: none;"> 
			<img
				id="img-checkid0" style="display: none;"
				src="${pageContext.request.contextPath}/assets/images/checking.png">
			<img
				id="img-checkid" style="display: none;"
				src="${pageContext.request.contextPath}/assets/images/check.png">
			<label class="block-label" for="passwd">패스워드</label> <input
				id="passwd" name="passwd" type="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input id="join-submit0" type="submit" value="가입하기" style="display: none"><img
				id="join-submit"
				src="${pageContext.request.contextPath}/assets/images/signup.png">

		</form>
	</div> 
</body>
</html>
