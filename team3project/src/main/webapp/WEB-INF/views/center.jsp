<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FAQ 게시판</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="css/bootstrap.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<%
	String a = (String) request.getAttribute("page");
%>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//alert('a');
						/*  $("#showData").empty();
						$("#testShow").text("보여주기");
						$("#showData").text("테스트");
						 */

						//자주 묻는 질문 (전체 리스트) 가져오기 
						$.ajax({
									type : "get",
									url : "qnaAll",
									dataType : "json",
									success : function(data) {
										//alert("a");
										var str = "<br><br>";
										str = "<table class='table table-striped' width='99%' border='0' style='margin-top: 15px'>";
										str += "<tr><th scope='row'>번호</th><th scope='row'>제목</th><th scope='row'>작성일</th><th scope='row'>문제유형</th></tr>";
										var list = data.datas;
										//alert(data.datas);
										$(list)
												.each(
														function(idx, objArr) {
															str += "<tr>";
															str += "<td><div id='haveno'>"+ objArr["faq_no"]+ "</div></td>";
															str += "<td><a href='javascript:clicked("+ objArr["faq_no"]+ ");'>"+ objArr["faq_title"]+"</a></td>";
															//str +="<td>"+objArr["faq_content"]+"</td>";
															str += "<td>"+ objArr["faq_date"]+"</td>";
															str += "<td>"+ objArr["faq_type"]+"</td>";
															//str +="<td><input type='hidden' value = 'objArr["qna_class"]'></td>";
															str += "</tr>";
														});
										str += "</table>";
										$("#showData").append(str);
									},
									error : function() {
										$("#showData")
												.text("에러 발생 :답변 불러오기 실패");
									}
								});
					});

	// 주문 문제 관련 답변 보기
	$(document)
			.ready(
					function() {
						$("#btnOrder")
								.click(
										function() {
											$("#showData").empty();
											//alert($("#valueOrder").val());
											$
													.ajax({
														type : "get",
														url : "qnaPart",
														data : {"order" : jQuery("#valueOrder").val()},
														dataType : "json",
														success : function(data) {
															var str = "<br><br>";
															str = "<table class='table table-striped' width='99%' border='0' style='margin-top: 15px'>";
															str += "<tr><th>번호</th><th>제목</th><th>작성일</th><th>문제유형</th></tr>";
															var list = data.datas;
															//alert(data.datas);
															$(list).each(function(idx,objArr) {
																				str += "<tr>";
																				str += "<td>"+objArr["faq_no"]+"</td>";
																				str += "<td><a href='javascript:clicked("+ objArr["faq_no"]+ ");'>"+objArr["faq_title"]+"</a></td>";
																				//str +="<td>"+objArr["faq_content"]+"</td>";
																				str += "<td>"+objArr["faq_date"]+"</td>";
																				str += "<td>"+objArr["faq_type"]+"</td>";
																				//str +="<td><input type="hidden" value = "objArr["qna_class"]"></td>";
																				str += "</tr>";
																			});
															str += "</table>";
															$("#showData").append(str);
														},
														error : function() {
															$("#showData")
																	.text("주문관련 답변 에러");
														}

													});
										});
					});

	//배송 문제 관련 답변 보기
	$(document)
			.ready(
					function() {

						$("#btnDeliver")
								.click(
										function() {
											$("#showData").empty();
											//alert($("#valueDeliver").val());
											$
													.ajax({
														type : "get",
														url : "qnaPart",
														data : {
															"order" : jQuery(
																	"#valueDeliver")
																	.val()
														},
														dataType : "json",
														success : function(data) {

															var str = "<br><br>";
															str = "<table class='table table-striped' width='99%' border='0' style='margin-top: 15px; margin=auto;'>";
															str += "<tr><th>번호</th><th>제목</th><th>작성일</th><th>문제유형</th></tr>";
															var list = data.datas;
															//alert(data.datas);
															$(list)
																	.each(
																			function(
																					idx,
																					objArr) {
																				str += "<tr>";
																				str += "<td>"+ objArr["faq_no"]+ "</td>";
																				str += "<td><a href='javascript:clicked("+objArr["faq_no"]+");'>"+ objArr["faq_title"]+"</a></td>";
																				//str +="<td>"+objArr["faq_content"]+"</td>";
																				str += "<td>"+objArr["faq_date"]+ "</td>";
																				str += "<td>"+objArr["faq_type"]+ "</td>";
																				//str +="<td><input type="hidden" value = "objArr["qna_class"]"></td>";

																				str += "</tr>";
																			});
															str += "</table>";
															$("#showData")
																	.append(str);
														},
														error : function() {
															$("#showData")
																	.text(
																			"배송 관련 답변 에러");
														}

													});
										});
					});

	//제품 문제 관련 답변 보기
	$(document)
			.ready(
					function() {

						$("#btnProduct")
								.click(
										function() {
											$("#showData").empty();
											//alert($("#valueProduct").val());
											$
													.ajax({
														type : "get",
														url : "qnaPart",
														data : {
															"order" : jQuery(
																	"#valueProduct")
																	.val()
														},
														dataType : "json",
														success : function(data) {

															var str = "<br><br>";
															str = "<table class='table table-striped' width='99%' border='0' style='margin-top: 15px'>";
															str += "<tr><th>번호</th><th>제목</th><th>작성일</th><th>문제유형</th></tr>";
															var list = data.datas;
															//alert(data.datas);
															$(list)
																	.each(
																			function(
																					idx,
																					objArr) {
																				str += "<tr>";
																				str += "<td>"	+ objArr["faq_no"]+"</td>";
																				str += "<td><a href='javascript:clicked("+objArr["faq_no"]+");'>"+ objArr["faq_title"]+"</a></td>";
																				//str +="<td>"+objArr["faq_content"]+"</td>";
																				str +="<td>"+objArr["faq_date"]+"</td>";
																				str += "<td>"+objArr["faq_type"]+"</td>";
																				//str +="<td><input type="hidden" value = "objArr["qna_class"]"></td>";
																				str += "</tr>";
																			});
															str += "</table>";
															$("#showData")
																	.append(str);
														},
														error : function() {
															$("#showData")
																	.text(
																			"제품 관련 답변 에러");
														}

													});
										});
					});

	
		function clicked(faq_no) {
			//alert("a");
			
			$("#showContent").empty();
			 $.ajax({
				type:"get",
				url:"faqDetail",
				data:{"no": faq_no},
				

				dataType:"json",
				success:function(data){
					
					var str = "<br><br>";
					str ="<table class='table table-striped' width='95%' border='0' style='margin-top: 15px'>";
					str +="<tr><th>번호</th><th>제목</th><th>작성일</th><th>문제유형</th></tr>";
						var list = data.datas;
					//	alert(data.datas);
					$(list).each(function(idx, objArr){
						var ss="";	
						str +="<tr>";
						str +="<td><input type='hidden' id='haveNo' value='"+objArr["faq_no"]+"'>"+objArr["faq_no"]+"</td>";
						str +="<td>"+objArr["faq_title"]+"</a></td>";
						str +="<td>"+objArr["faq_date"]+"</td>";
						str +="<td>"+objArr["faq_type"]+"</td>";
						str+="</tr>";
						str +="<tr>";
						ss +="<td>"+objArr["faq_content"]+"</td>";
						str +="<td colspan='4'><div class='showContent' border='0' style='margin:auto;><textarea class='form-control col-sm-10' rows='10' cols='50' style='width:99%;  background-color:white;' readonly='readonly'>"+objArr["faq_content"]+"</textarea></td></div>";
						//str ="<div class='showContent' border='0'><td><input type='textarea' style='width: 1200px; height:500px; text-align: top;' value="+objArr["faq_content"]+"></td></div>";
						str+="</tr>";
							//똑똑한 생각 남... 각각의 줄마다 div 를 걸고 네임값에다가 faq_no 를 추가해서 값을 준 다음에 각각 클릭 먹을 때마다 그 쪽아이디에다가 값을 보이게 하는거지!!!!존똑!
							// ex) "<td><div id=have"+objArr["faq_no"]"+><a href='javascript:clicked("	+ objArr["faq_no"]+ ");'>""+objArr["faq_title"]+"</a></td>";
							// $("#have+objArr["faq_no"]")
							
					});
					str +="</table>";
					$("#showContent").append(str);
					$("#showContent").append(ss);

				
				}, 
				error:function(){
				
					$("#showContent").text("상세보기 에러");
				
				}
			});
				showContent.focus();
		}

		


