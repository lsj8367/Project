<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="bean" class="kr.del.reply.ReplyBean"/>
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="replyMgr" class="kr.del.reply.ReplyMgr"></jsp:useBean>

<%
String spage = request.getParameter("page");

int num = bean.getNum();
int gnum = bean.getGnum();
int onum = bean.getOnum() + 1;
int nested = bean.getNested() + 1;
String star = bean.getStar();
replyMgr.updateOnum(gnum, onum);


bean.setOnum(onum);
bean.setNested(nested);
bean.setBdate();
bean.setNum(replyMgr.currentGetNum() + 1);
bean.setStar(star);

replyMgr.saveReplyData(bean);
response.sendRedirect("replylist.jsp?page=" + spage);
%>

