<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>Insert title here</title>
</head>
<body>
<%@include file="admin_header.jsp"%>
<div class="divtag"><h4>신규책 주문 정보</h4></div>
<hr>
<table class="newbooktable" style="text-align: center; margin: auto; width: 90%;">
	<tr>
		<th>주문번호</th>
		<th>주문자</th>
       	<th>주문자id</th>
       	<th>주문비밀번호</th>
		<th>주문책이름</th>
		<th>주문수량</th>
		<th>주문일자</th>
		<th>주문상태</th>
	</tr>
	<c:forEach var="nblist" items="${nborderlist }">
		<tr>
			<td>${nblist.orderlist_no }</td>
			<td>${nblist.order_person }</td>
	        <td>${nblist.order_id }</td>
	        <td>${nblist.order_passwd }</td>
			<td>${nblist.order_bookname }</td>
			<td>${nblist.order_scount }</td>
			<td>${nblist.order_date }</td>
			<td>${nblist.order_state }</td>
		</tr>
	</c:forEach>
</table>
<br><br>
<div class="divtag"><h4>중고책 주문 정보</h4></div>
<hr>
<table class="newbooktable" style="text-align: center; margin: auto; width: 90%;">
	<tr>
		<th>주문번호</th>
		<th>주문자</th>
        <th>주문자id</th>
        <th>주문비밀번호</th>
		<th>주문책이름</th>
		<th>주문수량</th>
		<th>주문일자</th>
		<th>주문상태</th>
	</tr>
	<c:forEach var="oblist" items="${oborderlist }">
		<tr>
			<td>${oblist.orderlist_no }</td>
			<td>${oblist.order_person }</td>
	        <td>${oblist.order_id }</td>
	        <td>${oblist.order_passwd }</td>
			<td>${oblist.order_bookname }</td>
			<td>${oblist.order_scount }</td>
			<td>${oblist.order_date }</td>
			<td>${oblist.order_state }</td>
		</tr>
	</c:forEach>
</table>
<%@include file="admin_footer.jsp"%>
</body>
</html>