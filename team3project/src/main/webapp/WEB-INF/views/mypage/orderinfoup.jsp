<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 다음 주소  api -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
//주소 찾기 버튼 다음 api
function execPostCode() {
    new daum.Postcode({
        oncomplete: function(data) {
           // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

           // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
           // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
           var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
           var extraRoadAddr = ''; // 도로명 조합형 주소 변수

           // 법정동명이 있을 경우 추가한다. (법정리는 제외)
           // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
           if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
               extraRoadAddr += data.bname;
           }
           // 건물명이 있고, 공동주택일 경우 추가한다.
           if(data.buildingName !== '' && data.apartment === 'Y'){
              extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
           }
           // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
           if(extraRoadAddr !== ''){
               extraRoadAddr = ' (' + extraRoadAddr + ')';
           }
           // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
           if(fullRoadAddr !== ''){
               fullRoadAddr += extraRoadAddr;
           }

           // 주소 정보를 해당 필드에 넣는다.
           console.log(fullRoadAddr);
           
          
           $("[id=address1]").val(fullRoadAddr);
           
       }
    }).open();
}

</script>
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
<table style="width: 100%; float: none; margin: auto; text-align: center;">
	<tr>
		<td style="width: 60%;" rowspan="5">
					
			<form action="myorderupok" method="post">
			<table class="buymaintable" style="width: 80%; float: none; margin: auto; text-align: center;">
			
				<tr>
					<th colspan="2" colspan="2" style="background-color: #114c3d; color: white; text-align: center;">
					주문정보
					</th>
				</tr>
				<tr>
					<th>주문번호</th>
					<td>
						${odinfo.orderlistNo }
						<input type="hidden" name="orderlist_no" value="${odinfo.orderlistNo }">
					</td>
				</tr>
				<tr>
					<th>주문자명</th>
					<td><input type="text" name="order_person" value="${odinfo.orderPerson }"></td>
				</tr>
				<tr>
					<th>주문일자</th>
					<td>${odinfo.orderDate }</td>
				</tr>
				<tr>
					<th>배송주소</th>
					<td style="text-align: left;">
						<input type="text" value="${odinfo.orderAddress }" name="order_address" id ="address1" readonly>
						<input type="button" value="찾기" id="btnZip" onclick="execPostCode();">
						<span class="error" id="errMsg_address1" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<th>주문내역</th>
					<td>
						<table style="width: 100%; float: none; margin: auto; text-align: center; border-bottom: 1px;">
							<tr><th>책번호</th><th>책종류</th><th>권수</th><th>가격</th></tr>
							<c:forEach var="oia" items="${odinfoall }">
								<tr>
									<td>
										<c:choose>
											<c:when test="${oia.orderBooktype eq '1' }">
												<a href = "newbook?book_no=${oia.orderBookno }">${oia.orderBookno }</a>
											</c:when>
											<c:when test="${oia.orderBooktype eq '2' }">
												<a href = "oldbook?book_no=${oia.orderBookno }">${oia.orderBookno }</a>
											</c:when>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${oia.orderBooktype eq '1' }">
												새책
											</c:when>
											<c:when test="${oia.orderBooktype eq '2' }">
												중고책
											</c:when>
										</c:choose>
									</td>
									<td>${oia.orderScount }</td>
									<td>${oia.orderSum }</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				<tr>
					<th>총금액</th>
					<td>${odinfo.orderSum }</td>
				</tr>
				<tr>
					<th>결제종류</th>
					<td>
						<c:choose>
							<c:when test="${odinfo.orderPaytype eq '0' }">
								무통장입금
							</c:when>
							<c:when test="${odinfo.orderPaytype eq '1' }">
								카드결제
							</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>주문진행현황</th>
					<td>
						<c:choose>
							<c:when test="${odinfo.orderState eq '0'}">
								주문완료(결제대기)
							</c:when>
							<c:when test="${odinfo.orderState eq '1'}">
								결제완료
							</c:when>
							<c:when test="${odinfo.orderState eq '2'}">
								상품준비중
							</c:when>
							<c:when test="${odinfo.orderState eq '3'}">
								배송준비중
							</c:when>
							<c:when test="${odinfo.orderState eq '4'}">
								배송중
							</c:when>
							<c:when test="${odinfo.orderState eq '5'}">
								배송완료
							</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan='2' style="text-align: center;">
						
						<input class="btn btn-outline-success" type="submit" value = "수정완료">
						<input class="btn btn-outline-success" type="button" value = "취소" onclick="location.href='orderinfocheck?no=${order.orderlistNo }'">
					</td>
				</tr>
			</table>
			</form>

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

	</table>
<br>

</div>

</body>
</html>