</script>

</head>
	<%@include file="header.jsp"%>
<body>
	<br>
	<br>
	<h2 align="left">많이 하는 질문</h2>
	<h3 align="right">
		고객센터 문의번호 <br> 1899-1111
	</h3>

<form action="sos" method="get" name="frmSos" id="frmSos">
<div style="margin: auto; align-content: right;"><input type="submit" value="1:1 문의 게시판" ></div> 
</form>

	<form action="centerpage?page=<%=a%>" method="post" id="centerpage">
		<table border="0"
			style="width: 100%; float: none; margin: auto; text-align: center;">

			<!-- <tr>
				<td><input type="button" onclick="openFunc()" id="openFunc"
					value="1:1문의"></td>
			</tr> -->

			<tr>
				<!-- 위에 항목 누를 시, ajax로 작동하도록 -->
				<th>
				<input type="button" id="btnOrder" value="주문"> 
				<input type="button" id="btnDeliver" value="배송"> 
				<input type="button" id="btnProduct" value="상품">
				</th>
				</tr>
		</table>
		<div id="showData"></div>
		<br>
		<div id="showContent"></div>
		<!--  ajax에서 값을 넘기기 위해 사용. -->
		<input type="hidden" id="valueOrder" value="주문"> 
		<input type="hidden" id="valueDeliver" value="배송"> 
		<input type="hidden" id="valueProduct" value="제품">
	</form>
	<script type="js/jquery-3.1.1.js"></script>
	<script type="js/bootstrap.js"></script>
</body>
	<%@include file="footer.jsp"%>
</html>