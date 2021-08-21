<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="oldheader.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>비회원 주문 조회</title>
<link rel="stylesheet" href="resources/static/css/layout.css">
<link rel="stylesheet" href="resources/static/css/table.css">
<script type="text/javascript">
window.onload = function() {
	document.getElementById("btnGo").onclick = function() {
		if(unmemberfrm.orderlist_no.value === ""){
			unmemberfrm.orderlist_no.focus();
			alert("주문번호 입력");
			return;
		}else if(unmemberfrm.order_passwd.value === ""){
			unmemberfrm.order_passwd.focus();
			alert("비밀번호 입력");
			return;
		}
		unmemberfrm.submit();
	}
}

</script>
</head>
<body>
<form action="unmember" method="post" id="unmemberfrm">
<div id="container" align="center">
	<div id="leftbar">
		<%@ include file="menutab.jsp" %>
	</div>
	<div id="content">
		<table class="newbooktable" style="width: 60%; margin: auto; float: none; " >
			<tr>	
				<th colspan="2">주문조회하기</th>
			</tr>
			<tr>
				<td>주문번호</td>
				<td><input type="text" id="orderlist_no" name="orderlist_no" placeholder="주문번호"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="order_passwd" name="order_passwd" placeholder="비밀번호"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input class="btn btn-primary btn-lg" type="button" value="조회하기" id="btnGo"></td>
			</tr>
		</table>
    </div>
    <div id="footer">
    	<%@ include file="footer.jsp" %>
    </div>
</div>
</form>
</body>
</html>