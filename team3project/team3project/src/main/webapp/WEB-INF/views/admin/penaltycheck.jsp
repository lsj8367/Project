<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
    
<!DOCTYPE html>
<html>
<head>
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
    font-family:  'TmoneyRoundWindRegular';
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
<script type="text/javascript">
	function delUser(){
		document.frm.submit();
	}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<%@include file="admin_header.jsp"%>
<body>
<table class="newbooktable" style="margin:auto; width:90%; text-align: center;">
	<tr><th>회원아이디</th><th>회원명</th><th>연락처</th><th>메일</th><th>패널티적용상태</th></tr>
	<c:choose>
		<c:when test="${fn:length(dulist) eq 0}">
			<tr>
				<td colspan='4'>탈퇴시킬 회원이 없습니다.<td>
			</tr>
		</c:when>
		<c:otherwise>
			<form action="deluser" method = "post" name = "frm">
				<c:forEach var="du" items="${dulist }">
					<tr>
						<td>
							${du.user_id }
							<input type = "hidden" name = "user_id" value="${du.user_id }">
						</td>
						<td>${du.user_name }</td>
						<td>${du.user_tel }</td>
						<td>${du.user_mail }</td>
						<td>${du.user_penalty }</td>
					</tr>
				</c:forEach>
			</form>
			<tr>
				<td colspan='5'>
					<button type="button" class="btn btn-outline-secondary" onclick="javascript:delUser()">강제탈퇴</button>
				</td>
			</tr>
		</c:otherwise>
	</c:choose>
</table>
<hr>
<table class="newbooktable" style="margin:auto; width:90%; text-align: center;">
	<tr><th>회원아이디</th><th>회원명</th><th>연락처</th><th>메일</th><th>패널티적용상태</th></tr>
	<c:choose>
		<c:when test="${fn:length(ulist) eq 0}">
			<tr>
				<td colspan='5'><h3>회원이 존재하지 않습니다.</h3><td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="u" items="${ulist }">
				<tr>
					<td>
						${u.user_id }
						<input type = "hidden" name = "user_id" value="${u.user_id }">
					</td>
					<td>${u.user_name }</td>
					<td>${u.user_tel }</td>
					<td>${u.user_mail }</td>
					<td>${u.user_penalty }</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
</table>
		<br>	<div class="divtag" style="margin: auto; text-align: center; width: 100%"><button type="button" class="btn btn-outline-secondary" onclick = "location.href='admin'">메인으로 이동</button></div>
</body>
	<%@include file="admin_footer.jsp"%>
</html>