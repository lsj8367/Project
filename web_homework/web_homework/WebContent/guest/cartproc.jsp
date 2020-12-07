<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- scope에 session을 걸어주면 세션마다 고유한 값을 가진다. --%>
<jsp:useBean id="cartMgr" class="kr.del.order.CartMgr" scope="session"/>
<jsp:useBean id="order" class="kr.del.order.OrderBean"/>
<jsp:setProperty property="*" name="order"/>

<%
//컨트롤러 역할 : flag - 구매목록 보기, 수정, 삭제 판단
String flag = request.getParameter("flag");
String id = (String)session.getAttribute("idKey");
//out.println(order.getProduct_no() + " 주문수 : " + order.getQuantity());

if(id == null){
	response.sendRedirect("../member/login.jsp");	//
}else{
	if(flag == null){ //구매목록 보기 - 카트에 담기
		order.setId(id); //이 아이디는 클라이언트마다 제각각 즉, 아이디마다 다 정보가 다름.
		cartMgr.addCart(order); //orderbean클래스에 담기는 객체들은 id, product_no, quantity 들고
		//cartmgr의 addCart메소드를 호출
%>
		<script>
			alert("장바구니에 상품을 담았습니다.");
			location.href="cartlist.jsp";
		</script>
<%
	}else if(flag.equals("update")){
		order.setId(id);
		cartMgr.updateCart(order);
%>
		<script>
			alert("장바구니 리스트를 변경하였습니다.");
			location.href="cartlist.jsp";
		</script>		
<%
	}else if(flag.equals("del")){
		cartMgr.deleteCart(order);
%>
		<script>
			alert("장바구니 리스트를 삭제하였습니다.");
			location.href="cartlist.jsp";
		</script>
<%		
	}
}
%>