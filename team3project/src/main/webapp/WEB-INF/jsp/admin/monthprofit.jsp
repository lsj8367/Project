<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
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
</head>
<%@include file="admin_header.jsp"%>
<body>
<div class="divtag"><h3>월별 매출 통계</h3></div>
<div class="divtag"><h4>순수익</h4></div>
<form action="chartprint" method = "post">
<table class="newbooktable" style="text-align: center; width: 90%; margin:auto;"> 
 <tr><th>월</th><th>책주문량</th><th>주문건수</th><th>수익</th></tr>
 <c:set var="profit" value="0"/>
  <c:forEach var="rp" items="${rp }">
	<tr>
		<td>
			${rp.order_month }
			<input type="hidden" name="cmonth" value="${rp.order_month }">
		</td>
		<td>${rp.month_scount}</td>
		<td>${rp.order_count }</td>
		<td>${rp.order_profit }원<input type="hidden" name="rprofit" value="${rp.order_profit }"></td>
	</tr>
	<c:set var="profit" value="${profit + rp.order_profit }" />
</c:forEach>
	<tr><td colspan='4'><c:out value="${profit }" />원</td></tr>
    </table> 
<hr>
<br>    
<div class="divtag"><h4>판매금액</h4></div>
<table class="newbooktable" style="text-align: center; width: 90%; margin:auto;"> 
 <tr><th>월</th><th>책분류</th><th>책주문량</th><th>주문건수</th><th>수익</th></tr>
 <c:set var="nsum" value="0"/>
 <c:set var="osum" value="0"/>
 <c:set var="total" value="0"/>
  <c:forEach var="np" items="${np }">
	<tr>
		<td>${np.order_month }</td>
		<td>새책</td>
		<td>${np.month_scount}</td>
		<td>${np.order_count }</td>
		<td>${np.order_profit }원<input type="hidden" name="nbprofit" value="${np.order_profit }"></td>
	</tr>
	<c:set var="nsum" value="${nsum + np.order_profit }" />
	<c:set var="total" value="${total + np.order_profit }" />
</c:forEach>
	<tr><td colspan='5'><c:out value="${nsum }" />원</td></tr>
   <c:forEach var="op" items="${op }" varStatus="status">
	<tr>
		<td>${op.order_month }</td>
		<td>중고책</td>
		<td>${op.month_scount}</td>
		<td>${op.order_count }</td>
		<td>${op.order_profit }원<input type="hidden" name="obprofit" value="${op.order_profit }"></td>
	</tr>
	<c:set var="osum" value="${osum + op.order_profit }" />
	<c:set var="total" value="${total + op.order_profit }" />
</c:forEach>
	<tr><td colspan='5'><c:out value="${osum }" />원</td></tr>
	<tr><td colspan='5'><c:out value="${total }" />원</td></tr>
    </table>
    <br><br>
    
 </form> 
    <!-- Markup -->
<div id="additionalYAxis"></div>
</body>
<%@include file="admin_footer.jsp"%>
</html>