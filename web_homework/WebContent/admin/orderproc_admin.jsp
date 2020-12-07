<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="orderMgr" class="kr.del.order.OrderMgr"/>
<%
String no = request.getParameter("no");
String flag = request.getParameter("flag");
String state = request.getParameter("state");
//out.println(no + " " + flag + " " + state);

boolean b = false;

if(flag.equals("update")){
	b = orderMgr.updateOrder(no, state);
}else if(flag.equals("delete")){
	b = orderMgr.deleteOrder(no);
}else{
	response.sendRedirect("ordermanager.jsp");
}

if(b){
%>
	<script>
		alert("정상적으로 처리 되었습니다.");
		location.href="ordermanager.jsp";
	</script>

<%	}else{%>

	<script>
		alert("오류발생\n관리자에게 문의");
		location.href="ordermanager.jsp";
	</script>

<%
}
%>