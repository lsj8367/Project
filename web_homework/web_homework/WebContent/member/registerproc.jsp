<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="memberBean" class="kr.del.member.MemberBean"/>
<jsp:setProperty property="*" name="memberBean"/>
<jsp:useBean id="memberMgr" class="kr.del.member.MemberMgr"/>

<%
boolean b = memberMgr.memberInsert(memberBean);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../css/button.css">
</head>
<body>
<%
if(b){
	out.println("<b>회원가입이 정상적으로 되었습니다.</b>");
	out.println("<a href='login.jsp' class='myButton'>로그인</a>");
}else{
	out.println("<b>회원가입 실패</b>");
	out.println("<a href='register.jsp' class='myButton'>가입 재시도</a>");
}
%>
</body>
</html>