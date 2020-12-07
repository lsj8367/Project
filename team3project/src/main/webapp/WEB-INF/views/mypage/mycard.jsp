<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@include file="../header.jsp"%>
<body>
구현 의문점 : 굳이 다른 페이지로 가야하는가? 카드인데?
<br><br>
<table border="1" style="width: 100%; float: none; margin: auto; text-align: center;">
	<!-- 1tr -->
	<tr>
		<td style="width: 20%;" rowspan="5">
				Quick Menu
		</td>
		
		<td style="width: 60%;"  rowspan="5">
			전체 등록 카드 내역
			<br><br>
			<table border="1" style="margin: auto;">
				<tr>
					<th>카드번호</th><th>카드사</th>
				</tr>
			
				<c:forEach var="card" items="${cdinfo}">
					<tr>
						<td>${card.card_no}</td>
						<td>${card.card_comp}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
		
		<td style="width: 20%;" rowspan="1">
			${sessionScope.name}님 환영합니다.<br>보유포인트는 ${sessionScope.point}point
		</td>
	</tr>
	
	<tr>
		<td rowspan="2">
		이책은 어떠세요?<br><br>
		* 주의사항<br>자기가 기부했던 도서 제외<br>자기가 구매했던 도서 제외<br>
		</td>
	</tr>
</table>
</body>
<%@include file="../footer.jsp"%>
</html>