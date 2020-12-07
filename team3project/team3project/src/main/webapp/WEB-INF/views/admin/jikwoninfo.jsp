<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

.newbooktable td input, .newbooktable td textarea {
	width: 100%; border: 0; font-family: 'TmoneyRoundWindRegular'; text-align: center;
}

.newbooktable td select{
	border: 0; font-family: 'TmoneyRoundWindRegular'; text-align: center;
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
</head>
<%@include file="admin_header.jsp"%>
<body>
<form action="upadminjik" method="post">
<table class="newbooktable" style="text-align: center; width: 80%; margin:auto;"> 
 <tr><th>id</th><th>이름</th><th>직급</th><th>승진</th></tr>
  <c:forEach var="al" items="${al }">
	<tr>
		<td>
			${al.admin_id }
			<input type = "hidden" name = "admin_id" value="${al.admin_id }">		
		</td>
		<td>${al.admin_name}</td>
		<td>${al.admin_jik }</td>
		<td>
			<select name = "admin_jik">
			<c:choose>
				<c:when test="${al.admin_jik eq '사원' }">
					<option value="사원" selected>사원
					<option value="대리">대리
				</c:when>
				<c:when test="${al.admin_jik eq '대리' }">
					<option value="대리" selected>대리
					<option value="과장">과장
				</c:when>
				<c:when test="${al.admin_jik eq '과장' }">
					<option value="과장" selected>과장
					<option value="차장">차장
				</c:when>
				<c:when test="${al.admin_jik eq '차장' }">
					<option value="차장" selected>차장
					<option value="부장">부장
				</c:when>
				<c:when test="${al.admin_jik eq '부장' }">
					<option value="부장" selected>부장
					<option value="이사">이사
				</c:when>
				<c:when test="${al.admin_jik eq '이사' }">
					<option value="이사" selected>이사
				</c:when>
				<c:when test="${al.admin_jik eq '사장' }">
					<option value="사장" selected>사장
				</c:when>
			</c:choose>
			</select>
		</td>
	</tr>
</c:forEach>
	<tr><td colspan='4'>
		<button type="submit" class="btn btn-outline-secondary">확인</button>
	</td></tr>
</table>
</form>
</body>
<%@include file="admin_footer.jsp"%>
</html>