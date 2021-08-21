<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table style="width: 90%; margin-left: auto; margin-right: auto;">
		<c:forEach var="sob" items="${soblist }">
			<tr>
				<td><img src="./resources/static/${sob.ob_image }" ></td>
			<td>${sob.ob_name}</td>
			<td>${sob.ob_author}</td>
			<td>${sob.ob_inter}</td>
			<td>
			${sob.ob_comp}<br>
			(${sob.ob_bdate})
			</td>
			<td>${sob.ob_state}</td>
			<td>${sob.ob_donor}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>