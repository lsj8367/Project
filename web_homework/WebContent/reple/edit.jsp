<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="replyMgr" class="kr.del.reply.ReplyMgr"></jsp:useBean>
<jsp:useBean id="dto" class="kr.del.reply.ReplyDto"></jsp:useBean>


<%
String num = request.getParameter("num");
String spage = request.getParameter("page");
dto = replyMgr.getData(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 게시판</title>
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
<link rel="stylesheet" type="text/css" href="../css/button.css">
<script type="text/javascript">
function check() {
	if(frm.pass.value == ""){
		frm.pass.focus();
		alert("비밀번호 입력");
		return;
	}
	
	if(confirm("정말 수정할까요?")){
		frm.submit(); //yes누르면 editsave.jsp로 넘어감
	}
}
</script>
</head>
<body>
<form action="editsave.jsp" name="frm" method="post">
<input type="hidden" name="num" value="<%=num %>">
<input type="hidden" name="page" value="<%=spage %>">
<h2 style="text-align: center; color: blue;">리뷰 수정하기</h2>
<table class="table" style="width: 100%">
	<tr><th>정보</th><th>내용</th></tr>
	<tr>
		<td>이름</td>
		<td>
			<input type="text" name="name" value="<%=dto.getName() %>">
		</td>
	</tr>
	<tr>
		<td>암호</td>
		<td><input type="text" name="pass"></td>
	</tr>
	<tr>
		<td>메일</td>
		<td>
			<input type="text" name="mail" value="<%=dto.getMail() %>">
		</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>
			<input type="text" name="title" value="<%=dto.getTitle() %>">
		</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			<textarea rows="10" style="width: 97%" name="cont"><%=dto.getCont() %></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
			<input type="button" value="수정완료" onclick="check()">&nbsp;
			<input type="button" value="목록보기" onclick="location.href='replylist.jsp?page=<%=spage%>'">
		</td>
	</tr>
</table>
</form>
</body>
</html>