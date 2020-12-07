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
session.removeAttribute("adminOk");
%>
로그아웃 되었습니다.
<br><br>
<a href="javascript:window.close()" class="myButton">창 닫기</a>

</body>
</html>