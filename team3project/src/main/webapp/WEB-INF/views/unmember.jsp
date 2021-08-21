<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<% String order_passwd = (String)request.getAttribute("order_passwd"); %>
<html>
<head>
<meta charset="UTF-8">
<title>비회원 주문조회</title>
<link rel="stylesheet" href="resources/static/css/layout.css">
<link rel="stylesheet" href="resources/static/css/table.css">
</head>
<body>
<div id="container">
     <div id="content">
     	 <table class="newbooktable" style="width: 70%;">
     	 	<tr>
     	 		<th colspan="2">주문조회 결과</th>
     	 	</tr>
     	 	<c:choose>
			<c:when test="${list.order_passwd eq order_passwd}">
			<tr>
				<td>주문번호</td>
				<td>${list.orderlist_no}</td>
			</tr>
			<tr>	
				<td>고객명</td>
				<td>${list.order_person} 님 </td>
			</tr>  
				<c:choose>
					<c:when test=" ${list.order_booktype eq 1}">
						<td>책유형</td>
						<td>새책 </td>
					</c:when>
								
					<c:otherwise>
						<td>책유형</td>
						<td>중고책</td>
					</c:otherwise>
				</c:choose>
				<tr>
					<fmt:parseDate var="dateString" value="${list.order_date }" pattern="yyyy-MM-dd HH:mm:ss.S" />	
    				 <td>주문일 </td> 
    				 <td><fmt:formatDate value="${dateString}" pattern="yyyy년 MM월 dd일" /></td>
    			</tr>
    			<tr>
					<td>결제유형</td>
					<td>무통장 결제</td>
				</tr>
				<tr>	
					<td>금액</td>
					<td>${list.order_sum }</td>
				</tr>
				<tr>
					<td colspan="2"><input class="btn btn-primary btn-lg" type="submit" value="돌아가기" onclick="history.back()"></td>
				</tr>
				</c:when>
				
				<c:otherwise>
				<tr>
					<td>비밀번호가 일치하지 않습니다.</td>
					<td><input type="submit" class="btn btn-primary btn-lg" value="돌아가기" onclick="history.back()"></td>
				</tr>
				</c:otherwise>
			</c:choose>
			</table>
     	 </div>

      	<div id="footer">
      		<%@ include file="footer.jsp" %>
   	   </div>
    </div>

</body>
</html>