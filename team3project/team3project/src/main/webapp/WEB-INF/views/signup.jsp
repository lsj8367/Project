<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel='stylesheet' href='https://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>
  
<style>
body {
  font-family: 'TmoneyRoundWindExtraBold';
}


.wrapper {
  margin-top: 80px;
  margin-bottom: 80px;
  margin-left: 800px;
  margin-right: 800px;
}

.form-signin {
  max-width: 380px;
  padding: 15px 35px 45px;
  margin: 0 auto;
  background-color: #fff;
  border: 1px solid rgba(0, 0, 0, 0.1);
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 30px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  font-size: 16px;
  height: auto;
  padding: 10px;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 20px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>

 <script>
  window.console = window.console || function(t) {};
</script>

  
  
 <script>
  if (document.location.search.match(/type=embed/gi)) {
    window.parent.postMessage("resize", "*");
  }
</script>


<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- 다음 주소  api -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
//뒤로 돌아가기
function goBack(){
   window.history.back();
}
//초기화 버튼
function resetForm(){
   document.getElementById("userForm").reset();
}

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
           console.log(data.zonecode);
           console.log(fullRoadAddr);
           
          
           $("[name=zipcode]").val(data.zonecode);
           $("[name=address1]").val(fullRoadAddr);
           
           /* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
           document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
           document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
       }
    }).open();
}



$(document).ready(function(){
   $(".error").hide();
   $("#checkSubmit").click( function(event){
      /** 사용자 아이디 체크 **/
      var userId = $("#id").val();
      var idLen = userId.length;
      
      if(idLen < 1){
         $("#errMsg_id").show();
         $("#errMsg_id").text("비어있음");
         $("#id").focus();
         event.preventDefault();
         return;
      }else{
         $("#errMsg_id").hide();
      }
      
      /** 사용자 비밀번호 체크 **/
      var userPwd1 = $("#pwd1").val();
      var pwd1Len = userPwd1.length;
      
      if(pwd1Len < 1){
         $("#errMsg_pwd1").show();
         $("#errMsg_pwd1").text("비어있음");
         $("#pwd1").focus();
         event.preventDefault();
         return;
      }else{
         $("#errMsg_pwd1").hide();
      }
      
      /** 사용자 비밀번호2 체크 & 일치여부 **/
      var userPwd2 = $("#pwd2").val();
      var pwd2Len = userPwd2.length;
      
      if(pwd2Len < 1){
         $("#errMsg_pwd2").show();
         $("#errMsg_pwd2").text("비어있음");
         $("#pwd2").focus();
         event.preventDefault();
         return;
      }else if(userPwd2 != userPwd1){
         $("#errMsg_pwd2").show();
         $("#errMsg_pwd2").text("비밀번호 불일치");
         $("#pwd2").focus();
         event.preventDefault();
         return;
      }
      else{
         $("#errMsg_pwd2").hide();
      }
      
      /** 사용자 이름 체크 **/
      var userName = $("#name").val();
      var nameLen = userName.length;
      
      if(nameLen < 1){
         $("#errMsg_name").show();
         $("#errMsg_name").text("비어있음");
         $("#name").focus();
         event.preventDefault();
         return;
      }else{
         $("#errMsg_name").hide();
      }
      
      /** 사용자 주민등록번호 체크 **/
      var userRrnumber1 = $("#rrnumber1").val();
      var rrnumber1Len = userRrnumber1.length;
      
      var userRrnumber2 = $("#rrnumber2").val();
      var rrnumber2Len = userRrnumber2.length;
      if(rrnumber1Len < 6 || rrnumber2Len < 7){
         $("#errMsg_rrnumber").show();
         $("#errMsg_rrnumber").text("비어있음");
         $("#rrnumber1").focus();
         event.preventDefault();
         return;
      }      else{
         $("#errMsg_rrnumber").hide();
      }
      
      /** 사용자 전화번호 체크 **/
      var userPhone2 = $("#phone2").val();
      var userPhone3 = $("#phone3").val();
      var phone2Len = userPhone2.length;
      var phone3Len = userPhone3.length;
      if(phone2Len < 1 || phone3Len < 1){
         $("#errMsg_phone").show();
         $("#errMsg_phone").text("비어있음");
         $("#phone2").focus();
         event.preventDefault();
         return;
      }else{
         $("#errMsg_phone").hide();
      }
      
      /** 사용자 이메일 체크 **/
      var userEmail1 = $("#email1").val();
      var email1Len = userEmail1.length;
      if(email1Len < 1){
         $("#errMsg_email").show();
         $("#errMsg_email").text("비어있음");
         $("#email1").focus();
         event.preventDefault();
         return;
      }else{
         $("#errMsg_email").hide();
      }
      
      /** 사용자 주소 체크 **/
      var userZipcode = $("#zipcode").val();
      var zipcodeLen = userZipcode.length;
      if(zipcodeLen < 1){
         $("#errMsg_zipcode").show();
         $("#errMsg_zipcode").text("비어있음");
         $("#zipcode").focus();
         event.preventDefault();
         return;
      }else{
         $("#errMsg_email").hide();
      }
      
      var userAddress1 = $("#address1").val();
      var address1Len = userAddress1.length;
      if(address1Len < 1){
         $("#errMsg_address1").show();
         $("#errMsg_address1").text("비어있음");
         $("#address1").focus();
         event.preventDefault();
         return;
      }else{
         $("#errMsg_email").hide();
      }
      
      
      
      
      
      /** 사용자 카드 체크 **/
      var userCard1 = $("#card1").val();
      var userCard2 = $("#card2").val();
      var userCard3 = $("#card3").val();
      var userCard4 = $("#card4").val();
      var card1Len = userCard1.length;
      var card2Len = userCard2.length;
      var card3Len = userCard3.length;
      var card4Len = userCard4.length;
      if(card1Len < 4 || card2Len < 4 || card3Len < 4 || card4Len < 4 ){
         $("#errMsg_card").show();
         $("#errMsg_card").text("비어있음");
         $("#card1").focus();
         event.preventDefault();
         return;
      }else{
         $("#errMsg_card").hide();
      }
      
      /** 사용자 비밀번호 체크 **/
      var userCardPwd = $("#cardpwd").val();
      var cardPwdLen = userCardPwd.length;
      if(cardPwdLen < 1){
         $("#errMsg_cardpwd").show();
         $("#errMsg_cardpwd").text("비어있음");
         $("#cardpwd").focus();
         event.preventDefault();
         return;
      }else{
         $("#errMsg_cardpwd").hide();
      }
   });
});

