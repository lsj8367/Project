<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%@ include file="oldheader.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>중고 책 정보</title>
<link rel="stylesheet" href="resources/static/css/layout.css">
<link rel="stylesheet" href="resources/static/css/table.css">
</head>
<body style="overflow: auto; margin: 0; padding: 0; height: 100%">
<div id="container">
		
		<div id="leftbar">
			<%@ include file="menutab.jsp" %>
     	 </div>
     	 
     	 <div id="content">
     	 	<form name="oldfrm" method="post">
				<input type="hidden" name="ob_no" value="${bookinfo.ob_no}">
				<input type="hidden" name="user_id" value="${sessionScope.id}">
				<table style="margin: auto; float: none; width: 100%" class="newbooktable">
					<tr>
						<th colspan="2">책 정보 </th><th>조회수 :${bookinfo.ob_readcnt}</th>
						</tr>
					<tr>
						<td rowspan="6">
						<img width="200" src="./resources/static/${bookinfo.ob_image}"/> <!-- 사진이미지 경로 -->
				
						</td>
					</tr>
				
					<tr>
						<td colspan="2">
							책 제목 : ${bookinfo.ob_name}
						</td>
					</tr>
					<tr>
						<td colspan="2">
							저자 : ${bookinfo.ob_author}
							<c:choose>
								<c:when test="${bookinfo.ob_inter eq ''}">
									 
								</c:when>
								<c:otherwise>
									/ 역자 : ${bookinfo.ob_inter}						
								</c:otherwise>
							</c:choose>	
						</td>
					</tr>
					<tr>
							<fmt:parseDate var="dateString" value="${bookinfo.ob_bdate}" pattern="yyyy-MM-dd HH:mm:ss.S" />
						<td colspan="2">등록일 : <fmt:formatDate value="${dateString}" pattern="yyyy년MM월dd일" /></td>
					</tr>
					<tr>
						<td colspan="2">
							출판사 : ${bookinfo.ob_comp }
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<c:choose>
								<c:when test="${sessionScope.id eq null}">
									가격 : ${bookinfo.ob_price } 원
									<input type="hidden" value="${bookinfo.ob_price}">
								</c:when>
								<c:otherwise>
									회원가 : <f:parseNumber value="${bookinfo.ob_price *0.5}" integerOnly="true"/> 원
									<input type="hidden" value="${bookinfo.ob_price}">
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td colspan="3" style="text-align: center;">
							<input type="submit" class="btn btn-primary btn-lg" id="goCart" value="장바구니에 담기" onclick="window.open('cart?page=rentmain','장바구니','width=400,height=500,location=no,status=no,scrollbars=no')" >
							<!--  formaction ="cart?page=rentmain" -->
							<input type="submit" class="btn btn-primary btn-lg" id="goBuy" value="바로 구매하기" formaction="buy">
						</td>
					</tr>
				</table>
				</form>
				<br><br>
				<table class="newbooktable" style="margin: auto; float: none; width: 100%">
					<tr>
						<th>기증자 한마디</th> 
					</tr>
					<tr>
						<td>${bookinfo.ob_comment }</td>
					</tr>
				</table>
     	 	
     	 	
     	 	
     	 </div>

     	 <div id="sidebar">
     	 	
     	 	
     	 	
      	</div>

      	<div id="footer">
      		<%@ include file="footer.jsp" %>
   	   </div>
</div>
    
</body>
</html>