<%@page import="kr.del.product.ProductDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="kr.del.product.ProductMgr"></jsp:useBean>

<%
String no = request.getParameter("no");
ProductDto dto = productMgr.getProduct(no);


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 보기</title>
<style>
    .table {
      border-collapse: collapse;
      border-top: 3px solid #168;
    }  
    .table th {
      color: #168;
      background: #f0f6f9;
      text-align: center;
    }
    .table th, .table td {
      padding: 10px;
      border: 1px solid #ddd;
    }
    .table th:first-child, .table td:first-child {
      border-left: 0;
    }
    .table th:last-child, .table td:last-child {
      border-right: 0;
    }
    .table tr td:first-child{
      text-align: center;
    }
    .table caption{caption-side: bottom; display: none;}
</style>
<link type="text/css" rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body style="margin-top: 20px">
상품 상세 보기<br>
<%@ include file = "top.jsp" %>
<form action="cartproc.jsp">
<table style="width: 100%" class="table">
	<tr>
		<td style="width: 20%">
			<img src="../food/<%=dto.getImage() %>" width="150">
		</td>
		<td style="width: 50%; vertical-align: top;">
			<table>
				<tr><td>번호 : </td><td><%=dto.getNo() %></td></tr>
				<tr><td>상품명 : </td><td><%=dto.getName() %></td></tr>
				<tr><td>가격 : </td><td><%=dto.getPrice() %></td></tr>
				<tr><td>등록일 : </td><td><%=dto.getSdate() %></td></tr>
				<!-- <tr><td>재고량 : </td><td>//dto.getStock() %></td></tr>-->
				<tr>
					<td>
						<br><br><br>
						주문수 : 
						<input type="number" name="quantity" value="1" min="0" style="text-align: center;">
					</td>
				</tr>
			</table>
		</td>
		<td style="width: 20%; vertical-align: top;">
			<b>상품 설명</b><br>
			<%= dto.getDetail() %>
		</td>
	</tr>
	<tr>
		<td colspan="3" style="text-align: center;">
			<br>
			<input type="hidden" name="product_no" value="<%= dto.getNo()%>">
			<input type="submit" value="장바구니에 담기">
			<input type="button" value="이전화면으로" onclick="history.back()"> 
		</td>
	</tr>
</table>
</form>
<%@ include file = "bottom.jsp" %>
</body>
</html>