<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/button.css">
<link rel="stylesheet" type="text/css" href="../css/board.css">
<script type="text/javascript">
function check() {
	if(frm.id.value === "" || frm.pwd.value === ""){
		alert("자료를 입력하세요");
		return;
	}
	frm.submit();
}
</script>
</head>
<body>
<form action="adminlogin.jsp" name="frm" method="post">
<table style="width: 100%">
	<tr>
		<td>
			<%
			String sessionValue = (String)session.getAttribute("adminOk");
			if(sessionValue != null){
			%>
				이미 로그인 중인 상태<br><br>
				<a href="adminlogout.jsp" class="myButton">로그아웃</a>
				<a href="javascript:window.close()" class="myButton">창닫기</a>
				
				
			<% }else{%>
				
				<table>
					<tr>
						<td>아 이 디 : <input type="text" name="id"></td>
					</tr>
					<tr>
						<td>비밀번호 : <input type="text" name="pwd"></td>
					</tr>
					<tr>
						<td>
							<a href="#" onclick="check()" class="myButton">로그인</a>
							<a href="javascript:window.close()" class="myButton">창닫기</a>
						</td>
					</tr>
				</table>						
			<%	
			}
			%>
		</td>
	</tr>
</table>
</form>
</body>
</html>