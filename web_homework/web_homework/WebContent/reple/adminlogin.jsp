<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/button.css">
</head>
<body>
<%
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

if(id.equalsIgnoreCase("admin") && pwd.equals("111")){
	session.setAttribute("adminOk", id);
	out.println("로그인 성공<br>");
}else{
	out.println("로그인 실패<br>");
}
%>
<br>

<a href="javascript:window.close()" class="myButton">창닫기</a>

</body>
</html>