$(function(){
   //아이디 중복체크
       $('#id').blur(function(){
           $.ajax({
           type:"POST",
           url:"checkSignupId",
           data:{
                  "id":$('#id').val()
           },
           success:function(data){   //data : checkSignup에서 넘겨준 결과값
                  if($.trim(data)=="YES"){
                     if($('#id').val()!="YES"){ 
                        $("#errMsg_id").show();
                         $("#errMsg_id").text("사용가능한 아이디 입니다");
                        

                         
                     }
                    }else{
                     	if($('#id').val()!="NO"){
                        $("#errMsg_id").show();
                         $("#errMsg_id").text("중복된 아이디 입니다");
                         $('#id').val('');
                         

                         
                     }
                  }
               }
          }) 
        })

   });


</script>
<script type="text/javascript">
window.onunload = function () {
	 window.opener.location.reload(); //opener로 login명령 했던 buymain에 값 전달해주고 새로고침 시키면서 자기는 닫아짐
	 window.open("about:blank", "_self").close(); //근데 바로 닫으면 세션유지가 안되서 주소없는 창으로 되돌렸다가 닫는다.
}
</script>
<%-- --include file="header.jsp"%>--%>
</head>
<body translate="no" >
  <div class="wrapper">
    <form class="form-signup" action="signup" method="post" id="userForm">       
      <h2 class="form-signup-heading" style="font-family: 'TmoneyRoundWindExtraBold';">Please Sign Up</h2>
      
      
     
     <label>아이디</label>
     <span class="error" id="errMsg_id" style="color: red; text-align: right;"></span>
     
     <input type="text" class="form-control" onKeyup="this.value=this.value.replace(/[^a-z0-9]/gi,'');" placeholder="UserID" name ="id" id ="id">
     
      <label>비밀번호</label>
     <span class="error"  id="errMsg_pwd1" style="color: red; text-align: right;"></span>      
      <input type="password" class="form-control" onKeyup="this.value=this.value.replace(/[^a-z0-9]/gi,'');" placeholder="Password" name ="pwd1" id ="pwd1">
     
      <label>비밀번호 확인</label>
     <span class="error"  id="errMsg_pwd2" style="color: red; text-align: right;"></span>
      <input type="password" class="form-control" onKeyup="this.value=this.value.replace(/[^a-z0-9]/gi,'');" placeholder="Password" name ="pwd2" id= "pwd2">
      
     
     <label>이름</label>
     <span class="error"  id="errMsg_name" style="color: red; text-align: right;"></span>
     <input type="text" class="form-control" onKeyup="this.value=this.value.replace(/[a-z0-9]/gi,'');" placeholder="ex)홍길동" name ="name" id = "name">
     
     <label>주민등록 번호</label>
     <span class="error" id="errMsg_rrnumber" style="color: red; text-align: right;"></span>
     <div class="mapper-input">
        <input type="text" class="small-input" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" name ="rrnumber1" id ="rrnumber1" maxlength="6" size="7">
        -
        <input type="password" class="small-input" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" name ="rrnumber2" id ="rrnumber2" maxlength="7" size="8">
     </div>
     
     
     <label>연락처</label>
     <span class="error" id="errMsg_phone" style="color: red;"></span>
     <div class="mapper-input">
     <select name ="phone1" class="small-input">
               <option selected="selected">010</option>
               <option>011</option>
     </select>
     -
     <input type="text" class="small-input" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" name ="phone2"  id ="phone2" maxlength="4" size="3">
     -
     <input type="text" class="small-input" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" name ="phone3" id ="phone3" maxlength="4" size="3">
     </div>
     
     <label>이메일</label>
     <span class="error" id="errMsg_email" style="color: red;"></span>
     <div class="mapper-input">
     <input type="text" class="small-input" onKeyup="this.value=this.value.replace(/[^a-z0-9]/gi,'');" name ="email1" id = "email1" size="10">
     @
     <select class="small-input" name = "eamil2" >
         <option selected="selected" value="hanmail.net">hanmail.net</option>
         <option>naver.com</option>
         <option>gmail.com</option>
     </select>
     </div>
     
     <label>우편번호</label>
     <span class="error" id="errMsg_zipcode" style="color: red;"></span>
     <div class="mapper-input">
        <input class="small-input" type="text" readonly="readonly" name ="zipcode" id ="zipcode">
      <input class="small-input" type="button" value="찾기" id="btnZip" onclick="execPostCode();">
     </div>
     
     
     <label>주소</label>
     <span class="error" id="errMsg_address1" style="color: red;"></span>
     <div class="mapper-input">
        <input class="small-input" type="text" readonly="readonly" name ="address1" id ="address1">
     </div>
     
     <label>상세주소</label>
     <span class="error" id="errMsg_address2" style="color: red;"></span>
     <div class="mapper-input">
     <input class="small-input" type="text" name ="address2" id = "address2">
     </div>
     
     <label>카드사</label>
     <span class="error" id="errMsg_address2" style="color: red;"></span>
     <div class="mapper-input">
     <select class="small-input" name = "cardcomp" >
               <option selected="selected" value="KB국민카드">KB국민카드</option>
               <option>신한카드</option>
               <option>하나카드</option>
               <option>롯데카드</option>
               <option>BC카드</option>
               <option>NH농협카드</option>
               <option>현대카드</option>
            </select>
     </div>
     
     <label>카드번호</label>
     <span class="error" id="errMsg_card" style="color: red;"></span>
     <div class="mapper-input">
              <input class="small-input"  type="text" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" name ="card1" id ="card1" maxlength="4" size="3">
            -
            <input class="small-input"  type="text" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" name ="card2" id ="card2" maxlength="4" size="3">
            -
            <input class="small-input"  type="text" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" name ="card3" id ="card3" maxlength="4" size="3">
            -
            <input class="small-input"  type="text" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" name ="card4" id ="card4" maxlength="4" size="3">
     </div> 
     
   
     <label>카드비밀번호</label>
     <span class="error" id="errMsg_cardpwd" style="color: red;"></span>
     <div class="mapper-input">
     <input class="small-input" type="password" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" name ="cardpwd" id ="cardpwd" maxlength="4" size="4">
     </div>
     
     <div class="mapper-input" style="text-align: center; vertical-align: center;">
     
      <input class="btn btn-outline-info" type="submit" class="form-control" value="가입" id="checkSubmit">
     <input class="btn btn-outline-info" type="button" class="form-control" value="초기화" onclick="resetForm();">
     <input class="btn btn-outline-info" type="button" class="form-control" value="뒤로가기" onclick="goBack();">
     </div>
         
    </form>
  </div>
  
  <!-- footer 크기만큼 주면 안 가려짐 -->
<div style="height: 100px;"></div>
<%-- 
<div id="footer">
<%@include file="footer.jsp" %>
</div>--%>
</body>

</html>