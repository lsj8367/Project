<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = (String)session.getAttribute("idKey");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/style_login.css">
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
<script type="text/javascript">
window.onload = function(){
	document.getElementById("btnLogin").addEventListener("click", funcLogin, false);
	//document.getElementById("btnNewMember").addEventListener("click", funcNew, false);
	document.getElementById("btnHome").addEventListener("click", funcHome, false);
	
	document.getElementById("btn1").addEventListener("click",funcReg,false);	
}

function funcLogin(){
	//alert("a");
	if(loginForm.id.value === ""){
		alert("아이디 입력");
		loginForm.id.focus();
	}else if(loginForm.passwd.value === ""){
		alert("비밀번호 입력");
		loginForm.passwd.focus();
	}else{
		loginForm.action = "loginPro.jsp";
		loginForm.method = "post";
		loginForm.submit();
	}
	
	
}
function funcHome(){
	location.href = "../guest/guest_index.jsp";
}

function funcReg(){
	location.href = "register.jsp";
}
</script>
</head>
<body>
<div class="login-card">
    <h1>로그인</h1><br>
  <form method="post" name="loginForm">
    <input type="text" name="id" placeholder="Username">
    <input type="password" name="passwd" placeholder="Password">
    <input type="button" id="btnLogin" class="login login-submit" value="login">
    <input type="button" id="btnHome" value="homepage" class="login login-submit">
    <input type="button" id="btn1" value="회원가입" class="login login-submit">
  </form>
  </div>
</body>
</html>