<%@page import="kr.del.product.ProductDto"%>
<%@page import="kr.del.order.OrderDto"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- orderMgr에는 물건번호밖에없음 --%>
<jsp:useBean id="orderMgr" class="kr.del.order.OrderMgr"></jsp:useBean>
<jsp:useBean id="productMgr" class="kr.del.product.ProductMgr"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문정보</title>
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
<body style="margin-top: 20px;">
<%@ include file="top.jsp" %>

<table style="width: 100%" class="table">
	<tr><th>주문번호</th><th>상품명</th><th>주문 수</th><th>주문일</th><th>주문상태</th></tr>
	<%
	String id = (String)session.getAttribute("idKey");
	ArrayList<OrderDto> list = orderMgr.getOrder(id);
	if(list.size() == 0){
	%>
		<tr>
			<td colspan="5">주문한 상품이 없습니다.</td>
		</tr>
	<%
	}else{
		for(OrderDto ord:list){
			ProductDto product = productMgr.getProduct(ord.getProduct_no());
	%>
		<tr style="text-align: center;">
			<td><%=ord.getNo() %></td>
			<td><%=product.getName() %></td>
			<td><%=ord.getQuantity() %></td>
			<td><%=ord.getSdate() %></td>
			<td>
			<%
				switch(ord.getState()){
				case "1": out.println("접수"); break;
				case "2": out.println("입급확인"); break;
				case "3": out.println("배송준비"); break;
				case "4": out.println("배송중"); break;
				case "5": out.println("처리완료"); break;
				default:out.println("접수중");
				}
			
			%>
			</td>
		</tr>
	<%
		}
	}
	%>
</table>

<%@ include file="bottom.jsp" %>
</body>
</html>