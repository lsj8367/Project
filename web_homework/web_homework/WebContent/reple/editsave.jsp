<%@page import="kr.del.reply.ReplyMgr"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="bean" class="kr.del.reply.ReplyBean"></jsp:useBean>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="replyMgr" class="kr.del.reply.ReplyMgr"></jsp:useBean>
<%
String spage = request.getParameter("page");

boolean b = replyMgr.checkPass(bean.getNum(), bean.getPass());
if(b){
	replyMgr.saveEdit(bean);
	response.sendRedirect("replylist.jsp?page=" + spage);
}else{
%>
	<script>
		alert("비밀번호 일치하지 않음.")
		history.back();
	</script>
<%
}
%>

