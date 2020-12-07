<%@page import="kr.del.reply.ReplyDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int pageBlock, start, end; //페이지처리하려고 만드는변수
int pageSu, spage=1; //페이징처리 변수 1이 기본
%>

<jsp:useBean id="replyMgr" class="kr.del.reply.ReplyMgr"/>
<jsp:useBean id="dto" class="kr.del.reply.ReplyDto"/>
<jsp:useBean id="bean" class="kr.del.reply.ReplyBean"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배달 후기</title>
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
<link rel="stylesheet" type="text/css" href="../css/board.css"><!-- 수정 필요! -->
<link rel="stylesheet" type="text/css" href="../css/button.css">
<script type="text/javascript">
window.onload = function() {
	document.getElementById("btnSearch").onclick = function() {
		if(frm.sword.value === ""){
			frm.sword.focus();
			alert("검색어를 입력하세요");
			return;
		}
		frm.submit();
	}
}
</script>
</head>
<body>
<table style="width: 100%">
	<tr>
		<th style="text-align: center;">
			<a href="../home.jsp" class="myButton">메인화면</a>
			<a href="replylist.jsp?page=1" class="myButton">목록보기</a>
			<a href="replywrite.jsp" class="myButton">글쓰기</a>
			<!-- <a href="#" class="myButton" onclick="window.open('admin.jsp','','width=500,height=250,top=300,left=300')">관리자모드</a> -->
		</th>
	</tr>
</table>
<table style="width: 100%" class="table">
	<tr>
		<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>평점</th>
	</tr>
	<%
	request.setCharacterEncoding("utf-8");
	try{
		spage = Integer.parseInt(request.getParameter("page"));
	}catch(Exception e){
		spage = 1;
	}
	
	if(spage <= 0) spage = 1; //아무것도 안눌러도 1페이지 나오게 출력
	
	String stype = request.getParameter("stype");
	String sword = request.getParameter("sword");
	
	replyMgr.totalList();
	pageSu = replyMgr.getPageSu();
	
	ArrayList<ReplyDto> list = replyMgr.getDataAll(spage, stype, sword);
	
	
	for(int i = 0; i < list.size(); i++){
		dto = (ReplyDto)list.get(i);
		//들여쓰기 설정하기
		int read = dto.getNested();
		
		String space = "";
		for(int j=0; j < read; j++){
			space += "&nbsp;&nbsp;";
		}
		
		String a = "";
		if(dto.getStar().equals("1")){
			a = "★";
		}else if(dto.getStar().equals("2")){
			a = "★★";
		}else if(dto.getStar().equals("3")){
			a = "★★★";
		}else if(dto.getStar().equals("4")){
			a = "★★★★";
		}else if(dto.getStar().equals("5")){
			a = "★★★★★";
		}else{
			a = null;
		}
	%>
	<tr style="text-align: center;">
		<td><%= dto.getNum() %></td>
		<td>
		<%=space%><a href="replycontent.jsp?num=<%=dto.getNum()%>&page=<%=spage%>"><%= dto.getTitle() %></a>
		</td>
		<td><%= dto.getName() %></td>
		<td><%= dto.getBdate() %></td>
		<td><%= dto.getReadcnt() %></td>
		<td><%= a %></td>
	</tr>
	<%
	}
	%>
</table>
<table style="width: 100%">
	<tr style="text-align: center;">
		<td>
			<% 
			for(int i = 1; i <= pageSu; i++){ //일반적인 페이징 기법
				if(i == spage){
					out.print("<b style='font-size:12pt;color:red'>[" + i + "]</b>");
				}else{
					out.print("<a href=replylist.jsp?page=" + i + ">[" + i + "]</a>");					
				}
			}
			%>
			<br><br>
			<form action="replylist.jsp" name="frm" method="post">
				<select name="stype">
				<option value="title" selected="selected">글제목</option>
				<option value="name">작성자</option>
				</select>
				<input type="text" name="sword">
				<input type="button" value="검색" id="btnSearch">
			</form>
		</td>
	</tr>
</table>
</body>
</html>