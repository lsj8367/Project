<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>니책내책 구매메인페이지</title>
<style type="text/css">
a { text-decoration:none !important }
a:hover { text-decoration:none !important }

@font-face {
    font-family: 'TmoneyRoundWindExtraBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindExtraBold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

body {
  font-family: 'TmoneyRoundWindExtraBold';
}

div#s_left{
	width:15%;
	height: 100%;
	float:left;
}
#contents{
	width: 70%;
	height: 100%;
	float: left;
}
#s_right{
	width: 15%;
	height: 100%;
	float:left;
}


html, body {
	height:100%;
	overflow:hidden;
    margin:0;

    padding:0;
}

#footer_wrap {width:100%;height:118px;clear:both;margin-top:30px; position: sticky;}


//테이블
.buymaintable {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: auto;
  float : none;
  padding: 0;
  width: 80%;
  table-layout: fixed;
}

.buymaintable caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

.buymaintable tr {
  background-color: #fdfdfd;
  border: 1px solid #ddd;
  padding: .35em;
}

.buymaintable th,
.buymaintable td {
  padding: .625em;
  text-align: left;
}

.buymaintable th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
}

@media screen and (max-width: 600px) {
  .buymaintable {
    border: 0;
  }

  .buymaintable caption {
    font-size: 1.3em;
  }
  
  .buymaintable thead {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
  }
  
  .buymaintable tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
  }
  
  .buymaintable td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: right;
  }
  
  .buymaintable td::before {
    /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  .buymaintable td:last-child {
    border-bottom: 0;
  }
}
</style>
</head>


<%@include file="header.jsp" %>
<body style="overflow: auto; margin: 0; padding: 0; height: 100%">
<div id="s_left" align="center">
	<table class="buymaintable" style="width: 50%; float: none; margin: auto; text-align: center;">
		<tr>
			<td style="background-color: #114c3d; color: white; text-align: center;">
				베스트 셀러
			</td>
		<tr>
		<tr>
			<td style="text-align: center;">
				
				<a href="newbook?book_no=${bestseller.nb_no}"><img width="100" src="${bestseller.nb_image}"></a>			
			</td>
		<tr>
	</table>
</div>

<div id="contents">
<!-- 메인 3x3으로 나눠서 디자인 -->
<table style="width: 100%; float: none; margin: auto; text-align: center;">
	<tr>
		<td style="width: 60%;">
			<!-- 조회수 Top 3 -->
			<table class="buymaintable"  style="margin: auto; width: 100%;">
				<tr style="text-align: left;">
					<td style="background-color: #114c3d; color: white;">조회수 TOP3</td>
				</tr>
				<tr>
					<td >
						<div style="justify-content:center; display: grid; grid-template: repeat(1, 200px) / repeat(3, 200px); grid-auto-flow: row;">
							<c:forEach var="read" items="${readtop3}">
									<div style="justify-content:center;">
										<div>
											<a href="newbook?book_no=${read.nb_no}"><img width="100" height="160" src="${read.nb_image}"></a>
										</div>	
									</div>
							</c:forEach>
						</div>
					</td>
				</tr>
			</table>
		</td>
		
	</tr>
	<tr>
		<td rowspan="2">
		<table class="buymaintable"  style="margin: auto; width: 100%;">
			<tr style="text-align: left;">
				<td style="background-color: #114c3d; color: white;">오늘의 추천</td>
			</tr>
			<tr>
				<td>
			<!-- 랜덤으로 책 띄우기 -->
					<div style="display: grid; margin-left:100px; grid-template: repeat(2, 200px) / repeat(5, 200px); grid-auto-flow: row; justify-content:center;" >
						<c:forEach var="random" items="${random10}">
								<div>
									<div>
										<a href="newbook?book_no=${random.nb_no}"><img width="100" height="160" src="${random.nb_image}"></a>
									</div>				
								</div>
						</c:forEach>
					</div>
				</td>
			</tr>
		</table>
		</td>
		
	</tr>
</table>
</div>

<div id="s_right">
	<table class="buymaintable"  style="margin: auto; width: 100%;">
		<tr>
			<td style="background-color: #114c3d; color: white; text-align: center;">
			<c:choose>
				<c:when test="${sessionScope.id eq null}">
					방문자님 환영합니다.
				</c:when>
				
				<c:otherwise>
					${sessionScope.name}님 환영합니다.<br>보유포인트는 ${sessionScope.point}point
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td style="text-align: center;">
				<button type="button" class="btn btn-outline-success" onclick="location.href='give'">책 기증하기</button>
				<br>
				<br>
				<button type="button" class="btn btn-outline-success" onclick="window.open('point','포인트TIP','width=400,height=500,location=no,status=no,scrollbars=no')">포인트를 얻고 싶다면?</button>
			
			</td>
		</tr>
	</table>

<br>

</div>


<div id="footer_wrap">
<%@include file="footer.jsp" %>
</div>
</body>
</html>