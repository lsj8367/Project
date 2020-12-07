<%@page import="kr.del.member.MemberDto"%>
<%@page import="kr.del.product.ProductDto"%>
<%@page import="kr.del.order.OrderDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="orderMgr" class="kr.del.order.OrderMgr"/>
<jsp:useBean id="productMgr" class="kr.del.product.ProductMgr"/>
<jsp:useBean id="memberMgr" class="kr.del.member.MemberMgr"></jsp:useBean>

<%
OrderDto order = orderMgr.getOrderDetail(request.getParameter("no"));
ProductDto product = productMgr.getProduct(order.getProduct_no());
MemberDto member = memberMgr.getMember(order.getId());

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문관리</title>
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
<%@ include file="admin_top.jsp" %>
<form action="orderproc_admin.jsp" name="detailFrm" method="post">
<table style="width: 100%" class="table">
	<tr>
		<td>고객 아이디 : <%=order.getId() %></td>
		<td>주 문 번 호 : <%=order.getNo() %></td>
		<td>음 식 번 호 : <%=order.getProduct_no() %></td>
		<td>음 식 명 : <%=product.getName() %></td>
	</tr>
	<tr>
		<td>상 품 가 격 : <%=product.getPrice() %></td>
		<td>주 문 수 량 : <%=order.getQuantity() %></td>
		<!-- <td>재 고 수 량 : product.getStock() %></td> -->
		<td>배 송 주 소 : <%=member.getAddress() %></td>
		<td>주 문 일 자 : <%=order.getSdate() %></td>
	</tr>
	<tr>
		<td colspan="4">
		고객 결제 금액 <%=Integer.parseInt(order.getQuantity()) * Integer.parseInt(product.getPrice())%>
		</td>
	</tr>
	<tr>
		<td colspan="4" style="text-align: center;">
		주문상태 : 
		<select name="state">
		<option value="1">접수</option>
		<option value="2">입금확인</option>
		<option value="3">배달준비</option>
		<option value="4">배달중</option>
		<option value="5">배달완료</option>
		</select>
		<script type="text/javascript">
		document.detailFrm.state.value = <%=order.getState() %>
		</script>
		</td>
	</tr>
	<tr>
		<td colspan="4" style="text-align: center;">
			<input type="button" value="수 정" onclick="orderUpdate(this.form)"> /
			<input type="button" value="삭 제" onclick="orderDelete(this.form)">
			<input type="hidden" name="no" value="<%=order.getNo()%>">
			<input type="hidden" name="flag">
		</td>
	</tr>
</table>
</form>
<%@ include file="bottom.html" %>
</body>
</html>