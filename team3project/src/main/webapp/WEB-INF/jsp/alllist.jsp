<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등급의 전체목록</title>
<link rel="stylesheet" href="resources/static/css/layout.css">
<link rel="stylesheet" href="resources/static/css/table.css">

<style type="text/css">
html, body { height:100%; overflow:hidden }
</style>

</head>
<%@include file="oldheader.jsp" %>
<body style="overflow: auto; margin: 0; padding: 0;">
<br><br><br>
<!-- 전체 페이지  -->
<div id="container">
		
		<!-- 왼쪽 사이드바 메뉴 -->
		
		<div id="leftbar">
			<%@ include file="menutab.jsp" %>
     	 </div>
     	 
     	 
     	 <!-- 메인 시작 -->
     	 
     	 <div id="content">
     	 	<table class="newbooktable" style="width: 100%; margin: auto;">

				<tr>
					<th>책</th><th>제목</th><th>가격</th><th>출판년도</th><th>출판사</th>
				</tr>
				
				<!-- 반복!! -->
				<!-- 데이터 가져오는곳 -->
				<c:choose>
				<c:when test="${f:length(list) eq 0}">
					<tr style="text-align: center;"><td colspan="5">목록이 없습니다.</td></tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="l" items="${list}">
						<c:if test="${l.ob_state eq '1' }">
						<tr style="text-align: center;">
							<td><a href="oldbook?book_no=${l.ob_no }"><img width="100" src="./${l.ob_image}"/></a></td>
							<td><a href="oldbook?book_no=${l.ob_no }">${l.ob_name}</a></td>
							<td>${l.ob_price} 원</td>
							<fmt:parseDate var="dateString" value="${l.ob_bdate}" pattern="yyyy-MM-dd HH:mm:ss.S" />
							<td><fmt:formatDate value="${dateString}" pattern="yyyy년MM월dd일" /></td>
							<td>${l.ob_comp}</td>
						</tr>
						</c:if>
						<c:if test="${l.ob_state eq '2' or '3' }">
						<tr style="text-align: center;">
							<td><a href="oldrental?book_no=${l.ob_no }"><img width="100" src="./${l.ob_image}"/></a></td>
							<td><a href="oldrental?book_no=${l.ob_no }">${l.ob_name}</a></td>
							<td>${l.ob_price} 원</td>
							<fmt:parseDate var="dateString" value="${l.ob_bdate}" pattern="yyyy-MM-dd HH:mm:ss.S" />
							<td><fmt:formatDate value="${dateString}" pattern="yyyy년MM월dd일" /></td>
							<td>${l.ob_comp}</td>
						</tr>
						</c:if>
					</c:forEach>
				</c:otherwise>
				</c:choose>
			</table>
     	 </div>
			
			
			
			<!-- 우측 사이드바-->
     	 <div id="sidebar">
	     	 <table class="newbooktable" style="padding: 0; margin: 0; float: right; width: 100%">
				<tr>
					<c:choose>
						<c:when test="${sessionScope.id eq null}">
							<td colspan="2"><a href="give">내 책 기증하기</a></td>
						</c:when>
	
						<c:otherwise>
							<td colspan="2">
								<a href="give">내 책 기증하기</a><br>
								<a href="mypage">대여목록 보기</a>
							</td>				
						</c:otherwise>
					</c:choose>
				
				</tr>
				<tr>
					<c:choose>
					<c:when test="${sessionScope.id eq null}">
						<td colspan="2">방문자님 환영합니다.</td>
					</c:when>
					<c:otherwise>
						<td colspan="2">${sessionScope.name}님 환영합니다.<br>보유포인트는 ${sessionScope.point}point</td>
					</c:otherwise>
					</c:choose>
				</tr>
	
				<tr>
					<td colspan="2">↓이달의 베스트 대여도서 ↓</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><a href="oldrental?book_no=${best.ob_no }"> <img width="100" src="./${best.ob_image}"></a></td>
					<!-- ${best.ob_name} -->
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">이달의 다독왕은 <br>
						${readbest.user_name} 님 입니다
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center"><a href="center">고객 센터</a></td>
				</tr>
			</table>
      	</div>


			<!-- footer 시작 -->
      	<div id="footer">
      	<%@include file="footer.jsp" %>
   	   </div>

</div>
</body>
</html>