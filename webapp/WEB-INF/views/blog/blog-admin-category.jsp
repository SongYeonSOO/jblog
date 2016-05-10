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
var count=1;
var renderHtml = function( cvo ) {
	console.log(cvo);
	// 대신에 js template Library를 사용한다. ex) EJS, Underscore.js
	var html = "<tr id=tr-"+ cvo.category_no + ">" +
					"<td>" + count + " </td>" +
					"<td>" + cvo.name  + "</td>" +
					"<td>" +  cvo.post_count  +"</td>" +
					"<td>" +  cvo.description  +"</td>" +
					"<td> <img src='${pageContext.request.contextPath}/assets/images/delete.jpg' class='delimg' id='delImage-"+ cvo.category_no + "' data-no = " + cvo.category_no +">	</td> " +
					"</tr> " ;
	
	$("#admin-cat").append(html);
	
	if ( cvo.post_count > 0 || cvo.name == '미분류') {
		$("#delImage-"+ cvo.category_no).hide();
	}
	console.log( $("#delImage-"+ cvo.category_no).attr("data-no") );
	count ++;
	$("#delImage-"+ cvo.category_no).css("cursor", "pointer");
	$("#delImage-"+ cvo.category_no).click(function(){		
		console.log("clicked");
	$.ajax({

		//ajax가 js가 참조할 수 있는 객체로 만들어줌! 

		url : "/jblog/blog/${blogId}/blog-admin-categorydelete/"+cvo.category_no, //요청 url
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
				console.log("success:data false");
				alert("삭제실패!");


				return;
			}
			
			$("#tr-"+cvo.category_no).remove();
			
//			window.location.replace("/blog/" + blogId + "/blog-admin-category");
				return ;
				
		},
		// 실패시 call-back
		error : function(jqXHR, status, error) {

			console.log("error: ok");

		}
});
		
	});	
	
};
var fetchList = function() {
	
	$.ajax({
		// 내가 정한 주소 p: page
		//브라우저에서 "/mysite/guestbook?a=ajax-list&p="로 자동으로 바뀐다!!!!
		url : "${pageContext.request.contextPath}/blog/${blogId}/blog-admin-categoryList",
		type : "get",
		dataType : "json", //이거 안쓰면 parsing error난다! listaction이 실행되기 때문
		data : "", //get방식이니깐 본문에 들어가는 data가 없음
		success : function(response) {
			// data: array , vo : object
			$.each(response.data, function(index, cvo) {
				console.log(index + ":" + cvo);
				renderHtml(cvo);
			});
		},
		error : function(xhr, status, error) {
			console.log(status + ":" + error);
		}// xhr : xml http request

	});
};

	
	$(function() {


		$("#form-insert").submit(function(event){
			event.preventDefault();
			//1. 이름 유효성 체크
			var catename = $("#catename").val();
			var catedes = $("#catedes").val();


			if (catename == "") {
				alert("카테고리 이름 없다!");
				$("#catename").val("").focus();
				return false;
			}
			//2. 설명 유효성 체크
		
			if (catedes == "") {
				alert("카테고리 설명 없다!");
				$("#catedes").val("").focus();
				return false;
			}

			$.ajax({
				// 내가 정한 주소 p: page
				//브라우저에서 "/mysite/guestbook?a=ajax-list&p="로 자동으로 바뀐다!!!!
				url : "${pageContext.request.contextPath}/blog/${blogId}/blog-admin-categorying",
				type : "post",
				dataType : "json", //이거 안쓰면 parsing error난다! listaction이 실행되기 때문
				data : "name=" + catename +"&description=" + catedes, //get방식이니깐 본문에 들어가는 data가 없음
				success : function(response) {
					// data: array , vo : object
					renderHtml(response.data);
				},
				error : function(xhr, status, error) {
					console.log(status + ":" + error);
				}// xhr : xml http request
				
			});
			this.reset();
		});
		//ajax ; 나중에 하자
	///{blogId}/blog-admin-categorydelete/{category_no}
		fetchList();
	
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
					<li class="selected">카테고리</li>
					<li><a href="/jblog/blog/${blogId}/blog-admin-write">글작성</a></li>
				</ul>
				<table class="admin-cat" id="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>


				</table>
<form id="form-insert">
					<h4 class="n-c">새로운 카테고리 추가</h4>
					<table id="admin-cat-add">
						<tr>
							<td class="t" >카테고리명</td>
							<td><input type="text" name="name" id="catename"></td>
						</tr>
						<tr>
							<td class="t" >설명</td>
							<td><input type="text" name="description" id="catedes"></td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td><input type="submit" value="카테고리 추가" id="newcate"></td>
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