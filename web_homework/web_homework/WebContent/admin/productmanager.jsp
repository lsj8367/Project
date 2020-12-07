<%@page import="kr.del.product.ProductDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="kr.del.product.ProductMgr"></jsp:useBean>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품관리</title>
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
<link type="text/css" rel="stylesheet" href="../css/button.css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body style="margin-top: 10px;">
상품 목록
<br>
<%@ include file="admin_top.jsp" %>
<table style="width: 100%" class="table">
	<tr style="background-color: yellow;">
		<th>번호</th><th>상품명</th><th>가격</th><th>등록일</th><th>재고량</th><th>상세보기</th>
	</tr>
	<%
	ArrayList<ProductDto> list = productMgr.getProductAll();
	if(list.size() == 0){
	%>
		<tr><td colspan="6">등록된 상품이 없습니다.</td></tr>
	<%	
	}else{
		for(ProductDto p:list){
	%>
	<tr style="text-align: center;">
		<td><%=p.getNo() %></td>
		<td><%=p.getName() %></td>
		<td><%=p.getPrice() %></td>
		<td><%=p.getSdate() %></td>
		<td><%=p.getStock() %></td>
		<td>
			<a href="javascript:productDetail('<%=p.getNo()%>')">보기</a>
		</td>
	</tr>
	<%	
		}
	}
	%>
	<tr><td colspan="6" style="text-align: left;">
		<a href="productinsert.jsp" class="myButton">상품 등록하기 </a>
	</td></tr>
</table>
<%@ include file="bottom.html" %>
<form action="productdetail.jsp" name="detailForm" method="post">
<input type="hidden" name="no">
</form>
</body>
</html>