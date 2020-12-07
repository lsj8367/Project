<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String memid = (String)session.getAttribute("idKey");
String log = "";

if(memid == null){
	log = "<a href='../member/login.jsp' class='myButton'>로그인</a>";
}else{
	log = "<a href='../member/logout.jsp' class='myButton'>로그아웃</a>";
}
String mem="";
if(memid == null){
	mem = "<a href='../member/register.jsp' class='myButton'>회원가입</a>";
}else{
	mem = "<a href='../member/memberupdate.jsp' class='myButton'>회원수정</a>";	
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/button.css">
<body>
	<a href="../home.jsp"><img style="text-align: center;" src="../image/main.jpg" width="40%" height="20%"/></a>◀◀◀◀◀홈으로 돌아가기
<table style="width: 100%; height: 50%" class="table">
	<tr style="text-align: center;">
		<td><%= log %></td>
		<td><%= mem %></td>
		<td><a href="productlist.jsp" class="myButton">음식목록</a></td>
		<td><a href="cartlist.jsp" class="myButton">주문목록</a></td>
		<td><a href="orderlist.jsp" class="myButton">배달목록</a></td>
		<td><a href="../reple/replylist.jsp" class="myButton">리뷰보기</a></td>
	</tr>
</table>
</body>
</html>