<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>바로구매 결제 페이지</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- 다음 주소  api -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>



<script type="text/javascript">
// 들어가자 마자 바로 카드결제 숨기기
$(document).ready(function() {
	$('.mycard').hide();
	$('.orderpass').hide();
	// 비회원이면 포인트 못씀
	
	$('#realpoint').val('0');
	$('#checkSubmit').click( function(event){		
		/** 주소 체크 **/
		var userAddress1 = $('#address1').val();
		var address1Len = userAddress1.length;
		
		if(address1Len < 1){
			$('#errMsg_address1').show();
			$('#errMsg_address1').text('비어있음');
			$('#address1').focus();
			event.preventDefault();
			return;
		}else{
			$('#errMsg_address1').hide();
		}
		
	});
	
	
	//비회원일때
	if(${sessionScope.id eq null}){
		$('.mypoint').hide();
		$('#orderpass').val('');
		$('#realpoint').val('');
		$('.orderpass').show();
		
		// 서밋을 누르면
		$('#checkSubmit').click( function(event){
			/** 비회원 주문번호 체크 **/
			var userOrderpwd = $('#orderpwd').val();
			var orderpwdLen = userOrderpwd.length;
			
			if(orderpwdLen < 4){
				$('#errMsg_orderpwd').show();
				$('#errMsg_orderpwd').text('비어있음');
				$('#orderpwd').focus();
				event.preventDefault();
				return;
			}else{
				$('#errMsg_orderpwd').hide();
			}
			
			
			
		});
	}

});


//카드 보여주는 건 회원만!
function showCard(){
	if(${sessionScope.id eq null}){
		$('#loginDialog').dialog({
		      title: '로그인 오류!',
		      modal: true, //팝업창으로 띄우기
		      width: '300', //폭
		      height: '300', // 높이
		      buttons:{ //다이얼로그 안에 버튼
	              "확인":function(){
	              $(this).dialog("close");
	              $("input:radio[name='radioPaytype']:radio[value='무통장입금']").prop("checked", true);
	            }
		     }
			});
					
	}else{
		//회원일 때
		$('.mycard').show();
		//카드를 사용할 경우 카드 비밀번호 필수 입력
		$('#checkSubmit').click( function(event){
			/** 카드 주문번호 체크 **/
			var userCard_password = $('#card_password').val();
			var card_passwordLen = userCard_password.length;
			
			if(card_passwordLen < 4){
				$('#errMsg_card_password').show();
				$('#errMsg_card_password').text('비어있음');
				$('#card_password').focus();
				event.preventDefault();
				return;
			}else{
				$('#errMsg_card_password').hide();
			}
			
		});
		
		
		
	}
}


function hideCard(){
	$('.mycard').hide();
}

