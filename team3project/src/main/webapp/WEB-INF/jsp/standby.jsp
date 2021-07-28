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
	<form method="post" action="standbyok">
	<table style="width: 90%; margin-left: auto; margin-right: auto;">
		<c:forEach var="sl" items="${slist }">
			<tr>
				<td>
					${sl.ob_no}
					<input type = "hidden" name="ob_no" value="${sl.ob_no}">
				</td>
				<td><img src="${sl.ob_image }" ></td>
				<td>${sl.ob_name}</td>
				<td>${sl.ob_author}</td>
				<td>${sl.ob_inter}</td>
				<td>
				${sl.ob_comp}<br>
				(${sl.ob_bdate})
				</td>
				<td>
					<select name="ob_state">
						<option value="0" selected>승인대기
						<option value="1">최상
						<option value="2">상
						<option value="3">중
						<option value="4">하
						<option value="5">최하
					</select>
				</td>
				<td>${sl.ob_donor}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan='8' style="text-align: center;"><input type="submit" value="완료"></td>
		</tr>
	</table>
	</form>
</body>
</html>