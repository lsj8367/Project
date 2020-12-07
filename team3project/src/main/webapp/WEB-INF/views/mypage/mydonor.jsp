<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
//a태그
a { text-decoration:none !important } 
a:hover { text-decoration:none !important }

@font-face {
    font-family: 'TmoneyRoundWindExtraBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindExtraBold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}


//
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

<%@include file="../header.jsp"%>
<body style="overflow: auto; margin: 0; padding: 0; height: 100%">

<br><br>
<div id="s_left" align="center">
	<table class="buymaintable" style="width: 80%; float: none; margin: auto; text-align: center;">
		<tr>
			<td style="background-color: #114c3d; color: white; text-align: center;">
				Quick Menu
			</td>
		<tr>
		<tr>
			<td style="text-align: center;">
				<button type="button" class="btn btn-outline-success" onclick="location.href='mypage'">마이페이지</button>
				<br><br>
				<button type="button" class="btn btn-outline-success" onclick="location.href='myorder'">주문내역/배송조회</button>
				<br><br>
				<button type="button" class="btn btn-outline-success" onclick="location.href='myrent'">대여내역/연체조회</button>
			</td>
		<tr>
	</table>
</div>

<div id ="contents">
<table class="buymaintable" style="width: 80%; float: none; margin: auto; text-align: center;">
	<!-- 1tr -->
	<tr>
		<td style="width: 60%;"  rowspan="5">
			<table  class="buymaintable" style="width: 80%; float: none; margin: auto; text-align: center;">
						<tr>
							<td colspan="2" style="background-color: #114c3d; color: white; text-align: center;">
								전체 기증 내역
							</td>
						</tr>
						<tr>
							<th>도서명</th><th>나의 한마디</th>
						</tr>
						
						<c:forEach var="donor" items="${dnabook}">
				<tr>
					<td>${donor.ob_name}</td>
					<td>${donor.ob_comment}</td>
				</tr>
				</c:forEach>
					</table>

		</td>
	</tr>
</table>
<div id="footer_wrap">
<%@include file="../footer.jsp"%>
</div>
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
		
	</table>
<br>

</div>

</body>

</html>