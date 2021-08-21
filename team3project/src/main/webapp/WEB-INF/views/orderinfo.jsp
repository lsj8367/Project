<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
신규책 주문 정보
<hr>
<table>
	<tr>
		<th>주문번호</th>
		<th>주문자</th>
       	<th>주문자id</th>
       	<th>주문비밀번호</th>
		<th>주문책이름</th>
		<th>주문수량</th>
		<th>주문일자</th>
		<th>주문상태</th>
	</tr>
	<c:forEach var="nblist" items="${nborderlist }">
		<tr>
			<td>${nblist.orderlist_no }</td>
			<td>${nblist.order_person }</td>
	        <td>${nblist.order_id }</td>
	        <td>${nblist.order_passwd }</td>
			<td>${nblist.order_bookname }</td>
			<td>${nblist.order_scount }</td>
			<td>${nblist.order_date }</td>
			<td>${nblist.order_state }</td>
		</tr>
	</c:forEach>
</table>
<br><br>
중고책 주문 정보
<hr>
<table>
	<tr>
		<th>주문번호</th>
		<th>주문자</th>
        <th>주문자id</th>
        <th>주문비밀번호</th>
		<th>주문책이름</th>
		<th>주문수량</th>
		<th>주문일자</th>
		<th>주문상태</th>
	</tr>
	<c:forEach var="oblist" items="${oborderlist }">
		<tr>
			<td>${oblist.orderlist_no }</td>
			<td>${oblist.order_person }</td>
	        <td>${oblist.order_id }</td>
	        <td>${oblist.order_passwd }</td>
			<td>${oblist.order_bookname }</td>
			<td>${oblist.order_scount }</td>
			<td>${oblist.order_date }</td>
			<td>${oblist.order_state }</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>