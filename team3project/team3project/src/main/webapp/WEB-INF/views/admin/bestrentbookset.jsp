<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function monthview(){
	frm.action = "monthbestrent";
	frm.submit();
}

function plusPoint(){
	frm.action="givepoint2";
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

<form name="frm" method="post">
<table id="mytable" class="newbooktable" style="text-align: center; margin: auto; width:80%;"> 
   <tr>
   	<td colspan='8'>
   		<select name = "sql">
				<option value="" selected>전체보기
				<c:forEach var="rt" items="${rtm }">
					<option value=" and month(rent_sdate) like concat('%',${rt.rmonth },'%') ">${rt.rmonth }월
				</c:forEach>
			</select>
		<button type="button" class="btn btn-outline-secondary" onclick = "javascript:monthview()">보기</button>
	</td>
	</tr>
	<tr>
		<th>순위</th><th>대여월</th><th>책번호</th><th>책이름</th><th>기증자id</th><th>책상태</th><th>판매량</th>
 	</tr>
 	<c:forEach var="rtl" items="${rtl }" varStatus="status">
 	<tr>
 		<td>
 			${rtl.rn }
 		</td>
 		<td>${rtl.rmonth }월</td>
 		<td>${rtl.rent_no }</td>
 		<td>${rtl.ob_name }</td>
 		<td>${rtl.ob_userid }</td>
 		<td>
 			<c:if test="${rtl.ob_state eq '2'}">
 				상
 			</c:if>
 			<c:if test="${rtl.ob_state eq '3'}">
 				중
 			</c:if>	
 		</td>	
   		<td>${rtl.count }</td>  
 	</tr>
  	<jsp:useBean id="now" class="java.util.Date" />

	<fmt:formatDate value="${now}" pattern="MM" var="today" />  

	<c:if test="${today eq rtl.rmonth }">
		<input type="hidden" value = "${rtl.rn }" name="rn">
		<input type="hidden" value = "${rtl.ob_userid }" name="ob_userid">
		<p/>
	</c:if>
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