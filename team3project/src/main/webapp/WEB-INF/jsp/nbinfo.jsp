<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새책정보조회</title>
</head>
<body>
<table style="margin-left: auto; margin-right: auto; width: 90%">
	<c:forEach var="nb" items="${nblist}">
		<tr>
			<td><img src="${nb.nb_image }" ></td>
			<td>${nb.nb_no}</td>
			<td>${nb.nb_name}</td>
			<td>
				${nb.nb_author}<br>
				<c:if test="${nb.nb_inter != null}">
					${nb.nb_inter}
				</c:if>
			</td>
			<td>
			${nb.nb_comp}<br>
			(${nb.nb_bdate})
			</td>
			<td>${nb.nb_stock}</td>
			<td>${nb.nb_scount}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>