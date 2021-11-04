<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<style> 

@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
body{font-family:나눔고딕, NanumGothic, NanumBarunGothic,'Nanum Gothic',arial,verdana,sans-serif;}
#sidebar {
	float: left;
	height: 100%;
	background: black;
}

#sidebar a {
	display: block;
	color:#fff;
	text-decoration:none;
}

.M01 {
	margin: 0px;
	padding:0px;
	background: #000;
	list-style: none;
}

.M01>li {
	position: relative;
	width: 100px;
	height: 50px;
	background: #000;
	text-align: center;
	line-height: 50px;
}

.M01>li:hover .M02 {
	left: 100px;
}
.M01>li a:hover{
	text-decoration:none;
	display:block; 
	width:100px;
	background: #AB06AD;
}

.M02 {
	width:100px;
	background:black;
	position: absolute;
	list-style: none;
	margin: 0px;
	padding: 0px;
	top: 0;
	left: -9999px;
	text-align: center;
}

.M02>li {
	position: relative;
	width: 130px;
	height: 50px;
	background: #000;
	text-align: center;
	line-height: 50px;
}

.M02>li a:hover{
	text-decoration:none;
	display:block; 
	width:130px;
	background: red;
}
</style>
</head>
<body>
<div id="sidebar" style="width: 100px; height: 98%; background-color: black; color: white;">
	<ul class = "M01"> 
		<li><a href="">기본정보</a>
  			<ul class = "M02">
  				<li><a href="admininfo?id=abc">개인정보조회</a></li>
  			</ul>
  		</li>
  		<li><a href="">회원관리</a>
		    <ul class = "M02">
		       <li><a href="userdata">회원자료보기</a></li>
		       <li><a href="">회원별 포인트확인</a></li>
		       <li><a href="">회원별 연체순위</a></li>
		    </ul>
		</li>
		<li><a href="">상품관리</a>
		    <ul class = "M02">
				<li><a href = "bookregister">도서등록</a></li>
				<li><a href="newbookdata">전체도서관리</a></li>
				<li><a href="">승인대기기부도서</a></li>
				<li><a href="">리뷰관리</a></li>    
			</ul>
		</li>
		<li><a href="">주문관리</a>
		    <ul class = "M02">
		       <li><a href="">주문내역관리</a></li>
		       <li><a href="">결제여부확인</a></li>
		       <li><a href="">배송관리</a></li>
		       <li><a href="">관리자주문취소</a></li>
		    </ul>
		</li>
		<li><a href="">대여관리</a>
		    <ul class = "M02">
		       <li><a href="">대여내역관리</a></li>
		       <li><a href="">대여중인 책 관리</a></li>
		    </ul>
		</li>
		<li><a href="">매출통계</a>
		    <ul class = "M02">
		       <li><a href="">베스트리뷰</a></li>
		       <li><a href="">베스트셀러</a></li>
		       <li><a href="">베스트대여도서</a></li>
		       <li><a href="">월별매출확인</a></li>
		       <li><a href="">이달의 대여왕</a></li>
		    </ul>
		</li>
		<!-- <li><a href="">회원관리</a>
		    <ul class = "M02">
		       <li><a href="">직원승인</a></li>
		       <li><a href="">직원내역확인</a></li>
		    </ul>
		</li> -->
	</ul>
</div>
</body>
</html>