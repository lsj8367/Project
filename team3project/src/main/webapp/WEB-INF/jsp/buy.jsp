<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%@ include file="oldheader.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>구매하기</title>
<% String space = " "; %>
<script type="text/javascript">
function pointcheck() {
   var list = "<fmt:parseNumber value='${buyinfo.ob_price * 0.5}' integerOnly='true'/>";
   //alert(list - buyfrm.writepoint.value); //10000
   var result = list - buyfrm.writepoint.value;
   if(buyfrm.writepoint.value > (buyfrm.point.value * 0.1)){
      alert("전체포인트의 10%만 사용이 가능합니다");
      buyfrm.writepoint.focus();
      return;
   }else{
      document.querySelector("#view").value=result;
      document.buyfrm.sum.value=list;
   }
   
}

function hideCard(){
   $(".mycard").hide();
}
function showCard(){
   $(".mycard").show();
}


function checkCardPassword(){
   
   var inputPwd = $('#card_password').val();
   var real = ${card.card_passwd};
   if(inputPwd != real){
      alert("비밀번호가 일치하지 않습니다.");
      $('#card_password').val('');
   }
}

</script>
<!-- 다음 주소  api -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<style type="text/css">
html,
body {
    margin:0;
    padding:0;
    height:100%;
}
#footer {
    position:absolute;
    bottom:0;
    width:100%;
    height:100px;   
}
.newbooktable {
  border: 1px solid #ccc;
  border-collapse: collapse;
  margin: auto;
  float : none;
  padding: 0;
  width: 30%;
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
  text-align: center;
}

.newbooktable th {
  font-size: .85em;
  letter-spacing: .1em;
  text-transform: uppercase;
  background-color: #37aacf;
  text-align: center;
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
</style>
</head>
<body>
<br>
<form action="buyresult" method="post" id="buyfrm" name="buyfrm">
<table class="newbooktable">
   <tr>
      <th colspan="2">책 정보</th>
   </tr>
      <c:choose>
      <c:when test="${sessionScope.id eq null}">
            <input type="hidden" name="ob_no" value="${buyinfo.ob_no}">
      <tr>
          <td>책제목</td> 
          <td><input type="text" name="ob_name" value="${buyinfo.ob_name }"></td>
      </tr>
      <tr>
         <td>이름</td>
         <td><input type="text" name="order_person"></td>
      </tr>
      <tr>
         <td>비밀번호 </td>
         <td><input type="password" name="order_password" placeholder="비밀번호 입력" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
      </tr>
      <tr>
         <td>가격</td>
         <td><fmt:parseNumber value="${buyinfo.ob_price * 0.5}" integerOnly="true"/> 원 </td>
      </tr>
      <tr>
         <td>주소</td>
         <td><input type="text" name="order_address" placeholder="주소를 입력해주세요"></td>
      </tr>
      <tr>
         <td>결제방법</td>
         <td>무통장입금</td>
      </tr>
         <input type="hidden" name="radioPaytype" value="무통장입금">
         <input type="hidden" name="order_sum" value="<fmt:parseNumber value='${buyinfo.ob_price * 0.5 }' integerOnly='true'/>"><br>
         <input type="hidden" name="order_id" value="<%= space%>">
         
      <tr>
         <td colspan="2"><input type="submit" class="btn btn-primary btn-lg" value="결제하기"></td>
      </tr>
         
      </c:when>
      
      <c:otherwise>
         <input type="hidden" name="ob_no" value="${buyinfo.ob_no}">
         <input type="hidden" name="order_password" value="${point.user_passwd }">
         <input type="hidden" name="ob_name" value="${buyinfo.ob_name }">
         <input type="hidden" name="order_person" value="${sessionScope.name }">
         <input type="hidden" name="order_id" value="${sessionScope.id }">
         <input type="hidden" name="order_password" value="''">
         <input type="hidden" value="${point.user_point }" id="point" name="point">
         <input type="hidden" name="order_address" value="${point.user_addr }">
      <tr>
         <td>책제목</td>
         <td>${buyinfo.ob_name }</td>
      </tr>
      <tr>
         <td>${point.user_name} 님의 사용가능한 포인트</td> 
         <td>${point.user_point} 점</td>
      </tr>
      <tr>
         <td>
            포인트 사용하기 
         </td>
         <td>
             <input type="text" id="writepoint" name="writepoint" size="10" placeholder="0">&nbsp;<input type="button" value="적용하기" class="btn btn-primary btn" onclick="pointcheck()">
         </td>      
      </tr>
      <tr>
         <td>가격</td>
         <td><fmt:parseNumber value="${buyinfo.ob_price * 0.5 }" integerOnly="true"/> 원 <br></td>
      </tr>
      <tr>
         <td>주소</td>
         <td>${point.user_addr}</td>
      </tr>
      <tr>
         <td>상세주소</td>
         <td><input type="text" name="add_address" placeholder="상세주소 입력"></td>
      </tr>
      <tr>
         <td>포인트 적용 후</td>
         <td><input type="text" id="view" name="order_sum"> 원</td>
      </tr>
      
      <tr>
         <td>결제방법</td>
         <td>
            <input type="radio" name="radioPaytype" id="pay" value="무통장입금" checked="checked" onclick="hideCard();">무통장입금
            <input type="radio" name="radioPaytype" id ="cardpay" value="카드결제" onclick="showCard();">카드결제
         </td>
      </tr>
      <tr class="mycard" style="display: none;">
         <td>
            ${point.user_name}님의 등록된 카드 정보
         </td>
         <td>
            <span></span><br>
            <span>카드사 : ${card.card_comp}</span><br>
            <span>카드번호 : ${card.card_no}</span><br>
            카드 비밀번호 : <input type="password" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" id="card_password" maxlength="4" name="card_password" size="3">
            <input type="button" name="check_cardpassword" class="btn btn-primary btn"  value="확인" onclick="checkCardPassword();">
            <input type="hidden" name="card_comp" value="${card.card_comp }">
            <input type="hidden" name="card_no" value="${card.card_no}">
         </td>
      </tr>
      <tr>
         <td colspan="2" style="text-align: center;">
         <input type="submit" class="btn btn-primary btn-lg" value="결제하기">
         </td>
      </tr>
      </c:otherwise>
   </c:choose>
</table>
</form>

<div id="footer">
<%@ include file="footer.jsp"%>
</div>
</body>
</html>