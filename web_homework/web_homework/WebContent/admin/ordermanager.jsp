<%@page import="kr.del.product.ProductDto"%>
<%@page import="kr.del.order.OrderDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="orderMgr" class="kr.del.order.OrderMgr"></jsp:useBean>
<jsp:useBean id="productMgr" class="kr.del.product.ProductMgr"></jsp:useBean>
<%--주문한 상품의 이름이나 다른 정보까지 보기위해 productMgr 불러옴  --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문관리 : 관리자</title>
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
<body style="margin-top: 20px">
<%@ include file="admin_top.jsp" %>
<table style="width: 100%" class="table">
	<tr style="background-color: aqua; color: white;">
		<th>주문번호</th><th>주문인</th><th>상품명</th><th>주문량</th><th>주문일</th><th>주문상태</th><th>보기</th>
	</tr>
	<%
	ArrayList<OrderDto> list = orderMgr.getOrderAll();
	if(list.size() == 0){
	%>
		<tr><td colspan="7">주문 내역이 없습니다.</td></tr>
	<%
	}else{
		for(int i = 0; i < list.size(); i++){
			OrderDto order = list.get(i);
			ProductDto product = productMgr.getProduct(order.getProduct_no());
			//order의 product_no와 상품명이 같은것 출력
	%>
		<tr style="text-align: center;">
			<td><%=order.getNo() %></td>
			<td><%=order.getId() %></td>
			<td><%=product.getName() %></td>
			<td><%=order.getQuantity() %></td>
			<td><%=order.getSdate() %></td>
			<td>
			<%
				switch(order.getState()){
				case "1": out.println("접수"); break;
				case "2": out.println("입급확인"); break;
				case "3": out.println("배달준비"); break;
				case "4": out.println("배달중"); break;
				case "5": out.println("배달완료"); break;
				default:out.println("접수중");
				}
			%>
			</td>
			<td>
				<a class="myButton" href="javascript:orderDetail('<%=order.getNo() %>')">상세보기</a>
			</td>
		</tr>
	<%
		}
	}
	%>
</table>
<%@ include file="bottom.html" %>
<form action="orderdetail.jsp" name="detailFrm" method="post">
	<input type="hidden" name="no">
	
</form>
</body>
</html>