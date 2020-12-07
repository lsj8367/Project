<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .table {
      border-collapse: collapse;
      border-top: 3px solid #168;
    }  
    .table th {
      color: #168;
      background: #f0f6f9;
      text-align: center;
    }
    .table th, .table td {
      padding: 10px;
      border: 1px solid #ddd;
    }
    .table th:first-child, .table td:first-child {
      border-left: 0;
    }
    .table th:last-child, .table td:last-child {
      border-right: 0;
    }
    .table tr td:first-child{
      text-align: center;
    }
    .table caption{caption-side: bottom; display: none;}
</style>
</head>
<body style="margin-top: 20px;">
<%@ include file = "top.jsp" %>
<table style="width: 100%" class="table">
<%if(memid != null){ %>
	<tr>
		<td>
			<%=memid %>님 오늘도 맛있는 하루!
			<img title="hi" src="../image/pic1.png">
		</td>
	</tr>
<%}else{ %>
	<tr>
		<td style="background-image: url(../image/screen.png); background-size: 100%">
			<br>고객님 어서오세요.
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>로그인 후 이용가능합니다
		</td>
	</tr>	
<%} %>	
</table>
<%@ include file = "bottom.jsp" %>
</body>
</html>