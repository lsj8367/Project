<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="productMgr" class="kr.del.product.ProductMgr" scope="page"/>

<%
String flag = request.getParameter("flag");
boolean result = false;

if(flag.equals("insert")){
	//System.out.println("insert");
	result = productMgr.insertProduct(request);
	//클라이언트에서 입력한 데이터가 request를 통해서 jsp로 받는데 그것을 그대로
	//insertProduct로 가져간다.
	
}else if(flag.equals("update")){
	//System.out.println("update");
	result = productMgr.updateProduct(request);
	
}
else if(flag.equals("delete")){
	//System.out.println("delete");	
	result = productMgr.deleteProduct(request.getParameter("no"));
}else{
	response.sendRedirect("productmanager.jsp");
}

if(result){
%>
	<script>
	alert("정상 처리되었습니다.");
	location.href="productmanager.jsp";
	</script>
	
<% }else{ %>
	<script>
	alert("오류 발생\n관리자에게 문의 바람");
	location.href="productmanager.jsp";
	</script>

<%
}
%>