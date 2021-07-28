<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%@ include file="oldheader.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>중고 책 정보</title>
<style type="text/css">
html, body { height:100%; overflow:hidden }
#footer_wrap {width:100%;height:118px;clear:both;margin-top:30px; position: sticky;}
#contents{
	width: 70%;
	height: 100%;
	float: left;
}


html, body {

    margin:0;

    padding:0;
}

#footer {

    position:absolute;

    bottom:0;

    width:100%;

    height:100px;   
}



//테이블 적용
.oldrentaltable {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: 0;
  padding: 0;
  width: 100%;
  table-layout: fixed;
}

.oldrentaltable caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

.oldrentaltable tr {
  background-color: #fdfdfd;
  border: 1px solid #ddd;
  padding: .35em;
}

.oldrentaltable th,
.oldrentaltable td {
  padding: .625em;
  text-align: center;
}

.oldrentaltable th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
}

@media screen and (max-width: 600px) {
  .oldrentaltable {
    border: 0;
  }

  .oldrentaltable caption {
    font-size: 1.3em;
  }
  
  .oldrentaltable thead {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
  }
  
  .oldrentaltable tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
  }
  
  .oldrentaltable td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: right;
  }
  
  .oldrentaltable td::before {
    /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  .oldrentaltable td:last-child {
    border-bottom: 0;
  }
}
</style>
</head>



<body style="overflow: auto; margin: 0; padding: 0; height: 100%">
<!-- dialog 띄우기 -->
<div id="loginDialog" style="display: none;">
   <p>로그인이 필요합니다.</p>
</div>

<div id="rentDialog" style="display: none;">
   <p>대여 중인 책입니다.</p>
</div>

<div id="pointDialog" style="display: none;">
   <p>포인트가 부족합니다.</p>
</div>
<br><br><br>
<form action="Rental" method="post">
<input type="hidden" value="${bookinfo.ob_no}" name="ob_no">
<input type="hidden" value="${sessionScope.id}" name="user_id">
<input type="hidden" value="${rentUser.user_point}" id="user_point">


<table class="oldrentaltable" border="1" style="width: 60%; float: none; margin: auto;">
	<tr>
		<td colspan="2"  style="text-align: center; background-color: #37aacf; color: white;">
			책제목
		</td>
	</tr>
	
	<tr>
		<td colspan="2" style="text-align: right;">
			주문수 : ${bookinfo.ob_scount} 조회수 :${bookinfo.ob_readcnt}
		</td>
	</tr>
	<tr>
		<td>
		<img width="200" src="./${bookinfo.ob_image}"/>
		</td>
			<td>
				<table class="oldrentaltable" border="1" style="width: 80%; float: none; margin: auto;">
					<tr >
						<td style="text-align: center; background-color: #37aacf; color: white;">
							${bookinfo.ob_name}
						</td>
					</tr>
					<tr>
						<td>
							저자 : ${bookinfo.ob_author}
							<c:choose>
								<c:when test="${bookinfo.ob_inter eq ''}">
									<br>
								</c:when>
								<c:otherwise>
									/ 역자 : ${bookinfo.ob_inter}	<br>						
								</c:otherwise>
							</c:choose>
							<fmt:parseDate var="dateString" value="${bookinfo.ob_bdate}" pattern="yyyy-MM-dd HH:mm:ss.S" />
                 					등록일 : <fmt:formatDate value="${dateString}" pattern="yyyy년MM월dd일" /><br>
									출판사 : ${bookinfo.ob_comp }<br>
									
							<c:if test="${bookinfo.ob_state eq '1'}">
								상태 : <c:out value="${bookinfo.ob_state}"/>등급
							</c:if>
							<c:if test="${bookinfo.ob_state eq '2'}">
								상태 : <c:out value="${bookinfo.ob_state}"/>등급
							</c:if>
							<c:if test="${bookinfo.ob_state eq '3'}">
								상태 : <c:out value="${bookinfo.ob_state}"/>등급
							</c:if>
							
							
							<!--  가격 : ${bookinfo.ob_price } 원 <br>-->
						</td>
					</tr>
				</table>
			</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
			기증자 한마디 <br>
			${bookinfo.ob_comment}
		</td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
			<!--  <input type="submit" id="goCart" value="장바구니에 담기" formaction ="cart">-->
			<input type="submit" class="btn btn-outline-info" id="goBuy" value="대여하기">
		</td>
	</tr>
</table>
</form>

<!-- footer 크기만큼 주면 안 가려짐 -->
<div style="height: 100px;"></div>
<div id="footer">
<%@include file="footer.jsp" %>
</div>

<script type="text/javascript">
$(document).ready(function() {
	//로그인 안 하고 들어간 경우
	if(${sessionScope.id eq null}){
		//alert("대여 서비스는 로그인이 필요합니다.");
		$('#loginDialog').dialog({
		      title: '로그인 오류!',
		      modal: true, //팝업창으로 띄우기
		      width: '300', //폭
		      height: '300', // 높이
		      buttons:{ //다이얼로그 안에 버튼
	              "확인":function(){
	              $(this).dialog("close");
					window.history.back();
	            }
		     }
			});
		return;
	}
	//포인트 부족일 경우 최소 11000은 있어야 한다.pointDialog
	if($('#user_point').val() < 11000){
		$('#pointDialog').dialog({
		      title: '대여 오류!',
		      modal: true, //팝업창으로 띄우기
		      width: '300', //폭
		      height: '300', // 높이
		      buttons:{ //다이얼로그 안에 버튼
	              "확인":function(){
	              $(this).dialog("close");
					window.history.back();
	            }
		     }
			});
		return;
	}
	//대여중일 경우
	if(${bookinfo.ob_donor} == 1){
		$('#rentDialog').dialog({
		      title: '대여 오류!',
		      modal: true, //팝업창으로 띄우기
		      width: '300', //폭
		      height: '300', // 높이
		      buttons:{ //다이얼로그 안에 버튼
	              "확인":function(){
	              $(this).dialog("close");
					window.history.back();
	            }
		     }
			});
		return;
	}
	
});
</script>

</body>
</html>