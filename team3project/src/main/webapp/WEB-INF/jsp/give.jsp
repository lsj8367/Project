<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="oldheader.jsp"%>
<%
session.getAttribute("id");
session.getAttribute("name");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/static/css/layout.css">
<link rel="stylesheet" href="resources/static/css/table.css">
<script type="text/javascript" src="${pageContext.request.contextPath}"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}../../css/admin.css">
<style type="text/css">

</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("a[name='file-delete']").on("click", function(e) {
			e.preventDefault();
			deleteFile($(this));
		});
	})
	function addFile() {
		var str = "<div class='file-group'><input type='file' name='file'><a href='#this' name='file-delete'>삭제</a></div>";
		$("#file-list").append(str);
		$("a[name='file-delete']").on("click", function(e) {
			e.preventDefault();
			deleteFile($(this));
		});
	}

	function deleteFile(obj) {
		obj.parent().remove();
	}

	function check() {
		/*
		if(givefrm.file.value ==""){
			alert("이미지를 추가하세요");
			givefrm.file.focus();
		}
		 */
		if (givefrm.ob_name.value == "") {
			alert("제목을 입력하세요");
			givefrm.ob_name.focus();
			return;
		} else if (givefrm.ob_author.value == "") {
			alert("저자 입력하세요");
			givefrm.ob_author.focus();
			return;
		} else if (givefrm.ob_comp.value == "") {
			alert("출판사 입력");
			givefrm.ob_comp.focus();
			return;
		} else if (givefrm.ob_bdate.value == "") {
			alert("날짜 선택");
			givefrm.ob_bdate.focus();
			return;
		}
		/*
		else if(givefrm.ob_comment.value == ""){
			alert("한마디 입력");
			givefrm.ob_comment.focus();
		}
		 */
		else
			givefrm.submit();
	}
</script>
</head>
<body>
<form action="give" method="post" name="givefrm" id="givefrm" enctype="multipart/form-data">
<div id="container">
	<div id="leftbar">
		<%@ include file="menutab.jsp"%>
	</div>
	
     	 <div id="content">
     	 	<table class='table table-striped' style='margin-top: 15px; margin: auto; text-align: center;'>
			<tr>
				<th colspan="2">중고 도서 기부</th>
			</tr>

			 <tr>
				<td>이미지</td>
				<td><input type="file" name="file" accept="images/*"></td>
			</tr> 
			<tr>
				<td>제목</td>
				<td><input type="text" name="ob_name"></td>
			</tr>
			<tr>
				<td>저자</td>
				<td><input type="text" name="ob_author"></td>
			</tr>
			<tr>
				<td>역자</td>
				<td><input type="text" name="ob_inter"></td>
			</tr>

			<tr>
				<td>분야</td>
				<td><select name="ob_genre">
						<option value="미분류" selected>미분류
						<option value="소설/시/희곡">소설/시/희곡
						<option value="에세이">에세이
						<option value="어린이">어린이
						<option value="유아">유아
						<option value="경제경영">경제경영
						<option value="인문학">인문학
						<option value="사회과학">사회과학
						<option value="수험서/자격증">수험서/자격증
						<option value="외국어">외국어
						<option value="대학교재">대학교재
						<option value="컴퓨터/모바일">컴퓨터/모바일
				</select></td>
			</tr>
			<tr>
				<td>출판사</td>
				<td><input type="text" name="ob_comp"></td>
			</tr>
			<tr>
				<td>출판일자</td>
				<td><input type="date" name="ob_bdate"></td>
			</tr>
			<tr>
				<td>도서정가</td>
				<td><input type="number" name="ob_price"></td>
			</tr>
			<tr>
				<td colspan="2">기증자 한마디</td>
			</tr>
			<tr>
				<td colspan="2"><textarea rows="1" name="ob_comment"
						cols="80px"></textarea></td>
			</tr>
			<!-- <tr>
				<td colspan="2" style="text-align: center;">
					<input type="button" value="기증하기" onClick="check()">
					<input type="reset" value="초기화">
					<input type="button" value="뒤로가기" onClick="history.back()">
				</td>
			</tr> -->
		<!-- 	<tr>
				<td colspan="2">
					<div class="form-group" id="file-list">
		클릭시 파일 추가 탭
						<a href="#this" onclick="addFile()">파일추가</a>
						<div class="file-group">
							<input type="file" name="file"> <a href='#this'
								name='file-delete'>삭제</a>
						</div>
					</div>
				</td>
			</tr> -->
			<tr>
				<td colspan="2" style="margin: auto; text-align: center; align-items: center;">
					<button type="button" onclick="check()" class="btn btn-primary btn-lg">기증하기</button>
				</td>
			</tr>

		</table>
		<!-- 
		<input type="hidden" value="${ob_no }">
		 -->
     	 </div>

      	<div id="footer">
			<%@include file="footer.jsp"%>
   	   </div>
    </div>
		
</form>

</body>
</html>
