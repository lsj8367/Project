<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style type="text/css">
//a태그
a { text-decoration:none !important } 
a:hover { text-decoration:none !important }

@font-face {
    font-family: 'TmoneyRoundWindExtraBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/TmoneyRoundWindExtraBold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}


//
body {
  font-family: 'TmoneyRoundWindExtraBold';
}

div#s_left{
	width:15%;
	height: 100%;
	float:left;
}
#contents{
	width: 70%;
	height: 100%;
	float: left;
}
#s_right{
	width: 15%;
	height: 100%;
	float:left;
}


html, body {
	height:100%;
	overflow:hidden;
    margin:0;

    padding:0;
}

#footer_wrap {width:100%;height:118px;clear:both;margin-top:30px; position: sticky;}


//테이블
.buymaintable {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: auto;
  float : none;
  padding: 0;
  width: 80%;
  table-layout: fixed;
}

.buymaintable caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

.buymaintable tr {
  background-color: #fdfdfd;
  border: 1px solid #ddd;
  padding: .35em;
}

.buymaintable th,
.buymaintable td {
  padding: .625em;
  text-align: left;
}

.buymaintable th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
}

@media screen and (max-width: 600px) {
  .buymaintable {
    border: 0;
  }

  .buymaintable caption {
    font-size: 1.3em;
  }
  
  .buymaintable thead {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
  }
  
  .buymaintable tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
  }
  
  .buymaintable td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: right;
  }
  
  .buymaintable td::before {
    /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  .buymaintable td:last-child {
    border-bottom: 0;
  }
}
</style>
</head>
<%@include file="../header.jsp"%>
<body  style="overflow: auto; margin: 0; padding: 0; height: 100%">
<br><br>
<div id="s_left" align="center">
	<table class="buymaintable" style="width: 80%; float: none; margin: auto; text-align: center;">
		<tr>
			<td style="background-color: #114c3d; color: white; text-align: center;">
				Quick Menu
			</td>
		<tr>
		<tr>
			<td style="text-align: center;">
				<button type="button" class="btn btn-outline-success" onclick="location.href='mypage'">마이페이지</button>
				<br><br>
				<button type="button" class="btn btn-outline-success" onclick="location.href='myorder'">주문내역/배송조회</button>
				<br><br>
				<button type="button" class="btn btn-outline-success" onclick="location.href='myrent'">대여내역/연체조회</button>
			</td>
		<tr>
	</table>
</div>

