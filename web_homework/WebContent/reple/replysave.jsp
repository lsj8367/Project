<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="replyMgr" class="kr.del.reply.ReplyMgr"/>
<jsp:useBean id="bean" class="kr.del.reply.ReplyBean"/>
<jsp:setProperty property="*" name="bean"/>

<%
bean.setBip(request.getRemoteAddr());
bean.setBdate();
int maxNum = replyMgr.currentGetNum() + 1;
bean.setNum(maxNum);
bean.setGnum(maxNum);
String star = request.getParameter("star");
//out.println(star);
bean.setStar(star);
replyMgr.saveData(bean);

response.sendRedirect("replylist.jsp?page=1"); //저장 후 목록보기
%>