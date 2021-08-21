<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 내역 조회</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

</head>
<%@ include file="header.jsp"%>
<body>
	<br>
	<br>
	<h2 align="center">1:1 문의</h2>
	
		<table border="1"
			style="width: 100%; float: none; margin: auto; text-align: center;">

			<tr>
<td>		
<c:choose>
				<c:when test="${f:length(inqinfo) eq 0 }">
			1:1 문의 내역이 없습니다. <br>
				</c:when>
					<c:otherwise>
		<table border="1" style="margin: auto;">
				<tr>
					<th>글번호</th>
					<th>아이디</th>
					<th>제목</th>
					<th>내용</th>
					<th>작성일</th>
				</tr>

				<c:forEach var="inq" items="${inqinfo }">

					<tr>
						<td>${inq.inq_no }</td>
						<td>${inq.inq_id }</td>
						<td>${inq.inq_title }</td>
						<td>${inq.inq_context }</td>
						<td>${inq.inq_ddate }</td>
					</tr>
				</c:forEach>
				</table>
			</c:otherwise>
</c:choose>


	<form method="get" action="sospage" id="frmsos" name="frmsos">
	<table style="width: 100%; float: none; margin: :auto; text-align: center;" >	
			<tr>
				<td colspan="4" align="center">
					<input type="submit" id="btnCall" value="문의하기"> 
					<input type="button" onclick="javascript:history.back()" id="btnCancel" value="돌아가기">
				</td>
			</tr>
		</table>
		</form>
	


</body>
	<%@ include file="footer.jsp"%>
</html>