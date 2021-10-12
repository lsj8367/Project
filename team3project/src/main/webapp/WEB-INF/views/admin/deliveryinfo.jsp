<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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
<%@include file="admin_header.jsp"%>
<body>
	<form method="post" action="deliveryok">
	<table class="newbooktable" style="width: 90%; margin-left: auto; margin-right: auto;">
		<tr><th>주문번호</th><th>주문자</th><th>주문일자</th><th>주문상태</th></tr>
		<c:forEach var="o" items="${olist }">
			<tr>
				<td>
					${o.orderlistNo}
					<input type = "hidden" name="orderlist_no" value="${o.orderlistNo}">
				</td>
				<td>${o.orderPerson}</td>
				<td>${o.orderDate}</td>
				<td>
					<select name="order_state">
						<c:choose>
						<c:when test="${o.orderState eq '1'}">
							<option value="1" selected>결제완료
						</c:when>
						<c:otherwise>
							<option value="1">결제완료
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${o.orderState eq '2'}">
							<option value="2" selected>상품준비중
						</c:when>
						<c:otherwise>
							<option value="2">상품준비중
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${o.orderState eq '3'}">
							<option value="3" selected>배송준비중
						</c:when>
						<c:otherwise>
							<option value="3">배송준비중
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${o.orderState eq '4'}">
							<option value="4" selected>배송중
						</c:when>
						<c:otherwise>
							<option value="4">배송중
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${o.orderState eq '5'}">
							<option value="5" selected>배송완료
						</c:when>
						<c:otherwise>
							<option value="5">배송완료
						</c:otherwise>
						</c:choose>						
					</select>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan='8' style="text-align: center;">
				<button type="submit" class="btn btn-outline-secondary">완료</button>
			</td>
		</tr>
	</table>
	</form>
</body>
<%@include file="admin_footer.jsp"%>
</html>