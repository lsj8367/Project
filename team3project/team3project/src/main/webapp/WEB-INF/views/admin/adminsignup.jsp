<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">

<title>직원 회원가입</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="resources/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/font/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/font/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="resources/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/css/util.css">
	<link rel="stylesheet" type="text/css" href="resources/css/adminmain.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form" action = "adminsignupok" method="post">
					<span class="login100-form-title p-b-26">
						Welcome
					</span>
					<span class="login100-form-title p-b-48">
						<i class="zmdi zmdi-font"></i>
					</span>

					<div class="wrap-input100 validate-input" data-validate="Enter or Rewrite admin_id">
						<span class="btn-id-check">
							<i class="zmdi zmdi-check"></i>
						</span>
						<input class="input100" type="text" name="admin_id" id="admin_id" onKeyup="this.value=this.value.replace(/[^a-z0-9]/gi,'');" >
						<span class="focus-input100" data-placeholder="admin_id"></span>
						<span class="error" id="errMsg_adminid" style="color: red;"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Enter or Rewrite admin_passwd">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
						<input class="input100" type="password" name="admin_passwd" id="admin_passwd"onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
						<span class="focus-input100" data-placeholder="admin_passwd"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Enter or Rewrite admin_name">
						<input class="input100" type="text" name="admin_name">
						<span class="focus-input100" data-placeholder="admin_name"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "사원,대리, 부장,과장만 가능">
						<input class="input100" type="text" name="admin_jik">
						<span class="focus-input100" data-placeholder="admin_jik"></span>
					</div>

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn">
								Sign up
							</button>
						</div>
					</div>
					<div class="text-center p-t-115">
						<span class="txt1">
							직원등록을 그만하시겠어요?
						</span>

						<a class="txt2" href="adminlogin">
							돌아가기
						</a>
						<br>
						<a class="txt3" href="main">
							메인으로
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/bootstrap/js/popper.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/daterangepicker/moment.min.js"></script>
	<script src="resources/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="resources/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="resources/js/adminmain.js"></script>

</body>
</html>