<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
session.removeAttribute("idKey");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
alert("정상적으로 로그아웃 되었습니다.");
//location.href="login.jsp";
location.href="../guest/guest_index.jsp";
</script>
</body>
</html>