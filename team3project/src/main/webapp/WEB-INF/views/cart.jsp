<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
</head>
<body>
장바구니 메뉴 준비중입니다.

<!--  
<form action="buyall" method="post" name="cartfrm">
			보기		주문번호		주문인		금액		삭제<br>
<c:choose>
	<c:when test="${f:length(list) eq 0}">
		목록이 없습니다.
	</c:when>
	<c:otherwise>
		<c:set var="sum" value="0"/>
		<c:forEach var="c" items="${list}" varStatus="status">
		<input type="hidden" name="page" value="< a %>">
		<input type="hidden" name="ob_no" value="${c.ob_no }">
				<img width="70" src="./${c.ob_image }">
		<input type="hidden" name="no" value="${status.count }">
				${c.ob_name }<br>
				${c.ob_author }<br>
				${c.ob_inter } <br>
				${c.ob_genre }<br>
				${c.ob_comp }<br>
				${c.ob_price } 원<br>
				<input type="submit" value="삭제" formaction="deletelist">
				<hr>
				<c:set var="sum" value="${sum + c.ob_price}"/>
		</c:forEach>
					<input type="submit" value="결제하기">
		총 금액 : <c:out value="${sum }"/> 원
	</c:otherwise>
	</c:choose>
	
</form>
-->
</body>
</html>