function usePoint(){
	//포인트 다시 누르면 초기화
	$('#order_sum').val('${order_sum}'); 
	$('#point').val('${userDto.user_point}');//가지고 있는 포인트 값
	var usepoint = $('#usepoint').val();
	var limitpoint = $('#order_sum').val()*0.1;
	var point = $('#point').val();
	$('#realpoint').val(usepoint);
	
	
	
	if(usepoint > limitpoint){
		$('#pointDialog').dialog({
		      title: '포인트오류',
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
	else if(point < usepoint){
		$('#pointMinusDialog').dialog({
		      title: '포인트 부족',
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
	
	else{
		var order_sum = $('#order_sum').val();
		$('#order_sum').val(order_sum-usepoint);
		$('#point').val(point- usepoint);
	}
	
	if(usepoint == 0){
		$('#order_sum').val('${order_sum}');
	}
}

function checkCard(){
	// 뭘 못 가져오는 걸까....?
	if($('#realcardpwd').val()!= $('#card_password').val()){
		$('#cardPwdDialog').dialog({
		      title: '비밀번호가 틀렸습니다!',
		      modal: true, //팝업창으로 띄우기
		      width: '300', //폭
		      height: '300', // 높이
		      buttons:{ //다이얼로그 안에 버튼
	              "확인":function(){
	              $(this).dialog("close");
	              $('#card_password').val('');
	            }
		     }
			});
	}
};

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

           // 우편번호와 주소 정보를 해당 필드에 넣는다.
          
           console.log(fullRoadAddr);
           
          
          
           $("[name=address1]").val(fullRoadAddr);
           
           /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
           document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
           document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
       }
    }).open();
}
</script>
<style type="text/css">
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

#footer_wrap {width:100%;height:118px;clear:both;margin-top:30px; position: sticky;}



//테이블 적용
.directbuytable {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: 0;
  padding: 0;
  width: 100%;
  table-layout: fixed;
}

.directbuytable caption {
  font-size: 1.5em;
  margin: .5em 0 .75em;
}

.directbuytable tr {
  background-color: #fdfdfd;
  border: 1px solid #ddd;
  padding: .35em;
}

.directbuytable th,
.directbuytable td {
  padding: .625em;
  text-align: left;
}

.directbuytable th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
}

@media screen and (max-width: 600px) {
  .directbuytable {
    border: 0;
  }

  .directbuytable caption {
    font-size: 1.3em;
  }
  
  .directbuytable thead {
    border: none;
    clip: rect(0 0 0 0);
    height: 1px;
    margin: -1px;
    overflow: hidden;
    padding: 0;
    position: absolute;
    width: 1px;
  }
  
  .directbuytable tr {
    border-bottom: 3px solid #ddd;
    display: block;
    margin-bottom: .625em;
  }
  
  .directbuytable td {
    border-bottom: 1px solid #ddd;
    display: block;
    font-size: .8em;
    text-align: right;
  }
  
  .directbuytable td::before {
    /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
    content: attr(data-label);
    float: left;
    font-weight: bold;
    text-transform: uppercase;
  }
  
  .directbuytable td:last-child {
    border-bottom: 0;
  }
}
</style>

</head>
<%@include file="header.jsp"%>
<body style="overflow: auto; margin: 0; padding: 0; height: 100%">
<!-- dialog 띄우기 -->
<div id="loginDialog" style="display: none;">
   <p>로그인이 필요합니다.</p>
</div>
<div id="pointDialog" style="display: none;">
   <p>포인트는 금액의 10%만 사용가능합니다!</p>
</div>

<div id="pointMinusDialog" style="display: none;">
   <p>포인트가 부족합니다!</p>
</div>

<div id="cardPwdDialog" style="display: none;">
   <p>비밀번호를 다시 입력해주세요.</p>
</div>




<div id="contents">
<form method="get" action="directbuy_pay">
<input type="hidden" name="id" id="id" value="${sessionScope.id}">
<input type="hidden" name="order_bookno" id="order_bookno" value="${orderbook.nb_no}">
<input type="hidden" name="realcardpwd" id="realcardpwd" value="${cardDto.card_passwd}">
<input type="hidden" name="realpwd" id="realpwd">
<input type="hidden" name="realpoint" id="realpoint">
<table class="directbuytable"  border="1" style="width: 70%; float: none; margin: auto;">
	<tr>
		<td colspan="2" style="text-align: center; background-color: #114c3d; color: white;">
			바로구매 결제 페이지
		</td>
	</tr>
	<tr>
		<td style="width: 20%;">구매도서명</td>
		<td style="text-align: left;">
			<input name="orderbookname" readonly="readonly" value="${orderbook.nb_name}">
		</td>
	</tr>
	<tr>
		<td>주문권수</td>
		<td><input type = "text" readonly="readonly" name = "order_scount" value="${orderscount}"></td>
		<!-- 주문권수 어떻게 불러오냐???? -->
	</tr>
	<tr class="mypoint">
		<td>포인트 사용하기</td>
		<td>
			현재 보유하고 있는 포인트 : <input type="text" readonly="readonly" name="point" id="point" value="${userDto.user_point}" size=10>&nbsp;point<br>
			<input type="text" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" name="usepoint" id="usepoint" size=10>
			<button type="button" class="btn btn-outline-success" onclick="usePoint();" style="width: 100">포인트 사용하기</button>
		</td>
	</tr>
	<tr>
		<td>결제 방법</td>
		<td>
			<input type="radio" name="radioPaytype" id ="pay" value="무통장입금" checked="checked" onclick="hideCard();">무통장입금
			<input type="radio" name="radioPaytype" id ="cardpay" value="카드결제" onclick="showCard();">카드결제
		</td>
	</tr>
	<tr class="mycard">
		<td colspan="2">
			<div>
				<span>${userDto.user_name}님의 등록된 카드 정보</span><br>
				<span>카드사 : ${cardDto.card_comp}</span><br>
				<span>카드번호 : ${cardDto.card_no}</span><br>
				카드 비밀번호 : <input type="password" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" id="card_password" maxlength="4" name="card_password" size="5">
				<input type="button" class="btn btn-outline-success" name="check_cardpassword" id="check_cardpassword"   value="확인" onclick="checkCard();">
				<span class="error" id="errMsg_card_password" style="color: red;"></span>
			</div>
		</td>
	</tr>
	<tr>
		<td>금액</td>
		<td><input type = "text" readonly="readonly" id = "order_sum" name = "order_sum" value="${order_sum}"></td>
	</tr>
	<tr class="orderpass">
		<td>
			비회원 주문 비밀번호 : 
		</td>
		<td>
			<input type="text"  name="orderpwd" id="orderpwd"  maxlength="4" size="6">
			<span class="error" id="errMsg_orderpwd" style="color: red;"></span>
		</td>
	</tr>
	<tr >
		<td>
		
			배송 주소 :
		</td>
			
		<td style="vertical-align: center;">
			<input type="text" readonly="readonly" name="address1" id="address1"  >
			
			<input type="button" class="btn btn-outline-success" value="찾기" id="btnAddr" onclick="execPostCode();">
			<span class="error" id="errMsg_address1" style="color: red;"></span>
		</td>
	</tr>
	<tr >
		<td>
			상세 주소 : 
		</td>
		<td>
			<input type="text" name="address2" id="address2">
		</td>
	</tr>
	
	<tr>
		<td>금액</td>
		<td><input type = "text" readonly="readonly" id = "order_sum" name = "order_sum" value="${order_sum}"></td>
	</tr>
	<tr>
		<td colspan="2" style="text-align: center;">
			<input type="submit" class="btn btn-outline-success" value = "결제" id="checkSubmit">
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