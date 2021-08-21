<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보-관리자</title>
</head>
<body>
	<table style="width: 90%; margin-left: auto; margin-right: auto;">
		<c:forEach var="u" items="${ulist }">
			<tr>
				<td>${u.user_id }</td>
				<td>${u.user_name }</td>
				<td>${u.user_tel }</td>
				<td>${u.user_addr }</td>
				<td>${u.user_mail }</td>
				<td>${u.user_birth }</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>