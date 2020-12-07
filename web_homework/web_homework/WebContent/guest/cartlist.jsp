<%@page import="kr.del.member.MemberDto"%>
<%@page import="kr.del.product.ProductDto"%>
<%@page import="kr.del.order.OrderBean"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cartMgr" class="kr.del.order.CartMgr" scope="session"/>
<jsp:useBean id="productMgr" class="kr.del.product.ProductMgr"/>
<jsp:useBean id="memberMgr" class="kr.del.member.MemberMgr"></jsp:useBean>

<%
request.setCharacterEncoding("utf-8");
String id = (String)session.getAttribute("idKey");

MemberDto dto = memberMgr.getMember(id);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 주문</title>
<link type="text/css" rel="stylesheet" href="../css/button.css">
<script type="text/javascript" src="../js/script.js"></script>
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
</head>
<body>
<%@ include file="top.jsp" %>

<table style="width: 100%" class="table">
	<tr style="background-color: silver;">
  		<th>주문상품</th><th>가격(소계)</th><th>수량</th><th>주소</th><th>조회</th>
 	 </tr>
<%
int totalPrice = 0;
Hashtable hCart = cartMgr.getCartList();
if(id == null){%>
	<script>
		alert("로그인이 필요합니다");
		location.href = "../member/login.jsp";
	</script>
<%}else{

if(hCart.size() == 0){
%>
	<tr><td colspan="5">주문 내역이 없습니다.</td></tr>
<%	
}else{
	Enumeration enu = hCart.keys();
	while(enu.hasMoreElements()){
		OrderBean order = (OrderBean)hCart.get(enu.nextElement());
		ProductDto product = productMgr.getProduct(order.getProduct_no());
		int price = Integer.parseInt(product.getPrice());
		int quantity = Integer.parseInt(order.getQuantity());
		int subTotal = price * quantity;  //소계
		totalPrice += subTotal;
		//System.out.println(product.getName() + " " + price + " " + quantity + " " + subTotal);
		
%>
	<tr style="text-align: center;"> 
    <td><%=product.getName() %></td>
    <td><%=subTotal %></td>
    <td><%=quantity %></td>
    <td><%=dto.getAddress() %></td>
    <td>
    	<a class="myButton" href="javascript:productDetail_guest('<%=product.getNo() %>')">상세보기</a>
    </td>
  </tr>
<%}%>
	<tr>
  	<td colspan="4">
  		<br>
  		총 결제 금액 : <%=totalPrice %> &nbsp;&nbsp;
  		<a href="orderproc.jsp" class="myButton">주문하기</a>
  	</td>
  </tr>
<%	
}
}
%>
</table>

<%@ include file="bottom.jsp" %>

<form action="productdetail_g.jsp" name="detailFrm">
  <input type="hidden" name="no">
 </form>
</body>
</html>