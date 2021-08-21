<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책정보</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
// 책 개수 보유한 만큼 제어
$(function(){
	$('#decreaseQuantity').click(function(e){
		e.preventDefault();
		var stat = $('#orderscount').val();
		var num = parseInt(stat,10);
		num--;
			if(num<=0){
				$('#minDialog').dialog({
				      title: '수량 오류!',
				      modal: true, //팝업창으로 띄우기
				      width: '300', //폭
				      height: '300', // 높이
				      buttons:{ //다이얼로그 안에 버튼
			              "확인":function(){
			              $(this).dialog("close");
			            }
				     }
					});
				
				num =1;
			}
		$('#orderscount').val(num);
		});
	
		$('#increaseQuantity').click(function(e){
			e.preventDefault();
			var stat = $('#orderscount').val();
			var num = parseInt(stat,10);
			num++;
		
			if(num>${newbook.nb_stock}){
				$('#maxDialog').dialog({
				      title: '수량 오류!',
				      modal: true, //팝업창으로 띄우기
				      width: '300', //폭
				      height: '300', // 높이
				      buttons:{ //다이얼로그 안에 버튼
			              "확인":function(){
			              $(this).dialog("close");
							
			            }
				     }
					});
				
				num=5;
			}
			$('#orderscount').val(num);
	});
});

// 세션이 있을 경우에만 10%할인된 가격 보여주기
$(document).ready(function(){
	$(".user").hide();
	if(!${sessionScope.id eq null}){
		$("#user_price").show();
		$("#user_price").text("-> (회원 10% 할인  : " + ${newbook.nb_price}*0.9 +")");
		event.preventDefault();
	}	
});

// 회원일 경우에만 리뷰쓰고 추천 눌룰  수 있게
$(function(){
	//비회원일 경우
	if(${sessionScope.id eq null}){
		$("#writereview").attr("disabled", true);
		$(".gonggam").attr("disabled", true);
		$(".delete").attr("disabled", true);
	}
	//회원일 경우
	else{
		$("#writereview").attr("disabled", false);
		$(".gonggam").attr("disabled", false);
		$(".delete").attr("disabled", false);
	}
});

function cart(){
	$('#cartDialog').dialog({
	      title: '장바구니 서비스 준비중....!',
	      modal: true, //팝업창으로 띄우기
	      width: '300', //폭
	      height: '300', // 높이
	      buttons:{ //다이얼로그 안에 버튼
            "확인":function(){
            $(this).dialog("close");
				
          }
	     }
	});
}
</script>

<style type="text/css">
html, body { height:100%; overflow:hidden }
#footer_wrap {width:100%;height:118px;clear:both;margin-top:30px; position: sticky;}
div#s_left {
	width: 30%;
	height: 100%;
	float: left;
}

#contents{
	width: 70%;
	height: 100%;
	float: center;
	margin-left: 280px;
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
.newbooktable {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: 0;
  padding: 0;
  width: 100%;
  table-layout: fixed;
}

.newbooktable caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

.newbooktable tr {
  background-color: #fdfdfd;
  border: 1px solid #ddd;
  padding: .35em;
}

.newbooktable th,
.newbooktable td {
  padding: .625em;
  text-align: left;
}

.newbooktable th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
}

@media screen and (max-width: 600px) {
  .newbooktable {
    border: 0;
  }

  .newbooktable caption {
    font-size: 1.3em;
  }
  
  .newbooktable thead {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
  }
  
  .newbooktable tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
  }
  
  .newbooktable td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: right;
  }
  
  .newbooktable td::before {
    /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  .newbooktable td:last-child {
    border-bottom: 0;
  }
  
  .newbooktable td textarea{
  	width:90%; margin:auto;
  }
}
</style>

</head>
<%@include file="header.jsp" %>
<body style="overflow: auto; margin: 0; padding: 0; height: 100%">
<!-- dialog 띄우기 -->
<div id="minDialog" style="display: none;">
   <p>주문할 수 있는 최소 수량입니다.</p>
</div>
<div id="maxDialog" style="display: none;">
   <p>주문할 수 있는 최대 수량입니다.</p>
</div>
<div id="cartDialog" style="display: none;">
   <p>이용에 불편을 드려 죄송합니다.</p>
</div>


<!-- 유저 아이디랑 책 번호 -->



<div id="contents">
<form action="directbuy" method="post">
<input type="hidden" name="id" id="id" value="${sessionScope.id}">
<input type="hidden" name="order_bookno" id="order_bookno" value="${newbook.nb_no}">


