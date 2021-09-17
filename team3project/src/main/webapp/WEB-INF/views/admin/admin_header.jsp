<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css">
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<link href='https://fonts.googleapis.com/css?family=Lato:400,300,700' rel='stylesheet' type='text/css'>

<style>
@font-face {
    font-family:'TmoneyRoundWindExtraBold'';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindExtraBold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@import
	url(https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,300,600)
	;
.mainbar{
  position: fixed;
  top: 0;
  width: 100%;
  box-sizing: border-box;
  z-index: 100;

}
header {
  top: 0;
  width: 100%;
  font-family:'TmoneyRoundWindExtraBold';
}

body {
	font-family: 'TmoneyRoundWindExtraBold';
	font-size: 100%;
	overflow-y: scroll;
	-ms-text-size-adjust: 100%;
	-webkit-text-size-adjust: 100%;
	-ms-text-size-adjust: 100%;
	-webkit-text-size-adjust: 100%;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
	text-rendering: optimizeLegibility;
	background-color: #fefefe;
}

a {
	text-decoration: none;
	-webkit-transition: all 0.6s ease;
	-moz-transition: all 0.6s ease;
	transition: all 0.6s ease;
}

a:hover {
	-webkit-transition: all 0.6s ease;
	-moz-transition: all 0.6s ease;
	transition: all 0.6s ease;
}

.app {
	height: 100vh;
}

/* -------------
Sidebar
----------------*/
.sidebar {
	position: absolute;
	width: 16em;
	height: 100%;
	top: 0;
	overflow: hidden;
	background-color: #19222a;
	-webkit-transform: translateZ(0);
	visibility: visible;
	-webkit-backface-visibility: hidden;
}

.sidebar header {
	background-color: black;
	width: 100%;
	display: block;
	padding: 0.75em 1em;
	z-index: 1000;
}

.sidebar button{
	background: black;
  color: white;
  border: 2px solid black;
  font-size: 30px;
 z-index:130;
	
}

/* -------------
Sidebar Nav
----------------*/
.sidebar-nav {
	position: fixed;
	background-color: #19222a;
	height: 100%;
	font-weight: 16em;
	font-size: 1.2em;
	overflow: auto;
	padding-bottom: 6em;
	z-index: 9;
	overflow: hidden;
	-webkit-scrollbar { display: none; }

	/* -------------
  Chev elements
  ----------------*/
	/* -------------
  Nav-Flyout
  ----------------*/
	/* -------------
  Hover
  ----------------*/
}

.sidebar-nav ul {
	list-style: none;
	display: block;
	padding: 0;
	margin: 0;
}


img {
	font-size: 1.8em;
	padding-right: 0.5em;
	width: 60px;
	margin-top: 0;
	display: inline;
	vertical-align: middle;
}

.sidebar-nav ul li {
	margin-left: 0;
	padding-left: 0;
	display: inline-block;
	width: 100%;
	list-style: none;
	/* -------------
  Sidebar: icons
  ----------------*/
}

.sidebar-nav ul li a {
	color: rgba(255, 255, 255, 0.9);
	font-size: 0.75em;
	padding: 1.05em 1em;
	position: relative;
	display: block;
}

.sidebar-nav ul li a:hover {
	background-color: rgba(0, 0, 0, 0.9);
	-webkit-transition: all 0.6s ease;
	-moz-transition: all 0.6s ease;
	transition: all 0.6s ease;
	position: relative;
	display: block;
}

.sidebar-nav ul li i {
  font-size: 1.8em;
  padding-right: 0.5em;
  width: 9em;
  display: inline;
  vertical-align: middle;
}

.sidebar-nav ul li img {
	font-size: 1.8em;
	padding-right: 0.5em;
	width: 30px;
	display: inline;
	vertical-align: middle;
}

.sidebar-nav>ul>li>a:after {
	content: '\f125';
	font-family: 'TmoneyRoundWindExtraBold';
	font-size: 0.5em;
	width: 10px;
	color: #fff;
	position: absolute;
	right: 0.75em;
	top: 45%;
}

.sidebar-nav .nav-flyout {
	position: absolute;
	background-color: #080D11;
	z-index: 9;
	left: 2.5em;
	top: 0;
	height: 100vh;
	-webkit-transform: translateX(100%);
	-moz-transform: translateX(100%);
	-ms-transform: translateX(100%);
	-o-transform: translateX(100%);
	transform: translateX(100%);
	-webkit-transition: all 0.5s ease;
	-moz-transition: all 0.5s ease;
	transition: all 0.5s ease;
}

.sidebar-nav .nav-flyout a:hover {
	background-color: rgba(255, 255, 255, 0.05);
}

.sidebar-nav ul>li:hover .nav-flyout {
	-webkit-transform: translateX(0);
	-moz-transform: translateX(0);
	-ms-transform: translateX(0);
	-o-transform: translateX(0);
	transform: translateX(0);
	-webkit-transition: all 0.5s ease;
	-moz-transition: all 0.5s ease;
	transition: all 0.5s ease;
}

::-webkit-scrollbar {
	display: none; 
}


.app{
-ms-overflow-style: none; 
}
.app::-webkit-scrollbar{ 
display:none; 
}

.w3-container a img{
		width: 50px;
	}

.sidebar button, .w3-teal button{
  background: black;
  color: white;
  border: 2px solid black;
  font-size: 30px;
  text-transform: uppercase;
  cursor: pointer;
  display: inline-block;
  margin: 15px 30px;
/* animate and time the transitions*/
  -webkit-transition: all 0.4s;
  -moz-transition: all 0.4s;
  transition: all 0.4s;
	text-align: center; 
	width: 40px; 
	margin-top: 0;
}

.sidebar button:hover, .w3-teal button:hover{
  color: gray;
  background-color: black;
  border: 0px;
  -webkit-transition: all 0.4s;
  -moz-transition: all 0.4s;
  transition: all 0.4s;
}


.w3-container{
  margin: 15px;
  height: 50px;
  font-size: 14px;
  background-color: black;
	}

.scroll {
  box-shadow: 0 7px 0 0 rgba(0, 0, 0, .1);
}
 
.scroll .mainbar {
  font-size: 1.5rem;
}
 
.scroll header nav {
  align-items: center;
}

.scroll .w3-container{
  margin: 0;
  height: 40px;
}

.sidebar-nav .eventbutton{
	display:none;
}

.sidebar-nav .closebutton, .sidebar-nav .closebutton:hover{
	display:inline-block;
	background-color: #19222a;
	cursor: pointer;
	border: none;
	width: 140px;
	font-size: 20px;
}

@media screen and (max-width: 1060px) {
  .mainbar {
    height: 40px;
  }
  
  .w3-container{
  margin: 0;
  height: 30px;
  background-color: black;
  display: none;
	}
	
	.w3-container a img{
		width: 40px;
	}
	

 .sidebar button{
  	font-size: 25px;
  }
  
  .sidebar header{
  	display:none;
  }

.sidebar-nav .eventbutton, .sidebar-nav .eventbutton:hover{
	display:inline-block;
	background-color: #19222a;
	cursor: pointer;
	border: none;
	width: 140px;
	font-size: 20px;
}
  }
</style>
</head>
<body>
<div class="mainbar">
	<div class="w3-sidebar w3-bar-block w3-card w3-animate-left"
		style="display: none" id="mySidebar">
		<section class="app">
			<aside class="sidebar">
				<header>
				<table>
				<tr style="text-align: center; margin: auto;">
				<td>
					<a href="gomain"><img src='<c:url value="resources/static/images/mbookw.png" />' /></a>
				</td>
				<td>
					<button class="w3-bar-item w3-button w3-large" onclick="w3_close()">&times;</button>
				</td>
				</tr>
				</table>
				</header>
				<nav class="sidebar-nav">
					
					<ul>
						<li><a href=""><i class="fas fa-file-alt"></i> <span>기본정보</span> </a>
							<ul class="nav-flyout">
								<li><a href="admininfo"><i class="fas fa-user-cog"></i>개인정보조회</a></li>
								<li><a href="addfaq"><i class="fas fa-question-circle"></i>FAQ추가</a></li>
								<li><a href="faqmanage"><i class="far fa-question-circle"></i>FAQ관리</a></li>
							</ul></li>
						<li><a href=""><i class="fas fa-users"></i><span>회원관리</span></a>
							<ul class="nav-flyout">
								<li><a href="userdata"><i class="far fa-address-card"></i>회원자료보기</a></li>
								<li><a href="userinquiry"><i class="xi-comment-o" ></i>1:1문의관리</a></li>
								<li><a href="userpointcheck"><i class="fas fa-ruble-sign"></i>회원별 포인트확인</a></li>
								<li><a href="userpenalty"><i class="fas fa-user-check"></i>회원패널티적용</a></li>
								<li><a href="userpenaltycheck"><i class="fas fa-user-minus"></i>회원패널티확인</a></li>
							</ul></li>
						<li><a href=""><i class="ion-ios-book" ></i><span>상품관리</span></a>
							<ul class="nav-flyout">
								<li><a href="bookregister"><i class="ion-ios-book-outline" ></i>도서등록</a></li>
								<li><a href="newbookdata"><i class="fas fa-book"></i>전체도서관리</a></li>
								<li><a href="donatebooklist"><i class="xi-spinner-2" ></i>승인대기기부도서</a></li>
								<li><a href="selloblist"><i class="xi-book-o" ></i>판매중고책관리</a></li>
								<li><a href="reuse"><i class="fas fa-tasks"></i>승인거절도서관리</a></li>
								<li><a href="reviewmanage"><i class="xi-message-o" ></i>리뷰관리</a></li>
							</ul></li>
						<li><a href=""><i class="xi-cart-o" ></i><span>주문관리</span></a>
							<ul class="nav-flyout">
								<li><a href="orderlist"><i class="xi-library-books-o" ></i>주문내역관리</a></li>
								<li><a href="nobankbookadmit"><i class="xi-money" ></i>결제여부확인</a></li>
								<li><a href="delivery"><i class="xi-box"></i>배송관리</a></li>
								<li><a href="ordercancel"><i class="fas fa-retweet"></i>관리자주문취소</a></li>
							</ul></li>
						<li><a href=""><i class="fas fa-book-reader"></i><span>대여관리</span></a>
							<ul class="nav-flyout">
								<li><a href="rentlist"><i class="xi-library-books" ></i>대여내역관리</a></li>
								<li><a href="oblist"><i class="xi-book" ></i>중고책관리</a></li>
							</ul></li>
						<li><a href=""><i class="xi-chart-bar" ></i><span>매출통계</span></a>
							<ul class="nav-flyout">
								<li><a href="bestreviewset"><i class="fas fa-comment-dots"></i>베스트리뷰통계</a></li>
								<li><a href="bestsellerset"><i class="xi-receipt" ></i>베스트셀러통계</a></li>
								<li><a href="bestrentbookset"><i class="fab fa-elementor"></i>베스트대여도서통계</a></li>
								<li><a href="monthprofit"><i class="xi-chart-line" ></i>월별매출확인</a></li>
								<li><a href="monthrenter"><i class="xi-crown"></i>이달의 대여왕</a></li>
								<li><a href="monthbuyer"><i class="fas fa-crown"></i>이달의 구매왕</a></li>
							</ul>
						</li>
						<c:if test="${info.adminJik eq '이사' or info.adminJik eq '사장' }">
							 <li><a href=""><i class="xi-key" ></i><span>직원관리</span></a>
							    <ul class = "nav-flyout">
							       <li><a href="jikwonok"><i class="xi-shield-checked" ></i>직원승인</a></li>
							       <li><a href="jikwoninfo"><i class="xi-security" ></i>직원내역확인</a></li>
							    </ul>
							</li>
						</c:if>
						<li><button class="closebutton" onclick="w3_close()">close</button></li>
						<li><button class="eventbutton" onclick="location.href='gomain'">main</button></li>
						<li><button class="eventbutton" onclick="location.href='adminlogin'">home</button></li>
						<li><button class="eventbutton" onclick="location.href='admin_logout'">logout</button></li>
					</ul>
					
				</nav>
			</aside>
		</section>
	</div>
	<div id="main" style="background-color: black">
		<div class="w3-teal" >
		<table style="width: 100%; height:30px; background-color: black" >
			<tr>
				<td>
					<button class = "toggle-menu" id="openNav" onclick="w3_open()">&#9776;</button>
				</td>
				<td align="right">
					<div class="w3-container">
					<c:if test="${info.adminId ne null }">
						${info.adminJik } &nbsp;&nbsp;
						${info.adminName }님 안녕하세요 &nbsp;&nbsp;
						<a href = "adminlogin"><img src='<c:url value="images/home.jpg" />' /></a>
						<a href = "admin_logout"><img src='<c:url value="images/logout_black.jpg" />' /></a>&nbsp;
					</c:if>
					</div>
				</td>
			</tr>
		</table>
			
		</div>

		

	</div>
</div>
<main></main>
	<script>
		function w3_open() {
			document.getElementById("main").style.marginLeft = "16em";
			document.getElementById("mySidebar").style.width = "16em";
			document.getElementById("mySidebar").style.display = "block";
			document.getElementById("openNav").style.display = "none";
		}
		function w3_close() {
			document.getElementById("main").style.marginLeft = "0%";
			document.getElementById("mySidebar").style.display = "none";
			document.getElementById("openNav").style.display = "inline-block";
		}
	</script>
</body>
</html>