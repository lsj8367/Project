<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<style type="text/css">
@font-face {
    font-family: 'TmoneyRoundWindRegular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindRegular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

body {
  padding-top: 200px;
  font-family: 'TmoneyRoundWindRegular';
}

//테이블 적용
.newbooktable {
  font-family: 'TmoneyRoundWindRegular';
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin-left:auto;
  margin-right:auto;
  float : none;
  padding: 0;
  width: 50%;
  table-layout: fixed;
  text-align: center;
}

.newbooktable caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

.newbooktable tr {
  background-color: #fdfdfd;
  border: 1px solid #ddd;
  padding: .35em;
}

.newbooktable th,
.newbooktable td {
  padding: .625em;
  text-align: center;
  font-family: 'TmoneyRoundWindRegular';
}

.newbooktable td input, .newbooktable td textarea, .newbooktable td select{
	width: 100%; border: 0; font-family: 'TmoneyRoundWindRegular'; text-align: center;
}

.newbooktable th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
  background:gray;
  color: white;
  font-family: 'TmoneyRoundWindRegular';
}

@media screen and (max-width: 600px) {
  .newbooktable {
    border: 0;
  }

  .newbooktable caption {
    font-size: 1.3em;
  }
  
  .newbooktable th {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
    font-family:  'TmoneyRoundWindRegular';
  }
  
  .newbooktable tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
    text-align: center;
  }
  
  .newbooktable td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: center;
  }
  
  .newbooktable td::before {
    /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  .newbooktable td:last-child {
    border-bottom: 0;
  }
  
}
</style>
<title>Insert title here</title>
</head>
<%@include file="admin_header.jsp"%>
<body>
<form method="post" action="delorder">
	<table class="newbooktable" style="width: 90%; margin-left: auto; margin-right: auto;">
		<tr><th>주문번호</th><th>주문자</th><th>구매도서번호</th><th>구매량</th><th>미입금기간</th><th>주문상태</th></tr>
		<c:choose>
		<c:when test="${fn:length(delay) eq 0}">
			<tr>
				<td colspan='4' style="text-align: center;">입금기한을 넘은 주문자가 존재하지 않습니다.<td>
			</tr>
			<tr>
				<td colspan='4' style="text-align: center;"><input type="button" value="메인으로 이동" onclick = "location.href='admin'"></td>
			</tr>
		</c:when>
		<c:otherwise>
		<c:forEach var="d" items="${delay }">
			<tr>
				<td>
					${d.orderlistNo}
					<input type = "hidden" name="orderlist_no" value="${d.orderlistNo}">
				</td>
				<td>${d.orderPerson}</td>
				<td>${d.orderBookno }<input type = "hidden" name="order_bookno" value="${d.orderBookno}"></td>
				<td>${d.orderScount }<input type = "hidden" name="order_scount" value="${d.orderScount}"></td>
				<td>${d.orderDelay}</td>
				<td>주문완료</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan='6' style="text-align: center;">
				<button type="submit" class="btn btn-outline-secondary">주문삭제</button>
			</td>
		</tr>
		</c:otherwise>
		</c:choose>
	</table>
	</form>
</body>
<%@include file="admin_footer.jsp"%>
</html>