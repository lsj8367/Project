<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String adminId = (String)session.getAttribute("adminKey");
if(adminId == null){
	response.sendRedirect("adminlogin.jsp");
}
%>
<link type="text/css" rel="stylesheet" href="../css/button.css">
<table style="width: 100%" class="table">
	<tr style="background-color: aqua; text-align: center;">
		<td><a href="../home.jsp" class="myButton">홈페이지</a></td>
		<td><a href="adminlogout.jsp" class="myButton">로그아웃</a></td>
		<td><a href="membermanager.jsp" class="myButton">회원관리</a></td>
		<td><a href="productmanager.jsp" class="myButton">상품관리</a></td>
		<td><a href="ordermanager.jsp" class="myButton">주문관리</a></td>
	</tr>
</table>