<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새책</title>
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
	
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<!-- 검색창 메소드 -->
<script type="text/javascript">
	window.onload = function() {
		document.getElementById("btnSearch").onclick = function() {
			if (frm.search.value === "") {
				frm.search.focus();
				$('#dialog').dialog({
					title : '검색오류!',
					modal : true, //팝업창으로 띄우기
					width : '300', //폭
					height : '300', // 높이
					buttons : { //다이얼로그 안에 버튼
						"확인" : function() {
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

<style>
body {

	padding-top: 0px;

	padding-bottom: 30px;

	font-family: 'TmoneyRoundWindRegular';
}


@font-face {
	font-family: 'TmoneyRoundWindRegular';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindRegular.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

.menu a {
	cursor: pointer;
}

.menu .hide {
	display: none;
}

.dropdownmenu ul, .dropdownmenu li {
	margin: 0;
	padding: 0;
}

.dropdownmenu ul {
	background: white;
	list-style: none;
	width: 100%;
}

.dropdownmenu li {
	float: left;
	position: relative;
	width: auto;
}

.dropdownmenu a {
	background: #FFFFFF;
	color: blue;
	display: block;
	padding: 10px 25px;
	text-align: center;
	text-decoration: none;
	-webkit-transition: all .25s ease;
	-moz-transition: all .25s ease;
	-ms-transition: all .25s ease;
	-o-transition: all .25s ease;
	transition: all .25s ease;
}

.dropdownmenu li:hover a {
	background: #FFFFFF;
}

#submenu {
	left: 0;
	opacity: 0;
	position: absolute;
	top: 35px;
	visibility: hidden;
	z-index: 1;
}

li:hover ul#submenu {
	opacity: 1;
	top: 40px;
	visibility: visible;
}

#submenu li {
	float: none;
	width: 100%;
}

#submenu a:hover {
	background: #DF4B05;
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
					        <a class="nav-link" href="buymain">Home<span class="sr-only"></span></a>
					      </li>
					      <li class="nav-item">
					      	<a class="nav-link" href="#" onclick='window.open("login","_blank","height=500,width=500, status=yes,toolbar=no,menubar=no,location=no");return false'>로그인</a>
					      </li>
					      <li class="nav-item">
					        
					        <a class="nav-link" href="#" onclick='window.open("signup","_parent","height=700,width=500, status=yes,toolbar=no,menubar=no,location=no");return false'>회원가입</a>
					      </li>
					      <li class="nav-item">
					        <a class="nav-link" href="unmembercheck">비회원주문조회</a>
					      </li>
					      <li class="nav-item">
					        <a class="nav-link" href="sos?page=main">1:1문의</a>
					      </li>
					
					    </ul>
					
				</c:when>

				<c:otherwise>
					<ul class="navbar-nav mr-auto">
				      <li class="nav-item active">
				        <a class="nav-link" href="main">Home<span class="sr-only"></span></a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="logout">로그아웃</a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="mypage">마이페이지</a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="cart?page=main">장바구니</a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="sos?page=main">1:1문의</a>
				      </li>
				      <li class="nav-item">
				        <a class="nav-link" href="#">${sessionScope.name}님 로그인중입니다.</a>
				      </li>
				
				    </ul>
				</c:otherwise>
			</c:choose>
    
		<form action="search" method="post" id="frm">
				<select name="type">
					<option value="nb_name" selected="selected">제목</option>
					<option value="nb_comp">출판사</option>
				</select>
				<input type="text" name="search" placeholder="도서 검색하기">
				<input type="button" value="검색" id="btnSearch">
		</form>

  </div>

</nav>




	<div id="dialog" style="display: none;">
		<p>검색어를 입력하세요</p>
	</div>

	<header>
		<br>
		<table style="width: 60%; margin: auto;">
			<tr style="text-align: center;">
				<th><a href="list?book=all">전체보기</a></th>
				<th>
					<nav class="dropdownmenu">
						<ul>
							<li style="width: 100%"><a style="color: blue;">장르</a>
								<ul id="submenu" class="hide" onchange="location.href=this.value">
									<li><a href="list?book=a">미분류(default)</a></li>
									<li><a href="list?book=b">소설/시/희곡</a></li>
									<li><a href="list?book=c">에세이</a></li>
									<li><a href="list?book=d">어린이</a></li>
									<li><a href="list?book=e">유아</a></li>
									<li><a href="list?book=f">경제경영</a></li>
									<li><a href="list?book=g">인문학</a></li>
									<li><a href="list?book=h">외국어</a></li>
									<li><a href="list?book=i">사회과학</a></li>
									<li><a href="list?book=j">수험서/자격증</a></li>
									<li><a href="list?book=k">대학교재</a></li>
									<li><a href="list?book=l">it/모바일</a></li>
								</ul></li>
						</ul>
					</nav>
				</th>
				<th><a href="list?book=best">베스트30</a></th>
				<th><a href="list?book=new">신간</a></th>
				<th><a href="rentlist1?book=rentmain">중고책관련</a></th>
			</tr>
		</table>

	</header>

</body>
</html>