<table class="newbooktable" border="1" style="width: 80%; float: none; margin: auto;">
	<tr>
		<td colspan="3" style="text-align: center; background-color: #114c3d; color: white;">
			${newbook.nb_name}
		</td>
	</tr>
	<tr>
		<td colspan="3" style="text-align: right;">
			주문수 :${newbook.nb_scount} 조회수 :${newbook.nb_readcnt}
		</td>
	</tr>
	<tr>
		<td rowspan="4" style="text-align: center;">
			<img width="100" src="${newbook.nb_image}">
		</td>
		
		<c:choose>
			<c:when test="${newbook.nb_inter eq null}">
				<td colspan="2">
					저자 : ${newbook.nb_author}
				</td>
			</c:when>
			<c:otherwise>
				<td>
					저자 : ${newbook.nb_author}
				</td>
				<td>
					번역자 : ${newbook.nb_inter}
				</td>
			</c:otherwise>
		</c:choose>
		
		
	</tr>
	
	<tr>
		<td colspan="2">
			출판사 : ${newbook.nb_comp}
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
			출판년도 : ${newbook.nb_bdate}
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
			가격 : ${newbook.nb_price}
			<span class="user" id="user_price" style="color: red;"></span>
		</td>
	</tr>
	
	<tr>
		<td style="text-align: center;">
			배송비<br>무료
		</td>
		
		<td colspan="2">
			
			<div class="number">
			주문수량 :
			<a href="#" id="decreaseQuantity">-</a>
			
			<input id = "orderscount" name="orderscount" readonly="readonly" value="1" size = 1>
			
			<a href="#" id="increaseQuantity">+</a>
			
			</div>
		</td>
	</tr>
	
	<tr>
		<td colspan="3" style="text-align: left;">
			설명 : ${newbook.nb_plot}
		</td>
	</tr>
	<tr>
		<td colspan="3" style="text-align: center;">
			<input type="button" class="btn btn-outline-success" value="장바구니" onclick="cart();">
			<input type="submit" class="btn btn-outline-success" value="바로구매" >
			<!-- 바로 구매 할려고 넘어갈려면 책개수랑 북번호 만 필요하면 되나??? -->
		</td>
	</tr>
</table>
</form>
<hr>
<!-- 랜덤으로 3개만 띄우기 -->
<table class="newbooktable" style="width: 80%; float: none; margin: auto;">
	<tr>
		<td style="text-align: left; background-color: #114c3d; color: white;">
			저자의 다른 책
		</td>
	</tr>
	<tr>
	<c:choose>
		<c:when test="${f:length(authorList) eq 1}">
			<td>목록이 없습니다.</td>
		</c:when>
		<c:otherwise>
			<c:forEach var="author" items="${authorList}">
				<td style="text-align: center;">
				<a href="newbook?book_no=${author.nb_no}"><img width="100" src="${author.nb_image}"></a>
				<br>
					${author.nb_name}
				</td>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</tr>
</table>

<hr>
<table class="newbooktable" style="width: 80%; float: none; margin: auto;">
<!-- 리뷰 반복 구역 -->
	<tr>
		<td colspan="5" style="text-align: left; background-color: #114c3d; color: white;">리뷰</td>
	</tr>
	<tr>
		<th>작성자</th>
		<th>리뷰내용</th>
		<th>작성일시</th>
		<th>추천</th>
		<th>삭제</th>
	</tr>
	<c:forEach var="r" items="${reviewList}">
		<tr>
			<th>
				<p class="review_id">
					${r.review_id}
				</p>
			</th>
			<th>${r.review_context}</th>
			<th>${r.review_date}</th>
			<th>
				${r.review_gonggam}
				<input type="image" width="20" class="gonggam" src="resources/static/images/gonggam.png" value="추천" onclick="location.href='plusGonggam?review_no=${r.review_no}'">
			</th>
			<th>
				<input type="button" class="btn btn-outline-success" class="delete" value="삭제" onclick="location.href='deleteReview?review_no=${r.review_no}'">
			</th>
		</tr>
	</c:forEach>
</table>

<form action="writeReview" method="post">
<input type="hidden" value="${sessionScope.id}" name="review_id">
<input type="hidden" value="${newbook.nb_no}" name="review_bookno">

<table class="newbooktable" style="width:80%; float: none; margin: auto;">

	<tr style="justify-content:center;">
		<td style="width: 90%;">
			<textarea rows=5; cols=110; style="resize: none;" name="review_context"></textarea>
		</td>
		<td style="text-align: left;">
			<input type="submit" class="btn btn-outline-success" value="리뷰쓰기" id="writereview" name="writereview">
		</td>
	</tr>
</table>
</form>
<div id="footer_wrap">
<%@include file="footer.jsp" %>
</div>
</div>





</body>
</html>