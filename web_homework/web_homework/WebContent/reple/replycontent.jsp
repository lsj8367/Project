<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="replyMgr" class="kr.del.reply.ReplyMgr"/>
<jsp:useBean id="dto" class="kr.del.reply.ReplyDto"/>

<%
String num = request.getParameter("num");
String spage = request.getParameter("page");

replyMgr.updateReadcnt(num);
dto = replyMgr.getData(num);

String name = dto.getName();
String pass = dto.getPass();
String mail = dto.getMail();
String title = dto.getTitle();
String cont = dto.getCont();
String bip = dto.getBip();
String bdate = dto.getBdate();
int count = dto.getReadcnt();
String star = dto.getStar();

String apass = "*****"; //일반인은 비밀번호를 안보여줌
String adminOk = (String)session.getAttribute("adminOk"); //관리자용 세션
if(adminOk != null){
	if(adminOk.equalsIgnoreCase("admin")) apass = pass;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰</title>
<link rel="stylesheet" type="text/css" href="../css/button.css">
</head>
<body>
<table class="table">
	<tr>
		<td colspan="3">제목 : <%=title %></td>
	</tr>
	<tr>
		<td>작성일 : <%=bdate %></td>
		<td>조회수 : <%=count %></td>
	</tr>
	<tr>
		<td colspan="3">
			<textarea rows="10" style="width: 99%" readonly="readonly"><%=cont %></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="1" style="text-align: right;">
		<a class="myButton" href="reple.jsp?num=<%=num %>&page=<%=spage %>">댓글쓰기</a>
		<a class="myButton" href="edit.jsp?num=<%=num %>&page=<%=spage %>">수정</a>
		<a class="myButton" href="delete.jsp?num=<%=num %>&page=<%=spage %>">삭제</a>
		<a class="myButton" href="replylist.jsp?page=<%=spage%>">목록보기</a>
		</td>
	</tr>
</table>
</body>
</html>