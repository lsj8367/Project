<%@page import="kr.del.product.ProductDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="kr.del.product.ProductMgr"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품목록</title>
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
<link href="../css/button.css" rel="stylesheet" type="text/css">
<script src="../js/script.js"></script>
</head>
<body>
<%@ include file="top.jsp" %>
<table class="table" style="width: 100%">
	<tr>
		<th>음식</th><th>가격</th><th>상세보기</th>	
	</tr>
<%
	ArrayList<ProductDto> list = productMgr.getProductAll();
	
	for(ProductDto p:list){
%>		
	<tr	style="text-align: center;">
		<td>
			<img src="../food/<%=p.getImage()%>" width="80"/>
		</td>
		<td>
			<%=p.getPrice() %>
		</td>
		<td>
			<%=p.getName() %>
			<a class="myButton" href="javascript:productDetail_guest('<%= p.getNo() %>')">더 보기</a>
		</td>
	</tr>

	<%	
	}
	%>
</table>

<%@ include file="bottom.jsp" %>

<form action="productdetail_g.jsp" name="detailFrm" method="post">
	<input type="hidden" name="no">
</form>
</body>
</html>