<div id ="contents">
<table class="buymaintable" style="width: 80%; float: none; margin: auto; text-align: center;">
	<!-- 1tr -->
	<tr>
		<td style="width: 60%;">
			<c:choose>
				<c:when test="${f:length(odbook) eq 0}">
					주문내역이 없습니다.<br>
				</c:when>
				
				<c:otherwise>
					
					<table class="buymaintable"  style="margin: auto; width: 80%;">
						<tr>
							<td colspan="6" style="background-color: #114c3d; color: white; text-align: center;">
							주문목록
							</td>
						</tr>
						<tr>
							<th>주문번호</th><th>주문일자</th><th>도서종류수</th><th>주문도서수</th><th>주문가격</th><th>주문상태</th>
						</tr>
					
						<c:forEach var="order" items="${odbook}">
						<tr>
							<td>${order.orderlist_no}</td>
							<td>
								<fmt:parseDate var="dateString" value="${order.order_date}" pattern="yyyy-MM-dd HH:mm:ss.S" />
		      					<fmt:formatDate value="${dateString}" pattern="yyyy년 MM월 dd일" />
							</td>
							<td>${order.count}</td>
							<td>${order.sum }</td>
							<td>${order.order_sum }</td>
							<td>
							<c:choose>
							<c:when test="${order.order_state eq '0'}">
								주문완료
							</c:when>
							<c:when test="${order.order_state eq '1'}">
								결제완료
							</c:when>
							<c:when test="${order.order_state eq '2'}">
								상품준비중
							</c:when>
							<c:when test="${order.order_state eq '3'}">
								배송준비중
							</c:when>
							<c:when test="${order.order_state eq '4'}">
								배송중
							</c:when>
							<c:when test="${order.order_state eq '5'}">
								배송완료
							</c:when>
						</c:choose>
							</td>
						</tr>
						</c:forEach>
						<tr>
							<td colspan="6" style="text-align: center;">
							<button type="button" class="btn btn-outline-success" onclick="location.href='myorder'">더보기</button>
							</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	
	<!-- 2tr -->
	<tr>
		<td>
			
			<c:choose>
				<c:when test="${f:length(odbook) eq 0}">
					최근 대여 목록이 없습니다.<br><br><br>
				</c:when>
				
				<c:otherwise>
					<table class="buymaintable" style="width: 80%; float: none; margin: auto; text-align: center;">
						<tr>
							<td colspan="3" style="background-color: #114c3d; color: white; text-align: center;">
								최근 대여 내역
							</td>
						</tr>
						
						<tr>
							<th>도서명</th><th>대여시작일</th><th>반납예정일</th>
						</tr>
						
						<c:forEach var="rent" items="${rtbook}">
						<tr>
							<td>${rent.ob_name}</td>
							<td>${rent.rent_sdate}</td>
							<td>${rent.rent_edate}</td>
						</tr>
						</c:forEach>
						<tr>
							<td colspan="3" style="text-align: center;">
								<button type="button" class="btn btn-outline-success" onclick="location.href='myrent'">더보기</button>
							</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	
	<!-- 3tr -->
	<tr>
		<td>
			<c:choose>
				<c:when test="${f:length(dnbook) eq 0}">
					기증 내역이 없습니다.<br><br><br>
				</c:when>
				
				<c:otherwise>
					<table  class="buymaintable" style="width: 80%; float: none; margin: auto; text-align: center;">
						<tr>
							<td colspan="2" style="background-color: #114c3d; color: white; text-align: center;">
								최근 기증 내역
							</td>
						</tr>
						<tr>
							<th>도서명</th><th>나의 한마디</th>
						</tr>
						
						<c:forEach var="donor" items="${dnbook}">
						<tr>
							<td>${donor.ob_name}</td>
							<td>${donor.ob_comment}</td>
						</tr>
						</c:forEach>
						
						<tr>
							<td colspan="2" style="text-align: center;">
							<button type="button" class="btn btn-outline-success" onclick="location.href='mydonor'">더보기</button>
							</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
			
			
			
		</td>
	</tr>
	
	<!-- 4tr -->
	<tr>
		<td>
			<table  class="buymaintable" style="width: 80%; float: none; margin: auto; text-align: center;">
				<tr>
					<td colspan="2" style="background-color: #114c3d; color: white; text-align: center;">
						나의 등록 카드
					</td>
				</tr>
				<tr>
					<th>카드번호</th><th>카드사</th>
				</tr>
			
				<c:forEach var="card" items="${cdinfo}">
					<tr>
						<td>${card.card_no}</td>
						<td>${card.card_comp}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
	
	<!-- 5tr -->
	<tr>
		<td>
			<c:choose>
				<c:when test="${f:length(inqinfo) eq 0}">
					나의 QnA 질의내역이 없습니다.<br><br><br>
				</c:when>
				
				<c:otherwise>
					<table  class="buymaintable" style="width: 80%; float: none; margin: auto; text-align: center;">
						<tr>
							<td colspan="3" style="background-color: #114c3d; color: white; text-align: center;">
								나의 QnA 질의내역
							</td>
						</tr>
						<tr>
							<th>QnA 제목</th><th>문의내용</th><th>문의일자</th>
						</tr>
		
						<c:forEach var="inq" items="${inqinfo}">
							<tr>
								<td>${inq.inq_title}</td>
								<td>${inq.inq_context}</td>
								<td>${inq.inq_ddate}</td>
							</tr>
						</c:forEach>
						
						<tr>
							<td colspan="3" style="text-align: center;">
								<button type="button" class="btn btn-outline-success" onclick="location.href='myqna'">더보기</button>
							</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
			
			
		</td>
	</tr>
</table>
<div id="footer_wrap">
<%@include file="../footer.jsp"%>
</div>
</div>

<div id="s_right">
	<table class="buymaintable"  style="margin: auto; width: 100%;">
		<tr>
			<td style="background-color: #114c3d; color: white; text-align: center;">
			<c:choose>
				<c:when test="${sessionScope.id eq null}">
					방문자님 환영합니다.
				</c:when>
				
				<c:otherwise>
					${sessionScope.name}님 환영합니다.<br>보유포인트는 ${sessionScope.point}point
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td style="text-align: center;">
				오늘의 추천!
				<div onclick="location.href='newbook?book_no=${randNewbook.nb_no}'">
				<img  src="${randNewbook.nb_image}" width="100" height="160"><br>
		</div>
			</td>
		</tr>
	</table>
<br>

</div>
</body>
</html>