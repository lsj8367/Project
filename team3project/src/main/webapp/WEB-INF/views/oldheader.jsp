<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중고</title>
<!-- jQuery -->

<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

 -->

<%-- <script src="${pageContext.request.contextPath}/resources/common/js/jquery-3.3.1.min.js" ></script> --%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Bootstrap CSS -->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" 
integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">


<!-- Bootstrap theme -->

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css">



<!-- common CSS -->

<link rel="stylesheet" href="<c:url value='/resources/common/css/common.css'/>">
	
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- 검색창 메소드 -->
<script type="text/javascript">
window.onload = function() {
	document.getElementById("btnSearch").onclick = function() {
		if(frm.search.value === ""){
			frm.search.focus();
			//alert("검색어를 입력하세요");
			 
			 //$('#dialog').dialog();
			 $('#dialog').dialog({
		      title: '검색오류!',
		      modal: true, //팝업창으로 띄우기
		      width: '300', //폭
		      height: '300', // 높이
		      buttons:{ //다이얼로그 안에 버튼
	              "확인":function(){
	              $(this).dialog("close");
	            }
		     }
			});
			return;
		}
		frm.submit();
	}
}
</script>
<style type="text/css">
body {

	padding-top: 0px;

	padding-bottom: 30px;

}


@font-face {
	font-family: 'TmoneyRoundWindRegular';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindRegular.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

body {
	font-family: 'TmoneyRoundWindRegular';
}

.menu a {
	cursor: pointer;
}

.menu .hide {
	display: none;
}

</style>
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
  <a class="navbar-brand" href="main">My B Your B</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>


  <div class="collapse navbar-collapse" id="navbarsExample03">
			<c:choose>
				<c:when test="${sessionScope.id eq null}">
					    <ul class="navbar-nav mr-auto">
					      <li class="nav-item active">
					        <a class="nav-link" href="rentlist1?book=rentmain">Home<span class="sr-only"></span></a>
					      </li>
					      <li class="nav-item">
					        <a class="nav-link" href="login">로그인</a>
					      </li>
					      <li class="nav-item">
					        <a class="nav-link" href="signup">회원가입</a>
					      </li>
					      <li class="nav-item">
					        <a class="nav-link" href="unmembercheck">비회원주문조회</a>
					      </li>
					      <li class="nav-item">
					        <a class="nav-link" href="sos?page=main">1:1문의</a>
					      </li>
					      <li class="nav-item">
					        <a class="nav-link" href="#">방문해주셔서 감사합니다</a>
					      </li>
					    </ul>
					
				</c:when>

				<c:otherwise>
					<ul class="navbar-nav mr-auto">
				      <li class="nav-item active">
				        <a class="nav-link" href="rentmain?book=rentmain">Home<span class="sr-only"></span></a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="logout">로그아웃</a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="mypage">마이페이지</a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="cart?page=rentmain">장바구니</a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="sos?page=main">1:1문의</a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="buymain">새책 구매하기</a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="#">${sessionScope.name}님 로그인중입니다.</a>
				      </li>
				    </ul>
				</c:otherwise>
			</c:choose>
    
	<form action="oldsearch" method="post" id="frm">
				<select name="type">
					<option value="ob_name" selected="selected">제목</option>
					<option value="ob_comp">출판사</option>
				</select>
				<input type="text" id="search" name="search" placeholder="도서 검색하기">
				<button type="button" class="btn btn-secondary" id="btnSearch">검색</button>
		</form>

  </div>

</nav>

<div id="dialog" style="display: none;">
   <p>검색어를 입력하세요</p>
</div>
</body>
</html>