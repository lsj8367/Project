<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel='stylesheet' href='https://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>
  
<style>
body {
  font-family: 'TmoneyRoundWindExtraBold';
}


.wrapper {
  margin-top: 80px;
  margin-bottom: 80px;
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

<script type="text/javascript">
$(document).ready(function(){
   $(".error").hide();
   $("#checkSubmit").click( function(event){
      /** 사용자 아이디 체크 **/
      var userId = $("#id").val();
      var idLen = userId.length;
      
      if(idLen < 1){
         $("#errMsg_id").show();
         $("#errMsg_id").text("비어있음");
         //alert("aa");
         $("#id").focus();
         event.preventDefault();
         return false;
      }else{
         $("#errMsg_id").hide();
      }
      
      /** 사용자 비밀번호 체크 **/
      var userPwd1 = $("#pwd").val();
      var pwd1Len = userPwd1.length;
      
      if(pwd1Len < 1){
         $("#errMsg_pwd1").show();
         $("#errMsg_pwd1").text("비어있음");
         //alert("비어있음");
         $("#pwd1").focus();
         event.preventDefault();
         return false;
      }else{
         $("#errMsg_pwd1").hide();
      }
   });
});
</script>
<script type="text/javascript">
window.onunload = function () {
    window.opener.location.reload(); //opener로 login명령 했던 buymain에 값 전달해주고 새로고침 시키면서 자기는 닫아짐
    window.open("about:blank", "_self").close(); //근데 바로 닫으면 세션유지가 안되서 주소없는 창으로 되돌렸다가 닫는다.
}

</script>

</head>
<body translate="no" >
  <div class="wrapper">
    <form class="form-signin" action="login" method="post">       
      <h2 class="form-signin-heading" style="font-family: 'TmoneyRoundWindExtraBold';">Please login</h2>
      
      <input type="text" class="form-control" onKeyup="this.value=this.value.replace(/[^a-z0-9]/gi,'');" placeholder="UserID" name="id" id="id">
     <span class="error" id="errMsg_id" style="color: red;"></span>
      
      <input type="password" class="form-control" onKeyup="this.value=this.value.replace(/[^a-z0-9]/gi,'');" placeholder="Password" name ="pwd" id ="pwd">
     <span class="error" id="errMsg_pwd1" style="color: red;"></span>
      
      <button class="btn btn-lg btn-primary btn-block" type="submit" id="checkSubmit">Login</button>   
    </form>
  </div>
</body>
</html>