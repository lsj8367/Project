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
		<c:forEach var="rb" items="${rblist }">
			<tr>
				<td><img src="${rb.ob_image }" ></td>
			<td>${rb.ob_name}</td>
			<td>${rb.ob_author}</td>
			<td>${rb.ob_inter}</td>
			<td>
			${rb.ob_comp}<br>
			(${rb.ob_bdate})
			</td>
			<td>
				<c:choose>
				    <c:when test="${rb.ob_state eq '2'}">
				        상
				    </c:when>
				    <c:when test="${rb.ob_state eq '3'}">
				        중
				    </c:when>
			    </c:choose>
			</td>
			<td>${sob.ob_donor}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>