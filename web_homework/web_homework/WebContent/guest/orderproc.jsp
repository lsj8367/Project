<%@page import="kr.del.order.OrderBean"%>
<%@page import="kr.del.order.OrderDto"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cartMgr" class="kr.del.order.CartMgr" scope="session" />
<jsp:useBean id="orderMgr" class="kr.del.order.OrderMgr" />
<jsp:useBean id="productMgr" class="kr.del.product.ProductMgr" />

<%
Hashtable hCart = cartMgr.getCartList();

Enumeration enu = hCart.keys();

if(hCart.size() == 0) {
%>
	<script>
	alert("주문내역이 없습니다");
	location.href = "orderlist.jsp";
	</script>
<%	
}else{
	while(enu.hasMoreElements()){
		OrderBean order = (OrderBean)hCart.get(enu.nextElement());
		orderMgr.insertOrder(order);         //주문 자료를 shop_order 에 저장
		productMgr.reduceProduct(order);   //주문 수량 만큼 상품의 재고량에서 빼기
		cartMgr.deleteCart(order);   // 장바구니 내용 지우기
	}
%>
	<script>
	alert("주문처리 성공!");
	location.href = "orderlist.jsp";
	</script>
<%
}
%>




