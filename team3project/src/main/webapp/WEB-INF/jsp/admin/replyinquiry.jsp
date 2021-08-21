<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 댓글</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />">
<script type="text/javascript">
function check(){
	if(frm.inq_context.value==""){
		alert("댓글을 입력하세요");
		frm.inq_context.focus();
	}else
		frm.submit();
}
</script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<style type="text/css">
@font-face {
    font-family: 'TmoneyRoundWindRegular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindRegular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

body {
  padding-top: 200px;
  font-family: 'TmoneyRoundWindRegular';
}

//테이블 적용
.newbooktable {
  font-family: 'TmoneyRoundWindRegular';
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin-left:auto;
  margin-right:auto;
  float : none;
  padding: 0;
  width: 50%;
  table-layout: fixed;
  text-align: center;
}

.newbooktable caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

.newbooktable tr {
  background-color: #fdfdfd;
  border: 1px solid #ddd;
  padding: .35em;
}

.newbooktable th,
.newbooktable td {
  padding: .625em;
  text-align: center;
  font-family: 'TmoneyRoundWindRegular';
}

.newbooktable td input, .newbooktable td textarea, .newbooktable td select{
	width: 100%; border: 0; font-family: 'TmoneyRoundWindRegular'; text-align: center;
}

.newbooktable th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
  background:gray;
  color: white;
  font-family: 'TmoneyRoundWindRegular';
}

@media screen and (max-width: 600px) {
  .newbooktable {
    border: 0;
  }

  .newbooktable caption {
    font-size: 1.3em;
  }
  
  .newbooktable th {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
  }
  
  .newbooktable tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
    text-align: center;
  }
  
  .newbooktable td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: center;
  }
  
  .newbooktable td::before {
    /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  .newbooktable td:last-child {
    border-bottom: 0;
  }
  
}
</style>
</head>
	<%@include file="admin_header.jsp"%>
<body>
<form action="replyinq" name="frm" method="post">
<input type="hidden" name="inq_no" value = "${m.max + 1}">
<input type="hidden" name="inq_gnum" value = "${data.inq_gnum }">
<input type="hidden" name="inq_onum" value = "${data.inq_onum + 1 }">
<input type="hidden" name="inq_nested" value = "${data.inq_nested + 1}">
	<table class="newbooktable" style="width: 70%; margin-left: auto; margin-right: auto;">
		<tr>
			<th colspan="2">1:1문의 댓글</th>
		</tr>
		<tr>
			<td align="center" width="100">작성자id</td>
			<td width="430">${data.inq_id }</td>
		</tr>
		<tr>
			<td align="center">댓글작성자</td>
			<td>
				${info.admin_name }(${info.admin_id })
				<input type="hidden" value="${info.admin_name }(${info.admin_id })" name="inq_id">
			</td>
		</tr>
		<tr>
			<td align="center">제 목</td>
			<td><input name="inq_title" size="50" value = "[Re]:${data.inq_title }" readonly></td>
		</tr>
		<tr>
			<td align="center">내 용</td>
			<td><textarea name="inq_context" rows="10" style="width: 100%"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center" height="30">
				<button type="button" class="btn btn-outline-secondary" onClick="check()">작성</button>&nbsp;
				<button type="button" class="btn btn-outline-secondary" onClick="location.href='userinquiry'">목록</button>
			</td>
		</tr>
	</table>
</form>

</body>
	<%@include file="admin_footer.jsp"%>
</html>