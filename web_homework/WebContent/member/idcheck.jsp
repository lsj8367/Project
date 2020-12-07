<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="memberMgr" class="kr.del.member.MemberMgr" scope="page"/>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");

boolean b = memberMgr.checkId(id); //id 중복확인 메소드


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>id중복</title>
<link href="../css/button.css" rel="stylesheet" type="text/css">
<script src="../js/script.js"></script>
</head>
<body style="text-align: center; margin-top: 30px;">
<b><%= id %> : </b>
<%
if(b){
%>
	<b>이미 사용 중인 id입니다.</b><p/>
	<a href="#" class="myButton" onclick="opener.document.regForm.id.focus(); window.close()">닫기</a>
<% }else{%>
	사용 가능합니다.<p/>
	<a href="#" class="myButton" onclick="opener.document.regForm.passwd.focus(); window.close()">닫기</a>	
<%
}
%>
</body>
</html>