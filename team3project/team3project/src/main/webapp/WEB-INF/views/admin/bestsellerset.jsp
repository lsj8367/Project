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
	frm.action = "monthbestseller";
	frm.submit();
}

function addStock(){
	frm.action="addstock";
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
<table id="mytable" class="newbooktable" style="text-align: center; margin: auto; width: 90%"> 
   <tr>
   	<td colspan='8'>
   		<select name = "sql">
				<option value="" selected>전체보기
				<c:forEach var="o" items="${om }">
					<option value="and month(order_date) like concat('%',${o.smonth },'%')">${o.smonth }월
				</c:forEach>
			</select>
		<button type = "button" class="btn btn-outline-secondary" onclick = "javascript:monthview()">보기</button></td>
	</tr>
	<tr>
		<th>순위</th><th>판매월</th><th>책번호</th><th>책이름</th><th>판매량</th>
 	</tr>
 	<c:forEach var="ol" items="${ol }" varStatus="status">
 	<tr>
 		<td>
 			${ol.rn }
 		</td>
 		<td>${ol.smonth }월</td>
 		<td>${ol.nb_no }</td>
 		<td>${ol.nb_name }</td>
 		<td>${ol.scount }
   		</td>  
 	</tr>
  	<jsp:useBean id="now" class="java.util.Date" />

	<fmt:formatDate value="${now}" pattern="MM" var="today" />  

	<c:if test="${today eq ol.smonth }">
		<input type="hidden" value = "${ol.rn }" name="rn">
		<input type="hidden" value = "${ol.nb_no }" name="nb_no">
		<p/>
	</c:if>
 	</c:forEach>
 	<tr>
 		<td colspan='5'>
			<button type="button" class="btn btn-outline-secondary" onclick="addStock()">재고추가</button>
		</td>
	</tr>	
</table> 
</form>
</body>
<%@include file="admin_footer.jsp"%>
</html>