<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
  .divtag{
  	text-align: center;
  	margin: auto;
  	width: 100%;
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
function plusPoint(){
	frm.action="givepoint3";
	frm.submit();
}
</script>
</head>
<%@include file="admin_header.jsp"%>
<body>
<div class="divtag"><h3>이달(${cm }월)의 대여왕</h3></div>
<form name="frm" method="post">
<table class = "newbooktable" id="mytable" style="text-align: center; width: 90%; margin:auto;"> 
	<tr>
		<th>순위</th><th>회원id</th><th>회원명</th><th>연락처</th><th>이메일</th><th>대여횟수</th>
 	</tr>
 	<c:forEach var="ru" items="${ru }" varStatus="status">
 	<tr>
 		<td>${ru.rn }</td>
 		<td>${ru.user_id }</td>
 		<td>${ru.user_name }</td>
 		<td>${ru.user_tel }</td>
 		<td>${ru.user_mail }</td>	
   		<td>${ru.count }</td>  
 	</tr>
 	<input type="hidden" name="user_id" value = "${ru.user_id }">
 	<input type="hidden" name = "rn" value = "${ru.rn }">
 	</c:forEach>
 	<tr>
 		<td colspan='7'>
			<button type="button" class="btn btn-outline-secondary" onclick="plusPoint()">포인트 지급</button>
		</td>
	</tr>	
</table> 
</form>
</body>
<%@include file="admin_footer.jsp"%>
</html>