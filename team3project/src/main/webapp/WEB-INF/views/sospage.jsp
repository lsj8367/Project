<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기</title>
<script type="text/javascript">
function check(){
 if(frm.inq_title.value ==""){
		alert("제목을 입력하세요");
		frm.inq_title.focus();
	}else if(frm.inq_context.value ==""){
		alert("내용을 입력하세요");
		frm.inq_context.focus();
	}else
		frm.submit();
}
</script>
</head>
<%@ include file="header.jsp" %>
<body>
<form action="sospage" name="frm" id="frm" method="post">

<table border="1" style="width: 40%; float: none; margin: auto; text-align: center;">
	<tr>
			<td colspan="2"><h2> 1:1 문의 하기</h2></td>
		</tr>
			
		<tr>
			<td align="center">제 목</td>
			<td><input name="inq_title" size="50" value=""></td>
		</tr>
		<tr>
			<td align="center">내 용</td>
			<td><textarea name="inq_context" cols="50" rows="30" placeholder="문의 받으실 내용을 작성해주세요. 상세하게 설명해주시면 원활한 상담을 받으실 수 있습니다. "></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center" height="30">
				<input type="submit" value="작  성" onClick="check()">&nbsp;
				<input type="button" value="목  록" onClick="location.href='sos'"></td>
		</tr>
</table>
</form>
</body>
</html>