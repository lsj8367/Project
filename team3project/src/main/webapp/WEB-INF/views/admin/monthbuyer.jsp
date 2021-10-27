<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function plusPoint(){
	frm.action="givepoint4";
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
<div class="divtag" style="text-align: center; margin: auto; width: 100%; font-family: 'TmoneyRoundWindRegular';"><h4>이달(${cm }월)의 구매왕</h4></div>
<form name="frm" method="post">
<table id="mytable" style="text-align: center; width: 90%; margin:auto;" class="newbooktable"> 
	<tr>
		<th>순위</th><th>id</th><th>주문자명</th><th>연락처</th><th>이메일</th><th>구매횟수</th><th>구매도서수</th>
 	</tr>
 	<c:forEach var="bu" items="${bu }" varStatus="status">
 	<tr>
 		<td>
 			${bu.rn }
 			<input type="hidden" value="${bu.rn }" name="rn">
 		</td>
 		<td>
 		<c:choose>
 			<c:when test="${empty bu.orderId}">
 				비회원
 			</c:when>
 			<c:otherwise>
 				${bu.orderId }
 				<input type="hidden" value="${bu.orderId }" name="user_id">
 			</c:otherwise>
 		</c:choose>
 		</td>
 		<td>${bu.orderPerson }</td>
 	</tr>
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