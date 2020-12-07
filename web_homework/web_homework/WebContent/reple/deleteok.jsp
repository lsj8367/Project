<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="boardMgr" class="kr.del.reply.ReplyMgr"></jsp:useBean>

<%
String spage = request.getParameter("page");
String num = request.getParameter("num");
String pass = request.getParameter("pass");

boolean b = boardMgr.checkPass(Integer.parseInt(num), pass); //여기서 글번호, 비밀번호 다 가져옴
if(b){
	boardMgr.delData(num); //글번호 같은걸 지움
	response.sendRedirect("replylist.jsp?page=" + spage); //글 삭제 후 목록보기
}else{
%>
	<script>
		alert("비밀번호 불일치");
		history.back();
	</script>
<% 
}
%>
