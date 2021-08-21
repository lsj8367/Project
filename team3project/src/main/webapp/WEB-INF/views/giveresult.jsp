<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%
//session.getAttribute("id");
//session.getAttribute("name");
String a = (String)session.getAttribute("id");
if(a.equals("")||a==null)
	response.sendRedirect("login");
	
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	
	//alert('a');
	$("#showResult").empty();
	//alert('b');
	$.ajax({
		type:"post",
		url:"showgive",
		
		dataType:"json",
		success:function(data){
		//	alert('d');
			var str = "<br>";
			str +="<h2 style='text-align: center'>현재 기부한 책 목록</h2>";
			str += "<table class='table table-striped' width='70%' border='0' style='margin-top: 15px; margin: auto; width: 70%;'>";
			str += "<tr><th>책 제목</th><th>저자</th><th>출판사</th><th>기증일</th><th>기증자</th></tr>";
			var list = data.datas;
		
			$(list).each(function (idx, objArr) {
				str +="<tr>";
				str +="<td>"+objArr["ob_name"]+"</td>";
				str +="<td>"+objArr["ob_author"]+"</td>";
				str +="<td>"+objArr["ob_comp"]+"</td>";
				str +="<td>"+objArr["ob_ddate"]+"</td>";
				str +="<td>"+objArr["user_name"]+"</td>";
				str +="</tr>";
			});
			str+="</table>";
			$("#showResult").append(str);
		},
		error:function(){
			$("#showResult").text("에러 발생 : 기증정보 불러오기 실패.");
		}
	});
});
</script>
</head>
<body>
	<%@include file="oldheader.jsp"%>
	<div id="showResult"></div>
<script type="text/javascript">
$(document).ready(function () {
	function crayBtn1()
	{
		$("#dialog-message").dialog({
			modal: true, 
			buttons: {
				"닫기": function() { $(this).dialog('close'); }
			}
		});
	}
	
});
</script>
		<div id="dialog-message" title="감사합니다." style='display: none;'>
					${sessionScope.name }님
					소중한 책을<br /> 기부해주셔서 감사합니다.
				</div>
	<%@include file="footer.jsp"%>

</body>
</html>